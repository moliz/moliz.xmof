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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.trace.uml2.tracemodel.ActionExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.Input;
import org.modelexecution.fuml.trace.uml2.tracemodel.Output;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ActionExecutionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ActionExecutionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ActionExecutionImpl#getIncomingControl <em>Incoming Control</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.ActionExecutionImpl#getOutgoingControl <em>Outgoing Control</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionExecutionImpl extends ActivityNodeExecutionImpl implements ActionExecution {
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
	protected ActionExecutionImpl() {
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
	public EList<Input> getInputs() {
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
	public EList<Output> getOutputs() {
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
	public EList<ControlTokenInstance> getIncomingControl() {
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
	public EList<ControlTokenInstance> getOutgoingControl() {
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
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				return ((InternalEList<?>)getOutgoingControl()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				return getIncomingControl();
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				return getOutgoingControl();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case TracemodelPackage.ACTION_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__INCOMING_CONTROL:
				return incomingControl != null && !incomingControl.isEmpty();
			case TracemodelPackage.ACTION_EXECUTION__OUTGOING_CONTROL:
				return outgoingControl != null && !outgoingControl.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActionExecutionImpl
