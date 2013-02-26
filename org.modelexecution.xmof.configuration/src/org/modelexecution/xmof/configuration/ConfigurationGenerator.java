/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.MainEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsFactory;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ConfigurationGenerator {

	private static final String MAIN = "Main"; //$NON-NLS-1$
	private static final String URI_SEPARATOR = "/"; //$NON-NLS-1$
	private static final String CONF = "Conf"; //$NON-NLS-1$
	private static final String CONFIGURATION = "Configuration"; //$NON-NLS-1$
	private static final String INITIALIZATION_CLASS = "Initialization"; //$NON-NLS-1$
	private static final String INITIALIZATION_REFERENCE = "init"; //$NON-NLS-1$

	private Collection<EPackage> inputPackages;
	private Collection<EClass> mainClasses = new ArrayList<EClass>();

	public ConfigurationGenerator(Collection<EPackage> inputPackages,
			Collection<EClass> mainClasses) {
		super();
		initialize(inputPackages, mainClasses);
	}

	private void initialize(Collection<EPackage> inputPackages,
			Collection<EClass> mainClasses) {
		this.inputPackages = new ArrayList<EPackage>(inputPackages);
		if (mainClasses != null) {
			this.mainClasses = new ArrayList<EClass>(mainClasses);
		}
	}

	public Collection<EPackage> generateConfigurationPackages() {
		List<EPackage> configurationPackages = new ArrayList<EPackage>();
		for (EPackage inputPackage : inputPackages) {
			configurationPackages
					.add(generateConfigurationPackage(inputPackage));
		}
		return configurationPackages;
	}

	private EPackage generateConfigurationPackage(EPackage inputPackage) {
		EPackage configurationPackage = getEcoreFactory().createEPackage();
		configurationPackage.setName(inputPackage.getName() + CONFIGURATION);
		configurationPackage.setNsPrefix(inputPackage.getNsPrefix() + CONF);
		configurationPackage.setNsURI(inputPackage.getNsURI() + URI_SEPARATOR
				+ CONFIGURATION.toLowerCase());
				
		EClass initializationClass = generateInitializationClass();
		boolean initializationClassReferenced = false;		
		
		for (EClassifier inputClassifier : inputPackage.getEClassifiers()) {
			if (isConcreteEClass(inputClassifier)) {
				BehavioredEClass configurationClass = generateConfigurationClass((EClass) inputClassifier);
				configurationPackage.getEClassifiers().add(configurationClass);
				if(configurationClass instanceof MainEClass) {
					EReference initializationReference = generateInitializationReference(initializationClass);
					configurationClass.getEStructuralFeatures().add(initializationReference);
					initializationClassReferenced = true;
				}
			}
		}
		
		if(initializationClassReferenced) {
			configurationPackage.getEClassifiers().add(initializationClass);
		}
		
		for (EPackage subPackage : inputPackage.getESubpackages()) {
			configurationPackage.getESubpackages().add(
					generateConfigurationPackage(subPackage));
		}
		
		configurationPackage.getEClassifiers().addAll(createPrimitiveBehaviors());				
				
		return configurationPackage;
	}

	private EClass generateInitializationClass() {
		EClass initializationClass = getEcoreFactory().createEClass();
		initializationClass.setName(INITIALIZATION_CLASS);
		return initializationClass;
	}

	private EReference generateInitializationReference(
			EClass initializationClass) {
		EReference init = getEcoreFactory().createEReference();
		init.setName(INITIALIZATION_REFERENCE);
		init.setContainment(true);
		init.setLowerBound(0);
		init.setUpperBound(1);
		init.setEType(initializationClass);
		return init;
	}

	private boolean isConcreteEClass(EClassifier inputClassifier) {
		return inputClassifier instanceof EClass
				&& !((EClass) inputClassifier).isAbstract();
	}

	private BehavioredEClass generateConfigurationClass(EClass inputClass) {
		BehavioredEClass beClass = createBehavioredEClass(inputClass);
		beClass.getESuperTypes().add(inputClass);
		beClass.setName(inputClass.getName() + CONFIGURATION);
		return beClass;
	}

	private BehavioredEClass createBehavioredEClass(EClass inputClass) {
		if (mainClasses.contains(inputClass)) {
			MainEClass mainEClass = KernelFactory.eINSTANCE.createMainEClass();
			Behavior classifierBehavior = createMainBehavior(MAIN);
			mainEClass.getOwnedBehavior().add(classifierBehavior);
			mainEClass.setClassifierBehavior(classifierBehavior);
			return mainEClass;
		} else {
			return KernelFactory.eINSTANCE.createBehavioredEClass();
		}
	}

	private Behavior createMainBehavior(String name) {
		Activity activity = IntermediateActivitiesFactory.eINSTANCE
				.createActivity();
		activity.setName(name);
		return activity;
	}

	private EcoreFactory getEcoreFactory() {
		return EcoreFactory.eINSTANCE;
	}

	private EList<OpaqueBehavior> createPrimitiveBehaviors() {
		EList<OpaqueBehavior> primitiveBehaviors = new BasicEList<OpaqueBehavior>();
		primitiveBehaviors.add(createAddBehavior());
		primitiveBehaviors.add(createSubtractBehavior());
		primitiveBehaviors.add(createMultiplyBehavior());
		primitiveBehaviors.add(createDivideBehavior());
		primitiveBehaviors.add(createSmallerBehavior());
		primitiveBehaviors.add(createGreaterBehavior());
		primitiveBehaviors.add(createListgetBehavior());
		primitiveBehaviors.add(createListsizeBehavior());				
		primitiveBehaviors.add(createListindexofBehavior());
		return primitiveBehaviors;
	}
	
	private OpaqueBehavior createListindexofBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("listindexof");
		
		DirectedParameter list = createDirectedParameter("list", ParameterDirectionKind.IN);
		list.setLowerBound(0);
		list.setUpperBound(-1);
		behavior.getOwnedParameter().add(list);
		
		DirectedParameter index = createDirectedParameter("object", ParameterDirectionKind.IN);
		index.setLowerBound(1);
		index.setUpperBound(1);
		behavior.getOwnedParameter().add(index);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(0);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createDivideBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("divide");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createMultiplyBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("multiply");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createAddBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("add");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createSubtractBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("subtract");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createGreaterBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("greater");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createSmallerBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("smaller");
		
		DirectedParameter inparam1 = createDirectedParameter("x", ParameterDirectionKind.IN);
		inparam1.setLowerBound(1);
		inparam1.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam1);
		
		DirectedParameter inparam2 = createDirectedParameter("y", ParameterDirectionKind.IN);
		inparam2.setLowerBound(1);
		inparam2.setUpperBound(1);
		behavior.getOwnedParameter().add(inparam2);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createListgetBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("listget");
		
		DirectedParameter list = createDirectedParameter("list", ParameterDirectionKind.IN);
		list.setLowerBound(1);
		list.setUpperBound(-1);
		behavior.getOwnedParameter().add(list);
		
		DirectedParameter index = createDirectedParameter("index", ParameterDirectionKind.IN);
		index.setLowerBound(1);
		index.setUpperBound(1);
		behavior.getOwnedParameter().add(index);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(0);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;
	}
	
	private OpaqueBehavior createListsizeBehavior() {
		OpaqueBehavior behavior = BasicBehaviorsFactory.eINSTANCE.createOpaqueBehavior();		
		behavior.setName("listsize");
		
		DirectedParameter inparam = createDirectedParameter("list", ParameterDirectionKind.IN);
		inparam.setLowerBound(0);
		inparam.setUpperBound(-1);
		behavior.getOwnedParameter().add(inparam);
		
		DirectedParameter outparam = createDirectedParameter("result", ParameterDirectionKind.OUT);
		outparam.setLowerBound(1);
		outparam.setUpperBound(1);
		behavior.getOwnedParameter().add(outparam);
		
		return behavior;				
	}
	
	private DirectedParameter createDirectedParameter(String name, ParameterDirectionKind direction) {
		DirectedParameter param = KernelFactory.eINSTANCE.createDirectedParameter();
		param.setName(name);
		param.setDirection(direction);		
		return param;
	}
	
}
