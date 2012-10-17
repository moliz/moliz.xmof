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

import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;

import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl#getParameterOutputTokens <em>Parameter Output Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl#getOutputParameterNode <em>Output Parameter Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterOutputImpl extends EObjectImpl implements ParameterOutput {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getParameterOutputTokens() <em>Parameter Output Tokens</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterOutputTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<ObjectTokenInstance> parameterOutputTokens;

	/**
	 * The default value of the '{@link #getOutputParameterNode() <em>Output Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityParameterNode OUTPUT_PARAMETER_NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputParameterNode() <em>Output Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityParameterNode outputParameterNode = OUTPUT_PARAMETER_NODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterOutputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackageImpl.Literals.PARAMETER_OUTPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ObjectTokenInstance> getParameterOutputTokens() {
		if (parameterOutputTokens == null) {
			parameterOutputTokens = new BasicInternalEList<ObjectTokenInstance>(ObjectTokenInstance.class);
		}
		return parameterOutputTokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNode getOutputParameterNode() {
		return outputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputParameterNode(ActivityParameterNode newOutputParameterNode) {
		outputParameterNode = newOutputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackageImpl.PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS:
				return getParameterOutputTokens();
			case TracemodelPackageImpl.PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE:
				return getOutputParameterNode();
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
			case TracemodelPackageImpl.PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS:
				getParameterOutputTokens().clear();
				getParameterOutputTokens().addAll((Collection<? extends ObjectTokenInstance>)newValue);
				return;
			case TracemodelPackageImpl.PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE:
				setOutputParameterNode((ActivityParameterNode)newValue);
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
			case TracemodelPackageImpl.PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS:
				getParameterOutputTokens().clear();
				return;
			case TracemodelPackageImpl.PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE:
				setOutputParameterNode(OUTPUT_PARAMETER_NODE_EDEFAULT);
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
			case TracemodelPackageImpl.PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS:
				return parameterOutputTokens != null && !parameterOutputTokens.isEmpty();
			case TracemodelPackageImpl.PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE:
				return OUTPUT_PARAMETER_NODE_EDEFAULT == null ? outputParameterNode != null : !OUTPUT_PARAMETER_NODE_EDEFAULT.equals(outputParameterNode);
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
		result.append(" (outputParameterNode: ");
		result.append(outputParameterNode);
		result.append(')');
		return result.toString();
	}

} //ParameterOutputImpl
