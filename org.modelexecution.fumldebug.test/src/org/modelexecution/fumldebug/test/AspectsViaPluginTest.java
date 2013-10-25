/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation based on the EventTest in org.modelexecution.fumldebugcore.test
 */
package org.modelexecution.fumldebug.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * This test only verifies whether the events (and thus the aspects)
 * work properly when executing an activity via the {@link ExecutionContext}
 * provided by the plug-in org.modelexecution.fumldebug. Therefore, this
 * test only executes one activity and checks if events are notified. If
 * this is the case, the aspects seem to work.
 * 
 * This test is based on EventTest in org.modelexecution.fumldebug.core.test.
 * 
 * @author Philip Langer
 *
 */
public class AspectsViaPluginTest implements ExecutionEventListener {
	
	private List<Event> eventlist = new ArrayList<Event>();
	
	public AspectsViaPluginTest() {
		ExecutionContext.getInstance().addEventListener(this);
	}

	@Test
	public void testEventsWhenExecutingActivity() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityExecution");
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());		
	}
	
	@Test
	public void testExecutionTrace() {
		Class_ class_ = ActivityFactory.createClass("class");
		Activity activity = ActivityFactory.createActivity("Activity testExecutionTrace");		
		CreateObjectAction createObjectAction1 = ActivityFactory.createCreateObjectAction(activity, "create object 1", class_);
		CreateObjectAction createObjectAction2 = ActivityFactory.createCreateObjectAction(activity, "create object 2", class_);
		ActivityFactory.createControlFlow(activity, createObjectAction1, createObjectAction2);
		
		ExecutionContext executionContext = ExecutionContext.getInstance();
		executionContext.execute(activity, null, new ParameterValueList());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		int executionId = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		Trace trace = executionContext.getTrace(executionId);
		assertNotNull(trace);
		
		assertEquals(1, trace.getActivityExecutions().size());
		ActivityExecution activityExecution = trace.getActivityExecutions().get(0);
		assertEquals(2, activityExecution.getNodeExecutions().size());
		
		assertEquals(1, activityExecution.getNodeExecutionsByNode(createObjectAction1).size());
		ActivityNodeExecution createObjectAction1Execution = activityExecution.getNodeExecutionsByNode(createObjectAction1).get(0);
		assertEquals(1, activityExecution.getNodeExecutionsByNode(createObjectAction2).size());
		ActivityNodeExecution createObjectAction2Execution = activityExecution.getNodeExecutionsByNode(createObjectAction2).get(0);
		
		// check logical relationships
		assertEquals(0, createObjectAction1Execution.getLogicalPredecessor().size());
		assertEquals(1, createObjectAction1Execution.getLogicalSuccessor().size());
		assertEquals(createObjectAction2Execution, createObjectAction1Execution.getLogicalSuccessor().get(0));
		
		assertEquals(1, createObjectAction2Execution.getLogicalPredecessor().size());
		assertEquals(createObjectAction1Execution, createObjectAction2Execution.getLogicalPredecessor().get(0));
		assertEquals(0, createObjectAction2Execution.getLogicalSuccessor().size());		
	}
	
	@Override
	public void notify(Event event) {		
		eventlist.add(event);
	}
	
}
