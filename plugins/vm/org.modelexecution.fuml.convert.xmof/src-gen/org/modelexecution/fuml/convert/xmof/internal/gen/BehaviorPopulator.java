/*
* Copyright (c) 2012 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API and generator
*/
package org.modelexecution.fuml.convert.xmof.internal.gen;
    	
import javax.annotation.Generated;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

@Generated(value="Generated by org.modelexecution.fuml.convert.xmof.gen.ElementPopulatorGenerator.xtend")
public class BehaviorPopulator implements IElementPopulator {

	@Override
	public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
		org.eclipse.emf.ecore.EModelElement element, ConversionResultImpl result) {
			
		if (!(element instanceof org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior) ||
			!(fumlElement instanceof fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior)) {
			return;
		}
		
		fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior fumlNamedElement = (fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior) fumlElement;
		org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior xmofElement = (org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior) element;
		
		fumlNamedElement.specification = (fUML.Syntax.Classes.Kernel.Operation) result
							.getFUMLElement(xmofElement.getSpecification());
		fumlNamedElement.isReentrant = xmofElement.isReentrant();
		
		for (org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter value : xmofElement.getOwnedParameter()) {
					fumlNamedElement.ownedParameter.add((fUML.Syntax.Classes.Kernel.Parameter) result.getFUMLElement(value));
		}
		
		fumlNamedElement.context = (fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier) result
							.getFUMLElement(xmofElement.getContext());
		
	}
	
}
