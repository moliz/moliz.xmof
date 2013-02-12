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

import org.eclipse.uml2.uml.OutputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output#getOutputPin <em>Output Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getOutput()
 * @model
 * @generated
 */
public interface Output extends EObject {
	/**
	 * Returns the value of the '<em><b>Tokens</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tokens</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tokens</em>' containment reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getOutput_Tokens()
	 * @model containment="true"
	 * @generated
	 */
	EList<TokenInstance> getTokens();

	/**
	 * Returns the value of the '<em><b>Output Pin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Pin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Pin</em>' reference.
	 * @see #setOutputPin(OutputPin)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getOutput_OutputPin()
	 * @model
	 * @generated
	 */
	OutputPin getOutputPin();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output#getOutputPin <em>Output Pin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Pin</em>' reference.
	 * @see #getOutputPin()
	 * @generated
	 */
	void setOutputPin(OutputPin value);

} // Output
