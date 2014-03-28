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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
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
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.ui.XMOFUIPlugin;

public class NewParameterValueDefinitionlWizard extends Wizard implements
		INewWizard {

	private static final BasicBehaviorsPackage BASIC_BEHAVIORS_PACKAGE = BasicBehaviorsPackage.eINSTANCE;

	private SelectTargetFilePage selectTargetFilePage;
	private SelectXMOFBasedConfigurationAndOperationFilePage selectXMOFConfigurationFilePage;

	private ISelection selection;
	private ResourceSet resourceSet = new ResourceSetImpl();

	public NewParameterValueDefinitionlWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void addPages() {
		selectXMOFConfigurationFilePage = new SelectXMOFBasedConfigurationAndOperationFilePage(
				selection, resourceSet);
		selectTargetFilePage = new SelectTargetFilePage(selection);
		addPage(selectXMOFConfigurationFilePage);
		addPage(selectTargetFilePage);
	}

	public boolean performFinish() {
		final Resource configurationResource = selectXMOFConfigurationFilePage
				.getMetamodelResource();
		final Collection<EOperation> selectedOperations = selectXMOFConfigurationFilePage
				.getSelectedOperations();
		final IFile targetFile = selectTargetFilePage.getTargetFile();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					doFinish(targetFile, configurationResource,
							selectedOperations, monitor);
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
			Resource configurationResource, Collection<EOperation> eOperations,
			IProgressMonitor monitor) throws CoreException {
		if (configurationResource == null) {
			throwCoreException("No xMOF-based configuration file selected");
		}
		if (targetFile == null) {
			throwCoreException("No target file location selected");
		}
		if (eOperations == null || eOperations.size() == 0) {
			throwCoreException("No operation selected");
		}

		BehavioredEOperation behavioredEOperation = getBehavioredEOperation(eOperations);
		if(behavioredEOperation == null) {
			throwCoreException("No operation with attached behavior selected");
		}
				
		monitor.beginTask("Creating " + targetFile.getName(), 2);
		
		List<EParameter> behaviorParameters = getBehaviorParameters(behavioredEOperation);
		
		EObject parameterValueDefinition = EcoreUtil.create(BASIC_BEHAVIORS_PACKAGE.getParameterValueDefinition());
		EList<EObject> parameterValues = new BasicEList<EObject>();
		for(EParameter behaviorParameter : behaviorParameters) {
			EObject parameterValue = EcoreUtil.create(BASIC_BEHAVIORS_PACKAGE.getParameterValue());
			parameterValue.eSet(BASIC_BEHAVIORS_PACKAGE.getParameterValue_Parameter(), behaviorParameter);
			parameterValues.add(parameterValue);
		}
		parameterValueDefinition.eSet(BASIC_BEHAVIORS_PACKAGE.getParameterValueDefinition_ParameterValues(), parameterValues);

		Resource resource = resourceSet.createResource(URI.createURI(targetFile
				.getFullPath().toPortableString()));
		resource.getContents().add(parameterValueDefinition);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		try {
			resource.save(options);
		} catch (IOException e1) {
			throwCoreException(e1.getMessage());
		}

		monitor.worked(1);
		monitor.setTaskName("Opening parameter value definition file for editing...");
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

	private List<EParameter> getBehaviorParameters(
			BehavioredEOperation behavioredEOperation) {
		List<EParameter> behaviorParameters = new ArrayList<EParameter>();
		
		if(behavioredEOperation.getMethod().size() != 0) {
			Behavior behavior = behavioredEOperation.getMethod().get(0);
			behaviorParameters.addAll(behavior.getOwnedParameter());
		}
		
		return behaviorParameters;
	}

	private BehavioredEOperation getBehavioredEOperation(
			Collection<EOperation> eOperations) {
		for(EOperation eOperation : eOperations) {
			if(eOperation instanceof BehavioredEOperation) {
				return ((BehavioredEOperation)eOperation);
			}
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