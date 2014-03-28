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
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.states.states.Transition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.modelexecution.xmof.states.states.impl.StateImpl#getOutgoing
 * <em>Outgoing</em>}</li>
 * <li>{@link org.modelexecution.xmof.states.states.impl.StateImpl#getIncoming
 * <em>Incoming</em>}</li>
 * <li>{@link org.modelexecution.xmof.states.states.impl.StateImpl#getObjects
 * <em>Objects</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StateImpl extends MinimalEObjectImpl.Container implements State {
	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected Transition outgoing;

	/**
	 * The cached value of the '{@link #getIncoming() <em>Incoming</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIncoming()
	 * @generated
	 * @ordered
	 */
	protected Transition incoming;

	/**
	 * The cached value of the '{@link #getObjects() <em>Objects</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> objects;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatesPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Transition getOutgoing() {
		if (outgoing != null && outgoing.eIsProxy()) {
			InternalEObject oldOutgoing = (InternalEObject) outgoing;
			outgoing = (Transition) eResolveProxy(oldOutgoing);
			if (outgoing != oldOutgoing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							StatesPackage.STATE__OUTGOING, oldOutgoing,
							outgoing));
			}
		}
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Transition basicGetOutgoing() {
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetOutgoing(Transition newOutgoing,
			NotificationChain msgs) {
		Transition oldOutgoing = outgoing;
		outgoing = newOutgoing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, StatesPackage.STATE__OUTGOING,
					oldOutgoing, newOutgoing);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOutgoing(Transition newOutgoing) {
		if (newOutgoing != outgoing) {
			NotificationChain msgs = null;
			if (outgoing != null)
				msgs = ((InternalEObject) outgoing).eInverseRemove(this,
						StatesPackage.TRANSITION__SOURCE, Transition.class,
						msgs);
			if (newOutgoing != null)
				msgs = ((InternalEObject) newOutgoing).eInverseAdd(this,
						StatesPackage.TRANSITION__SOURCE, Transition.class,
						msgs);
			msgs = basicSetOutgoing(newOutgoing, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StatesPackage.STATE__OUTGOING, newOutgoing, newOutgoing));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Transition getIncoming() {
		if (incoming != null && incoming.eIsProxy()) {
			InternalEObject oldIncoming = (InternalEObject) incoming;
			incoming = (Transition) eResolveProxy(oldIncoming);
			if (incoming != oldIncoming) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							StatesPackage.STATE__INCOMING, oldIncoming,
							incoming));
			}
		}
		return incoming;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Transition basicGetIncoming() {
		return incoming;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetIncoming(Transition newIncoming,
			NotificationChain msgs) {
		Transition oldIncoming = incoming;
		incoming = newIncoming;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, StatesPackage.STATE__INCOMING,
					oldIncoming, newIncoming);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIncoming(Transition newIncoming) {
		if (newIncoming != incoming) {
			NotificationChain msgs = null;
			if (incoming != null)
				msgs = ((InternalEObject) incoming).eInverseRemove(this,
						StatesPackage.TRANSITION__TARGET, Transition.class,
						msgs);
			if (newIncoming != null)
				msgs = ((InternalEObject) newIncoming).eInverseAdd(this,
						StatesPackage.TRANSITION__TARGET, Transition.class,
						msgs);
			msgs = basicSetIncoming(newIncoming, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					StatesPackage.STATE__INCOMING, newIncoming, newIncoming));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<EObject> getObjects() {
		if (objects == null) {
			objects = new EObjectContainmentEList<EObject>(EObject.class, this,
					StatesPackage.STATE__OBJECTS);
		}
		return objects;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			if (outgoing != null)
				msgs = ((InternalEObject) outgoing).eInverseRemove(this,
						StatesPackage.TRANSITION__SOURCE, Transition.class,
						msgs);
			return basicSetOutgoing((Transition) otherEnd, msgs);
		case StatesPackage.STATE__INCOMING:
			if (incoming != null)
				msgs = ((InternalEObject) incoming).eInverseRemove(this,
						StatesPackage.TRANSITION__TARGET, Transition.class,
						msgs);
			return basicSetIncoming((Transition) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			return basicSetOutgoing(null, msgs);
		case StatesPackage.STATE__INCOMING:
			return basicSetIncoming(null, msgs);
		case StatesPackage.STATE__OBJECTS:
			return ((InternalEList<?>) getObjects())
					.basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			if (resolve)
				return getOutgoing();
			return basicGetOutgoing();
		case StatesPackage.STATE__INCOMING:
			if (resolve)
				return getIncoming();
			return basicGetIncoming();
		case StatesPackage.STATE__OBJECTS:
			return getObjects();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			setOutgoing((Transition) newValue);
			return;
		case StatesPackage.STATE__INCOMING:
			setIncoming((Transition) newValue);
			return;
		case StatesPackage.STATE__OBJECTS:
			getObjects().clear();
			getObjects().addAll((Collection<? extends EObject>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			setOutgoing((Transition) null);
			return;
		case StatesPackage.STATE__INCOMING:
			setIncoming((Transition) null);
			return;
		case StatesPackage.STATE__OBJECTS:
			getObjects().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case StatesPackage.STATE__OUTGOING:
			return outgoing != null;
		case StatesPackage.STATE__INCOMING:
			return incoming != null;
		case StatesPackage.STATE__OBJECTS:
			return objects != null && !objects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public EObject getObjectState(EObject eObject) {
		EObject container = this.eContainer;
		if (container instanceof StateSystem) {
			StateSystem stateSystem = (StateSystem) container;
			return stateSystem.getObjectState(this, eObject);
		}
		return null;
	}

	@Override
	public State getPredecessorState() {
		Transition incomingTransition = getIncoming();
		if (incomingTransition != null) {
			return incomingTransition.getSource();
		}
		return null;
	}

	@Override
	public State getSuccessorState() {
		Transition outgoingTransition = getOutgoing();
		if (outgoingTransition != null) {
			return outgoingTransition.getTarget();
		}
		return null;
	}
	
} // StateImpl
