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

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;

public class CreateActivityFeature extends AbstractCreateFeature {

	public CreateActivityFeature(IFeatureProvider fp) {
		super(fp, "Activity", "Create an Activity");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		String activityName = ExampleUtil.askString("Create Activity",
				"Enter new activity name", "Activity");
		if (activityName == null || activityName.trim().length() == 0) {
			return EMPTY;
		}

		Activity activity = IntermediateActivitiesFactory.eINSTANCE
				.createActivity();
		activity.setName(activityName);

		// TODO add activity to the specified resource
		getDiagram().eResource().getContents().add(activity);

		addGraphicalRepresentation(context, activity);

		return new Object[] { activity };
	}

}
