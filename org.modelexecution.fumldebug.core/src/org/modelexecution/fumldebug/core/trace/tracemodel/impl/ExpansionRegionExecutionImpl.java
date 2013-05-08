/**
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expansion Region Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionRegionExecutionImpl#getExpansionInputs <em>Expansion Inputs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpansionRegionExecutionImpl extends StructuredActivityNodeExecutionImpl implements ExpansionRegionExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getExpansionInputs() <em>Expansion Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpansionInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpansionInput> expansionInputs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionRegionExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.EXPANSION_REGION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ExpansionInput> getExpansionInputs() {
		if (expansionInputs == null) {
			expansionInputs = new EObjectContainmentEList<ExpansionInput>(ExpansionInput.class, this, TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS);
		}
		return expansionInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL:
				return ((InternalEList<?>)getOutgoingControl()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return ((InternalEList<?>)getExpansionInputs()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INCOMING_CONTROL:
				return getIncomingControl();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NESTED_NODE_EXECUTIONS:
				return getNestedNodeExecutions();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return getExpansionInputs();
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				getIncomingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				getOutgoingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
				getNestedNodeExecutions().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				getExpansionInputs().clear();
				getExpansionInputs().addAll((Collection<? extends ExpansionInput>)newValue);
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
				return;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				getExpansionInputs().clear();
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
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__INCOMING_CONTROL:
				return incomingControl != null && !incomingControl.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null && !outgoingControl.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__NESTED_NODE_EXECUTIONS:
				return nestedNodeExecutions != null && !nestedNodeExecutions.isEmpty();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS:
				return expansionInputs != null && !expansionInputs.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public List<TokenInstance> getIncomingTokens() {
		List<TokenInstance> incomingTokens = super.getIncomingTokens();
		
		for(ExpansionInput expansionInput : this.getExpansionInputs()) {
			for(InputValue inputValue : expansionInput.getExpansionInputValues()) {
				incomingTokens.add(inputValue.getInputObjectToken());
			}
		}
		
		return incomingTokens;
	}

} //ExpansionRegionExecutionImpl
