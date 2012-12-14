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
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_CORNER_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_CORNER_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.STRUCTURED_ACTIVITY_NODE_DEFAULT_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.STRUCTURED_ACTIVITY_NODE_DEFAULT_WIDTH;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;

public class AddStructuredActivityNodeFeature extends AbstractAddFeature {

	public AddStructuredActivityNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof StructuredActivityNode
				&& getTargetActivity(context) != null;
	}
	
	private Activity getTargetActivity(IAddContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof Activity) {
				return (Activity) object;
			}
		}
		return null;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();

		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetContainer, true);
		createNodeRectangle(context, containerShape);

		getPeCreateService().createChopboxAnchor(containerShape);
		layoutPictogramElement(containerShape);

		return containerShape;
	}

	private StructuredActivityNode getAddedNode(IAddContext context) {
		StructuredActivityNode addedAction = (StructuredActivityNode) context.getNewObject();
		return addedAction;
	}

	private void createNodeRectangle(IAddContext context,
			ContainerShape containerShape) {

		StructuredActivityNode addedNode = getAddedNode(context);

		RoundedRectangle roundedRectangle = getGaService()
				.createRoundedRectangle(containerShape, ACTION_CORNER_WIDTH,
						ACTION_CORNER_HEIGHT);
		roundedRectangle.setForeground(manageColor(FOREGROUND));
		roundedRectangle.setBackground(manageColor(BACKGROUND));
		roundedRectangle.setLineWidth(NODE_LINE_WIDTH);
		roundedRectangle.setLineStyle(LineStyle.DASH);
		getGaService().setLocationAndSize(roundedRectangle, context.getX(),
				context.getY(), STRUCTURED_ACTIVITY_NODE_DEFAULT_WIDTH,
				STRUCTURED_ACTIVITY_NODE_DEFAULT_HEIGHT);

		link(containerShape, addedNode);
	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}

}
