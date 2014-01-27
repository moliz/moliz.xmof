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
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.states.states.StateSystem#getStates <em>States</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.StateSystem#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.StateSystem#getTrace <em>Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.states.states.StatesPackage#getStateSystem()
 * @model
 * @generated
 */
public interface StateSystem extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.states.states.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getStateSystem_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<State> getStates();

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.states.states.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getStateSystem_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transition> getTransitions();

	/**
	 * Returns the value of the '<em><b>Trace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace</em>' reference.
	 * @see #setTrace(Trace)
	 * @see org.modelexecution.xmof.states.states.StatesPackage#getStateSystem_Trace()
	 * @model transient="true"
	 * @generated
	 */
	Trace getTrace();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.states.states.StateSystem#getTrace <em>Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace</em>' reference.
	 * @see #getTrace()
	 * @generated
	 */
	void setTrace(Trace value);

	EObject getObjectState(State state, EObject eObject);
	
	void addObjectsState(State state, EObject eObject, EObject eObjectState);
	
	EObject getOriginalObjectState(State state, EObject eObjectState);

	State getStateAfterActivityExecution(ActivityExecution activityExecution);
	
	State getLastState();
	
	State getStateCausedByActivityNodeExecution(ActivityNodeExecution activityNodeExecution);
	
} // StateSystem
