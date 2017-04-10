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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class StructuralFeaturePopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof EStructuralFeature)
				|| !(fumlElement instanceof StructuralFeature)) {
			return;
		}

		EStructuralFeature eStructuralFeature = (EStructuralFeature) element;
		StructuralFeature umlStructuralFeature = (StructuralFeature) fumlElement;

		umlStructuralFeature.setIsUnique(eStructuralFeature.isUnique());
		umlStructuralFeature.setIsOrdered(eStructuralFeature.isOrdered());
		umlStructuralFeature.setIsReadOnly(!eStructuralFeature.isChangeable());
	}
}
