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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Classes.Kernel.Parameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Parameter Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterSettingImpl#getParameterValues <em>Parameter Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputParameterSettingImpl extends ParameterSettingImpl implements OutputParameterSetting {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getParameterValues() <em>Parameter Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterValues()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputParameterValue> parameterValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputParameterSettingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OUTPUT_PARAMETER_SETTING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<OutputParameterValue> getParameterValues() {
		if (parameterValues == null) {
			parameterValues = new EObjectContainmentEList<OutputParameterValue>(OutputParameterValue.class, this, TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES);
		}
		return parameterValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES:
				return ((InternalEList<?>)getParameterValues()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER:
				return getParameter();
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES:
				return getParameterValues();
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
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER:
				setParameter((Parameter)newValue);
				return;
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES:
				getParameterValues().clear();
				getParameterValues().addAll((Collection<? extends OutputParameterValue>)newValue);
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
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER:
				setParameter(PARAMETER_EDEFAULT);
				return;
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES:
				getParameterValues().clear();
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
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER:
				return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES:
				return parameterValues != null && !parameterValues.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //OutputParameterSettingImpl
