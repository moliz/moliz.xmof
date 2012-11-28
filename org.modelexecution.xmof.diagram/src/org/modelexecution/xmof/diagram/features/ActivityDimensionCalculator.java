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

import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_DEFAULT_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_NAME_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_OFFSET;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_VERTICAL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.PIN_LABEL_MARGIN;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.diagram.DiagramFonts;

public class ActivityDimensionCalculator {

	private Activity activity;
	private EList<ActivityParameterNode> inputParameterNodes = new BasicEList<ActivityParameterNode>();
	private EList<ActivityParameterNode> outputParameterNodes = new BasicEList<ActivityParameterNode>();
	
	private Diagram diagram = null;
	private int contextX;
	private int contextY;

	public ActivityDimensionCalculator(Activity activity, Diagram diagram,
			int contextX, int contextY) {
		super();
		this.activity = activity;
		this.diagram = diagram;
		this.contextX = contextX;
		this.contextY = contextY;
		initializeActivityParameterNodeLists();
	}	
	
	private void initializeActivityParameterNodeLists() {
		for(ActivityNode node : activity.getNode()) {
			if(node instanceof ActivityParameterNode) {
				ActivityParameterNode parameterNode = (ActivityParameterNode)node;
				DirectedParameter parameter = parameterNode.getParameter();
				if(parameter.getDirection() == ParameterDirectionKind.IN || parameter.getDirection() == ParameterDirectionKind.INOUT) {
					inputParameterNodes.add(parameterNode);
				} else if(parameter.getDirection() == ParameterDirectionKind.OUT || parameter.getDirection() == ParameterDirectionKind.RETURN) {
					outputParameterNodes.add(parameterNode);
				}
			}
		}
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public Activity getActivity() {
		return activity;
	}

	public int getActivityParameterNodeAreaHeight() {
		return ACTIVITY_PARAMETER_NODE_HEIGHT + ACTIVITY_PARAMETER_NODE_LABEL_HEIGHT;
	}

	private int getMaxInputOrOutputActivityParameterNodeNumber() {
		return Math.max(inputParameterNodes.size(), outputParameterNodes.size());
	}

	public int getActivityRectangleMinWidth() {
		return ACTIVITY_DEFAULT_WIDTH;
	}

	public int getActivityRectangleWidth() {
		return Math.max(ACTIVITY_DEFAULT_WIDTH, getActivityNameWidth());
	}

	public int getActivityRectangleHeight() {
		int maxInputOutputParameterNodeNumber = getMaxInputOrOutputActivityParameterNodeNumber();
		return Math.max(ACTIVITY_DEFAULT_HEIGHT, maxInputOutputParameterNodeNumber
				* (getActivityParameterNodeAreaHeight() + ACTIVITY_PARAMETER_NODE_VERTICAL_MARGIN));
	}

	public int removeActivityLabelMargin(int width) {
		return width - getDoubleActivityLabelMargin();
	}

	private int getDoubleActivityLabelMargin() {
		return ACTIVITY_NAME_LABEL_MARGIN * 2;
	}

	private int getActivityNameWidth() {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(activity.getName(), getActivityNameFont())
				.getWidth()
				+ getDoubleActivityLabelMargin();
	}

	public int getInputActivityParameterNodeNameWidth() {
		List<String> parameterNodeNames = new ArrayList<String>();
		for (ActivityParameterNode node : inputParameterNodes) {
			parameterNodeNames.add(node.getName());
		}
		return computeActivityParameterNodeNameWidth(parameterNodeNames);
	}

	public int getOutputParameterNodeNameWidth() {
		List<String> parameterNodeNames = new ArrayList<String>();
		for (ActivityParameterNode node : outputParameterNodes) {
			parameterNodeNames.add(node.getName());
		}
		return computeActivityParameterNodeNameWidth(parameterNodeNames);
	}

	private int computeActivityParameterNodeNameWidth(List<String> parameterNodeNames) {
		int longestWidth = 0;
		for (String name : parameterNodeNames) {
			longestWidth = Math.max(longestWidth, (getActivityParameterNodeNameWidth(name) + ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN));
		}
		return longestWidth;
	}

	private int getActivityParameterNodeNameWidth(String parmaeterNodeName) {
		return GraphitiUi.getUiLayoutService()
				.calculateTextSize(parmaeterNodeName, getActivityParameterNodeNameFont()).getWidth()
				+ ACTIVITY_PARAMETER_NODE_LABEL_MARGIN;
	}

	private Font getActivityNameFont() {
		return DiagramFonts.getActivityNameFont(diagram);
	}

	private Font getActivityParameterNodeNameFont() {
		return DiagramFonts.getActivityParameterNodeNameFont(diagram);
	}

	public int getActivityNameTextX() {
		return getInputActivityParameterNodeNameWidth();
	}

	public int getActivityNameTextY() {
		return ACTIVITY_NAME_LABEL_MARGIN;
	}

	public int getActivityTextWidth() {
		return getActivityTextWidth(getActivityRectangleWidth());
	}

	public int getActivityTextWidth(int activityRectangleWidth) {
		return activityRectangleWidth - 2 * ACTIVITY_NAME_LABEL_MARGIN;
	}

	public int getActivityNameTextHeight() {
		return getActivityNameTextHeight(getActivityRectangleHeight());
	}

	public int getActivityNameTextHeight(int activityRectangleHeight) {
		return activityRectangleHeight;
	}

	public int getOutputActivityParameterNodeAreaX(int overallWidth) {
		return contextX + overallWidth - NODE_LINE_WIDTH - ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN;
	}

	public int getOutputActivityParameterNodeAreaY(int parameterNodeNumber) {
		return contextY + ACTIVITY_PARAMETER_NODE_OFFSET + (parameterNodeNumber - 1)
				* (ACTIVITY_PARAMETER_NODE_VERTICAL_MARGIN + getActivityParameterNodeAreaHeight());
	}

	public int getOutputActivityParameterNodeAreaWidth() {
		return getOutputParameterNodeNameWidth();
	}

	public int getInputActivityParameterNodeAreaX() {
		return contextX - getInputActivityParameterNodeAreaWidth() + NODE_LINE_WIDTH
				+ PIN_LABEL_MARGIN + ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN;
	}

	public int getInputActivityParameterNodeAreaY(int parameterNodeNumber) {
		return getOutputActivityParameterNodeAreaY(parameterNodeNumber);
	}

	public int getInputActivityParameterNodeAreaWidth() {
		return getInputActivityParameterNodeNameWidth() + ACTIVITY_PARAMETER_NODE_LABEL_MARGIN;
	}

}
