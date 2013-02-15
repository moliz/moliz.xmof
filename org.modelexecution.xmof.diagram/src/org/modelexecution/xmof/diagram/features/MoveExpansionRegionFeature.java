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
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class MoveExpansionRegionFeature extends DefaultMoveShapeFeature {

	public MoveExpansionRegionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void moveShape(IMoveShapeContext context) {
		super.moveShape(context);
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (bo instanceof ExpansionRegion) {
			ExpansionRegion expansionRegion = (ExpansionRegion) bo;
			relocateExpansionNodes(expansionRegion, context);
			relocatePins(expansionRegion, context);
		}
	}

	private void relocatePins(ExpansionRegion expansionRegion,
			IMoveShapeContext context) {
		for(InputPin pin : expansionRegion.getStructuredNodeInput()) {
			PictogramElement pinShape = getPinShape(pin);
			if(pinShape != null) {
				GraphicsAlgorithm pinRectangle = pinShape.getGraphicsAlgorithm();
				pinRectangle.setX(pinRectangle.getX() + context.getDeltaX());
				pinRectangle.setY(pinRectangle.getY() + context.getDeltaY());
			}
		}
		
	}

	private void relocateExpansionNodes(ExpansionRegion expansionRegion, IMoveShapeContext context) {
		for (ExpansionNode expansionNode : expansionRegion.getInputElement()) {	
			PictogramElement expansionNodeShape = getExpansionNodeShape(expansionNode);
			if(expansionNodeShape != null) {
				GraphicsAlgorithm expansionNodeRectangle = expansionNodeShape
						.getGraphicsAlgorithm();
				expansionNodeRectangle.setX(expansionNodeRectangle.getX() + context.getDeltaX());
				expansionNodeRectangle.setY(expansionNodeRectangle.getY() + context.getDeltaY());
			}
		}
		
		for (ExpansionNode expansionNode : expansionRegion.getOutputElement()) {
			PictogramElement expansionNodeShape = getExpansionNodeShape(expansionNode);
			if(expansionNodeShape != null) {
				GraphicsAlgorithm expansionNodeRectangle = expansionNodeShape
						.getGraphicsAlgorithm();
				expansionNodeRectangle.setX(expansionNodeRectangle.getX() + context.getDeltaX());
				expansionNodeRectangle.setY(expansionNodeRectangle.getY() + context.getDeltaY());
			}
		}		
	}

	private PictogramElement getExpansionNodeShape(ExpansionNode expansionNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(
				expansionNode);
	}
	
	private PictogramElement getPinShape(InputPin pin) {
		return getFeatureProvider().getPictogramElementForBusinessObject(pin);
	}
	
}
