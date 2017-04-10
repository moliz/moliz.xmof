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
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
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

public class NewXMOFBasedModelWizard extends Wizard implements INewWizard {

	private SelectTargetFilePage selectTargetFilePage;
	private SelectXMOFBasedConfigurationAndClassFilePage selectXMOFConfigurationFilePage;

	private ISelection selection;
	private ResourceSet resourceSet = new ResourceSetImpl();

	public NewXMOFBasedModelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		selectXMOFConfigurationFilePage = new SelectXMOFBasedConfigurationAndClassFilePage(
				selection, resourceSet);
		selectTargetFilePage = new SelectTargetFilePage(selection);
		addPage(selectXMOFConfigurationFilePage);
		addPage(selectTargetFilePage);
	}

	public boolean performFinish() {
		final Resource configurationResource = selectXMOFConfigurationFilePage
				.getMetamodelResource();
		final Collection<EClass> selectedClasses = selectXMOFConfigurationFilePage
				.getSelectedClasses();
		final IFile targetFile = selectTargetFilePage.getTargetFile();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(targetFile, configurationResource,
							selectedClasses, monitor);
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

	private void doFinish(final IFile targetFile,
			Resource configurationResource, Collection<EClass> eClasses,
			IProgressMonitor monitor) throws CoreException {
		if (configurationResource == null) {
			throwCoreException("No xMOF-based configuration file selected");
		}
		if (targetFile == null) {
			throwCoreException("No target file location selected");
		}
		if (eClasses == null || eClasses.size() == 0) {
			throwCoreException("No model object selected");
		}

		monitor.beginTask("Creating " + targetFile.getName(), 2);

		EObject modelObject = EcoreUtil.create(eClasses.iterator().next());

		Resource resource = resourceSet.createResource(URI.createURI(targetFile
				.getFullPath().toPortableString()));
		resource.getContents().add(modelObject);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		try {
			resource.save(options);
		} catch (IOException e1) {
			throwCoreException(e1.getMessage());
		}

		monitor.worked(1);
		monitor.setTaskName("Opening xMOF-based model file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, targetFile, true);
				} catch (PartInitException e) {
				}
			}
		});

		monitor.worked(1);
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