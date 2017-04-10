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

import static org.modelexecution.xmof.diagram.DiagramColors.FOREGROUND;
import static org.modelexecution.xmof.diagram.DiagramDimensions.FORK_JOIN_NODE_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.FORK_JOIN_NODE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;

public class AddJoinForkNodeFeature extends AddControlNodeFeature {

	public AddJoinForkNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && (context.getNewObject() instanceof JoinNode)
				|| (context.getNewObject() instanceof ForkNode);
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Object node = context.getNewObject();
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();
		Shape nodeShape = getPeCreateService().createShape(targetContainer,
				true);

		Rectangle rectangle = getGaService().createRectangle(nodeShape);
		rectangle.setHeight(FORK_JOIN_NODE_HEIGHT);
		rectangle.setWidth(FORK_JOIN_NODE_WIDTH);
		rectangle.setForeground(manageColor(FOREGROUND));
		rectangle.setBackground(manageColor(FOREGROUND));
		rectangle.setLineWidth(NODE_LINE_WIDTH);
		rectangle.setFilled(true);

		getGaService().setLocation(rectangle, context.getX(), context.getY());

		getPeCreateService().createChopboxAnchor(nodeShape);

		link(nodeShape, node);
		return nodeShape;
	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}
}
