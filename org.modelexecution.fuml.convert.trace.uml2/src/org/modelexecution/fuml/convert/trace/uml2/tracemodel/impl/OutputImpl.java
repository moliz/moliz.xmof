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

import org.eclipse.uml2.uml.OutputPin;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.OutputImpl#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.OutputImpl#getOutputPin <em>Output Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputImpl extends EObjectImpl implements Output {
	/**
	 * The cached value of the '{@link #getTokens() <em>Tokens</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<TokenInstance> tokens;

	/**
	 * The cached value of the '{@link #getOutputPin() <em>Output Pin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputPin()
	 * @generated
	 * @ordered
	 */
	protected OutputPin outputPin;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OUTPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TokenInstance> getTokens() {
		if (tokens == null) {
			tokens = new EObjectContainmentEList<TokenInstance>(TokenInstance.class, this, TracemodelPackage.OUTPUT__TOKENS);
		}
		return tokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPin getOutputPin() {
		if (outputPin != null && outputPin.eIsProxy()) {
			InternalEObject oldOutputPin = (InternalEObject)outputPin;
			outputPin = (OutputPin)eResolveProxy(oldOutputPin);
			if (outputPin != oldOutputPin) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.OUTPUT__OUTPUT_PIN, oldOutputPin, outputPin));
			}
		}
		return outputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPin basicGetOutputPin() {
		return outputPin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputPin(OutputPin newOutputPin) {
		OutputPin oldOutputPin = outputPin;
		outputPin = newOutputPin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.OUTPUT__OUTPUT_PIN, oldOutputPin, outputPin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.OUTPUT__TOKENS:
				return ((InternalEList<?>)getTokens()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.OUTPUT__TOKENS:
				return getTokens();
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				if (resolve) return getOutputPin();
				return basicGetOutputPin();
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
			case TracemodelPackage.OUTPUT__TOKENS:
				getTokens().clear();
				getTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin((OutputPin)newValue);
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
			case TracemodelPackage.OUTPUT__TOKENS:
				getTokens().clear();
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin((OutputPin)null);
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
			case TracemodelPackage.OUTPUT__TOKENS:
				return tokens != null && !tokens.isEmpty();
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				return outputPin != null;
		}
		return eDynamicIsSet(featureID);
	}

} //OutputImpl
