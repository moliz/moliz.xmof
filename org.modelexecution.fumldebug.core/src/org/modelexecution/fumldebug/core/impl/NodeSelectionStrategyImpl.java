/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.impl;

import java.util.List;

import org.modelexecution.fumldebug.core.ActivityNodeChoice;
import org.modelexecution.fumldebug.core.ExecutionStatus;
import org.modelexecution.fumldebug.core.NodeSelectionStrategy;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class NodeSelectionStrategyImpl implements NodeSelectionStrategy {

	/* (non-Javadoc)
	 * @see org.modelexecution.fumldebug.core.impl.NodeSelectionStrategy#chooseNextNode(fUML.Semantics.Activities.IntermediateActivities.ActivityExecution, org.modelexecution.fumldebug.core.ExecutionHierarchy, boolean)
	 */
	//TODO public ActivityNodeChoice chooseNextNode(ActivityExecution execution, ExecutionHierarchy executionHierarchy, HashMap<ActivityExecution, ActivityExecutionStatus> executionStatus) {
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
			int callerExecutionID = executionStatus.getCallerExecutionID(executionID);
			while(callerExecutionID != -1) {
				nextNode = chooseNode(callerExecutionID, executionStatus);				
				if(nextNode != null) {
					break;
				}
				callerExecutionID = executionStatus.getCallerExecutionID(callerExecutionID);
			}
		}
		return nextNode;
	}	
	
	private ActivityNodeChoice chooseNode(int executionID, ExecutionStatus executionStatus) {
		ActivityNodeChoice nextNode = null;		
		List<ActivityNode> enabledNodes = null;
		
		enabledNodes = executionStatus.getEnabledNodes(executionID);		
		if(enabledNodes != null && enabledNodes.size() > 0) {
			nextNode = new ActivityNodeChoice(executionID, enabledNodes.get(0));
		}			
		return nextNode;
	}
	
	private ActivityNodeChoice chooseNodeInCalleeHierarchy(int executionID, ExecutionStatus executionStatus) {
		ActivityNodeChoice nextNode = null;
		
		List<Integer> calleeExecutionIDs = executionStatus.getDirectCalleesExecutionID(executionID);
		for(int calleeExecutionID : calleeExecutionIDs) {
			nextNode = chooseNode(calleeExecutionID, executionStatus);
			if(nextNode != null) {
				break;
			}
			nextNode = chooseNodeInCalleeHierarchy(calleeExecutionID, executionStatus);
			if(nextNode != null) {
				break;
			}
		}						
		return nextNode;
	}
}
