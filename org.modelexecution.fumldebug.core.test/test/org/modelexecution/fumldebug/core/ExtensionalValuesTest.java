/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */

package org.modelexecution.fumldebug.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.CompleteActions.ReadExtentAction;
import fUML.Syntax.Actions.CompleteActions.ReduceAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Generalization;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExtensionalValuesTest implements ExecutionEventListener {
	
	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();

	public ExtensionalValuesTest() {
		ExecutionContext.getInstance().addEventListener(this);
	}
	
	@BeforeClass
	public static void setUpBeforeClass () throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		eventlist = new ArrayList<Event>();
		extensionalValueLists = new ArrayList<ExtensionalValueList>();
		ExecutionContext.getInstance().reset();		
		ExecutionContext.getInstance().addEventListener(this);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testReduceAction() {
		Activity activity = ActivityFactory.createActivity("testReduceAction");
		Parameter inparam = ActivityFactory.createParameter(activity, "input", ParameterDirectionKind.in);
		Parameter outparam = ActivityFactory.createParameter(activity, "output", ParameterDirectionKind.out);
		ActivityParameterNode inparamnode = ActivityFactory.createActivityParameterNode(activity, "input", inparam);
		ActivityParameterNode outparamnode = ActivityFactory.createActivityParameterNode(activity, "output", outparam);
		ReduceAction reduce = ActivityFactory.createReduceAction(activity, "add", ExecutionContext.getInstance().getOpaqueBehavior("add"));
		
		ActivityFactory.createObjectFlow(activity, inparamnode, reduce.collection);
		ActivityFactory.createObjectFlow(activity, reduce.result, outparamnode);
		
		IntegerValue integer1 = ActivityFactory.createIntegerValue(1);
		IntegerValue integer2 = ActivityFactory.createIntegerValue(2);
		IntegerValue integer3 = ActivityFactory.createIntegerValue(3);
		ParameterValue input = ActivityFactory.createParameterValue(inparam, integer1, integer2, integer3);
		ParameterValueList inputlist = ActivityFactory.createParameterValueList(input);
		
		ExecutionContext.getInstance().execute(activity, null, inputlist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		ParameterValueList outputlist = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertEquals(1, outputlist.size());
		assertEquals(outparam, outputlist.get(0).parameter);
		assertEquals(1, outputlist.get(0).values.size());
		assertTrue(outputlist.get(0).values.get(0) instanceof IntegerValue);
		assertEquals(6, ((IntegerValue)outputlist.get(0).values.get(0)).value);
	}
	
	@Test
	public void testReadExtentAction() {
		Class_ classA = ActivityFactory.createClass("A");
		Class_ classB = ActivityFactory.createClass("B");
		Class_ classC = ActivityFactory.createClass("C");
		
		Generalization generalizationAsuperofB = new Generalization();
		generalizationAsuperofB.general = classA;
		generalizationAsuperofB.specific = classB;
		classB.addGeneralization(generalizationAsuperofB);
		
		Generalization generalizationBsuperofC = new Generalization();
		generalizationBsuperofC.general = classB;
		generalizationBsuperofC.specific = classC;
		classC.addGeneralization(generalizationBsuperofC);
				
		Object_ oA = ActivityFactory.createObject(classA);
		Object_ oB = ActivityFactory.createObject(classB);
		Object_ oC = ActivityFactory.createObject(classC);
		
		Activity activity = ActivityFactory.createActivity("testReadExtentAction");
		Parameter paramA = ActivityFactory.createParameter(activity, "parameter for A instances", ParameterDirectionKind.out);
		Parameter paramB = ActivityFactory.createParameter(activity, "parameter for B instances", ParameterDirectionKind.out);
		Parameter paramC = ActivityFactory.createParameter(activity, "parameter for C instances", ParameterDirectionKind.out);
		ActivityParameterNode paramnodeA = ActivityFactory.createActivityParameterNode(activity, "parameter node for A instances", paramA);
		ActivityParameterNode paramnodeB = ActivityFactory.createActivityParameterNode(activity, "parameter node for B instances", paramB);
		ActivityParameterNode paramnodeC = ActivityFactory.createActivityParameterNode(activity, "parameter node for C instances", paramC);
		
		ReadExtentAction readA = ActivityFactory.createReadExtentAction(activity, "get all A", classA);
		ReadExtentAction readB = ActivityFactory.createReadExtentAction(activity, "get all B", classB);
		ReadExtentAction readC = ActivityFactory.createReadExtentAction(activity, "get all C", classC);
		
		ActivityFactory.createObjectFlow(activity, readA.result, paramnodeA);
		ActivityFactory.createObjectFlow(activity, readB.result, paramnodeB);
		ActivityFactory.createObjectFlow(activity, readC.result, paramnodeC);
		
		ExecutionContext.getInstance().getLocus().add(oA);
		ExecutionContext.getInstance().getLocus().add(oB);
		ExecutionContext.getInstance().getLocus().add(oC);
		
		assertEquals(3, ExecutionContext.getInstance().getExtensionalValues().size());
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(executionID);
		
		assertEquals(3, output.size());
		
		ParameterValue parametervalueA = null, parametervalueB = null, parametervalueC = null;
		
		for(ParameterValue paramvalue : output) {
			if(paramvalue.parameter.equals(paramA)) {
				parametervalueA = paramvalue;
			} else if(paramvalue.parameter.equals(paramB)) {
				parametervalueB = paramvalue;
			} else if(paramvalue.parameter.equals(paramC)) {
				parametervalueC = paramvalue;
			}
		}
		
		assertEquals(3, parametervalueA.values.size());
		List<Object_> objectsA = getObjects(parametervalueA);
		assertTrue(objectsA.contains(oA));
		assertTrue(objectsA.contains(oB));
		assertTrue(objectsA.contains(oC));
		
		assertEquals(2, parametervalueB.values.size());
		List<Object_> objectsB = getObjects(parametervalueB);
		assertFalse(objectsB.contains(oA));
		assertTrue(objectsB.contains(oB));
		assertTrue(objectsB.contains(oC));
		
		assertEquals(1, parametervalueC.values.size());
		List<Object_> objectsC = getObjects(parametervalueC);
		assertFalse(objectsC.contains(oA));
		assertFalse(objectsC.contains(oB));
		assertTrue(objectsC.contains(oC));
	}
	
	private List<Object_> getObjects(ParameterValue parametervalue) {
		List<Object_> objects = new ArrayList<Object_>();
		for(Value value : parametervalue.values) {
			if(value instanceof Object_) {
				objects.add((Object_)value);
			} else if(value instanceof Reference) {
				objects.add(((Reference)value).referent);
			}
		}
		return objects;
	}

	@Test
	public void testSingleCreateObjectAction() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleCreateObjectAction");
		Class_ class_person = ActivityFactory.createClass("Person");
		CreateObjectAction action = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person", class_person);
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(action, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(action, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());
		
		assertEquals(2, extensionalValueLists.size());
		assertEquals(0, extensionalValueLists.get(0).size());
		assertEquals(1, extensionalValueLists.get(1).size());
		assertTrue(extensionalValueLists.get(1).get(0) instanceof Object_);
		Object_ o = (Object_)(extensionalValueLists.get(1).get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
	}
	
	@Test
	public void testAddStructuralFeatureValueAction() {
		ExecutionContext executionContext = ExecutionContext.getInstance();
		
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, executionContext.getPrimitiveStringType(), class_person);
		
		Activity activity = new fUML.Syntax.Activities.IntermediateActivities.Activity();
		activity.setName("TestAddStructuralFeatureValueAction");		
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitialNode");
		CreateObjectAction createobject_tanja = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person tanja", class_person);
		ValueSpecificationAction valuespec_tanja =  ActivityFactory.createValueSpecificationAction(activity, "ValueSpecification tanja", "tanja");
		AddStructuralFeatureValueAction addstructuralfeaturevalue = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddStructuralFeatureValue Person Name", property_name);				
		
		ActivityFactory.createControlFlow(activity, initialnode, createobject_tanja);
		ActivityFactory.createControlFlow(activity, createobject_tanja, valuespec_tanja);
		ActivityFactory.createObjectFlow(activity, createobject_tanja.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_tanja.result, addstructuralfeaturevalue.value);
				
		executionContext.execute(activity, null, new ParameterValueList());
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_tanja, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_tanja, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		
		assertEquals(5, extensionalValueLists.size());
		assertEquals(0, extensionalValueLists.get(0).size());
		assertEquals(0, extensionalValueLists.get(1).size());
		
		for(int i=2;i<4;++i) {
			assertEquals(1, extensionalValueLists.get(i).size());
			assertTrue(extensionalValueLists.get(i).get(0) instanceof Object_);		
			Object_ o = (Object_)(extensionalValueLists.get(i).get(0));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(1, o.featureValues.size());
			assertEquals(property_name, o.featureValues.get(0).feature);
			assertEquals(0, o.featureValues.get(0).values.size());
		}
		
		assertEquals(1, extensionalValueLists.get(4).size());
		assertTrue(extensionalValueLists.get(4).get(0) instanceof Object_);		
		Object_ o = (Object_)(extensionalValueLists.get(4).get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
	}
	
	@Test
	public void testMultipleAddStructuralFeatureValueActions() {
		ExecutionContext executionContext = ExecutionContext.getInstance();
		
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, executionContext.getPrimitiveStringType(), class_person);
		
		Activity activity = new fUML.Syntax.Activities.IntermediateActivities.Activity();
		activity.setName("TestMultipleAddStructuralFeatureValueActions");
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitialNode");
		CreateObjectAction createobject_tanja = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person tanja", class_person);
		CreateObjectAction createobject_philip = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person philip", class_person);
		ValueSpecificationAction valuespec_tanja =  ActivityFactory.createValueSpecificationAction(activity, "ValueSpecification tanja", "tanja");
		ValueSpecificationAction valuespec_philip =  ActivityFactory.createValueSpecificationAction(activity, "ValueSpecification philip", "philip");		
		AddStructuralFeatureValueAction addstructuralfeaturevalue = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddStructuralFeatureValue Person Name", property_name);				
		
		ActivityFactory.createControlFlow(activity, initialnode, createobject_tanja);
		ActivityFactory.createControlFlow(activity, createobject_tanja, valuespec_tanja);
		ActivityFactory.createControlFlow(activity, valuespec_tanja, createobject_philip);
		ActivityFactory.createControlFlow(activity, createobject_philip, valuespec_philip);
		//ActivityFactory.createControlFlow(activity, valuespec_philip, addstructuralfeaturevalue);

		ActivityFactory.createObjectFlow(activity, createobject_tanja.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_tanja.result, addstructuralfeaturevalue.value);
		ActivityFactory.createObjectFlow(activity, createobject_philip.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_philip.result, addstructuralfeaturevalue.value);	
		
		executionContext.execute(activity, null, new ParameterValueList());
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		List<ActivityNode> nodeorder = new ArrayList<ActivityNode>();
		nodeorder.add(initialnode);
		nodeorder.add(createobject_tanja);
		nodeorder.add(valuespec_tanja);
		nodeorder.add(addstructuralfeaturevalue);
		nodeorder.add(createobject_philip);
		nodeorder.add(valuespec_philip);
		nodeorder.add(addstructuralfeaturevalue);
		
		for(int i=0;i<nodeorder.size();++i) {
			assertTrue(eventlist.get(1+2*i) instanceof ActivityNodeEntryEvent);
			assertEquals(nodeorder.get(i), ((ActivityNodeEntryEvent)eventlist.get(1+2*i)).getNode());		
			assertTrue(eventlist.get(2+2*i) instanceof ActivityNodeExitEvent);
			assertEquals(nodeorder.get(i), ((ActivityNodeExitEvent)eventlist.get(2+2*i)).getNode());
		}

		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		
		assertEquals(8, extensionalValueLists.size());
		assertEquals(0, extensionalValueLists.get(0).size());
		assertEquals(0, extensionalValueLists.get(1).size());
		
		for(int i=2;i<4;++i) {
			assertEquals(1, extensionalValueLists.get(i).size());
			assertTrue(extensionalValueLists.get(i).get(0) instanceof Object_);		
			Object_ o = (Object_)(extensionalValueLists.get(i).get(0));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(1, o.featureValues.size());
			assertEquals(property_name, o.featureValues.get(0).feature);
			assertEquals(0, o.featureValues.get(0).values.size());
		}
		
		assertEquals(1, extensionalValueLists.get(4).size());
		assertTrue(extensionalValueLists.get(4).get(0) instanceof Object_);		
		Object_ o = (Object_)(extensionalValueLists.get(4).get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
		
		for(int i=5;i<7;++i) {
			assertEquals(2, extensionalValueLists.get(i).size());
			assertTrue(extensionalValueLists.get(i).get(0) instanceof Object_);		
			o = (Object_)(extensionalValueLists.get(i).get(0));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(1, o.featureValues.size());
			assertEquals(property_name, o.featureValues.get(0).feature);
			assertEquals(1, o.featureValues.get(0).values.size());
			assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
			assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
			
			assertTrue(extensionalValueLists.get(i).get(1) instanceof Object_);		
			o = (Object_)(extensionalValueLists.get(i).get(1));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(1, o.featureValues.size());
			assertEquals(property_name, o.featureValues.get(0).feature);
			assertEquals(0, o.featureValues.get(0).values.size());
		}
		
		assertEquals(2, extensionalValueLists.get(7).size());
		assertTrue(extensionalValueLists.get(7).get(0) instanceof Object_);		
		o = (Object_)(extensionalValueLists.get(7).get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
		
		assertTrue(extensionalValueLists.get(7).get(1) instanceof Object_);		
		o = (Object_)(extensionalValueLists.get(7).get(1));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("philip", ((StringValue)o.featureValues.get(0).values.get(0)).value);
	}	
	
	@Override
	public void notify(Event event) {	
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
		}
		if(event instanceof ActivityNodeEntryEvent || event instanceof ActivityExitEvent) {
			ExtensionalValueList list = new ExtensionalValueList();
			for(int i=0;i<ExecutionContext.getInstance().getExtensionalValues().size();++i) {
				if(ExecutionContext.getInstance().getExtensionalValues().get(i).getClass() == Object_.class) {
					//list.add(ExecutionContext.getInstance().getExtensionalValues().get(i));
					list.add(copyObject((Object_)ExecutionContext.getInstance().getExtensionalValues().get(i)));
				}
			}
			extensionalValueLists.add(list);
		}
	}
	
	
	private Object_ copyObject(Object_ object) {
		Object_ newObject = new Object_();
		for (int i = 0; i < object.types.size(); i++) {
			newObject.types.addValue(object.types.getValue(i));
		}
		
		for (int i = 0; i < object.featureValues.size(); i++) {
			FeatureValue featureValue = object.featureValues.getValue(i);
			FeatureValue newFeatureValue = new FeatureValue();
			newFeatureValue.feature = featureValue.feature;
			newFeatureValue.position = featureValue.position;
			for(int j=0;j<featureValue.values.size();++j) {
				if(featureValue.values.get(j) instanceof PrimitiveValue) {
					newFeatureValue.values.add(featureValue.values.get(j).copy());
				} else if(featureValue.values.get(j) instanceof Object_) {
					newFeatureValue.values.add(copyObject((Object_)featureValue.values.get(j)));
				} 
			}			
			newObject.featureValues.add(newFeatureValue);						
		}
		
		return newObject;		
	}
}
