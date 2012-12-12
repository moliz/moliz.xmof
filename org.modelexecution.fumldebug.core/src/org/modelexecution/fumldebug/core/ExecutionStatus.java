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

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;

import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionStatus {

	private List<ActivityNode> enabledNodes = new ArrayList<ActivityNode>();		
	private HashMap<ActivityNode, ActivityNodeActivation> enabledActivations = new HashMap<ActivityNode, ActivityNodeActivation>();
	private HashMap<ActivityNodeActivation, TokenList> enabledActivationTokens = new HashMap<ActivityNodeActivation, TokenList>();
	
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
		return enabledNodes;
	}
	
	/**
	 * Removes the activation of the given activity node
	 * @param node
	 * @return
	 */
	public ActivityNodeActivation removeActivation(ActivityNode node) {
		ActivityNodeActivation activation = enabledActivations.remove(node);
		return activation;
	}
	
	/**
	 * Removes the token list of the provided activity node activation
	 * @param activation
	 * @return
	 */
	public TokenList removeTokens(ActivityNodeActivation activation) {
		return enabledActivationTokens.remove(activation);
	}
	
	/** 
	 * @return true if the execution has enabled nodes
	 */
	public boolean hasEnabledNodes() {
		if(enabledNodes.size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	protected HashMap<ActivityNode, ActivityNodeActivation> getEnalbedActivations() {
		return enabledActivations;
	}
	
	protected HashMap<ActivityNodeActivation, TokenList> getEnabledActivationTokens() {
		return enabledActivationTokens;
	}
	
	/** 
	 * @return the activation of the given activity node
	 */
	public ActivityNodeActivation getEnalbedActivations(ActivityNode node) {
		return enabledActivations.get(node);
	}
	
	/** 
	 * @return the tokens for the given activation
	 */
	public TokenList getEnabledActivationTokens(ActivityNodeActivation activation) {
		return enabledActivationTokens.get(activation);
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
		return enabledNodesSinceLastStep;
	}
		
	/**
	 * 
	 * @return returns the 
	 */
	public TokenInstance getTokenInstance(Token token) {
		return tokenInstances.get(token);
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
	
	public void addTokenCopie(Token original, Token copy) {
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
	
	public List<Token> getCopiedToken(Token original) {
		return tokenCopies.get(original);
	}
	
	public List<ActivityEdge> getTraversedActivityEdges(Token token) {
		return edgeTraversal.get(token);
	}
	
}
