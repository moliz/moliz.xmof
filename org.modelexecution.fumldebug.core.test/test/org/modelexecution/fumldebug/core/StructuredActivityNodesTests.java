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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;

/**
 * @author Tanja Mayerhofer
 *
 */
public class StructuredActivityNodesTests extends MolizTest implements ExecutionEventListener{

	private List<Event> eventlist = new ArrayList<Event>();
	
	public StructuredActivityNodesTests() {
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
		registerPrimitiveBehaviors(ExecutionContext.getInstance());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void testExpansionRegion_nestedExpanionRegion() {
		Activity activity = ActivityFactory.createActivity("testExpansionRegion_nestedExpanionRegion");
		Parameter parameter1 = ActivityFactory.createParameter(activity, "parameter1",  ParameterDirectionKind.in);
		ActivityParameterNode parameterNode1 = ActivityFactory.createActivityParameterNode(activity, "parameterNode1", parameter1);
		Parameter parameter2 = ActivityFactory.createParameter(activity, "parameter2",  ParameterDirectionKind.in);
		ActivityParameterNode parameterNode2 = ActivityFactory.createActivityParameterNode(activity, "parameterNode2", parameter2);
		Parameter parameter3 = ActivityFactory.createParameter(activity, "parameter3",  ParameterDirectionKind.out);
		ActivityParameterNode parameterNode3 = ActivityFactory.createActivityParameterNode(activity, "parameterNode3", parameter3);
		ExpansionRegion expansionRegionOuter = ActivityFactory.createExpansionRegion(activity, "outer expansionRegion", ExpansionKind.iterative, new ArrayList<ActivityNode>(), 1, 1, 1);
		ExpansionRegion expansionRegionInner = ActivityFactory.createExpansionRegion(activity, "inner expansionRegion", ExpansionKind.iterative, new ArrayList<ActivityNode>(), 1, 1);
		expansionRegionOuter.input.get(0).setUpper(-1);
		expansionRegionOuter.addNode(expansionRegionInner);
		expansionRegionOuter.addNode(expansionRegionInner.inputElement.get(0));
		expansionRegionOuter.addNode(expansionRegionInner.outputElement.get(0));
		ActivityFactory.createObjectFlow(activity, parameterNode1, expansionRegionOuter.inputElement.get(0));
		ActivityFactory.createObjectFlow(activity, parameterNode2, expansionRegionOuter.input.get(0));
		ActivityFactory.createObjectFlow(activity, expansionRegionOuter.outputElement.get(0), parameterNode3);
		ActivityFactory.createObjectFlow(expansionRegionOuter, expansionRegionOuter.inputElement.get(0), expansionRegionOuter.outputElement.get(0));
		ActivityFactory.createObjectFlow(expansionRegionOuter, expansionRegionOuter.input.get(0), expansionRegionInner.inputElement.get(0));
		ActivityFactory.createObjectFlow(expansionRegionInner, expansionRegionInner.inputElement.get(0), expansionRegionInner.outputElement.get(0));
		ActivityFactory.createObjectFlow(expansionRegionOuter, expansionRegionInner.outputElement.get(0), expansionRegionOuter.outputElement.get(0));
		
		StringValue str1 = ActivityFactory.createStringValue("str1");
		StringValue str2 = ActivityFactory.createStringValue("str2");
		StringValue str3 = ActivityFactory.createStringValue("str3");
		StringValue str4 = ActivityFactory.createStringValue("str4");
		ParameterValue valuesParameter1 = ActivityFactory.createParameterValue(parameter1, str1, str2);
		ParameterValue valuesParameter2 = ActivityFactory.createParameterValue(parameter2, str3, str4);
		ParameterValueList parameterValues = ActivityFactory.createParameterValueList(valuesParameter1, valuesParameter2);
		
		ExecutionContext.getInstance().execute(activity, null, parameterValues);	
		//ParameterValueList output = ExecutionContext.getInstance().getLocus().executor.execute(activity, null, parameterValues);
		int activityExecutionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ParameterValueList output = ExecutionContext.getInstance().getActivityOutput(activityExecutionID);
		
		assertEquals(1, output.size());
		assertEquals(6, output.get(0).values);
	}
	
	@Test
	@Ignore
	public void testLoopNode1_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.LoopNodeTestActivity1 testactivity = factory.new LoopNodeTestActivity1();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();	
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.loopnode);
		
		// first iteration - body
		ExecutionContext.getInstance().nextStep(executionID, testactivity.createobject);

