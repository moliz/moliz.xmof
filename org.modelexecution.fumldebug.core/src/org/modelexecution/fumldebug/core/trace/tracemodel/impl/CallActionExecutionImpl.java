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
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Action Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl#getCallee <em>Callee</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallActionExecutionImpl extends ActionExecutionImpl implements CallActionExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getCallee() <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallee()
	 * @generated
	 * @ordered
	 */
	protected ActivityExecution callee;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.CALL_ACTION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution getCallee() {
		if (callee != null && callee.eIsProxy()) {
			InternalEObject oldCallee = (InternalEObject)callee;
			callee = (ActivityExecution)eResolveProxy(oldCallee);
			if (callee != oldCallee) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, oldCallee, callee));
			}
		}
		return callee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution basicGetCallee() {
		return callee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallee(ActivityExecution newCallee, NotificationChain msgs) {
		ActivityExecution oldCallee = callee;
		callee = newCallee;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, oldCallee, newCallee);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallee(ActivityExecution newCallee) {
		if (newCallee != callee) {
			NotificationChain msgs = null;
			if (callee != null)
				msgs = ((InternalEObject)callee).eInverseRemove(this, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, ActivityExecution.class, msgs);
			if (newCallee != null)
				msgs = ((InternalEObject)newCallee).eInverseAdd(this, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, ActivityExecution.class, msgs);
			msgs = basicSetCallee(newCallee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, newCallee, newCallee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalSuccessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalPredecessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (chronologicalSuccessor != null)
					msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalSuccessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (chronologicalPredecessor != null)
					msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalPredecessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActivityExecution((ActivityExecution)otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				if (callee != null)
					msgs = ((InternalEObject)callee).eInverseRemove(this, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, ActivityExecution.class, msgs);
				return basicSetCallee((ActivityExecution)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				return basicSetCallee(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.CALL_ACTION_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.CALL_ACTION_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.CALL_ACTION_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.CALL_ACTION_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				if (resolve) return getCallee();
				return basicGetCallee();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				setCallee((ActivityExecution)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				setCallee((ActivityExecution)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.CALL_ACTION_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.CALL_ACTION_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.CALL_ACTION_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.CALL_ACTION_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE:
				return callee != null;
		}
		return eDynamicIsSet(featureID);
	}

} //CallActionExecutionImpl
