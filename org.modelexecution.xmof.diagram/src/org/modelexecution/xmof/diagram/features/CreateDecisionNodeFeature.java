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
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;

public class CreateDecisionNodeFeature extends AbstractCreateFeature {

	public CreateDecisionNodeFeature(IFeatureProvider fp) {
		super(fp, "Decision Node", "Create a Decision Node");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return getTargetActivity(context) != null;
	}

	@Override
	public Object[] create(ICreateContext context) {
		DecisionNode decisionNode = IntermediateActivitiesFactory.eINSTANCE
				.createDecisionNode();
		getTargetActivity(context).getNode().add(decisionNode);
		addGraphicalRepresentation(context, decisionNode);

		return new Object[] { decisionNode };
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
