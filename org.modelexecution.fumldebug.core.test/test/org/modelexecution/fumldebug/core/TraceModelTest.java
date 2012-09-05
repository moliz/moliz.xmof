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
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;

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
		
	@Test
	public void testActivityNodeExecutionOrder() {
		Activity activity = ActivityFactory.createActivity("testActivityNodeExecutionOrder");
		InitialNode initialnode = ActivityFactory.createInitialNode(activity, "initial node");
		MergeNode mergenode1 = ActivityFactory.createMergeNode(activity, "merge node 1");
		MergeNode mergenode2 = ActivityFactory.createMergeNode(activity, "merge node 2");
		ActivityFactory.createControlFlow(activity, initialnode, mergenode1);
		ActivityFactory.createControlFlow(activity, mergenode1, mergenode2);
		
		// Start execution
		ExecutionContext.getInstance().execute(activity, null, null);
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		assertEquals(initialnode, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());		
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		assertEquals(initialnode, ((ActivityNodeExitEvent)eventlist.get(2)).getNode());
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode1, ((ActivityNodeEntryEvent)eventlist.get(3)).getNode());		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(mergenode2, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(mergenode2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(7)).getActivity());				
		
		Trace trace = ExecutionContext.getInstance().getTrace(activityentry.getActivityExecutionID());
		assertNotNull(trace);
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(activity, activityExecution.getActivity());
		assertEquals(activityentry.getActivityExecutionID(), activityExecution.getActivityExecutionID());
		assertNull(activityExecution.getCaller());
		assertEquals(3, activityExecution.getNodeExecutions().size());
		assertEquals(initialnode, activityExecution.getNodeExecutions().get(0).getNode());
		assertEquals(mergenode1, activityExecution.getNodeExecutions().get(1).getNode());
		assertEquals(mergenode2, activityExecution.getNodeExecutions().get(2).getNode());
	}	
	
	@Test
	public void testSingleActivityExecution() {
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
		assertEquals(cflow_initial2createobjtanja, ctoken_output_initialnode.getTraversedEdge());
		assertEquals(cflow_initial2createobjtanja, ctoken_input_createObjectTanja.getTraversedEdge());
		assertEquals(ctoken_output_initialnode, ctoken_input_createObjectTanja);
		
		assertEquals(cflow_createobjtanja2valuetanja, ctoken_output_createObjectTanja.getTraversedEdge());
		assertEquals(cflow_createobjtanja2valuetanja, ctoken_input_valueTanja.getTraversedEdge());
		assertEquals(ctoken_output_createObjectTanja, ctoken_input_valueTanja);
		
		assertEquals(cflow_valuetanja2createobjphilip, ctoken_output_valueTanja.getTraversedEdge());
		assertEquals(cflow_valuetanja2createobjphilip, ctoken_input_createObjectPhilip.getTraversedEdge());
		assertEquals(ctoken_output_valueTanja, ctoken_input_createObjectPhilip);
		
		assertEquals(cflow_createobjphilip2valuephilip, ctoken_output_createObjectPhilip.getTraversedEdge());
		assertEquals(cflow_createobjphilip2valuephilip, ctoken_input_valuePhilip.getTraversedEdge());
		assertEquals(ctoken_output_createObjectPhilip, ctoken_input_valuePhilip);
		
		assertEquals(oflow_createobjtanja2nametanja, otoken_output_createObjectTanja.getTraversedEdge());
		assertEquals(oflow_createobjtanja2nametanja, otoken_inputObj_addNameTanja.getTraversedEdge());
		assertEquals(otoken_output_createObjectTanja, otoken_inputObj_addNameTanja);
		
		assertEquals(oflow_valuetanja2nametanja, otoken_output_valueTanja.getTraversedEdge());
		assertEquals(oflow_valuetanja2nametanja, otoken_inputStr_addNameTanja.getTraversedEdge());
		assertEquals(otoken_output_valueTanja, otoken_inputStr_addNameTanja);
		
		assertEquals(oflow_createobjphilip2namephilip, otoken_output_createObjectPhilip.getTraversedEdge());
		assertEquals(oflow_createobjphilip2namephilip, otoken_inputObj_addNamePhilip.getTraversedEdge());
		assertEquals(otoken_output_createObjectPhilip, otoken_inputObj_addNamePhilip);
		
		assertEquals(oflow_valuephilip2namephilip, otoken_output_valuePhilip.getTraversedEdge());
		assertEquals(oflow_valuephilip2namephilip, otoken_inputStr_addNamePhilip.getTraversedEdge());
		assertEquals(otoken_output_valuePhilip, otoken_inputStr_addNamePhilip);
		
		assertNull(otoken_output_addNameTanja.getTraversedEdge());
		assertNull(otoken_output_addNamePhilip.getTraversedEdge());
	}
	
	@Override
	public void notify(Event event) {
		eventlist.add(event);
	}
			
}
