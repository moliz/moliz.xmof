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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Actions.BasicActions.CallBehaviorActionActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.StructuredActivityNodeActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivationList;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ActivityExecutionStatus {

	private ActivityExecutionStatus directCallerExecutionStatus = null;

	private List<ActivityExecutionStatus> directCalledExecutionStatuses = new ArrayList<ActivityExecutionStatus>();
	
	private ActivityExecution activityExecution = null;
	private Activity activity = null;
	private int executionID = -1;
	
	private boolean inResumeMode = false;
	
	private HashMap<ActivityNode, ActivityNodeExecutionStatus> enabledActivityNodeExecutionStatuses = new HashMap<ActivityNode, ActivityNodeExecutionStatus>(); 
	private HashMap<ActivityNode, ActivityNodeExecutionStatus> executingActivityNodeExecutionStatuses = new HashMap<ActivityNode, ActivityNodeExecutionStatus>();
	private HashMap<ActivityNode, ActivityNodeExecutionStatus> allActivityNodeExecutionStatuses = new HashMap<ActivityNode, ActivityNodeExecutionStatus>();
	
	private CallActionExecutionStatus callerNodeExecutionStatus = null;	
	
	// Data structure for saving the enabledNodesBetweenSteps
	private List<ActivityNode> enabledNodesSinceLastStep = new ArrayList<ActivityNode>();
		
	// Data structure for saving tokens sent by ActivityNodes 
	private HashMap<ActivityNodeActivation, List<Token>> tokensending = new HashMap<ActivityNodeActivation, List<Token>>();
	// Data structure for saving token copies that are created during transferring a tokens from a source to the target
	private HashMap<Token, List<Token>> tokenCopies = new HashMap<Token, List<Token>>();
	private HashMap<Token, Token> tokenOriginals = new HashMap<Token, Token>();
	// Data structure for saving over which edge a token was sent
	private HashMap<Token, List<ActivityEdge>> edgeTraversal = new HashMap<Token, List<ActivityEdge>>();
	
	public ActivityExecutionStatus(ActivityExecution activityExecution, int executionID) {
		this.activityExecution = activityExecution;
		this.activity = (Activity)activityExecution.getBehavior();
		this.executionID = executionID;
	}
	
	public ActivityExecution getActivityExecution() {
		return activityExecution;
	}
	
	public int getExecutionID() {
		return executionID;
	}

	public Activity getActivity() {
		return activity;
	}
	
	public boolean isInResumeMode() {
		return inResumeMode;
	}

	public void setInResumeMode(boolean inResumeMode) {
		this.inResumeMode = inResumeMode;		
	}
	
	public void setWholeExecutionInResumeMode(boolean isResumeMode) {
		ActivityExecutionStatus rootActivityExecutionStatus = this.getRootCallerExecutionStatus();
		rootActivityExecutionStatus.setInResumeMode(isResumeMode);
		List<ActivityExecutionStatus> allCalleeExecutionStatuses = rootActivityExecutionStatus.getAllCalleeExecutionStatuses();
		for(ActivityExecutionStatus calleeExecutionStatus : allCalleeExecutionStatuses) {
			calleeExecutionStatus.setInResumeMode(isResumeMode);
		}
	}

	public ActivityExecutionStatus getDirectCallerExecutionStatus() {
		return directCallerExecutionStatus;
	}

	public void setDirectCallerExecutionStatus(ActivityExecutionStatus callerExecutionStatus) {
		this.directCallerExecutionStatus = callerExecutionStatus;
	}	

	public List<ActivityExecutionStatus> getDirectCalledExecutionStatuses() {
		return new ArrayList<ActivityExecutionStatus>(directCalledExecutionStatuses);
	}
	
	public void addDirectCalledExecutionStatus(ActivityExecutionStatus activityExecutionStatus) {
		directCalledExecutionStatuses.add(activityExecutionStatus);
	}
	
	public ActivityExecutionStatus getRootCallerExecutionStatus() {
		if(directCallerExecutionStatus != null) {
			return directCallerExecutionStatus.getRootCallerExecutionStatus();
		} else {
			return this;
		}
	}
	
	public List<ActivityExecutionStatus> getAllCalleeExecutionStatuses() {
		List<ActivityExecutionStatus> calleeExecutionStatuses = new ArrayList<ActivityExecutionStatus>();
		calleeExecutionStatuses.addAll(directCalledExecutionStatuses);
		for(ActivityExecutionStatus calledExecutionStatus : directCalledExecutionStatuses) {
			calleeExecutionStatuses.addAll(calledExecutionStatus.getAllCalleeExecutionStatuses());
		}
		return calleeExecutionStatuses;
	}

	/**
	 * Provides the enabled nodes
	 * @return
	 */
	public List<ActivityNode> getEnabledNodes() {
		List<ActivityNodeExecutionStatus> enabledNodeStatuses = new ArrayList<ActivityNodeExecutionStatus>(enabledActivityNodeExecutionStatuses.values());
		Collections.sort(enabledNodeStatuses);
		List<ActivityNode> enabledNodes = new ArrayList<ActivityNode>();
		for(ActivityNodeExecutionStatus enabledNodeStatus : enabledNodeStatuses) {
			enabledNodes.add(enabledNodeStatus.getActivityNodeActivation().node);
		}
		return enabledNodes;
	}
	
	public ActivityNodeActivation getExecutingActivation(ActivityNode node) { 		
		ActivityNodeExecutionStatus status = getExecutingActivityNodeExecutionStatus(node);
		ActivityNodeActivation activation = status.getActivityNodeActivation();
		return activation;
	}
	
	public ActivityNodeExecutionStatus getExecutingActivityNodeExecutionStatus(ActivityNode node) {
		return executingActivityNodeExecutionStatuses.get(node);
	}
	
	/**
	 * Returns the tokens provided to the node in the activity execution
	 * @param activation
	 * @return
	 */
	public TokenList getTokens(ActivityNode node) {
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(node);
		TokenList tokens = status.removeWaitingTokens();
		if(status.getWaitingTokens().size() == 0) {
			enabledActivityNodeExecutionStatuses.remove(node);
		}
		return tokens;
	}
	
	/** 
	 * @return true if the execution has enabled nodes
	 */
	private boolean hasEnabledNodes() {
		List<ActivityNode> enabledNodes = this.getEnabledNodes();
		if(enabledNodes.size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasEnabledNodesIncludingCallees() {
		boolean hasEnabledNodes = this.hasEnabledNodes();
		
		if(hasEnabledNodes) {
			return true;
		}
		
		List<ActivityExecutionStatus> directCalledExecutionStatuses = this.getDirectCalledExecutionStatuses();		
		for(ActivityExecutionStatus directCalledExecutionStatus : directCalledExecutionStatuses) {
			boolean calleeHasEnabledNodes = directCalledExecutionStatus.hasEnabledNodesIncludingCallees();
			if(calleeHasEnabledNodes) {
				return true;
			}
		}		
		return false;
	}
	
	public void addEnabledActivation(ActivityNodeActivation activation, TokenList tokens) {	
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(activation.node);
		if(status == null) {			
			status = createActivityNodeExecutionStatus(activation);
			enabledActivityNodeExecutionStatuses.put(activation.node, status);
			allActivityNodeExecutionStatuses.put(activation.node, status);
		}
		status.addWaitingTokens(tokens);		
		if(!enabledNodesSinceLastStep.contains(activation.node)) {
			enabledNodesSinceLastStep.add(activation.node);
		}
	}
	
	public ActivityNodeExecutionStatus getActivityNodeExecutionStatus(ActivityNode node) {
		return allActivityNodeExecutionStatuses.get(node);
	}
	
	public void addExecutingActivation(ActivityNode node) {	
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(node);
		if(status != null) {
			executingActivityNodeExecutionStatuses.put(node, status);
		}
	}
	
	public void removeExecutingActivation(ActivityNode node) {
		executingActivityNodeExecutionStatuses.remove(node);
	}
	
	public void removeEnabledActivation(ActivityNode... node) {
		for(ActivityNode n : node)
			enabledActivityNodeExecutionStatuses.remove(n);
	}
	
	private ActivityNodeExecutionStatus createActivityNodeExecutionStatus(ActivityNodeActivation activation) {
		int activationIndex = getNextNodeActivationIndex();
		ActivityNodeExecutionStatus status = null;
		if(activation instanceof ConditionalNodeActivation) {
			status = new ConditionalNodeExecutionStatus(this, (ConditionalNodeActivation)activation, activationIndex);
		} else if(activation instanceof LoopNodeActivation) {
			status = new LoopNodeExecutionStatus(this, (LoopNodeActivation)activation, activationIndex);
		} else if(activation instanceof StructuredActivityNodeActivation) {
			status = new StructuredActivityNodeExecutionStatus(this, (StructuredActivityNodeActivation)activation, activationIndex);
		} else if(activation instanceof CallActionActivation && !(activation instanceof CallBehaviorActionActivation && ((CallBehaviorAction)activation.node).behavior instanceof OpaqueBehavior)) {
			status = new CallActionExecutionStatus(this, (CallActionActivation)activation, activationIndex);
		} else if(activation instanceof ExpansionRegionActivation) {
			status = new ExpansionRegionExecutionStatus(this, (ExpansionRegionActivation)activation, activationIndex);
		} else {
			status = new ActivityNodeExecutionStatus(this, activation, activationIndex);
		}
		return status;
	}
	
	private int getNextNodeActivationIndex() {
		int index = -1;
		for(ActivityNodeExecutionStatus status : enabledActivityNodeExecutionStatuses.values()) {
			int i = status.getIndex();
			if(index < i) {
				index = i;
			}
		}
		return ++index;
	}
	
	public ActivityNodeActivation getEnabledActivation(ActivityNode node) { 		
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(node);
		ActivityNodeActivation activation = status.getActivityNodeActivation();
		return activation;
	}
		
	public List<TokenList> getEnabledActivationTokens(ActivityNodeActivation activation) {
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(activation.node);
		List<TokenList> tokens = status.getWaitingTokens();
		return tokens;
	}
		
	public ActivityNodeExecutionStatus getActivityCallerNoderExecutionStatus() {
		return callerNodeExecutionStatus;
	}
	
	public void setActivityCallerNode(CallActionActivation callerNodeActivation) {
		callerNodeExecutionStatus = (CallActionExecutionStatus)directCallerExecutionStatus.getExecutingActivityNodeExecutionStatus(callerNodeActivation.node);
		callerNodeExecutionStatus.setCalledActivityExecutionStatus(this);
	}

	private List<ActivityNode> getEnabledNodesSinceLastStep() {
		return new ArrayList<ActivityNode>(enabledNodesSinceLastStep);
	}
	
	private void clearEnabledNodesSinceLastStep() {
		enabledNodesSinceLastStep.clear();
	}
	
	public List<Token> removeTokenSending(ActivityNodeActivation node) {
		return tokensending.remove(node);
	}

	public void addTokenSending(ActivityNodeActivation node, List<Token> tokens, ActivityEdge edge) {
		List<Token> existingTokenSending = null;
		if(tokensending.containsKey(node)) {
			existingTokenSending = tokensending.get(node);
		} else {
			existingTokenSending = new ArrayList<Token>();
			tokensending.put(node, existingTokenSending);
		}
		existingTokenSending.addAll(tokens);
		
		for(Token token : tokens) {
			List<ActivityEdge> traversedEdge = edgeTraversal.get(token);
			if(traversedEdge == null) {
				traversedEdge = new ArrayList<ActivityEdge>();
				edgeTraversal.put(token, traversedEdge);
			}
			traversedEdge.add(edge);
		}
	}
	
	public void addTokenCopy(Token original, Token copy) {
		List<Token> tokenlist = tokenCopies.get(original);
		if(tokenlist == null) {
			tokenlist = new ArrayList<Token>();
			tokenCopies.put(original, tokenlist);
		}
		tokenlist.add(copy);
		
		if(!tokenOriginals.containsKey(copy)) {
			tokenOriginals.put(copy, original);
		}
	}
	
	public Token getOriginalToken(Token copy) {		
		return tokenOriginals.get(copy);
	}	
	
	public List<ActivityEdge> getTraversedActivityEdges(Token token) {
		List<ActivityEdge> traversedEdges = edgeTraversal.get(token);
		if(traversedEdges == null) {
			return new ArrayList<ActivityEdge>();
		} else {
			return new ArrayList<ActivityEdge>(traversedEdges);
		}
	}

	public boolean isNodeEnabled(ActivityNode activityNode) {
		if(activityNode == null) {
			return false;
		}
		List<ActivityNode> enabledNodes = this.getEnabledNodes();
		boolean nodeEnabled = enabledNodes.contains(activityNode);
		return nodeEnabled;
	}
	
	public boolean isAnyNodeEnabled(List<ActivityNode> nodes) {
		List<ActivityNode> nodes_ = new ArrayList<ActivityNode>(nodes);
		List<ActivityNode> enabledNodes = new ArrayList<ActivityNode>(this.getEnabledNodes());
		if(nodes_.removeAll(enabledNodes)) {
			return true;
		}
		
		for(ActivityNode node : nodes) {
			if(node instanceof CallAction) {
				CallActionActivation callActionActivation = (CallActionActivation)activityExecution.activationGroup.getNodeActivation(node);
				for(Execution callExecution : callActionActivation.callExecutions) {
					if(callExecution instanceof ActivityExecution) {
						ActivityExecutionStatus callExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus((ActivityExecution)callExecution);
						if(callExecutionStatus.hasEnabledNodesIncludingCallees()){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
				
	public void handleEndOfExecution() { 
		obtainActivityOutput();
		
		ActivityNodeExecutionStatus callerNodeExecutionStatus = getActivityCallerNoderExecutionStatus();
		if (callerNodeExecutionStatus instanceof CallActionExecutionStatus) {
			CallActionExecutionStatus callerActionExecutionStatus = (CallActionExecutionStatus)callerNodeExecutionStatus;
			callerActionExecutionStatus.handleEndOfExecution();									
		} else {
			// ActivityExecution was triggered by user
			ParameterValueList outputValues = activityExecution.getOutputParameterValues();
			ExecutionContext.getInstance().setActivityExecutionOutput(executionID, outputValues);
			activityExecution.destroy();
			ExecutionContext.getInstance().eventHandler.handleActivityExit(activityExecution);

			ExecutionContext.getInstance().executionStatus.removeActivityExecution(executionID);
		}
	}
	
	public void destroyActivityExecution() {
		activityExecution.destroy();
	}

	private void obtainActivityOutput() {
		// START code from void ActivityExecution.execute()
		ActivityParameterNodeActivationList outputActivations = activityExecution.activationGroup.getOutputParameterNodeActivations();
		for (ActivityParameterNodeActivation outputActivation : outputActivations) {
			ParameterValue parameterValue = new ParameterValue();
			parameterValue.parameter = ((ActivityParameterNode) (outputActivation.node)).parameter;

			TokenList tokens = outputActivation.getTokens();
			for (Token token : tokens) {
				Value value = ((ObjectToken) token).value;
				if (value != null) {
					parameterValue.values.addValue(value);
				}
			}

			activityExecution.setParameterValue(parameterValue);
		}
		// END code from void ActivityExecution.execute()
	}
	
	public void handleSuspension(Element location) {
			List<ActivityNode> allEnabledNodes = getEnabledNodes();
			List<ActivityNode> enabledNodesSinceLastStepForExecution = getEnabledNodesSinceLastStep();
			for (int i = 0; i < enabledNodesSinceLastStepForExecution.size(); ++i) {
				if (!allEnabledNodes.contains(enabledNodesSinceLastStepForExecution.get(i))) {
					enabledNodesSinceLastStepForExecution.remove(i);
					--i;
				}
			}
			
			List<Breakpoint> hitBreakpoints = new ArrayList<Breakpoint>();
			for (ActivityNode node : enabledNodesSinceLastStepForExecution) {
				Breakpoint breakpoint = ExecutionContext.getInstance().getBreakpoint(node);
				if (breakpoint != null) {
					hitBreakpoints.add(breakpoint);
				}
			}

			if (hitBreakpoints.size() > 0) {
				setWholeExecutionInResumeMode(false);
				ExecutionContext.getInstance().eventHandler.handleBreakpointSuspension(activityExecution, location, hitBreakpoints, enabledNodesSinceLastStepForExecution);
			} else {
				ExecutionContext.getInstance().eventHandler.handleSuspension(activityExecution, location, enabledNodesSinceLastStepForExecution);
			}
			clearEnabledNodesSinceLastStep();					
	}

	public Set<ActivityNode> getExecutingNodes() {
		return this.executingActivityNodeExecutionStatuses.keySet();
	}
}
