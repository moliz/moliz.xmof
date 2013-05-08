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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue#getValueSnapshot <em>Value Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getParameterValue()
 * @model abstract="true"
 * @generated
 */
public interface ParameterValue extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Snapshot</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Snapshot</em>' reference.
	 * @see #setValueSnapshot(ValueSnapshot)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getParameterValue_ValueSnapshot()
	 * @model required="true"
	 * @generated
	 */
	ValueSnapshot getValueSnapshot();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue#getValueSnapshot <em>Value Snapshot</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Snapshot</em>' reference.
	 * @see #getValueSnapshot()
	 * @generated
	 */
	void setValueSnapshot(ValueSnapshot value);

} // ParameterValue
