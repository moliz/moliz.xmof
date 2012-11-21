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
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;

public class CreateControlFlowFeature extends AbstractCreateConnectionFeature {

	public CreateControlFlowFeature(IFeatureProvider fp) {
		super(fp, "Control Flow", "Create Control Flow");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Action source = getAction(context.getSourceAnchor());
		Action target = getAction(context.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	private Action getAction(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof Action) {
				return (Action) object;
			}
		}
		return null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		Action source = getAction(context.getSourceAnchor());
		Action target = getAction(context.getTargetAnchor());
		if (source != null && target != null) {
			newConnection = createControlFlow(context, source, target);
		}
		return newConnection;
	}

	private Connection createControlFlow(ICreateConnectionContext context,
			Action source, Action target) {
		ControlFlow controlFlow = createControlFlow(source, target);
		AddConnectionContext addContext = new AddConnectionContext(
				context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(controlFlow);
		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	private ControlFlow createControlFlow(Action source, Action target) {
		ControlFlow controlFlow = IntermediateActivitiesFactory.eINSTANCE
				.createControlFlow();
		controlFlow.setSource(source);
		controlFlow.setTarget(target);
		source.getOutgoing().add(controlFlow);
		target.getIncoming().add(controlFlow);
		// TODO add object flow to activity containing the action of the pins
		getDiagram().eResource().getContents().add(controlFlow);
		return controlFlow;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return getAction(context.getSourceAnchor()) != null;
	}

}
