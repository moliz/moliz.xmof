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
import static org.modelexecution.xmof.diagram.DiagramColors.TEXT_FOREGROUND;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_CORNER_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_CORNER_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_WIDTH;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.diagram.DiagramFonts;
import org.modelexecution.xmof.diagram.PropertyUtil;

public class AddActionFeature extends AbstractAddFeature {

	public AddActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof Action
				&& (getTargetActivity(context) != null || getTargetExpansionRegion(context) != null);
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
	
	private ExpansionRegion getTargetExpansionRegion(IAddContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof ExpansionRegion) {
				return (ExpansionRegion) object;
			}
		}
		return null;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Action addedAction = getAddedAction(context);
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();

		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetContainer, true);	
		if(addedAction instanceof CallBehaviorAction) {
			PropertyUtil.setCallBehaviorActionBehavior(containerShape, "");
		} else if(addedAction instanceof CallOperationAction) {
			PropertyUtil.setCallOperationActionOperation(containerShape, "");
		}
		
		createActionRectangle(context, containerShape);
		addActionLabels(addedAction, containerShape);
		addOutputPins(context);
		addInputPins(context);

		getPeCreateService().createChopboxAnchor(containerShape);
		layoutPictogramElement(containerShape);

		return containerShape;
	}

	private Action getAddedAction(IAddContext context) {
		Action addedAction = (Action) context.getNewObject();
		return addedAction;
	}

	private void createActionRectangle(IAddContext context,
			ContainerShape containerShape) {

		Action addedAction = getAddedAction(context);
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				addedAction, getDiagram());

		RoundedRectangle roundedRectangle = getGaService()
				.createRoundedRectangle(containerShape, ACTION_CORNER_WIDTH,
						ACTION_CORNER_HEIGHT);
		roundedRectangle.setForeground(manageColor(FOREGROUND));
		roundedRectangle.setBackground(manageColor(BACKGROUND));
		roundedRectangle.setLineWidth(NODE_LINE_WIDTH);
		getGaService().setLocationAndSize(roundedRectangle, context.getX(),
				context.getY(), calculator.getActionRectangleWidth(),
				calculator.getActionRectangleHeight());

		link(containerShape, addedAction);
	}

	private void addActionLabels(Action addedAction,
			ContainerShape containerShape) {
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				addedAction, getDiagram(), getActionTypeName(addedAction));

		Shape actionTypeTextShape = getPeCreateService().createShape(
				containerShape, false);
		PropertyUtil.setActionTypeTextShape(actionTypeTextShape);
		
		Text actionTypeText = getGaService().createText(actionTypeTextShape,
				getActionTypeName(addedAction));
		actionTypeText.setForeground(manageColor(TEXT_FOREGROUND));
		actionTypeText.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
		actionTypeText.setVerticalAlignment(Orientation.ALIGNMENT_MIDDLE);
		actionTypeText.setFont(getActionTypeNameFont());
		getGaService().setLocationAndSize(actionTypeText, ACTION_LABEL_MARGIN,
				0, calculator.getActionTextWidth(),
				calculator.getActionTypeNameTextHeight());

		Shape actionNameShape = getPeCreateService().createShape(
				containerShape, false);
		PropertyUtil.setActionNameTextShape(actionNameShape);
		
		Text actionNameText = getGaService().createText(actionNameShape,
				addedAction.getName());
		actionNameText.setForeground(manageColor(TEXT_FOREGROUND));
		actionNameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		actionNameText.setVerticalAlignment(Orientation.ALIGNMENT_MIDDLE);
		actionNameText.setFont(getActionNameFont());
		getGaService().setLocationAndSize(actionNameText, ACTION_LABEL_MARGIN,
				0, calculator.getActionTextWidth(),
				calculator.getActionNameTextHeight());

		link(actionTypeTextShape, addedAction);
		link(actionNameShape, addedAction);
	}

	private Font getActionNameFont() {
		return DiagramFonts.getActionNameFont(getDiagram());
	}

	private Font getActionTypeNameFont() {
		return DiagramFonts.getActionTypeNameFont(getDiagram());
	}

	private String getActionTypeName(Action addedAction) {
		return addedAction.eClass().getName();
	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}

	private void addOutputPins(IAddContext context) {
		Action addedAction = getAddedAction(context);
		ContainerShape targetShape = (ContainerShape) context
				.getTargetContainer();
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				addedAction, getDiagram(), context.getX(), context.getY());

		int pinNumber = 1;
		List<OutputPin> outputPins = new ArrayList<OutputPin>(addedAction.getOutput());
		for (OutputPin outputPin : outputPins) {
			ContainerShape pinShape = getPeCreateService()
					.createContainerShape(targetShape, true);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(pinShape);
			getGaService().setLocationAndSize(
					invisibleRectangle,
					calculator.getOutputPinAreaX(calculator
							.getActionRectangleWidth()),
					calculator.getOutputPinAreaY(pinNumber),
					calculator.getOutputPinAreaWidth(),
					calculator.getPinAreaHeight());

			Text text = getGaService().createText(invisibleRectangle,
					outputPin.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(text, PIN_LABEL_MARGIN, 0,
					calculator.getOutputPinNameWidth(), PIN_LABEL_HEIGHT);

			Shape pinRectangleShape = getPeCreateService().createShape(
					pinShape, true);
			Rectangle pinRectangle = getGaService().createRectangle(
					pinRectangleShape);
			pinRectangle.setForeground(manageColor(FOREGROUND));
			pinRectangle.setBackground(manageColor(BACKGROUND));
			pinRectangle.setLineWidth(NODE_LINE_WIDTH);
			getGaService().setLocationAndSize(pinRectangle, 0, 0 + PIN_HEIGHT,
					PIN_WIDTH, PIN_HEIGHT);

			getPeCreateService().createChopboxAnchor(pinRectangleShape);

			link(pinShape, outputPin);
			link(pinRectangleShape, outputPin);
			pinNumber++;
		}
	}

	private void addInputPins(IAddContext context) {
		Action addedAction = getAddedAction(context);
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();
		ActionDimensionCalculator calculator = new ActionDimensionCalculator(
				addedAction, getDiagram(), context.getX(), context.getY());

		int pinNumber = 1;

		List<InputPin> inputPins = new ArrayList<>(addedAction.getInput());
		for (InputPin inputPin : inputPins) {
			ContainerShape pinShape = getPeCreateService()
					.createContainerShape(targetContainer, true);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(pinShape);
			getGaService().setLocationAndSize(invisibleRectangle,
					calculator.getInputPinAreaX(),
					calculator.getInputPinAreaY(pinNumber),
					calculator.getInputPinAreaWidth(),
					calculator.getPinAreaHeight());

			Text text = getGaService().createText(invisibleRectangle,
					inputPin.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(text, -PIN_LABEL_MARGIN, 0,
					calculator.getInputPinNameWidth(), PIN_LABEL_HEIGHT);

			Shape pinRectangleShape = getPeCreateService().createShape(
					pinShape, true);
			Rectangle pinRectangle = getGaService().createRectangle(
					pinRectangleShape);
			pinRectangle.setForeground(manageColor(FOREGROUND));
			pinRectangle.setBackground(manageColor(BACKGROUND));
			pinRectangle.setLineWidth(NODE_LINE_WIDTH);
			getGaService().setLocationAndSize(pinRectangle,
					calculator.getInputPinNameWidth() - PIN_WIDTH, PIN_HEIGHT,
					PIN_WIDTH, PIN_HEIGHT);

			getPeCreateService().createChopboxAnchor(pinRectangleShape);

			link(pinShape, inputPin);
			link(pinRectangleShape, inputPin);
			pinNumber++;
		}
	}

}
