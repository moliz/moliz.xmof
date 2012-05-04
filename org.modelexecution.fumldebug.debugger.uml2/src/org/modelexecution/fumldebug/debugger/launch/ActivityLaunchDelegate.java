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

import java.util.Collection;
import java.util.Collections;

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
import org.modelexecution.fumldebug.debugger.ActivityProviderRegistry;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.IActivityProvider;
import org.modelexecution.fumldebug.debugger.launch.internal.InternalActivityProcess;
import org.modelexecution.fumldebug.debugger.launch.internal.InternalActivityProcess.Mode;
import org.modelexecution.fumldebug.debugger.model.ActivityDebugTarget;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityLaunchDelegate extends LaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		Activity activity = loadActivity(configuration);
		InternalActivityProcess activityProcess = new InternalActivityProcess(
				activity, getProcessMode(mode));

		IProcess process = DebugPlugin.newProcess(launch, activityProcess,
				"RunningActivity");

		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			ActivityDebugTarget debugTarget = new ActivityDebugTarget(launch,
					process);
			launch.addDebugTarget(debugTarget);
		}
	}

	private Activity loadActivity(ILaunchConfiguration configuration)
			throws CoreException {
		String resourcePath = getResourcePath(configuration);
		String activityName = getActivityName(configuration);
		Collection<Activity> allActivities = getAllActivities(resourcePath);
		Activity activity = getActivityByName(allActivities, activityName);
		return activity;
	}

	private String getActivityName(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLDebuggerPlugin.ATT_ACTIVITY_NAME,
				(String) null);
	}

	private String getResourcePath(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLDebuggerPlugin.ATT_RESOURCE,
				(String) null);
	}

	private Collection<Activity> getAllActivities(String resourcePath) {
		Collection<Activity> allActivities = Collections.emptyList();
		ActivityProviderRegistry activityProviderRegistry = ActivityProviderRegistry
				.getInstance();
		IResource iResource = loadResource(resourcePath);
		if (iResource.exists()
				&& activityProviderRegistry.hasActivityProvider(iResource)) {
			IActivityProvider activityProvider = activityProviderRegistry
					.getActivityProvider(iResource);
			allActivities = activityProvider.getActivities(iResource);
		}
		return allActivities;
	}

	private IResource loadResource(String resourcePath) {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(resourcePath);
	}

	private Activity getActivityByName(Collection<Activity> allActivities,
			String activityName) {
		for (Activity activity : allActivities) {
			if (activityName.equals(activity.name)) {
				return activity;
			}
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

}
