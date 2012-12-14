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
import static org.modelexecution.xmof.diagram.DiagramDimensions.EXPANSION_NODE_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.EXPANSION_NODE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_OFFSET;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class AddExpansionNodeFeature extends AbstractAddFeature {

	public AddExpansionNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof ExpansionNode
				&& getTargetExpansionRegion(context) != null;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ExpansionNode addedExpansionNode = getExpansionNode(context);
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);
		
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer().getContainer(); // activity
		ContainerShape regionContainer = (ContainerShape) context
				.getTargetContainer(); // expansion region	
		
		int x = regionContainer.getGraphicsAlgorithm().getX();
		int y = regionContainer.getGraphicsAlgorithm().getY();
		int nodeNumber = 0;
		
		if(expansionRegion.getInputElement().contains(addedExpansionNode)) { // expansion node is input
			x -= (EXPANSION_NODE_WIDTH/2);
			nodeNumber = expansionRegion.getInputElement().size();
		} else { // expansion node is output
			x += (regionContainer.getGraphicsAlgorithm().getWidth() - (EXPANSION_NODE_WIDTH/2));
			nodeNumber = expansionRegion.getOutputElement().size();
		}				
		
		y += (PIN_OFFSET + (EXPANSION_NODE_HEIGHT + PIN_OFFSET) * (nodeNumber-1));
		
		int regionMinHeight = PIN_OFFSET + (EXPANSION_NODE_HEIGHT + PIN_OFFSET) * nodeNumber;
		if(regionContainer.getGraphicsAlgorithm().getHeight() < regionMinHeight) {
			regionContainer.getGraphicsAlgorithm().setHeight(regionMinHeight);
		}
				
		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetContainer, true);
		
		Rectangle rectangle = getGaService().createRectangle(
				containerShape);
		rectangle.setForeground(manageColor(FOREGROUND));
		rectangle.setBackground(manageColor(BACKGROUND));
		rectangle.setLineWidth(NODE_LINE_WIDTH);
		getGaService().setLocationAndSize(rectangle, x, y,
				EXPANSION_NODE_WIDTH, EXPANSION_NODE_HEIGHT);

		Shape lineShape1 = getPeCreateService().createShape(
				containerShape, false);
		Polyline polyline1 = getGaService().createPolyline(
				lineShape1,
				new int[] { 0, (EXPANSION_NODE_HEIGHT / 3),
						EXPANSION_NODE_WIDTH,
						(EXPANSION_NODE_HEIGHT / 3) });
		polyline1.setLineWidth(NODE_LINE_WIDTH);
		polyline1.setForeground(manageColor(FOREGROUND));

		Shape lineShape2 = getPeCreateService().createShape(
				containerShape, false);
		Polyline polyline2 = getGaService().createPolyline(
				lineShape2,
				new int[] { 0, (EXPANSION_NODE_HEIGHT / 3) * 2,
						EXPANSION_NODE_WIDTH,
						(EXPANSION_NODE_HEIGHT / 3) * 2 });
		polyline2.setLineWidth(NODE_LINE_WIDTH);
		polyline2.setForeground(manageColor(FOREGROUND));
		
		getPeCreateService().createChopboxAnchor(containerShape);

		link(containerShape, addedExpansionNode);

		return containerShape;
	}

	protected ExpansionRegion getTargetExpansionRegion(IAddContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof ExpansionRegion) {
				return (ExpansionRegion) object;
			}
		}
		return null;
	}

	protected ExpansionNode getExpansionNode(IAddContext context) {
		ExpansionNode addedExpansionNode = (ExpansionNode) context
				.getNewObject();
		return addedExpansionNode;
	}

	protected IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	protected IGaService getGaService() {
		return Graphiti.getGaService();
	}

}
