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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.StructuredActivityNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structured Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.StructuredActivityNodeExecutionImpl#getNestedNodeExecutions <em>Nested Node Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructuredActivityNodeExecutionImpl extends ActionExecutionImpl implements StructuredActivityNodeExecution {
	/**
	 * The cached value of the '{@link #getNestedNodeExecutions() <em>Nested Node Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestedNodeExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> nestedNodeExecutions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuredActivityNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.STRUCTURED_ACTIVITY_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityNodeExecution> getNestedNodeExecutions() {
		if (nestedNodeExecutions == null) {
			nestedNodeExecutions = new EObjectResolvingEList<ActivityNodeExecution>(ActivityNodeExecution.class, this, TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS);
		}
		return nestedNodeExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				return getNestedNodeExecutions();
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
				getNestedNodeExecutions().addAll((Collection<? extends ActivityNodeExecution>)newValue);
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				return nestedNodeExecutions != null && !nestedNodeExecutions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StructuredActivityNodeExecutionImpl
