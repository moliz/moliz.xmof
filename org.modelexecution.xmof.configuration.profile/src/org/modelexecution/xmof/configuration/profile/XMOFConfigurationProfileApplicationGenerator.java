/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration.profile;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.modelexecution.xmof.vm.IXMOFVirtualMachineListener;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent.Type;
import org.modelversioning.emfprofile.Profile;

public class XMOFConfigurationProfileApplicationGenerator implements IXMOFVirtualMachineListener {
	
	private XMOFBasedModel model;
	private Collection<Profile> configurationProfiles;
	private Resource profileApplicationResource;

	@Override
	public void notify(XMOFVirtualMachineEvent event) {
		if (Type.STOP.equals(event.getType())) {
			updateProfileApplication();
		}
	}

	private void updateProfileApplication() {
		// TODO implement
	}
	

}
