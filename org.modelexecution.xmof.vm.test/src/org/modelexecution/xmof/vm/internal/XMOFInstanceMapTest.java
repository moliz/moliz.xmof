/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.xmof.XMOFConverter;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory;

import fUML.Semantics.Classes.Kernel.EnumerationValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Feature;
import fUML.Syntax.Classes.Kernel.FeatureList;
import fUML.Syntax.Classes.Kernel.PropertyList;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class XMOFInstanceMapTest {

	private XMOFInstanceMap instanceMap;
	private SimpleStudentSystemFactory factory;
	private IConversionResult conversionResult;

	@Before
	public void setUpSimpleStudentSystem() {
		factory = new SimpleStudentSystemFactory();
		Resource metamodelResource = factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();

		XMOFConverter converter = new XMOFConverter();
		conversionResult = converter.convert(metamodelResource);

		Locus locus = new Locus();

		instanceMap = new XMOFInstanceMap(conversionResult,
				modelResource.getContents(), locus);

	}

	@Test
	public void testExistenceOfObjectMappings() {
		assertNotNull(instanceMap.getObject(factory.getStudent1()));
		assertNotNull(instanceMap.getObject(factory.getStudent2()));
		assertNotNull(instanceMap.getObject(factory.getStudentSystem()));

		assertInverseMapping(factory.getStudent1());
		assertInverseMapping(factory.getStudent2());
		assertInverseMapping(factory.getStudentSystem());
	}

	private void assertInverseMapping(EObject eObject) {
		assertEquals(eObject,
				instanceMap.getEObject(instanceMap.getObject(eObject)));
	}

	@Test
	public void testObjectTypes() {
		assertEquals(factory.getStudentClass(),
				conversionResult.getInputObject(instanceMap.getObject(factory
						.getStudent1()).types.get(0)));
		assertEquals(factory.getStudentClass(),
				conversionResult.getInputObject(instanceMap.getObject(factory
						.getStudent2()).types.get(0)));
		assertFalse(factory.getStudentClass().equals(
				conversionResult.getInputObject(instanceMap.getObject(factory
						.getStudentSystem()).types.get(0))));
	}

	@Test
	public void testNameAttribute() {
		checkName(factory.getStudent1());
		checkName(factory.getStudent2());
		checkName(factory.getStudentSystem());
	}

	private void checkName(EObject eNamedObject) {
		Object_ fUMLStudent1 = instanceMap.getObject(eNamedObject);
		assertEquals(getName(eNamedObject), getName(fUMLStudent1));
	}

	private String getName(Object_ object) {
		FeatureValue value = object.getFeatureValue(getFeatureByName(object
				.getTypes().get(0).feature, "name"));
		StringValue firstValue = (StringValue) value.values.get(0);
		return firstValue.value;
	}

	private String getName(EObject eObject) {
		return (String) eObject.eGet(eObject.eClass().getEStructuralFeature(
				"name"));
	}

	@Test
	public void testLinks() {
		Collection<Link> links = getAllLinks(instanceMap.getExtensionalValues());
		// three links:
		// 1) studentsystem students> student 1
		// 1) studentsystem students> student 2
		// 3) student1 <knownBy knows> student2
		assertEquals(3, links.size());

		Object_ student1 = instanceMap.getObject(factory.getStudent1());
		Object_ student2 = instanceMap.getObject(factory.getStudent2());

		// check
		// studentsystem students> student 1 and
		// studentsystem students> student 2
		checkStudentsLinkWithStudent(links, student1);
		checkStudentsLinkWithStudent(links, student2);

		// check student1 <knownBy knows> student2
		Association knowsAssociation = (Association) conversionResult
				.getFUMLElement(getKnowsReference());
		StructuralFeature knowsProperty = getPropertyByName(
				knowsAssociation.memberEnd, "knows");
		StructuralFeature knownByProperty = getPropertyByName(
				knowsAssociation.memberEnd, "knownBy");

		Link student1KnowsStudent2 = getLinkBetween(links, student1, student2);
		assertNotNull(student1KnowsStudent2);
		assertEquals(2, student1KnowsStudent2.featureValues.size());
		assertEquals(knowsAssociation, student1KnowsStudent2.type);

		FeatureValue student1KnowsValue = student1KnowsStudent2
				.getFeatureValue(knowsProperty);
		assertEquals(1, student1KnowsValue.values.size());
		assertTrue(student1KnowsValue.values.contains(student2));

		FeatureValue student1KnownByValue = student1KnowsStudent2
				.getFeatureValue(knownByProperty);
		assertEquals(1, student1KnownByValue.values.size());
		assertTrue(student1KnownByValue.values.contains(student1));
	}

	private void checkStudentsLinkWithStudent(Collection<Link> links,
			Object_ student) {
		Object_ system = instanceMap.getObject(factory.getStudentSystem());
		Association studentsAssociation = (Association) conversionResult
				.getFUMLElement(getStudentsReference());
		Link systemStudentsStudent = getLinkBetween(links, system, student);
		assertNotNull(systemStudentsStudent);
		assertEquals(2, systemStudentsStudent.featureValues.size());
		assertEquals(studentsAssociation, systemStudentsStudent.type);
		FeatureValue studentSystemStudentsValue = systemStudentsStudent
				.getFeatureValue(getPropertyByName(
						studentsAssociation.memberEnd, "students"));
		assertEquals(1, studentSystemStudentsValue.values.size());
		assertTrue(studentSystemStudentsValue.values.contains(student));
	}

	private Object getStudentsReference() {
		return factory.getMainEClass().getEStructuralFeature("students");
	}

	private EStructuralFeature getKnowsReference() {
		return factory.getStudentClass().getEStructuralFeature("knows");
	}

	private StructuralFeature getPropertyByName(PropertyList properties,
			String name) {
		for (Feature feature : properties) {
			if (name.equals(feature.name)) {
				return (StructuralFeature) feature;
			}
		}
		return null;
	}

	private Collection<Link> getAllLinks(
			Collection<ExtensionalValue> extensionalValues) {
		Collection<Link> allLinks = new ArrayList<Link>();
		for (ExtensionalValue value : extensionalValues) {
			if (value instanceof Link) {
				allLinks.add((Link) value);
			}
		}
		return allLinks;
	}

	private Link getLinkBetween(Collection<Link> links, Object student1,
			Object student2) {
		for (Link link : links) {
			FeatureValue value1 = link.featureValues.get(0);
			FeatureValue value2 = link.featureValues.get(1);
			if ((value1.values.contains(student1) && value2.values
					.contains(student2))
					|| (value2.values.contains(student1) && value1.values
							.contains(student2))) {
				return link;
			}
		}
		return null;
	}

	@Test
	public void testStatusEnumerationValues() {
		checkStatusEnumerationValue(factory.getStudent1());
		checkStatusEnumerationValue(factory.getStudent2());
	}

	private void checkStatusEnumerationValue(EObject eStudent) {
		Object_ fUMLStudent = instanceMap.getObject(eStudent);
		assertEquals(getStatusEnumValue(eStudent),
				getStatusEnumValue(fUMLStudent));
	}

	private String getStatusEnumValue(Object_ object) {
		FeatureValue value = object.getFeatureValue(getFeatureByName(object
				.getTypes().get(0).feature, "status"));
		EnumerationValue firstValue = (EnumerationValue) value.values.get(0);
		return firstValue.literal.name;
	}

	private String getStatusEnumValue(EObject eObject) {
		return ((EEnumLiteral) eObject.eGet(eObject.eClass()
				.getEStructuralFeature("status"))).getName();
	}

	private StructuralFeature getFeatureByName(FeatureList featureList,
			String name) {
		for (Feature feature : featureList) {
			if (name.equals(feature.name)) {
				return (StructuralFeature) feature;
			}
		}
		return null;
	}

}
