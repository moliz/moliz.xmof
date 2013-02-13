/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel.presentation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DefaultPersistencyBehavior;
import org.eclipse.graphiti.ui.editor.DefaultUpdateBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class DiagramEditorInternal extends DiagramEditor {

	TransactionalEditingDomain editingDomain;

	public DiagramEditorInternal(TransactionalEditingDomain editingDomain) {
		super();
		this.editingDomain = editingDomain;
	}

	@Override
	protected DefaultPersistencyBehavior createPersistencyBehavior() {
		// replaces default persistency behavior as it leads to NPE when
		// obtaining shell (getShell())
		return new DefaultPersistencyBehavior(this) {
			public void saveDiagram(IProgressMonitor monitor) {
				// set version info.
				final Diagram diagram = diagramEditor.getDiagramTypeProvider()
						.getDiagram();
				setDiagramVersion(diagram);

				Map<Resource, Map<?, ?>> saveOptions = createSaveOptions();
				final Set<Resource> savedResources = new HashSet<Resource>();

				diagramEditor.disableAdapters();
				try {
					savedResources.addAll(save(
							diagramEditor.getEditingDomain(), saveOptions));

					BasicCommandStack commandStack = (BasicCommandStack) diagramEditor
							.getEditingDomain().getCommandStack();
					commandStack.saveIsDone();

					// Store the last executed command on the undo stack as save
					// point
					// and refresh the dirty state of the editor
					savedCommand = commandStack.getUndoCommand();
				} catch (final Exception exception) {
					XMOFEditorPlugin.INSTANCE.log(exception);
				}
				diagramEditor.enableAdapters();

				Resource[] savedResourcesArray = savedResources
						.toArray(new Resource[savedResources.size()]);
				diagramEditor.commandStackChanged(null);
				IDiagramTypeProvider provider = diagramEditor
						.getDiagramTypeProvider();
				provider.resourcesSaved(diagramEditor.getDiagramTypeProvider()
						.getDiagram(), savedResourcesArray);
			}
		};
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof KernelEditor
				&& this.equals(((KernelEditor) part).getSelectedPage())) {
			updateActions(getSelectionActions());
		}
	}

	public Diagram getDiagram() {
		return getDiagramTypeProvider().getDiagram();
	}

	@Override
	protected DefaultUpdateBehavior createUpdateBehavior() {
		return new DefaultUpdateBehavior(this) {
			@Override
			protected void createEditingDomain() {
				initializeEditingDomain(editingDomain);
			}
		};
	}

}
