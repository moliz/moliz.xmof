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
public class ReceptionPopulator implements IElementPopulator {

	@Override
	public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
		org.modelexecution.fuml.Syntax.Classes.Kernel.Element fumlElement_, 
		ConversionResultImpl result) {
			
		if (!(fumlElement_ instanceof org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Reception) ||
			!(fumlElement instanceof fUML.Syntax.CommonBehaviors.Communications.Reception)) {
			return;
		}
		
		fUML.Syntax.CommonBehaviors.Communications.Reception fumlNamedElement = (fUML.Syntax.CommonBehaviors.Communications.Reception) fumlElement;
		org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Reception fumlNamedElement_ = (org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Reception) fumlElement_;
		
		fumlNamedElement.signal = (fUML.Syntax.CommonBehaviors.Communications.Signal) result
							.getFUMLElement(fumlNamedElement_.getSignal());
							
	}
	
}
