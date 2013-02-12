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

import org.eclipse.uml2.uml.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input#getConsumedValue <em>Consumed Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input#getInputPin <em>Input Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getInput()
 * @model
 * @generated
 */
public interface Input extends EObject {
	/**
	 * Returns the value of the '<em><b>Tokens</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tokens</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tokens</em>' reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getInput_Tokens()
	 * @model
	 * @generated
	 */
	EList<TokenInstance> getTokens();

	/**
	 * Returns the value of the '<em><b>Consumed Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consumed Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consumed Value</em>' reference.
	 * @see #setConsumedValue(ValueInstanceSnapshot)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getInput_ConsumedValue()
	 * @model
	 * @generated
	 */
	ValueInstanceSnapshot getConsumedValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input#getConsumedValue <em>Consumed Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Consumed Value</em>' reference.
	 * @see #getConsumedValue()
	 * @generated
	 */
	void setConsumedValue(ValueInstanceSnapshot value);

	/**
	 * Returns the value of the '<em><b>Input Pin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Pin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Pin</em>' reference.
	 * @see #setInputPin(InputPin)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getInput_InputPin()
	 * @model
	 * @generated
	 */
	InputPin getInputPin();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input#getInputPin <em>Input Pin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Pin</em>' reference.
	 * @see #getInputPin()
	 * @generated
	 */
	void setInputPin(InputPin value);

} // Input
