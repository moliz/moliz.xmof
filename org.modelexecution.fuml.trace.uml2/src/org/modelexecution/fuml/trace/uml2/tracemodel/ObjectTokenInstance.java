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
 * A representation of the model object '<em><b>Object Token Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance#getTransportedValue <em>Transported Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getObjectTokenInstance()
 * @model
 * @generated
 */
public interface ObjectTokenInstance extends TokenInstance {
	/**
	 * Returns the value of the '<em><b>Transported Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transported Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transported Value</em>' reference.
	 * @see #setTransportedValue(ValueInstance)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getObjectTokenInstance_TransportedValue()
	 * @model required="true"
	 * @generated
	 */
	ValueInstance getTransportedValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance#getTransportedValue <em>Transported Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transported Value</em>' reference.
	 * @see #getTransportedValue()
	 * @generated
	 */
	void setTransportedValue(ValueInstance value);

} // ObjectTokenInstance
