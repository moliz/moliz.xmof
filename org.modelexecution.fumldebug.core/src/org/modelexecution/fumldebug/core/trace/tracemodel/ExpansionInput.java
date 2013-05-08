/**
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expansion Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionInputValues <em>Expansion Input Values</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionNode <em>Expansion Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getExpansionInput()
 * @model
 * @generated
 */
public interface ExpansionInput extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Expansion Input Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.InputValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expansion Input Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expansion Input Values</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getExpansionInput_ExpansionInputValues()
	 * @model containment="true"
	 * @generated
	 */
	List<InputValue> getExpansionInputValues();

	/**
	 * Returns the value of the '<em><b>Expansion Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expansion Node</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expansion Node</em>' attribute.
	 * @see #setExpansionNode(ExpansionNode)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getExpansionInput_ExpansionNode()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionNode"
	 * @generated
	 */
	ExpansionNode getExpansionNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionNode <em>Expansion Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expansion Node</em>' attribute.
	 * @see #getExpansionNode()
	 * @generated
	 */
	void setExpansionNode(ExpansionNode value);

} // ExpansionInput
