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
import java.util.ArrayList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
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
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadSelfAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.EEnumLiteralSpecification;
import org.modelexecution.xmof.Syntax.Classes.Kernel.InstanceValue;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralInteger;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralString;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralUnlimitedNatural;
import org.modelexecution.xmof.Syntax.Classes.Kernel.MainEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ValueSpecification;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsFactory;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class SimpleStudentSystemFactory {

	private static final String NAME = "name";
	private static final String STATUS = "status";
	private static final String NICKNAME = "nickname";
	private static final String NICKNAME_NOT_UNIQUE = "nickname not unique";
	
	private final static EcoreFactory ECORE = EcoreFactory.eINSTANCE;
	private final static KernelFactory KERNEL = KernelFactory.eINSTANCE;
	private final static IntermediateActivitiesFactory INTERMED_ACTIVITIES = IntermediateActivitiesFactory.eINSTANCE;
	private final static IntermediateActionsFactory INTERMED_ACTIONS = IntermediateActionsFactory.eINSTANCE;	
	private final static BasicBehaviorsFactory BASIC_BEHAVIORS = BasicBehaviorsFactory.eINSTANCE;
	private final static BasicActionsFactory BASIC_ACTIONS = BasicActionsFactory.eINSTANCE;	

	private MainEClass mainEClass;
	private EPackage rootPackage;
	private BehavioredEClass studentClass;
	private EObject student1;
	private EObject student2;
	private EObject studentSystem;
	private EReference knowsReference;
	private EEnum studentStatusEnum;
	private EReference studentsReference;
	private EAttribute nicknameAttribute, nameAttribute, nicknameNotUniqueAttribute, statusAttribute;
	private OpaqueBehavior listgetBehavior;
	
	public Resource createMetamodelResource() {
		return createMetamodelResource(MainEClassClassifierBehaviorKind.CREATE);
	}

	public Resource createMetamodelResource(
			MainEClassClassifierBehaviorKind mainEClassClassifierBehavior) {
		Resource resource = new ResourceSetImpl().createResource(URI
				.createFileURI(new File("simple-student-system.xmof") //$NON-NLS-1$
						.getAbsolutePath()));
		resource.getContents().add(
				createMetamodel(mainEClassClassifierBehavior));
		return resource;
	}

	public EPackage createMetamodel(
			MainEClassClassifierBehaviorKind mainEClassClassifierBehavior) {
		rootPackage = ECORE.createEPackage();
		rootPackage.setName("StudentSystemPackage"); //$NON-NLS-1$
		rootPackage.setNsURI("http://www.modelexecution.org/student-system"); //$NON-NLS-1$
		rootPackage.setNsPrefix("sistusy"); //$NON-NLS-1$
		rootPackage.getEClassifiers().add(createListgetBehavior());
		rootPackage.getEClassifiers().add(createStudentStatusEnum());
		rootPackage.getEClassifiers().add(createStudentClass());
		rootPackage.getEClassifiers().add(
				createMainEClass(mainEClassClassifierBehavior));		
		return rootPackage;
	}	
	
	private OpaqueBehavior createListgetBehavior() {
		listgetBehavior = BASIC_BEHAVIORS.createOpaqueBehavior();		
		listgetBehavior.setName("listget");
		
		DirectedParameter list = createDirectedParameter("list", ParameterDirectionKind.IN);
		list.setLowerBound(1);
		list.setUpperBound(-1);
		listgetBehavior.getOwnedParameter().add(list);
		
		DirectedParameter index = createDirectedParameter("index", ParameterDirectionKind.IN);
		index.setLowerBound(1);
		index.setUpperBound(1);
		listgetBehavior.getOwnedParameter().add(index);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(0);
		outparam.setUpperBound(1);
		listgetBehavior.getOwnedParameter().add(outparam);
		
		return listgetBehavior;
	}
	
	private DirectedParameter createDirectedParameter(String name, ParameterDirectionKind direction) {
		DirectedParameter param = KERNEL.createDirectedParameter();
		param.setName(name);
		param.setDirection(direction);		
		return param;
	}
	
	private EClassifier createStudentStatusEnum() {
		studentStatusEnum = ECORE.createEEnum();
		studentStatusEnum.setName("StudentStatus");
		EEnumLiteral activeLiteral = ECORE.createEEnumLiteral();
		activeLiteral.setLiteral("ACTIVE");
		activeLiteral.setName("active");
		activeLiteral.setValue(0);
		EEnumLiteral passiveLiteral = ECORE.createEEnumLiteral();
		passiveLiteral.setLiteral("PASSIVE");
		passiveLiteral.setName("passive");
		passiveLiteral.setValue(1);
		studentStatusEnum.getELiterals().add(activeLiteral);
		studentStatusEnum.getELiterals().add(passiveLiteral);
		return studentStatusEnum;
	}

	public enum MainEClassClassifierBehaviorKind {
		CREATE, DESTROY_ROOT, DESTROY_CHILD, 
		ADD_MULTIPLE_VALUES, ADD_MULTIPLE_VALUES_DUPLICATE, ADD_MULTIPLE_VALUES_REPLACE, 
		ADD_SINGLE_VALUE, ADD_SINGLE_VALUE_ALREADY_SET, ADD_SINGLE_VALUE_DUPLICATE, ADD_SINGLE_VALUE_REPLACE,
		CLEAR_MULTIPLE_VALUES, CLEAR_SINGLE_VALUE,
		REMOVE_MULTIPLE_UNIQUE, REMOVE_MULTIPLE_NOT_UNIQUE, REMOVE_MULTIPLE_DUPLICATES, REMOVE_MULTIPLE_NOT_UNIQUE_AT,
		REMOVE_SINGLE_VALUE,
		SET_ENUMERATION;
	}
	
	private MainEClass createMainEClass(
			MainEClassClassifierBehaviorKind mainEClassClassifierBehavior) {
		createMainEClass();

		Behavior classifierBehavior = null;
		switch (mainEClassClassifierBehavior) {
		case CREATE:
			classifierBehavior = createMainEClassClassifierBehavior_CREATE();
			break;
		case DESTROY_ROOT:
			classifierBehavior = createMainEClassClassifierBehavior_DESTROY();
			break;
		case DESTROY_CHILD:
			classifierBehavior = createMainEClassClassifierBehavior_DESTROY_CHILD();
			break;
		case ADD_MULTIPLE_VALUES:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES();
			break;
		case ADD_MULTIPLE_VALUES_DUPLICATE:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_DUPLICATE();
			break;
		case ADD_MULTIPLE_VALUES_REPLACE:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_REPLACE();
			break;
		case ADD_SINGLE_VALUE:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_SINGLE_VALUE();
			break;
		case ADD_SINGLE_VALUE_ALREADY_SET:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_ALREADY_SET();
			break;
		case ADD_SINGLE_VALUE_DUPLICATE:
			classifierBehavior = createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_DUPLICATE();
			break;
		case ADD_SINGLE_VALUE_REPLACE: 
			classifierBehavior = createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_REPLACE();
			break;
		case CLEAR_MULTIPLE_VALUES:
			classifierBehavior = createMainEClassClassifierBehavior_CLEAR_MULTIPLE_VALUES();
			break;
		case CLEAR_SINGLE_VALUE:
			classifierBehavior = createMainEClassClassifierBehavior_CLEAR_SINGLE_VALUE();
			break;
		case REMOVE_MULTIPLE_UNIQUE:
			classifierBehavior = createMainEClassClassifierBehavior_REMOVE_MULTIPLE_UNIQUE();
			break;
		case REMOVE_MULTIPLE_NOT_UNIQUE:
			classifierBehavior = createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE();
			break;
		case REMOVE_MULTIPLE_DUPLICATES:
			classifierBehavior = createMainEClassClassifierBehavior_REMOVE_MULTIPLE_DUPLICATES();
			break;
		case REMOVE_MULTIPLE_NOT_UNIQUE_AT:
			classifierBehavior = createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE_AT();
			break;
		case REMOVE_SINGLE_VALUE:
			classifierBehavior = createMainEClassClassifierBehavior_REMOVE_SINGLE_VALUE();
			break;
		case SET_ENUMERATION:
			classifierBehavior = createMainEClassClassifierBehavior_SET_ENUMERATION();
			break;
		default:
			classifierBehavior = createMainEClassClassifierBehavior_CREATE();
			break;
		}
		setMainEClassClassifierBehavior(classifierBehavior);
		return mainEClass;
	}		

	private MainEClass createMainEClass() {
		mainEClass = KERNEL.createMainEClass();
		mainEClass.setName("StudentSystem"); //$NON-NLS-1$
		mainEClass.getEStructuralFeatures().add(createNameAttribute());
		mainEClass.getEStructuralFeatures().add(createRefToStudents());
		return mainEClass;
	}

	private Behavior createMainEClassClassifierBehavior_CREATE() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_CREATE");
		
		InitialNode initialNode = createInitialNode(activity);

		CreateObjectAction createStudentAction = createCreateObjectAction(
				activity, "CreateStudent", studentClass); //$NON-NLS-1$
		createControlFlow(activity, initialNode, createStudentAction);

		ActivityFinalNode finalNode = INTERMED_ACTIVITIES
				.createActivityFinalNode();
		activity.getNode().add(finalNode);
		createControlFlow(activity, createStudentAction, finalNode);
		return activity;
	}

	private Behavior createMainEClassClassifierBehavior_DESTROY() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_DESTROY");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		DestroyObjectAction destroyObjectAction = createDestroyObjectAction(
				activity, "Destroy aStudentSystem");
		createObjectFlow(activity, readSelfAction.getResult(),
				destroyObjectAction.getTarget());
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_DESTROY_CHILD() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_DESTROY_CHILD");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		DestroyObjectAction destroyObjectAction = createDestroyObjectAction(
				activity, "Destroy students");
		createObjectFlow(activity, readSelfAction.getResult(),
				readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(),
				destroyObjectAction.getTarget());
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_ADD_MULTIPLE_VALUES");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		ValueSpecificationAction specify1get = createValueSpecificationAction(activity, "specify 1", 1, false);
		ValueSpecificationAction specify1set = createValueSpecificationAction(activity, "specify 1", 1, true);
		ValueSpecificationAction specifyNickname1 = createValueSpecificationAction(activity, "specify tanj", "tanj");
		ValueSpecificationAction specifyNickname2 = createValueSpecificationAction(activity, "specify tanjihhhii", "tanjihhhii");
		ValueSpecificationAction specifyNickname3 = createValueSpecificationAction(activity, "specify tanjania", "tanjania");
		AddStructuralFeatureValueAction setNickname1 = createAddStructuralFeatureValueAction(activity, "set nickname1", nicknameAttribute, false, false);
		AddStructuralFeatureValueAction setNickname2 = createAddStructuralFeatureValueAction(activity, "set nickname2", nicknameAttribute, true, false);
		AddStructuralFeatureValueAction setNickname3 = createAddStructuralFeatureValueAction(activity, "set nickname3", nicknameAttribute, false, false);
		CallBehaviorAction callListGet = createCallBehaviorAction(activity, "call list get", listgetBehavior);
		
		createObjectFlow(activity, readSelfAction.getResult(), readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(), callListGet.getInput().get(0));
		createObjectFlow(activity, specify1get.getResult(), callListGet.getInput().get(1));
		createObjectFlow(activity, callListGet.getOutput().get(0), setNickname1.getObject());
		createObjectFlow(activity, specifyNickname1.getResult(), setNickname1.getValue());
		createObjectFlow(activity, setNickname1.getResult(), setNickname2.getObject());
		createObjectFlow(activity, specifyNickname2.getResult(), setNickname2.getValue());
		createObjectFlow(activity, specify1set.getResult(), setNickname2.getInsertAt());
		createObjectFlow(activity, setNickname2.getResult(), setNickname3.getObject());
		createObjectFlow(activity, specifyNickname3.getResult(), setNickname3.getValue());
				
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_DUPLICATE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES();
		activity.setName("MainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_DUPLICATE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("set nickname3")) {
				AddStructuralFeatureValueAction setNickname3 = (AddStructuralFeatureValueAction)node;
				InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
				inputPin.setName("insertAt"); //$NON-NLS-1$
				inputPin.setLowerBound(1);
				inputPin.setUpperBound(1);		
				setNickname3.setInsertAt(inputPin);
				ValueSpecificationAction specify2set = createValueSpecificationAction(activity, "specify 2", 2, true);
				createObjectFlow(activity, specify2set.getResult(), setNickname3.getInsertAt());
			}
			if(node.getName().equals("specify tanjania")) {
				node.setName("specify tanjihhhii");
				ValueSpecificationAction specifyNickname3 = (ValueSpecificationAction)node;
				LiteralString valueSpecification = KERNEL.createLiteralString();
				valueSpecification.setValue("tanjihhhii");
				specifyNickname3.setValue(valueSpecification);
			}
		}
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_REPLACE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_ADD_MULTIPLE_VALUES();
		activity.setName("MainEClassClassifierBehavior_ADD_MULTIPLE_VALUES_REPLACE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("set nickname3")) {
				AddStructuralFeatureValueAction setNickname3 = (AddStructuralFeatureValueAction)node;
				setNickname3.setReplaceAll(true);
			}
		}
		return activity;
	}

	private Behavior createMainEClassClassifierBehavior_ADD_SINGLE_VALUE() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_ADD_SINGLE_VALUE");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		ValueSpecificationAction specify3get = createValueSpecificationAction(activity, "specify 3", 3, false);
		ValueSpecificationAction specifyTanj = createValueSpecificationAction(activity, "specify tanj", "tanj");
		AddStructuralFeatureValueAction setName = createAddStructuralFeatureValueAction(activity, "set name", nameAttribute, false, false);
		CallBehaviorAction callListGet = createCallBehaviorAction(activity, "call list get", listgetBehavior);
		
		createObjectFlow(activity, readSelfAction.getResult(), readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(), callListGet.getInput().get(0));
		createObjectFlow(activity, specify3get.getResult(), callListGet.getInput().get(1));
		createObjectFlow(activity, callListGet.getOutput().get(0), setName.getObject());
		createObjectFlow(activity, specifyTanj.getResult(), setName.getValue());
				
		return activity;
	}

	private Behavior createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_ALREADY_SET() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_ADD_SINGLE_VALUE();
		activity.setName("MainEClassClassifierBehavior_ADD_SINGLE_VALUE_ALREADY_SET");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("specify 3")) {
				ValueSpecificationAction specify3get = (ValueSpecificationAction)node;
				specify3get.setName("specify 1");
				LiteralInteger valueSpecification = KERNEL.createLiteralInteger();
				valueSpecification.setValue(1);
				specify3get.setValue(valueSpecification);
			}
		}
		return activity;
	}
	private Behavior createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_DUPLICATE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_ALREADY_SET();
		activity.setName("MainEClassClassifierBehavior_ADD_SINGLE_VALUE_DUPLICATE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("specify tanj")) {
				ValueSpecificationAction specifyTanj = (ValueSpecificationAction)node;
				specifyTanj.setName("specify Tanja");
				LiteralString valueSpecification = KERNEL.createLiteralString();
				valueSpecification.setValue("Tanja");
				specifyTanj.setValue(valueSpecification);
			}
		}
		return activity;
	}	

	private Behavior createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_REPLACE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_ADD_SINGLE_VALUE_ALREADY_SET();
		activity.setName("MainEClassClassifierBehavior_ADD_SINGLE_VALUE_REPLACE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("set name")) {
				AddStructuralFeatureValueAction setName = (AddStructuralFeatureValueAction)node;
				setName.setReplaceAll(true);
			}
		}
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_CLEAR_MULTIPLE_VALUES() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_CLEAR_MULTIPLE_VALUES");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		ValueSpecificationAction specify1get = createValueSpecificationAction(activity, "specify 1", 1, false);
		CallBehaviorAction callListGet = createCallBehaviorAction(activity, "call list get", listgetBehavior);
		ClearStructuralFeatureAction clearNickname = createClearStructuralFeatureAction(activity, "clear nickname", nicknameAttribute);
		
		createObjectFlow(activity, readSelfAction.getResult(), readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(), callListGet.getInput().get(0));
		createObjectFlow(activity, specify1get.getResult(), callListGet.getInput().get(1));
		createObjectFlow(activity, callListGet.getOutput().get(0), clearNickname.getObject());
				
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_CLEAR_SINGLE_VALUE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_CLEAR_MULTIPLE_VALUES();
		activity.setName("MainEClassClassifierBehavior_CLEAR_SINGLE_VALUE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("clear nickname")) {
				ClearStructuralFeatureAction clear = (ClearStructuralFeatureAction)node;
				clear.setName("clear name");
				clear.setStructuralFeature(nameAttribute);
			}
		}
		return activity;
	}

	private Behavior createMainEClassClassifierBehavior_REMOVE_MULTIPLE_UNIQUE() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_REMOVE_MULTIPLE_UNIQUE");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		ValueSpecificationAction specify1get = createValueSpecificationAction(activity, "specify 1", 1, false);
		ValueSpecificationAction specifyTanjania = createValueSpecificationAction(activity, "specify tanjania", "tanjania");
		CallBehaviorAction callListGet = createCallBehaviorAction(activity, "call list get", listgetBehavior);
		RemoveStructuralFeatureValueAction removeNicknameNotUnique = createRemoveStructuralFeatureValueAction(activity, "remove nicknameNotUnique", nicknameNotUniqueAttribute, false, false);
				
		createObjectFlow(activity, readSelfAction.getResult(), readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(), callListGet.getInput().get(0));
		createObjectFlow(activity, specify1get.getResult(), callListGet.getInput().get(1));
		createObjectFlow(activity, callListGet.getOutput().get(0), removeNicknameNotUnique.getObject());
		createObjectFlow(activity, specifyTanjania.getResult(), removeNicknameNotUnique.getValue());
				
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_REMOVE_MULTIPLE_UNIQUE();
		activity.setName("MainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("specify tanjania")) {
				ValueSpecificationAction specifyNickname = (ValueSpecificationAction)node;
				specifyNickname.setName("specify tanj");
				LiteralString valuespecification = KERNEL.createLiteralString();
				valuespecification.setValue("tanj");
				specifyNickname.setValue(valuespecification);
			}
		}		
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_REMOVE_MULTIPLE_DUPLICATES() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE();
		activity.setName("MainEClassClassifierBehavior_REMOVE_MULTIPLE_DUPLICATES");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("remove nicknameNotUnique")) {
				RemoveStructuralFeatureValueAction remove = (RemoveStructuralFeatureValueAction)node;
				remove.setRemoveDuplicates(true);
			}
		}		
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE_AT() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE();
		activity.setName("MainEClassClassifierBehavior_REMOVE_MULTIPLE_NOT_UNIQUE_AT");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("remove nicknameNotUnique")) {
				RemoveStructuralFeatureValueAction remove = (RemoveStructuralFeatureValueAction)node;
				InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
				inputPin.setName("removeAt"); //$NON-NLS-1$
				inputPin.setLowerBound(1);
				inputPin.setUpperBound(1);		
				remove.setRemoveAt(inputPin);
				ValueSpecificationAction specify3 = createValueSpecificationAction(activity, "specify 3", 3, true);
				createObjectFlow(activity, specify3.getResult(), remove.getRemoveAt());
			}
		}		
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_REMOVE_SINGLE_VALUE() {
		Activity activity = (Activity)createMainEClassClassifierBehavior_REMOVE_MULTIPLE_UNIQUE();
		activity.setName("MainEClassClassifierBehavior_REMOVE_SINGLE_VALUE");
		for(ActivityNode node : new ArrayList<ActivityNode>(activity.getNode())) {
			if(node.getName().equals("specify tanjania")) {
				ValueSpecificationAction specifyNickname = (ValueSpecificationAction)node;
				specifyNickname.setName("specify Tanja");
				LiteralString valuespecification = KERNEL.createLiteralString();
				valuespecification.setValue("Tanja");
				specifyNickname.setValue(valuespecification);
			} else if(node.getName().equals("remove nicknameNotUnique")) {
				RemoveStructuralFeatureValueAction remove = (RemoveStructuralFeatureValueAction)node;
				remove.setName("remove name");
				remove.setStructuralFeature(nameAttribute);
			}
		}		
		return activity;
	}
	
	private Behavior createMainEClassClassifierBehavior_SET_ENUMERATION() {
		Activity activity = INTERMED_ACTIVITIES.createActivity();
		activity.setName("MainEClassClassifierBehavior_SET_ENUMERATION");
		ReadSelfAction readSelfAction = createReadSelfAction(activity,
				"ReadSelf aStudentSystem");
		ReadStructuralFeatureAction readFeatureAction = createReadStructuralFeatureValueAction(activity, "Read students", studentsReference);
		ValueSpecificationAction specify1get = createValueSpecificationAction(activity, "specify 1", 1, false);
		ValueSpecificationAction specifyTanjania = createValueSpecificationAction(activity, "specify tanjania", studentStatusEnum.getEEnumLiteralByLiteral("PASSIVE"));
		CallBehaviorAction callListGet = createCallBehaviorAction(activity, "call list get", listgetBehavior);
		AddStructuralFeatureValueAction setStatus = createAddStructuralFeatureValueAction(activity, "set status", statusAttribute, false, true);
				
		createObjectFlow(activity, readSelfAction.getResult(), readFeatureAction.getObject());
		createObjectFlow(activity, readFeatureAction.getResult(), callListGet.getInput().get(0));
		createObjectFlow(activity, specify1get.getResult(), callListGet.getInput().get(1));
		createObjectFlow(activity, callListGet.getOutput().get(0), setStatus.getObject());
		createObjectFlow(activity, specifyTanjania.getResult(), setStatus.getValue());
				
		return activity;
	}
	
	public void setMainEClassClassifierBehavior(Behavior classifierBehavior) {
		mainEClass.getOwnedBehavior().add(classifierBehavior);
		mainEClass.setClassifierBehavior(classifierBehavior);
	}

	private EAttribute createNameAttribute() {
		EAttribute nameAttribute = ECORE.createEAttribute();
		nameAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		nameAttribute.setName(NAME);
		return nameAttribute;
	}
	
	private EStructuralFeature createNicknameAttribute() {
		nicknameAttribute = ECORE.createEAttribute();
		nicknameAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		nicknameAttribute.setName(NICKNAME);
		nicknameAttribute.setLowerBound(0);
		nicknameAttribute.setUpperBound(-1);
		return nicknameAttribute;
	}
	
	private EStructuralFeature createNicknameNotUniqueAttribute() {
		nicknameNotUniqueAttribute = ECORE.createEAttribute();
		nicknameNotUniqueAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		nicknameNotUniqueAttribute.setName(NICKNAME_NOT_UNIQUE);
		nicknameNotUniqueAttribute.setLowerBound(0);
		nicknameNotUniqueAttribute.setUpperBound(-1);
		nicknameNotUniqueAttribute.setUnique(false);
		return nicknameNotUniqueAttribute;
	}

	private EStructuralFeature createStatusAttribute() {
		statusAttribute = ECORE.createEAttribute();
		statusAttribute.setEType(studentStatusEnum);
		statusAttribute.setName(STATUS);
		return statusAttribute;
	}

	private EStructuralFeature createRefToStudents() {
		studentsReference = ECORE.createEReference();
		studentsReference.setName("students"); //$NON-NLS-1$
		studentsReference.setContainment(true);
		studentsReference.setEType(studentClass);
		studentsReference.setLowerBound(0);
		studentsReference.setUpperBound(-1);
		return studentsReference;
	}

	private EStructuralFeature createRefKnows() {
		knowsReference = ECORE.createEReference();
		knowsReference.setName("knows"); //$NON-NLS-1$
		knowsReference.setContainment(false);
		knowsReference.setEType(studentClass);
		knowsReference.setLowerBound(0);
		knowsReference.setUpperBound(-1);
		return knowsReference;
	}

	private EStructuralFeature createRefKnownBy() {
		EReference knownByReference = ECORE.createEReference();
		knownByReference.setName("knownBy"); //$NON-NLS-1$
		knownByReference.setContainment(false);
		knownByReference.setEType(studentClass);
		knownByReference.setLowerBound(0);
		knownByReference.setUpperBound(-1);
		knownByReference.setEOpposite(knowsReference);
		knowsReference.setEOpposite(knownByReference);
		return knownByReference;
	}

	private BehavioredEClass createStudentClass() {
		studentClass = KERNEL.createBehavioredEClass();
		studentClass.setName("Student"); //$NON-NLS-1$
		nameAttribute = createNameAttribute();
		studentClass.getEStructuralFeatures().add(nameAttribute);
		studentClass.getEStructuralFeatures().add(createStatusAttribute());
		studentClass.getEStructuralFeatures().add(createRefKnows());
		studentClass.getEStructuralFeatures().add(createRefKnownBy());
		studentClass.getEStructuralFeatures().add(createNicknameAttribute());
		studentClass.getEStructuralFeatures().add(createNicknameNotUniqueAttribute());
		return studentClass;
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
		action.setClassifier(eClass);
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}

	private ReadSelfAction createReadSelfAction(Activity activity, String name) {
		ReadSelfAction action = INTERMED_ACTIONS.createReadSelfAction();
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}
	
	private ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, int value, boolean unlimitedNatural) {
		ValueSpecificationAction action = createValueSpecificationAction(activity, name);
		
		ValueSpecification valueSpecification = null;
		if(!unlimitedNatural) {
			valueSpecification = KERNEL.createLiteralInteger();
			((LiteralInteger)valueSpecification).setValue(value);
			
		} else {
			valueSpecification = KERNEL.createLiteralUnlimitedNatural();
			((LiteralUnlimitedNatural)valueSpecification).setValue(value);
		}
		action.setValue(valueSpecification);
		
		return action;
	}
	
	private ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, String value) {
		ValueSpecificationAction action = createValueSpecificationAction(activity, name);
		
		LiteralString valueSpecification = KERNEL.createLiteralString();
		valueSpecification.setValue(value);
		action.setValue(valueSpecification);

		return action;
	}
	
	private ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, EEnumLiteral value) {
		ValueSpecificationAction action = createValueSpecificationAction(activity, name);
		
		EEnumLiteralSpecification valueSpecification = KERNEL.createEEnumLiteralSpecification();
		valueSpecification.setEEnumLiteral(value);
		valueSpecification.getClassifier().add(value.getEEnum());
		
		InstanceValue instanceValue = KERNEL.createInstanceValue();
		instanceValue.setInstance(valueSpecification);
		action.setValue(instanceValue);
		
		return action;
	}

	private AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(
			Activity activity, String name, EStructuralFeature eStructuralFeature, boolean insertAt, boolean replaceAll) {
		AddStructuralFeatureValueAction action = INTERMED_ACTIONS.createAddStructuralFeatureValueAction();
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		
		action.setStructuralFeature(eStructuralFeature);
		
		if(insertAt) {
			InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
			inputPin.setName("insertAt"); //$NON-NLS-1$
			inputPin.setLowerBound(1);
			inputPin.setUpperBound(1);		
			action.setInsertAt(inputPin);
		}
		
		action.setReplaceAll(replaceAll);
		
		return action;
	}
	
	private CallBehaviorAction createCallBehaviorAction(Activity activity, String name, Behavior behavior) {
		CallBehaviorAction action = BASIC_ACTIONS.createCallBehaviorAction();
		action.setName(name);
		
		action.setBehavior(behavior);				
		
		int amountinputpins = 0;
		int amountoutputpins = 0;
		for(DirectedParameter param : behavior.getOwnedParameter()) {
			if(param.getDirection() == ParameterDirectionKind.IN || param.getDirection() == ParameterDirectionKind.INOUT) {
				InputPin pin = BASIC_ACTIONS.createInputPin();
				pin.setName("InputPin " + (++amountinputpins) + " (" + name + ")");
				pin.setLowerBound(param.getLowerBound());
				pin.setUpperBound(param.getUpperBound());
				action.getArgument().add(pin);
			}
			if(param.getDirection() == ParameterDirectionKind.OUT || param.getDirection() == ParameterDirectionKind.INOUT || param.getDirection() == ParameterDirectionKind.RETURN) {
				OutputPin pin = BASIC_ACTIONS.createOutputPin();
				pin.setName("OutputPin " + (++amountoutputpins) + " (" + name + ")");
				action.getResult().add(pin);
			}
		}						
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}		
	
	private ValueSpecificationAction createValueSpecificationAction(Activity activity, String name) {
		ValueSpecificationAction action = INTERMED_ACTIONS.createValueSpecificationAction();
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);		
		return action;
	}
	
	private DestroyObjectAction createDestroyObjectAction(Activity activity,
			String name) {
		DestroyObjectAction action = INTERMED_ACTIONS
				.createDestroyObjectAction();
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}
	
	private ReadStructuralFeatureAction createReadStructuralFeatureValueAction(
			Activity activity, String name, EStructuralFeature structuralFeature) {
		ReadStructuralFeatureAction action = INTERMED_ACTIONS.createReadStructuralFeatureAction();
		action.setStructuralFeature(structuralFeature);		
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		return action;
	}

	private ClearStructuralFeatureAction createClearStructuralFeatureAction(Activity activity, String name, EStructuralFeature structuralFeature) {
		ClearStructuralFeatureAction action = INTERMED_ACTIONS.createClearStructuralFeatureAction();
		
		action.setStructuralFeature(structuralFeature);
		
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		
		return action;
	}
	
	private RemoveStructuralFeatureValueAction createRemoveStructuralFeatureValueAction(Activity activity, String name, EStructuralFeature structuralFeature, boolean removeAt, boolean isRemoveDuplicates) {
		RemoveStructuralFeatureValueAction action = INTERMED_ACTIONS.createRemoveStructuralFeatureValueAction();
		
		action.setStructuralFeature(structuralFeature);
		action.setRemoveDuplicates(isRemoveDuplicates);
		
		if(removeAt) {
			InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
			inputPin.setName("removeAt"); //$NON-NLS-1$
			inputPin.setLowerBound(1);
			inputPin.setUpperBound(1);		
			action.setRemoveAt(inputPin);
		}
		
		action.setName(name);
		action.setActivity(activity);
		activity.getNode().add(action);
		
		return action;
	}

	private ControlFlow createControlFlow(Activity activity,
			ActivityNode source, ActivityNode target) {
		ControlFlow cflow = INTERMED_ACTIVITIES.createControlFlow();
		cflow.setName("ControlFlow " + source.getName() + " --> " //$NON-NLS-1$ $NON-NLS-2$
				+ target.getName());
		cflow.setSource(source);
		cflow.setTarget(target);
		source.getOutgoing().add(cflow);
		target.getIncoming().add(cflow);
		cflow.setActivity(activity);
		activity.getEdge().add(cflow);
		return cflow;
	}

	private ObjectFlow createObjectFlow(Activity activity, ActivityNode source,
			ActivityNode target) {
		ObjectFlow oflow = INTERMED_ACTIVITIES.createObjectFlow();
		oflow.setName("ObjectFlow " + source.getName() + " --> " //$NON-NLS-1$ $NON-NLS-2$
				+ target.getName());
		oflow.setSource(source);
		oflow.setTarget(target);
		source.getOutgoing().add(oflow);
		target.getIncoming().add(oflow);
		oflow.setActivity(activity);
		activity.getEdge().add(oflow);
		return oflow;
	}

	public Resource createModelResource() {
		Resource resource = new ResourceSetImpl().createResource(URI
				.createFileURI(new File("simple-student-system1.xmi") //$NON-NLS-1$
						.getAbsolutePath()));
		EFactory factory = rootPackage.getEFactoryInstance();

		studentSystem = factory.create(mainEClass);
		studentSystem.eSet(mainEClass.getEStructuralFeature(NAME),
				"aStudentSystem"); //$NON-NLS-1$

		student1 = factory.create(studentClass);
		student1.eSet(studentClass.getEStructuralFeature(NAME), "Tanja"); //$NON-NLS-1$
		// student1.eSet(studentClass.getEStructuralFeature(STATUS),
		// studentStatusEnum.getEEnumLiteral(0));

		student2 = factory.create(studentClass);
		student2.eSet(studentClass.getEStructuralFeature(NAME), "Philip"); //$NON-NLS-1$
		// student2.eSet(studentClass.getEStructuralFeature(STATUS),
		// studentStatusEnum.getEEnumLiteral(1));

		EList<EObject> studentList = new BasicEList<EObject>();
		studentList.add(student1);
		studentList.add(student2);

		studentSystem.eSet(mainEClass.getEStructuralFeature("students"), //$NON-NLS-1$
				studentList);

		BasicEList<EObject> knowsValue = new BasicEList<EObject>();
		knowsValue.add(student2);
		student1.eSet(knowsReference, knowsValue);

		resource.getContents().add(studentSystem);
		return resource;
	}

	public MainEClass getMainEClass() {
		return mainEClass;
	}

	public EPackage getRootPackage() {
		return rootPackage;
	}

	public BehavioredEClass getStudentClass() {
		return studentClass;
	}

	public EObject getStudent1() {
		return student1;
	}

	public EObject getStudent2() {
		return student2;
	}

	public EObject getStudentSystem() {
		return studentSystem;
	}

	public EReference getStudentsReference() {
		return studentsReference;
	}

	public EAttribute getStudentNicknameAttribute() {
		return nicknameAttribute;
	}

	public EAttribute getStudentNameAttribute() {
		return nameAttribute;
	}

	public EAttribute getStudentNicknameNotUniqueAttribute() {
		return nicknameNotUniqueAttribute;
	}

	public EEnum getStudentStatusEnum() {
		return studentStatusEnum;
	}

	public EAttribute getStudentStatusAttribute() {
		return statusAttribute;
	}

}
