/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

public class XMOFVMPlugin implements BundleActivator {

	private static BundleContext context;
	public static XMOFVMPlugin instance;

	private ServiceTracker<Object, Object> logServiceTracker;
	private LogService logService;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		XMOFVMPlugin.context = bundleContext;
		XMOFVMPlugin.instance = this;
		logServiceTracker = new ServiceTracker<>(context,
				LogService.class.getName(), null);
		logServiceTracker.open();
		logService = (LogService) logServiceTracker.getService();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		XMOFVMPlugin.context = null;
		logServiceTracker.close();
		logServiceTracker = null;
	}

	public LogService getLog() {
		return logService;
	}

}
