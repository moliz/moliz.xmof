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
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterValueImpl#getParameterOutputObjectToken <em>Parameter Output Object Token</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputParameterValueImpl extends ParameterValueImpl implements OutputParameterValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getParameterOutputObjectToken() <em>Parameter Output Object Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterOutputObjectToken()
	 * @generated
	 * @ordered
	 */
	protected ObjectTokenInstance parameterOutputObjectToken;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputParameterValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OUTPUT_PARAMETER_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance getParameterOutputObjectToken() {
		if (parameterOutputObjectToken != null && parameterOutputObjectToken.eIsProxy()) {
			InternalEObject oldParameterOutputObjectToken = (InternalEObject)parameterOutputObjectToken;
			parameterOutputObjectToken = (ObjectTokenInstance)eResolveProxy(oldParameterOutputObjectToken);
			if (parameterOutputObjectToken != oldParameterOutputObjectToken) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN, oldParameterOutputObjectToken, parameterOutputObjectToken));
			}
		}
		return parameterOutputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance basicGetParameterOutputObjectToken() {
		return parameterOutputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterOutputObjectToken(ObjectTokenInstance newParameterOutputObjectToken) {
		ObjectTokenInstance oldParameterOutputObjectToken = parameterOutputObjectToken;
		parameterOutputObjectToken = newParameterOutputObjectToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN, oldParameterOutputObjectToken, parameterOutputObjectToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				if (resolve) return getValueSnapshot();
				return basicGetValueSnapshot();
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN:
				if (resolve) return getParameterOutputObjectToken();
				return basicGetParameterOutputObjectToken();
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
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)newValue);
				return;
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN:
				setParameterOutputObjectToken((ObjectTokenInstance)newValue);
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
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				setValueSnapshot((ValueSnapshot)null);
				return;
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN:
				setParameterOutputObjectToken((ObjectTokenInstance)null);
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
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__VALUE_SNAPSHOT:
				return valueSnapshot != null;
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN:
				return parameterOutputObjectToken != null;
		}
		return eDynamicIsSet(featureID);
	}

} //OutputParameterValueImpl
