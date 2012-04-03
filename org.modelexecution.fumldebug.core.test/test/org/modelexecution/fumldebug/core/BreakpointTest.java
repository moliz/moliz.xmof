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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.test.Return5BehaviorExecution;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Actions.BasicActions.CallBehaviorActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.BasicActions.OutputPinList;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * @author Tanja Mayerhofer
 *
 */
public class BreakpointTest implements ExecutionEventListener{

	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();
	
	public BreakpointTest() {
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
		ExecutionContext.getInstance().activityExecutionOutput = new HashMap<ActivityExecution, ParameterValueList>();
		ExecutionContext.getInstance().activityExecutions = new HashMap<Integer, ActivityExecution>();
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
		ExecutionContext.getInstance().addBreakpoint(create2);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());
		
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(5)).getLocation());
		assertNull(eventlist.get(5).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create2entry, eventlist.get(7).getParent());	
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, eventlist.get(9).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());
		assertEquals(activityentry, eventlist.get(10).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create3);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, eventlist.get(5).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(create3, create3breakpoint.getNode());
		assertEquals(activityentry, create3breakpoint.getParent());
		
		assertTrue(eventlist.get(7) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(7)).getLocation());
		assertNull(eventlist.get(7).getParent());
		
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
		
		assertEquals(11, eventlist.size());
				
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, eventlist.get(9).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());
		assertEquals(activityentry, eventlist.get(10).getParent());
		
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
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
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
		assertEquals(create1entry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, eventlist.get(5).getParent());			
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create3entry, eventlist.get(7).getParent());
		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, eventlist.get(8).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create1);
		ExecutionContext.getInstance().addBreakpoint(create2);
		ExecutionContext.getInstance().addBreakpoint(create3);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(3, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(create1, create1breakpoint.getNode());
		assertEquals(activityentry, create1breakpoint.getParent());
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(2)).getLocation());
		assertNull(eventlist.get(2).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(3);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertEquals(create1entry, eventlist.get(4).getParent());
					
		assertTrue(eventlist.get(5) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(5);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());	
		assertTrue(eventlist.get(6) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(6)).getLocation());
		assertNull(eventlist.get(6).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(11, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create2entry, eventlist.get(8).getParent());	
		
		assertTrue(eventlist.get(9) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(9);
		assertEquals(create3, create3breakpoint.getNode());
		assertEquals(activityentry, create3breakpoint.getParent());	
		assertTrue(eventlist.get(10) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(10)).getLocation());
		assertNull(eventlist.get(10).getParent());
		
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
		
		assertEquals(14, eventlist.size());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(11);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
		assertEquals(create3entry, eventlist.get(12).getParent());
		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(13)).getActivity());
		assertEquals(activityentry, eventlist.get(13).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create1);
		ExecutionContext.getInstance().addBreakpoint(create3);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(3, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(create1, create1breakpoint.getNode());
		assertEquals(activityentry, create1breakpoint.getParent());
		assertTrue(eventlist.get(2) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(2)).getLocation());
		assertNull(eventlist.get(2).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(3);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertEquals(create1entry, eventlist.get(4).getParent());							
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create2entry, eventlist.get(6).getParent());	
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(7);
		assertEquals(create3, create3breakpoint.getNode());
		assertEquals(activityentry, create3breakpoint.getParent());	
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(8)).getLocation());
		assertNull(eventlist.get(8).getParent());
		
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
		
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(9);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		assertEquals(create3entry, eventlist.get(10).getParent());
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activityentry, eventlist.get(11).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create2);
		ExecutionContext.getInstance().addBreakpoint(create4);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, eventlist.get(3).getParent());							
		
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());	
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(5)).getLocation());
		assertNull(eventlist.get(5).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
				
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create2entry, eventlist.get(7).getParent());	
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, eventlist.get(9).getParent());	
		
		assertTrue(eventlist.get(10) instanceof BreakpointEvent);
		BreakpointEvent create4breakpoint = (BreakpointEvent)eventlist.get(10);
		assertEquals(create4, create4breakpoint.getNode());
		assertEquals(activityentry, create4breakpoint.getParent());	
		assertTrue(eventlist.get(11) instanceof StepEvent);
		assertEquals(create3, ((StepEvent)eventlist.get(11)).getLocation());
		assertNull(eventlist.get(11).getParent());
		
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
		
		assertEquals(15, eventlist.size());
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(12);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());
		assertEquals(create4entry, eventlist.get(13).getParent());
		assertTrue(eventlist.get(14) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(14)).getActivity());
		assertEquals(activityentry, eventlist.get(14).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create2);
		ExecutionContext.getInstance().addBreakpoint(create4);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, eventlist.get(3).getParent());							
		
		assertTrue(eventlist.get(4) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(4);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());	
		assertTrue(eventlist.get(5) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(5)).getLocation());
		assertNull(eventlist.get(5).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
				
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create2entry, eventlist.get(7).getParent());	
		
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(8)).getLocation());
		assertNull(eventlist.get(8).getParent());
		
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
				
		assertEquals(13, eventlist.size());
				
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(9);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		assertEquals(create3entry, eventlist.get(10).getParent());	
		
		assertTrue(eventlist.get(11) instanceof BreakpointEvent);
		BreakpointEvent create4breakpoint = (BreakpointEvent)eventlist.get(11);
		assertEquals(create4, create4breakpoint.getNode());
		assertEquals(activityentry, create4breakpoint.getParent());	
		assertTrue(eventlist.get(12) instanceof StepEvent);
		assertEquals(create3, ((StepEvent)eventlist.get(12)).getLocation());
		assertNull(eventlist.get(12).getParent());
		
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
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(13);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		assertEquals(create4entry, eventlist.get(14).getParent());
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(15)).getActivity());
		assertEquals(activityentry, eventlist.get(15).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create2);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
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
		assertEquals(initialentry, eventlist.get(3).getParent());							
		assertTrue(eventlist.get(4) instanceof StepEvent);
		assertEquals(initial, ((StepEvent)eventlist.get(4)).getLocation());
		assertNull(eventlist.get(4).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());				
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(5);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		assertEquals(create1entry, eventlist.get(6).getParent());							
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(7);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());	
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(8)).getLocation());
		assertNull(eventlist.get(8).getParent());
		
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));
		
		// Next step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID());
				
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(9);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		assertEquals(create2entry, eventlist.get(10).getParent());			
		assertTrue(eventlist.get(11) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(11)).getLocation());
		assertNull(eventlist.get(11).getParent());
		
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
				
		assertEquals(17, eventlist.size());
				
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(12);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());
		assertEquals(create3entry, eventlist.get(13).getParent());		
		assertTrue(eventlist.get(14) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create4entry = (ActivityNodeEntryEvent)eventlist.get(14);
		assertEquals(create4, create4entry.getNode());	
		assertEquals(activityentry, create4entry.getParent());
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(create4, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		assertEquals(create4entry, eventlist.get(15).getParent());
		assertTrue(eventlist.get(16) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(16)).getActivity());
		assertEquals(activityentry, eventlist.get(16).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create3);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(create1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(create1entry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, eventlist.get(5).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create3breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(create3, create3breakpoint.getNode());
		assertEquals(activityentry, create3breakpoint.getParent());
		
		assertTrue(eventlist.get(7) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(7)).getLocation());
		assertNull(eventlist.get(7).getParent());
		
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
		
		assertEquals(11, eventlist.size());
				
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(8);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		assertEquals(create3entry, eventlist.get(9).getParent());
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(10)).getActivity());
		assertEquals(activityentry, eventlist.get(10).getParent());
		
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
		ExecutionContext.getInstance().removeBreakpoint(create3);
		
		// Reset data structures
		eventlist = new ArrayList<Event>();
		extensionalValueLists = new ArrayList<ExtensionalValueList>();
				
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
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
		assertEquals(create1entry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		create2entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create2entry, eventlist.get(5).getParent());	
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		create3entry = (ActivityNodeEntryEvent)eventlist.get(6);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		assertEquals(create3entry, eventlist.get(7).getParent());
		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, eventlist.get(8).getParent());
		
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
	}
	
	/**
	 * Breakpoints are set for CreateObjectAction1 and CreateObjectAction2.
	 * Therefore after the execution of ForkNode, two BreakPointEvents should be thrown
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
		ExecutionContext.getInstance().addBreakpoint(create1);
		ExecutionContext.getInstance().addBreakpoint(create2);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		assertTrue(eventlist.get(1) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(1)).getLocation());
		assertNull(eventlist.get(1).getParent());
	
		assertEquals(1, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertEquals(initial, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).get(0));
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent initialentry = (ActivityNodeEntryEvent)eventlist.get(2);
		assertEquals(initial, initialentry.getNode());	
		assertEquals(activityentry, initialentry.getParent());
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		assertEquals(initial, ((ActivityNodeExitEvent)eventlist.get(3)).getNode());
		assertEquals(initialentry, eventlist.get(3).getParent());
					
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent forkentry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(fork, forkentry.getNode());	
		assertEquals(activityentry, forkentry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(fork, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(forkentry, eventlist.get(5).getParent());	
		
		assertTrue(eventlist.get(6) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(6);
		assertEquals(create1, create1breakpoint.getNode());
		assertEquals(activityentry, create1breakpoint.getParent());		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(7);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());
		
		assertTrue(eventlist.get(8) instanceof StepEvent);
		assertEquals(fork, ((StepEvent)eventlist.get(8)).getLocation());
		assertNull(eventlist.get(8).getParent());
		
		assertEquals(3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));						
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create1);
		
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(9);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		assertEquals(create1entry, eventlist.get(10).getParent());
		assertTrue(eventlist.get(11) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(11)).getLocation());
		assertNull(eventlist.get(11).getParent());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create2);
		
		assertEquals(15, eventlist.size());
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(12);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(13)).getNode());
		assertEquals(create2entry, eventlist.get(13).getParent());
		assertTrue(eventlist.get(14) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(14)).getLocation());
		assertNull(eventlist.get(14).getParent());
		
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
		
		assertEquals(18, eventlist.size());
		
		assertTrue(eventlist.get(15) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(15);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(16) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(16)).getNode());
		assertEquals(create3entry, eventlist.get(16).getParent());
		assertTrue(eventlist.get(17) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(17)).getActivity());
		assertEquals(activityentry, eventlist.get(17).getParent());
		
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
		ExecutionContext.getInstance().addBreakpoint(create1);
		ExecutionContext.getInstance().addBreakpoint(create2);
		
		// Start Debugging
		ExecutionContext.getInstance().debug(activity, null, new ParameterValueList());
				
		assertEquals(4, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof BreakpointEvent);
		BreakpointEvent create1breakpoint = (BreakpointEvent)eventlist.get(1);
		assertEquals(create1, create1breakpoint.getNode());
		assertEquals(activityentry, create1breakpoint.getParent());		
		assertTrue(eventlist.get(2) instanceof BreakpointEvent);
		BreakpointEvent create2breakpoint = (BreakpointEvent)eventlist.get(2);
		assertEquals(create2, create2breakpoint.getNode());
		assertEquals(activityentry, create2breakpoint.getParent());
		
		assertTrue(eventlist.get(3) instanceof StepEvent);
		assertNull(((StepEvent)eventlist.get(3)).getLocation());
		assertNull(eventlist.get(3).getParent());
	
		assertEquals(3, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create1));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));		
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());			
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create1);
		
		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create1entry = (ActivityNodeEntryEvent)eventlist.get(4);
		assertEquals(create1, create1entry.getNode());	
		assertEquals(activityentry, create1entry.getParent());
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());
		assertEquals(create1entry, eventlist.get(5).getParent());
		assertTrue(eventlist.get(6) instanceof StepEvent);
		assertEquals(create1, ((StepEvent)eventlist.get(6)).getLocation());
		assertNull(eventlist.get(6).getParent());
		
		assertEquals(2, ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).size());
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create2));
		assertTrue(ExecutionContext.getInstance().getEnabledNodes(activityentry.getActivityExecutionID()).contains(create3));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		Object_ o1 = (Object_)(extensionalValueLists.get(extensionalValueLists.size()-1).get(0));
		assertEquals(1, o1.getTypes().size());
		assertEquals(class1, o1.getTypes().get(0));				
		
		// Next Step
		ExecutionContext.getInstance().nextStep(activityentry.getActivityExecutionID(), create2);
		
		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create2entry = (ActivityNodeEntryEvent)eventlist.get(7);
		assertEquals(create2, create2entry.getNode());	
		assertEquals(activityentry, create2entry.getParent());
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(create2entry, eventlist.get(8).getParent());
		assertTrue(eventlist.get(9) instanceof StepEvent);
		assertEquals(create2, ((StepEvent)eventlist.get(9)).getLocation());
		assertNull(eventlist.get(9).getParent());
		
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
		
		assertEquals(13, eventlist.size());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		ActivityNodeEntryEvent create3entry = (ActivityNodeEntryEvent)eventlist.get(10);
		assertEquals(create3, create3entry.getNode());	
		assertEquals(activityentry, create3entry.getParent());
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(create3, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		assertEquals(create3entry, eventlist.get(11).getParent());
		assertTrue(eventlist.get(12) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(12)).getActivity());
		assertEquals(activityentry, eventlist.get(12).getParent());
		
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
