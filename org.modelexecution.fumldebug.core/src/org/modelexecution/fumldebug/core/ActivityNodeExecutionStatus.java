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
import java.util.List;

import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;

public class ActivityNodeExecutionStatus implements Comparable<ActivityNodeExecutionStatus>{

	protected ActivityExecutionStatus activityExecutionStatus = null; //TODO necessary?
	private int activationIndex = -1;
	private ActivityNodeActivation activityNodeActivation = null;
	private List<TokenList> waitingTokens = new ArrayList<TokenList>();

	public ActivityNodeExecutionStatus(ActivityExecutionStatus activityExecutionStatus, ActivityNodeActivation activityNodeActivation, int index) {
		this.activityExecutionStatus = activityExecutionStatus;
		this.activityNodeActivation = activityNodeActivation;
		this.activationIndex = index;
	}
	
	public int getIndex() {
		return activationIndex;
	}
	
	public void addWaitingTokens(TokenList tokens) {
		waitingTokens.add(tokens);
	}
	
	public TokenList removeWaitingTokens() {
		TokenList tokens = new TokenList();		
		if(waitingTokens.size() > 0) {
			tokens.addAll(waitingTokens.remove(0));
		}
		return tokens;
	}
	
	public ActivityNodeActivation getActivityNodeActivation() {			
		return this.activityNodeActivation;
	}
	
	public List<TokenList> getWaitingTokens() {
		return new ArrayList<TokenList>(waitingTokens);
	}

	public int compareTo(ActivityNodeExecutionStatus otherStatus) {
		if(activationIndex < otherStatus.getIndex()) {
			return -1;
		} else if(activationIndex > otherStatus.getIndex()) {
			return 1;
		}
		return 0;
	}
}
