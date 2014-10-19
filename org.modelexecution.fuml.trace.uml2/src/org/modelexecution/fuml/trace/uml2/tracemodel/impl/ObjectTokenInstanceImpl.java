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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Token Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ObjectTokenInstanceImpl#getTransportedValue <em>Transported Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectTokenInstanceImpl extends TokenInstanceImpl implements ObjectTokenInstance {
	/**
	 * The cached value of the '{@link #getTransportedValue() <em>Transported Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportedValue()
	 * @generated
	 * @ordered
	 */
	protected ValueInstance transportedValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectTokenInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OBJECT_TOKEN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance getTransportedValue() {
		if (transportedValue != null && transportedValue.eIsProxy()) {
			InternalEObject oldTransportedValue = (InternalEObject)transportedValue;
			transportedValue = (ValueInstance)eResolveProxy(oldTransportedValue);
			if (transportedValue != oldTransportedValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE, oldTransportedValue, transportedValue));
			}
		}
		return transportedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance basicGetTransportedValue() {
		return transportedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransportedValue(ValueInstance newTransportedValue) {
		ValueInstance oldTransportedValue = transportedValue;
		transportedValue = newTransportedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE, oldTransportedValue, transportedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				if (resolve) return getTransportedValue();
				return basicGetTransportedValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				setTransportedValue((ValueInstance)newValue);
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
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				setTransportedValue((ValueInstance)null);
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
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				return transportedValue != null;
		}
		return super.eIsSet(featureID);
	}

} //ObjectTokenInstanceImpl
