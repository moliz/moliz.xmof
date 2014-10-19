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
package org.modelexecution.fuml.trace.convert.uml2.internal;

import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelFactory;

/**
 * Factory for {@link org.modelexecution.fuml.trace.uml2.tracemodel
 * elements}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2TraceElementFactory {

	public EObject create(EObject element) {
		String className = element.eClass().getName();
		switch (className) {
		case "ActionExecution":
			return TracemodelFactory.eINSTANCE.createActionExecution();
		case "ActivityExecution":
			return TracemodelFactory.eINSTANCE.createActivityExecution();
		case "CallActionExecution":
			return TracemodelFactory.eINSTANCE.createCallActionExecution();
		case "ControlNodeExecution":
			return TracemodelFactory.eINSTANCE.createControlNodeExecution();
		case "ControlTokenInstance":
			return TracemodelFactory.eINSTANCE.createControlTokenInstance();
		case "DecisionNodeExecution":
			return TracemodelFactory.eINSTANCE.createDecisionNodeExecution();
		case "ExpansionInput":
			return TracemodelFactory.eINSTANCE.createExpansionInput();
		case "ExpansionRegionExecution":
			return TracemodelFactory.eINSTANCE.createExpansionRegionExecution();
		case "InitialNodeExecution":
			return TracemodelFactory.eINSTANCE.createInitialNodeExecution();
		case "Input":
			return TracemodelFactory.eINSTANCE.createInput();
		case "InputParameterSetting":
			return TracemodelFactory.eINSTANCE.createInputParameterSetting();
		case "InputParameterValue":
			return TracemodelFactory.eINSTANCE.createInputParameterValue();
		case "InputValue":
			return TracemodelFactory.eINSTANCE.createInputValue();
		case "ObjectTokenInstance":
			return TracemodelFactory.eINSTANCE.createObjectTokenInstance();
		case "Output":
			return TracemodelFactory.eINSTANCE.createOutput();
		case "OutputParameterSetting":
			return TracemodelFactory.eINSTANCE.createOutputParameterSetting();
		case "OutputParameterValue":
			return TracemodelFactory.eINSTANCE.createOutputParameterValue();
		case "OutputValue":
			return TracemodelFactory.eINSTANCE.createOutputValue();
		case "StructuredActivityNodeExecution":
			return TracemodelFactory.eINSTANCE
					.createStructuredActivityNodeExecution();
		case "Trace":
			return TracemodelFactory.eINSTANCE.createTrace();
		case "ValueInstance":
			return TracemodelFactory.eINSTANCE.createValueInstance();
		case "ValueSnapshot":
			return TracemodelFactory.eINSTANCE.createValueSnapshot();
		}
		return null;
	}

}
