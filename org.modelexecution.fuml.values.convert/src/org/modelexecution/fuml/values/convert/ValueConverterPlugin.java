/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.values.convert;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

public class ValueConverterPlugin implements BundleActivator {

	private static BundleContext context;
	public static ValueConverterPlugin instance;

	private ServiceTracker<Object, Object> logServiceTracker;
	private LogService logService;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		ValueConverterPlugin.context = bundleContext;
		ValueConverterPlugin.instance = this;
		logServiceTracker = new ServiceTracker<>(context,
				LogService.class.getName(), null);
		logServiceTracker.open();
		logService = (LogService) logServiceTracker.getService();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		ValueConverterPlugin.context = null;
		logServiceTracker.close();
		logServiceTracker = null;
	}

	public LogService getLog() {
		return logService;
	}

	public static void log(int level, String message) {
		if (ValueConverterPlugin.instance != null
				&& ValueConverterPlugin.instance.getLog() != null)
			ValueConverterPlugin.instance.getLog().log(level, message);
	}

}
