/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.aspect;

import java.util.ArrayList;
import java.util.List;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.ExecutionEventProvider;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.StepEventImpl;

import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityFinalNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationList;
import fUML.Semantics.Activities.IntermediateActivities.ObjectNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Activities.IntermediateActivities.ControlNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.DecisionNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.SemanticVisitor;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public aspect EventEmitterAspect implements ExecutionEventListener {
 
	private ExecutionEventProvider eventprovider = null;
	private List<Event> eventlist = new ArrayList<Event>();
	
	public EventEmitterAspect()	{
		eventprovider = ExecutionContext.getInstance().getExecutionEventProvider();
		eventprovider.addEventListener(this);		
	}	
	
	/**
	 * Execution of method ActivityExecution.execute()
	 * @param execution Execution object of the executed behavior
	 */
	private pointcut activityExecution(ActivityExecution execution) : execution (void ActivityExecution.execute()) && target(execution);
		
	/**
	 * Handling of ActivityEntryEvent
	 * @param execution Execution object of the executed behavior
	 */
	before(ActivityExecution execution) : activityExecution(execution) {
		Activity activity = (Activity) (execution.getTypes().getValue(0));
		ActivityEntryEvent event = new ActivityEntryEventImpl(activity);
		eventprovider.notifyEventListener(event);
	}
	
	/**
	 * Handling of ActivityExitEvent in EXECUTION MODE
	 * @param execution
	 */
	after(ActivityExecution execution) : activityExecution(execution) {
		if(!ExecutionContext.getInstance().isDebugMode()) {
			Activity activity = (Activity) (execution.getTypes().getValue(0));
			ActivityExitEvent event = new ActivityExitEventImpl(activity);
			eventprovider.notifyEventListener(event);			
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
		eventprovider.notifyEventListener(new ActivityNodeEntryEventImpl(activation.node));				
	}
	
	/**
	 * Call of the method ActionActivation.sendOffers() within ActionActivation.fire(TokenList)
	 * @param activation Activation object of the Action for which sendOffers() is called 
	 */
	//private pointcut fireActionActivationSendOffers(ActionActivation activation, ActionActivation cflowactivation) : call(void ActionActivation.sendOffers()) && target(activation) && cflow (execution(void ActionActivation.fire(TokenList)) && target(cflowactivation));
	private pointcut fireActionActivationSendOffers(ActionActivation activation) : call(void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList));
	
	/**
	 * Handling of ActivityNodeExitEvent for Actions
	 * @param activation Activation object of the Action
	 */
	//before(ActivityNodeActivation activation, ActivityNodeActivation cflowactivation) : fireActionActivationSendOffers(activation, cflowactivation) {
	before(ActivityNodeActivation activation) : fireActionActivationSendOffers(activation) {
		/*
		if(activation.getClass() != cflowactivation.getClass()) {
			return;
		}*/				
		eventprovider.notifyEventListener(new ActivityNodeExitEventImpl(activation.node));
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
		eventprovider.notifyEventListener(new ActivityNodeEntryEventImpl(activation.node));		
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
		ActivityNodeExitEvent event = new ActivityNodeExitEventImpl(activation.node);				
		eventprovider.notifyEventListener(event);
	}
	
	/**
	 * Call of ActivityNodeActivation.sendOffers(TokenList) in the execution context of ControlNodeActivation.fire(TokenList)
	 * @param activation Activation object for which sendOffers(TokenList) is called
	 * @param cflowactivation Activation object in which's execution context of fire(TokenList) activation.sendOffers(TokenList) is called
	 */
	//private pointcut controlNodeFireSendOffers(ActivityNodeActivation activation, ActivityNodeActivation cflowactivation) : call(void ActivityNodeActivation.sendOffers(TokenList)) && target(activation) && cflow (execution(void ControlNodeActivation.fire(TokenList)) && target(cflowactivation));
	private pointcut controlNodeFireSendOffers(ControlNodeActivation activation) : call(void ActivityNodeActivation.sendOffers(TokenList)) && target(activation) && withincode(void ControlNodeActivation.fire(TokenList));
	
	/**
	 * Handling of ActivityNodeExitEvent for MergeNode, InitialNode, ForkNode, JoinNode
	 * @param activation Activation object of the MergeNode, InitialNode, ForkNode, or JoinNode
	 * @param cflowactivation Activation object of the ActivityNode in which's execution context of fire(TokenList) activation.sendOffers(TokenList) is called
	 */
	//before(ActivityNodeActivation activation, ActivityNodeActivation cflowactivation) : controlNodeFireSendOffers(activation, cflowactivation) {
	before(ControlNodeActivation activation) : controlNodeFireSendOffers(activation) {
		if(activation.node==null) {
			//anonymous fork node
			return;
		}
		/*
		if(activation.getClass() != cflowactivation.getClass()) {
			//sendOffers() was only called in the context of the ControlNode but not for the ContronNode itself
			return;
		}*/
		eventprovider.notifyEventListener(new ActivityNodeExitEventImpl(activation.node));
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
		 * HINT may occur more than once because ActivityEdgeInstance.sendOffer(TokenList) 
		 * is called in a loop in DecisionNodeActivation.fire(TokenList)
		 */		
		eventprovider.notifyEventListener(new ActivityNodeExitEventImpl(activation.node));
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
				eventprovider.notifyEventListener(new ActivityNodeExitEventImpl(activation.node));
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
	
	// DEBUG MODE - START
	
	/**
	 * Call of Object_.destroy() within Executor.execute(*)
	 * in DEBUG MODE
	 * @param o Object_ for which destroy() is called
	 */
	private pointcut debugExecutorDestroy(Object_ o) : call (void Object_.destroy()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(o) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Prevents the method Executor.execute() from destroying the ActivityExecution 
	 * in DEBUG MODE
	 * @param o
	 */
	void around(Object_ o) : debugExecutorDestroy(o) {
	}
	
	/*
	 * Prevents the execution of the ActivityNodeActivation.fire() method in debugging mode
	 */

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
		ExecutionContext.getInstance().addEnabledActivityNodeActivation(0, activation, tokens);
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
		
		if(tokens.size() > 0) {
			ExecutionContext.getInstance().addEnabledActivityNodeActivation(0, activation, tokens);
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
		if(ExecutionContext.getInstance().getEnabledNodes().size() == 0 ) {
			eventprovider.notifyEventListener(new ActivityExitEventImpl(activation.node.activity));
		} else {			
			eventprovider.notifyEventListener(new StepEventImpl(activation.node));
		}		
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
			ExecutionContext.getInstance().addEnabledActivityNodeActivation(0, activation, incomingTokens);
		}
		
	}

	/**
	 * Call of run(ActivityNodeActivationList)
	 * in DEBUG MODE
	 */
	private pointcut activityActivationGroupRun() : call (void ActivityNodeActivationGroup.run(ActivityNodeActivationList)) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Handling of first StepEvent 
	 * in DEBUG MODE.
	 * First step is the step were the activity execution started and 
	 * the initial enabled nodes are determined.
	 */
	after() : activityActivationGroupRun() {
		if(ExecutionContext.getInstance().getInitialEnabledNodes().size() == 0) {
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
	private pointcut valueAddedToActivityNodeActivationList(ActivityNodeActivationList list) : execution (void ActivityNodeActivationList.addValue(*))  && target(list) && cflow (execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList))) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * Stores the initial enabled nodes to produce an ActivityExitEvent if no
	 * nodes are enabled or activity contains no nodes.
	 * in DEBUG MODE
	 * @param list
	 */
	after(ActivityNodeActivationList list) : valueAddedToActivityNodeActivationList(list) {
		ExecutionContext.getInstance().addInitialEnabledNodes(list.get(list.size()-1));
	}
	
	/**
	 * Execution of Executor.execute(Behavior, Object_, ParameterValueList)
	 * in DEBUG MODE
	 * @param behavior Behavior which is passed to Executor.execute(*)
	 */
	private pointcut executorExecuteFinished(Behavior behavior) : execution (ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && args(behavior, Object_, ParameterValueList) && if(ExecutionContext.getInstance().isDebugMode());
	
	/**
	 * If there are no initial enabled nodes in the activity a ActivityExitEvent is produced
	 * @param behavior Behavior which has no initial enabled nodes 
	 */
	after(Behavior behavior) : executorExecuteFinished(behavior) {
		if(ExecutionContext.getInstance().getInitialEnabledNodes().size() == 0 ) {
			eventprovider.notifyEventListener(new ActivityExitEventImpl((Activity)behavior));
		}
	}
	
	// DEBUG MODE - END
	
}
