/*
* Copyright (c) 2014 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API
* Tanja Mayerhofer - implementation
*/
package org.modelexecution.fuml.trace.convert.uml2.internal.populator;

import org.eclipse.uml2.uml.StructuralFeature;
import org.modelexecution.fuml.trace.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at)
 *
 */
public class FeatureValuePopulator implements IUML2TraceElementPopulator {

	/* (non-Javadoc)
	 * @see org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator#populate(java.lang.Object, java.lang.Object, org.modelexecution.fuml.trace.uml2.convert.IConversionResult)
	 */
	@Override
	public void populate(Object umlTraceElement, Object fumlTraceElement,
			IConversionResult result, org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {

		if(!(umlTraceElement instanceof FeatureValue) || !(fumlTraceElement instanceof fUML.Semantics.Classes.Kernel.FeatureValue)) {
			return;
		}

		FeatureValue umlFeatureValue = (FeatureValue) umlTraceElement;
		fUML.Semantics.Classes.Kernel.FeatureValue fumlFeatureValue = (fUML.Semantics.Classes.Kernel.FeatureValue) fumlTraceElement;
		
		umlFeatureValue.setFeature((StructuralFeature)modelConversionResult.getInputObject(fumlFeatureValue.feature));
		
		umlFeatureValue.setPosition(fumlFeatureValue.position);
		
		for(fUML.Semantics.Classes.Kernel.Value value : fumlFeatureValue.values) {
			umlFeatureValue.getValues().add((Value)result.getOutputUMLTraceElement(value));
		}
				
	}

}
