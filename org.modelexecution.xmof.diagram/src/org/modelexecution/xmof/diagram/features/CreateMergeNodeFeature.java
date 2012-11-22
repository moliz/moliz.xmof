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
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;

public class CreateMergeNodeFeature extends AbstractCreateFeature {

	public CreateMergeNodeFeature(IFeatureProvider fp) {
		super(fp, "Merge Node", "Create a Merge Node");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return getTargetActivity(context) != null;
	}

	@Override
	public Object[] create(ICreateContext context) {
		MergeNode mergeNode = IntermediateActivitiesFactory.eINSTANCE
				.createMergeNode();
		getTargetActivity(context).getNode().add(mergeNode);
		addGraphicalRepresentation(context, mergeNode);

		return new Object[] { mergeNode };
	}

	private Activity getTargetActivity(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof Activity) {
				return (Activity) object;
			}
		}
		return null;
	}

}
