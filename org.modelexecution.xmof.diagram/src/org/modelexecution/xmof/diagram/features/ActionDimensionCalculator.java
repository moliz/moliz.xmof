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
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTION_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_VERTICAL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_OFFSET;

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

	public ActionDimensionCalculator(Action action) {
		super();
		this.action = action;
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

	public int getOverallHeight() {
		int maxInputOutputPinNumber = getMaxInputOrOutputPinNumber();
		return Math.max(ACTION_DEFAULT_HEIGHT, maxInputOutputPinNumber
				* (getPinAreaHeight() + PIN_VERTICAL_MARGIN));
	}

	public int getPinAreaHeight() {
		return PIN_HEIGHT + PIN_LABEL_HEIGHT;
	}

	private int getMaxInputOrOutputPinNumber() {
		return Math.max(action.getInput().size(), action.getOutput().size());
	}

	public int getOverallWidth() {
		int width = getActionRectangleWidth();
		width = addPinWidth(width);
		return width;
	}

	public int getOverallMinWidth() {
		int width = ACTION_DEFAULT_WIDTH;
		width = addPinWidth(width);
		return width;
	}

	private int addPinWidth(int width) {
		if (action.getOutput().size() > 0) {
			width += getOutputPinNameWidth();
		}
		if (action.getInput().size() > 0) {
			width += getInputPinNameWidth();
		}
		return width;
	}

	public int getActionRectangleWidth() {
		return Math.max(ACTION_DEFAULT_WIDTH, getMaxOfNameOrTypeNameWidth());
	}

	public int getActionRectangleHeight() {
		return getOverallHeight();
	}

	private int getMaxOfNameOrTypeNameWidth() {
		return Math.max(getActionNameWidth(), getActionTypeNameWidth());
	}

	private int getActionTypeNameWidth() {
		return GraphitiUi
				.getUiLayoutService()
				.calculateTextSize(getActionTypeName(), getActionTypeNameFont())
				.getWidth() + 10;
	}

	private Font getActionTypeNameFont() {
		return DiagramFonts.getActionTypeNameFont(getDiagram());
	}

	private int getActionNameWidth() {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(action.getName(), getActionNameFont())
				.getWidth() + 10;
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

	public int getActionTypeNameTextX() {
		return getInputPinNameWidth() - 5;
	}

	public int getActionTypeNameTextY() {
		return 3;
	}

	public int getActionTypeNameTextWidth() {
		return getActionRectangleWidth();
	}

	public int getActionTypeNameTextHeight() {
		return 20;
	}

	public int getActionNameTextX() {
		return getInputPinNameWidth();
	}

	public int getActionNameTextY() {
		return 5;
	}

	public int getActionNameTextWidth() {
		return getActionRectangleWidth();
	}

	public int getActionNameTextHeight() {
		return getOverallHeight() - 5;
	}

	public int getOutputPinAreaX() {
		return getOutputPinAreaX(getOverallWidth());
	}

	public int getOutputPinAreaX(int overallWidth) {
		return overallWidth - getOutputPinAreaWidth() - ACTION_LINE_WIDTH - 5;
	}

	public int getOutputPinAreaY(int pinNumber) {
		return PIN_OFFSET + (pinNumber - 1)
				* (PIN_VERTICAL_MARGIN + getPinAreaHeight());
	}

	public int getOutputPinAreaWidth() {
		return getOutputPinNameWidth();
	}

	public int getInputPinAreaX() {
		return ACTION_LINE_WIDTH;
	}

	public int getInputPinAreaY(int pinNumber) {
		return getOutputPinAreaY(pinNumber);
	}

	public int getInputPinAreaWidth() {
		return getInputPinNameWidth() + PIN_LABEL_MARGIN;
	}

}
