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

import org.eclipse.core.resources.IResource;
import org.modelexecution.fumldebug.debugger.uml2.UML2ActivityProvider;

public class ActivityProviderRegistry {

	private static ActivityProviderRegistry instance;

	public static ActivityProviderRegistry getInstance() {
		if (instance == null) {
			instance = new ActivityProviderRegistry();
		}
		return instance;
	}

	public boolean hasActivityProvider(IResource resource) {
		// TODO implement using extension point
		return new UML2ActivityProvider().canProvide(resource);
	}

	public IActivityProvider getActivityProvider(IResource resource) {
		// TODO implement using extension point
		return new UML2ActivityProvider();
	}

}
