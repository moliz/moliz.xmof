package org.modelexecution.xmof.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMOFUIPlugin implements BundleActivator {

	private static BundleContext context;
	public static final String PLUGIN_ID = "org.modelexecution.xmof.ui";

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		XMOFUIPlugin.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		XMOFUIPlugin.context = null;
	}

}
