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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structured Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.StructuredActivityNodeExecutionImpl#getNestedNodeExecutions <em>Nested Node Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructuredActivityNodeExecutionImpl extends ActionExecutionImpl implements StructuredActivityNodeExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getNestedNodeExecutions() <em>Nested Node Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestedNodeExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> nestedNodeExecutions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredActivityNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.STRUCTURED_ACTIVITY_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ActivityNodeExecution> getNestedNodeExecutions() {
		if (nestedNodeExecutions == null) {
			nestedNodeExecutions = new EObjectResolvingEList<ActivityNodeExecution>(ActivityNodeExecution.class, this, TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS);
		}
		return nestedNodeExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL:
				return getIncomingControl();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				return getNestedNodeExecutions();
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				getIncomingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				getOutgoingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
				getNestedNodeExecutions().addAll((Collection<? extends ActivityNodeExecution>)newValue);
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				return;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				getNestedNodeExecutions().clear();
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
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL:
				return incomingControl != null && !incomingControl.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null && !outgoingControl.isEmpty();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS:
				return nestedNodeExecutions != null && !nestedNodeExecutions.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //StructuredActivityNodeExecutionImpl
