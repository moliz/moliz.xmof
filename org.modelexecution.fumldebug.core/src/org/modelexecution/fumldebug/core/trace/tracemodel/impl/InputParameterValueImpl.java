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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterValueImpl#getParameterInputObjectToken <em>Parameter Input Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputParameterValueImpl extends ParameterValueImpl implements InputParameterValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getParameterInputObjectToken() <em>Parameter Input Object Token</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterInputObjectToken()
	 * @generated
	 * @ordered
	 */
	protected ObjectTokenInstance parameterInputObjectToken;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputParameterValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INPUT_PARAMETER_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance getParameterInputObjectToken() {
		return parameterInputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParameterInputObjectToken(ObjectTokenInstance newParameterInputObjectToken, NotificationChain msgs) {
		ObjectTokenInstance oldParameterInputObjectToken = parameterInputObjectToken;
		parameterInputObjectToken = newParameterInputObjectToken;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN, oldParameterInputObjectToken, newParameterInputObjectToken);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterInputObjectToken(ObjectTokenInstance newParameterInputObjectToken) {
		if (newParameterInputObjectToken != parameterInputObjectToken) {
			NotificationChain msgs = null;
			if (parameterInputObjectToken != null)
				msgs = ((InternalEObject)parameterInputObjectToken).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN, null, msgs);
			if (newParameterInputObjectToken != null)
				msgs = ((InternalEObject)newParameterInputObjectToken).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN, null, msgs);
			msgs = basicSetParameterInputObjectToken(newParameterInputObjectToken, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN, newParameterInputObjectToken, newParameterInputObjectToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN:
				return basicSetParameterInputObjectToken(null, msgs);
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
			case TracemodelPackage.INPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				if (resolve) return getValueSnapshot();
				return basicGetValueSnapshot();
			case TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN:
				return getParameterInputObjectToken();
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
			case TracemodelPackage.INPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)newValue);
				return;
			case TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN:
				setParameterInputObjectToken((ObjectTokenInstance)newValue);
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
			case TracemodelPackage.INPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)null);
				return;
			case TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN:
				setParameterInputObjectToken((ObjectTokenInstance)null);
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
			case TracemodelPackage.INPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				return valueSnapshot != null;
			case TracemodelPackage.INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN:
				return parameterInputObjectToken != null;
		}
		return eDynamicIsSet(featureID);
	}

} //InputParameterValueImpl
