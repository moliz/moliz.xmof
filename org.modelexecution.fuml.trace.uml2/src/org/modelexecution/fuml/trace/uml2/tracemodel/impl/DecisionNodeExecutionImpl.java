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

import org.modelexecution.fuml.trace.uml2.tracemodel.DecisionNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.InputValue;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decision Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.DecisionNodeExecutionImpl#getDecisionInputValue <em>Decision Input Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecisionNodeExecutionImpl extends ControlNodeExecutionImpl implements DecisionNodeExecution {
	/**
	 * The cached value of the '{@link #getDecisionInputValue() <em>Decision Input Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecisionInputValue()
	 * @generated
	 * @ordered
	 */
	protected InputValue decisionInputValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.DECISION_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputValue getDecisionInputValue() {
		return decisionInputValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDecisionInputValue(InputValue newDecisionInputValue, NotificationChain msgs) {
		InputValue oldDecisionInputValue = decisionInputValue;
		decisionInputValue = newDecisionInputValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, oldDecisionInputValue, newDecisionInputValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecisionInputValue(InputValue newDecisionInputValue) {
		if (newDecisionInputValue != decisionInputValue) {
			NotificationChain msgs = null;
			if (decisionInputValue != null)
				msgs = ((InternalEObject)decisionInputValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, null, msgs);
			if (newDecisionInputValue != null)
				msgs = ((InternalEObject)newDecisionInputValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, null, msgs);
			msgs = basicSetDecisionInputValue(newDecisionInputValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, newDecisionInputValue, newDecisionInputValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return basicSetDecisionInputValue(null, msgs);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return getDecisionInputValue();
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				setDecisionInputValue((InputValue)newValue);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				setDecisionInputValue((InputValue)null);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return decisionInputValue != null;
		}
		return super.eIsSet(featureID);
	}

} //DecisionNodeExecutionImpl
