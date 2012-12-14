/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import static junit.framework.Assert.*;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

public class XMOFVirtualMachineTest {

	@Test
	public void runSimpleModel() {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();
		XMOFBasedModel simpleModel = new XMOFBasedModel(
				modelResource.getContents());
		XMOFVirtualMachine vm = new XMOFVirtualMachine(simpleModel);
		assertTrue(vm.mayRun());
		vm.run();
	}

	@Test
	public void runPetriNetModel() {
		PetriNetFactory factory = new PetriNetFactory();
		factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();
		XMOFBasedModel petrinet = new XMOFBasedModel(
				modelResource.getContents());
		XMOFVirtualMachine vm = new XMOFVirtualMachine(petrinet);
		assertTrue(vm.mayRun());
		vm.run();
	}

}
