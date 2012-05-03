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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class FUMLDebuggerPlugin implements BundleActivator {

	public static final String ID = "org.modelexecution.fumldebug.debugger"; //$NON-NLS-1$

	public static final String ATT_RESOURCE = "ATT_RESOURCE"; //$NON-NLS-1$
	public static final String ATT_ACTIVITY_NAME = "ATT_NAME"; //$NON-NLS-1$

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		FUMLDebuggerPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		FUMLDebuggerPlugin.context = null;
	}

}
