/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;
import org.modelexecution.xmof.diagram.features.AddActionFeature;
import org.modelexecution.xmof.diagram.features.AddFlowFeature;
import org.modelexecution.xmof.diagram.features.CreateAddStructuralFeatureValueActionFeature;
import org.modelexecution.xmof.diagram.features.CreateControlFlowFeature;
import org.modelexecution.xmof.diagram.features.CreateObjectFlowFeature;
import org.modelexecution.xmof.diagram.features.CreateReadStructuralFeatureActionFeature;
import org.modelexecution.xmof.diagram.features.CreateValueSpecificationActionFeature;
import org.modelexecution.xmof.diagram.features.DeleteActionFeature;
import org.modelexecution.xmof.diagram.features.DisallowDeletePinFeature;
import org.modelexecution.xmof.diagram.features.DisallowMovePinFeature;
import org.modelexecution.xmof.diagram.features.DisallowRemovePinFeature;
import org.modelexecution.xmof.diagram.features.DisallowResizePinFeature;
import org.modelexecution.xmof.diagram.features.LayoutActionFeature;
import org.modelexecution.xmof.diagram.features.MoveActionFeature;
import org.modelexecution.xmof.diagram.features.RemoveActionFeature;
import org.modelexecution.xmof.diagram.features.UpdateActionFeature;

public class XMOFFeatureProvider extends DefaultFeatureProvider {

	public XMOFFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object newObject = context.getNewObject();
		if (newObject instanceof Action) {
			return new AddActionFeature(this);
		} else if (isControlOrObjectFlow(newObject)) {
			return new AddFlowFeature(this);
		}
		return super.getAddFeature(context);
	}

	private boolean isControlOrObjectFlow(Object newObject) {
		return newObject instanceof ControlFlow
				|| newObject instanceof ObjectFlow;
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateValueSpecificationActionFeature(this),
				new CreateAddStructuralFeatureValueActionFeature(this),
				new CreateReadStructuralFeatureActionFeature(this) };
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateObjectFlowFeature(this),
				new CreateControlFlowFeature(this) };
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof Action) {
				return new UpdateActionFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof Action) {
			return new LayoutActionFeature(this);
		}
		return super.getLayoutFeature(context);
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getShape());
		if (bo instanceof Pin) {
			return new DisallowMovePinFeature(this);
		} else if (bo instanceof Action) {
			return new MoveActionFeature(this);
		}
		return super.getMoveShapeFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (bo instanceof Pin) {
			return new DisallowDeletePinFeature(this);
		} else if (bo instanceof Action) {
			return new DeleteActionFeature(this);
		}
		return super.getDeleteFeature(context);
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (bo instanceof Pin) {
			return new DisallowRemovePinFeature(this);
		} else if (bo instanceof Action) {
			return new RemoveActionFeature(this);
		}
		return super.getRemoveFeature(context);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (bo instanceof Pin) {
			return new DisallowResizePinFeature(this);
		}
		return super.getResizeShapeFeature(context);
	}

}
