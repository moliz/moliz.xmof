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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Initial Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.InitialNodeExecutionImpl#getOutgoingControl <em>Outgoing Control</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InitialNodeExecutionImpl extends ControlNodeExecutionImpl implements InitialNodeExecution {
	/**
	 * The cached value of the '{@link #getOutgoingControl() <em>Outgoing Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingControl()
	 * @generated
	 * @ordered
	 */
	protected ControlTokenInstance outgoingControl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InitialNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.INITIAL_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlTokenInstance getOutgoingControl() {
		return outgoingControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutgoingControl(ControlTokenInstance newOutgoingControl, NotificationChain msgs) {
		ControlTokenInstance oldOutgoingControl = outgoingControl;
		outgoingControl = newOutgoingControl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL, oldOutgoingControl, newOutgoingControl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutgoingControl(ControlTokenInstance newOutgoingControl) {
		if (newOutgoingControl != outgoingControl) {
			NotificationChain msgs = null;
			if (outgoingControl != null)
				msgs = ((InternalEObject)outgoingControl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL, null, msgs);
			if (newOutgoingControl != null)
				msgs = ((InternalEObject)newOutgoingControl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL, null, msgs);
			msgs = basicSetOutgoingControl(newOutgoingControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL, newOutgoingControl, newOutgoingControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return basicSetOutgoingControl(null, msgs);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				setOutgoingControl((ControlTokenInstance)newValue);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				setOutgoingControl((ControlTokenInstance)null);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null;
		}
		return super.eIsSet(featureID);
	}

} //InitialNodeExecutionImpl
