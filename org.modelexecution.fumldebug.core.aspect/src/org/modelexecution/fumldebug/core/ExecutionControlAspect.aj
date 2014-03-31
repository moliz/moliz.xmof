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

import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Actions.BasicActions.CallBehaviorActionActivation;
import fUML.Semantics.Actions.BasicActions.CallOperationActionActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ClauseActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ClauseActivationList;
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.StructuredActivityNodeActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroup;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationList;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ControlNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.DecisionNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public aspect ExecutionControlAspect {

	private pointcut activityExecution(ActivityExecution execution) : call (void Execution.execute()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(execution);

	private pointcut inStepwiseExecutionMode() : cflow(execution(void ExecutionContext.executeStepwise(Behavior, Object_, ParameterValueList)));

	private pointcut activityExecutionInStepwiseExecutionMode(ActivityExecution execution) :  activityExecution(execution) && inStepwiseExecutionMode();

	private pointcut inExecutionMode() : cflow(execution(void ExecutionContext.execute(Behavior, Object_, ParameterValueList)));

	private pointcut activityExecutionInExecutionMode(ActivityExecution execution) :  activityExecution(execution) && inExecutionMode();

	/**
	 * Handling of ActivityEntryEvent in stepwise execution mode.
	 * 
	 * @param execution
	 *            Execution object of the executed behavior
	 */
	before(ActivityExecution execution) : activityExecutionInStepwiseExecutionMode(execution) {
		handleActivityEntry(execution, null);
	}

	/**
	 * Handling of ActivityEntryEvent in execution mode.
	 * 
	 * @param execution
	 *            Execution object of the executed behavior
	 */
	before(ActivityExecution execution) : activityExecutionInExecutionMode(execution) {		
		handleActivityEntry(execution, null);
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(execution);
		activityExecutionStatus.setWholeExecutionInResumeMode(true);
	}

	/**
	 * Handling of first resume() call in case of execution mode.
	 * 
	 * @param execution
	 */
	after(ActivityExecution execution) : activityExecutionInExecutionMode(execution) {
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(execution);
		boolean hasEnabledNodes = false;
		if(activityExecutionStatus != null) {
			hasEnabledNodes = activityExecutionStatus.hasEnabledNodesIncludingCallees();
		}
		if (hasEnabledNodes) {
			ExecutionContext.getInstance().resume(activityExecutionStatus.getExecutionID());
		}
	}

	/**
	 * Execution of the method ActionActivation.fire(TokenList).
	 * 
	 * @param activation
	 *            Activation object of the Action
	 */
	private pointcut fireActionActivationExecution(ActionActivation activation) : execution (void ActionActivation.fire(TokenList)) && target(activation);

	/**
	 * Handling of ActivityNodeEntryEvent for Actions.
	 * 
	 * @param activation
	 *            Activation object of the
	 */
	before(ActionActivation activation) : fireActionActivationExecution(activation) {
		handleActivityNodeEntry(activation);
	}

	/**
	 * Execution of the method ControlNodeActivation.fire(TokenList).
	 * 
	 * @param activation
	 *            Activation object of the ControlNode for which fire(TokenList)
	 *            is called
	 */
	private pointcut controlNodeFire(ControlNodeActivation activation) : execution (void ControlNodeActivation.fire(TokenList)) && target(activation);

	/**
	 * Handling of ActivityNodeEntryEvent for ControlNodes.
	 * 
	 * @param activation
	 *            Activation object of the ControlNode
	 */
	before(ControlNodeActivation activation) : controlNodeFire(activation) {
		if (activation.node == null) { // anonymous fork node
			return;
		}
		handleActivityNodeEntry(activation);
	}

	/**
	 * Call of Object_.destroy() within Executor.execute(*).
	 * 
	 * @param o
	 *            Object_ for which destroy() is called
	 */
	private pointcut debugExecutorDestroy(Object_ o) : call (void Object_.destroy()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(o);

	/**
	 * Prevents the method Executor.execute() from destroying the
	 * ActivityExecution. This is done after the execution of the Activity has
	 * finished see {@link #handleEndOfActivityExecution(ActivityExecution)}.
	 * 
	 * @param o
	 */
	void around(Object_ o) : debugExecutorDestroy(o) {

	}

	private pointcut debugCallBehaviorActionActivationDestroy(Object_ o, CallActionActivation activation) : call (void Object_.destroy()) && withincode(void CallActionActivation.doAction()) && this(activation) && target(o);

	/**
	 * Prevents the method CallActionActivation.doAction() from destroying the
	 * Execution of the called Activity. This is done when the execution of the
	 * called Activity is finished see
	 * {@link #handleEndOfActivityExecution(ActivityExecution)}.
	 * 
	 * @param o
	 *            Execution that should be destroyed
	 * @param activation
	 *            Activation of the CallAction
	 */
	void around(Object o, CallActionActivation activation) : debugCallBehaviorActionActivationDestroy(o, activation) {		
		if (callsOpaqueBehaviorExecution(activation)) {
			proceed(o, activation);
		}
	}

	private pointcut debugRemoveCallExecution(CallActionActivation activation) : call (void CallActionActivation.removeCallExecution(Execution)) && withincode(void CallActionActivation.doAction()) && this(activation);

	/**
	 * Prevents the method CallActionActivation.removeCallExecution from
	 * removing the CallExecution within CallActionActivation.doAction(). 
	 * This is done when the execution of the called Activity finished see
	 * {@link #handleEndOfActivityExecution(ActivityExecution)}.
	 * 
	 * @param activation
	 */
	void around(CallActionActivation activation) : debugRemoveCallExecution(activation) {
		if (callsOpaqueBehaviorExecution(activation)) {
			proceed(activation);
		}
	}

	private pointcut callActionSendsOffers(CallActionActivation activation) : call (void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList));

	/**
	 * Prevents the method CallBehaviorActionActivation.fire() from sending
	 * offers (if an Activity was called). This is done when the execution of
	 * the called Activity is finished see
	 * {@link #handleEndOfActivityExecution(ActivityExecution)}.
	 */
	void around(CallActionActivation activation) : callActionSendsOffers(activation) {
		if (activation.callExecutions.size() == 0) {
			if (activation.node instanceof CallBehaviorAction) {
				if (((CallBehaviorAction) activation.node).behavior instanceof OpaqueBehavior) {
					// If an OpaqueBehaviorExecution was called, this Execution
					// was already removed in CallActionActivation.doAction()
					proceed(activation);
				}
			}
		}
	}
	
	private pointcut callActionCallIsReady(CallActionActivation activation) : call (boolean ActionActivation.isReady()) && target(activation) && withincode(void ActionActivation.fire(TokenList));

	/**
	 * Ensures that the do-while loop in the Action.fire() method is not called
	 * for a CallBehaviorActionActivation that calls an Activity by returning
	 * false for CallBehaviorActionActiviation.fire(). After the execution of the
	 * called Activity, is is checked if the CallBehaviorAction can be executed
	 * again see {@link #handleEndOfActivityExecution(ActivityExecution)}.
	 * 
	 * @return false
	 */
	boolean around(CallActionActivation activation) : callActionCallIsReady(activation) {
		if (activation.callExecutions.size() == 0) {
			// If an OpaqueBehaviorExecution was called, this Execution was
			// already removed in CallActionActivation.doAction()
			return proceed(activation);
		} else {
			return false;
		}
	}

	/**
	 * Checks if a CallActionActivation called an OpaqueBehavior.
	 * 
	 * @param activation
	 *            CallActionActivation that shall be checked
	 * @return true if the CallActionActivation called an OpaqueBehavior
	 */
	private boolean callsOpaqueBehaviorExecution(CallActionActivation activation) {
		if (activation.callExecutions.size() > 0) {
			if (activation.callExecutions.get(activation.callExecutions.size() - 1) instanceof OpaqueBehaviorExecution) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Call of ActivityNodeActivation.fire(TokenList) within 
	 * void ActivityNodeActivation.receiveOffer() in the execution flow of
	 * ActivityNodeActivationGroup.run(ActivityNodeActivationList) i.e., call of
	 * ActivityNodeActivation.fire(TokenList) of the initial enabled nodes.
	 * 
	 * @param activation
	 *            Activation object of the ActivityNode for which
	 *            fire(TokenList) is called
	 * @param tokens
	 *            Tokens which are the parameters for
	 *            ActivityNodeActivation.fire(TokenList)
	 */
	private pointcut debugActivityNodeFiresInitialEnabledNodes(ActivityNodeActivation activation, TokenList tokens) : call (void ActivityNodeActivation.fire(TokenList)) && withincode(void ActivityNodeActivation.receiveOffer()) && cflow(execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList))) && target(activation) && args(tokens);

	/**
	 * Prevents the call of the method ActivityNodeActivation.fire(TokenList)
	 * for an initial enabled node and adds it to the enabled activity nodes
	 * instead.
	 * 
	 * @param activation
	 *            Activation object of the initial enabled activity node
	 * @param tokens
	 *            Tokens which are the parameters for the fire(TokenList) method
	 */
	void around(ActivityNodeActivation activation, TokenList tokens) : debugActivityNodeFiresInitialEnabledNodes(activation, tokens) {
		if (activation instanceof ActivityParameterNodeActivation) {
			proceed(activation, tokens);
		} else {
			addEnabledActivityNodeActivation(0, activation, tokens);
		}
	}

	private void addEnabledActivityNodeActivation(int position,	ActivityNodeActivation activation, TokenList tokens) {
		ActivityExecution currentActivityExecution = activation.getActivityExecution();
		ActivityExecutionStatus exestatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(currentActivityExecution);
		exestatus.addEnabledActivation(activation, tokens);		
	}

	/**
	 * Call of ActivityNodeActivation.fire(TokenList) within
	 * ActivityNodeActivation.receiveOffer() which does not happen in the
	 * execution flow of
	 * ActivityNodeActivationGroup.run(ActivityNodeActivationList), i.e., call
	 * of ActivityNodeActivation.fire(TokenList) of all ActivityNodes other than
	 * initial enabled nodes.
	 * 
	 * @param activation
	 *            Activation object of the ActivityNode for which
	 *            fire(TokenList) is called
	 * @param tokens
	 *            Tokens that are the parameter of fire(TokenList)
	 */
	private pointcut debugActivityNodeFiresOtherThanInitialEnabledNodes(ActivityNodeActivation activation, TokenList tokens) : call (void ActivityNodeActivation.fire(TokenList)) && withincode(void ActivityNodeActivation.receiveOffer()) && !(cflow(execution(void ActivityNodeActivationGroup.run(ActivityNodeActivationList)))) && target(activation) && args(tokens);

	/**
	 * Prevents the call of the method ActivityNodeActivation.fire(TokenList)
	 * and adds it to enabled activity node list instead.
	 * 
	 * @param activation
	 *            ActivityNodeActivation object for which fire(TokenList) is
	 *            called
	 * @param tokens
	 *            Tokens that are the parameter of fire(TokenList)
	 */
	void around(ActivityNodeActivation activation, TokenList tokens) : debugActivityNodeFiresOtherThanInitialEnabledNodes(activation, tokens) {
		if (activation.node == null) { // anonymous fork
			proceed(activation, tokens);
			return;
		}

		if (activation instanceof ObjectNodeActivation) {
			proceed(activation, tokens);
			return;
		}

		if (tokens.size() > 0) {
			addEnabledActivityNodeActivation(0, activation, tokens);
		} else {
			if (activation instanceof DecisionNodeActivation) {
				/*
				 * If a decision input flow is provided for a decision node and
				 * this decision node has no other incoming edge no tokens are
				 * provided
				 */
				addEnabledActivityNodeActivation(0, activation, tokens);
			} else if (activation instanceof ExpansionRegionActivation) {
				/*
				 * ExpansionRegionActiviation.takeOfferedTokens() always returns
				 * an empty list of tokens to
				 * ActivityNodeActivation.receiveOffer() which provides the
				 * tokens to the ActivityNodeActivation.fire() method
				 */
				addEnabledActivityNodeActivation(0, activation, tokens);
			}
		}
	}

	/**
	 * Execution of ActivityNodeActivation.fire(TokenList).
	 * 
	 * @param activation
	 *            Activation object for which fire(TokenList) is called
	 */
	private pointcut debugActivityNodeFiresExecution(ActivityNodeActivation activation) : execution (void ActivityNodeActivation.fire(TokenList)) && target(activation);

	/**
	 * Handling of ActivityNodeExitEvent and SuspendEvent for ActivityNodes.
	 * 
	 * @param activation
	 *            Activation object for the ActivityNode
	 */
	after(ActivityNodeActivation activation) :  debugActivityNodeFiresExecution(activation) {
		if (activation.node == null) { // anonymous fork
			return;
		}

		if (activation instanceof ObjectNodeActivation) {
			return;
		}

		// Handle activity node exit event
		if (!(  (activation instanceof CallOperationActionActivation) || 
				(activation instanceof CallBehaviorActionActivation && ((CallBehaviorAction) activation.node).behavior instanceof Activity) || 
				(activation instanceof StructuredActivityNodeActivation) )) {
			// (1) in the case of a call operation action or a call behavior action
			// which calls an activity, the exit event is issued when the called
			// activity execution ends
			// (2) in the case of a structured activity node the exit event is issued
			// when no contained nodes are enabled
			handleActivityNodeExit(activation);
		}
		
		
		if(activation instanceof StructuredActivityNodeActivation) { 
			// For an structured activity node this advice is executed right after its execution started
			// and the initially enabled nodes within this structured activity nodes have been determined.
			updateStructuredActivityNode((StructuredActivityNodeActivation)activation);			
		}

		if (activation.group instanceof ExpansionActivationGroup) {
			// executed node is contained in expansion region
			updateExpansionRegion((ExpansionActivationGroup) activation.group);
		}

		// Handle suspension
		if (activation.getActivityExecution().getTypes().size() == 0) {
			// Activity was already destroyed, i.e., the Activity already
			// finished. This can happen in the case of existing breakpoints in
			// resume mode.
			return;
		}
		
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(activation.getActivityExecution());
		boolean hasEnabledNodes = activityExecutionStatus.hasEnabledNodesIncludingCallees();
		if (!hasEnabledNodes) {
			handleActivityExit(activation.getActivityExecution());
		} else {
			if (activation instanceof CallActionActivation) {
				if (((CallActionActivation) activation).callExecutions.size() > 0) {
					return;
				}
			}
			handleSuspension(activation.getActivityExecution(), activation.node);
		}
	}
	
	private void updateStructuredActivityNode(StructuredActivityNodeActivation structuredActivityNodeActivation) {
		structuredActivityNodeActivation.firing = true; // this is necessary because the doAction method of a structured activity node is interrupted (because it consists of the execution of the contained nodes)
		
		StructuredActivityNodeExecutionStatus structuredActivityNodeExecutionStatus = (StructuredActivityNodeExecutionStatus)getActivityNodeExecutionStatus(structuredActivityNodeActivation);
		structuredActivityNodeExecutionStatus.updateStatus();				
	}
	
	private void updateExpansionRegion(ExpansionActivationGroup expansionActivationGroup) {
		ExpansionRegionActivation expansionRegionActivation = expansionActivationGroup.regionActivation;
		ExpansionRegionExecutionStatus expansionRegionExecutionStatus = (ExpansionRegionExecutionStatus)getActivityNodeExecutionStatus(expansionRegionActivation);
		expansionRegionExecutionStatus.updateStatus();
	}
	/**
	 * Execution of ActionActivation.sendOffers() in the execution context of
	 * ActionActivation.fire(TokenList)
	 * 
	 * @param activation
	 *            Activation object for which sendOffers() is called
	 */
	private pointcut debugFireActionActivationSendOffers(
			ActionActivation activation) : execution(void ActionActivation.sendOffers()) && target(activation) && cflow (execution(void ActionActivation.fire(TokenList)));

	/**
	 * Handles the do-while loop in the method ActionActivation.fire(TokenList)
	 * (is fireAgain) If the ActionActivation can fire again it is added to the
	 * enabled activity node list and because the token offers are consumed
	 * using the activation.takeOfferedTokens() method, the
	 * activation.fire(TokenList) method does not execute the action's behavior
	 * again
	 * 
	 * @param activation
	 */
	after(ActionActivation activation) : debugFireActionActivationSendOffers(activation) {
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(activation.getActivityExecution());
		ActivityNodeExecutionStatus activityNodeExecutionStatus = activityExecutionStatus.getActivityNodeExecutionStatus(activation.node);
		activityNodeExecutionStatus.checkIfCanFireAgain();
	}

	/**
	 * Call of ActivityNodeActivationGroup.run(ActivityNodeActivationList)
	 */
	private pointcut activityActivationGroupRun(ActivityNodeActivationGroup activationgroup) : call (void ActivityNodeActivationGroup.run(ActivityNodeActivationList)) && withincode(void ActivityNodeActivationGroup.activate(ActivityNodeList, ActivityEdgeList)) && target(activationgroup);

	/**
	 * Handling of first SuspendEvent. First step is the step were the activity
	 * execution started and the initial enabled nodes are determined.
	 */
	after(ActivityNodeActivationGroup activationgroup) : activityActivationGroupRun(activationgroup) {
		ActivityExecution activityExecution = null;

		if (activationgroup instanceof ExpansionActivationGroup) {
			activityExecution = ((ExpansionActivationGroup) activationgroup).regionActivation.group.activityExecution;
		} else {
			activityExecution = activationgroup.activityExecution;
		}

		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(activityExecution);
		if (activityExecutionStatus != null) {
			if (activityExecutionStatus.getEnabledNodes().size() == 0) {
				return;
			}
			Activity activity = (Activity) activityExecution.types.get(0);

			handleSuspension(activityExecution, activity);
		}
	}

	/**
	 * Execution of Execution.execute()
	 * 
	 * @param execution
	 *            Execution object for which execute() is called
	 */
	private pointcut activityExecutionExecuteExecution(
			ActivityExecution execution) : execution (void Execution.execute()) && target(execution);

	/**
	 * If there are no initial enabled nodes in the activity a ActivityExitEvent
	 * is produced
	 * 
	 * @param behavior
	 *            Behavior which has no initial enabled nodes
	 */
	after(ActivityExecution execution) : activityExecutionExecuteExecution(execution) {
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(execution);
		if (activityExecutionStatus != null	&& activityExecutionStatus.getEnabledNodes().size() == 0) {
			handleActivityExit(execution);
		}
	}

	/**
	 * Handle call of activity by call action
	 * 
	 * @param execution
	 * @param activation
	 */
	private pointcut callActivityExecutionExecute(ActivityExecution execution,
			CallActionActivation activation) : call(void Execution.execute()) && withincode(void CallActionActivation.doAction()) && target(execution) && this(activation);

	before(ActivityExecution execution, CallActionActivation activation) : callActivityExecutionExecute(execution, activation) {
		handleActivityEntry(execution, activation);
	}
	
	private void handleActivityEntry(ActivityExecution execution, CallActionActivation callerNodeActivation) {
		ExecutionContext.getInstance().executionStatus.addActivityExecution(execution, callerNodeActivation);
	}

	private void handleActivityExit(ActivityExecution execution) { 
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(execution);
		activityExecutionStatus.handleEndOfExecution();
	}

	private void handleActivityNodeEntry(ActivityNodeActivation activation) {
		ExecutionContext.getInstance().eventHandler.handleActivityNodeEntry(activation);
	}

	private void handleActivityNodeExit(ActivityNodeActivation activation) {
		if (activation instanceof CallActionActivation) {
			if (((CallActionActivation) activation).callExecutions.size() > 0) {
				return;
			}
		}
		if (activation instanceof ExpansionRegionActivation) {
			if (((ExpansionRegionActivation) activation).activationGroups.size() > 0) {
				if (((ExpansionRegionActivation) activation).activationGroups.get(0).nodeActivations.get(0).running) {
					return;
				}
			}
		}

		if (activation.node == null) {
			return;
		}
		
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(activation.getActivityExecution());
		ActivityNodeExecutionStatus activityNodeExecutionStatus = activityExecutionStatus.getExecutingActivityNodeExecutionStatus(activation.node);
		activityNodeExecutionStatus.handleEndOfExecution();
	}
	
	private void handleSuspension(ActivityExecution execution, Element location) {
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(execution);
		activityExecutionStatus.handleSuspension(location);
	}

	/*
	 * Decision nodes
	 */
		
	private pointcut decisionNodeTakesDecisionInputFlow(ActivityEdgeInstance edgeInstance) : call (TokenList ActivityEdgeInstance.takeOfferedTokens()) && target(edgeInstance) && withincode(Value DecisionNodeActivation.getDecisionInputFlowValue());
	
	/**
	 * Ensures that a decision node only consumes one offered decision input flow value
	 * 
	 * @param edgeInstance
	 */
	TokenList around(ActivityEdgeInstance edgeInstance) : decisionNodeTakesDecisionInputFlow(edgeInstance) {
		TokenList tokens = new TokenList();
		if(edgeInstance.offers.size() > 0) {
			TokenList offeredTokens = edgeInstance.offers.getValue(0).getOfferedTokens();
			tokens.addAll(offeredTokens);
			edgeInstance.offers.removeValue(0);
		}
		return tokens;
	}
	
	/* 
	 * ExpansionRegion
	 */
	
	/**
	 * Call of ActionActivation.sendOffers() by ActionActivation.fire(TokenList).
	 * 
	 * @param activation
	 */
	private pointcut expansionRegionSendsOffers(ExpansionRegionActivation activation) : call (void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList));

	/**
	 * Prevents the method ExpansionRegionActivation.fire() from sending offers.
	 */
	void around(ExpansionRegionActivation activation) : expansionRegionSendsOffers(activation) {
		return;
	}

	/**
	 * Call of ExpansionRegionActivation.runGroup(ExpansionActivationGroup).
	 * 
	 * @param expansionActivationGroup
	 */
	private pointcut expansionActivationGroupRunGroup(ExpansionActivationGroup expansionActivationGroup) : call (void ExpansionRegionActivation.runGroup(ExpansionActivationGroup)) && args(expansionActivationGroup) && withincode(void ExpansionRegionActivation.doStructuredActivity());

	/**
	 * Ensures that ExpansionRegionActivation.runGroup(ExpansionActivationGroup)
	 * is only called for the first ExpansionActiviationGroup.
	 * 
	 * @param expansionActivationGroup
	 */
	void around(ExpansionActivationGroup expansionActivationGroup) : expansionActivationGroupRunGroup(expansionActivationGroup) {		
		for (OutputPinActivation groupOutput : expansionActivationGroup.groupOutputs) {
			// set running = true for inserted anonymous output pins
			groupOutput.run();
		}
		if (expansionActivationGroup.index == 1) {
			// only start execution of first expansion activation group
			proceed(expansionActivationGroup);
		}
		return;
	}	
	
	/**
	 * Call of ActivityNodeActivationGroup.terminateAll() from within
	 * ExpansionRegionActivation.runGroup(ExpansionActivationGroup).
	 * 
	 * @param activationGroup
	 */
	private pointcut debugActivityNodeActivationGroupTerminateAll(ActivityNodeActivationGroup activationGroup) : call (void ActivityNodeActivationGroup.terminateAll()) && withincode(void ExpansionRegionActivation.runGroup(ExpansionActivationGroup)) && target(activationGroup);

	/**
	 * Prevents the execution of the method
	 * ActivityNodeActivationGroup.terminateAll() from being executed if it is
	 * called by ExpansionRegionActivation.runGroup(ExpansionActivationGroup).
	 * 
	 * @param activationGroup
	 *            ActivityNodeActivationGroup for which terminateAll() is called
	 */
	void around(ActivityNodeActivationGroup activationGroup) : debugActivityNodeActivationGroupTerminateAll(activationGroup) {
		return;
	}
	
	/*
	 * Structured activity nodes
	 */
	
	private pointcut structuredActivityNodeSendsOffers(StructuredActivityNodeActivation activation) : call (void ActionActivation.sendOffers()) && target(activation) && withincode(void ActionActivation.fire(TokenList));

	/**
	 * Prevents the method void ActionActivation.sendOffers() from sending offers for structured activity node
	 */
	void around(StructuredActivityNodeActivation activation) : structuredActivityNodeSendsOffers(activation) { 
		// this is necessary because the doAction operation of the structured activity node is interrupted 
		// because it consists of the execution of its contained nodes
		return; 
	}			

	/*
	 * Conditional nodes
	 */
		
	private pointcut conditionalNodeTerminatesAll() : call (void ActivityNodeActivationGroup.terminateAll()) && withincode(void ConditionalNodeActivation.doStructuredActivity());
	
	/**
	 * Prevents the method ConditionalNodeActivation.doStructuredActivity() from terminating all contained nodes.
	 */
	void around() : conditionalNodeTerminatesAll() { 		
		return; 
	}
	
	private pointcut clauseActivationAddedToConditionalNode(ClauseActivation clauseactivation) : call(void ClauseActivationList.addValue(ClauseActivation)) && withincode(void ConditionalNodeActivation.doStructuredActivity()) && args(clauseactivation);
	
	before(ClauseActivation clauseActivation) : clauseActivationAddedToConditionalNode(clauseActivation) {
		ConditionalNodeExecutionStatus conditionalNodeExecutionStatus = getConditionalNodeExecutionStatus(clauseActivation.conditionalNodeActivation); 
		conditionalNodeExecutionStatus.addClauseActivation(clauseActivation);
	}
	
	private pointcut conditionalNodeClauseStartsRunningTest(ClauseActivation clauseactivation) : call(void ClauseActivation.runTest()) && target(clauseactivation);
	
	before(ClauseActivation clauseActivation) : conditionalNodeClauseStartsRunningTest(clauseActivation) {
		ConditionalNodeExecutionStatus conditionalNodeExecutionStatus = getConditionalNodeExecutionStatus(clauseActivation.conditionalNodeActivation);
		conditionalNodeExecutionStatus.clauseStartsTest(clauseActivation);
	}	

	/*
	 * Loop nodes
	 */
	
	private pointcut loopNodeTerminatesAll() : call (void ActivityNodeActivationGroup.terminateAll()) && withincode(void LoopNodeActivation.doStructuredActivity());
	
	/**
	 * Prevents the method LoopNodeActivation.doStructuredActivity() from terminating all contained nodes.
	 */
	void around() : loopNodeTerminatesAll() { 		
		return; 
	}

	private pointcut loopNodeStartsTestFirst(LoopNodeActivation activation) : call(boolean LoopNodeActivation.runTest()) && withincode(void LoopNodeActivation.doStructuredActivity()) && target(activation);
	
	before(LoopNodeActivation activation) : loopNodeStartsTestFirst(activation) {
		LoopNodeExecutionStatus loopNodeExecutionStatus = getLoopNodeExecutionStatus(activation);
		loopNodeExecutionStatus.loopNodeStartsTest();
	}
	
	private pointcut loopNodeStartsBodyFirst(LoopNodeActivation activation) : call(void LoopNodeActivation.runBody()) && withincode(void LoopNodeActivation.doStructuredActivity()) && target(activation);
	
	before(LoopNodeActivation activation) : loopNodeStartsBodyFirst(activation) {
		LoopNodeExecutionStatus loopNodeExecutionStatus = getLoopNodeExecutionStatus(activation);
		loopNodeExecutionStatus.loopNodeStartsBody();
	}
	
	private ActivityNodeExecutionStatus getActivityNodeExecutionStatus(ActivityNodeActivation activityNodeActivation) {
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(activityNodeActivation.getActivityExecution());
		ActivityNodeExecutionStatus activityNodeExecutionStatus = activityExecutionStatus.getActivityNodeExecutionStatus(activityNodeActivation.node);
		return activityNodeExecutionStatus;
	}
	
	private ConditionalNodeExecutionStatus getConditionalNodeExecutionStatus(ConditionalNodeActivation conditionalNodeActivation) {
		ConditionalNodeExecutionStatus conditionalNodeExecutionStatus = (ConditionalNodeExecutionStatus)getActivityNodeExecutionStatus(conditionalNodeActivation);
		return conditionalNodeExecutionStatus;
	}
	
	private LoopNodeExecutionStatus getLoopNodeExecutionStatus(LoopNodeActivation conditionalNodeActivation) {
		LoopNodeExecutionStatus loopNodeExecutionStatus = (LoopNodeExecutionStatus)getActivityNodeExecutionStatus(conditionalNodeActivation);
		return loopNodeExecutionStatus;
	}
}