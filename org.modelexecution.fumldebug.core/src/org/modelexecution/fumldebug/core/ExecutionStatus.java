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

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;

/**
 * 
 * @author Tanja
 *
 */
public class ExecutionStatus {
	
	private HashMap<Integer, ActivityExecutionStatus> activityExecutionStatuses = new HashMap<Integer, ActivityExecutionStatus>();

	// key = ID of called activity execution, value = ID of calling root activity execution
	private HashMap<Integer, Integer> executionIDs = new HashMap<Integer, Integer>(); 
	
	/**
	 * Returns true if the execution with the provided ID is executing.
	 * 
	 * @param executionID
	 *            ID of the execution for which it is determined if it is
	 *            currently executed.
	 * @return true if the execution with the provided ID is executing, false
	 *         otherwise
	 */
	public boolean isExecutionRunning(int executionID) {
		return activityExecutionStatuses.containsKey(executionID);
	}
	
	/**
	 * Adds an execution.
	 * 
	 * @param activityExecution
	 *            execution to be added
	 * @param caller
	 *            node calling the execution
	 * @param entryevent
	 *            entry event of the execution
	 * @return id of the added execution
	 */
	public int addActivityExecution(ActivityExecution activityExecution, CallActionActivation caller) {
		int executionID = getExecutionID(activityExecution);		
		ActivityExecutionStatus status = new ActivityExecutionStatus(activityExecution, executionID);
		
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

		ExecutionContext.getInstance().eventHandler.handleActivityEntry(activityExecution, caller);
		
		return executionID;
	}	
	
	/**
	 * Removes an execution including all called and all calling executions.
	 * 
	 * @param executionID
	 *            executionID of the execution to be removed.
	 */
	public void removeActivityExecution(int executionID) { 
		List<ActivityExecutionStatus> executionsToBeRemoved = new ArrayList<ActivityExecutionStatus>();
		
		ActivityExecutionStatus activityExecutionStatus = getActivityExecutionStatus(executionID);
		
		if(activityExecutionStatus == null) {
			// the activity execution has already been removed
			return;
		}
		
		ActivityExecutionStatus rootCallerExecutionStatus = activityExecutionStatus.getRootCallerExecutionStatus();
		executionsToBeRemoved.add(rootCallerExecutionStatus);
		
		List<ActivityExecutionStatus> calleeExecutions = rootCallerExecutionStatus.getAllCalleeExecutionStatuses();
		executionsToBeRemoved.addAll(calleeExecutions);
		
		for(ActivityExecutionStatus status : executionsToBeRemoved) {
			activityExecutionStatuses.remove(status.getExecutionID());
		}		
	}
	
	/**
	 * Provides the executionID of the root activity calling the activity with
	 * the provided ID. This is required for determining the trace after the
	 * execution finished.
	 * 
	 * @param executionID
	 *            executionID of the execution for which the ID of the root
	 *            activity is provided
	 * @return executionID of the root activity calling the activity with the
	 *         provided ID
	 */
	public int getRootExecutionID(int executionID) {
		if(executionIDs.containsKey(executionID)) {
			return executionIDs.get(executionID);
		}
		return -1;
	}

	/**
	 * Returns the status of the execution with the provided executionID.
	 * 
	 * @param executionID
	 *            executionID of the execution for which the status is provided
	 * @return status of the execution with the provided executionID
	 */
	public ActivityExecutionStatus getActivityExecutionStatus(int executionID) {
		return activityExecutionStatuses.get(executionID);
	}
	
	/**
	 * Returns the status of the provided execution.
	 * 
	 * @param activityExecution
	 *            execution for which the status is provided
	 * @return status of the provided execution
	 */
	public ActivityExecutionStatus getActivityExecutionStatus(ActivityExecution activityExecution) {
		int executionID = getExecutionID(activityExecution);
		return activityExecutionStatuses.get(executionID);
	}

	/**
	 * Determines an ID for the provided execution.
	 * 
	 * @param execution
	 *            execution for which an ID is determined
	 * @return ID for the provided execution
	 */
	private int getExecutionID(ActivityExecution execution) {
		if(execution == null) {
			return -1;
		}
		return execution.hashCode();
	}

}
