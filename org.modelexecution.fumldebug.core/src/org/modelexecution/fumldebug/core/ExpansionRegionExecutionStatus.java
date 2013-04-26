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

import java.util.List;

import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivationList;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroup;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroupList;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNodeList;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class ExpansionRegionExecutionStatus extends ActivityNodeExecutionStatus {

	private ExpansionRegionActivation expansionRegionActivation = null;
	
	public ExpansionRegionExecutionStatus(ActivityExecutionStatus activityExecutionStatus, ExpansionRegionActivation activityNodeActivation, int index) {
		super(activityExecutionStatus, activityNodeActivation, index);
		expansionRegionActivation = (ExpansionRegionActivation)activityNodeActivation;
	}
	
	/**
	 * Handles the further execution of an expansion region.
	 * 
	 * @param expansionActivationGroup
	 */
	public void updateStatus() {
		ExpansionActivationGroup currentExpansionActivationGroup = getCurrentExpansionActivationGroup();

		if(currentExpansionActivationGroup == null) {
			return;
		}
		
		boolean groupHasEnabledNode = hasExpansionActivationGroupEnabledNodes(currentExpansionActivationGroup);
		if (!groupHasEnabledNode) {
			// no enabled node exists in current executed expansion activation group
			if (currentExpansionActivationGroup.index < expansionRegionActivation.activationGroups.size()) {
				// terminate expansion activation group
				currentExpansionActivationGroup.terminateAll();
				// further expansion activation groups have to be executed
				ExpansionActivationGroup nextExpansionActivationGroup = determineNextExpansionActivationGroup(currentExpansionActivationGroup);				
				expansionRegionActivation.runGroup(nextExpansionActivationGroup);
			} else {
				// execution of expansion region is finished
				handleEndOfExecution();
			}
		}
	}

	private ExpansionActivationGroup getCurrentExpansionActivationGroup() {
		// determine which expansion region activation group has running node activations
		for(ExpansionActivationGroup expansionActivationGroup : expansionRegionActivation.activationGroups) {
			for(ActivityNodeActivation nodeActivation : expansionActivationGroup.nodeActivations) {
				if(nodeActivation.running) {
					return expansionActivationGroup;
				}
			}
		}
		return null;
	}
	/**
	 * Checks if the given expansion activation group has enabled nodes.
	 * 
	 * @param expansionActivationGroup
	 * @return true if enabled nodes exist
	 */
	private boolean hasExpansionActivationGroupEnabledNodes(ExpansionActivationGroup expansionActivationGroup) {
		boolean groupHasEnabledNode = false;

		// Check if expansion activation group contains enabled node
		for (ActivityNodeActivation nodeActivation : expansionActivationGroup.nodeActivations) {
			groupHasEnabledNode = activityExecutionStatus.isNodeEnabled(nodeActivation.node);
			if (groupHasEnabledNode) {
				return true;
			}
		}

		// Check if an activity called by an call action contained in the
		// activation group is still executing
		List<ActivityExecutionStatus> directCalledExecutionStatuses = activityExecutionStatus.getDirectCalledExecutionStatuses();
		for (ActivityExecutionStatus calledExecutionStatus : directCalledExecutionStatuses) {
			ActivityNodeExecutionStatus callerNodeExecutionStatus = calledExecutionStatus.getActivityCallerNoderExecutionStatus();
			if (expansionActivationGroup.nodeActivations.contains(callerNodeExecutionStatus.getActivityNodeActivation())) {
				// Checks if activity was called by a call action contained in
				// the activation group
				if (calledExecutionStatus.hasEnabledNodesIncludingCallees()) {
					// Checks if the activity is still under execution
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Determines the expansion activation group of an expansion region to be
	 * executed next according to its index.
	 * 
	 * @param expansionActivationGroup
	 * @return
	 */
	private ExpansionActivationGroup determineNextExpansionActivationGroup(ExpansionActivationGroup expansionActivationGroup) {
		ExpansionRegionActivation expansionRegionActiviaton = expansionActivationGroup.regionActivation;

		ExpansionActivationGroup nextExpansionActivationGroup = null;
		for (ExpansionActivationGroup group : expansionRegionActiviaton.activationGroups) {
			if (group.index == expansionActivationGroup.index + 1) {
				nextExpansionActivationGroup = group;
			}
		}
		return nextExpansionActivationGroup;
	}	

	/**
	 * Finishes the execution of an expansion region:
	 * <ol>
	 * <li>provide output of expansion activation groups</li>
	 * <li>terminate expansion activation groups</li>
	 * <li>provide output of expansion region</li>
	 * <li>send offers from expansion region</li>
	 * </ol>
	 */
	@Override
	public void handleEndOfExecution() {
		// provide expansion activation group output and terminate activation groups
		for (ExpansionActivationGroup activationGroup : expansionRegionActivation.activationGroups) {
			// START code from ExpansionRegionActivation.runGroup(ExpansionActivationGroup)
			OutputPinActivationList groupOutputs = activationGroup.groupOutputs;
			for (int i = 0; i < groupOutputs.size(); i++) {
				OutputPinActivation groupOutput = groupOutputs.getValue(i);
				groupOutput.fire(groupOutput.takeOfferedTokens());
			}
			activationGroup.terminateAll();
			// END code from ExpansionRegionActivation.runGroup(ExpansionActivationGroup)
		}

		// provide expansion region output
		ExpansionActivationGroupList activationGroups = expansionRegionActivation.activationGroups;
		ExpansionRegion region = (ExpansionRegion) expansionRegionActivation.node;
		ExpansionNodeList outputElements = region.outputElement;
		// START code from ExpansionRegionActivation.doStructuredActivity()
		for (int i = 0; i < activationGroups.size(); i++) {
			ExpansionActivationGroup activationGroup = activationGroups
					.getValue(i);
			OutputPinActivationList groupOutputs = activationGroup.groupOutputs;
			for (int j = 0; j < groupOutputs.size(); j++) {
				OutputPinActivation groupOutput = groupOutputs.getValue(j);
				ExpansionNode outputElement = outputElements.getValue(j);
				expansionRegionActivation.getExpansionNodeActivation(outputElement).addTokens(groupOutput.takeTokens());
			}
		}
		// END code from ExpansionRegionActivation.doStructuredActivity()

		// remove expansion activation groups
		expansionRegionActivation.activationGroups.clear();

		// send offers
		expansionRegionActivation.sendOffers();
				
		super.handleEndOfExecution();
	}

}
