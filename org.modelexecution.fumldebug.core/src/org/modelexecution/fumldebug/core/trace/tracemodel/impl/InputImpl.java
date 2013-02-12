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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot;

import fUML.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl#getInputPin <em>Input Pin</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl#getConsumedValue <em>Consumed Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputImpl extends EObjectImpl implements Input {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	 * The default value of the '{@link #getInputPin() <em>Input Pin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputPin()
	 * @generated
	 * @ordered
	 */
	protected static final InputPin INPUT_PIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInputPin() <em>Input Pin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputPin()
	 * @generated
	 * @ordered
	 */
	protected InputPin inputPin = INPUT_PIN_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TokenInstance> getTokens() {
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
	public InputPin getInputPin() {
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.INPUT__TOKENS:
				return getTokens();
			case TracemodelPackage.INPUT__INPUT_PIN:
				return getInputPin();
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				if (resolve) return getConsumedValue();
				return basicGetConsumedValue();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackage.INPUT__TOKENS:
				getTokens().clear();
				getTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.INPUT__INPUT_PIN:
				setInputPin((InputPin)newValue);
				return;
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				setConsumedValue((ValueInstanceSnapshot)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracemodelPackage.INPUT__TOKENS:
				getTokens().clear();
				return;
			case TracemodelPackage.INPUT__INPUT_PIN:
				setInputPin(INPUT_PIN_EDEFAULT);
				return;
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				setConsumedValue((ValueInstanceSnapshot)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracemodelPackage.INPUT__TOKENS:
				return tokens != null && !tokens.isEmpty();
			case TracemodelPackage.INPUT__INPUT_PIN:
				return INPUT_PIN_EDEFAULT == null ? inputPin != null : !INPUT_PIN_EDEFAULT.equals(inputPin);
			case TracemodelPackage.INPUT__CONSUMED_VALUE:
				return consumedValue != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (inputPin: ");
		result.append(inputPin);
		result.append(')');
		return result.toString();
	}

} //InputImpl
