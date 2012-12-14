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

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ETypedElement;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Actions.BasicActions.Pin;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class MultiplicityElementPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		// MultiplicityElement is in fUML implementation not an interface that
		// is implemented by all that are typed elements. The typed element is
		// instead contained by typed element. Thus, we have to check the
		// multiplicities of each most general class that contains a
		// multiplicity element in fUML.
		if (!(element instanceof ETypedElement)
				|| !isFUMLMultiplicityElement(fumlElement)) {
			return;
		}

		ETypedElement eTypedElement = (ETypedElement) element;
		setMultiplicity(fumlElement, eTypedElement);
	}

	private boolean isFUMLMultiplicityElement(Element fumlElement) {
		return fumlElement instanceof Parameter
				|| fumlElement instanceof StructuralFeature
				|| fumlElement instanceof Pin;
	}

	private void setMultiplicity(Element fumlElement,
			ETypedElement eTypedElement) {
		if (fumlElement instanceof StructuralFeature) {
			StructuralFeature structuralFeature = (StructuralFeature) fumlElement;
			structuralFeature.setLower(eTypedElement.getLowerBound());
			structuralFeature.setUpper(eTypedElement.getUpperBound());
		} else if (fumlElement instanceof Parameter) {
			Parameter parameter = (Parameter) fumlElement;
			parameter.setLower(eTypedElement.getLowerBound());
			parameter.setUpper(eTypedElement.getUpperBound());
		} else if (fumlElement instanceof Pin) {
			Pin pin = (Pin) fumlElement;
			pin.setLower(eTypedElement.getLowerBound());
			pin.setUpper(eTypedElement.getUpperBound());
		}
	}
}
