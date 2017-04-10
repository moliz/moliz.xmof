/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.debug.internal.process;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Tanja
 *
 */
public class ProfileApplicationDisplayEditorRetriever implements Runnable {

	private String editorID;
	private IEditorPart editor = null;
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		retrieveEditor();
	}
	
	private void retrieveEditor() {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();

		IEditorReference[] editorReferences = page.getEditorReferences();
		for (int i = 0; i < editorReferences.length; ++i) {
			IEditorReference editorReference = editorReferences[i];
			if (editorReference.getId().equals(editorID)) {
				editor = editorReference.getEditor(false);
			}
		}
	}
	
	public void setEditorID(String editorID) {
		this.editorID = editorID;
	}

	public IEditorPart getEditor() {
		return editor;
	}

}
