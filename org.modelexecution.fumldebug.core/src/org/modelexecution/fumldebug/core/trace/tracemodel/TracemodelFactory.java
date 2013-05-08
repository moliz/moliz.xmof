/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel;

import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage
 * @generated
 */
public interface TracemodelFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracemodelFactory eINSTANCE = org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trace</em>'.
	 * @generated
	 */
	Trace createTrace();

	/**
	 * Returns a new object of class '<em>Activity Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Execution</em>'.
	 * @generated
	 */
	ActivityExecution createActivityExecution();

	/**
	 * Returns a new object of class '<em>Call Action Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Call Action Execution</em>'.
	 * @generated
	 */
	CallActionExecution createCallActionExecution();

	/**
	 * Returns a new object of class '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input</em>'.
	 * @generated
	 */
	Input createInput();

	/**
	 * Returns a new object of class '<em>Output</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output</em>'.
	 * @generated
	 */
	Output createOutput();

	/**
	 * Returns a new object of class '<em>Object Token Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object Token Instance</em>'.
	 * @generated
	 */
	ObjectTokenInstance createObjectTokenInstance();

	/**
	 * Returns a new object of class '<em>Control Token Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Token Instance</em>'.
	 * @generated
	 */
	ControlTokenInstance createControlTokenInstance();

	/**
	 * Returns a new object of class '<em>Action Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Execution</em>'.
	 * @generated
	 */
	ActionExecution createActionExecution();

	/**
	 * Returns a new object of class '<em>Control Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Node Execution</em>'.
	 * @generated
	 */
	ControlNodeExecution createControlNodeExecution();

	/**
	 * Returns a new object of class '<em>Value Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Instance</em>'.
	 * @generated
	 */
	ValueInstance createValueInstance();

	/**
	 * Returns a new object of class '<em>Value Snapshot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Snapshot</em>'.
	 * @generated
	 */
	ValueSnapshot createValueSnapshot();

	/**
	 * Returns a new object of class '<em>Decision Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Node Execution</em>'.
	 * @generated
	 */
	DecisionNodeExecution createDecisionNodeExecution();

	/**
	 * Returns a new object of class '<em>Structured Activity Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structured Activity Node Execution</em>'.
	 * @generated
	 */
	StructuredActivityNodeExecution createStructuredActivityNodeExecution();

	/**
	 * Returns a new object of class '<em>Input Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Value</em>'.
	 * @generated
	 */
	InputValue createInputValue();

	/**
	 * Returns a new object of class '<em>Output Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output Value</em>'.
	 * @generated
	 */
	OutputValue createOutputValue();

	/**
	 * Returns a new object of class '<em>Input Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Parameter Value</em>'.
	 * @generated
	 */
	InputParameterValue createInputParameterValue();

	/**
	 * Returns a new object of class '<em>Input Parameter Setting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Parameter Setting</em>'.
	 * @generated
	 */
	InputParameterSetting createInputParameterSetting();

	/**
	 * Returns a new object of class '<em>Output Parameter Setting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output Parameter Setting</em>'.
	 * @generated
	 */
	OutputParameterSetting createOutputParameterSetting();

	/**
	 * Returns a new object of class '<em>Output Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output Parameter Value</em>'.
	 * @generated
	 */
	OutputParameterValue createOutputParameterValue();

	/**
	 * Returns a new object of class '<em>Initial Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initial Node Execution</em>'.
	 * @generated
	 */
	InitialNodeExecution createInitialNodeExecution();

	/**
	 * Returns a new object of class '<em>Expansion Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expansion Input</em>'.
	 * @generated
	 */
	ExpansionInput createExpansionInput();

	/**
	 * Returns a new object of class '<em>Expansion Region Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expansion Region Execution</em>'.
	 * @generated
	 */
	ExpansionRegionExecution createExpansionRegionExecution();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracemodelPackage getTracemodelPackage();

} //TracemodelFactory
