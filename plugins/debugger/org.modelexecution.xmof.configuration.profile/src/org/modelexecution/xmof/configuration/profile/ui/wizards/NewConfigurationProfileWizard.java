/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration.profile.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.modelversioning.emfprofile.project.ui.wizard.ProfileProjectData;

public class NewConfigurationProfileWizard extends Wizard implements INewWizard {

	private IWorkbench workbench;
	private SpecifyProfileProjectDataPage specifyProfileDataPage;
	private SelectXMOFModelFilePage selectXMOFModelFilePage;
	private ISelection selection;
	private ResourceSet resourceSet = new ResourceSetImpl();
	private ProfileProjectData profileProjectData;

	public NewConfigurationProfileWizard() {
		super();
		setNeedsProgressMonitor(true);
		profileProjectData = new ProfileProjectData();
	}

	public void addPages() {
		selectXMOFModelFilePage = new SelectXMOFModelFilePage(profileProjectData, selection,
				resourceSet);
		specifyProfileDataPage = new SpecifyProfileProjectDataPage(
				profileProjectData, selection);
		addPage(selectXMOFModelFilePage);
		addPage(specifyProfileDataPage);
	}

	@Override
	public boolean performFinish() {
		specifyProfileDataPage.updateData();
		try {
			doFinish();
			addToWorkingSets();
			return true;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			realException.printStackTrace();
		} catch (InterruptedException e) {
			MessageDialog.openError(getShell(), "Error", e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	private void doFinish() throws InvocationTargetException,
			InterruptedException {
		final Resource xmofResource = selectXMOFModelFilePage.getXMOFResource();
		getContainer().run(
				false,
				true,
				new NewConfigurationProfileOperation(profileProjectData,
						xmofResource));
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench = workbench;
	}

	private void addToWorkingSets() {
		IWorkingSet[] workingSets = specifyProfileDataPage
				.getSelectedWorkingSets();
		if (workingSets.length > 0)
			workbench.getWorkingSetManager().addToWorkingSets(
					specifyProfileDataPage.getProjectHandle(), workingSets);
	}
	
	
}