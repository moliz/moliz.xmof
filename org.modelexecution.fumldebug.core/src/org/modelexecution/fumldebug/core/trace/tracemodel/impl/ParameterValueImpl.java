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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterValueImpl#getValueSnapshot <em>Value Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ParameterValueImpl extends EObjectImpl implements ParameterValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getValueSnapshot() <em>Value Snapshot</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueSnapshot()
	 * @generated
	 * @ordered
	 */
	protected ValueSnapshot valueSnapshot;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.PARAMETER_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot getValueSnapshot() {
		if (valueSnapshot != null && valueSnapshot.eIsProxy()) {
			InternalEObject oldValueSnapshot = (InternalEObject)valueSnapshot;
			valueSnapshot = (ValueSnapshot)eResolveProxy(oldValueSnapshot);
			if (valueSnapshot != oldValueSnapshot) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT, oldValueSnapshot, valueSnapshot));
			}
		}
		return valueSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot basicGetValueSnapshot() {
		return valueSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueSnapshot(ValueSnapshot newValueSnapshot) {
		ValueSnapshot oldValueSnapshot = valueSnapshot;
		valueSnapshot = newValueSnapshot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT, oldValueSnapshot, valueSnapshot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT:
				if (resolve) return getValueSnapshot();
				return basicGetValueSnapshot();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)newValue);
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
			case TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)null);
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
			case TracemodelPackage.PARAMETER_VALUE__VALUE_SNAPSHOT:
				return valueSnapshot != null;
		}
		return eDynamicIsSet(featureID);
	}

} //ParameterValueImpl
