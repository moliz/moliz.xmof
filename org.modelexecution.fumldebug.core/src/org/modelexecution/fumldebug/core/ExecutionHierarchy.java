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
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExecutionHierarchy {

	protected HashMap<ActivityExecution, List<ActivityNode>> enabledNodes = new HashMap<ActivityExecution, List<ActivityNode>>();		
	protected HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeActivation>> enabledActivations = new HashMap<ActivityExecution, HashMap<ActivityNode, ActivityNodeActivation>>();
	protected HashMap<ActivityNodeActivation, TokenList> enabledActivationTokens = new HashMap<ActivityNodeActivation, TokenList>();
	// key = called execution, value = caller execution
	protected HashMap<ActivityExecution, ActivityExecution> executionHierarchyCaller = new HashMap<ActivityExecution, ActivityExecution>();
	// key = caller execution, value = list of callee executions (i.e. called executions)
	protected HashMap<ActivityExecution, List<ActivityExecution>> executionHierarchyCallee = new HashMap<ActivityExecution, List<ActivityExecution>>();
	
	public List<ActivityNode> getEnabledNodes(ActivityExecution execution) {
		return enabledNodes.get(execution);
	}
	
	public ActivityNodeActivation removeActivation(ActivityExecution execution, ActivityNode node) {
		ActivityNodeActivation activation = null;
		HashMap<ActivityNode, ActivityNodeActivation> activations = enabledActivations.get(execution);
		if(activations != null) {
			activation = activations.remove(node);
		}
		return activation;
	}
	
	public TokenList removeTokens(ActivityNodeActivation activation) {
		return enabledActivationTokens.remove(activation);
	}
	
	public List<ActivityExecution> getCalleeExecutions(ActivityExecution execution) {
		return executionHierarchyCallee.get(execution);
	}
	
	public ActivityExecution getCaller(ActivityExecution execution) {
		return executionHierarchyCaller.get(execution);
	}
	
	/**
	 * Removes this execution and all called executions from the hierarchy.
	 * @param execution
	 */
	public void removeExecution(ActivityExecution execution) { 
		enabledActivations.remove(execution);
		enabledNodes.remove(execution);
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
	
	public boolean hasEnabledNodesIncludingCallees(ActivityExecution execution) {
		if(hasEnabledNodes(execution)) {
			return true;
		}						
		List<ActivityExecution> callees = executionHierarchyCallee.get(execution);
		if(callees != null) {
			for(int i=0;i<callees.size();++i) {
				boolean hasenablednodes = hasEnabledNodesIncludingCallees(callees.get(i));
				if(hasenablednodes) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasEnabledNodes(ActivityExecution execution) {
		if(execution == null) {
			return false;
		}
		List<ActivityNode> enabled = enabledNodes.get(execution);
		if(enabled != null && enabled.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean hasCallerEnabledNodes(ActivityExecution execution) {
		ActivityExecution caller = executionHierarchyCaller.get(execution);
		return hasEnabledNodesIncludingCallees(caller);
	}
	
}
