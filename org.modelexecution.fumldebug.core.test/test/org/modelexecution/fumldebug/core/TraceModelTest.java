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
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;

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
	public void testEmptyActivityWithOutputParameterMultiple() {
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
		assertEquals(0, activityExecution.getNodeExecutions().size());
		
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
		
	@Override
	public void notify(Event event) {
		eventlist.add(event);
	}
			
}
