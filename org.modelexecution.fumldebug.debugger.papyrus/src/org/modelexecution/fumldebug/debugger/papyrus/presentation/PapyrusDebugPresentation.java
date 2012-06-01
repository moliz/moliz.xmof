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
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugEditorPresentation;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServiceMultiException;
import org.eclipse.papyrus.infra.core.services.ServiceNotFoundException;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.model.ActivityDebugTarget;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeStackFrame;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeThread;
import org.modelexecution.fumldebug.debugger.papyrus.provider.PapyrusActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;

/**
 * Model presentation implementation for Papyrus UML2 Activity Diagram editors.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusDebugPresentation extends LabelProvider implements
		IDebugModelPresentation, IDebugEditorPresentation {

	private static final String PAPYRUS_MULTIDIAGRAM_EDITOR_ID = "org.eclipse.papyrus.infra.core.papyrusEditor"; //$NON-NLS-1$
	private static final String DECORATION_SERVICE = "DECORATION_SERVICE"; //$NON-NLS-1$

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
			} else if (element instanceof ActivityDebugTarget) {
				return ((ActivityDebugTarget) element).getName();
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

	@Override
	public boolean addAnnotations(IEditorPart editorPart, IStackFrame frame) {
		if (editorPart instanceof PapyrusMultiDiagramEditor
				&& frame instanceof ActivityNodeStackFrame) {
			PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor) editorPart;
			ActivityNodeStackFrame stackFrame = (ActivityNodeStackFrame) frame;
			return addAnnotations(papyrusEditor, stackFrame);
		}
		return false;
	}

	private boolean addAnnotations(PapyrusMultiDiagramEditor editor,
			ActivityNodeStackFrame frame) {
		ActivityNode currentNode = getActivityNode(frame);
		if (currentNode != null) {
			showActivityNode(currentNode, editor);
			annotateCurrentNode(currentNode, editor);
		}
		return false;
	}

	private ActivityNode getActivityNode(ActivityNodeStackFrame frame) {
		PapyrusActivityProvider activityProvider = getPapyrusActivityProvider(frame
				.getActivityDebugTarget());
		Object inputObject = activityProvider.getConversionResult()
				.getInputObject(frame.getActivityNode());
		if (inputObject instanceof ActivityNode) {
			return (ActivityNode) inputObject;
		}
		return null;
	}

	private PapyrusActivityProvider getPapyrusActivityProvider(
			ActivityDebugTarget target) {
		IActivityProvider activityProvider = target.getActivityProvider();
		if (activityProvider instanceof PapyrusActivityProvider) {
			return (PapyrusActivityProvider) activityProvider;
		}
		return null;
	}

	private void showActivityNode(ActivityNode currentNode,
			PapyrusMultiDiagramEditor editor) {
		// TODO Auto-generated method stub

	}

	private void annotateCurrentNode(ActivityNode currentNode,
			PapyrusMultiDiagramEditor editor) {
		DecorationService decorationService = getDecorationService(editor);
		decorationService.addDecoration("CurrentNode", currentNode, 1,
				"This is the current node");
	}

	private DecorationService getDecorationService(
			PapyrusMultiDiagramEditor editor) {
		try {
			if (!haveDecorationService(editor)) {
				configureDecorationService(editor);
			}
			return (DecorationService) editor.getServicesRegistry().getService(
					DECORATION_SERVICE);
		} catch (ServiceException e) {
			FUMLDebuggerPlugin.log(e);
		}
		return null;
	}

	private void configureDecorationService(PapyrusMultiDiagramEditor editor) {
		try {
			DecorationService decorationService = new DecorationService();
			editor.getServicesRegistry().add(DECORATION_SERVICE, 0,
					decorationService);
			editor.getServicesRegistry().startServices(DECORATION_SERVICE);
		} catch (ServiceMultiException e) {
			FUMLDebuggerPlugin.log(e);
		} catch (ServiceNotFoundException e) {
			FUMLDebuggerPlugin.log(e);
		}
	}

	private boolean haveDecorationService(PapyrusMultiDiagramEditor editor)
			throws ServiceException {
		try {
			return editor.getServicesRegistry().getService(DECORATION_SERVICE) != null;
		} catch (ServiceException e) {
			return false;
		}
	}

	@Override
	public void removeAnnotations(IEditorPart editorPart, IThread thread) {
		// TODO Auto-generated method stub
	}

}
