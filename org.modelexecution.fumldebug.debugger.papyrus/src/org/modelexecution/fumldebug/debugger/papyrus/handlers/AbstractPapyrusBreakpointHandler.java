package org.modelexecution.fumldebug.debugger.papyrus.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.breakpoints.ActivityNodeBreakpoint;
import org.modelexecution.fumldebug.debugger.papyrus.provider.PapyrusActivityProvider;

public abstract class AbstractPapyrusBreakpointHandler extends AbstractHandler {

	protected ISelection getSelection() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getSelection();
	}

	protected ISelection getSelection(ExecutionEvent event) {
		return HandlerUtil.getActiveWorkbenchWindow(event).getActivePage()
				.getSelection();
	}

	protected ActivityNode getSelectedActivityNode(ISelection selection) {
		View selectedView = getSelectedView(selection);
		if (selectedView != null) {
			EObject element = selectedView.getElement();
			if (element instanceof ActivityNode) {
				return (ActivityNode) element;
			}
		}
		return null;
	}

	protected View getSelectedView(ISelection selection) {
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement != null && firstElement instanceof ShapeEditPart) {
				ShapeEditPart shapeEditPart = (ShapeEditPart) firstElement;
				Object model = shapeEditPart.getModel();
				if (model instanceof View) {
					return (View) model;
				}
			}
		}
		return null;
	}

	protected IResource getIResource(Resource eResource) {
		URI eUri = eResource.getURI();
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			return ResourcesPlugin.getWorkspace().getRoot()
					.findMember(platformString);
		}
		return null;
	}

	protected IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	protected IBreakpoint getBreakpoint(String qualifiedName,
			IResource iResource) {
		IBreakpoint[] breakpoints = getBreakpoints();
		for (IBreakpoint breakpoint : breakpoints) {
			if (breakpoint instanceof ActivityNodeBreakpoint) {
				ActivityNodeBreakpoint activityNodeBreakpoint = (ActivityNodeBreakpoint) breakpoint;
				IMarker marker = breakpoint.getMarker();
				if (marker.getResource().equals(iResource)) {
					if (qualifiedName.equals(activityNodeBreakpoint
							.getQualifiedNameOfActivityNode())) {
						return breakpoint;
					}
				}
			}
		}
		return null;
	}

	protected IBreakpoint[] getBreakpoints() {
		return getBreakpointManager().getBreakpoints(
				PapyrusActivityProvider.MODEL_TYPE_IDENTIFIER);
	}

}