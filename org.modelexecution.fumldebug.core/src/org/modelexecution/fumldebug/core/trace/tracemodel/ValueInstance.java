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

import fUML.Semantics.Classes.Kernel.Value;
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
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getRuntimeValue <em>Runtime Value</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#isDestroyed <em>Destroyed</em>}</li>
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
	 * Returns the value of the '<em><b>Runtime Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Value</em>' attribute.
	 * @see #setRuntimeValue(Value)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_RuntimeValue()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.Value" required="true" transient="true"
	 * @generated
	 */
	Value getRuntimeValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getRuntimeValue <em>Runtime Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Value</em>' attribute.
	 * @see #getRuntimeValue()
	 * @generated
	 */
	void setRuntimeValue(Value value);

	/**
	 * Returns the value of the '<em><b>Snapshots</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot}.
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
	List<ValueSnapshot> getSnapshots();

	/**
	 * Returns the value of the '<em><b>Original</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original</em>' reference.
	 * @see #setOriginal(ValueSnapshot)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Original()
	 * @model required="true"
	 * @generated
	 */
	ValueSnapshot getOriginal();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original</em>' reference.
	 * @see #getOriginal()
	 * @generated
	 */
	void setOriginal(ValueSnapshot value);

	/**
	 * Returns the value of the '<em><b>Destroyed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destroyed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destroyed</em>' attribute.
	 * @see #setDestroyed(boolean)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Destroyed()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isDestroyed();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#isDestroyed <em>Destroyed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destroyed</em>' attribute.
	 * @see #isDestroyed()
	 * @generated
	 */
	void setDestroyed(boolean value);

	ValueSnapshot getLatestSnapshot();

} // ValueInstance
