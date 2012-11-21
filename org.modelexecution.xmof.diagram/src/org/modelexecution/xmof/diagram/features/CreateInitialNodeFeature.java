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
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;

public class CreateInitialNodeFeature extends AbstractCreateFeature {

	public CreateInitialNodeFeature(IFeatureProvider fp) {
		super(fp, "Initial Node", "Create an Initial Node");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		InitialNode initialNode = IntermediateActivitiesFactory.eINSTANCE
				.createInitialNode();
		// TODO add to activity later
		getDiagram().eResource().getContents().add(initialNode);
		
		addGraphicalRepresentation(context, initialNode);
		
		return new Object[] { initialNode };
	}

}
