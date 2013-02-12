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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Action Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution#getCallee <em>Callee</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getCallActionExecution()
 * @model
 * @generated
 */
public interface CallActionExecution extends ActionExecution {
	/**
	 * Returns the value of the '<em><b>Callee</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callee</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callee</em>' reference.
	 * @see #setCallee(ActivityExecution)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getCallActionExecution_Callee()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getCaller
	 * @model opposite="caller"
	 * @generated
	 */
	ActivityExecution getCallee();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution#getCallee <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callee</em>' reference.
	 * @see #getCallee()
	 * @generated
	 */
	void setCallee(ActivityExecution value);

} // CallActionExecution
