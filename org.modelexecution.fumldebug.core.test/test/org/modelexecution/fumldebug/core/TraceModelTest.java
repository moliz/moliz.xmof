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
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * @author Tanja Mayerhofer
 *
 */
public class TraceModelTest extends MolizTest implements ExecutionEventListener {

	private List<Event> eventlist = new ArrayList<Event>();
	
	public TraceModelTest() {
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
/*	
	@Test
	public void testEmptyActivityWithInputParameter() {
		Activity activity = ActivityFactory.createActivity("testEmptyActivityWithInputParameter");
		Parameter parameter = ActivityFactory.createParameter(activity, "inputparam1", ParameterDirectionKind.in);
		ActivityParameterNode parameternode = ActivityFactory.createActivityParameterNode(activity, "inputparamnode1", parameter);
		
		
		// Define input parameter
		ParameterValue parametervalue = new ParameterValue();
		parametervalue.parameter = parameter;
		ValueList values = new ValueList();
		StringValue value = new StringValue();
		value.value = "INPUT";
		values.add(value);
		parametervalue.values = values;
		
		ParameterValueList parametervalues = new ParameterValueList();
		parametervalues.add(parametervalue);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, parametervalues);
				
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
		assertEquals(activityentry.getActivityExecutionID(), activityExecution.getActivityExecutionID());
		assertNull(activityExecution.getCaller());
		assertEquals(0, activityExecution.getNodeExecutions().size());
		assertEquals(1, activityExecution.getParameterInputs().size());
		assertTrue(activityExecution.getParameterInputs().get(0) instanceof UserParameterInput);
		assertEquals(parameternode, activityExecution.getParameterInputs().get(0).getInputParameterNode());
		assertEquals(1, activityExecution.getParameterInputs().get(0).getParameterInputTokens().size());
		assertTrue(activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT",((StringValue)activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue()).value);
	}
	
	@Test
	public void testEmptyActivityWithInputParameterMultiple() {
		Activity activity = ActivityFactory.createActivity("testEmptyActivityWithInputParameterMultiple");
		Parameter parameter1 = ActivityFactory.createParameter(activity, "inputparam1", ParameterDirectionKind.in);
		Parameter parameter2 = ActivityFactory.createParameter(activity, "inputparam2", ParameterDirectionKind.in);
		ActivityParameterNode parameternode1 = ActivityFactory.createActivityParameterNode(activity, "inputparamnode1", parameter1);
		ActivityParameterNode parameternode2 = ActivityFactory.createActivityParameterNode(activity, "inputparamnode2", parameter2);
		
		
		// Define input parameter
		ParameterValue parametervalue1 = new ParameterValue();
		parametervalue1.parameter = parameter1;
		ValueList values1 = new ValueList();
		StringValue value1_1 = new StringValue();
		value1_1.value = "INPUT 1 1";
		StringValue value1_2 = new StringValue();
		value1_2.value = "INPUT 1 2";
		values1.add(value1_1);
		values1.add(value1_2);
		parametervalue1.values = values1;
		
		ParameterValue parametervalue2 = new ParameterValue();
		parametervalue2.parameter = parameter2;
		ValueList values2 = new ValueList();
		StringValue value2 = new StringValue();
		value2.value = "INPUT 2";
		values2.add(value2);
		parametervalue2.values = values2;
		
		ParameterValueList parametervalues = new ParameterValueList();
		parametervalues.add(parametervalue1);
		parametervalues.add(parametervalue2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, parametervalues);
				
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
		assertEquals(activityentry.getActivityExecutionID(), activityExecution.getActivityExecutionID());
		assertNull(activityExecution.getCaller());
		assertEquals(0, activityExecution.getNodeExecutions().size());
		assertEquals(2, activityExecution.getParameterInputs().size());
		
		assertEquals(parameternode1, activityExecution.getParameterInputs().get(0).getInputParameterNode());
		assertEquals(2, activityExecution.getParameterInputs().get(0).getParameterInputTokens().size());
		assertTrue(activityExecution.getParameterInputs().get(0) instanceof UserParameterInput);
		assertTrue(activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT 1 1",((StringValue)activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue()).value);
		assertTrue(activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(1).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT 1 2",((StringValue)activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(1).getValue().getValue()).value);
		
		assertTrue(activityExecution.getParameterInputs().get(1) instanceof UserParameterInput);
		assertEquals(parameternode2, activityExecution.getParameterInputs().get(1).getInputParameterNode());
		assertEquals(1, activityExecution.getParameterInputs().get(1).getParameterInputTokens().size());
		assertTrue(activityExecution.getParameterInputs().get(1).getParameterInputTokens().get(0).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT 2",((StringValue)activityExecution.getParameterInputs().get(1).getParameterInputTokens().get(0).getValue().getValue()).value);
	}
	
	@Test
	public void testEmptyActivityWithOutputParameter() {
		Activity activity = ActivityFactory.createActivity("testEmptyActivityWithOutputParameter");
		Parameter inparameter = ActivityFactory.createParameter(activity, "inputparam1", ParameterDirectionKind.in);
		ActivityParameterNode inparameternode = ActivityFactory.createActivityParameterNode(activity, "inputparamnode1", inparameter);
		Parameter outparameter = ActivityFactory.createParameter(activity, "ouputparam1", ParameterDirectionKind.out);
		ActivityParameterNode outparameternode = ActivityFactory.createActivityParameterNode(activity, "outputparamnode1", outparameter);
		ActivityFactory.createObjectFlow(activity, inparameternode, outparameternode);
		
		// Define input parameter
		ParameterValue parametervalue = new ParameterValue();
		parametervalue.parameter = inparameter;
		ValueList values = new ValueList();
		StringValue value = new StringValue();
		value.value = "INPUT";
		values.add(value);
		parametervalue.values = values;
		
		ParameterValueList parametervalues = new ParameterValueList();
		parametervalues.add(parametervalue);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, parametervalues);
				
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
		assertEquals(activityentry.getActivityExecutionID(), activityExecution.getActivityExecutionID());
		assertNull(activityExecution.getCaller());
		assertEquals(0, activityExecution.getNodeExecutions().size());
		
		assertEquals(1, activityExecution.getParameterInputs().size());
		assertEquals(inparameternode, activityExecution.getParameterInputs().get(0).getInputParameterNode());
		assertEquals(1, activityExecution.getParameterInputs().get(0).getParameterInputTokens().size());
		assertTrue(activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT",((StringValue)activityExecution.getParameterInputs().get(0).getParameterInputTokens().get(0).getValue().getValue()).value);
		
		assertEquals(1, activityExecution.getParameterOutputs().size());
		assertEquals(outparameternode, activityExecution.getParameterOutputs().get(0).getOutputParameterNode());
		assertEquals(1, activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().size());
		assertTrue(activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().get(0).getValue().getValue() instanceof StringValue);
		assertEquals("INPUT",((StringValue)activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().get(0).getValue().getValue()).value);
	}
	
	@Test
	public void testActivityWithOutputParameterMultiple() {
		Activity activity = ActivityFactory.createActivity("testEmptyActivityWithOutputParameterMultiple");
		Parameter outparameter1 = ActivityFactory.createParameter(activity, "ouputparam1", ParameterDirectionKind.out);
		ActivityParameterNode outparameternode1 = ActivityFactory.createActivityParameterNode(activity, "outputparamnode1", outparameter1);
		Parameter outparameter2 = ActivityFactory.createParameter(activity, "ouputparam2", ParameterDirectionKind.out);
		ActivityParameterNode outparameternode2 = ActivityFactory.createActivityParameterNode(activity, "outputparamnode2", outparameter2);
		ValueSpecificationAction vs1 = ActivityFactory.createValueSpecificationAction(activity, "vs 1", 1);
		ValueSpecificationAction vs2 = ActivityFactory.createValueSpecificationAction(activity, "vs 2", 2);
		ValueSpecificationAction vs3 = ActivityFactory.createValueSpecificationAction(activity, "vs 3", 3);
		ActivityFactory.createObjectFlow(activity, vs1.result, outparameternode1);
		ActivityFactory.createObjectFlow(activity, vs2.result, outparameternode1);
		ActivityFactory.createObjectFlow(activity, vs3.result, outparameternode2);
		ActivityFactory.createControlFlow(activity, vs1, vs2);
		ActivityFactory.createControlFlow(activity, vs2, vs3);
		
		ParameterValueList parametervalues = new ParameterValueList();
		
		// Start execution
		ExecutionContext.getInstance().execute(activity, null, parametervalues);
				
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(vs1, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(vs1, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(vs2, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(vs2, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(vs3, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(vs3, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(7)).getParent());				
		
		Trace trace = ExecutionContext.getInstance().getTrace(activityentry.getActivityExecutionID());
		assertNotNull(trace);
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(activity, activityExecution.getActivity());
		assertEquals(activityentry.getActivityExecutionID(), activityExecution.getActivityExecutionID());
		assertNull(activityExecution.getCaller());
		assertEquals(3, activityExecution.getNodeExecutions().size());
		
		assertEquals(0, activityExecution.getParameterInputs().size());
		
		assertEquals(2, activityExecution.getParameterOutputs().size());
		assertEquals(outparameternode1, activityExecution.getParameterOutputs().get(0).getOutputParameterNode());
		assertEquals(2, activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().size());
		assertEquals(1,((IntegerValue)activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().get(0).getValue().getValue()).value);
		assertEquals(2,((IntegerValue)activityExecution.getParameterOutputs().get(0).getParameterOutputTokens().get(1).getValue().getValue()).value);
		assertEquals(outparameternode2, activityExecution.getParameterOutputs().get(1).getOutputParameterNode());
		assertEquals(1, activityExecution.getParameterOutputs().get(1).getParameterOutputTokens().size());
		assertEquals(3,((IntegerValue)activityExecution.getParameterOutputs().get(1).getParameterOutputTokens().get(0).getValue().getValue()).value);
	}
*/		
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
/*	
	@Test
	public void testLogicalOrderOnActions() {
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
		
		ControlFlow cflow_initial2createobjtanja = ActivityFactory.createControlFlow(activity, initialnode, createobject_tanja);
		ControlFlow cflow_createobjtanja2valuetanja = ActivityFactory.createControlFlow(activity, createobject_tanja, valuespec_tanja);
		ControlFlow cflow_valuetanja2createobjphilip = ActivityFactory.createControlFlow(activity, valuespec_tanja, createobject_philip);
		ControlFlow cflow_createobjphilip2valuephilip = ActivityFactory.createControlFlow(activity, createobject_philip, valuespec_philip);

		ObjectFlow oflow_createobjtanja2nametanja = ActivityFactory.createObjectFlow(activity, createobject_tanja.result, addstructuralfeaturevalue.object);
		ObjectFlow oflow_valuetanja2nametanja = ActivityFactory.createObjectFlow(activity, valuespec_tanja.result, addstructuralfeaturevalue.value);
		ObjectFlow oflow_createobjphilip2namephilip = ActivityFactory.createObjectFlow(activity, createobject_philip.result, addstructuralfeaturevalue.object);
		ObjectFlow oflow_valuephilip2namephilip = ActivityFactory.createObjectFlow(activity, valuespec_philip.result, addstructuralfeaturevalue.value);
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(7, activityExecution.getNodeExecutions().size());
		
		// Initial Node
		ActivityNodeExecution nodeExecution_initialNode = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initialnode).size());		
		assertEquals(nodeExecution_initialNode, activityExecution.getNodeExecutionsByNode(initialnode).get(0));
		
		assertEquals(initialnode, nodeExecution_initialNode.getNode());
		assertEquals(activityExecution, nodeExecution_initialNode.getActivityExecution());
		
		assertEquals(0, nodeExecution_initialNode.getInputs().size());
		assertEquals(1, nodeExecution_initialNode.getOutputs().size());
		Output output_ctrl_initialNode = nodeExecution_initialNode.getOutputs().get(0);
		assertNull(output_ctrl_initialNode.getOutputPin());
		assertEquals(1, output_ctrl_initialNode.getTokens().size());
		assertTrue(output_ctrl_initialNode.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initialnode = (ControlTokenInstance)output_ctrl_initialNode.getTokens().get(0);
		
		// Create Object Action (Tanja)
		ActivityNodeExecution nodeExecution_createObjectTanja = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(createobject_tanja).size());
		assertEquals(nodeExecution_createObjectTanja, activityExecution.getNodeExecutionsByNode(createobject_tanja).get(0));
		
		assertEquals(createobject_tanja, nodeExecution_createObjectTanja.getNode());
		assertEquals(activityExecution, nodeExecution_createObjectTanja.getActivityExecution());
		
		assertEquals(1, nodeExecution_createObjectTanja.getInputs().size());
		Input input_ctrl_createObjectTanja = nodeExecution_createObjectTanja.getInputs().get(0);
		assertNull(input_ctrl_createObjectTanja.getInputPin());
		assertEquals(1, input_ctrl_createObjectTanja.getTokens().size());
		assertTrue(input_ctrl_createObjectTanja.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_createObjectTanja = (ControlTokenInstance)input_ctrl_createObjectTanja.getTokens().get(0);
		assertEquals(ctoken_output_initialnode, ctoken_input_createObjectTanja);
		
		assertEquals(2, nodeExecution_createObjectTanja.getOutputs().size());
		Output output_obj_createObjectTanja = nodeExecution_createObjectTanja.getOutputs().get(0);
		assertEquals(createobject_tanja.result, output_obj_createObjectTanja.getOutputPin());
		assertEquals(1, output_obj_createObjectTanja.getTokens().size());
		assertTrue(output_obj_createObjectTanja.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_createObjectTanja = (ObjectTokenInstance)output_obj_createObjectTanja.getTokens().get(0);
		ValueInstance value_output_createObjectTanja = otoken_output_createObjectTanja.getValue();
		assertTrue(value_output_createObjectTanja.getValue() instanceof Object_);
		Object_ obj_tanja_createObjectTanja = (Object_)value_output_createObjectTanja.getValue();
		assertEquals(1, obj_tanja_createObjectTanja.types.size());
		assertEquals(class_person, obj_tanja_createObjectTanja.types.get(0));
		assertEquals(1, obj_tanja_createObjectTanja.featureValues.size());
		assertEquals(property_name, obj_tanja_createObjectTanja.featureValues.get(0).feature);
		assertEquals(0, obj_tanja_createObjectTanja.featureValues.get(0).values.size());
		Output output_ctrl_createObjectTanja = nodeExecution_createObjectTanja.getOutputs().get(1);
		assertNull(output_ctrl_createObjectTanja.getOutputPin());
		assertEquals(1, output_ctrl_createObjectTanja.getTokens().size());
		assertTrue(output_ctrl_createObjectTanja.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_createObjectTanja = (ControlTokenInstance)output_ctrl_createObjectTanja.getTokens().get(0);
		
		// Value Specification Action ("tanja")
		ActivityNodeExecution nodeExecution_valueTanja = activityExecution.getNodeExecutions().get(2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(valuespec_tanja).size());
		assertEquals(nodeExecution_valueTanja, activityExecution.getNodeExecutionsByNode(valuespec_tanja).get(0));
		
		assertEquals(valuespec_tanja, nodeExecution_valueTanja.getNode());
		assertEquals(activityExecution, nodeExecution_valueTanja.getActivityExecution());
		
		assertEquals(1, nodeExecution_valueTanja.getInputs().size());
		Input input_ctrl_valueTanja = nodeExecution_valueTanja.getInputs().get(0);
		assertNull(input_ctrl_valueTanja.getInputPin());
		assertEquals(1, input_ctrl_valueTanja.getTokens().size());
		assertTrue(input_ctrl_valueTanja.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_valueTanja = (ControlTokenInstance)input_ctrl_valueTanja.getTokens().get(0);
		assertEquals(ctoken_output_createObjectTanja, ctoken_input_valueTanja);
		
		assertEquals(2, nodeExecution_valueTanja.getOutputs().size());
		Output output_value_valueTanja = nodeExecution_valueTanja.getOutputs().get(0);
		assertEquals(valuespec_tanja.result, output_value_valueTanja.getOutputPin());
		assertEquals(1, output_value_valueTanja.getTokens().size());
		assertTrue(output_value_valueTanja.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_valueTanja = (ObjectTokenInstance)output_value_valueTanja.getTokens().get(0);
		ValueInstance value_output_valueTanja = otoken_output_valueTanja.getValue();
		assertTrue(value_output_valueTanja.getValue() instanceof StringValue);
		StringValue strvalue_tanja_valueTanja = (StringValue)value_output_valueTanja.getValue();
		assertEquals("tanja", strvalue_tanja_valueTanja.value);
		Output output_ctrl_valueTanja = nodeExecution_valueTanja.getOutputs().get(1);
		assertNull(output_ctrl_valueTanja.getOutputPin());
		assertEquals(1, output_ctrl_valueTanja.getTokens().size());
		assertTrue(output_ctrl_valueTanja.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_valueTanja = (ControlTokenInstance)output_ctrl_valueTanja.getTokens().get(0);
		
		// Add Structural Feature Value Action (name="tanja")
		ActivityNodeExecution nodeExecution_addNameTanja = activityExecution.getNodeExecutions().get(3);
		assertEquals(2, activityExecution.getNodeExecutionsByNode(addstructuralfeaturevalue).size());
		assertTrue(activityExecution.getNodeExecutionsByNode(addstructuralfeaturevalue).contains(nodeExecution_addNameTanja));
		
		assertEquals(addstructuralfeaturevalue, nodeExecution_addNameTanja.getNode());
		assertEquals(activityExecution, nodeExecution_addNameTanja.getActivityExecution());
		
		assertEquals(2, nodeExecution_addNameTanja.getInputs().size());
		Input input_obj_addNameTanja = nodeExecution_addNameTanja.getInputs().get(0);
		assertEquals(addstructuralfeaturevalue.object, input_obj_addNameTanja.getInputPin());
		assertEquals(1, input_obj_addNameTanja.getTokens().size());
		assertTrue(input_obj_addNameTanja.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_inputObj_addNameTanja = (ObjectTokenInstance)input_obj_addNameTanja.getTokens().get(0);
		assertEquals(otoken_output_createObjectTanja, otoken_inputObj_addNameTanja);	
		Input input_value_addNameTanja = nodeExecution_addNameTanja.getInputs().get(1);
		assertEquals(addstructuralfeaturevalue.value, input_value_addNameTanja.getInputPin());
		assertEquals(1, input_value_addNameTanja.getTokens().size());
		assertTrue(input_value_addNameTanja.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_inputStr_addNameTanja = (ObjectTokenInstance)input_value_addNameTanja.getTokens().get(0);
		assertEquals(otoken_output_valueTanja, otoken_inputStr_addNameTanja);		
		
		assertEquals(1, nodeExecution_addNameTanja.getOutputs().size());
		Output output_obj_addNameTanja = nodeExecution_addNameTanja.getOutputs().get(0);
		assertEquals(addstructuralfeaturevalue.result, output_obj_addNameTanja.getOutputPin());
		assertEquals(1, output_obj_addNameTanja.getTokens().size());
		assertTrue(output_obj_addNameTanja.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_addNameTanja = (ObjectTokenInstance)output_obj_addNameTanja.getTokens().get(0);
		ValueInstance value_output_addNameTanja = otoken_output_addNameTanja.getValue();
		assertTrue(value_output_addNameTanja.getValue() instanceof Object_);
		Object_ obj_tanja_addNameTanja = (Object_)value_output_addNameTanja.getValue();
		assertFalse(obj_tanja_addNameTanja.equals(obj_tanja_createObjectTanja));
		assertEquals(0, obj_tanja_createObjectTanja.featureValues.get(0).values.size());
		assertEquals(1, obj_tanja_addNameTanja.types.size());
		assertEquals(class_person, obj_tanja_addNameTanja.types.get(0));
		assertEquals(1, obj_tanja_addNameTanja.featureValues.size());
		assertEquals(property_name, obj_tanja_addNameTanja.featureValues.get(0).feature);
		assertEquals(1, obj_tanja_addNameTanja.featureValues.get(0).values.size());
		assertTrue(obj_tanja_addNameTanja.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)obj_tanja_addNameTanja.featureValues.get(0).values.get(0)).value);
		
		// Create Object Action (Philip)
		ActivityNodeExecution nodeExecution_createObjectPhilip = activityExecution.getNodeExecutions().get(4);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(createobject_philip).size());
		assertEquals(nodeExecution_createObjectPhilip, activityExecution.getNodeExecutionsByNode(createobject_philip).get(0));
		
		assertEquals(createobject_philip, nodeExecution_createObjectPhilip.getNode());
		assertEquals(activityExecution, nodeExecution_createObjectPhilip.getActivityExecution());
		
		assertEquals(1, nodeExecution_createObjectPhilip.getInputs().size());
		Input input_ctrl_createObjectPhilip = nodeExecution_createObjectPhilip.getInputs().get(0);
		assertNull(input_ctrl_createObjectPhilip.getInputPin());
		assertEquals(1, input_ctrl_createObjectPhilip.getTokens().size());
		assertTrue(input_ctrl_createObjectPhilip.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_createObjectPhilip = (ControlTokenInstance)input_ctrl_createObjectPhilip.getTokens().get(0);
		assertEquals(ctoken_output_valueTanja, ctoken_input_createObjectPhilip);
		
		assertEquals(2, nodeExecution_createObjectPhilip.getOutputs().size());
		Output output_obj_createObjectPhilip = nodeExecution_createObjectPhilip.getOutputs().get(0);
		assertEquals(createobject_philip.result, output_obj_createObjectPhilip.getOutputPin());
		assertEquals(1, output_obj_createObjectPhilip.getTokens().size());
		assertTrue(output_obj_createObjectPhilip.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_createObjectPhilip = (ObjectTokenInstance)output_obj_createObjectPhilip.getTokens().get(0);
		ValueInstance value_output_createObjectPhilip = otoken_output_createObjectPhilip.getValue();
		assertTrue(value_output_createObjectPhilip.getValue() instanceof Object_);
		Object_ obj_philip_createObjectPhilip = (Object_)value_output_createObjectPhilip.getValue();
		assertEquals(1, obj_philip_createObjectPhilip.types.size());
		assertEquals(class_person, obj_philip_createObjectPhilip.types.get(0));
		assertEquals(1, obj_philip_createObjectPhilip.featureValues.size());
		assertEquals(property_name, obj_philip_createObjectPhilip.featureValues.get(0).feature);
		assertEquals(0, obj_philip_createObjectPhilip.featureValues.get(0).values.size());
		Output output_ctrl_createObjectPhilip = nodeExecution_createObjectPhilip.getOutputs().get(1);
		assertNull(output_ctrl_createObjectPhilip.getOutputPin());
		assertEquals(1, output_ctrl_createObjectPhilip.getTokens().size());
		assertTrue(output_ctrl_createObjectPhilip.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_createObjectPhilip = (ControlTokenInstance)output_ctrl_createObjectPhilip.getTokens().get(0);
		
		// Value Specification Action ("philip")
		ActivityNodeExecution nodeExecution_valuePhilip = activityExecution.getNodeExecutions().get(5);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(valuespec_philip).size());
		assertEquals(nodeExecution_valuePhilip, activityExecution.getNodeExecutionsByNode(valuespec_philip).get(0));
		
		assertEquals(valuespec_philip, nodeExecution_valuePhilip.getNode());
		assertEquals(activityExecution, nodeExecution_valuePhilip.getActivityExecution());
		
		assertEquals(1, nodeExecution_valuePhilip.getInputs().size());
		Input input_ctrl_valuePhilip = nodeExecution_valuePhilip.getInputs().get(0);
		assertNull(input_ctrl_valuePhilip.getInputPin());
		assertEquals(1, input_ctrl_valuePhilip.getTokens().size());
		assertTrue(input_ctrl_valuePhilip.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_valuePhilip = (ControlTokenInstance)input_ctrl_valuePhilip.getTokens().get(0);
		assertEquals(ctoken_output_createObjectPhilip, ctoken_input_valuePhilip);
		
		assertEquals(1, nodeExecution_valuePhilip.getOutputs().size());
		Output output_value_valuePhilip = nodeExecution_valuePhilip.getOutputs().get(0);
		assertEquals(valuespec_philip.result, output_value_valuePhilip.getOutputPin());
		assertEquals(1, output_value_valuePhilip.getTokens().size());
		assertTrue(output_value_valuePhilip.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_valuePhilip = (ObjectTokenInstance)output_value_valuePhilip.getTokens().get(0);
		ValueInstance value_output_valuePhilip = otoken_output_valuePhilip.getValue();
		assertTrue(value_output_valuePhilip.getValue() instanceof StringValue);
		StringValue strvalue_philip_valuePhilip = (StringValue)value_output_valuePhilip.getValue();
		assertEquals("philip", strvalue_philip_valuePhilip.value);
		
		// Add Structural Feature Value Action (name="philip")
		ActivityNodeExecution nodeExecution_addNamePhilip = activityExecution.getNodeExecutions().get(6);
		assertEquals(2, activityExecution.getNodeExecutionsByNode(addstructuralfeaturevalue).size());
		assertTrue(activityExecution.getNodeExecutionsByNode(addstructuralfeaturevalue).contains(nodeExecution_addNamePhilip));
		
		assertEquals(addstructuralfeaturevalue, nodeExecution_addNamePhilip.getNode());
		assertEquals(activityExecution, nodeExecution_addNamePhilip.getActivityExecution());

		assertEquals(2, nodeExecution_addNamePhilip.getInputs().size());	
		Input input_obj_addNamePhilip = nodeExecution_addNamePhilip.getInputs().get(0);
		assertEquals(addstructuralfeaturevalue.object, input_obj_addNamePhilip.getInputPin());
		assertEquals(1, input_obj_addNamePhilip.getTokens().size());
		assertTrue(input_obj_addNamePhilip.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_inputObj_addNamePhilip = (ObjectTokenInstance)input_obj_addNamePhilip.getTokens().get(0);
		assertEquals(otoken_output_createObjectPhilip, otoken_inputObj_addNamePhilip);		
		Input input_value_addNamePhilip = nodeExecution_addNamePhilip.getInputs().get(1);
		assertEquals(addstructuralfeaturevalue.value, input_value_addNamePhilip.getInputPin());
		assertEquals(1, input_value_addNamePhilip.getTokens().size());
		assertTrue(input_value_addNamePhilip.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_inputStr_addNamePhilip = (ObjectTokenInstance)input_value_addNamePhilip.getTokens().get(0);
		assertEquals(otoken_output_valuePhilip, otoken_inputStr_addNamePhilip);		
		
		assertEquals(1, nodeExecution_addNamePhilip.getOutputs().size());
		Output output_obj_addNamePhilip = nodeExecution_addNamePhilip.getOutputs().get(0);
		assertEquals(addstructuralfeaturevalue.result, output_obj_addNamePhilip.getOutputPin());
		assertEquals(1, output_obj_addNamePhilip.getTokens().size());
		assertTrue(output_obj_addNamePhilip.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_addNamePhilip = (ObjectTokenInstance)output_obj_addNamePhilip.getTokens().get(0);
		ValueInstance value_output_addNamePhilip = otoken_output_addNamePhilip.getValue();
		assertTrue(value_output_addNamePhilip.getValue() instanceof Object_);
		Object_ obj_philip_addNamePhilip = (Object_)value_output_addNamePhilip.getValue();
		assertFalse(obj_philip_addNamePhilip.equals(obj_philip_createObjectPhilip));
		assertEquals(0, obj_philip_createObjectPhilip.featureValues.get(0).values.size());
		assertEquals(1, obj_philip_addNamePhilip.types.size());
		assertEquals(class_person, obj_philip_addNamePhilip.types.get(0));
		assertEquals(1, obj_philip_addNamePhilip.featureValues.size());
		assertEquals(property_name, obj_philip_addNamePhilip.featureValues.get(0).feature);
		assertEquals(1, obj_philip_addNamePhilip.featureValues.get(0).values.size());
		assertTrue(obj_philip_addNamePhilip.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("philip", ((StringValue)obj_philip_addNamePhilip.featureValues.get(0).values.get(0)).value);
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_initialNode.getChronologicalPredecessor());
		assertEquals(nodeExecution_createObjectTanja, nodeExecution_initialNode.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_initialNode, nodeExecution_createObjectTanja.getChronologicalPredecessor());
		assertEquals(nodeExecution_valueTanja, nodeExecution_createObjectTanja.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_createObjectTanja, nodeExecution_valueTanja.getChronologicalPredecessor());
		assertEquals(nodeExecution_addNameTanja, nodeExecution_valueTanja.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_valueTanja, nodeExecution_addNameTanja.getChronologicalPredecessor());
		assertEquals(nodeExecution_createObjectPhilip, nodeExecution_addNameTanja.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_addNameTanja, nodeExecution_createObjectPhilip.getChronologicalPredecessor());
		assertEquals(nodeExecution_valuePhilip, nodeExecution_createObjectPhilip.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_createObjectPhilip, nodeExecution_valuePhilip.getChronologicalPredecessor());
		assertEquals(nodeExecution_addNamePhilip, nodeExecution_valuePhilip.getChronologicalSuccessor());
		
		assertEquals(nodeExecution_valuePhilip, nodeExecution_addNamePhilip.getChronologicalPredecessor());
		assertEquals(null, nodeExecution_addNamePhilip.getChronologicalSuccessor());
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initialNode.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initialNode.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_createObjectTanja, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_createObjectTanja.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_createObjectTanja.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initialNode, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_valueTanja));
		assertTrue(logicalSuccessor.contains(nodeExecution_addNameTanja));
		
		logicalPredecessor = nodeExecution_valueTanja.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_valueTanja.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_createObjectTanja, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_createObjectPhilip));
		assertTrue(logicalSuccessor.contains(nodeExecution_addNameTanja));
		
		logicalPredecessor = nodeExecution_addNameTanja.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_addNameTanja.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_createObjectTanja));
		assertTrue(logicalPredecessor.contains(nodeExecution_valueTanja));
		assertEquals(0, logicalSuccessor.size());
		
		logicalPredecessor = nodeExecution_createObjectPhilip.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_createObjectPhilip.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_valueTanja, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_valuePhilip));
		assertTrue(logicalSuccessor.contains(nodeExecution_addNamePhilip));
		
		logicalPredecessor = nodeExecution_valuePhilip.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_valuePhilip.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_createObjectPhilip, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_addNamePhilip, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_addNamePhilip.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_addNamePhilip.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_createObjectPhilip));
		assertTrue(logicalPredecessor.contains(nodeExecution_valuePhilip));
		assertEquals(0, logicalSuccessor.size());
		
		// Traversed edges
		assertEquals(1, ctoken_output_initialnode.getTraversedEdges().size());
		assertEquals(cflow_initial2createobjtanja, ctoken_output_initialnode.getTraversedEdges().get(0));
		assertEquals(1, ctoken_input_createObjectTanja.getTraversedEdges().size());
		assertEquals(cflow_initial2createobjtanja, ctoken_input_createObjectTanja.getTraversedEdges().get(0));
		assertEquals(ctoken_output_initialnode, ctoken_input_createObjectTanja);
		
		assertEquals(1, ctoken_output_createObjectTanja.getTraversedEdges().size());
		assertEquals(cflow_createobjtanja2valuetanja, ctoken_output_createObjectTanja.getTraversedEdges().get(0));
		assertEquals(1, ctoken_input_valueTanja.getTraversedEdges().size());
		assertEquals(cflow_createobjtanja2valuetanja, ctoken_input_valueTanja.getTraversedEdges().get(0));
		assertEquals(ctoken_output_createObjectTanja, ctoken_input_valueTanja);
		
		assertEquals(1, ctoken_output_valueTanja.getTraversedEdges().size());
		assertEquals(cflow_valuetanja2createobjphilip, ctoken_output_valueTanja.getTraversedEdges().get(0));
		assertEquals(1, ctoken_input_createObjectPhilip.getTraversedEdges().size());
		assertEquals(cflow_valuetanja2createobjphilip, ctoken_input_createObjectPhilip.getTraversedEdges().get(0));
		assertEquals(ctoken_output_valueTanja, ctoken_input_createObjectPhilip);
		
		assertEquals(1, ctoken_output_createObjectPhilip.getTraversedEdges().size());
		assertEquals(cflow_createobjphilip2valuephilip, ctoken_output_createObjectPhilip.getTraversedEdges().get(0));
		assertEquals(1, ctoken_input_valuePhilip.getTraversedEdges().size());
		assertEquals(cflow_createobjphilip2valuephilip, ctoken_input_valuePhilip.getTraversedEdges().get(0));
		assertEquals(ctoken_output_createObjectPhilip, ctoken_input_valuePhilip);
		
		assertEquals(1, otoken_output_createObjectTanja.getTraversedEdges().size());
		assertEquals(oflow_createobjtanja2nametanja, otoken_output_createObjectTanja.getTraversedEdges().get(0));
		assertEquals(1, otoken_inputObj_addNameTanja.getTraversedEdges().size());
		assertEquals(oflow_createobjtanja2nametanja, otoken_inputObj_addNameTanja.getTraversedEdges().get(0));
		assertEquals(otoken_output_createObjectTanja, otoken_inputObj_addNameTanja);
		
		assertEquals(1, otoken_output_valueTanja.getTraversedEdges().size());
		assertEquals(oflow_valuetanja2nametanja, otoken_output_valueTanja.getTraversedEdges().get(0));
		assertEquals(1, otoken_inputStr_addNameTanja.getTraversedEdges().size());
		assertEquals(oflow_valuetanja2nametanja, otoken_inputStr_addNameTanja.getTraversedEdges().get(0));
		assertEquals(otoken_output_valueTanja, otoken_inputStr_addNameTanja);
		
		assertEquals(1, otoken_output_createObjectPhilip.getTraversedEdges().size());
		assertEquals(oflow_createobjphilip2namephilip, otoken_output_createObjectPhilip.getTraversedEdges().get(0));
		assertEquals(1, otoken_inputObj_addNamePhilip.getTraversedEdges().size());
		assertEquals(oflow_createobjphilip2namephilip, otoken_inputObj_addNamePhilip.getTraversedEdges().get(0));
		assertEquals(otoken_output_createObjectPhilip, otoken_inputObj_addNamePhilip);
		
		assertEquals(1, otoken_output_valuePhilip.getTraversedEdges().size());
		assertEquals(oflow_valuephilip2namephilip, otoken_output_valuePhilip.getTraversedEdges().get(0));
		assertEquals(1, otoken_inputStr_addNamePhilip.getTraversedEdges().size());
		assertEquals(oflow_valuephilip2namephilip, otoken_inputStr_addNamePhilip.getTraversedEdges().get(0));
		assertEquals(otoken_output_valuePhilip, otoken_inputStr_addNamePhilip);
		
		assertEquals(0, otoken_output_addNameTanja.getTraversedEdges().size());
		assertEquals(0, otoken_output_addNamePhilip.getTraversedEdges().size());
	}

	@Test
	public void testForkNodeControlFlow() {
		Activity activity = ActivityFactory.createActivity("testForkNodeControlFlow");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial node");
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, initial, fork);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, fork, create2);
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		int executionorder_create1 = getExecutionOrderIndex(eventlist, create1);
		int executionorder_create2 = getExecutionOrderIndex(eventlist, create2);
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(4, activityExecution.getNodeExecutions().size());
					
		// Initial Node
		ActivityNodeExecution nodeExecution_initial = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_initial, activityExecution.getNodeExecutionsByNode(initial).get(0));
		
		assertEquals(initial, nodeExecution_initial.getNode());
		assertEquals(activityExecution, nodeExecution_initial.getActivityExecution());
		
		assertEquals(0, nodeExecution_initial.getInputs().size());					
		
		assertEquals(1, nodeExecution_initial.getOutputs().size());
		
		Output output_ctrl_initial = nodeExecution_initial.getOutputs().get(0);
		assertNull(output_ctrl_initial.getOutputPin());
		assertEquals(1, output_ctrl_initial.getTokens().size());
		assertTrue(output_ctrl_initial.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initial = (ControlTokenInstance)output_ctrl_initial.getTokens().get(0);
		
		// Fork Node
		ActivityNodeExecution nodeExecution_fork = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(fork).size());		
		assertEquals(nodeExecution_fork, activityExecution.getNodeExecutionsByNode(fork).get(0));
		
		assertEquals(fork, nodeExecution_fork.getNode());
		assertEquals(activityExecution, nodeExecution_fork.getActivityExecution());
		
		assertEquals(1, nodeExecution_fork.getInputs().size());		
		
		Input input_ctrl_fork = nodeExecution_fork.getInputs().get(0);
		assertNull(input_ctrl_fork.getInputPin());
		assertEquals(1, input_ctrl_fork.getTokens().size());
		assertTrue(input_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_fork = (ControlTokenInstance)input_ctrl_fork.getTokens().get(0);		
		assertEquals(ctoken_output_initial, ctoken_input_fork);				
		
		assertEquals(1, nodeExecution_fork.getOutputs().size());
		
		Output output_ctrl_fork = nodeExecution_fork.getOutputs().get(0);
		assertNull(output_ctrl_fork.getOutputPin());
		assertEquals(1, output_ctrl_fork.getTokens().size());
		assertTrue(output_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_fork = (ControlTokenInstance)output_ctrl_fork.getTokens().get(0);
		
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(executionorder_create1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create1 = (ControlTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(ctoken_output_fork, ctoken_input_create1);	
		
		assertEquals(1, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		// Create Object Action 2
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(executionorder_create2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		assertEquals(ctoken_output_fork, ctoken_input_create2);	
				
		assertEquals(1, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_initial.getChronologicalPredecessor());
		assertEquals(nodeExecution_fork, nodeExecution_initial.getChronologicalSuccessor());
		assertEquals(nodeExecution_initial, nodeExecution_fork.getChronologicalPredecessor());
		
		if(executionorder_create1 < executionorder_create2) {
			assertEquals(nodeExecution_create1, nodeExecution_fork.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_fork, nodeExecution_create1.getChronologicalPredecessor());
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create2.getChronologicalSuccessor());
		} else {
			assertEquals(nodeExecution_create2, nodeExecution_fork.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_fork, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create1.getChronologicalSuccessor());
		}
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initial.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initial.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_fork, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_fork.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_fork.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initial, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create1));
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Traversed edges
		assertEquals(ctoken_output_initial,  ctoken_input_fork);
		assertEquals(1, ctoken_input_fork.getTraversedEdges().size());
		assertEquals(cflow1, ctoken_input_fork.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_fork, ctoken_input_create1);
		assertEquals(ctoken_output_fork, ctoken_input_create2);
		assertEquals(2, ctoken_input_create2.getTraversedEdges().size());
		assertTrue(ctoken_input_create2.getTraversedEdges().contains(cflow2));
		assertTrue(ctoken_input_create2.getTraversedEdges().contains(cflow3));
	}
	
	@Test
	public void testForkNodeControlFlowTwoEdgesWithSameSourceTarget() {
		Activity activity = ActivityFactory.createActivity("testForkNodeControlFlowTwoEdgesWithSameSourceTarget");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial node");
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, initial, fork);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow cflow4 = ActivityFactory.createControlFlow(activity, fork, create2);
		
		ExecutionContext.getInstance().execute(activity, null, null);
		
		int executionorder_create1 = getExecutionOrderIndex(eventlist, create1);
		int executionorder_create2 = getExecutionOrderIndex(eventlist, create2);
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(4, activityExecution.getNodeExecutions().size());
					
		// Initial Node
		ActivityNodeExecution nodeExecution_initial = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_initial, activityExecution.getNodeExecutionsByNode(initial).get(0));
		
		assertEquals(initial, nodeExecution_initial.getNode());
		assertEquals(activityExecution, nodeExecution_initial.getActivityExecution());
		
		assertEquals(0, nodeExecution_initial.getInputs().size());					
		
		assertEquals(1, nodeExecution_initial.getOutputs().size());
		
		Output output_ctrl_initial = nodeExecution_initial.getOutputs().get(0);
		assertNull(output_ctrl_initial.getOutputPin());
		assertEquals(1, output_ctrl_initial.getTokens().size());
		assertTrue(output_ctrl_initial.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initial = (ControlTokenInstance)output_ctrl_initial.getTokens().get(0);
		
		// Fork Node
		ActivityNodeExecution nodeExecution_fork = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(fork).size());		
		assertEquals(nodeExecution_fork, activityExecution.getNodeExecutionsByNode(fork).get(0));
		
		assertEquals(fork, nodeExecution_fork.getNode());
		assertEquals(activityExecution, nodeExecution_fork.getActivityExecution());
		
		assertEquals(1, nodeExecution_fork.getInputs().size());		
		
		Input input_ctrl_fork = nodeExecution_fork.getInputs().get(0);
		assertNull(input_ctrl_fork.getInputPin());
		assertEquals(1, input_ctrl_fork.getTokens().size());
		assertTrue(input_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_fork = (ControlTokenInstance)input_ctrl_fork.getTokens().get(0);		
		assertEquals(ctoken_output_initial, ctoken_input_fork);				
		
		assertEquals(1, nodeExecution_fork.getOutputs().size());
		
		Output output_ctrl_fork = nodeExecution_fork.getOutputs().get(0);
		assertNull(output_ctrl_fork.getOutputPin());
		assertEquals(1, output_ctrl_fork.getTokens().size());
		assertTrue(output_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_fork = (ControlTokenInstance)output_ctrl_fork.getTokens().get(0);
		
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(executionorder_create1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create1 = (ControlTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(ctoken_output_fork, ctoken_input_create1);	
		
		assertEquals(1, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		// Create Object Action 2
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(executionorder_create2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		assertEquals(ctoken_output_fork, ctoken_input_create2);	
				
		assertEquals(1, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_initial.getChronologicalPredecessor());
		assertEquals(nodeExecution_fork, nodeExecution_initial.getChronologicalSuccessor());
		assertEquals(nodeExecution_initial, nodeExecution_fork.getChronologicalPredecessor());
		
		if(executionorder_create1 < executionorder_create2) {
			assertEquals(nodeExecution_create1, nodeExecution_fork.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_fork, nodeExecution_create1.getChronologicalPredecessor());
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create2.getChronologicalSuccessor());
		} else {
			assertEquals(nodeExecution_create2, nodeExecution_fork.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_fork, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create1.getChronologicalSuccessor());
		}
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initial.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initial.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_fork, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_fork.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_fork.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initial, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create1));
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Traversed edges
		assertEquals(ctoken_output_initial,  ctoken_input_fork);
		assertEquals(1, ctoken_input_fork.getTraversedEdges().size());
		assertEquals(cflow1, ctoken_input_fork.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_fork, ctoken_input_create1);
		assertEquals(ctoken_output_fork, ctoken_input_create2);
		assertEquals(3, ctoken_input_create2.getTraversedEdges().size());
		assertTrue(ctoken_input_create2.getTraversedEdges().contains(cflow2));
		assertTrue(ctoken_input_create2.getTraversedEdges().contains(cflow3));
		assertTrue(ctoken_input_create2.getTraversedEdges().contains(cflow4));
	}
	
	@Test
	public void testForkNodeControlFlowTwoIncomingControlFlowEdges() {
		Activity activity = ActivityFactory.createActivity("testForkNodeControlFlowTwoIncomingControlFlowEdges");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial node");
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, initial, fork);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, fork, create2);
		ControlFlow cflow4 = ActivityFactory.createControlFlow(activity, create1, create2);
				
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(4, activityExecution.getNodeExecutions().size());
					
		// Initial Node
		ActivityNodeExecution nodeExecution_initial = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_initial, activityExecution.getNodeExecutionsByNode(initial).get(0));
		
		assertEquals(initial, nodeExecution_initial.getNode());
		assertEquals(activityExecution, nodeExecution_initial.getActivityExecution());
		
		assertEquals(0, nodeExecution_initial.getInputs().size());					
		
		assertEquals(1, nodeExecution_initial.getOutputs().size());
		
		Output output_ctrl_initial = nodeExecution_initial.getOutputs().get(0);
		assertNull(output_ctrl_initial.getOutputPin());
		assertEquals(1, output_ctrl_initial.getTokens().size());
		assertTrue(output_ctrl_initial.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initial = (ControlTokenInstance)output_ctrl_initial.getTokens().get(0);
		
		// Fork Node
		ActivityNodeExecution nodeExecution_fork = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(fork).size());		
		assertEquals(nodeExecution_fork, activityExecution.getNodeExecutionsByNode(fork).get(0));
		
		assertEquals(fork, nodeExecution_fork.getNode());
		assertEquals(activityExecution, nodeExecution_fork.getActivityExecution());
		
		assertEquals(1, nodeExecution_fork.getInputs().size());		
		
		Input input_ctrl_fork = nodeExecution_fork.getInputs().get(0);
		assertNull(input_ctrl_fork.getInputPin());
		assertEquals(1, input_ctrl_fork.getTokens().size());
		assertTrue(input_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_fork = (ControlTokenInstance)input_ctrl_fork.getTokens().get(0);		
		assertEquals(ctoken_output_initial, ctoken_input_fork);				
		
		assertEquals(1, nodeExecution_fork.getOutputs().size());
		
		Output output_ctrl_fork = nodeExecution_fork.getOutputs().get(0);
		assertNull(output_ctrl_fork.getOutputPin());
		assertEquals(1, output_ctrl_fork.getTokens().size());
		assertTrue(output_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_fork = (ControlTokenInstance)output_ctrl_fork.getTokens().get(0);
		
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create1 = (ControlTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(ctoken_output_fork, ctoken_input_create1);	
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_cflow_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_cflow_create1.getOutputPin());
		assertEquals(1, output_cflow_create1.getTokens().size());
		assertTrue(output_cflow_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_cflow_create1.getTokens().get(0);
		
		// Create Object Action 2
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(2, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken1_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		ControlTokenInstance ctoken2_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(1);
		
		ControlTokenInstance ctoken_input_create2_fromFork = null;
		ControlTokenInstance ctoken_input_create2_fromCreate1 = null;
		if(ctoken1_input_create2.equals(ctoken_output_fork)) {
			ctoken_input_create2_fromFork = ctoken1_input_create2;
		} else if (ctoken1_input_create2.equals(ctoken_output_create1)) {
			ctoken_input_create2_fromCreate1 = ctoken1_input_create2;
		}
		if(ctoken2_input_create2.equals(ctoken_output_fork)) {
			ctoken_input_create2_fromFork = ctoken2_input_create2;
		} else if (ctoken2_input_create2.equals(ctoken_output_create1)) {
			ctoken_input_create2_fromCreate1 = ctoken2_input_create2;
		}
		assertNotNull(ctoken_input_create2_fromFork);
		assertNotNull(ctoken_input_create2_fromCreate1);
		assertFalse(ctoken_input_create2_fromFork.equals(ctoken_input_create2_fromCreate1));
		
		assertEquals(ctoken_output_fork, ctoken_input_create2_fromFork);	
				
		assertEquals(1, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_initial.getChronologicalPredecessor());
		assertEquals(nodeExecution_fork, nodeExecution_initial.getChronologicalSuccessor());
		assertEquals(nodeExecution_initial, nodeExecution_fork.getChronologicalPredecessor());

		assertEquals(nodeExecution_create1, nodeExecution_fork.getChronologicalSuccessor());

		assertEquals(nodeExecution_fork, nodeExecution_create1.getChronologicalPredecessor());
		assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());

		assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
		assertEquals(null, nodeExecution_create2.getChronologicalSuccessor());		
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initial.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initial.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_fork, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_fork.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_fork.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initial, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create1));
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_create2, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_fork));
		assertTrue(logicalPredecessor.contains(nodeExecution_create1));
		assertEquals(0, logicalSuccessor.size());		
		
		// Traversed edges
		assertEquals(ctoken_output_initial,  ctoken_input_fork);
		assertEquals(1, ctoken_input_fork.getTraversedEdges().size());
		assertEquals(cflow1, ctoken_input_fork.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_fork, ctoken_input_create1);
		assertEquals(ctoken_output_fork, ctoken_input_create2_fromFork);
		assertEquals(2, ctoken_input_create2_fromFork.getTraversedEdges().size());
		assertTrue(ctoken_input_create2_fromFork.getTraversedEdges().contains(cflow2));
		assertTrue(ctoken_input_create2_fromFork.getTraversedEdges().contains(cflow3));
		
		assertEquals(ctoken_output_create1, ctoken_input_create2_fromCreate1);
		assertEquals(1, ctoken_input_create2_fromCreate1.getTraversedEdges().size());
		assertTrue(ctoken_input_create2_fromCreate1.getTraversedEdges().contains(cflow4));
	}
	
	@Test
	public void testActionControlFlowMultipleOutgoing() {
		Activity activity = ActivityFactory.createActivity("testActionControlFlowMultipleOutgoing");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class3");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, create1, create2);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, create1, create3);
				
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(3, activityExecution.getNodeExecutions().size());
						
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(0, nodeExecution_create1.getInputs().size());					
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_cflow_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_cflow_create1.getOutputPin());
		assertEquals(1, output_cflow_create1.getTokens().size());
		assertTrue(output_cflow_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_cflow_create1.getTokens().get(0);
		
		// Create Object Action 2
		int index_create2 = getExecutionOrderIndex(eventlist, create2);
		
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(index_create2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_create2);	
				
		assertEquals(1, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		// Create Object Action 3
		int index_create3 = getExecutionOrderIndex(eventlist, create3);

		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(index_create3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(1, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);

		assertEquals(ctoken_output_create1, ctoken_input_create3);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_create1.getChronologicalPredecessor());
		
		if(index_create2 < index_create3) {
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(nodeExecution_create3, nodeExecution_create2.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create3.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create3.getChronologicalSuccessor());
		} else {
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create3.getChronologicalPredecessor());
			assertEquals(nodeExecution_create3, nodeExecution_create3.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create2.getChronologicalSuccessor());
		}
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		assertTrue(logicalSuccessor.contains(nodeExecution_create3));
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		// Traversed edges
		assertEquals(ctoken_output_create1,  ctoken_input_create2);
		assertEquals(ctoken_output_create1,  ctoken_input_create3);
		assertEquals(2, ctoken_output_create1.getTraversedEdges().size());
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow1));
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow2));		
	}
	
	@Test
	public void testActionControlFlowMultipleIncomingFromSameTarget() {
		Activity activity = ActivityFactory.createActivity("testActionControlFlowMultipleIncomingFromSameTarget");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class3");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, create1, create2);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, create1, create2);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, create1, create3);
				
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(3, activityExecution.getNodeExecutions().size());
						
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(0, nodeExecution_create1.getInputs().size());					
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_cflow_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_cflow_create1.getOutputPin());
		assertEquals(1, output_cflow_create1.getTokens().size());
		assertTrue(output_cflow_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_cflow_create1.getTokens().get(0);
		
		// Create Object Action 2
		int index_create2 = getExecutionOrderIndex(eventlist, create2);
		
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(index_create2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_create2);	
				
		assertEquals(1, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		// Create Object Action 3
		int index_create3 = getExecutionOrderIndex(eventlist, create3);
		
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(index_create3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(1, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);

		assertEquals(ctoken_output_create1, ctoken_input_create3);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_create1.getChronologicalPredecessor());
		
		if(index_create2 < index_create3) {
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(nodeExecution_create3, nodeExecution_create2.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create3.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create3.getChronologicalSuccessor());
		} else {
			assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create1, nodeExecution_create3.getChronologicalPredecessor());
			assertEquals(nodeExecution_create3, nodeExecution_create3.getChronologicalSuccessor());
			
			assertEquals(nodeExecution_create2, nodeExecution_create2.getChronologicalPredecessor());
			assertEquals(null, nodeExecution_create2.getChronologicalSuccessor());
		}
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		assertTrue(logicalSuccessor.contains(nodeExecution_create3));
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		// Traversed edges
		assertEquals(ctoken_output_create1,  ctoken_input_create2);
		assertEquals(ctoken_output_create1,  ctoken_input_create3);
		assertEquals(3, ctoken_output_create1.getTraversedEdges().size());
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow1));
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow2));		
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow3));
	}
	
	@Test
	public void testActionControlFlowMultipleIncomingFromDifferentTargets() {
		Activity activity = ActivityFactory.createActivity("testActionControlFlowMultipleIncomingFromDifferentTargets");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class3");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, create1, create2);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, create1, create3);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, create2, create3);
				
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(3, activityExecution.getNodeExecutions().size());
						
		// Create Object Action 1
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(0, nodeExecution_create1.getInputs().size());					
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_cflow_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_cflow_create1.getOutputPin());
		assertEquals(1, output_cflow_create1.getTokens().size());
		assertTrue(output_cflow_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_cflow_create1.getTokens().get(0);
		
		// Create Object Action 2
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_create2);	
				
		assertEquals(2, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		Output output_cflow_create2 = nodeExecution_create2.getOutputs().get(1);
		assertNull(output_cflow_create2.getOutputPin());
		assertEquals(1, output_cflow_create2.getTokens().size());
		assertTrue(output_cflow_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create2 = (ControlTokenInstance)output_cflow_create2.getTokens().get(0);
		
		// Create Object Action 3
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(2, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		assertTrue(input_ctrl_create3.getTokens().get(1) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken1_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);
		ControlTokenInstance ctoken2_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(1);
		
		ControlTokenInstance ctoken_input_create3_fromCreate1 = null;
		ControlTokenInstance ctoken_input_create3_fromCreate2 = null;
		if(ctoken1_input_create3.equals(ctoken_output_create1)) {
			ctoken_input_create3_fromCreate1 = ctoken1_input_create3;
		} else if (ctoken1_input_create3.equals(ctoken_output_create2)) {
			ctoken_input_create3_fromCreate2 = ctoken1_input_create3;
		}
		if(ctoken2_input_create3.equals(ctoken_output_create1)) {
			ctoken_input_create3_fromCreate1 = ctoken2_input_create3;
		} else if (ctoken2_input_create3.equals(ctoken_output_create2)) {
			ctoken_input_create3_fromCreate2 = ctoken2_input_create3;
		}
		assertNotNull(ctoken_input_create3_fromCreate1);
		assertNotNull(ctoken_input_create3_fromCreate2);
		assertFalse(ctoken_input_create3_fromCreate1.equals(ctoken_input_create3_fromCreate2));
		
		assertEquals(ctoken_output_create1, ctoken_input_create3_fromCreate1);	
		assertEquals(ctoken_output_create2, ctoken_input_create3_fromCreate2);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, nodeExecution_create1.getChronologicalPredecessor());
		assertEquals(nodeExecution_create2, nodeExecution_create1.getChronologicalSuccessor());

		assertEquals(nodeExecution_create1, nodeExecution_create2.getChronologicalPredecessor());
		assertEquals(nodeExecution_create3, nodeExecution_create2.getChronologicalSuccessor());

		assertEquals(nodeExecution_create2, nodeExecution_create3.getChronologicalPredecessor());
		assertEquals(null, nodeExecution_create3.getChronologicalSuccessor());
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		assertTrue(logicalSuccessor.contains(nodeExecution_create3));
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_create3, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_create1));
		assertTrue(logicalPredecessor.contains(nodeExecution_create2));
		assertEquals(0, logicalSuccessor.size());
		
		// Traversed edges
		assertEquals(ctoken_output_create1, ctoken_input_create2);
		assertEquals(ctoken_output_create1, ctoken_input_create3_fromCreate1);
		assertEquals(2, ctoken_output_create1.getTraversedEdges().size());
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow1));
		assertTrue(ctoken_output_create1.getTraversedEdges().contains(cflow2));		
		
		assertEquals(ctoken_output_create2,  ctoken_input_create3_fromCreate2);
		assertEquals(1, ctoken_output_create2.getTraversedEdges().size());
		assertTrue(ctoken_output_create2.getTraversedEdges().contains(cflow3));
	}
	
	@Test
	public void testActionDataFlowMultipleOutgoingEdges() {
		Activity activity = ActivityFactory.createActivity("testActionDataFlowMultipleOutgoingEdges");
		Class_ class_ = ActivityFactory.createClass("class");	
		Property property = ActivityFactory.createProperty("property", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity, "create", class_);		
		ValueSpecificationAction vspec1 = ActivityFactory.createValueSpecificationAction(activity, "vspec1", "str1");
		ValueSpecificationAction vspec2 = ActivityFactory.createValueSpecificationAction(activity, "vspec2", "str2");
		AddStructuralFeatureValueAction add1 = ActivityFactory.createAddStructuralFeatureValueAction(activity, "add1", property, false);
		AddStructuralFeatureValueAction add2 = ActivityFactory.createAddStructuralFeatureValueAction(activity, "add2", property, false);
	
		ObjectFlow oflow1 = ActivityFactory.createObjectFlow(activity, create.result, add1.object);
		ObjectFlow oflow2 = ActivityFactory.createObjectFlow(activity, create.result, add2.object);
		ObjectFlow oflow3 = ActivityFactory.createObjectFlow(activity, vspec1.result, add1.value);
		ObjectFlow oflow4 = ActivityFactory.createObjectFlow(activity, vspec2.result, add2.value);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(4, activityExecution.getNodeExecutions().size());
						
		// Create Object Action
		int index_create = getExecutionOrderIndex(eventlist, create);
		
		ActivityNodeExecution nodeExecution_create = activityExecution.getNodeExecutions().get(index_create);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create).size());		
		assertEquals(nodeExecution_create, activityExecution.getNodeExecutionsByNode(create).get(0));
		
		assertEquals(create, nodeExecution_create.getNode());
		assertEquals(activityExecution, nodeExecution_create.getActivityExecution());
		
		assertEquals(0, nodeExecution_create.getInputs().size());					
		
		assertEquals(1, nodeExecution_create.getOutputs().size());
		
		Output output_oflow_create = nodeExecution_create.getOutputs().get(0);
		assertEquals(create.result, output_oflow_create.getOutputPin());
		assertEquals(1, output_oflow_create.getTokens().size());
		assertTrue(output_oflow_create.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create = (ObjectTokenInstance)output_oflow_create.getTokens().get(0);
		assertTrue(otoken_output_create.getValue().getValue() instanceof Object_);
		Object_ obj_output_create = (Object_)otoken_output_create.getValue().getValue();
		assertEquals(class_, obj_output_create.types.get(0));
				
		// Value Specification Action 1
		int index_vspec1 = getExecutionOrderIndex(eventlist, vspec1);
		
		ActivityNodeExecution nodeExecution_vspec1 = activityExecution.getNodeExecutions().get(index_vspec1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec1).size());		
		assertEquals(nodeExecution_vspec1, activityExecution.getNodeExecutionsByNode(vspec1).get(0));

		assertEquals(vspec1, nodeExecution_vspec1.getNode());
		assertEquals(activityExecution, nodeExecution_vspec1.getActivityExecution());

		assertEquals(0, nodeExecution_vspec1.getInputs().size());
						
		assertEquals(1, nodeExecution_vspec1.getOutputs().size());
		
		Output output_oflow_vspec1 = nodeExecution_vspec1.getOutputs().get(0);
		assertEquals(vspec1.result, output_oflow_vspec1.getOutputPin());
		assertEquals(1, output_oflow_vspec1.getTokens().size());
		assertTrue(output_oflow_vspec1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec1 = (ObjectTokenInstance)output_oflow_vspec1.getTokens().get(0);
		assertTrue(otoken_output_vspec1.getValue().getValue() instanceof StringValue);
		StringValue str_output_vspec1 = (StringValue)otoken_output_vspec1.getValue().getValue();
		assertEquals("str1", str_output_vspec1.value);
	
		// Value Specification Action 2
		int index_vspec2 = getExecutionOrderIndex(eventlist, vspec2);

		ActivityNodeExecution nodeExecution_vspec2 = activityExecution.getNodeExecutions().get(index_vspec2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec2).size());		
		assertEquals(nodeExecution_vspec2, activityExecution.getNodeExecutionsByNode(vspec2).get(0));

		assertEquals(vspec2, nodeExecution_vspec2.getNode());
		assertEquals(activityExecution, nodeExecution_vspec2.getActivityExecution());

		assertEquals(0, nodeExecution_vspec2.getInputs().size());

		assertEquals(1, nodeExecution_vspec2.getOutputs().size());

		Output output_oflow_vspec2 = nodeExecution_vspec2.getOutputs().get(0);
		assertEquals(vspec2.result, output_oflow_vspec2.getOutputPin());
		assertEquals(1, output_oflow_vspec2.getTokens().size());
		assertTrue(output_oflow_vspec2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec2 = (ObjectTokenInstance)output_oflow_vspec2.getTokens().get(0);
		assertTrue(otoken_output_vspec2.getValue().getValue() instanceof StringValue);
		StringValue str_output_vspec2 = (StringValue)otoken_output_vspec2.getValue().getValue();
		assertEquals("str2", str_output_vspec2.value);
		
		// Add Structural Feature Value Action
		int index_add1 = getExecutionOrderIndex(eventlist, add1);
		int index_add2 = getExecutionOrderIndex(eventlist, add2);
		
		assertTrue((index_add1 == -1 && index_add2 > -1) || (index_add1 > -1 && index_add2 == -1));
				
		ActivityNodeExecution nodeExecution_add = null;
		AddStructuralFeatureValueAction add = null;
		boolean isAdd1 = false;
		if(index_add1 > -1) {
			isAdd1 = true;
			add = add1;
			nodeExecution_add = activityExecution.getNodeExecutions().get(index_add1);			
			assertEquals(add1, nodeExecution_add.getNode());
		} else {
			add = add2;
			nodeExecution_add = activityExecution.getNodeExecutions().get(index_add2);
			assertEquals(add2, nodeExecution_add.getNode());
		}
					
		assertEquals(activityExecution, nodeExecution_add.getActivityExecution());

		assertEquals(2, nodeExecution_add.getInputs().size());
		
		Input input1_oflow_add = nodeExecution_add.getInputs().get(0);
		Input input2_oflow_add = nodeExecution_add.getInputs().get(1);
		
		Input input_obj_add = null;
		Input input_value_add = null;
		if(input1_oflow_add.getInputPin().equals(add.object)) {
			input_obj_add = input1_oflow_add;
		} else if(input1_oflow_add.getInputPin().equals(add.value)) {
			input_value_add = input1_oflow_add;
		}
		if(input2_oflow_add.getInputPin().equals(add.object)) {
			input_obj_add = input2_oflow_add;
		} else if(input2_oflow_add.getInputPin().equals(add.value)) {
			input_value_add = input2_oflow_add;
		}
		
		assertNotNull(input_obj_add);
		assertNotNull(input_value_add);
				
		assertEquals(1, input_obj_add.getTokens().size());
		assertTrue(input_obj_add.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_obj_add = (ObjectTokenInstance)input_obj_add.getTokens().get(0);
		
		assertEquals(1, input_value_add.getTokens().size());
		assertTrue(input_value_add.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_value_add = (ObjectTokenInstance)input_value_add.getTokens().get(0);
		
		assertEquals(otoken_output_create, otoken_input_obj_add);
		if(isAdd1) {
			assertEquals(otoken_output_vspec1, otoken_input_value_add);
		} else {
			assertEquals(otoken_output_vspec2, otoken_input_value_add);
		}
		
		assertEquals(1, nodeExecution_add.getOutputs().size());
		
		Output output_oflow_add = nodeExecution_add.getOutputs().get(0);
		assertEquals(add.result, output_oflow_add.getOutputPin());
		assertEquals(1, output_oflow_add.getTokens().size());
		assertTrue(output_oflow_add.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_add = (ObjectTokenInstance)output_oflow_add.getTokens().get(0);
		assertTrue(otoken_output_add.getValue().getValue() instanceof Object_);
		Object_ obj_output_add =  (Object_)otoken_output_add.getValue().getValue();
		assertEquals(class_, obj_output_add.types.get(0));
		if(isAdd1) {
			assertEquals("str1",((StringValue)obj_output_add.featureValues.get(0).values.get(0)).value);
		} else {
			assertEquals("str2",((StringValue)obj_output_add.featureValues.get(0).values.get(0)).value);
		}

		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_create.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_create.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_add));

		if(isAdd1) {
			logicalPredecessor = nodeExecution_vspec1.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_vspec1.getLogicalSuccessor();		
			assertEquals(0, logicalPredecessor.size());
			assertEquals(1, logicalSuccessor.size());
			assertEquals(nodeExecution_add, logicalSuccessor.get(0));

			logicalPredecessor = nodeExecution_vspec2.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_vspec2.getLogicalSuccessor();		
			assertEquals(0, logicalPredecessor.size());
			assertEquals(0, logicalSuccessor.size());

			logicalPredecessor = nodeExecution_add.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_add.getLogicalSuccessor();		
			assertEquals(2, logicalPredecessor.size());
			assertTrue(logicalPredecessor.contains(nodeExecution_vspec1));
			assertTrue(logicalPredecessor.contains(nodeExecution_create));
			assertEquals(0, logicalSuccessor.size());
		} else {
			logicalPredecessor = nodeExecution_vspec2.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_vspec2.getLogicalSuccessor();		
			assertEquals(0, logicalPredecessor.size());
			assertEquals(1, logicalSuccessor.size());
			assertEquals(nodeExecution_add, logicalSuccessor.get(0));

			logicalPredecessor = nodeExecution_vspec1.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_vspec1.getLogicalSuccessor();		
			assertEquals(0, logicalPredecessor.size());
			assertEquals(0, logicalSuccessor.size());

			logicalPredecessor = nodeExecution_add.getLogicalPredecessor();
			logicalSuccessor = nodeExecution_add.getLogicalSuccessor();		
			assertEquals(2, logicalPredecessor.size());
			assertTrue(logicalPredecessor.contains(nodeExecution_vspec2));
			assertTrue(logicalPredecessor.contains(nodeExecution_create));
			assertEquals(0, logicalSuccessor.size());
		}		

		// Traversed edges
		assertEquals(otoken_output_create, otoken_input_obj_add);
		assertEquals(1, otoken_output_create.getTraversedEdges().size());
		
		if(isAdd1) {
			assertEquals(oflow1, otoken_output_create.getTraversedEdges().get(0));
			
			assertEquals(otoken_output_vspec1, otoken_input_value_add);
			assertEquals(1, otoken_output_vspec1.getTraversedEdges().size());
			assertEquals(oflow3, otoken_output_vspec1.getTraversedEdges().get(0));
			
		} else {
			assertEquals(oflow2, otoken_output_create.getTraversedEdges().get(0));
			
			assertEquals(otoken_output_vspec2, otoken_input_value_add);
			assertEquals(1, otoken_output_vspec2.getTraversedEdges().size());
			assertEquals(oflow4, otoken_output_vspec2.getTraversedEdges().get(0));
		}
		
		assertEquals(0, otoken_output_add.getTraversedEdges().size());
		assertFalse(obj_output_create.equals(obj_output_add));		
	}
	
	@Test
	public void testActionDataFlowMultipleOutgoingEdgesWithFork() {
		Activity activity = ActivityFactory.createActivity("testActionDataFlowMultipleOutgoingEdgesWithFork");
		Class_ class_ = ActivityFactory.createClass("class");	
		Property property = ActivityFactory.createProperty("property", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity, "create", class_);		
		ValueSpecificationAction vspec1 = ActivityFactory.createValueSpecificationAction(activity, "vspec1", "str1");
		ValueSpecificationAction vspec2 = ActivityFactory.createValueSpecificationAction(activity, "vspec2", "str2");
		AddStructuralFeatureValueAction add1 = ActivityFactory.createAddStructuralFeatureValueAction(activity, "add1", property, false);
		AddStructuralFeatureValueAction add2 = ActivityFactory.createAddStructuralFeatureValueAction(activity, "add2", property, false);
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		
		ObjectFlow oflow1 = ActivityFactory.createObjectFlow(activity, create.result, fork);		
		ObjectFlow oflow2 = ActivityFactory.createObjectFlow(activity, fork, add1.object);
		ObjectFlow oflow3 = ActivityFactory.createObjectFlow(activity, fork, add2.object);		
		ObjectFlow oflow4 = ActivityFactory.createObjectFlow(activity, vspec1.result, add1.value);
		ObjectFlow oflow5 = ActivityFactory.createObjectFlow(activity, vspec2.result, add2.value);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(6, activityExecution.getNodeExecutions().size());
						
		// Create Object Action
		int index_create = getExecutionOrderIndex(eventlist, create);
		
		ActivityNodeExecution nodeExecution_create = activityExecution.getNodeExecutions().get(index_create);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create).size());		
		assertEquals(nodeExecution_create, activityExecution.getNodeExecutionsByNode(create).get(0));
		
		assertEquals(create, nodeExecution_create.getNode());
		assertEquals(activityExecution, nodeExecution_create.getActivityExecution());
		
		assertEquals(0, nodeExecution_create.getInputs().size());					
		
		assertEquals(1, nodeExecution_create.getOutputs().size());
		
		Output output_oflow_create = nodeExecution_create.getOutputs().get(0);
		assertEquals(create.result, output_oflow_create.getOutputPin());
		assertEquals(1, output_oflow_create.getTokens().size());
		assertTrue(output_oflow_create.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create = (ObjectTokenInstance)output_oflow_create.getTokens().get(0);
		assertTrue(otoken_output_create.getValue().getValue() instanceof Object_);
		Object_ obj_output_create = (Object_)otoken_output_create.getValue().getValue();		

		// Fork
		int index_fork = getExecutionOrderIndex(eventlist, fork);
		
		ActivityNodeExecution nodeExecution_fork = activityExecution.getNodeExecutions().get(index_fork);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(fork).size());		
		assertEquals(nodeExecution_fork, activityExecution.getNodeExecutionsByNode(fork).get(0));
		
		assertEquals(fork, nodeExecution_fork.getNode());
		assertEquals(activityExecution, nodeExecution_fork.getActivityExecution());
		
		assertEquals(1, nodeExecution_fork.getInputs().size());					
		
		Input input_oflow_fork = nodeExecution_fork.getInputs().get(0);
		assertEquals(1, input_oflow_fork.getTokens().size());
		assertTrue(input_oflow_fork.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_fork = (ObjectTokenInstance)input_oflow_fork.getTokens().get(0);
		
		assertEquals(1, nodeExecution_fork.getOutputs().size());
		
		Output output_oflow_fork = nodeExecution_fork.getOutputs().get(0);
		assertNull(output_oflow_fork.getOutputPin());
		assertEquals(1, output_oflow_fork.getTokens().size());
		assertTrue(output_oflow_fork.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_fork = (ObjectTokenInstance)output_oflow_fork.getTokens().get(0);
		assertTrue(otoken_output_fork.getValue().getValue() instanceof Object_);
		Object_ obj_output_fork = (Object_)otoken_output_fork.getValue().getValue();
		assertEquals(class_, obj_output_fork.types.get(0));
		
		// Value Specification Action 1
		int index_vspec1 = getExecutionOrderIndex(eventlist, vspec1);
		
		ActivityNodeExecution nodeExecution_vspec1 = activityExecution.getNodeExecutions().get(index_vspec1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec1).size());		
		assertEquals(nodeExecution_vspec1, activityExecution.getNodeExecutionsByNode(vspec1).get(0));

		assertEquals(vspec1, nodeExecution_vspec1.getNode());
		assertEquals(activityExecution, nodeExecution_vspec1.getActivityExecution());

		assertEquals(0, nodeExecution_vspec1.getInputs().size());
						
		assertEquals(1, nodeExecution_vspec1.getOutputs().size());
		
		Output output_oflow_vspec1 = nodeExecution_vspec1.getOutputs().get(0);
		assertEquals(vspec1.result, output_oflow_vspec1.getOutputPin());
		assertEquals(1, output_oflow_vspec1.getTokens().size());
		assertTrue(output_oflow_vspec1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec1 = (ObjectTokenInstance)output_oflow_vspec1.getTokens().get(0);
		assertTrue(otoken_output_vspec1.getValue().getValue() instanceof StringValue);
		StringValue str_output_vspec1 = (StringValue)otoken_output_vspec1.getValue().getValue();
		assertEquals("str1", str_output_vspec1.value);
	
		// Value Specification Action 2
		int index_vspec2 = getExecutionOrderIndex(eventlist, vspec2);

		ActivityNodeExecution nodeExecution_vspec2 = activityExecution.getNodeExecutions().get(index_vspec2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec2).size());		
		assertEquals(nodeExecution_vspec2, activityExecution.getNodeExecutionsByNode(vspec2).get(0));

		assertEquals(vspec2, nodeExecution_vspec2.getNode());
		assertEquals(activityExecution, nodeExecution_vspec2.getActivityExecution());

		assertEquals(0, nodeExecution_vspec2.getInputs().size());

		assertEquals(1, nodeExecution_vspec2.getOutputs().size());

		Output output_oflow_vspec2 = nodeExecution_vspec2.getOutputs().get(0);
		assertEquals(vspec2.result, output_oflow_vspec2.getOutputPin());
		assertEquals(1, output_oflow_vspec2.getTokens().size());
		assertTrue(output_oflow_vspec2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec2 = (ObjectTokenInstance)output_oflow_vspec2.getTokens().get(0);
		assertTrue(otoken_output_vspec2.getValue().getValue() instanceof StringValue);
		StringValue str_output_vspec2 = (StringValue)otoken_output_vspec2.getValue().getValue();
		assertEquals("str2", str_output_vspec2.value);
		
		// Add Structural Feature Value Action 1
		int index_add1 = getExecutionOrderIndex(eventlist, add1);
		ActivityNodeExecution nodeExecution_add1 = activityExecution.getNodeExecutions().get(index_add1);
		
		assertEquals(add1, nodeExecution_add1.getNode());
		assertEquals(activityExecution, nodeExecution_add1.getActivityExecution());
		
		assertEquals(2, nodeExecution_add1.getInputs().size());
		
		Input input1_oflow_add1 = nodeExecution_add1.getInputs().get(0);
		Input input2_oflow_add1 = nodeExecution_add1.getInputs().get(1);
		
		Input input_obj_add1 = null;
		Input input_value_add1 = null;
		if(input1_oflow_add1.getInputPin().equals(add1.object)) {
			input_obj_add1 = input1_oflow_add1;
		} else if(input1_oflow_add1.getInputPin().equals(add1.value)) {
			input_value_add1 = input1_oflow_add1;
		}
		if(input2_oflow_add1.getInputPin().equals(add1.object)) {
			input_obj_add1 = input2_oflow_add1;
		} else if(input2_oflow_add1.getInputPin().equals(add1.value)) {
			input_value_add1 = input2_oflow_add1;
		}
		
		assertNotNull(input_obj_add1);
		assertNotNull(input_value_add1);
				
		assertEquals(1, input_obj_add1.getTokens().size());
		assertTrue(input_obj_add1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_obj_add1 = (ObjectTokenInstance)input_obj_add1.getTokens().get(0);
		
		assertEquals(1, input_value_add1.getTokens().size());
		assertTrue(input_value_add1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_value_add1 = (ObjectTokenInstance)input_value_add1.getTokens().get(0);
		
		assertEquals(otoken_output_fork, otoken_input_obj_add1);
		assertEquals(otoken_output_vspec1, otoken_input_value_add1);
		
		assertEquals(1, nodeExecution_add1.getOutputs().size());
		
		Output output_oflow_add1 = nodeExecution_add1.getOutputs().get(0);
		assertEquals(add1.result, output_oflow_add1.getOutputPin());
		assertEquals(1, output_oflow_add1.getTokens().size());
		assertTrue(output_oflow_add1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_add1 = (ObjectTokenInstance)output_oflow_add1.getTokens().get(0);
		assertTrue(otoken_output_add1.getValue().getValue() instanceof Object_);
		Object_ obj_output_add1 =  (Object_)otoken_output_add1.getValue().getValue();
		assertEquals(class_, obj_output_add1.types.get(0));
		assertEquals("str1",((StringValue)obj_output_add1.featureValues.get(0).values.get(0)).value);
		
		// Add Structural Feature Value Action 2		
		int index_add2 = getExecutionOrderIndex(eventlist, add2);
		ActivityNodeExecution nodeExecution_add2 = activityExecution.getNodeExecutions().get(index_add2);
		
		assertEquals(add2, nodeExecution_add2.getNode());
		assertEquals(activityExecution, nodeExecution_add2.getActivityExecution());

		assertEquals(2, nodeExecution_add2.getInputs().size());
		
		Input input1_oflow_add2 = nodeExecution_add2.getInputs().get(0);
		Input input2_oflow_add2 = nodeExecution_add2.getInputs().get(1);
		
		Input input_obj_add2 = null;
		Input input_value_add2 = null;
		if(input1_oflow_add2.getInputPin().equals(add2.object)) {
			input_obj_add2 = input1_oflow_add2;
		} else if(input1_oflow_add2.getInputPin().equals(add2.value)) {
			input_value_add2 = input1_oflow_add2;
		}
		if(input2_oflow_add2.getInputPin().equals(add2.object)) {
			input_obj_add2 = input2_oflow_add2;
		} else if(input2_oflow_add2.getInputPin().equals(add2.value)) {
			input_value_add2 = input2_oflow_add2;
		}
		
		assertNotNull(input_obj_add2);
		assertNotNull(input_value_add2);
				
		assertEquals(1, input_obj_add2.getTokens().size());
		assertTrue(input_obj_add2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_obj_add2 = (ObjectTokenInstance)input_obj_add2.getTokens().get(0);
		
		assertEquals(1, input_value_add2.getTokens().size());
		assertTrue(input_value_add2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_value_add2 = (ObjectTokenInstance)input_value_add2.getTokens().get(0);
		
		assertEquals(otoken_output_fork, otoken_input_obj_add2);
		assertEquals(otoken_output_vspec2, otoken_input_value_add2);
		
		assertEquals(1, nodeExecution_add1.getOutputs().size());
		
		Output output_oflow_add2 = nodeExecution_add2.getOutputs().get(0);
		assertEquals(add2.result, output_oflow_add2.getOutputPin());
		assertEquals(1, output_oflow_add2.getTokens().size());
		assertTrue(output_oflow_add2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_add2 = (ObjectTokenInstance)output_oflow_add2.getTokens().get(0);
		assertTrue(otoken_output_add2.getValue().getValue() instanceof Object_);
		Object_ obj_output_add2 =  (Object_)otoken_output_add2.getValue().getValue();
		assertEquals(class_, obj_output_add2.types.get(0));
		assertEquals("str2",((StringValue)obj_output_add2.featureValues.get(0).values.get(0)).value);
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_create.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_create.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_fork));

		logicalPredecessor = nodeExecution_fork.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_fork.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_create));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_add1));
		assertTrue(logicalSuccessor.contains(nodeExecution_add2));
		
		logicalPredecessor = nodeExecution_vspec1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_vspec1.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_add1, logicalSuccessor.get(0));

		logicalPredecessor = nodeExecution_vspec2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_vspec2.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_add2, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_add1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_add1.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_vspec1));
		assertTrue(logicalPredecessor.contains(nodeExecution_fork));
		assertEquals(0, logicalSuccessor.size());

		logicalPredecessor = nodeExecution_add2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_add2.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_vspec2));
		assertTrue(logicalPredecessor.contains(nodeExecution_fork));
		assertEquals(0, logicalSuccessor.size());

		// Traversed edges
		assertEquals(otoken_output_create, otoken_input_fork);
		assertEquals(class_, obj_output_create.types.get(0));
		assertEquals(0, obj_output_create.featureValues.get(0).values.size());
		assertEquals(1, otoken_output_create.getTraversedEdges().size());
		assertEquals(oflow1, otoken_output_create.getTraversedEdges().get(0));
		
		assertEquals(otoken_output_fork, otoken_input_obj_add1);
		assertEquals(otoken_output_fork, otoken_input_obj_add2);
		assertFalse(otoken_output_fork.equals(otoken_output_create));
		assertEquals(class_, obj_output_fork.types.get(0));
		assertEquals(0, obj_output_fork.featureValues.get(0).values.size());				
		assertEquals(2, otoken_output_fork.getTraversedEdges().size());
		assertTrue(otoken_output_fork.getTraversedEdges().contains(oflow2));
		assertTrue(otoken_output_fork.getTraversedEdges().contains(oflow3));
				
		assertEquals(otoken_output_vspec1, otoken_input_value_add1);
		assertEquals("str1", str_output_vspec1.value);
		assertEquals(1, otoken_output_vspec1.getTraversedEdges().size());
		assertEquals(oflow4, otoken_output_vspec1.getTraversedEdges().get(0));			

		assertEquals(otoken_output_vspec2, otoken_input_value_add2);
		assertEquals("str2", str_output_vspec2.value);
		assertEquals(1, otoken_output_vspec2.getTraversedEdges().size());
		assertEquals(oflow5, otoken_output_vspec2.getTraversedEdges().get(0));
		
		assertEquals(0, otoken_output_add1.getTraversedEdges().size());
		assertFalse(obj_output_fork.equals(obj_output_add1));		
		assertEquals(class_, obj_output_add1.types.get(0));
		
		assertEquals(0, otoken_output_add2.getTraversedEdges().size());
		assertFalse(obj_output_fork.equals(obj_output_add2));		
		assertEquals(class_, obj_output_add2.types.get(0));			
		
		if(index_add1 < index_add2) {
			assertEquals(1, obj_output_add1.featureValues.get(0).values.size());
			assertEquals("str1", ((StringValue)obj_output_add1.featureValues.get(0).values.get(0)).value);
			
			assertEquals(2, obj_output_add2.featureValues.get(0).values.size());
			assertEquals("str2", ((StringValue)obj_output_add2.featureValues.get(0).values.get(0)).value);
			assertEquals("str1", ((StringValue)obj_output_add2.featureValues.get(0).values.get(1)).value);
		} else {
			assertEquals(1, obj_output_add2.featureValues.get(0).values.size());
			assertEquals("str2", ((StringValue)obj_output_add2.featureValues.get(0).values.get(0)).value);
			
			assertEquals(2, obj_output_add2.featureValues.get(0).values.size());
			assertEquals("str1", ((StringValue)obj_output_add1.featureValues.get(0).values.get(0)).value);
			assertEquals("str2", ((StringValue)obj_output_add1.featureValues.get(0).values.get(1)).value);
		}
	}

	@Test
	public void testJoinNodeControlFlow() {
		Activity activity = ActivityFactory.createActivity("testForkNodeControlFlow");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial node");
		ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
		JoinNode join = ActivityFactory.createJoinNode(activity, "join");
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class2");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, initial, fork);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, fork, create1);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, fork, create2);
		ControlFlow cflow4 = ActivityFactory.createControlFlow(activity, create1, join);
		ControlFlow cflow5 = ActivityFactory.createControlFlow(activity, create2, join);
		ControlFlow cflow6 = ActivityFactory.createControlFlow(activity, join, create3);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(6, activityExecution.getNodeExecutions().size());
					
		// Initial Node
		ActivityNodeExecution nodeExecution_initial = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_initial, activityExecution.getNodeExecutionsByNode(initial).get(0));
		
		assertEquals(initial, nodeExecution_initial.getNode());
		assertEquals(activityExecution, nodeExecution_initial.getActivityExecution());
		
		assertEquals(0, nodeExecution_initial.getInputs().size());					
		
		assertEquals(1, nodeExecution_initial.getOutputs().size());
		
		Output output_ctrl_initial = nodeExecution_initial.getOutputs().get(0);
		assertNull(output_ctrl_initial.getOutputPin());
		assertEquals(1, output_ctrl_initial.getTokens().size());
		assertTrue(output_ctrl_initial.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initial = (ControlTokenInstance)output_ctrl_initial.getTokens().get(0);
		
		// Fork Node
		ActivityNodeExecution nodeExecution_fork = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(fork).size());		
		assertEquals(nodeExecution_fork, activityExecution.getNodeExecutionsByNode(fork).get(0));
		
		assertEquals(fork, nodeExecution_fork.getNode());
		assertEquals(activityExecution, nodeExecution_fork.getActivityExecution());
		
		assertEquals(1, nodeExecution_fork.getInputs().size());		
		
		Input input_ctrl_fork = nodeExecution_fork.getInputs().get(0);
		assertNull(input_ctrl_fork.getInputPin());
		assertEquals(1, input_ctrl_fork.getTokens().size());
		assertTrue(input_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_fork = (ControlTokenInstance)input_ctrl_fork.getTokens().get(0);		
		assertEquals(ctoken_output_initial, ctoken_input_fork);				
		
		assertEquals(1, nodeExecution_fork.getOutputs().size());
		
		Output output_ctrl_fork = nodeExecution_fork.getOutputs().get(0);
		assertNull(output_ctrl_fork.getOutputPin());
		assertEquals(1, output_ctrl_fork.getTokens().size());
		assertTrue(output_ctrl_fork.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_fork = (ControlTokenInstance)output_ctrl_fork.getTokens().get(0);
		
		// Create Object Action 1
		int executionorder_create1 = getExecutionOrderIndex(eventlist, create1);		
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(executionorder_create1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create1 = (ControlTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(ctoken_output_fork, ctoken_input_create1);	
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_ctrl_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_ctrl_create1.getOutputPin());
		assertEquals(1, output_ctrl_create1.getTokens().size());
		assertTrue(output_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_ctrl_create1.getTokens().get(0);
		
		// Create Object Action 2
		int executionorder_create2 = getExecutionOrderIndex(eventlist, create2);
		ActivityNodeExecution nodeExecution_create2 = activityExecution.getNodeExecutions().get(executionorder_create2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create2).size());		
		assertEquals(nodeExecution_create2, activityExecution.getNodeExecutionsByNode(create2).get(0));

		assertEquals(create2, nodeExecution_create2.getNode());
		assertEquals(activityExecution, nodeExecution_create2.getActivityExecution());

		assertEquals(1, nodeExecution_create2.getInputs().size());
		
		Input input_ctrl_create2 = nodeExecution_create2.getInputs().get(0);
		assertNull(input_ctrl_create2.getInputPin());
		assertEquals(1, input_ctrl_create2.getTokens().size());
		assertTrue(input_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create2 = (ControlTokenInstance)input_ctrl_create2.getTokens().get(0);
		assertEquals(ctoken_output_fork, ctoken_input_create2);	
				
		assertEquals(2, nodeExecution_create2.getOutputs().size());
		
		Output output_oflow_create2 = nodeExecution_create2.getOutputs().get(0);
		assertEquals(create2.result, output_oflow_create2.getOutputPin());
		assertEquals(1, output_oflow_create2.getTokens().size());
		assertTrue(output_oflow_create2.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create2 = (ObjectTokenInstance)output_oflow_create2.getTokens().get(0);
		assertTrue(otoken_output_create2.getValue().getValue() instanceof Object_);
		assertEquals(class2, ((Object_)otoken_output_create2.getValue().getValue()).types.get(0));
		
		Output output_ctrl_create2 = nodeExecution_create2.getOutputs().get(1);
		assertNull(output_ctrl_create2.getOutputPin());
		assertEquals(1, output_ctrl_create2.getTokens().size());
		assertTrue(output_ctrl_create2.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create2 = (ControlTokenInstance)output_ctrl_create2.getTokens().get(0);
		
		// Join Node
		ActivityNodeExecution nodeExecution_join = activityExecution.getNodeExecutions().get(4);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(join).size());		
		assertEquals(nodeExecution_join, activityExecution.getNodeExecutionsByNode(join).get(0));

		assertEquals(join, nodeExecution_join.getNode());
		assertEquals(activityExecution, nodeExecution_join.getActivityExecution());

		assertEquals(1, nodeExecution_join.getInputs().size());

		Input input_ctrl_join = nodeExecution_join.getInputs().get(0);
		assertNull(input_ctrl_join.getInputPin());
		assertEquals(2, input_ctrl_join.getTokens().size());
		assertTrue(input_ctrl_join.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken1_input_join = (ControlTokenInstance)input_ctrl_join.getTokens().get(0);
		assertTrue(input_ctrl_join.getTokens().get(1) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken2_input_join = (ControlTokenInstance)input_ctrl_join.getTokens().get(1);
		
		assertTrue( (ctoken1_input_join.equals(ctoken_output_create1) && ctoken2_input_join.equals(ctoken_output_create2)) ||
				(ctoken1_input_join.equals(ctoken_output_create2) && ctoken2_input_join.equals(ctoken_output_create1)));
		
		assertEquals(1, nodeExecution_join.getOutputs().size());

		Output output_ctrl_join = nodeExecution_join.getOutputs().get(0);
		assertNull(output_ctrl_join.getOutputPin());
		assertEquals(2, output_ctrl_join.getTokens().size());
		assertTrue(output_ctrl_join.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken1_output_join = (ControlTokenInstance)output_ctrl_join.getTokens().get(0);
		assertTrue(output_ctrl_join.getTokens().get(1) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken2_output_join = (ControlTokenInstance)output_ctrl_join.getTokens().get(1);
		List<ControlTokenInstance> ctokens_output_join = new ArrayList<ControlTokenInstance>();
		ctokens_output_join.add(ctoken1_output_join);
		ctokens_output_join.add(ctoken2_output_join);
		
		// Create Object Action 3
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(5);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(2, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken1_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);
		ControlTokenInstance ctoken2_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(1);
		List<ControlTokenInstance> ctokens_input_create3 = new ArrayList<ControlTokenInstance>();
		ctokens_input_create3.add(ctoken1_input_create3);
		ctokens_input_create3.add(ctoken2_input_create3);
		
		assertTrue(ctokens_output_join.containsAll(ctokens_input_create3));	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));				
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initial.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initial.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_fork, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_fork.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_fork.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initial, logicalPredecessor.get(0));
		assertEquals(2, logicalSuccessor.size());
		assertTrue(logicalSuccessor.contains(nodeExecution_create1));
		assertTrue(logicalSuccessor.contains(nodeExecution_create2));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_join, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create2.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create2.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_fork, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());		
		assertEquals(nodeExecution_join, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_join.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_join.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_create1));
		assertTrue(logicalPredecessor.contains(nodeExecution_create2));
		assertEquals(1, logicalSuccessor.size());		
		assertEquals(nodeExecution_create3, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_join, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Traversed edges
		assertEquals(1, ctoken_output_initial.getTraversedEdges().size());
		assertEquals(cflow1, ctoken_output_initial.getTraversedEdges().get(0));
		
		assertEquals(2, ctoken_output_fork.getTraversedEdges().size());
		assertTrue(ctoken_output_fork.getTraversedEdges().contains(cflow2));
		assertTrue(ctoken_output_fork.getTraversedEdges().contains(cflow3));
		
		assertEquals(1, ctoken_output_create1.getTraversedEdges().size());
		assertEquals(cflow4, ctoken_output_create1.getTraversedEdges().get(0));
		
		assertEquals(1, ctoken_output_create2.getTraversedEdges().size());
		assertEquals(cflow5, ctoken_output_create2.getTraversedEdges().get(0));
		
		assertEquals(1, ctoken1_output_join.getTraversedEdges().size());
		assertEquals(cflow6, ctoken1_output_join.getTraversedEdges().get(0));
		
		assertEquals(1, ctoken2_output_join.getTraversedEdges().size());
		assertEquals(cflow6, ctoken2_output_join.getTraversedEdges().get(0));
	}
	
	@Test
	public void testNodeWithoutOutput() {
		Activity activity = ActivityFactory.createActivity("testNodeWithoutOutput");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial");
		MergeNode merge = ActivityFactory.createMergeNode(activity, "merge");
		ControlFlow cflow1 = ActivityFactory.createControlFlow(activity, initial, merge);

		ExecutionContext.getInstance().execute(activity, null, null);				

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();

		Trace trace = ExecutionContext.getInstance().getTrace(executionID);

		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());

		assertEquals(2, activityExecution.getNodeExecutions().size());

		// Initial Node
		ActivityNodeExecution nodeExecution_initial = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_initial, activityExecution.getNodeExecutionsByNode(initial).get(0));

		assertEquals(initial, nodeExecution_initial.getNode());
		assertEquals(activityExecution, nodeExecution_initial.getActivityExecution());

		assertEquals(0, nodeExecution_initial.getInputs().size());					

		assertEquals(1, nodeExecution_initial.getOutputs().size());

		Output output_ctrl_initial = nodeExecution_initial.getOutputs().get(0);
		assertNull(output_ctrl_initial.getOutputPin());
		assertEquals(1, output_ctrl_initial.getTokens().size());
		assertTrue(output_ctrl_initial.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_initial = (ControlTokenInstance)output_ctrl_initial.getTokens().get(0);

		// Merge Node
		ActivityNodeExecution nodeExecution_merge = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(merge).size());		
		assertEquals(nodeExecution_merge, activityExecution.getNodeExecutionsByNode(merge).get(0));

		assertEquals(merge, nodeExecution_merge.getNode());
		assertEquals(activityExecution, nodeExecution_merge.getActivityExecution());

		assertEquals(1, nodeExecution_merge.getInputs().size());		

		Input input_ctrl_merge = nodeExecution_merge.getInputs().get(0);
		assertNull(input_ctrl_merge.getInputPin());
		assertEquals(1, input_ctrl_merge.getTokens().size());
		assertTrue(input_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_merge = (ControlTokenInstance)input_ctrl_merge.getTokens().get(0);		
		assertEquals(ctoken_output_initial, ctoken_input_merge);				

		assertEquals(1, nodeExecution_merge.getOutputs().size());

		Output output_ctrl_merge = nodeExecution_merge.getOutputs().get(0);
		assertNull(output_ctrl_merge.getOutputPin());
		assertEquals(0, output_ctrl_merge.getTokens().size());
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_initial.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_initial.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_merge, logicalSuccessor.get(0));

		logicalPredecessor = nodeExecution_merge.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_merge.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_initial, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());
		
		// Chronological predecessor / successor relationship
		ActivityNodeExecution chronologicalPredecessor = nodeExecution_initial.getChronologicalPredecessor();
		ActivityNodeExecution chronologicalSuccessor = nodeExecution_initial.getChronologicalSuccessor();		
		assertEquals(null, chronologicalPredecessor);
		assertEquals(nodeExecution_merge, chronologicalSuccessor);

		chronologicalPredecessor = nodeExecution_merge.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_merge.getChronologicalSuccessor();		
		assertEquals(nodeExecution_initial, chronologicalPredecessor);
		assertEquals(null, chronologicalSuccessor);
		
		// Traversed edges
		assertEquals(1, ctoken_output_initial.getTraversedEdges().size());
		assertEquals(cflow1, ctoken_output_initial.getTraversedEdges().get(0));
	}
	
	@Test
	public void testDecisionNodeFlow() {
		Activity activity = ActivityFactory.createActivity("testDecisionNodeFlow");
		ValueSpecificationAction vspec = ActivityFactory.createValueSpecificationAction(activity, "vspec", 1);
		DecisionNode decision = ActivityFactory.createDecisionNode(activity, "decision");
		MergeNode merge = ActivityFactory.createMergeNode(activity, "merge");		
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class2");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ObjectFlow cflow1 = ActivityFactory.createObjectFlow(activity, vspec.result, decision);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, decision, create1, 1);
		ActivityFactory.createControlFlow(activity, decision, create2, 2);
		ControlFlow cflow4 = ActivityFactory.createControlFlow(activity, create1, merge);
		ActivityFactory.createControlFlow(activity, create2, merge);
		ControlFlow cflow6 = ActivityFactory.createControlFlow(activity, merge, create3);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(5, activityExecution.getNodeExecutions().size());
					
		// Value Specification Action
		ActivityNodeExecution nodeExecution_vspec = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec).size());		
		assertEquals(nodeExecution_vspec, activityExecution.getNodeExecutionsByNode(vspec).get(0));
		
		assertEquals(vspec, nodeExecution_vspec.getNode());
		assertEquals(activityExecution, nodeExecution_vspec.getActivityExecution());
		
		assertEquals(0, nodeExecution_vspec.getInputs().size());					
		
		assertEquals(1, nodeExecution_vspec.getOutputs().size());
		
		Output output_oflow_vspec = nodeExecution_vspec.getOutputs().get(0);
		assertEquals(vspec.result, output_oflow_vspec.getOutputPin());
		assertEquals(1, output_oflow_vspec.getTokens().size());
		assertTrue(output_oflow_vspec.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec = (ObjectTokenInstance)output_oflow_vspec.getTokens().get(0);
		assertEquals(1, ((IntegerValue)otoken_output_vspec.getValue().getValue()).value);		
		
		// Decision Node
		ActivityNodeExecution nodeExecution_decision = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(decision).size());		
		assertEquals(nodeExecution_decision, activityExecution.getNodeExecutionsByNode(decision).get(0));
		
		assertEquals(decision, nodeExecution_decision.getNode());
		assertEquals(activityExecution, nodeExecution_decision.getActivityExecution());
		
		assertEquals(1, nodeExecution_decision.getInputs().size());		
		
		Input input_oflow_decision = nodeExecution_decision.getInputs().get(0);
		assertNull(input_oflow_decision.getInputPin());
		assertEquals(1, input_oflow_decision.getTokens().size());
		assertTrue(input_oflow_decision.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_decision = (ObjectTokenInstance)input_oflow_decision.getTokens().get(0);		
		assertEquals(otoken_output_vspec, otoken_input_decision);				
		
		assertEquals(1, nodeExecution_decision.getOutputs().size());
		
		Output output_oflow_decision = nodeExecution_decision.getOutputs().get(0);
		assertNull(output_oflow_decision.getOutputPin());
		assertEquals(1, output_oflow_decision.getTokens().size());
		assertTrue(output_oflow_decision.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_decision = (ObjectTokenInstance)output_oflow_decision.getTokens().get(0);
		
		// Create Object Action 1
		int executionorder_create1 = getExecutionOrderIndex(eventlist, create1);		
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(executionorder_create1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_input_create1 = (ObjectTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(otoken_output_decision, otoken_input_create1);	
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_ctrl_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_ctrl_create1.getOutputPin());
		assertEquals(1, output_ctrl_create1.getTokens().size());
		assertTrue(output_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_ctrl_create1.getTokens().get(0);
				
		// Merge Node
		ActivityNodeExecution nodeExecution_merge = activityExecution.getNodeExecutions().get(3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(merge).size());		
		assertEquals(nodeExecution_merge, activityExecution.getNodeExecutionsByNode(merge).get(0));

		assertEquals(merge, nodeExecution_merge.getNode());
		assertEquals(activityExecution, nodeExecution_merge.getActivityExecution());

		assertEquals(1, nodeExecution_merge.getInputs().size());

		Input input_ctrl_merge = nodeExecution_merge.getInputs().get(0);
		assertNull(input_ctrl_merge.getInputPin());
		assertEquals(1, input_ctrl_merge.getTokens().size());
		assertTrue(input_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_merge = (ControlTokenInstance)input_ctrl_merge.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		
		assertEquals(1, nodeExecution_merge.getOutputs().size());

		Output output_ctrl_merge = nodeExecution_merge.getOutputs().get(0);
		assertNull(output_ctrl_merge.getOutputPin());
		assertEquals(1, output_ctrl_merge.getTokens().size());
		assertTrue(output_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_merge = (ControlTokenInstance)output_ctrl_merge.getTokens().get(0);
		
		// Create Object Action 3
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(4);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(1, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);

		assertEquals(ctoken_output_merge, ctoken_input_create3);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));				
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_vspec.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_vspec.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_decision, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_decision.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_decision.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_vspec, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_create1, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_decision, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_merge, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_merge.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_merge.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());		
		assertEquals(nodeExecution_create3, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_merge, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Chronological predecessor / successor relationship
		ActivityNodeExecution chronologicalPredecessor = nodeExecution_vspec.getChronologicalPredecessor();
		ActivityNodeExecution chronologicalSuccessor = nodeExecution_vspec.getChronologicalSuccessor();
		assertEquals(null, chronologicalPredecessor);
		assertEquals(nodeExecution_decision, chronologicalSuccessor);

		chronologicalPredecessor = nodeExecution_decision.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_decision.getChronologicalSuccessor();
		assertEquals(nodeExecution_vspec, chronologicalPredecessor);
		assertEquals(nodeExecution_create1, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create1.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create1.getChronologicalSuccessor();
		assertEquals(nodeExecution_decision, chronologicalPredecessor);
		assertEquals(nodeExecution_merge, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_merge.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_merge.getChronologicalSuccessor();
		assertEquals(nodeExecution_create1, chronologicalPredecessor);
		assertEquals(nodeExecution_create3, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create3.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create3.getChronologicalSuccessor();
		assertEquals(nodeExecution_merge, chronologicalPredecessor);
		assertEquals(null, chronologicalSuccessor);
		
		// Traversed edges
		assertEquals(otoken_output_vspec, otoken_input_decision);
		assertEquals(1, otoken_output_vspec.getTraversedEdges().size());
		assertEquals(cflow1, otoken_output_vspec.getTraversedEdges().get(0));
		
		assertEquals(otoken_output_decision, otoken_input_create1);
		assertEquals(1, otoken_output_decision.getTraversedEdges().size());
		assertEquals(cflow2, otoken_output_decision.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		assertEquals(1, ctoken_output_create1.getTraversedEdges().size());
		assertEquals(cflow4, ctoken_output_create1.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_merge, ctoken_input_create3);
		assertEquals(1, ctoken_output_merge.getTraversedEdges().size());
		assertEquals(cflow6, ctoken_output_merge.getTraversedEdges().get(0));
		
		assertEquals(0, otoken_output_create1.getTraversedEdges().size());
		assertEquals(0, otoken_output_create3.getTraversedEdges().size());
	}
	
	@Test
	public void testDecisionNodeFlowWith2IncomingEdges() {
		Activity activity = ActivityFactory.createActivity("testDecisionNodeFlowWith2IncomingEdges");
		ValueSpecificationAction vspec = ActivityFactory.createValueSpecificationAction(activity, "vspec", 1);
		DecisionNode decision = ActivityFactory.createDecisionNode(activity, "decision");
		MergeNode merge = ActivityFactory.createMergeNode(activity, "merge");		
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class3");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ObjectFlow cflow1 = ActivityFactory.createObjectFlow(activity, vspec.result, decision);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, decision, create1, 1);
		ActivityFactory.createControlFlow(activity, decision, create2, 2);
		ControlFlow cflow4 = ActivityFactory.createControlFlow(activity, create1, merge);
		ActivityFactory.createControlFlow(activity, create2, merge);
		ControlFlow cflow6 = ActivityFactory.createControlFlow(activity, merge, create3);
		ControlFlow cflow7 = ActivityFactory.createControlFlow(activity, vspec, decision);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(5, activityExecution.getNodeExecutions().size());
					
		// Value Specification Action
		ActivityNodeExecution nodeExecution_vspec = activityExecution.getNodeExecutions().get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec).size());		
		assertEquals(nodeExecution_vspec, activityExecution.getNodeExecutionsByNode(vspec).get(0));
		
		assertEquals(vspec, nodeExecution_vspec.getNode());
		assertEquals(activityExecution, nodeExecution_vspec.getActivityExecution());
		
		assertEquals(0, nodeExecution_vspec.getInputs().size());					
		
		assertEquals(2, nodeExecution_vspec.getOutputs().size());
		
		Output output_oflow_vspec = nodeExecution_vspec.getOutputs().get(0);
		assertEquals(vspec.result, output_oflow_vspec.getOutputPin());
		assertEquals(1, output_oflow_vspec.getTokens().size());
		assertTrue(output_oflow_vspec.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec = (ObjectTokenInstance)output_oflow_vspec.getTokens().get(0);
		assertEquals(1, ((IntegerValue)otoken_output_vspec.getValue().getValue()).value);	
		
		Output output_cflow_vspec = nodeExecution_vspec.getOutputs().get(1);
		assertNull(output_cflow_vspec.getOutputPin());
		assertEquals(1, output_cflow_vspec.getTokens().size());
		assertTrue(output_cflow_vspec.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_vspec = (ControlTokenInstance)output_cflow_vspec.getTokens().get(0);		
		
		// Decision Node
		ActivityNodeExecution nodeExecution_decision = activityExecution.getNodeExecutions().get(1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(decision).size());		
		assertEquals(nodeExecution_decision, activityExecution.getNodeExecutionsByNode(decision).get(0));
		
		assertEquals(decision, nodeExecution_decision.getNode());
		assertEquals(activityExecution, nodeExecution_decision.getActivityExecution());
		
		assertEquals(1, nodeExecution_decision.getInputs().size());		

		
		Input input_decision = nodeExecution_decision.getInputs().get(0);
		assertNull(input_decision.getInputPin());
		assertEquals(2, input_decision.getTokens().size());
		
		ObjectTokenInstance otoken_input_decision = null;
		ControlTokenInstance ctoken_input_decision = null;		
		if(input_decision.getTokens().get(0) instanceof ObjectTokenInstance) {
			otoken_input_decision = (ObjectTokenInstance)input_decision.getTokens().get(0); 
		} else {
			ctoken_input_decision = (ControlTokenInstance)input_decision.getTokens().get(0); 
		}
		if(input_decision.getTokens().get(1) instanceof ObjectTokenInstance) {
			otoken_input_decision = (ObjectTokenInstance)input_decision.getTokens().get(1); 
		} else {
			ctoken_input_decision = (ControlTokenInstance)input_decision.getTokens().get(1); 
		}
		assertNotNull(otoken_input_decision);
		assertNotNull(ctoken_input_decision);
		
		assertEquals(otoken_output_vspec, otoken_input_decision);				
		assertEquals(ctoken_output_vspec, ctoken_input_decision);
		
		assertEquals(1, nodeExecution_decision.getOutputs().size());
		
		Output output_oflow_decision = nodeExecution_decision.getOutputs().get(0);
		assertNull(output_oflow_decision.getOutputPin());
		assertEquals(2, output_oflow_decision.getTokens().size());
		
		ObjectTokenInstance otoken_output_decision = null;
		ControlTokenInstance ctoken_output_decision = null;
		
		if(output_oflow_decision.getTokens().get(0) instanceof ObjectTokenInstance) {
			otoken_output_decision = (ObjectTokenInstance)output_oflow_decision.getTokens().get(0);
			ctoken_output_decision = (ControlTokenInstance)output_oflow_decision.getTokens().get(1);
		} else {
			otoken_output_decision = (ObjectTokenInstance)output_oflow_decision.getTokens().get(1);
			ctoken_output_decision = (ControlTokenInstance)output_oflow_decision.getTokens().get(0);
		}
		
		// Create Object Action 1
		int executionorder_create1 = getExecutionOrderIndex(eventlist, create1);		
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(executionorder_create1);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());					
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(2, input_ctrl_create1.getTokens().size());
		
		ObjectTokenInstance otoken_input_create1 = null;
		ControlTokenInstance ctoken_input_create1 = null;		
		if(input_ctrl_create1.getTokens().get(0) instanceof ObjectTokenInstance) {
			otoken_input_create1 = (ObjectTokenInstance) input_ctrl_create1.getTokens().get(0);
			ctoken_input_create1 = (ControlTokenInstance) input_ctrl_create1.getTokens().get(1);
		} else {
			otoken_input_create1 = (ObjectTokenInstance) input_ctrl_create1.getTokens().get(1);
			ctoken_input_create1 = (ControlTokenInstance) input_ctrl_create1.getTokens().get(0);
		}

		assertEquals(otoken_output_decision, otoken_input_create1);	
		assertEquals(ctoken_output_decision, ctoken_input_create1);
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_ctrl_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_ctrl_create1.getOutputPin());
		assertEquals(1, output_ctrl_create1.getTokens().size());
		assertTrue(output_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_ctrl_create1.getTokens().get(0);
				
		// Merge Node
		ActivityNodeExecution nodeExecution_merge = activityExecution.getNodeExecutions().get(3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(merge).size());		
		assertEquals(nodeExecution_merge, activityExecution.getNodeExecutionsByNode(merge).get(0));

		assertEquals(merge, nodeExecution_merge.getNode());
		assertEquals(activityExecution, nodeExecution_merge.getActivityExecution());

		assertEquals(1, nodeExecution_merge.getInputs().size());

		Input input_ctrl_merge = nodeExecution_merge.getInputs().get(0);
		assertNull(input_ctrl_merge.getInputPin());
		assertEquals(1, input_ctrl_merge.getTokens().size());
		assertTrue(input_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_merge = (ControlTokenInstance)input_ctrl_merge.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		
		assertEquals(1, nodeExecution_merge.getOutputs().size());

		Output output_ctrl_merge = nodeExecution_merge.getOutputs().get(0);
		assertNull(output_ctrl_merge.getOutputPin());
		assertEquals(1, output_ctrl_merge.getTokens().size());
		assertTrue(output_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_merge = (ControlTokenInstance)output_ctrl_merge.getTokens().get(0);
		
		// Create Object Action 3
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(4);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(1, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);

		assertEquals(ctoken_output_merge, ctoken_input_create3);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));				
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_vspec.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_vspec.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_decision, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_decision.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_decision.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_vspec, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_create1, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_decision, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_merge, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_merge.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_merge.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());		
		assertEquals(nodeExecution_create3, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_merge, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Chronological predecessor / successor relationship
		ActivityNodeExecution chronologicalPredecessor = nodeExecution_vspec.getChronologicalPredecessor();
		ActivityNodeExecution chronologicalSuccessor = nodeExecution_vspec.getChronologicalSuccessor();
		assertEquals(null, chronologicalPredecessor);
		assertEquals(nodeExecution_decision, chronologicalSuccessor);

		chronologicalPredecessor = nodeExecution_decision.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_decision.getChronologicalSuccessor();
		assertEquals(nodeExecution_vspec, chronologicalPredecessor);
		assertEquals(nodeExecution_create1, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create1.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create1.getChronologicalSuccessor();
		assertEquals(nodeExecution_decision, chronologicalPredecessor);
		assertEquals(nodeExecution_merge, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_merge.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_merge.getChronologicalSuccessor();
		assertEquals(nodeExecution_create1, chronologicalPredecessor);
		assertEquals(nodeExecution_create3, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create3.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create3.getChronologicalSuccessor();
		assertEquals(nodeExecution_merge, chronologicalPredecessor);
		assertEquals(null, chronologicalSuccessor);
		
		// Traversed edges
		assertEquals(otoken_output_vspec, otoken_input_decision);
		assertEquals(1, otoken_output_vspec.getTraversedEdges().size());
		assertEquals(cflow1, otoken_output_vspec.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_vspec, ctoken_input_decision);
		assertEquals(1, ctoken_output_vspec.getTraversedEdges().size());
		assertEquals(cflow7, ctoken_output_vspec.getTraversedEdges().get(0));
		
		assertEquals(otoken_output_decision, otoken_input_create1);
		assertEquals(1, otoken_output_decision.getTraversedEdges().size());
		assertEquals(cflow2, otoken_output_decision.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_decision, ctoken_input_create1);
		assertEquals(1, otoken_output_decision.getTraversedEdges().size());
		assertEquals(cflow2, otoken_output_decision.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		assertEquals(1, ctoken_output_create1.getTraversedEdges().size());
		assertEquals(cflow4, ctoken_output_create1.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_merge, ctoken_input_create3);
		assertEquals(1, ctoken_output_merge.getTraversedEdges().size());
		assertEquals(cflow6, ctoken_output_merge.getTraversedEdges().get(0));
		
		assertEquals(0, otoken_output_create1.getTraversedEdges().size());
		assertEquals(0, otoken_output_create3.getTraversedEdges().size());
	}
	
	@Test
	public void testDecisionNodeFlowWithDecisionInputFlow() {
		Activity activity = ActivityFactory.createActivity("testDecisionNodeFlowWithDecisionInputFlow");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "initial");
		ValueSpecificationAction vspec = ActivityFactory.createValueSpecificationAction(activity, "vspec", 1);
		DecisionNode decision = ActivityFactory.createDecisionNode(activity, "decision");
		MergeNode merge = ActivityFactory.createMergeNode(activity, "merge");		
		Class_ class1 = ActivityFactory.createClass("class1");		
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);		
		Class_ class2 = ActivityFactory.createClass("class2");
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		Class_ class3 = ActivityFactory.createClass("class2");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ObjectFlow cflow1 = ActivityFactory.createDecisionInputFlow(activity, vspec.result, decision);
		ControlFlow cflow2 = ActivityFactory.createControlFlow(activity, initial, decision);
		ControlFlow cflow3 = ActivityFactory.createControlFlow(activity, decision, create1, 1);
		ActivityFactory.createControlFlow(activity, decision, create2, 2);
		ControlFlow cflow5 = ActivityFactory.createControlFlow(activity, create1, merge);
		ActivityFactory.createControlFlow(activity, create2, merge);
		ControlFlow cflow7 = ActivityFactory.createControlFlow(activity, merge, create3);
		
		ExecutionContext.getInstance().execute(activity, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, activityExecution.getActivityExecutionID());
		assertEquals(activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(activity, activityExecution.getActivity());
		
		assertEquals(6, activityExecution.getNodeExecutions().size());
					
		// Initial Node
		int index_init = getExecutionOrderIndex(eventlist, initial);		
		ActivityNodeExecution nodeExecution_init = activityExecution.getNodeExecutions().get(index_init);
		
		assertEquals(1, activityExecution.getNodeExecutionsByNode(initial).size());		
		assertEquals(nodeExecution_init, activityExecution.getNodeExecutionsByNode(initial).get(0));

		assertEquals(initial, nodeExecution_init.getNode());
		assertEquals(activityExecution, nodeExecution_init.getActivityExecution());

		assertEquals(0, nodeExecution_init.getInputs().size());					

		assertEquals(1, nodeExecution_init.getOutputs().size());

		Output output_cflow_init = nodeExecution_init.getOutputs().get(0);
		assertNull(output_cflow_init.getOutputPin());
		assertEquals(1, output_cflow_init.getTokens().size());
		assertTrue(output_cflow_init.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_init = (ControlTokenInstance)output_cflow_init.getTokens().get(0);		

		// Value Specification Action
		int index_vspec = getExecutionOrderIndex(eventlist, vspec);
		ActivityNodeExecution nodeExecution_vspec = activityExecution.getNodeExecutions().get(index_vspec);
		
		assertEquals(1, activityExecution.getNodeExecutionsByNode(vspec).size());		
		assertEquals(nodeExecution_vspec, activityExecution.getNodeExecutionsByNode(vspec).get(0));
		
		assertEquals(vspec, nodeExecution_vspec.getNode());
		assertEquals(activityExecution, nodeExecution_vspec.getActivityExecution());
		
		assertEquals(0, nodeExecution_vspec.getInputs().size());					
		
		assertEquals(1, nodeExecution_vspec.getOutputs().size());
		
		Output output_oflow_vspec = nodeExecution_vspec.getOutputs().get(0);
		assertEquals(vspec.result, output_oflow_vspec.getOutputPin());
		assertEquals(1, output_oflow_vspec.getTokens().size());
		assertTrue(output_oflow_vspec.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_vspec = (ObjectTokenInstance)output_oflow_vspec.getTokens().get(0);
		assertEquals(1, ((IntegerValue)otoken_output_vspec.getValue().getValue()).value);		
		
		// Decision Node
		ActivityNodeExecution nodeExecution_decision = activityExecution.getNodeExecutions().get(2);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(decision).size());		
		assertEquals(nodeExecution_decision, activityExecution.getNodeExecutionsByNode(decision).get(0));
		
		assertEquals(decision, nodeExecution_decision.getNode());
		assertEquals(activityExecution, nodeExecution_decision.getActivityExecution());
		
		assertEquals(1, nodeExecution_decision.getInputs().size());		
		
		Input input_oflow_decision = nodeExecution_decision.getInputs().get(0);
		assertNull(input_oflow_decision.getInputPin());
		assertEquals(2, input_oflow_decision.getTokens().size());
		
		ObjectTokenInstance otoken_input_decision = null;
		ControlTokenInstance ctoken_input_decision = null;
		
		if(input_oflow_decision.getTokens().get(0) instanceof ObjectTokenInstance) {
			otoken_input_decision = (ObjectTokenInstance)input_oflow_decision.getTokens().get(0);
			ctoken_input_decision = (ControlTokenInstance)input_oflow_decision.getTokens().get(1);
		} else {
			otoken_input_decision = (ObjectTokenInstance)input_oflow_decision.getTokens().get(1);
			ctoken_input_decision = (ControlTokenInstance)input_oflow_decision.getTokens().get(0);
		}
		
		assertEquals(1, nodeExecution_decision.getOutputs().size());
		
		Output output_oflow_decision = nodeExecution_decision.getOutputs().get(0);
		assertNull(output_oflow_decision.getOutputPin());
		assertEquals(1, output_oflow_decision.getTokens().size());
		assertTrue(output_oflow_decision.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_decision = (ControlTokenInstance)output_oflow_decision.getTokens().get(0);
		
		// Create Object Action 1		
		ActivityNodeExecution nodeExecution_create1 = activityExecution.getNodeExecutions().get(3);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create1).size());		
		assertEquals(nodeExecution_create1, activityExecution.getNodeExecutionsByNode(create1).get(0));
		
		assertEquals(create1, nodeExecution_create1.getNode());
		assertEquals(activityExecution, nodeExecution_create1.getActivityExecution());
		
		assertEquals(1, nodeExecution_create1.getInputs().size());	
		
		Input input_ctrl_create1 = nodeExecution_create1.getInputs().get(0);
		assertNull(input_ctrl_create1.getInputPin());
		assertEquals(1, input_ctrl_create1.getTokens().size());
		assertTrue(input_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create1 = (ControlTokenInstance)input_ctrl_create1.getTokens().get(0);		
		assertEquals(ctoken_output_decision, ctoken_input_create1);	
		
		assertEquals(2, nodeExecution_create1.getOutputs().size());
		
		Output output_oflow_create1 = nodeExecution_create1.getOutputs().get(0);
		assertEquals(create1.result, output_oflow_create1.getOutputPin());
		assertEquals(1, output_oflow_create1.getTokens().size());
		assertTrue(output_oflow_create1.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create1 = (ObjectTokenInstance)output_oflow_create1.getTokens().get(0);
		assertTrue(otoken_output_create1.getValue().getValue() instanceof Object_);
		assertEquals(class1, ((Object_)otoken_output_create1.getValue().getValue()).types.get(0));
		
		Output output_ctrl_create1 = nodeExecution_create1.getOutputs().get(1);
		assertNull(output_ctrl_create1.getOutputPin());
		assertEquals(1, output_ctrl_create1.getTokens().size());
		assertTrue(output_ctrl_create1.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_create1 = (ControlTokenInstance)output_ctrl_create1.getTokens().get(0);
				
		// Merge Node
		ActivityNodeExecution nodeExecution_merge = activityExecution.getNodeExecutions().get(4);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(merge).size());		
		assertEquals(nodeExecution_merge, activityExecution.getNodeExecutionsByNode(merge).get(0));

		assertEquals(merge, nodeExecution_merge.getNode());
		assertEquals(activityExecution, nodeExecution_merge.getActivityExecution());

		assertEquals(1, nodeExecution_merge.getInputs().size());

		Input input_ctrl_merge = nodeExecution_merge.getInputs().get(0);
		assertNull(input_ctrl_merge.getInputPin());
		assertEquals(1, input_ctrl_merge.getTokens().size());
		assertTrue(input_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_merge = (ControlTokenInstance)input_ctrl_merge.getTokens().get(0);
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		
		assertEquals(1, nodeExecution_merge.getOutputs().size());

		Output output_ctrl_merge = nodeExecution_merge.getOutputs().get(0);
		assertNull(output_ctrl_merge.getOutputPin());
		assertEquals(1, output_ctrl_merge.getTokens().size());
		assertTrue(output_ctrl_merge.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_output_merge = (ControlTokenInstance)output_ctrl_merge.getTokens().get(0);
		
		// Create Object Action 3
		ActivityNodeExecution nodeExecution_create3 = activityExecution.getNodeExecutions().get(5);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(create3).size());		
		assertEquals(nodeExecution_create3, activityExecution.getNodeExecutionsByNode(create3).get(0));

		assertEquals(create3, nodeExecution_create3.getNode());
		assertEquals(activityExecution, nodeExecution_create3.getActivityExecution());

		assertEquals(1, nodeExecution_create3.getInputs().size());

		Input input_ctrl_create3 = nodeExecution_create3.getInputs().get(0);
		assertNull(input_ctrl_create3.getInputPin());
		assertEquals(1, input_ctrl_create3.getTokens().size());
		assertTrue(input_ctrl_create3.getTokens().get(0) instanceof ControlTokenInstance);
		ControlTokenInstance ctoken_input_create3 = (ControlTokenInstance)input_ctrl_create3.getTokens().get(0);

		assertEquals(ctoken_output_merge, ctoken_input_create3);	

		assertEquals(1, nodeExecution_create3.getOutputs().size());

		Output output_oflow_create3 = nodeExecution_create3.getOutputs().get(0);
		assertEquals(create3.result, output_oflow_create3.getOutputPin());
		assertEquals(1, output_oflow_create3.getTokens().size());
		assertTrue(output_oflow_create3.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance otoken_output_create3 = (ObjectTokenInstance)output_oflow_create3.getTokens().get(0);
		assertTrue(otoken_output_create3.getValue().getValue() instanceof Object_);
		assertEquals(class3, ((Object_)otoken_output_create3.getValue().getValue()).types.get(0));				
		
		// Logical predecessor / successor relationship
		List<ActivityNodeExecution> logicalPredecessor = nodeExecution_vspec.getLogicalPredecessor();
		List<ActivityNodeExecution> logicalSuccessor = nodeExecution_vspec.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_decision, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_init.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_init.getLogicalSuccessor();		
		assertEquals(0, logicalPredecessor.size());
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_decision, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_decision.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_decision.getLogicalSuccessor();		
		assertEquals(2, logicalPredecessor.size());
		assertTrue(logicalPredecessor.contains(nodeExecution_vspec));
		assertTrue(logicalPredecessor.contains(nodeExecution_init));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_create1, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create1.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create1.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_decision, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());
		assertEquals(nodeExecution_merge, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_merge.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_merge.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_create1, logicalPredecessor.get(0));
		assertEquals(1, logicalSuccessor.size());		
		assertEquals(nodeExecution_create3, logicalSuccessor.get(0));
		
		logicalPredecessor = nodeExecution_create3.getLogicalPredecessor();
		logicalSuccessor = nodeExecution_create3.getLogicalSuccessor();		
		assertEquals(1, logicalPredecessor.size());
		assertEquals(nodeExecution_merge, logicalPredecessor.get(0));
		assertEquals(0, logicalSuccessor.size());		
		
		// Chronological predecessor / successor relationship
		ActivityNodeExecution chronologicalPredecessor = null;
		ActivityNodeExecution chronologicalSuccessor = null;
		
		if(index_init < index_vspec) {
			chronologicalPredecessor = nodeExecution_init.getChronologicalPredecessor();
			chronologicalSuccessor = nodeExecution_init.getChronologicalSuccessor();
			assertEquals(null, chronologicalPredecessor);
			assertEquals(nodeExecution_vspec, chronologicalSuccessor);
			
			chronologicalPredecessor = nodeExecution_vspec.getChronologicalPredecessor();
			chronologicalSuccessor = nodeExecution_vspec.getChronologicalSuccessor();
			assertEquals(nodeExecution_init, chronologicalPredecessor);
			assertEquals(nodeExecution_decision, chronologicalSuccessor);
		} else {
			chronologicalPredecessor = nodeExecution_vspec.getChronologicalPredecessor();
			chronologicalSuccessor = nodeExecution_vspec.getChronologicalSuccessor();
			assertEquals(null, chronologicalPredecessor);
			assertEquals(nodeExecution_vspec, chronologicalSuccessor);
			
			chronologicalPredecessor = nodeExecution_init.getChronologicalPredecessor();
			chronologicalSuccessor = nodeExecution_init.getChronologicalSuccessor();
			assertEquals(nodeExecution_init, chronologicalPredecessor);
			assertEquals(nodeExecution_decision, chronologicalSuccessor);
		}
		
		chronologicalPredecessor = nodeExecution_decision.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_decision.getChronologicalSuccessor();
		assertEquals(nodeExecution_vspec, chronologicalPredecessor);
		assertEquals(nodeExecution_create1, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create1.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create1.getChronologicalSuccessor();
		assertEquals(nodeExecution_decision, chronologicalPredecessor);
		assertEquals(nodeExecution_merge, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_merge.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_merge.getChronologicalSuccessor();
		assertEquals(nodeExecution_create1, chronologicalPredecessor);
		assertEquals(nodeExecution_create3, chronologicalSuccessor);
		
		chronologicalPredecessor = nodeExecution_create3.getChronologicalPredecessor();
		chronologicalSuccessor = nodeExecution_create3.getChronologicalSuccessor();
		assertEquals(nodeExecution_merge, chronologicalPredecessor);
		assertEquals(null, chronologicalSuccessor);
		
		// Traversed edges
		assertEquals(ctoken_output_init, ctoken_input_decision);
		assertEquals(1, ctoken_output_init.getTraversedEdges().size());
		assertEquals(cflow2, ctoken_output_init.getTraversedEdges().get(0));
		
		assertEquals(otoken_output_vspec, otoken_input_decision);
		assertEquals(1, otoken_output_vspec.getTraversedEdges().size());
		assertEquals(cflow1, otoken_output_vspec.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_decision, ctoken_input_create1);
		assertEquals(1, ctoken_output_decision.getTraversedEdges().size());
		assertEquals(cflow3, ctoken_output_decision.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_create1, ctoken_input_merge);
		assertEquals(1, ctoken_output_create1.getTraversedEdges().size());
		assertEquals(cflow5, ctoken_output_create1.getTraversedEdges().get(0));
		
		assertEquals(ctoken_output_merge, ctoken_input_create3);
		assertEquals(1, ctoken_output_merge.getTraversedEdges().size());
		assertEquals(cflow7, ctoken_output_merge.getTraversedEdges().get(0));
		
		assertEquals(0, otoken_output_create1.getTraversedEdges().size());
		assertEquals(0, otoken_output_create3.getTraversedEdges().size());
	}	
	
	@Test
	public void testChronologicalOrder() {
		Activity activity = ActivityFactory.createActivity("testChronologicalOrder");
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		CreateObjectAction action1 = ActivityFactory.createCreateObjectAction(activity, "create1", class1);
		CreateObjectAction action2 = ActivityFactory.createCreateObjectAction(activity, "create2", class2);
		
		// Start stepwise execution
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());

		// Activity started							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int executionID = activityentry.getActivityExecutionID();

		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		
		assertEquals(2, activityExecution.getNodeExecutions().size());
		
		ActivityNodeExecution nodeExecution1 = activityExecution.getNodeExecutionsByNode(action1).get(0);
		ActivityNodeExecution nodeExecution2 = activityExecution.getNodeExecutionsByNode(action2).get(0);		
		assertNotNull(nodeExecution1);
		assertNotNull(nodeExecution2);
		
		assertNull(nodeExecution1.getChronologicalPredecessor());
		assertNull(nodeExecution1.getChronologicalSuccessor());
		assertNull(nodeExecution2.getChronologicalPredecessor());
		assertNull(nodeExecution2.getChronologicalSuccessor());
		
		int index1 = activityExecution.getNodeExecutions().indexOf(nodeExecution1);
		int index2 = activityExecution.getNodeExecutions().indexOf(nodeExecution2);
						
		// Execute next step
		if(index1 > index2) {
			// Execute create object action 1
			ExecutionContext.getInstance().nextStep(executionID, action1);
			
			assertEquals(nodeExecution1, activityExecution.getNodeExecutions().get(0));
			assertNull(nodeExecution1.getChronologicalPredecessor());
			assertNull(nodeExecution1.getChronologicalSuccessor());
			
			// Execute create object action 2
			ExecutionContext.getInstance().nextStep(executionID, action2);
			
			assertEquals(nodeExecution2, activityExecution.getNodeExecutions().get(1));
			assertEquals(nodeExecution1, nodeExecution2.getChronologicalPredecessor());
			assertNull(nodeExecution2.getChronologicalSuccessor());
			assertEquals(nodeExecution2, nodeExecution1.getChronologicalSuccessor());
			assertNull(nodeExecution1.getChronologicalPredecessor());
			
		} else {
			// Execute create object action 2
			ExecutionContext.getInstance().nextStep(executionID, action2);

			assertEquals(nodeExecution2, activityExecution.getNodeExecutions().get(0));
			assertNull(nodeExecution2.getChronologicalPredecessor());
			assertNull(nodeExecution2.getChronologicalSuccessor());

			// Execute create object action 1
			ExecutionContext.getInstance().nextStep(executionID, action1);

			assertEquals(nodeExecution1, activityExecution.getNodeExecutions().get(1));
			assertEquals(nodeExecution2, nodeExecution1.getChronologicalPredecessor());
			assertNull(nodeExecution1.getChronologicalSuccessor());
			assertEquals(nodeExecution1, nodeExecution2.getChronologicalSuccessor());
			assertNull(nodeExecution2.getChronologicalPredecessor());
		}
				
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		ActivityFactory.createCreateObjectAction(activity, "create3", class3);
		ActivityFactory.createCreateObjectAction(activity, "create4", class4);
		
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
		eventlist.clear();
		// Start stepwise execution
		ExecutionContext.getInstance().executeStepwise(activity, null, null);
			
		// Activity started							
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));
		executionID = activityentry.getActivityExecutionID();

		trace = ExecutionContext.getInstance().getTrace(executionID);

		assertEquals(1, trace.getActivityExecutions().size());
		activityExecution = trace.getActivityExecutions().get(0);

		assertEquals(4, activityExecution.getNodeExecutions().size());
		
		// Execute next action (last in the list)
		ActivityNodeExecution executedNodeExecution = activityExecution.getNodeExecutions().get(3);
		ExecutionContext.getInstance().nextStep(executionID, executedNodeExecution.getNode());
				
		ActivityNodeExecution nodeExecution = activityExecution.getNodeExecutions().get(0);
		assertEquals(executedNodeExecution, nodeExecution);
		assertNull(nodeExecution.getChronologicalPredecessor());
		assertNull(nodeExecution.getChronologicalSuccessor());
		
		// Execute next action (last in the list)
		executedNodeExecution = activityExecution.getNodeExecutions().get(3);
		ExecutionContext.getInstance().nextStep(executionID, executedNodeExecution.getNode());
		
		nodeExecution = activityExecution.getNodeExecutions().get(1);
		assertEquals(executedNodeExecution, nodeExecution);
		assertEquals(activityExecution.getNodeExecutions().get(0), nodeExecution.getChronologicalPredecessor());
		assertNull(nodeExecution.getChronologicalSuccessor());
		assertEquals(null, activityExecution.getNodeExecutions().get(0).getChronologicalPredecessor());
		assertEquals(nodeExecution, activityExecution.getNodeExecutions().get(0).getChronologicalSuccessor());
		
		// Execute next action (last in the list)
		executedNodeExecution = activityExecution.getNodeExecutions().get(3);
		ExecutionContext.getInstance().nextStep(executionID, executedNodeExecution.getNode());

		nodeExecution = activityExecution.getNodeExecutions().get(2);
		assertEquals(executedNodeExecution, nodeExecution);
		assertEquals(activityExecution.getNodeExecutions().get(1), nodeExecution.getChronologicalPredecessor());
		assertNull(nodeExecution.getChronologicalSuccessor());
		assertEquals(nodeExecution, activityExecution.getNodeExecutions().get(1).getChronologicalSuccessor());
				
		// Execute next action (last in the list)
		executedNodeExecution = activityExecution.getNodeExecutions().get(3);
		ExecutionContext.getInstance().nextStep(executionID, executedNodeExecution.getNode());

		nodeExecution = activityExecution.getNodeExecutions().get(3);
		assertEquals(executedNodeExecution, nodeExecution);
		assertEquals(activityExecution.getNodeExecutions().get(2), nodeExecution.getChronologicalPredecessor());
		assertNull(nodeExecution.getChronologicalSuccessor());
		assertEquals(nodeExecution, activityExecution.getNodeExecutions().get(2).getChronologicalSuccessor());		
	}
	
	@Test
	public void testCallBehaviorWithInputOutput() {
		Activity a1 = ActivityFactory.createActivity("Create New Student");
		Parameter a1_inputparam = ActivityFactory.createParameter(a1, "name", ParameterDirectionKind.in);
		ActivityParameterNode a1_input = ActivityFactory.createActivityParameterNode(a1, "name", a1_inputparam);
		Parameter a1_outputparam = ActivityFactory.createParameter(a1, "student", ParameterDirectionKind.out);
		ActivityParameterNode a1_output = ActivityFactory.createActivityParameterNode(a1, "student", a1_outputparam);
		Class_ student = ActivityFactory.createClass("Student");
		CreateObjectAction a1_create = ActivityFactory.createCreateObjectAction(a1, "create student", student);
		Property name = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), student);
		AddStructuralFeatureValueAction a1_setname = ActivityFactory.createAddStructuralFeatureValueAction(a1, "set name", name);
		ObjectFlow a1_o1 = ActivityFactory.createObjectFlow(a1, a1_input, a1_setname.value);
		ObjectFlow a1_o2 = ActivityFactory.createObjectFlow(a1, a1_create.result, a1_setname.object);
		ObjectFlow a1_o3 = ActivityFactory.createObjectFlow(a1, a1_setname.result, a1_output);
		
		Activity a2 = ActivityFactory.createActivity("testCallBehaviorWithInputOutput");
		ValueSpecificationAction a2_vspec = ActivityFactory.createValueSpecificationAction(a2, "specify tanja", "tanja");
		CallBehaviorAction a2_call = ActivityFactory.createCallBehaviorAction(a2, "call Create New Student", a1, 1, 1);
		ObjectFlow a2_o1 = ActivityFactory.createObjectFlow(a2, a2_vspec.result, a2_call.input.get(0));
		
		ExecutionContext.getInstance().execute(a2, null, null);				
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent entryevent = (ActivityEntryEvent)eventlist.get(0);
		int executionID = entryevent.getActivityExecutionID();
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		
		assertEquals(2, trace.getActivityExecutions().size());
		ActivityExecution a2_activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(executionID, a2_activityExecution.getActivityExecutionID());
		assertEquals(a2_activityExecution, trace.getActivityExecutionByID(executionID));		
		assertEquals(a2, a2_activityExecution.getActivity());
		ActivityExecution a1_activityExecution = trace.getActivityExecutions().get(1);		
		assertEquals(a1, a1_activityExecution.getActivity());
		
		// START Activity 2 (calling activity)
		
		assertEquals(2, a2_activityExecution.getNodeExecutions().size());
		
		assertNull(a2_activityExecution.getCaller());
		assertEquals(0, a2_activityExecution.getParameterInputs().size());
		assertEquals(0, a2_activityExecution.getParameterOutputs().size());
				
		// Value specification action		
		ActivityNodeExecution a2_nodeExecution_vspec = a2_activityExecution.getNodeExecutions().get(0);
		
		assertEquals(a2_vspec, a2_nodeExecution_vspec.getNode());
		assertEquals(a2_activityExecution, a2_nodeExecution_vspec.getActivityExecution());

		assertEquals(0, a2_nodeExecution_vspec.getInputs().size());					

		assertEquals(1, a2_nodeExecution_vspec.getOutputs().size());

		Output a2_output_oflow_vspec = a2_nodeExecution_vspec.getOutputs().get(0);
		assertEquals(a2_vspec.result, a2_output_oflow_vspec.getOutputPin());
		assertEquals(1, a2_output_oflow_vspec.getTokens().size());
		assertTrue(a2_output_oflow_vspec.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a2_otoken_output_vspec = (ObjectTokenInstance)a2_output_oflow_vspec.getTokens().get(0);	
		assertEquals("tanja", ((StringValue)a2_otoken_output_vspec.getValue().getValue()).value);
		
		// Call behavior action		
		ActivityNodeExecution a2_nodeExecution_call = a2_activityExecution.getNodeExecutions().get(1);
		assertTrue(a2_nodeExecution_call instanceof CallActivityNodeExecution);
		assertEquals(a1, ((CallActivityNodeExecution)a2_nodeExecution_call).getCalledBehavior());
		assertEquals(a1_activityExecution, ((CallActivityNodeExecution)a2_nodeExecution_call).getCallee());
		assertEquals(a2_nodeExecution_call, a1_activityExecution.getCaller());
		
		assertEquals(a2_call, a2_nodeExecution_call.getNode());
		assertEquals(a2_activityExecution, a2_nodeExecution_call.getActivityExecution());

		assertEquals(1, a2_nodeExecution_call.getInputs().size());					
		Input a2_input_oflow_call = a2_nodeExecution_call.getInputs().get(0);
		assertEquals(a2_call.input.get(0), a2_input_oflow_call.getInputPin());
		assertEquals(1, a2_input_oflow_call.getTokens().size());
		assertTrue(a2_input_oflow_call.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a2_otoken_input_call = (ObjectTokenInstance)a2_input_oflow_call.getTokens().get(0);
		assertEquals("tanja", ((StringValue)a2_otoken_input_call.getValue().getValue()).value);		
				
		assertEquals(1, a2_nodeExecution_call.getOutputs().size());

		Output a2_output_oflow_call = a2_nodeExecution_call.getOutputs().get(0);
		assertEquals(a2_call.result.get(0), a2_output_oflow_call.getOutputPin());
		assertEquals(1, a2_output_oflow_call.getTokens().size());
		assertTrue(a2_output_oflow_call.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a2_otoken_output_call = (ObjectTokenInstance)a2_output_oflow_call.getTokens().get(0);
		assertTrue(a2_otoken_output_call.getValue().getValue() instanceof Object_);
		assertEquals(student, ((Object_)a2_otoken_output_call.getValue().getValue()).types.get(0));
		assertEquals("tanja", ((StringValue)((Object_)a2_otoken_output_call.getValue().getValue()).featureValues.get(0).values.get(0)).value);				
		
		// Tokens
		assertEquals(a2_otoken_output_vspec, a2_otoken_input_call);
		assertEquals(1, a2_otoken_input_call.getTraversedEdges().size());
		assertEquals(a2_o1, a2_otoken_input_call.getTraversedEdges().get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, a2_nodeExecution_vspec.getChronologicalPredecessor());
		assertEquals(a2_nodeExecution_call, a2_nodeExecution_vspec.getChronologicalSuccessor());
		
		assertEquals(a2_nodeExecution_vspec, a2_nodeExecution_call.getChronologicalPredecessor());
		assertEquals(null, a2_nodeExecution_call.getChronologicalSuccessor());
		
		// Logical predecessor / successor relationship
		assertEquals(0, a2_nodeExecution_vspec.getLogicalPredecessor().size());
		assertEquals(1, a2_nodeExecution_vspec.getLogicalSuccessor().size());
		assertEquals(a2_nodeExecution_call, a2_nodeExecution_vspec.getLogicalSuccessor().get(0));
		
		assertEquals(1, a2_nodeExecution_call.getLogicalPredecessor().size());
		assertEquals(a2_nodeExecution_vspec, a2_nodeExecution_call.getLogicalPredecessor().get(0));
		assertEquals(0, a2_nodeExecution_call.getLogicalSuccessor().size());		
		
		assertEquals(a2_otoken_output_vspec, a2_otoken_input_call);
		
		// END Activity 2 (calling activity)
		
		
		// START Activity 1 (called activity)
		
		assertEquals(2, a1_activityExecution.getNodeExecutions().size());
		
		assertEquals(a2_nodeExecution_call, a1_activityExecution.getCaller());		
		
		assertEquals(1, a1_activityExecution.getParameterInputs().size());
		ParameterInput a1_parameterinput = a1_activityExecution.getParameterInputs().get(0);
		assertEquals(a1_input, a1_parameterinput.getInputParameterNode());
		assertEquals(1, a1_parameterinput.getParameterInputTokens().size());
		ObjectTokenInstance a1_otoken_input_param = a1_parameterinput.getParameterInputTokens().get(0);
		assertTrue(a1_otoken_input_param.getValue().getValue() instanceof StringValue);
		assertEquals("tanja", ((StringValue)a1_otoken_input_param.getValue().getValue()).value);
		
		assertEquals(1, a1_activityExecution.getParameterOutputs().size());
		ParameterOutput a1_parameteroutput = a1_activityExecution.getParameterOutputs().get(0);
		assertEquals(a1_output, a1_parameteroutput.getOutputParameterNode());
		assertEquals(1, a1_parameteroutput.getParameterOutputTokens().size());
		ObjectTokenInstance a1_otoken_output_param = a1_parameteroutput.getParameterOutputTokens().get(0);
		Object_ a1_object_outparam = (Object_)a1_otoken_output_param.getValue().getValue();
		assertEquals(student, a1_object_outparam.types.get(0));
		assertEquals("tanja", ((StringValue)a1_object_outparam.featureValues.get(0).values.get(0)).value);		
		
		// Create object action		
		ActivityNodeExecution a1_nodeExecution_create = a1_activityExecution.getNodeExecutions().get(0);

		assertEquals(a1_create, a1_nodeExecution_create.getNode());
		assertEquals(a1_activityExecution, a1_nodeExecution_create.getActivityExecution());

		assertEquals(0, a1_nodeExecution_create.getInputs().size());					

		assertEquals(1, a1_nodeExecution_create.getOutputs().size());

		Output a1_output_oflow_create = a1_nodeExecution_create.getOutputs().get(0);
		assertEquals(a1_create.result, a1_output_oflow_create.getOutputPin());
		assertEquals(1, a1_output_oflow_create.getTokens().size());
		assertTrue(a1_output_oflow_create.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a1_otoken_output_create = (ObjectTokenInstance)a1_output_oflow_create.getTokens().get(0);
		Object_ a1_object_create = (Object_)a1_otoken_output_create.getValue().getValue();
		assertEquals(student, a1_object_create.getTypes().get(0));
		
		// Add structural feature value action
		ActivityNodeExecution a1_nodeExecution_setname = a1_activityExecution.getNodeExecutions().get(1);

		assertEquals(a1_setname, a1_nodeExecution_setname.getNode());
		assertEquals(a1_activityExecution, a1_nodeExecution_setname.getActivityExecution());

		assertEquals(2, a1_nodeExecution_setname.getInputs().size());	
		Input a1_inputobj_oflow_setname = null;
		Input a1_inputv_oflow_setname = null;
		
		if(a1_nodeExecution_setname.getInputs().get(0).getInputPin().equals(a1_setname.object)) {
			a1_inputobj_oflow_setname = a1_nodeExecution_setname.getInputs().get(0);
			a1_inputv_oflow_setname = a1_nodeExecution_setname.getInputs().get(1);
		} else {
			a1_inputobj_oflow_setname = a1_nodeExecution_setname.getInputs().get(1);
			a1_inputv_oflow_setname = a1_nodeExecution_setname.getInputs().get(0);
		}
		
		//TODO abbildung der token nochmal überdenken
		//Problem hier könnte gelöst werden in dem man 
		//1) Aspekt schreibt für CallActionActivation.doAction() wo man eine Map erzeugt Parameter - Token
		//2) Aspekt für ActivityParameterNodeActivation.fire() hier müste man die Token Copy anlegen im Execution Status
		//Dann hätte man den selben Token wie vom OutputPin des aufrufenden ActivityNodes der den Token als Input für die CallAction bereitgestellt hat
		//Aber scheinbar ist die Semantik so dass neue Token mit den selben Values erzeugt werden --> soll man das so umsetzen?
		
		assertEquals(a1_setname.value, a1_inputv_oflow_setname.getInputPin());
		assertEquals(1, a1_inputv_oflow_setname.getTokens().size());
		assertTrue(a1_inputv_oflow_setname.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a1_otoken_inputv_setname = (ObjectTokenInstance)a1_inputv_oflow_setname.getTokens().get(0);	
		assertEquals("tanja", ((StringValue)a1_otoken_inputv_setname.getValue().getValue()).value);		
		
		assertEquals(a1_setname.object, a1_inputobj_oflow_setname.getInputPin());
		assertEquals(1, a1_inputobj_oflow_setname.getTokens().size());
		assertTrue(a1_inputobj_oflow_setname.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a1_otoken_inputo_setname = (ObjectTokenInstance)a1_inputobj_oflow_setname.getTokens().get(0);
		Object_ a1_object_set = (Object_)a1_otoken_inputo_setname.getValue().getValue();
		assertEquals(student, a1_object_set.getTypes().get(0));		
		
		assertEquals(1, a1_nodeExecution_setname.getOutputs().size());		
		
		Output a1_output_oflow_setname = a1_nodeExecution_setname.getOutputs().get(0);
		assertEquals(a1_setname.result, a1_output_oflow_setname.getOutputPin());
		assertEquals(1, a1_output_oflow_setname.getTokens().size());
		assertTrue(a1_output_oflow_setname.getTokens().get(0) instanceof ObjectTokenInstance);
		ObjectTokenInstance a1_otoken_output_setname = (ObjectTokenInstance)a1_output_oflow_setname.getTokens().get(0);
		assertTrue(a1_otoken_output_setname.getValue().getValue() instanceof Object_);
		assertEquals(student, ((Object_)a1_otoken_output_setname.getValue().getValue()).types.get(0));
		assertEquals("tanja", ((Object_)a1_otoken_output_setname.getValue().getValue()).featureValues.get(0).values.get(0));						
		
		// Tokens
		assertEquals(a1_otoken_input_param, a1_otoken_inputv_setname);
		assertEquals(a2_otoken_input_call, a1_otoken_input_param);
		assertEquals(2, a1_otoken_input_param.getTraversedEdges().size());
		assertTrue(a1_otoken_input_param.getTraversedEdges().contains(a1_o1));
		assertTrue(a1_otoken_input_param.getTraversedEdges().contains(a2_o1));
		
		assertEquals(a1_otoken_output_create, a1_otoken_inputo_setname);
		assertEquals(1, a1_otoken_inputo_setname.getTraversedEdges());
		assertEquals(a1_o2, a1_otoken_inputo_setname.getTraversedEdges().get(0));
		
		assertEquals(a1_otoken_output_param, a1_otoken_output_setname);		
		assertEquals(a2_otoken_output_call, a1_otoken_output_param);
		assertEquals(1, a1_otoken_input_param.getTraversedEdges().size());
		assertEquals(a1_o3, a1_otoken_input_param.getTraversedEdges().get(0));
		
		// Chronological predecessor / successor relationship
		assertEquals(null, a1_nodeExecution_create.getChronologicalPredecessor());
		assertEquals(a1_nodeExecution_setname, a1_nodeExecution_create.getChronologicalSuccessor());

		assertEquals(a1_nodeExecution_create, a1_nodeExecution_setname.getChronologicalPredecessor());
		assertEquals(null, a1_nodeExecution_setname.getChronologicalSuccessor());

		// Logical predecessor / successor relationship
		assertEquals(0, a1_nodeExecution_create.getLogicalPredecessor().size());
		assertEquals(1, a1_nodeExecution_create.getLogicalSuccessor().size());
		assertEquals(a1_nodeExecution_setname, a1_nodeExecution_create.getLogicalSuccessor().get(0));

		assertEquals(0, a1_nodeExecution_setname.getLogicalPredecessor().size());
		assertEquals(a1_nodeExecution_create, a1_nodeExecution_setname.getLogicalPredecessor().get(0));
		assertEquals(0, a1_nodeExecution_setname.getLogicalSuccessor().size());		

		assertEquals(a1_otoken_inputo_setname, a2_otoken_input_call);		
	}
*/	
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
/*	
	private int getExecutionOrderIndex(List<Event> eventlist, ActivityNode node) {
		int activityNodeExecutionCount = -1;
		for(int i=0;i<eventlist.size();++i) {
			if(eventlist.get(i) instanceof ActivityNodeEntryEvent) {
				++activityNodeExecutionCount;
				ActivityNodeEntryEvent e = (ActivityNodeEntryEvent)eventlist.get(i);
				if(e.getNode().equals(node)) {
					return activityNodeExecutionCount;
				}				
			}
		}
		return -1;
	}
*/	
}
