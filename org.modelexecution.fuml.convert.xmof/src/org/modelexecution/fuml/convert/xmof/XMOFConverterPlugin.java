/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.xmof;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMOFConverterPlugin implements BundleActivator {

	public static final String ID = "org.modelexecution.fuml.convert.xmof";
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		XMOFConverterPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		XMOFConverterPlugin.context = null;
	}

}
