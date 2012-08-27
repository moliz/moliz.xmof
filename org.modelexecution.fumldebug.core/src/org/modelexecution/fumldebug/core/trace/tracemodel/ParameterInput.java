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

import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getParameterInputTokens <em>Parameter Input Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getInputParameterNode <em>Input Parameter Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface ParameterInput extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Parameter Input Tokens</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Input Tokens</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Input Tokens</em>' reference list.
	 * @generated
	 */
	List<ObjectTokenInstance> getParameterInputTokens();

	/**
	 * Returns the value of the '<em><b>Input Parameter Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Parameter Node</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameter Node</em>' attribute.
	 * @see #setInputParameterNode(ActivityParameterNode)
	 * @generated
	 */
	ActivityParameterNode getInputParameterNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getInputParameterNode <em>Input Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Parameter Node</em>' attribute.
	 * @see #getInputParameterNode()
	 * @generated
	 */
	void setInputParameterNode(ActivityParameterNode value);

} // ParameterInput
