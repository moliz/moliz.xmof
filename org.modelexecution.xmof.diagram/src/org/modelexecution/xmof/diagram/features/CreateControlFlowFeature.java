/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;

public class CreateControlFlowFeature extends AbstractCreateConnectionFeature {

	public CreateControlFlowFeature(IFeatureProvider fp) {
		super(fp, "Control Flow", "Create Control Flow");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		ActivityNode source = getActivityNode(context.getSourceAnchor());
		ActivityNode target = getActivityNode(context.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	private ActivityNode getActivityNode(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof ActivityNode) {
				return (ActivityNode) object;
			}
		}
		return null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		ActivityNode source = getActivityNode(context.getSourceAnchor());
		ActivityNode target = getActivityNode(context.getTargetAnchor());
		if (source != null && target != null) {
			newConnection = createControlFlow(context, source, target);
		}
		return newConnection;
	}

	private Connection createControlFlow(ICreateConnectionContext context,
			ActivityNode source, ActivityNode target) {
		ControlFlow controlFlow = createControlFlow(source, target);
		AddConnectionContext addContext = new AddConnectionContext(
				context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(controlFlow);
		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	private ControlFlow createControlFlow(ActivityNode source,
			ActivityNode target) {
		ControlFlow controlFlow = IntermediateActivitiesFactory.eINSTANCE
				.createControlFlow();
		controlFlow.setSource(source);
		controlFlow.setTarget(target);
		source.getOutgoing().add(controlFlow);
		target.getIncoming().add(controlFlow);
		if(source.getActivity() != null) { // source node resides in activity
			source.getActivity().getEdge().add(controlFlow);
		} else if(source.getInStructuredNode() != null) { // source node resides in structured node
			source.getInStructuredNode().getEdge().add(controlFlow);
		}
		return controlFlow;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		return isAction(sourceAnchor) || isInitialNode(sourceAnchor) || isMergeNode(sourceAnchor) || isDecisionNode(sourceAnchor) || isForkNode(sourceAnchor) || isJoinNode(sourceAnchor);
	}

	private boolean isJoinNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof JoinNode;
		} else {
			return false;
		}
	}

	private boolean isForkNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof ForkNode;
		} else {
			return false;
		}
	}

	private boolean isDecisionNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof DecisionNode;
		} else {
			return false;
		}
	}

	private boolean isMergeNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof MergeNode;
		} else {
			return false;
		}
	}

	private boolean isInitialNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof InitialNode;
		} else {
			return false;
		}
	}

	private boolean isAction(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object != null && object instanceof Action;
		} else {
			return false;
		}
	}

}
