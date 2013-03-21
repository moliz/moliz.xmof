/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

import fUML.Semantics.Classes.Kernel.Value;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl#getRuntimeValue <em>Runtime Value</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl#getOriginal <em>Original</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl#isDestroyed <em>Destroyed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueInstanceImpl extends EObjectImpl implements ValueInstance {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The default value of the '{@link #getRuntimeValue() <em>Runtime Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeValue()
	 * @generated
	 * @ordered
	 */
	protected static final Value RUNTIME_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuntimeValue() <em>Runtime Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeValue()
	 * @generated
	 * @ordered
	 */
	protected Value runtimeValue = RUNTIME_VALUE_EDEFAULT;

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
	 * The default value of the '{@link #isDestroyed() <em>Destroyed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDestroyed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DESTROYED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDestroyed() <em>Destroyed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDestroyed()
	 * @generated
	 * @ordered
	 */
	protected boolean destroyed = DESTROYED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstanceImpl() {
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
	public void setRuntimeValue(Value newRuntimeValue) {
		Value oldRuntimeValue = runtimeValue;
		runtimeValue = newRuntimeValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE, oldRuntimeValue, runtimeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ValueSnapshot> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EObjectContainmentEList<ValueSnapshot>(ValueSnapshot.class, this, TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS);
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
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestroyed(boolean newDestroyed) {
		boolean oldDestroyed = destroyed;
		destroyed = newDestroyed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.VALUE_INSTANCE__DESTROYED, oldDestroyed, destroyed));
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				return getRuntimeValue();
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return getSnapshots();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				if (resolve) return getOriginal();
				return basicGetOriginal();
			case TracemodelPackage.VALUE_INSTANCE__DESTROYED:
				return isDestroyed();
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
			case TracemodelPackage.VALUE_INSTANCE__DESTROYED:
				setDestroyed((Boolean)newValue);
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				setRuntimeValue(RUNTIME_VALUE_EDEFAULT);
				return;
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				getSnapshots().clear();
				return;
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				setOriginal((ValueSnapshot)null);
				return;
			case TracemodelPackage.VALUE_INSTANCE__DESTROYED:
				setDestroyed(DESTROYED_EDEFAULT);
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
			case TracemodelPackage.VALUE_INSTANCE__RUNTIME_VALUE:
				return RUNTIME_VALUE_EDEFAULT == null ? runtimeValue != null : !RUNTIME_VALUE_EDEFAULT.equals(runtimeValue);
			case TracemodelPackage.VALUE_INSTANCE__SNAPSHOTS:
				return snapshots != null && !snapshots.isEmpty();
			case TracemodelPackage.VALUE_INSTANCE__ORIGINAL:
				return original != null;
			case TracemodelPackage.VALUE_INSTANCE__DESTROYED:
				return destroyed != DESTROYED_EDEFAULT;
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
		result.append(" (runtimeValue: ");
		result.append(runtimeValue);
		result.append(", destroyed: ");
		result.append(destroyed);
		result.append(')');
		return result.toString();
	}

	@Override
	public ValueSnapshot getLatestSnapshot() {
		int snapshotsize = this.snapshots.size();
		if(snapshotsize>0) {
			return this.snapshots.get(snapshotsize-1);
		}
		return null;
	}

} //ValueInstanceImpl
