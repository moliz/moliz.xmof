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
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.NamedElement;

public class NamedElementPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof ENamedElement) || !(fumlElement instanceof NamedElement)) {
			return;
		}

		ENamedElement eNamedElement = (ENamedElement) element;
		NamedElement fumlNamedElement = (NamedElement) fumlElement;
		
		fumlNamedElement.setName(eNamedElement.getName());
		fumlNamedElement.qualifiedName = calculateQualifiedName(eNamedElement);
	}

	private String calculateQualifiedName(ENamedElement eNamedElement) {
		String qualifiedName = eNamedElement.getName();		
		EObject eContainer = eNamedElement.eContainer();
		if(eContainer instanceof ENamedElement) {
			qualifiedName = calculateQualifiedName((ENamedElement)eContainer) + "." + qualifiedName;
		}		
		return qualifiedName;
	}
	
}
