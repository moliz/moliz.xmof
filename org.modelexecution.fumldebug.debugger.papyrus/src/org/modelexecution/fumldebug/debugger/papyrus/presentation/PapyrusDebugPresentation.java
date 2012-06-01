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

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeStackFrame;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeThread;

/**
 * Model presentation implementation for Papyrus UML2 Activity Diagram editors.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusDebugPresentation extends LabelProvider implements
		IDebugModelPresentation {

	private static final String PAPYRUS_MULTIDIAGRAM_EDITOR_ID = "org.eclipse.papyrus.infra.core.papyrusEditor"; //$NON-NLS-1$

	@Override
	public IEditorInput getEditorInput(Object element) {
		if (element instanceof IFile) {
			return new FileEditorInput((IFile) element);
		}
		return null;
	}

	@Override
	public String getEditorId(IEditorInput input, Object element) {
		return PAPYRUS_MULTIDIAGRAM_EDITOR_ID;
	}

	@Override
	public void setAttribute(String attribute, Object value) {
	}

	@Override
	public Image getImage(Object element) {
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		try {
			if (element instanceof ActivityNodeThread) {
				return ((ActivityNodeThread) element).getName();
			} else if (element instanceof ActivityNodeStackFrame) {
				return ((ActivityNodeStackFrame) element).getName();
			}
		} catch (DebugException e) {
			FUMLDebuggerPlugin.log(e);
		}
		return super.getText(element);
	}

	@Override
	public void computeDetail(IValue value, IValueDetailListener listener) {
		// values not implemented yet
	}

}
