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

import java.util.HashMap;
import java.util.List;

import org.modelexecution.fumldebug.core.ActivityNodeChoice;
import org.modelexecution.fumldebug.core.ExecutionHierarchy;
import org.modelexecution.fumldebug.core.ExecutionStatus;
import org.modelexecution.fumldebug.core.NodeSelectionStrategy;

import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class NodeSelectionStrategyImpl implements NodeSelectionStrategy {

	/* (non-Javadoc)
	 * @see org.modelexecution.fumldebug.core.impl.NodeSelectionStrategy#chooseNextNode(fUML.Semantics.Activities.IntermediateActivities.ActivityExecution, org.modelexecution.fumldebug.core.ExecutionHierarchy, boolean)
	 */
	public ActivityNodeChoice chooseNextNode(ActivityExecution execution, ExecutionHierarchy executionHierarchy, HashMap<ActivityExecution, ExecutionStatus> executionStatus) {
		ActivityNodeChoice nextNode = null;
		
		// look for enabled node in current execution
		ExecutionStatus executionstatus = executionStatus.get(execution);
		nextNode = chooseNode(execution, executionstatus);
		
		// look for enabled node in callee executions
		if(nextNode == null) {
			nextNode = chooseNodeInCalleeHierarchy(execution, executionHierarchy, executionStatus);
		}

		// look for enabled node in caller execution
		if(nextNode == null) {
			ActivityExecution callerExecution = executionHierarchy.getCaller(execution);
			while(callerExecution != null) {
				ExecutionStatus callerstatus = executionStatus.get(callerExecution);
				nextNode = chooseNode(callerExecution, callerstatus);
				
				if(nextNode != null) {
					break;
				}
				callerExecution = executionHierarchy.getCaller(execution);
			}
		}
		return nextNode;
	}	
	
	private ActivityNodeChoice chooseNode(ActivityExecution execution, ExecutionStatus executionstatus) {
		ActivityNodeChoice nextNode = null;
		
		List<ActivityNode> enabledNodes = null;
		
		if(executionstatus != null) {
			enabledNodes = executionstatus.getEnabledNodes();
		}
		
		if(enabledNodes != null && enabledNodes.size() > 0) {
			nextNode = new ActivityNodeChoice(execution.hashCode(), enabledNodes.get(0));
		}	
		
		return nextNode;
	}
	
	private ActivityNodeChoice chooseNodeInCalleeHierarchy(ActivityExecution execution, ExecutionHierarchy executionHierarchy, HashMap<ActivityExecution, ExecutionStatus> executionStatus) {
		ActivityNodeChoice nextNode = null;
		
		List<ActivityExecution> calleeExecutions = executionHierarchy.getCallee(execution);
		if(calleeExecutions != null) {
			for(ActivityExecution calleeExecution : calleeExecutions) {
				ExecutionStatus calleestatus = executionStatus.get(calleeExecution);
				nextNode = chooseNode(calleeExecution, calleestatus);
				if(nextNode != null) {
					break;
				}
				nextNode = chooseNodeInCalleeHierarchy(calleeExecution, executionHierarchy, executionStatus);
				if(nextNode != null) {
					break;
				}
			}						
		}
		
		return nextNode;
	}
}
