/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.ui.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.PlatformUI;

public class SelectModelEditorPage extends WizardPage {

	private static final String MODEL_EDITOR_SELECTION_PAGE = "model_editor_selection_page";

	private ResourceManager resourceManager;
	
	private Table editorTable;
	
	private List<IEditorDescriptor> internalEditors;
	private IEditorDescriptor selectedEditor;
	
	public SelectModelEditorPage() {
		super(MODEL_EDITOR_SELECTION_PAGE, "Model Editor", null);
		resourceManager = new LocalResourceManager(JFaceResources.getResources());
		setDescription("Select preferred model editor.");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_VERTICAL));

		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 8;
		composite.setLayout(layout);
		
		editorTable = new Table(composite, SWT.SINGLE | SWT.BORDER);
		editorTable.addListener(SWT.Selection, new EditorSelectionListener());
		GridData data = new GridData();
		data.widthHint = convertHorizontalDLUsToPixels(200);
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.verticalAlignment = GridData.FILL;
		data.grabExcessVerticalSpace = true;
		data.horizontalSpan = 2;
		editorTable.setLayoutData(data);
		data.heightHint = editorTable.getItemHeight() * 12;
		TableViewer editorTableViewer = new TableViewer(editorTable);
		editorTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		editorTableViewer.setLabelProvider(new ColumnLabelProvider() {
			
			public String getText(Object element) {
				IEditorDescriptor d = (IEditorDescriptor) element;
				return TextProcessor.process(d.getLabel(), "."); //$NON-NLS-1$
			}
			
			public Image getImage(Object element) {
				IEditorDescriptor d = (IEditorDescriptor) element;
				return (Image) resourceManager.get(d.getImageDescriptor());
			}

		});
		editorTableViewer.setInput(getInternalEditors());
		setControl(composite);
	}

	protected List<IEditorDescriptor> getInternalEditors() {
		if (internalEditors == null) {
			internalEditors = new ArrayList<IEditorDescriptor>();
			IEditorRegistry reg = PlatformUI.getWorkbench()
					.getEditorRegistry();
			IFileEditorMapping[] fileEditorMappings = reg.getFileEditorMappings();
			for(int i=0;i<fileEditorMappings.length;++i) {
				IFileEditorMapping fileEditorMapping = fileEditorMappings[i];
				IEditorDescriptor[] editors = fileEditorMapping.getEditors();
				for(int j=0;j<editors.length;++j) {
					IEditorDescriptor editor = editors[j];
					if (!internalEditors.contains(editor))
						internalEditors.add(editor);
				}
			}
		}
		Collections.sort(internalEditors, new EditorDescriptorComparator());
		return internalEditors;
	}
	
	public String getEditorID() {
		if (selectedEditor != null)
			return selectedEditor.getId();
		return null;
	}
	
	private class EditorSelectionListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			if (event.widget == editorTable) {
				if (editorTable.getSelectionIndex() != -1)
					selectedEditor = (IEditorDescriptor)editorTable.getSelection()[0].getData();
				else
					selectedEditor = null;
			}	
		}
		
	}
	
	private class EditorDescriptorComparator implements Comparator<IEditorDescriptor> {

		@Override
		public int compare(IEditorDescriptor editor1, IEditorDescriptor editor2) {
			return editor1.getLabel().compareTo(editor2.getLabel());
		}
		
	}

}