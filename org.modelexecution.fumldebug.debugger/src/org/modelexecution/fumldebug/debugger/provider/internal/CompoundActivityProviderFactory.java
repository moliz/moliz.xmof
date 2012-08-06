/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.provider.internal;

import org.eclipse.core.resources.IResource;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

/**
 * An {@link IActivityProviderFactory activity provider factory} that uses the
 * {@link ActivityProviderFactoryRegistry instance} to create
 * {@link IActivityProvider activity providers}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 */
public class CompoundActivityProviderFactory implements
		IActivityProviderFactory {

	private static final ActivityProviderFactoryRegistry registry = ActivityProviderFactoryRegistry
			.getInstance();

	@Override
	public boolean supports(IResource resource) {
		return registry.hasActivityProviderFactory(resource);
	}

	@Override
	public IActivityProvider createActivityProvider(IResource resource) {
		IActivityProviderFactory providerFactory = registry
				.getActivityProviderFactory(resource);
		return providerFactory != null ? providerFactory
				.createActivityProvider(resource) : null;
	}

}
