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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;

import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Token Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl#getTransportedValue <em>Transported Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectTokenInstanceImpl extends TokenInstanceImpl implements ObjectTokenInstance {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getTransportedValue() <em>Transported Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportedValue()
	 * @generated
	 * @ordered
	 */
	protected ValueInstance transportedValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.OBJECT_TOKEN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance getTransportedValue() {
		if (transportedValue != null && transportedValue.eIsProxy()) {
			InternalEObject oldTransportedValue = (InternalEObject)transportedValue;
			transportedValue = (ValueInstance)eResolveProxy(oldTransportedValue);
			if (transportedValue != oldTransportedValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE, oldTransportedValue, transportedValue));
			}
		}
		return transportedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance basicGetTransportedValue() {
		return transportedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransportedValue(ValueInstance newTransportedValue) {
		ValueInstance oldTransportedValue = transportedValue;
		transportedValue = newTransportedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE, oldTransportedValue, transportedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				return getTraversedEdges();
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				if (resolve) return getTransportedValue();
				return basicGetTransportedValue();
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
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
				getTraversedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
				return;
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				setTransportedValue((ValueInstance)newValue);
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
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
				return;
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				setTransportedValue((ValueInstance)null);
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
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				return traversedEdges != null && !traversedEdges.isEmpty();
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE:
				return transportedValue != null;
		}
		return eDynamicIsSet(featureID);
	}

} //ObjectTokenInstanceImpl
