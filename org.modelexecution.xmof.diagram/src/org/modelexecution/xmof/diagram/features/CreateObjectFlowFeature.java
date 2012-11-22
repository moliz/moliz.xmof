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
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;

public class CreateObjectFlowFeature extends AbstractCreateConnectionFeature {

	public CreateObjectFlowFeature(IFeatureProvider fp) {
		super(fp, "Object Flow", "Create Object Flow");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		OutputPin source = getOutputPin(context.getSourceAnchor());
		InputPin target = getInputPin(context.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	private OutputPin getOutputPin(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof OutputPin) {
				return (OutputPin) object;
			}
		}
		return null;
	}

	private InputPin getInputPin(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof InputPin) {
				return (InputPin) object;
			}
		}
		return null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		OutputPin source = getOutputPin(context.getSourceAnchor());
		InputPin target = getInputPin(context.getTargetAnchor());
		if (source != null && target != null) {
			newConnection = createObjectFlow(context, source, target);
		}
		return newConnection;
	}

	private Connection createObjectFlow(ICreateConnectionContext context,
			OutputPin source, InputPin target) {
		ObjectFlow objectFlow = createObjectFlow(source, target);
		AddConnectionContext addContext = new AddConnectionContext(
				context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(objectFlow);
		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	private ObjectFlow createObjectFlow(OutputPin source, InputPin target) {
		ObjectFlow objectFlow = IntermediateActivitiesFactory.eINSTANCE
				.createObjectFlow();
		objectFlow.setSource(source);
		objectFlow.setTarget(target);
		source.getOutgoing().add(objectFlow);
		target.getIncoming().add(objectFlow);
		source.getActivity().getEdge().add(objectFlow);
		return objectFlow;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		return getOutputPin(context.getSourceAnchor()) != null || isMergeNode(sourceAnchor) || isDecisionNode(sourceAnchor) || isForkNode(sourceAnchor) || isJoinNode(sourceAnchor);
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

}
