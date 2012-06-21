package org.modelexecution.fumldebug.debugger.papyrus.decorations;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
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
import org.eclipse.papyrus.uml.diagram.common.providers.DiagramDecorationAdapter;
import org.eclipse.papyrus.uml.diagram.common.util.ServiceUtilsForGMF;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;

/**
 * An abstract decorator for decorating icons on top of a papyrus diagram.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 */
public abstract class AbstractIconDecorator extends AbstractDecorator implements
		Observer {

	private String decorationId;
	private DiagramDecorationAdapter diagramDecorationAdapter;
	private DecorationService decorationService;

	public AbstractIconDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
		diagramDecorationAdapter = new DiagramDecorationAdapter(decoratorTarget);
		EditPart editPart = getDecoratorTargetEditPart();
		try {
			obtainDecorationService(getDiagramEditDomain(editPart));
			obtainDecorationIdExclusively(getDecoratorTargetView());
		} catch (InterruptedException e) {
			FUMLDebuggerPlugin.log(new Status(IStatus.ERROR,
					FUMLDebuggerPlugin.ID, "ViewID not accessible", e)); //$NON-NLS-1$
		} catch (ServiceException e) {
			FUMLDebuggerPlugin.log(new Status(IStatus.ERROR,
					FUMLDebuggerPlugin.ID,
					"Decoration service not accessible", e)); //$NON-NLS-1$
		}
	}

	protected EditPart getDecoratorTargetEditPart() {
		return (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
	}

	protected IDiagramEditDomain getDiagramEditDomain(EditPart editPart) {
		return (IDiagramEditDomain) editPart.getViewer().getEditDomain();
	}

	protected DiagramDecorationAdapter getDiagramDecorationAdapter() {
		return diagramDecorationAdapter;
	}

	private void obtainDecorationService(IDiagramEditDomain domain)
			throws ServiceException {
		ServicesRegistry serviceRegistry = ServiceUtilsForGMF.getInstance()
				.getServiceRegistry(domain);
		decorationService = serviceRegistry.getService(DecorationService.class);
		decorationService.addListener(this);
	}

	private void obtainDecorationIdExclusively(final View view)
			throws InterruptedException {
		TransactionUtil.getEditingDomain(view).runExclusive(new Runnable() {
			public void run() {
				if (view != null) {
					AbstractIconDecorator.this.decorationId = getDecorationId(view);
				} else {
					AbstractIconDecorator.this.decorationId = null;
				}
			}
		});
	}

	/**
	 * Sets the tool tip of the decoration handled by this decorator.
	 * 
	 * @param message
	 *            the tool tip to be set.
	 */
	protected void setToolTip(String message) {
		Label toolTip = getDiagramDecorationAdapter().getToolTip(message);
		getDecoration().setToolTip(toolTip);
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
				setUpDecoration(view, editPart, decoration);
			}
		}
	}

	private void setUpDecoration(
			View view,
			EditPart editPart,
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration) {
		configureDecoration(decoration);
		if (view instanceof Edge) {
			setEdgeDecoration(decoration);
		} else {
			setNodeDecoration(editPart, decoration);
		}
		setToolTip(getToolTipMessage());
	}

	protected void setEdgeDecoration(
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration) {
		IDecoration iDecoration = getDiagramDecorationAdapter().setDecoration(
				decoration, 50, 0, true);
		setDecoration(iDecoration);
	}

	protected void setNodeDecoration(
			EditPart editPart,
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration) {
		IDecoration iDecoration = getDiagramDecorationAdapter().setDecoration(
				decoration, 0, computeMargin(editPart), true);
		setDecoration(iDecoration);
	}

	protected int computeMargin(EditPart editPart) {
		int margin = -1;
		if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
			margin = MapModeUtil.getMapMode(
					((org.eclipse.gef.GraphicalEditPart) editPart).getFigure())
					.DPtoLP(margin);
		}
		return margin;
	}

	private Decoration getDecorationFromService(View view) {
		return decorationService.getDecorations().get(getDecorationId(view));
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

	protected View getDecoratorTargetView() {
		return (View) getDecoratorTarget().getAdapter(View.class);
	}

	@Override
	public void activate() {
		if (decorationId != null) {
			DebugDecoratorProvider.addDecorator(decorationId, this);
		}
	}

	@Override
	public void deactivate() {
		if (decorationId == null) {
			return;
		}
		DebugDecoratorProvider.removeDecorator(decorationId);
		super.deactivate();
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh();
	}

	/**
	 * Returns the ID of this decoration for the specified {@code view}.
	 * 
	 * @param view
	 *            the view for obtaining the decoration ID.
	 * @return the decoration ID.
	 */
	protected abstract String getDecorationId(View view);

	/**
	 * Configures the specified decoration by, for instance, setting the
	 * preferred position etc.
	 * 
	 * @param decoration
	 *            to be configured.
	 */
	protected abstract void configureDecoration(
			org.eclipse.papyrus.infra.services.decoration.util.IDecoration decoration);

	/**
	 * Returns the tool tip message to be set.
	 * 
	 * @return the tool tip message.
	 */
	protected abstract String getToolTipMessage();

}