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

import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroup;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ForkNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;

public aspect TokenAspect {

	private pointcut valueAddedToLocusBecauseOfCopy() : call (void Locus.add(ExtensionalValue)) && withincode(Value ExtensionalValue.copy());

	/**
	 * Prevent addition of copied value to locus
	 */
	void around() : valueAddedToLocusBecauseOfCopy() {
	}

	private pointcut tokenSendingViaEdge(ActivityEdgeInstance edgeInstance,	TokenList tokens) : call (void ActivityEdgeInstance.sendOffer(TokenList)) && target(edgeInstance) && args(tokens);

	/**
	 * Store sent tokens 
	 * @param edgeInstance
	 * @param tokens
	 */
	before(ActivityEdgeInstance edgeInstance, TokenList tokens) : tokenSendingViaEdge(edgeInstance, tokens) {
		// store token sendings via edges for trace
		ActivityNodeActivation sourceNodeActivation = edgeInstance.source;
		  
		if(sourceNodeActivation.group == null) { 
			if(sourceNodeActivation instanceof ForkNodeActivation && sourceNodeActivation.node == null) { // anonymous fork node 
				 sourceNodeActivation = sourceNodeActivation.incomingEdges.get(0).source; 
			} else if(sourceNodeActivation instanceof OutputPinActivation &&  sourceNodeActivation.outgoingEdges.get(0).target.group instanceof ExpansionActivationGroup) { // anonymous output pin activation for expansion region
				sourceNodeActivation = ((ExpansionActivationGroup)sourceNodeActivation.outgoingEdges.get(0).target.group).regionActivation; 
			} 
		}
		  
		ActivityExecution currentActivityExecution = sourceNodeActivation.getActivityExecution(); 
		ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(currentActivityExecution);
		 
		if (edgeInstance.group == null) { // anonymous fork node was inserted
			if (edgeInstance.source instanceof ForkNodeActivation) { 
				edgeInstance = edgeInstance.source.incomingEdges.get(0); 
			} else if (edgeInstance.target instanceof ForkNodeActivation) { 
				edgeInstance = edgeInstance.target.outgoingEdges.get(0); 
			} 
		}
		activityExecutionStatus.addTokenSending(sourceNodeActivation, tokens, edgeInstance.edge);
	}

	private pointcut tokenTransferring(Token tokenOriginal,	ActivityNodeActivation activation) : call (Token Token.transfer(ActivityNodeActivation)) && target(tokenOriginal) && args(activation);

	/**
	 * Create token copy map 
	 * @param tokenOriginal
	 */
	Token around(Token tokenOriginal, ActivityNodeActivation holder) : tokenTransferring(tokenOriginal, holder){
		// store token copies for trace
		Token tokenCopy = proceed(tokenOriginal, holder);

		if(holder.group == null) { 
			if(holder instanceof ForkNodeActivation && holder.node == null) { //anonymous fork node
				holder = holder.incomingEdges.get(0).source; 
			} else if(holder instanceof OutputPinActivation) { 
				if(holder.outgoingEdges.size() > 0)	{ 
					if(holder.outgoingEdges.get(0).target.node.inStructuredNode != null) { 
						holder = ((ExpansionActivationGroup)holder.outgoingEdges.get(0).target.group).regionActivation; 
					} 
				} else if(holder.incomingEdges.size() > 0) {
					if(holder.incomingEdges.get(0).source.node.inStructuredNode != null) { 
						holder = ((ExpansionActivationGroup)holder.incomingEdges.get(0).source.group).regionActivation; 
					} 
				} 
			} 
		}
		 
		if(holder != null && holder.group != null) { 
			ActivityExecution currentActivityExecution = holder.getActivityExecution();
			ActivityExecutionStatus activityExecutionStatus = ExecutionContext.getInstance().executionStatus.getActivityExecutionStatus(currentActivityExecution); 
			if(activityExecutionStatus != null) {
				activityExecutionStatus.addTokenCopy(tokenOriginal,	tokenCopy);
			}
		}

		return tokenCopy;
	}

}
