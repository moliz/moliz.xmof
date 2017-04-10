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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class SelectXMOFBasedConfigurationFilePage extends WizardPage implements Listener,
		ISelectionChangedListener {

	private static final String SLASH = "/";

	private static final String XMOF = "xmof";
	private static final String XMOF_EXTENSION = "*.xmof";
	
	private static final String PLATFORM_RESOURCE = "platform:/resource";

	private static final String XMOF_CONF_SELECTION_PAGE = "xmof_conf_selection_page";

	private ResourceSet resourceSet;
	private Resource xmofResource;
	private Collection<Object> selectedObjects = new ArrayList<Object>();
	private ISelection selection;
	
	protected Text uriText;
	protected Button loadButton;
	protected Button browseFileSystemButton;
	protected Button browseWorkspaceButton;

	private TreeViewer eClassesTreeViewer;

	private Label classLabel;

	public SelectXMOFBasedConfigurationFilePage(ISelection selection,
			ResourceSet resourceSet) {
		super(XMOF_CONF_SELECTION_PAGE, "xMOF-based Configuration File", null);
		setDescription("Specify the xMOF-based configuration file.");
		this.resourceSet = resourceSet;
		this.selection = selection;
	}

	public Resource getMetamodelResource() {
		return xmofResource;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_VERTICAL));

		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 8;
		composite.setLayout(layout);

		createURIControl(composite);
		setControl(composite);
		initializeFromSelection();
	}

	protected void createURIControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL));
		{
			FormLayout layout = new FormLayout();
			layout.marginTop = 10;
			layout.spacing = 10;
			composite.setLayout(layout);
		}

		Label uriLabel = new Label(composite, SWT.LEFT);
		{
			FormData data = new FormData();
			data.left = new FormAttachment(0);
			uriLabel.setLayoutData(data);
		}
		uriLabel.setText(getURITextLabel());

		Composite uriComposite = new Composite(composite, SWT.NONE);
		{
			FormData data = new FormData();
			data.top = new FormAttachment(uriLabel, 5);
			data.left = new FormAttachment(0);
			data.right = new FormAttachment(100);
			uriComposite.setLayoutData(data);

			GridLayout layout = new GridLayout(2, false);
			layout.marginTop = -5;
			layout.marginLeft = -5;
			layout.marginRight = -5;
			uriComposite.setLayout(layout);
		}

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		{
			FormData data = new FormData();
			data.top = new FormAttachment(uriLabel, 0, SWT.CENTER);
			data.left = new FormAttachment(uriLabel, 0);
			data.right = new FormAttachment(100);
			buttonComposite.setLayoutData(data);

			FormLayout layout = new FormLayout();
			layout.marginTop = 0;
			layout.marginBottom = 0;
			layout.marginLeft = 0;
			layout.marginRight = 0;
			layout.spacing = 5;
			buttonComposite.setLayout(layout);
		}

		browseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
		browseFileSystemButton
				.setText(getBrowseFileSystemButtonLabel());
		browseFileSystemButton.addListener(SWT.Selection, this);

		browseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
		browseWorkspaceButton.setText(getBrowseWorkspaceButtonLabel());
		browseWorkspaceButton.addListener(SWT.Selection, this);
		browseWorkspaceButton.setFocus();

		{
			FormData data = new FormData();
			data.right = new FormAttachment(browseWorkspaceButton);
			browseFileSystemButton.setLayoutData(data);
		}

		{
			FormData data = new FormData();
			data.right = new FormAttachment(100);
			browseWorkspaceButton.setLayoutData(data);
		}

		uriText = new Text(uriComposite, SWT.SINGLE | SWT.BORDER);
		setURIText(getURITextInitialValue());
		if (uriText.getText().length() > 0) {
			uriText.selectAll();
		}
		uriText.addListener(SWT.Modify, this);

		loadButton = new Button(uriComposite, SWT.PUSH);
		loadButton.setText("Load");
		loadButton.setLayoutData(new GridData(GridData.END));
		loadButton.addListener(SWT.Selection, this);

		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			if (uriComposite.getChildren().length == 1) {
				gridData.horizontalSpan = 2;
			}
			uriText.setLayoutData(gridData);
		}

		classLabel = new Label(parent, SWT.LEFT);
		classLabel.setText(getSelectionString());
		classLabel.setVisible(false);
		eClassesTreeViewer = new TreeViewer(parent);
		GridData treeLayoutData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		treeLayoutData.heightHint = 400;
		eClassesTreeViewer.getTree().setLayoutData(treeLayoutData);
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		eClassesTreeViewer
				.setContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		eClassesTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				adapterFactory));
		eClassesTreeViewer.getTree().setEnabled(false);
		eClassesTreeViewer.addSelectionChangedListener(this);
	}

	protected String getSelectionString() {
		return "";
	}

	protected String getURITextLabel() {
		return "";
	}

	protected String getBrowseFileSystemButtonLabel() {
		return "Browse File System";
	}

	protected String getBrowseWorkspaceButtonLabel() {
		return "Browse Workspace";
	}

	protected String getURITextInitialValue() {
		return "";
	}

	protected void uriTextModified(String text) {
		setErrorMessage(null);
		setMessage(null);
	}

	private void initializeFromSelection() {
		IFile file = getMetamodelFileFromSelection();
		if (file != null && XMOF.equals(file.getFileExtension())) {
			setURIText(PLATFORM_RESOURCE + SLASH + file.getProject().getName()
					+ SLASH + file.getProjectRelativePath().toString());
			loadMetamodel();
			updateEClassTreeViewer();
			loadButton.setFocus();
			getContainer().updateButtons();
		}
	}

	protected void setURIText(String uri) {
		uri = uri.trim();
		StringBuffer text = new StringBuffer(uriText.getText());
		if (!uri.equals(text)) {
			uriText.setText(uri.trim());
		}
		loadMetamodel();
	}

	protected boolean browseFileSystem() {
		FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN
				| SWT.SINGLE);
		fileDialog.setFilterExtensions(new String[] { XMOF_EXTENSION	 });

		if (fileDialog.open() != null && fileDialog.getFileNames().length > 0) {
			String[] fileNames = fileDialog.getFileNames();
			StringBuffer text = new StringBuffer();
			for (int i = 0; i < fileNames.length; ++i) {
				String filePath = fileDialog.getFilterPath() + File.separator
						+ fileNames[i];
				text.append(URI.createFileURI(filePath).toString());
				text.append(" ");
			}
			setURIText(text.toString());
			return true;
		}
		return false;
	}

	protected boolean browseWorkspace() {
		ViewerFilter extensionFilter = null;
		extensionFilter = new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return !(element instanceof IFile)
						|| XMOF.equals(((IFile) element).getFileExtension());
			}
		};

		IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(),
				null, null, false, null, extensionFilter == null ? null
						: Collections.singletonList(extensionFilter));
		if (files.length > 0) {
			StringBuffer text = new StringBuffer();
			for (int i = 0; i < files.length; ++i) {
				text.append(URI.createPlatformResourceURI(files[i]
						.getFullPath().toString(), true));
				text.append("  ");
			}
			setURIText(URI.decode(text.toString()));
			return true;
		}
		return false;
	}

	private IFile getMetamodelFileFromSelection() {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement != null && firstElement instanceof IFile) {
				IFile iFile = (IFile) firstElement;
				if (XMOF.equals(iFile.getFileExtension())) {
					return iFile;
				}

			}
		}
		return null;
	}

	protected boolean loadMetamodel() {
		if (uriText.getText().startsWith("platform:/")) {
			xmofResource = resourceSet.getResource(URI
					.createPlatformResourceURI(
							uriText.getText().replace(PLATFORM_RESOURCE, ""),
							true), true);
		} else {
			EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uriText.getText());
			if (ePackage != null) {
				xmofResource = ePackage.eResource();
			} else {
				xmofResource = null;
			}
		}
		return haveMetamodel();
	}

	@Override
	public void handleEvent(Event event) {

		if (event.type == SWT.Modify && event.widget == uriText) {
			uriTextModified(uriText.getText().trim());
		} else if (event.type == SWT.Selection
				&& event.widget == browseFileSystemButton) {
			browseFileSystem();
		} else if (event.type == SWT.Selection
				&& event.widget == browseWorkspaceButton) {
			browseWorkspace();
		} else if (event.type == SWT.Selection && event.widget == loadButton) {
			loadMetamodel();
		}

		String text = uriText.getText();
		loadButton.setEnabled(text != null && text.trim().length() > 0);

		updateEClassTreeViewer();
		getContainer().updateButtons();
	}

	private void updateEClassTreeViewer() {
		if (haveMetamodel()) {
			eClassesTreeViewer.getTree().setEnabled(true);
			eClassesTreeViewer.setInput(xmofResource);
			eClassesTreeViewer.refresh(true);
			eClassesTreeViewer.getTree().setVisible(true);
			eClassesTreeViewer.expandToLevel(2);
			classLabel.setVisible(true);
		} else {
			eClassesTreeViewer.getTree().setEnabled(false);
			eClassesTreeViewer.getTree().setVisible(false);
			classLabel.setVisible(false);
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		return haveMetamodel();
	}

	protected boolean haveMetamodel() {
		return xmofResource != null
				&& xmofResource.getContents().size() > 0;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		selectedObjects.clear();
		ISelection treeSelection = event.getSelection();
		if (treeSelection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) treeSelection;
			for (Iterator<?> iter = structuredSelection.iterator(); iter
					.hasNext();) {
				Object next = iter.next();
				selectedObjects.add(next);
			}
			getContainer().updateButtons();
		}		
	}
	
	public Collection<Object> getSelectedObjects() {
		return selectedObjects;
	}
}