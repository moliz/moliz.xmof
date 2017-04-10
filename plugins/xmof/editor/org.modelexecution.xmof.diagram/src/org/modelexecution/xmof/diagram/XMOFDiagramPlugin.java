package org.modelexecution.xmof.diagram;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class XMOFDiagramPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.modelexecution.xmof.diagram"; //$NON-NLS-1$
	public static final String DIAGRAM_TYPE_ID = "org.modelexecution.xmof.diagram.XMOFDiagramType"; //$NON-NLS-1$
	public static final String DIAGRAM_TYPE = "xMOF"; //$NON-NLS-1$
	public static final String DIAGRAM_TYPE_NAME = DIAGRAM_TYPE + " Diagram"; //$NON-NLS-1$
	
	public static final String DECORATOR_PROVIDER_EXTENSION_ID = "org.modelexecution.xmof.diagram.DecorationProvider";
	public static final String DECORATOR_PROVIDER_EXTENSION_ATTRIBUTE_CLASS = "decoratorProviderClass";

	// The shared instance
	private static XMOFDiagramPlugin plugin;
	
	/**
	 * The constructor
	 */
	public XMOFDiagramPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static XMOFDiagramPlugin getDefault() {
		return plugin;
	}

}
