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

import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fuml.trace.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at)
 *
 */
public class ActivityNodeExecutionPopulator implements IUML2TraceElementPopulator {

	/* (non-Javadoc)
	 * @see org.modelexecution.fuml.trace.convert.uml2.internal.IUML2TraceElementPopulator#populate(java.lang.Object, java.lang.Object, org.modelexecution.fuml.trace.uml2.convert.IConversionResult)
	 */
	@Override
	public void populate(Object umlTraceElement, Object fumlTraceElement,
			IConversionResult result, org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {

		if(!(umlTraceElement instanceof ActivityNodeExecution) || !(fumlTraceElement instanceof org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution)) {
			return;
		}

		ActivityNodeExecution umlActivityNodeExecution = (ActivityNodeExecution) umlTraceElement;
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution fumlActivityNodeExecution = (org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution) fumlTraceElement;
		
		umlActivityNodeExecution.setActivityExecution((ActivityExecution)result.getOutputUMLTraceElement(fumlActivityNodeExecution.getActivityExecution()));
		
		umlActivityNodeExecution.setChronologicalPredecessor((ActivityNodeExecution)result.getOutputUMLTraceElement(fumlActivityNodeExecution.getChronologicalPredecessor()));
		
		umlActivityNodeExecution.setChronologicalSuccessor((ActivityNodeExecution)result.getOutputUMLTraceElement(fumlActivityNodeExecution.getChronologicalSuccessor()));
		
		umlActivityNodeExecution.setExecuted(fumlActivityNodeExecution.isExecuted());
		
		umlActivityNodeExecution.setUnderExecution(fumlActivityNodeExecution.isUnderExecution());
		
		umlActivityNodeExecution.setNode((ActivityNode)modelConversionResult.getInputObject(fumlActivityNodeExecution.getNode()));
		
		for(org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution logicalPredecessor : fumlActivityNodeExecution.getLogicalPredecessor()) {
			umlActivityNodeExecution.getLogicalPredecessor().add((ActivityNodeExecution)result.getOutputUMLTraceElement(logicalPredecessor));
		}
		
		for(org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution logicalSuccessor : fumlActivityNodeExecution.getLogicalSuccessor()) {
			umlActivityNodeExecution.getLogicalPredecessor().add((ActivityNodeExecution)result.getOutputUMLTraceElement(logicalSuccessor));
		}		

	}

}
