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
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ReadSelfAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * @author Tanja Mayerhofer
 *
 */
public class NFRTests extends MolizTest implements ExecutionEventListener{

	private Class_ eHS;
	private Class_ client;
	private Property prop_eHS;
	private Property prop_client;	
	private Association eHS_client;
	private Operation op_requestPatientInfoPages;
	private Operation op_RPIP_service;
	
	private Activity A;
	private Activity B;
	private Activity requestPatientInfoPage;
	private Activity RPIP_service;
	private Activity main;
	
	private ActivityExecution execution_main;
	private ActivityExecution execution_RPIP_service;
	private ActivityExecution execution_requestPatientInfoPages;
	private ActivityExecution execution_a;
	private ActivityExecution execution_b;
	private CallActionExecution call_RPIP_service;
	private CallActionExecution call_requestPatientInfoPages;
	private CallActionExecution call_a;
	private CallActionExecution call_b;
	
	
	private List<Event> eventlist = new ArrayList<Event>();
	
	public NFRTests() {
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
		
		eHS = null;
		client = null;
		prop_eHS = null;
		prop_client = null;	
		eHS_client = null;
		op_requestPatientInfoPages = null;
		op_RPIP_service = null;
		
		A = null;
		B = null;
		requestPatientInfoPage = null;
		RPIP_service = null;
		main = null;
		
		execution_main = null;
		execution_RPIP_service = null;
		execution_requestPatientInfoPages = null;
		execution_a = null;
		execution_b = null;
		call_RPIP_service = null;
		call_requestPatientInfoPages = null;
		call_a = null;
		call_b = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleNFRModel() {
		initializeClasses();
						
		A = createActivityAWithInitial();
		B = createActivityBWithInitial();
		requestPatientInfoPage = createActivityRequestPatientInfoPage(A, B);
		RPIP_service = createActivityRPIP_service();
		main = createActivityMain();
		
		// execute
		ExecutionContext.getInstance().execute(main, null, null);
		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);	
		
		assertTrue(initializeTrace(trace));				
		assertTrue(assertMain());		
		assertTrue(assertRPIP_service());
		assertTrue(assertRequestPatientInfoPage());
		assertTrue(assertABWithInitial());
		
		// assert execution hierarchy
		assertTrue(assertExecutionHierarchy());
	}

	@Test
	public void testSimpleNFRModelWithEmptyAB() {
		initializeClasses();
						
		A = createActivityAWithoutInitial();
		B = createActivityBWithoutInitial();
		requestPatientInfoPage = createActivityRequestPatientInfoPage(A, B);
		RPIP_service = createActivityRPIP_service();
		main = createActivityMain();
		
		// execute
		ExecutionContext.getInstance().execute(main, null, null);
		
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);	
		
		assertTrue(initializeTrace(trace));				
		assertTrue(assertMain());		
		assertTrue(assertRPIP_service());
		assertTrue(assertRequestPatientInfoPage());
		assertTrue(assertABWithoutInitial());
		
