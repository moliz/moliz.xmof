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

import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * Class for storing the status of an user-level activity execution, i.e., the
 * status of an activity execution which was started by the user. Called
 * sub-activities are managed by this class.
 * 
 * @author Mayerhofer
 * 
 */
public class ExecutionStatus {
	
	private HashMap<Integer, ActivityExecution> activityExecutions = new HashMap<Integer, ActivityExecution>();
	
	private HashMap<ActivityExecution, ActivityExecutionStatus> activityExecutionStatuses = new HashMap<ActivityExecution, ActivityExecutionStatus>();
	
	private List<ActivityExecutionStatus> rootLevelActivityExecutionStatuses = new ArrayList<ActivityExecutionStatus>();
	
	// TODO is this data structure really necessary? 
	private ExecutionHierarchy executionhierarchy = new ExecutionHierarchy();
	
	public ExecutionStatus() {
	}

	public boolean isExecutionRunning(int executionID) {
		return activityExecutions.containsKey(executionID);
	}
	
	//TODO is the entry event really necessary?
	public int addActivityExecution(ActivityExecution activityExecution, ActivityNodeActivation caller, ActivityEntryEvent entryevent) {
		int executionID = getExecutionID(activityExecution);
		
		ActivityExecutionStatus status = new ActivityExecutionStatus(activityExecution);
		
		status.setActivityEntryEvent(entryevent);
		
		activityExecutionStatuses.put(activityExecution, status);
		activityExecutions.put(executionID, activityExecution);
		
		ActivityExecution callerExecution = null;
		
		if(caller != null) {
			status.setActivityCalls(caller);
			callerExecution = caller.getActivityExecution();		
			
			ActivityExecutionStatus callerStatus = activityExecutionStatuses.get(callerExecution);
			status.setInResumeMode(callerStatus.isInResumeMode());
		} else {			
			rootLevelActivityExecutionStatuses.add(status);
		}
		
		executionhierarchy.addExecution(activityExecution, callerExecution);
		
		return executionID;
	}	
	
	public void removeActivityExecution(int executionID) {
//TODO this is only for removing root level activities. is it necessary to have this for called activities also?
		List<ActivityExecution> executionsToBeRemoved = new ArrayList<ActivityExecution>();
		
		ActivityExecution execution = activityExecutions.get(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(execution);
		ActivityExecutionStatus rootExecutionStatus = activityExecutionStatuses.get(rootExecution);
		executionsToBeRemoved.add(rootExecution);
		
		List<ActivityExecution> calleeExecutions = executionhierarchy.getAllCallees(rootExecution);
		executionsToBeRemoved.addAll(calleeExecutions);
		
		for(ActivityExecution e : executionsToBeRemoved) {
			activityExecutions.remove(e.hashCode());
			activityExecutionStatuses.remove(e);
		}
		
		executionhierarchy.removeExecution(rootExecution);		
		rootLevelActivityExecutionStatuses.remove(rootExecutionStatus);
		
/*		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
			activityExecutionStatus.remove(callees.get(i));
		}
		
		executionhierarchy.removeExecution(execution);		
		activityExecutionStatus.remove(execution);*/
	}
/*	
	private void removeExecution(ActivityExecution execution) {
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
		}
		this.activityExecutions.remove(execution.hashCode());
	}
*/	
	public ActivityExecutionStatus getActivityExecutionStatus(int executionID) {
		ActivityExecution execution = activityExecutions.get(executionID);
		return activityExecutionStatuses.get(execution);
	}
	
	public List<Integer> getDirectCalleesExecutionID(int executionID) {
		List<Integer> directCalleesExecutionID = new ArrayList<Integer>();
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		List<ActivityExecution> directCallees = executionhierarchy.getDirectCallees(activityExecution);
		for(ActivityExecution callee : directCallees) {
			directCalleesExecutionID.add(getExecutionID(callee));
		}
		return directCalleesExecutionID;
	}
	
	public int getRootCallerExecutionID(int executionID) {
		//TODO required?
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(activityExecution);
		return getExecutionID(rootExecution);
	}
	
	public int getCallerExecutionID(int executionID) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecution callerExecution = executionhierarchy.getCaller(activityExecution);
		return getExecutionID(callerExecution);
	}
	
	public ActivityNodeActivation getActivityNodeActivation(int executionID, ActivityNode node) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecutionStatus status = activityExecutionStatuses.get(activityExecution);
		return status.getEnabledActivation(node);
		//return activityExecution.activationGroup.getNodeActivation(node); //TODO might cause problems with structured activity nodes --> introduce activation map again in activity execution status
	}
	
	public boolean isNodeEnabled(int executionID, ActivityNode activityNode) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecutionStatus status = activityExecutionStatuses.get(activityExecution);
		return status.isNodeEnabled(activityNode);
	}
	
	public TokenList getTokens(int executionID, ActivityNode node) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecutionStatus status = activityExecutionStatuses.get(activityExecution);
		return status.getTokens(node);
	}
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		ActivityExecutionStatus status = activityExecutionStatuses.get(activityExecution);
		if(status == null) {
			return new ArrayList<ActivityNode>();
		}
		return status.getEnabledNodes();
	}
	
	public boolean hasEnabledNodesIncludingCallees(int executionID) {	
		ActivityExecution execution = activityExecutions.get(executionID);		
		if(execution == null) {
			return false;
		}
		
		ActivityExecutionStatus executionStatus = activityExecutionStatuses.get(execution);		
		if(executionStatus == null) {
			return false;
		}
		
		if(executionStatus.hasEnabledNodes()) {
			return true;
		}
		
		List<ActivityExecution> callees = executionhierarchy.getDirectCallees(execution);		
		for(ActivityExecution callee : callees) {
			boolean hasEnabledNodes = hasEnabledNodesIncludingCallees(getExecutionID(callee));
			if(hasEnabledNodes) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isExecutionInResumeMode(int executionID) {
		ActivityExecution execution = activityExecutions.get(executionID);
		ActivityExecutionStatus executionStatus = activityExecutionStatuses.get(execution);
		if(executionStatus == null) {
			return false;
		}
		return executionStatus.isInResumeMode();
	}

	public void setExecutionInResumeMode(int executionID, boolean inResumeMode) {
		List<ActivityExecution> executionsToSetResume = new ArrayList<ActivityExecution>();
		
		ActivityExecution execution = activityExecutions.get(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(execution);
		executionsToSetResume.add(rootExecution);
		
		List<ActivityExecution> calleeExecutions = executionhierarchy.getAllCallees(rootExecution);
		executionsToSetResume.addAll(calleeExecutions);
		
		for(ActivityExecution e : executionsToSetResume) {
			ActivityExecutionStatus executionStatus = activityExecutionStatuses.get(e);
			executionStatus.setInResumeMode(inResumeMode);
		}
	}
	
	protected int getExecutionID(ActivityExecution execution) { //TODO protected because of Aspect
		if(execution == null) {
			return -1;
		}
		return execution.hashCode();
	}
	
	protected ExecutionHierarchy getExecutionHierarchy() { //TODO protected because of test cases --> is hierarchy necessary?
		return executionhierarchy;
	}
}
