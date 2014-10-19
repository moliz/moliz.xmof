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

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.Trace;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.TraceImpl#getActivityExecutions <em>Activity Executions</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.TraceImpl#getValueInstances <em>Value Instances</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.impl.TraceImpl#getInitialLocusValueInstances <em>Initial Locus Value Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceImpl extends MinimalEObjectImpl.Container implements Trace {
	/**
	 * The cached value of the '{@link #getActivityExecutions() <em>Activity Executions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityExecution> activityExecutions;

	/**
	 * The cached value of the '{@link #getValueInstances() <em>Value Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueInstance> valueInstances;

	/**
	 * The cached value of the '{@link #getInitialLocusValueInstances() <em>Initial Locus Value Instances</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialLocusValueInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueInstance> initialLocusValueInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityExecution> getActivityExecutions() {
		if (activityExecutions == null) {
			activityExecutions = new EObjectContainmentWithInverseEList<ActivityExecution>(ActivityExecution.class, this, TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS, TracemodelPackage.ACTIVITY_EXECUTION__TRACE);
		}
		return activityExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueInstance> getValueInstances() {
		if (valueInstances == null) {
			valueInstances = new EObjectContainmentEList<ValueInstance>(ValueInstance.class, this, TracemodelPackage.TRACE__VALUE_INSTANCES);
		}
		return valueInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueInstance> getInitialLocusValueInstances() {
		if (initialLocusValueInstances == null) {
			initialLocusValueInstances = new EObjectResolvingEList<ValueInstance>(ValueInstance.class, this, TracemodelPackage.TRACE__INITIAL_LOCUS_VALUE_INSTANCES);
		}
		return initialLocusValueInstances;
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getActivityExecutions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return ((InternalEList<?>)getActivityExecutions()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return ((InternalEList<?>)getValueInstances()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return getActivityExecutions();
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return getValueInstances();
			case TracemodelPackage.TRACE__INITIAL_LOCUS_VALUE_INSTANCES:
				return getInitialLocusValueInstances();
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				getActivityExecutions().clear();
				getActivityExecutions().addAll((Collection<? extends ActivityExecution>)newValue);
				return;
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				getValueInstances().clear();
				getValueInstances().addAll((Collection<? extends ValueInstance>)newValue);
				return;
			case TracemodelPackage.TRACE__INITIAL_LOCUS_VALUE_INSTANCES:
				getInitialLocusValueInstances().clear();
				getInitialLocusValueInstances().addAll((Collection<? extends ValueInstance>)newValue);
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				getActivityExecutions().clear();
				return;
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				getValueInstances().clear();
				return;
			case TracemodelPackage.TRACE__INITIAL_LOCUS_VALUE_INSTANCES:
				getInitialLocusValueInstances().clear();
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return activityExecutions != null && !activityExecutions.isEmpty();
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return valueInstances != null && !valueInstances.isEmpty();
			case TracemodelPackage.TRACE__INITIAL_LOCUS_VALUE_INSTANCES:
				return initialLocusValueInstances != null && !initialLocusValueInstances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TraceImpl
