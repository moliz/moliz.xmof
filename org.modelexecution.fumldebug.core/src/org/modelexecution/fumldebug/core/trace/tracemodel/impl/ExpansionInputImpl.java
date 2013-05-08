/**
 * Copyright (c) 2013 Vienna University of Technology.
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
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expansion Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl#getExpansionInputValues <em>Expansion Input Values</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl#getExpansionNode <em>Expansion Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpansionInputImpl extends EObjectImpl implements ExpansionInput {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	 * The default value of the '{@link #getExpansionNode() <em>Expansion Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionNode()
	 * @generated
	 * @ordered
	 */
	protected static final ExpansionNode EXPANSION_NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpansionNode() <em>Expansion Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionNode()
	 * @generated
	 * @ordered
	 */
	protected ExpansionNode expansionNode = EXPANSION_NODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionInputImpl() {
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
	public List<InputValue> getExpansionInputValues() {
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				return getExpansionInputValues();
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				return getExpansionNode();
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				getExpansionInputValues().clear();
				getExpansionInputValues().addAll((Collection<? extends InputValue>)newValue);
				return;
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				setExpansionNode((ExpansionNode)newValue);
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				getExpansionInputValues().clear();
				return;
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				setExpansionNode(EXPANSION_NODE_EDEFAULT);
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
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_INPUT_VALUES:
				return expansionInputValues != null && !expansionInputValues.isEmpty();
			case TracemodelPackage.EXPANSION_INPUT__EXPANSION_NODE:
				return EXPANSION_NODE_EDEFAULT == null ? expansionNode != null : !EXPANSION_NODE_EDEFAULT.equals(expansionNode);
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
		result.append(" (expansionNode: ");
		result.append(expansionNode);
		result.append(')');
		return result.toString();
	}

} //ExpansionInputImpl
