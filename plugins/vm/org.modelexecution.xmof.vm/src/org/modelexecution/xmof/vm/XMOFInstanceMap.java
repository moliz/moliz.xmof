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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.xmof.Semantics.Classes.Kernel.BooleanValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.IntegerValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.StringValue;
import org.modelexecution.xmof.vm.internal.XMOFInstanceMapBuilder;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
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

	private XMOFInstanceMapBuilder instanceMapBuilder;

	protected XMOFInstanceMap() {
	}

	public XMOFInstanceMap(IConversionResult result,
			List<EObject> modelElements, Locus locus) {
		instanceMapBuilder = new XMOFInstanceMapBuilder(this);
		instanceMapBuilder.build(result, modelElements, locus);
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

	public void addMapping(Object_ object, EObject eObject) {
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

	public void addMapping(Class_ class_, EClass eClass) {
		classToEClassMap.put(class_, eClass);
		eClassToClassMap.put(eClass, class_);
	}

	public void addExtensionalValue(Link link) {
		// links have no corresponding object in EMF, so we add it as key only
		// to keep track of all extensional values without mappings
		objectToEObjectMap.put(link, null);
	}

	public void removeExtensionalValue(Link link) {
		objectToEObjectMap.remove(link);
	}

	public Collection<EObject> getAllEObjects() {
		return eObjectToObjectMap.keySet();
	}

	private Value getValue(Object value, EClassifier valueType) {
		if (value instanceof EObject) {
			return getObject((EObject) value);
		}
		if (valueType instanceof EDataType) {
			return instanceMapBuilder.createFUMLValue(value,
					(EDataType) valueType);
		}
		return null;
	}

	public Value getValue(
			org.modelexecution.xmof.Semantics.Classes.Kernel.Value value) {
		if (value instanceof StringValue) {
			StringValue stringValue = (StringValue) value;
			return getValue(stringValue.getValue(),
					EcorePackage.eINSTANCE.getEString());
		} else if (value instanceof BooleanValue) {
			BooleanValue booleanValue = (BooleanValue) value;
			return getValue(booleanValue.isValue(),
					EcorePackage.eINSTANCE.getEBoolean());
		} else if (value instanceof IntegerValue) {
			IntegerValue integerValue = (IntegerValue) value;
			return getValue(integerValue.getValue(),
					EcorePackage.eINSTANCE.getEInt());
		} else if (value instanceof EnumerationValue) {
			EnumerationValue enumerationValue = (EnumerationValue) value;
			return getValue(enumerationValue.getLiteral(),
					enumerationValue.getType());
		} else if (value instanceof ObjectValue) {
			ObjectValue objectValue = (ObjectValue) value;
			return getValue(objectValue.getEObject(), objectValue.getEObject()
					.eClass());
		}
		return null;
	}

}
