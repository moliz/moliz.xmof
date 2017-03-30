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
import java.util.Set;

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

	protected final static EClass BEHAVIORED_E_CLASS = KernelPackage.eINSTANCE.getBehavioredEClass();

	private Collection<EObject> originalObjects;
	private Collection<EObject> configurationObjects;
	private Collection<EPackage> configurationPackages;
	private Map<EClass, EClass> originalClassToConfigurationClassMap;
	private Map<EObject, EObject> originalToConfigurationObjectMap;
	private Map<EObject, EObject> configurationToOriginalObjectMap;

	public ConfigurationObjectMap(Collection<EObject> originalOrConfigurationObjects,
			Collection<EPackage> configurationPackages, boolean providedAreConfiguration) {
		super();
		initializeCollectionsAndMaps();
		this.originalObjects.addAll(originalOrConfigurationObjects);
		this.configurationPackages.addAll(configurationPackages);
		initialize(providedAreConfiguration);
	}

	public ConfigurationObjectMap(Collection<EObject> originalObjects, Collection<EPackage> configurationPackages) {
		this(originalObjects, configurationPackages, false);
	}

	private void initialize(boolean providedAreConfiguration) {
		if (providedAreConfiguration) {
			configurationObjects.addAll(originalObjects);
			Collection<EObject> originalObjectsCopy = new HashSet<EObject>(originalObjects);
			for (EObject originalObject : originalObjectsCopy) {
				Set<EObject> allChildren = new HashSet<EObject>();
				originalObject.eAllContents().forEachRemaining((child) -> allChildren.add(child));
				allChildren.add(originalObject);
				for (EObject o : allChildren) {
					originalObjects.add(o);
					configurationObjects.add(o);
					originalToConfigurationObjectMap.put(o, o);
					configurationToOriginalObjectMap.put(o, o);
					originalClassToConfigurationClassMap.put(o.eClass(), o.eClass());
				}
			}
		} else {
			createConfigurationObjects();
			setReferenceValuesOfConfigurationObjects();
		}
	}

	private void initializeCollectionsAndMaps() {
		this.originalObjects = new HashSet<EObject>();
		this.configurationPackages = new HashSet<EPackage>();
		this.configurationObjects = new HashSet<EObject>();
		this.originalToConfigurationObjectMap = new HashMap<EObject, EObject>();
		this.configurationToOriginalObjectMap = new HashMap<EObject, EObject>();
		this.originalClassToConfigurationClassMap = new HashMap<EClass, EClass>();
	}

	private void createConfigurationObjects() {
		for (EObject originalObject : originalObjects) {
			if (!originalToConfigurationObjectMap.containsKey(originalObject))
				createConfigurationObject(originalObject);
		}
	}

	private void createConfigurationObject(EObject originalObject) {
		EClass originalClass = originalObject.eClass();
		EClass configurationClass = getConfigurationClass(originalClass);
		EObject configurationObject = createConfigurationObject(originalObject, configurationClass);
		addToMap(originalObject, configurationObject);
		configurationObjects.add(configurationObject);
		createMappedObjectsOfChildren(originalObject);
	}

	private EClass getConfigurationClass(EClass originalClass) {
		if (originalClassToConfigurationClassMap.containsKey(originalClass)) {
			return originalClassToConfigurationClassMap.get(originalClass);
		}

		for (EPackage configurationPackage : configurationPackages) {
			EClass configurationClass = getConfigurationClass(originalClass, configurationPackage);
			if (configurationClass != null) {
				originalClassToConfigurationClassMap.put(originalClass, configurationClass);
				return configurationClass;
			}
		}
		return null;
	}

	private EClass getConfigurationClass(EClass originalClass, EPackage configurationPackage) {
		for (EClassifier configurationClassifier : configurationPackage.getEClassifiers()) {
			if (isEClass(configurationClassifier)
					&& isConfigurationClass(originalClass, (EClass) configurationClassifier)) {
				return (EClass) configurationClassifier;
			}
		}
		if (!configurationPackage.getESubpackages().isEmpty()) {
			for (EPackage configurationSubPackage : configurationPackage.getESubpackages()) {
				EClass configurationClass = getConfigurationClass(originalClass, configurationSubPackage);
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

	private boolean isConfigurationClass(EClass originalClass, EClass configurationClass) {
		if (originalClass == configurationClass) {
			return true;
		}
		return (configurationClass.getESuperTypes().contains(originalClass))
				&& configurationClass.getName().equals(originalClass.getName() + "Configuration");
	}

	private EObject createConfigurationObject(EObject originalObject, EClass configurationClass) {
		EFactory factory = configurationClass.getEPackage().getEFactoryInstance();
		EObject configurationObject = factory.create(configurationClass);
		return setAttributeValues(configurationObject, originalObject);
	}

	private EObject setAttributeValues(EObject configurationObject, EObject originalObject) {
		for (EAttribute eAttribute : originalObject.eClass().getEAllAttributes()) {
			if (eAttribute.isChangeable() && !eAttribute.isDerived())
				if (originalObject.eIsSet(eAttribute)) {
					configurationObject.eSet(configurationObject.eClass().getEStructuralFeature(eAttribute.getName()),
							originalObject.eGet(eAttribute));
				}
		}
		return configurationObject;
	}

	private void createMappedObjectsOfChildren(EObject originalObject) {
		for (EObject originalChildObject : originalObject.eContents()) {
			createConfigurationObject(originalChildObject);
		}
	}

	protected void addToMap(EObject originalObject, EObject mappedObject) {
		originalToConfigurationObjectMap.put(originalObject, mappedObject);
		configurationToOriginalObjectMap.put(mappedObject, originalObject);
	}

	private void setReferenceValuesOfConfigurationObjects() {
		for (Entry<EObject, EObject> entry : configurationToOriginalObjectMap.entrySet()) {
			EObject configurationObject = entry.getKey();
			EObject originalObject = entry.getValue();
			setReferenceValues(configurationObject, originalObject);
		}
	}

	private void setReferenceValues(EObject configurationObject, EObject originalObject) {
		for (EReference eReference : originalObject.eClass().getEAllReferences()) {
			if (eReference.isChangeable() && !eReference.isDerived()) {
				if (originalObject.eIsSet(eReference)) {
					Object originalValue = originalObject.eGet(eReference, true);
					Object newValue;
					if (eReference.isMany()) {
						EList<EObject> newValueList = new BasicEList<EObject>();
						EList<?> originalValueList = (EList<?>) originalValue;
						for (Object originalValueObject : originalValueList) {
							newValueList.add(originalToConfigurationObjectMap.get(originalValueObject));
						}
						newValue = newValueList;
					} else {
						newValue = (EObject) originalToConfigurationObjectMap.get(originalValue);
					}

					configurationObject.eSet(configurationObject.eClass().getEStructuralFeature(eReference.getName()),
							newValue);
				}
			}
		}
	}

	public Collection<EObject> getOriginalObjects() {
		return originalObjects;
	}

	public Collection<EObject> getConfigurationObjects() {
		Collection<EObject> objects = new HashSet<EObject>();
		objects.addAll(configurationObjects);
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
