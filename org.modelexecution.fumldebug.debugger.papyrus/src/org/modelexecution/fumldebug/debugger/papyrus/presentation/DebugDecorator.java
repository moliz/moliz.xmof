/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.presentation;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration.PreferedPosition;
import org.eclipse.papyrus.uml.diagram.common.providers.DiagramDecorationAdapter;
import org.eclipse.papyrus.uml.diagram.common.util.ServiceUtilsForGMF;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;

/**
 * A decorator for annotating debug information.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class DebugDecorator extends AbstractDecorator implements Observer {

	private String viewId;
	private DiagramDecorationAdapter diagramDecorationAdapter;
	private DecorationService decorationService;

	public DebugDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
		diagramDecorationAdapter = new DiagramDecorationAdapter(decoratorTarget);
		EditPart editPart = getDecoratorTargetEditPart();
		try {
			obtainDecorationService(getDiagramEditDomain(editPart));
			obtainViewIdExclusively(getDecoratorTargetView());
		} catch (InterruptedException e) {
			FUMLDebuggerPlugin.log(new Status(IStatus.ERROR,
					FUMLDebuggerPlugin.ID, "ViewID not accessible", e)); //$NON-NLS-1$
		} catch (ServiceException e) {
			FUMLDebuggerPlugin.log(new Status(IStatus.ERROR,
					FUMLDebuggerPlugin.ID,
					"Decoration service not accessible", e)); //$NON-NLS-1$
		}
	}

	private EditPart getDecoratorTargetEditPart() {
		return (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
	}

	private IDiagramEditDomain getDiagramEditDomain(EditPart editPart) {
		return (IDiagramEditDomain) editPart.getViewer().getEditDomain();
	}

	private void obtainDecorationService(IDiagramEditDomain domain)
			throws ServiceException {
		ServicesRegistry serviceRegistry = ServiceUtilsForGMF.getInstance()
				.getServiceRegistry(domain);
		decorationService = serviceRegistry.getService(DecorationService.class);
		decorationService.addListener(this);
	}

	private void obtainViewIdExclusively(final View view)
			throws InterruptedException {
		TransactionUtil.getEditingDomain(view).runExclusive(new Runnable() {
			public void run() {
				DebugDecorator.this.viewId = view != null ? ViewUtil
						.getIdStr(view) : null;
			}
		});
	}

	@Override
	public void refresh() {
		removeDecoration();
		addNewDecoration();
	}

	private void addNewDecoration() {
		View view = getDecoratorTargetView();
		EditPart editPart = getDecoratorTargetEditPart();
		if (isViewNotNull(view) && isEditPartNotNull(editPart)
				&& isGraphicalEditPart(editPart)) {
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration = getDecorationFromService(view);
			if (decoration != null) {
				decoration.setPosition(PreferedPosition.NORTH_WEST);
				if (view instanceof Edge) {
					setEdgeDecoration(decoration);
				} else {
					setNodeDecoration(editPart, decoration);
				}

				setToolTip(decoration.getMessage());
			}
		}
	}

	private Decoration getDecorationFromService(View view) {
		return decorationService.getDecorations().get(ViewUtil.getIdStr(view));
	}

	private boolean isGraphicalEditPart(EditPart editPart) {
		return editPart instanceof org.eclipse.gef.GraphicalEditPart;
	}

	private boolean isEditPartNotNull(EditPart editPart) {
		return editPart != null && editPart.getViewer() != null;
	}

	private boolean isViewNotNull(View view) {
		return view != null && view.eResource() != null;
	}

	private View getDecoratorTargetView() {
		return (View) getDecoratorTarget().getAdapter(View.class);
	}

	private void setEdgeDecoration(
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration) {
		IDecoration iDecoration = diagramDecorationAdapter.setDecoration(
				decoration, 50, 0, true);
		setDecoration(iDecoration);
	}

	private void setNodeDecoration(
			EditPart editPart,
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration) {
		IDecoration iDecoration = diagramDecorationAdapter.setDecoration(
				decoration, 0, computeMargin(editPart), true);
		setDecoration(iDecoration);
	}

	private int computeMargin(EditPart editPart) {
		int margin = -1;
		if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
			margin = MapModeUtil.getMapMode(
					((org.eclipse.gef.GraphicalEditPart) editPart).getFigure())
					.DPtoLP(margin);
		}
		return margin;
	}

	private void setToolTip(String message) {
		Label toolTip = diagramDecorationAdapter.getToolTip(message);
		getDecoration().setToolTip(toolTip);
	}

	@Override
	public void activate() {
		if (viewId != null) {
			DebugDecoratorProvider.addDecorator(viewId, this);
		}
	}

	@Override
	public void deactivate() {
		if (viewId == null) {
			return;
		}
		DebugDecoratorProvider.removeDecorator(viewId);
		super.deactivate();
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}

}
