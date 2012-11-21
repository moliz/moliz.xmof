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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityEdge;

public class DeleteActionFeature extends DefaultDeleteFeature {

	public DeleteActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		deletePins(context);
		super.delete(context);
	}

	private void deletePins(IDeleteContext context) {
		EList<EObject> actions = context.getPictogramElement().getLink()
				.getBusinessObjects();
		for (EObject eObject : actions) {
			if (eObject instanceof Action) {
				Action action = (Action) eObject;
				deleteEdges(action.getIncoming());
				deleteEdges(action.getOutgoing());
				deleteOutputPins(action.getOutput());
				deleteInputPins(action.getInput());
			}
		}
	}

	private void deleteInputPins(EList<InputPin> inputPins) {
		for (InputPin inputPin : new BasicEList<InputPin>(inputPins)) {
			deleteEdges(inputPin.getIncoming());
			deleteEdges(inputPin.getOutgoing());
			PictogramElement pinShape = getPinShape(inputPin);
			delete(pinShape);
		}
	}

	private void deleteOutputPins(EList<OutputPin> outputPins) {
		for (OutputPin outputPin : new BasicEList<OutputPin>(outputPins)) {
			deleteEdges(outputPin.getIncoming());
			deleteEdges(outputPin.getOutgoing());
			PictogramElement pinShape = getPinShape(outputPin);
			delete(pinShape);
		}
	}

	private PictogramElement getPinShape(Pin pin) {
		return getFeatureProvider().getPictogramElementForBusinessObject(pin);
	}

	private void delete(PictogramElement pictogramElement) {
		IDeleteContext deleteContext = new DeleteContext(pictogramElement);
		IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(
				deleteContext);
		if (deleteFeature != null) {
			deleteFeature.delete(deleteContext);
		}
	}

	private void deleteEdges(EList<ActivityEdge> edges) {
		for (ActivityEdge edge : new BasicEList<ActivityEdge>(edges)) {
			delete(getEdgeConnection(edge));
		}
	}

	private PictogramElement getEdgeConnection(ActivityEdge edge) {
		return getFeatureProvider().getPictogramElementForBusinessObject(edge);
	}

}
