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

import fUML.Semantics.Classes.Kernel.Value;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Snapshot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValue <em>Value</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueSnapshot()
 * @model
 * @generated
 */
public interface ValueSnapshot extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Value)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueSnapshot_Value()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.Value" required="true" transient="true"
	 * @generated
	 */
	Value getValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Value value);

	/**
	 * Returns the value of the '<em><b>Value Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Instance</em>' container reference.
	 * @see #setValueInstance(ValueInstance)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueSnapshot_ValueInstance()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots
	 * @model opposite="snapshots" required="true" transient="false"
	 * @generated
	 */
	ValueInstance getValueInstance();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Instance</em>' container reference.
	 * @see #getValueInstance()
	 * @generated
	 */
	void setValueInstance(ValueInstance value);

	Value getRuntimeValue();

} // ValueSnapshot
