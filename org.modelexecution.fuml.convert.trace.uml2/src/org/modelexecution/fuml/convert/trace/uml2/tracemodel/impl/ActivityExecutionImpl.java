/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.Activity;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getUserParameterInputs <em>User Parameter Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getTrace <em>Trace</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityExecutionImpl#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityExecutionImpl extends EObjectImpl implements ActivityExecution {
	/**
	 * The cached value of the '{@link #getUserParameterInputs() <em>User Parameter Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserParameterInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<UserParameterInput> userParameterInputs;

	/**
	 * The cached value of the '{@link #getNodeExecutions() <em>Node Executions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> nodeExecutions;

	/**
	 * The cached value of the '{@link #getCaller() <em>Caller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaller()
	 * @generated
	 * @ordered
	 */
	protected CallActionExecution caller;

	/**
	 * The default value of the '{@link #getActivityExecutionID() <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExecutionID()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTIVITY_EXECUTION_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getActivityExecutionID() <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExecutionID()
	 * @generated
	 * @ordered
	 */
	protected int activityExecutionID = ACTIVITY_EXECUTION_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.ACTIVITY_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserParameterInput> getUserParameterInputs() {
		if (userParameterInputs == null) {
			userParameterInputs = new EObjectContainmentEList<UserParameterInput>(UserParameterInput.class, this, TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS);
		}
		return userParameterInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityNodeExecution> getNodeExecutions() {
		if (nodeExecutions == null) {
			nodeExecutions = new EObjectContainmentWithInverseEList<ActivityNodeExecution>(ActivityNodeExecution.class, this, TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS, TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION);
		}
		return nodeExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecution getCaller() {
		if (caller != null && caller.eIsProxy()) {
			InternalEObject oldCaller = (InternalEObject)caller;
			caller = (CallActionExecution)eResolveProxy(oldCaller);
			if (caller != oldCaller) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, oldCaller, caller));
			}
		}
		return caller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecution basicGetCaller() {
		return caller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaller(CallActionExecution newCaller, NotificationChain msgs) {
		CallActionExecution oldCaller = caller;
		caller = newCaller;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, oldCaller, newCaller);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaller(CallActionExecution newCaller) {
		if (newCaller != caller) {
			NotificationChain msgs = null;
			if (caller != null)
				msgs = ((InternalEObject)caller).eInverseRemove(this, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
			if (newCaller != null)
				msgs = ((InternalEObject)newCaller).eInverseAdd(this, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
			msgs = basicSetCaller(newCaller, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_EXECUTION__CALLER, newCaller, newCaller));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getActivityExecutionID() {
		return activityExecutionID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityExecutionID(int newActivityExecutionID) {
		int oldActivityExecutionID = activityExecutionID;
		activityExecutionID = newActivityExecutionID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID, oldActivityExecutionID, activityExecutionID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace getTrace() {
		if (eContainerFeatureID() != TracemodelPackage.ACTIVITY_EXECUTION__TRACE) return null;
		return (Trace)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrace(Trace newTrace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTrace, TracemodelPackage.ACTIVITY_EXECUTION__TRACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrace(Trace newTrace) {
		if (newTrace != eInternalContainer() || (eContainerFeatureID() != TracemodelPackage.ACTIVITY_EXECUTION__TRACE && newTrace != null)) {
			if (EcoreUtil.isAncestor(this, newTrace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTrace != null)
				msgs = ((InternalEObject)newTrace).eInverseAdd(this, TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS, Trace.class, msgs);
			msgs = basicSetTrace(newTrace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_EXECUTION__TRACE, newTrace, newTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getActivity() {
		if (activity != null && activity.eIsProxy()) {
			InternalEObject oldActivity = (InternalEObject)activity;
			activity = (Activity)eResolveProxy(oldActivity);
			if (activity != oldActivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY, oldActivity, activity));
			}
		}
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(Activity newActivity) {
		Activity oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY, oldActivity, activity));
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
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodeExecutions()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				if (caller != null)
					msgs = ((InternalEObject)caller).eInverseRemove(this, TracemodelPackage.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
				return basicSetCaller((CallActionExecution)otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTrace((Trace)otherEnd, msgs);
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
			case TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return ((InternalEList<?>)getUserParameterInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return ((InternalEList<?>)getNodeExecutions()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				return basicSetCaller(null, msgs);
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				return basicSetTrace(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				return eInternalContainer().eInverseRemove(this, TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS, Trace.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return getUserParameterInputs();
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return getNodeExecutions();
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				if (resolve) return getCaller();
				return basicGetCaller();
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				return getActivityExecutionID();
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				return getTrace();
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
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
			case TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				getUserParameterInputs().clear();
				getUserParameterInputs().addAll((Collection<? extends UserParameterInput>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				getNodeExecutions().clear();
				getNodeExecutions().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				setCaller((CallActionExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				setActivityExecutionID((Integer)newValue);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				setTrace((Trace)newValue);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY:
				setActivity((Activity)newValue);
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
			case TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				getUserParameterInputs().clear();
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				getNodeExecutions().clear();
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				setCaller((CallActionExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				setActivityExecutionID(ACTIVITY_EXECUTION_ID_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				setTrace((Trace)null);
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY:
				setActivity((Activity)null);
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
			case TracemodelPackage.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return userParameterInputs != null && !userParameterInputs.isEmpty();
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return nodeExecutions != null && !nodeExecutions.isEmpty();
			case TracemodelPackage.ACTIVITY_EXECUTION__CALLER:
				return caller != null;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				return activityExecutionID != ACTIVITY_EXECUTION_ID_EDEFAULT;
			case TracemodelPackage.ACTIVITY_EXECUTION__TRACE:
				return getTrace() != null;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY:
				return activity != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (activityExecutionID: ");
		result.append(activityExecutionID);
		result.append(')');
		return result.toString();
	}

} //ActivityExecutionImpl