		// first iteration - test
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.callsubtract);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify0);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testid);
		
		// second iteration - body
		ExecutionContext.getInstance().nextStep(executionID, testactivity.createobject);
		
		// second iteration - test
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.callsubtract);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify0);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testid);
		
		
		// check events
		assertEquals(26, eventlist.size());	

		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_specify2, entry_loop, entry_create, entry_specify1, entry_subtract, entry_specify0, entry_testid;
		ActivityNodeExitEvent exit_specify2, exit_loop, exit_create, exit_specify1, exit_subtract, exit_specify0, exit_testid;

		SuspendEvent suspend_activity, suspend_specify2, suspend_loop, suspend_create, suspend_specify1, suspend_subtract, suspend_specify0, suspend_testid = null;

		int i=-1;
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specify2 = (ActivityNodeEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specify2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specify2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_loop = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_loop = (SuspendEvent)eventlist.get(i);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		
		assertTrue(checkActivityNodeEntryEvent(entry_specify2, testactivity.specify2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_loop, testactivity.loopnode, entry_activity));
		
		assertTrue(checkActivityNodeExitEvent(exit_specify2, testactivity.specify2, entry_specify2));

		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.specify2));
		assertTrue(checkSuspendEvent(suspend_specify2, testactivity.specify2, entry_activity, testactivity.loopnode));
		assertTrue(checkSuspendEvent(suspend_loop, testactivity.loopnode, entry_activity, testactivity.createobject));
		
		for(int j=0;j<2;++j) {
			assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
			entry_create = (ActivityNodeEntryEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
			exit_create = (ActivityNodeExitEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof SuspendEvent);
			suspend_create = (SuspendEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
			entry_specify1 = (ActivityNodeEntryEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
			exit_specify1 = (ActivityNodeExitEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof SuspendEvent);
			suspend_specify1 = (SuspendEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
			entry_subtract = (ActivityNodeEntryEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
			exit_subtract = (ActivityNodeExitEvent)eventlist.get(i);		
			assertTrue(eventlist.get(++i) instanceof SuspendEvent);
			suspend_subtract = (SuspendEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
			entry_specify0 = (ActivityNodeEntryEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
			exit_specify0 = (ActivityNodeExitEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof SuspendEvent);
			suspend_specify0 = (SuspendEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
			entry_testid = (ActivityNodeEntryEvent)eventlist.get(i);
			assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
			exit_testid = (ActivityNodeExitEvent)eventlist.get(i);
			if(j==0) {
				assertTrue(eventlist.get(++i) instanceof SuspendEvent);
				suspend_testid = (SuspendEvent)eventlist.get(i);
			}
			
			assertTrue(checkActivityNodeEntryEvent(entry_create, testactivity.createobject, entry_activity));
			assertTrue(checkActivityNodeEntryEvent(entry_specify1, testactivity.specify1, entry_activity));
			assertTrue(checkActivityNodeEntryEvent(entry_subtract, testactivity.callsubtract, entry_activity));
			assertTrue(checkActivityNodeEntryEvent(entry_specify0, testactivity.specify0, entry_activity));
			assertTrue(checkActivityNodeEntryEvent(entry_testid, testactivity.testid, entry_activity));
			
			assertTrue(checkActivityNodeExitEvent(exit_create, testactivity.createobject, entry_create));
			assertTrue(checkActivityNodeExitEvent(exit_specify1, testactivity.specify1, entry_specify1));
			assertTrue(checkActivityNodeExitEvent(exit_subtract, testactivity.callsubtract, entry_subtract));
			assertTrue(checkActivityNodeExitEvent(exit_specify0, testactivity.specify0, entry_specify0));
			assertTrue(checkActivityNodeExitEvent(exit_testid, testactivity.testid, entry_testid));
			
			assertTrue(checkSuspendEvent(suspend_create, testactivity.createobject, entry_activity, testactivity.specify1));
			assertTrue(checkSuspendEvent(suspend_specify1, testactivity.specify1, entry_activity, testactivity.callsubtract));
			assertTrue(checkSuspendEvent(suspend_subtract, testactivity.callsubtract, entry_activity, testactivity.specify0));
			assertTrue(checkSuspendEvent(suspend_specify0, testactivity.specify0, entry_activity, testactivity.testid));
			if(j==0) {
				assertTrue(checkSuspendEvent(suspend_testid, testactivity.testid, entry_activity, testactivity.createobject));
			}
		}
		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_loop = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(i);

		assertTrue(checkActivityNodeExitEvent(exit_loop, testactivity.loopnode, entry_loop));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));

		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	@Ignore
	public void testLoopNode2_execute() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.LoopNodeTestActivity2 testactivity = factory.new LoopNodeTestActivity2();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		//ParameterValueList outvalues = ExecutionContext.getInstance().getLocus().executor.execute(activity, null, new ParameterValueList());
		
		ExecutionContext.getInstance().execute(activity, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();	
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testConditionalNode1_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.ConditionalNodeTestActivity1 testactivity = factory.new ConditionalNodeTestActivity1();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();	
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.readname);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.fork);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.conditionalnode);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specifytanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testidtanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specifyphilip);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testidphilip);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify1);

		// check events
		assertEquals(26, eventlist.size());	

		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_readname, entry_fork, entry_conditionalnode, entry_specifytanja, entry_testidtanja, entry_specifyphilip, entry_testidphilip, entry_specify1;
		ActivityNodeExitEvent exit_readname, exit_fork, exit_conditionalnode, exit_specifytanja, exit_testidtanja, exit_specifyphilip, exit_testidphilip, exit_specify1;

		SuspendEvent suspend_activity, suspend_readname, suspend_fork, suspend_conditionalnode, suspend_specifytanja, suspend_testidtanja, suspend_specifyphilip, suspend_testidphilip;

		int i=-1;
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_readname = (ActivityNodeEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_readname = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_readname = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_fork = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_fork = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_fork = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_conditionalnode = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_conditionalnode = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specifytanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specifytanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specifytanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_testidtanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_testidtanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_testidtanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specifyphilip = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specifyphilip = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specifyphilip = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_testidphilip = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_testidphilip = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_testidphilip = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specify1 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specify1 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_conditionalnode = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(i);

		assertTrue(checkActivityEntryEvent(entry_activity, activity));

		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));

		assertTrue(checkActivityNodeEntryEvent(entry_readname, testactivity.readname, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_fork, testactivity.fork, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_conditionalnode, testactivity.conditionalnode, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specifytanja, testactivity.specifytanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_testidtanja, testactivity.testidtanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specifyphilip, testactivity.specifyphilip, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_testidphilip, testactivity.testidphilip, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specify1, testactivity.specify1, entry_activity));

		assertTrue(checkActivityNodeExitEvent(exit_readname, testactivity.readname, entry_readname));
		assertTrue(checkActivityNodeExitEvent(exit_fork, testactivity.fork, entry_fork));
		assertTrue(checkActivityNodeExitEvent(exit_conditionalnode, testactivity.conditionalnode, entry_conditionalnode));
		assertTrue(checkActivityNodeExitEvent(exit_specifytanja, testactivity.specifytanja, entry_specifytanja));
		assertTrue(checkActivityNodeExitEvent(exit_testidtanja, testactivity.testidtanja, entry_testidtanja));
		assertTrue(checkActivityNodeExitEvent(exit_specifyphilip, testactivity.specifyphilip, entry_specifyphilip));		
		assertTrue(checkActivityNodeExitEvent(exit_testidphilip, testactivity.testidphilip, entry_testidphilip));
		assertTrue(checkActivityNodeExitEvent(exit_specify1, testactivity.specify1, entry_specify1));

		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.readname));
		assertTrue(checkSuspendEvent(suspend_readname, testactivity.readname, entry_activity, testactivity.fork, testactivity.conditionalnode));
		assertTrue(checkSuspendEvent(suspend_fork, testactivity.fork, entry_activity, (ActivityNode[])null));
		assertTrue(checkSuspendEvent(suspend_conditionalnode, testactivity.conditionalnode, entry_activity, testactivity.specifytanja, testactivity.specifyphilip));
		assertTrue(checkSuspendEvent(suspend_specifytanja, testactivity.specifytanja, entry_activity, testactivity.testidtanja));
		assertTrue(checkSuspendEvent(suspend_testidtanja, testactivity.testidtanja, entry_activity, (ActivityNode[])null));
		assertTrue(checkSuspendEvent(suspend_specifyphilip, testactivity.specifyphilip, entry_activity, testactivity.testidphilip));
		assertTrue(checkSuspendEvent(suspend_testidphilip, testactivity.testidphilip, entry_activity, testactivity.specify1));

		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testConditionalNode2_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.ConditionalNodeTestActivity2 testactivity = factory.new ConditionalNodeTestActivity2();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();	
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.readname);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.fork);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.conditionalnode);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specifytanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testidtanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify1);

		// check events
		assertEquals(20, eventlist.size());	

		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_readname, entry_fork, entry_conditionalnode, entry_specifytanja, entry_testidtanja, entry_specify1;
		ActivityNodeExitEvent exit_readname, exit_fork, exit_conditionalnode, exit_specifytanja, exit_testidtanja, exit_specify1;

		SuspendEvent suspend_activity, suspend_readname, suspend_fork, suspend_conditionalnode, suspend_specifytanja, suspend_testidtanja;

		int i=-1;
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_readname = (ActivityNodeEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_readname = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_readname = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_fork = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_fork = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_fork = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_conditionalnode = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_conditionalnode = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specifytanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specifytanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specifytanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_testidtanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_testidtanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_testidtanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specify1 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specify1 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_conditionalnode = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(i);

		assertTrue(checkActivityEntryEvent(entry_activity, activity));

		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));

		assertTrue(checkActivityNodeEntryEvent(entry_readname, testactivity.readname, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_fork, testactivity.fork, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_conditionalnode, testactivity.conditionalnode, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specifytanja, testactivity.specifytanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_testidtanja, testactivity.testidtanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specify1, testactivity.specify1, entry_activity));

		assertTrue(checkActivityNodeExitEvent(exit_readname, testactivity.readname, entry_readname));
		assertTrue(checkActivityNodeExitEvent(exit_fork, testactivity.fork, entry_fork));
		assertTrue(checkActivityNodeExitEvent(exit_conditionalnode, testactivity.conditionalnode, entry_conditionalnode));
		assertTrue(checkActivityNodeExitEvent(exit_specifytanja, testactivity.specifytanja, entry_specifytanja));
		assertTrue(checkActivityNodeExitEvent(exit_testidtanja, testactivity.testidtanja, entry_testidtanja));
		assertTrue(checkActivityNodeExitEvent(exit_specify1, testactivity.specify1, entry_specify1));

		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.readname));
		assertTrue(checkSuspendEvent(suspend_readname, testactivity.readname, entry_activity, testactivity.fork, testactivity.conditionalnode));
		assertTrue(checkSuspendEvent(suspend_fork, testactivity.fork, entry_activity, (ActivityNode[])null));
		assertTrue(checkSuspendEvent(suspend_conditionalnode, testactivity.conditionalnode, entry_activity, testactivity.specifytanja));
		assertTrue(checkSuspendEvent(suspend_specifytanja, testactivity.specifytanja, entry_activity, testactivity.testidtanja));
		assertTrue(checkSuspendEvent(suspend_testidtanja, testactivity.testidtanja, entry_activity, testactivity.specify1));

		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testConditionalNode3_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.ConditionalNodeTestActivity3 testactivity = factory.new ConditionalNodeTestActivity3();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();	
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.readname);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.fork);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.conditionalnode);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specifytanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testidtanja);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specifyphilip);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.testidphilip);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.specify2);

		// check events
		assertEquals(26, eventlist.size());	

		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_readname, entry_fork, entry_conditionalnode, entry_specifytanja, entry_testidtanja, entry_specifyphilip, entry_testidphilip, entry_specify2;
		ActivityNodeExitEvent exit_readname, exit_fork, exit_conditionalnode, exit_specifytanja, exit_testidtanja, exit_specifyphilip, exit_testidphilip, exit_specify2;

		SuspendEvent suspend_activity, suspend_readname, suspend_fork, suspend_conditionalnode, suspend_specifytanja, suspend_testidtanja, suspend_specifyphilip, suspend_testidphilip;

		int i=-1;
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_readname = (ActivityNodeEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_readname = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_readname = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_fork = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_fork = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_fork = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_conditionalnode = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_conditionalnode = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specifytanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specifytanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specifytanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_testidtanja = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_testidtanja = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_testidtanja = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specifyphilip = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specifyphilip = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_specifyphilip = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_testidphilip = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_testidphilip = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_testidphilip = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_specify2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_specify2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_conditionalnode = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(i);

		assertTrue(checkActivityEntryEvent(entry_activity, activity));

		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));

		assertTrue(checkActivityNodeEntryEvent(entry_readname, testactivity.readname, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_fork, testactivity.fork, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_conditionalnode, testactivity.conditionalnode, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specifytanja, testactivity.specifytanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_testidtanja, testactivity.testidtanja, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specifyphilip, testactivity.specifyphilip, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_testidphilip, testactivity.testidphilip, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_specify2, testactivity.specify2, entry_activity));

		assertTrue(checkActivityNodeExitEvent(exit_readname, testactivity.readname, entry_readname));
		assertTrue(checkActivityNodeExitEvent(exit_fork, testactivity.fork, entry_fork));
		assertTrue(checkActivityNodeExitEvent(exit_conditionalnode, testactivity.conditionalnode, entry_conditionalnode));
		assertTrue(checkActivityNodeExitEvent(exit_specifytanja, testactivity.specifytanja, entry_specifytanja));
		assertTrue(checkActivityNodeExitEvent(exit_testidtanja, testactivity.testidtanja, entry_testidtanja));
		assertTrue(checkActivityNodeExitEvent(exit_specifyphilip, testactivity.specifyphilip, entry_specifyphilip));		
		assertTrue(checkActivityNodeExitEvent(exit_testidphilip, testactivity.testidphilip, entry_testidphilip));
		assertTrue(checkActivityNodeExitEvent(exit_specify2, testactivity.specify2, entry_specify2));

		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.readname));
		assertTrue(checkSuspendEvent(suspend_readname, testactivity.readname, entry_activity, testactivity.fork, testactivity.conditionalnode));
		assertTrue(checkSuspendEvent(suspend_fork, testactivity.fork, entry_activity, (ActivityNode[])null));
		assertTrue(checkSuspendEvent(suspend_conditionalnode, testactivity.conditionalnode, entry_activity, testactivity.specifytanja));
		assertTrue(checkSuspendEvent(suspend_specifytanja, testactivity.specifytanja, entry_activity, testactivity.testidtanja));
		assertTrue(checkSuspendEvent(suspend_testidtanja, testactivity.testidtanja, entry_activity, testactivity.specifyphilip));
		assertTrue(checkSuspendEvent(suspend_specifyphilip, testactivity.specifyphilip, entry_activity, testactivity.testidphilip));
		assertTrue(checkSuspendEvent(suspend_testidphilip, testactivity.testidphilip, entry_activity, testactivity.specify2));

		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testStructuredActivityNode5_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity5 testactivity = factory.new StructuredActivityNodeTestActivity5();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();		
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.structurednode1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.initial);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.create1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.create2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.structurednode2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.callA2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.initial2);	
		ExecutionContext.getInstance().nextStep(executionID); // testactivity.initialA2
		ExecutionContext.getInstance().nextStep(executionID); // testactivity.setname
		
		// check events
		assertEquals(31, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode1, entry_structurednode2, entry_initial, entry_create1, entry_create2, entry_call_1, entry_initial2;
		ActivityNodeExitEvent exit_structurednode1, exit_structurednode2, exit_initial, exit_create1, exit_create2, exit_call_1, exit_initial2;
		
		ActivityEntryEvent entry_activityA2;
		ActivityExitEvent exit_activityA2;
		ActivityNodeEntryEvent entry_initialA2, entry_setnameA2;
		ActivityNodeExitEvent exit_initialA2, exit_setnameA2;
		
		SuspendEvent suspend_activity, suspend_structurednode1, suspend_initial, suspend_create1, suspend_create2, suspend_structurednode2, suspend_activity2, suspend_initial2, suspend_initialA2;
		
		int i=-1;
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_structurednode1 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_structurednode1 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_initial = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_create1 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_create1 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_create1 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_create2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_create2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_create2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_structurednode2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_structurednode2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_call_1 = (ActivityNodeEntryEvent)eventlist.get(i);
		
		assertTrue(eventlist.get(++i) instanceof ActivityEntryEvent);
		entry_activityA2 = (ActivityEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_activity2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_initial2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_initial2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_initial2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_initialA2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_initialA2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof SuspendEvent);
		suspend_initialA2 = (SuspendEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeEntryEvent);
		entry_setnameA2 = (ActivityNodeEntryEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_setnameA2 = (ActivityNodeExitEvent)eventlist.get(i);	
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activityA2 = (ActivityExitEvent)eventlist.get(i);
		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_call_1 = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_structurednode2 = (ActivityNodeExitEvent)eventlist.get(i);
		assertTrue(eventlist.get(++i) instanceof ActivityNodeExitEvent);
		exit_structurednode1 = (ActivityNodeExitEvent)eventlist.get(i);		
		assertTrue(eventlist.get(++i) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(i);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityEntryEvent(entry_activityA2, testactivity.activity2, entry_call_1));
		
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityExitEvent(exit_activityA2, testactivity.activity2, entry_activityA2));
		
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode1, testactivity.structurednode1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create1, testactivity.create1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create2, testactivity.create2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode2, testactivity.structurednode2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_call_1, testactivity.callA2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initialA2, testactivity.initialA2, entry_activityA2));
		assertTrue(checkActivityNodeEntryEvent(entry_setnameA2, testactivity.setname, entry_activityA2));
		assertTrue(checkActivityNodeEntryEvent(entry_initial2, testactivity.initial2, entry_activity));
		
		assertTrue(checkActivityNodeExitEvent(exit_structurednode1, testactivity.structurednode1, entry_structurednode1));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_create1, testactivity.create1, entry_create1));
		assertTrue(checkActivityNodeExitEvent(exit_create2, testactivity.create2, entry_create2));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode2, testactivity.structurednode2, entry_structurednode2));
		assertTrue(checkActivityNodeExitEvent(exit_call_1, testactivity.callA2, entry_call_1));		
		assertTrue(checkActivityNodeExitEvent(exit_initialA2, testactivity.initialA2, entry_initialA2));
		assertTrue(checkActivityNodeExitEvent(exit_setnameA2, testactivity.setname, entry_setnameA2));
		assertTrue(checkActivityNodeExitEvent(exit_initial2, testactivity.initial2, entry_initial2));
		
		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.structurednode1));
		assertTrue(checkSuspendEvent(suspend_structurednode1, testactivity.structurednode1, entry_activity, testactivity.initial));
		assertTrue(checkSuspendEvent(suspend_initial, testactivity.initial, entry_activity, testactivity.create1));
		assertTrue(checkSuspendEvent(suspend_create1, testactivity.create1, entry_activity, testactivity.create2));
		assertTrue(checkSuspendEvent(suspend_create2, testactivity.create2, entry_activity, testactivity.structurednode2));
		assertTrue(checkSuspendEvent(suspend_structurednode2, testactivity.structurednode2, entry_activity, testactivity.callA2, testactivity.initial2));
		assertTrue(checkSuspendEvent(suspend_initial2, testactivity.initial2, entry_activity, (ActivityNode[])null));
		assertTrue(checkSuspendEvent(suspend_activity2, testactivity.activity2, entry_activityA2, testactivity.initialA2));
		assertTrue(checkSuspendEvent(suspend_initialA2, testactivity.initialA2, entry_activityA2, testactivity.setname));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testStructuredActivityNode4_execute() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity4 testactivity = factory.new StructuredActivityNodeTestActivity4();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();		
		
		// check events
		assertEquals(20, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode1, entry_structurednode2, entry_initial, entry_create1, entry_create2, entry_call_1;
		ActivityNodeExitEvent exit_structurednode1, exit_structurednode2, exit_initial, exit_create1, exit_create2, exit_call_1;
		
		ActivityEntryEvent entry_activityA2;
		ActivityExitEvent exit_activityA2;
		ActivityNodeEntryEvent entry_initialA2, entry_setnameA2;
		ActivityNodeExitEvent exit_initialA2, exit_setnameA2;
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		entry_structurednode1 = (ActivityNodeEntryEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(3);				
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		entry_create1 = (ActivityNodeEntryEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		exit_create1 = (ActivityNodeExitEvent)eventlist.get(5);
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		entry_create2 = (ActivityNodeEntryEvent)eventlist.get(6);
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		exit_create2 = (ActivityNodeExitEvent)eventlist.get(7);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		entry_structurednode2 = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		entry_call_1 = (ActivityNodeEntryEvent)eventlist.get(9);
		
		assertTrue(eventlist.get(10) instanceof ActivityEntryEvent);
		entry_activityA2 = (ActivityEntryEvent)eventlist.get(10);
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		entry_initialA2 = (ActivityNodeEntryEvent)eventlist.get(11);
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		exit_initialA2 = (ActivityNodeExitEvent)eventlist.get(12);	
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		entry_setnameA2 = (ActivityNodeEntryEvent)eventlist.get(13);
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		exit_setnameA2 = (ActivityNodeExitEvent)eventlist.get(14);	
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		exit_activityA2 = (ActivityExitEvent)eventlist.get(15);
		
		assertTrue(eventlist.get(16) instanceof ActivityNodeExitEvent);
		exit_call_1 = (ActivityNodeExitEvent)eventlist.get(16);		
		assertTrue(eventlist.get(17) instanceof ActivityNodeExitEvent);
		exit_structurednode2 = (ActivityNodeExitEvent)eventlist.get(17);
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		exit_structurednode1 = (ActivityNodeExitEvent)eventlist.get(18);		
		assertTrue(eventlist.get(19) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(19);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityEntryEvent(entry_activityA2, testactivity.activity2, entry_call_1));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityExitEvent(exit_activityA2, testactivity.activity2, entry_activityA2));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode1, testactivity.structurednode1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create1, testactivity.create1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create2, testactivity.create2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode2, testactivity.structurednode2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_call_1, testactivity.callA2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initialA2, testactivity.initialA2, entry_activityA2));
		assertTrue(checkActivityNodeEntryEvent(entry_setnameA2, testactivity.setname, entry_activityA2));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode1, testactivity.structurednode1, entry_structurednode1));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_create1, testactivity.create1, entry_create1));
		assertTrue(checkActivityNodeExitEvent(exit_create2, testactivity.create2, entry_create2));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode2, testactivity.structurednode2, entry_structurednode2));
		assertTrue(checkActivityNodeExitEvent(exit_call_1, testactivity.callA2, entry_call_1));		
		assertTrue(checkActivityNodeExitEvent(exit_initialA2, testactivity.initialA2, entry_initialA2));
		assertTrue(checkActivityNodeExitEvent(exit_setnameA2, testactivity.setname, entry_setnameA2));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testStructuredActivityNode3_execute() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity3 testactivity = factory.new StructuredActivityNodeTestActivity3();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();		
		
		// check events
		assertEquals(16, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode1, entry_structurednode2, entry_initial, entry_create1, entry_create2, entry_setname_1, entry_setname_2;
		ActivityNodeExitEvent exit_structurednode1, exit_structurednode2, exit_initial, exit_create1, exit_create2, exit_setname_1, exit_setname_2;
				
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		entry_structurednode1 = (ActivityNodeEntryEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(3);				
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		entry_create1 = (ActivityNodeEntryEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		exit_create1 = (ActivityNodeExitEvent)eventlist.get(5);
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		entry_create2 = (ActivityNodeEntryEvent)eventlist.get(6);
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		exit_create2 = (ActivityNodeExitEvent)eventlist.get(7);
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		entry_structurednode2 = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		entry_setname_1 = (ActivityNodeEntryEvent)eventlist.get(9);
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		exit_setname_1 = (ActivityNodeExitEvent)eventlist.get(10);
		assertTrue(eventlist.get(11) instanceof ActivityNodeEntryEvent);
		entry_setname_2 = (ActivityNodeEntryEvent)eventlist.get(11);
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		exit_setname_2 = (ActivityNodeExitEvent)eventlist.get(12);		
		assertTrue(eventlist.get(13) instanceof ActivityNodeExitEvent);
		exit_structurednode2 = (ActivityNodeExitEvent)eventlist.get(13);
		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		exit_structurednode1 = (ActivityNodeExitEvent)eventlist.get(14);		
		assertTrue(eventlist.get(15) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(15);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode1, testactivity.structurednode1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create1, testactivity.create1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create2, testactivity.create2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode2, testactivity.structurednode2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_1, testactivity.setname, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_2, testactivity.setname, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode1, testactivity.structurednode1, entry_structurednode1));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_create1, testactivity.create1, entry_create1));
		assertTrue(checkActivityNodeExitEvent(exit_create2, testactivity.create2, entry_create2));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode2, testactivity.structurednode2, entry_structurednode2));
		assertTrue(checkActivityNodeExitEvent(exit_setname_1, testactivity.setname, entry_setname_1));
		assertTrue(checkActivityNodeExitEvent(exit_setname_2, testactivity.setname, entry_setname_2));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testStructuredActivityNode3_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity3 testactivity = factory.new StructuredActivityNodeTestActivity3();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();		
		
		ExecutionContext.getInstance().nextStep(executionID, testactivity.structurednode1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.initial);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.create1);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.create2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.structurednode2);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.setname);
		ExecutionContext.getInstance().nextStep(executionID, testactivity.setname);
		
		// check events
		assertEquals(23, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode1, entry_structurednode2, entry_initial, entry_create1, entry_create2, entry_setname_1, entry_setname_2;
		ActivityNodeExitEvent exit_structurednode1, exit_structurednode2, exit_initial, exit_create1, exit_create2, exit_setname_1, exit_setname_2;
		SuspendEvent suspend_activity, suspend_structurednode1, suspend_initial, suspend_create1, suspend_create2, suspend_structurednode2, suspend_setname_1;		
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_structurednode1 = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof SuspendEvent);
		suspend_structurednode1 = (SuspendEvent)eventlist.get(3);
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(5);		
		assertTrue(eventlist.get(6) instanceof SuspendEvent);
		suspend_initial = (SuspendEvent)eventlist.get(6);						
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		entry_create1 = (ActivityNodeEntryEvent)eventlist.get(7);
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		exit_create1 = (ActivityNodeExitEvent)eventlist.get(8);		
		assertTrue(eventlist.get(9) instanceof SuspendEvent);
		suspend_create1 = (SuspendEvent)eventlist.get(9);			
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		entry_create2 = (ActivityNodeEntryEvent)eventlist.get(10);
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		exit_create2 = (ActivityNodeExitEvent)eventlist.get(11);		
		assertTrue(eventlist.get(12) instanceof SuspendEvent);
		suspend_create2 = (SuspendEvent)eventlist.get(12);			
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		entry_structurednode2 = (ActivityNodeEntryEvent)eventlist.get(13);
		assertTrue(eventlist.get(14) instanceof SuspendEvent);
		suspend_structurednode2 = (SuspendEvent)eventlist.get(14);			
		assertTrue(eventlist.get(15) instanceof ActivityNodeEntryEvent);
		entry_setname_1 = (ActivityNodeEntryEvent)eventlist.get(15);
		assertTrue(eventlist.get(16) instanceof ActivityNodeExitEvent);
		exit_setname_1 = (ActivityNodeExitEvent)eventlist.get(16);
		assertTrue(eventlist.get(17) instanceof SuspendEvent);
		suspend_setname_1 = (SuspendEvent)eventlist.get(17);
		assertTrue(eventlist.get(18) instanceof ActivityNodeEntryEvent);
		entry_setname_2 = (ActivityNodeEntryEvent)eventlist.get(18);
		assertTrue(eventlist.get(19) instanceof ActivityNodeExitEvent);
		exit_setname_2 = (ActivityNodeExitEvent)eventlist.get(19);		
		assertTrue(eventlist.get(20) instanceof ActivityNodeExitEvent);
		exit_structurednode2 = (ActivityNodeExitEvent)eventlist.get(20);
		assertTrue(eventlist.get(21) instanceof ActivityNodeExitEvent);
		exit_structurednode1 = (ActivityNodeExitEvent)eventlist.get(21);		
		assertTrue(eventlist.get(22) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(22);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));		
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode1, testactivity.structurednode1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create1, testactivity.create1, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_create2, testactivity.create2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode2, testactivity.structurednode2, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_1, testactivity.setname, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_2, testactivity.setname, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode1, testactivity.structurednode1, entry_structurednode1));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_create1, testactivity.create1, entry_create1));
		assertTrue(checkActivityNodeExitEvent(exit_create2, testactivity.create2, entry_create2));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode2, testactivity.structurednode2, entry_structurednode2));
		assertTrue(checkActivityNodeExitEvent(exit_setname_1, testactivity.setname, entry_setname_1));
		assertTrue(checkActivityNodeExitEvent(exit_setname_2, testactivity.setname, entry_setname_2));
		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.structurednode1));
		assertTrue(checkSuspendEvent(suspend_structurednode1, testactivity.structurednode1, entry_activity, testactivity.initial));
		assertTrue(checkSuspendEvent(suspend_initial, testactivity.initial, entry_activity, testactivity.create1));
		assertTrue(checkSuspendEvent(suspend_create1, testactivity.create1, entry_activity, testactivity.create2));
		assertTrue(checkSuspendEvent(suspend_create2, testactivity.create2, entry_activity, testactivity.structurednode2));
		assertTrue(checkSuspendEvent(suspend_structurednode2, testactivity.structurednode2, entry_activity, testactivity.setname));
		assertTrue(checkSuspendEvent(suspend_setname_1, testactivity.setname, entry_activity, testactivity.setname));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(testactivity.checkOutput(outvalues));
	}
	
	@Test
	public void testStructuredActivityNode1_execute() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity1 testactivity = factory.new StructuredActivityNodeTestActivity1();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();		
		
		// check events
		assertEquals(8, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode, entry_setname_1, entry_setname_2;
		ActivityNodeExitEvent exit_structurednode, exit_setname_1, exit_setname_2;
				
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		entry_structurednode = (ActivityNodeEntryEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_setname_1 = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		exit_setname_1 = (ActivityNodeExitEvent)eventlist.get(3);				
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		entry_setname_2 = (ActivityNodeEntryEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		exit_setname_2 = (ActivityNodeExitEvent)eventlist.get(5);		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		exit_structurednode = (ActivityNodeExitEvent)eventlist.get(6);
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(7);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode, testactivity.structurednode, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_1, testactivity.addaction, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_2, testactivity.addaction, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode, testactivity.structurednode, entry_structurednode));
		assertTrue(checkActivityNodeExitEvent(exit_setname_1, testactivity.addaction, entry_setname_1));
		assertTrue(checkActivityNodeExitEvent(exit_setname_2, testactivity.addaction, entry_setname_2));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(checkStructuredActivityNode1Output(outvalues, testactivity));
	}
	
	@Test
	public void testStructuredActivityNode1_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity1 testactivity = factory.new StructuredActivityNodeTestActivity1();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, testactivity.parametervaluelist);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ExecutionContext.getInstance().nextStep(executionID);
		ExecutionContext.getInstance().nextStep(executionID);
		ExecutionContext.getInstance().nextStep(executionID);
		
		// check events
		assertEquals(11, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_structurednode, entry_setname_1, entry_setname_2;
		ActivityNodeExitEvent exit_structurednode, exit_setname_1, exit_setname_2;
		SuspendEvent suspend_activity, suspend_structurednode, suspend_setname_1;
				
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_structurednode = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof SuspendEvent);
		suspend_structurednode = (SuspendEvent)eventlist.get(3);
		assertTrue(eventlist.get(4) instanceof ActivityNodeEntryEvent);
		entry_setname_1 = (ActivityNodeEntryEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		exit_setname_1 = (ActivityNodeExitEvent)eventlist.get(5);		
		assertTrue(eventlist.get(6) instanceof SuspendEvent);
		suspend_setname_1 = (SuspendEvent)eventlist.get(6);
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		entry_setname_2 = (ActivityNodeEntryEvent)eventlist.get(7);
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		exit_setname_2 = (ActivityNodeExitEvent)eventlist.get(8);		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		exit_structurednode = (ActivityNodeExitEvent)eventlist.get(9);
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(10);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode, testactivity.structurednode, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_1, testactivity.addaction, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_setname_2, testactivity.addaction, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode, testactivity.structurednode, entry_structurednode));
		assertTrue(checkActivityNodeExitEvent(exit_setname_1, testactivity.addaction, entry_setname_1));
		assertTrue(checkActivityNodeExitEvent(exit_setname_2, testactivity.addaction, entry_setname_2));
		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.structurednode));
		assertTrue(checkSuspendEvent(suspend_structurednode, testactivity.structurednode, entry_activity, testactivity.addaction));
		assertTrue(checkSuspendEvent(suspend_setname_1, testactivity.addaction, entry_activity, testactivity.addaction));
		
		// check output
		ParameterValueList outvalues = ExecutionContext.getInstance().getActivityOutput(executionID);
		assertTrue(checkStructuredActivityNode1Output(outvalues, testactivity));
	}
	
	@Test
	public void testStructuredActivityNode2_empty_execute() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity2 testactivity = factory.new StructuredActivityNodeTestActivity2();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().execute(activity, null, null);		
		
		// check events
		assertEquals(8, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_initial, entry_structurednode, entry_final;
		ActivityNodeExitEvent exit_initial, exit_structurednode, exit_final;
				
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof ActivityNodeEntryEvent);
		entry_structurednode = (ActivityNodeEntryEvent)eventlist.get(3);
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		exit_structurednode = (ActivityNodeExitEvent)eventlist.get(4);				
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		entry_final = (ActivityNodeEntryEvent)eventlist.get(5);
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		exit_final = (ActivityNodeExitEvent)eventlist.get(6);				
		assertTrue(eventlist.get(7) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(7);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode, testactivity.structurednode, entry_activity));		
		assertTrue(checkActivityNodeEntryEvent(entry_final, testactivity.final_, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode, testactivity.structurednode, entry_structurednode));		
		assertTrue(checkActivityNodeExitEvent(exit_final, testactivity.final_, entry_final));		
	}
	
	@Test
	public void testStructuredActivityNode2_empty_executestepwise() {
		TestActivityFactory factory = new TestActivityFactory();
		TestActivityFactory.StructuredActivityNodeTestActivity2 testactivity = factory.new StructuredActivityNodeTestActivity2();
		Activity activity = testactivity.activity;
		
		// execute activity
		ExecutionContext.getInstance().executeStepwise(activity, null, null);		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		ExecutionContext.getInstance().nextStep(executionID);
		ExecutionContext.getInstance().nextStep(executionID);
		ExecutionContext.getInstance().nextStep(executionID);
		
		// check events
		assertEquals(11, eventlist.size());	
		
		ActivityEntryEvent entry_activity;
		ActivityExitEvent exit_activity;
		ActivityNodeEntryEvent entry_initial, entry_structurednode, entry_final;
		ActivityNodeExitEvent exit_initial, exit_structurednode, exit_final;
		SuspendEvent suspend_activity, suspend_initial, suspend_structurednode;
				
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		entry_activity = (ActivityEntryEvent)eventlist.get(0);		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		suspend_activity = (SuspendEvent)eventlist.get(1);
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		entry_initial = (ActivityNodeEntryEvent)eventlist.get(2);
		assertTrue(eventlist.get(3) instanceof ActivityNodeExitEvent);
		exit_initial = (ActivityNodeExitEvent)eventlist.get(3);
		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		suspend_initial = (SuspendEvent)eventlist.get(4);
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		entry_structurednode = (ActivityNodeEntryEvent)eventlist.get(5);
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		exit_structurednode = (ActivityNodeExitEvent)eventlist.get(6);
		assertTrue(eventlist.get(7) instanceof SuspendEvent);
		suspend_structurednode = (SuspendEvent)eventlist.get(7);		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		entry_final = (ActivityNodeEntryEvent)eventlist.get(8);
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		exit_final = (ActivityNodeExitEvent)eventlist.get(9);				
		assertTrue(eventlist.get(10) instanceof ActivityExitEvent);
		exit_activity = (ActivityExitEvent)eventlist.get(10);
		
		assertTrue(checkActivityEntryEvent(entry_activity, activity));
		assertTrue(checkActivityExitEvent(exit_activity, activity, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_initial, testactivity.initial, entry_activity));
		assertTrue(checkActivityNodeEntryEvent(entry_structurednode, testactivity.structurednode, entry_activity));		
		assertTrue(checkActivityNodeEntryEvent(entry_final, testactivity.final_, entry_activity));
		assertTrue(checkActivityNodeExitEvent(exit_initial, testactivity.initial, entry_initial));
		assertTrue(checkActivityNodeExitEvent(exit_structurednode, testactivity.structurednode, entry_structurednode));		
		assertTrue(checkActivityNodeExitEvent(exit_final, testactivity.final_, entry_final));		
		assertTrue(checkSuspendEvent(suspend_activity, activity, entry_activity, testactivity.initial));
		assertTrue(checkSuspendEvent(suspend_initial, testactivity.initial, entry_activity, testactivity.structurednode));
		assertTrue(checkSuspendEvent(suspend_structurednode, testactivity.structurednode, entry_activity, testactivity.final_));
	}

	private boolean checkStructuredActivityNode1Output(ParameterValueList outvalues, TestActivityFactory.StructuredActivityNodeTestActivity1 testactivity) {
		if(outvalues.size() != 1) {
			return false;
		}
		if(outvalues.get(0).values.size() != 2) {
			return false;
		}
		if(outvalues.get(0).values.get(0) != testactivity.o1) {
			return false;
		}
		if(outvalues.get(0).values.get(1) != testactivity.o2) {
			return false;
		}
		Object_ o1_out = (Object_)outvalues.get(0).values.get(0);
		Object_ o2_out = (Object_)outvalues.get(0).values.get(1);
		if(!((StringValue)o1_out.featureValues.get(0).values.get(0)).value.equals(testactivity.string1.value)) {
			return false;
		}
		if(!((StringValue)o2_out.featureValues.get(0).values.get(0)).value.equals(testactivity.string2.value)) {
			return false;
		}
		return true;
	}	
	
	private boolean checkActivityExitEvent(ActivityExitEvent event,	Activity activity, ActivityEntryEvent parentevent) {
		if(!event.getActivity().equals(activity)) {
			return false;
		}
		if(!event.getParent().equals(parentevent)) {
			return false;
		}
		return true;
	}

	private boolean checkActivityNodeExitEvent(ActivityNodeExitEvent event,	ActivityNode node, Event parentevent) {
		if(!event.getNode().equals(node)) {
			return false;
		}
		if(!event.getParent().equals(parentevent)) {
			return false;
		}
		return true;
	}

	private boolean checkActivityNodeEntryEvent(ActivityNodeEntryEvent event, ActivityNode node, Event parentevent) {
		if(!event.getNode().equals(node)) {
			return false;
		}
		if(!event.getParent().equals(parentevent)) {
			return false;
		}
		return true;
	}

	private boolean checkActivityEntryEvent(ActivityEntryEvent event, Activity activity) {
		if(!event.getActivity().equals(activity)) {
			return false;
		}
		if(event.getParent() != null) {
			return false;
		}
		return true;
	}
	
	private boolean checkActivityEntryEvent(ActivityEntryEvent event, Activity activity, Event parent) {
		if(!event.getActivity().equals(activity)) {
			return false;
		}
		if(!event.getParent().equals(parent)) {
			return false;
		}
		return true;
	}
	
	private boolean checkSuspendEvent(SuspendEvent event, Element location, Event parentevent, ActivityNode... enablednodes) {
		if(!event.getLocation().equals(location)) {
			return false;
		}
		if(!event.getParent().equals(parentevent)) {
			return false;
		}
		int enablednodesamount = 0;
		if(enablednodes != null) {
			enablednodesamount = enablednodes.length;
		}
		if(event.getNewEnabledNodes().size() != enablednodesamount) {
			return false;
		}
		if(enablednodes != null) {
			if(!event.getNewEnabledNodes().containsAll(Arrays.asList(enablednodes))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void notify(Event event) {
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
			System.err.println(event);
		}		
	}
	
}
