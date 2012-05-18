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
	
	//protected HashMap<ActivityExecution, List<ActivityNode>> enabledNodes = new HashMap<ActivityExecution, List<ActivityNode>>();		
	//protected HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeActivation>> enabledActivations = new HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeActivation>>();
	//protected HashMap<ActivityNodeActivation, TokenList> enabledActivationTokens = new HashMap<ActivityNodeActivation, TokenList>();
	
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
	
	public boolean hasEnabledNodes() {
		if(enabledNodes.size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public HashMap<ActivityNode, ActivityNodeActivation> getEnalbedActivations() {
		return enabledActivations;
	}
	
	public HashMap<ActivityNodeActivation, TokenList> getEnabledActivationTokens() {
		return enabledActivationTokens;
	}
}
