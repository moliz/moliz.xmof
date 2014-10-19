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

import org.modelexecution.fuml.trace.uml2.tracemodel.InputValue;
import org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.InputValueImpl#getInputObjectToken <em>Input Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputValueImpl extends InputOutputValueImpl implements InputValue {
	/**
	 * The cached value of the '{@link #getInputObjectToken() <em>Input Object Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputObjectToken()
	 * @generated
	 * @ordered
	 */
	protected ObjectTokenInstance inputObjectToken;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INPUT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance getInputObjectToken() {
		if (inputObjectToken != null && inputObjectToken.eIsProxy()) {
			InternalEObject oldInputObjectToken = (InternalEObject)inputObjectToken;
			inputObjectToken = (ObjectTokenInstance)eResolveProxy(oldInputObjectToken);
			if (inputObjectToken != oldInputObjectToken) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN, oldInputObjectToken, inputObjectToken));
			}
		}
		return inputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance basicGetInputObjectToken() {
		return inputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputObjectToken(ObjectTokenInstance newInputObjectToken) {
		ObjectTokenInstance oldInputObjectToken = inputObjectToken;
		inputObjectToken = newInputObjectToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN, oldInputObjectToken, inputObjectToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				if (resolve) return getInputObjectToken();
				return basicGetInputObjectToken();
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				setInputObjectToken((ObjectTokenInstance)newValue);
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				setInputObjectToken((ObjectTokenInstance)null);
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				return inputObjectToken != null;
		}
		return super.eIsSet(featureID);
	}

} //InputValueImpl
