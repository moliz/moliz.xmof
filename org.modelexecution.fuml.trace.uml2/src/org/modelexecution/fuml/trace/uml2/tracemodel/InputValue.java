/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputValue#getInputObjectToken <em>Input Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInputValue()
 * @model
 * @generated
 */
public interface InputValue extends InputOutputValue {
	/**
	 * Returns the value of the '<em><b>Input Object Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Object Token</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Object Token</em>' reference.
	 * @see #setInputObjectToken(ObjectTokenInstance)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInputValue_InputObjectToken()
	 * @model required="true"
	 * @generated
	 */
	ObjectTokenInstance getInputObjectToken();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputValue#getInputObjectToken <em>Input Object Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Object Token</em>' reference.
	 * @see #getInputObjectToken()
	 * @generated
	 */
	void setInputObjectToken(ObjectTokenInstance value);

} // InputValue
