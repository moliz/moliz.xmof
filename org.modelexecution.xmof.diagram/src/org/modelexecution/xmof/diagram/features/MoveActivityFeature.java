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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

public class MoveActivityFeature extends DefaultMoveShapeFeature {

	public MoveActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void moveShape(IMoveShapeContext context) {
		super.moveShape(context);
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (bo instanceof Activity) {
			Activity activity = (Activity) bo;
			relocateActivityParameterNodes(activity, context);
		}
	}

	private void relocateActivityParameterNodes(Activity activity, IMoveShapeContext context) {
		for (ActivityNode node : activity.getNode()) {
			if(node instanceof ActivityParameterNode) {
				ActivityParameterNode parameterNode = (ActivityParameterNode)node;				
				PictogramElement shape = getActivityParameterNode(parameterNode);
				GraphicsAlgorithm area = shape.getGraphicsAlgorithm();
				area.setX(area.getX() + context.getDeltaX());
				area.setY(area.getY() + context.getDeltaY());
			}
		}
	}

	private PictogramElement getActivityParameterNode(ActivityParameterNode activityParameterNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(activityParameterNode);
	}

}
