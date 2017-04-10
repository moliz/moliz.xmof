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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.xmof.configuration.profile.ProfileGenerator;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.project.EMFProfileProjectNatureUtil;
import org.modelversioning.emfprofile.project.ui.wizard.NewProfileProjectOperation;
import org.modelversioning.emfprofile.project.ui.wizard.ProfileProjectData;

public class NewConfigurationProfileOperation extends
		NewProfileProjectOperation {

	private Resource xmofResource;
	private ProfileProjectData projectData;

	public NewConfigurationProfileOperation(ProfileProjectData projectData,
			Resource xmofResource) {
		super(projectData, false);
		this.xmofResource = xmofResource;
		this.projectData = projectData;
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		super.execute(monitor);

		Collection<EPackage> xmofPackages = getEPackages(xmofResource);
		ProfileGenerator generator = new ProfileGenerator(projectData, xmofPackages);
		Collection<Profile> profilePackages = generator
				.generateConfigurationProfile();
		Resource profileResource = loadProfileResource(xmofResource
				.getResourceSet());
		EObject notation = profileResource.getContents().get(1);
		profileResource.getContents().clear();
		profileResource.getContents().addAll(profilePackages);
		profileResource.getContents().add(notation);
		try {
			profileResource.save(null);
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		}
	}

	private Resource loadProfileResource(ResourceSet resourceSet) {
		URI profileURI = getProfileURI();
		Resource profileResource = resourceSet.getResource(profileURI, true);
		return profileResource;
	}

	private URI getProfileURI() {
		return EMFProfileProjectNatureUtil
				.getDefaultProfileDiagramURI(projectData.getProjectHandle());
	}

	private Collection<EPackage> getEPackages(Resource metaModelResource) {
		Collection<EPackage> packages = new ArrayList<EPackage>();
		for (EObject eObject : metaModelResource.getContents()) {
			if (eObject instanceof EPackage) {
				packages.add((EPackage) eObject);
			}
		}
		return packages;
	}

}
