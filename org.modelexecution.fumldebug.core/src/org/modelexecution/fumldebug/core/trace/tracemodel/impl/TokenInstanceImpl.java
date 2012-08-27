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

import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Token Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl#getTraversedEdge <em>Traversed Edge</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TokenInstanceImpl extends EObjectImpl implements TokenInstance {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The default value of the '{@link #getTraversedEdge() <em>Traversed Edge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraversedEdge()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityEdge TRAVERSED_EDGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTraversedEdge() <em>Traversed Edge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraversedEdge()
	 * @generated
	 * @ordered
	 */
	protected ActivityEdge traversedEdge = TRAVERSED_EDGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TokenInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackageImpl.Literals.TOKEN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityEdge getTraversedEdge() {
		return traversedEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTraversedEdge(ActivityEdge newTraversedEdge) {
		traversedEdge = newTraversedEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackageImpl.TOKEN_INSTANCE__TRAVERSED_EDGE:
				return getTraversedEdge();
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
			case TracemodelPackageImpl.TOKEN_INSTANCE__TRAVERSED_EDGE:
				setTraversedEdge((ActivityEdge)newValue);
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
			case TracemodelPackageImpl.TOKEN_INSTANCE__TRAVERSED_EDGE:
				setTraversedEdge(TRAVERSED_EDGE_EDEFAULT);
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
			case TracemodelPackageImpl.TOKEN_INSTANCE__TRAVERSED_EDGE:
				return TRAVERSED_EDGE_EDEFAULT == null ? traversedEdge != null : !TRAVERSED_EDGE_EDEFAULT.equals(traversedEdge);
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
		result.append(" (traversedEdge: ");
		result.append(traversedEdge);
		result.append(')');
		return result.toString();
	}

} //TokenInstanceImpl
