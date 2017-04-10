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
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;

public class SelectXMOFBasedConfigurationAndOperationFilePage extends SelectXMOFBasedConfigurationFilePage {

	public SelectXMOFBasedConfigurationAndOperationFilePage(ISelection selection,
			ResourceSet resourceSet) {
		super(selection, resourceSet);
	}

	public Collection<EOperation> getSelectedOperations() {
		Collection<EOperation> operations = new ArrayList<EOperation>();
		for(Iterator<Object> iterator = getSelectedObjects().iterator();iterator.hasNext();) {
			Object object = iterator.next();
			if(object instanceof EOperation)
				operations.add((EOperation)object);
		}
		return operations;
	}

	@Override
	public boolean canFlipToNextPage() {
		return haveMetamodel() && haveSelectedOperation();
	}

	private boolean haveSelectedOperation() {
		return getSelectedOperations().size() > 0;
	}

	@Override
	protected String getSelectionString() {
		return "Select an operation";
	}
}