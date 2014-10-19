/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.convert.uml2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UML2TraceConverterPlugin implements BundleActivator {

	public static final String ID = "org.modelexecution.fuml.trace.convert.uml2";
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		UML2TraceConverterPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		UML2TraceConverterPlugin.context = null;
	}

}
