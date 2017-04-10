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
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectNode;

public class CreateControlFlowFeature extends CreateActivityEdgeFeature {

	public CreateControlFlowFeature(IFeatureProvider fp) {
		super(fp, "Control Flow");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		if (!super.canCreate(context)) {
			return false;
		}

		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		ActivityNode source = getActivityNode(sourceAnchor);
		ActivityNode target = getActivityNode(targetAnchor);
		
		if (source != null && target != null && source != target) {
			if (isActivityNode(sourceAnchor) && !isObjectNode(sourceAnchor)
					&& isActivityNode(targetAnchor) && !isObjectNode(targetAnchor)) {
				return true;
			}
		}

		return false;
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
		if (source.getActivity() != null) { // source node resides in activity
			source.getActivity().getEdge().add(controlFlow);
		} else if (source.getInStructuredNode() != null) { // source node
															// resides in
															// structured node
			source.getInStructuredNode().getEdge().add(controlFlow);
		}
		return controlFlow;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		return isActivityNode(sourceAnchor) && !isObjectNode(sourceAnchor);
	}

	private boolean isActivityNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof ActivityNode;
		} else {
			return false;
		}
	}

	private boolean isObjectNode(Anchor anchor) {
		if (anchor != null && anchor.getParent() != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			return object instanceof ObjectNode;
		} else {
			return false;
		}
	}

}
