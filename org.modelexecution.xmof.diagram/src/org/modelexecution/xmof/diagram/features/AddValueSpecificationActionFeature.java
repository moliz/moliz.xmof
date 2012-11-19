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
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_DEFAULT_HEIGHT;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_DEFAULT_WIDTH;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.ACTION_LINE_WIDTH;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_HEIGHT;
import static org.modelexecution.xmof.diagram.XMOFDiagramDimensions.PIN_WIDTH;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;

public class AddValueSpecificationActionFeature extends AbstractAddFeature {

	public AddValueSpecificationActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof ValueSpecificationAction
				&& context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ValueSpecificationAction addedAction = (ValueSpecificationAction) context
				.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(
				targetDiagram, true);

		int longestPinNameWidth = 40; // TODO compute text width

		RoundedRectangle roundedRectangle; // need to access it later
		IGaService gaService = Graphiti.getGaService();

		{
			// create invisible outer rectangle expanded by
			// the width needed for the anchor
			Rectangle invisibleRectangle = gaService
					.createInvisibleRectangle(containerShape);
			gaService.setLocationAndSize(invisibleRectangle, context.getX(),
					context.getY(), ACTION_DEFAULT_WIDTH + PIN_WIDTH
							+ longestPinNameWidth - ACTION_LINE_WIDTH,
					ACTION_DEFAULT_HEIGHT);

			// create and set graphics algorithm
			roundedRectangle = gaService.createRoundedRectangle(
					invisibleRectangle, ACTION_CORNER_WIDTH,
					ACTION_CORNER_HEIGHT);
			roundedRectangle.setForeground(manageColor(FOREGROUND));
			roundedRectangle.setBackground(manageColor(BACKGROUND));
			roundedRectangle.setLineWidth(ACTION_LINE_WIDTH);
			gaService.setLocationAndSize(roundedRectangle, 0, 0,
					ACTION_DEFAULT_WIDTH, ACTION_DEFAULT_HEIGHT);

			// if added Class has no resource we add it to the resource
			// of the diagram
			// in a real scenario the business model would have its own resource
			if (addedAction.eResource() == null) {
				getDiagram().eResource().getContents().add(addedAction);
			}
			// create link and wire it
			link(containerShape, addedAction);
		}

		// SHAPE WITH TEXT
		{
			// create shape for text
			Shape shape = peCreateService.createShape(containerShape, false);

			// create and set text graphics algorithm
			Text text = gaService.createText(shape, addedAction.getName());
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			// vertical alignment has as default value "center"
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
			gaService.setLocationAndSize(text, 0, 0, ACTION_DEFAULT_WIDTH,
					ACTION_DEFAULT_HEIGHT);

			// create link and wire it
			link(shape, addedAction);
		}

		// OUTPUT PIN
		{
			// create an additional box relative anchor at middle-right
			BoxRelativeAnchor boxAnchor = peCreateService
					.createBoxRelativeAnchor(containerShape);

			// anchor references visible rectangle instead of invisible
			// rectangle
			boxAnchor.setReferencedGraphicsAlgorithm(roundedRectangle);

			Rectangle invisibleRectangle = gaService
					.createInvisibleRectangle(boxAnchor);
			gaService.setLocationAndSize(invisibleRectangle,
					ACTION_DEFAULT_WIDTH - (ACTION_LINE_WIDTH * 2), 5,
					PIN_WIDTH + longestPinNameWidth, PIN_HEIGHT * 2);

			// create and set text graphics algorithm
			Text text = gaService.createText(invisibleRectangle,
					"output");
			text.setForeground(manageColor(TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			// vertical alignment has as default value "center"
			text.setFont(gaService
					.manageDefaultFont(getDiagram(), false, false));
			gaService.setLocationAndSize(text, 0, 0, longestPinNameWidth,
					PIN_HEIGHT);

			// assign a graphics algorithm for the box relative anchor
			Rectangle pinRectangle = gaService
					.createRectangle(invisibleRectangle);
			pinRectangle.setForeground(manageColor(FOREGROUND));
			pinRectangle.setBackground(manageColor(BACKGROUND));
			pinRectangle.setLineWidth(ACTION_LINE_WIDTH);

			// anchor is located on the right border of the visible rectangle
			// and touches the border of the invisible rectangle
			gaService.setLocationAndSize(pinRectangle, 0, PIN_HEIGHT,
					PIN_WIDTH, PIN_HEIGHT);

			// TODO link to pin
		}

		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
