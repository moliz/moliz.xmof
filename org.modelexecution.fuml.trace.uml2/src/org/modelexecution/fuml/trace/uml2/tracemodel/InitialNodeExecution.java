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
 * A representation of the model object '<em><b>Initial Node Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution#getOutgoingControl <em>Outgoing Control</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInitialNodeExecution()
 * @model
 * @generated
 */
public interface InitialNodeExecution extends ControlNodeExecution {
	/**
	 * Returns the value of the '<em><b>Outgoing Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Control</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Control</em>' containment reference.
	 * @see #setOutgoingControl(ControlTokenInstance)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getInitialNodeExecution_OutgoingControl()
	 * @model containment="true"
	 * @generated
	 */
	ControlTokenInstance getOutgoingControl();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution#getOutgoingControl <em>Outgoing Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outgoing Control</em>' containment reference.
	 * @see #getOutgoingControl()
	 * @generated
	 */
	void setOutgoingControl(ControlTokenInstance value);

} // InitialNodeExecution
