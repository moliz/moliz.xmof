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
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Actions.BasicActions.OutputPin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl#getOutputPin <em>Output Pin</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl#getOutputValues <em>Output Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputImpl extends EObjectImpl implements Output {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The default value of the '{@link #getOutputPin() <em>Output Pin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputPin()
	 * @generated
	 * @ordered
	 */
	protected static final OutputPin OUTPUT_PIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputPin() <em>Output Pin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputPin()
	 * @generated
	 * @ordered
	 */
	protected OutputPin outputPin = OUTPUT_PIN_EDEFAULT;

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
	public OutputImpl() {
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
	public List<OutputValue> getOutputValues() {
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				return getOutputPin();
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				return getOutputValues();
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin((OutputPin)newValue);
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				getOutputValues().clear();
				getOutputValues().addAll((Collection<? extends OutputValue>)newValue);
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				setOutputPin(OUTPUT_PIN_EDEFAULT);
				return;
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				getOutputValues().clear();
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
			case TracemodelPackage.OUTPUT__OUTPUT_PIN:
				return OUTPUT_PIN_EDEFAULT == null ? outputPin != null : !OUTPUT_PIN_EDEFAULT.equals(outputPin);
			case TracemodelPackage.OUTPUT__OUTPUT_VALUES:
				return outputValues != null && !outputValues.isEmpty();
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
		result.append(" (outputPin: ");
		result.append(outputPin);
		result.append(')');
		return result.toString();
	}

} //OutputImpl
