/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.uml2.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;

/**
 * Represents the input of a UML2 to fUML conversion and obtains its elements to
 * be converted.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2Input {

	private Object originalInput;
	private Collection<EObject> eObjectsToConvert;

	public UML2Input(Object input) {
		super();
		this.originalInput = input;
		eObjectsToConvert = deriveEObjectsToConvertFromInput();
	}

	public Object getOriginalInput() {
		return originalInput;
	}

	public Collection<EObject> deriveEObjectsToConvertFromInput() {
		if (originalInput instanceof EObject) {
			return getEObjectsToConvertFromEObject((EObject) originalInput);
		} else if (originalInput instanceof Resource) {
			return getEObjectsToConvertFromResource((Resource) originalInput);
		}
		throw new IllegalArgumentException(
				"Cannot obtain objects to convert from input.");
	}

	private Collection<EObject> getEObjectsToConvertFromEObject(EObject eObject) {
		if (eObject.eResource() != null) {
			return getEObjectsToConvertFromResource(eObject.eResource());
		} else {
			return Arrays.asList((new EObject[] { EcoreUtil.getRootContainer(
					eObject, true) }));
		}
	}

	private Collection<EObject> getEObjectsToConvertFromResource(
			Resource eResource) {
		if (eResource.getResourceSet() != null) {
			return getEObjectsToConvertFromResourceSet(eResource
					.getResourceSet());
		} else {
			return eResource.getContents();
		}
	}

	private Collection<EObject> getEObjectsToConvertFromResourceSet(
			ResourceSet resourceSet) {
		Collection<EObject> eObjectsToConvert = new HashSet<EObject>();
		for (Resource resource : resourceSet.getResources()) {
			eObjectsToConvert.addAll(resource.getContents());
		}
		return eObjectsToConvert;
	}

	public Collection<Element> getElementsToConvert() {
		Collection<Element> elementsToConvert = new HashSet<Element>();
		for (EObject eObjectToConvert : eObjectsToConvert) {
			if (eObjectToConvert instanceof Element) {
				elementsToConvert.add((Element) eObjectToConvert);
			}
		}
		return elementsToConvert;
	}

}
