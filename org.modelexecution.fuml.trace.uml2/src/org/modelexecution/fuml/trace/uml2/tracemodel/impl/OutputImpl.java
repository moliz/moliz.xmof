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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.OutputPin;

import org.modelexecution.fuml.trace.uml2.tracemodel.Output;
import org.modelexecution.fuml.trace.uml2.tracemodel.OutputValue;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.OutputImpl#getOutputPin <em>Output Pin</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.OutputImpl#getOutputValues <em>Output Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputImpl extends MinimalEObjectImpl.Container implements Output {
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
	 * The cached value of the '{@link #getOutputValues() <em>Output Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputValues()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputValue> outputValues;

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
	public EList<OutputValue> getOutputValues() {
		if (outputValues == null) {
			outputValues = new EObjectContainmentEList<OutputValue>(OutputValue.class, this, TracemodelPackage.OUTPUT__OUTPUT_VALUES);
		}
		return outputValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				return ((InternalEList<?>)getOutputValues()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				if (resolve) return getOutputPin();
				return basicGetOutputPin();
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				return getOutputValues();
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin((OutputPin)newValue);
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				getOutputValues().clear();
				getOutputValues().addAll((Collection<? extends OutputValue>)newValue);
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin((OutputPin)null);
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				getOutputValues().clear();
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				return outputPin != null;
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				return outputValues != null && !outputValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OutputImpl
