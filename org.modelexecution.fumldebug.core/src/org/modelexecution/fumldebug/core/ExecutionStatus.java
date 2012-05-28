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

import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionStatus {

	private List<ActivityNode> enabledNodes = new ArrayList<ActivityNode>();		
	private HashMap<ActivityNode, ActivityNodeActivation> enabledActivations = new HashMap<ActivityNode, ActivityNodeActivation>();
	private HashMap<ActivityNodeActivation, TokenList> enabledActivationTokens = new HashMap<ActivityNodeActivation, TokenList>();
	
	// Data structure for saving the Activations of the initial enabled nodes 	
	private List<ActivityNodeActivation> initialEnabledNodeActivations = new ArrayList<ActivityNodeActivation>();	
	// Data structure for saving the ActivityEntryEvent
	private ActivityEntryEvent activityentryevent = null;
	// Data structure for saving the ActivityNodeEntryEvents for the ActivityNodes
	private HashMap<ActivityNode, ActivityNodeEntryEvent> activitynodeentryevents = new HashMap<ActivityNode, ActivityNodeEntryEvent>();
	// Data structure for saving which ActivityNodeActivation started the execution of the ActivityExecution 
	private ActivityNodeActivation activitycall = null; 
	// Data structure for saving the enabledNodesBetweenSteps
	private List<ActivityNode> enabledNodesSinceLastStep = new ArrayList<ActivityNode>();

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
	 * @return the initialEnabledNodeActivations
	 */
	public List<ActivityNodeActivation> getInitialEnabledNodeActivations() {
		return initialEnabledNodeActivations;
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
		
}
