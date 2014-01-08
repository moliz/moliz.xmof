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

import java.io.File;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.Transition;

import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;

/**
 * @author Tanja
 * 
 */
public class StatesBuilderTest {

	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String CHILDS = "childs"; //$NON-NLS-1$

	private final static EcoreFactory ECORE = EcoreFactory.eINSTANCE;

	private EPackage rootPackage;
	private EFactory rootFactory;
	private EClass childClass;
	private EClass rootClass;

	@Test
	public void test() {
		createMetamodelResource();
		Resource modelResource = createModelResource();
		EditingDomain editingDomain = createEditingDomain(modelResource);

		StatesBuilder statesBuilder = new StatesBuilder(modelResource);

		CreateObjectAction action = new CreateObjectAction();
		action.qualifiedName = "action"; //$NON-NLS-1$
		ActivityNodeEntryEvent event = new ActivityNodeEntryEventImpl(0,
				action, null);
		statesBuilder.notify(event);

		EObject rootObject = createRootObject("root2", //$NON-NLS-1$
				null);
		Command cmd = new AddCommand(editingDomain,
				modelResource.getContents(), rootObject);
		editingDomain.getCommandStack().execute(cmd);

		StateSystem stateSystem = statesBuilder.getStateSystem();
		assertNotNull(stateSystem);
		assertEquals(2, stateSystem.getStates().size());
		assertEquals(1, stateSystem.getTransitions().size());

		State state1 = stateSystem.getStates().get(0);
		assertEquals(1, state1.getObjects().size());
		assertEquals(1, state1.getObjects().get(0).eContents().size());

		State state2 = stateSystem.getStates().get(1);
		assertEquals(2, state2.getObjects().size());
		assertEquals(1, state2.getObjects().get(0).eContents().size());
		assertEquals(0, state2.getObjects().get(1).eContents().size());

		Transition transition = stateSystem.getTransitions().get(0);
		assertEquals(state1, transition.getSource());
		assertEquals(state2, transition.getTarget());
		assertEquals(action.qualifiedName, transition.getEvent()
				.getQualifiedName());
	}

	private Resource createMetamodelResource() {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI
				.createFileURI(new File("metamodel.ecore") //$NON-NLS-1$
						.getAbsolutePath()));
		resource.getContents().add(createMetamodel());
		return resource;
	}

	private EPackage createMetamodel() {
		rootPackage = ECORE.createEPackage();
		rootPackage.setName("StudentSystemPackage"); //$NON-NLS-1$
		rootPackage.setNsURI("http://www.modelexecution.org/student-system"); //$NON-NLS-1$
		rootPackage.setNsPrefix("sistusy"); //$NON-NLS-1$
		rootPackage.getEClassifiers().add(createChildClass());
		rootPackage.getEClassifiers().add(createRootClass());
		rootFactory = rootPackage.getEFactoryInstance();
		return rootPackage;
	}

	private EClass createChildClass() {
		childClass = ECORE.createEClass();
		childClass.setName("ChildClass"); //$NON-NLS-1$
		EAttribute nameAttribute = createNameAttribute();
		childClass.getEStructuralFeatures().add(nameAttribute);
		return childClass;
	}

	private EClass createRootClass() {
		rootClass = ECORE.createEClass();
		rootClass.setName("RootClass"); //$NON-NLS-1$
		EAttribute nameAttribute = createNameAttribute();
		rootClass.getEStructuralFeatures().add(nameAttribute);
		rootClass.getEStructuralFeatures().add(createChildsContainment());
		return rootClass;
	}

	private EAttribute createNameAttribute() {
		EAttribute nameAttribute = ECORE.createEAttribute();
		nameAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		nameAttribute.setName(NAME);
		return nameAttribute;
	}

	private EStructuralFeature createChildsContainment() {
		EReference childsContainment = ECORE.createEReference();
		childsContainment.setName(CHILDS);
		childsContainment.setContainment(true);
		childsContainment.setEType(childClass);
		childsContainment.setLowerBound(0);
		childsContainment.setUpperBound(-1);
		return childsContainment;
	}

	private Resource createModelResource() {
		Resource resource = new ResourceSetImpl().createResource(URI
				.createFileURI(new File("model.xmi") //$NON-NLS-1$
						.getAbsolutePath()));

		EObject childObject = createChildObject("child"); //$NON-NLS-1$

		EList<EObject> childObjects = new BasicEList<EObject>();
		childObjects.add(childObject);

		EObject rootObject = createRootObject("root", //$NON-NLS-1$
				childObjects);

		resource.getContents().add(rootObject);

		return resource;
	}

	private EObject createRootObject(String name, EList<EObject> childObjects) {
		EObject rootObject = rootFactory.create(rootClass);
		rootObject.eSet(rootClass.getEStructuralFeature(NAME), name);
		if (childObjects != null)
			rootObject.eSet(rootClass.getEStructuralFeature(CHILDS),
					childObjects);
		return rootObject;
	}

	private EObject createChildObject(String name) {
		EObject childObject = rootFactory.create(childClass);
		childObject.eSet(childClass.getEStructuralFeature(NAME), name);
		return childObject;
	}

	private EditingDomain createEditingDomain(Resource modelResource) {
		return TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain(modelResource.getResourceSet());
	}
}
