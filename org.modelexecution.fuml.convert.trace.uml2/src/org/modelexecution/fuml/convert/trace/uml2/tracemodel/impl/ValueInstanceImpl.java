/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstance;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstanceSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ValueInstanceImpl#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ValueInstanceImpl#getOriginal <em>Original</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ValueInstanceImpl#getValueID <em>Value ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueInstanceImpl extends EObjectImpl implements ValueInstance {
	/**
	 * The cached value of the '{@link #getSnapshots() <em>Snapshots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSnapshots()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueInstanceSnapshot> snapshots;

	/**
	 * The cached value of the '{@link #getOriginal() <em>Original</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginal()
	 * @generated
	 * @ordered
	 */
	protected ValueInstanceSnapshot original;

	/**
	 * The default value of the '{@link #getValueID() <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueID()
	 * @generated
	 * @ordered
	 */
	protected static final int VALUE_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getValueID() <em>Value ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueID()
	 * @generated
	 * @ordered
	 */
	protected int valueID = VALUE_ID_EDEFAULT;

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
	public EList<ValueInstanceSnapshot> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EObjectContainmentEList<ValueInstanceSnapshot>(ValueInstanceSnapshot.class, this, TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS);
		}
		return snapshots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstanceSnapshot getOriginal() {
		if (original != null && original.eIsProxy()) {
			InternalEObject oldOriginal = (InternalEObject)original;
			original = (ValueInstanceSnapshot)eResolveProxy(oldOriginal);
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
	public ValueInstanceSnapshot basicGetOriginal() {
		return original;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginal(ValueInstanceSnapshot newOriginal) {
		ValueInstanceSnapshot oldOriginal = original;
		original = newOriginal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__ORIGINAL, oldOriginal, original));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValueID() {
		return valueID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueID(int newValueID) {
		int oldValueID = valueID;
		valueID = newValueID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__VALUE_ID, oldValueID, valueID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return ((InternalEList<?>)getSnapshots()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return getSnapshots();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				if (resolve) return getOriginal();
				return basicGetOriginal();
			case TracemodelPackage.VALUE_INSTANCE__VALUE_ID:
				return getValueID();
		}
		return eDynamicGet(featureID, resolve, coreType);
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
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				getSnapshots().clear();
				getSnapshots().addAll((Collection<? extends ValueInstanceSnapshot>)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				setOriginal((ValueInstanceSnapshot)newValue);
				return;
			case TracemodelPackage.VALUE_INSTANCE__VALUE_ID:
				setValueID((Integer)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				getSnapshots().clear();
				return;
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				setOriginal((ValueInstanceSnapshot)null);
				return;
			case TracemodelPackage.VALUE_INSTANCE__VALUE_ID:
				setValueID(VALUE_ID_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return snapshots != null && !snapshots.isEmpty();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				return original != null;
			case TracemodelPackage.VALUE_INSTANCE__VALUE_ID:
				return valueID != VALUE_ID_EDEFAULT;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (valueID: ");
		result.append(valueID);
		result.append(')');
		return result.toString();
	}

} //ValueInstanceImpl
