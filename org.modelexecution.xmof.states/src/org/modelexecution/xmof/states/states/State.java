/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.states.states;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.states.states.State#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.State#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.State#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.states.states.StatesPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.states.states.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference.
	 * @see #setOutgoing(Transition)
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getState_Outgoing()
	 * @see org.modelexecution.xmof.states.states.Transition#getSource
	 * @model opposite="source"
	 * @generated
	 */
	Transition getOutgoing();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.states.states.State#getOutgoing <em>Outgoing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outgoing</em>' reference.
	 * @see #getOutgoing()
	 * @generated
	 */
	void setOutgoing(Transition value);

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.states.states.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference.
	 * @see #setIncoming(Transition)
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getState_Incoming()
	 * @see org.modelexecution.xmof.states.states.Transition#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	Transition getIncoming();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.states.states.State#getIncoming <em>Incoming</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incoming</em>' reference.
	 * @see #getIncoming()
	 * @generated
	 */
	void setIncoming(Transition value);

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference list.
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getState_Objects()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getObjects();

	EObject getObjectState(EObject eObject);
	
	State getPredecessorState();

	State getSuccessorState();
	
} // State
