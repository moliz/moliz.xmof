/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.modelexecution.fuml.convert.IConversionResult;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * Converts instances of xMOF-based models to fUML {@link ExtensionalValue
 * extensional values} and keeps track of them.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFInstanceMapper {

	private IConversionResult conversionResult;
	private Map<Object_, EObject> objectToEObjectMap = new HashMap<Object_, EObject>();
	private Map<EObject, Object_> eObjectToObjectMap = new HashMap<EObject, Object_>();

	public XMOFInstanceMapper(IConversionResult result,
			List<EObject> modelElements) {
		this.conversionResult = result;
		initialize(modelElements);
	}

	private void initialize(List<EObject> modelElements) {
		createObjects(modelElements);
		setLinks();
		setValues();
	}

	private void createObjects(List<EObject> modelElements) {
		for (EObject eObject : modelElements) {
			createObject(eObject);
		}
	}

	private void createObject(EObject eObject) {
		Object_ object = new Object_();
		addSuperTypes(eObject, object);
		object.createFeatureValues();
		addMapping(object, eObject);
		createChildObjects(eObject);
	}

	private void addSuperTypes(EObject eObject, Object_ object) {
		object.types.add((Class_) conversionResult.getFUMLElement(eObject
				.eClass()));
		for (EClass superType : eObject.eClass().getEAllSuperTypes()) {
			object.types.add((Class_) conversionResult
					.getFUMLElement(superType));
		}
	}

	private void createChildObjects(EObject eObject) {
		for (EObject eChildObject : eObject.eContents()) {
			createObject(eChildObject);
		}
	}

	private void setLinks() {
		for (EObject eObject : eObjectToObjectMap.keySet()) {
			setContainmentLink(eObject);
			setCrossReferenceLinks(eObject);
		}
	}

	private void setContainmentLink(EObject eObject) {
		if (eObject.eContainer() != null) {
			EObject eContainer = eObject.eContainer();
			EReference eReference = eObject.eContainmentFeature();
			addLink(eContainer, eObject, eReference);
		}
	}

	private void setCrossReferenceLinks(EObject eObject) {
		for (EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) eObject
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject referencedEObject = (EObject) featureIterator.next();
			EReference eReference = (EReference) featureIterator.feature();
			addLink(eObject, referencedEObject, eReference);
		}
	}

	private void addLink(EObject sourceEObject, EObject targetEObject,
			EReference eReference) {
		Object_ sourceObject = getObject(sourceEObject);
		Object_ targetObject = getObject(targetEObject);
		Association association = (Association) conversionResult
				.getFUMLElement(eReference);

		int index = 0;
		if (eReference.isMany()) {
			List<?> values = (List<?>) sourceEObject.eGet(eReference);
			index = values.indexOf(targetEObject);
		}

		Link newLink = new Link();
		newLink.type = association;
		// TODO check how to configure and set the link
		//newLink.setFeatureValue(endData.end, asValueList(targetObject), index);
	}

	private ValueList asValueList(Object_ object) {
		ValueList valueList = new ValueList();
		valueList.add(object);
		return valueList;
	}

	private void setValues() {
		// TODO implement
	}

	private void addMapping(Object_ object, EObject eObject) {
		objectToEObjectMap.put(object, eObject);
		eObjectToObjectMap.put(eObject, object);
	}

	private void removeMapping(Object_ object) {
		eObjectToObjectMap.remove(objectToEObjectMap.get(object));
		objectToEObjectMap.remove(object);
	}

	private void removeMapping(EObject eObject) {
		objectToEObjectMap.remove(eObjectToObjectMap.get(eObject));
		eObjectToObjectMap.remove(eObject);
	}

	public Collection<ExtensionalValue> getExtensionalValues() {
		// TODO implement
		return Collections.emptyList();
	}

	public Object_ getObject(EObject eObject) {
		return eObjectToObjectMap.get(eObject);
	}

	public EObject getEObject(Object_ object) {
		return objectToEObjectMap.get(object);
	}

}
