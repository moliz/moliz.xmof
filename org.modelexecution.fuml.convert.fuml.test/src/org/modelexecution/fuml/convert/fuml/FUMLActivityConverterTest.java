/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 * Tanja Mayerhofer - implementation
 */
package org.modelexecution.fuml.convert.fuml;

import java.io.File;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IValueConversionResult;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Classes.Kernel.Class_;

public class FUMLActivityConverterTest {

	private ResourceSet resourceSet;

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();	
		resourceSet.getPackageRegistry().put(IntermediateActivitiesPackage.eNS_URI,
				IntermediateActivitiesPackage.eINSTANCE);
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
				"models/activity1.xmi",
				"activity1");
		FUMLConverter converter = new FUMLConverter();

		Assert.assertTrue(converter.canConvert(activity));
		Assert.assertTrue(converter.canConvert(activity.eResource()));
		Assert.assertFalse(converter.canConvert(null));
		Assert.assertFalse(converter.canConvert("NO"));
	}

	@Test
	public void testConvertingSimpleActivity() {
		Activity activity = loadActivity(
				"models/activity1.xmi",
				"activity1");
		FUMLConverter converter = new FUMLConverter();
		IConversionResult result = converter.convert(activity);
		
		Assert.assertEquals(1, result.getActivities().size());
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = result
				.getActivities().iterator().next();

		Assert.assertEquals(activity.getName(), fUMLActivity.name);
		Assert.assertEquals(activity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(activity.isActive(), fUMLActivity.isActive);
	}
	
	@Test
	public void testConvertingSimpleObject() {
		Resource classModelResource = getResource("models/class1.xmi");
		FUMLConverter converter = new FUMLConverter();
		IConversionResult result = converter.convert(classModelResource);
		
		Resource valueResource = getResource("models/object1.xmi");
		FUMLValueConverter valueConverter = new FUMLValueConverter(result);
		IValueConversionResult valueConversionResult = valueConverter.convert(valueResource);
		
		Assert.assertEquals(1, valueConversionResult.getExtensionalValues().size());
		Value outputValue = (Value)valueConversionResult.getExtensionalValues().iterator().next();
		Assert.assertTrue(outputValue instanceof Object_);
		Object_ outputObject = (Object_)outputValue;
		Assert.assertEquals(1, outputObject.types.size());
		Assert.assertTrue(outputObject.types.get(0) instanceof Class_);
		Class_ outputObjectType = (Class_)outputObject.types.get(0);
		Assert.assertEquals("class1", outputObjectType.name);
		Assert.assertEquals(1, outputObject.featureValues.size());
		FeatureValue objectFeature = outputObject.featureValues.get(0);
		Assert.assertEquals("attribute1", objectFeature.feature.name);
		Assert.assertEquals(1, objectFeature.values.size());
		Assert.assertTrue(objectFeature.values.get(0) instanceof StringValue);
		StringValue objectValue = (StringValue)objectFeature.values.get(0);
		Assert.assertEquals("string1", objectValue.value);
	}	

}
