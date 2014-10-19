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

import org.eclipse.uml2.uml.ExpansionNode;

import org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput;
import org.modelexecution.fuml.trace.uml2.tracemodel.InputValue;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expansion Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ExpansionInputImpl#getExpansionInputValues <em>Expansion Input Values</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ExpansionInputImpl#getExpansionNode <em>Expansion Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpansionInputImpl extends MinimalEObjectImpl.Container implements ExpansionInput {
	/**
	 * The cached value of the '{@link #getExpansionInputValues() <em>Expansion Input Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionInputValues()
	 * @generated
	 * @ordered
	 */
	protected EList<InputValue> expansionInputValues;

	/**
	 * The cached value of the '{@link #getExpansionNode() <em>Expansion Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionNode()
	 * @generated
	 * @ordered
	 */
	protected ExpansionNode expansionNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpansionInputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.EXPANSION_INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InputValue> getExpansionInputValues() {
		if (expansionInputValues == null) {
			expansionInputValues = new EObjectContainmentEList<InputValue>(InputValue.class, this, TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES);
		}
		return expansionInputValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionNode getExpansionNode() {
		if (expansionNode != null && expansionNode.eIsProxy()) {
			InternalEObject oldExpansionNode = (InternalEObject)expansionNode;
			expansionNode = (ExpansionNode)eResolveProxy(oldExpansionNode);
			if (expansionNode != oldExpansionNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE, oldExpansionNode, expansionNode));
			}
		}
		return expansionNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionNode basicGetExpansionNode() {
		return expansionNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpansionNode(ExpansionNode newExpansionNode) {
		ExpansionNode oldExpansionNode = expansionNode;
		expansionNode = newExpansionNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE, oldExpansionNode, expansionNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				return ((InternalEList<?>)getExpansionInputValues()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				return getExpansionInputValues();
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				if (resolve) return getExpansionNode();
				return basicGetExpansionNode();
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				getExpansionInputValues().clear();
				getExpansionInputValues().addAll((Collection<? extends InputValue>)newValue);
				return;
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				setExpansionNode((ExpansionNode)newValue);
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				getExpansionInputValues().clear();
				return;
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				setExpansionNode((ExpansionNode)null);
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				return expansionInputValues != null && !expansionInputValues.isEmpty();
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				return expansionNode != null;
		}
		return super.eIsSet(featureID);
	}

} //ExpansionInputImpl
