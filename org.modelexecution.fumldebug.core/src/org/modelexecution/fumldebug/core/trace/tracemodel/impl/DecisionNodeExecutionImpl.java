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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decision Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getDecisionInputValue <em>Decision Input Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecisionNodeExecutionImpl extends ControlNodeExecutionImpl implements DecisionNodeExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getDecisionInputValue() <em>Decision Input Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecisionInputValue()
	 * @generated
	 * @ordered
	 */
	protected InputValue decisionInputValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.DECISION_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputValue getDecisionInputValue() {
		return decisionInputValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDecisionInputValue(InputValue newDecisionInputValue, NotificationChain msgs) {
		InputValue oldDecisionInputValue = decisionInputValue;
		decisionInputValue = newDecisionInputValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, oldDecisionInputValue, newDecisionInputValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecisionInputValue(InputValue newDecisionInputValue) {
		if (newDecisionInputValue != decisionInputValue) {
			NotificationChain msgs = null;
			if (decisionInputValue != null)
				msgs = ((InternalEObject)decisionInputValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, null, msgs);
			if (newDecisionInputValue != null)
				msgs = ((InternalEObject)newDecisionInputValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, null, msgs);
			msgs = basicSetDecisionInputValue(newDecisionInputValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE, newDecisionInputValue, newDecisionInputValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return basicSetDecisionInputValue(null, msgs);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.DECISION_NODE_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.DECISION_NODE_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.DECISION_NODE_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				return getRoutedTokens();
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return getDecisionInputValue();
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				getRoutedTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				setDecisionInputValue((InputValue)newValue);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				setDecisionInputValue((InputValue)null);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.DECISION_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.DECISION_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.DECISION_NODE_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				return routedTokens != null && !routedTokens.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return decisionInputValue != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public boolean consumedTokenInstance(TokenInstance tokenInstance) {
		if(this.getDecisionInputValue() != null) {
			if(this.getDecisionInputValue().getInputObjectToken().equals(tokenInstance)) {
				return true;
			}
		}
		return super.providedTokenInstance(tokenInstance);
	}

	@Override
	public List<TokenInstance> getIncomingTokens() {
		List<TokenInstance> incomingTokens = new ArrayList<TokenInstance>();
		incomingTokens.addAll(super.getIncomingTokens());
		if(this.getDecisionInputValue() != null) {
			incomingTokens.add(this.getDecisionInputValue().getInputObjectToken());
		}		
		return incomingTokens;
	}

	@Override
	public List<TokenInstance> getOutgoingTokens() {
		return super.getIncomingTokens();
	}

} //DecisionNodeExecutionImpl
