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
import java.util.List;

import org.modelexecution.fumldebug.core.ClauseExecutionStatus.ClauseExecutionState;

import fUML.Semantics.Activities.CompleteStructuredActivities.ClauseActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Syntax.Activities.CompleteStructuredActivities.Clause;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ConditionalNodeExecutionStatus extends ActivityNodeExecutionStatus {

	private List<ClauseExecutionStatus> clauseExecutionStatuses = new ArrayList<ClauseExecutionStatus>();
	
	public ConditionalNodeExecutionStatus(ActivityExecutionStatus activityExecutionStatus, ActivityNodeActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
	}
	
	public void addClauseActivation(ClauseActivation clauseactivation) {	
		ClauseExecutionStatus existingClauseExecutionStatus = getClauseExecutionStatus(clauseactivation);
		if(existingClauseExecutionStatus == null) {
			ClauseExecutionStatus clauseExecutionStatus = new ClauseExecutionStatus(clauseactivation);
			clauseExecutionStatuses.add(clauseExecutionStatus);
		}
	}
	
	public void clauseStartsTest(ClauseActivation clauseActivation) {
		ClauseExecutionStatus clauseExecutionStatus = getClauseExecutionStatus(clauseActivation);
		clauseExecutionStatus.setStatus(ClauseExecutionState.TESTSTARTED);		
	}

	public void updateStatus() {
		List<ClauseExecutionStatus> clausesWithTestStarted = getClauseExecutionStatusesInState(ClauseExecutionState.TESTSTARTED);
		List<ClauseExecutionStatus> clausesWithBodyStarted = getClauseExecutionStatusesInState(ClauseExecutionState.BODYSTARTED);
		
		for(ClauseExecutionStatus ClauseExecutionStatus : clausesWithTestStarted) {
			if(!activityExecutionStatus.isAnyNodeEnabled(new ArrayList<ActivityNode>(ClauseExecutionStatus.getClauseActivation().clause.test))) {
				ClauseExecutionStatus.setStatus(ClauseExecutionState.TESTFINISHED);
				if(ClauseExecutionStatus.getClauseActivation().getDecision().value == true) {
					ClauseExecutionStatus.setTestFulfilled();
					ClauseExecutionStatus.getClauseActivation().selectBody();
				}
			}
		}

		for(ClauseExecutionStatus ClauseExecutionStatus : clausesWithBodyStarted) {
			if(!activityExecutionStatus.isAnyNodeEnabled(new ArrayList<ActivityNode>(ClauseExecutionStatus.getClauseActivation().clause.body))) {
				ClauseExecutionStatus.setStatus(ClauseExecutionState.BODYFINISHED);
			}
		}		
	}
	
	public boolean anyClauseStartedBody() {
		List<ClauseExecutionStatus> clausesWithFinishedBody = getClauseExecutionStatusesInState(ClauseExecutionState.BODYSTARTED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;		
	}
	
	public boolean anyClauseFinishedBody() {
		List<ClauseExecutionStatus> clausesWithFinishedBody = getClauseExecutionStatusesInState(ClauseExecutionState.BODYFINISHED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;
	}
	
	public ClauseActivation getClauseActivationWithExecutedBody() {
		List<ClauseExecutionStatus> clausesWithFinishedBody = getClauseExecutionStatusesInState(ClauseExecutionState.BODYFINISHED);
		if(clausesWithFinishedBody.size() > 0) {
			return clausesWithFinishedBody.get(0).getClauseActivation();
		}
		return null;
	}
	
	private List<ClauseExecutionStatus> getClauseExecutionStatusesInState(ClauseExecutionState status) {
		List<ClauseExecutionStatus> clauseExecutionStatuses = new ArrayList<ClauseExecutionStatus>();
		for(ClauseExecutionStatus clauseExecutionStatus : this.clauseExecutionStatuses) {
			if(clauseExecutionStatus.getStatus() == status) {
				clauseExecutionStatuses.add(clauseExecutionStatus);
			}
		}
		return clauseExecutionStatuses;
	}
	
	public boolean areAllClauseTestsFinished() {
		int startedClausTests = 0;
		for(ClauseExecutionStatus clauseExecutionStatus : clauseExecutionStatuses) {
			if(clauseExecutionStatus.getStatus() == ClauseExecutionState.TESTSTARTED) {
				++startedClausTests;
			} 
		}
		if(startedClausTests == 0) {
			return true;
		} 
		return false;
	}
	
	public List<ClauseActivation> getSuccessorClausesToBeEvaluated() {
		List<ClauseActivation> successorClauses = new ArrayList<ClauseActivation>();
		for(ClauseExecutionStatus clauseExecutionStatus : clauseExecutionStatuses) {
			if(clauseExecutionStatus.getStatus() == ClauseExecutionState.TESTFINISHED && !clauseExecutionStatus.isTestFulfilled()) {
				for(ClauseActivation successor : clauseExecutionStatus.getClauseActivation().getSuccessors()) {
					ClauseExecutionStatus successorClauseExecutionStatus = getClauseExecutionStatus(successor);
					if(successorClauseExecutionStatus.getStatus() == ClauseExecutionState.INITIALIZED) {
						if(successor.isReady()) {
							successorClauses.add(successor);
						}
					}
				}
			}
		}
		return successorClauses;
	}
	
	public void setClauseSelectedForExecutingBody(Clause selectedClause) {
		for(ClauseExecutionStatus clauseExecutionStatus : clauseExecutionStatuses) {
			if(clauseExecutionStatus.getClauseActivation().clause == selectedClause) {
				clauseExecutionStatus.setStatus(ClauseExecutionState.BODYSTARTED);
			}
		}
	}
	
	private ClauseExecutionStatus getClauseExecutionStatus(ClauseActivation clauseActivation) {
		for(ClauseExecutionStatus clauseExecutionStatus : clauseExecutionStatuses) {
			if(clauseExecutionStatus.getClauseActivation().equals(clauseActivation)) {
				return clauseExecutionStatus;
			}
		}
		return null;
	}
}
