/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.states.states.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.states.states.Transition;
import org.modelexecution.xmof.states.states.impl.internal.ObjectStates;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.states.states.impl.StateSystemImpl#getStates <em>States</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.impl.StateSystemImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.modelexecution.xmof.states.states.impl.StateSystemImpl#getTrace <em>Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateSystemImpl extends MinimalEObjectImpl.Container implements StateSystem {
	
	private Map<State, ObjectStates> objectStateMap = new HashMap<State, ObjectStates>();
	
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> states;

	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> transitions;

	/**
	 * The cached value of the '{@link #getTrace() <em>Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrace()
	 * @generated
	 * @ordered
	 */
	protected Trace trace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatesPackage.Literals.STATE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<State>(State.class, this, StatesPackage.STATE_SYSTEM__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTransitions() {
		if (transitions == null) {
			transitions = new EObjectContainmentEList<Transition>(Transition.class, this, StatesPackage.STATE_SYSTEM__TRANSITIONS);
		}
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace getTrace() {
		if (trace != null && trace.eIsProxy()) {
			InternalEObject oldTrace = (InternalEObject)trace;
			trace = (Trace)eResolveProxy(oldTrace);
			if (trace != oldTrace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatesPackage.STATE_SYSTEM__TRACE, oldTrace, trace));
			}
		}
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace basicGetTrace() {
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrace(Trace newTrace) {
		Trace oldTrace = trace;
		trace = newTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatesPackage.STATE_SYSTEM__TRACE, oldTrace, trace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatesPackage.STATE_SYSTEM__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case StatesPackage.STATE_SYSTEM__TRANSITIONS:
				return ((InternalEList<?>)getTransitions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatesPackage.STATE_SYSTEM__STATES:
				return getStates();
			case StatesPackage.STATE_SYSTEM__TRANSITIONS:
				return getTransitions();
			case StatesPackage.STATE_SYSTEM__TRACE:
				if (resolve) return getTrace();
				return basicGetTrace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatesPackage.STATE_SYSTEM__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends State>)newValue);
				return;
			case StatesPackage.STATE_SYSTEM__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case StatesPackage.STATE_SYSTEM__TRACE:
				setTrace((Trace)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatesPackage.STATE_SYSTEM__STATES:
				getStates().clear();
				return;
			case StatesPackage.STATE_SYSTEM__TRANSITIONS:
				getTransitions().clear();
				return;
			case StatesPackage.STATE_SYSTEM__TRACE:
				setTrace((Trace)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatesPackage.STATE_SYSTEM__STATES:
				return states != null && !states.isEmpty();
			case StatesPackage.STATE_SYSTEM__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case StatesPackage.STATE_SYSTEM__TRACE:
				return trace != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public EObject getObjectState(State state, EObject eObject) {
		ObjectStates objectStates = objectStateMap.get(state);
		EObject eObjectState = objectStates.getEObjectState(eObject);
		return eObjectState;
	}
	
	@Override
	public EObject getOriginalObjectState(State state, EObject eObjectState) {
		ObjectStates objectStates = objectStateMap.get(state);
		EObject eObject = objectStates.getEObject(eObjectState);
		return eObject;
	}

	@Override
	public void addObjectsState(State state, EObject eObject,
			EObject eObjectState) {
		if(!objectStateMap.containsKey(state)) {
			objectStateMap.put(state, new ObjectStates(state));
		}
		ObjectStates objectStates = objectStateMap.get(state);
		objectStates.addObjectState(eObject, eObjectState);
	}

	@Override
	public State getStateAfterActivityExecution(ActivityExecution activityExecution) {
		ActivityNodeExecution lastNodeExecution = activityExecution.getLastExecutedNode();
		if(lastNodeExecution instanceof CallActionExecution && activityExecution.getCaller() != null) {
			// TODO in case there is no caller it might be necessary to retrieve the very last node executed
			lastNodeExecution = activityExecution.getCaller().getActivityExecution().getLastExecutedNode();
		}
		State state = getStateCausedByActivityNodeExecution(lastNodeExecution);
		if (state != null) {
			return state;
		}
		ActivityNodeExecution nodeExecution = lastNodeExecution.getChronologicalSuccessor();	
		while (state == null && nodeExecution != null) {
			state = getStateCausedByActivityNodeExecution(nodeExecution);
			nodeExecution = nodeExecution.getChronologicalSuccessor();
		}
		if (state != null) {
			return state.getPredecessorState();
		} else {
			return getLastState();
		}
	}
	
	@Override
	public State getLastState() {
		return getStates().get(states.size()-1);
	}
	
	@Override
	public State getStateCausedByActivityNodeExecution(ActivityNodeExecution activityNodeExecution) {
		if (activityNodeExecution != null) {
			for (Transition transition : getTransitions()) {
				if(transition.getEvent().getActionExecution() == activityNodeExecution) {
					return transition.getTarget();
				}
			}
		}
		return null;
	} 

} //StateSystemImpl
