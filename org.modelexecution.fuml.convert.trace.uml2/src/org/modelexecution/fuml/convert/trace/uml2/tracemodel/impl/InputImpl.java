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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.uml2.uml.InputPin;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstanceSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.InputImpl#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.InputImpl#getConsumedValue <em>Consumed Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.InputImpl#getInputPin <em>Input Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputImpl extends EObjectImpl implements Input {
	/**
	 * The cached value of the '{@link #getTokens() <em>Tokens</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<TokenInstance> tokens;

	/**
	 * The cached value of the '{@link #getConsumedValue() <em>Consumed Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConsumedValue()
	 * @generated
	 * @ordered
	 */
	protected ValueInstanceSnapshot consumedValue;

	/**
	 * The cached value of the '{@link #getInputPin() <em>Input Pin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputPin()
	 * @generated
	 * @ordered
	 */
	protected InputPin inputPin;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TokenInstance> getTokens() {
		if (tokens == null) {
			tokens = new EObjectResolvingEList<TokenInstance>(TokenInstance.class, this, TracemodelPackage.INPUT__TOKENS);
		}
		return tokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstanceSnapshot getConsumedValue() {
		if (consumedValue != null && consumedValue.eIsProxy()) {
			InternalEObject oldConsumedValue = (InternalEObject)consumedValue;
			consumedValue = (ValueInstanceSnapshot)eResolveProxy(oldConsumedValue);
			if (consumedValue != oldConsumedValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.INPUT__CONSUMED_VALUE, oldConsumedValue, consumedValue));
			}
		}
		return consumedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstanceSnapshot basicGetConsumedValue() {
		return consumedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConsumedValue(ValueInstanceSnapshot newConsumedValue) {
		ValueInstanceSnapshot oldConsumedValue = consumedValue;
		consumedValue = newConsumedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT__CONSUMED_VALUE, oldConsumedValue, consumedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPin getInputPin() {
		if (inputPin != null && inputPin.eIsProxy()) {
			InternalEObject oldInputPin = (InternalEObject)inputPin;
			inputPin = (InputPin)eResolveProxy(oldInputPin);
			if (inputPin != oldInputPin) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.INPUT__INPUT_PIN, oldInputPin, inputPin));
			}
		}
		return inputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPin basicGetInputPin() {
		return inputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputPin(InputPin newInputPin) {
		InputPin oldInputPin = inputPin;
		inputPin = newInputPin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT__INPUT_PIN, oldInputPin, inputPin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.INPUT__TOKENS:
				return getTokens();
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				if (resolve) return getConsumedValue();
				return basicGetConsumedValue();
			case TracemodelPackage.INPUT__INPUT_PIN:
				if (resolve) return getInputPin();
				return basicGetInputPin();
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
			case TracemodelPackage.INPUT__TOKENS:
				getTokens().clear();
				getTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				setConsumedValue((ValueInstanceSnapshot)newValue);
				return;
			case TracemodelPackage.INPUT__INPUT_PIN:
				setInputPin((InputPin)newValue);
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
			case TracemodelPackage.INPUT__TOKENS:
				getTokens().clear();
				return;
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				setConsumedValue((ValueInstanceSnapshot)null);
				return;
			case TracemodelPackage.INPUT__INPUT_PIN:
				setInputPin((InputPin)null);
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
			case TracemodelPackage.INPUT__TOKENS:
				return tokens != null && !tokens.isEmpty();
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				return consumedValue != null;
			case TracemodelPackage.INPUT__INPUT_PIN:
				return inputPin != null;
		}
		return eDynamicIsSet(featureID);
	}

} //InputImpl
