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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modeldriven.fuml.library.listfunctions.ListGetFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.impl.BreakpointImpl;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Actions.BasicActions.CallBehaviorActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.BasicActions.OutputPinList;
import fUML.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ReadSelfAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.TestIdentityAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.ParameterList;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.PropertyList;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * @author Tanja Mayerhofer
 *
 */
public class DebugTest extends MolizTest implements ExecutionEventListener{

	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();
	
	public DebugTest() {
		ExecutionContext.getInstance().reset();
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
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExpansionRegionWithInputPin() {
		Class_ cl = ActivityFactory.createClass("Person");
		Property name = ActivityFactory.createProperty("name", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), cl);
		
		Activity activity = ActivityFactory.createActivity("testExpansionRegionWithInputPin");
		Parameter paramin = ActivityFactory.createParameter(activity, "in", ParameterDirectionKind.in);
		Parameter paramout = ActivityFactory.createParameter(activity, "out", ParameterDirectionKind.out);
		ActivityParameterNode innode = ActivityFactory.createActivityParameterNode(activity, "in", paramin);
		ActivityParameterNode outnode = ActivityFactory.createActivityParameterNode(activity, "out", paramout);
		ValueSpecificationAction specify = ActivityFactory.createValueSpecificationAction(activity, "specify name", "name");
		AddStructuralFeatureValueAction setname = ActivityFactory.createAddStructuralFeatureValueAction(activity, "set name", name, true);
		List<ActivityNode> expansionnodes = new ArrayList<ActivityNode>();
		expansionnodes.add(setname);
		ExpansionRegion region = ActivityFactory.createExpansionRegion(activity, "set names", ExpansionKind.parallel, expansionnodes, 1, 1);
		InputPin regionpin = new InputPin();
		regionpin.setName("regioninput");
		regionpin.setLower(1);
		regionpin.setUpper(1);
		region.structuredNodeInput.add(regionpin);
		region.input.add(regionpin);
		ActivityFactory.createObjectFlow(activity, innode, region.inputElement.get(0));
		ActivityFactory.createObjectFlow(activity, specify.result, region.structuredNodeInput.get(0));
		ActivityFactory.createObjectFlow(region, region.inputElement.get(0), setname.object);
		ActivityFactory.createObjectFlow(region, region.structuredNodeInput.get(0), setname.value);
		ActivityFactory.createObjectFlow(region, setname.result, region.outputElement.get(0));
		ActivityFactory.createObjectFlow(activity, region.outputElement.get(0), outnode);
		
		// create input
		ParameterValueList input = new ParameterValueList();
		
		ParameterValue inputvalue = new ParameterValue();
		inputvalue.parameter = paramin;
		ValueList valuelist = new ValueList();		
		for(int i=0;i<3;++i) {
			Object_ object = new Object_();
			object.types.add(cl);
			object.createFeatureValues();
			StringValue value = new StringValue();
			value.value = "lala";
			ValueList values = new ValueList();
			values.add(value);
			object.setFeatureValue(name, values, 0);
			ExecutionContext.getInstance().getLocus().add(object);
			Reference reference = new Reference();
			reference.referent = object;
			valuelist.add(reference);
		}
				
		inputvalue.values = valuelist; 
		input.add(inputvalue);
		
		// execute
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent) eventlist.get(0)).getActivityExecutionID();
		
		// check output
		ParameterValueList output = ExecutionContext.getInstance()
				.getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(3, output.get(0).values.size());
		
		for(int i=0;i<3;++i) {
			Object_ o = ((Reference)output.get(0).values.get(i)).referent;
			assertEquals("name", ((StringValue)o.getFeatureValue(name).values.get(0)).value);
		}
		
		// check events
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(specify, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(specify, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(region, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());	
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(setname, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(setname, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(setname, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(setname, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(setname, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(setname, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(region, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());	
		
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
	}
	
	/**
	 * Tests the execution of an activity without nodes
	 */
	@Test
	public void testActivityWihtoutNodes() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityWihtoutNodes");
		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Tests the execution of an activity that has nodes 
	 * but no node can become enabled.
	 * 
	 * Activity:
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ControlFlow:
	 * MergeNode1 --> MergeNode 2
	 * MergeNode2 --> MergeNode 1
	 */
	@Test
	public void testActivityWithoutEnabledNodes() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityWithoutEnabledNodes");
		MergeNode merge1 = ActivityFactory.createMergeNode(activity, "MergeNode 1");
		MergeNode merge2 = ActivityFactory.createMergeNode(activity, "MergeNode 2");
		
		ActivityFactory.createControlFlow(activity, merge1, merge2);
		ActivityFactory.createControlFlow(activity, merge2, merge1);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Tests the execution of an activity with one single initial node
	 * 
	 * Activity:
	 * InitialNode
	 */
	@Test
	public void testActivitySingleInitialNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivitySingleInitialNode");
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitilNode");
		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(1)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());	
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));	
	}
	
	/**
	 * Tests the execution of the combination of CreateObjectAction, 
	 * ValueSpecificationAction, and AddStructuralFeatureValueAction, 
	 * whereas the flow of the execution is controlled to accomplish the
	 * following execution order: InitialNode - CreateObjectAction1 - ValueSpecificationAction1 - 
	 * AddStructuralFeatureValueAction - CreateObjectAction2 - ValueSpecificationAction2 - 
	 * AddStructuralFeatureValueAction
	 * 
	 * Activity:
	 * InitialNode
	 * CreateObjectAction1 (class = Person)
	 * ValueSpecificationAction1 (value = "tanja")
	 * CreateObjectAction2 (class = Person)
	 * ValueSpecificationAction2 (value = "philip")
	 * AddStructuralFeatureValueAction (feature = "Name")
	 * 
	 * Activity ControlFlow: 
	 * InitialNode --> CreateObjectAction1 
	 * CreateObjectAction1 --> ValueSpecificationAction1 
	 * ValueSpecificationAction1 --> CreateObjectAction2 
	 * CreateObjectAction2 --> ValueSpecificationAction2
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction1.result --> AddStructuralFeatureValueAction.object
	 * CreateObjectAction2.result --> AddStructuralFeatureValueAction.object
	 * ValueSpecificationAction1.result --> AddStructuralFeatureValueAction.value
	 * ValueSpecificationAction2.result --> AddStructuralFeatureValueAction.value
	 */
	@Test
	public void testMultipleAddStructuralFeatureValueActions() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class_person);
		
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
		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		/*
		 * ActivityStart
		 * Step location = null
		 */
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(eventlist.get(0), step1.getParent());	
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initialnode, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		ExecutionContext.getInstance().nextStep(executionID);
	
		/*
		 * ActivityNodeEntry initial
		 * ActivityNodeExit initial
		 * Step location = initial
		 */
		assertEquals(5, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(createobject_tanja, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));				
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());					
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(initialnode, step2.getLocation());	
		assertEquals(eventlist.get(0), step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(createobject_tanja, step2.getNewEnabledNodes().get(0));
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry create tanja
		 * ActivityNodeExit create tanja
		 * Step location = create tanja
		 */
		assertEquals(8, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(valuespec_tanja, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_tanja, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());					
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_tanja, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(createobject_tanja, step3.getLocation());
		assertEquals(eventlist.get(0), step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(valuespec_tanja, step3.getNewEnabledNodes().get(0));
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry value tanja
		 * ActivityNodeExit value tanja
		 * Step location = value tanja 
		 */
		assertEquals(11, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(executionID).size());	
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(createobject_philip));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(addstructuralfeaturevalue));
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());					
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(valuespec_tanja, step4.getLocation());
		assertEquals(eventlist.get(0), step4.getParent());
		assertEquals(2, step4.getNewEnabledNodes().size());
		assertTrue(step4.getNewEnabledNodes().contains(createobject_philip));
		assertTrue(step4.getNewEnabledNodes().contains(addstructuralfeaturevalue));
		
		ExecutionContext.getInstance().nextStep(executionID, addstructuralfeaturevalue);
		
		/*
		 * ActivityNodeEntry add tanja
		 * ActivityNodeExit add tanja
		 * Step location = add tanja
		 */
		assertEquals(14, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(createobject_philip, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());					
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		SuspendEvent step5 = ((SuspendEvent)eventlist.get(13));
		assertEquals(addstructuralfeaturevalue, step5.getLocation());
		assertEquals(eventlist.get(0), step5.getParent());
		assertEquals(0, step5.getNewEnabledNodes().size());
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry create philip
		 * ActivityNodeExit create philip
		 * Step location = create philip
		 */
		assertEquals(17, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(valuespec_philip, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_philip, ((ActivityNodeEntryEvent)eventlist.get(14)).getNode());					
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_philip, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertTrue(eventlist.get(16) instanceof SuspendEvent);
		SuspendEvent step6 = ((SuspendEvent)eventlist.get(16));
		assertEquals(createobject_philip, step6.getLocation());
		assertEquals(eventlist.get(0), step6.getParent());
		assertEquals(1, step6.getNewEnabledNodes().size());
		assertEquals(valuespec_philip, step6.getNewEnabledNodes().get(0));
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry value philip
		 * ActivityNodeExit value philip
		 * Step location = value philip
		 */
		assertEquals(20, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_philip, ((ActivityNodeEntryEvent)eventlist.get(17)).getNode());					
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_philip, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		assertTrue(eventlist.get(19) instanceof SuspendEvent);
		SuspendEvent step7 = ((SuspendEvent)eventlist.get(19));
		assertEquals(valuespec_philip, step7.getLocation());
		assertEquals(eventlist.get(0), step7.getParent());
		assertEquals(1, step7.getNewEnabledNodes().size());
		assertEquals(addstructuralfeaturevalue, step7.getNewEnabledNodes().get(0));
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry add philip
		 * ActivityNodeExit add philip
		 * ActivityExit
		 */		
		assertEquals(23, eventlist.size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		
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
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * This test case is a variation of the test case
	 * {@link #testMultipleAddStructuralFeatureValueActions()}, 
	 * whereas the flow of the execution is controlled to accomplish the
	 * following execution order: InitialNode - CreateObjectAction1 - 
	 * ValueSpecificationAction1 - CreateObjectAction2 - ValueSpecificationAction2 - 
	 * AddStructuralFeatureValueAction - AddStructuralFeatureValueAction
	 */
	@Test
	public void testMultipleAddStructuralFeatureValueActions2() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class_person);
		
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
		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		/*
		 * ActivityStart
		 * Step location = null
		 */
		assertEquals(2, eventlist.size());		
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());	
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		assertTrue(eventlist.get(1) instanceof SuspendEvent);	
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));	
		
		ExecutionContext.getInstance().nextStep(executionID);
	
		/*
		 * ActivityNodeEntry initial
		 * ActivityNodeExit initial
		 * Step location = initial
		 */
		assertEquals(5, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(createobject_tanja, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));				
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());					
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(initialnode, ((SuspendEvent)eventlist.get(4)).getLocation());	
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(4)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry create tanja
		 * ActivityNodeExit create tanja
		 * Step location = create tanja
		 */
		assertEquals(8, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(valuespec_tanja, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_tanja, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());					
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_tanja, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		assertEquals(createobject_tanja, ((SuspendEvent)eventlist.get(7)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(7)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry value tanja
		 * ActivityNodeExit value tanja
		 * Step location = value tanja 
		 */
		assertEquals(11, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(executionID).size());	
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(createobject_philip));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(addstructuralfeaturevalue));
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());					
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_tanja, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		assertEquals(valuespec_tanja, ((SuspendEvent)eventlist.get(10)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(10)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID, createobject_philip);
		
		/*
		 * ActivityNodeEntry create philip
		 * ActivityNodeExit create philip
		 * Step location = create philip
		 */
		assertEquals(14, eventlist.size());
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(valuespec_philip));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(addstructuralfeaturevalue));
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(createobject_philip, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());					
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(createobject_philip, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		assertEquals(createobject_philip, ((SuspendEvent)eventlist.get(13)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(13)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID, valuespec_philip);
		
		/*
		 * ActivityNodeEntry value philip
		 * ActivityNodeExit value philip
		 * Step location = value philip
		 */
		assertEquals(17, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		assertEquals(valuespec_philip, ((ActivityNodeEntryEvent)eventlist.get(14)).getNode());					
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(valuespec_philip, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertTrue(eventlist.get(16) instanceof SuspendEvent);
		assertEquals(valuespec_philip, ((SuspendEvent)eventlist.get(16)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(16)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID, addstructuralfeaturevalue);
		
		/*
		 * ActivityNodeEntry add tanja
		 * ActivityNodeExit add tanja
		 * Step location = add tanja
		 */
		assertEquals(20, eventlist.size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(addstructuralfeaturevalue, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeEntryEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(17)).getNode());					
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(addstructuralfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		assertTrue(eventlist.get(19) instanceof SuspendEvent);
		assertEquals(addstructuralfeaturevalue, ((SuspendEvent)eventlist.get(19)).getLocation());
		assertEquals(eventlist.get(0), ((TraceEvent)eventlist.get(19)).getParent());
		
		ExecutionContext.getInstance().nextStep(executionID);
		
		/*
		 * ActivityNodeEntry add philip
		 * ActivityNodeExit add philip
		 * ActivityExit
		 */		
		assertEquals(23, eventlist.size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		
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
			assertEquals(1, o.featureValues.size());
			assertEquals(property_name, o.featureValues.get(0).feature);
			assertEquals(0, o.featureValues.get(0).values.size());
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
				assertEquals(1, o.featureValues.size());
				assertEquals(property_name, o.featureValues.get(0).feature);
				assertEquals(0, o.featureValues.get(0).values.size());
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
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(0, o.featureValues.get(0).values.size());
			
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
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}

	/**
	 * Tests the execution of an CallBehaviorAction that calls an Activity.
	 * 
	 * Activity 1 (Caller):
	 * InitialNode
	 * CreateObjectAction (class = Class1)
	 * CallBehaviorAction (behavior = Activity 2)
	 * 
	 * Activity 1 ControlFlow:
	 * InitialNode --> CreateObjectAction
	 * CreateObjectAction --> CallBehaviorAction
	 * 
	 * Activity 2 (Callee):
	 * InitialNode
	 * CreateObjectAction (class = Class2)
	 * 
	 * Activity 2 ControlFlow:
	 * InitialNode --> CreateObjectAction
	 */
	@Test
	public void testCallBehaviorAction() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		
		Activity activitycallee = ActivityFactory.createActivity("TestCallBehaviorAction Callee");
		InitialNode initialnodecallee = ActivityFactory.createInitialNode(activitycallee, "InitialNode Callee");
		CreateObjectAction createobjectclass2 = ActivityFactory.createCreateObjectAction(activitycallee, "CreateObjectAction Class2", class2);
		ActivityFactory.createControlFlow(activitycallee, initialnodecallee, createobjectclass2);
		
		Activity activitycaller = ActivityFactory.createActivity("TestCallBehaviorAction Caller");
		InitialNode initialnodecaller = ActivityFactory.createInitialNode(activitycaller, "InitialNode Caller");
		CreateObjectAction createobjectclass1 = ActivityFactory.createCreateObjectAction(activitycaller, "CreateObjectAction Class1", class1);				
		CallBehaviorAction callaction = ActivityFactory.createCallBehaviorAction(activitycaller, "CallBehaviorAction Call ", activitycallee);
		ActivityFactory.createControlFlow(activitycaller, initialnodecaller, createobjectclass1);
		ActivityFactory.createControlFlow(activitycaller, createobjectclass1, callaction);		
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activitycaller, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycaller = ((ActivityEntryEvent)eventlist.get(0));
		int callerexecutionID = entrycaller.getActivityExecutionID();
		assertEquals(activitycaller, entrycaller.getActivity());		
		assertNull(entrycaller.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activitycaller, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(initialnodecaller, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecallerentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initialnodecaller, initialnodecallerentry.getNode());		
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnodecaller, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialnodecallerentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(initialnodecaller, ((SuspendEvent)eventlist.get(4)).getLocation());	
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(createobjectclass1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);					
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl1entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(createobjectclass1, createcl1entry.getNode());	
		assertEquals(entrycaller, createcl1entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobjectclass1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(createcl1entry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		assertEquals(createobjectclass1, ((SuspendEvent)eventlist.get(7)).getLocation());	
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(7)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(callaction, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
		
		assertEquals(11, eventlist.size());
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(callaction, callactionentry.getNode());
		assertEquals(entrycaller, callactionentry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycallee = (ActivityEntryEvent)eventlist.get(9);
		int calleeexecutionID = entrycallee.getActivityExecutionID();
		assertEquals(activitycallee, entrycallee.getActivity());
		assertEquals(callactionentry, entrycallee.getParent());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		assertEquals(activitycallee, ((SuspendEvent)eventlist.get(10)).getLocation());
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(initialnodecallee, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(calleeexecutionID);				
		
		assertEquals(14, eventlist.size());
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecalleeentry =(ActivityNodeEntryEvent)eventlist.get(11); 
		assertEquals(initialnodecallee, initialnodecalleeentry.getNode());	
		assertEquals(entrycallee, initialnodecalleeentry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(initialnodecallee, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(initialnodecalleeentry, ((TraceEvent)eventlist.get(12)).getParent());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		assertEquals(initialnodecallee, ((SuspendEvent)eventlist.get(13)).getLocation());	
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(13)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(createobjectclass2, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(calleeexecutionID);
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		assertEquals(19, eventlist.size());
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl2entry = (ActivityNodeEntryEvent)eventlist.get(14); 
		assertEquals(createobjectclass2, createcl2entry.getNode());	
		assertEquals(entrycallee, createcl2entry.getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(createobjectclass2, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(createcl2entry, ((TraceEvent)eventlist.get(15)).getParent());
		//assertTrue(eventlist.get(16) instanceof StepEvent);
		//assertEquals(createobjectclass2, ((StepEvent)eventlist.get(16)).getLocation());	
		//assertNull(eventlist.get(16).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(activitycallee, ((ActivityExitEvent)eventlist.get(16)).getActivity());
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(16)).getParent());
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(callaction, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(17)).getParent());
		assertTrue(eventlist.get(18) instanceof ActivityExitEvent);
		assertEquals(activitycaller, ((ActivityExitEvent)eventlist.get(18)).getActivity());
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(18)).getParent());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(2, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		assertTrue(e.get(1) instanceof Object_);		
		o = (Object_)(e.get(1));
		assertEquals(1, o.types.size());
		assertEquals(class2, o.types.get(0));
				
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));
		
		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 8));
		eventlistactivity1.addAll(eventlist.subList(17, 18));
		
		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(9, 16));		
	}
	
	/**
	 * This test case is an extension of the test case
	 * {@link #testCallBehaviorAction()}
	 * 
	 * Activity 1 (Caller):
	 * InitialNode
	 * CreateObjectAction1 (class = Class1)
	 * CallBehaviorAction (behavior = Activity 2)
	 * CreateObjectAction2 (class = Class1)
	 * 
	 * Activity 1 ControlFlow:
	 * InitialNode --> CreateObjectAction1
	 * CreateObjectAction1 --> CallBehaviorAction
	 * CallBehaviorAction --> CreateObjectAction2
	 * 
	 * Activity 2 (Callee):
	 * InitialNode
	 * CreateObjectAction (class = Class2)
	 * 
	 * Activity 2 ControlFlow:
	 * InitialNode --> CreateObjectAction
	 */
	@Test
	public void testCallBehaviorAction2() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		
		Activity activitycallee = ActivityFactory.createActivity("TestCallBehaviorAction Callee");
		InitialNode initialnodecallee = ActivityFactory.createInitialNode(activitycallee, "InitialNode Callee");
		CreateObjectAction createobjectclass2 = ActivityFactory.createCreateObjectAction(activitycallee, "CreateObjectAction Class2", class2);
		ActivityFactory.createControlFlow(activitycallee, initialnodecallee, createobjectclass2);
		
		Activity activitycaller = ActivityFactory.createActivity("TestCallBehaviorAction Caller");
		InitialNode initialnodecaller = ActivityFactory.createInitialNode(activitycaller, "InitialNode Caller");
		CreateObjectAction createobjectclass1 = ActivityFactory.createCreateObjectAction(activitycaller, "CreateObjectAction Class1", class1);				
		CallBehaviorAction callaction = ActivityFactory.createCallBehaviorAction(activitycaller, "CallBehaviorAction Call ", activitycallee);
		CreateObjectAction createobjectclass1_2 = ActivityFactory.createCreateObjectAction(activitycaller, "CreateObjectAction Class1 2", class1);
		ActivityFactory.createControlFlow(activitycaller, initialnodecaller, createobjectclass1);
		ActivityFactory.createControlFlow(activitycaller, createobjectclass1, callaction);
		ActivityFactory.createControlFlow(activitycaller, callaction, createobjectclass1_2);		
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activitycaller, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycaller = ((ActivityEntryEvent)eventlist.get(0));
		int callerexecutionID = entrycaller.getActivityExecutionID();
		assertEquals(activitycaller, entrycaller.getActivity());		
		assertNull(entrycaller.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activitycaller, step1.getLocation());	
		assertEquals(entrycaller, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initialnodecaller, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(initialnodecaller, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecallerentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initialnodecaller, initialnodecallerentry.getNode());		
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnodecaller, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialnodecallerentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(initialnodecaller, step2.getLocation());	
		assertEquals(entrycaller, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(createobjectclass1, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(createobjectclass1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);					
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl1entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(createobjectclass1, createcl1entry.getNode());	
		assertEquals(entrycaller, createcl1entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(createobjectclass1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(createcl1entry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(createobjectclass1, step3.getLocation());	
		assertEquals(entrycaller, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(callaction, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(callaction, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
		
		assertEquals(11, eventlist.size());
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(callaction, callactionentry.getNode());
		assertEquals(entrycaller, callactionentry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycallee = (ActivityEntryEvent)eventlist.get(9);
		int calleeexecutionID = entrycallee.getActivityExecutionID();
		assertEquals(activitycallee, entrycallee.getActivity());
		assertEquals(callactionentry, entrycallee.getParent());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(activitycallee, step4.getLocation());
		assertEquals(entrycallee, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(initialnodecallee, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(initialnodecallee, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(calleeexecutionID);				
		
		assertEquals(14, eventlist.size());
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecalleeentry =(ActivityNodeEntryEvent)eventlist.get(11); 
		assertEquals(initialnodecallee, initialnodecalleeentry.getNode());	
		assertEquals(entrycallee, initialnodecalleeentry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(initialnodecallee, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(initialnodecalleeentry, ((TraceEvent)eventlist.get(12)).getParent());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		SuspendEvent step5 = ((SuspendEvent)eventlist.get(13));
		assertEquals(initialnodecallee, step5.getLocation());	
		assertEquals(entrycallee, step5.getParent());
		assertEquals(1, step5.getNewEnabledNodes().size());
		assertEquals(createobjectclass2, step5.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(createobjectclass2, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(calleeexecutionID);		
		
		assertEquals(19, eventlist.size());
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl2entry = (ActivityNodeEntryEvent)eventlist.get(14); 
		assertEquals(createobjectclass2, createcl2entry.getNode());	
		assertEquals(entrycallee, createcl2entry.getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(createobjectclass2, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(createcl2entry, ((TraceEvent)eventlist.get(15)).getParent());
		//assertTrue(eventlist.get(16) instanceof StepEvent);
		//assertEquals(createobjectclass2, ((StepEvent)eventlist.get(16)).getLocation());	
		//assertNull(eventlist.get(16).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(activitycallee, ((ActivityExitEvent)eventlist.get(16)).getActivity());
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(16)).getParent());
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(callaction, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(17)).getParent());
		assertTrue(eventlist.get(18) instanceof SuspendEvent);
		SuspendEvent step6 = ((SuspendEvent)eventlist.get(18));
		assertEquals(callaction, step6.getLocation());
		assertEquals(entrycaller, step6.getParent());
		assertEquals(1, step6.getNewEnabledNodes().size());
		assertEquals(createobjectclass1_2, step6.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(createobjectclass1_2, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());				
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(2, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		assertTrue(e.get(1) instanceof Object_);		
		o = (Object_)(e.get(1));
		assertEquals(1, o.types.size());
		assertEquals(class2, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);
		
		assertEquals(22, eventlist.size());
		assertTrue(eventlist.get(19) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl12entry = (ActivityNodeEntryEvent)eventlist.get(19);
		assertEquals(createobjectclass1_2, createcl12entry.getNode());	
		assertEquals(entrycaller, createcl12entry.getParent());
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		assertEquals(createobjectclass1_2, ((ActivityNodeExitEvent)eventlist.get(20)).getNode());
		assertEquals(createcl12entry, ((TraceEvent)eventlist.get(20)).getParent());
		assertTrue(eventlist.get(21) instanceof ActivityExitEvent);
		assertEquals(activitycaller, ((ActivityExitEvent)eventlist.get(21)).getActivity());
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(21)).getParent());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(3, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		assertTrue(e.get(1) instanceof Object_);		
		o = (Object_)(e.get(1));
		assertEquals(1, o.types.size());
		assertEquals(class2, o.types.get(0));
		o = (Object_)(e.get(2));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));

		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 8));
		eventlistactivity1.addAll(eventlist.subList(17, 21));

		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(9, 16));	
	}
	
	/**
	 * Tests the execution of an CallBehaviorAction that calls an 
	 * OpaqueBehavior that produces an IntegerValue with value 5
	 * as Output.
	 * Besides the occurrence of the events it is also tested if the
	 * OutputPin of the CallBehaviorAction actually holds an ObjectToken
	 * with the IntegerValue 5 after its execution.
	 * 
	 * Activity: 
	 * InitialNode
	 * CallBehaviorAction (behavior = RETURN5, outputPin)
	 * ActivityFinalNode 
	 * 
	 * Activity ControlFlow:
	 * InitialNode --> CallBehaviorAction
	 * CallBehaviorAction --> ActivityFinalNode
	 */
	@Test
	public void testCallBehaviorActionCallingOpaqueBehavior() {
		OpaqueBehavior return5behavior = new OpaqueBehavior();
		Parameter output = new Parameter();
		output.setDirection(ParameterDirectionKind.out);
		output.setName("result");
		return5behavior.ownedParameter.add(output);
		
		Return5BehaviorExecution return5execution = new Return5BehaviorExecution();
		return5execution.types.add(return5behavior);
		
		ExecutionContext.getInstance().addOpaqueBehavior(return5execution);
		
		Activity activity = ActivityFactory.createActivity("TestCallBehaviorActionCallingOpaqueBehavior");
		//OpaqueBehavior return5behavior = ExecutionContext.getInstance().getOpaqueBehavior("RETURN5");
		
		CallBehaviorAction callaction = ActivityFactory.createCallBehaviorAction(activity, "Call Behavior RETURN 5", return5behavior);		
		OutputPin outputpin_callaction = new OutputPin();
		outputpin_callaction.setName("OutputPin (Call Behavior RETURN)");
		OutputPinList output_callaction = new OutputPinList();
		output_callaction.add(outputpin_callaction);
		callaction.result = output_callaction;
		callaction.output = output_callaction;
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "Initial Node");
		ActivityFinalNode finalnode = ActivityFactory.createActivityFinalNode(activity, "Activity Final Node");
		
		ActivityFactory.createControlFlow(activity, initialnode, callaction);
		ActivityFactory.createControlFlow(activity, callaction, finalnode);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int executionID = activityentry.getActivityExecutionID();
		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodeentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initialnode, initialnodeentry.getNode());		
		assertEquals(activityentry, initialnodeentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialnodeentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(initialnode, ((SuspendEvent)eventlist.get(4)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(callaction, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);						
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(callaction, callactionentry.getNode());		
		assertEquals(activityentry, callactionentry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(callaction, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		assertEquals(callaction, ((SuspendEvent)eventlist.get(7)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(7)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(finalnode, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		ActivityExecution activityexe = (ActivityExecution)ExecutionContext.getInstance().getExtensionalValues().get(0);
		CallBehaviorActionActivation callactivation = null;
		for(int i=0;i<activityexe.activationGroup.nodeActivations.size();++i) {
			if(activityexe.activationGroup.nodeActivations.get(i) instanceof CallBehaviorActionActivation) {
				callactivation = (CallBehaviorActionActivation)activityexe.activationGroup.nodeActivations.get(i);
			}
		}
		assertEquals(1, callactivation.pinActivations.get(0).heldTokens.size());
		assertEquals(5, ((IntegerValue)(callactivation.pinActivations.get(0).heldTokens.get(0).getValue())).value);
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);						
	
		assertEquals(11, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent finalnodeentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(finalnode, finalnodeentry.getNode());		
		assertEquals(activityentry, finalnodeentry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(finalnode, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(finalnodeentry, ((TraceEvent)eventlist.get(9)).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(executionID).size());			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * This test case is an variant of the test case 
	 * {@link #testCallBehaviorActionCallingOpaqueBehavior()}
	 * 
	 * It is tested, if the execution of an CallBehaviorAction 
	 * which calls an OpaqueBehavior also  works if the 
	 * CallBehaviorAction is the last action of the activity.
	 * Therewith it is primarily tested if after the execution of
	 * the CallBehaviorAction an ActivityExitEvent is fired instead
	 * of an StepEvent.
	 * 
	 * Activity: 
	 * InitialNode
	 * CallBehaviorAction (behavior = RETURN5, outputPin)
	 * 
	 * Activity ControlFlow:
	 * InitialNode --> CallBehaviorAction
	 */
	@Test
	public void testCallBehaviorActionCallingOpaqueBehavior2() {
		OpaqueBehavior return5behavior = new OpaqueBehavior();
		Parameter output = new Parameter();
		output.setDirection(ParameterDirectionKind.out);
		output.setName("result");
		return5behavior.ownedParameter.add(output);
		
		Return5BehaviorExecution return5execution = new Return5BehaviorExecution();
		return5execution.types.add(return5behavior);
		
		ExecutionContext.getInstance().addOpaqueBehavior(return5execution);
		
		Activity activity = ActivityFactory.createActivity("TestCallBehaviorActionCallingOpaqueBehavior");
		//OpaqueBehavior return5behavior = ExecutionContext.getInstance().getOpaqueBehavior("RETURN5");
		
		CallBehaviorAction callaction = ActivityFactory.createCallBehaviorAction(activity, "Call Behavior RETURN 5", return5behavior);		
		OutputPin outputpin_callaction = new OutputPin();
		outputpin_callaction.setName("OutputPin (Call Behavior RETURN)");
		OutputPinList output_callaction = new OutputPinList();
		output_callaction.add(outputpin_callaction);
		callaction.result = output_callaction;
		callaction.output = output_callaction;
		
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "Initial Node");
		
		ActivityFactory.createControlFlow(activity, initialnode, callaction);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int executionID = activityentry.getActivityExecutionID();
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(initialnode, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodeentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initialnode, initialnodeentry.getNode());		
		assertEquals(activityentry, initialnodeentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialnodeentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(initialnode, ((SuspendEvent)eventlist.get(4)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(callaction, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);						
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(callaction, callactionentry.getNode());		
		assertEquals(activityentry, callactionentry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(callaction, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(6)).getParent());						
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(7)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(executionID).size());			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		//ActivityExecution activityexe = (ActivityExecution)ExecutionContext.getInstance().getExtensionalValues().get(0);
		int activityexecutionID = activityentry.getActivityExecutionID();		
		ActivityExecution activityexe = ExecutionContext.getInstance().getActivityExecution(activityexecutionID);
		CallBehaviorActionActivation callactivation = null;
		for(int i=0;i<activityexe.activationGroup.nodeActivations.size();++i) {
			if(activityexe.activationGroup.nodeActivations.get(i) instanceof CallBehaviorActionActivation) {
				callactivation = (CallBehaviorActionActivation)activityexe.activationGroup.nodeActivations.get(i);
			}
		}
		assertEquals(1, callactivation.pinActivations.get(0).heldTokens.size());
		assertEquals(5, ((IntegerValue)(callactivation.pinActivations.get(0).heldTokens.get(0).getValue())).value);
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * This test case tests if the execution works with
	 * multiple initial enabled nodes.
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class2)
	 * (without edges)
	 */
	@Test
	public void testMoreThanOneInitialNode() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		
		Activity activity = ActivityFactory.createActivity("TestMoreThanOneInitialNode");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int executionID = activityentry.getActivityExecutionID();
		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());	
		assertEquals(activityentry, step1.getParent());
		assertEquals(3, step1.getNewEnabledNodes().size());
		assertTrue(step1.getNewEnabledNodes().contains(create1));
		assertTrue(step1.getNewEnabledNodes().contains(create2));
		assertTrue(step1.getNewEnabledNodes().contains(create3));
		
		assertEquals(3, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(create3));
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID, create1);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, createcl1entry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(createcl1entry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());	
		assertEquals(activityentry, step2.getParent());
		assertEquals(0, step2.getNewEnabledNodes().size());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(executionID).contains(create3));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID, create2);					
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, createcl2entry.getNode());	
		assertEquals(activityentry, createcl2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(createcl2entry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(create2, step3.getLocation());	
		assertEquals(activityentry, step3.getParent());
		assertEquals(0, step3.getNewEnabledNodes().size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(executionID).get(0));
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(2, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		o = (Object_)(e.get(1));
		assertEquals(1, o.types.size());
		assertEquals(class2, o.types.get(0));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(executionID);					
		
		assertEquals(11, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent createcl3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, createcl3entry.getNode());	
		assertEquals(activityentry, createcl3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(createcl3entry, ((TraceEvent)eventlist.get(9)).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(executionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(3, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		o = (Object_)(e.get(1));
		assertEquals(1, o.types.size());
		assertEquals(class2, o.types.get(0));
		o = (Object_)(e.get(2));
		assertEquals(1, o.types.size());
		assertEquals(class3, o.types.get(0));
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * This tests the execution of an CallBehaviorAction that calls an Activity
	 * and produces output
	 * 
	 * Activity 1 (Caller):
	 * ValueSpecificationAction (value="tanja")
	 * CallBehaviorAction (behavior = Activity2)
	 * AddStructuralFeatureValueAction (feature = Name)
	 * 
	 * Activity 1 ControlFlow:
	 * ValueSpecificationAction --> CallBehaviorAction
	 * 
	 * Activity 1 ObjectFlow:
	 * ValueSpecificationAction.result --> AddStructuralFeatureValueAction.value
	 * CallBehaviorAction.result.get(0) --> AddStructuralFeatureValueAction.object
	 * 
	 * Activity 2 (Callee, 1 parameter):
	 * ActivityParameterNode (direction = out)
	 * CreateObjectAction (class = Person)
	 * 
	 * Activity 2 ObjectFlow:
	 * CreateObjectAction.result --> ActivityParameterNode
	 */
	@Test
	public void testCallBehaviorActionWithActivityOutput() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class_person);
		
		Activity activity2 = ActivityFactory.createActivity("TestCallBehaviorActionWithActivityOutput Callee Activity 2");
		Parameter param = ActivityFactory.createParameter(activity2, "Result Parameter (" + activity2.name + ")", ParameterDirectionKind.out);				
		ActivityParameterNode paramnode = ActivityFactory.createActivityParameterNode(activity2, "ParameterNode", param);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity2, "CreateObject Person", class_person);		
		ActivityFactory.createObjectFlow(activity2, create.result, paramnode);
		
		Activity activity1 = ActivityFactory.createActivity("TestCallBehaviorActionWithActivityOutput Caller Activity 1");
		ValueSpecificationAction vs = ActivityFactory.createValueSpecificationAction(activity1, "ValueSpecification tanja", "tanja");
		CallBehaviorAction call = ActivityFactory.createCallBehaviorAction(activity1, "Call Activity 2", activity2, 1);		
		AddStructuralFeatureValueAction add = ActivityFactory.createAddStructuralFeatureValueAction(activity1, "AddStructuralFeature name", property_name);
						
		ActivityFactory.createControlFlow(activity1, vs, call);
		ActivityFactory.createObjectFlow(activity1, vs.result, add.value);
		ActivityFactory.createObjectFlow(activity1, call.result.get(0), add.object);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycaller = ((ActivityEntryEvent)eventlist.get(0));
		int callerexecutionID = entrycaller.getActivityExecutionID();
		assertEquals(activity1, entrycaller.getActivity());		
		assertNull(entrycaller.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());	
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(vs, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecallerentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(vs, initialnodecallerentry.getNode());		
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(vs, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialnodecallerentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(vs, ((SuspendEvent)eventlist.get(4)).getLocation());	
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(call, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));			
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());				
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);						
		
		assertEquals(8, eventlist.size());
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(5);		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(call, callactionentry.getNode());
		assertEquals(entrycaller, callactionentry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityEntryEvent);
		ActivityEntryEvent entrycallee = (ActivityEntryEvent)eventlist.get(6);
		int calleeexecutionID = entrycallee.getActivityExecutionID();
		assertEquals(activity2, entrycallee.getActivity());
		assertEquals(callactionentry, entrycallee.getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		assertEquals(activity2, ((SuspendEvent)eventlist.get(7)).getLocation());
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(7)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		assertEquals(create, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(calleeexecutionID);				
		
		assertEquals(13, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialnodecalleeentry =(ActivityNodeEntryEvent)eventlist.get(8); 
		assertEquals(create, initialnodecalleeentry.getNode());	
		assertEquals(entrycallee, initialnodecalleeentry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(initialnodecalleeentry, ((TraceEvent)eventlist.get(9)).getParent());				
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(10)).getActivity());
		assertEquals(entrycallee, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(11)).getParent());
		
		assertTrue(eventlist.get(12) instanceof SuspendEvent);
		assertEquals(call, ((SuspendEvent)eventlist.get(12)).getLocation());	
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(12)).getParent());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(add, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(callerexecutionID);
		
		assertEquals(16, eventlist.size());
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent addentry =(ActivityNodeEntryEvent)eventlist.get(13); 
		assertEquals(add, addentry.getNode());	
		assertEquals(entrycaller, addentry.getParent());
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(add, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		assertEquals(addentry, ((TraceEvent)eventlist.get(14)).getParent());				
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		assertEquals(entrycaller, ((TraceEvent)eventlist.get(15)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(callerexecutionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(calleeexecutionID).size());
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class_person, o.types.get(0));
		assertEquals(1, o.featureValues.size());
		assertEquals(property_name, o.featureValues.get(0).feature);
		assertEquals(1, o.featureValues.get(0).values.size());
		assertTrue(o.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)o.featureValues.get(0).values.get(0)).value);
		
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));

		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 5));
		eventlistactivity1.addAll(eventlist.subList(11, 15));

		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(6, 10));	
	}
	
	/**
	 * Tests the retrieving of the output of an activity execution.
	 * 
	 * Activity (1 parameter):
	 * CreateObjectAction (class = Class1)
	 * ActivityParameterNode (direction=out)
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction.result --> ActivityParameterNode
	 */
	@Test
	public void testActivityWithOutput() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Activity activity = ActivityFactory.createActivity("TestActivityWithOutput");
		Parameter param = ActivityFactory.createParameter(activity, "OutputParameter", ParameterDirectionKind.out);
		ActivityParameterNode paramnode = ActivityFactory.createActivityParameterNode(activity, "OutputParameterNode", param);
		CreateObjectAction action = ActivityFactory.createCreateObjectAction(activity, "CreateObjectAction", class1);
		ActivityFactory.createObjectFlow(activity, action.result, paramnode);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());				
		assertNull(activityentry.getParent());		
		int activityexecutionID = activityentry.getActivityExecutionID();		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(action, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent actionentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(action, actionentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(action, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(actionentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());		
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		ParameterValue outputvalue = output.get(0);
		assertEquals(param, outputvalue.parameter);
		ValueList values = output.get(0).values;
		assertEquals(1, values.size());
		Value value = values.get(0);
		assertTrue(value instanceof Reference);
		Object_ valueobject = ((Reference)value).referent;		
		assertEquals(1, valueobject.types.size());
		assertEquals(class1, valueobject.types.get(0));
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Tests if an ActivityExecution is destroyed at the end of its execution
	 * 
	 * Activity:
	 * CreateObjectAction
	 */
	@Test
	public void testDestroyingOfActivity() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Activity activity = ActivityFactory.createActivity("TestDestroyingOfActivity");
		CreateObjectAction action = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());				
		assertNull(activityentry.getParent());	
		int activityexecutionID = activityentry.getActivityExecutionID();		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(action, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		/*
		 * At the locus only the ActivityExecution object exists
		 * and the type of this object is set properly
		 */
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());
		assertTrue(ExecutionContext.getInstance().getExtensionalValues().get(0) instanceof ActivityExecution);
		ActivityExecution execution = (ActivityExecution)ExecutionContext.getInstance().getExtensionalValues().get(0);
		assertEquals(activity, (Activity) execution.types.get(0));
				
		assertEquals(1, execution.types.size());
		assertEquals(activity, execution.types.get(0));
		assertEquals(execution, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent actionentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(action, actionentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(action, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(actionentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());		
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		/*
		 * At the locus is only the created object present, the ActivityExecution was removed.
		 * Also the ActivityExecution object was destroyed, i.e., the type was removed
		 */
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());
		assertTrue(ExecutionContext.getInstance().getExtensionalValues().get(0) instanceof Object_);
		o = (Object_)ExecutionContext.getInstance().getExtensionalValues().get(0);
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getActivityExecution(activityexecutionID).types.size());
		assertEquals(0, execution.types.size());
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}	
	
	/**
	 * Tests the clean up of the ExecutionContext after an Activity has been executed
	 *  
	 * Activity:
	 * CreateObjectAction
	 */
	@Test
	public void testCleanUpOfExecutionContext() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Activity activity = ActivityFactory.createActivity("TestDestroyingOfActivity");
		CreateObjectAction action = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int activityexecutionID = activityentry.getActivityExecutionID();		

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(action, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
		
		ExtensionalValueList e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(0, e.size());
		
		/*
		 * The ExecutionContext contains the ActivityExecution object, a list for its enabled nodes, and
		 * no output for this execution
		 */
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));	
		
		ActivityExecution execution = ExecutionContext.getInstance().getActivityExecution(activityexecutionID);
		
		List<ActivityNode> nodes = new ArrayList<ActivityNode>();
		nodes.add(action);
		checkActivatedNodes(execution, nodes);
		List<ActivityExecution> callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(execution, callees, null);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());		
		
		e = extensionalValueLists.get(extensionalValueLists.size()-1);
		assertEquals(1, e.size());
		assertTrue(e.get(0) instanceof Object_);		
		Object_ o = (Object_)(e.get(0));
		assertEquals(1, o.types.size());
		assertEquals(class1, o.types.get(0));
		
		/*
		 * The ExecutionContext still contains the ActivityExecution object, 
		 * contains the output (in this case an empty list), 
		 * but not a list for its enabled nodes
		 */
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));	
		
		execution = ExecutionContext.getInstance().getActivityExecution(activityexecutionID);
		
		checkActivatedNodes(execution, null);
		checkCallHierarchy(execution, null, null, true);
		
		checkActivityExecutionEnded(execution);

		assertNotNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		assertEquals(0, ExecutionContext.getInstance().getActivityOutput(activityexecutionID).size());	
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));	
	}
	
	private void checkActivityExecutionEnded(ActivityExecution rootActivity) {			
		ExecutionStatus exestatus = ExecutionContext.getInstance().getActivityExecutionStatus(rootActivity);
		assertNull(exestatus);
		
		ExecutionHierarchy hierarchy = ExecutionContext.getInstance().getExecutionHierarchy();	
		assertEquals(0, hierarchy.getCallee().size());
		assertEquals(0, hierarchy.getCaller().size());	
		assertFalse(hierarchy.getCaller().containsKey(rootActivity));
	}
	
	private void checkActivatedNodes(ActivityExecution execution, List<ActivityNode> nodes) {
		if(nodes != null) {
			ExecutionStatus exestatus = ExecutionContext.getInstance().getActivityExecutionStatus(execution);
			
			List<ActivityNode> enablednodes = exestatus.getEnabledNodes();
			assertNotNull(enablednodes);
			assertEquals(nodes.size(), enablednodes.size());
			for(int i=0;i<nodes.size();++i) {
				assertTrue(enablednodes.contains(nodes.get(i)));
			}
			
			HashMap<ActivityNode, ActivityNodeActivation> enabledactivations = exestatus.getEnalbedActivations();		
			assertNotNull(enabledactivations);
			assertEquals(nodes.size(), enabledactivations.size());
			for(int i=0;i<nodes.size();++i) {
				ActivityNodeActivation activation = enabledactivations.get(nodes.get(i));
				assertNotNull(activation);
				assertTrue(exestatus.getEnabledActivationTokens().containsKey(activation));
			}		
		} else {
			assertNull(ExecutionContext.getInstance().getActivityExecutionStatus(execution));
		}
	}
	
	private void checkCallHierarchy(ActivityExecution execution, List<ActivityExecution> callees_expected, ActivityExecution caller_expected, boolean executiondestroyed) {
		ExecutionHierarchy hierarchy = ExecutionContext.getInstance().getExecutionHierarchy();
		
		if(callees_expected != null) {
			List<ActivityExecution> callees = hierarchy.getCallee(execution);
			assertNotNull(callees);
			assertEquals(callees_expected.size(), callees.size());
			
			for(int i=0;i<callees_expected.size();++i) {
				assertTrue(callees.contains(callees_expected.get(i)));
			}
		} else {
			List<ActivityExecution> callees = hierarchy.getCallee(execution);
			assertNull(callees);
		}
		
		if(!executiondestroyed) {
			ActivityExecution caller = hierarchy.getCaller(execution);
			assertEquals(caller_expected, caller);
		} else {
			assertFalse(hierarchy.getCaller().containsKey(execution));
		}
	}
	
	private void checkCallHierarchy(ActivityExecution execution, List<ActivityExecution> callees_expected, ActivityExecution caller_expected) {
		checkCallHierarchy(execution, callees_expected, caller_expected, false);
	}
	
	/**
	 * This test case is an extension of the test case
	 * {@link #testCleanUpOfExecutionContext()}
	 * and tests additionally the clean up if an other activity is called using the
	 * CallBehaviorAction
	 * 
	 * Activity 1 (Caller):
	 * InitialNode1
	 * ForkNode1
	 * MergeNode1
	 * CallBehaviorAction1
	 * 
	 * Activity 1 ControlFlow:
	 * InitialNode1 --> ForkNode1
	 * ForkNode1 --> CallBehaviorAction1
	 * ForkNode1 --> MergeNode1
	 * 
	 * Activity 2 (Callee):
	 * MergeNode2
	 * 
	 * Execution order: InitialNode1 - ForkNode1 - CallBehaviorAction1 - MergeNode1 - MergeNode2
	 */
	@Test
	public void testCleanUpOfExecutionContextWithCallBehaviorAction(){
		Activity activity2 = ActivityFactory.createActivity("TestCleanUpOfExecutionContextWithCallBehaviorAction Callee");
		MergeNode merge2 = ActivityFactory.createMergeNode(activity2, "MergeNode Callee");
		
		Activity activity = ActivityFactory.createActivity("TestCleanUpOfExecutionContextWithCallBehaviorAction Caller");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "InitialNode");
		ForkNode fork = ActivityFactory.createForkNode(activity, "ForkNode"); 
		MergeNode merge = ActivityFactory.createMergeNode(activity, "MergeNode");
		CallBehaviorAction call = ActivityFactory.createCallBehaviorAction(activity, "CallBehaviorAction", activity2);
		ActivityFactory.createControlFlow(activity, initial, fork);
		ActivityFactory.createControlFlow(activity, fork, call);
		ActivityFactory.createControlFlow(activity, fork, merge);
		
		//DEBUG
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());				
		assertNull(activityentry.getParent());		
		int activityexecutionID = activityentry.getActivityExecutionID();		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());	
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initial, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));		
		ActivityExecution executionactivity1 = ExecutionContext.getInstance().getActivityExecution(activityexecutionID);			
		assertEquals(activity, executionactivity1.getTypes().get(0));
		
		List<ActivityNode> nodes = new ArrayList<ActivityNode>();
		nodes.add(initial);
		checkActivatedNodes(executionactivity1, nodes);
		List<ActivityExecution> callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(executionactivity1, callees, null);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initial, initialentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(initial, step2.getLocation());	
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(fork, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(fork, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
				
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));		
		assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		
		nodes = new ArrayList<ActivityNode>();
		nodes.add(fork);
		checkActivatedNodes(executionactivity1, nodes);
		callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(executionactivity1, callees, null);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent forkentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(fork, forkentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(5)).getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(forkentry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(fork, step3.getLocation());	
		assertEquals(activityentry, step3.getParent());
		assertEquals(2, step3.getNewEnabledNodes().size());
		assertTrue(step3.getNewEnabledNodes().contains(call));
		assertTrue(step3.getNewEnabledNodes().contains(merge));
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).contains(call));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).contains(merge));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));		
		assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));			
		
		nodes = new ArrayList<ActivityNode>();
		nodes.add(call);
		nodes.add(merge);
		checkActivatedNodes(executionactivity1, nodes);
		callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(executionactivity1, callees, null);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID, call);						
	
		assertEquals(11, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent callentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(call, callentry.getNode());	
		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry = (ActivityEntryEvent)eventlist.get(9);
		int activity2executionID = activity2entry.getActivityExecutionID();
		assertEquals(activity2, activity2entry.getActivity());
		assertEquals(callentry, activity2entry.getParent());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(activity2, step4.getLocation());	
		assertEquals(activity2entry, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(merge2, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).contains(merge));
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID).contains(merge2));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity2executionID));
		assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		ActivityExecution executionactivity2 = ExecutionContext.getInstance().getActivityExecution(activity2executionID);
		assertEquals(activity2, executionactivity2.getTypes().get(0));

		// caller activity execution
		nodes = new ArrayList<ActivityNode>();
		nodes.add(merge);
		checkActivatedNodes(executionactivity1, nodes);
		callees = new ArrayList<ActivityExecution>();
		callees.add(executionactivity2);
		checkCallHierarchy(executionactivity1, callees, null);
		
		// callee activity execution
		nodes = new ArrayList<ActivityNode>();
		nodes.add(merge2);
		checkActivatedNodes(executionactivity2, nodes);
		callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(executionactivity2, callees, executionactivity1);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activityexecutionID, merge);						
		
		assertEquals(14, eventlist.size());
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent mergeentry = (ActivityNodeEntryEvent)eventlist.get(11);
		assertEquals(merge, mergeentry.getNode());	
		assertEquals(activityentry, mergeentry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(merge, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(mergeentry, ((TraceEvent)eventlist.get(12)).getParent());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		SuspendEvent step5 = ((SuspendEvent)eventlist.get(13));
		assertEquals(merge, step5.getLocation());	
		assertEquals(activityentry, step5.getParent());
		assertEquals(0, step5.getNewEnabledNodes().size());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
		assertEquals(merge2, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).get(0));		
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
				
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity2executionID));
		assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		assertEquals(executionactivity2, ExecutionContext.getInstance().getActivityExecution(activity2executionID));

		// caller activity execution
		nodes = new ArrayList<ActivityNode>();
		checkActivatedNodes(executionactivity1, nodes);
		callees = new ArrayList<ActivityExecution>();
		callees.add(executionactivity2);
		checkCallHierarchy(executionactivity1, callees, null);
		
		// callee activity execution
		nodes = new ArrayList<ActivityNode>();
		nodes.add(merge2);
		checkActivatedNodes(executionactivity2, nodes);
		callees = new ArrayList<ActivityExecution>();
		checkCallHierarchy(executionactivity2, callees, executionactivity1);
		
		assertNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		
		//NEXT STEP
		ExecutionContext.getInstance().nextStep(activity2executionID);						
		
		assertEquals(19, eventlist.size());
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent merge2entry = (ActivityNodeEntryEvent)eventlist.get(14);
		assertEquals(merge2, merge2entry.getNode());	
		assertEquals(activity2entry, merge2entry.getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(merge2, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(merge2entry, ((TraceEvent)eventlist.get(15)).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(16)).getActivity());	
		assertEquals(activity2entry, ((TraceEvent)eventlist.get(16)).getParent());
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		assertEquals(callentry, ((TraceEvent)eventlist.get(17)).getParent());		
		assertTrue(eventlist.get(18) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(18)).getActivity());	
		assertEquals(activityentry, ((TraceEvent)eventlist.get(18)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());		
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertNotNull(ExecutionContext.getInstance().getActivityExecution(activityexecutionID));
		assertNull(ExecutionContext.getInstance().getActivityExecution(activity2executionID));
		assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activityexecutionID));

		// caller activity execution						
		checkActivatedNodes(executionactivity1, null);
		checkCallHierarchy(executionactivity1, null, null, true);		
		
		// callee activity execution
		checkActivatedNodes(executionactivity2, null);
		checkCallHierarchy(executionactivity2, null, null, true);
		
		// complete cleanup
		checkActivityExecutionEnded(executionactivity1);
		
		assertNotNull(ExecutionContext.getInstance().getActivityOutput(activityexecutionID));
		assertEquals(0, ExecutionContext.getInstance().getActivityOutput(activityexecutionID).size());
		assertNull(ExecutionContext.getInstance().getActivityOutput(activity2executionID));
		
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));

		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 8));
		eventlistactivity1.addAll(eventlist.subList(11, 13));
		eventlistactivity1.addAll(eventlist.subList(17, 18));

		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(9, 10));	
		eventlistactivity2.addAll(eventlist.subList(14, 16));
	}
	
	/**
	 * Tests the correct execution of an Activity that calls the same 
	 * Activity multiple times
	 * 
	 * Activity1 (Caller):
	 * InitialNode
	 * ForkNode
	 * CallBehaviorAction1
	 * CallBehaviorAction2
	 * 
	 * Acitivty1 ControlFlow:
	 * InitialNode --> ForkNode
	 * ForkNode --> CallBehaviorAction1
	 * ForkNode --> CallBehaviorAction2
	 * 
	 * Activity2 (Callee)
	 * CreateObjectAction (type = Class1)
	 */
	@Test
	public void testCallBehaviorMultipleTimes() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		
		Activity activity2 = ActivityFactory.createActivity("TestCallBehaviorMultipleTimes Activity2");
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity2, "CreateObjectAction", class1);
		
		Activity activity1 = ActivityFactory.createActivity("TestCallBehaviorMultipleTimes Activity1");
		InitialNode initial = ActivityFactory.createInitialNode(activity1, "InitialNode");
		ForkNode fork = ActivityFactory.createForkNode(activity1, "ForkNode");
		CallBehaviorAction call1 = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction1", activity2);
		CallBehaviorAction call2 = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction1", activity2);
		ActivityFactory.createControlFlow(activity1, initial, fork);
		ActivityFactory.createControlFlow(activity1, fork, call1);
		ActivityFactory.createControlFlow(activity1, fork, call2);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity1entry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity1, activity1entry.getActivity());				
		assertNull(activity1entry.getParent());		
		int activity1executionID = activity1entry.getActivityExecutionID();		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity1, step1.getLocation());	
		assertEquals(activity1entry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initial, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).get(0));	
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());				
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity1executionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(initial, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());		
		assertEquals(activity1entry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(eventlist.get(2), ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(initial, step2.getLocation());	
		assertEquals(activity1entry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(fork, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(fork, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());						
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity1executionID);						
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(fork, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());		
		assertEquals(activity1entry, ((TraceEvent)eventlist.get(5)).getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(eventlist.get(5), ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(fork, step3.getLocation());	
		assertEquals(activity1entry, step3.getParent());
		assertEquals(2, step3.getNewEnabledNodes().size());
		assertTrue(step3.getNewEnabledNodes().contains(call1));
		assertTrue(step3.getNewEnabledNodes().contains(call2));
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity1executionID).contains(call1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity1executionID).contains(call2));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
				
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity1executionID, call1);						
	
		assertEquals(11, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent call1entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(call1, call1entry.getNode());	
		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry_1 = (ActivityEntryEvent)eventlist.get(9);
		int activity2executionID_1 = activity2entry_1.getActivityExecutionID();
		assertEquals(activity2, activity2entry_1.getActivity());
		assertEquals(call1entry, activity2entry_1.getParent());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(activity2, step4.getLocation());	
		assertEquals(activity2entry_1, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(create, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity1executionID).contains(call2));
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).contains(create));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity1executionID, call2);
		
		assertEquals(14, eventlist.size());
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent call2entry = (ActivityNodeEntryEvent)eventlist.get(11);
		assertEquals(call2, call2entry.getNode());	
		assertTrue(eventlist.get(12) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry_2 = (ActivityEntryEvent)eventlist.get(12);
		int activity2executionID_2 = activity2entry_2.getActivityExecutionID();
		assertEquals(activity2, activity2entry_2.getActivity());
		assertEquals(call2entry, activity2entry_2.getParent());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		SuspendEvent step5 = ((SuspendEvent)eventlist.get(13));
		assertEquals(activity2, step5.getLocation());	
		assertEquals(activity2entry_2, step5.getParent());
		assertEquals(1, step5.getNewEnabledNodes().size());
		assertEquals(create, step5.getNewEnabledNodes().get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).contains(create));
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_2).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID_2).contains(create));		
		
		//NEXT STEP
		ExecutionContext.getInstance().nextStep(activity2executionID_1);						
		
		assertEquals(19, eventlist.size());
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);		
		assertEquals(create, ((ActivityNodeEntryEvent)eventlist.get(14)).getNode());	
		assertEquals(activity2entry_1, ((TraceEvent)eventlist.get(14)).getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(eventlist.get(14), ((TraceEvent)eventlist.get(15)).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(16)).getActivity());	
		assertEquals(activity2entry_1, ((TraceEvent)eventlist.get(16)).getParent());
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(call1, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		assertEquals(call1entry, ((TraceEvent)eventlist.get(17)).getParent());		
		assertTrue(eventlist.get(18) instanceof SuspendEvent);
		SuspendEvent step6 = ((SuspendEvent)eventlist.get(18));
		assertEquals(call1, step6.getLocation());	
		assertEquals(activity1entry, step6.getParent());
		assertEquals(0, step6.getNewEnabledNodes().size());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_2).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID_2).contains(create));
		
		//NEXT STEP
		ExecutionContext.getInstance().nextStep(activity2executionID_2);						
		
		assertEquals(24, eventlist.size());
		assertTrue(eventlist.get(19) instanceof ActivityNodeEntryEvent);		
		assertEquals(create, ((ActivityNodeEntryEvent)eventlist.get(19)).getNode());	
		assertEquals(activity2entry_2, ((TraceEvent)eventlist.get(19)).getParent());
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(20)).getNode());
		assertEquals(eventlist.get(19), ((TraceEvent)eventlist.get(20)).getParent());
		assertTrue(eventlist.get(21) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(21)).getActivity());	
		assertEquals(activity2entry_2, ((TraceEvent)eventlist.get(21)).getParent());
		
		assertTrue(eventlist.get(22) instanceof ActivityNodeExitEvent);
		assertEquals(call2, ((ActivityNodeExitEvent)eventlist.get(22)).getNode());
		assertEquals(call2entry, ((TraceEvent)eventlist.get(22)).getParent());		
		assertTrue(eventlist.get(23) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(23)).getActivity());	
		assertEquals(activity1entry, ((TraceEvent)eventlist.get(23)).getParent());
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		assertEquals(class1, ((Object_)extensionalValueLists.get(extensionalValueLists.size()-1).get(0)).types.get(0));
		assertEquals(class1, ((Object_)extensionalValueLists.get(extensionalValueLists.size()-1).get(1)).types.get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_1).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID_2).size());
		
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));

		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 8));
		eventlistactivity1.add(eventlist.get(11));
		eventlistactivity1.addAll(eventlist.subList(17, 18));
		eventlistactivity1.addAll(eventlist.subList(22, 23));

		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(9, 10));	
		eventlistactivity2.addAll(eventlist.subList(14, 16));

		List<Event> eventlistactivity3 = new ArrayList<Event>();
		eventlistactivity3.addAll(eventlist.subList(12, 13));	
		eventlistactivity3.addAll(eventlist.subList(19, 21));
	}
	
	/**
	 * This test case tests the execution of nested CallBehaviorActions. The last Activity returns
	 * an object to the first calling activity.
	 * 
	 * Activity1:
	 * CallBehaviorAction (behavior = Activity2)
	 * 
	 * Activity2:
	 * CallBehaviorAction (behavior = Activity3)
	 * 
	 * Activity3:
	 * CreateObjectAction (type = Class1)
	 */
	@Test
	public void testCallBehaviorNested() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		
		Activity activity3 = ActivityFactory.createActivity("TestCallBehaviorNested Activity3");
		Parameter activity3_param = ActivityFactory.createParameter(activity3, "Output Parameter Activity 3 (" + activity3.name + ")", ParameterDirectionKind.out);		
		ActivityParameterNode activity3_paramnode = ActivityFactory.createActivityParameterNode(activity3, "Activity3 Output", activity3_param);				
		CreateObjectAction activity3_create = ActivityFactory.createCreateObjectAction(activity3, "Create Object Class1", class1);
		ActivityFactory.createObjectFlow(activity3, activity3_create.result, activity3_paramnode);
		
		Activity activity2 = ActivityFactory.createActivity("TestCallBehaviorNested Activity2");
		CallBehaviorAction activity2_call = ActivityFactory.createCallBehaviorAction(activity2, "CallBehaviorAction CallActivity3", activity3, 1);
		Parameter activity2_param = ActivityFactory.createParameter(activity2, "Output Parameter Activity 2 (" + activity2.name + ")", ParameterDirectionKind.out);		
		ActivityParameterNode activity2_paramnode = ActivityFactory.createActivityParameterNode(activity2, "Activity2 Output", activity2_param);
		ActivityFactory.createObjectFlow(activity2, activity2_call.result.get(0), activity2_paramnode);
		
		Activity activity1 = ActivityFactory.createActivity("TestCallBehaviorNested Activity1");
		CallBehaviorAction activity1_call = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction CallActivity2", activity2, 1);
		Parameter activity1_param = ActivityFactory.createParameter(activity1, "Output Parameter Activity 1 (" + activity1.name + ")", ParameterDirectionKind.out);		
		ActivityParameterNode activity1_paramnode = ActivityFactory.createActivityParameterNode(activity1, "Activity1 Output", activity1_param);
		ActivityFactory.createObjectFlow(activity1, activity1_call.result.get(0), activity1_paramnode);
		
		// DEBUG
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity1entry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity1, activity1entry.getActivity());				
		assertNull(activity1entry.getParent());		
		int activity1executionID = activity1entry.getActivityExecutionID();		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity1, step1.getLocation());	
		assertEquals(activity1entry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(activity1_call, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(activity1_call, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).get(0));	
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());				
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity1executionID);						
		
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent activity1_callentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(activity1_call, activity1_callentry.getNode());	
		assertEquals(activity1entry, activity1_callentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry = (ActivityEntryEvent)eventlist.get(3);
		int activity2executionID = activity2entry.getActivityExecutionID();
		assertEquals(activity2, activity2entry.getActivity());
		assertEquals(activity1_callentry, activity2entry.getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(activity2, step2.getLocation());	
		assertEquals(activity2entry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(activity2_call, step2.getNewEnabledNodes().get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
		assertEquals(activity2_call, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity2executionID);						
		
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent activity2_callentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(activity2_call, activity2_callentry.getNode());
		assertEquals(activity2entry, ((TraceEvent)eventlist.get(5)).getParent());
		assertTrue(eventlist.get(6) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity3entry = (ActivityEntryEvent)eventlist.get(6);
		int activity3executionID = activity3entry.getActivityExecutionID();
		assertEquals(activity3, activity3entry.getActivity());
		assertEquals(activity2_callentry, activity3entry.getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(activity3, step3.getLocation());	
		assertEquals(activity3entry, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(activity3_create, step3.getNewEnabledNodes().get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity3executionID).size());
		assertEquals(activity3_create, ExecutionContext.getInstance().getEnabledNodes(activity3executionID).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// NEXT STEP
		ExecutionContext.getInstance().nextStep(activity3executionID);						
		
		assertEquals(15, eventlist.size());
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(activity3_create, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());			
		assertEquals(activity3entry, ((TraceEvent)eventlist.get(8)).getParent());		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(activity3_create, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(eventlist.get(8), ((TraceEvent)eventlist.get(9)).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity3, ((ActivityExitEvent)eventlist.get(10)).getActivity());	
		assertEquals(activity3entry, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(activity2_call, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		assertEquals(activity2_callentry, ((TraceEvent)eventlist.get(11)).getParent());		
		assertTrue(eventlist.get(12) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(12)).getActivity());	
		assertEquals(activity2entry, ((TraceEvent)eventlist.get(12)).getParent());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(activity1_call, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());
		assertEquals(activity1_callentry, ((TraceEvent)eventlist.get(13)).getParent());		
		assertTrue(eventlist.get(14) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(14)).getActivity());	
		assertEquals(activity1entry, ((TraceEvent)eventlist.get(14)).getParent());				
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity3executionID).size());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		assertEquals(class1, ((Object_)extensionalValueLists.get(extensionalValueLists.size()-1).get(0)).types.get(0));
				
		assertNull(ExecutionContext.getInstance().getActivityOutput(activity3executionID));
		assertNull(ExecutionContext.getInstance().getActivityOutput(activity2executionID));
		assertNotNull(ExecutionContext.getInstance().getActivityOutput(activity1executionID));
		assertEquals(1, ExecutionContext.getInstance().getActivityOutput(activity1executionID).size());
		ParameterValue output = ExecutionContext.getInstance().getActivityOutput(activity1executionID).get(0);
		assertEquals(activity1_param, output.parameter);
		assertEquals(1, output.values.size());
		assertEquals(class1, ((Reference)(output.values.get(0))).referent.types.get(0));
		
		// Check activityExecutionID
		assertFalse(checkSameActivityExecutionID(eventlist));

		List<Event> eventlistactivity1 = new ArrayList<Event>();		
		eventlistactivity1.addAll(eventlist.subList(0, 2));
		eventlistactivity1.addAll(eventlist.subList(13, 14));

		List<Event> eventlistactivity2 = new ArrayList<Event>();
		eventlistactivity2.addAll(eventlist.subList(3, 5));	
		eventlistactivity2.addAll(eventlist.subList(11, 12));
		
		List<Event> eventlistactivity3 = new ArrayList<Event>();
		eventlistactivity3.addAll(eventlist.subList(6, 10));	
	}
	
	@Test
	public void testStepEventNewEnabledNodesWithParallelPaths() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		
		Activity activity = ActivityFactory.createActivity("testStepEventNewEnabledNodesWithForkNode");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "Create Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "Create Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "Create Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());

		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(2, step1.getNewEnabledNodes().size());
		assertTrue(step1.getNewEnabledNodes().contains(create1));
		assertTrue(step1.getNewEnabledNodes().contains(create3));

		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));

		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create1);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create2, step2.getNewEnabledNodes().get(0));

		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				

		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create2);

		assertEquals(8, eventlist.size());

		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(6)).getParent());	
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(create2, step3.getLocation());
		assertEquals(activityentry, step3.getParent());
		assertEquals(0, step3.getNewEnabledNodes().size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));

		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());

		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(9)).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(10)).getParent());

		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class1, o2.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	@Test
	public void testIllegalArguments() {
		/*
		 * nextStep(int executionID): Wrong execution ID
		 */
		try{
			ExecutionContext.getInstance().nextStep(0);
			fail("Expected IllegalArgumentException because of illegal execution id");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains(ExecutionContext.exception_illegalexecutionid));
		}
		
		/*
		 * nextStep(int executionID): ID of activity execution without any nodes, i.e., also without enabled nodes
		 */
		Activity activity = ActivityFactory.createActivity("testIllegalArguments1");		
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
		assertEquals(2, eventlist.size());
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);			
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());			
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());
		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		try {
			ExecutionContext.getInstance().nextStep(executionID);
			fail("Expected IllegalArgumentException because activity execution has no enabled nodes");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains(ExecutionContext.exception_noenablednodes));			
		}
		
		/*
		 * nextStep(int, ActivityNode): activity node that is not enabled
		 */
		Activity activity2 = ActivityFactory.createActivity("testIllegalArguments2");
		InitialNode initial = ActivityFactory.createInitialNode(activity2, "InitialNode");
		MergeNode merge = ActivityFactory.createMergeNode(activity2, "MergeNode");
		ActivityFactory.createControlFlow(activity2, initial, merge);
		eventlist = new ArrayList<Event>();
		ExecutionContext.getInstance().executeStepwise(activity2, null, new ParameterValueList());
		assertEquals(2, eventlist.size());
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);			
		assertEquals(activity2, ((ActivityEntryEvent)eventlist.get(0)).getActivity());	
		int executionID2 = ((TraceEvent)eventlist.get(0)).getActivityExecutionID();
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity2, step1.getLocation());
		assertEquals(eventlist.get(0), step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initial, step1.getNewEnabledNodes().get(0));
		
		try {
			ExecutionContext.getInstance().nextStep(executionID2, merge);
			fail("Expected IllegalArgumentException because activity node is not enabled in activity execution");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains(ExecutionContext.exception_illegalactivitynode));
		}
	}
	
	/**
	 * This test case tests the interleaved execution of several activities.
	 */
	@Test
	public void testStartOfTwoActivities() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		
		Activity activity1 = ActivityFactory.createActivity("testStartOfTwoActivities activity1");
		CreateObjectAction action_create1 = ActivityFactory.createCreateObjectAction(activity1, "Create Object Class1", class1);		
		CreateObjectAction action_create2 = ActivityFactory.createCreateObjectAction(activity1, "Create Object Class2", class2);
		ActivityFactory.createControlFlow(activity1, action_create1, action_create2);
		
		Activity activity2 = ActivityFactory.createActivity("testStartOfTwoActivities activity2");
		CreateObjectAction action_create3 = ActivityFactory.createCreateObjectAction(activity2, "Create Object Class3", class3);
		CreateObjectAction action_create4 = ActivityFactory.createCreateObjectAction(activity2, "Create Object Class4", class4);
		ActivityFactory.createControlFlow(activity2, action_create3, action_create4);
		
		/*
		 * 1) debug activity1
		 * 2) debug activity2
		 * 3) resume activity1
		 * 4) resume activity2  
		 */
		
		// Start Debugging Activity 1
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());

		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry1 = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry1.getActivity());		
		assertNull(activityentry1.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().size());
		assertEquals(action_create1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().get(0));

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).size());
		assertEquals(action_create1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).get(0));

		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());


		// Start Debugging Activity 2
		ExecutionContext.getInstance().executeStepwise(activity2, null, new ParameterValueList());

		assertEquals(4, eventlist.size());

		assertTrue(eventlist.get(2) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry2 = ((ActivityEntryEvent)eventlist.get(2));		
		assertEquals(activity2, activityentry2.getActivity());		
		assertNull(activityentry2.getParent());

		assertTrue(eventlist.get(3) instanceof SuspendEvent);
		assertEquals(activity2, ((SuspendEvent)eventlist.get(3)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().size());
		assertEquals(action_create3, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().get(0));

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).size());
		assertEquals(action_create3, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).get(0));

		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
				
				
		// Resume Activity 1
		ExecutionContext.getInstance().resume(activityentry1.getActivityExecutionID());

		assertEquals(9, eventlist.size());

		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create1, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(action_create1, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create2, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());	

		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(8)).getActivity());

		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));		
		
		// Resume Activity 2
		ExecutionContext.getInstance().resume(activityentry2.getActivityExecutionID());

		assertEquals(14, eventlist.size());

		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create3, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(action_create3, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());	
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create4, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());	

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(action_create4, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());	

		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(13)).getActivity());

		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		Object_ o3 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		
		Object_ o4 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));	
		
		/*
		 * 1) debug activity1
		 * 2) debug activity2
		 * 3) resume activity1
		 * 4) next step activity2
		 * 5) next step activity2  
		 */
		
		eventlist.clear();
		extensionalValueLists.clear();
		
		// Start Debugging Activity 1
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());

		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry1 = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry1.getActivity());		
		assertNull(activityentry1.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().size());
		assertEquals(action_create1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().get(0));

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).size());
		assertEquals(action_create1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).get(0));

		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());


		// Start Debugging Activity 2
		ExecutionContext.getInstance().executeStepwise(activity2, null, new ParameterValueList());

		assertEquals(4, eventlist.size());

		assertTrue(eventlist.get(2) instanceof ActivityEntryEvent);
		activityentry2 = ((ActivityEntryEvent)eventlist.get(2));		
		assertEquals(activity2, activityentry2.getActivity());		
		assertNull(activityentry2.getParent());

		assertTrue(eventlist.get(3) instanceof SuspendEvent);
		assertEquals(activity2, ((SuspendEvent)eventlist.get(3)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().size());
		assertEquals(action_create3, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().get(0));

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).size());
		assertEquals(action_create3, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).get(0));

		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
				
				
		// Resume Activity 1
		ExecutionContext.getInstance().resume(activityentry1.getActivityExecutionID());

		assertEquals(9, eventlist.size());

		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create1, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(action_create1, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create2, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());	

		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(8)).getActivity());

		assertEquals(6, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(4));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(5));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));		
		
		// Next Step Activity 2
		ExecutionContext.getInstance().nextStep(activityentry2.getActivityExecutionID());

		assertEquals(12, eventlist.size());

		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create3, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(action_create3, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());	
		
		assertTrue(eventlist.get(11) instanceof SuspendEvent);
		assertEquals(action_create3, ((SuspendEvent)eventlist.get(11)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(11)).getNewEnabledNodes().size());
		assertEquals(action_create4, ((SuspendEvent)eventlist.get(11)).getNewEnabledNodes().get(0));
		
		assertEquals(7, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		o3 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(6));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		
		// Next Step Activity 2
		ExecutionContext.getInstance().nextStep(activityentry2.getActivityExecutionID());

		assertEquals(15, eventlist.size());
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create4, ((ActivityNodeEntryEvent)eventlist.get(12)).getNode());	

		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(action_create4, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());	

		assertTrue(eventlist.get(14) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(14)).getActivity());

		assertEquals(8, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o4 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(7));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));		
		
		
		/*
		 * Breakpoint for create object action 1 in activity 1
		 * 1) debug activity1
		 * 2) debug activity2
		 * 3) resume activity2
		 * 4) next step activity1
		 * 5) next step activity1
		 */
		
		eventlist.clear();
		extensionalValueLists.clear();
		
		Breakpoint breakpoint = new BreakpointImpl(action_create1);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging Activity 1
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());

		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry1 = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry1.getActivity());		
		assertNull(activityentry1.getParent());

		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(1)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(1)).getBreakpoints().get(0));		
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().size());
		assertEquals(action_create1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().get(0));		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).size());
		assertEquals(action_create1, ExecutionContext.getInstance().getEnabledNodes(activityentry1.getActivityExecutionID()).get(0));

		assertEquals(8, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Start Debugging Activity 2
		ExecutionContext.getInstance().executeStepwise(activity2, null, new ParameterValueList());

		assertEquals(4, eventlist.size());

		assertTrue(eventlist.get(2) instanceof ActivityEntryEvent);
		activityentry2 = ((ActivityEntryEvent)eventlist.get(2));		
		assertEquals(activity2, activityentry2.getActivity());		
		assertNull(activityentry2.getParent());

		assertTrue(eventlist.get(3) instanceof SuspendEvent);
		assertEquals(activity2, ((SuspendEvent)eventlist.get(3)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().size());
		assertEquals(action_create3, ((SuspendEvent)eventlist.get(3)).getNewEnabledNodes().get(0));

		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).size());
		assertEquals(action_create3, ExecutionContext.getInstance().getEnabledNodes(activityentry2.getActivityExecutionID()).get(0));

		assertEquals(8, extensionalValueLists.get(extensionalValueLists.size()-1).size());
								
		// Resume Activity 2
		ExecutionContext.getInstance().resume(activityentry2.getActivityExecutionID());

		assertEquals(9, eventlist.size());

		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create3, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(action_create3, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create4, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_create4, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());	

		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(8)).getActivity());

		assertEquals(10, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		o3 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(8));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		
		o4 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(9));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));		
		
		// Next Step Activity 1
		ExecutionContext.getInstance().nextStep(activityentry1.getActivityExecutionID());

		assertEquals(12, eventlist.size());

		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create1, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(action_create1, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());	
		
		assertTrue(eventlist.get(11) instanceof SuspendEvent);
		assertEquals(action_create1, ((SuspendEvent)eventlist.get(11)).getLocation());
		assertEquals(1, ((SuspendEvent)eventlist.get(11)).getNewEnabledNodes().size());
		assertEquals(action_create2, ((SuspendEvent)eventlist.get(11)).getNewEnabledNodes().get(0));
		
		assertEquals(11, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(10));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Next Step Activity 1
		ExecutionContext.getInstance().nextStep(activityentry1.getActivityExecutionID());

		assertEquals(15, eventlist.size());
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		assertEquals(action_create2, ((ActivityNodeEntryEvent)eventlist.get(12)).getNode());	

		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(action_create2, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());	

		assertTrue(eventlist.get(14) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(14)).getActivity());

		assertEquals(12, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(11));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
	}
	
	/**
	 * Test of the clean up if an activity execution is terminated
	 * 
	 * Activity 1 (Caller):
	 * IntitialNode
	 * CallBehaviorAction
	 * 
	 * Activity 1 ControlFlow:
	 * InitialNode1 --> CallBehaviorAction1
	 * 
	 * Activity 2 (Callee):
	 * InitialNode2
	 */
	@Test
	public void testTerminationOfActivityExecution() {
		Activity activity2 = ActivityFactory.createActivity("testTerminationOfActivityExecution Callee");
		InitialNode initial2 = ActivityFactory.createInitialNode(activity2, "InitialNode 3");
		
		Activity activity1 = ActivityFactory.createActivity("testTerminationOfActivityExecution Caller");
		InitialNode initial1 = ActivityFactory.createInitialNode(activity1, "InitialNode 1");
		CallBehaviorAction call = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction", activity2);
		ActivityFactory.createControlFlow(activity1, initial1, call);
		
		/*
		 * 1) Run to InitialNode2 of Callee (Activity2)
		 * 
		 * First run: 
		 * 2) Terminate execution of Caller (Activity1)
		 * 
		 * Second run:
		 * 3) Terminate execution of Callee (Activity2)
		 * 
		 * In both cases both ActivityExecutions must be terminated, i.e. the data structures
		 * must be cleared from both ActivityExecutions. 
		 */						
		
		for(int i=0;i<2;++i) {
			
			//Breakpoint at InitialNode2 of Callee
			Breakpoint breakpoint = new BreakpointImpl(initial2);
			ExecutionContext.getInstance().addBreakpoint(breakpoint);
			
			//DEBUG Activity1
			ExecutionContext.getInstance().executeStepwise(activity1, null, null);
			
			assertEquals(2, eventlist.size());							
			assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
			ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
			int activity1executionID = activityentry.getActivityExecutionID();
			assertEquals(activity1, activityentry.getActivity());				
			
			assertTrue(eventlist.get(1) instanceof SuspendEvent);
			assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());	
			assertEquals(activityentry, ((SuspendEvent)eventlist.get(1)).getParent());
			assertEquals(1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().size());
			assertEquals(initial1, ((SuspendEvent)eventlist.get(1)).getNewEnabledNodes().get(0));
			
			assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
			assertEquals(initial1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).get(0));	
					
			// Clean up tests		
			assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
			assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity1executionID).contains(initial1));
			
			assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity1executionID));		
			ActivityExecution executionactivity1 = ExecutionContext.getInstance().getActivityExecution(activity1executionID);			
			assertEquals(activity1, executionactivity1.getTypes().get(0));
			
			List<ActivityNode> nodes = new ArrayList<ActivityNode>();
			nodes.add(initial1);
			checkActivatedNodes(executionactivity1, nodes);
			
			List<ActivityExecution> callees = new ArrayList<ActivityExecution>();
			checkCallHierarchy(executionactivity1, callees, null);
			
			assertNull(ExecutionContext.getInstance().getActivityOutput(activity1executionID));
			
			// Resume Activity1
			ExecutionContext.getInstance().resume(activity1executionID);						
		
			assertEquals(7, eventlist.size());
			
			assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
			assertEquals(initial1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());		
	
			assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
			assertEquals(initial1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
										
			assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
			assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	
			
			assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
			ActivityEntryEvent activity2entry = (ActivityEntryEvent)eventlist.get(5);
			int activity2executionID = activity2entry.getActivityExecutionID();
			assertEquals(activity2, activity2entry.getActivity());
	
			assertTrue(eventlist.get(6) instanceof BreakpointEvent);
			assertEquals(1, ((BreakpointEvent)eventlist.get(6)).getBreakpoints().size());
			assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(6)).getBreakpoints().get(0));		
			assertEquals(activity2, ((SuspendEvent)eventlist.get(6)).getLocation());	
			assertEquals(activity2entry, ((SuspendEvent)eventlist.get(6)).getParent());
			assertEquals(1, ((SuspendEvent)eventlist.get(6)).getNewEnabledNodes().size());
			assertEquals(initial2, ((SuspendEvent)eventlist.get(6)).getNewEnabledNodes().get(0));		
	
			// Clean up tests
			assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
			assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
			assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2executionID).contains(initial2));
			
			assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity1executionID));
			assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity2executionID));
			assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activity1executionID));
			assertEquals(activity1, executionactivity1.getTypes().get(0));
			ActivityExecution executionactivity2 = ExecutionContext.getInstance().getActivityExecution(activity2executionID);
			assertEquals(activity2, executionactivity2.getTypes().get(0));
	
			nodes = new ArrayList<ActivityNode>();
			checkActivatedNodes(executionactivity1, nodes);
			
			callees = new ArrayList<ActivityExecution>();
			callees.add(executionactivity2);
			checkCallHierarchy(executionactivity1, callees, null);	
	
			nodes = new ArrayList<ActivityNode>();
			nodes.add(initial2);
			checkActivatedNodes(executionactivity2, nodes);
			
			callees = new ArrayList<ActivityExecution>();
			checkCallHierarchy(executionactivity2, callees, executionactivity1);
			
			assertNull(ExecutionContext.getInstance().getActivityOutput(activity1executionID));
			
			// Termination
			if(i==0) {
				ExecutionContext.getInstance().terminate(activity1executionID);
			} else if(i==1) {
				ExecutionContext.getInstance().terminate(activity2executionID);
			}
			
			// Clean up tests 
			assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity1executionID).size());
			assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2executionID).size());
							
			assertNotNull(ExecutionContext.getInstance().getActivityExecution(activity1executionID));
			assertEquals(executionactivity1, ExecutionContext.getInstance().getActivityExecution(activity1executionID));
							
			checkActivatedNodes(executionactivity1, null);
			checkCallHierarchy(executionactivity1, null, null, true);		
			
			checkActivatedNodes(executionactivity2, null);
			checkCallHierarchy(executionactivity2, null, null, true);
			
			// complete cleanup
			checkActivityExecutionEnded(executionactivity1);
			
			assertNull(ExecutionContext.getInstance().getActivityOutput(activity1executionID));
			assertNull(ExecutionContext.getInstance().getActivityOutput(activity2executionID));
			
			// Reset infrastructure
			eventlist.clear();
			ExecutionContext.getInstance().reset();
			ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
		}
	}
	
	/**
	 * Test of call operation action + read self action
	 * 
	 * Class class
	 * Property property 
	 * 
	 * Activity (Calling the operation):
	 * intitial node
	 * read self action
	 * call operation action
	 * activity final node
	 * activity parameter node
	 * 
	 * initial node --> read self action
	 * read self action.result --> call operation action.target
	 * call operation action.result --> activity parameter node
	 * call operation action --> activity final node
	 * 
	 * Operation Activity:
	 * read self action
	 * read property action
	 * activity parameter node
	 * 
	 * read self action.result --> read property.object
	 * read property.result --> activity parameter node
	 * 
	 * Context for executing activity is object of the class with property="value"
	 * This value is the output of the activity
	 */
	@Test
	public void testCallOperationAction() {				
		Class_ class_ = ActivityFactory.createClass("Class");
		Property property = ActivityFactory.createProperty("property", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		
		Activity opactivity = ActivityFactory.createActivity("operation activity");
		ReadSelfAction readself2 = ActivityFactory.createReadSelfAction(opactivity, "read self 2");
		ReadStructuralFeatureAction readproperty = ActivityFactory.createReadStructuralFeatureAction(opactivity, "read property", property);
		Parameter opactivityparam = ActivityFactory.createParameter(opactivity, "operation activity parameter", ParameterDirectionKind.out);
		ActivityParameterNode opactivityparamnode = ActivityFactory.createActivityParameterNode(opactivity, "operation activity parameter node", opactivityparam);
		ActivityFactory.createObjectFlow(opactivity, readself2.result, readproperty.object);
		ActivityFactory.createObjectFlow(opactivity, readproperty.result, opactivityparamnode);
				
		Parameter operationparam = ActivityFactory.createParameter("operation param", ParameterDirectionKind.return_);
		ParameterList operationparams = new ParameterList();
		operationparams.add(operationparam);
		Operation operation = ActivityFactory.createOperation("operation", operationparams, opactivity, class_);		
		
		Activity activity = ActivityFactory.createActivity("call a operation");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial");
		ReadSelfAction readself1 = ActivityFactory.createReadSelfAction(activity, "read self 1");
		CallOperationAction calloperation = ActivityFactory.createCallOperationAction(activity, "call operation", operation);
		ActivityFinalNode activityfinal = ActivityFactory.createActivityFinalNode(activity, "final");
		Parameter activityparam = ActivityFactory.createParameter(activity, "activity parameter", ParameterDirectionKind.out);
		ActivityParameterNode activityparamnode = ActivityFactory.createActivityParameterNode(activity, "activity parameter node", activityparam);
		ActivityFactory.createControlFlow(activity, initial, readself1);
		ActivityFactory.createObjectFlow(activity, readself1.result, calloperation.target);
		ActivityFactory.createObjectFlow(activity, calloperation.result.get(0), activityparamnode);
		ActivityFactory.createControlFlow(activity, calloperation, activityfinal);				
		
		Object_ object = new Object_();
		object.types.add(class_);
		object.createFeatureValues();
		StringValue value = new StringValue();
		value.value = "value";
		ValueList values = new ValueList();
		values.add(value);
		object.setFeatureValue(property, values, 0);
		ExecutionContext.getInstance().getLocus().add(object);
		
		// Start execution
		ExecutionContext.getInstance().executeStepwise(activity, object, new ParameterValueList());				
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int activityexecutionID = activityentry.getActivityExecutionID();
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());	
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initial, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
				
		// Next step: initial node
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initial, initialentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(initial, step2.getLocation());	
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(readself1, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(readself1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));			
				
		// Next step: read self 1
		ExecutionContext.getInstance().nextStep(activityexecutionID);					
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent readself1entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(readself1, readself1entry.getNode());	
		assertEquals(activityentry, readself1entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(readself1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(readself1entry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(readself1, step3.getLocation());	
		assertEquals(activityentry, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(calloperation, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(calloperation, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
		
		// Next step: call operation
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
		
		assertEquals(11, eventlist.size());
		ActivityNodeEntryEvent callactionentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(calloperation, callactionentry.getNode());
		assertEquals(activityentry, callactionentry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		ActivityEntryEvent opactivityentry = (ActivityEntryEvent)eventlist.get(9);
		int opactivityexecutionID = opactivityentry.getActivityExecutionID();
		assertEquals(opactivity, opactivityentry.getActivity());
		assertEquals(callactionentry, opactivityentry.getParent());
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(opactivity, step4.getLocation());
		assertEquals(opactivityentry, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(readself2, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).size());
		assertEquals(readself2, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		
		// Next step: read self 2
		ExecutionContext.getInstance().nextStep(opactivityexecutionID);				
		
		assertEquals(14, eventlist.size());
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent readself2entry =(ActivityNodeEntryEvent)eventlist.get(11); 
		assertEquals(readself2, readself2entry.getNode());	
		assertEquals(opactivityentry, readself2entry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(readself2, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(readself2entry, ((TraceEvent)eventlist.get(12)).getParent());
		assertTrue(eventlist.get(13) instanceof SuspendEvent);
		SuspendEvent step5 = ((SuspendEvent)eventlist.get(13));
		assertEquals(readself2, step5.getLocation());	
		assertEquals(opactivityentry, step5.getParent());
		assertEquals(1, step5.getNewEnabledNodes().size());
		assertEquals(readproperty, step5.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).size());
		assertEquals(readproperty, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		
		// Next step: read property
		ExecutionContext.getInstance().nextStep(opactivityexecutionID);		
		
		assertEquals(19, eventlist.size());
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent readpropertyentry = (ActivityNodeEntryEvent)eventlist.get(14); 
		assertEquals(readproperty, readpropertyentry.getNode());	
		assertEquals(opactivityentry, readpropertyentry.getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(readproperty, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(readpropertyentry, ((TraceEvent)eventlist.get(15)).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(opactivity, ((ActivityExitEvent)eventlist.get(16)).getActivity());
		assertEquals(opactivityentry, ((TraceEvent)eventlist.get(16)).getParent());
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(calloperation, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		assertEquals(callactionentry, ((TraceEvent)eventlist.get(17)).getParent());
		assertTrue(eventlist.get(18) instanceof SuspendEvent);
		SuspendEvent step6 = ((SuspendEvent)eventlist.get(18));
		assertEquals(calloperation, step6.getLocation());
		assertEquals(activityentry, step6.getParent());
		assertEquals(1, step6.getNewEnabledNodes().size());
		assertEquals(activityfinal, step6.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(activityfinal, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).size());				
		
		// Next step: activity final node
		ExecutionContext.getInstance().nextStep(activityexecutionID);
		
		assertEquals(22, eventlist.size());
		assertTrue(eventlist.get(19) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent activityfinalentry = (ActivityNodeEntryEvent)eventlist.get(19);
		assertEquals(activityfinal, activityfinalentry.getNode());	
		assertEquals(activityentry, activityfinalentry.getParent());
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		assertEquals(activityfinal, ((ActivityNodeExitEvent)eventlist.get(20)).getNode());
		assertEquals(activityfinalentry, ((TraceEvent)eventlist.get(20)).getParent());
		assertTrue(eventlist.get(21) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(21)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(21)).getParent());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(opactivityexecutionID).size());
				
		// Check output of operation activity
		ParameterValueList outputs = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, outputs.size());
		ParameterValue output = outputs.get(0);
		assertEquals(activityparam, output.parameter);
		ValueList outputvalues = output.values;
		assertEquals(1, outputvalues.size());
		Value outputvalue = outputvalues.get(0);
		assertTrue(outputvalue instanceof StringValue);
		StringValue outputvaluestr = (StringValue)outputvalue;
		assertEquals(value.value, outputvaluestr.value);		
	}
	
	/**
	 * Classes:
	 * Net
	 * 	transitions : Transition
	 * Transition
	 * 	name : String
	 * 	isEnabled : boolean
	 * 	net : Net
	 * 
	 * Activity:
	 * read self
	 * read transition
	 * expansion region
	 * 	fork
	 * 	read isEnabled
	 * 	decision
	 * specify 1
	 * call behavior list get
	 * activity parameter node (out)
	 * 
	 * read self . result --> read transitions . object
	 * read transitions . result --> expansion region . inputElement.get(0)
	 * expansion region . inputElement.get(0) --> fork
	 * fork --> read isEnabled . object
	 * fork --> decision
	 * read isEnabled. result --> decision (decision input flow)
	 * expansion region . outputElement.get(0) --> call behavior list get . list
	 * expansion region --> specify1
	 * specify1 . result --> call behavior list get . index
	 * call behavior list get . activity parameter node
	 * 
	 */
	@Test
	public void testExpansionRegion() {
		ExpansionRegionTestData testdata = new ExpansionRegionTestData();
		testdata.initialize();
		
		// Activity
		Activity activity = testdata.getActivity();
		ReadSelfAction readself = testdata.getReadself();
		ReadStructuralFeatureAction readtransitions = testdata.getReadtransitions();
		ForkNode fork = testdata.getFork();
		ReadStructuralFeatureAction readisenabled = testdata.getReadisenabled();
		DecisionNode decision = testdata.getDecision();
		ExpansionRegion expansionregion = testdata.getExpansionregion();
		ValueSpecificationAction specify1 = testdata.getSpecify1();		
		CallBehaviorAction calllistget = testdata.getCalllistget();		
		Parameter activityparameter = testdata.getActivityparameter();

		// Objects
		Object_ obj_net = testdata.getObj_net();
		Object_ obj_transition1 = testdata.getObj_transition1();		
		
		// Start execution
		ExecutionContext.getInstance().executeStepwise(activity, obj_net, null);
		
		assertEquals(2, eventlist.size());							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int activityexecutionID = activityentry.getActivityExecutionID();
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());	
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(readself, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(readself, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));	
				
		// Next step: read self
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
	
		assertEquals(5, eventlist.size());
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent readselfentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(readself, readselfentry.getNode());		
		assertEquals(activityentry, ((TraceEvent)eventlist.get(2)).getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(readself, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(readselfentry, ((TraceEvent)eventlist.get(3)).getParent());
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(readself, step2.getLocation());	
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(readtransitions, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(readtransitions, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));			
				
		// Next step: read transitions
		ExecutionContext.getInstance().nextStep(activityexecutionID);					
	
		assertEquals(8, eventlist.size());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent readtransitionsentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(readtransitions, readtransitionsentry.getNode());	
		assertEquals(activityentry, readtransitionsentry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(readtransitions, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(readtransitionsentry, ((TraceEvent)eventlist.get(6)).getParent());
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(readtransitions, step3.getLocation());	
		assertEquals(activityentry, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(expansionregion, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(expansionregion, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
		
		// Next step: expansion region
		ExecutionContext.getInstance().nextStep(activityexecutionID);						
		
		assertEquals(10, eventlist.size());
		ActivityNodeEntryEvent expansionregionentry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(expansionregion, expansionregionentry.getNode());
		assertEquals(activityentry, expansionregionentry.getParent());
		assertTrue(eventlist.get(9) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(9));
		assertEquals(expansionregion, step4.getLocation());
		assertEquals(activityentry, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(fork, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(fork, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
		
		// Nodes of the expansion region are executed for all three transitions
		for(int i=0;i<3;++i) {
			// Next step: fork node
			ExecutionContext.getInstance().nextStep(activityexecutionID);				
		
			assertEquals(13+9*i, eventlist.size());
			assertTrue(eventlist.get(10+9*i) instanceof ActivityNodeEntryEvent);
			ActivityNodeEntryEvent forkentry =(ActivityNodeEntryEvent)eventlist.get(10+9*i); 
			assertEquals(fork, forkentry.getNode());
			assertEquals(activityentry, forkentry.getParent());
			assertTrue(eventlist.get(11+9*i) instanceof ActivityNodeExitEvent);
			assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(11+9*i)).getNode());
			assertEquals(forkentry, ((TraceEvent)eventlist.get(11+9*i)).getParent());
			assertTrue(eventlist.get(12+9*i) instanceof SuspendEvent);
			SuspendEvent step5 = ((SuspendEvent)eventlist.get(12+9*i));
			assertEquals(fork, step5.getLocation());	
			assertEquals(activityentry, step5.getParent());
			assertEquals(1, step5.getNewEnabledNodes().size());
			assertEquals(readisenabled, step5.getNewEnabledNodes().get(0));
			
			assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
			assertEquals(readisenabled, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
			
			// Next step: read isEnabled
			ExecutionContext.getInstance().nextStep(activityexecutionID);	
					
			assertEquals(16+9*i, eventlist.size());
			assertTrue(eventlist.get(13+9*i) instanceof ActivityNodeEntryEvent);
			ActivityNodeEntryEvent readisenabledentry =(ActivityNodeEntryEvent)eventlist.get(13+9*i); 
			assertEquals(readisenabled, readisenabledentry.getNode());
			assertEquals(activityentry, readisenabledentry.getParent());
			assertTrue(eventlist.get(14+9*i) instanceof ActivityNodeExitEvent);
			assertEquals(readisenabled, ((ActivityNodeExitEvent)eventlist.get(14+9*i)).getNode());
			assertEquals(readisenabledentry, ((TraceEvent)eventlist.get(14+9*i)).getParent());
			assertTrue(eventlist.get(15+9*i) instanceof SuspendEvent);
			SuspendEvent step6 = ((SuspendEvent)eventlist.get(15+9*i));
			assertEquals(readisenabled, step6.getLocation());	
			assertEquals(activityentry, step6.getParent());
			assertEquals(1, step6.getNewEnabledNodes().size());
			assertEquals(decision, step6.getNewEnabledNodes().get(0));
			
			assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
			assertEquals(decision, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
			
			// Next step: decision
			ExecutionContext.getInstance().nextStep(activityexecutionID);	
			
			if(i<2) {
				assertEquals(19+9*i, eventlist.size());
			} else {
				assertEquals(19+9*i+1, eventlist.size());
			}
			assertTrue(eventlist.get(16+9*i) instanceof ActivityNodeEntryEvent);
			ActivityNodeEntryEvent decisionentry =(ActivityNodeEntryEvent)eventlist.get(16+9*i); 
			assertEquals(decision, decisionentry.getNode());
			assertEquals(activityentry, decisionentry.getParent());
			assertTrue(eventlist.get(17+9*i) instanceof ActivityNodeExitEvent);
			assertEquals(decision, ((ActivityNodeExitEvent)eventlist.get(17+9*i)).getNode());
			assertEquals(decisionentry, ((TraceEvent)eventlist.get(17+9*i)).getParent());
			if(i<2) {
				assertTrue(eventlist.get(18+9*i) instanceof SuspendEvent);
				SuspendEvent step7 = ((SuspendEvent)eventlist.get(18+9*i));
				assertEquals(decision, step7.getLocation());	
				assertEquals(activityentry, step7.getParent());
				assertEquals(1, step7.getNewEnabledNodes().size());
				assertEquals(fork, step7.getNewEnabledNodes().get(0));
				
				assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
				assertEquals(fork, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
			} else {
				assertTrue(eventlist.get(18+9*i) instanceof ActivityNodeExitEvent);
				assertEquals(expansionregion, ((ActivityNodeExitEvent)eventlist.get(18+9*i)).getNode());
				assertEquals(expansionregionentry, ((TraceEvent)eventlist.get(18+9*i)).getParent());
				
				assertTrue(eventlist.get(19+9*i) instanceof SuspendEvent);
				SuspendEvent step7 = ((SuspendEvent)eventlist.get(19+9*i));
				assertEquals(decision, step7.getLocation());	
				assertEquals(activityentry, step7.getParent());
				assertEquals(1, step7.getNewEnabledNodes().size());
				assertEquals(specify1, step7.getNewEnabledNodes().get(0));
				
				assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
				assertEquals(specify1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));
			}
		}
		
		// Next step: specify1
		ExecutionContext.getInstance().nextStep(activityexecutionID);		
		
		assertEquals(41, eventlist.size());
		assertTrue(eventlist.get(38) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent specify1propertyentry = (ActivityNodeEntryEvent)eventlist.get(38); 
		assertEquals(specify1, specify1propertyentry.getNode());	
		assertEquals(activityentry, specify1propertyentry.getParent());
		assertTrue(eventlist.get(39) instanceof ActivityNodeExitEvent);
		assertEquals(specify1, ((ActivityNodeExitEvent)eventlist.get(39)).getNode());
		assertEquals(specify1propertyentry, ((TraceEvent)eventlist.get(39)).getParent());
		assertTrue(eventlist.get(40) instanceof SuspendEvent);
		SuspendEvent step8 = ((SuspendEvent)eventlist.get(40));
		assertEquals(specify1, step8.getLocation());
		assertEquals(activityentry, step8.getParent());
		assertEquals(1, step8.getNewEnabledNodes().size());
		assertEquals(calllistget, step8.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
		assertEquals(calllistget, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).get(0));				
		
		// Next step: call list get
		ExecutionContext.getInstance().nextStep(activityexecutionID);
		
		assertEquals(44, eventlist.size());
		assertTrue(eventlist.get(41) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent calllistgetentry = (ActivityNodeEntryEvent)eventlist.get(41);
		assertEquals(calllistget, calllistgetentry.getNode());	
		assertEquals(activityentry, calllistgetentry.getParent());
		assertTrue(eventlist.get(42) instanceof ActivityNodeExitEvent);
		assertEquals(calllistget, ((ActivityNodeExitEvent)eventlist.get(42)).getNode());
		assertEquals(calllistgetentry, ((TraceEvent)eventlist.get(42)).getParent());
		assertTrue(eventlist.get(43) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(43)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(43)).getParent());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityexecutionID).size());
				
		// Check output of operation activity
		ParameterValueList outputs = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, outputs.size());
		ParameterValue output = outputs.get(0);
		assertEquals(activityparameter, output.parameter);
		ValueList outputvalues = output.values;
		assertEquals(1, outputvalues.size());
		Value outputvalue = outputvalues.get(0);
		
		assertTrue(outputvalue instanceof Reference);
		Object_ outputobj = ((Reference)outputvalue).referent;
		assertEquals(obj_transition1, outputobj);		
	}
	
	/**
	 * Classes:
	 * Net
	 * 	transitions : Transition
	 * Transition
	 * 	name : String
	 * 	isEnabled : boolean
	 * 	net : Net
	 * 
	 * Activity:
	 * read self
	 * read transition
	 * expansion region
	 * 	fork
	 * 	call isenabled
	 * 	decision
	 * specify 1
	 * call behavior list get
	 * activity parameter node (out)
	 * 
	 * read self . result --> read transitions . object
	 * read transitions . result --> expansion region . inputElement.get(0)
	 * expansion region . inputElement.get(0) --> fork
	 * fork --> call isEnabled . target
	 * fork --> decision
	 * call isEnabled. output[0] --> decision (decision input flow)
	 * expansion region . outputElement.get(0) --> call behavior list get . list
	 * expansion region --> specify1
	 * specify1 . result --> call behavior list get . index
	 * call behavior list get . activity parameter node
	 * 
	 * Activity isenabled:
	 * read self transition
	 * read isenabled
	 * 
	 * read self transition . result --> read isenabled . object
	 */
	@Test
	public void testExpansionRegionWithCallAction() {
		ExpansionRegionTestData testdata = new ExpansionRegionTestData();
		testdata.initializeWithCallOperationAction();
		
		// Activity
		Activity activity = testdata.getActivity();
		ReadSelfAction readself = testdata.getReadself();
		ReadStructuralFeatureAction readtransitions = testdata.getReadtransitions();
		ForkNode fork = testdata.getFork();
		ReadStructuralFeatureAction readisenabled = testdata.getReadisenabled();
		DecisionNode decision = testdata.getDecision();
		ExpansionRegion expansionregion = testdata.getExpansionregion();
		ValueSpecificationAction specify1 = testdata.getSpecify1();		
		CallBehaviorAction calllistget = testdata.getCalllistget();		
		Parameter activityparameter = testdata.getActivityparameter();
		CallOperationAction callisenabled = testdata.getCallisenabled();
		ReadSelfAction readselftransition = testdata.getReadselftransition();		
		
		// Objects
		Object_ obj_net = testdata.getObj_net();
		Object_ obj_transition1 = testdata.getObj_transition1();		
		
		// Start execution
		ExecutionContext.getInstance().execute(activity, obj_net, null);
		
		// Check execution order
		ActivityNodeList executionorder = new ActivityNodeList();
		executionorder.add(readself);
		executionorder.add(readtransitions);
		executionorder.add(expansionregion);		
		
		ActivityNodeList executionorder_expansion = new ActivityNodeList();
		executionorder_expansion.add(fork);
		executionorder_expansion.add(callisenabled);
		executionorder_expansion.add(readselftransition);
		executionorder_expansion.add(readisenabled);
		executionorder_expansion.add(decision);
		
		executionorder.addAll(executionorder_expansion);
		executionorder.addAll(executionorder_expansion);
		executionorder.addAll(executionorder_expansion);
		
		executionorder.add(specify1);
		executionorder.add(calllistget);		
		
		System.err.println(eventlist.toString().replaceAll(",", "\n"));
		
		assertTrue(checkExecutionOrder(executionorder));
		
		// Check output of activity
		int activityexecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ParameterValueList outputs = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, outputs.size());
		ParameterValue output = outputs.get(0);
		assertEquals(activityparameter, output.parameter);
		ValueList outputvalues = output.values;
		assertEquals(1, outputvalues.size());
		Value outputvalue = outputvalues.get(0);
		
		assertTrue(outputvalue instanceof Reference);
		Object_ outputobj = ((Reference)outputvalue).referent;
		assertEquals(obj_transition1, outputobj);			
	}
	
	@Test
	public void testExpansionRegionWithLoop() {
		ExpansionRegionTestData testdata = new ExpansionRegionTestData();
		testdata.initializeWithLoop();
		
		// Activity
		Activity activity = testdata.getActivity();
		ReadSelfAction readself = testdata.getReadself();
		ReadStructuralFeatureAction readtransitions = testdata.getReadtransitions();
		ForkNode fork = testdata.getFork();
		ReadStructuralFeatureAction readisenabled = testdata.getReadisenabled();
		DecisionNode decision = testdata.getDecision();
		ExpansionRegion expansionregion = testdata.getExpansionregion();
		ValueSpecificationAction specify1 = testdata.getSpecify1();		
		CallBehaviorAction calllistget = testdata.getCalllistget();		
		Parameter activityparameter = testdata.getActivityparameter();
		InitialNode initial = testdata.getInitial();
		MergeNode merge = testdata.getMerge();
		ForkNode fork2 = testdata.getFork2();
		DestroyObjectAction destroytransition = testdata.getDestroytransition();

		// Objects
		Object_ obj_net = testdata.getObj_net();
		Object_ obj_transition1 = testdata.getObj_transition1();		
		Object_ obj_transition3 = testdata.getObj_transition3();
		
		// Start execution
		ExecutionContext.getInstance().execute(activity, obj_net, null);
		
		// Check execution order		
		ActivityNodeList executionorderloop = new ActivityNodeList();
		executionorderloop.add(merge);
		executionorderloop.add(readself);
		executionorderloop.add(readtransitions);
		executionorderloop.add(expansionregion);		
		
		ActivityNodeList executionorder_expansion = new ActivityNodeList();
		executionorder_expansion.add(fork);
		executionorder_expansion.add(readisenabled);
		executionorder_expansion.add(decision);
		
		executionorderloop.addAll(executionorder_expansion);
		executionorderloop.addAll(executionorder_expansion);
		executionorderloop.addAll(executionorder_expansion);
		
		executionorderloop.add(specify1);
		executionorderloop.add(calllistget);		
		executionorderloop.add(fork2);
		executionorderloop.add(destroytransition);
		
		ActivityNodeList executionorder = new ActivityNodeList();
		executionorder.add(initial);
		executionorder.addAll(executionorderloop);
		executionorderloop.remove(fork);
		executionorderloop.remove(readisenabled);
		executionorderloop.remove(decision);
		executionorder.addAll(executionorderloop);
		executionorder.add(merge);
		executionorder.add(readself);
		executionorder.add(readtransitions);
		executionorder.add(expansionregion);
		executionorder.add(fork);
		executionorder.add(readisenabled);
		executionorder.add(decision);
		executionorder.add(specify1);
		
		System.err.println(eventlist.toString().replaceAll(",", "\n"));
		
		assertTrue(checkExecutionOrder(executionorder));
		
		// Check output of activity
		int activityexecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ParameterValueList outputs = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, outputs.size());
		ParameterValue output = outputs.get(0);
		assertEquals(activityparameter, output.parameter);
		ValueList outputvalues = output.values;
		assertEquals(2, outputvalues.size());
		
		Value outputvalue = outputvalues.get(0);		
		assertTrue(outputvalue instanceof Reference);
		Object_ outputobj = ((Reference)outputvalue).referent;
		assertEquals(obj_transition1, outputobj);			
		
		Value outputvalue2 = outputvalues.get(1);		
		assertTrue(outputvalue2 instanceof Reference);
		Object_ outputobj2 = ((Reference)outputvalue2).referent;
		assertEquals(obj_transition3, outputobj2);
	}
	
	/**
	 * Tests the case where an expansion region only contains one 
	 * activity node which is an call operation action and the 
	 * expansion node is the last activity node of the containing activity
	 * 
	 * Class cl
	 * 	prop : Integer
	 * 
	 * Input objects
	 * o1 : cl
	 * 	prop = 0
	 * o2 : cl
	 * 	prop = 0
	 * 
	 * Activity 1:
	 * input parameter accepting the two objects
	 * output parameter providing the two objects
	 * fork
	 * expansionregion with 1 inputelement, 1 outputelement, calloperationaction to activity2

	 * inputparameter -> fork
	 * fork -> expansionregion.inputelement
	 * fork -> outputparameter
	 * expansionregion.inputelement -> calloperationaction.target
	 * 
	 * Activity 2:
	 * read self
	 * fork
	 * read prop
	 * specify value 1
	 * call behavior "add"
	 * set prop
	 * 
	 * read self.result -> fork
	 * fork -> set tokens.object
	 * fork -> read prop.object
	 * read prop.result -> add.input[0]
	 * specify value 1.result -> add.input[1]
	 * add.output[0] -> set prop.value
	 * 
	 * The expected output are the two objects with prop = 1 (instead of 0)
	 * 
	 */
	@Test
	public void testExpansionRegionAsLastNodeWithCallOperationAction() {
		ExpansionRegionTestData2 testdata = new ExpansionRegionTestData2();		
		
		Activity activity = ActivityFactory.createActivity("activity");
		Parameter parameter = ActivityFactory.createParameter(activity, "param", ParameterDirectionKind.in);
		ActivityParameterNode parameternode = ActivityFactory.createActivityParameterNode(activity, "activityparam", parameter);
		Parameter parameterout = ActivityFactory.createParameter(activity, "paramout", ParameterDirectionKind.out);
		ActivityParameterNode parameternodeout = ActivityFactory.createActivityParameterNode(activity, "activityparamout", parameterout);
		ForkNode fork2 = ActivityFactory.createForkNode(activity, "fork2");
		ExpansionRegion expansionregion = testdata.createExpansionRegion(activity);
		
		ActivityFactory.createObjectFlow(activity, parameternode, fork2);
		ActivityFactory.createObjectFlow(activity, fork2, expansionregion.inputElement.get(0));
		ActivityFactory.createObjectFlow(activity, fork2, parameternodeout);
				
		ParameterValueList input = testdata.createInputObjects(parameter);
		
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID(); 
		
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(2, output.get(0).values.size());
		
		Object_ o1 = ((Reference)output.get(0).values.get(0)).referent;		
		IntegerValue propvalue1 = (IntegerValue)o1.featureValues.get(0).values.get(0);
		
		Object_ o2 = ((Reference)output.get(0).values.get(1)).referent;
		IntegerValue propvalue2 = (IntegerValue)o2.featureValues.get(0).values.get(0);
		
		assertEquals(1, propvalue1.value);
		assertEquals(1, propvalue2.value);
	}
	
	/**
	 * This test is basically structured as {@link testExpansionRegionAsLastNodeWithCallOperationAction}
	 * but includes two expansion regions with the same content
	 */
	@Test
	public void testExpansionRegionAsLastNodeWithCallOperationAction2() {
		ExpansionRegionTestData2 testdata = new ExpansionRegionTestData2();		
		
		Activity activity = ActivityFactory.createActivity("activity");
		Parameter parameter = ActivityFactory.createParameter(activity, "param", ParameterDirectionKind.in);
		ActivityParameterNode parameternode = ActivityFactory.createActivityParameterNode(activity, "activityparam", parameter);
		Parameter parameterout = ActivityFactory.createParameter(activity, "paramout", ParameterDirectionKind.out);
		ActivityParameterNode parameternodeout = ActivityFactory.createActivityParameterNode(activity, "activityparamout", parameterout);
		ForkNode fork2 = ActivityFactory.createForkNode(activity, "fork2");
		ExpansionRegion expansionregion1 = testdata.createExpansionRegion(activity);
		ExpansionRegion expansionregion2 = testdata.createExpansionRegion(activity);
		
		ActivityFactory.createObjectFlow(activity, parameternode, fork2);
		ActivityFactory.createObjectFlow(activity, fork2, expansionregion1.inputElement.get(0));
		ActivityFactory.createObjectFlow(activity, fork2, expansionregion2.inputElement.get(0));
		ActivityFactory.createObjectFlow(activity, fork2, parameternodeout);
				
		ParameterValueList input = testdata.createInputObjects(parameter);
		
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID(); 
		
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(2, output.get(0).values.size());
		
		Object_ o1 = ((Reference)output.get(0).values.get(0)).referent;		
		IntegerValue propvalue1 = (IntegerValue)o1.featureValues.get(0).values.get(0);
		
		Object_ o2 = ((Reference)output.get(0).values.get(1)).referent;
		IntegerValue propvalue2 = (IntegerValue)o2.featureValues.get(0).values.get(0);
		
		assertEquals(2, propvalue1.value);
		assertEquals(2, propvalue2.value);
	}
	
	@Test
	public void testListIndexOfFeature() {
		Activity activity = ActivityFactory.createActivity("testListIndexOfFeature");
		Parameter paramlist = ActivityFactory.createParameter(activity, "list", ParameterDirectionKind.in);
		ActivityParameterNode paramnodelist = ActivityFactory.createActivityParameterNode(activity, "list", paramlist);
		Parameter paramobject = ActivityFactory.createParameter(activity, "object", ParameterDirectionKind.in);
		ActivityParameterNode paramnodeobject = ActivityFactory.createActivityParameterNode(activity, "object", paramobject);
		Parameter paramresult = ActivityFactory.createParameter(activity, "result", ParameterDirectionKind.out);
		ActivityParameterNode paramnoderesult = ActivityFactory.createActivityParameterNode(activity, "result", paramresult);		
		CallBehaviorAction call = ActivityFactory.createCallBehaviorAction(activity, "call indexof", ExecutionContext.getInstance().getOpaqueBehavior("listindexof"), 1, 2);
		ActivityFactory.createObjectFlow(activity, paramnodelist, call.input.get(0));
		ActivityFactory.createObjectFlow(activity, paramnodeobject, call.input.get(1));
		ActivityFactory.createObjectFlow(activity, call.output.get(0), paramnoderesult);
		
		// create list input
		ParameterValue list = new ParameterValue();
		list.parameter = paramlist;
		ValueList valuelist = new ValueList();		
		for(int i=0;i<3;++i) {			
			IntegerValue value = new IntegerValue();
			value.value = i;
			ValueList values = new ValueList();
			values.add(value);
			valuelist.add(value);
		}				
		list.values = valuelist;
		
		// create object input
		ParameterValue object = new ParameterValue();
		object.parameter = paramobject;
		ValueList valuelist2 = new ValueList();
		valuelist2.add(valuelist.get(2));
		object.values = valuelist2;
		
		ParameterValueList input = new ParameterValueList();
		input.add(list);
		input.add(object);
		
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID(); 
		
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(1, output.get(0).values.size());		
		assertEquals(3, ((IntegerValue)output.get(0).values.get(0)).value);		
	}
	
	@Test
	public void testTestIdentityAction() {
		Activity activity = ActivityFactory.createActivity("testTestIdentityAction");
		TestIdentityAction equals = ActivityFactory.createTestIdentityAction(activity, "equals");
		Parameter param1 = ActivityFactory.createParameter("param1", ParameterDirectionKind.in);
		ActivityParameterNode paramnode1 = ActivityFactory.createActivityParameterNode(activity, "param1", param1); 
		Parameter param2 = ActivityFactory.createParameter("param2", ParameterDirectionKind.in);
		ActivityParameterNode paramnode2 = ActivityFactory.createActivityParameterNode(activity, "param2", param2);
		Parameter param3 = ActivityFactory.createParameter("param3", ParameterDirectionKind.out);
		ActivityParameterNode paramnode3 = ActivityFactory.createActivityParameterNode(activity, "param3", param3);
		ActivityFactory.createObjectFlow(activity, paramnode1, equals.first);
		ActivityFactory.createObjectFlow(activity, paramnode2, equals.second);
		ActivityFactory.createObjectFlow(activity, equals.result, paramnode3);
		
		// create list input
		ParameterValue par1 = new ParameterValue();
		par1.parameter = param1;
		ValueList valuelist1 = new ValueList();
		IntegerValue value1 = new IntegerValue();
		value1.value = 1;
		ValueList values1 = new ValueList();
		values1.add(value1);
		valuelist1.add(value1);		
		par1.values = valuelist1;
		
		// create object
		ParameterValue par2 = new ParameterValue();
		par2.parameter = param2;
		ValueList valuelist2 = new ValueList();
		IntegerValue value2 = new IntegerValue();
		value2.value = 1;
		ValueList values2 = new ValueList();
		values2.add(value2);
		valuelist2.add(value2);		
		par2.values = valuelist2;

		ParameterValueList input = new ParameterValueList();
		input.add(par1);
		input.add(par2);

		// execute
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent) eventlist.get(0))
				.getActivityExecutionID();

		// check output
		ParameterValueList output = ExecutionContext.getInstance()
				.getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(1, output.get(0).values.size());
		assertEquals(true, ((BooleanValue) output.get(0).values.get(0)).value);
		
		// check events
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(equals, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
				
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(equals, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
				
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());	
	}
	
	@Test
	public void testReadIsKindOfAction() {
		Class_ c1 = ActivityFactory.createClass("Class1");
		Class_ c2 = ActivityFactory.createClass("Class2");
		Activity activity = ActivityFactory.createActivity("testReadIsKindOfAction");
		Parameter param1 = ActivityFactory.createParameter("param1", ParameterDirectionKind.in);
		ActivityParameterNode paramnode1 = ActivityFactory.createActivityParameterNode(activity, "paramnode1", param1);
		Parameter param2 = ActivityFactory.createParameter("param2", ParameterDirectionKind.out);
		ActivityParameterNode paramnode2 = ActivityFactory.createActivityParameterNode(activity, "paramnode2", param2);
		ReadIsClassifiedObjectAction typeof1 = ActivityFactory.createReadIsClassifiedObjectAction(activity, "typeof1", c1);
		ReadIsClassifiedObjectAction typeof2 = ActivityFactory.createReadIsClassifiedObjectAction(activity, "typeof2", c2);
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		
		ActivityFactory.createObjectFlow(activity, paramnode1, fork);
		ActivityFactory.createObjectFlow(activity, fork, typeof1.object);
		ActivityFactory.createObjectFlow(activity, fork, typeof2.object);
		ActivityFactory.createObjectFlow(activity, typeof1.result, paramnode2);
		ActivityFactory.createObjectFlow(activity, typeof2.result, paramnode2);
		ActivityFactory.createControlFlow(activity, typeof1, typeof2);
		
		// create one object of Class1
		ParameterValueList input = new ParameterValueList();
		ParameterValue inputvalue = new ParameterValue();
		inputvalue.parameter = param1;			
		ValueList valuelist = new ValueList();		
		Object_ object = new Object_();
		object.types.add(c1);
		object.createFeatureValues();
		ExecutionContext.getInstance().getLocus().add(object);
		Reference reference = new Reference();
		reference.referent = object;
		valuelist.add(reference);
		inputvalue.values = valuelist; 				
		input.add(inputvalue);
			
		// execute
		ExecutionContext.getInstance().execute(activity, null, input);
		int activityexecutionID = ((ActivityEntryEvent) eventlist.get(0))
				.getActivityExecutionID();

		// check output
		ParameterValueList output = ExecutionContext.getInstance()
				.getActivityOutput(activityexecutionID);
		assertEquals(1, output.size());
		assertEquals(2, output.get(0).values.size());
		assertEquals(true, ((BooleanValue) output.get(0).values.get(0)).value);
		assertEquals(false, ((BooleanValue) output.get(0).values.get(1)).value);
		
		// check events
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(fork, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
				
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(typeof1, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(typeof1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(typeof2, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(typeof2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());	
	}
	
	private class ExpansionRegionTestData2{
		Operation op;
		Class_ cl;
		Property prop;
		
		public ExpansionRegionTestData2() {
			cl = ActivityFactory.createClass("cl");
			prop = ActivityFactory.createProperty("prop", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), cl);
			op = ActivityFactory.createOperation("op", null, createAdd1Activity(), cl);
		}
		
		public ExpansionRegion createExpansionRegion(Activity activity) {
			CallOperationAction calloperation = ActivityFactory.createCallOperationAction(activity, "calloperation", op);
			List<ActivityNode> expansionnodes = new ArrayList<ActivityNode>();
			expansionnodes.add(calloperation);
			ExpansionRegion expansionregion = ActivityFactory.createExpansionRegion(activity, "expansion", ExpansionKind.iterative, expansionnodes, 1, 0);
			ActivityFactory.createObjectFlow(expansionregion, expansionregion.inputElement.get(0), calloperation.target);
			return expansionregion;
		}
	
		public ParameterValueList createInputObjects(Parameter parameter) {
			ParameterValueList input = new ParameterValueList();
			
			ParameterValue inputvalue = new ParameterValue();
			inputvalue.parameter = parameter;
			ValueList valuelist = new ValueList();		
			for(int i=0;i<2;++i) {
				Object_ object = new Object_();
				object.types.add(cl);
				object.createFeatureValues();
				IntegerValue value = new IntegerValue();
				value.value = 0;
				ValueList values = new ValueList();
				values.add(value);
				object.setFeatureValue(prop, values, 0);
				ExecutionContext.getInstance().getLocus().add(object);
				Reference reference = new Reference();
				reference.referent = object;
				valuelist.add(reference);
			}
					
			inputvalue.values = valuelist; 
			input.add(inputvalue);
			return input;
		}
	
		private Activity createAdd1Activity() {
			Activity activity2 = ActivityFactory.createActivity("activity2");
			ReadSelfAction readself = ActivityFactory.createReadSelfAction(activity2, "readself");
			ForkNode fork = ActivityFactory.createForkNode(activity2, "fork");
			ValueSpecificationAction vspec = ActivityFactory.createValueSpecificationAction(activity2, "specify 1", 1);
			ReadStructuralFeatureAction readprop = ActivityFactory.createReadStructuralFeatureAction(activity2, "read prop", prop);
			CallBehaviorAction add = ActivityFactory.createCallBehaviorAction(activity2, "call add", ExecutionContext.getInstance().getOpaqueBehavior("add"), 1, 2);
			AddStructuralFeatureValueAction setprop = ActivityFactory.createAddStructuralFeatureValueAction(activity2, "set prop", prop, true);
			ActivityFactory.createObjectFlow(activity2, readself.result, fork);
			ActivityFactory.createObjectFlow(activity2, fork, setprop.object);
			ActivityFactory.createObjectFlow(activity2, fork, readprop.object);
			ActivityFactory.createObjectFlow(activity2, readprop.result, add.input.get(0));
			ActivityFactory.createObjectFlow(activity2, vspec.result, add.input.get(1));
			ActivityFactory.createObjectFlow(activity2, add.output.get(0), setprop.value);
			return activity2;
		}
	}
	
	private boolean checkExecutionOrder(ActivityNodeList nodeorder) {
		boolean isValid = true;
		
		Iterator<ActivityNode> nodes = nodeorder.iterator();
		ActivityNode node = nodes.next();
		
		for(Event e : eventlist) {
			if(e instanceof ActivityNodeEntryEvent) {
				ActivityNodeEntryEvent nodeentry = (ActivityNodeEntryEvent)e;
				if(!nodeentry.getNode().equals(node)) {
					isValid = false;
					break;
				}
				if(nodes.hasNext()) {
					node = nodes.next();
				} else {
					break;
				}
			}
		}
		
		if(isValid) {
			if(nodeorder.lastIndexOf(node) != nodeorder.size()-1) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	@Override
	public void notify(Event event) {
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
			System.err.println(event);
		}
		
		if(event instanceof SuspendEvent || event instanceof ActivityExitEvent) {
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
	
	private class ExpansionRegionTestData {
		private Class_ cl_net;
		private Class_ cl_transition;
		private Property prop_transitioname;
		private Property prop_isEnabled;
		private Property prop_transitions;
		private Property prop_net;
		private Association assoc;

		// Activity
		private Activity activity;
		private ReadSelfAction readself;
		private ReadStructuralFeatureAction readtransitions;
		private ForkNode fork;
		private ReadStructuralFeatureAction readisenabled;
		private DecisionNode decision;
		private ExpansionRegion expansionregion;
		private ValueSpecificationAction specify1;		
		private Behavior listgetbehavior;
		private CallBehaviorAction calllistget;		
		private Parameter activityparameter;
		private ActivityParameterNode activityparameternode;
		private CallOperationAction callisenabled;
		private ReadSelfAction readselftransition;
		private InitialNode initial;
		private MergeNode merge;
		private ForkNode fork2;
		private DestroyObjectAction destroytransition;
		
		// Objects
		private Object_ obj_net;
		private Object_ obj_transition1;
		private Object_ obj_transition2;
		private Object_ obj_transition3;	
		private Link link1;
		private Link link2;
		private Link link3;

		public void initialize() {
			initializeClassPart();	
			activity = ActivityFactory.createActivity("activity testExpansionRegion");
			initializeExpansionRegion();
			initializeActivity();
			initializeObjectPart();
		}
		
		public void initializeWithCallOperationAction() {
			initializeClassPart();	
			activity = ActivityFactory.createActivity("activity testExpansionRegion");
			initializeExpansionRegionWithCallOperationAction();
			initializeActivity();
			initializeObjectPart();
		}
		
		public void initializeWithLoop() {
			initializeClassPart();	
			activity = ActivityFactory.createActivity("activity testExpansionRegion");
			initializeExpansionRegion();
			initializeActivityWithLoop();
			initializeObjectPart();
		}
		
		private void initializeActivityWithLoop() {
			initializeActivity();
			initial = ActivityFactory.createInitialNode(activity, "initial");
			merge = ActivityFactory.createMergeNode(activity, "merge");
			fork2 = ActivityFactory.createForkNode(activity, "fork2");
			destroytransition = ActivityFactory.createDestroyObjectAction(activity, "destroy transition", true, true);
			ActivityFactory.createControlFlow(activity, initial, merge);
			ActivityFactory.createControlFlow(activity, merge, readself);			
			
			ActivityEdge calllistget2param = calllistget.result.get(0).outgoing.remove(0); 
			activityparameternode.incoming.remove(0);
			activity.edge.remove(calllistget2param);
			
			ActivityFactory.createObjectFlow(activity, calllistget.result.get(0), fork2);
			ActivityFactory.createObjectFlow(activity, fork2, activityparameternode);
			ActivityFactory.createObjectFlow(activity, fork2, destroytransition.target);
			ActivityFactory.createControlFlow(activity, destroytransition, merge);
		}
		
		private void initializeActivity() {			
			readself = ActivityFactory.createReadSelfAction(activity, "read self");
			readtransitions = ActivityFactory.createReadStructuralFeatureAction(activity, "read transitions", prop_transitions);
			specify1 = ActivityFactory.createValueSpecificationAction(activity, "specify 1", 1);		
			listgetbehavior = initializeListGetBehavior();
			calllistget = ActivityFactory.createCallBehaviorAction(activity, "call list get", listgetbehavior, 1, 2);		
			activityparameter = ActivityFactory.createParameter("parameter enabled transitions", ParameterDirectionKind.out);
			activityparameternode = ActivityFactory.createActivityParameterNode(activity, "enabled transitions", activityparameter);			
			ActivityFactory.createObjectFlow(activity, readself.result, readtransitions.object);
			ActivityFactory.createObjectFlow(activity, readtransitions.result, expansionregion.inputElement.get(0));
			ActivityFactory.createObjectFlow(activity, expansionregion.outputElement.get(0), calllistget.argument.get(0));
			ActivityFactory.createControlFlow(activity, expansionregion, specify1);
			ActivityFactory.createObjectFlow(activity, specify1.result, calllistget.argument.get(1));
			ActivityFactory.createObjectFlow(activity, calllistget.result.get(0), activityparameternode);
		}

		private void initializeExpansionRegion() {
			fork = ActivityFactory.createForkNode(activity, "fork");
			readisenabled = ActivityFactory.createReadStructuralFeatureAction(activity, "read isEnabled", prop_isEnabled);
			decision = ActivityFactory.createDecisionNode(activity, "decision");
			List<ActivityNode> expansionnodes = new ArrayList<ActivityNode>();
			expansionnodes.add(fork);
			expansionnodes.add(readisenabled);
			expansionnodes.add(decision);		
			expansionregion = ActivityFactory.createExpansionRegion(activity, "expansion region", ExpansionKind.parallel, expansionnodes, 1, 1);
			ActivityFactory.createObjectFlow(expansionregion, expansionregion.inputElement.get(0), fork);
			ActivityFactory.createObjectFlow(expansionregion, fork, readisenabled.object);
			ActivityFactory.createObjectFlow(expansionregion, fork, decision);
			ActivityFactory.createDecisionInputFlow(expansionregion, readisenabled.result, decision);
			ActivityFactory.createObjectFlow(expansionregion, decision, expansionregion.outputElement.get(0), true);
		}
		
		private void initializeExpansionRegionWithCallOperationAction() {
			fork = ActivityFactory.createForkNode(activity, "fork");
			
			Activity activityisenabled = ActivityFactory.createActivity("Transition::isEnabled()");
			readselftransition = ActivityFactory.createReadSelfAction(activityisenabled, "read self transition");
			readisenabled = ActivityFactory.createReadStructuralFeatureAction(activityisenabled, "read is enabeld", prop_isEnabled);
			
			Parameter param_isEnabled = ActivityFactory.createParameter("isEnabled", ParameterDirectionKind.out);
			ParameterList params_isEnabled = new ParameterList();
			params_isEnabled.add(param_isEnabled);
			Operation op_isenabled = ActivityFactory.createOperation("isEnabled", params_isEnabled, activityisenabled, cl_transition);
			ActivityParameterNode paramnode_isEnabled = ActivityFactory.createActivityParameterNode(activityisenabled, "is enabled result", param_isEnabled);
			ActivityFactory.createObjectFlow(activityisenabled, readselftransition.result, readisenabled.object);
			ActivityFactory.createObjectFlow(activityisenabled, readisenabled.result, paramnode_isEnabled);
			callisenabled = ActivityFactory.createCallOperationAction(activity, "callIsEnabled", op_isenabled);			
			decision = ActivityFactory.createDecisionNode(activity, "decision");
			
			List<ActivityNode> expansionnodes = new ArrayList<ActivityNode>();
			expansionnodes.add(fork);			
			expansionnodes.add(callisenabled);			
			expansionnodes.add(decision);		
			expansionregion = ActivityFactory.createExpansionRegion(activity, "expansion region", ExpansionKind.parallel, expansionnodes, 1, 1);
			ActivityFactory.createObjectFlow(expansionregion, expansionregion.inputElement.get(0), fork);
			ActivityFactory.createObjectFlow(expansionregion, fork, callisenabled.target);
			ActivityFactory.createDecisionInputFlow(expansionregion, callisenabled.output.get(0), decision);
			ActivityFactory.createObjectFlow(expansionregion, fork, decision);
			ActivityFactory.createObjectFlow(expansionregion, decision, expansionregion.outputElement.get(0), true);
		}

		private void initializeObjectPart() {
			obj_net = createObject(cl_net);
			obj_transition1 = createObject(cl_transition);
			setFeatureValue(obj_transition1, prop_transitioname, "transition1");
			setFeatureValue(obj_transition1, prop_isEnabled, true);
			obj_transition2 = createObject(cl_transition);
			setFeatureValue(obj_transition2, prop_transitioname, "transition2");
			setFeatureValue(obj_transition2, prop_isEnabled, false);
			obj_transition3 = createObject(cl_transition);	
			setFeatureValue(obj_transition3, prop_transitioname, "transition3");
			setFeatureValue(obj_transition3, prop_isEnabled, true);		
			link1 = createLink(assoc);
			setFeatureValue(link1, prop_net, obj_net);
			setFeatureValue(link1, prop_transitions, obj_transition1);
			link2 = createLink(assoc);
			setFeatureValue(link2, prop_net, obj_net);
			setFeatureValue(link2, prop_transitions, obj_transition2);
			link3 = createLink(assoc);
			setFeatureValue(link3, prop_net, obj_net);
			setFeatureValue(link3, prop_transitions, obj_transition3);
	
			ExecutionContext.getInstance().getLocus().add(obj_net);
			ExecutionContext.getInstance().getLocus().add(obj_transition1);
			ExecutionContext.getInstance().getLocus().add(obj_transition2);
			ExecutionContext.getInstance().getLocus().add(obj_transition3);
			ExecutionContext.getInstance().getLocus().add(link1);
			ExecutionContext.getInstance().getLocus().add(link2);
			ExecutionContext.getInstance().getLocus().add(link3);
		}

		private void initializeClassPart() {
			// Classes
			cl_net = ActivityFactory.createClass("Net");
			cl_transition = ActivityFactory.createClass("Transition");
			prop_transitioname = ActivityFactory.createProperty("transition name", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), cl_transition);
			prop_isEnabled = ActivityFactory.createProperty("isEnabled", 1, 1, ExecutionContext.getInstance().getPrimitiveBooleanType(), cl_transition);
			prop_transitions = ActivityFactory.createProperty("transitions", 0, -1, cl_transition, cl_net);
			prop_net = ActivityFactory.createProperty("net", 1, 1, cl_net, cl_transition);
			PropertyList assocends = new PropertyList();
			assocends.add(prop_transitions);
			assocends.add(prop_net);
			assoc = ActivityFactory.createAssociation("transitions", assocends);
		}
		
		private Object_ createObject(Class_ type) {
			Object_ obj = new Object_();
			obj.createFeatureValues();
			obj.types.add(type);
			return obj;
		}
		
		private void setFeatureValue(CompoundValue compound, Property property, String value) {
			ValueList valuelist = new ValueList();
			StringValue stringvalue = new StringValue();
			stringvalue.value = value;
			valuelist.add(stringvalue);
			compound.setFeatureValue(property, valuelist, 0);
		}
		
		private Link createLink(Association association) {
			Link link = new Link();
			link.type = association;
			return link;
		}
		
		private void setFeatureValue(CompoundValue compound, Property property, boolean value) {
			ValueList valuelist = new ValueList();
			BooleanValue booleanvalue = new BooleanValue();
			booleanvalue.value = value;
			valuelist.add(booleanvalue);
			compound.setFeatureValue(property, valuelist, 0);
		}
		
		private void setFeatureValue(CompoundValue compound, Property property, Object_ value) {
			Reference reference = new Reference();
			reference.referent = value;
			ValueList valuelist = new ValueList();
			valuelist.add(reference);
			compound.setFeatureValue(property, valuelist, 0);
		}
		
		private Behavior initializeListGetBehavior() {
			OpaqueBehavior listgetbehavior = new OpaqueBehavior();
			
			Parameter output = new Parameter();
			output.setDirection(ParameterDirectionKind.out);
			output.setName("result");
			listgetbehavior.ownedParameter.add(output);
			
			Parameter inputlist = new Parameter();
			inputlist.setDirection(ParameterDirectionKind.in);
			inputlist.setName("list");
			listgetbehavior.ownedParameter.add(inputlist);
			
			Parameter inputindex = new Parameter();
			inputindex.setDirection(ParameterDirectionKind.in);
			inputindex.setName("index");
			listgetbehavior.ownedParameter.add(inputindex);
			
			ListGetFunctionBehaviorExecution listgetexecution = new ListGetFunctionBehaviorExecution();
			listgetexecution.types.add(listgetbehavior);
			
			ExecutionContext.getInstance().addOpaqueBehavior(listgetexecution);
			
			return listgetbehavior;
		}		

		public Activity getActivity() {
			return activity;
		}

		public ReadSelfAction getReadself() {
			return readself;
		}

		public ReadStructuralFeatureAction getReadtransitions() {
			return readtransitions;
		}

		public ForkNode getFork() {
			return fork;
		}

		public ReadStructuralFeatureAction getReadisenabled() {
			return readisenabled;
		}

		public DecisionNode getDecision() {
			return decision;
		}

		public ExpansionRegion getExpansionregion() {
			return expansionregion;
		}

		public ValueSpecificationAction getSpecify1() {
			return specify1;
		}

		public CallBehaviorAction getCalllistget() {
			return calllistget;
		}

		public Parameter getActivityparameter() {
			return activityparameter;
		}

		public Object_ getObj_net() {
			return obj_net;
		}

		public Object_ getObj_transition1() {
			return obj_transition1;
		}

		public CallOperationAction getCallisenabled() {
			return callisenabled;
		}

		public ReadSelfAction getReadselftransition() {
			return readselftransition;
		}

		public InitialNode getInitial() {
			return initial;
		}

		public MergeNode getMerge() {
			return merge;
		}

		public ForkNode getFork2() {
			return fork2;
		}

		public DestroyObjectAction getDestroytransition() {
			return destroytransition;
		}

		public Object_ getObj_transition3() {
			return obj_transition3;
		}	
		
	}
	
}
