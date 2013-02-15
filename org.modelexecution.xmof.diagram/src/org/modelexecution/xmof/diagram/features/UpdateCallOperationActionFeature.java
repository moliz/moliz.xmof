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

import org.eclipse.emf.ecore.EParameter;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.diagram.PropertyUtil;

public class UpdateCallOperationActionFeature extends UpdateCallActionFeature {

	public UpdateCallOperationActionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return bo instanceof CallOperationAction;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		IReason nameUpdateReason = super.updateNeeded(context);
		boolean updateNameNeeded = nameUpdateReason.toBoolean();

		String operationNamePe = getOperationNameFromPictogramElement(context);

		String operationNameBo = getOperationNameFromBusinessObject(context);

		// update needed, if names are different
		boolean updateBehaviorNeeded = !operationNamePe.equals(operationNameBo);
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

		String operationNamePe = getOperationNameFromPictogramElement(context);

		String operationNameBo = getOperationNameFromBusinessObject(context);

		CallOperationAction action = getCallOperationAction(context);
		if (operationNameBo.equals(operationNamePe) || action == null) {
			return updated;
		}

		// remove diagram representation of action
		PictogramElement pictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(action);
		int x = pictogramElement.getGraphicsAlgorithm().getX();
		int y = pictogramElement.getGraphicsAlgorithm().getY();

		removeActionDiagramRepresentation(action);

		// remove pins
		deletePins(action);		

		// add new pins according to operation
		BehavioredEOperation operation = action.getOperation();
		if (operation != null) {
			for (EParameter param : operation.getEParameters()) {
				if(isInputParameter(param)) {
					InputPin pin = createInputPin(param);
					action.getArgument().add(pin);
				} else {
					OutputPin pin = createOutputPin(param);
					action.getResult().add(pin);
				}
			}
		}		

		// add diagram representation of action
		addActionDiagramRepresentation(action, x, y);

		// set operation name as property of pictogram element
		pictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(action);
		PropertyUtil.setCallOperationActionOperation(pictogramElement,
				operationNameBo);

		return true;
	}
	
	private InputPin createInputPin(EParameter parameter) {
		InputPin pin = BasicActionsFactory.eINSTANCE
				.createInputPin();
		pin.setName(parameter.getName());
		pin.setLowerBound(parameter.getLowerBound());
		pin.setUpperBound(parameter.getUpperBound());
		return pin;
	}
	
	private OutputPin createOutputPin(EParameter parameter) {
		OutputPin pin = BasicActionsFactory.eINSTANCE
				.createOutputPin();
		pin.setName(parameter.getName());
		return pin;
	}
	
	private boolean isInputParameter(EParameter parameter) {
		if(parameter instanceof DirectedParameter) {			
			DirectedParameter directedParam = (DirectedParameter)parameter;
			return (directedParam.getDirection() == ParameterDirectionKind.IN
					|| directedParam.getDirection() == ParameterDirectionKind.INOUT);
		}
		return true;
	}

	private String getOperationNameFromPictogramElement(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		return PropertyUtil.getCallOperationActionOperation(pictogramElement);
	}

	private String getOperationNameFromBusinessObject(IUpdateContext context) {
		String operationNameBo = "";
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof CallOperationAction) {
			CallOperationAction action = (CallOperationAction) bo;
			BehavioredEOperation operation = action.getOperation();
			if (operation != null) {
				operationNameBo = operation.getName();
			}
		}
		return operationNameBo;
	}

	private CallOperationAction getCallOperationAction(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof CallOperationAction) {
			return (CallOperationAction) bo;
		}
		return null;
	}

}
