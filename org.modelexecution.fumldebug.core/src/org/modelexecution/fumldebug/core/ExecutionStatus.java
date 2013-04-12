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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ClauseActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ForkedToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.Clause;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionStatus {
	//TODO introduce called Exeuctionstatusses??
	
	private EnabledNodeHandler enabledNodeHandler = new EnabledNodeHandler();
	private ActivityExecution activityExecution = null;
	
	// Data structure for saving the ActivityEntryEvent
	private ActivityEntryEvent activityentryevent = null;
	// Data structure for saving the ActivityNodeEntryEvents for the ActivityNodes
	private HashMap<ActivityNode, ActivityNodeEntryEvent> activitynodeentryevents = new HashMap<ActivityNode, ActivityNodeEntryEvent>();
	// Data structure for saving which ActivityNodeActivation started the execution of the ActivityExecution 
	private ActivityNodeActivation activitycall = null; 
	// Data structure for saving the enabledNodesBetweenSteps
	private List<ActivityNode> enabledNodesSinceLastStep = new ArrayList<ActivityNode>();
	// Data structure for saving the Tokens flowing through an ActivityExecution
	private HashMap<Token, TokenInstance> tokenInstances = new HashMap<Token, TokenInstance>();
	// Data structure for saving tokens sent by ActivityNodes 
	private HashMap<ActivityNodeActivation, List<Token>> tokensending = new HashMap<ActivityNodeActivation, List<Token>>();
	// Data structure for saving token copies that are created during transferring a tokens from a source to the target
	private HashMap<Token, List<Token>> tokenCopies = new HashMap<Token, List<Token>>();
	private HashMap<Token, Token> tokenOriginals = new HashMap<Token, Token>();
	// Data structure for saving over which edge a token was sent
	private HashMap<Token, List<ActivityEdge>> edgeTraversal = new HashMap<Token, List<ActivityEdge>>();
	
	public ExecutionStatus(ActivityExecution activityExecution) {
		this.activityExecution = activityExecution;
	}

	/**
	 * Provides the enabled nodes
	 * @return
	 */
	public List<ActivityNode> getEnabledNodes() {
		return enabledNodeHandler.getEnabledNodes();
	}
	
	/**
	 * Removes the token list of the provided activity node activation
	 * @param activation
	 * @return
	 */
	public TokenList removeTokens(ActivityNodeActivation activation) {
		return enabledNodeHandler.removeTokens(activation);
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
	
	public void addEnabledActivation(ActivityNodeActivation activation, TokenList tokens) {
		if(activation != null && activation.node != null) {
			enabledNodeHandler.addEnabledNode(activation, tokens);
			if(!enabledNodesSinceLastStep.contains(activation.node)) {
				enabledNodesSinceLastStep.add(activation.node);
			}
		}
	}
	
	/** 
	 * @return the activation of the given activity node
	 */
	public ActivityNodeActivation getEnabledActivation(ActivityNode node) {
		return enabledNodeHandler.getEnabledActivation(node);
	}
	
	/** 
	 * @return the tokens for the given activation
	 */
	public List<TokenList> getEnabledActivationTokens(ActivityNodeActivation activation) {
		return enabledNodeHandler.getEnabledActivationTokens(activation);
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
						if(ExecutionContext.getInstance().hasEnabledNodesIncludingCallees((ActivityExecution)callExecution)) {
							//TODO refactor!! do not call ExecutionContext!!
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private class EnabledNodeHandler {
		private Set<ActivityNode> enabledNodes = new LinkedHashSet<ActivityNode>();		
		private HashMap<ActivityNode, ActivityNodeActivation> enabledActivations = new HashMap<ActivityNode, ActivityNodeActivation>();
		private HashMap<ActivityNodeActivation, List<TokenList>> enabledActivationTokens = new HashMap<ActivityNodeActivation, List<TokenList>>();
		
		void addEnabledNode(ActivityNodeActivation activation, TokenList tokens) {
			enabledActivations.put(activation.node, activation);
			
			List<TokenList> existingtokensets = enabledActivationTokens.get(activation);
			if(existingtokensets == null) {
				existingtokensets = new ArrayList<TokenList>();
				enabledActivationTokens.put(activation, existingtokensets);
			}
			existingtokensets.add(tokens);
			
			enabledNodes.add(activation.node);			
		}
		
		TokenList removeTokens(ActivityNodeActivation activation) {
			TokenList tokens = new TokenList();
			List<TokenList> tokensets = enabledActivationTokens.get(activation);		
			if(tokensets.size() > 0) {
				tokens.addAll(tokensets.remove(0));
			}
			if(tokensets.size() == 0) {				
				enabledActivationTokens.remove(activation);
				enabledNodes.remove(activation.node);
				enabledActivations.remove(activation.node);
			}
			return tokens;
		}
		
		List<ActivityNode> getEnabledNodes() {			
			return new ArrayList<ActivityNode>(enabledNodes);
		}
		
		ActivityNodeActivation getEnabledActivation(ActivityNode node) {			
			return enabledActivations.get(node);
		}
		
		public List<TokenList> getEnabledActivationTokens(ActivityNodeActivation activation) {
			return new ArrayList<TokenList>(enabledActivationTokens.get(activation));
		}
	}
	
	private HashMap<ConditionalNodeActivation, List<ClauseExecution>> controlNodeExecutionInfo = new HashMap<ConditionalNodeActivation, List<ClauseExecution>>();
	
	public void addConditionalNodeExecution(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseExecution> clauseexecutions = new ArrayList<ClauseExecution>();		
		controlNodeExecutionInfo.put(conditionalnodeactivation, clauseexecutions);
	}
	
	public void removeConditionalNodeExecution(ConditionalNodeActivation conditionalnodeactivation) {
		controlNodeExecutionInfo.remove(conditionalnodeactivation);
	}
	
	public void addClauseActivation(ConditionalNodeActivation conditionalnodeactivation, ClauseActivation clauseactivation) {		
		ClauseExecution existingclauseexecution = getClauseExecution(conditionalnodeactivation, clauseactivation);
		if(existingclauseexecution == null) {
			ClauseExecution clauseexecution = new ClauseExecution(clauseactivation);
			controlNodeExecutionInfo.get(conditionalnodeactivation).add(clauseexecution);
		}
	}
	
	public void clauseStartsTest(ConditionalNodeActivation conditionalnodeactivation, ClauseActivation clauseActivation) {
		ClauseExecution clauseexecution = getClauseExecution(conditionalnodeactivation, clauseActivation);
		clauseexecution.setStatus(ClauseExecutionStatus.TESTSTARTED);		
	}
	
	private ClauseExecution getClauseExecution(ConditionalNodeActivation conditionalnodeactivation, ClauseActivation clauseActivation) {
		List<ClauseExecution> clauseexecutions = controlNodeExecutionInfo.get(conditionalnodeactivation);
		for(ClauseExecution clauseexecution : clauseexecutions) {
			if(clauseexecution.getClauseActivation().equals(clauseActivation)) {
				return clauseexecution;
			}
		}
		return null;
	}

	public void updateStatusOfConditionalNode(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseExecution> clausesWithTestStarted = getClauseExecutions(conditionalnodeactivation, ClauseExecutionStatus.TESTSTARTED);
		List<ClauseExecution> clausesWithBodyStarted = getClauseExecutions(conditionalnodeactivation, ClauseExecutionStatus.BODYSTARTED);
		
		for(ClauseExecution clauseExecution : clausesWithTestStarted) {
			if(!isAnyNodeEnabled(new ArrayList<ActivityNode>(clauseExecution.getClauseActivation().clause.test))) {
				clauseExecution.setStatus(ClauseExecutionStatus.TESTFINISHED);
				if(clauseExecution.getClauseActivation().getDecision().value == true) {
					clauseExecution.setTestFulfilled();
					clauseExecution.getClauseActivation().selectBody();
				}
			}
		}

		for(ClauseExecution clauseExecution : clausesWithBodyStarted) {
			if(!isAnyNodeEnabled(new ArrayList<ActivityNode>(clauseExecution.getClauseActivation().clause.body))) {
				clauseExecution.setStatus(ClauseExecutionStatus.BODYFINISHED);
			}
		}		
	}
	
	public boolean anyClauseStartedBody(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseExecution> clausesWithFinishedBody = getClauseExecutions(conditionalnodeactivation, ClauseExecutionStatus.BODYSTARTED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;		
	}
	
	public boolean anyClauseFinishedBody(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseExecution> clausesWithFinishedBody = getClauseExecutions(conditionalnodeactivation, ClauseExecutionStatus.BODYFINISHED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;
	}
	
	public ClauseActivation getClauseActivationWithExecutedBody(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseExecution> clausesWithFinishedBody = getClauseExecutions(conditionalnodeactivation, ClauseExecutionStatus.BODYFINISHED);
		if(clausesWithFinishedBody.size() > 0) {
			return clausesWithFinishedBody.get(0).getClauseActivation();
		}
		return null;
	}
	
	private List<ClauseExecution> getClauseExecutions(ConditionalNodeActivation conditionalnodeactivation, ClauseExecutionStatus status) {
		List<ClauseExecution> clauseExecutions = new ArrayList<ClauseExecution>();
		for(ClauseActivation clauseActivation : conditionalnodeactivation.clauseActivations) {
			ClauseExecution clauseExecution = getClauseExecution(conditionalnodeactivation, clauseActivation);
			if(clauseExecution.getStatus() == status) {
				clauseExecutions.add(clauseExecution);
			}
		}
		return clauseExecutions;
	}
	
	/*
	public boolean areAllClauseTestsFinished(ConditionalNodeActivation conditionalnodeactivation) {
		int finishedClauseTests = 0;
		int notStartedClauseTests = 0;
		for(ClauseActivation clauseActivation : conditionalnodeactivation.clauseActivations) {
			ClauseExecution clauseExecution = getClauseExecution(conditionalnodeactivation, clauseActivation);
			if(clauseExecution.getStatus() == ClauseExecutionStatus.TESTFINISHED || clauseExecution.getStatus() == ClauseExecutionStatus.BODYSTARTED || clauseExecution.getStatus() == ClauseExecutionStatus.BODYFINISHED) {
				++finishedClauseTests;
			} else if(clauseExecution.getStatus() == ClauseExecutionStatus.INITIALIZED) {
				++notStartedClauseTests;
			}
		}
		if((finishedClauseTests + notStartedClauseTests) == conditionalnodeactivation.clauseActivations.size()) {
			return true;
		} else {
			return false;
		}
	}
	*/
	
	public boolean areAllClauseTestsFinished(ConditionalNodeActivation conditionalnodeactivation) {
		int startedClausTests = 0;
		for(ClauseActivation clauseActivation : conditionalnodeactivation.clauseActivations) {
			ClauseExecution clauseExecution = getClauseExecution(conditionalnodeactivation, clauseActivation);
			if(clauseExecution.getStatus() == ClauseExecutionStatus.TESTSTARTED) {
				++startedClausTests;
			} 
		}
		if(startedClausTests == 0) {
			return true;
		} 
		return false;
	}
	
	public List<ClauseActivation> getSuccessorClausesToBeEvaluated(ConditionalNodeActivation conditionalnodeactivation) {
		List<ClauseActivation> successorClauses = new ArrayList<ClauseActivation>();
		for(ClauseActivation clauseActivation : conditionalnodeactivation.clauseActivations) {
			ClauseExecution clauseExecution = getClauseExecution(conditionalnodeactivation, clauseActivation);
			if(clauseExecution.getStatus() == ClauseExecutionStatus.TESTFINISHED && !clauseExecution.isTestFulfilled()) {
				for(ClauseActivation successor : clauseActivation.getSuccessors()) {
					ClauseExecution successorClauseExecution = getClauseExecution(conditionalnodeactivation, successor);
					if(successorClauseExecution.status == ClauseExecutionStatus.INITIALIZED) {
						if(successor.isReady()) {
							successorClauses.add(successor);
						}
					}
				}
			}
		}
		return successorClauses;
	}
	
	public void setClauseSelectedForExecutingBody(ConditionalNodeActivation conditionalnodeactivation, Clause selectedClause) {
		List<ClauseExecution> clauseexecutions = controlNodeExecutionInfo.get(conditionalnodeactivation);
		for(ClauseExecution clauseexecution : clauseexecutions) {
			if(clauseexecution.getClauseActivation().clause == selectedClause) {
				clauseexecution.setStatus(ClauseExecutionStatus.BODYSTARTED);
			}
		}
	}
	
	private class ClauseExecution {
		private ClauseActivation clauseactivation = null;
		private ClauseExecutionStatus status = ClauseExecutionStatus.INITIALIZED;
		private boolean testFulfilled = false;
		
		private ClauseExecution(ClauseActivation clauseactivation) {
			this.clauseactivation = clauseactivation;
		}
		
		private void setTestFulfilled() {
			testFulfilled = true;
		}		
		
		private boolean isTestFulfilled() {
			return testFulfilled;
		}
		
		private void setStatus(ClauseExecutionStatus status) {
			this.status = status;
		}
		
		private ClauseActivation getClauseActivation() {
			return clauseactivation;
		}
		
		private ClauseExecutionStatus getStatus() {
			return status;
		}
	}
	
	private enum ClauseExecutionStatus {INITIALIZED, TESTSTARTED, TESTFINISHED, BODYSTARTED, BODYFINISHED};
}
