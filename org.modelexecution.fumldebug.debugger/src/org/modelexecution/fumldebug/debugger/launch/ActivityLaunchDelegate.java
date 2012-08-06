/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.launch;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.model.ActivityDebugTarget;
import org.modelexecution.fumldebug.debugger.process.internal.InternalActivityProcess;
import org.modelexecution.fumldebug.debugger.process.internal.InternalActivityProcess.Mode;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityLaunchDelegate extends LaunchConfigurationDelegate {

	private static final String ACTIVITY_EXEC_LABEL = "fUML Activity Execution Process";

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		String resourcePath = getResourcePath(configuration);
		String activityName = getActivityName(configuration);
		IResource iResource = loadResource(resourcePath);

		IActivityProvider activityProvider = loadActivityProvider(iResource);
		Activity activity = activityProvider.getActivity(activityName);

		InternalActivityProcess activityProcess = new InternalActivityProcess(
				activity, getProcessMode(mode));

		IProcess process = DebugPlugin.newProcess(launch, activityProcess,
				ACTIVITY_EXEC_LABEL);

		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			ActivityDebugTarget debugTarget = new ActivityDebugTarget(launch,
					process, activityProvider);
			launch.addDebugTarget(debugTarget);
		}
	}

	private String getResourcePath(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLDebuggerPlugin.ATT_RESOURCE,
				(String) null);
	}

	private String getActivityName(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLDebuggerPlugin.ATT_ACTIVITY_NAME,
				(String) null);
	}

	private IResource loadResource(String resourcePath) {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(resourcePath);
	}

	private IActivityProvider loadActivityProvider(IResource iResource) {
		IActivityProviderFactory providerFactory = IActivityProviderFactory.instance;
		if (iResource.exists() && providerFactory.supports(iResource)) {
			return providerFactory.createActivityProvider(iResource);
		}
		return null;
	}

	private Mode getProcessMode(String mode) {
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return Mode.DEBUG;
		} else {
			return Mode.RUN;
		}
	}

	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		return false;
	}

}
