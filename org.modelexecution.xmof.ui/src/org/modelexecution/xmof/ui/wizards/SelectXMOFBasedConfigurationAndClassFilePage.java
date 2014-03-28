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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;

public class SelectXMOFBasedConfigurationAndClassFilePage extends SelectXMOFBasedConfigurationFilePage {

	public SelectXMOFBasedConfigurationAndClassFilePage(ISelection selection,
			ResourceSet resourceSet) {
		super(selection, resourceSet);
	}

	public Collection<EClass> getSelectedClasses() {
		Collection<EClass> classes = new ArrayList<EClass>();
		for(Iterator<Object> iterator = getSelectedObjects().iterator();iterator.hasNext();) {
			Object object = iterator.next();
			if(object instanceof EClass)
				classes.add((EClass)object);
		}
		return classes;
	}

	@Override
	public boolean canFlipToNextPage() {
		return haveMetamodel() && haveSelectedClass();
	}

	private boolean haveSelectedClass() {
		return getSelectedClasses().size() > 0;
	}

	@Override
	protected String getSelectionString() {
		return "Select a model object to be created";
	}
}