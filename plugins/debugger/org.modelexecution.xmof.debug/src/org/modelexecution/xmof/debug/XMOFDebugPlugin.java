package org.modelexecution.xmof.debug;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMOFDebugPlugin extends Plugin implements BundleActivator {

	public static final String ATT_INIT_MODEL_PATH = "ATT_INIT_MODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_MODEL_PATH = "ATT_MODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_USE_CONFIGURATION_METAMODEL = "ATT_USE_CONFIGURATION_METAMODEL"; //$NON-NLS-1$
	public static final String ATT_CONFIGURATION_METAMODEL_PATH = "ATT_CONFIGURATION_METAMODEL_PATH"; //$NON-NLS-1$
	public static final String ATT_RUNTIME_PROFILE_NSURI = "ATT_RUNTIME_PROFILE_NSURI"; //$NON-NLS-1$
	public static final String ATT_RUNTIME_PROFILE_APPLICATION_FILE_PATH = "ATT_RUNTIME_PROFILE_APPLICATION_FILE_PATH"; //$NON-NLS-1$
	public static final String PROCESS_FACTORY_ID = "org.modelexecution.xmof.debug.processFactory"; //$NON-NLS-1$

	public static final String MODEL_TYPE_IDENTIFIER = "org.modelexecution.xmof.debug"; //$NON-NLS-1$
	public static final String ID = "org.modelexecution.xmof.debug"; //$NON-NLS-1$

	private static BundleContext context;
	private static XMOFDebugPlugin instance;

	public XMOFDebugPlugin() {
		super();
		setDefault(this);
	}

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		XMOFDebugPlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		XMOFDebugPlugin.context = null;
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, ID, t.getMessage(), t);
		log(status);
	}

	private static void setDefault(XMOFDebugPlugin plugin) {
		instance = plugin;
	}

	public static XMOFDebugPlugin getDefault() {
		return instance;
	}
}
