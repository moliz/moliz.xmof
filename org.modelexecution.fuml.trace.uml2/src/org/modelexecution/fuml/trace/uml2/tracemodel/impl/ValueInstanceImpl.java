/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value;

import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ValueInstanceImpl#getRuntimeValue <em>Runtime Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ValueInstanceImpl#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ValueInstanceImpl#getOriginal <em>Original</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ValueInstanceImpl#getCreator <em>Creator</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ValueInstanceImpl#getDestroyer <em>Destroyer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueInstanceImpl extends MinimalEObjectImpl.Container implements ValueInstance {
	/**
	 * The cached value of the '{@link #getRuntimeValue() <em>Runtime Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeValue()
	 * @generated
	 * @ordered
	 */
	protected Value runtimeValue;

	/**
	 * The cached value of the '{@link #getSnapshots() <em>Snapshots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnapshots()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueSnapshot> snapshots;

	/**
	 * The cached value of the '{@link #getOriginal() <em>Original</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginal()
	 * @generated
	 * @ordered
	 */
	protected ValueSnapshot original;

	/**
	 * The cached value of the '{@link #getCreator() <em>Creator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected ActivityNodeExecution creator;

	/**
	 * The cached value of the '{@link #getDestroyer() <em>Destroyer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestroyer()
	 * @generated
	 * @ordered
	 */
	protected ActivityNodeExecution destroyer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.VALUE_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value getRuntimeValue() {
		return runtimeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRuntimeValue(Value newRuntimeValue, NotificationChain msgs) {
		Value oldRuntimeValue = runtimeValue;
		runtimeValue = newRuntimeValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE, oldRuntimeValue, newRuntimeValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuntimeValue(Value newRuntimeValue) {
		if (newRuntimeValue != runtimeValue) {
			NotificationChain msgs = null;
			if (runtimeValue != null)
				msgs = ((InternalEObject)runtimeValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE, null, msgs);
			if (newRuntimeValue != null)
				msgs = ((InternalEObject)newRuntimeValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE, null, msgs);
			msgs = basicSetRuntimeValue(newRuntimeValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE, newRuntimeValue, newRuntimeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueSnapshot> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EObjectContainmentWithInverseEList<ValueSnapshot>(ValueSnapshot.class, this, TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS, TracemodelPackage.VALUE_SNAPSHOT__VALUE_INSTANCE);
		}
		return snapshots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot getOriginal() {
		if (original != null && original.eIsProxy()) {
			InternalEObject oldOriginal = (InternalEObject)original;
			original = (ValueSnapshot)eResolveProxy(oldOriginal);
			if (original != oldOriginal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.VALUE_INSTANCE__ORIGINAL, oldOriginal, original));
			}
		}
		return original;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot basicGetOriginal() {
		return original;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginal(ValueSnapshot newOriginal) {
		ValueSnapshot oldOriginal = original;
		original = newOriginal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__ORIGINAL, oldOriginal, original));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution getCreator() {
		if (creator != null && creator.eIsProxy()) {
			InternalEObject oldCreator = (InternalEObject)creator;
			creator = (ActivityNodeExecution)eResolveProxy(oldCreator);
			if (creator != oldCreator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.VALUE_INSTANCE__CREATOR, oldCreator, creator));
			}
		}
		return creator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution basicGetCreator() {
		return creator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreator(ActivityNodeExecution newCreator) {
		ActivityNodeExecution oldCreator = creator;
		creator = newCreator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__CREATOR, oldCreator, creator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution getDestroyer() {
		if (destroyer != null && destroyer.eIsProxy()) {
			InternalEObject oldDestroyer = (InternalEObject)destroyer;
			destroyer = (ActivityNodeExecution)eResolveProxy(oldDestroyer);
			if (destroyer != oldDestroyer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.VALUE_INSTANCE__DESTROYER, oldDestroyer, destroyer));
			}
		}
		return destroyer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution basicGetDestroyer() {
		return destroyer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestroyer(ActivityNodeExecution newDestroyer) {
		ActivityNodeExecution oldDestroyer = destroyer;
		destroyer = newDestroyer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__DESTROYER, oldDestroyer, destroyer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSnapshots()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				return basicSetRuntimeValue(null, msgs);
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return ((InternalEList<?>)getSnapshots()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				return getRuntimeValue();
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return getSnapshots();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				if (resolve) return getOriginal();
				return basicGetOriginal();
			case TracemodelPackage.VALUE_INSTANCE__CREATOR:
				if (resolve) return getCreator();
				return basicGetCreator();
			case TracemodelPackage.VALUE_INSTANCE__DESTROYER:
				if (resolve) return getDestroyer();
				return basicGetDestroyer();
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				setRuntimeValue((Value)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				getSnapshots().clear();
				getSnapshots().addAll((Collection<? extends ValueSnapshot>)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				setOriginal((ValueSnapshot)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__CREATOR:
				setCreator((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__DESTROYER:
				setDestroyer((ActivityNodeExecution)newValue);
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				setRuntimeValue((Value)null);
				return;
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				getSnapshots().clear();
				return;
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				setOriginal((ValueSnapshot)null);
				return;
			case TracemodelPackage.VALUE_INSTANCE__CREATOR:
				setCreator((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.VALUE_INSTANCE__DESTROYER:
				setDestroyer((ActivityNodeExecution)null);
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				return runtimeValue != null;
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return snapshots != null && !snapshots.isEmpty();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				return original != null;
			case TracemodelPackage.VALUE_INSTANCE__CREATOR:
				return creator != null;
			case TracemodelPackage.VALUE_INSTANCE__DESTROYER:
				return destroyer != null;
		}
		return super.eIsSet(featureID);
	}

} //ValueInstanceImpl
