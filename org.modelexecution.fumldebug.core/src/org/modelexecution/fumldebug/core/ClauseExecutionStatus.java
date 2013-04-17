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

import fUML.Semantics.Activities.CompleteStructuredActivities.ClauseActivation;

public class ClauseExecutionStatus {

	private ClauseActivation clauseActivation = null;
	private ClauseExecutionState status = ClauseExecutionState.INITIALIZED;
	private boolean testFulfilled = false;
	
	public ClauseExecutionStatus(ClauseActivation clauseactivation) {
		this.clauseActivation = clauseactivation;
	}
	
	public void setTestFulfilled() {
		testFulfilled = true;
	}		
	
	public boolean isTestFulfilled() {
		return testFulfilled;
	}
	
	public void setStatus(ClauseExecutionState status) {
		this.status = status;
	}
	
	public ClauseActivation getClauseActivation() {
		return clauseActivation;
	}
	
	public ClauseExecutionState getStatus() {
		return status;
	}
	
	public enum ClauseExecutionState {
		INITIALIZED, TESTSTARTED, TESTFINISHED, BODYSTARTED, BODYFINISHED;
	}
}
