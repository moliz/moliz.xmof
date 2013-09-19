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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;

public class ConfigurationGenerator {

	protected static final String MAIN = "main"; //$NON-NLS-1$
	private static final String URI_SEPARATOR = "/"; //$NON-NLS-1$
	private static final String CONF = "Conf"; //$NON-NLS-1$
	private static final String CONFIGURATION = "Configuration"; //$NON-NLS-1$

	private Collection<EPackage> inputPackages;
	private Collection<EClass> mainClasses = new ArrayList<EClass>();

	public ConfigurationGenerator(Collection<EPackage> inputPackages, Collection<EClass> mainClasses) {
		super();
		initialize(inputPackages, mainClasses);
	}

	private void initialize(Collection<EPackage> inputPackages, Collection<EClass> mainClasses) {
		this.inputPackages = new ArrayList<EPackage>(inputPackages);
		if(mainClasses != null) {
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
				
		for (EClassifier inputClassifier : inputPackage.getEClassifiers()) {
			if (isConfigurationNeeded(inputClassifier)) {
				BehavioredEClass configurationClass = generateConfigurationClass((EClass) inputClassifier);
				configurationPackage.getEClassifiers().add(configurationClass);
			}
		}
				
		for (EPackage subPackage : inputPackage.getESubpackages()) {
			configurationPackage.getESubpackages().add(
					generateConfigurationPackage(subPackage));
		}
						
		return configurationPackage;
	}

	private boolean isConfigurationNeeded(EClassifier inputClassifier) {
		return inputClassifier instanceof EClass;				
	}

	private BehavioredEClass generateConfigurationClass(EClass inputClass) {
		BehavioredEClass beClass = createBehavioredEClass(inputClass);
		beClass.getESuperTypes().add(inputClass);
		beClass.setName(inputClass.getName() + CONFIGURATION);
		return beClass;
	}

	private BehavioredEClass createBehavioredEClass(EClass inputClass) {
		BehavioredEClass behavioredEClass = KernelFactory.eINSTANCE.createBehavioredEClass();
		if(mainClasses.contains(inputClass)) {
			behavioredEClass.getEOperations().add(createMainOperation());
		}
		return  behavioredEClass;
	}

	private BehavioredEOperation createMainOperation() {
		BehavioredEOperation mainOperation = KernelFactory.eINSTANCE.createBehavioredEOperation();
		mainOperation.setName(MAIN);
		return mainOperation;
		
	}

	private EcoreFactory getEcoreFactory() {
		return EcoreFactory.eINSTANCE;
	}
	
}
