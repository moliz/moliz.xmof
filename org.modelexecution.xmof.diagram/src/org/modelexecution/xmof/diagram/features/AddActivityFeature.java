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
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_DEFAULT_HEIGHT;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_NAME_LABEL_MARGIN;
import static org.modelexecution.xmof.diagram.DiagramDimensions.ACTIVITY_NAME_LABEL_HEIGHT;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
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
		// TODO add parameters

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
		getGaService()
				.setLocationAndSize(roundedRectangle, context.getX(),
						context.getY(), ACTIVITY_DEFAULT_WIDTH,
						ACTIVITY_DEFAULT_HEIGHT);

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
		activityNameText.setVerticalAlignment(Orientation.ALIGNMENT_MIDDLE);
		activityNameText.setFont(getActionNameFont());
		getGaService().setLocationAndSize(activityNameText,
				ACTIVITY_NAME_LABEL_MARGIN, 0, ACTIVITY_DEFAULT_WIDTH,
				ACTIVITY_NAME_LABEL_HEIGHT);

		link(activityNameTextShape, activity);
	}

	private Font getActionNameFont() {
		return DiagramFonts.getActionNameFont(getDiagram());
	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}

}
