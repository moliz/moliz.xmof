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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.papyrus.breakpoints.PapyrusActivityNodeBreakpoint;

/**
 * Implements the command for removing a {@link PapyrusActivityNodeBreakpoint
 * activity node breakpoint}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusRemoveActivityNodeBreakpointHandler extends
		AbstractPapyrusBreakpointHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = getSelection(event);
		ActivityNode activityNode = getSelectedActivityNode(selection);
		if (activityNode != null) {
			String qualifiedName = activityNode.getQualifiedName();
			IResource iResource = getIResource(activityNode.eResource());
			removeBreakpoint(qualifiedName, iResource);
		}
		return null;
	}

	private void removeBreakpoint(String qualifiedName, IResource iResource) {
		IBreakpoint breakpoint = getBreakpoint(qualifiedName, iResource);
		if (breakpoint != null) {
			try {
				getBreakpointManager().removeBreakpoint(breakpoint, true);
			} catch (CoreException e) {
				FUMLDebuggerPlugin.log(e);
			}
		}
	}
	
	@Override
	public void setEnabled(Object evaluationContext) {
		ISelection selection = getSelection();
		ActivityNode activityNode = getSelectedActivityNode(selection);
		if (activityNode != null) {
			String qualifiedName = activityNode.getQualifiedName();
			IResource iResource = getIResource(activityNode.eResource());
			setBaseEnabled(haveBreakpoint(qualifiedName, iResource));
		} else {
			setBaseEnabled(false);
		}
	}

	private boolean haveBreakpoint(String qualifiedName, IResource iResource) {
		return getBreakpoint(qualifiedName, iResource) != null;
	}

}
