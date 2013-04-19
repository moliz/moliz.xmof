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

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ForkedToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ActivityExecutionStatus {
	//TODO introduce called Exeuctionstatusses instead of hierarchy??
	private ActivityExecutionStatus directCallerExecutionStatus = null;
	//direct callees
	private List<ActivityExecutionStatus> directCalledExecutionStatuses = new ArrayList<ActivityExecutionStatus>();
	
	private ActivityExecution activityExecution = null;
	private int executionID = -1;
	
	private boolean inResumeMode = false;
	
	private HashMap<ActivityNode, ActivityNodeExecutionStatus> enabledActivityNodeExecutionStatuses = new HashMap<ActivityNode, ActivityNodeExecutionStatus>(); 
	private HashMap<ActivityNode, ActivityNodeExecutionStatus> executingActivityNodeExecutionStatuses = new HashMap<ActivityNode, ActivityNodeExecutionStatus>();
	
	// Data structure for saving the ActivityEntryEvent
	private ActivityEntryEvent activityentryevent = null;
	// Data structure for saving the ActivityNodeEntryEvents for the ActivityNodes
	private HashMap<ActivityNode, ActivityNodeEntryEvent> activitynodeentryevents = new HashMap<ActivityNode, ActivityNodeEntryEvent>(); //TODO should be held by eventhandler (class has to be newly introduced)
	// Data structure for saving which ActivityNodeActivation started the execution of the ActivityExecution 
	private ActivityNodeActivation activitycall = null; 
	// Data structure for saving the enabledNodesBetweenSteps
	private List<ActivityNode> enabledNodesSinceLastStep = new ArrayList<ActivityNode>();
	
// TODO put token stuff into own class?
	// Data structure for saving the Tokens flowing through an ActivityExecution
	private HashMap<Token, TokenInstance> tokenInstances = new HashMap<Token, TokenInstance>();
	// Data structure for saving tokens sent by ActivityNodes 
	private HashMap<ActivityNodeActivation, List<Token>> tokensending = new HashMap<ActivityNodeActivation, List<Token>>();
	// Data structure for saving token copies that are created during transferring a tokens from a source to the target
	private HashMap<Token, List<Token>> tokenCopies = new HashMap<Token, List<Token>>();
	private HashMap<Token, Token> tokenOriginals = new HashMap<Token, Token>();
	// Data structure for saving over which edge a token was sent
	private HashMap<Token, List<ActivityEdge>> edgeTraversal = new HashMap<Token, List<ActivityEdge>>();
	
	public ActivityExecutionStatus(ActivityExecution activityExecution, int executionID) {
		this.activityExecution = activityExecution;
		this.executionID = executionID;
	}
	
	public ActivityExecution getActivityExecution() {
		return activityExecution;
	}
	
	public int getExecutionID() {
		return executionID;
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
	
	public ActivityNodeExecutionStatus getEnabledActivityNodeExecutionStatus(ActivityNode node) {
		return enabledActivityNodeExecutionStatuses.get(node);
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
	public boolean hasEnabledNodes() {
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
		}
		status.addWaitingTokens(tokens);		
		if(!enabledNodesSinceLastStep.contains(activation.node)) {
			enabledNodesSinceLastStep.add(activation.node);
		}
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
	
	
	private ActivityNodeExecutionStatus createActivityNodeExecutionStatus(ActivityNodeActivation activation) {
		int activationIndex = getNextNodeActivationIndex();
		ActivityNodeExecutionStatus status = null;
		if(activation instanceof ConditionalNodeActivation) {
			status = new ConditionalNodeExecutionStatus(this, activation, activationIndex);
		} else if(activation instanceof LoopNodeActivation) {
			status = new LoopNodeExecutionStatus(this, activation, activationIndex);
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
	
	/** 
	 * @return the activation of the given activity node
	 */	
	//TODO delegating operations: remove operations and call directly status class operations
	public ActivityNodeActivation getEnabledActivation(ActivityNode node) { //TODO refactor?		
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(node);
		ActivityNodeActivation activation = status.getActivityNodeActivation();
		return activation;
	}
	
	/** 
	 * @return the tokens for the given activation
	 */
	public List<TokenList> getEnabledActivationTokens(ActivityNodeActivation activation) {
		ActivityNodeExecutionStatus status = enabledActivityNodeExecutionStatuses.get(activation.node);
		List<TokenList> tokens = status.getWaitingTokens();
		return tokens;
	}
	
	/**
	 * @return the activityentryevents
	 */
	public ActivityEntryEvent getActivityEntryEvent() {
		return activityentryevent;
	}

	/**
	 * @param activityentryevent the activityentryevents to set
	 */
	public void setActivityEntryEvent(ActivityEntryEvent activityentryevent) {
		this.activityentryevent = activityentryevent;
	}

	/**
	 * @return the activitynodeentryevents
	 */
	public ActivityNodeEntryEvent getActivityNodeEntryEvent(ActivityNode node) {
		return activitynodeentryevents.get(node);
	}

	/**
	 * @param activitynodeentryevents the activitynodeentryevents to set
	 */	
	public void setActivityNodeEntryEvent(ActivityNode node, ActivityNodeEntryEvent entryevent) {
		this.activitynodeentryevents.put(node, entryevent);
	}

	/**
	 * @return the activitycalls
	 */
	public ActivityNodeActivation getActivityCall() {
		return activitycall;
	}
	
	/**
	 * @param activitycalls the activitycalls to set
	 */
	public void setActivityCalls(ActivityNodeActivation activitycalls) {
		this.activitycall = activitycalls;
	}

	/**
	 * @return the enabledNodesSinceLastStep
	 */
	public List<ActivityNode> getEnabledNodesSinceLastStep() {
		return new ArrayList<ActivityNode>(enabledNodesSinceLastStep);
	}
	
	public void clearEnabledNodesSinceLastStep() {
		enabledNodesSinceLastStep.clear();
	}
	
	public TokenInstance getTokenInstance(Token token) {
		TokenInstance tokenInstance = tokenInstances.get(token);
		if(token instanceof ForkedToken && tokenInstance == null) {
			// The input token is provided by an anonymous fork node
			Token baseToken = ((ForkedToken) token).baseToken;
			tokenInstance = tokenInstances.get(baseToken);							
		}
		return tokenInstance;
	}
	
	/**
	 * Adds a tokenInstance for a token
	 * @param token
	 * @param tokenInstance
	 */
	public void addTokenInstance(Token token, TokenInstance tokenInstance) {
		tokenInstances.put(token, tokenInstance);
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
		
		tokenOriginals.put(copy, original);
	}
	
	public Token getOriginalToken(Token copy) {
		return tokenOriginals.get(copy);
	}	
	
	public List<ActivityEdge> getTraversedActivityEdges(Token token) {
		return new ArrayList<ActivityEdge>(edgeTraversal.get(token));
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
						if(ExecutionContext.getInstance().hasEnabledNodesIncludingCallees(callExecution.hashCode())) {
							//TODO refactor!! do not call ExecutionContext!!
							return true;
						}
					}
				}
			}
		}
		return false;
	}
				
}
