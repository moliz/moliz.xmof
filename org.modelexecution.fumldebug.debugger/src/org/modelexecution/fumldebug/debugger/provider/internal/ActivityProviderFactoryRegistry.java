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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

/**
 * Registry for {@link IActivityProvider activity providers}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ActivityProviderFactoryRegistry {

	private static final String IACTIVITY_PROVIDER_FACTORY_ID = "org.modelexecution.fumldebug.debugger.activityProviderFactory"; //$NON-NLS-1$
	private static final String ACTIVITY_PROVIDER_FACTORY_CLASS_ATT = "activityProviderFactoryClass"; //$NON-NLS-1$

	private static ActivityProviderFactoryRegistry instance;
	private Collection<IActivityProviderFactory> providerFactories = new HashSet<IActivityProviderFactory>();

	protected static ActivityProviderFactoryRegistry getInstance() {
		if (instance == null) {
			instance = new ActivityProviderFactoryRegistry();
		}
		return instance;
	}

	private ActivityProviderFactoryRegistry() {
		loadProviderFactoriesFromExtensionPoint();
	}

	private void loadProviderFactoriesFromExtensionPoint() {
		for (IConfigurationElement element : getExtensionPointConfigElement()) {
			registerProviderFactoryFromElement(element);
		}
	}

	private IConfigurationElement[] getExtensionPointConfigElement() {
		return Platform.getExtensionRegistry().getConfigurationElementsFor(
				IACTIVITY_PROVIDER_FACTORY_ID);
	}

	private void registerProviderFactoryFromElement(
			IConfigurationElement element) {
		try {
			IActivityProviderFactory factory = getSpecifiedActivityProviderFactory(element);
			if (factory != null) {
				providerFactories.add(factory);
			}
		} catch (CoreException e) {
			logProviderLoadingFromExtensionError(e);
		}
	}

	private IActivityProviderFactory getSpecifiedActivityProviderFactory(
			IConfigurationElement element) throws CoreException {
		Object factory = element
				.createExecutableExtension(ACTIVITY_PROVIDER_FACTORY_CLASS_ATT);
		if (factory instanceof IActivityProviderFactory) {
			return (IActivityProviderFactory) factory;
		} else {
			return null;
		}
	}

	private void logProviderLoadingFromExtensionError(CoreException e) {
		FUMLDebuggerPlugin.log(new Status(IStatus.WARNING,
				FUMLDebuggerPlugin.ID,
				"Could not load registered activity provider factory.", e));
	}

	/**
	 * Specifies whether this instance contains an
	 * {@link IActivityProviderFactory} for the specified {@code resource}.
	 * 
	 * @param resource
	 *            the {@link IResource} in question.
	 * @return <code>true</code> if this instance has an appropriate provider,
	 *         <code>false</code> otherwise.
	 */
	public boolean hasActivityProviderFactory(IResource resource) {
		return getProviderFactoryForResource(resource) != null;
	}

	/**
	 * Returns the {@link IActivityProviderFactory} for the specified
	 * {@code resource} or <code>null</code> if no appropriate
	 * {@link IActivityProviderFactory} is available.
	 * 
	 * @param resource
	 *            to get {@link IActivityProviderFactory} for.
	 * @return the {@link IActivityProviderFactory} or <code>null</code> if no
	 *         appropriate factory is available.
	 */
	public IActivityProviderFactory getActivityProviderFactory(
			IResource resource) {
		return getProviderFactoryForResource(resource);
	}

	private IActivityProviderFactory getProviderFactoryForResource(
			IResource resource) {
		for (IActivityProviderFactory factory : providerFactories) {
			if (factory.supports(resource))
				return factory;
		}
		return null;
	}

}
