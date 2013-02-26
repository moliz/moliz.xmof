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
import org.modelexecution.fuml.convert.IConversionResult;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * Provides a mapping of xMOF-based models to fUML {@link ExtensionalValue
 * extensional values}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFInstanceMap {

	private Map<ExtensionalValue, EObject> objectToEObjectMap = new HashMap<ExtensionalValue, EObject>();
	private Map<EObject, ExtensionalValue> eObjectToObjectMap = new HashMap<EObject, ExtensionalValue>();
	private Map<Class_, EClass> classToEClassMap = new HashMap<Class_, EClass>();
	private Map<EClass, Class_> eClassToClassMap = new HashMap<EClass, Class_>();

	public XMOFInstanceMap(IConversionResult result,
			List<EObject> modelElements, Locus locus) {
		new XMOFInstanceMapBuilder(this).build(result, modelElements, locus);
	}

	public EClass getEClass(Class_ class_) {
		return classToEClassMap.get(class_);
	}
	
	public Class_ getClass(EClass eClass) {
		return eClassToClassMap.get(eClass);
	}
	
	public Collection<ExtensionalValue> getExtensionalValues() {
		return Collections.unmodifiableCollection(objectToEObjectMap.keySet());
	}

	public Object_ getObject(EObject eObject) {
		return (Object_) eObjectToObjectMap.get(eObject);
	}

	public EObject getEObject(Object_ object) {
		return objectToEObjectMap.get(object);
	}

	protected void addMapping(Object_ object, EObject eObject) {
		objectToEObjectMap.put(object, eObject);
		eObjectToObjectMap.put(eObject, object);
	}

	protected void removeMapping(Object_ object) {
		eObjectToObjectMap.remove(objectToEObjectMap.get(object));
		objectToEObjectMap.remove(object);
	}

	protected void removeMapping(EObject eObject) {
		objectToEObjectMap.remove(eObjectToObjectMap.get(eObject));
		eObjectToObjectMap.remove(eObject);
	}
	
	protected void addMapping(Class_ class_, EClass eClass) {
		classToEClassMap.put(class_, eClass);
		eClassToClassMap.put(eClass, class_);
	}

	protected void addExtensionalValue(Link link) {
		// links have no corresponding object in EMF, so we add it as key only
		// to keep track of all extensional values without mappings
		objectToEObjectMap.put(link, null);
	}

	protected Collection<EObject> getAllEObjects() {
		return eObjectToObjectMap.keySet();
	}

}
