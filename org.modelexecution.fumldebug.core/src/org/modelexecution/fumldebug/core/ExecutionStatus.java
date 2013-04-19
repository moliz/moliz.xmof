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
			ActivityExecution callerExecution = caller.getActivityExecution();
			int callerExecutionID = getExecutionID(callerExecution);
			
			ActivityExecutionStatus callerStatus = getActivityExecutionStatus(callerExecutionID);
			status.setDirectCallerExecutionStatus(callerStatus);
			callerStatus.addDirectCalledExecutionStatus(status);
			
			status.setActivityCallerNode(caller);
			
			status.setInResumeMode(callerStatus.isInResumeMode());
			
			ActivityExecutionStatus rootExecutionStatus = status.getRootCallerExecutionStatus();
			executionIDs.put(executionID, rootExecutionStatus.getExecutionID());
		}  else {
			executionIDs.put(executionID, executionID);
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
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
		ActivityExecutionStatus status = getActivityExecutionStatus(executionID);
		if(status == null) {
			return new ArrayList<ActivityNode>();
		}
		return status.getEnabledNodes();
	}

	protected int getExecutionID(ActivityExecution execution) { //TODO protected because of Aspect
		if(execution == null) {
			return -1;
		}
		return execution.hashCode();
	}
	
}
