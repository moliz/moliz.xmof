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
