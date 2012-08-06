/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.sourcelookup;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputerDelegate;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.WorkspaceSourceContainer;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.launch.ActivityLaunchDelegate;

/**
 * Source path computer delegate for fUML {@link ActivityLaunchDelegate activity
 * launch configurations}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ActivitySourcePathComputerDelegate implements
		ISourcePathComputerDelegate {

	@Override
	public ISourceContainer[] computeSourceContainers(
			ILaunchConfiguration configuration, IProgressMonitor monitor)
			throws CoreException {
		String resourceString = getResourceString(configuration);
		ISourceContainer sourceContainer = createResourceContainerOrWorkspaceContainer(resourceString);
		return new ISourceContainer[] { sourceContainer };
	}

	private String getResourceString(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLDebuggerPlugin.ATT_RESOURCE,
				(String) null);
	}

	private ISourceContainer createResourceContainerOrWorkspaceContainer(
			String resourceString) {
		ISourceContainer sourceContainer = computeSourceContainer(resourceString);
		return sourceContainer != null ? sourceContainer
				: new WorkspaceSourceContainer();
	}

	private ISourceContainer computeSourceContainer(String resourceString) {
		if (resourceString != null) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(new Path(resourceString));
			return computeSourceContainer(resource);
		}
		return null;
	}

	private ISourceContainer computeSourceContainer(IResource resource) {
		if (resource != null) {
			IContainer container = resource.getParent();
			return createSourceContainer(container);
		}
		return null;
	}

	private ISourceContainer createSourceContainer(IContainer container) {
		if (container.getType() == IResource.PROJECT) {
			return new ProjectSourceContainer((IProject) container, false);
		} else if (container.getType() == IResource.FOLDER) {
			return new FolderSourceContainer(container, false);
		}
		return null;
	}

}
