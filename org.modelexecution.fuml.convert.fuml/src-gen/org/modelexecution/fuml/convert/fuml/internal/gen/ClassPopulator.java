/*
* Copyright (c) 2013 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API and generator
* Tanja Mayerhofer - generator
*/
package org.modelexecution.fuml.convert.fuml.internal.gen;
    	
import javax.annotation.Generated;

import org.modelexecution.fuml.convert.fuml.internal.IElementPopulator;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;

@Generated(value="Generated by org.modelexecution.fuml.convert.fuml.gen.ElementPopulatorGenerator.xtend")
public class ClassPopulator implements IElementPopulator {

	@Override
	public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
		org.modelexecution.fuml.Syntax.Classes.Kernel.Element fumlElement_, 
		ConversionResultImpl result
		) {
			
		if (!(fumlElement_ instanceof org.modelexecution.fuml.Syntax.Classes.Kernel.Class) ||
			!(fumlElement instanceof fUML.Syntax.Classes.Kernel.Class_)) {
			return;
		}
		
		fUML.Syntax.Classes.Kernel.Class_ fumlNamedElement = (fUML.Syntax.Classes.Kernel.Class_) fumlElement;
		org.modelexecution.fuml.Syntax.Classes.Kernel.Class fumlNamedElement_ = (org.modelexecution.fuml.Syntax.Classes.Kernel.Class) fumlElement_;
		
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Property value : fumlNamedElement_.getOwnedAttribute()) {
					fumlNamedElement.ownedAttribute.add((fUML.Syntax.Classes.Kernel.Property) result.getFUMLElement(value));
		}
		
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Operation value : fumlNamedElement_.getOwnedOperation()) {
					fumlNamedElement.ownedOperation.add((fUML.Syntax.Classes.Kernel.Operation) result.getFUMLElement(value));
		}
		
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Class value : fumlNamedElement_.getSuperClass()) {
					fumlNamedElement.superClass.add((fUML.Syntax.Classes.Kernel.Class_) result.getFUMLElement(value));
		}
		
		fumlNamedElement.isActive = fumlNamedElement_.isActive();
		
		for (org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Reception value : fumlNamedElement_.getOwnedReception()) {
					fumlNamedElement.ownedReception.add((fUML.Syntax.CommonBehaviors.Communications.Reception) result.getFUMLElement(value));
		}
		
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier value : fumlNamedElement_.getNestedClassifier()) {
					fumlNamedElement.nestedClassifier.add((fUML.Syntax.Classes.Kernel.Classifier) result.getFUMLElement(value));
		}
		
							
	}
	
}