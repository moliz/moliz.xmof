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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelversioning.emfprofile.project.ui.wizard.ProfileProjectData;
import org.modelversioning.emfprofile.project.ui.wizard.ProfileProjectNewPage;

public class SpecifyProfileProjectDataPage extends ProfileProjectNewPage {

	private static final String PROFILE_DATA_PAGE = "PROFILE_DATA_PAGE";

	private ProfileProjectData profileProjectData;
	
	public SpecifyProfileProjectDataPage(ProfileProjectData profileProjectData,
			ISelection selection) {
		super(PROFILE_DATA_PAGE, profileProjectData,
				(IStructuredSelection) selection);
		setTitle("Specify Profile Project Data");
		setDescription("Specify the data for the configuration profile.");		
		this.profileProjectData = profileProjectData;
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		updateValues();
	}

	private void updateValues() {
		if(profileProjectData == null)
			return;
		setProfileName(profileProjectData.getProfileName());
		setProfileNamespace(profileProjectData.getProfileNamespace());
	}
	
	@Override
	public void setVisible(boolean visible) {
		if(visible) 
			updateValues();		
		super.setVisible(visible);
	}
}