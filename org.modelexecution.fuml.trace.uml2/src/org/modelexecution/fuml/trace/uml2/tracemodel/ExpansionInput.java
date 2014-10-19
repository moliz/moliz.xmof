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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.ExpansionNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expansion Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput#getExpansionInputValues <em>Expansion Input Values</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput#getExpansionNode <em>Expansion Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getExpansionInput()
 * @model
 * @generated
 */
public interface ExpansionInput extends EObject {
	/**
	 * Returns the value of the '<em><b>Expansion Input Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.InputValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expansion Input Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expansion Input Values</em>' containment reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getExpansionInput_ExpansionInputValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<InputValue> getExpansionInputValues();

	/**
	 * Returns the value of the '<em><b>Expansion Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expansion Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expansion Node</em>' reference.
	 * @see #setExpansionNode(ExpansionNode)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getExpansionInput_ExpansionNode()
	 * @model
	 * @generated
	 */
	ExpansionNode getExpansionNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput#getExpansionNode <em>Expansion Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expansion Node</em>' reference.
	 * @see #getExpansionNode()
	 * @generated
	 */
	void setExpansionNode(ExpansionNode value);

} // ExpansionInput
