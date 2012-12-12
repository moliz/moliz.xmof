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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;

import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Token Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl#getValueInstance <em>Value Instance</em>}</li>
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
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getValueInstance() <em>Value Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueInstance()
	 * @generated
	 * @ordered
	 */
	protected ValueInstance valueInstance;

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
		return TracemodelPackageImpl.Literals.OBJECT_TOKEN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance getValueInstance() {
		return valueInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueInstance(ValueInstance newValueInstance, NotificationChain msgs) {
		ValueInstance oldValueInstance = valueInstance;
		valueInstance = newValueInstance;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueInstance(ValueInstance newValueInstance) {
		if (newValueInstance != valueInstance) {
			NotificationChain msgs = null;
			if (valueInstance != null)
				msgs = ((InternalEObject)valueInstance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE, null, msgs);
			if (newValueInstance != null)
				msgs = ((InternalEObject)newValueInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE, null, msgs);
			msgs = basicSetValueInstance(newValueInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE:
				return basicSetValueInstance(null, msgs);
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
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				return getTraversedEdges();
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE:
				return getValueInstance();
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
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
				getTraversedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
				return;
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE:
				setValueInstance((ValueInstance)newValue);
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
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				getTraversedEdges().clear();
				return;
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE:
				setValueInstance((ValueInstance)null);
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
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES:
				return traversedEdges != null && !traversedEdges.isEmpty();
			case TracemodelPackageImpl.OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE:
				return valueInstance != null;
		}
		return eDynamicIsSet(featureID);
	}

} //ObjectTokenInstanceImpl
