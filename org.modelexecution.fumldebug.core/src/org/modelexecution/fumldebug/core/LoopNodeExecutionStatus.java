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

import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.Values;
import fUML.Semantics.Activities.CompleteStructuredActivities.ValuesList;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationList;
import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.BasicActions.OutputPinList;
import fUML.Syntax.Actions.BasicActions.Pin;
import fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class LoopNodeExecutionStatus extends StructuredActivityNodeExecutionStatus {

	LoopExecutionState state = LoopExecutionState.INITIALIZED;
	LoopNodeActivation loopNodeActivation = null;
	
	public LoopNodeExecutionStatus(ActivityExecutionStatus activityExecutionStatus, LoopNodeActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
		loopNodeActivation = (LoopNodeActivation)activityNodeActivation;
	}
	
	public void loopNodeStartsTest() {
		state = LoopExecutionState.TESTSTARTED;
	}
	
	public void loopNodeStartsBody() {
		state = LoopExecutionState.BODYSTARTED;
	}
	
	@Override
	public void updateStatus() {
		LoopNode loopNode = (LoopNode)getActivityNodeActivation().node;
		if(state == LoopExecutionState.TESTSTARTED) {
			if(!activityExecutionStatus.isAnyNodeEnabled(new ArrayList<ActivityNode>(loopNode.test))) {
				state = LoopExecutionState.TESTFINISHED;
			}
		} else if(state == LoopExecutionState.BODYSTARTED) {			
			if(!activityExecutionStatus.isAnyNodeEnabled(new ArrayList<ActivityNode>(loopNode.bodyPart))) {
				state = LoopExecutionState.BODYFINISHED;
			}
		}		
		
		if(isLoopNodeTestFinished()) { 			
			if(isLoopNodeTestFulfilled()) {
				runLoopNodeBody();				
			} else {
				finishLoopNodeExecution();
			}			
		} else if(isLoopBodyFinished()) {
			finishLoopNodeBody();
			runLoopNodeTest();
		}
	}
	
	private boolean isLoopNodeTestFinished() {
		return (state == LoopExecutionState.TESTFINISHED);
	}
	
	private boolean isLoopBodyFinished() {
		return (state == LoopExecutionState.BODYFINISHED);
	}
	
	private void finishLoopNodeBody() {
		LoopNode loopNode = (LoopNode)loopNodeActivation.node;
		// START code from void LoopNodeActivation.runBody()
		OutputPinList bodyOutputs = loopNode.bodyOutput;
		ValuesList bodyOutputLists = loopNodeActivation.bodyOutputLists;
		for (int i = 0; i < bodyOutputs.size(); i++) {
			OutputPin bodyOutput = bodyOutputs.getValue(i);
			Values bodyOutputList = bodyOutputLists.getValue(i);
			bodyOutputList.values = loopNodeActivation.getPinValues(bodyOutput);
		}
		// END code from void LoopNodeActivation.runBody()
	}
	
	private boolean isLoopNodeTestFulfilled() {
		// START code from boolean LoopNodeActivation.runTest()
		ValueList values = loopNodeActivation.getPinValues(((LoopNode)loopNodeActivation.node).decider);
		boolean decision = false;
		if (values.size() > 0) {
			decision = ((BooleanValue) (values.getValue(0))).value;
		}
		// END code from boolean LoopNodeActivation.runTest()		
		return decision;
	}
	
	private void runLoopNodeBody() {
		if(!((LoopNode)loopNodeActivation.node).isTestedFirst) {
			prepareLoopIteration();
		} else {
			finishLoopIteration();
		}
		loopNodeActivation.runBody();
	}
	
	private void runLoopNodeTest() {
		if(((LoopNode)loopNodeActivation.node).isTestedFirst) {
			prepareLoopIteration();
		} else {
			finishLoopIteration();
		}
		loopNodeActivation.runTest();
	}
	
	private void prepareLoopIteration() {
		// START code from void LoopNodeActivation.doStructuredActivity()
		LoopNode loopNode = (LoopNode) (loopNodeActivation.node);
		OutputPinList loopVariables = loopNode.loopVariable;
		ValuesList bodyOutputLists = loopNodeActivation.bodyOutputLists;
		// Set loop variable values
		loopNodeActivation.runLoopVariables();
		for (int i = 0; i < loopVariables.size(); i++) {
			OutputPin loopVariable = loopVariables.getValue(i);
			Values bodyOutputList = bodyOutputLists.getValue(i);
			ValueList values = bodyOutputList.values;
			loopNodeActivation.putPinValues(loopVariable, values);
			((OutputPinActivation) loopNodeActivation.activationGroup
					.getNodeActivation(loopVariable)).sendUnofferedTokens();
		}

		// Run all the non-executable, non-pin nodes in the conditional
		// node.
		ActivityNodeActivationList nodeActivations = loopNodeActivation.activationGroup.nodeActivations;
		ActivityNodeActivationList nonExecutableNodeActivations = new ActivityNodeActivationList();
		for (int i = 0; i < nodeActivations.size(); i++) {
			ActivityNodeActivation nodeActivation = nodeActivations
					.getValue(i);
			if (!(nodeActivation.node instanceof ExecutableNode | nodeActivation.node instanceof Pin)) {
				nonExecutableNodeActivations.addValue(nodeActivation);
			}
		}
		loopNodeActivation.activationGroup.run(nonExecutableNodeActivations);
		// END code from void LoopNodeActivation.doStructuredActivity()
	}
	
	private void finishLoopIteration() {
		loopNodeActivation.activationGroup.terminateAll();
	}
	
	private void finishLoopNodeExecution() {
		LoopNode loopNode = (LoopNode)loopNodeActivation.node;
		
		// START code void LoopNodeActivation.doStructuredActivity()		
		ValuesList bodyOutputLists = loopNodeActivation.bodyOutputLists;
		OutputPinList resultPins = loopNode.result;
		for (int i = 0; i < bodyOutputLists.size(); i++) {
			Values bodyOutputList = bodyOutputLists.getValue(i);
			OutputPin resultPin = resultPins.getValue(i);
			loopNodeActivation.putTokens(resultPin, bodyOutputList.values);
		}
		// END code void LoopNodeActivation.doStructuredActivity()
		
		handleEndOfExecution();
	}
	
	private enum LoopExecutionState {INITIALIZED, TESTSTARTED, TESTFINISHED, BODYSTARTED, BODYFINISHED}
}
