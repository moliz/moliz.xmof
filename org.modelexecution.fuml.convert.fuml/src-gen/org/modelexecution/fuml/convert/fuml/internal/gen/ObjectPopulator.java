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

import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IValueConversionResult;
import org.modelexecution.fuml.convert.fuml.internal.IValuePopulator;

@Generated(value="Generated by org.modelexecution.fuml.convert.fuml.gen.ElementPopulatorGenerator.xtend")
public class ObjectPopulator implements IValuePopulator {

	@Override
	public void populate(Object fumlElement,
		Object fumlElement_, 
		IConversionResult result
		, IValueConversionResult valueConversionResult) {
			
		if (!(fumlElement_ instanceof org.modelexecution.fuml.Semantics.Classes.Kernel.Object) ||
			!(fumlElement instanceof fUML.Semantics.Classes.Kernel.Object_)) {
			return;
		}
		
		fUML.Semantics.Classes.Kernel.Object_ fumlNamedElement = (fUML.Semantics.Classes.Kernel.Object_) fumlElement;
		org.modelexecution.fuml.Semantics.Classes.Kernel.Object fumlNamedElement_ = (org.modelexecution.fuml.Semantics.Classes.Kernel.Object) fumlElement_;
		
		
		for (org.modelexecution.fuml.Syntax.Classes.Kernel.Class value : fumlNamedElement_.getTypes()) {
					fumlNamedElement.types.add((fUML.Syntax.Classes.Kernel.Class_) result.getFUMLElement(value));
		}
		
							
	}
	
}