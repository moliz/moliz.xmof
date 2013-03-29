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

import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ForkedToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionStatus {
	private EnabledNodeHandler enabledNodeHandler = new EnabledNodeHandler();
	
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
	
	public ExecutionStatus() {

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
}
