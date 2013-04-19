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
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * TODO
 * Class for storing the status of an user-level activity execution, i.e., the
 * status of an activity execution which was started by the user. Called
 * sub-activities are managed by this class.
 * 
 * @author Tanja
 * 
 */
public class ExecutionStatus {
	
	private HashMap<Integer, ActivityExecutionStatus> activityExecutionStatuses = new HashMap<Integer, ActivityExecutionStatus>();

	// key = ID of called activity execution, value = ID of calling root activity execution
	private HashMap<Integer, Integer> executionIDs = new HashMap<Integer, Integer>(); 
	
	public ExecutionStatus() {
	}

	public boolean isExecutionRunning(int executionID) {
		return activityExecutionStatuses.containsKey(executionID);
	}
	
	//TODO is the entry event really necessary?
	public int addActivityExecution(ActivityExecution activityExecution, ActivityNodeActivation caller, ActivityEntryEvent entryevent) {
		int executionID = getExecutionID(activityExecution);		
		ActivityExecutionStatus status = new ActivityExecutionStatus(activityExecution, executionID);
		
		status.setActivityEntryEvent(entryevent);
		
		activityExecutionStatuses.put(executionID, status);
				
		if(caller != null) {
			status.setActivityCalls(caller);
			ActivityExecution callerExecution = caller.getActivityExecution();
			int callerExecutionID = getExecutionID(callerExecution);
			
			ActivityExecutionStatus callerStatus = getActivityExecutionStatus(callerExecutionID);
			status.setDirectCallerExecutionStatus(callerStatus);
			callerStatus.addDirectCalledExecutionStatus(status);
			
			status.setInResumeMode(callerStatus.isInResumeMode());
			
			ActivityExecutionStatus rootExecutionStatus = status.getRootCallerExecutionStatus();
			executionIDs.put(executionID, rootExecutionStatus.getExecutionID());
		}  else {
			executionIDs.put(executionID, executionID);
//TODO			rootLevelActivityExecutionStatuses.add(status);
		}

		return executionID;
	}	
	
	public void removeActivityExecution(int executionID) {
//TODO this is only for removing root level activities. is it necessary to have this for called activities also?
		List<ActivityExecutionStatus> executionsToBeRemoved = new ArrayList<ActivityExecutionStatus>();
		
		ActivityExecutionStatus activityExecutionStatus = getActivityExecutionStatus(executionID);
		
		ActivityExecutionStatus rootCallerExecutionStatus = activityExecutionStatus.getRootCallerExecutionStatus();
		executionsToBeRemoved.add(rootCallerExecutionStatus);
		
		List<ActivityExecutionStatus> calleeExecutions = rootCallerExecutionStatus.getAllCalleeExecutionStatuses();
		executionsToBeRemoved.addAll(calleeExecutions);
		
		for(ActivityExecutionStatus status : executionsToBeRemoved) {
			activityExecutionStatuses.remove(status.getExecutionID());
		}
				
//TODO		rootLevelActivityExecutionStatuses.remove(rootCallerExecutionStatus);		
	}
	
	public int getRootExecutionID(int executionID) {
		if(executionIDs.containsKey(executionID)) {
			return executionIDs.get(executionID);
		}
		return -1;
	}

	public ActivityExecutionStatus getActivityExecutionStatus(int executionID) {
		return activityExecutionStatuses.get(executionID);
	}
	
	public ActivityExecutionStatus getActivityExecutionStatus(ActivityExecution activityExecution) {
		int executionID = getExecutionID(activityExecution);
		return activityExecutionStatuses.get(executionID);
	}
/*	
	private ActivityExecution getActivityExecution(int executionID) {
		ActivityExecutionStatus activityExecutionStatus = getActivityExecutionStatus(executionID);
		if(activityExecutionStatus != null) {
			return activityExecutionStatus.getActivityExecution();
		}
		return null;
	}
*/
	/*
	public List<Integer> getDirectCalleesExecutionID(int executionID) {
		List<Integer> directCalleesExecutionID = new ArrayList<Integer>();
		ActivityExecution activityExecution = getActivityExecution(executionID);
		List<ActivityExecution> directCallees = executionhierarchy.getDirectCallees(activityExecution);
		for(ActivityExecution callee : directCallees) {
			directCalleesExecutionID.add(getExecutionID(callee));
		}
		return directCalleesExecutionID;
	}
	*/
	/*
	public int getRootCallerExecutionID(int executionID) {
		//TODO required?
		ActivityExecution activityExecution = getActivityExecution(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(activityExecution);
		return getExecutionID(rootExecution);
	}*/
	/*
	public int getCallerExecutionID(int executionID) {
		ActivityExecution activityExecution = getActivityExecution(executionID);
		ActivityExecution callerExecution = executionhierarchy.getCaller(activityExecution);
		return getExecutionID(callerExecution);
	}*/
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
		ActivityExecutionStatus status = getActivityExecutionStatus(executionID);
		if(status == null) {
			return new ArrayList<ActivityNode>();
		}
		return status.getEnabledNodes();
	}
/*	
	public boolean hasEnabledNodesIncludingCallees(int executionID) {	
		ActivityExecution execution = getActivityExecution(executionID);		
		if(execution == null) {
			return false;
		}
		
		ActivityExecutionStatus executionStatus = getActivityExecutionStatus(executionID);		
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
*/
	/*
	public void setExecutionInResumeMode(int executionID, boolean inResumeMode) {
		ActivityExecutionStatus activityExecutionStatus = getActivityExecutionStatus(executionID);
		
		List<ActivityExecutionStatus> 
		List<ActivityExecution> executionsToSetResume = new ArrayList<ActivityExecution>();
		
		ActivityExecution execution = getActivityExecution(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(execution);
		executionsToSetResume.add(rootExecution);
		
		List<ActivityExecution> calleeExecutions = executionhierarchy.getAllCallees(rootExecution);
		executionsToSetResume.addAll(calleeExecutions);
		
		for(ActivityExecution e : executionsToSetResume) {
			int executionIDtoBeResumed = getExecutionID(e);
			ActivityExecutionStatus executionStatus = getActivityExecutionStatus(executionIDtoBeResumed);
			executionStatus.setInResumeMode(inResumeMode);
		}
	}
*/	
	protected int getExecutionID(ActivityExecution execution) { //TODO protected because of Aspect
		if(execution == null) {
			return -1;
		}
		return execution.hashCode();
	}
	
	/*
	protected ExecutionHierarchy getExecutionHierarchy() { //TODO protected because of test cases --> is hierarchy necessary?
		return executionhierarchy;
	}*/
}
