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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.TestActivityFactory.ConditionalNodeTestActivity3;
import org.modelexecution.fumldebug.core.TestActivityFactory.DecisionNodeTestActivity1;
import org.modelexecution.fumldebug.core.TestActivityFactory.ExpansionRegionTestActivity1;
import org.modelexecution.fumldebug.core.TestActivityFactory.LinkCreatorDestroyerActivity;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

/**
 * @author Tanja Mayerhofer
 *
 */
public class TraceModelTest extends MolizTest implements ExecutionEventListener {

	private List<Event> eventlist = new ArrayList<Event>();
	
	public TraceModelTest() {
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().addEventListener(this);
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
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().addEventListener(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogicalNodeWithCallBehaviorAction() {
		Class_ c = ActivityFactory.createClass("class");
		
		Activity a = ActivityFactory.createActivity("a");
		
		InitialNode initialNode = ActivityFactory.createInitialNode(a, "init");
		CreateObjectAction createObjectAction = ActivityFactory.createCreateObjectAction(a, "createObject", c);
		ActivityFinalNode finalNode = ActivityFactory.createActivityFinalNode(a, "final");
		
		ActivityFactory.createControlFlow(a, initialNode, createObjectAction);
		ActivityFactory.createControlFlow(a, createObjectAction, finalNode);
		
		Activity main = ActivityFactory.createActivity("main");
		
		InitialNode initialNodeMain = ActivityFactory.createInitialNode(main, "initMain");
		CallBehaviorAction callBehaviorActionMain = ActivityFactory.createCallBehaviorAction(main, "callBeahaviorAction", a);
		ActivityFinalNode finalNodeMain = ActivityFactory.createActivityFinalNode(main, "finalMain");
		
		ActivityFactory.createControlFlow(main, initialNodeMain, callBehaviorActionMain);
		ActivityFactory.createControlFlow(main, callBehaviorActionMain, finalNodeMain);
		
		ExecutionContext.getInstance().execute(main, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		ActivityExecution mainActivityExecution = trace.getActivityExecutionByID(executionID);
		List<ActivityNodeExecution> nodeExecutions = mainActivityExecution.getNodeExecutions();
		
		assertNotNull(nodeExecutions);
		assertEquals(3, nodeExecutions.size());
		
		ActivityNodeExecution initNodeExecution = mainActivityExecution.getNodeExecutionsByNode(initialNodeMain).get(0);
		ActivityNodeExecution callActionExecution = mainActivityExecution.getNodeExecutionsByNode(callBehaviorActionMain).get(0);
		ActivityNodeExecution finalNodeExecution = mainActivityExecution.getNodeExecutionsByNode(finalNodeMain).get(0);
		
		assertTrue(checkLogicalPredecessor(initNodeExecution, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(callActionExecution, initNodeExecution));
		assertTrue(checkLogicalPredecessor(finalNodeExecution, callActionExecution));
		
		assertEquals(1, initNodeExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(initNodeExecution, callActionExecution));
		assertEquals(1, callActionExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(callActionExecution, finalNodeExecution));
		assertEquals(0, finalNodeExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(finalNodeExecution, (ActivityNodeExecution[])null));

		int index = (trace.getActivityExecutions().indexOf(mainActivityExecution) + 1) % 2;
		ActivityExecution calledActivityExecution = trace.getActivityExecutions().get(index);
		
		assertEquals(3, calledActivityExecution.getNodeExecutions().size());
		ActivityNodeExecution initCalleeExecution = calledActivityExecution.getNodeExecutionsByNode(initialNode).get(0);
		ActivityNodeExecution createCalleeExecution = calledActivityExecution.getNodeExecutionsByNode(createObjectAction).get(0);
		ActivityNodeExecution finalCalleeExecution = calledActivityExecution.getNodeExecutionsByNode(finalNode).get(0);
		
		assertEquals(0, initCalleeExecution.getLogicalPredecessor().size());
		assertTrue(checkLogicalPredecessor(initCalleeExecution, (ActivityNodeExecution[])null));
		assertEquals(1, createCalleeExecution.getLogicalPredecessor().size());
		assertTrue(checkLogicalPredecessor(createCalleeExecution, initCalleeExecution));
		assertEquals(1, finalCalleeExecution.getLogicalPredecessor().size());
		assertTrue(checkLogicalPredecessor(finalCalleeExecution, createCalleeExecution));
		
		assertEquals(1, initCalleeExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(initCalleeExecution, createCalleeExecution));
		assertEquals(1, createCalleeExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(createCalleeExecution, finalCalleeExecution));
		assertEquals(0, finalCalleeExecution.getLogicalSuccessor().size());
		assertTrue(checkLogicalSuccessor(finalCalleeExecution, (ActivityNodeExecution[])null));
	}
	
	@Test
	public void testLinkCreatorDestroyer() {
		// TODO
		TestActivityFactory factory = new TestActivityFactory();
		LinkCreatorDestroyerActivity testactivity = factory.new LinkCreatorDestroyerActivity();
		Activity activity = testactivity.activity;
		
		ExecutionContext.getInstance().getLocus().add(testactivity.u);
		ExecutionContext.getInstance().getLocus().add(testactivity.s1);
		ExecutionContext.getInstance().getLocus().add(testactivity.s2);
		ExecutionContext.getInstance().getLocus().add(testactivity.s3);
		ExecutionContext.getInstance().getLocus().add(testactivity.l1);
		ExecutionContext.getInstance().getLocus().add(testactivity.l2);
		ExecutionContext.getInstance().getLocus().add(testactivity.l3);
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		
		Link l4 = null;
		Object_ s4 = null;
		
		int executionID = -1;
		for(Event e : eventlist) {
			if(e instanceof ActivityEntryEvent) {
				executionID = ((ActivityEntryEvent)e).getActivityExecutionID();
				break;
			} else if(e instanceof ExtensionalValueEvent) {
				ExtensionalValueEvent event = (ExtensionalValueEvent)e;
				ExtensionalValue value = event.getExtensionalValue();
				if(event.getType() == ExtensionalValueEventType.CREATION) {
					if(value instanceof Link) {
						l4 = (Link)value;
					} else if(value instanceof Object_) {
						s4 = (Object_)value;
					}
				}
			}
		}
		
		// get trace
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		// check instance values
		assertEquals(9, trace.getValueInstances().size());
		
		ActivityExecution activityExecution = trace.getActivityExecutionByID(executionID);
		ActivityNodeExecution removeExecution = activityExecution.getNodeExecutionsByNode(testactivity.removestudent).get(0);
		ActivityNodeExecution addExecution = activityExecution.getNodeExecutionsByNode(testactivity.addstudent).get(0);
		ActivityNodeExecution createExecution = activityExecution.getNodeExecutionsByNode(testactivity.createstudent).get(0);
		ActivityNodeExecution destroyExecution = activityExecution.getNodeExecutionsByNode(testactivity.destroystudent).get(0);
		
		checkValueInstance(trace.getValueInstances().get(0), testactivity.u, 1, null, null);
		checkValueInstance(trace.getValueInstances().get(1), testactivity.s1, 1, null, null);
		checkValueInstance(trace.getValueInstances().get(2), testactivity.s2, 1, null, null);
		checkValueInstance(trace.getValueInstances().get(3), testactivity.s3, 1, null, destroyExecution);
		checkValueInstance(trace.getValueInstances().get(4), testactivity.l1, 0, null, null);
		checkValueInstance(trace.getValueInstances().get(5), testactivity.l2, 0, null, removeExecution);
		checkValueInstance(trace.getValueInstances().get(6), testactivity.l3, 0, null, destroyExecution);
		checkValueInstance(trace.getValueInstances().get(7), s4, 1, createExecution, null);
		checkValueInstance(trace.getValueInstances().get(8), l4, 0, addExecution, null);
	}
	
	private boolean checkValueInstance(ValueInstance valueInstance, Value runtimeValue, int snapshots, ActivityNodeExecution creator, ActivityNodeExecution destroyer) {
		if(valueInstance.getRuntimeValue() != runtimeValue)
			return false;
		if(valueInstance.getSnapshots().size() != snapshots)
			return false;
		if(valueInstance.getCreator() != creator)
			return false;
		if(valueInstance.getDestroyer() != destroyer)
			return false;
		return true;		
	}
	
	@Test
	public void testExpansionRegion() {
		TestActivityFactory factory = new TestActivityFactory();
		ExpansionRegionTestActivity1 testactivity = factory.new ExpansionRegionTestActivity1();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = -1;
		for(Event e : eventlist) {
			if(e instanceof ActivityEntryEvent) {
				executionID = ((ActivityEntryEvent)e).getActivityExecutionID();
				break;
			}
		}		
		
		// check output
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(output));
		
		// get trace
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(4, exe_activity.getNodeExecutions().size());
		ExpansionRegionExecution exe_expansionregion = (ExpansionRegionExecution)exe_activity.getNodeExecutionsByNode(testactivity.expansionregion).get(0);
		ActionExecution exe_setname_1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.setname).get(0);
		ActionExecution exe_setname_2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.setname).get(1);
		ActionExecution exe_setname_3 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.setname).get(2);
		
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_expansionregion, exe_setname_1, exe_setname_2, exe_setname_3));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_expansionregion, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_setname_1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_setname_2, (ActivityNodeExecution[])null));		
		assertTrue(checkLogicalPredecessor(exe_setname_3, (ActivityNodeExecution[])null));		

		assertTrue(checkLogicalSuccessor(exe_expansionregion, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_setname_1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_setname_2, (ActivityNodeExecution[])null));		
		assertTrue(checkLogicalSuccessor(exe_setname_3, (ActivityNodeExecution[])null));		
		
		// check executions
		assertEquals(0, exe_expansionregion.getIncomingControl().size());
		assertEquals(1, exe_expansionregion.getInputs().size());
		assertEquals(1, exe_expansionregion.getExpansionInputs().size());
		assertEquals(0, exe_expansionregion.getOutputs().size());
		assertEquals(0, exe_expansionregion.getOutgoingControl().size());
		assertEquals(3, exe_expansionregion.getNestedNodeExecutions().size());
		assertTrue(exe_expansionregion.getNestedNodeExecutions().containsAll(Arrays.asList(exe_setname_1, exe_setname_2, exe_setname_3)));
		
		assertEquals(0, exe_setname_1.getIncomingControl().size());
		assertEquals(2, exe_setname_1.getInputs().size());
		assertEquals(1, exe_setname_1.getOutputs().size());
		assertEquals(0, exe_setname_1.getOutgoingControl().size());
		
		assertEquals(0, exe_setname_2.getIncomingControl().size());
		assertEquals(2, exe_setname_2.getInputs().size());
		assertEquals(1, exe_setname_2.getOutputs().size());
		assertEquals(0, exe_setname_2.getOutgoingControl().size());
		
		assertEquals(0, exe_setname_3.getIncomingControl().size());
		assertEquals(2, exe_setname_3.getInputs().size());
		assertEquals(1, exe_setname_3.getOutputs().size());
		assertEquals(0, exe_setname_3.getOutgoingControl().size());
		
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_activity, exe_expansionregion));
		assertTrue(checkObjectTokenSending(exe_setname_1, exe_activity));
		assertTrue(checkObjectTokenSending(exe_setname_2, exe_activity));
		assertTrue(checkObjectTokenSending(exe_setname_3, exe_activity));
		
		// check value instances and snapshots
		List<ValueInstance> valueInstances = getValueInstancesExceptForLinks(trace);
		assertEquals(4, valueInstances.size());
		
		ValueInstance person1=null, person2=null, person3=null;
		ValueSnapshot person1_snapshot1=null, person1_snapshot2=null, person2_snapshot1=null, person2_snapshot2=null, person3_snapshot1=null, person3_snapshot2=null;		
		
		for(int i=0;i<3;++i) {
			ValueInstance person;
			ValueSnapshot snapshot1, snapshot2;					
			
			person = valueInstances.get(i);
			assertNotNull(person);
			Object_ person_runtime = (Object_)person.getRuntimeValue();
			assertTrue(checkObjectType(person_runtime, testactivity.class_));
			assertTrue(checkObjectFeatureValue(person_runtime, testactivity.name, "tanja"));		
			assertEquals(2, person.getSnapshots().size());
			snapshot1 = person.getSnapshots().get(0);
			assertTrue(checkObjectType((Object_)snapshot1.getValue(), testactivity.class_));
			assertTrue(checkObjectFeatureValueEmpty((Object_)snapshot1.getValue(), testactivity.name));
			snapshot2 = person.getSnapshots().get(1);
			assertTrue(checkObjectType((Object_)snapshot2.getValue(), testactivity.class_));
			assertTrue(checkObjectFeatureValue((Object_)snapshot2.getValue(), testactivity.name, "tanja"));
			
			if(i==0) {
				person1 = person;
				person1_snapshot1 = snapshot1;
				person1_snapshot2 = snapshot2;
			} else if(i==1) {
				person2 = person;
				person2_snapshot1 = snapshot1;
				person2_snapshot2 = snapshot2;
			} else if(i==2) {
				person3 = person;
				person3_snapshot1 = snapshot1;
				person3_snapshot2 = snapshot2;
			}
		}
				
		ValueInstance tanja = valueInstances.get(3);		
		assertNotNull(tanja);
		assertEquals("tanja", ((StringValue)tanja.getRuntimeValue()).value);
		assertEquals(1, tanja.getSnapshots().size());
		ValueSnapshot tanja_snapshot = tanja.getSnapshots().get(0);		
		assertEquals("tanja", ((StringValue)tanja_snapshot.getValue()).value);
				
		// check tokens
		assertTrue(checkOutput(exe_setname_1, testactivity.setname.result, person1, person1_snapshot2));
		assertTrue(checkOutput(exe_setname_2, testactivity.setname.result, person2, person2_snapshot2));
		assertTrue(checkOutput(exe_setname_3, testactivity.setname.result, person3, person3_snapshot2));
		
		assertTrue(checkInput(exe_setname_1, testactivity.setname.object, person1, person1_snapshot1));
		assertTrue(checkInput(exe_setname_1, testactivity.setname.value, tanja, tanja_snapshot));
		assertTrue(checkInput(exe_setname_2, testactivity.setname.object, person2, person2_snapshot1));
		assertTrue(checkInput(exe_setname_2, testactivity.setname.value, tanja, tanja_snapshot));
		assertTrue(checkInput(exe_setname_3, testactivity.setname.object, person3, person3_snapshot1));
		assertTrue(checkInput(exe_setname_3, testactivity.setname.value, tanja, tanja_snapshot));
	
		assertTrue(checkInput(exe_expansionregion, testactivity.expansionregion.inputElement.get(0), person1, person1_snapshot1));
		assertTrue(checkInput(exe_expansionregion, testactivity.expansionregion.inputElement.get(0), person2, person2_snapshot1));
		assertTrue(checkInput(exe_expansionregion, testactivity.expansionregion.inputElement.get(0), person3, person3_snapshot1));
		
		// check parameter
		assertTrue(checkParameterInput(exe_activity, testactivity.objectparameter, person1, person1_snapshot1));		
		assertTrue(checkParameterInput(exe_activity, testactivity.objectparameter, person2, person2_snapshot1));
		assertTrue(checkParameterInput(exe_activity, testactivity.objectparameter, person3, person3_snapshot1));
		assertTrue(checkParameterInput(exe_activity, testactivity.valueparameter, tanja, tanja_snapshot));
		assertTrue(checkParameterOutput(exe_activity, testactivity.outparameter, person1, person1_snapshot2));
		assertTrue(checkParameterOutput(exe_activity, testactivity.outparameter, person2, person2_snapshot2));
		assertTrue(checkParameterOutput(exe_activity, testactivity.outparameter, person3, person3_snapshot2));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_activity, testactivity.objectparameter, testactivity.o1, testactivity.o3));
		assertTrue(checkToken(exe_activity, testactivity.valueparameter, testactivity.o2, testactivity.o4));
		
		assertTrue(checkToken(exe_setname_1, testactivity.setname.result, testactivity.o5, testactivity.o6));
		assertTrue(checkToken(exe_setname_2, testactivity.setname.result, testactivity.o5, testactivity.o6));
		assertTrue(checkToken(exe_setname_3, testactivity.setname.result, testactivity.o5, testactivity.o6));		
	}
	
	@Test
	public void testConditionalNode() {
		TestActivityFactory factory = new TestActivityFactory();
		ConditionalNodeTestActivity3 testactivity = factory.new ConditionalNodeTestActivity3();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = -1;
		for(Event e : eventlist) {
			if(e instanceof ActivityEntryEvent) {
				executionID = ((ActivityEntryEvent)e).getActivityExecutionID();
				break;
			}
		}		
		
		// get trace
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(8, exe_activity.getNodeExecutions().size());
		ActionExecution exe_readname = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.readname).get(0);
		ControlNodeExecution exe_fork = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.fork).get(0);
		StructuredActivityNodeExecution exe_conditional = (StructuredActivityNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.conditionalnode).get(0);
		ActionExecution exe_specifytanja = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.specifytanja).get(0);
		ActionExecution exe_testidtanja = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.testidtanja).get(0);
		ActionExecution exe_specifyphilip = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.specifyphilip).get(0);
		ActionExecution exe_testidphilp = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.testidphilip).get(0);
		ActionExecution exe_specify2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.specify2).get(0);
		
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_readname, exe_fork, exe_conditional, exe_specifytanja, exe_testidtanja, exe_specifyphilip, exe_testidphilp, exe_specify2));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_readname, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_fork, exe_readname));
		assertTrue(checkLogicalPredecessor(exe_conditional, exe_readname));		
		assertTrue(checkLogicalPredecessor(exe_specifytanja, (ActivityNodeExecution[])null));		
		assertTrue(checkLogicalPredecessor(exe_testidtanja, exe_specifytanja, exe_fork));
		assertTrue(checkLogicalPredecessor(exe_specifyphilip, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_testidphilp, exe_specifyphilip, exe_fork));
		assertTrue(checkLogicalPredecessor(exe_specify2, (ActivityNodeExecution[])null));

		assertTrue(checkLogicalSuccessor(exe_readname, exe_fork));
		assertTrue(checkLogicalSuccessor(exe_fork, exe_testidtanja, exe_testidphilp));
		assertTrue(checkLogicalSuccessor(exe_conditional, (ActivityNodeExecution[])null));		
		assertTrue(checkLogicalSuccessor(exe_specifytanja, exe_testidtanja));		
		assertTrue(checkLogicalSuccessor(exe_testidtanja, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_specifyphilip, exe_testidphilp));
		assertTrue(checkLogicalSuccessor(exe_testidphilp, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_specify2, (ActivityNodeExecution[])null));
		
		// check executions
		assertEquals(1, exe_conditional.getIncomingControl().size());
		assertEquals(0, exe_conditional.getInputs().size());
		assertEquals(1, exe_conditional.getOutputs().size());
		assertEquals(0, exe_conditional.getOutgoingControl().size());
		assertEquals(5, exe_conditional.getNestedNodeExecutions().size());
		assertTrue(exe_conditional.getNestedNodeExecutions().containsAll(Arrays.asList(exe_specifytanja, exe_testidtanja, exe_specifyphilip, exe_testidphilp, exe_specify2)));
		
		assertEquals(0, exe_readname.getIncomingControl().size());
		assertEquals(1, exe_readname.getInputs().size());
		assertEquals(1, exe_readname.getOutputs().size());
		assertEquals(1, exe_readname.getOutgoingControl().size());
		
		assertEquals(1, exe_fork.getRoutedTokens().size());
		
		assertEquals(0, exe_specifytanja.getIncomingControl().size());
		assertEquals(0, exe_specifytanja.getInputs().size());
		assertEquals(1, exe_specifytanja.getOutputs().size());
		assertEquals(0, exe_specifytanja.getOutgoingControl().size());
		
		assertEquals(0, exe_testidtanja.getIncomingControl().size());
		assertEquals(2, exe_testidtanja.getInputs().size());
		assertEquals(1, exe_testidtanja.getOutputs().size());
		assertEquals(0, exe_testidtanja.getOutgoingControl().size());
		
		assertEquals(0, exe_specifyphilip.getIncomingControl().size());
		assertEquals(0, exe_specifyphilip.getInputs().size());
		assertEquals(1, exe_specifyphilip.getOutputs().size());
		assertEquals(0, exe_specifyphilip.getOutgoingControl().size());
		
		assertEquals(0, exe_testidphilp.getIncomingControl().size());
		assertEquals(2, exe_testidphilp.getInputs().size());
		assertEquals(1, exe_testidphilp.getOutputs().size());
		assertEquals(0, exe_testidphilp.getOutgoingControl().size());
		
		assertEquals(0, exe_specify2.getIncomingControl().size());
		assertEquals(0, exe_specify2.getInputs().size());
		assertEquals(1, exe_specify2.getOutputs().size());
		assertEquals(0, exe_specify2.getOutgoingControl().size());
		
		// check control token flow            
		assertTrue(checkControlTokenSending(exe_readname, exe_conditional));
				
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_activity, exe_readname));
		assertTrue(checkObjectTokenSending(exe_readname, exe_fork));
		assertTrue(checkObjectTokenSending(exe_fork, exe_testidtanja));
		assertTrue(checkObjectTokenSending(exe_fork, exe_testidphilp));
		assertTrue(checkObjectTokenSending(exe_specifytanja, exe_testidtanja));
		assertTrue(checkObjectTokenSending(exe_specifyphilip, exe_testidphilp));
		assertTrue(checkObjectTokenSending(exe_conditional, exe_activity));
		
		// check value instances and snapshots
		assertEquals(7, trace.getValueInstances().size());
		 
		ValueInstance person = trace.getValueInstances().get(0);
		assertNotNull(person);
		Object_ person_runtime = (Object_)person.getRuntimeValue();
		assertTrue(checkObjectType(person_runtime, testactivity.class_));
		assertTrue(checkObjectFeatureValue(person_runtime, testactivity.name, "philip"));		
		assertEquals(1, person.getSnapshots().size());
		Object_ student1_snapshot1 = (Object_)person.getSnapshots().get(0).getValue();
		assertTrue(checkObjectType(student1_snapshot1, testactivity.class_));
		assertTrue(checkObjectFeatureValue(student1_snapshot1, testactivity.name, "philip"));
		
		ValueInstance philip_read = trace.getValueInstances().get(1);		
		assertNotNull(philip_read);
		assertEquals(1, philip_read.getSnapshots().size());
		assertEquals("philip", ((StringValue)philip_read.getRuntimeValue()).value);
		assertEquals("philip", ((StringValue)philip_read.getSnapshots().get(0).getValue()).value);
		
		ValueInstance tanja = trace.getValueInstances().get(2);		
		assertNotNull(tanja);
		assertEquals(1, tanja.getSnapshots().size());
		assertEquals("tanja", ((StringValue)tanja.getRuntimeValue()).value);
		assertEquals("tanja", ((StringValue)tanja.getSnapshots().get(0).getValue()).value);
		
		ValueInstance false_ = trace.getValueInstances().get(3);		
		assertNotNull(false_);
		assertEquals(1, false_.getSnapshots().size());
		assertEquals(false, ((BooleanValue)false_.getRuntimeValue()).value);
		assertEquals(false, ((BooleanValue)false_.getSnapshots().get(0).getValue()).value);
		
		ValueInstance philip = trace.getValueInstances().get(4);		
		assertNotNull(philip);
		assertEquals(1, philip.getSnapshots().size());
		assertEquals("philip", ((StringValue)philip.getRuntimeValue()).value);
		assertEquals("philip", ((StringValue)philip.getSnapshots().get(0).getValue()).value);
		
		ValueInstance true_ = trace.getValueInstances().get(5);		
		assertNotNull(true_);
		assertEquals(1, true_.getSnapshots().size());
		assertEquals(true, ((BooleanValue)true_.getRuntimeValue()).value);
		assertEquals(true, ((BooleanValue)true_.getSnapshots().get(0).getValue()).value);
		
		ValueInstance two = trace.getValueInstances().get(6);		
		assertNotNull(two);
		assertEquals(1, two.getSnapshots().size());
		assertEquals(2, ((IntegerValue)two.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)two.getSnapshots().get(0).getValue()).value);
		
		// check tokens
		assertTrue(checkOutput(exe_readname, testactivity.readname.result, philip_read, philip_read.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_specifytanja, testactivity.specifytanja.result, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_testidtanja, testactivity.testidtanja.result, false_, false_.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_specifyphilip, testactivity.specifyphilip.result, philip, philip.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_testidphilp, testactivity.testidphilip.result, true_, true_.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_specify2, testactivity.specify2.result, two, two.getSnapshots().get(0)));
		
		assertTrue(checkInput(exe_readname, testactivity.readname.object, person, person.getSnapshots().get(0)));
		assertTrue(checkInput(exe_testidtanja, testactivity.testidtanja.first, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkInput(exe_testidtanja, testactivity.testidtanja.second, philip_read, philip_read.getSnapshots().get(0)));
		assertTrue(checkInput(exe_testidphilp, testactivity.testidphilip.first, philip, philip.getSnapshots().get(0)));
		assertTrue(checkInput(exe_testidphilp, testactivity.testidphilip.second, philip_read, philip_read.getSnapshots().get(0)));
		
		// check parameter
		assertTrue(checkParameterInput(exe_activity, testactivity.inparameter, person, person.getSnapshots().get(0)));		
		assertTrue(checkParameterOutput(exe_activity, testactivity.outparameter, two, two.getSnapshots().get(0)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_activity, testactivity.inparameter, testactivity.o1));
		assertTrue(checkToken(exe_readname, testactivity.readname.result, testactivity.o2, testactivity.o3, testactivity.o4));
		assertTrue(checkToken(exe_specifytanja, testactivity.specifytanja.result, testactivity.o5));
		assertTrue(checkToken(exe_testidtanja, testactivity.testidtanja.result, (ActivityEdge[])null));
		assertTrue(checkToken(exe_specifyphilip, testactivity.specifyphilip.result, testactivity.o6));
		assertTrue(checkToken(exe_testidphilp, testactivity.testidphilip.result, (ActivityEdge[])null));
		assertTrue(checkToken(exe_specify2, testactivity.specify2.result, (ActivityEdge[])null));
		assertTrue(checkToken(exe_conditional, testactivity.conditionalnode.result.get(0), testactivity.o7));
		
		assertTrue(checkToken(exe_readname, testactivity.c1));
	}

	@Test
	public void testStructuredActivityNode() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity4 testactivity = factory.new StructuredActivityNodeTestActivity4();
		Activity activity = testactivity.activity;

		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();

		// get trace
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);

		assertEquals(2, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		assertEquals(testactivity.activity2, trace.getActivityExecutions().get(1).getActivity());

		// get executions
		ActivityExecution exe_activity1 = trace.getActivityExecutions().get(0);
		assertEquals(6, exe_activity1.getNodeExecutions().size());
		StructuredActivityNodeExecution exe_strnode1 = (StructuredActivityNodeExecution)exe_activity1.getNodeExecutionsByNode(testactivity.structurednode1).get(0);
		ControlNodeExecution exe_initial1 = (ControlNodeExecution)exe_activity1.getNodeExecutionsByNode(testactivity.initial).get(0);
		ActionExecution exe_createo1 = (ActionExecution)exe_activity1.getNodeExecutionsByNode(testactivity.create1).get(0);
		ActionExecution exe_createo2 = (ActionExecution)exe_activity1.getNodeExecutionsByNode(testactivity.create2).get(0);
		StructuredActivityNodeExecution exe_strnode2 = (StructuredActivityNodeExecution)exe_activity1.getNodeExecutionsByNode(testactivity.structurednode2).get(0);
		CallActionExecution exe_calla2 = (CallActionExecution)exe_activity1.getNodeExecutionsByNode(testactivity.callA2).get(0);

		ActivityExecution exe_activity2 = trace.getActivityExecutions().get(1);
		assertEquals(2, exe_activity2.getNodeExecutions().size());
		ControlNodeExecution exe_initial2 = (ControlNodeExecution)exe_activity2.getNodeExecutionsByNode(testactivity.initialA2).get(0);
		ActionExecution exe_setname = (ActionExecution)exe_activity2.getNodeExecutionsByNode(testactivity.setname).get(0);

		// check chronological order
		assertTrue(checkChronologicalOrder(exe_strnode1, exe_initial1, exe_createo1, exe_createo2, exe_strnode2, exe_calla2, exe_initial2, exe_setname));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_strnode1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_initial1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_createo1, exe_initial1));                
		assertTrue(checkLogicalPredecessor(exe_createo2, exe_createo1));                
		assertTrue(checkLogicalPredecessor(exe_strnode2, exe_createo1, exe_createo2));
		assertTrue(checkLogicalPredecessor(exe_calla2, exe_createo1, exe_createo2));
		assertTrue(checkLogicalPredecessor(exe_initial2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_setname, exe_initial2));

		assertTrue(checkLogicalSuccessor(exe_strnode1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_initial1, exe_createo1));
		assertTrue(checkLogicalSuccessor(exe_createo1, exe_createo2, exe_strnode2, exe_calla2));                
		assertTrue(checkLogicalSuccessor(exe_createo2, exe_strnode2, exe_calla2));              
		assertTrue(checkLogicalSuccessor(exe_strnode2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_calla2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_initial2, exe_setname));
		assertTrue(checkLogicalSuccessor(exe_setname, (ActivityNodeExecution[])null));

		// check executions
		assertEquals(0, exe_strnode1.getIncomingControl().size());
		assertEquals(1, exe_strnode1.getInputs().size());
		assertEquals(0, exe_strnode1.getOutputs().size());
		assertEquals(0, exe_strnode1.getOutgoingControl().size());
		assertEquals(4, exe_strnode1.getNestedNodeExecutions().size());
		assertTrue(exe_strnode1.getNestedNodeExecutions().containsAll(Arrays.asList(exe_initial1, exe_createo1, exe_createo2, exe_strnode2)));

		assertEquals(1, exe_initial1.getRoutedTokens().size());

		assertEquals(1, exe_createo1.getIncomingControl().size());
		assertEquals(0, exe_createo1.getInputs().size());
		assertEquals(1, exe_createo1.getOutputs().size());
		assertEquals(1, exe_createo1.getOutgoingControl().size());

		assertEquals(1, exe_createo2.getIncomingControl().size());
		assertEquals(0, exe_createo2.getInputs().size());
		assertEquals(1, exe_createo2.getOutputs().size());
		assertEquals(1, exe_createo2.getOutgoingControl().size());

		assertEquals(1, exe_strnode2.getIncomingControl().size());
		assertEquals(2, exe_strnode2.getInputs().size());
		assertEquals(0, exe_strnode2.getOutputs().size());
		assertEquals(0, exe_strnode2.getOutgoingControl().size());
		assertEquals(1, exe_strnode2.getNestedNodeExecutions().size());
		assertTrue(exe_strnode2.getNestedNodeExecutions().containsAll(Arrays.asList(exe_calla2)));

		assertEquals(0, exe_calla2.getIncomingControl().size());
		assertEquals(2, exe_calla2.getInputs().size());
		assertEquals(1, exe_calla2.getOutputs().size());
		assertEquals(0, exe_calla2.getOutgoingControl().size());

		assertEquals(1, exe_initial2.getRoutedTokens().size());

		assertEquals(1, exe_setname.getIncomingControl().size());
		assertEquals(2, exe_setname.getInputs().size());
		assertEquals(1, exe_setname.getOutputs().size());
		assertEquals(0, exe_setname.getOutgoingControl().size());

		// check control token flow            
		assertTrue(checkControlTokenSending(exe_initial1, exe_createo1));
		assertTrue(checkControlTokenSending(exe_createo1, exe_createo2));
		assertTrue(checkControlTokenSending(exe_createo2, exe_strnode2));
		assertTrue(checkControlTokenSending(exe_initial2, exe_setname));

		// check object token flow
		assertTrue(checkObjectTokenSending(exe_activity1, exe_strnode1));
		assertTrue(checkObjectTokenSending(exe_createo1, exe_strnode2));
		assertTrue(checkObjectTokenSending(exe_createo1, exe_calla2));
		assertTrue(checkObjectTokenSending(exe_createo2, exe_strnode2));
		assertTrue(checkObjectTokenSending(exe_createo2, exe_calla2));
		assertTrue(checkObjectTokenSending(exe_calla2, exe_activity1));

		assertTrue(checkObjectTokenSending(exe_activity2, exe_setname));                
		assertTrue(checkObjectTokenSending(exe_setname, exe_activity2));

		// check value instances and snapshots
		assertEquals(3, trace.getValueInstances().size());

		ValueInstance tanja = trace.getValueInstances().get(0);        
		assertNotNull(tanja);
		assertEquals(1, tanja.getSnapshots().size());
		assertEquals("tanja", ((StringValue)tanja.getRuntimeValue()).value);
		assertEquals("tanja", ((StringValue)tanja.getSnapshots().get(0).getValue()).value);

		ValueInstance student1 = trace.getValueInstances().get(1);
		assertNotNull(student1);
		Object_ student1_runtime = (Object_)student1.getRuntimeValue();
		assertTrue(checkObjectType(student1_runtime, testactivity.class_));
		assertTrue(checkObjectFeatureValue(student1_runtime, testactivity.name, "tanja"));              
		assertEquals(2, student1.getSnapshots().size());
		Object_ student1_snapshot1 = (Object_)student1.getSnapshots().get(0).getValue();
		assertTrue(checkObjectType(student1_snapshot1, testactivity.class_));
		assertTrue(checkObjectFeatureValueEmpty(student1_snapshot1, testactivity.name));
		Object_ student1_snapshot2 = (Object_)student1.getSnapshots().get(1).getValue();
		assertTrue(checkObjectType(student1_snapshot2, testactivity.class_));
		assertTrue(checkObjectFeatureValue(student1_snapshot2, testactivity.name, "tanja"));

		ValueInstance student2 = trace.getValueInstances().get(2);
		assertNotNull(student2);
		Object_ student2_runtime = (Object_)student2.getRuntimeValue();
		assertTrue(checkObjectType(student2_runtime, testactivity.class_));
		assertTrue(checkObjectFeatureValueEmpty(student2_runtime, testactivity.name));          
		assertEquals(1, student2.getSnapshots().size());
		Object_ student2_snapshot1 = (Object_)student2.getSnapshots().get(0).getValue();
		assertTrue(checkObjectType(student2_snapshot1, testactivity.class_));
		assertTrue(checkObjectFeatureValueEmpty(student2_snapshot1, testactivity.name));

		// check tokens
		assertTrue(checkOutput(exe_createo1, testactivity.create1.result, student1, student1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_createo2, testactivity.create2.result, student2, student2.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_calla2, testactivity.callA2.output.get(0), student1, student1.getSnapshots().get(1)));
		assertTrue(checkOutput(exe_setname, testactivity.setname.result, student1, student1.getSnapshots().get(1)));

		assertTrue(checkInput(exe_setname, testactivity.setname.object, student1, student1.getSnapshots().get(0)));
		assertTrue(checkInput(exe_setname, testactivity.setname.value, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkInput(exe_strnode2, testactivity.structurednode2.structuredNodeInput.get(1), student1, student1.getSnapshots().get(0)));
		assertTrue(checkInput(exe_strnode2, testactivity.structurednode2.structuredNodeInput.get(1), student2, student2.getSnapshots().get(0)));
		assertTrue(checkInput(exe_strnode2, testactivity.structurednode2.structuredNodeInput.get(0), tanja, tanja.getSnapshots().get(0)));

		// check parameter
		assertTrue(checkParameterInput(exe_activity1, testactivity.parameterin, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkParameterInput(exe_activity2, testactivity.parameterinobjectA2, student1, student1.getSnapshots().get(0)));
		assertTrue(checkParameterInput(exe_activity2, testactivity.parameterinobjectA2, student2, student2.getSnapshots().get(0)));
		assertTrue(checkParameterInput(exe_activity2, testactivity.parameterinvalueA2, tanja, tanja.getSnapshots().get(0)));

		assertTrue(checkParameterOutput(exe_activity1, testactivity.parameterout, student1, student1.getSnapshots().get(1)));
		assertTrue(checkParameterOutput(exe_activity2, testactivity.parameteroutA2, student1, student1.getSnapshots().get(1)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_createo1, testactivity.create1.result, testactivity.o3, testactivity.o5));
		assertTrue(checkToken(exe_createo2, testactivity.create2.result, testactivity.o4, testactivity.o5));
		assertTrue(checkToken(exe_calla2, testactivity.callA2.output.get(0), testactivity.o7, testactivity.o8, testactivity.o9));              

		assertTrue(checkToken(exe_setname, testactivity.setname.result, testactivity.o3A2));

		assertTrue(checkToken(exe_initial1, testactivity.c1));
		assertTrue(checkToken(exe_createo1, testactivity.c2));
		assertTrue(checkToken(exe_createo2, testactivity.c3));
		assertTrue(checkToken(exe_initial2, testactivity.c1A2));

		assertTrue(checkToken(exe_activity1, testactivity.parameterin, testactivity.o1, testactivity.o2, testactivity.o6));		
		assertTrue(checkIfTokenExists(exe_activity2, testactivity.parameterinobjectA2, testactivity.o1A2));
		assertTrue(checkIfTokenExists(exe_activity2, testactivity.parameterinobjectA2, (ObjectFlow[])null));
		assertTrue(checkToken(exe_activity2, testactivity.parameterinvalueA2, testactivity.o2A2));              
	}

	private boolean checkObjectType(Object_ object, Class_ class_) {
		return object.types.contains(class_);
	}
	
	private boolean checkObjectFeatureValue(Object_ object, StructuralFeature feature, String expectedValue) {	
		StringValue stringValue = new StringValue();
		stringValue.value = expectedValue;
		for(Value value : object.getFeatureValue(feature).values) {
			if(value.equals(stringValue)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkObjectFeatureValueEmpty(Object_ object, StructuralFeature feature) {	
		return (object.getFeatureValue(feature).values.size() == 0);
	}
	
	@Test
	public void testDecisionNode1() {
		TestActivityFactory factory = new TestActivityFactory();
		DecisionNodeTestActivity1 testactivity = factory.new DecisionNodeTestActivity1();
		Activity activity = testactivity.activity;
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		// get trace
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entry = ((ActivityEntryEvent)eventlist.get(0));				
		Trace trace = ExecutionContext.getInstance().getTrace(entry.getActivityExecutionID());
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(2, exe_activity.getNodeExecutions().size());
		ActionExecution exe_vs1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs1).get(0);
		DecisionNodeExecution exe_decision = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0);

		// check executions
		assertEquals(0, exe_vs1.getIncomingControl().size());
		assertEquals(0, exe_vs1.getOutgoingControl().size());
		assertEquals(0, exe_vs1.getInputs().size());
		
		assertNull(exe_decision.getDecisionInputValue());
		assertEquals(1, exe_decision.getRoutedTokens().size());
		
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_vs1, exe_decision));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_vs1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_decision, exe_vs1));

		assertTrue(checkLogicalSuccessor(exe_vs1, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_decision, (ActivityNodeExecution[])null));

		// check object token flow
		assertTrue(checkObjectTokenSending(exe_vs1, exe_decision));

		// check value instances and snapshots
		assertEquals(1, trace.getValueInstances().size());
		ValueInstance integer = trace.getValueInstances().get(0);		
		assertNotNull(integer);
		assertEquals(1, integer.getSnapshots().size());
		assertEquals(1, ((IntegerValue)integer.getRuntimeValue()).value);
		assertEquals(1, ((IntegerValue)integer.getSnapshots().get(0).getValue()).value);

		// check tokens
		assertTrue(checkOutput(exe_vs1, testactivity.vs1.result, integer, integer.getSnapshots().get(0)));

		// check parameter
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer, integer.getSnapshots().get(0)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_vs1, testactivity.vs1.result, testactivity.e1, testactivity.e2));
	}
	
	@Test
	public void testDecisionNode2() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.DecisionNodeTestActivity2 testactivity = factory.new DecisionNodeTestActivity2();
		Activity activity = testactivity.activity;
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		// get trace
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entry = ((ActivityEntryEvent)eventlist.get(0));				
		Trace trace = ExecutionContext.getInstance().getTrace(entry.getActivityExecutionID());
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(3, exe_activity.getNodeExecutions().size());
		ActionExecution exe_vs1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs1).get(0);
		ActionExecution exe_vs2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0);
		DecisionNodeExecution exe_decision = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0);

		// check executions 
		assertEquals(0, exe_vs1.getIncomingControl().size());
		assertEquals(0, exe_vs1.getInputs().size());
		
		assertEquals(0, exe_vs2.getInputs().size());
		
		assertNotNull(exe_decision.getDecisionInputValue());		
		assertTrue(exe_decision.getDecisionInputValue().getInputObjectToken() == exe_vs2.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertEquals(trace.getValueInstances().get(1).getSnapshots().get(0), exe_decision.getDecisionInputValue().getInputValueSnapshot());
		assertEquals(1, exe_decision.getRoutedTokens().size());
		
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_vs1, exe_vs2, exe_decision));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_vs1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_vs2, exe_vs1));
		assertTrue(checkLogicalPredecessor(exe_decision, exe_vs1, exe_vs2));

		assertTrue(checkLogicalSuccessor(exe_vs1, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_vs2, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_decision, (ActivityNodeExecution[])null));

		// check control token flow
		assertTrue(checkControlTokenSending(exe_vs1, exe_vs2));
		
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_vs1, exe_decision));
		assertTrue(checkObjectTokenSending(exe_vs2, exe_decision));

		// check value instances and snapshots
		assertEquals(2, trace.getValueInstances().size());
		ValueInstance integer1 = trace.getValueInstances().get(0);		
		assertNotNull(integer1);
		assertEquals(1, integer1.getSnapshots().size());
		assertEquals(1, ((IntegerValue)integer1.getRuntimeValue()).value);
		assertEquals(1, ((IntegerValue)integer1.getSnapshots().get(0).getValue()).value);
		ValueInstance integer2 = trace.getValueInstances().get(1);		
		assertNotNull(integer2);
		assertEquals(1, integer2.getSnapshots().size());
		assertEquals(2, ((IntegerValue)integer2.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)integer2.getSnapshots().get(0).getValue()).value);
		
		// check tokens
		assertTrue(checkOutput(exe_vs1, testactivity.vs1.result, integer1, integer1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs2, testactivity.vs2.result, integer2, integer2.getSnapshots().get(0)));

		// check parameter
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer1, integer1.getSnapshots().get(0)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_vs1, testactivity.vs1.result, testactivity.e1, testactivity.e2));
		assertTrue(checkToken(exe_vs1, testactivity.c1));
		assertTrue(checkToken(exe_vs2, testactivity.vs2.result, testactivity.decisionInputFlow));		
	}

	@Test
	public void testDecisionNode3() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.DecisionNodeTestActivity7 testactivity = factory.new DecisionNodeTestActivity7();
		Activity activity = testactivity.activity;
		
		// execute
		ExecutionContext.getInstance().execute(activity, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
			
		// get trace				
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(9, exe_activity.getNodeExecutions().size());
		assertEquals(1, exe_activity.getNodeExecutionsByNode(testactivity.vs0).size());
		assertEquals(1, exe_activity.getNodeExecutionsByNode(testactivity.vs1).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.vs2).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.merge).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.merge2).size());
		assertEquals(1, exe_activity.getNodeExecutionsByNode(testactivity.decision).size());
		
		ActionExecution exe_vs0 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs0).get(0);
		ActionExecution exe_vs1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs1).get(0);
		DecisionNodeExecution exe_decision = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0);
		
		ActionExecution exe_vs2_1, exe_vs2_2;
		if(((ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0)).getIncomingControl().get(0) == exe_vs0.getOutgoingControl().get(0)) {
			exe_vs2_1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0);
			exe_vs2_2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(1);
		} else {
			exe_vs2_1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(1);
			exe_vs2_2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0);
		}

		ControlNodeExecution exe_merge_1, exe_merge_2, exe_merge2_1, exe_merge2_2;
		if(((ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0)).getRoutedTokens().get(0) == exe_vs0.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken()) {
			exe_merge_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0);
			exe_merge_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(1);
		} else {
			exe_merge_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(1);
			exe_merge_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0);
		}
		if(((ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0)).getRoutedTokens().get(0) == exe_vs0.getOutgoingControl().get(0)) {
			exe_merge2_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0);
			exe_merge2_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(1);
		} else {
			exe_merge2_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(1);
			exe_merge2_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0);
		}		

		// check value instances and snapshots
		assertEquals(4, trace.getValueInstances().size());
		ValueInstance integer0 = trace.getValueInstances().get(0);		
		assertNotNull(integer0);
		assertEquals(1, integer0.getSnapshots().size());
		assertEquals(0, ((IntegerValue)integer0.getRuntimeValue()).value);
		assertEquals(0, ((IntegerValue)integer0.getSnapshots().get(0).getValue()).value);
		ValueInstance integer1 = trace.getValueInstances().get(1);		
		assertNotNull(integer1);
		assertEquals(1, integer1.getSnapshots().size());
		assertEquals(1, ((IntegerValue)integer1.getRuntimeValue()).value);
		assertEquals(1, ((IntegerValue)integer1.getSnapshots().get(0).getValue()).value);	
		ValueInstance integer2_1 = trace.getValueInstances().get(2);		
		assertNotNull(integer2_1);
		assertEquals(1, integer2_1.getSnapshots().size());
		assertEquals(2, ((IntegerValue)integer2_1.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)integer2_1.getSnapshots().get(0).getValue()).value);			
		ValueInstance integer2_2 = trace.getValueInstances().get(3);		
		assertNotNull(integer2_2);
		assertEquals(1, integer2_2.getSnapshots().size());
		assertEquals(2, ((IntegerValue)integer2_2.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)integer2_2.getSnapshots().get(0).getValue()).value);
				
		// check executions 
		assertEquals(0, exe_vs0.getIncomingControl().size());
		assertEquals(0, exe_vs0.getInputs().size());
		assertEquals(1, exe_vs0.getOutputs().size());
		assertEquals(1, exe_vs0.getOutgoingControl().size());
		
		assertEquals(1, exe_vs1.getIncomingControl().size());
		assertEquals(0, exe_vs1.getInputs().size());
		assertEquals(1, exe_vs1.getOutputs().size());
		assertEquals(1, exe_vs1.getOutgoingControl().size());
		
		assertEquals(1, exe_vs2_1.getIncomingControl().size());
		assertEquals(0, exe_vs2_1.getInputs().size());
		assertEquals(1, exe_vs2_1.getOutputs().size());
		assertEquals(0, exe_vs2_1.getOutgoingControl().size());
		
		assertEquals(1, exe_vs2_2.getIncomingControl().size());
		assertEquals(0, exe_vs2_2.getInputs().size());
		assertEquals(1, exe_vs2_2.getOutputs().size());
		assertEquals(0, exe_vs2_2.getOutgoingControl().size());
		
		assertEquals(1, exe_merge_1.getRoutedTokens().size());
		assertEquals(1, exe_merge_2.getRoutedTokens().size());
		assertEquals(1, exe_merge2_1.getRoutedTokens().size());
		assertEquals(1, exe_merge2_2.getRoutedTokens().size());

		assertEquals(2, exe_decision.getRoutedTokens().size());
		assertTrue(exe_decision.getRoutedTokens().get(0) == exe_vs0.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());		
		assertTrue(exe_decision.getRoutedTokens().get(1) == exe_vs1.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertNotNull(exe_decision.getDecisionInputValue());		
		assertTrue(exe_decision.getDecisionInputValue().getInputObjectToken() == exe_vs2_1.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertEquals(integer2_1.getSnapshots().get(0), exe_decision.getDecisionInputValue().getInputValueSnapshot());
				
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_vs0, exe_merge_1, exe_vs1, exe_merge2_1, exe_merge2_2, exe_merge_2, exe_vs2_1, exe_decision, exe_vs2_2));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_vs0, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_vs1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge_1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge2_1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge_2, exe_vs1));
		assertTrue(checkLogicalPredecessor(exe_merge2_2, exe_vs1));
		assertTrue(checkLogicalPredecessor(exe_vs2_1, exe_merge2_1));
		assertTrue(checkLogicalPredecessor(exe_vs2_2, exe_merge2_2));
		assertTrue(checkLogicalPredecessor(exe_decision, exe_vs2_1, exe_merge_1, exe_merge_2));
		
		assertTrue(checkLogicalSuccessor(exe_vs0, exe_vs1, exe_merge_1, exe_merge2_1));
		assertTrue(checkLogicalSuccessor(exe_vs1, exe_merge_2, exe_merge2_2));
		assertTrue(checkLogicalSuccessor(exe_merge_1, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_merge2_1, exe_vs2_1));
		assertTrue(checkLogicalSuccessor(exe_merge_2, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_merge2_2, exe_vs2_2));
		assertTrue(checkLogicalSuccessor(exe_vs2_1, exe_decision));
		assertTrue(checkLogicalSuccessor(exe_vs2_2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_decision, (ActivityNodeExecution[])null));

		// check control token flow
		assertTrue(checkControlTokenSending(exe_vs0, exe_vs1));
		assertTrue(checkControlTokenSending(exe_vs0, exe_merge2_1));
		assertTrue(checkControlTokenSending(exe_vs1, exe_merge2_2));
		assertTrue(checkControlTokenSending(exe_merge2_1, exe_vs2_1));
		assertTrue(checkControlTokenSending(exe_merge2_2, exe_vs2_2));
		
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_vs0, exe_merge_1));
		assertTrue(checkObjectTokenSending(exe_vs1, exe_merge_2));
		assertTrue(checkObjectTokenSending(exe_merge_1, exe_decision));
		assertTrue(checkObjectTokenSending(exe_merge_2, exe_decision));		
		assertTrue(checkObjectTokenSending(exe_vs2_1, exe_decision));		
		
		// check tokens
		assertTrue(checkOutput(exe_vs0, testactivity.vs0.result, integer0, integer0.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs1, testactivity.vs1.result, integer1, integer1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs2_1, testactivity.vs2.result, integer2_1, integer2_1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs2_2, testactivity.vs2.result, integer2_2, integer2_2.getSnapshots().get(0)));

		// check parameter
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer0, integer0.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer1, integer1.getSnapshots().get(0)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_vs0, testactivity.c1, testactivity.c2, testactivity.c4));
		assertTrue(checkToken(exe_vs1, testactivity.c3, testactivity.c4));
		
		assertTrue(checkToken(exe_vs0, testactivity.vs0.result, testactivity.e1, testactivity.e3, testactivity.e4));
		assertTrue(checkToken(exe_vs1, testactivity.vs1.result, testactivity.e2, testactivity.e3, testactivity.e4));
		assertTrue(checkToken(exe_vs2_1, testactivity.vs2.result, testactivity.decisionInputFlow));		
		assertTrue(checkToken(exe_vs2_2, testactivity.vs2.result, (ActivityEdge[])null));
	}
	
	@Test
	public void testDecisionNode4() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.DecisionNodeTestActivity7 testactivity = factory.new DecisionNodeTestActivity7();
		Activity activity = testactivity.activity;
		
		// execute
		ExecutionContext.getInstance().executeStepwise(activity, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.vs0);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.merge);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.merge2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.vs2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.decision);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.vs1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.merge);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.merge2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.vs2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.decision);
		
		// get trace				
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());
		
		// get executions
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(10, exe_activity.getNodeExecutions().size());
		assertEquals(1, exe_activity.getNodeExecutionsByNode(testactivity.vs0).size());
		assertEquals(1, exe_activity.getNodeExecutionsByNode(testactivity.vs1).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.vs2).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.merge).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.merge2).size());
		assertEquals(2, exe_activity.getNodeExecutionsByNode(testactivity.decision).size());
		
		ActionExecution exe_vs0 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs0).get(0);
		ActionExecution exe_vs1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs1).get(0);
		
		ActionExecution exe_vs2_1, exe_vs2_2;
		if(exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0).wasExecutedBefore(exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(1))) {
			exe_vs2_1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0);
			exe_vs2_2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(1);
		} else {
			exe_vs2_1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(1);
			exe_vs2_2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(testactivity.vs2).get(0);
		}
		DecisionNodeExecution exe_decision_1, exe_decision_2;
		if(exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0).wasExecutedBefore(exe_activity.getNodeExecutionsByNode(testactivity.decision).get(1))) {
			exe_decision_1 = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0);
			exe_decision_2 = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(1);
		} else {
			exe_decision_1 = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(1);
			exe_decision_2 = (DecisionNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.decision).get(0);
		}
		ControlNodeExecution exe_merge_1, exe_merge_2, exe_merge2_1, exe_merge2_2;
		if(exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0).wasExecutedBefore(exe_activity.getNodeExecutionsByNode(testactivity.merge).get(1))) {
			exe_merge_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0);
			exe_merge_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(1);
		} else {
			exe_merge_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(1);
			exe_merge_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge).get(0);
		}
		if(exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0).wasExecutedBefore(exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(1))) {
			exe_merge2_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0);
			exe_merge2_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(1);
		} else {
			exe_merge2_1 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(1);
			exe_merge2_2 = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(testactivity.merge2).get(0);
		}

		// check value instances and snapshots
		assertEquals(4, trace.getValueInstances().size());
		ValueInstance integer0 = trace.getValueInstances().get(0);		
		assertNotNull(integer0);
		assertEquals(1, integer0.getSnapshots().size());
		assertEquals(0, ((IntegerValue)integer0.getRuntimeValue()).value);
		assertEquals(0, ((IntegerValue)integer0.getSnapshots().get(0).getValue()).value);
		ValueInstance integer2_1 = trace.getValueInstances().get(1);		
		assertNotNull(integer2_1);
		assertEquals(1, integer2_1.getSnapshots().size());
		assertEquals(2, ((IntegerValue)integer2_1.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)integer2_1.getSnapshots().get(0).getValue()).value);
		ValueInstance integer1 = trace.getValueInstances().get(2);		
		assertNotNull(integer1);
		assertEquals(1, integer1.getSnapshots().size());
		assertEquals(1, ((IntegerValue)integer1.getRuntimeValue()).value);
		assertEquals(1, ((IntegerValue)integer1.getSnapshots().get(0).getValue()).value);		
		ValueInstance integer2_2 = trace.getValueInstances().get(3);		
		assertNotNull(integer2_2);
		assertEquals(1, integer2_2.getSnapshots().size());
		assertEquals(2, ((IntegerValue)integer2_2.getRuntimeValue()).value);
		assertEquals(2, ((IntegerValue)integer2_2.getSnapshots().get(0).getValue()).value);
				
		// check executions 
		assertEquals(0, exe_vs0.getIncomingControl().size());
		assertEquals(0, exe_vs0.getInputs().size());
		assertEquals(1, exe_vs0.getOutputs().size());
		assertEquals(1, exe_vs0.getOutgoingControl().size());
		
		assertEquals(1, exe_vs1.getIncomingControl().size());
		assertEquals(0, exe_vs1.getInputs().size());
		assertEquals(1, exe_vs1.getOutputs().size());
		assertEquals(1, exe_vs1.getOutgoingControl().size());
		
		assertEquals(1, exe_vs2_1.getIncomingControl().size());
		assertEquals(0, exe_vs2_1.getInputs().size());
		assertEquals(1, exe_vs2_1.getOutputs().size());
		assertEquals(0, exe_vs2_1.getOutgoingControl().size());
		
		assertEquals(1, exe_vs2_2.getIncomingControl().size());
		assertEquals(0, exe_vs2_2.getInputs().size());
		assertEquals(1, exe_vs2_2.getOutputs().size());
		assertEquals(0, exe_vs2_2.getOutgoingControl().size());
		
		assertEquals(1, exe_merge_1.getRoutedTokens().size());
		assertEquals(1, exe_merge_2.getRoutedTokens().size());
		assertEquals(1, exe_merge2_1.getRoutedTokens().size());
		assertEquals(1, exe_merge2_2.getRoutedTokens().size());

		assertEquals(1, exe_decision_1.getRoutedTokens().size());
		assertTrue(exe_decision_1.getRoutedTokens().get(0) == exe_vs0.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());		
		assertNotNull(exe_decision_1.getDecisionInputValue());		
		assertTrue(exe_decision_1.getDecisionInputValue().getInputObjectToken() == exe_vs2_1.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertEquals(integer2_1.getSnapshots().get(0), exe_decision_1.getDecisionInputValue().getInputValueSnapshot());
		
		assertEquals(1, exe_decision_2.getRoutedTokens().size());
		assertTrue(exe_decision_2.getRoutedTokens().get(0) == exe_vs1.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertNotNull(exe_decision_2.getDecisionInputValue());
		assertTrue(exe_decision_2.getDecisionInputValue().getInputObjectToken() == exe_vs2_2.getOutputs().get(0).getOutputValues().get(0).getOutputObjectToken());
		assertEquals(integer2_2.getSnapshots().get(0), exe_decision_2.getDecisionInputValue().getInputValueSnapshot());
		
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_vs0, exe_merge_1, exe_merge2_1, exe_vs2_1, exe_decision_1, exe_vs1, exe_merge_2, exe_merge2_2, exe_vs2_2, exe_decision_2));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_vs0, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_vs1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge_1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge2_1, exe_vs0));
		assertTrue(checkLogicalPredecessor(exe_merge_2, exe_vs1));
		assertTrue(checkLogicalPredecessor(exe_merge2_2, exe_vs1));
		assertTrue(checkLogicalPredecessor(exe_vs2_1, exe_merge2_1));
		assertTrue(checkLogicalPredecessor(exe_vs2_2, exe_merge2_2));
		assertTrue(checkLogicalPredecessor(exe_decision_1, exe_vs2_1, exe_merge_1));
		assertTrue(checkLogicalPredecessor(exe_decision_2, exe_vs2_2, exe_merge_2));
		
		assertTrue(checkLogicalSuccessor(exe_vs0, exe_vs1, exe_merge_1, exe_merge2_1));
		assertTrue(checkLogicalSuccessor(exe_vs1, exe_merge_2, exe_merge2_2));
		assertTrue(checkLogicalSuccessor(exe_merge_1, exe_decision_1));
		assertTrue(checkLogicalSuccessor(exe_merge2_1, exe_vs2_1));
		assertTrue(checkLogicalSuccessor(exe_merge_2, exe_decision_2));
		assertTrue(checkLogicalSuccessor(exe_merge2_2, exe_vs2_2));
		assertTrue(checkLogicalSuccessor(exe_vs2_1, exe_decision_1));
		assertTrue(checkLogicalSuccessor(exe_vs2_2, exe_decision_2));
		assertTrue(checkLogicalSuccessor(exe_decision_1, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_decision_2, (ActivityNodeExecution[])null));

		// check control token flow
		assertTrue(checkControlTokenSending(exe_vs0, exe_vs1));
		assertTrue(checkControlTokenSending(exe_vs0, exe_merge2_1));
		assertTrue(checkControlTokenSending(exe_vs1, exe_merge2_2));
		assertTrue(checkControlTokenSending(exe_merge2_1, exe_vs2_1));
		assertTrue(checkControlTokenSending(exe_merge2_2, exe_vs2_2));
		
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_vs0, exe_merge_1));
		assertTrue(checkObjectTokenSending(exe_vs1, exe_merge_2));
		assertTrue(checkObjectTokenSending(exe_merge_1, exe_decision_1));
		assertTrue(checkObjectTokenSending(exe_merge_2, exe_decision_2));		
		assertTrue(checkObjectTokenSending(exe_vs2_1, exe_decision_1));
		assertTrue(checkObjectTokenSending(exe_vs2_2, exe_decision_2));		
		
		// check tokens
		assertTrue(checkOutput(exe_vs0, testactivity.vs0.result, integer0, integer0.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs1, testactivity.vs1.result, integer1, integer1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs2_1, testactivity.vs2.result, integer2_1, integer2_1.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_vs2_2, testactivity.vs2.result, integer2_2, integer2_2.getSnapshots().get(0)));

		// check parameter
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer0, integer0.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity, testactivity.parameter, integer1, integer1.getSnapshots().get(0)));

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_vs0, testactivity.c1, testactivity.c2, testactivity.c4));
		assertTrue(checkToken(exe_vs1, testactivity.c3, testactivity.c4));
		
		assertTrue(checkToken(exe_vs0, testactivity.vs0.result, testactivity.e1, testactivity.e3, testactivity.e4));
		assertTrue(checkToken(exe_vs1, testactivity.vs1.result, testactivity.e2, testactivity.e3, testactivity.e4));
		assertTrue(checkToken(exe_vs2_1, testactivity.vs2.result, testactivity.decisionInputFlow));
		assertTrue(checkToken(exe_vs2_2, testactivity.vs2.result, testactivity.decisionInputFlow));		
	}
	
	@Test
	public void testTokenFlow1() {
		// create class part
		Class_ cl_student = ActivityFactory.createClass("Student");
		Property prop_name = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), cl_student);
		
		// create activity 2 (called activity)
		Activity activity2 = ActivityFactory.createActivity("testTokenFlow1 A2");
		Parameter param_in_activity2 = ActivityFactory.createParameter(activity2, "in parameter activity2", ParameterDirectionKind.in);
		Parameter param_out_activity2 = ActivityFactory.createParameter(activity2, "out parameter activity2", ParameterDirectionKind.out);
		ActivityParameterNode paramnode_in_activity2 = ActivityFactory.createActivityParameterNode(activity2, "in parameternode activity2", param_in_activity2);
		ActivityParameterNode paramnode_out_activity2 = ActivityFactory.createActivityParameterNode(activity2, "out parameternode activity2", param_out_activity2);
		
		CreateObjectAction create_student = ActivityFactory.createCreateObjectAction(activity2, "create student", cl_student);
		ValueSpecificationAction specify_mayerhofer = ActivityFactory.createValueSpecificationAction(activity2, "specify mayerhofer", "mayerhofer");
		AddStructuralFeatureValueAction add_name1 = ActivityFactory.createAddStructuralFeatureValueAction(activity2, "set name 1", prop_name, false);
		AddStructuralFeatureValueAction add_name2 = ActivityFactory.createAddStructuralFeatureValueAction(activity2, "set name 2", prop_name, false);
		ReadStructuralFeatureAction read_name = ActivityFactory.createReadStructuralFeatureAction(activity2, "read name", prop_name);
		ForkNode fork = ActivityFactory.createForkNode(activity2, "fork");
		ObjectFlow e3 = ActivityFactory.createObjectFlow(activity2, create_student.result, fork);		
		ObjectFlow e4 = ActivityFactory.createObjectFlow(activity2, fork, add_name1.object);
		ObjectFlow e5 = ActivityFactory.createObjectFlow(activity2, fork, add_name2.object);
		ObjectFlow e6 = ActivityFactory.createObjectFlow(activity2, paramnode_in_activity2, add_name1.value);
		ObjectFlow e7 = ActivityFactory.createObjectFlow(activity2, specify_mayerhofer.result, add_name2.value);
		ObjectFlow e8 = ActivityFactory.createObjectFlow(activity2, add_name1.result, read_name.object);
		ObjectFlow e9 = ActivityFactory.createObjectFlow(activity2, read_name.result, paramnode_out_activity2);
		ControlFlow e11 = ActivityFactory.createControlFlow(activity2, add_name1, specify_mayerhofer);
		ControlFlow e10 = ActivityFactory.createControlFlow(activity2, add_name2, read_name);
		
		// create activity 1 (calling activity)
		Activity activity1 = ActivityFactory.createActivity("testTokenFlow1 A1");
		Parameter param_in_activity1 = ActivityFactory.createParameter(activity1, "in parameter activity1", ParameterDirectionKind.in);
		Parameter param_out_activity1 = ActivityFactory.createParameter(activity1, "out parameter activity1", ParameterDirectionKind.out);
		ActivityParameterNode paramnode_in_activity1 = ActivityFactory.createActivityParameterNode(activity1, "in parameternode activity 1", param_in_activity1);
		ActivityParameterNode paramnode_out_activity1 = ActivityFactory.createActivityParameterNode(activity1, "out parameternode activity 1", param_out_activity1);
		CallBehaviorAction call_activity2 = ActivityFactory.createCallBehaviorAction(activity1, "call activity2", activity2, 1, 1);
		ObjectFlow e1 = ActivityFactory.createObjectFlow(activity1, paramnode_in_activity1, call_activity2.input.get(0));
		ObjectFlow e2 = ActivityFactory.createObjectFlow(activity1, call_activity2.output.get(0), paramnode_out_activity1);
		
		Parameter param_in_activity1_unused = ActivityFactory.createParameter(activity1, "in parameter activity1", ParameterDirectionKind.in);
		Parameter param_out_activity1_unused = ActivityFactory.createParameter(activity1, "out parameter activity1", ParameterDirectionKind.out);
		ActivityFactory.createActivityParameterNode(activity1, "in parameternode activity 1", param_in_activity1_unused);
		ActivityFactory.createActivityParameterNode(activity1, "out parameternode activity 1", param_out_activity1_unused);
				
		// create input values
		ParameterValueList inparametervalues = new ParameterValueList();
		
		ParameterValue inparametervalue = new ParameterValue();
		inparametervalue.parameter = param_in_activity1;
		inparametervalue.values = new ValueList();
		StringValue stringValue = new StringValue();
		stringValue.value = "tanja";
		inparametervalue.values.add(stringValue);		
		inparametervalues.add(inparametervalue);
		
		inparametervalue = new ParameterValue();
		inparametervalue.parameter = param_in_activity1_unused;
		inparametervalue.values = new ValueList();
		StringValue stringValueLala = new StringValue();
		stringValueLala.value = "lala";
		inparametervalue.values.add(stringValueLala);
		inparametervalues.add(inparametervalue);
		
		// execute
		ExecutionContext.getInstance().execute(activity1, null, inparametervalues);
		
		// get trace
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entry_activity1 = ((ActivityEntryEvent)eventlist.get(0));
		assertTrue(entry_activity1.getActivity().equals(activity1));				
		Trace trace = ExecutionContext.getInstance().getTrace(entry_activity1.getActivityExecutionID());
		assertNotNull(trace);
		assertEquals(2, trace.getActivityExecutions().size());
		assertEquals(activity1, trace.getActivityExecutions().get(0).getActivity());
		assertEquals(activity2, trace.getActivityExecutions().get(1).getActivity());
		
		// get executions of activity 1 (calling activity)
		ActivityExecution exe_activity1 = trace.getActivityExecutions().get(0);
		assertEquals(1, exe_activity1.getNodeExecutions().size());
		CallActionExecution exe_call_activity2 = (CallActionExecution)exe_activity1.getNodeExecutionsByNode(call_activity2).get(0);
		
		// get executions of activity 2 (called activity)
		ActivityExecution exe_activity2 = trace.getActivityExecutions().get(1);
		assertEquals(6, exe_activity2.getNodeExecutions().size());
		ActionExecution exe_create_student = (ActionExecution)exe_activity2.getNodeExecutionsByNode(create_student).get(0);
		ActionExecution exe_specify_mayerhofer = (ActionExecution)exe_activity2.getNodeExecutionsByNode(specify_mayerhofer).get(0);
		ActionExecution exe_add_name1 = (ActionExecution)exe_activity2.getNodeExecutionsByNode(add_name1).get(0);
		ActionExecution exe_add_name2 = (ActionExecution)exe_activity2.getNodeExecutionsByNode(add_name2).get(0);
		ActionExecution exe_read_name = (ActionExecution)exe_activity2.getNodeExecutionsByNode(read_name).get(0);
		ControlNodeExecution exe_fork = (ControlNodeExecution)exe_activity2.getNodeExecutionsByNode(fork).get(0);
			
		// check chronological order
		assertTrue(checkChronologicalOrder(exe_call_activity2, exe_create_student, exe_fork, exe_add_name1, exe_specify_mayerhofer, exe_add_name2, exe_read_name));
		
		// check logical order
		assertTrue(checkLogicalPredecessor(exe_call_activity2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_create_student, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_specify_mayerhofer, exe_add_name1));
		assertTrue(checkLogicalPredecessor(exe_fork, exe_create_student));
		assertTrue(checkLogicalPredecessor(exe_add_name1, exe_fork));
		assertTrue(checkLogicalPredecessor(exe_add_name2, exe_fork, exe_specify_mayerhofer));
		assertTrue(checkLogicalPredecessor(exe_read_name, exe_add_name1, exe_add_name2));
		
		assertTrue(checkLogicalSuccessor(exe_call_activity2, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalSuccessor(exe_create_student, exe_fork));
		assertTrue(checkLogicalSuccessor(exe_specify_mayerhofer, exe_add_name2));
		assertTrue(checkLogicalSuccessor(exe_fork, exe_add_name1, exe_add_name2));
		assertTrue(checkLogicalSuccessor(exe_add_name1, exe_read_name, exe_specify_mayerhofer));
		assertTrue(checkLogicalSuccessor(exe_add_name2, exe_read_name));
		assertTrue(checkLogicalSuccessor(exe_read_name, (ActivityNodeExecution[])null));
		
		// check control token flow
		assertTrue(checkControlTokenSending(exe_add_name1, exe_specify_mayerhofer));
		assertTrue(checkControlTokenSending(exe_add_name2, exe_read_name));
		
		// check object token flow
		assertTrue(checkObjectTokenSending(exe_create_student, exe_fork));
		assertTrue(checkObjectTokenSending(exe_fork, exe_add_name1));
		assertTrue(checkObjectTokenSending(exe_fork, exe_add_name2));
		assertTrue(checkObjectTokenSending(exe_specify_mayerhofer, exe_add_name2));
		assertTrue(checkObjectTokenSending(exe_add_name1, exe_read_name));
		assertTrue(checkObjectTokenSending(exe_activity1, exe_call_activity2));
		assertTrue(checkObjectTokenSending(exe_activity2, exe_add_name1));
		assertTrue(checkObjectTokenSending(exe_call_activity2, exe_activity1));
		assertTrue(checkObjectTokenSending(exe_read_name, exe_activity2));
		
		// check value instances and snapshots
		assertEquals(4, trace.getValueInstances().size());
		 
		ValueInstance tanja = getValueInstance(trace, stringValue);		
		assertNotNull(tanja);
		assertEquals(1, tanja.getSnapshots().size());
		
		StringValue stringValue2 = new StringValue();
		stringValue2.value = "mayerhofer";
		ValueInstance mayerhofer = getValueInstance(trace, stringValue2);
		assertNotNull(mayerhofer);
		assertEquals(1, mayerhofer.getSnapshots().size());
		
		ValueInstance lala = getValueInstance(trace, stringValueLala);		
		assertNotNull(lala);
		assertEquals(1, lala.getSnapshots().size());
		
		Object_ studentObject = new Object_();
		studentObject.types.add(cl_student);
		studentObject.createFeatureValues();
		ValueInstance student = getValueInstance(trace, studentObject);
		assertNotNull(student);
		assertEquals(3, student.getSnapshots().size());
		
		ValueList names = new ValueList();
		names.add(stringValue);
		studentObject.setFeatureValue(prop_name, names, 0);
		assertTrue(studentObject.equals(student.getSnapshots().get(1).getValue()));
		
		names.add(0, stringValue2);
		studentObject.setFeatureValue(prop_name, names, 0);
		assertTrue(studentObject.equals(student.getSnapshots().get(2).getValue()));
		
		// check tokens
		assertTrue(checkOutput(exe_create_student, create_student.result, student, student.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_specify_mayerhofer, specify_mayerhofer.result, mayerhofer, mayerhofer.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_add_name1, add_name1.result, student, student.getSnapshots().get(1)));
		assertTrue(checkOutput(exe_add_name2, add_name2.result, student, student.getSnapshots().get(2)));
		assertTrue(checkOutput(exe_read_name, read_name.result, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_read_name, read_name.result, mayerhofer, mayerhofer.getSnapshots().get(0)));
		
		assertTrue(checkInput(exe_add_name1, add_name1.object, student, student.getSnapshots().get(0)));
		assertTrue(checkInput(exe_add_name2, add_name2.object, student, student.getSnapshots().get(1)));
		assertTrue(checkInput(exe_read_name, read_name.object, student, student.getSnapshots().get(2)));
		assertTrue(checkInput(exe_add_name1, add_name1.value, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkInput(exe_add_name2, add_name2.value, mayerhofer, mayerhofer.getSnapshots().get(0)));
		
		assertTrue(checkInput(exe_call_activity2, call_activity2.input.get(0), tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_call_activity2, call_activity2.output.get(0), tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkOutput(exe_call_activity2, call_activity2.output.get(0), mayerhofer, mayerhofer.getSnapshots().get(0)));
		
		// check parameter
		assertTrue(checkParameterInput(exe_activity1, param_in_activity1, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkParameterInput(exe_activity2, param_in_activity2, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkParameterInput(exe_activity1, param_in_activity1_unused, lala, lala.getSnapshots().get(0)));
		
		assertTrue(checkParameterOutput(exe_activity1, param_out_activity1, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity1, param_out_activity1, mayerhofer, mayerhofer.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity2, param_out_activity2, tanja, tanja.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity2, param_out_activity2, mayerhofer, mayerhofer.getSnapshots().get(0)));
		assertTrue(checkParameterOutput(exe_activity1, param_out_activity1_unused, null, null));
		
		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_create_student, create_student.result, e3, e4, e5));
		assertTrue(checkToken(exe_specify_mayerhofer, specify_mayerhofer.result, e7));
		assertTrue(checkToken(exe_add_name1, add_name1.result, e8));
		assertTrue(checkToken(exe_add_name2, add_name2.result));
		assertTrue(checkToken(exe_read_name, read_name.result, e9));
		assertTrue(checkToken(exe_call_activity2, call_activity2.output.get(0), e2));
		
		assertTrue(checkToken(exe_add_name2, e10));
		assertTrue(checkToken(exe_add_name1, e11));
		
		assertTrue(checkToken(exe_activity2, param_in_activity2, e6));
		assertTrue(checkToken(exe_activity1, param_in_activity1, e1));
		assertTrue(checkToken(exe_activity1, param_in_activity1_unused));
	}
	
	@Test
	public void testTokenFlow2ControlNodes() {
		// create class
		Class_ class_ = ActivityFactory.createClass("class");

		// create activity
		Activity activity = ActivityFactory.createActivity("testTokenFlow2ControlNodes");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial");
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		JoinNode join = ActivityFactory.createJoinNode(activity, "join");
		MergeNode merge = ActivityFactory.createMergeNode(activity, "merge");
		ActivityFinalNode final_ = ActivityFactory.createActivityFinalNode(activity, "final");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class_);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class_);
		ControlFlow e1 = ActivityFactory.createControlFlow(activity, initial, fork);
		ControlFlow e2 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow e3 = ActivityFactory.createControlFlow(activity, fork, create2);
		ControlFlow e4 = ActivityFactory.createControlFlow(activity, create1, create2);
		ObjectFlow e5 = ActivityFactory.createObjectFlow(activity, create1.result, join);
		ObjectFlow e7 = ActivityFactory.createObjectFlow(activity, create2.result, join);
		ControlFlow e6 = ActivityFactory.createControlFlow(activity, create1, join);
		ControlFlow e8 = ActivityFactory.createControlFlow(activity, create2, join);
		ControlFlow e9 = ActivityFactory.createControlFlow(activity, join, merge);
		ControlFlow e10 = ActivityFactory.createControlFlow(activity, merge, final_);

		// execute
		ExecutionContext.getInstance().execute(activity, null, null);

		// get trace
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entry_activity = ((ActivityEntryEvent)eventlist.get(0));				
		Trace trace = ExecutionContext.getInstance().getTrace(entry_activity.getActivityExecutionID());
		assertNotNull(trace);
		assertEquals(1, trace.getActivityExecutions().size());
		assertEquals(activity, trace.getActivityExecutions().get(0).getActivity());

		// get executions of activity 2 (called activity)
		ActivityExecution exe_activity = trace.getActivityExecutions().get(0);
		assertEquals(7, exe_activity.getNodeExecutions().size());
		ActionExecution exe_create1 = (ActionExecution)exe_activity.getNodeExecutionsByNode(create1).get(0);
		ActionExecution exe_create2 = (ActionExecution)exe_activity.getNodeExecutionsByNode(create2).get(0);		
		ControlNodeExecution exe_fork = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(fork).get(0);
		ControlNodeExecution exe_initial = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(initial).get(0);
		ControlNodeExecution exe_join = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(join).get(0);
		ControlNodeExecution exe_merge = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(merge).get(0);
		ControlNodeExecution exe_final = (ControlNodeExecution)exe_activity.getNodeExecutionsByNode(final_).get(0);

		// check chronological order
		assertTrue(checkChronologicalOrder(exe_initial, exe_fork, exe_create1, exe_create2, exe_join, exe_merge, exe_final));

		// check logical order
		assertTrue(checkLogicalPredecessor(exe_initial, (ActivityNodeExecution[])null));
		assertTrue(checkLogicalPredecessor(exe_fork, exe_initial));
		assertTrue(checkLogicalPredecessor(exe_create1, exe_fork));
		assertTrue(checkLogicalPredecessor(exe_create2, exe_fork, exe_create1));
		assertTrue(checkLogicalPredecessor(exe_join, exe_create1, exe_create2));
		assertTrue(checkLogicalPredecessor(exe_merge, exe_join));
		assertTrue(checkLogicalPredecessor(exe_final, exe_merge));
		
		assertTrue(checkLogicalSuccessor(exe_initial, exe_fork));
		assertTrue(checkLogicalSuccessor(exe_fork, exe_create1, exe_create2));
		assertTrue(checkLogicalSuccessor(exe_create1, exe_create2, exe_join));
		assertTrue(checkLogicalSuccessor(exe_create2, exe_join));
		assertTrue(checkLogicalSuccessor(exe_join, exe_merge));
		assertTrue(checkLogicalSuccessor(exe_merge, exe_final));
		assertTrue(checkLogicalSuccessor(exe_final, (ActivityNodeExecution[])null));

		// check control token flow
		assertTrue(checkControlTokenSending(exe_initial, exe_fork));
		assertTrue(checkControlTokenSending(exe_fork, exe_create1));
		assertTrue(checkControlTokenSending(exe_fork, exe_create2));
		assertTrue(checkControlTokenSending(exe_create1, exe_create2));
		assertTrue(checkControlTokenSending(exe_create1, exe_join));
		assertTrue(checkControlTokenSending(exe_create2, exe_join));
		assertTrue(checkControlTokenSending(exe_join, exe_merge));
		assertTrue(checkControlTokenSending(exe_merge, exe_final));

		// check object token flow
		assertTrue(checkObjectTokenSending(exe_create1, exe_join));
		assertTrue(checkObjectTokenSending(exe_create2, exe_join));

		// check value instances and snapshots
		assertEquals(2, trace.getValueInstances().size());		
		assertEquals(1, trace.getValueInstances().get(0).getSnapshots().size());
		assertEquals(1, trace.getValueInstances().get(1).getSnapshots().size());

		// check traversed edges of control and object tokens
		assertTrue(checkToken(exe_initial, e1, e2, e3));
		
		assertTrue(checkToken(exe_create1, create1.result, e5, e9, e10));
		assertTrue(checkToken(exe_create2, create2.result, e7, e9, e10));

		assertTrue(checkToken(exe_create1, e4, e6, e9, e10));
		assertTrue(checkToken(exe_create2, e8, e9, e10));
	}	
	
	@Test
	public void testEmptyActivity() {
		Activity activity = ActivityFactory.createActivity("testEmptyActivity");
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());				
		
		Trace trace = ExecutionContext.getInstance().getTrace(activityentry.getActivityExecutionID());
		assertNotNull(trace);
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(activity, activityExecution.getActivity());
		assertNull(activityExecution.getCaller());
		assertEquals(0, activityExecution.getNodeExecutions().size());		
	}
		
	@Test
	public void testChronologicalNodeExecutionOrder1SingleActivity() {
		Activity activity = ActivityFactory.createActivity("testActivityNodeExecutionOrder");
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "initial node");
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "merge node 1");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "merge node 2");
		ActivityFactory.createControlFlow(activity, initialnode, mergenode1);
		ActivityFactory.createControlFlow(activity, mergenode1, mergenode2);
		
		// Execute activity
		ExecutionContext.getInstance().execute(activity, null, null);
		
		// Get trace
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);		
		assertEquals(activity, activityExecution.getActivity());
		assertEquals(executionID, activityExecution.getActivityExecutionID());		
		assertNull(activityExecution.getCaller());
		
		assertEquals(3, activityExecution.getNodeExecutions().size());
		
		ControlNodeExecution exe_initial = (ControlNodeExecution)activityExecution.getNodeExecutionsByNode(initialnode).get(0);
		ControlNodeExecution exe_merge1 = (ControlNodeExecution)activityExecution.getNodeExecutionsByNode(mergenode1).get(0);
		ControlNodeExecution exe_merge2 = (ControlNodeExecution)activityExecution.getNodeExecutionsByNode(mergenode2).get(0);
		
		assertNotNull(exe_initial);
		assertEquals(initialnode, exe_initial.getNode());
		assertNotNull(exe_merge1);
		assertEquals(mergenode1, exe_merge1.getNode());
		assertNotNull(exe_merge2);
		assertEquals(mergenode2, exe_merge2.getNode());
		
		// Assert chronological order
		assertTrue(checkChronologicalOrder(exe_initial, exe_merge1, exe_merge2));		
	}
	
	@Test
	public void testChronologicalNodeExecutionOrder2TwoActivities() {
		Activity a2 = ActivityFactory.createActivity("activity 2 (testChronologicalNodeExecutionOrder2TwoActivities)");
		InitialNode a2_initial = ActivityFactory.createInitialNode(a2, "initial node a2");
		MergeNode a2_merge = ActivityFactory.createMergeNode(a2, "merge node a2");
		ActivityFinalNode a2_final = ActivityFactory.createActivityFinalNode(a2, "final node a2");
		ActivityFactory.createControlFlow(a2, a2_initial, a2_merge);
		ActivityFactory.createControlFlow(a2, a2_merge, a2_final);
		
		Activity a1 = ActivityFactory.createActivity("activity 1 (testChronologicalNodeExecutionOrder2TwoActivities)");
		InitialNode a1_initial = ActivityFactory.createInitialNode(a1, "initial node a1");
		CallBehaviorAction a1_callaction = ActivityFactory.createCallBehaviorAction(a1, "call action a1", a2);
		ActivityFinalNode a1_final = ActivityFactory.createActivityFinalNode(a1, "final node a1");
		ActivityFactory.createControlFlow(a1, a1_initial, a1_callaction);
		ActivityFactory.createControlFlow(a1, a1_callaction, a1_final);
		
		// Execute activity
		ExecutionContext.getInstance().execute(a1, null, null);
		
		// Get trace
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertNotNull(trace);
		assertEquals(2, trace.getActivityExecutions().size());
		
		// Execution of activity 1 
		ActivityExecution a1_execution = trace.getActivityExecutions().get(0);		
		assertEquals(a1, a1_execution.getActivity());	
		
		assertEquals(3, a1_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a1_exe_initial = a1_execution.getNodeExecutionsByNode(a1_initial).get(0);
		assertEquals(a1_initial, a1_exe_initial.getNode());
		ActivityNodeExecution a1_exe_callaction = a1_execution.getNodeExecutionsByNode(a1_callaction).get(0);
		assertEquals(a1_callaction, a1_exe_callaction.getNode());
		ActivityNodeExecution a1_exe_final = a1_execution.getNodeExecutionsByNode(a1_final).get(0);
		assertEquals(a1_final, a1_exe_final.getNode());
		
		// Execution of activity 2
		ActivityExecution a2_execution = trace.getActivityExecutions().get(1);		
		assertEquals(a2, a2_execution.getActivity());		
		
		assertEquals(3, a2_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a2_exe_initial = a2_execution.getNodeExecutionsByNode(a2_initial).get(0);
		assertEquals(a2_initial, a2_exe_initial.getNode());
		ActivityNodeExecution a2_exe_merge = a2_execution.getNodeExecutionsByNode(a2_merge).get(0);
		assertEquals(a2_merge, a2_exe_merge.getNode());
		ActivityNodeExecution a2_exe_final = a2_execution.getNodeExecutionsByNode(a2_final).get(0);
		assertEquals(a2_final, a2_exe_final.getNode());
			
		// Assert chronological order
		assertTrue(checkChronologicalOrder(a1_exe_initial, a1_exe_callaction, a2_exe_initial, a2_exe_merge, a2_exe_final, a1_exe_final));		
	}
	
	@Test
	public void testChronologicalNodeExecutionOrder3InterleavedActivityExecutions() {
		Class_ c = ActivityFactory.createClass("class");
		Activity a2 = ActivityFactory.createActivity("activity 2 (testChronologicalNodeExecutionOrder3InterleavedActivityExecutions)");
		InitialNode a2_initial = ActivityFactory.createInitialNode(a2, "initial node a2");
		CreateObjectAction a2_create = ActivityFactory.createCreateObjectAction(a2, "create object action a2", c);
		ActivityFinalNode a2_final = ActivityFactory.createActivityFinalNode(a2, "final node a2");
		ActivityFactory.createControlFlow(a2, a2_initial, a2_create);
		ActivityFactory.createControlFlow(a2, a2_create, a2_final);
		
		Activity a1 = ActivityFactory.createActivity("activity 1 (testChronologicalNodeExecutionOrder3InterleavedActivityExecutions)");
		InitialNode a1_initial = ActivityFactory.createInitialNode(a1, "initial node a1");
		ForkNode a1_fork = ActivityFactory.createForkNode(a1, "fork node a1");
		CallBehaviorAction a1_callaction = ActivityFactory.createCallBehaviorAction(a1, "call action a1", a2);
		CreateObjectAction a1_create1 = ActivityFactory.createCreateObjectAction(a1, "create object action 1 a1", c);
		CreateObjectAction a1_create2 = ActivityFactory.createCreateObjectAction(a1, "create object action 2 a1", c);
		JoinNode a1_join = ActivityFactory.createJoinNode(a1, "join node a1");
		ActivityFinalNode a1_final = ActivityFactory.createActivityFinalNode(a1, "final node a1");
		ActivityFactory.createControlFlow(a1, a1_initial, a1_fork);
		ActivityFactory.createControlFlow(a1, a1_fork, a1_callaction);
		ActivityFactory.createControlFlow(a1, a1_fork, a1_create1);
		ActivityFactory.createControlFlow(a1, a1_create1, a1_create2);
		ActivityFactory.createControlFlow(a1, a1_create2, a1_join);
		ActivityFactory.createControlFlow(a1, a1_callaction, a1_join);
		ActivityFactory.createControlFlow(a1, a1_join, a1_final);
		
		// Execute activity
		ExecutionContext.getInstance().executeStepwise(a1, null, null);
		int executionID_a1 = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_initial);
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_fork);
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_callaction);
		int executionID_a2 = ((ActivityEntryEvent)eventlist.get(eventlist.size()-2)).getActivityExecutionID();
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_create1);
		ExecutionContext.getInstance().nextStep(executionID_a2, a2_initial);
		ExecutionContext.getInstance().nextStep(executionID_a2, a2_create);
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_create2);
		ExecutionContext.getInstance().nextStep(executionID_a2, a2_final);
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_join);
		ExecutionContext.getInstance().nextStep(executionID_a1, a1_final);
				
		// Get trace				
		Trace trace = ExecutionContext.getInstance().getTrace(executionID_a1);
		assertNotNull(trace);
		assertEquals(2, trace.getActivityExecutions().size());
		
		// Execution of activity 1 
		ActivityExecution a1_execution = trace.getActivityExecutions().get(0);		
		assertEquals(a1, a1_execution.getActivity());	
		
		assertEquals(7, a1_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a1_exe_initial = a1_execution.getNodeExecutionsByNode(a1_initial).get(0);
		assertEquals(a1_initial, a1_exe_initial.getNode());
		ActivityNodeExecution a1_exe_fork = a1_execution.getNodeExecutionsByNode(a1_fork).get(0);
		assertEquals(a1_fork, a1_exe_fork.getNode());
		ActivityNodeExecution a1_exe_callaction = a1_execution.getNodeExecutionsByNode(a1_callaction).get(0);
		assertEquals(a1_callaction, a1_exe_callaction.getNode());
		ActivityNodeExecution a1_exe_create1 = a1_execution.getNodeExecutionsByNode(a1_create1).get(0);
		assertEquals(a1_create1, a1_exe_create1.getNode());
		ActivityNodeExecution a1_exe_create2 = a1_execution.getNodeExecutionsByNode(a1_create2).get(0);
		assertEquals(a1_create2, a1_exe_create2.getNode());
		ActivityNodeExecution a1_exe_join = a1_execution.getNodeExecutionsByNode(a1_join).get(0);
		assertEquals(a1_join, a1_exe_join.getNode());
		ActivityNodeExecution a1_exe_final = a1_execution.getNodeExecutionsByNode(a1_final).get(0);
		assertEquals(a1_final, a1_exe_final.getNode());
		
		// Execution of activity 2
		ActivityExecution a2_execution = trace.getActivityExecutions().get(1);		
		assertEquals(a2, a2_execution.getActivity());		
		
		assertEquals(3, a2_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a2_exe_initial = a2_execution.getNodeExecutionsByNode(a2_initial).get(0);
		assertEquals(a2_initial, a2_exe_initial.getNode());
		ActivityNodeExecution a2_exe_create = a2_execution.getNodeExecutionsByNode(a2_create).get(0);
		assertEquals(a2_create, a2_exe_create.getNode());
		ActivityNodeExecution a2_exe_final = a2_execution.getNodeExecutionsByNode(a2_final).get(0);
		assertEquals(a2_final, a2_exe_final.getNode());
			
		// Assert chronological order
		assertTrue(checkChronologicalOrder(a1_exe_initial, a1_exe_fork, a1_exe_callaction, a1_exe_create1, a2_exe_initial, a2_exe_create, a1_exe_create2, a2_exe_final, a1_exe_join, a1_exe_final));		
	}
	
	@Test
	public void testActivityExecutionHierarchy1() {
		Class_ c = ActivityFactory.createClass("class");
		
		Activity a5 = ActivityFactory.createActivity("activity 5 (testActivityExecutionHierarchy1)");
		CreateObjectAction a5_create = ActivityFactory.createCreateObjectAction(a5, "create object a5", c);
		
		Activity a4 = ActivityFactory.createActivity("activity 4 (testActivityExecutionHierarchy1)");
		CallBehaviorAction a4_call = ActivityFactory.createCallBehaviorAction(a4, "call action a4", a5);
		
		Activity a2 = ActivityFactory.createActivity("activity 2 (testActivityExecutionHierarchy1)");
		CallBehaviorAction a2_call = ActivityFactory.createCallBehaviorAction(a2, "call action a2", a4);
		
		Activity a3 = ActivityFactory.createActivity("activity 3 (testActivityExecutionHierarchy1)");
		CreateObjectAction a3_create = ActivityFactory.createCreateObjectAction(a3, "create object a3", c);
				
		Activity a1 = ActivityFactory.createActivity("activity 1 (testChronologicalNodeExecutionOrder3InterleavedActivityExecutions)");
		InitialNode a1_initial = ActivityFactory.createInitialNode(a1, "initial node a1");
		CallBehaviorAction a1_callaction1 = ActivityFactory.createCallBehaviorAction(a1, "call action 1 a1", a2);
		CallBehaviorAction a1_callaction2 = ActivityFactory.createCallBehaviorAction(a1, "call action 2 a1", a3);
		ActivityFactory.createControlFlow(a1, a1_initial, a1_callaction1);
		ActivityFactory.createControlFlow(a1, a1_callaction1, a1_callaction2);
		
		// Execute activity
		ExecutionContext.getInstance().execute(a1, null, null);
		int executionID_a1 = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		// Get trace				
		Trace trace = ExecutionContext.getInstance().getTrace(executionID_a1);
		assertNotNull(trace);
		
		assertEquals(5, trace.getActivityExecutions().size());
		
		// Execution of activity 1 
		ActivityExecution a1_execution = trace.getActivityExecutions().get(0);		
		assertEquals(a1, a1_execution.getActivity());	
		
		assertEquals(3, a1_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a1_exe_initial = a1_execution.getNodeExecutionsByNode(a1_initial).get(0);
		ActivityNodeExecution a1_exe_callaction1 = a1_execution.getNodeExecutionsByNode(a1_callaction1).get(0);
		ActivityNodeExecution a1_exe_callaction2 = a1_execution.getNodeExecutionsByNode(a1_callaction2).get(0);
		
		// Execution of activity 2
		ActivityExecution a2_execution = trace.getActivityExecutions().get(1);		
		assertEquals(a2, a2_execution.getActivity());		
		
		assertEquals(1, a2_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a2_exe_callaction = a2_execution.getNodeExecutionsByNode(a2_call).get(0);
		
		// Execution of activity 4
		ActivityExecution a4_execution = trace.getActivityExecutions().get(2);		
		assertEquals(a4, a4_execution.getActivity());		

		assertEquals(1, a4_execution.getNodeExecutions().size());

		ActivityNodeExecution a4_exe_callaction = a4_execution.getNodeExecutionsByNode(a4_call).get(0);
		
		// Execution of activity 5
		ActivityExecution a5_execution = trace.getActivityExecutions().get(3);		
		assertEquals(a5, a5_execution.getActivity());		

		assertEquals(1, a5_execution.getNodeExecutions().size());

		ActivityNodeExecution a5_exe_create = a5_execution.getNodeExecutionsByNode(a5_create).get(0);
				
		// Execution of activity 3
		ActivityExecution a3_execution = trace.getActivityExecutions().get(4);		
		assertEquals(a3, a3_execution.getActivity());		
		
		assertEquals(1, a3_execution.getNodeExecutions().size());
		
		ActivityNodeExecution a3_exe_create = a3_execution.getNodeExecutionsByNode(a3_create).get(0);

		// Assert activity execution hierarchy
		assertEquals(null, a1_execution.getCaller());
		assertEquals(a1_exe_callaction1, a2_execution.getCaller());
		assertEquals(a2_exe_callaction, a4_execution.getCaller());
		assertEquals(a4_exe_callaction, a5_execution.getCaller());
		assertEquals(a1_exe_callaction2, a3_execution.getCaller());
		
		assertEquals(a2_execution, ((CallActionExecution)a1_exe_callaction1).getCallee());
		assertEquals(a4_execution, ((CallActionExecution)a2_exe_callaction).getCallee());
		assertEquals(a5_execution, ((CallActionExecution)a4_exe_callaction).getCallee());
		assertEquals(a3_execution, ((CallActionExecution)a1_exe_callaction2).getCallee());
		
		// Assert chronological order
		assertTrue(checkChronologicalOrder(a1_exe_initial, a1_exe_callaction1, a2_exe_callaction, a4_exe_callaction, a5_exe_create, a1_exe_callaction2, a3_exe_create));		
	}
	
	private boolean checkChronologicalOrder(ActivityNodeExecution... executions) {
		for(int i=0;i<executions.length;++i) {
			ActivityNodeExecution e = executions[i];						
			ActivityNodeExecution predecessor = e.getChronologicalPredecessor(); 
			ActivityNodeExecution successor = e.getChronologicalSuccessor();
			
			// check predecessor
			if(i > 0) {				
				if(predecessor == null) {
					return false;
				}
				if(!predecessor.equals(executions[i-1])) {			
					return false;
				}
			} else {
				// first node execution
				if (predecessor != null) {			
					return false;
				}
			}
				
			// check successor
			if(i < executions.length-1) {				
				if(successor == null) {
					return false;
				}
				if(!successor.equals(executions[i+1])) {			
					return false;
				}
			} else {
				// last node execution
				if (successor != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	private ValueInstance getValueInstance(Trace trace, Value value) {
		for(ValueInstance valueInstance : trace.getValueInstances()) {
			if(valueInstance.getOriginal().getValue().equals(value)) {
				return valueInstance;
			}
		}
		return null;
	}
	
	private boolean checkControlTokenSending(ActivityNodeExecution sourceExecution, ActivityNodeExecution targetExecution) {
		List<ControlTokenInstance> ctokens_out = getOutgoingControlToken(sourceExecution);
		List<ControlTokenInstance> ctoken_in = getIncomingControlToken(targetExecution);		
		if(ctokens_out.size() == 0) { // no tokens have been sent
			return false;
		}
		for(ControlTokenInstance ctoken : ctokens_out) {
			if(ctoken_in.contains(ctoken)) { // one token was sent from source to target
				return true;
			}
		}
		return false;
	}
	
	private List<ControlTokenInstance> getIncomingControlToken(
			ActivityNodeExecution execution) {
		List<ControlTokenInstance> tokens = new ArrayList<ControlTokenInstance>();
		if(execution instanceof ActionExecution) {
			tokens.addAll(((ActionExecution)execution).getIncomingControl());
		} else if(execution instanceof ControlNodeExecution) {
			for(TokenInstance token : ((ControlNodeExecution)execution).getRoutedTokens()) {
				if(token instanceof ControlTokenInstance) {
					tokens.add((ControlTokenInstance)token);
				}
			}			
		}
		return tokens;
	}

	private List<ControlTokenInstance> getOutgoingControlToken(
			ActivityNodeExecution execution) {
		List<ControlTokenInstance> tokens = new ArrayList<ControlTokenInstance>();
		if(execution instanceof ActionExecution) {
			tokens.addAll(((ActionExecution)execution).getOutgoingControl());
		} else if(execution instanceof ControlNodeExecution) {
			for(TokenInstance token : ((ControlNodeExecution)execution).getRoutedTokens()) {
				if(token instanceof ControlTokenInstance) {
					tokens.add((ControlTokenInstance)token);
				}
			}			
		}
		return tokens;
	}
/*
	private boolean checkControlTokenSending(ActionExecution sourceExecution, ActionExecution targetExecution) {
		for(ControlTokenInstance ctoken : sourceExecution.getOutgoingControl()) {
			if(targetExecution.getIncomingControl().contains(ctoken)) {
				return true;
			}
		}
		return false;
	}
	*/
	private boolean checkObjectTokenSending(ActivityExecution sourceExecution, ActivityNodeExecution targetExecution) {
		List<ObjectTokenInstance> outgoingTokens = new ArrayList<ObjectTokenInstance>();		
		for(InputParameterSetting input : sourceExecution.getActivityInputs()) {
			for(InputParameterValue value : input.getParameterValues()) {
				outgoingTokens.add(value.getParameterInputObjectToken());
			}
		}
		
		List<TokenInstance> incomingTokens = targetExecution.getIncomingTokens();
		for(ObjectTokenInstance token : outgoingTokens) {
			if(incomingTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkObjectTokenSending(ActivityNodeExecution sourceExecution, ActivityExecution targetExecution) {
		List<TokenInstance> outgoingTokens = sourceExecution.getOutgoingTokens();				
		List<ObjectTokenInstance> incomingTokens = new ArrayList<ObjectTokenInstance>();
		for(OutputParameterSetting output : targetExecution.getActivityOutputs()) {
			for(OutputParameterValue value : output.getParameterValues()) {
				incomingTokens.add(value.getParameterOutputObjectToken());
			}
		}
		
		for(TokenInstance token : outgoingTokens) {
			if(incomingTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkObjectTokenSending(ActivityNodeExecution sourceExecution, ActivityNodeExecution targetExecution) {
		List<TokenInstance> outgoingTokens = sourceExecution.getOutgoingTokens();
		List<TokenInstance> incomingTokens = targetExecution.getIncomingTokens();
		for(TokenInstance token : outgoingTokens) {
			if(token instanceof ObjectTokenInstance) {
				if(incomingTokens.contains(token)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkLogicalSuccessor(ActivityNodeExecution nodeExecution, ActivityNodeExecution... successorNodeExecutions) {
		if(successorNodeExecutions == null) {
			return (nodeExecution.getLogicalSuccessor().size() == 0);
		}
		for(ActivityNodeExecution successorNodeExecution : successorNodeExecutions) {
			if(!nodeExecution.getLogicalSuccessor().contains(successorNodeExecution)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkLogicalPredecessor(ActivityNodeExecution nodeExecution, ActivityNodeExecution... predecessorNodeExecutions) {
		if(predecessorNodeExecutions == null) {
			return (nodeExecution.getLogicalPredecessor().size() == 0);
		}
		for(ActivityNodeExecution predecessorNodeExecution : predecessorNodeExecutions) {
			if(!nodeExecution.getLogicalPredecessor().contains(predecessorNodeExecution)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkParameterOutput(ActivityExecution execution,
			Parameter parameter, ValueInstance valueInstance,
			ValueSnapshot valueSnapshot) {
		OutputParameterSetting parameterSetting = null;
		for(OutputParameterSetting setting : execution.getActivityOutputs()) {
			if(setting.getParameter().equals(parameter)) {
				parameterSetting = setting;
				break;
			}
		}
		if(parameterSetting == null) {
			return false;
		}
		
		OutputParameterValue parameterValue = null;
		for(OutputParameterValue value : parameterSetting.getParameterValues()) {
			if(value.getParameterOutputObjectToken().getTransportedValue().equals(valueInstance)) {
				parameterValue = value;
				break;
			}
		}
		
		if(parameterValue == null) {
			if(valueInstance != null) {			
				return false;
			} else {
				return true;
			}
		}
		
		if(!parameterValue.getValueSnapshot().equals(valueSnapshot)) {
			return false;
		}		
		
		return true;
	}

	private boolean checkParameterInput(ActivityExecution execution, Parameter parameter,
			ValueInstance valueInstance, ValueSnapshot valueSnapshot) {
		InputParameterSetting parameterSetting = null;
		for(InputParameterSetting setting : execution.getActivityInputs()) {
			if(setting.getParameter().equals(parameter)) {
				parameterSetting = setting;
				break;
			}
		}
		if(parameterSetting == null) {
			return false;
		}
		
		InputParameterValue parameterValue = null;
		for(InputParameterValue value : parameterSetting.getParameterValues()) {
			if(value.getParameterInputObjectToken().getTransportedValue().equals(valueInstance)) {
				parameterValue = value;
				break;
			}
		}
		if(parameterValue == null) {
			return false;
		}
		
		if(!parameterValue.getValueSnapshot().equals(valueSnapshot)) {
			return false;
		}
				
		return true;
	}

	private boolean checkInput(ActionExecution execution, InputPin pin,
			ValueInstance valueInstance, ValueSnapshot valueSnapshot) {
		Input input = null;
		for(Input i : execution.getInputs()) {
			if(i.getInputPin().equals(pin)) {
				input = i;
				break;
			}
		}		
		if(input == null) {
			return false;
		}

		InputValue inputValue = null;
		for(InputValue value : input.getInputValues()) {
			if(value.getInputObjectToken().getTransportedValue().equals(valueInstance)) {
				inputValue = value;
				break;
			}
		}		
		if(inputValue == null) {
			return false;
		}
		
		if(inputValue.getInputValueSnapshot().equals(valueSnapshot)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkInput(ExpansionRegionExecution execution, ExpansionNode expansionNode,
			ValueInstance valueInstance, ValueSnapshot valueSnapshot) {
		ExpansionInput input = null;
		for(ExpansionInput i : execution.getExpansionInputs()) {
			if(i.getExpansionNode().equals(expansionNode)) {
				input = i;
				break;
			}
		}		
		if(input == null) {
			return false;
		}

		InputValue inputValue = null;
		for(InputValue value : input.getExpansionInputValues()) {
			if(value.getInputObjectToken().getTransportedValue().equals(valueInstance)) {
				inputValue = value;
				break;
			}
		}		
		if(inputValue == null) {
			return false;
		}
		
		if(inputValue.getInputValueSnapshot().equals(valueSnapshot)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkOutput(ActionExecution execution,
			OutputPin pin, ValueInstance valueInstance, ValueSnapshot valueSnapshot) {
		Output output = null;
		for(Output o : execution.getOutputs()) {
			if(o.getOutputPin().equals(pin)) {
				output = o;
				break;
			}
		}		
		if(output == null) {
			return false;
		}

		OutputValue outputValue = null;
		for(OutputValue value : output.getOutputValues()) {
			if(value.getOutputObjectToken().getTransportedValue().equals(valueInstance)) {
				outputValue = value;
				break;
			}
		}		
		if(outputValue == null) {
			return false;
		}
		
		if(outputValue.getOutputValueSnapshot().equals(valueSnapshot)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkToken(ControlNodeExecution exe_initial, ActivityEdge... edges) {
		List<TokenInstance> tokens = getToken(exe_initial);
		return checkToken(tokens, edges);
	}

	private List<TokenInstance> getToken(ControlNodeExecution execution) {
		return execution.getRoutedTokens();
	}

	private boolean checkToken(ActivityExecution execution,
			Parameter parameter, ObjectFlow... edges) {
		List<TokenInstance> tokens = getToken(execution, parameter);	
		if(tokens == null || tokens.size() == 0) {
			return false;
		}
		return checkToken(tokens, edges);
	}

	private boolean checkToken(ActionExecution execution, ControlFlow... edges) {
		List<TokenInstance> tokens = getToken(execution);		
		return checkToken(tokens, edges);
	}

	private boolean checkToken(ActionExecution execution, OutputPin pin, ActivityEdge... edges) {
		List<TokenInstance> tokens = getToken(execution, pin);		
		return checkToken(tokens, edges);
	}
	
	private boolean checkToken(List<TokenInstance> tokens, ActivityEdge... edges) {
		if(edges == null) {
			edges = new ActivityEdge[0];
		}
		for(TokenInstance token : tokens) {
			if(token.getTraversedEdges().size() != edges.length) {
				return false;
			}			
			if(!token.getTraversedEdges().containsAll(Arrays.asList(edges))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkIfTokenExists(ActivityExecution execution, Parameter parameter, ObjectFlow... edges) {
		List<TokenInstance> tokens = getToken(execution, parameter);	
		if(tokens == null || tokens.size() == 0) {
			return false;
		}
		for(TokenInstance token : tokens) {
			if(checkToken(token, edges)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkToken(TokenInstance token, ActivityEdge... edges) {
		if(edges == null) {
			edges = new ActivityEdge[0];
		}

		if(token.getTraversedEdges().size() != edges.length) {
			return false;
		}			
		if(!token.getTraversedEdges().containsAll(Arrays.asList(edges))) {
			return false;
		}

		return true;
	}
	
	private List<TokenInstance> getToken(ActionExecution execution, OutputPin pin) {
		List<TokenInstance> tokens = new ArrayList<TokenInstance>();
		for(Output o : execution.getOutputs()) {
			if(o.getOutputPin().equals(pin)) {
				for(OutputValue ov : o.getOutputValues()) {
					if(ov.getOutputObjectToken() != null) {
						tokens.add(ov.getOutputObjectToken());
					}
				}
			}
		}
		return tokens;
	}
	
	private List<TokenInstance> getToken(ActivityExecution execution, Parameter parameter) {
		List<TokenInstance> tokens = new ArrayList<TokenInstance>();
		for(InputParameterSetting o : execution.getActivityInputs()) {
			if(o.getParameter().equals(parameter)) {
				for(InputParameterValue ov : o.getParameterValues()) {
					if(ov.getParameterInputObjectToken() != null) {
						tokens.add(ov.getParameterInputObjectToken());
					}
				}
			}
		}
		return tokens;
	}
	
	private List<TokenInstance> getToken(ActionExecution execution) {
		List<TokenInstance> tokens = new ArrayList<TokenInstance>();
		tokens.addAll(execution.getOutgoingControl());
		return tokens;
	}
	
	private List<ValueInstance> getValueInstancesExceptForLinks(Trace trace) {
		List<ValueInstance> valueInstances = new ArrayList<ValueInstance>(trace.getValueInstances());
		valueInstances.removeAll(getLinkValueInstances(trace));
		return valueInstances;
	}
	
	private List<ValueInstance> getLinkValueInstances(Trace trace) {
		List<ValueInstance> linkValueInstances = new ArrayList<ValueInstance>();
		for(ValueInstance valueInstance : trace.getValueInstances()) {
			if(valueInstance.getRuntimeValue() instanceof Link) {
				linkValueInstances.add(valueInstance);
			}				
		}
		return linkValueInstances;
	}

	@Override
	public void notify(Event event) {
		eventlist.add(event);
		
		if(event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntry = (ActivityEntryEvent)event;
			System.err.println("Activity Entry " + activityEntry.getActivity().name);
		} else if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExit = (ActivityExitEvent)event;
			System.err.println("Activity Exit " + activityExit.getActivity().name);
		} else if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent nodeEntry = (ActivityNodeEntryEvent)event;
			System.err.println("Activity Node Entry " + nodeEntry.getNode().name);
		} else if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent nodeEntry = (ActivityNodeExitEvent)event;
			System.err.println("Activity Node Exit " + nodeEntry.getNode().name);
		}
	}
	
}
