/*
* Copyright (c) 2014 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Tanja Mayerhofer - initial API and implementation
*/
package org.modelexecution.fuml.trace.convert.uml2.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fuml.trace.convert.uml2.UML2TraceConverter;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.KernelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at) 
 *
 */
public class UML2TraceConverterTest {

	private static final String MODEL_FOLDER = "model/";
	private static final String OUTPUT_FOLDER = "output/";
	
	private static final String UML_MODEL_FILENAME = "conversiontest.uml";
	
	private static final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();
	
	private static ResourceSet resourceSet;
	private static Resource modelResource;
	private static IConversionResult modelConversionResult;
	
	private static TracemodelFactory fUMLTracemodelFactory = TracemodelFactory.eINSTANCE;
	
	@BeforeClass
	public static void initializeModel() {
		resourceSet = initializeResourceSet();
		modelResource = loadModel();
		modelConversionResult = convertModel(modelResource);
	}

	private static ResourceSet initializeResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
				UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		resourceSet.getPackageRegistry().put(TracemodelPackage.eNS_URI,
				TracemodelPackage.eINSTANCE);
		
		resourceSet.getPackageRegistry().put(org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage.eNS_URI,
				org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage.eINSTANCE);

		resourceSet.getPackageRegistry().put(KernelPackage.eNS_URI,
				KernelPackage.eINSTANCE);
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(".xmi", new XMIResourceFactoryImpl());
		
		return resourceSet;
	}
	
	private static Resource loadModel() {
		URI modelUri = URI.createFileURI(new File(MODEL_FOLDER + UML_MODEL_FILENAME).getAbsolutePath());
		Resource modelResource = resourceSet.getResource(modelUri, true);
		return modelResource;
	}
	
	private static IConversionResult convertModel(Resource resource) {
		EObject rootModelElement = resource.getContents().get(0);
		IConverter converter = converterRegistry.getConverter(rootModelElement);
		IConversionResult modelConversionResult = converter.convert(rootModelElement);
		return modelConversionResult;
	}
	
	@Test
	public void testTraceConversion() {
		Trace fumlTrace = fUMLTracemodelFactory.createTrace();
		
		org.modelexecution.fuml.trace.convert.IConverter traceConverter = new UML2TraceConverter();
		boolean canConvert = traceConverter.canConvert(fumlTrace, modelConversionResult);
		Assert.assertTrue(canConvert);
		org.modelexecution.fuml.trace.convert.IConversionResult traceConversionResult = traceConverter.convert(fumlTrace, modelConversionResult);
		
		org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace = traceConversionResult.getTrace();
		Assert.assertNotNull(umlTrace);
	}
	
	@Test
	public void testTracePersisting() {
		Trace fumlTrace = fUMLTracemodelFactory.createTrace();
		
		org.modelexecution.fuml.trace.convert.IConverter traceConverter = new UML2TraceConverter();
		org.modelexecution.fuml.trace.convert.IConversionResult traceConversionResult = traceConverter.convert(fumlTrace, modelConversionResult);
		org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace = traceConversionResult.getTrace();
		
		String outputFilename = "testTracePersisting.xmi";
		persistUML2Trace(umlTrace, outputFilename);
		
		Resource persistedTraceResource = loadUMLTrace(outputFilename);
		Assert.assertEquals(1, persistedTraceResource.getContents().size());
		EObject persistedTrace = persistedTraceResource.getContents().get(0);
		Assert.assertTrue(persistedTrace instanceof org.modelexecution.fuml.trace.uml2.tracemodel.Trace);
	}

	private Resource loadUMLTrace(String filename) {
		ResourceSet resourceSet = initializeResourceSet();
		URI uri = URI.createFileURI(new File(OUTPUT_FOLDER + filename).getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		return resource;
	}
	
	private void persistUML2Trace(org.modelexecution.fuml.trace.uml2.tracemodel.Trace trace, String outputFilename) {
		URI outputUri = URI.createFileURI(new File(OUTPUT_FOLDER + outputFilename).getAbsolutePath());
		Resource traceResource = resourceSet.createResource(outputUri);
		traceResource.getContents().add(trace);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		try {
			traceResource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}
	
	@Test
	public void testConversionRequiresModelConversionResult() {
		Trace trace = fUMLTracemodelFactory.createTrace();
		ActivityExecution activityExecution = fUMLTracemodelFactory.createActivityExecution();
		trace.getActivityExecutions().add(activityExecution);
		activityExecution.setActivity(new fUML.Syntax.Activities.IntermediateActivities.Activity());
		
		org.modelexecution.fuml.trace.convert.IConverter traceConverter = new UML2TraceConverter();
		boolean canConvert = traceConverter.canConvert(trace, modelConversionResult);
		Assert.assertFalse(canConvert);
	}

	@Test
	public void testActivityExecutionConversion() {
		Trace fumlTrace = fUMLTracemodelFactory.createTrace();
		
		Activity fumlActivity = modelConversionResult.getActivity("testActivity");
		ActivityExecution fumlActivityExecution1 = createFUMLActivityExecution(fumlActivity, 1);
		ActivityExecution fumlActivityExecution2 = createFUMLActivityExecution(fumlActivity, 2);
		fumlTrace.getActivityExecutions().add(fumlActivityExecution1);
		fumlTrace.getActivityExecutions().add(fumlActivityExecution2);
		
		org.modelexecution.fuml.trace.convert.IConverter traceConverter = new UML2TraceConverter();
		Assert.assertTrue(traceConverter.canConvert(fumlTrace, modelConversionResult));
		org.modelexecution.fuml.trace.convert.IConversionResult traceConversionResult = traceConverter.convert(fumlTrace, modelConversionResult);
		org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace = traceConversionResult.getTrace();
		Assert.assertNotNull(umlTrace);
		
		Assert.assertEquals(2, umlTrace.getActivityExecutions().size());
		org.eclipse.uml2.uml.Activity umlActivity = (org.eclipse.uml2.uml.Activity)modelConversionResult.getInputObject(fumlActivity);
		org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution umlActivityExecution1 = umlTrace.getActivityExecutions().get(0);
		Assert.assertTrue(umlActivity == umlActivityExecution1.getActivity());
		Assert.assertEquals(1, umlActivityExecution1.getActivityExecutionID());
		org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution umlActivityExecution2 = umlTrace.getActivityExecutions().get(1);
		Assert.assertTrue(umlActivity == umlActivityExecution2.getActivity());
		Assert.assertEquals(2, umlActivityExecution2.getActivityExecutionID());
		
		persistUML2Trace(umlTrace, "testActivityExecutionConversion.xmi");
	}

	private ActivityExecution createFUMLActivityExecution(Activity activity, int executionID) {
		ActivityExecution activityExecution = fUMLTracemodelFactory.createActivityExecution();
		activityExecution.setActivity(activity);
		activityExecution.setActivityExecutionID(executionID);
		return activityExecution;
	}
}
