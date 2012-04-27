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
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;

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
		Activity activity = loadActivity(
				"models/complexActivity/model.uml",
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

}
