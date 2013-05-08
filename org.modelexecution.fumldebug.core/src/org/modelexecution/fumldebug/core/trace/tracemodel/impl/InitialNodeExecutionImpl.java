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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Initial Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InitialNodeExecutionImpl#getOutgoingControl <em>Outgoing Control</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InitialNodeExecutionImpl extends ControlNodeExecutionImpl implements InitialNodeExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	public InitialNodeExecutionImpl() {
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
	 * @generated not
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
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL, newOutgoingControl, newOutgoingControl));
		}
		
		if(!this.routedTokens.contains(newOutgoingControl)) {
			this.routedTokens.add(newOutgoingControl);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return basicSetOutgoingControl(null, msgs);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ROUTED_TOKENS:
				return getRoutedTokens();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				getRoutedTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				setOutgoingControl((ControlTokenInstance)newValue);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				return;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				setOutgoingControl((ControlTokenInstance)null);
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
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.INITIAL_NODE_EXECUTION__ROUTED_TOKENS:
				return routedTokens != null && !routedTokens.isEmpty();
			case TracemodelPackage.INITIAL_NODE_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null;
		}
		return eDynamicIsSet(featureID);
	}
	
} //InitialNodeExecutionImpl
