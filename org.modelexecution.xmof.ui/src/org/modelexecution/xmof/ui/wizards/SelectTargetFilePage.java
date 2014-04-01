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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class SelectTargetFilePage extends WizardNewFileCreationPage {

	public SelectTargetFilePage(ISelection selection) {
		super("xmof", (IStructuredSelection) selection);
		setTitle("Model File");
		setDescription("Specify the model file location and name.");
		setFileName(getFileNameFromSelection(selection));
	}

	private String getFileNameFromSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement != null && firstElement instanceof IFile) {
				IFile iFile = (IFile) firstElement;
				return iFile.getName().replace("." + iFile.getFileExtension(),
						"")
						+ ".xmi";
			}
		}
		return "";
	}

	@Override
	protected boolean validatePage() {
		if (super.validatePage()) {
			String extension = new Path(getFileName()).getFileExtension();
			if (extension == null || !"xmi".equals(extension)) {
				setErrorMessage("The extension must be *.xmi");
				return false;
			}
			return true;
		}
		return false;
	}

	public IFile getTargetFile() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.getFile(getContainerFullPath().append(getFileName()));
	}
}