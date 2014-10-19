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

import org.modelexecution.fuml.trace.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActionExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.Input;
import org.modelexecution.fuml.trace.uml2.tracemodel.Output;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at)
 *
 */
public class ActionPopulator implements IUML2TraceElementPopulator {

	/* (non-Javadoc)
	 * @see org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator#populate(java.lang.Object, java.lang.Object, org.modelexecution.fuml.trace.uml2.convert.IConversionResult)
	 */
	@Override
	public void populate(Object umlTraceElement, Object fumlTraceElement,
			IConversionResult result, org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {

		if(!(umlTraceElement instanceof ActionExecution) || !(fumlTraceElement instanceof org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution)) {
			return;
		}

		ActionExecution umlActionExecution = (ActionExecution) umlTraceElement;
		org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution fumlActionExecution = (org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution) fumlTraceElement;
		
		for(org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance incomingControl : fumlActionExecution.getIncomingControl()) {
			umlActionExecution.getIncomingControl().add((ControlTokenInstance)result.getOutputUMLTraceElement(incomingControl));
		}

		for(org.modelexecution.fumldebug.core.trace.tracemodel.Input input : fumlActionExecution.getInputs()) {
			umlActionExecution.getInputs().add((Input)result.getOutputUMLTraceElement(input));
		}
		
		for(org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance outgoingControl : fumlActionExecution.getOutgoingControl()) {
			umlActionExecution.getOutgoingControl().add((ControlTokenInstance)result.getOutputUMLTraceElement(outgoingControl));
		}
		
		for(org.modelexecution.fumldebug.core.trace.tracemodel.Output output : fumlActionExecution.getOutputs()) {
			umlActionExecution.getOutputs().add((Output)result.getOutputUMLTraceElement(output));
		}
		
	}

}
