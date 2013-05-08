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

import java.util.List;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class NodeSelectionStrategyImpl implements NodeSelectionStrategy {

	/* (non-Javadoc)
	 * @see org.modelexecution.fumldebug.core.impl.NodeSelectionStrategy#chooseNextNode(fUML.Semantics.Activities.IntermediateActivities.ActivityExecution, org.modelexecution.fumldebug.core.ExecutionHierarchy, boolean)
	 */
	public ActivityNodeChoice chooseNextNode(int executionID, ExecutionStatus executionStatus) {
		ActivityNodeChoice nextNode = null;
		
		// look for enabled node in current execution
		nextNode = chooseNode(executionID, executionStatus);
		
		// look for enabled node in callee executions
		if(nextNode == null) {
			nextNode = chooseNodeInCalleeHierarchy(executionID, executionStatus);
		}

		// look for enabled node in caller execution
		if(nextNode == null) {
			ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
			if(activityExecutionStatus != null) {
				ActivityExecutionStatus callerExecutionStatus = activityExecutionStatus.getDirectCallerExecutionStatus();
				while(callerExecutionStatus != null) {
					int callerExecutionID = callerExecutionStatus.getExecutionID();
					nextNode = chooseNode(callerExecutionID, executionStatus);				
					if(nextNode != null) {
						break;
					}
					callerExecutionStatus = callerExecutionStatus.getDirectCallerExecutionStatus();
				}
			}
		}
		return nextNode;
	}	
	
	private ActivityNodeChoice chooseNode(int executionID, ExecutionStatus executionStatus) {
		ActivityNodeChoice nextNode = null;		
		List<ActivityNode> enabledNodes = null;
		
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		if(activityExecutionStatus != null) {
			enabledNodes = activityExecutionStatus.getEnabledNodes();
		}
		if(enabledNodes != null && enabledNodes.size() > 0) {
			nextNode = new ActivityNodeChoice(executionID, enabledNodes.get(0));
		}			
		return nextNode;
	}
	
	private ActivityNodeChoice chooseNodeInCalleeHierarchy(int executionID, ExecutionStatus executionStatus) {
		ActivityNodeChoice nextNode = null;
		
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		if(activityExecutionStatus != null) {
			List<ActivityExecutionStatus> directCalleeExecutionStatuses = activityExecutionStatus.getDirectCalledExecutionStatuses();
			for(ActivityExecutionStatus directCalleeExecutionStatus : directCalleeExecutionStatuses) {
				int calleeExecutionID = directCalleeExecutionStatus.getExecutionID();
				nextNode = chooseNode(calleeExecutionID, executionStatus);
				if(nextNode != null) {
					break;
				}
				nextNode = chooseNodeInCalleeHierarchy(calleeExecutionID, executionStatus);
				if(nextNode != null) {
					break;
				}
			}						
		}
		return nextNode;
	}
}
