/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.ActivityParameterNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Parameter Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput#getUserInputTokens <em>User Input Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput#getInputParameterNode <em>Input Parameter Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getUserParameterInput()
 * @model
 * @generated
 */
public interface UserParameterInput extends EObject {
	/**
	 * Returns the value of the '<em><b>User Input Tokens</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ObjectTokenInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Input Tokens</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Input Tokens</em>' containment reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getUserParameterInput_UserInputTokens()
	 * @model containment="true"
	 * @generated
	 */
	EList<ObjectTokenInstance> getUserInputTokens();

	/**
	 * Returns the value of the '<em><b>Input Parameter Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parameter Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameter Node</em>' reference.
	 * @see #setInputParameterNode(ActivityParameterNode)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getUserParameterInput_InputParameterNode()
	 * @model required="true"
	 * @generated
	 */
	ActivityParameterNode getInputParameterNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput#getInputParameterNode <em>Input Parameter Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Parameter Node</em>' reference.
	 * @see #getInputParameterNode()
	 * @generated
	 */
	void setInputParameterNode(ActivityParameterNode value);

} // UserParameterInput
