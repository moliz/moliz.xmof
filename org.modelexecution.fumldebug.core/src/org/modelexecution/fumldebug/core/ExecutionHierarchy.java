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

import java.util.HashMap;
import java.util.List;

import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionHierarchy {

	// key = called execution, value = caller execution
	protected HashMap<ActivityExecution, ActivityExecution> executionHierarchyCaller = new HashMap<ActivityExecution, ActivityExecution>();
	
	// key = caller execution, value = list of callee executions (i.e. called executions)
	protected HashMap<ActivityExecution, List<ActivityExecution>> executionHierarchyCallee = new HashMap<ActivityExecution, List<ActivityExecution>>();
		
	/**
	 * Provides directly called activity executions of the given activity execution
	 * @param execution
	 * @return
	 */
	public List<ActivityExecution> getCalleeExecutions(ActivityExecution execution) {
		return executionHierarchyCallee.get(execution);
	}
	
	/**
	 * Provides the activity execution of the direct calling activity of the provided activity execution
	 * @param execution
	 * @return
	 */
	public ActivityExecution getCaller(ActivityExecution execution) {
		return executionHierarchyCaller.get(execution);
	}
	
	/**
	 * Provices the activity execution of the root calling activity of the provided activity execution
	 * @param execution
	 * @return
	 */
	public ActivityExecution getRootCaller(ActivityExecution execution) {
		ActivityExecution callerExecution = getCaller(execution);
		if(callerExecution == null) {
			return execution;
		} else {
			return getRootCaller(callerExecution);
		}
	}
	
	/**
	 * Removes this execution and all called executions from the hierarchy.
	 * @param execution
	 */
	public void removeExecution(ActivityExecution execution) { 
		ActivityExecution callerExecution = executionHierarchyCaller.get(execution);
		if(callerExecution != null) {				
			executionHierarchyCallee.get(callerExecution).remove(execution);
		}
		List<ActivityExecution> callees = executionHierarchyCallee.get(execution);
		for(int i=0;i<callees.size();++i) {
			removeExecution(callees.get(i));
		}
		executionHierarchyCallee.remove(execution);
		executionHierarchyCaller.remove(execution);
	}	
	
}
