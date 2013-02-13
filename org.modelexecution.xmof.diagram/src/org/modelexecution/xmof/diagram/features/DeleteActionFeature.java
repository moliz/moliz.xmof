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
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;

public class DeleteActionFeature extends DeleteActivityNodeFeature {

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
				deleteOutputPins(action.getOutput());
				deleteInputPins(action.getInput());
			}
		}
	}

	protected void deleteInputPins(EList<InputPin> inputPins) {
		for (InputPin inputPin : new BasicEList<InputPin>(inputPins)) {
			deleteEdges(inputPin.getIncoming());
			deleteEdges(inputPin.getOutgoing());
			PictogramElement pinShape = getPinShape(inputPin);
			delete(pinShape);
		}
	}

	protected void deleteOutputPins(EList<OutputPin> outputPins) {
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

}
