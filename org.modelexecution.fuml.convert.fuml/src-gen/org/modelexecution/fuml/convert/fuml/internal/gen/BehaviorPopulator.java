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
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.fuml.internal.IElementPopulator;

@Generated(value="Generated by org.modelexecution.fuml.convert.fuml.gen.ElementPopulatorGenerator.xtend")
public class BehaviorPopulator implements IElementPopulator {

	@Override
	public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
		org.modelexecution.fuml.Syntax.Classes.Kernel.Element fumlElement_, 
		ConversionResultImpl result) {
			
		if (!(fumlElement_ instanceof org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior) ||
			!(fumlElement instanceof fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior)) {
			return;
		}
		
		fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior fumlNamedElement = (fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior) fumlElement;
		org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior fumlNamedElement_ = (org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior) fumlElement_;
		
		fumlNamedElement.specification = (fUML.Syntax.Classes.Kernel.BehavioralFeature) result
							.getFUMLElement(fumlNamedElement_.getSpecification());
		fumlNamedElement.isReentrant = fumlNamedElement_.isReentrant();
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter value : fumlNamedElement_.getOwnedParameter()) {
					fumlNamedElement.ownedParameter.add((fUML.Syntax.Classes.Kernel.Parameter) result.getFUMLElement(value));
		}
		
		fumlNamedElement.context = (fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier) result
							.getFUMLElement(fumlNamedElement_.getContext());
							
	}
	
}
