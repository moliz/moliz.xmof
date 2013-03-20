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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
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
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getIncomingControl <em>Incoming Control</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getOutgoingControl <em>Outgoing Control</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl#getCallee <em>Callee</em>}</li>
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
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	 * The cached value of the '{@link #getCallee() <em>Callee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallee()
	 * @generated
	 * @ordered
	 */
	protected ActivityExecution callee;

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
	public List<Input> getInputs() {
		if (inputs == null) {
			inputs = new EObjectContainmentEList<Input>(Input.class, this, TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS);
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
			outputs = new EObjectContainmentEList<Output>(Output.class, this, TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS);
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
			incomingControl = new EObjectResolvingEList<ControlTokenInstance>(ControlTokenInstance.class, this, TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL);
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
			outgoingControl = new EObjectContainmentEList<ControlTokenInstance>(ControlTokenInstance.class, this, TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL);
		}
		return outgoingControl;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE, oldCallee, callee));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE, oldCallee, newCallee);
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
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE, newCallee, newCallee));
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalSuccessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalPredecessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (chronologicalSuccessor != null)
					msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalSuccessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (chronologicalPredecessor != null)
					msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalPredecessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActivityExecution((ActivityExecution)otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL:
				return ((InternalEList<?>)getOutgoingControl()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
				return basicSetCallee(null, msgs);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				return getRoutedTokens();
			case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL:
				return getIncomingControl();
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
				if (resolve) return getCallee();
				return basicGetCallee();
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				getRoutedTokens().addAll((Collection<? extends TokenInstance>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				getIncomingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				getOutgoingControl().addAll((Collection<? extends ControlTokenInstance>)newValue);
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
				setCallee((ActivityExecution)newValue);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				getRoutedTokens().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL:
				getIncomingControl().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL:
				getOutgoingControl().clear();
				return;
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
				setCallee((ActivityExecution)null);
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
			case TracemodelPackage.DECISION_NODE_EXECUTION__ROUTED_TOKENS:
				return routedTokens != null && !routedTokens.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL:
				return incomingControl != null && !incomingControl.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null && !outgoingControl.isEmpty();
			case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE:
				return callee != null;
			case TracemodelPackage.DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE:
				return decisionInputValue != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ActionExecution.class) {
			switch (derivedFeatureID) {
				case TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS: return TracemodelPackage.ACTION_EXECUTION__INPUTS;
				case TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS: return TracemodelPackage.ACTION_EXECUTION__OUTPUTS;
				case TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL: return TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL;
				case TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL: return TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == CallActionExecution.class) {
			switch (derivedFeatureID) {
				case TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE: return TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ActionExecution.class) {
			switch (baseFeatureID) {
				case TracemodelPackage.ACTION_EXECUTION__INPUTS: return TracemodelPackage.DECISION_NODE_EXECUTION__INPUTS;
				case TracemodelPackage.ACTION_EXECUTION__OUTPUTS: return TracemodelPackage.DECISION_NODE_EXECUTION__OUTPUTS;
				case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL: return TracemodelPackage.DECISION_NODE_EXECUTION__INCOMING_CONTROL;
				case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL: return TracemodelPackage.DECISION_NODE_EXECUTION__OUTGOING_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == CallActionExecution.class) {
			switch (baseFeatureID) {
				case TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE: return TracemodelPackage.DECISION_NODE_EXECUTION__CALLEE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //DecisionNodeExecutionImpl
