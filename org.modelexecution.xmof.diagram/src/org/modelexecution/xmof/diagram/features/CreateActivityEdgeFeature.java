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
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

public abstract class CreateActivityEdgeFeature extends
		AbstractCreateConnectionFeature {

	protected static final String CREATE_A = "Create a ";

	public CreateActivityEdgeFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// checks if source and target node are in the same activity or
		// structured activity node
		Activity sourceActivity = null;
		Activity targetActivity = null;
		StructuredActivityNode sourceStructuredNode = null;
		StructuredActivityNode targetStructuredNode = null;

		ActivityNode source = getActivityNode(context.getSourceAnchor());
		if (source != null) {
			sourceActivity = source.getActivity();
			sourceStructuredNode = source.getInStructuredNode();
		}
		ActivityNode target = getActivityNode(context.getTargetAnchor());
		if (target != null) {
			targetActivity = target.getActivity();
			targetStructuredNode = target.getInStructuredNode();
		}

		if (source != null && target != null && source != target) {
			if (sourceActivity != null && targetActivity != null) {
				return sourceActivity.equals(targetActivity);
			} else if (sourceStructuredNode != null
					&& targetStructuredNode != null) {
				return sourceStructuredNode.equals(targetStructuredNode);
			}
		}
		return false;
	}

	protected ActivityNode getActivityNode(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof ActivityNode) {
				return (ActivityNode) object;
			}
		}
		return null;
	}

}
