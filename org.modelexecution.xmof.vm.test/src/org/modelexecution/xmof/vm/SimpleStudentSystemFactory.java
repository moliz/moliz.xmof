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

import java.io.File;

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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.MainEClass;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

public class SimpleStudentSystemFactory {

	private final static EcoreFactory ECORE = EcoreFactory.eINSTANCE;
	private final static KernelFactory KERNEL = KernelFactory.eINSTANCE;
	private final static IntermediateActivitiesFactory INTERMED_ACTIVITIES = IntermediateActivitiesFactory.eINSTANCE;
	private final static IntermediateActionsFactory INTERMED_ACTIONS = IntermediateActionsFactory.eINSTANCE;
	private final static BasicActionsFactory BASIC_ACTIONS = BasicActionsFactory.eINSTANCE;

	private MainEClass mainEClass;
	private EPackage rootPackage;
	private BehavioredEClass studentClass;

	public Resource createMetamodelResource() {
		Resource resource = new ResourceSetImpl().createResource(URI
				.createFileURI(new File("simple-student-system.xmof")
						.getAbsolutePath()));
		resource.getContents().add(createMetamodel());
		return resource;
	}

	public EPackage createMetamodel() {
		rootPackage = ECORE.createEPackage();
		rootPackage.setName("StudentSystemPackage");
		rootPackage.setNsURI("http://www.modelexecution.org/student-system");
		rootPackage.setNsPrefix("sistusy");
		rootPackage.getEClassifiers().add(createStudentClass());
		rootPackage.getEClassifiers().add(createMainEClass());
		return rootPackage;
	}

	private MainEClass createMainEClass() {
		mainEClass = KERNEL.createMainEClass();
		mainEClass.setName("StudentSystem");
		mainEClass.getEStructuralFeatures().add(createNameAttribute());
		mainEClass.getEStructuralFeatures().add(createRefToStudents());
		Behavior classifierBehavior = createMainEClassClassifierBehavior();
		mainEClass.getOwnedBehavior().add(classifierBehavior);
		mainEClass.setClassifierBehavior(classifierBehavior);
		return mainEClass;
	}

	private EStructuralFeature createNameAttribute() {
		EAttribute nameAttribute = ECORE.createEAttribute();
		nameAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		nameAttribute.setName("name");
		return nameAttribute;
	}

	private EStructuralFeature createRefToStudents() {
		EReference studentsReference = ECORE.createEReference();
		studentsReference.setName("students");
		studentsReference.setContainment(true);
		studentsReference.setEType(studentClass);
		studentsReference.setLowerBound(0);
		studentsReference.setUpperBound(-1);
		return studentsReference;
	}

	private BehavioredEClass createStudentClass() {
		studentClass = KERNEL.createBehavioredEClass();
		studentClass.setName("Student");
		studentClass.getEStructuralFeatures().add(createNameAttribute());
		return studentClass;
	}

	private Behavior createMainEClassClassifierBehavior() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		InitialNode initialNode = createInitialNode(activity);

		CreateObjectAction createStudentAction = createCreateObjectAction(
				activity, "CreateStudent", studentClass);
		createControlFlow(activity, initialNode, createStudentAction);

		ActivityFinalNode finalNode = INTERMED_ACTIVITIES
				.createActivityFinalNode();
		activity.getNode().add(finalNode);
		createControlFlow(activity, createStudentAction, finalNode);
		return activity;
	}

	private InitialNode createInitialNode(Activity activity) {
		InitialNode initialNode = INTERMED_ACTIVITIES.createInitialNode();
		activity.getNode().add(initialNode);
		return initialNode;
	}

	private CreateObjectAction createCreateObjectAction(Activity activity,
			String name, EClass eClass) {
		CreateObjectAction action = INTERMED_ACTIONS.createCreateObjectAction();
		action.setName(name);
		OutputPin outputPin = BASIC_ACTIONS.createOutputPin();
		outputPin.setName("OutputPin (" + name + ")");
		action.setResult(outputPin);
		action.getOutput().add(outputPin);
		action.setClassifier(eClass);
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}

	private ControlFlow createControlFlow(Activity activity,
			ActivityNode source, ActivityNode target) {
		ControlFlow cflow = INTERMED_ACTIVITIES.createControlFlow();
		cflow.setName("ControlFlow " + source.getName() + " --> "
				+ target.getName());
		cflow.setSource(source);
		cflow.setTarget(target);
		source.getOutgoing().add(cflow);
		target.getIncoming().add(cflow);
		cflow.setActivity(activity);
		activity.getEdge().add(cflow);
		return cflow;
	}

	public Resource createModelResource() {
		Resource resource = new ResourceSetImpl().createResource(URI
				.createFileURI(new File("simple-student-system1.xmi")
						.getAbsolutePath()));
		EFactory factory = rootPackage.getEFactoryInstance();

		EObject studentSystem = factory.create(mainEClass);
		studentSystem.eSet(mainEClass.getEStructuralFeature("name"),
				"aStudentSystem");

		EObject student1 = factory.create(studentClass);
		student1.eSet(studentClass.getEStructuralFeature("name"), "Tanja");
		EObject student2 = factory.create(studentClass);
		student2.eSet(studentClass.getEStructuralFeature("name"), "Philip");

		EList<EObject> studentList = new BasicEList<EObject>();
		studentList.add(student1);
		studentList.add(student2);

		studentSystem.eSet(mainEClass.getEStructuralFeature("students"),
				studentList);

		resource.getContents().add(studentSystem);
		return resource;
	}

}
