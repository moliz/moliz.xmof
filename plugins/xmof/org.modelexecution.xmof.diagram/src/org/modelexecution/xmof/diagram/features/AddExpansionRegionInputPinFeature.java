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
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_OFFSET;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_WIDTH;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class AddExpansionRegionInputPinFeature extends AbstractAddFeature {

	public AddExpansionRegionInputPinFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof InputPin
				&& getTargetExpansionRegion(context) != null;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		InputPin addedInputPin = getInputPin(context);
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);
		
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer().getContainer(); // activity
		ContainerShape regionContainer = (ContainerShape) context
				.getTargetContainer(); // expansion region	
		
		int x = regionContainer.getGraphicsAlgorithm().getX();
		int y = regionContainer.getGraphicsAlgorithm().getY() - PIN_HEIGHT;
				
		x += PIN_OFFSET;
		x += (PIN_WIDTH + PIN_OFFSET) * (expansionRegion.getStructuredNodeInput().size() - 1);
		
		int regionMinWidht = PIN_OFFSET + (PIN_WIDTH + PIN_OFFSET) * expansionRegion.getStructuredNodeInput().size();
		if(regionContainer.getGraphicsAlgorithm().getWidth() < regionMinWidht) {
			regionContainer.getGraphicsAlgorithm().setWidth(regionMinWidht);
		}
				
		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetContainer, true);
		
		Rectangle rectangle = getGaService().createRectangle(
				containerShape);
		rectangle.setForeground(manageColor(FOREGROUND));
		rectangle.setBackground(manageColor(BACKGROUND));
		rectangle.setLineWidth(NODE_LINE_WIDTH);
		getGaService().setLocationAndSize(rectangle, x, y,
				PIN_WIDTH, PIN_HEIGHT);
		
		getPeCreateService().createChopboxAnchor(containerShape);

		link(containerShape, addedInputPin);

		return containerShape;
	}

	private InputPin getInputPin(IAddContext context) {
		InputPin addedInputPin = (InputPin) context
				.getNewObject();
		return addedInputPin;
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
