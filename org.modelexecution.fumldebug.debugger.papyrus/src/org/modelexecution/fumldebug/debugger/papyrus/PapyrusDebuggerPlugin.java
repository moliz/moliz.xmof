/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.ui.IStartup;
import org.modelexecution.fumldebug.debugger.papyrus.breakpoints.PapyrusBreakpointVisualizer;
import org.osgi.framework.BundleContext;

/**
 * The singleton Papyrus debugger plug-in.
 * 
 * This class is needed for adding the breakpoint visualizer as listener to the
 * breakpoint manager.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusDebuggerPlugin extends Plugin implements IStartup {

	private static PapyrusBreakpointVisualizer breakpointVisualizer;

	public static PapyrusBreakpointVisualizer getBreakpointVisualizer() {
		return breakpointVisualizer;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	private void initializeBreakpointVisualizer() {
		breakpointVisualizer = new PapyrusBreakpointVisualizer();
		IBreakpointManager mgr = getBreakpointManager();
		mgr.addBreakpointListener(breakpointVisualizer);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		IBreakpointManager breakpointManager = getBreakpointManager();
		breakpointManager.removeBreakpointListener(breakpointVisualizer);
	}

	private IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	@Override
	public void earlyStartup() {
		initializeBreakpointVisualizer();
	}

}
