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
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityParameterNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Parameter Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityParameterNodeExecutionImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityParameterNodeExecutionImpl#getProvidedValues <em>Provided Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityParameterNodeExecutionImpl extends ActivityNodeExecutionImpl implements ActivityParameterNodeExecution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getInput() <em>Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInput()
	 * @generated
	 * @ordered
	 */
	protected Input input;

	/**
	 * The cached value of the '{@link #getProvidedValues() <em>Provided Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputValue> providedValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.ACTIVITY_PARAMETER_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Input getInput() {
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInput(Input newInput, NotificationChain msgs) {
		Input oldInput = input;
		input = newInput;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT, oldInput, newInput);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInput(Input newInput) {
		if (newInput != input) {
			NotificationChain msgs = null;
			if (input != null)
				msgs = ((InternalEObject)input).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT, null, msgs);
			if (newInput != null)
				msgs = ((InternalEObject)newInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT, null, msgs);
			msgs = basicSetInput(newInput, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT, newInput, newInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<OutputValue> getProvidedValues() {
		if (providedValues == null) {
			providedValues = new EObjectContainmentEList<OutputValue>(OutputValue.class, this, TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES);
		}
		return providedValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT:
				return basicSetInput(null, msgs);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES:
				return ((InternalEList<?>)getProvidedValues()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__UNDER_EXECUTION:
				return isUnderExecution();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT:
				return getInput();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES:
				return getProvidedValues();
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
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution((Boolean)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT:
				setInput((Input)newValue);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES:
				getProvidedValues().clear();
				getProvidedValues().addAll((Collection<? extends OutputValue>)newValue);
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
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__UNDER_EXECUTION:
				setUnderExecution(UNDER_EXECUTION_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT:
				setInput((Input)null);
				return;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES:
				getProvidedValues().clear();
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
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__UNDER_EXECUTION:
				return underExecution != UNDER_EXECUTION_EDEFAULT;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT:
				return input != null;
			case TracemodelPackage.ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES:
				return providedValues != null && !providedValues.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //ActivityParameterNodeExecutionImpl
