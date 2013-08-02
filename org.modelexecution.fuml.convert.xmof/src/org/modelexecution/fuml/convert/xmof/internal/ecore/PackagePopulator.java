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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.NamedElement;
import fUML.Syntax.Classes.Kernel.Package;
import fUML.Syntax.Classes.Kernel.PackageableElement;

public class PackagePopulator implements IElementPopulator {

	private IConversionResult result;
	
	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof EPackage) || !(fumlElement instanceof Package)) {
			return;
		}

		EPackage ePackage = (EPackage) element;
		Package umlPackage = (Package) fumlElement;

		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			umlPackage.addPackagedElement((PackageableElement) result
					.getFUMLElement(eClassifier));
		}

		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			umlPackage.addPackagedElement((PackageableElement) result
					.getFUMLElement(eSubPackage));			
		}
		
		this.result = result;				
		EPackage rootPackage = getRootPackage(ePackage);
		updatedQualifiedName(rootPackage);
	}
	
	private EPackage getRootPackage(EPackage ePackage) {
		EPackage eSuperPackage = ePackage.getESuperPackage();
		if(eSuperPackage == null) {
			return ePackage;
		}
		return getRootPackage(eSuperPackage);
	}
	
	private void updatedQualifiedName(EPackage ePackage) {		
		NamedElement fUMLPackage = (NamedElement)result.getFUMLElement(ePackage);
		fUMLPackage.setName(fUMLPackage.name);		
		for(EClassifier eClassifier : ePackage.getEClassifiers()) {
			Element fUMLElement = result.getFUMLElement(eClassifier);
			if(fUMLElement instanceof NamedElement) {
				NamedElement fUMLNamedElement = (NamedElement) fUMLElement;
				fUMLNamedElement.setName(fUMLNamedElement.name);
			}
		}
		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			updatedQualifiedName(eSubPackage);
		}
	}
}
