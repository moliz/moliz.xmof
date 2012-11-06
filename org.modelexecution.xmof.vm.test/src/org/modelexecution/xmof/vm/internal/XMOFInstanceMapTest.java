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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.xmof.XMOFConverter;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Classes.Kernel.Feature;
import fUML.Syntax.Classes.Kernel.FeatureList;
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
		EObject eStudent1 = factory.getStudent1();
		Object_ fUMLStudent1 = instanceMap.getObject(eStudent1);
		assertEquals(getName(eStudent1), getName(fUMLStudent1));

		EObject eStudent2 = factory.getStudent2();
		Object_ fUMLStudent2 = instanceMap.getObject(eStudent2);
		assertEquals(getName(eStudent2), getName(fUMLStudent2));

		EObject eStudentSystem = factory.getStudentSystem();
		Object_ fUMLStudentSystem = instanceMap.getObject(eStudentSystem);
		assertEquals(getName(eStudentSystem), getName(fUMLStudentSystem));
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
