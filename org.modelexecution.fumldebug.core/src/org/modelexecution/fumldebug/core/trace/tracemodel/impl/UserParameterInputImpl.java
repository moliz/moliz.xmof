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
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;

import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Parameter Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl#getInputParameterNode <em>Input Parameter Node</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl#getProvidedValues <em>Provided Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserParameterInputImpl extends EObjectImpl implements UserParameterInput {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The default value of the '{@link #getInputParameterNode() <em>Input Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityParameterNode INPUT_PARAMETER_NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInputParameterNode() <em>Input Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityParameterNode inputParameterNode = INPUT_PARAMETER_NODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProvidedValues() <em>Provided Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputValue> providedValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserParameterInputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.USER_PARAMETER_INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNode getInputParameterNode() {
		return inputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputParameterNode(ActivityParameterNode newInputParameterNode) {
		ActivityParameterNode oldInputParameterNode = inputParameterNode;
		inputParameterNode = newInputParameterNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE, oldInputParameterNode, inputParameterNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<OutputValue> getProvidedValues() {
		if (providedValues == null) {
			providedValues = new EObjectContainmentEList<OutputValue>(OutputValue.class, this, TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES);
		}
		return providedValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES:
				return ((InternalEList<?>)getProvidedValues()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				return getInputParameterNode();
			case TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES:
				return getProvidedValues();
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
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode((ActivityParameterNode)newValue);
				return;
			case TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES:
				getProvidedValues().clear();
				getProvidedValues().addAll((Collection<? extends OutputValue>)newValue);
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
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode(INPUT_PARAMETER_NODE_EDEFAULT);
				return;
			case TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES:
				getProvidedValues().clear();
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
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				return INPUT_PARAMETER_NODE_EDEFAULT == null ? inputParameterNode != null : !INPUT_PARAMETER_NODE_EDEFAULT.equals(inputParameterNode);
			case TracemodelPackage.USER_PARAMETER_INPUT__PROVIDED_VALUES:
				return providedValues != null && !providedValues.isEmpty();
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
		result.append(" (inputParameterNode: ");
		result.append(inputParameterNode);
		result.append(')');
		return result.toString();
	}

} //UserParameterInputImpl
