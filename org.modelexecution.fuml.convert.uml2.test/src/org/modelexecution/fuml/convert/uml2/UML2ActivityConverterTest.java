/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.uml2;

import java.io.File;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecificationAction;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;

import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural;
import fUML.Syntax.Classes.Kernel.Property;

public class UML2ActivityConverterTest {

	private ResourceSet resourceSet;

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
				UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	private Activity loadActivity(String path, String activityName) {
		return obtainActivity(getResource(path), activityName);
	}

	private Resource getResource(String path) {
		return resourceSet.getResource(
				URI.createFileURI(new File(path).getAbsolutePath()), true);
	}

	private Activity obtainActivity(Resource resource, String activityName) {
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator
				.hasNext();) {
			EObject next = iterator.next();
			if (next instanceof Activity) {
				Activity activity = (Activity) next;
				if (activityName.equals(activity.getName())) {
					return activity;
				}
			}
		}
		return null;
	}

	@Test
	public void testCanConvert() {
		Activity activity = loadActivity(
				"models/activityWithCreateObjectAction/model.uml",
				"TestActivity");
		UML2Converter converter = new UML2Converter();

		Assert.assertTrue(converter.canConvert(activity));
		Assert.assertTrue(converter.canConvert(activity.eResource()));
		Assert.assertFalse(converter.canConvert(null));
		Assert.assertFalse(converter.canConvert("NO"));
		Assert.assertTrue(converter.canConvert(activity.getEdges().get(0)));

	}

	@Test
	public void testSimpleFeatureValuesOfConvertedActivity() {
		Activity activity = loadActivity(
				"models/activityWithCreateObjectAction/model.uml",
				"TestActivity");
		UML2Converter converter = new UML2Converter();
		IConversionResult result = converter.convert(activity);

		Assert.assertEquals(1, result.getActivities().size());
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = result
				.getActivities().iterator().next();

		Assert.assertEquals(activity.getName(), fUMLActivity.name);
		Assert.assertEquals(activity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(activity.isActive(), fUMLActivity.isActive);
	}

	@Test
	public void testConvertingComplexActivity() {
		Activity activity = loadActivity("models/complexActivity/model.uml",
				"Activity1");
		UML2Converter converter = new UML2Converter();
		IConversionResult result = converter.convert(activity);

		Assert.assertEquals(1, result.getActivities().size());
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = result
				.getActivities().iterator().next();

		Assert.assertEquals(activity.getName(), fUMLActivity.name);
		Assert.assertEquals(activity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(activity.isActive(), fUMLActivity.isActive);
	}

	@Test
	public void testTypedElementMultiplicityElementConversion() {
		IConversionResult result = convertResource("models/typedElements/model.uml");

		Model model = (Model) result.getInput();
		Class person = (Class) model.getPackagedElement("Person");
		Class university = (Class) model.getPackagedElement("University");
		Assert.assertNotNull(person);
		Assert.assertNotNull(university);

		Class_ personfUML = (Class_) result.getFUMLElement(person);
		Class_ universityfUML = (Class_) result.getFUMLElement(university);
		Assert.assertNotNull(personfUML);
		Assert.assertNotNull(universityfUML);

		Assert.assertEquals(2, personfUML.attribute.size());
		Property namefUML_att = null;
		Property universityfUML_att = null;
		for (Property p : personfUML.attribute) {
			if (p.name.equals("name"))
				namefUML_att = p;
			else if (p.name.equals("university"))
				universityfUML_att = p;
		}
		Assert.assertNotNull(namefUML_att);
		Assert.assertNotNull(universityfUML_att);
		
		Assert.assertNotNull(namefUML_att.typedElement.type);
		Assert.assertNotNull(universityfUML_att.typedElement.type);
		
		Assert.assertEquals("String", namefUML_att.typedElement.type.name);
		Assert.assertEquals(universityfUML, universityfUML_att.typedElement.type);
		
		Assert.assertEquals(2, namefUML_att.multiplicityElement.lower);
		Assert.assertEquals(2, ((LiteralInteger)namefUML_att.multiplicityElement.lowerValue).value);
		Assert.assertEquals(5, namefUML_att.multiplicityElement.upper.naturalValue);
		Assert.assertEquals(5, ((LiteralUnlimitedNatural)namefUML_att.multiplicityElement.upperValue).value.naturalValue);
		
		Activity activity = (Activity) model.getPackagedElement("activity");
		Assert.assertNotNull(activity);
		
		Assert.assertEquals(1, activity.getOwnedParameters().size());
		Parameter parameter = activity.getOwnedParameters().get(0);
		fUML.Syntax.Classes.Kernel.Parameter parameterfUML = (fUML.Syntax.Classes.Kernel.Parameter)result.getFUMLElement(parameter);
		Assert.assertNotNull(parameterfUML);
		Assert.assertEquals(personfUML, parameterfUML.type);
		Assert.assertEquals(3, parameterfUML.multiplicityElement.lower);
		Assert.assertEquals(3, ((LiteralInteger)parameterfUML.multiplicityElement.lowerValue).value);
		Assert.assertEquals(6, parameterfUML.multiplicityElement.upper.naturalValue);
		Assert.assertEquals(6, ((LiteralUnlimitedNatural)parameterfUML.multiplicityElement.upperValue).value.naturalValue);
		
		ActivityParameterNode parameterNode = (ActivityParameterNode)activity.getNode("parameterNode");		
		ValueSpecificationAction valueAction = (ValueSpecificationAction)activity.getNode("specifyValue");
		Assert.assertNotNull(parameterNode);
		Assert.assertNotNull(valueAction);
		fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode parameterNodefUML = (fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode)result.getFUMLElement(parameterNode);
		fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction valueActionfUML = (fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction)result.getFUMLElement(valueAction);
		Assert.assertNotNull(parameterNodefUML);
		Assert.assertNotNull(valueActionfUML);
		
		Assert.assertEquals(personfUML, parameterNodefUML.typedElement.type);
		Assert.assertEquals("String", valueActionfUML.result.typedElement.type.name);
		Assert.assertEquals("String", valueActionfUML.value.type.name);
		
		Assert.assertEquals(4, valueActionfUML.result.multiplicityElement.lower);
		Assert.assertEquals(4, ((LiteralInteger)valueActionfUML.result.multiplicityElement.lowerValue).value);
		Assert.assertEquals(7, valueActionfUML.result.multiplicityElement.upper.naturalValue);
		Assert.assertEquals(7, ((LiteralUnlimitedNatural)valueActionfUML.result.multiplicityElement.upperValue).value.naturalValue);
		
		Assert.assertFalse(result.hasErrors());
	}

	private IConversionResult convertResource(String path) {
		Resource resource = getResource(path);
		NamedElement namedElement = obtainFirstNamedElement(resource);
		UML2Converter converter = new UML2Converter();
		return converter.convert(namedElement);
	}

	private NamedElement obtainFirstNamedElement(Resource resource) {
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof NamedElement)
				return (NamedElement) eObject;
		}
		return null;
	}
}
