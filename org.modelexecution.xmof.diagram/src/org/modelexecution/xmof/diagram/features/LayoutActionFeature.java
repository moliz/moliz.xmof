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
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;

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
		ContainerShape actionShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm actionRectangle = actionShape.getGraphicsAlgorithm();

		Action action = getAction(context);
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				action, getDiagram(), actionRectangle.getX(),
				actionRectangle.getY());

		boolean anythingChanged = ensureMinHeightAndWidth(actionRectangle,
				calculator);

		IGaService gaService = Graphiti.getGaService();
		int actionRectangleWidth = gaService.calculateSize(actionRectangle)
				.getWidth();
		int actionTextWidth = calculator
				.getActionTextWidth(actionRectangleWidth);
		Iterator<Shape> iter = actionShape.getChildren().iterator();
		while (iter.hasNext()) {
			Shape shape = iter.next();
			GraphicsAlgorithm gaText = shape.getGraphicsAlgorithm();
			IDimension size = gaService.calculateSize(gaText);
			if (gaText instanceof Text) {
				Text text = (Text) gaText;
				if (actionRectangleWidth != size.getWidth()) {
					gaService.setWidth(text, actionTextWidth);
					anythingChanged = true;
				}
				if (action.getName().equals(text.getValue())) {
					int textHeight = actionRectangle.getHeight();
					if (text.getHeight() != textHeight) {
						gaService.setHeight(text,
								calculator.getActionNameTextHeight(textHeight));
						anythingChanged = true;
					}
				}
			}
		}

		if (anythingChanged) {
			setUpPins(action, calculator, actionRectangleWidth);
		}

		return anythingChanged;
	}

	private boolean ensureMinHeightAndWidth(GraphicsAlgorithm actionRectangle,
			ActionDimensionCalculator calculator) {
		boolean anythingChanged = false;
		if (actionRectangle.getHeight() < calculator.getActionRectangleHeight()) {
			actionRectangle.setHeight(calculator.getActionRectangleHeight());
			anythingChanged = true;
		}

		if (actionRectangle.getWidth() < calculator
				.getActionRectangleMinWidth()) {
			actionRectangle.setWidth(calculator.getActionRectangleMinWidth());
			anythingChanged = true;
		}
		return anythingChanged;
	}

	private void setUpPins(Action action, ActionDimensionCalculator calculator,
			int actionRectangleWidth) {
		for (int pinNumber = 1; pinNumber <= action.getOutput().size(); pinNumber++) {
			OutputPin pin = action.getOutput().get(pinNumber - 1);
			PictogramElement pinShape = getPinShape(pin);
			GraphicsAlgorithm pinArea = pinShape.getGraphicsAlgorithm();
			Graphiti.getGaService().setLocationAndSize(pinArea,
					calculator.getOutputPinAreaX(actionRectangleWidth),
					calculator.getOutputPinAreaY(pinNumber),
					calculator.getOutputPinAreaWidth(),
					calculator.getPinAreaHeight());
		}

		for (int pinNumber = 1; pinNumber <= action.getInput().size(); pinNumber++) {
			InputPin pin = action.getInput().get(pinNumber - 1);
			PictogramElement pinShape = getPinShape(pin);
			GraphicsAlgorithm pinArea = pinShape.getGraphicsAlgorithm();
			Graphiti.getGaService().setLocationAndSize(pinArea,
					calculator.getInputPinAreaX(),
					calculator.getInputPinAreaY(pinNumber),
					calculator.getInputPinAreaWidth(),
					calculator.getPinAreaHeight());
		}
	}

	private PictogramElement getPinShape(Pin pin) {
		return getFeatureProvider().getPictogramElementForBusinessObject(pin);
	}

}
