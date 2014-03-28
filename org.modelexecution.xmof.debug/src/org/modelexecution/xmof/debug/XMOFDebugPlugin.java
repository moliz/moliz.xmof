package org.modelexecution.xmof.debug;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMOFDebugPlugin implements BundleActivator {

	public static final String ATT_INIT_MODEL_PATH = "ATT_INIT_MODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_MODEL_PATH = "ATT_MODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_USE_CONFIGURATION_METAMODEL = "ATT_USE_CONFIGURATION_METAMODEL"; //$NON-NLS-1$
	public static final String ATT_CONFIGURATION_METAMODEL_PATH = "ATT_CONFIGURATION_METAMODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_RUNTIME_PROFILE_NSURI = "ATT_RUNTIME_PROFILE_NSURI"; //$NON-NLS-1$
	public static final String ATT_RUNTIME_PROFILE_APPLICATION_FILE_PATH = "ATT_RUNTIME_PROFILE_APPLICATION_FILE_PATH"; //$NON-NLS-1$
	public static final String PROCESS_FACTORY_ID = "org.modelexecution.xmof.debug.processFactory"; //$NON-NLS-1$

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		XMOFDebugPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		XMOFDebugPlugin.context = null;
	}

}
