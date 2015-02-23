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
package org.modelexecution.fuml.values.convert.uml2.internal;

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
		case "fUML.Semantics.Classes.Kernel.FeatureValue":
			return KernelFactory.eINSTANCE.createFeatureValue();
		case "fUML.Semantics.Classes.Kernel.UnlimitedNaturalValue":
			return KernelFactory.eINSTANCE.createUnlimitedNaturalValue();
		case "fUML.Semantics.Classes.Kernel.StringValue":
			return KernelFactory.eINSTANCE.createStringValue();
		case "fUML.Semantics.Classes.Kernel.Reference":
			return KernelFactory.eINSTANCE.createReference();
		case "fUML.Semantics.Classes.Kernel.Object_":
			return KernelFactory.eINSTANCE.createObject();
		case "fUML.Semantics.Classes.Kernel.Link":
			return KernelFactory.eINSTANCE.createLink();
		case "fUML.Semantics.Classes.Kernel.IntegerValue":
			return KernelFactory.eINSTANCE.createIntegerValue();
		case "fUML.Semantics.Classes.Kernel.EnumerationValue":
			return KernelFactory.eINSTANCE.createEnumerationValue();
		case "fUML.Semantics.Classes.Kernel.DataValue":
			return KernelFactory.eINSTANCE.createDataValue();
		case "fUML.Semantics.Classes.Kernel.BooleanValue":
			return KernelFactory.eINSTANCE.createBooleanValue();
		}
		return null;
	}

}
