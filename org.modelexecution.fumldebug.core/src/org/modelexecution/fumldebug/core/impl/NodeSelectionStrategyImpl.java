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
	public ActivityNodeChoice chooseNextNode(ActivityExecution execution, ExecutionHierarchy executionHierarchy, HashMap<ActivityExecution, ExecutionStatus> executionStatus, boolean inGivenExecution) {
		ActivityNodeChoice nextNode = null;
		
		/*
		 * Look for enabled node in current execution
		 */
		List<ActivityNode> enabledNodes = null;
		
		ExecutionStatus executionstatus = executionStatus.get(execution);
		if(executionstatus != null) {
			enabledNodes = executionstatus.getEnabledNodes();
		}
		
		if(enabledNodes != null && enabledNodes.size() > 0) {
			nextNode = new ActivityNodeChoice(execution.hashCode(), enabledNodes.get(0));
		}			
		
		if(!inGivenExecution && nextNode == null) {
			
			/*
			 * Look for enabled node in callee executions
			 */
			List<ActivityExecution> calleeExecutions = executionHierarchy.getCallee(execution);
			if(calleeExecutions != null) {
				for(int i=0;i<calleeExecutions.size();++i) {
					ActivityExecution calleeExecution = calleeExecutions.get(i);
					nextNode = chooseNextNode(calleeExecution, executionHierarchy, executionStatus, false);
					if(nextNode != null) {
						break;
					}
				}
			}
			
			/*
			 * Look for enabled node in caller execution
			 */
			if(nextNode == null) {
				ActivityExecution callerExecution = executionHierarchy.getCaller(execution);
				if(callerExecution != null) {
					nextNode = chooseNextNode(callerExecution, executionHierarchy, executionStatus, false);
				}
			}
		}			
		return nextNode;
	}	
}
