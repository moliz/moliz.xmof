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

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;

public class LayoutActionFeature extends AbstractLayoutFeature {

	public LayoutActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.size() == 1
				&& businessObjects.get(0) instanceof Action;
	}

	private Action getAction(ILayoutContext context) {
		return (Action) context.getPictogramElement().getLink()
				.getBusinessObjects().get(0);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
		ContainerShape containerShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		GraphicsAlgorithm actionRectangle = containerGa
				.getGraphicsAlgorithmChildren().get(0);

		Action action = getAction(context);
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				action, getDiagram());

		int overallHeight = calculator.getOverallHeight();
		if (containerGa.getHeight() < overallHeight) {
			containerGa.setHeight(overallHeight);
			anythingChanged = true;
		}

		int rectangleHeight = containerGa.getHeight();
		if (actionRectangle.getHeight() != rectangleHeight) {
			actionRectangle.setHeight(rectangleHeight);
			anythingChanged = true;
		}

		if (containerGa.getWidth() < calculator.getOverallMinWidth()) {
			containerGa.setWidth(calculator.getOverallMinWidth());
			anythingChanged = true;
		}

		int overallWidth = containerGa.getWidth();
		int rectangleWidth = overallWidth - calculator.getOutputPinAreaWidth()
				- calculator.getInputPinAreaWidth();
		if (actionRectangle.getWidth() != rectangleWidth) {
			actionRectangle.setWidth(rectangleWidth);
			anythingChanged = true;
		}

		Iterator<Shape> iter = containerShape.getChildren().iterator();
		while (iter.hasNext()) {
			Shape shape = iter.next();
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = gaService.calculateSize(graphicsAlgorithm);
			if (graphicsAlgorithm instanceof Text) {
				Text text = (Text) graphicsAlgorithm;
				if (rectangleWidth != size.getWidth()) {
					gaService.setWidth(text, rectangleWidth);
					anythingChanged = true;
				}
				if (action.getName().equals(text.getValue())) {
					gaService.setHeight(text, rectangleHeight - 5);
					anythingChanged = true;
				}
			}
		}

		for (Anchor anchor : containerShape.getAnchors()) {
			if (isOutputPinElement(anchor)) {
				anchor.getGraphicsAlgorithm().setX(
						calculator.getOutputPinAreaX(overallWidth - 5));
			}
		}

		return anythingChanged;
	}

	private boolean isOutputPinElement(Anchor anchor) {
		EList<EObject> businessObjects = anchor.getLink().getBusinessObjects();
		return businessObjects.size() > 0
				&& businessObjects.get(0) instanceof OutputPin;
	}

}
