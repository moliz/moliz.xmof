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
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;

public class CreateJoinNodeFeature extends AbstractCreateFeature {

	public CreateJoinNodeFeature(IFeatureProvider fp) {
		super(fp, "Join Node", "Create a Join Node");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return getTargetActivity(context) != null;
	}

	@Override
	public Object[] create(ICreateContext context) {
		JoinNode joinNode = IntermediateActivitiesFactory.eINSTANCE
				.createJoinNode();
		getTargetActivity(context).getNode().add(joinNode);
		addGraphicalRepresentation(context, joinNode);

		return new Object[] { joinNode };
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
