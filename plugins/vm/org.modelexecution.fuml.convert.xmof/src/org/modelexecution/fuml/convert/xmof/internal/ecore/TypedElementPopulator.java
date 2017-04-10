/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and generator
 */
package org.modelexecution.fuml.convert.xmof.internal.ecore;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ETypedElement;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.impl.ConversionStatusImpl;
import org.modelexecution.fuml.convert.xmof.XMOFConverterPlugin;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Activities.IntermediateActivities.ObjectNode;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.StructuralFeature;
import fUML.Syntax.Classes.Kernel.Type;
import fUML.Syntax.Classes.Kernel.ValueSpecification;

public class TypedElementPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		// TypedElement is in fUML implementation not an interface that is
		// implemented by all that are typed elements. The typed element is
		// instead contained by typed element. Thus, we have to check the type
		// of each most general class that contains a typed element in fUML.
		if (!(element instanceof ETypedElement)
				|| !isFUMLTypedElement(fumlElement)) {
			return;
		}

		ETypedElement eTypedElement = (ETypedElement) element;
		setType(fumlElement, eTypedElement.getEType(), result);
	}

	private boolean isFUMLTypedElement(Element fumlElement) {
		return fumlElement instanceof ValueSpecification
				|| fumlElement instanceof StructuralFeature
				|| fumlElement instanceof Parameter
				|| fumlElement instanceof ObjectNode;
	}

	private void setType(Element fumlElement, EClassifier eType,
			ConversionResultImpl result) {
		if (isComplexDataTypeOrEnumeration(eType)) {
			Element fumlType = result.getFUMLElement(eType);
			if (!(fumlType instanceof Type)) {
				result.getStatus().add(
						new Status(ConversionStatusImpl.ERROR_WHILE_CONVERSION,
								XMOFConverterPlugin.ID, "Type " + fumlType
										+ " is not a valid type "
										+ "and could not be set."));
			} else {
				setType(fumlElement, (Type) fumlType);
			}
		} else {
			// TODO handle simple data types
			// set up a package containing the simple data types in XMOFConverter,
			// add the package imports to each package,
			// and add the primitive types to the result map for EString, etc.
		}
	}

	private void setType(Element fumlElement, Type fumlType) {
		if (fumlElement instanceof ValueSpecification) {
			ValueSpecification valueSpecification = (ValueSpecification) fumlElement;
			valueSpecification.setType(fumlType);
		} else if (fumlElement instanceof StructuralFeature) {
			StructuralFeature structuralFeature = (StructuralFeature) fumlElement;
			structuralFeature.setType(fumlType);
		} else if (fumlElement instanceof Parameter) {
			Parameter parameter = (Parameter) fumlElement;
			parameter.setType(fumlType);
		} else if (fumlElement instanceof ObjectNode) {
			ObjectNode objectNode = (ObjectNode) fumlElement;
			objectNode.setType(fumlType);
		}
	}

	private boolean isComplexDataTypeOrEnumeration(EClassifier eType) {
		return eType instanceof EEnum || eType instanceof EClass;
	}
}
