/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.breakpoints.ActivityNodeBreakpoint;
import org.modelexecution.fumldebug.debugger.papyrus.breakpoints.PapyrusActivityNodeBreakpoint;

/**
 * Implements the command for adding a {@link PapyrusActivityNodeBreakpoint
 * activity node breakpoint}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusAddActivityNodeBreakpointHandler extends
		AbstractPapyrusBreakpointHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = getSelection(event);
		ActivityNode activityNode = getSelectedActivityNode(selection);
		if (activityNode != null) {
			String qualifiedName = activityNode.getQualifiedName();
			IResource iResource = getIResource(activityNode.eResource());
			if (haveNoBreakpoint(qualifiedName, iResource)) {
				addBreakpoint(qualifiedName, iResource);
			}
		}
		return null;
	}

	private boolean haveNoBreakpoint(String qualifiedName, IResource iResource) {
		return getBreakpoint(qualifiedName, iResource) == null;
	}

	private void addBreakpoint(String qualifiedName, IResource iResource) {
		try {
			IMarker marker = iResource
					.createMarker(ActivityNodeBreakpoint.MARKER_ID);
			marker.setAttribute(ActivityNodeBreakpoint.ACTIVITY_NODE_LOCATION,
					qualifiedName);
			PapyrusActivityNodeBreakpoint activityNodeBreakpoint = new PapyrusActivityNodeBreakpoint();
			activityNodeBreakpoint.setMarker(marker);
			activityNodeBreakpoint.setEnabled(true);
			getBreakpointManager().addBreakpoint(activityNodeBreakpoint);
		} catch (CoreException e) {
			FUMLDebuggerPlugin.log(e);
		}
	}

	@Override
	public void setEnabled(Object evaluationContext) {
		ISelection selection = getSelection();
		ActivityNode activityNode = getSelectedActivityNode(selection);
		if (activityNode != null) {
			String qualifiedName = activityNode.getQualifiedName();
			IResource iResource = getIResource(activityNode.eResource());
			setBaseEnabled(haveNoBreakpoint(qualifiedName, iResource));
		} else {
			setBaseEnabled(false);
		}
	}

}
