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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugEditorPresentation;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.model.ActivityDebugTarget;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeStackFrame;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeThread;
import org.modelexecution.fumldebug.debugger.papyrus.decorations.DebugDecoratorProvider;
import org.modelexecution.fumldebug.debugger.papyrus.provider.PapyrusActivityProvider;
import org.modelexecution.fumldebug.papyrus.util.DiResourceUtil;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;

/**
 * Model presentation implementation for Papyrus UML2 Activity Diagram editors.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusDebugPresentation extends LabelProvider implements
		IDebugModelPresentation, IDebugEditorPresentation {

	protected static final String CURRENT_ACTIVITY_NODE_MSG = "CURRENT_ACTIVITY_NODE_MSG";
	private static final String PAPYRUS_MULTIDIAGRAM_EDITOR_ID = "org.eclipse.papyrus.infra.core.papyrusEditor"; //$NON-NLS-1$
	private static final String CURRENT_NODE_DECORATION_ID_POSTFIX = "_debug_current_node"; //$NON-NLS-1$

	public static String getCurrentNodeDecorationId(View view) {
		return ViewUtil.getIdStr(view) + CURRENT_NODE_DECORATION_ID_POSTFIX;
	}

	private Collection<View> annotatedViews = new HashSet<View>();

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
		ActivityNode currentNode = getCurrentActivityNode(editor, frame);
		View currentNodeNotation = DiResourceUtil.getNotationElement(
				currentNode, getDiResource(editor));
		if (currentNodeNotation != null) {
			showNode(currentNodeNotation, editor);
			annotateCurrentNode(currentNodeNotation, editor);
			return true;
		}
		return false;
	}

	private ActivityNode getCurrentActivityNode(
			PapyrusMultiDiagramEditor editor, ActivityNodeStackFrame frame) {
		ActivityNode currentNodeIntern = getActivityNode(frame);
		ActivityNode currentNode = (ActivityNode) getCorrespondingEObject(
				editor, currentNodeIntern);
		return currentNode;
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

	private Resource getDiResource(PapyrusMultiDiagramEditor editor) {
		for (Resource resource : getResourceSet(editor).getResources()) {
			if (DiResourceUtil.isDiResource(resource)) {
				return resource;
			}
		}
		return null;
	}

	private ResourceSet getResourceSet(PapyrusMultiDiagramEditor editor) {
		return editor.getEditingDomain().getResourceSet();
	}

	private EObject getCorrespondingEObject(PapyrusMultiDiagramEditor editor,
			EObject eObject) {
		return getResourceSet(editor).getEObject(EcoreUtil.getURI(eObject),
				true);
	}

	private void showNode(View nodeToShow, PapyrusMultiDiagramEditor editor) {
		openPageContainingNode(nodeToShow, editor);
		revealNode(nodeToShow, editor);
	}

	private void openPageContainingNode(View nodeToShow,
			PapyrusMultiDiagramEditor editor) {
		try {
			ServicesRegistry serviceRegistry = editor.getServicesRegistry();
			IPageMngr iPageMngr = ServiceUtils.getInstance().getIPageMngr(
					serviceRegistry);
			if (iPageMngr.isOpen(nodeToShow.getDiagram())) {
				iPageMngr.closePage(nodeToShow.getDiagram());
			}
			iPageMngr.openPage(nodeToShow.getDiagram());
		} catch (ServiceException e) {
			FUMLDebuggerPlugin.log(e);
		}
	}

	private void revealNode(View nodeToShow, PapyrusMultiDiagramEditor editor) {
		EditPart editPartToShow = DiagramEditPartsUtil.getEditPartFromView(
				nodeToShow, editor.getDiagramEditPart());
		editor.getDiagramGraphicalViewer().reveal(editPartToShow);
	}

	private void annotateCurrentNode(View view, PapyrusMultiDiagramEditor editor) {
		try {
			ServicesRegistry serviceRegistry = editor.getServicesRegistry();
			DecorationService decorationService = serviceRegistry
					.getService(DecorationService.class);
			removeOldCurrentNodeAnnotation(decorationService);
			addNewCurrentNodeAnnotation(view, decorationService);
		} catch (ServiceException e) {
			FUMLDebuggerPlugin.log(e);
		}
	}

	private void removeOldCurrentNodeAnnotation(
			DecorationService decorationService) {
		for (View view : annotatedViews) {
			decorationService
					.removeDecoration(getCurrentNodeDecorationId(view));
		}
		annotatedViews.clear();
	}

	private void addNewCurrentNodeAnnotation(View view,
			DecorationService decorationService) {
		String decorationId = getCurrentNodeDecorationId(view);
		decorationService.addDecoration(decorationId, view,
				getCurrentNodeImage(), CURRENT_ACTIVITY_NODE_MSG);
		saveAnnotatedView(view);
	}

	private ImageDescriptor getCurrentNodeImage() {
		return DebugUITools
				.getImageDescriptor(IDebugUIConstants.IMG_OBJS_INSTRUCTION_POINTER_TOP);
	}

	private void saveAnnotatedView(View view) {
		annotatedViews.add(view);
	}

	@Override
	public void removeAnnotations(IEditorPart editorPart, IThread thread) {
		if (editorPart instanceof PapyrusMultiDiagramEditor) {
			try {
				PapyrusMultiDiagramEditor editor = (PapyrusMultiDiagramEditor) editorPart;
				ServicesRegistry serviceRegistry = editor.getServicesRegistry();
				DecorationService decorationService = serviceRegistry
						.getService(DecorationService.class);
				for (View view : annotatedViews) {
					// TODO maybe check whether the view is really a child
					// element of thread
					String id = getCurrentNodeDecorationId(view);
					decorationService.removeDecoration(id);
					DebugDecoratorProvider.refreshDecorators(view, id);
				}
			} catch (ServiceException s) {
				FUMLDebuggerPlugin.log(s);
			}
		}
	}

	@Override
	public void computeDetail(IValue value, IValueDetailListener listener) {
		// values not implemented yet
	}

	@Override
	public void setAttribute(String attribute, Object value) {
	}

}
