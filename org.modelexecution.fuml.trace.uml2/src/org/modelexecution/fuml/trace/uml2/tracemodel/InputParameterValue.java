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
 * A representation of the model object '<em><b>Input Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterValue#getParameterInputObjectToken <em>Parameter Input Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInputParameterValue()
 * @model
 * @generated
 */
public interface InputParameterValue extends ParameterValue {
	/**
	 * Returns the value of the '<em><b>Parameter Input Object Token</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Input Object Token</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Input Object Token</em>' containment reference.
	 * @see #setParameterInputObjectToken(ObjectTokenInstance)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInputParameterValue_ParameterInputObjectToken()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ObjectTokenInstance getParameterInputObjectToken();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterValue#getParameterInputObjectToken <em>Parameter Input Object Token</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Input Object Token</em>' containment reference.
	 * @see #getParameterInputObjectToken()
	 * @generated
	 */
	void setParameterInputObjectToken(ObjectTokenInstance value);

} // InputParameterValue
