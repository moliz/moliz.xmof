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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl#getIncomingControl <em>Incoming Control</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl#getOutgoingControl <em>Outgoing Control</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionExecutionImpl extends ActivityNodeExecutionImpl implements ActionExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<Input> inputs;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<Output> outputs;

	/**
	 * The cached value of the '{@link #getIncomingControl() <em>Incoming Control</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingControl()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlTokenInstance> incomingControl;

	/**
	 * The cached value of the '{@link #getOutgoingControl() <em>Outgoing Control</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingControl()
	 * @generated
	 * @ordered
	 */
	protected EList<ControlTokenInstance> outgoingControl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.ACTION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Input> getInputs() {
		if (inputs == null) {
			inputs = new EObjectContainmentEList<Input>(Input.class, this, TracemodelPackage.ACTION_EXECUTION__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Output> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectContainmentEList<Output>(Output.class, this, TracemodelPackage.ACTION_EXECUTION__OUTPUTS);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ControlTokenInstance> getIncomingControl() {
		if (incomingControl == null) {
			incomingControl = new EObjectResolvingEList<ControlTokenInstance>(ControlTokenInstance.class, this, TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL);
		}
		return incomingControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ControlTokenInstance> getOutgoingControl() {
		if (outgoingControl == null) {
			outgoingControl = new EObjectContainmentEList<ControlTokenInstance>(ControlTokenInstance.class, this, TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL);
		}
		return outgoingControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				return ((InternalEList<?>)getOutgoingControl()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.ACTION_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.ACTION_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.ACTION_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				return getIncomingControl();
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
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
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				getIncomingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				getOutgoingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
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
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTION_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.ACTION_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.ACTION_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.ACTION_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				return;
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
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
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.ACTION_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.ACTION_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.ACTION_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.ACTION_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				return incomingControl != null && !incomingControl.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null && !outgoingControl.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}
	
	public boolean providedTokenInstance(TokenInstance tokenInstance) {
		if(this.getOutgoingControl().contains(tokenInstance)) {
			return true;
		}
		for(Output output : this.getOutputs()) {
			for(OutputValue outputValue : output.getOutputValues()) {
				if(outputValue.getOutputObjectToken().equals(tokenInstance)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean consumedTokenInstance(TokenInstance tokenInstance) {
		if(this.getIncomingControl().contains(tokenInstance)) {
			return true;
		}
		for(Input input : this.getInputs()) {
			for(InputValue inputValue : input.getInputValues()) {
				if(inputValue.getInputObjectToken().equals(tokenInstance)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<TokenInstance> getIncomingTokens() {
		List<TokenInstance> incomingTokens = new ArrayList<TokenInstance>();
		incomingTokens.addAll(this.getIncomingControl());
		for(Input input : this.getInputs()) {
			for(InputValue inputValue : input.getInputValues()) {
				incomingTokens.add(inputValue.getInputObjectToken());
			}
		}
		return incomingTokens;
	}
		
	public List<TokenInstance> getOutgoingTokens() {
		List<TokenInstance> outgoingTokens = new ArrayList<TokenInstance>();
		outgoingTokens.addAll(this.getOutgoingControl());
		for(Output output : this.getOutputs()) {
			for(OutputValue outputValue : output.getOutputValues()) {
				outgoingTokens.add(outputValue.getOutputObjectToken());
			}
		}

		return outgoingTokens;
	}
			
} //ActionExecutionImpl
