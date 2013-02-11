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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValueID <em>Value ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance()
 * @model
 * @generated
 */
public interface ValueInstance extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Snapshots</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snapshots</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snapshots</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Snapshots()
	 * @model containment="true" required="true"
	 * @generated
	 */
	List<ValueInstanceSnapshot> getSnapshots();

	/**
	 * Returns the value of the '<em><b>Original</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original</em>' reference.
	 * @see #setOriginal(ValueInstanceSnapshot)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Original()
	 * @model required="true"
	 * @generated
	 */
	ValueInstanceSnapshot getOriginal();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original</em>' reference.
	 * @see #getOriginal()
	 * @generated
	 */
	void setOriginal(ValueInstanceSnapshot value);

	/**
	 * Returns the value of the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value ID</em>' attribute.
	 * @see #setValueID(int)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_ValueID()
	 * @model required="true"
	 * @generated
	 */
	int getValueID();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValueID <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value ID</em>' attribute.
	 * @see #getValueID()
	 * @generated
	 */
	void setValueID(int value);

} // ValueInstance
