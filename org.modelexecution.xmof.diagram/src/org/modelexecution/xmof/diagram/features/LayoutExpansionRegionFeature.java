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

import static org.modelexecution.xmof.diagram.DiagramDimensions.EXPANSION_NODE_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.EXPANSION_NODE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_OFFSET;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class LayoutExpansionRegionFeature extends AbstractLayoutFeature {

	public LayoutExpansionRegionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.size() == 1
				&& businessObjects.get(0) instanceof ExpansionRegion;
	}

	private ExpansionRegion getExpansionRegion(ILayoutContext context) {
		return (ExpansionRegion) context.getPictogramElement().getLink()
				.getBusinessObjects().get(0);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape actionShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm regionRectangle = actionShape.getGraphicsAlgorithm();

		ExpansionRegion expansionRegion = getExpansionRegion(context);

		boolean anythingChanged = ensureMinHeight(regionRectangle,
				expansionRegion);

		setUpExpansionNodes(expansionRegion);

		return anythingChanged;
	}

	private boolean ensureMinHeight(GraphicsAlgorithm expansionRegionRectangle,
			ExpansionRegion region) {
		boolean anythingChanged = false;

		int inputNodeNumber = region.getInputElement().size();
		int outputNodeNumber = region.getOutputElement().size();

		int minHeight = Math.max(inputNodeNumber, outputNodeNumber)
				* (EXPANSION_NODE_HEIGHT + PIN_OFFSET) + PIN_OFFSET;

		if (expansionRegionRectangle.getHeight() < minHeight) {
			expansionRegionRectangle.setHeight(minHeight);
			anythingChanged = true;
		}

		return anythingChanged;
	}

	private void setUpExpansionNodes(ExpansionRegion expansionRegion) {
		PictogramElement regionContainer = getExpansionRegionShape(expansionRegion);

		for (ExpansionNode expansionNode : expansionRegion.getInputElement()) {
			int nodeNumber = expansionRegion.getInputElement().indexOf(
					expansionNode);
			int x = regionContainer.getGraphicsAlgorithm().getX()
					- (EXPANSION_NODE_WIDTH / 2);
			int y = regionContainer.getGraphicsAlgorithm().getY()
					+ (PIN_OFFSET + (EXPANSION_NODE_HEIGHT + PIN_OFFSET)
							* (nodeNumber));
			PictogramElement expansionNodeShape = getExpansionNodeShape(expansionNode);
			if(expansionNodeShape != null) {
				GraphicsAlgorithm expansionNodeRectangle = expansionNodeShape
						.getGraphicsAlgorithm();
				Graphiti.getGaService().setLocation(expansionNodeRectangle, x, y);
			}
		}

		for (ExpansionNode expansionNode : expansionRegion.getOutputElement()) {
			int nodeNumber = expansionRegion.getOutputElement().indexOf(
					expansionNode);
			int x = regionContainer.getGraphicsAlgorithm().getX()
					+ regionContainer.getGraphicsAlgorithm().getWidth()
					- (EXPANSION_NODE_WIDTH / 2);
			int y = regionContainer.getGraphicsAlgorithm().getY()
					+ (PIN_OFFSET + (EXPANSION_NODE_HEIGHT + PIN_OFFSET)
							* (nodeNumber));
			PictogramElement expansionNodeShape = getExpansionNodeShape(expansionNode);
			if(expansionNodeShape != null) {
				GraphicsAlgorithm expansionNodeRectangle = expansionNodeShape
						.getGraphicsAlgorithm();
				Graphiti.getGaService().setLocation(expansionNodeRectangle, x, y);
			}
		}
	}

	private PictogramElement getExpansionNodeShape(ExpansionNode expansionNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(
				expansionNode);
	}

	private PictogramElement getExpansionRegionShape(
			ExpansionRegion expansionRegion) {
		return getFeatureProvider().getPictogramElementForBusinessObject(
				expansionRegion);
	}

}
