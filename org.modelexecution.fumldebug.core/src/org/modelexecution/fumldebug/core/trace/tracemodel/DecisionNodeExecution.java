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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decision Node Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution#getDecisionInputValue <em>Decision Input Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getDecisionNodeExecution()
 * @model
 * @generated
 */
public interface DecisionNodeExecution extends ControlNodeExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Decision Input Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision Input Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decision Input Value</em>' containment reference.
	 * @see #setDecisionInputValue(InputValue)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getDecisionNodeExecution_DecisionInputValue()
	 * @model containment="true"
	 * @generated
	 */
	InputValue getDecisionInputValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution#getDecisionInputValue <em>Decision Input Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decision Input Value</em>' containment reference.
	 * @see #getDecisionInputValue()
	 * @generated
	 */
	void setDecisionInputValue(InputValue value);

} // DecisionNodeExecution
