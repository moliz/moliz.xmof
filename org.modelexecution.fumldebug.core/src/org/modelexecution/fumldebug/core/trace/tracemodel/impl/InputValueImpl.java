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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl#getInputObjectToken <em>Input Object Token</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl#getInputValueSnapshot <em>Input Value Snapshot</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputValueImpl extends EObjectImpl implements InputValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getInputObjectToken() <em>Input Object Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputObjectToken()
	 * @generated
	 * @ordered
	 */
	protected ObjectTokenInstance inputObjectToken;

	/**
	 * The cached value of the '{@link #getInputValueSnapshot() <em>Input Value Snapshot</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputValueSnapshot()
	 * @generated
	 * @ordered
	 */
	protected ValueSnapshot inputValueSnapshot;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INPUT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance getInputObjectToken() {
		if (inputObjectToken != null && inputObjectToken.eIsProxy()) {
			InternalEObject oldInputObjectToken = (InternalEObject)inputObjectToken;
			inputObjectToken = (ObjectTokenInstance)eResolveProxy(oldInputObjectToken);
			if (inputObjectToken != oldInputObjectToken) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN, oldInputObjectToken, inputObjectToken));
			}
		}
		return inputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance basicGetInputObjectToken() {
		return inputObjectToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputObjectToken(ObjectTokenInstance newInputObjectToken) {
		ObjectTokenInstance oldInputObjectToken = inputObjectToken;
		inputObjectToken = newInputObjectToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN, oldInputObjectToken, inputObjectToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot getInputValueSnapshot() {
		if (inputValueSnapshot != null && inputValueSnapshot.eIsProxy()) {
			InternalEObject oldInputValueSnapshot = (InternalEObject)inputValueSnapshot;
			inputValueSnapshot = (ValueSnapshot)eResolveProxy(oldInputValueSnapshot);
			if (inputValueSnapshot != oldInputValueSnapshot) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT, oldInputValueSnapshot, inputValueSnapshot));
			}
		}
		return inputValueSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot basicGetInputValueSnapshot() {
		return inputValueSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputValueSnapshot(ValueSnapshot newInputValueSnapshot) {
		ValueSnapshot oldInputValueSnapshot = inputValueSnapshot;
		inputValueSnapshot = newInputValueSnapshot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT, oldInputValueSnapshot, inputValueSnapshot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				if (resolve) return getInputObjectToken();
				return basicGetInputObjectToken();
			case TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT:
				if (resolve) return getInputValueSnapshot();
				return basicGetInputValueSnapshot();
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				setInputObjectToken((ObjectTokenInstance)newValue);
				return;
			case TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT:
				setInputValueSnapshot((ValueSnapshot)newValue);
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				setInputObjectToken((ObjectTokenInstance)null);
				return;
			case TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT:
				setInputValueSnapshot((ValueSnapshot)null);
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
			case TracemodelPackage.INPUT_VALUE__INPUT_OBJECT_TOKEN:
				return inputObjectToken != null;
			case TracemodelPackage.INPUT_VALUE__INPUT_VALUE_SNAPSHOT:
				return inputValueSnapshot != null;
		}
		return eDynamicIsSet(featureID);
	}

} //InputValueImpl
