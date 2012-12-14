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

import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_DEFAULT_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_OFFSET;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_VERTICAL_MARGIN;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.diagram.DiagramFonts;

public class ActionDimensionCalculator {

	private Action action;
	private Diagram diagram = null;
	String actionTypeName = null;
	private int contextX;
	private int contextY;

	public ActionDimensionCalculator(Action action, Diagram diagram,
			int contextX, int contextY) {
		super();
		this.action = action;
		this.diagram = diagram;
		this.contextX = contextX;
		this.contextY = contextY;
	}

	public ActionDimensionCalculator(Action action, Diagram diagram) {
		super();
		this.action = action;
		this.diagram = diagram;
	}

	public ActionDimensionCalculator(Action action, Diagram diagram,
			String actionTypeName) {
		super();
		this.action = action;
		this.diagram = diagram;
		this.actionTypeName = actionTypeName;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public String getActionTypeName() {
		if (actionTypeName == null) {
			actionTypeName = action.eClass().getName();
		}
		return actionTypeName;
	}

	public void setActionTypeName(String actionTypeName) {
		this.actionTypeName = actionTypeName;
	}

	public Action getAction() {
		return action;
	}

	public int getPinAreaHeight() {
		return PIN_HEIGHT + PIN_LABEL_HEIGHT;
	}

	private int getMaxInputOrOutputPinNumber() {
		return Math.max(action.getInput().size(), action.getOutput().size());
	}

	public int getActionRectangleMinWidth() {
		return ACTION_DEFAULT_WIDTH;
	}

	public int getActionRectangleWidth() {
		return Math.max(ACTION_DEFAULT_WIDTH, getMaxOfNameOrTypeNameWidth());
	}

	public int getActionRectangleHeight() {
		int maxInputOutputPinNumber = getMaxInputOrOutputPinNumber();
		return Math.max(ACTION_DEFAULT_HEIGHT, maxInputOutputPinNumber
				* (getPinAreaHeight() + PIN_VERTICAL_MARGIN));
	}

	private int getMaxOfNameOrTypeNameWidth() {
		return Math.max(getActionNameWidth(), getActionTypeNameWidth());
	}

	private int getActionTypeNameWidth() {
		return GraphitiUi
				.getUiLayoutService()
				.calculateTextSize(getActionTypeName(), getActionTypeNameFont())
				.getWidth()
				+ getDoubleActionLabelMargin();
	}

	public int removeActionLabelMargin(int width) {
		return width - getDoubleActionLabelMargin();
	}

	private int getDoubleActionLabelMargin() {
		return ACTION_LABEL_MARGIN * 2;
	}

	private Font getActionTypeNameFont() {
		return DiagramFonts.getActionTypeNameFont(getDiagram());
	}

	private int getActionNameWidth() {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(action.getName(), getActionNameFont())
				.getWidth()
				+ getDoubleActionLabelMargin();
	}

	public int getInputPinNameWidth() {
		List<String> pinNames = new ArrayList<String>();
		for (InputPin pin : action.getInput()) {
			pinNames.add(pin.getName());
		}
		return computePinNameWidth(pinNames);
	}

	public int getOutputPinNameWidth() {
		List<String> pinNames = new ArrayList<String>();
		for (OutputPin pin : action.getOutput()) {
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

	private Font getActionNameFont() {
		return DiagramFonts.getActionNameFont(diagram);
	}

	private Font getPinNameFont() {
		return DiagramFonts.getPinNameFont(diagram);
	}

	public int getActionTypeNameTextHeight() {
		return 20;
	}

	public int getActionNameTextX() {
		return getInputPinNameWidth();
	}

	public int getActionNameTextY() {
		return ACTION_LABEL_MARGIN;
	}

	public int getActionTextWidth() {
		return getActionTextWidth(getActionRectangleWidth());
	}

	public int getActionTextWidth(int actionRectangleWidth) {
		return actionRectangleWidth - 2 * ACTION_LABEL_MARGIN;
	}

	public int getActionNameTextHeight() {
		return getActionNameTextHeight(getActionRectangleHeight());
	}

	public int getActionNameTextHeight(int actionRectangleHeight) {
		return actionRectangleHeight;
	}

	public int getOutputPinAreaX(int overallWidth) {
		return contextX + overallWidth - NODE_LINE_WIDTH;
	}

	public int getOutputPinAreaY(int pinNumber) {
		return contextY + PIN_OFFSET + (pinNumber - 1)
				* (PIN_VERTICAL_MARGIN + getPinAreaHeight());
	}

	public int getOutputPinAreaWidth() {
		return getOutputPinNameWidth();
	}

	public int getInputPinAreaX() {
		return contextX - getInputPinAreaWidth() + NODE_LINE_WIDTH
				+ PIN_LABEL_MARGIN;
	}

	public int getInputPinAreaY(int pinNumber) {
		return getOutputPinAreaY(pinNumber);
	}

	public int getInputPinAreaWidth() {
		return getInputPinNameWidth() + PIN_LABEL_MARGIN;
	}

}
