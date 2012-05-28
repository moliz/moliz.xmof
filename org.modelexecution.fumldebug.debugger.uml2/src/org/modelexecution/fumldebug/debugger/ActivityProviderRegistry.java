/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public class ActivityProviderRegistry {

	private static final String IACTIVITY_PROVIDER_ID = "org.modelexecution.fumldebug.debugger.activityProvider"; //$NON-NLS-1$
	private static final String ACTIVITY_PROVIDER_CLASS = "activityProviderClass"; //$NON-NLS-1$

	private static ActivityProviderRegistry instance;
	private Collection<IActivityProvider> providers = new HashSet<IActivityProvider>();

	public static ActivityProviderRegistry getInstance() {
		if (instance == null) {
			instance = new ActivityProviderRegistry();
		}
		return instance;
	}

	private ActivityProviderRegistry() {
		loadProvidersFromExtensionPoint();
	}

	private void loadProvidersFromExtensionPoint() {
		for (IConfigurationElement element : getExtensionPointConfigElement()) {
			registerProviderFromElement(element);
		}
	}

	private IConfigurationElement[] getExtensionPointConfigElement() {
		return Platform.getExtensionRegistry().getConfigurationElementsFor(
				IACTIVITY_PROVIDER_ID);
	}

	private void registerProviderFromElement(IConfigurationElement element) {
		try {
			IActivityProvider activityProvider = getSpecifiedActivityProvider(element);
			if (activityProvider != null) {
				providers.add(activityProvider);
			}
		} catch (CoreException e) {
			logProviderLoadingFromExtensionError(e);
		}
	}
	
	private IActivityProvider getSpecifiedActivityProvider(
			IConfigurationElement element) throws CoreException {
		Object activityProvider = element
				.createExecutableExtension(ACTIVITY_PROVIDER_CLASS);
		if (activityProvider instanceof IActivityProvider) {
			return (IActivityProvider) activityProvider;
		} else {
			return null;
		}
	}

	private void logProviderLoadingFromExtensionError(CoreException e) {
		FUMLDebuggerPlugin.log(new Status(IStatus.WARNING,
				FUMLDebuggerPlugin.ID,
				"Could not load registered activity provider.", e));
	}

	public boolean hasActivityProvider(IResource resource) {
		return getProviderForResource(resource) != null;
	}

	public IActivityProvider getActivityProvider(IResource resource) {
		return getProviderForResource(resource);
	}

	private IActivityProvider getProviderForResource(IResource resource) {
		for (IActivityProvider provider : providers) {
			if (provider.canProvide(resource))
				return provider;
		}
		return null;
	}

}
