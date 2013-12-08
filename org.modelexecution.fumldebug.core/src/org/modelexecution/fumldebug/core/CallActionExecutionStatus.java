/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core;

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.OutputPin;

public class CallActionExecutionStatus extends ActivityNodeExecutionStatus {
	
	private CallActionActivation callActionActivation = null;
	private ActivityExecutionStatus calledActivityExecutionStatus = null;
	private ActivityExecution calledActivityExecution = null;
	
	public CallActionExecutionStatus(ActivityExecutionStatus activityExecutionStatus, CallActionActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
		callActionActivation = activityNodeActivation;		
	}
	
	public void setCalledActivityExecutionStatus(ActivityExecutionStatus calledActivityExecutionStatus) {
		this.calledActivityExecutionStatus = calledActivityExecutionStatus;
		this.calledActivityExecution = calledActivityExecutionStatus.getActivityExecution();
	}

	public ActivityExecutionStatus getCalledActivityExecutionStatus() {
		return calledActivityExecutionStatus;
	}

	@Override
	public void handleEndOfExecution() {	
		obtainCallActionOutput();
		
		// Destroy execution of the called activity				
		calledActivityExecutionStatus.destroyActivityExecution();
		callActionActivation.removeCallExecution(calledActivityExecution);
		
		// Notify about ActivityExitEvent
		ExecutionContext.getInstance().eventHandler.handleActivityExit(calledActivityExecution);

		// Call sendOffer() from the CallAction			
		callActionActivation.sendOffers();
		
		// Notify about Exit of CallAction
		super.handleEndOfExecution();

		// Check if can fire again
		this.checkIfCanFireAgain();
		
		updateStatusOfContainingStructuredActivityNode();

		boolean hasCallerEnabledNodes = activityExecutionStatus.hasEnabledNodesIncludingCallees();
		if (!hasCallerEnabledNodes) {
			activityExecutionStatus.handleEndOfExecution();
		} else {
			activityExecutionStatus.handleSuspension(callActionActivation.node);
		}
	}

	private void obtainCallActionOutput() {
		// START code from void CallActionActivation.doAction()
		ParameterValueList outputParameterValues = calledActivityExecution.getOutputParameterValues();
		for (int j = 0; j < outputParameterValues.size(); j++) {
			ParameterValue outputParameterValue = outputParameterValues.getValue(j);
			OutputPin resultPin = ((CallAction) callActionActivation.node).result.getValue(j);
			callActionActivation.putTokens(resultPin, outputParameterValue.values);
		}
		// END code from void CallActionActivation.doAction()
	}
}
