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
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_CORNER_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_CORNER_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_NAME_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_NAME_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_LABEL_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_PARAMETER_NODE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.NODE_LINE_WIDTH;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.diagram.DiagramFonts;

public class AddActivityFeature extends AbstractAddFeature {

	public AddActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof Activity
				&& context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) { 		
		Activity addedActivity = getAddedActivity(context);
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		ContainerShape containerShape = getPeCreateService()
				.createContainerShape(targetDiagram, true);
		createActivityRectangle(context, containerShape);
		addActivityNameLabel(addedActivity, containerShape);

		addActivityParameterNodes(context);

		getPeCreateService().createChopboxAnchor(containerShape);
		layoutPictogramElement(containerShape);

		return containerShape;
	}

	private Activity getAddedActivity(IAddContext context) {
		Activity addedActivity = (Activity) context.getNewObject();
		return addedActivity;
	}

	private void createActivityRectangle(IAddContext context,
			ContainerShape containerShape) {

		Activity addedActivity = getAddedActivity(context);

		RoundedRectangle roundedRectangle = getGaService()
				.createRoundedRectangle(containerShape, ACTIVITY_CORNER_WIDTH,
						ACTIVITY_CORNER_HEIGHT);
		roundedRectangle.setForeground(manageColor(FOREGROUND));
		roundedRectangle.setBackground(manageColor(BACKGROUND));
		roundedRectangle.setLineWidth(ACTIVITY_LINE_WIDTH);

		ActivityDimensionCalculator calculator = new ActivityDimensionCalculator(
				addedActivity, getDiagram(), context.getX(), context.getY()); 

		getGaService()
				.setLocationAndSize(
						roundedRectangle,
						context.getX()
								+ calculator
										.getInputActivityParameterNodeAreaWidth(),
						context.getY(), ACTIVITY_DEFAULT_WIDTH,
						calculator.getActivityRectangleHeight());

		link(containerShape, addedActivity);
	}

	private void addActivityNameLabel(Activity activity,
			ContainerShape containerShape) {

		Shape activityNameTextShape = getPeCreateService().createShape(
				containerShape, false);
		Text activityNameText = getGaService().createText(
				activityNameTextShape, activity.getName());
		activityNameText.setForeground(manageColor(TEXT_FOREGROUND));
		activityNameText.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
		activityNameText.setVerticalAlignment(Orientation.ALIGNMENT_TOP);
		activityNameText.setFont(getActivityNameFont());
		getGaService().setLocationAndSize(activityNameText,
				ACTIVITY_NAME_LABEL_MARGIN, 0, ACTIVITY_DEFAULT_WIDTH,
				ACTIVITY_NAME_LABEL_HEIGHT);

		link(activityNameTextShape, activity);
	}

	private Font getActivityNameFont() {
		return DiagramFonts.getActionNameFont(getDiagram());
	}

	private void addActivityParameterNodes(IAddContext context) {
		Activity addedActivity = getAddedActivity(context);

		EList<ActivityParameterNode> inputParameterNodes = new BasicEList<ActivityParameterNode>();
		EList<ActivityParameterNode> outputParameterNodes = new BasicEList<ActivityParameterNode>();

		for (ActivityNode node : addedActivity.getNode()) {
			if (node instanceof ActivityParameterNode) {
				ActivityParameterNode parameterNode = (ActivityParameterNode) node;
				DirectedParameter parameter = parameterNode.getParameter();
				if (parameter.getDirection() == ParameterDirectionKind.IN
						|| parameter.getDirection() == ParameterDirectionKind.INOUT) {
					inputParameterNodes.add(parameterNode);
				} else if (parameter.getDirection() == ParameterDirectionKind.OUT
						|| parameter.getDirection() == ParameterDirectionKind.RETURN) {
					outputParameterNodes.add(parameterNode);
				}
			}
		}

		addInputActivityParameterNodes(context, inputParameterNodes);
		addOutputActivityParameterNodes(context, outputParameterNodes);

	}

	private void addInputActivityParameterNodes(IAddContext context,
			EList<ActivityParameterNode> inputParameterNodes) {
		Activity addedActivity = getAddedActivity(context);
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();

		ActivityDimensionCalculator calculator = new ActivityDimensionCalculator(
				addedActivity, getDiagram(), context.getX(), context.getY());
		int inputActivityParameterNodeAreaWidth = calculator.getInputActivityParameterNodeAreaWidth();
		calculator = new ActivityDimensionCalculator(
				addedActivity, getDiagram(), context.getX() + inputActivityParameterNodeAreaWidth, context.getY());

		int parameterNodeNumber = 1;

		for (ActivityParameterNode inputParameterNode : inputParameterNodes) {
			ContainerShape parameterNodeShape = getPeCreateService()
					.createContainerShape(targetContainer, true);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(parameterNodeShape);
			getGaService()
					.setLocationAndSize(
							invisibleRectangle,
							calculator.getInputActivityParameterNodeAreaX(),
							calculator
									.getInputActivityParameterNodeAreaY(parameterNodeNumber),
							calculator.getInputActivityParameterNodeAreaWidth(),
							calculator.getActivityParameterNodeAreaHeight());

			Text text = getGaService().createText(invisibleRectangle,
					inputParameterNode.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(
					text,
					-ACTIVITY_PARAMETER_NODE_LABEL_MARGIN
							- ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN, 0,
					calculator.getInputActivityParameterNodeNameWidth(),
					ACTIVITY_PARAMETER_NODE_LABEL_HEIGHT);

			Shape parameterNodeRectangleShape = getPeCreateService()
					.createShape(parameterNodeShape, true);
			Rectangle parameterNodeRectangle = getGaService().createRectangle(
					parameterNodeRectangleShape);
			parameterNodeRectangle.setForeground(manageColor(FOREGROUND));
			parameterNodeRectangle.setBackground(manageColor(BACKGROUND));
			parameterNodeRectangle.setLineWidth(NODE_LINE_WIDTH);
			getGaService().setLocationAndSize(
					parameterNodeRectangle,
					calculator.getInputActivityParameterNodeNameWidth()
							- ACTIVITY_PARAMETER_NODE_WIDTH,
					ACTIVITY_PARAMETER_NODE_HEIGHT,
					ACTIVITY_PARAMETER_NODE_WIDTH,
					ACTIVITY_PARAMETER_NODE_HEIGHT);

			getPeCreateService().createChopboxAnchor(
					parameterNodeRectangleShape);

			link(parameterNodeShape, inputParameterNode);
			link(parameterNodeRectangleShape, inputParameterNode);
			parameterNodeNumber++;
		}

	}

	private void addOutputActivityParameterNodes(IAddContext context,
			EList<ActivityParameterNode> outputParameterNodes) {
		Activity addedActivity = getAddedActivity(context);
		ContainerShape targetShape = (ContainerShape) context
				.getTargetContainer();
		ActivityDimensionCalculator calculator = new ActivityDimensionCalculator(
				addedActivity, getDiagram(), context.getX(), context.getY());
		int inputActivityParameterNodeAreaWidth = calculator.getInputActivityParameterNodeAreaWidth();
		calculator = new ActivityDimensionCalculator(
				addedActivity, getDiagram(), context.getX() + inputActivityParameterNodeAreaWidth, context.getY());

		int parameterNodeNumber = 1;
		for (ActivityParameterNode outputParameterNode : outputParameterNodes) {
			ContainerShape parameterNodeShape = getPeCreateService()
					.createContainerShape(targetShape, true);

			Rectangle invisibleRectangle = getGaService()
					.createInvisibleRectangle(parameterNodeShape);
			getGaService()
					.setLocationAndSize(
							invisibleRectangle,
							calculator
									.getOutputActivityParameterNodeAreaX(calculator
											.getActivityRectangleMinWidth()),
							calculator
									.getOutputActivityParameterNodeAreaY(parameterNodeNumber),
							calculator
									.getOutputActivityParameterNodeAreaWidth(),
							calculator.getActivityParameterNodeAreaHeight());

			Text text = getGaService().createText(invisibleRectangle,
					outputParameterNode.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setFont(getGaService().manageDefaultFont(getDiagram(), false,
					false));
			getGaService().setLocationAndSize(
					text,
					ACTIVITY_PARAMETER_NODE_LABEL_MARGIN
							+ ACTIVITY_PARAMETER_NODE_HORIZONTAL_MARGIN, 0,
					calculator.getOutputParameterNodeNameWidth(),
					ACTIVITY_PARAMETER_NODE_LABEL_HEIGHT);

			Shape parameterNodeRectangleShape = getPeCreateService()
					.createShape(parameterNodeShape, true);
			Rectangle parameterNodeRectangle = getGaService().createRectangle(
					parameterNodeRectangleShape);
			parameterNodeRectangle.setForeground(manageColor(FOREGROUND));
			parameterNodeRectangle.setBackground(manageColor(BACKGROUND));
			parameterNodeRectangle.setLineWidth(NODE_LINE_WIDTH);
			getGaService().setLocationAndSize(parameterNodeRectangle, 0,
					0 + ACTIVITY_PARAMETER_NODE_HEIGHT,
					ACTIVITY_PARAMETER_NODE_WIDTH,
					ACTIVITY_PARAMETER_NODE_HEIGHT);

			getPeCreateService().createChopboxAnchor(
					parameterNodeRectangleShape);

			link(parameterNodeShape, outputParameterNode);
			link(parameterNodeRectangleShape, outputParameterNode);
			parameterNodeNumber++;
		}

	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}

}
