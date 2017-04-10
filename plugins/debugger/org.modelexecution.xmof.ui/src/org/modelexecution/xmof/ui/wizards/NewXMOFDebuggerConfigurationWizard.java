/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.ui.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import modeldebuggerconfig.DebuggerConfiguration;
import modeldebuggerconfig.ModeldebuggerconfigFactory;
import modeldebuggerconfig.ModeldebuggerconfigPackage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.modelexecution.xmof.ui.XMOFUIPlugin;

public class NewXMOFDebuggerConfigurationWizard extends Wizard implements INewWizard {	
	
	private SelectModelEditorPage selectModelEditorPage;
	private SelectXMOFBasedConfigurationFilePage selectXMOFConfigurationFilePage;

	private ISelection selection;
	private ResourceSet resourceSet = new ResourceSetImpl();

	public NewXMOFDebuggerConfigurationWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		selectXMOFConfigurationFilePage = new SelectXMOFBasedConfigurationFilePage(
				selection, resourceSet);		
		selectModelEditorPage = new SelectModelEditorPage();
		addPage(selectXMOFConfigurationFilePage);
		addPage(selectModelEditorPage);
	}

	public boolean performFinish() {
		final Resource configurationResource = selectXMOFConfigurationFilePage
				.getMetamodelResource();
		final String editorID = selectModelEditorPage.getEditorID();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(editorID, configurationResource,
							monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			return false;
		}
		return true;
	}

	private void doFinish(String editorID, Resource configurationResource,
			IProgressMonitor monitor) throws CoreException {
		if (configurationResource == null) {
			throwCoreException("No xMOF-based configuration file selected");
		}
		
		monitor.beginTask("Creating configuration", 2);
		DebuggerConfiguration debugConfig = createDebuggerConfiguration(editorID,
				configurationResource);
		String debugConfigFilePath = getDebugConfigFilePath(configurationResource);
		IFile debugConfigFile = saveDebuggerConfiguration(debugConfig, debugConfigFilePath);		
		monitor.worked(1);
		
		monitor.setTaskName("Opening configuration for editing...");
		openDebuggerConfigurationFile(debugConfigFile);
		monitor.worked(1);
	}

	private String getDebugConfigFilePath(Resource configurationResource) {
		URI confURI = configurationResource.getURI();
		String confPath = confURI.toPlatformString(true);
		String confFilename = confURI.lastSegment();
		
		String debugConfPath = confPath.substring(0, confPath.length() - confFilename.length());
		debugConfPath = debugConfPath + ModeldebuggerconfigPackage.MODEL_DEBUGGER_CONFIG_FILENAME;
		
		return debugConfPath;
	}

	private void openDebuggerConfigurationFile(final IFile debugConfigFile) {
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, debugConfigFile, true);
				} catch (PartInitException e) {
				}
			}
		});
	}

	private IFile saveDebuggerConfiguration(DebuggerConfiguration debugConfig, String targetFilePath) throws CoreException {
		IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(targetFilePath));
		
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = registry.getExtensionToFactoryMap();
	    m.put(ModeldebuggerconfigPackage.MODEL_DEBUGGER_CONFIG_FILEEXTENSION, new XMIResourceFactoryImpl());
		
		Resource resource = resourceSet.createResource(URI.createURI(targetFile
				.getFullPath().toPortableString()));
		resource.getContents().add(debugConfig);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		try {
			resource.save(options);
		} catch (IOException e) {
			throwCoreException(e.getMessage());
		}		
		return targetFile;
	}

	private DebuggerConfiguration createDebuggerConfiguration(String editorID,
			Resource configurationResource) {
		DebuggerConfiguration debugConfig = ModeldebuggerconfigFactory.eINSTANCE.createDebuggerConfiguration();
		debugConfig.setEditorID(editorID);
		debugConfig.setConfigurationPackage(getConfigurationRootPackage(configurationResource));
		return debugConfig;
	}
	
	private EPackage getConfigurationRootPackage(Resource configurationResource) {
		EObject eObject = configurationResource.getContents().get(0);
		if(eObject instanceof EPackage) {
			return (EPackage)eObject;
		}
		return null;
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, XMOFUIPlugin.PLUGIN_ID,
				IStatus.OK, message, null);
		throw new CoreException(status);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}