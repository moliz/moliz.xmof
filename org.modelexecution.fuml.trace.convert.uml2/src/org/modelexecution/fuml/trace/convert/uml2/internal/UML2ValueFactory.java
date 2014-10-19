/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API
 * Tanja Mayerhofer - implementation
 */
package org.modelexecution.fuml.trace.convert.uml2.internal;

import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.KernelFactory;

/**
 * Factory for {@link org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel elements}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2ValueFactory {

	public EObject create(Object element) {
		String className = element.getClass().getName();
		switch (className) {
		case "FeatureValue":
			return KernelFactory.eINSTANCE.createFeatureValue();
		case "UnlimitedNaturalValue":
			return KernelFactory.eINSTANCE.createUnlimitedNaturalValue();
		case "StringValue":
			return KernelFactory.eINSTANCE.createStringValue();
		case "Reference":
			return KernelFactory.eINSTANCE.createReference();
		case "Object_":
			return KernelFactory.eINSTANCE.createObject();
		case "Link":
			return KernelFactory.eINSTANCE.createLink();
		case "IntegerValue":
			return KernelFactory.eINSTANCE.createIntegerValue();
		case "EnumerationValue":
			return KernelFactory.eINSTANCE.createEnumerationValue();
		case "DataValue":
			return KernelFactory.eINSTANCE.createDataValue();
		case "BooleanValue":
			return KernelFactory.eINSTANCE.createBooleanValue();
		}
		return null;
	}

}
