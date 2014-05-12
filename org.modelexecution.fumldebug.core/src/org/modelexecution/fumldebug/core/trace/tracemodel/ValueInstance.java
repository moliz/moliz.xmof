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

import fUML.Semantics.Classes.Kernel.Value;

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
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getCreator <em>Creator</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getDestroyer <em>Destroyer</em>}</li>
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
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snapshots</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snapshots</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Snapshots()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance
	 * @model opposite="valueInstance" containment="true"
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
	 * @model
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
	 * Returns the value of the '<em><b>Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creator</em>' reference.
	 * @see #setCreator(ActivityNodeExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Creator()
	 * @model
	 * @generated
	 */
	ActivityNodeExecution getCreator();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getCreator <em>Creator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creator</em>' reference.
	 * @see #getCreator()
	 * @generated
	 */
	void setCreator(ActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Destroyer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destroyer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destroyer</em>' reference.
	 * @see #setDestroyer(ActivityNodeExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getValueInstance_Destroyer()
	 * @model
	 * @generated
	 */
	ActivityNodeExecution getDestroyer();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getDestroyer <em>Destroyer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destroyer</em>' reference.
	 * @see #getDestroyer()
	 * @generated
	 */
	void setDestroyer(ActivityNodeExecution value);

	ValueSnapshot getLatestSnapshot();

} // ValueInstance
