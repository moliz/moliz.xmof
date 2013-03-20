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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl#getActivityExecutions <em>Activity Executions</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl#getValueInstances <em>Value Instances</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl#getLocusValueInstances <em>Locus Value Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceImpl extends EObjectImpl implements Trace {
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

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
	 * The cached value of the '{@link #getLocusValueInstances() <em>Locus Value Instances</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocusValueInstances()
	 * @generated
	 * @ordered
	 */
	protected ValueInstance locusValueInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceImpl() {
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
	public List<ActivityExecution> getActivityExecutions() {
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
	public List<ValueInstance> getValueInstances() {
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
	public ValueInstance getLocusValueInstances() {
		if (locusValueInstances != null && locusValueInstances.eIsProxy()) {
			InternalEObject oldLocusValueInstances = (InternalEObject)locusValueInstances;
			locusValueInstances = (ValueInstance)eResolveProxy(oldLocusValueInstances);
			if (locusValueInstances != oldLocusValueInstances) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES, oldLocusValueInstances, locusValueInstances));
			}
		}
		return locusValueInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance basicGetLocusValueInstances() {
		return locusValueInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocusValueInstances(ValueInstance newLocusValueInstances) {
		ValueInstance oldLocusValueInstances = locusValueInstances;
		locusValueInstances = newLocusValueInstances;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES, oldLocusValueInstances, locusValueInstances));
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return ((InternalEList<?>)getActivityExecutions()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return ((InternalEList<?>)getValueInstances()).basicRemove(otherEnd, msgs);
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return getActivityExecutions();
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return getValueInstances();
			case TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES:
				if (resolve) return getLocusValueInstances();
				return basicGetLocusValueInstances();
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				getActivityExecutions().clear();
				getActivityExecutions().addAll((Collection<? extends ActivityExecution>)newValue);
				return;
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				getValueInstances().clear();
				getValueInstances().addAll((Collection<? extends ValueInstance>)newValue);
				return;
			case TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES:
				setLocusValueInstances((ValueInstance)newValue);
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				getActivityExecutions().clear();
				return;
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				getValueInstances().clear();
				return;
			case TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES:
				setLocusValueInstances((ValueInstance)null);
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
			case TracemodelPackage.TRACE__ACTIVITY_EXECUTIONS:
				return activityExecutions != null && !activityExecutions.isEmpty();
			case TracemodelPackage.TRACE__VALUE_INSTANCES:
				return valueInstances != null && !valueInstances.isEmpty();
			case TracemodelPackage.TRACE__LOCUS_VALUE_INSTANCES:
				return locusValueInstances != null;
		}
		return eDynamicIsSet(featureID);
	}

	public ActivityExecution getActivityExecutionByID(int activityExecutionID) {
		List<ActivityExecution> activityExecutions = this.getActivityExecutions();		
		ActivityExecution activityExecution = null;		
		for(int i=0;i<activityExecutions.size();++i) {
			if(activityExecutions.get(i).getActivityExecutionID() == activityExecutionID) {
				activityExecution = activityExecutions.get(i);
				break;
			}
		}
		return activityExecution;
	}

	public ActivityExecution addActivityExecution(Activity activity, int activityExecutionID) {
		ActivityExecution activityExecution = new ActivityExecutionImpl();
		activityExecution.setActivity(activity);		
		activityExecution.setActivityExecutionID(activityExecutionID);				
		this.getActivityExecutions().add(activityExecution);
		return activityExecution;
	}

	public ActivityNodeExecution getLastActivityNodeExecution() {
		for(ActivityExecution activityExecution : this.activityExecutions) {
			for(ActivityNodeExecution nodeExecution : activityExecution.getNodeExecutions()) {
				if(nodeExecution.isExecuted()) {
					if(nodeExecution.getChronologicalSuccessor() == null) {
						return nodeExecution;
					}
				}
			}
		}
		return null;
	}


} //TraceImpl
