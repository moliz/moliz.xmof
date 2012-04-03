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
import java.util.HashMap;
import java.util.List;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.ExecutionEventProvider;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.BreakpointEventImpl;
import org.modelexecution.fumldebug.core.event.impl.StepEventImpl;

import fUML.Debug;
import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Actions.BasicActions.CallBehaviorActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityFinalNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationList;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivationList;
import fUML.Semantics.Activities.IntermediateActivities.ObjectNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Activities.IntermediateActivities.ControlNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.DecisionNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.SemanticVisitor;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;

import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

public aspect EventEmitterAspect implements ExecutionEventListener {
 
	private ExecutionEventProvider eventprovider = null;
	private List<Event> eventlist = new ArrayList<Event>();
	
	// Data structure for saving the Activations of the initial enabled nodes of an ActivityExecution  	
	private HashMap<ActivityExecution, List<ActivityNodeActivation>> initialEnabledNodeActivations = new HashMap<ActivityExecution, List<ActivityNodeActivation>>();
	
	// Data structure for saving the ActivityEntryEvent of an ActivityExecution
	private HashMap<ActivityExecution, ActivityEntryEvent> activityentryevents = new HashMap<ActivityExecution, ActivityEntryEvent>();
	// Data structure for saving the ActivityNodeEntryEvents for the ActivityNodes of an ActivityExecution
	private HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeEntryEvent>> activitynodeentryevents = new HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeEntryEvent>>();
	// Data structure for saving which ActivityNodeActivation started the execution of which ActivityExecutions 
	private HashMap<ActivityExecution, ActivityNodeActivation> activitycalls = new HashMap<ActivityExecution, ActivityNodeActivation>(); 
	// Data structure for saving which ActivityExecution was called by which ActivityExecution and which ActivityExecutions were called by it
	private HashMap<ActivityExecution, ActivityExecutionHierarchyEntry> activityexecutionhierarchy = new HashMap<ActivityExecution, ActivityExecutionHierarchyEntry>(); 
	
	public EventEmitterAspect()	{
		eventprovider = ExecutionContext.getInstance().getExecutionEventProvider();
		eventprovider.addEventListener(this);		
	}	
	
	/**
	 * Call of method ActivityExecution.execute() within ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)
	 * @param execution Execution object of the executed behavior
	 */
	private pointcut activityExecution(ActivityExecution execution) : call (void Execution.execute()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(execution);
		
	/**
	 * Handling of ActivityEntryEvent
	 * @param execution Execution object of the executed behavior
	 */
	before(ActivityExecution execution) : activityExecution(execution) {
		/*
		 * Initialization of the data structures because this is the starting point of an activity execution
		 */
		this.initialEnabledNodeActivations = new HashMap<ActivityExecution, List<ActivityNodeActivation>>();
		this.activityentryevents = new HashMap<ActivityExecution, ActivityEntryEvent>();
		this.activitynodeentryevents = new HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeEntryEvent>>();
						
		handleNewActivityExecution(execution, null, null);
	}		
	
	/**
	 * Handling of ActivityExitEvent in EXECUTION MODE
	 * @param execution
	 */
	after(ActivityExecution execution) : activityExecution(execution) {
		if(!ExecutionContext.getInstance().isDebugMode()) {
			handleEndOfActivityExecution(execution);		
		}
	}					
	
	/**
	 * Execution of the method ActionActivation.fire(TokenList)
	 * @param activation Activation object of the Action
	 */
	private pointcut fireActionActivationExecution(ActionActivation activation) : execution (void ActionActivation.fire(TokenList)) && target(activation);
	
	/**
	 * Handling of ActivityNodeEntryEvent for Actions
	 * @param activation Activation object of the 
	 */
	before(ActionActivation activation) : fireActionActivationExecution(activation) {
		handleActivityNodeEntry(activation);
	}
	
	/**
	 * Call of the method ActionActivation.sendOffers() within ActionActivation.fire(TokenList)
	 * @param activation Activation object of the Action for which sendOffers() is called 
	 */
	private pointcut fireActionActivationSendOffers(ActionActivation activation) : call(void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList));
	
	/**
	 * Handling of ActivityNodeExitEvent for Actions
	 * @param activation Activation object of the Action
	 */
	before(ActivityNodeActivation activation) : fireActionActivationSendOffers(activation) {	
		if(activation instanceof CallActionActivation) {
			if(((CallActionActivation)activation).callExecutions.size() > 0) {
				return;
			}
		}
		handleActivityNodeExit(activation);
	}
		
	/**
	 * Execution of the method ControlNodeActivation.fire(TokenList)
	 * @param activation Activation object of the ControlNode for which fire(TokenList) is called
	 */
	private pointcut controlNodeFire(ControlNodeActivation activation) : execution (void ControlNodeActivation.fire(TokenList)) && target(activation);
	
	/**
	 * Handling of ActivityNodeEntryEvent for ControlNodes
	 * @param activation Activation object of the ControlNode
	 */
	before(ControlNodeActivation activation) : controlNodeFire(activation) {
		if(activation.node==null) {
			//anonymous fork node
			return;
		}						
		handleActivityNodeEntry(activation);
	}
	
	/**
	 * Execution of the method ActivityFinalNodeActivation.fire(TokenList)
	 * @param activation Activation object of the ActivityFinalNode
	 */
	private pointcut activityFinalNodeFire(ActivityFinalNodeActivation activation) : execution (void ActivityFinalNodeActivation.fire(TokenList)) && target(activation);
	
	/**
	 * Handling of ActivityNodeExitEvent for ActivityFinalNodeActivation
	 * @param activation activation object of the ActivityFinalNode
	 */
	after(ActivityFinalNodeActivation activation) : activityFinalNodeFire(activation) {
		handleActivityNodeExit(activation);
	}
	
	/**
	 * Call of ActivityNodeActivation.sendOffers(TokenList) within ControlNodeActivation.fire(TokenList)
	 * @param activation Activation object for which sendOffers(TokenList) is called
	 */
	private pointcut controlNodeFireSendOffers(ControlNodeActivation activation) : call(void ActivityNodeActivation.sendOffers(TokenList)) && target(activation) && withincode(void ControlNodeActivation.fire(TokenList));
	
	/**
	 * Handling of ActivityNodeExitEvent for MergeNode, InitialNode, ForkNode, JoinNode
	 * @param activation Activation object of the MergeNode, InitialNode, ForkNode, or JoinNode
	 */
	before(ControlNodeActivation activation) : controlNodeFireSendOffers(activation) {
		if(activation.node==null) {
			//anonymous fork node
			return;
		}
		handleActivityNodeExit(activation);
	}
		
	/**
	 * Call of the method ActivityEdgeInstance.sendOffer(TokenList)) in the execution context of DecisionNodeActivation.fire(TokenList))
	 * @param activation Activation object the DecisionNode in which's context ActivityEdgeInstance.sendOffer(TokenList) is called
	 */
	private pointcut decisionNodeFireEdgeSendOffer(DecisionNodeActivation activation) : call(void ActivityEdgeInstance.sendOffer(TokenList))  && cflow (execution(void DecisionNodeActivation.fire(TokenList)) && target(activation));
	
	/**
	 * Handling of ActivityNodeExitEvent for DecisionNode
	 * @param activation Activation object of the DecisionNode
	 */
	before(DecisionNodeActivation activation) : decisionNodeFireEdgeSendOffer(activation) {
		/*
		 * This may occur more than once because ActivityEdgeInstance.sendOffer(TokenList) 
		 * is called in a loop in DecisionNodeActivation.fire(TokenList)
		 */		
		handleActivityNodeExit(activation);
	}	
	
	/**
	 * Execution of DecisionNodeActivation.fire(TokenList)
	 * @param activation DecisioNodeActivation object for which fire(TokenList) is called
	 */
	private pointcut decisionNodeFire(DecisionNodeActivation activation) : execution (void DecisionNodeActivation.fire(TokenList)) && target(activation);
	
	/**
	 * Handling of ActivityNodeExitEvent for DecisionNode if no
	 * outgoing edge exists or if no guard of any outgoing edge
	 * evaluates to true
	 * @param activation
	 */
	after(DecisionNodeActivation activation) : decisionNodeFire(activation) {
		Event e = eventlist.get(eventlist.size()-2);
		if(e instanceof StepEvent) {
			if(((StepEvent)e).getLocation() == activation.node) {		
				handleActivityNodeExit(activation);
			}
		}		
	}	
	
	/*
	 * Handling of StepEvent in Execution Mode
	 * TODO anpassen mit debug mode: was ist ein step bei der execution?
	 */
	private pointcut fireActivityNodeActivationCall(ActivityNodeActivation activation) : call (void ActivityNodeActivation.fire(TokenList)) && target(activation) && if(!(ExecutionContext.getInstance().isDebugMode()));	
	before(ActivityNodeActivation activation) : fireActivityNodeActivationCall(activation) {				
		if(activation.node==null) {
			//anonymous fork node
			return;
		}
		if(activation instanceof ObjectNodeActivation) {
			return;
		}
		eventprovider.notifyEventListener(new StepEventImpl(activation.node));
	}	
	
	public void notify(Event event) {
		eventlist.add(event);
	}
		
	/**
	 * Call of Object_.destroy() within Executor.execute(*)
	 * in DEBUG MODE
	 * @param o Object_ for which destroy() is called
	 */
	private pointcut debugExecutorDestroy(Object_ o) : call (void Object_.destroy()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(o) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the method Executor.execute() from destroying the ActivityExecution 
	 * in DEBUG MODE
	 * This is done after the execution of the Activity has finished see {@link #handleEndOfActivityExecution(ActivityExecution)}
	 * @param o
	 */
	void around(Object_ o) : debugExecutorDestroy(o) {
	}
	
	private pointcut debugCallBehaviorActionActivationDestroy(Object_ o, CallActionActivation activation) : call (void Object_.destroy()) && withincode(void CallActionActivation.doAction()) && this(activation) && target(o) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the method CallActionActivation.doAction() from destroying the Execution of the called Activity
	 * in DEBUG MODE.
	 * This is done when the execution of the called Activity is finished see {@link #handleEndOfActivityExecution(ActivityExecution)}
	 * @param o Execution that should be destroyed
	 * @param activation Activation of the CallAction
	 */
	void around(Object o, CallActionActivation activation) : debugCallBehaviorActionActivationDestroy(o, activation) {
		if(callsOpaqueBehaviorExecution(activation)) {
			proceed(o, activation);
		}
	}

	private pointcut debugRemoveCallExecution(CallActionActivation activation) : call (void CallActionActivation.removeCallExecution(Execution)) && withincode(void CallActionActivation.doAction()) && this(activation) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the method CallActionActivation.removeCallExecution from removing the
	 * CallExecution within CallActionActivation.doAction()
	 * in DEBUG MODE
	 * This is done when the execution of the called Activity finished see {@link #handleEndOfActivityExecution(ActivityExecution)}
	 * @param activation
	 */
	void around(CallActionActivation activation) : debugRemoveCallExecution(activation) {	
		if(callsOpaqueBehaviorExecution(activation)) {
			proceed(activation);
		}
	}
	
	private pointcut callBehaviorActionSendsOffers(CallBehaviorActionActivation activation) : call (void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList)) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the method CallBehaviorActionActivation.fire() from sending offers (if an Activity was called)
	 * in DEBUG MODE
	 * This is done when the execution of the called Activity is finished see {@link #handleEndOfActivityExecution(ActivityExecution)}
	 */
	void around(CallBehaviorActionActivation activation) : callBehaviorActionSendsOffers(activation) {
		if(activation.callExecutions.size()==0) {
			// If an OpaqueBehaviorExecution was called, this Execution was already removed in CallActionActivation.doAction()
			proceed(activation);
		}
	}
	
	private pointcut callBehaviorActionCallIsReady(CallBehaviorActionActivation activation) : call (boolean ActionActivation.isReady()) && target(activation) && withincode(void ActionActivation.fire(TokenList)) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Ensures that the do-while loop in the Action.fire() method is not called
	 * for a CallBehaviorActionActivation that calls an Activity by returning false 
	 * for CallBehaviorActionActiviation.fire()
	 * in DEBUG MODE
	 * After the execution of the called Activity, is is checked if the CallBehaviorAction
	 * can be executed again see {@link #handleEndOfActivityExecution(ActivityExecution)}
	 * @return false
	 */
	boolean around(CallBehaviorActionActivation activation) : callBehaviorActionCallIsReady(activation) {
		if(activation.callExecutions.size()==0) {
			// If an OpaqueBehaviorExecution was called, this Execution was already removed in CallActionActivation.doAction()
			return proceed(activation);
		} else {
			return false;
		}
	}
	
	private boolean callsOpaqueBehaviorExecution(CallActionActivation activation) {
		if(activation.callExecutions.get(activation.callExecutions.size()-1) instanceof OpaqueBehaviorExecution) {
			return true;
		}
		return false;
	}
	
	/**
	 * Call of ActivityNodeActivation.fire(TokenList) within void ActivityNodeActivation.receiveOffer() 
	 * in the execution flow of ActivityNodeActivationGroup.run(ActivityNodeActivationList)
	 * in DEBUG MODE
	 * i.e., call of ActivityNodeActivation.fire(TokenList) of the initial enabled nodes  
	 * @param activation Activation object of the ActivityNode for which fire(TokenList) is called
	 * @param tokens Tokens which are the parameters for ActivityNodeActivation.fire(TokenList)
	 */
	private pointcut debugActivityNodeFiresInitialEnabledNodes(ActivityNodeActivation activation, TokenList tokens) : call (void ActivityNodeActivation.fire(TokenList)) && withincode(void ActivityNodeActivation.receiveOffer()) && cflow(execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList))) && target(activation) && args(tokens) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the call of the method ActivityNodeActivation.fire(TokenList)
	 * for an initial enabled node and adds it to the enabled activity nodes instead 
	 * in DEBUG MODE
	 * @param activation Activation object of the initial enabled activity node
	 * @param tokens Tokens which are the parameters for the fire(TokenList) method
	 */
	void around(ActivityNodeActivation activation, TokenList tokens) : debugActivityNodeFiresInitialEnabledNodes(activation, tokens) {
		addEnabledActivityNodeActivation(0, activation, tokens);		
		if(activation.node != null) {
			if(ExecutionContext.getInstance().hasBreakpoint(activation.node)) {
				ActivityEntryEvent parentevent = this.activityentryevents.get(activation.getActivityExecution());
				BreakpointEvent event = new BreakpointEventImpl(activation.node, parentevent);
				eventprovider.notifyEventListener(event);
			}
		}
	}
	
	private void addEnabledActivityNodeActivation(int position, ActivityNodeActivation activation, TokenList tokens) {
		ActivityExecution currentActivityExecution = activation.getActivityExecution();
		List<ActivationConsumedTokens> enabledNodes = ExecutionContext.getInstance().enabledActivations.get(currentActivityExecution);		
		enabledNodes.add(position, new ActivationConsumedTokens(activation, tokens));
		if(activation instanceof ActionActivation) {
			((ActionActivation)activation).firing = false;
		}
	}

	private boolean hasCallingActivityEnabledNodes(ActivityExecution execution) {
		ActivityExecutionHierarchyEntry hierarchyentry = activityexecutionhierarchy.get(execution);					
		ActivityExecution callingexecution = hierarchyentry.parentexecution.execution;
		
		return (hasEnabledNodesIncludingSubActivities(callingexecution));		
	}
	
	private boolean hasEnabledNodesIncludingSubActivities(ActivityExecution execution) {						
		List<ActivityNode> enablednodes = ExecutionContext.getInstance().getEnabledNodes(execution.hashCode());
		
		if(enablednodes != null) {
			if(enablednodes.size() > 0) {
				return true;
			}
		}
		
		ActivityExecutionHierarchyEntry hierarchyentry = activityexecutionhierarchy.get(execution);
		 
		List<ActivityExecutionHierarchyEntry> calledexecutions = hierarchyentry.calledexecutions;
		for(int i=0; i<calledexecutions.size(); ++i) {			
			ActivityExecution e = calledexecutions.get(i).execution;
			boolean hasEnabledNode = hasEnabledNodesIncludingSubActivities(e);
			if(hasEnabledNode) {
				return true;
			}
		}
		
		return false;
	}
	
	private class ActivityExecutionHierarchyEntry {
		ActivityExecution execution = null;
		ActivityExecutionHierarchyEntry parentexecution = null;
		List<ActivityExecutionHierarchyEntry> calledexecutions = new ArrayList<ActivityExecutionHierarchyEntry>();
		
		ActivityExecutionHierarchyEntry(ActivityExecution execution, ActivityExecutionHierarchyEntry parentexecution) {
			this.execution = execution;
			this.parentexecution = parentexecution;
		}
	}
	
	/**
	 * Call of ActivityNodeActivation.fire(TokenList) within ActivityNodeActivation.receiveOffer()
	 * which does not happen in the execution flow of ActivityNodeActivationGroup.run(ActivityNodeActivationList)
	 * in DEBUG mode
	 * i.e., call of ActivityNodeActivation.fire(TokenList) of all ActivityNodes other than initial enabled nodes
	 * @param activation Activation object of the ActivityNode for which fire(TokenList) is called
	 * @param tokens Tokens that are the parameter of fire(TokenList)
	 */
	private pointcut debugActivityNodeFiresOtherThanInitialEnabledNodes(ActivityNodeActivation activation, TokenList tokens) : call (void ActivityNodeActivation.fire(TokenList)) && withincode(void ActivityNodeActivation.receiveOffer()) && !(cflow(execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList)))) && target(activation) && args(tokens) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the call of the method ActivityNodeActivation.fire(TokenList)
	 * and adds it to enabled activity node list instead
	 * in DEBUG MODE
	 * @param activation ActivityNodeActivation object for which fire(TokenList) is called
	 * @param tokens Tokens that are the parameter of fire(TokenList)
	 */
	void around(ActivityNodeActivation activation, TokenList tokens) : debugActivityNodeFiresOtherThanInitialEnabledNodes(activation, tokens) {
		if(activation.node == null) {
			//anonymous fork
			proceed(activation, tokens);
			return;
		}
		if(activation instanceof ObjectNodeActivation) {
			proceed(activation, tokens);
			return;
		}
		
		// Consider Breakpoint
		//boolean isResume = ExecutionContext.getInstance().isResume;
		boolean hasBreakpoint = ExecutionContext.getInstance().hasBreakpoint(activation.node);
		//boolean breakpointhit = (isResume && hasBreakpoint);		
		
		if(tokens.size() > 0) {
			addEnabledActivityNodeActivation(0, activation, tokens);
			//if(breakpointhit){
			if(hasBreakpoint) {
				ActivityEntryEvent parentevent = this.activityentryevents.get(activation.getActivityExecution());
				ExecutionContext.getInstance().isResume = false;
				BreakpointEvent event = new BreakpointEventImpl(activation.node, parentevent);
				eventprovider.notifyEventListener(event);				
			}
		}
	}
	
	
	/**
	 * Execution of ActivityNodeActivation.fire(TokenList)
	 * in DEBUG MODE
	 * @param activation Activation object for which fire(TokenList) is called
	 */
	private pointcut debugActivityNodeFiresExecution(ActivityNodeActivation activation) : execution (void ActivityNodeActivation.fire(TokenList)) && target(activation) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Handling of StepEvent for ActivityNodes
	 * in DEBUG MODE
	 * @param activation Activation object for the ActivityNode
	 */
	after(ActivityNodeActivation activation) :  debugActivityNodeFiresExecution(activation) {		
		if(activation.node == null) {
			// anonymous fork
			return;
		}
		if(activation instanceof ObjectNodeActivation) {
			return;
		}
		if(activation.getActivityExecution().getTypes().size() == 0){
			// Activity was already destroyed, i.e., the Activity already finished
			// This can happen in the case of existing breakpoints in resume mode				
			return;
		}
		boolean hasEnabledNodes = hasEnabledNodesIncludingSubActivities(activation.getActivityExecution());
		if(!hasEnabledNodes) {
			handleEndOfActivityExecution(activation.getActivityExecution());
		} else {	
			if(activation instanceof CallActionActivation) {
				if(((CallActionActivation)activation).callExecutions.size() > 0) {
					return;
				}
			}
			// Consider breakpoints
			boolean isResume = ExecutionContext.getInstance().isResume;
			boolean isBreakpointSet = ExecutionContext.getInstance().hasBreakpoint(activation.node);
			if(isResume) { // && !isBreakpointSet) {
				//if breakpoint was hit, isResume would be false 
				handleResume(activation);
				return;
			}
			eventprovider.notifyEventListener(new StepEventImpl(activation.node));			
		}		
	}
	
	private void handleResume(ActivityNodeActivation activation) {
		// Consider breakpoints
		int activityexecutionID = activation.getActivityExecution().hashCode();		
		ExecutionContext.getInstance().nextStep(activityexecutionID);
	}
	
	/**
	 * Execution of ActionActivation.sendOffers() in the execution context of ActionActivation.fire(TokenList)
	 * in DEBUG MODE
	 * @param activation Activation object for which sendOffers() is called
	 */
	private pointcut debugFireActionActivationSendOffers(ActionActivation activation) : execution(void ActionActivation.sendOffers()) && target(activation) && cflow (execution(void ActionActivation.fire(TokenList))) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Handles the do-while loop in the method ActionActivation.fire(TokenList)
	 * (is fireAgain)
	 * in DEBUG MODE
	 * If the ActionActivation can fire again it is added to the enabled activity node list
	 * and because the token offers are consumed using the activation.takeOfferedTokens() method,
	 * the activation.fire(TokenList) method does not execuite the action's behavior again
	 * @param activation
	 */
	after(ActionActivation activation) : debugFireActionActivationSendOffers(activation) {								
		SemanticVisitor._beginIsolation();
		boolean fireAgain = false;
		activation.firing = false;
		TokenList incomingTokens = null;
		if (activation.isReady()) {
			incomingTokens = activation.takeOfferedTokens();
			fireAgain = incomingTokens.size() > 0;
			activation.firing = activation.isFiring() & fireAgain;
		}
		SemanticVisitor._endIsolation();
		
		if(fireAgain) {
			addEnabledActivityNodeActivation(0, activation, incomingTokens);
		}		
	}

	/**
	 * Call of ActivityNodeActivationGroup.run(ActivityNodeActivationList)
	 * in DEBUG MODE
	 */
	private pointcut activityActivationGroupRun(ActivityNodeActivationGroup activationgroup) : call (void ActivityNodeActivationGroup.run(ActivityNodeActivationList)) && target(activationgroup) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Handling of first StepEvent 
	 * in DEBUG MODE.
	 * First step is the step were the activity execution started and 
	 * the initial enabled nodes are determined.
	 */
	after(ActivityNodeActivationGroup activationgroup) : activityActivationGroupRun(activationgroup) {
		if(initialEnabledNodeActivations.get(activationgroup.activityExecution).size() == 0) {
			return;
		}
		eventprovider.notifyEventListener(new StepEventImpl(null));
	}
	
	/**
	 * Execution of ActivityNodeActivationList.addValue(*) in the execution flow of 
	 * ActivityNodeActivationGroup.run(ActivityNodeActivationList)
	 * in DEBUG MODE
	 * @param list ActivityNodeActivationList for which addValue(*) is called
	 */
	private pointcut valueAddedToActivityNodeActivationList(ActivityNodeActivationList list, ActivityNodeActivationGroup activationgroup) : execution (void ActivityNodeActivationList.addValue(*))  && target(list) && cflow (execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList)) && target(activationgroup)) && if(ExecutionContext.getInstance().isDebugMode());		
	
	/**
	 * Stores the initial enabled nodes to produce an ActivityExitEvent if no
	 * nodes are enabled or activity contains no nodes.
	 * in DEBUG MODE
	 * @param list
	 */
	after(ActivityNodeActivationList list, ActivityNodeActivationGroup activationgroup) : valueAddedToActivityNodeActivationList(list, activationgroup) {
		initialEnabledNodeActivations.get(activationgroup.activityExecution).add(list.get(list.size()-1));
	}
	
	/**
	 * Execution of Execution.execute()
	 * in DEBUG MODE
	 * @param execution Execution object for which execute() is called 
	 */
	private pointcut activityExecutionExecuteExecution(ActivityExecution execution) : execution (void Execution.execute()) && target(execution)  && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * If there are no initial enabled nodes in the activity a ActivityExitEvent is produced
	 * @param behavior Behavior which has no initial enabled nodes 
	 */
	after(ActivityExecution execution) : activityExecutionExecuteExecution(execution) {
		if(initialEnabledNodeActivations.get(execution).size() == 0 ) {
			handleEndOfActivityExecution(execution);
		}
	}
		
	private pointcut callActivityExecutionExecute(ActivityExecution execution, CallActionActivation activation) : call(void Execution.execute()) && withincode(void CallActionActivation.doAction()) && target(execution) && this(activation) && if(ExecutionContext.getInstance().isDebugMode());
	
	before(ActivityExecution execution, CallActionActivation activation) : callActivityExecutionExecute(execution, activation) {
		ActivityNodeEntryEvent callaentryevent = this.activitynodeentryevents.get(activation.getActivityExecution()).get(activation.node);

		handleNewActivityExecution(execution, activation, callaentryevent);		
	}

	private void handleNewActivityExecution(ActivityExecution execution, ActivityNodeActivation caller, Event parent) {
		this.initialEnabledNodeActivations.put(execution, new ArrayList<ActivityNodeActivation>());
		this.activitynodeentryevents.put(execution, new HashMap<ActivityNode, ActivityNodeEntryEvent>());
		
		ActivityExecutionHierarchyEntry parentexecutionentry = null;
		
		if(caller != null) {
			this.activitycalls.put(execution, caller);
			
			ActivityExecution callerExecution = caller.getActivityExecution();
			parentexecutionentry = this.activityexecutionhierarchy.get(callerExecution);
		} 
		
		ActivityExecutionHierarchyEntry entry = new ActivityExecutionHierarchyEntry(execution, parentexecutionentry);		
		this.activityexecutionhierarchy.put(execution, entry);
		if(parentexecutionentry != null) {
			parentexecutionentry.calledexecutions.add(entry);
		}
		
		ExecutionContext.getInstance().activityExecutions.put(execution.hashCode(), execution);
		ExecutionContext.getInstance().enabledActivations.put(execution, new ArrayList<ActivationConsumedTokens>());
		
		Activity activity = (Activity) (execution.getBehavior());
		ActivityEntryEvent event = new ActivityEntryEventImpl(activity, execution.hashCode(), parent);		
		this.activityentryevents.put(execution, event);
		eventprovider.notifyEventListener(event);
	}
	
	private void handleEndOfActivityExecution(ActivityExecution execution) {
		Activity activity = (Activity) (execution.getBehavior());
		ActivityEntryEvent entryevent = this.activityentryevents.get(execution);
		ActivityExitEvent event = new ActivityExitEventImpl(activity, entryevent);
		
		/*
		 * Clear data structures
		 */
		this.initialEnabledNodeActivations.remove(execution);
		this.activityentryevents.remove(execution);
		this.activitynodeentryevents.remove(execution);
		ExecutionContext.getInstance().enabledActivations.remove(execution);
		
		{
			// Produce the output of activity
			// DUPLICATE CODE from void ActivityExecution.execute()
			ActivityParameterNodeActivationList outputActivations = execution.activationGroup.getOutputParameterNodeActivations();
			for (int i = 0; i < outputActivations.size(); i++) {
				ActivityParameterNodeActivation outputActivation = outputActivations.getValue(i);

				ParameterValue parameterValue = new ParameterValue();
				parameterValue.parameter = ((ActivityParameterNode) (outputActivation.node)).parameter;

				TokenList tokens = outputActivation.getTokens();
				for (int j = 0; j < tokens.size(); j++) {
					Token token = tokens.getValue(j);
					Value value = ((ObjectToken) token).value;
					if (value != null) {
						parameterValue.values.addValue(value);
						Debug.println("[event] Output activity=" + activity.name
								+ " parameter=" + parameterValue.parameter.name
								+ " value=" + value);
					}
				}

				execution.setParameterValue(parameterValue);
			}
		}
		
		ActivityNodeActivation caller = this.activitycalls.remove(execution);
		if(caller instanceof CallActionActivation) {				
			// Get the output from the called activity
			// DUPLICATE CODE from void CallActionActivation.doAction()
			ParameterValueList outputParameterValues = execution.getOutputParameterValues();
			for (int j = 0; j < outputParameterValues.size(); j++) {
				ParameterValue outputParameterValue = outputParameterValues.getValue(j);
				OutputPin resultPin = ((CallAction)caller.node).result.getValue(j);
				((CallActionActivation)caller).putTokens(resultPin, outputParameterValue.values);
			}
			
			// Destroy execution of the called activity
			execution.destroy();
			((CallActionActivation)caller).removeCallExecution(execution);
									
			// Notify about ActivityExitEvent
			eventprovider.notifyEventListener(event);
			
			// Notify about Exit of CallAction
			handleActivityNodeExit(caller);
			
			// Call sendOffer() from the CallAction
			((CallActionActivation) caller).sendOffers();
			
			// Check if can fire again
			((CallActionActivation) caller).firing = false;
			if(caller.isReady()) {
				TokenList incomingTokens = caller.takeOfferedTokens();
				if(incomingTokens.size() > 0) {
					addEnabledActivityNodeActivation(0, caller, new TokenList());
				}				
			}
					
			boolean hasCallerEnabledNodes = hasCallingActivityEnabledNodes(execution);
			ActivityExecutionHierarchyEntry entry = this.activityexecutionhierarchy.get(execution);
			if(entry != null) {
				ActivityExecutionHierarchyEntry parententry = entry.parentexecution;
				if(parententry != null) {
					parententry.calledexecutions.remove(entry);
				}
				this.activityexecutionhierarchy.remove(execution);
			}
						
			if(!hasCallerEnabledNodes) {
				handleEndOfActivityExecution(caller.getActivityExecution());
			} else {
				// Consider breakpoints
				boolean isResume = ExecutionContext.getInstance().isResume;
				boolean isBreakpointSet = ExecutionContext.getInstance().hasBreakpoint(caller.node);
				if(isResume && !isBreakpointSet) {
					handleResume(caller);
					return;
				}
				eventprovider.notifyEventListener(new StepEventImpl(caller.node));
			}
			
			return;
		} else {
			// ActivityExecution was triggered by user, i.e., ExecutionContext.debug() was called
			ParameterValueList outputValues = execution.getOutputParameterValues();
			ExecutionContext.getInstance().activityExecutionOutput.put(execution, outputValues);
			execution.destroy();
			eventprovider.notifyEventListener(event);
		}
	}		
	
	private void handleActivityNodeEntry(ActivityNodeActivation activation) {		
		ActivityEntryEvent activityentry = this.activityentryevents.get(activation.getActivityExecution());		
		ActivityNodeEntryEvent event = new ActivityNodeEntryEventImpl(activation.node, activityentry);
		this.activitynodeentryevents.get(activation.getActivityExecution()).put(activation.node, event);
		eventprovider.notifyEventListener(event);
	}

	private void handleActivityNodeExit(ActivityNodeActivation activation) {
		ActivityNodeEntryEvent entry = this.activitynodeentryevents.get(activation.getActivityExecution()).get(activation.node);
		ActivityNodeExitEvent event = new ActivityNodeExitEventImpl(activation.node, entry);
		eventprovider.notifyEventListener(event);
	}
	
}