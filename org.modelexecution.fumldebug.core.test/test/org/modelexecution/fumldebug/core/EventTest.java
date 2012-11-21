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
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

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
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActivityExecution() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityExecution");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
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
	}
	
	@Test
	public void testSingleActivityFinalNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleActivityFinalNode");
		ActivityFinalNode finalnode = ActivityFactory.createActivityFinalNode(activity, "ActivityFinalNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(finalnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(finalnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	@Test
	public void testSingleInitialNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleInitialNode");
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "InitialNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	@Test
	public void testSingleForkNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleForkNode");
		ForkNode forknode = ActivityFactory.createForkNode(activity, "ForkNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(forknode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(forknode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	@Test
	public void testSingleMergeNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleMergeNode");
		MergeNode mergenode = ActivityFactory.createMergeNode(activity, "MergeNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	@Test
	public void testSingleJoinNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleJoinNode");
		JoinNode joinnode = ActivityFactory.createJoinNode(activity, "JoinNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(joinnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(joinnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	@Test
	public void testSingleDecisionNode() {
		Activity activity = ActivityFactory.createActivity("Activity TestSingleMergeNode");
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());		
	}
	
	/**  
	 * This test case tests the execution of a DecisionNode that
	 * has a decision input flow.
	 * The decision compares 1 (decision input) with the values
	 * provided at the outgoing edges as guards.
	 * Accordingly, MergeNode 1 should be executed.
	 * 
	 * Activity: 
	 * ValueSpecificationAction (value = 1)
	 * DecisionNode (decisionInputFlow = ValueSpecificationAction)
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ObjectFlow:
	 * ValueSpecificationAction.result --> Decision
	 * 
	 * Activity ControlFlow: 
	 * ValueSpecificationAction --> DecisionNode
	 * Decision --> MergeNode1 (guard = 1)
	 * Decision --> MergeNode2 (guard = 1)
	 */
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
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());		
	}
	
	/**  
	 * This test case is a variation of the test case {@link #testDecisionNodeWithDecisionInputFlowOneGuardTrue()}
	 * The only difference is, that the ControlFlow between ValueSpecificationAction and DecisionNode is missing.
	 * 
	 * Activity: 
	 * ValueSpecificationAction (value = 1)
	 * DecisionNode (decisionInputFlow = ValueSpecificationAction)
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ObjectFlow:
	 * ValueSpecificationAction.result --> Decision
	 * 
	 * Activity ControlFlow: 
	 * Decision --> MergeNode1 (guard = 1)
	 * Decision --> MergeNode2 (guard = 2)
	 */
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
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());			
		
		assertTrue(eventlist.get(5) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(5)).getActivity());		
	}
	
	/**  
	 * This test case tests the execution of a DecisionNode that
	 * has a decision input flow.
	 * The decision compares 1 (decision input) with the values
	 * provided at the outgoing edges as guards.
	 * Despite both guards provide the value 1 and therefore evaluate to true, 
	 * only one path should be executed.
	 * 
	 * Activity: 
	 * ValueSpecificationAction (value = 1)
	 * DecisionNode (decisionInputFlow = ValueSpecificationAction)
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ObjectFlow:
	 * ValueSpecificationAction.result --> Decision
	 * 
	 * Activity ControlFlow: 
	 * ValueSpecificationAction --> DecisionNode
	 * Decision --> MergeNode1 (guard = 1)
	 * Decision --> MergeNode2 (guard = 1)
	 */
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
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		/*
		 * The decision node sends an offer to all successor nodes whose guard evaluates to true.
		 * In the case of MergeNodes, the fire() method of all MergeNodes is executed because
		 * MergeNodes do not consume any token.
		 */
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode2, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode2, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());		
	}
	
	/**  
	 * This test case is a modification of the test case {@link #testDecisionNodeWithDecisionInputFlowTwoGuardsTrue()}.
	 * The successor nodes are CreateObjectActions instead of MergeNodes.
	 * 
	 * Activity: 
	 * ValueSpecificationAction (value = 1)
	 * DecisionNode (decisionInputFlow = ValueSpecificationAction)
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ObjectFlow:
	 * ValueSpecificationAction.result --> Decision
	 * 
	 * Activity ControlFlow: 
	 * ValueSpecificationAction --> DecisionNode
	 * Decision --> MergeNode1 (guard = 1)
	 * Decision --> MergeNode2 (guard = 1)
	 */
	@Test
	public void testDecisionNodeWithDecisionInputFlowTwoGuardsTrue2() {
		Class_ cl = ActivityFactory.createClass("Class");
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionInputFlowTwoGuardsTrue");
		
		ValueSpecificationAction vsaction = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		ActivityFactory.createDecisionInputFlow(activity, vsaction.result, decisionnode);
		ActivityFactory.createControlFlow(activity, vsaction, decisionnode);
		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "Create1", cl);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "Create2", cl);
		ActivityFactory.createControlFlow(activity, decisionnode, create1, 1);
		ActivityFactory.createControlFlow(activity, decisionnode, create2, 1);
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		/*
		 * The decision node sends an offer to all successor nodes whose guard evaluates to true
		 * but only one (the first edge that got the offer) can be executed
		 */ 
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());	
		
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());
	}
	
	/**
	 * This is a modification of the test case {@link #testDecisionNodeWithDecisionInputFlowTwoGuardsTrue()}.
	 * The MergeNodes have a CreateObjectAction as successor node each.
	 * Only one of the CreateObjectActions is executed, even if both guards of the DecisionNode evaluate to true.
	 */
	@Test
	public void testDecisionNodeWithDecisionInputFlowTwoGuardsTrue3() {
		Class_ cl = ActivityFactory.createClass("Class");
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionInputFlowTwoGuardsTrue");
		
		ValueSpecificationAction vsaction = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		ActivityFactory.createDecisionInputFlow(activity, vsaction.result, decisionnode);
		ActivityFactory.createControlFlow(activity, vsaction, decisionnode);
		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "Create1", cl);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "Create2", cl);
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [1] 1");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [1] 2");
		
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, 1);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, 1);
		
		ActivityFactory.createControlFlow(activity, mergenode1, create1);
		ActivityFactory.createControlFlow(activity, mergenode2, create2);
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode2, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode2, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
				
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());
	}
	
	/**  
	 * This test case tests the execution of a DecisionNode that
	 * has a decision behavior and a decision input flow.
	 * The decision is: 2 (decision input value) < (decisionbehavior) 1 (input value).
	 * Accordingly MergeNode2 should be executed.
	 * 
	 * Activity: 
	 * ValueSpecificationAction1 (value = 1)
	 * ValueSpecificationAction2 (value = 2)
	 * DecisionNode (decisionInputFlow = ValueSpecificationAction2, decisionbehavior = IntegerGreater)
	 * MergeNode1
	 * MergeNode2
	 * 
	 * Activity ObjectFlow:
	 * ValueSpecificationAction1.result --> Decision
	 * 
	 * Activity ControlFlow: 
	 * Decision --> MergeNode1 (guard = true)
	 * Decision --> MergeNode2 (guard = false)
	 * 
	 * 
	 */
	@Test
	public void testDecisionNodeWithDecisionBehavior() {
		Activity activity = ActivityFactory.createActivity("Activity TestDecisionNodeWithDecisionBehavior");
		
		ValueSpecificationAction vsaction1 = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 1", 1);
		ValueSpecificationAction vsaction2 = ActivityFactory.createValueSpecificationAction(activity, "ValueSpecificationAction 2", 2);		

		DecisionNode decisionnode = ActivityFactory.createDecisionNode(activity, "DecisionNode");
		OpaqueBehavior decisionbehavior = ExecutionContext.getInstance().getOpaqueBehavior("greater");
		decisionnode.setDecisionInput(decisionbehavior);
		//decision input value
		ActivityFactory.createDecisionInputFlow(activity, vsaction2.result, decisionnode);
		//input value
		ActivityFactory.createObjectFlow(activity, vsaction1.result, decisionnode);		
		
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode [true]");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode [false]");
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode1, true);
		ActivityFactory.createControlFlow(activity, decisionnode, mergenode2, false);
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction1, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction1, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(vsaction2, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(vsaction2, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(decisionnode, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(decisionnode, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode2, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode2, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());		
	}	

	/**
	 * A Fork Node that has no incoming edges cannot produce a forked token that is sent to
	 * the successor nodes, i.e., a fork node cannot be a initial enabled node.
	 * Reason: Fork node has to have exactly 1 incoming edge.
	 */
	@Test
	public void testForkNodeAsInitialEnabledNode() {
		Activity activity = ActivityFactory.createActivity("testForkNodeAsInitialEnabledNode");
		ForkNode forknode = ActivityFactory.createForkNode(activity, "ForkNode");
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "MergeNode1");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "MergeNode2");
		ActivityFactory.createControlFlow(activity, forknode, mergenode1);
		ActivityFactory.createControlFlow(activity, forknode, mergenode2);
		
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(4, eventlist.size());		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(forknode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(forknode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());		
		assertTrue(eventlist.get(3) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(3)).getActivity());	
	}
	
	@Override
	public void notify(Event event) {
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
		}
	}
	
}
