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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fUML.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin <em>Input Pin</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputValues <em>Input Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getInput()
 * @model
 * @generated
 */
public interface Input extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Input Pin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Pin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Pin</em>' attribute.
	 * @see #setInputPin(InputPin)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getInput_InputPin()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.InputPin" transient="true"
	 * @generated
	 */
	InputPin getInputPin();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin <em>Input Pin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Pin</em>' attribute.
	 * @see #getInputPin()
	 * @generated
	 */
	void setInputPin(InputPin value);

	/**
	 * Returns the value of the '<em><b>Input Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.InputValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Values</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getInput_InputValues()
	 * @model containment="true"
	 * @generated
	 */
	List<InputValue> getInputValues();

} // Input
