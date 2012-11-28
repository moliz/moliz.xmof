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
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;

public class LayoutActivityFeature extends AbstractLayoutFeature {

	public LayoutActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape))
			return false;
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.size() == 1
				&& businessObjects.get(0) instanceof Activity;
	}

	private Activity getActivity(ILayoutContext context) {
		return (Activity) context.getPictogramElement().getLink()
				.getBusinessObjects().get(0);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape activityShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm activityRectangle = activityShape.getGraphicsAlgorithm();

		Activity activity = getActivity(context);
		ActivityDimensionCalculator calculator = new ActivityDimensionCalculator(
				activity, getDiagram(), activityRectangle.getX(),
				activityRectangle.getY());

		boolean anythingChanged = ensureMinHeightAndWidth(activityRectangle,
				calculator);

		IGaService gaService = Graphiti.getGaService();
		int activityRectangleWidth = gaService.calculateSize(activityRectangle)
				.getWidth();
		int activityTextWidth = calculator
				.getActivityTextWidth(activityRectangleWidth);
		Iterator<Shape> iter = activityShape.getChildren().iterator();
		while (iter.hasNext()) {
			Shape shape = iter.next();
			GraphicsAlgorithm gaText = shape.getGraphicsAlgorithm();
			IDimension size = gaService.calculateSize(gaText);
			if (gaText instanceof Text) {
				Text text = (Text) gaText;
				if (activityRectangleWidth != size.getWidth()) {
					gaService.setWidth(text, activityTextWidth);
					anythingChanged = true;
				}
				if (activity.getName().equals(text.getValue())) {
					int textHeight = activityRectangle.getHeight();
					if (text.getHeight() != textHeight) {
						gaService.setHeight(text,
								calculator.getActivityNameTextHeight(textHeight));
						anythingChanged = true;
					}
				}
			}
		}

		if (anythingChanged) {
			setUpActivityParameterNodes(activity, calculator, activityRectangleWidth);
		}

		return anythingChanged;
	}

	private boolean ensureMinHeightAndWidth(GraphicsAlgorithm activityRectangle,
			ActivityDimensionCalculator calculator) {
		boolean anythingChanged = false;
		if (activityRectangle.getHeight() < calculator.getActivityRectangleHeight()) {
			activityRectangle.setHeight(calculator.getActivityRectangleHeight());
			anythingChanged = true;
		}

		if (activityRectangle.getWidth() < calculator
				.getActivityRectangleMinWidth()) {
			activityRectangle.setWidth(calculator.getActivityRectangleMinWidth());
			anythingChanged = true;
		}
		return anythingChanged;
	}

	private void setUpActivityParameterNodes(Activity activity, ActivityDimensionCalculator calculator,
			int activityRectangleWidth) {
		int inputActivityParameterNodeNumber = 0;
		int outputActivityParameterNodeNumber = 0;
		for (ActivityNode node : activity.getNode()) {
			if(node instanceof ActivityParameterNode) {
				ActivityParameterNode parameterNode = (ActivityParameterNode)node;
				DirectedParameter parameter = parameterNode.getParameter();
				
				PictogramElement shape = getActivityParameterNodeShape(parameterNode);
				GraphicsAlgorithm area = shape.getGraphicsAlgorithm();
				
				if(parameter.getDirection() == ParameterDirectionKind.IN || parameter.getDirection() == ParameterDirectionKind.INOUT) {
					int activityParamenterNodeNumber = ++inputActivityParameterNodeNumber;
					Graphiti.getGaService().setLocationAndSize(area,
							calculator.getInputActivityParameterNodeAreaX(),
							calculator.getInputActivityParameterNodeAreaY(activityParamenterNodeNumber),
							calculator.getInputActivityParameterNodeAreaWidth(),
							calculator.getActivityParameterNodeAreaHeight());
				} else if(parameter.getDirection() == ParameterDirectionKind.OUT || parameter.getDirection() == ParameterDirectionKind.RETURN) {
					int activityParamenterNodeNumber = ++outputActivityParameterNodeNumber;
					Graphiti.getGaService().setLocationAndSize(area,
							calculator.getOutputActivityParameterNodeAreaX(activityRectangleWidth),
							calculator.getOutputActivityParameterNodeAreaY(activityParamenterNodeNumber),
							calculator.getOutputActivityParameterNodeAreaWidth(),
							calculator.getActivityParameterNodeAreaHeight());
				}
			}
			
		}
	}

	private PictogramElement getActivityParameterNodeShape(ActivityParameterNode activityParameterNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(activityParameterNode);
	}

}
