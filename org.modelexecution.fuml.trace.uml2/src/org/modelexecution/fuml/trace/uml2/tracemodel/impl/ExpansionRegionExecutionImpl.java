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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput;
import org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionRegionExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expansion Region Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ExpansionRegionExecutionImpl#getExpansionInputs <em>Expansion Inputs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpansionRegionExecutionImpl extends StructuredActivityNodeExecutionImpl implements ExpansionRegionExecution {
	/**
	 * The cached value of the '{@link #getExpansionInputs() <em>Expansion Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpansionInput> expansionInputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpansionRegionExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.EXPANSION_REGION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpansionInput> getExpansionInputs() {
		if (expansionInputs == null) {
			expansionInputs = new EObjectContainmentEList<ExpansionInput>(ExpansionInput.class, this, TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS);
		}
		return expansionInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return ((InternalEList<?>)getExpansionInputs()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return getExpansionInputs();
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				getExpansionInputs().clear();
				getExpansionInputs().addAll((Collection<? extends ExpansionInput>)newValue);
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				getExpansionInputs().clear();
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return expansionInputs != null && !expansionInputs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExpansionRegionExecutionImpl
