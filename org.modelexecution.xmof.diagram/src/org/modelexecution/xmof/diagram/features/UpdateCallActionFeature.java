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
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction;

public abstract class UpdateCallActionFeature extends UpdateActionFeature {

	public UpdateCallActionFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	protected void removeActionDiagramRepresentation(CallAction action) {
		PictogramElement pictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(action);

		IRemoveContext removeContext = new RemoveContext(pictogramElement);
		IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(
				removeContext);
		if (removeFeature != null) {
			removeFeature.remove(removeContext);
		}
	}

	protected void addActionDiagramRepresentation(CallAction action, int x, int y) {
		AreaContext areaContext = new AreaContext();
		areaContext.setSize(100, 100);
		AddContext addContext = new AddContext(areaContext, action);
		addContext.setLocation(x, y);
		PictogramElement targetContainer = getFeatureProvider()
				.getPictogramElementForBusinessObject(action.getActivity());
		addContext.setTargetContainer((ContainerShape) targetContainer);
		addGraphicalRepresentation(addContext, action);
	}
	
	protected void removePins(CallAction action) {
		action.getArgument().clear();
		action.getInput().clear();
		action.getResult().clear();
		action.getOutput().clear();
	}
	
}
