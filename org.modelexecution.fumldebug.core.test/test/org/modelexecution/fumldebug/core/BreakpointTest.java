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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.impl.BreakpointImpl;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * @author Tanja Mayerhofer
 *
 */
public class BreakpointTest extends MolizTest implements ExecutionEventListener {

	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();
	
	public BreakpointTest() {
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

	/**
	 * Breakpoint is set for CreateObjectAction2.
	 * After this breakpoint is hit, resume is called. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testSingleBreakpoint1() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testSingleBreakpoint1");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

		// Set Breakpoint
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
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
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(1, create2breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate2, create2breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create2breakpoint.getParent());		
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create2, step2.getNewEnabledNodes().get(0));
		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(6)).getParent());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(9)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}		
	
	/**
	 * Breakpoint is set for CreateObjectAction3.
	 * After this breakpoint is hit, resume is called. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testSingleBreakpoint2() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testSingleBreakpoint2");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

		// Set Breakpoint
		BreakpointImpl breakpointcreate3 = new BreakpointImpl(create3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		
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
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(5)).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(1, create3breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate3, create3breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create3breakpoint.getParent());		
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(6));
		assertEquals(create2, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create3, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(10, eventlist.size());
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(9)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * No Breakpoint is set and resume is called after the first step. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testResumeWihtoutBreakpoint() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testResumeWihtoutBreakpoint");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

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
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(5)).getParent());			
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(7)).getParent());
		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(8)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));		
	}
	
	/**
	 * Breakpoints are set for every node.
	 * After each breakpoint resume is called. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testBreakpointForEveryActivityNode() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testBreakpointForEveryActivityNode");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

		// Set Breakpoint
		BreakpointImpl breakpointcreate1 = new BreakpointImpl(create1);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate1);
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		BreakpointImpl breakpointcreate3 = new BreakpointImpl(create3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(1, create1breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate1, create1breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create1breakpoint.getParent());
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(1, create2breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate2, create2breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create2breakpoint.getParent());	
		SuspendEvent step2 = (SuspendEvent) eventlist.get(4);
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create2, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(6)).getParent());	
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(7);
		assertEquals(1, create3breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate3, create3breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create3breakpoint.getParent());	
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(7));
		assertEquals(create2, step3.getLocation());
		assertEquals(activityentry, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(create3, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
				
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
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
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction1 and CreateObjectAction3.
	 * After each breakpoint resume is called. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testMultipleBreakpoints() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testMultipleBreakpoints");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

		// Set Breakpoint
		BreakpointImpl breakpointcreate1 = new BreakpointImpl(create1);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate1);
		BreakpointImpl breakpointcreate3 = new BreakpointImpl(create3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(1, create1breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate1, create1breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create1breakpoint.getParent());
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());							
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(5)).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(1, create3breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate3, create3breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create3breakpoint.getParent());	
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(6));
		assertEquals(create2, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create3, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
				
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(9)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));	

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction2 and CreateObjectAction4.
	 * After each breakpoint resume is called. 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * CreateObjectAction4 (class = Class4)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 * CreateObjectAction3 --> CreateObjectAction4
	 */
	@Test
	public void testMultipleBreakpoints2() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		Activity activity = ActivityFactory.createActivity("testMultipleBreakpoints");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		CreateObjectAction create4 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class4", class4);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);
		ActivityFactory.createControlFlow(activity, create3, create4);

		// Set Breakpoint		
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		BreakpointImpl breakpointcreate4 = new BreakpointImpl(create4);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate4);
		
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
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());							
		
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(1, create2breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate2, create2breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create2breakpoint.getParent());	
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create2, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
				
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(6)).getParent());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());	
		
		assertTrue(eventlist.get(9) instanceof BreakpointEvent);
		BreakpointEvent create4breakpoint = (BreakpointEvent)eventlist.get(9);
		assertEquals(1, create4breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate4, create4breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create4breakpoint.getParent());	
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(9));
		assertEquals(create3, step3.getLocation());
		assertEquals(activityentry, step3.getParent());
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(create4, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create4, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));				
				
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(13, eventlist.size());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(10);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		assertEquals(create4entry, ((TraceEvent)eventlist.get(11)).getParent());
		assertTrue(eventlist.get(12) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(12)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(12)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		Object_ o4= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));	
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction2 and CreateObjectAction4.
	 * After each breakpoint/step, nextStep is called 
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * CreateObjectAction4 (class = Class4)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 * CreateObjectAction3 --> CreateObjectAction4
	 */
	@Test
	public void testNextStepWithBreakpoints() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		Activity activity = ActivityFactory.createActivity("testNextStepWithBreakpoints");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		CreateObjectAction create4 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class4", class4);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);
		ActivityFactory.createControlFlow(activity, create3, create4);

		// Set Breakpoint
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		BreakpointImpl breakpointcreate4 = new BreakpointImpl(create4);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate4);
				
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
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());							
		
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(1, create2breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate2, create2breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create2breakpoint.getParent());	
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(create2, step2.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
				
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
		assertEquals(1, step3.getNewEnabledNodes().size());
		assertEquals(create3, step3.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
				
		assertEquals(11, eventlist.size());
				
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(9)).getParent());	
		
		assertTrue(eventlist.get(10) instanceof BreakpointEvent);
		BreakpointEvent create4breakpoint = (BreakpointEvent)eventlist.get(10);
		assertEquals(1, create4breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate4, create4breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create4breakpoint.getParent());			
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(10));
		assertEquals(create3, step4.getLocation());
		assertEquals(activityentry, step4.getParent());
		assertEquals(1, step4.getNewEnabledNodes().size());
		assertEquals(create4, step4.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create4, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));				
				
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
		
		assertEquals(14, eventlist.size());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(11);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(create4entry, ((TraceEvent)eventlist.get(12)).getParent());
		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(13)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(13)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		Object_ o4= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));	
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoint is set for CreateObjectAction2.
	 * 
	 *  After the first step (get enabled nodes), NEXTSTEP is called.
	 *  Then the initialNode is executed and RESUME is called.
	 *  Then create 1 is executed and the breakpoint for create 2 is hit.
	 *  Then NEST STEP is called (--> create 2 is executed) 
	 *  and RESUME afterwards (--> create3, create4 are executed).
	 * 
	 * Activity:
	 * InitialNode
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * CreateObjectAction4 (class = Class4)
	 * 
	 * Activity ControlFlow:
	 * InitialNode --> CreateObjectAction1
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 * CreateObjectAction3 --> CreateObjectAction4
	 */
	@Test
	public void testNextStepResumeMix() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		Activity activity = ActivityFactory.createActivity("testNextStepWithBreakpoints");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "InitialNode");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		CreateObjectAction create4 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class4", class4);
		
		ActivityFactory.createControlFlow(activity, initial, create1);
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);
		ActivityFactory.createControlFlow(activity, create3, create4);

		// Set Breakpoint
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initial, initialentry.getNode());	
		assertEquals(activityentry, initialentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialentry, ((TraceEvent)eventlist.get(3)).getParent());							
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(initial, ((SuspendEvent)eventlist.get(4)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(4)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());				
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(6)).getParent());							
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(7);
		assertEquals(1, create2breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate2, create2breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create2breakpoint.getParent());	
		assertEquals(create1, ((SuspendEvent)eventlist.get(7)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(7)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
				
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(9)).getParent());			
		assertTrue(eventlist.get(10) instanceof SuspendEvent);
		assertEquals(create2, ((SuspendEvent)eventlist.get(10)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(10)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		
		// Resume execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
				
		assertEquals(16, eventlist.size());
				
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(11);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(12)).getParent());		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(13);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		assertEquals(create4entry, ((TraceEvent)eventlist.get(14)).getParent());
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(15)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));
		Object_ o4= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class4, o4.getTypes().get(0));	
		
		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
		
	/**
	 * Breakpoint is set for CreateObjectAction3.
	 * After this breakpoint is hit, RESUME is called. 
	 * Then the breakpoint is deleted and the activity is
	 * debugged again, after the first step, RESUME is called.
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> CreateObjectAction3
	 */
	@Test
	public void testRemoveBreakpoint() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Activity activity = ActivityFactory.createActivity("testSingleBreakpoint2");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, create3);

		// Set Breakpoint
		BreakpointImpl breakpointcreate3 = new BreakpointImpl(create3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(5)).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(1, create3breakpoint.getBreakpoints().size());
		assertEquals(breakpointcreate3, create3breakpoint.getBreakpoints().get(0));
		assertEquals(activityentry, create3breakpoint.getParent());
		assertEquals(create2, ((SuspendEvent)eventlist.get(6)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(6)).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(10, eventlist.size());
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(9)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		
		
		
		// Remove breakpoint
		ExecutionContext.getInstance().removeBreakpoint(breakpointcreate3);
		
		// Reset data structures
		eventlist = new ArrayList<Event>();
		extensionalValueLists = new ArrayList<ExtensionalValueList>();
				
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(1)).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		// The objects from the first execution still exist at the locus
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(5)).getParent());	
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		create3entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(7)).getParent());
		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(8)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(6, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(4));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(5));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));		

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction1 and CreateObjectAction2.
	 * Therefore after the execution of ForkNode, two BreakPointEvents should be received
	 * 
	 * Activity:
	 * InitialNode
	 * ForkNode
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3) 
	 * 
	 * Activity Control Flow:
	 * InitialNode --> ForkNode
	 * ForkNode --> CreateObjectAction1
	 * ForkNode --> CreateObjectAction2
	 * ForkNode --> CreateObjectAction3
	 */
	@Test
	public void testMultipleBreakpointsHitInOneStep() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		
		Activity activity = ActivityFactory.createActivity("testMultipleBreakpointsHitInOneStep");
		InitialNode initial = ActivityFactory.createInitialNode(activity, "InitialNode");
		ForkNode fork = ActivityFactory.createForkNode(activity, "ForkNode");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
		
		ActivityFactory.createControlFlow(activity, initial, fork);
		ActivityFactory.createControlFlow(activity, fork, create1);
		ActivityFactory.createControlFlow(activity, fork, create2);
		ActivityFactory.createControlFlow(activity, fork, create3);
		
		// Set Breakpoint
		BreakpointImpl breakpointcreate1 = new BreakpointImpl(create1);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate1);
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1= ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(initial, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initial, initialentry.getNode());	
		assertEquals(activityentry, initialentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialentry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent forkentry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(fork, forkentry.getNode());	
		assertEquals(activityentry, forkentry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(forkentry, ((TraceEvent)eventlist.get(5)).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(2, create1breakpoint.getBreakpoints().size());
		assertTrue(create1breakpoint.getBreakpoints().contains(breakpointcreate1));
		assertTrue(create1breakpoint.getBreakpoints().contains(breakpointcreate2));
		assertEquals(activityentry, create1breakpoint.getParent());		
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(6));
		assertEquals(fork, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(3, step2.getNewEnabledNodes().size());
		assertTrue(step2.getNewEnabledNodes().contains(create1));
		assertTrue(step2.getNewEnabledNodes().contains(create2));
		assertTrue(step2.getNewEnabledNodes().contains(create3));
		
		assertEquals(3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));						
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create1);
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(8)).getParent());
		assertTrue(eventlist.get(9) instanceof SuspendEvent);
		SuspendEvent step3 = ((SuspendEvent)eventlist.get(9));
		assertEquals(create1, step3.getLocation());
		assertEquals(activityentry, step3.getParent());
		assertEquals(0, step3.getNewEnabledNodes().size());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create2);
		
		assertEquals(13, eventlist.size());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(10);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(11)).getParent());
		assertTrue(eventlist.get(12) instanceof SuspendEvent);
		SuspendEvent step4 = ((SuspendEvent)eventlist.get(12));
		assertEquals(create2, step4.getLocation());
		assertEquals(activityentry, step4.getParent());
		assertEquals(0, step4.getNewEnabledNodes().size());
		
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
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create3);
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(13);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(14)).getParent());
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(15)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));	
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction1 and CreateObjectAction2.
	 * Therefore two BreakPointEvents should be sent right after starting the Activity.
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * CreateObjectAction3 (class = Class3)  
	 */
	@Test
	public void testMultipleBreakpointsHitInOneStep2() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		
		Activity activity = ActivityFactory.createActivity("testMultipleBreakpointsHitInOneStep2");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class3", class3);
				
		// Set Breakpoint
		BreakpointImpl breakpointcreate1 = new BreakpointImpl(create1);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate1);
		BreakpointImpl breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(2, create1breakpoint.getBreakpoints().size());
		assertTrue(create1breakpoint.getBreakpoints().contains(breakpointcreate1));
		assertTrue(create1breakpoint.getBreakpoints().contains(breakpointcreate2));
		assertEquals(activityentry, create1breakpoint.getParent());		
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(3, step1.getNewEnabledNodes().size());
		assertTrue(step1.getNewEnabledNodes().contains(create1));
		assertTrue(step1.getNewEnabledNodes().contains(create2));
		assertTrue(step1.getNewEnabledNodes().contains(create3));
	
		assertEquals(3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
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
		assertEquals(0, step2.getNewEnabledNodes().size());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));
		
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
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create3);
		
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
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));	
		o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class2, o2.getTypes().get(0));
		Object_ o3 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class3, o3.getTypes().get(0));

		// All events have the same activityExecutionID
		assertTrue(checkSameActivityExecutionID(eventlist));
	}
	
	/**
	 * Breakpoints are set on different places of the activity:
	 * (1) CallBehaviorAction
	 * (2) CreateObjectAction3
	 * (3) CreateObjectAction4
	 * (4) CreateObjectAction2
	 * (5) CallBehaviorAction, CreateObjectAction3, CreateObjectAction4, CreateObjectAction2
	 * (6) CreateObjectAction3, CreateObjectAction4 with deletion of control flow edge between these nodes
	 * After a breakpoint is hit, resume is called. 
	 * 
	 * Activity 1:
	 * CreateObjectAction1 (class = Class1)
	 * CallBehaviorAction (activity = Activity 2)
	 * CreateObjectAction2 (class = Class2)
	 *
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CallBehaviorAction
	 * CallBehaviorAction --> CreateObjectAction2
	 * 
	 * Activity 2:
	 * CreateObjectAction3 (class = Class3)
	 * CreateObjectAction4 (class = Class4)
	 *
	 * Activity ControlFlow:
	 * CreateObjectAction3 --> CreateObjectAction4
	 */	
	@Test
	public void testCallBehaviorAction() {		
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		
		Activity activity2 = ActivityFactory.createActivity("testCallBehaviorAction activity2");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity2, "CreateObject Class3", class3);
		CreateObjectAction create4 = ActivityFactory.createCreateObjectAction(activity2, "CreateObject Class4", class4);
		ControlFlow create3create4 = ActivityFactory.createControlFlow(activity2, create3, create4);
		
		Activity activity1 = ActivityFactory.createActivity("testCallBehaviorAction activity1");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity1, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity1, "CreateObject Class2", class2);
		CallBehaviorAction call = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction activity=activity2", activity2);
		ActivityFactory.createControlFlow(activity1, create1, call);
		ActivityFactory.createControlFlow(activity1, call, create2);

		
		/*
		 * Execution with breakpoint for CallBehaviorAction
		 */
		
		// Set Breakpoint
		Breakpoint breakpointcall = new BreakpointImpl(call);
		ExecutionContext.getInstance().addBreakpoint(breakpointcall);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		SuspendEvent step1 = ((SuspendEvent)eventlist.get(1));
		assertEquals(activity1, step1.getLocation());
		assertEquals(activityentry, step1.getParent());
		assertEquals(1, step1.getNewEnabledNodes().size());
		assertEquals(create1, step1.getNewEnabledNodes().get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, ((TraceEvent)eventlist.get(3)).getParent());
					
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent breakpointeventcall = (BreakpointEvent)eventlist.get(4);
		assertEquals(1, breakpointeventcall.getBreakpoints().size());
		assertEquals(breakpointcall, breakpointeventcall.getBreakpoints().get(0));
		assertEquals(activityentry, breakpointeventcall.getParent());
		SuspendEvent step2 = ((SuspendEvent)eventlist.get(4));
		assertEquals(create1, step2.getLocation());
		assertEquals(activityentry, step2.getParent());
		assertEquals(1, step2.getNewEnabledNodes().size());
		assertEquals(call, step2.getNewEnabledNodes().get(0));
		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(call, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent callentry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(call, callentry.getNode());	
		assertTrue(eventlist.get(6) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry = ((ActivityEntryEvent)eventlist.get(6));		
		assertEquals(activity2, activity2entry.getActivity());		
		assertEquals(callentry, activity2entry.getParent());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activity2entry, create3entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create3entry, ((TraceEvent)eventlist.get(8)).getParent());	
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(9);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activity2entry, create4entry.getParent());
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		assertEquals(create4entry, ((TraceEvent)eventlist.get(10)).getParent());
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activity2entry, ((TraceEvent)eventlist.get(11)).getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(callentry, ((TraceEvent)eventlist.get(12)).getParent());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(13);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		assertEquals(create2entry, ((TraceEvent)eventlist.get(14)).getParent());	
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		assertEquals(activityentry, ((TraceEvent)eventlist.get(15)).getParent());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		Object_ o2 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(1));
		assertEquals(1, o2.getTypes().size());
		assertEquals(class3, o2.getTypes().get(0));
		Object_ o3= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(2));
		assertEquals(1, o3.getTypes().size());
		assertEquals(class4, o3.getTypes().get(0));		
		Object_ o4= (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(3));
		assertEquals(1, o4.getTypes().size());
		assertEquals(class2, o4.getTypes().get(0));	
		
		int executionIDactivity1 = activityentry.getActivityExecutionID();
		int executionIDactivity2 = activity2entry.getActivityExecutionID();
		assertTrue(executionIDactivity1 != executionIDactivity2);
		for(int i=0;i<6;++i) {
			assertEquals(executionIDactivity1, ((TraceEvent)eventlist.get(i)).getActivityExecutionID());
		}
		for(int i=6;i<12;++i){
			assertEquals(executionIDactivity2, ((TraceEvent)eventlist.get(i)).getActivityExecutionID());
		}
		for(int i=12;i<16;++i) {
			assertEquals(executionIDactivity1, ((TraceEvent)eventlist.get(i)).getActivityExecutionID());
		}
		
		/*
		 * Execution with breakpoint for CreateObjectAction3
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Set Breakpoint		
		Breakpoint breakpointcreate3 = new BreakpointImpl(create3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
							
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(6)).getBreakpoints().size());
		assertEquals(breakpointcreate3, ((BreakpointEvent)eventlist.get(6)).getBreakpoints().get(0));
		assertEquals(activity2, ((SuspendEvent)eventlist.get(6)).getLocation());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());	
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());

		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(11)).getActivity());

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(13)).getNode());	
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		/*
		 * Execution with breakpoint for CreateObjectAction4
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Set Breakpoint		
		Breakpoint breakpointcreate4 = new BreakpointImpl(create4);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate4);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
							
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().size());
		assertEquals(breakpointcreate4, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().get(0));
		assertEquals(create3, ((SuspendEvent)eventlist.get(8)).getLocation());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());
		assertEquals(create4, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());
							
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());

		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(11)).getActivity());

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(13)).getNode());	
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		/*
		 * Execution with breakpoint for CreateObjectAction2
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Set Breakpoint		
		Breakpoint breakpointcreate2 = new BreakpointImpl(create2);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(13, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
							
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());				
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	

		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());

		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(10)).getActivity());

		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		
		assertTrue(eventlist.get(12) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(12)).getBreakpoints().size());
		assertEquals(breakpointcreate2, ((BreakpointEvent)eventlist.get(12)).getBreakpoints().get(0));
		assertEquals(call, ((SuspendEvent)eventlist.get(12)).getLocation());		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());		
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());							
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(13)).getNode());	
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		/*
		 * Execution with breakpoint for CreateObjectAction2
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Set Breakpoint		
		ExecutionContext.getInstance().addBreakpoint(breakpointcall);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate4);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(5, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(4)).getBreakpoints().size());
		assertEquals(breakpointcall, ((BreakpointEvent)eventlist.get(4)).getBreakpoints().get(0));
		assertEquals(create1, ((SuspendEvent)eventlist.get(4)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(call, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));				
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(8, eventlist.size());	
			
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	

		assertTrue(eventlist.get(6) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(6));		
		assertEquals(activity2, activity2entry.getActivity());	
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().size());
		assertEquals(breakpointcreate3, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().get(0));
		assertEquals(activity2, ((SuspendEvent)eventlist.get(7)).getLocation());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());		
		assertEquals(create3, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(11, eventlist.size());						
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());				
		
		assertTrue(eventlist.get(10) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(10)).getBreakpoints().size());
		assertEquals(breakpointcreate4, ((BreakpointEvent)eventlist.get(10)).getBreakpoints().get(0));
		assertEquals(create3, ((SuspendEvent)eventlist.get(10)).getLocation());
				
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());		
		assertEquals(create4, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());		
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());	

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());

		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(13)).getActivity());

		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(15)).getBreakpoints().size());
		assertEquals(breakpointcreate2, ((BreakpointEvent)eventlist.get(15)).getBreakpoints().get(0));
		assertEquals(call, ((SuspendEvent)eventlist.get(15)).getLocation());	
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());		
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());		
				
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(19, eventlist.size());		
				
		assertTrue(eventlist.get(16) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(16)).getNode());	
		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(17)).getNode());
		
		assertTrue(eventlist.get(18) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(18)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		/*
		 * Execution with breakpoint for CreateObjectAction3 and CreateObjectAction4
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Delete control flow edge from create object action 3 to create object action 4
		create3create4.source.outgoing.remove(create3create4);
		create3create4.target.incoming.remove(create3create4);
		activity2.edge.remove(create3create4);
				
		// Set Breakpoint		
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate4);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(call, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
							
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		assertEquals(2, ((BreakpointEvent)eventlist.get(6)).getBreakpoints().size());
		assertTrue(((BreakpointEvent)eventlist.get(6)).getBreakpoints().contains(breakpointcreate3));
		assertTrue(((BreakpointEvent)eventlist.get(6)).getBreakpoints().contains(breakpointcreate4));
		assertEquals(activity2, ((SuspendEvent)eventlist.get(6)).getLocation());		
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).contains(create3));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).contains(create4));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());	
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());

		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(11)).getActivity());

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(call, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(13)).getNode());	
		
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());
	}	
	
	/**
	 * Breakpoints are set on different places of the activity:
	 * (1) CallBehaviorAction activity = activity 3
	 * (5) CreateObjectAction5
	 * After a breakpoint is hit, resume is called. 
	 * 
	 * Activity 1:
	 * CreateObjectAction1 (class = Class1)
	 * CallBehaviorAction (activity = Activity 2)
	 * CreateObjectAction2 (class = Class2)
	 *
	 * Activity 1 ControlFlow:
	 * CreateObjectAction1 --> CallBehaviorAction
	 * CallBehaviorAction --> CreateObjectAction2
	 * 
	 * Activity 2:
	 * CreateObjectAction3 (class = Class3)
	 * CallBehaviorAction (activity = Activity 3)
	 * CreateObjectAction4 (class = Class4)
	 *
	 * Activity 2 ControlFlow:
	 * CreateObjectAction3 --> CallBehaviorAction 
	 * CallBehaviorAction  --> CreateObjectAction4
	 * 
	 * Activity 3:
	 * CreateObjectAction5 (class = Class5)
	 */	
	@Test
	public void testCallBehaviorActionNesting() {		
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		Class_ class4 = ActivityFactory.createClass("Class4");
		Class_ class5 = ActivityFactory.createClass("Class5");
		
		Activity activity3 = ActivityFactory.createActivity("testCallBehaviorAction activity3");
		CreateObjectAction create5 = ActivityFactory.createCreateObjectAction(activity3, "CreateObject Class5", class5);
		
		Activity activity2 = ActivityFactory.createActivity("testCallBehaviorAction activity2");
		CreateObjectAction create3 = ActivityFactory.createCreateObjectAction(activity2, "CreateObject Class3", class3);
		CallBehaviorAction callactivity3 = ActivityFactory.createCallBehaviorAction(activity2, "CallBehaviorAction activity=activity3", activity3);
		CreateObjectAction create4 = ActivityFactory.createCreateObjectAction(activity2, "CreateObject Class4", class4);
		ActivityFactory.createControlFlow(activity2, create3, callactivity3);
		ActivityFactory.createControlFlow(activity2, callactivity3, create4);
		
		Activity activity1 = ActivityFactory.createActivity("testCallBehaviorAction activity1");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity1, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity1, "CreateObject Class2", class2);
		CallBehaviorAction callactivity2 = ActivityFactory.createCallBehaviorAction(activity1, "CallBehaviorAction activity=activity2", activity2);
		ActivityFactory.createControlFlow(activity1, create1, callactivity2);
		ActivityFactory.createControlFlow(activity1, callactivity2, create2);		
		
		/*
		 * Execution with breakpoint for CallBehaviorAction (activity = activity3)
		 */		
		
		// Set Breakpoint		
		Breakpoint breakpointcallactivity3 = new BreakpointImpl(callactivity3);
		ExecutionContext.getInstance().addBreakpoint(breakpointcallactivity3);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(callactivity2, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());	
		
		assertTrue(eventlist.get(8) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().size());
		assertEquals(breakpointcallactivity3, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().get(0));
		assertEquals(create3, ((SuspendEvent)eventlist.get(8)).getLocation());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());
		assertEquals(callactivity3, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(22, eventlist.size());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(callactivity3, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	

		assertTrue(eventlist.get(10) instanceof ActivityEntryEvent);
		ActivityEntryEvent activity3entry = ((ActivityEntryEvent)eventlist.get(10));		
		assertEquals(activity3, activity3entry.getActivity());

		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(create5, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());	

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create5, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());

		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity3, ((ActivityExitEvent)eventlist.get(13)).getActivity());

		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(callactivity3, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(15)).getNode());	
		
		assertTrue(eventlist.get(16) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(16)).getNode());
		
		assertTrue(eventlist.get(17) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(17)).getActivity());
		
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(callactivity2, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		
		assertTrue(eventlist.get(19) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(19)).getNode());	
		
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(20)).getNode());
		
		assertTrue(eventlist.get(21) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(21)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(5, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		/*
		 * Execution with breakpoint for CreateObjectAction5
		 */
		try {
			setUp();			
		} catch (Exception e) {
			fail("error in setup");
		}
		assertEquals(0, eventlist.size());
		
		// Set Breakpoint		
		Breakpoint breakpointcreate5 = new BreakpointImpl(create5);
		ExecutionContext.getInstance().addBreakpoint(breakpointcreate5);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity1, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity1, activityentry.getActivity());		

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity1, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		assertEquals(callactivity2, ((ActivityNodeEntryEvent)eventlist.get(4)).getNode());	

		assertTrue(eventlist.get(5) instanceof ActivityEntryEvent);
		activity2entry = ((ActivityEntryEvent)eventlist.get(5));		
		assertEquals(activity2, activity2entry.getActivity());		
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(create3, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());					
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(callactivity3, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	

		assertTrue(eventlist.get(9) instanceof ActivityEntryEvent);
		activity3entry = ((ActivityEntryEvent)eventlist.get(9));		
		assertEquals(activity3, activity3entry.getActivity());

		assertTrue(eventlist.get(10) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(10)).getBreakpoints().size());
		assertEquals(breakpointcreate5, ((BreakpointEvent)eventlist.get(10)).getBreakpoints().get(0));
		assertEquals(activity3, ((SuspendEvent)eventlist.get(10)).getLocation());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activity2entry.getActivityExecutionID()).size());		
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activity3entry.getActivityExecutionID()).size());
		assertEquals(create5, ExecutionContext.getInstance().getEnabledNodes(activity3entry.getActivityExecutionID()).get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(22, eventlist.size());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		assertEquals(create5, ((ActivityNodeEntryEvent)eventlist.get(11)).getNode());	

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create5, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());

		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity3, ((ActivityExitEvent)eventlist.get(13)).getActivity());

		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(callactivity3, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof ActivityNodeEntryEvent);
		assertEquals(create4, ((ActivityNodeEntryEvent)eventlist.get(15)).getNode());	
		
		assertTrue(eventlist.get(16) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(16)).getNode());
		
		assertTrue(eventlist.get(17) instanceof ActivityExitEvent);
		assertEquals(activity2, ((ActivityExitEvent)eventlist.get(17)).getActivity());
		
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(callactivity2, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		
		assertTrue(eventlist.get(19) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(19)).getNode());	
		
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(20)).getNode());
		
		assertTrue(eventlist.get(21) instanceof ActivityExitEvent);
		assertEquals(activity1, ((ActivityExitEvent)eventlist.get(21)).getActivity());
		
		assertEquals(0, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		
		assertEquals(5, extensionalValueLists.get(extensionalValueLists.size()-1).size());
	}
	
	@Override
	public void notify(Event event) {
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
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
			
}
