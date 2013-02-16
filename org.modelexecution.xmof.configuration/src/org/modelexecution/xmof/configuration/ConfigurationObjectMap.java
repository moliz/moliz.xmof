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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

public class ConfigurationObjectMap {
	
	protected final static EClass MAIN_E_CLASS = KernelPackage.eINSTANCE.getMainEClass();
	protected final static EClass BEHAVIORED_E_CLASS = KernelPackage.eINSTANCE.getBehavioredEClass();
	
	private Collection<EObject> initializationObjects;
	private Collection<EObject> originalObjects;
	private Collection<EObject> configurationObjects;
	private Collection<EPackage> configurationPackages;
	private Map<EClass, EClass> originalClassToConfigurationClassMap;
	private Map<EObject, EObject> originalToConfigurationObjectMap;
	private Map<EObject, EObject> configurationToOriginalObjectMap;

	public ConfigurationObjectMap(Collection<EObject> originalObjects,
			Collection<EPackage> configurationPackages, Collection<EObject> initializationObjects) {
		super();
		this.originalObjects = originalObjects;
		this.configurationPackages = configurationPackages;
		this.initializationObjects = initializationObjects;
		initialize();
	}

	private void initialize() {
		initializeCollectionsAndMaps();
		createConfigurationObjects();
		setReferenceValuesOfConfigurationObjects();
		setReferenceBetweenMainEClassObjectAndInitializationRootObject();
	}

	private void initializeCollectionsAndMaps() {
		this.configurationObjects = new HashSet<EObject>();
		this.originalToConfigurationObjectMap = new HashMap<EObject, EObject>();
		this.configurationToOriginalObjectMap = new HashMap<EObject, EObject>();
		this.originalClassToConfigurationClassMap = new HashMap<EClass, EClass>();
	}

	private void createConfigurationObjects() {
		for (EObject originalObject : originalObjects) {
			createConfigurationObject(originalObject);
		}
	}

	private void createConfigurationObject(EObject originalObject) {
		EClass originalClass = originalObject.eClass();
		EClass configurationClass = getConfigurationClass(originalClass);
		EObject configurationObject = createConfigurationObject(originalObject,
				configurationClass);
		addToMap(originalObject, configurationObject);
		configurationObjects.add(configurationObject);
		createMappedObjectsOfChildren(originalObject);
	}

	private EClass getConfigurationClass(EClass originalClass) {
		if (originalClassToConfigurationClassMap.containsKey(originalClass)) {
			return originalClassToConfigurationClassMap.get(originalClass);
		}

		for (EPackage configurationPackage : configurationPackages) {
			EClass configurationClass = getConfigurationClass(originalClass,
					configurationPackage);
			if (configurationClass != null) {
				originalClassToConfigurationClassMap.put(originalClass,
						configurationClass);
				return configurationClass;
			}
		}
		return null;
	}

	private EClass getConfigurationClass(EClass originalClass,
			EPackage configurationPackage) {
		for (EClassifier configurationClassifier : configurationPackage
				.getEClassifiers()) {
			if (isEClass(configurationClassifier)
					&& isConfigurationClass(originalClass,
							(EClass) configurationClassifier)) {
				return (EClass) configurationClassifier;
			}
		}
		if (!configurationPackage.getESubpackages().isEmpty()) {
			for (EPackage configurationSubPackage : configurationPackage
					.getESubpackages()) {
				EClass configurationClass = getConfigurationClass(
						originalClass, configurationSubPackage);
				if (configurationClass != null) {
					return configurationClass;
				}
			}
		}
		return null;
	}

	private boolean isEClass(EClassifier configurationClassifier) {
		return configurationClassifier instanceof EClass;
	}
	
	private boolean isBehavioredEClass(EClassifier classifier) {
		return BEHAVIORED_E_CLASS.isInstance(classifier);
	}

	private boolean isConfigurationClass(EClass originalClass,
			EClass configurationClassifier) {
		return configurationClassifier.getName().equals(
				originalClass.getName() + "Configuration");
	}

	private EObject createConfigurationObject(EObject originalObject,
			EClass configurationClass) {
		EFactory factory = configurationClass.getEPackage()
				.getEFactoryInstance();
		EObject configurationObject = factory.create(configurationClass);
		return setAttributeValues(configurationObject, originalObject);
	}

	private EObject setAttributeValues(EObject configurationObject,
			EObject originalObject) {
		for (EAttribute eAttribute : originalObject.eClass()
				.getEAllAttributes()) {
			configurationObject.eSet(configurationObject.eClass()
					.getEStructuralFeature(eAttribute.getName()),
					originalObject.eGet(eAttribute));
		}
		return configurationObject;
	}

