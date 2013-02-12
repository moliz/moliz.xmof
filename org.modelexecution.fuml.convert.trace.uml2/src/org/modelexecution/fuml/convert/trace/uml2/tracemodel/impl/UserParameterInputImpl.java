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

import org.eclipse.uml2.uml.ActivityParameterNode;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ObjectTokenInstance;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Parameter Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.UserParameterInputImpl#getUserInputTokens <em>User Input Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.UserParameterInputImpl#getInputParameterNode <em>Input Parameter Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserParameterInputImpl extends EObjectImpl implements UserParameterInput {
	/**
	 * The cached value of the '{@link #getUserInputTokens() <em>User Input Tokens</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserInputTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<ObjectTokenInstance> userInputTokens;

	/**
	 * The cached value of the '{@link #getInputParameterNode() <em>Input Parameter Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityParameterNode inputParameterNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserParameterInputImpl() {
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
	public EList<ObjectTokenInstance> getUserInputTokens() {
		if (userInputTokens == null) {
			userInputTokens = new EObjectContainmentEList<ObjectTokenInstance>(ObjectTokenInstance.class, this, TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS);
		}
		return userInputTokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNode getInputParameterNode() {
		if (inputParameterNode != null && inputParameterNode.eIsProxy()) {
			InternalEObject oldInputParameterNode = (InternalEObject)inputParameterNode;
			inputParameterNode = (ActivityParameterNode)eResolveProxy(oldInputParameterNode);
			if (inputParameterNode != oldInputParameterNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE, oldInputParameterNode, inputParameterNode));
			}
		}
		return inputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNode basicGetInputParameterNode() {
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS:
				return ((InternalEList<?>)getUserInputTokens()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS:
				return getUserInputTokens();
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				if (resolve) return getInputParameterNode();
				return basicGetInputParameterNode();
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
			case TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS:
				getUserInputTokens().clear();
				getUserInputTokens().addAll((Collection<? extends ObjectTokenInstance>)newValue);
				return;
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode((ActivityParameterNode)newValue);
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
			case TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS:
				getUserInputTokens().clear();
				return;
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode((ActivityParameterNode)null);
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
			case TracemodelPackage.USER_PARAMETER_INPUT__USER_INPUT_TOKENS:
				return userInputTokens != null && !userInputTokens.isEmpty();
			case TracemodelPackage.USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				return inputParameterNode != null;
		}
		return eDynamicIsSet(featureID);
	}

} //UserParameterInputImpl
