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

import org.eclipse.emf.ecore.EObject;

import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Snapshot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot#getValue <em>Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getValueSnapshot()
 * @model
 * @generated
 */
public interface ValueSnapshot extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Value)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getValueSnapshot_Value()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Value getValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Value value);

	/**
	 * Returns the value of the '<em><b>Value Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Instance</em>' container reference.
	 * @see #setValueInstance(ValueInstance)
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getValueSnapshot_ValueInstance()
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance#getSnapshots
	 * @model opposite="snapshots" required="true" transient="false"
	 * @generated
	 */
	ValueInstance getValueInstance();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Instance</em>' container reference.
	 * @see #getValueInstance()
	 * @generated
	 */
	void setValueInstance(ValueInstance value);

} // ValueSnapshot
