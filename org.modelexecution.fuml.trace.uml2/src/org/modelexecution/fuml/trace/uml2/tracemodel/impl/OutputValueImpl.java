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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.OutputValue;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.OutputValueImpl#getOutputObjectToken <em>Output Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputValueImpl extends InputOutputValueImpl implements OutputValue {
	/**
	 * The cached value of the '{@link #getOutputObjectToken() <em>Output Object Token</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputObjectToken()
	 * @generated
	 * @ordered
	 */
	protected ObjectTokenInstance outputObjectToken;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OUTPUT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance getOutputObjectToken() {
		return outputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutputObjectToken(ObjectTokenInstance newOutputObjectToken, NotificationChain msgs) {
		ObjectTokenInstance oldOutputObjectToken = outputObjectToken;
		outputObjectToken = newOutputObjectToken;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN, oldOutputObjectToken, newOutputObjectToken);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputObjectToken(ObjectTokenInstance newOutputObjectToken) {
		if (newOutputObjectToken != outputObjectToken) {
			NotificationChain msgs = null;
			if (outputObjectToken != null)
				msgs = ((InternalEObject)outputObjectToken).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN, null, msgs);
			if (newOutputObjectToken != null)
				msgs = ((InternalEObject)newOutputObjectToken).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN, null, msgs);
			msgs = basicSetOutputObjectToken(newOutputObjectToken, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN, newOutputObjectToken, newOutputObjectToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN:
				return basicSetOutputObjectToken(null, msgs);
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
			case TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN:
				return getOutputObjectToken();
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
			case TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN:
				setOutputObjectToken((ObjectTokenInstance)newValue);
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
			case TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN:
				setOutputObjectToken((ObjectTokenInstance)null);
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
			case TracemodelPackage.OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN:
				return outputObjectToken != null;
		}
		return super.eIsSet(featureID);
	}

} //OutputValueImpl
