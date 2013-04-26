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
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Loci.LociL1.ChoiceStrategy;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.BasicActions.OutputPinList;
import fUML.Syntax.Activities.CompleteStructuredActivities.Clause;
import fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList;
import fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ConditionalNodeExecutionStatus extends StructuredActivityNodeExecutionStatus { 

	private List<ClauseExecutionStatus> clauseExecutionStatuses = new ArrayList<ClauseExecutionStatus>();
	private ConditionalNodeActivation conditionalNodeActivation = null;
	
	public ConditionalNodeExecutionStatus(ActivityExecutionStatus activityExecutionStatus, ConditionalNodeActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
		conditionalNodeActivation = (ConditionalNodeActivation)activityNodeActivation;
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

	@Override
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
		
		boolean allClauseTestsFinished = areAllClauseTestsFinished();
		boolean anyClauseStartedBody = anyClauseStartedBody();
		boolean anyClauseFinishedBody = anyClauseFinishedBody();
		if(allClauseTestsFinished && !anyClauseStartedBody && !anyClauseFinishedBody) {
			List<ClauseActivation> successorClausesToBeEvaluated = getSuccessorClausesToBeEvaluated();
			if(successorClausesToBeEvaluated.size() > 0) {
				startTestOfClauses(successorClausesToBeEvaluated);
			} else {
				startBodyOfSelectedClause();
			}
		} else if(anyClauseFinishedBody) {
			ClauseActivation selectedClause = getClauseActivationWithExecutedBody();
			finishConditionalNodeExecution(selectedClause);
		}	
	}
	
	private boolean anyClauseStartedBody() {
		List<ClauseExecutionStatus> clausesWithFinishedBody = getClauseExecutionStatusesInState(ClauseExecutionState.BODYSTARTED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;		
	}
	
	private boolean anyClauseFinishedBody() {
		List<ClauseExecutionStatus> clausesWithFinishedBody = getClauseExecutionStatusesInState(ClauseExecutionState.BODYFINISHED);
		if(clausesWithFinishedBody.size() > 0) {
			return true;
		}
		return false;
	}
	
	private ClauseActivation getClauseActivationWithExecutedBody() {
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
	
	private boolean areAllClauseTestsFinished() {
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
	
	private List<ClauseActivation> getSuccessorClausesToBeEvaluated() {
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
	
	private void setClauseSelectedForExecutingBody(Clause selectedClause) {
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
	
	private void startTestOfClauses(List<ClauseActivation> clauseActivations) {
		for(ClauseActivation clauseActivation : clauseActivations) {
			clauseActivation.receiveControl();
		}
	}
	
	private void startBodyOfSelectedClause() {
		if (conditionalNodeActivation.selectedClauses.size() > 0 & conditionalNodeActivation.isRunning()) {
			int i = ((ChoiceStrategy) conditionalNodeActivation.getExecutionLocus().factory.getStrategy("choice")).choose(conditionalNodeActivation.selectedClauses.size());
			Clause selectedClause = conditionalNodeActivation.selectedClauses.getValue(i - 1);
			setClauseSelectedForExecutingBody(selectedClause);
			
			ClauseList clauses = ((ConditionalNode)conditionalNodeActivation.node).clause;
			for (int j = 0; j < clauses.size(); j++) {
				Clause clause = clauses.getValue(j);
				if (clause != selectedClause) {
					ExecutableNodeList testNodes = clause.test;
					for (int k = 0; k < testNodes.size(); k++) {
						ExecutableNode testNode = testNodes.getValue(k);
						conditionalNodeActivation.activationGroup.getNodeActivation(testNode).terminate();
					}
				}
			}
			conditionalNodeActivation.activationGroup.runNodes(conditionalNodeActivation.makeActivityNodeList(selectedClause.body));
		}
	}
	
	private void finishConditionalNodeExecution(ClauseActivation selectedClause) {
		if(selectedClause != null) {
			OutputPinList resultPins = ((ConditionalNode)conditionalNodeActivation.node).result;
			OutputPinList bodyOutputPins = selectedClause.clause.bodyOutput;
			for (int k = 0; k < resultPins.size(); k++) {
				OutputPin resultPin = resultPins.getValue(k);
				OutputPin bodyOutputPin = bodyOutputPins.getValue(k);
				conditionalNodeActivation.putTokens(resultPin, conditionalNodeActivation.getPinValues(bodyOutputPin));
			}
			conditionalNodeActivation.activationGroup.terminateAll();
		}
		handleEndOfExecution();
	}
}
