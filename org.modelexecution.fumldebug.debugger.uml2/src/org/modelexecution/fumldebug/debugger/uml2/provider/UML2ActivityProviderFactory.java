/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.uml2.provider;

import org.eclipse.core.resources.IResource;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

/**
 * An {@link IActivityProviderFactory} for UML2 activity diagram resources.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2ActivityProviderFactory implements IActivityProviderFactory {

	private static final String FILE_EXT = "uml"; //$NON-NLS-1$

	@Override
	public boolean supports(IResource resource) {
		return FILE_EXT.equals(resource.getFileExtension());
	}

	@Override
	public UML2ActivityProvider createActivityProvider(IResource resource) {
		return new UML2ActivityProvider(resource);
	}

}
