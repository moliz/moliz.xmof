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
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.Before;
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
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at) 
 *
 */
public class UML2TraceConverterTest {

	private static final String ASSOCIATION_A_B = "association_a_b";
	private static final String PROPERTY_OBJECT_A = "objectA";
	private static final String PROPERTY_OBJECT_B = "objectB";
	
	private static final String CLASS_A = "ClassA";
	private static final String CLASS_B = "ClassB";
	
	private static final String MODEL_FOLDER = "model/";
	private static final String OUTPUT_FOLDER = "output/";
	
	private static final String UML_MODEL_FILENAME = "conversiontest.uml";
	
	private static final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();
	
	private static ResourceSet resourceSet;
	private static Resource modelResource;
	private static Model model;
	private static IConversionResult modelConversionResult;
	
	private static TracemodelFactory fUMLTracemodelFactory = TracemodelFactory.eINSTANCE;
	
	private org.modelexecution.fuml.trace.convert.IConversionResult traceConversionResult;
	private org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace;
	
	@Before
	public void resetTraceConversionResult() {
		traceConversionResult = null;
		umlTrace = null;
	}
	
	@BeforeClass
	public static void initializeModel() {
		resourceSet = initializeResourceSet();
		modelResource = loadModel();
		model = (Model)modelResource.getAllContents().next();		
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
		
		convertTrace(fumlTrace);
		
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
	
	private void convertTrace(Trace fumlTrace) {
		org.modelexecution.fuml.trace.convert.IConverter traceConverter = new UML2TraceConverter();
		boolean canConvert = traceConverter.canConvert(fumlTrace, modelConversionResult);
		Assert.assertTrue(canConvert);
		traceConversionResult = traceConverter.convert(fumlTrace, modelConversionResult);
		
		umlTrace = traceConversionResult.getTrace();
		
		Assert.assertNotNull(umlTrace);
	}
	
	@Test
	public void testRuntimeValueConversion() {
		// get UML model elements
		Class umlClass_ClassA = (Class)model.getOwnedType(CLASS_A);
		Class umlClass_ClassB = (Class)model.getOwnedType(CLASS_B);
		org.eclipse.uml2.uml.Property umlProperty_attributeA = umlClass_ClassA.getAllAttributes().get(0);
		org.eclipse.uml2.uml.Property umlProperty_attributeB = umlClass_ClassB.getAllAttributes().get(0);
		org.eclipse.uml2.uml.Association umlAssociation_a_b = (org.eclipse.uml2.uml.Association)model.getOwnedType(ASSOCIATION_A_B);
		org.eclipse.uml2.uml.Property umlProperty_objectA = umlAssociation_a_b.getOwnedEnd(PROPERTY_OBJECT_A, umlClass_ClassA);
		org.eclipse.uml2.uml.Property umlProperty_objectB = umlAssociation_a_b.getOwnedEnd(PROPERTY_OBJECT_B, umlClass_ClassB);
		
		// get converted fUML model elements
		Class_ fUMLClass_ClassA = (Class_)modelConversionResult.getFUMLElement(umlClass_ClassA);
		Class_ fUMLClass_ClassB = (Class_)modelConversionResult.getFUMLElement(umlClass_ClassB);
		Property fumlProperty_attributeA = (Property)modelConversionResult.getFUMLElement(umlProperty_attributeA);
		Property fumlProperty_attributeB = (Property)modelConversionResult.getFUMLElement(umlProperty_attributeB);
		Association fumlAssociation_a_b = (Association)modelConversionResult.getFUMLElement(umlAssociation_a_b);
		Property fumlProperty_objectA = (Property)modelConversionResult.getFUMLElement(umlProperty_objectA);
		Property fumlProperty_objectB = (Property)modelConversionResult.getFUMLElement(umlProperty_objectB);
		
		// create fUML values
		Value fumlValue_attributeA = createValue("value_attributeA");
		Value fumlValue_attributeB = createValue("value_attributeB");
		Object_ fumlObject_ObjectA = ActivityFactory.createObject(fUMLClass_ClassA);
		ActivityFactory.setObjectProperty(fumlObject_ObjectA, fumlProperty_attributeA, fumlValue_attributeA);
		Object_ fumlObject_ObjectB = ActivityFactory.createObject(fUMLClass_ClassB);
		ActivityFactory.setObjectProperty(fumlObject_ObjectB, fumlProperty_attributeB, fumlValue_attributeB);
		Reference fumlReference_ObjectA = createReference(fumlObject_ObjectA);
		Reference fumlReference_ObjectB = createReference(fumlObject_ObjectB);
		Link fumlLink_link1 = createLink(fumlAssociation_a_b, fumlProperty_objectA, fumlReference_ObjectA, fumlProperty_objectB, fumlReference_ObjectB);
		Link fumlLink_link2 = createLink(fumlAssociation_a_b, fumlProperty_objectA, fumlReference_ObjectA, fumlProperty_objectB, fumlReference_ObjectB);
		
		// create fUML trace with value instances for created fUML values (runtime values)		
		ValueInstance fumlValueInstance_attributeA = createValueInstance(fumlValue_attributeA);
		ValueInstance fumlValueInstance_attributeB = createValueInstance(fumlValue_attributeB);
		ValueInstance fumlValueInstance_ObjectA = createValueInstance(fumlObject_ObjectA);
		ValueInstance fumlValueInstance_ObjectB = createValueInstance(fumlObject_ObjectB);
		ValueInstance fumlValueInstance_link1 = createValueInstance(fumlLink_link1);
		ValueInstance fumlValueInstance_link2 = createValueInstance(fumlLink_link2);
		
		Trace fumlTrace = createTrace(fumlValueInstance_attributeA,
				fumlValueInstance_attributeB, fumlValueInstance_ObjectA,
				fumlValueInstance_ObjectB, fumlValueInstance_link1,
				fumlValueInstance_link2);
		
		// convert to UML trace 
		convertTrace(fumlTrace);
		
		// assert runtime values in UML trace
		Assert.assertEquals(6, umlTrace.getRuntimeValues().size());
		
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlValue_attributeA = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)traceConversionResult.getOutputUMLTraceElement(fumlValue_attributeA);
		Assert.assertEquals("value_attributeA", umlValue_attributeA.getValue());
		
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlValue_attributeB = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)traceConversionResult.getOutputUMLTraceElement(fumlValue_attributeB);
		Assert.assertEquals("value_attributeB", umlValue_attributeB.getValue());

		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object umlObject_ObjectA = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object)traceConversionResult.getOutputUMLTraceElement(fumlObject_ObjectA);
		Assert.assertEquals(umlClass_ClassA, umlObject_ObjectA.getTypes().get(0));
		Assert.assertEquals("value_attributeA", ((org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)umlObject_ObjectA.getFeatureValues().get(0).getValues().get(0)).getValue());
		Assert.assertFalse(umlValue_attributeA == umlObject_ObjectA.getFeatureValues().get(0).getValues().get(0));
		
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object umlObject_ObjectB = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object)traceConversionResult.getOutputUMLTraceElement(fumlObject_ObjectB);
		Assert.assertEquals(umlClass_ClassB, umlObject_ObjectB.getTypes().get(0));
		Assert.assertEquals("value_attributeB", ((org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)umlObject_ObjectB.getFeatureValues().get(0).getValues().get(0)).getValue());
		Assert.assertFalse(umlValue_attributeB == umlObject_ObjectB.getFeatureValues().get(0).getValues().get(0));
		
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Link umlLink1 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Link)traceConversionResult.getOutputUMLTraceElement(fumlLink_link1);
		Assert.assertEquals(umlAssociation_a_b, umlLink1.getType());
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference umlReference_ObjectA_1 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference)umlLink1.getFeatureValues().get(0).getValues().get(0);
		Assert.assertTrue(umlReference_ObjectA_1.getReferent() == umlObject_ObjectA);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference umlReference_ObjectB_1 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference)umlLink1.getFeatureValues().get(1).getValues().get(0);
		Assert.assertTrue(umlReference_ObjectB_1.getReferent() == umlObject_ObjectB);
		Assert.assertFalse(umlValue_attributeB == umlLink1.getFeatureValues().get(0).getValues().get(0));

		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Link umlLink2 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Link)traceConversionResult.getOutputUMLTraceElement(fumlLink_link2);
		Assert.assertEquals(umlAssociation_a_b, umlLink2.getType());
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference umlReference_ObjectA_2 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference)umlLink2.getFeatureValues().get(0).getValues().get(0);
		Assert.assertTrue(umlReference_ObjectA_2.getReferent() == umlObject_ObjectA);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference umlReference_ObjectB_2 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Reference)umlLink2.getFeatureValues().get(1).getValues().get(0);
		Assert.assertTrue(umlReference_ObjectB_2.getReferent() == umlObject_ObjectB);
		Assert.assertFalse(umlValue_attributeB == umlLink2.getFeatureValues().get(0).getValues().get(0));
		
		// assert containment of runtime values in trace
		Assert.assertTrue(umlTrace.getRuntimeValues().contains(umlValue_attributeA));
		Assert.assertTrue(umlTrace.getRuntimeValues().contains(umlValue_attributeB));
		Assert.assertTrue(umlTrace.getRuntimeValues().contains(umlObject_ObjectA));
		Assert.assertTrue(umlTrace.getRuntimeValues().contains(umlObject_ObjectB));
		Assert.assertTrue(umlTrace.getRuntimeValues().contains(umlLink2));
				
		// assert value instances in UML trace
		Assert.assertEquals(6, umlTrace.getValueInstances().size());
		Assert.assertTrue(umlTrace.getValueInstances().get(0).getRuntimeValue() == umlValue_attributeA);
		Assert.assertTrue(umlTrace.getValueInstances().get(1).getRuntimeValue() == umlValue_attributeB);
		Assert.assertTrue(umlTrace.getValueInstances().get(2).getRuntimeValue() == umlObject_ObjectA);
		Assert.assertTrue(umlTrace.getValueInstances().get(3).getRuntimeValue() == umlObject_ObjectB);
		Assert.assertTrue(umlTrace.getValueInstances().get(4).getRuntimeValue() == umlLink1);
		Assert.assertTrue(umlTrace.getValueInstances().get(5).getRuntimeValue() == umlLink2);
	}

	private ValueInstance createValueInstance(Value runtimeValue) {
		ValueInstance valueInstance_object1 = fUMLTracemodelFactory.createValueInstance();
		valueInstance_object1.setRuntimeValue(runtimeValue);
		return valueInstance_object1;
	}
	
	private Value createValue(String value) {
		StringValue stringValue = new StringValue();
		stringValue.value = value;
		return stringValue;
	}

	private Reference createReference(Object_ object) {
		Reference reference = new Reference();
		reference.referent = object;
		return reference;
	}
	
	private Link createLink(Association association,
			Property end1, Reference reference1, Property end2, Reference reference2) {
		Link link = new Link();
		link.type = association;
		
		FeatureValue featureValue1 = new FeatureValue();
		featureValue1.feature = end1;
		featureValue1.values.add(reference1);
		featureValue1.position = 0;
		
		FeatureValue featureValue2 = new FeatureValue();
		featureValue2.feature = end2;
		featureValue2.values.add(reference2);
		featureValue2.position = 0;
		
		link.featureValues.add(featureValue1);
		link.featureValues.add(featureValue2);
		
		return link;
	}
	
	private Trace createTrace(ValueInstance... valueInstances) {
		Trace fumlTrace = fUMLTracemodelFactory.createTrace();
		for(ValueInstance valueInstance : valueInstances) {
			fumlTrace.getValueInstances().add(valueInstance);
		}
		return fumlTrace;
	}
	
	@Test
	public void testOrderingOfConvertedValueSnapshots() {
		// create trace with one value instance containing three value snapshots
		Value fumlValue1 = createValue("value1");
		Value fumlValue2 = createValue("value2");
		Value fumlValue3 = createValue("value3");
		
		ValueSnapshot fumlValueSnapshot1 = createValueSnapshot(fumlValue1);
		ValueSnapshot fumlValueSnapshot2 = createValueSnapshot(fumlValue2);
		ValueSnapshot fumlValueSnapshot3 = createValueSnapshot(fumlValue3);
		
		ValueInstance fumlValueInstance = createValueInstance(null);
		fumlValueInstance.getSnapshots().add(fumlValueSnapshot1);
		fumlValueInstance.getSnapshots().add(fumlValueSnapshot2);
		fumlValueInstance.getSnapshots().add(fumlValueSnapshot3);
		
		Trace fumlTrace = createTrace(fumlValueInstance);
		
		// convert to UML trace
		convertTrace(fumlTrace);
		
		// assert correct order of value snapshots in UML trace
		Assert.assertEquals(1, umlTrace.getValueInstances().size());
		org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance umlValueInstance = umlTrace.getValueInstances().get(0);
		
		Assert.assertEquals(3, umlValueInstance.getSnapshots().size());
		
		org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot umlValueSnapshot1 = umlValueInstance.getSnapshots().get(0);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlValue1 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)umlValueSnapshot1.getValue();
		Assert.assertEquals("value1", umlValue1.getValue());
		
		org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot umlValueSnapshot2 = umlValueInstance.getSnapshots().get(1);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlValue2 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)umlValueSnapshot2.getValue();
		Assert.assertEquals("value2", umlValue2.getValue());
		
		org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot umlValueSnapshot3 = umlValueInstance.getSnapshots().get(2);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlValue3 = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue)umlValueSnapshot3.getValue();
		Assert.assertEquals("value3", umlValue3.getValue());
	}

	private ValueSnapshot createValueSnapshot(Value fumlValue) {
		ValueSnapshot valueSnapshot = fUMLTracemodelFactory.createValueSnapshot();
		valueSnapshot.setValue(fumlValue);
		return valueSnapshot;
	}
}