		// assert execution hierarchy
		assertTrue(assertExecutionHierarchy());
	}

	@Test
	public void testCallEmptyActivity() {
		Activity A = ActivityFactory.createActivity("A");
		Activity main = ActivityFactory.createActivity("main");
		CallBehaviorAction callA = ActivityFactory.createCallBehaviorAction(main, "call A", A);
		
		ExecutionContext.getInstance().execute(main, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		assertEquals(6, eventlist.size());
		assertEquals(main, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		assertEquals(callA, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		assertEquals(A, ((ActivityEntryEvent)eventlist.get(2)).getActivity());
		assertEquals(A, ((ActivityExitEvent)eventlist.get(3)).getActivity());
		assertEquals(callA, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertEquals(main, ((ActivityExitEvent)eventlist.get(5)).getActivity());
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertEquals(2, trace.getActivityExecutions().size());
		assertEquals(main, trace.getActivityExecutions().get(0).getActivity());
		ActivityExecution main_execution = trace.getActivityExecutions().get(0);
		assertEquals(1, main_execution.getNodeExecutions().size());
		assertEquals(callA, main_execution.getNodeExecutions().get(0).getNode());
		CallActionExecution callA_execution =(CallActionExecution)main_execution.getNodeExecutions().get(0); 
		assertEquals(A, trace.getActivityExecutions().get(1).getActivity());
		ActivityExecution A_execution = trace.getActivityExecutions().get(1);
		assertEquals(callA_execution, A_execution.getCaller());
		assertEquals(A_execution, callA_execution.getCallee());
	}
	
	@Test
	public void testCallEmptyActivity2() {
		Activity A = ActivityFactory.createActivity("A");
		Activity B = ActivityFactory.createActivity("B");
		Activity main = ActivityFactory.createActivity("main");
		CallBehaviorAction callA = ActivityFactory.createCallBehaviorAction(main, "call A", A);
		CallBehaviorAction callB = ActivityFactory.createCallBehaviorAction(main, "call B", B);
		ActivityFactory.createControlFlow(main, callA, callB);
		
		ExecutionContext.getInstance().execute(main, null, null);
		int executionID = ((ActivityEntryEvent)eventlist.get(0)).getActivityExecutionID();
		
		assertEquals(10, eventlist.size());
		assertEquals(main, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		assertEquals(callA, ((ActivityNodeEntryEvent)eventlist.get(1)).getNode());
		assertEquals(A, ((ActivityEntryEvent)eventlist.get(2)).getActivity());
		assertEquals(A, ((ActivityExitEvent)eventlist.get(3)).getActivity());
		assertEquals(callA, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
		assertEquals(callB, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		assertEquals(B, ((ActivityEntryEvent)eventlist.get(6)).getActivity());
		assertEquals(B, ((ActivityExitEvent)eventlist.get(7)).getActivity());
		assertEquals(callB, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		assertEquals(main, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		
		Trace trace = ExecutionContext.getInstance().getTrace(executionID);
		assertEquals(3, trace.getActivityExecutions().size());
		assertEquals(main, trace.getActivityExecutions().get(0).getActivity());
		ActivityExecution main_execution = trace.getActivityExecutions().get(0);
		assertEquals(2, main_execution.getNodeExecutions().size());
		assertEquals(callA, main_execution.getNodeExecutions().get(0).getNode());
		CallActionExecution callA_execution =(CallActionExecution)main_execution.getNodeExecutions().get(0); 
		assertEquals(callB, main_execution.getNodeExecutions().get(1).getNode());
		CallActionExecution callB_execution =(CallActionExecution)main_execution.getNodeExecutions().get(1);
		
		assertEquals(A, trace.getActivityExecutions().get(1).getActivity());
		ActivityExecution A_execution = trace.getActivityExecutions().get(1);
		assertEquals(callA_execution, A_execution.getCaller());
		assertEquals(A_execution, callA_execution.getCallee());
		
		assertEquals(B, trace.getActivityExecutions().get(2).getActivity());
		ActivityExecution B_execution = trace.getActivityExecutions().get(2);
		assertEquals(callB_execution, B_execution.getCaller());
		assertEquals(B_execution, callB_execution.getCallee());
	}
	
	private boolean assertABWithInitial() {
		boolean valid = true;
		
		// assert A	
		if(1 != execution_a.getNodeExecutions().size() || !(execution_a.getNodeExecutions().get(0).getNode() instanceof InitialNode))
			valid = false;

		// assert B
		if(1 != execution_b.getNodeExecutions().size() || !(execution_b.getNodeExecutions().get(0).getNode() instanceof InitialNode))
			valid = false;

		return valid;
	}
	
	private boolean assertABWithoutInitial() {
		boolean valid = true;
		
		// assert A	
		if(0 != execution_a.getNodeExecutions().size())
			valid = false;

		// assert B
		if(0 != execution_b.getNodeExecutions().size())
			valid = false;

		return valid;
	}

	private boolean assertRequestPatientInfoPage() {
		boolean valid = true;
		if(3 != execution_requestPatientInfoPages.getNodeExecutions().size())
			valid = false;
		call_a = (CallActionExecution)execution_requestPatientInfoPages.getNodeExecutions().get(1);
		call_b = (CallActionExecution)execution_requestPatientInfoPages.getNodeExecutions().get(2);
		return valid;
	}

	private boolean assertRPIP_service() {
		boolean valid = true;
		if(3 != execution_RPIP_service.getNodeExecutions().size())
			valid = false;
		call_requestPatientInfoPages = (CallActionExecution)execution_RPIP_service.getNodeExecutions().get(2);
		return valid;
	}

	private boolean assertMain() {
		boolean valid = true;
		if(4 != execution_main.getNodeExecutions().size())
			valid = false;
		call_RPIP_service = (CallActionExecution)execution_main.getNodeExecutions().get(3);
		return valid;
	}

	private boolean initializeTrace(Trace trace) {
		if(trace.getActivityExecutions().size() != 5)
			return false;

		boolean valid = true;
		execution_main = trace.getActivityExecutions().get(0);
		execution_RPIP_service = trace.getActivityExecutions().get(1);
		execution_requestPatientInfoPages = trace.getActivityExecutions().get(2);
		execution_a = trace.getActivityExecutions().get(3);
		execution_b = trace.getActivityExecutions().get(4);
		
		if (!main.equals(execution_main.getActivity())
				|| !RPIP_service.equals(execution_RPIP_service.getActivity())
				|| !requestPatientInfoPage
						.equals(execution_requestPatientInfoPages.getActivity())
				|| !A.equals(execution_a.getActivity())
				|| !B.equals(execution_b.getActivity()))
			valid = false;

		return valid;
	}

	private boolean assertExecutionHierarchy() {
		boolean valid = true;
		
		if(execution_main.getCaller() != null)
			valid = false;

		if(!execution_RPIP_service.equals(call_RPIP_service.getCallee()))
			valid = false;
		
		if(!call_RPIP_service.equals(execution_RPIP_service.getCaller()))
			valid = false;

		if(!execution_requestPatientInfoPages.equals(call_requestPatientInfoPages.getCallee()))
			valid = false;
		
		if(!call_requestPatientInfoPages.equals(execution_requestPatientInfoPages.getCaller()))
			valid = false;

		if(!execution_a.equals(call_a.getCallee()))
			valid = false;
		
		if(!call_a.equals(execution_a.getCaller())) 
			valid = false;

		if(!execution_b.equals(call_b.getCallee()))
			valid = false;
		
		if(!call_b.equals(execution_b.getCaller()))
			valid = false;

		return valid;
	}

	private Activity createActivityMain() {
		Activity main = ActivityFactory.createActivity("main");
		CreateObjectAction createeHS = ActivityFactory.createCreateObjectAction(main, "create eHS", eHS);
		CreateObjectAction createClient = ActivityFactory.createCreateObjectAction(main, "create Client", client);
		AddStructuralFeatureValueAction setClient = ActivityFactory.createAddStructuralFeatureValueAction(main, "set client", prop_client);
		CallOperationAction callRPIP_service = ActivityFactory.createCallOperationAction(main, "call RPIP_service", op_RPIP_service);
		ActivityFactory.createObjectFlow(main, createeHS.result, setClient.object);
		ActivityFactory.createObjectFlow(main, createClient.result, setClient.value);
		ActivityFactory.createObjectFlow(main, setClient.result, callRPIP_service.target);
		return main;
	}

	private void initializeClasses() {
		eHS = ActivityFactory.createClass("eHS");
		client = ActivityFactory.createClass("Client");
		prop_eHS = ActivityFactory.createProperty("eHS", 0, 1, eHS);
		prop_client = ActivityFactory.createProperty("client", 0, 1, client, eHS);
		
		eHS_client = new Association();
		eHS_client.memberEnd.add(prop_eHS);
		eHS_client.memberEnd.add(prop_client);
		eHS_client.ownedMember.add(prop_eHS);
		prop_eHS.association = eHS_client;
		prop_client.association = eHS_client;
		prop_client.owner = eHS_client;		
	}

	private Activity createActivityRPIP_service() {
		Activity RPIP_service = ActivityFactory.createActivity("RPIP_service");
		ReadSelfAction readself = ActivityFactory.createReadSelfAction(RPIP_service, "read self");
		ReadStructuralFeatureAction readClient = ActivityFactory.createReadStructuralFeatureAction(RPIP_service, "read client", prop_client);
		CallOperationAction callRequestPatientInfoPage = ActivityFactory.createCallOperationAction(RPIP_service, "call requestPatientInfoPage", op_requestPatientInfoPages);
		ActivityFactory.createObjectFlow(RPIP_service, readself.result, readClient.object);
		ActivityFactory.createObjectFlow(RPIP_service, readClient.result, callRequestPatientInfoPage.target);
		op_RPIP_service = ActivityFactory.createOperation("RPIP_service", null, RPIP_service, eHS);
		return RPIP_service;
	}

	private Activity createActivityRequestPatientInfoPage(Activity A, Activity B) {
		Activity requestPatientInfoPage = ActivityFactory.createActivity("requestPatientInfoPage");
		InitialNode initial = ActivityFactory.createInitialNode(requestPatientInfoPage, "initial");
		CallBehaviorAction callA = ActivityFactory.createCallBehaviorAction(requestPatientInfoPage, "call A", A);
		CallBehaviorAction callB = ActivityFactory.createCallBehaviorAction(requestPatientInfoPage, "call B", B);
		ActivityFactory.createControlFlow(requestPatientInfoPage, initial, callA);
		ActivityFactory.createControlFlow(requestPatientInfoPage, callA, callB);
		op_requestPatientInfoPages = ActivityFactory.createOperation("requestPatientInfoPages", null, requestPatientInfoPage, client);
		return requestPatientInfoPage;
	}

	private Activity createActivityBWithInitial() {
		Activity B = ActivityFactory.createActivity("B");
		ActivityFactory.createInitialNode(B, "initial B");
		return B;
	}

	private Activity createActivityAWithInitial() {
		Activity A = ActivityFactory.createActivity("A");
		ActivityFactory.createInitialNode(A, "initial A");
		return A;
	}
	
	private Activity createActivityBWithoutInitial() {
		Activity B = ActivityFactory.createActivity("B");
		return B;
	}

	private Activity createActivityAWithoutInitial() {
		Activity A = ActivityFactory.createActivity("A");
		return A;
	}

	@Override
	public void notify(Event event) {
		if(!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
			System.err.println(event);
		}
		
	}
	
}
