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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.uml2.uml.ActivityEdge;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Token Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.TokenInstanceImpl#getTraversedEdges <em>Traversed Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TokenInstanceImpl extends EObjectImpl implements TokenInstance {
	/**
	 * The cached value of the '{@link #getTraversedEdges() <em>Traversed Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraversedEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityEdge> traversedEdges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TokenInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.TOKEN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityEdge> getTraversedEdges() {
		if (traversedEdges == null) {
			traversedEdges = new EObjectResolvingEList<ActivityEdge>(ActivityEdge.class, this, TracemodelPackage.TOKEN_INSTANCE__TRAVERSED_EDGES);
		}
		return traversedEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.TOKEN_INSTANCE__TRAVERSED_EDGES:
				return getTraversedEdges();
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
			case TracemodelPackage.TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
				getTraversedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
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
			case TracemodelPackage.TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
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
			case TracemodelPackage.TOKEN_INSTANCE__TRAVERSED_EDGES:
				return traversedEdges != null && !traversedEdges.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //TokenInstanceImpl
