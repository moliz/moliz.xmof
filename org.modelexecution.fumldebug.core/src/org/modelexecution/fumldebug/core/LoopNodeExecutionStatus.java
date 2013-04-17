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

import fUML.Semantics.Activities.CompleteStructuredActivities.LoopNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class LoopNodeExecutionStatus extends ActivityNodeExecutionStatus {

	LoopExecutionState state = LoopExecutionState.INITIALIZED;
	
	public LoopNodeExecutionStatus(ActivityExecutionStatus activityExecutionStatus, ActivityNodeActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
	}
	
	public void loopNodeStartsTest() {
		state = LoopExecutionState.TESTSTARTED;
	}
	
	public void loopNodeStartsBody() {
		state = LoopExecutionState.BODYSTARTED;
	}
	
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
	}
	
	public boolean isLoopNodeTestFinished(LoopNodeActivation loopactivation) {
		return (state == LoopExecutionState.TESTFINISHED);
	}
	
	public boolean isLoopBodyFinished(LoopNodeActivation loopactivation) {
		return (state == LoopExecutionState.BODYFINISHED);
	}
	
	private enum LoopExecutionState {INITIALIZED, TESTSTARTED, TESTFINISHED, BODYSTARTED, BODYFINISHED}
}
