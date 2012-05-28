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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class FUMLDebuggerPlugin extends Plugin {

	public static final String ID = "org.modelexecution.fumldebug.debugger"; //$NON-NLS-1$
	public static final String PROCESS_FACTORY_ID = "org.modelexecution.fumldebug.debugger.activityProcessFactory"; //$NON-NLS-1$

	public static final String ATT_RESOURCE = "ATT_RESOURCE"; //$NON-NLS-1$
	public static final String ATT_ACTIVITY_NAME = "ATT_NAME"; //$NON-NLS-1$

	private static BundleContext context;
	private static FUMLDebuggerPlugin instance;

	public FUMLDebuggerPlugin() {
		super();
		setDefault(this);
	}

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		FUMLDebuggerPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		FUMLDebuggerPlugin.context = null;
	}

	private static void setDefault(FUMLDebuggerPlugin plugin) {
		instance = plugin;
	}

	public static FUMLDebuggerPlugin getDefault() {
		return instance;
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, ID, t.getMessage(), t);
		log(status);
	}

}
