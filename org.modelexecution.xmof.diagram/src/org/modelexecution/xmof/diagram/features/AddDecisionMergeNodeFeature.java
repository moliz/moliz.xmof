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

import static org.modelexecution.xmof.diagram.DiagramColors.BACKGROUND;
import static org.modelexecution.xmof.diagram.DiagramColors.FOREGROUND;
import static org.modelexecution.xmof.diagram.DiagramDimensions.MERGE_DECISION_NODE_POINTS;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;

public class AddDecisionMergeNodeFeature extends AddControlNodeFeature {

	public AddDecisionMergeNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && ((context.getNewObject() instanceof MergeNode) || (context
				.getNewObject() instanceof DecisionNode));
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Object node = context.getNewObject();
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();
		Shape nodeShape = getPeCreateService().createShape(targetContainer,
				true);

		Polygon nodePolygon = getGaService().createPolygon(nodeShape,
				MERGE_DECISION_NODE_POINTS);
		nodePolygon.setForeground(manageColor(FOREGROUND));
		nodePolygon.setBackground(manageColor(BACKGROUND));
		nodePolygon.setLineWidth(NODE_LINE_WIDTH);

		getGaService().setLocation(nodePolygon, context.getX(), context.getY());

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