	private void createMappedObjectsOfChildren(EObject originalObject) {
		for (EObject originalChildObject : originalObject.eContents()) {
			createConfigurationObject(originalChildObject);
		}
	}

	private void addToMap(EObject originalObject, EObject mappedObject) {
		originalToConfigurationObjectMap.put(originalObject, mappedObject);
		configurationToOriginalObjectMap.put(mappedObject, originalObject);
	}
	
	private void setReferenceBetweenMainEClassObjectAndInitializationRootObject() {
		EObject mainEClassObject = getMainEClassConfigurationObject();
		EObject initializationRootObject = getInitializationRootObject();
		EReference initializationReference = getInitializationReference(mainEClassObject, initializationRootObject);
		
		if(mainEClassObject != null && initializationRootObject != null && initializationReference != null) {
			mainEClassObject.eSet(mainEClassObject.eClass().getEStructuralFeature(initializationReference.getName()), initializationRootObject);
		}
	}
	
	private EReference getInitializationReference(EObject fromObject, EObject toObject) {
		if(fromObject == null || toObject == null) {
			return null;
		}
		
		EClass fromClass = fromObject.eClass();
		EClass toClass = toObject.eClass();
		
		for(EReference reference : fromClass.getEAllReferences()) {
			if(reference.getEType().equals(toClass)) {
				return reference;
			}
		}
		return null;
	}
	
	private EObject getMainEClassConfigurationObject() {
		for(EObject confObject : configurationObjects) {
			if (MAIN_E_CLASS.isInstance(confObject.eClass())) {
				return confObject;
			}
		}
		return null;
	}
	
	private EObject getInitializationRootObject() {
		EClass initRootClass = getInitializationRootClass();
		for(EObject initializationObject : initializationObjects) {
			if(initRootClass.isInstance(initializationObject)) {
				return initializationObject;
			}
		}
		return null;
	}
	private EClass getInitializationRootClass() {
		Collection<EClass> initializationClasses = getInitializationClasses(configurationPackages);
		for(EClass initializationClass : initializationClasses) {
			if(initializationClass.eContainer() instanceof EPackage) {
				return initializationClass;
			}
		}
		return null;
	}
	private Collection<EClass> getInitializationClasses(Collection<EPackage> configurationPackages) {
		Collection<EClass> initializationClasses = new HashSet<EClass>();
		for(EPackage configurationPackage : configurationPackages) {
			for(EClassifier configurationClassifier : configurationPackage.getEClassifiers()) {
				if(isEClass(configurationClassifier) && !isBehavioredEClass(configurationClassifier) && !originalClassToConfigurationClassMap.containsValue(configurationClassifier)) {
					initializationClasses.add((EClass)configurationClassifier);
				}
			}
			if(!configurationPackage.getESubpackages().isEmpty()) {
				initializationClasses.addAll(getInitializationClasses(configurationPackage.getESubpackages()));
			}
		}
		return initializationClasses;
	}

	private void setReferenceValuesOfConfigurationObjects() {
		for (Entry<EObject, EObject> entry : configurationToOriginalObjectMap
				.entrySet()) {
			EObject configurationObject = entry.getKey();
			EObject originalObject = entry.getValue();
			setReferenceValues(configurationObject, originalObject);
		}
	}

	private void setReferenceValues(EObject configurationObject,
			EObject originalObject) {
		for (EReference eReference : originalObject.eClass()
				.getEAllReferences()) {
			Object originalValue = originalObject.eGet(eReference, true);
			Object newValue;
			if (eReference.isMany()) {
				EList<EObject> newValueList = new BasicEList<EObject>();
				EList<?> originalValueList = (EList<?>) originalValue;
				for (Object originalValueObject : originalValueList) {
					newValueList.add(originalToConfigurationObjectMap
							.get(originalValueObject));
				}
				newValue = newValueList;
			} else {
				newValue = (EObject) originalToConfigurationObjectMap
						.get(originalValue);
			}
			configurationObject.eSet(configurationObject.eClass()
					.getEStructuralFeature(eReference.getName()), newValue);
		}
	}

	public Collection<EObject> getOriginalObjects() {
		return originalObjects;
	}

	public Collection<EObject> getConfigurationObjects() {
		Collection<EObject> objects = new HashSet<EObject>();
		objects.addAll(configurationObjects);
		objects.addAll(initializationObjects);
		return objects;
	}

	public Collection<EPackage> getConfigurationPackages() {
		return configurationPackages;
	}

	public EObject getConfigurationObject(EObject originalObject) {
		return originalToConfigurationObjectMap.get(originalObject);
	}

	public EObject getOriginalObject(EObject configurationObject) {
		return configurationToOriginalObjectMap.get(configurationObject);
	}

}
