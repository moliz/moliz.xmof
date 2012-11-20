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

import static org.modelexecution.xmof.diagram.XMOFDiagramColors.BACKGROUND;
import static org.modelexecution.xmof.diagram.XMOFDiagramColors.FOREGROUND;
import static org.modelexecution.xmof.diagram.XMOFDiagramColors.TEXT_FOREGROUND;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_CORNER_HEIGHT;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_CORNER_WIDTH;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_VERTICAL_MARGIN;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_WIDTH;

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
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.diagram.XMOFDiagramDimensions;

public class AddActionFeature extends AbstractAddFeature {

	public AddActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof Action
				&& context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Action addedAction = getAddedAction(context);
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetDiagram, true);
		RoundedRectangle roundedRectangle = createActionRectangle(context,
				containerShape);
		addActionLabels(addedAction, containerShape);
		addOutputPins(addedAction, containerShape, roundedRectangle);
		addInputPins(addedAction, containerShape, roundedRectangle);

		// layoutPictogramElement(containerShape);

		return containerShape;
	}

	private Action getAddedAction(IAddContext context) {
		Action addedAction = (Action) context.getNewObject();
		return addedAction;
	}

	private RoundedRectangle createActionRectangle(IAddContext context,
			ContainerShape containerShape) {
		Rectangle invisibleRectangle = getGaService().createInvisibleRectangle(
				containerShape);

		Action addedAction = getAddedAction(context);
		getGaService().setLocationAndSize(invisibleRectangle, context.getX(),
				context.getY(), computeOverallRectangleWidth(addedAction),
				computeOverallRectangleHeight(addedAction));

		RoundedRectangle roundedRectangle = getGaService()
				.createRoundedRectangle(invisibleRectangle,
						ACTION_CORNER_WIDTH, ACTION_CORNER_HEIGHT);
		roundedRectangle.setForeground(manageColor(FOREGROUND));
		roundedRectangle.setBackground(manageColor(BACKGROUND));
		roundedRectangle.setLineWidth(ACTION_LINE_WIDTH);
		getGaService().setLocationAndSize(roundedRectangle,
				computeInputPinNameWidth(addedAction), 0,
				computeActionRectangleWidth(addedAction),
				computeOverallRectangleHeight(addedAction));

		link(containerShape, addedAction);
		return roundedRectangle;
	}

	private int computeOverallRectangleHeight(Action addedAction) {
		return XMOFDiagramDimensions.computeActionHeight(addedAction);
	}

	private int computeOverallRectangleWidth(Action action) {
		int width = computeActionRectangleWidth(action);
		if (action.getOutput().size() > 0) {
			width += computeOutputPinNameWidth(action);
		}
		if (action.getInput().size() > 0) {
			width += computeInputPinNameWidth(action);
		}
		return width;
	}

	private int computeActionRectangleWidth(Action action) {
		return Math.max(ACTION_DEFAULT_WIDTH,
				computeMaxOfNameOrTypeNameWidth(action));
	}

	private int computeMaxOfNameOrTypeNameWidth(Action action) {
		return Math.max(getActionNameWidth(action.getName()),
				getActionTypeNameWidth(getActionTypeName(action)));
	}

	private int getActionNameWidth(String actionName) {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(actionName, getActionNameFont()).getWidth() + 10;
	}

	private Font getActionNameFont() {
		return getGaService().manageDefaultFont(getDiagram(), false, true);
	}

	private int getActionTypeNameWidth(String actionName) {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(actionName, getActionTypeNameFont())
				.getWidth() + 10;
	}

	private Font getActionTypeNameFont() {
		return getGaService().manageDefaultFont(getDiagram(), false, false);
	}

	private void addActionLabels(Action addedAction,
			ContainerShape containerShape) {
		Shape actionTypeTextShape = getPeCreateService().createShape(
				containerShape, false);

		Text actionTypeText = getGaService().createText(actionTypeTextShape,
				getActionTypeName(addedAction));
		actionTypeText.setForeground(manageColor(TEXT_FOREGROUND));
		actionTypeText.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
		actionTypeText.setFont(getActionTypeNameFont());
		getGaService().setLocationAndSize(actionTypeText,
				computeInputPinNameWidth(addedAction) - 5, 3,
				computeActionRectangleWidth(addedAction), 20);

		Shape actionNameShape = getPeCreateService().createShape(
				containerShape, false);
		Text actionNameText = getGaService().createText(actionNameShape,
				addedAction.getName());
		actionNameText.setForeground(manageColor(TEXT_FOREGROUND));
		actionNameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		actionNameText.setVerticalAlignment(Orientation.ALIGNMENT_MIDDLE);
		actionNameText.setFont(getActionNameFont());
		getGaService().setLocationAndSize(actionNameText,
				computeInputPinNameWidth(addedAction), 5,
				computeActionRectangleWidth(addedAction),
				computeOverallRectangleHeight(addedAction) - 5);

		link(actionTypeTextShape, addedAction);
		link(actionNameShape, addedAction);
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

	private int computeInputPinNameWidth(Action addedAction) {
		List<String> pinNames = new ArrayList<String>();
		for (InputPin pin : addedAction.getInput()) {
			pinNames.add(pin.getName());
		}
		return computePinNameWidth(pinNames);
	}

	private int computeOutputPinNameWidth(Action addedAction) {
		List<String> pinNames = new ArrayList<String>();
		for (OutputPin pin : addedAction.getOutput()) {
			pinNames.add(pin.getName());
		}
		return computePinNameWidth(pinNames);
	}

	private int computePinNameWidth(List<String> pinNames) {
		int longestWidth = 0;
		for (String name : pinNames) {
			longestWidth = Math.max(longestWidth, getPinNameWidth(name));
		}
		return longestWidth;
	}

	private int getPinNameWidth(String pinName) {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(pinName, getPinNameFont()).getWidth()
				+ PIN_LABEL_MARGIN;
	}

	private Font getPinNameFont() {
		return getGaService().manageDefaultFont(getDiagram(), false, false);
	}

	private void addOutputPins(Action addedAction,
			ContainerShape containerShape, RoundedRectangle roundedRectangle) {

		int outputPinNameWidth = computeOutputPinNameWidth(addedAction);
		int offset = 5;

		for (OutputPin outputPin : addedAction.getOutput()) {
			BoxRelativeAnchor boxAnchor = getPeCreateService()
					.createBoxRelativeAnchor(containerShape);
			boxAnchor.setReferencedGraphicsAlgorithm(roundedRectangle);

			int invisibleRectangleXPosition = computeOverallRectangleWidth(addedAction)
					- outputPinNameWidth
					- ACTION_LINE_WIDTH
					- computeInputPinNameWidth(addedAction);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(boxAnchor);
			getGaService().setLocationAndSize(invisibleRectangle,
					invisibleRectangleXPosition, offset, outputPinNameWidth,
					XMOFDiagramDimensions.getPinRectangleHeight());

			Text text = getGaService().createText(invisibleRectangle,
					outputPin.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(text, PIN_LABEL_MARGIN, 0,
					outputPinNameWidth, PIN_LABEL_HEIGHT);

			Rectangle pinRectangle = getGaService().createRectangle(
					invisibleRectangle);
			pinRectangle.setForeground(manageColor(FOREGROUND));
			pinRectangle.setBackground(manageColor(BACKGROUND));
			pinRectangle.setLineWidth(ACTION_LINE_WIDTH);
			getGaService().setLocationAndSize(pinRectangle, 0, PIN_HEIGHT,
					PIN_WIDTH, PIN_HEIGHT);
			link(boxAnchor, outputPin);
			offset += PIN_VERTICAL_MARGIN
					+ XMOFDiagramDimensions.getPinRectangleHeight();
		}
	}

	private void addInputPins(Action addedAction,
			ContainerShape containerShape, RoundedRectangle roundedRectangle) {

		int inputPinNameWidth = computeInputPinNameWidth(addedAction);
		int offset = 5;

		for (InputPin inputPin : addedAction.getInput()) {
			BoxRelativeAnchor boxAnchor = getPeCreateService()
					.createBoxRelativeAnchor(containerShape);
			boxAnchor.setReferencedGraphicsAlgorithm(roundedRectangle);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(boxAnchor);
			getGaService().setLocationAndSize(invisibleRectangle,
					-inputPinNameWidth + ACTION_LINE_WIDTH, offset,
					inputPinNameWidth + PIN_LABEL_MARGIN,
					XMOFDiagramDimensions.getPinRectangleHeight());

			Text text = getGaService().createText(invisibleRectangle,
					inputPin.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(text, -PIN_LABEL_MARGIN, 0,
					inputPinNameWidth, PIN_LABEL_HEIGHT);

			Rectangle pinRectangle = getGaService().createRectangle(
					invisibleRectangle);
			pinRectangle.setForeground(manageColor(FOREGROUND));
			pinRectangle.setBackground(manageColor(BACKGROUND));
			pinRectangle.setLineWidth(ACTION_LINE_WIDTH);
			getGaService().setLocationAndSize(pinRectangle,
					inputPinNameWidth - PIN_WIDTH, PIN_HEIGHT, PIN_WIDTH,
					PIN_HEIGHT);
			link(boxAnchor, inputPin);
			offset += PIN_VERTICAL_MARGIN
					+ XMOFDiagramDimensions.getPinRectangleHeight();
		}
	}

}
