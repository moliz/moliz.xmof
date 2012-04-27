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

import org.modelexecution.fumldebug.core.ExecutionHierarchy;
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
	public ActivityNode chooseNextNode(ActivityExecution execution, ExecutionHierarchy executionHierarchy, boolean inGivenExecution) {
		ActivityNode nextNode = null;
		
		List<ActivityNode> enabledNodes = executionHierarchy.getEnabledNodes(execution);
		
		if(enabledNodes != null && enabledNodes.size() > 0) {
			nextNode = enabledNodes.get(0);
		}			
		
		if(!inGivenExecution && nextNode == null) {
			ActivityExecution callerExecution = executionHierarchy.getCaller(execution);
			if(callerExecution != null) {
				nextNode = chooseNextNode(callerExecution, executionHierarchy, false);
			}
		}			
		return nextNode;
	}	
}
