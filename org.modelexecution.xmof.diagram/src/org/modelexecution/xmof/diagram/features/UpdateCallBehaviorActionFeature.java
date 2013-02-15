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
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.diagram.PropertyUtil;

public class UpdateCallBehaviorActionFeature extends UpdateCallActionFeature {

	public UpdateCallBehaviorActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return bo instanceof CallBehaviorAction;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		IReason nameUpdateReason = super.updateNeeded(context);
		boolean updateNameNeeded = nameUpdateReason.toBoolean();

		String behaviorNamePe = getBehaviorNameFromPictogramElement(context);

		String behaviorNameBo = getBehaviorNameFromBusinessObject(context);

		// update needed, if names are different
		boolean updateBehaviorNeeded = !behaviorNamePe.equals(behaviorNameBo);
		if (updateNameNeeded && !updateBehaviorNeeded) {
			return nameUpdateReason;
		} else if (!updateNameNeeded && updateBehaviorNeeded) {
			return Reason.createTrueReason("Behavior is out of date");
		} else if (updateNameNeeded && updateBehaviorNeeded) {
			return Reason.createTrueReason("Name and behavior are out of date");
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		boolean updated = super.update(context);

		String behaviorNamePe = getBehaviorNameFromPictogramElement(context);

		String behaviorNameBo = getBehaviorNameFromBusinessObject(context);

		CallBehaviorAction action = getCallBehaviorAction(context);
		if (behaviorNameBo.equals(behaviorNamePe) || action == null) {
			return updated;
		}

		// remove diagram representation of action
		PictogramElement pictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(action);
		int x = pictogramElement.getGraphicsAlgorithm().getX();
		int y = pictogramElement.getGraphicsAlgorithm().getY();

		removeActionDiagramRepresentation(action);

		// delete pins
		deletePins(action);

		// create new pins according to behavior
		Behavior behavior = action.getBehavior();
		if (behavior != null) {
			for (DirectedParameter param : behavior.getOwnedParameter()) {
				if (param.getDirection() == ParameterDirectionKind.IN
						|| param.getDirection() == ParameterDirectionKind.INOUT) {
					InputPin pin = BasicActionsFactory.eINSTANCE
							.createInputPin();
					pin.setName(param.getName());
					pin.setLowerBound(param.getLowerBound());
					pin.setUpperBound(param.getUpperBound());
					action.getArgument().add(pin);
				}
				if (param.getDirection() == ParameterDirectionKind.OUT
						|| param.getDirection() == ParameterDirectionKind.INOUT
						|| param.getDirection() == ParameterDirectionKind.RETURN) {
					OutputPin pin = BasicActionsFactory.eINSTANCE
							.createOutputPin();
					pin.setName(param.getName());
					action.getResult().add(pin);
				}
			}
		}

		// add diagram representation of action
		addActionDiagramRepresentation(action, x, y);

		// set behavior name as property of pictogram element
		pictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(action);
		PropertyUtil.setCallBehaviorActionBehavior(pictogramElement,
				behaviorNameBo);

		return true;
	}

	private String getBehaviorNameFromPictogramElement(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		return PropertyUtil.getCallBehaviorActionBehavior(pictogramElement);
	}

	private String getBehaviorNameFromBusinessObject(IUpdateContext context) {
		String behaviorNameBo = "";
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof CallBehaviorAction) {
			CallBehaviorAction action = (CallBehaviorAction) bo;
			Behavior behavior = action.getBehavior();
			if (behavior != null) {
				behaviorNameBo = behavior.getName();
			}
		}
		return behaviorNameBo;
	}

	private CallBehaviorAction getCallBehaviorAction(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof CallBehaviorAction) {
			return (CallBehaviorAction) bo;
		}
		return null;
	}

}
