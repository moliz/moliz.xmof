/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */

package org.modelexecution.fumldebug.core.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
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
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * @author Tanja Mayerhofer
 *
 */
public class DebugTest implements ExecutionEventListener{

	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();
	
	public DebugTest() {
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventlist = new ArrayList<Event>();
		extensionalValueLists = new ArrayList<ExtensionalValueList>();
		ExecutionContext.getInstance().reset();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActivityWihtoutNodes() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityWihtoutNodes");
		
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());		
	}
	
	@Test
	public void testActivityWithoutEnabledNodes() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityWithoutEnabledNodes");
		MergeNode merge1 = ActivityFactory.createMergeNode(activity, "MergeNode 1");
		MergeNode merge2 = ActivityFactory.createMergeNode(activity, "MergeNode 2");
		
		ActivityFactory.createControlFlow(activity, merge1, merge2);
		ActivityFactory.createControlFlow(activity, merge2, merge1);
		
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());		
	}
	
	@Test
	public void testActivitySingleInitialNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivitySingleInitialNode");
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitilNode");
		
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof StepEvent);
		//assertEquals(initialnode, ((StepEvent)eventlist.get(1)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testMultipleAddStructuralFeatureValueActions() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, class_person);
		
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
		
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
		
		/*
		 * ActivityStart
		 * Step location = null
		 */
		assertEquals(2, eventlist.size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes().get(0));	
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());		
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertEquals(null, ((StepEvent)eventlist.get(1)).getLocation());	
		
		ExecutionContext.getInstance().nextStep();
	
		/*
		 * ActivityNodeEntry initial
		 * ActivityNodeExit initial
		 * Step location = initial
		 */
		assertEquals(5, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(createobject_tanja, ExecutionContext.getInstance().getEnabledNodes().get(0));				
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());					
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertTrue(eventlist.get(4) instanceof StepEvent);
		assertEquals(initialnode, ((StepEvent)eventlist.get(4)).getLocation());	
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry create tanja
		 * ActivityNodeExit create tanja
		 * Step location = create tanja
		 */
		assertEquals(8, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(valuespec_tanja, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_tanja, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());					
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_tanja, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertTrue(eventlist.get(7) instanceof StepEvent);
		assertEquals(createobject_tanja, ((StepEvent)eventlist.get(7)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry value tanja
		 * ActivityNodeExit value tanja
		 * Step location = value tanja 
		 */
		assertEquals(11, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes().size());	
		assertTrue(ExecutionContext.getInstance().getEnabledNodes().contains(createobject_philip));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes().contains(addstructuralfeaturevalue));
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());					
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertTrue(eventlist.get(10) instanceof StepEvent);
		assertEquals(valuespec_tanja, ((StepEvent)eventlist.get(10)).getLocation());
		
		ExecutionContext.getInstance().nextStep(addstructuralfeaturevalue);
		
		/*
		 * ActivityNodeEntry add tanja
		 * ActivityNodeExit add tanja
		 * Step location = add tanja
		 */
		assertEquals(14, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(createobject_philip, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());					
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertTrue(eventlist.get(13) instanceof StepEvent);
		assertEquals(addstructuralfeaturevalue, ((StepEvent)eventlist.get(13)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry create philip
		 * ActivityNodeExit create philip
		 * Step location = create philip
		 */
		assertEquals(17, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(valuespec_philip, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_philip, ((ActivityNodeEntryEvent)eventlist.get(14)).getNode());					
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_philip, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertTrue(eventlist.get(16) instanceof StepEvent);
		assertEquals(createobject_philip, ((StepEvent)eventlist.get(16)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry value philip
		 * ActivityNodeExit value philip
		 * Step location = value philip
		 */
		assertEquals(20, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_philip, ((ActivityNodeEntryEvent)eventlist.get(17)).getNode());					
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_philip, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		assertTrue(eventlist.get(19) instanceof StepEvent);
		assertEquals(valuespec_philip, ((StepEvent)eventlist.get(19)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry add philip
		 * ActivityNodeExit add philip
		 * ActivityExit
		 */		
		assertEquals(23, eventlist.size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes().size());
		
		assertTrue(eventlist.get(20) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(20)).getNode());					
		assertTrue(eventlist.get(21) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(21)).getNode());
		assertTrue(eventlist.get(22) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(22)).getActivity());
					
		List<ActivityNode> nodeorder = new ArrayList<ActivityNode>();
		nodeorder.add(initialnode);
		nodeorder.add(createobject_tanja);
		nodeorder.add(valuespec_tanja);
		nodeorder.add(addstructuralfeaturevalue);
		nodeorder.add(createobject_philip);
		nodeorder.add(valuespec_philip);
		nodeorder.add(addstructuralfeaturevalue);
				
		assertEquals(8, extensionalValueLists.size());
		assertEquals(0, extensionalValueLists.get(0).size());
		assertEquals(0, extensionalValueLists.get(1).size());
		
		for(int i=2;i<4;++i) {
			assertEquals(1, extensionalValueLists.get(i).size());
			assertTrue(extensionalValueLists.get(i).get(0) instanceof Object_);		
			Object_ o = (Object_)(extensionalValueLists.get(i).get(0));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(0, o.featureValues.size());
			//assertEquals(1, o.featureValues.size());
			//assertEquals(property_name, o.featureValues.get(0).feature);
			//assertEquals(0, o.featureValues.get(0).values.size());
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
			assertEquals(0, o.featureValues.size());
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
	}
	
	@Test
	public void testMultipleAddStructuralFeatureValueActions2() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, class_person);
		
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

		ActivityFactory.createObjectFlow(activity, createobject_tanja.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_tanja.result, addstructuralfeaturevalue.value);
		ActivityFactory.createObjectFlow(activity, createobject_philip.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_philip.result, addstructuralfeaturevalue.value);	
		
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
		
		/*
		 * ActivityStart
		 * Step location = null
		 */
		assertEquals(2, eventlist.size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes().get(0));	
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());		
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertEquals(null, ((StepEvent)eventlist.get(1)).getLocation());	
		
		ExecutionContext.getInstance().nextStep();
	
		/*
		 * ActivityNodeEntry initial
		 * ActivityNodeExit initial
		 * Step location = initial
		 */
		assertEquals(5, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(createobject_tanja, ExecutionContext.getInstance().getEnabledNodes().get(0));				
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());					
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertTrue(eventlist.get(4) instanceof StepEvent);
		assertEquals(initialnode, ((StepEvent)eventlist.get(4)).getLocation());	
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry create tanja
		 * ActivityNodeExit create tanja
		 * Step location = create tanja
		 */
		assertEquals(8, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(valuespec_tanja, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_tanja, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());					
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_tanja, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertTrue(eventlist.get(7) instanceof StepEvent);
		assertEquals(createobject_tanja, ((StepEvent)eventlist.get(7)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry value tanja
		 * ActivityNodeExit value tanja
		 * Step location = value tanja 
		 */
		assertEquals(11, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes().size());	
		assertTrue(ExecutionContext.getInstance().getEnabledNodes().contains(createobject_philip));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes().contains(addstructuralfeaturevalue));
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());					
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertTrue(eventlist.get(10) instanceof StepEvent);
		assertEquals(valuespec_tanja, ((StepEvent)eventlist.get(10)).getLocation());
		
		ExecutionContext.getInstance().nextStep(createobject_philip);
		
		/*
		 * ActivityNodeEntry create philip
		 * ActivityNodeExit create philip
		 * Step location = create philip
		 */
		assertEquals(14, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(valuespec_philip, ExecutionContext.getInstance().getEnabledNodes().get(0));
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes().get(1));		
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_philip, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());					
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_philip, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertTrue(eventlist.get(13) instanceof StepEvent);
		assertEquals(createobject_philip, ((StepEvent)eventlist.get(13)).getLocation());
		
		ExecutionContext.getInstance().nextStep(valuespec_philip);
		
		/*
		 * ActivityNodeEntry value philip
		 * ActivityNodeExit value philip
		 * Step location = value philip
		 */
		assertEquals(17, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_philip, ((ActivityNodeEntryEvent)eventlist.get(14)).getNode());					
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_philip, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertTrue(eventlist.get(16) instanceof StepEvent);
		assertEquals(valuespec_philip, ((StepEvent)eventlist.get(16)).getLocation());
		
		ExecutionContext.getInstance().nextStep(addstructuralfeaturevalue);
		
		/*
		 * ActivityNodeEntry add philip
		 * ActivityNodeExit add philip
		 * Step location = add philip
		 */
		assertEquals(20, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes().size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes().get(0));
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(17)).getNode());					
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		assertTrue(eventlist.get(19) instanceof StepEvent);
		assertEquals(addstructuralfeaturevalue, ((StepEvent)eventlist.get(19)).getLocation());
		
		ExecutionContext.getInstance().nextStep();
		
		/*
		 * ActivityNodeEntry add tanja
		 * ActivityNodeExit add tanja
		 * ActivityExit
		 */		
		assertEquals(23, eventlist.size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes().size());
		
		assertTrue(eventlist.get(20) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(20)).getNode());					
		assertTrue(eventlist.get(21) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(21)).getNode());
		assertTrue(eventlist.get(22) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(22)).getActivity());
					
		List<ActivityNode> nodeorder = new ArrayList<ActivityNode>();
		nodeorder.add(initialnode);
		nodeorder.add(createobject_tanja);
		nodeorder.add(valuespec_tanja);
		nodeorder.add(createobject_philip);
		nodeorder.add(valuespec_philip);
		nodeorder.add(addstructuralfeaturevalue);
		nodeorder.add(addstructuralfeaturevalue);
				
		assertEquals(8, extensionalValueLists.size());
		assertEquals(0, extensionalValueLists.get(0).size());
		assertEquals(0, extensionalValueLists.get(1).size());
		
		/*
		 * Person object from CreateObjectAction Tanja 
		 */
		for(int i=2;i<4;++i) {
			assertEquals(1, extensionalValueLists.get(i).size());
			assertTrue(extensionalValueLists.get(i).get(0) instanceof Object_);		
			Object_ o = (Object_)(extensionalValueLists.get(i).get(0));
			assertEquals(1, o.types.size());
			assertEquals(class_person, o.types.get(0));
			assertEquals(0, o.featureValues.size());
		}
		
		/*
		 * Person objects from CreateObjectAction Tanja and CreateObjectAction Philip
		 */
		for(int i=4;i<6;++i) {
			assertEquals(2, extensionalValueLists.get(i).size());
			for(int j=0;j<2;++j) {
				assertTrue(extensionalValueLists.get(i).get(j) instanceof Object_);		
				Object_ o = (Object_)(extensionalValueLists.get(i).get(j));
				assertEquals(1, o.types.size());
				assertEquals(class_person, o.types.get(0));
				assertEquals(0, o.featureValues.size());
			}
		}
		
		/*
		 * Name was set for Person object from CreateObjectAction Tanja 
		 */
		assertEquals(2, extensionalValueLists.get(6).size());
		
		assertTrue(extensionalValueLists.get(6).get(0) instanceof Object_);		
		Object_ o = (Object_)(extensionalValueLists.get(6).get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
		
		assertTrue(extensionalValueLists.get(6).get(1) instanceof Object_);		
		o = (Object_)(extensionalValueLists.get(6).get(1));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(0, o.featureValues.size());
			
		/*
		 * Name was set for Person object from CreateObjectActipn Philip
		 */
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
		eventlist.add(event);
		
		if(event instanceof StepEvent || event instanceof ActivityExitEvent) {
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
