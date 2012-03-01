/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebugjunittest;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * @author Tanja Mayerhofer
 *
 */
public class EventTest  implements ExecutionEventListener{
	
	private List<Event> eventlist = new ArrayList<Event>();
	
	public EventTest() {
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}
	
	@BeforeClass
	public static void setUpBeforeClass () throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		eventlist = new ArrayList<Event>();
		ExecutionContext.getInstance().getLocus().extensionalValues = new ExtensionalValueList();							
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActivityExecution() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityExecution");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());
		
	}
	
	@Test
	public void testSingleCreateObjectAction() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleCreateObjectAction");
		Class_ class_person = ActivityFactory.createClass("Person");
		CreateObjectAction action = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person", class_person);
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(action, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(action, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(action, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testSingleActivityFinalNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleActivityFinalNode");
		ActivityFinalNode finalnode = ActivityFactory.createActivityFinalNode(activity, "ActivityFinalNode");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(finalnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(finalnode, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(finalnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testSingleInitialNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleInitialNode");
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitialNode");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(initialnode, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testSingleForkNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleForkNode");
		ForkNode forknode = ActivityFactory.createForkNode(activity, "ForkNode");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(forknode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(forknode, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(forknode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testSingleMergeNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleMergeNode");
		MergeNode mergenode = ActivityFactory.createMergeNode(activity, "MergeNode");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(mergenode, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testSingleDecisionNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleMergeNode");
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(decisionnode, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(4)).getActivity());		
	}
	
	@Test
	public void testDecisionNodeWithDecisionInputFlowOneGuardTrue() {
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionInputFlow");
		
		ValueSpecificationAction vsaction = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		ActivityFactory.createDecisionInputFlow(activity, vsaction.result, decisionnode);
		ActivityFactory.createControlFlow(activity, vsaction, decisionnode);
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [1]");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [2]");
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, 1);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, 2);
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(vsaction, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(decisionnode, ((StepEvent)eventlist.get(5)).getLocation());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(mergenode1, ((StepEvent)eventlist.get(8)).getLocation());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());		
	}
	
	@Test
	public void testDecisionNodeWithDecisionInputFlowOneGuardTrueWithoutControlFlowToDecisionNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionInputFlowOneGuardTrueWithoutControlFlowToDecisionNode");
		
		ValueSpecificationAction vsaction = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		ActivityFactory.createDecisionInputFlow(activity, vsaction.result, decisionnode);
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [1]");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [2]");
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, 1);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, 2);
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(vsaction, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(decisionnode, ((StepEvent)eventlist.get(5)).getLocation());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());			
		
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());		
	}

	@Test
	public void testDecisionNodeWithDecisionInputFlowTwoGuardsTrue() {
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionInputFlowTwoGuardsTrue");
		
		ValueSpecificationAction vsaction = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		ActivityFactory.createDecisionInputFlow(activity, vsaction.result, decisionnode);
		ActivityFactory.createControlFlow(activity, vsaction, decisionnode);
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [1] 1");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [1] 2");
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, 1);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, 1);
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(vsaction, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(decisionnode, ((StepEvent)eventlist.get(5)).getLocation());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(mergenode1, ((StepEvent)eventlist.get(8)).getLocation());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());		
	}
	
	@Test
	public void testDecisionNodeWithDecisionBehavior() {
		/**
		 * Test if inputvalue 1 < decisioninputvalue 2
		 */
		
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionBehavior");
		
		ValueSpecificationAction vsaction1 = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		ValueSpecificationAction vsaction2 = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 2", 2);
		// TODO insert decision Behavior
		//Behavior decisionbehavior = new 
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		//decision input value
		ActivityFactory.createDecisionInputFlow(activity, vsaction2.result, decisionnode);
		//input value
		ActivityFactory.createObjectFlow(activity, vsaction1.result, decisionnode);
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [true]");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [false]");
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, true);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, false);
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction1, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertEquals(vsaction1, ((StepEvent)eventlist.get(2)).getLocation());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(decisionnode, ((StepEvent)eventlist.get(5)).getLocation());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(mergenode1, ((StepEvent)eventlist.get(8)).getLocation());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());		
	}
	
	public void testLala() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("Name", 0, 1, class_person);
		
		Activity activity = new fUML.Syntax.Activities.IntermediateActivities.Activity();
		activity.setName("activity4");
		
		
		ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitialNode");
		CreateObjectAction createobject_tanja = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person tanja", class_person);
		CreateObjectAction createobject_konrad = ActivityFactory.createCreateObjectAction(activity, "CreateObject Person philip", class_person);
		ValueSpecificationAction valuespec_tanja =  ActivityFactory.createValueSpecificationAction(activity, "ValueSpecification tanja", "tanja");
		ValueSpecificationAction valuespec_konrad =  ActivityFactory.createValueSpecificationAction(activity, "ValueSpecification philip", "philip");		
		AddStructuralFeatureValueAction addstructuralfeaturevalue = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddStructuralFeatureValue Person Name", property_name);				
		
		ActivityFactory.createControlFlow(activity, initialnode, createobject_tanja);
		ActivityFactory.createControlFlow(activity, createobject_tanja, valuespec_tanja);
		ActivityFactory.createControlFlow(activity, valuespec_tanja, createobject_konrad);
		ActivityFactory.createControlFlow(activity, createobject_konrad, valuespec_konrad);
		ActivityFactory.createControlFlow(activity, valuespec_konrad, addstructuralfeaturevalue);

		ActivityFactory.createObjectFlow(activity, createobject_tanja.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_tanja.result, addstructuralfeaturevalue.value);
		ActivityFactory.createObjectFlow(activity, createobject_konrad.result, addstructuralfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, valuespec_konrad.result, addstructuralfeaturevalue.value);	
	}

	@Override
	public void notify(Event event) {		
		eventlist.add(event);
	}
	
}
