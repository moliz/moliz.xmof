package org.modelexecution.xmof.configuration.profile;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class XMOFConfigurationProfilePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.modelexecution.xmof.configuration.profile"; //$NON-NLS-1$
	public static final String RUNTIME_EMFPROFILE_EXTENSION = ".runtime.pa.xmi"; //$NON-NLS-1$

	// The shared instance
	private static XMOFConfigurationProfilePlugin plugin;
	
	public XMOFConfigurationProfilePlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static XMOFConfigurationProfilePlugin getDefault() {
		return plugin;
	}
	
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, t.getMessage(), t);
		log(status);
	}

}
