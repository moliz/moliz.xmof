/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.states.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.Transition;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory.MainActivityBehaviorKind;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

/**
 * @author Tanja
 * 
 */
public class StatesBuilderTestViaXMOFVM {

	@Test
	public void test() {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		factory.createMetamodelResource(MainActivityBehaviorKind.CREATE);
		Resource modelResource = factory.createModelResource();
		assertEquals(1, modelResource.getContents().size());
		createEditingDomain(modelResource);

		StatesBuilder statesBuilder = new StatesBuilder(modelResource);

		XMOFBasedModel model = new XMOFBasedModel(modelResource.getContents());
		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);
		vm.run();

		assertEquals(2, modelResource.getContents().size());
		assertTrue(modelResource.getContents().get(0).eClass().equals(factory.getStudentSystemClass()));
		assertTrue(modelResource.getContents().get(1).eClass().equals(factory.getStudentClass()));

		StateSystem stateSystem = statesBuilder.getStateSystem();
		assertNotNull(stateSystem);
		assertEquals(2, stateSystem.getStates().size());
		assertEquals(1, stateSystem.getTransitions().size());

		State state1 = stateSystem.getStates().get(0);
		assertEquals(1, state1.getObjects().size());
		assertTrue(state1.getObjects().get(0).eClass().equals(factory.getStudentSystemClass()));
		
		State state2 = stateSystem.getStates().get(1);
		assertEquals(2, state2.getObjects().size());
		assertTrue(state2.getObjects().get(0).eClass().equals(factory.getStudentSystemClass()));
		assertTrue(state2.getObjects().get(1).eClass().equals(factory.getStudentClass()));
		
		Transition transition = stateSystem.getTransitions().get(0);
		assertEquals(state1, transition.getSource());
		assertEquals(state2, transition.getTarget());
		assertEquals("CreateStudent", //$NON-NLS-1$
				transition.getEvent().getQualifiedName());
	}

	private EditingDomain createEditingDomain(Resource modelResource) {
		return TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain(modelResource.getResourceSet());
	}
}
