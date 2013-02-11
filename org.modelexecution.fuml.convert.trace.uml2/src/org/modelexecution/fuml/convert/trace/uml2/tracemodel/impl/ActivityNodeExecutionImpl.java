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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.ActivityNode;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution;
import org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalSuccessor <em>Logical Successor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalPredecessor <em>Logical Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalSuccessor <em>Chronological Successor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalPredecessor <em>Chronological Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#isExecuted <em>Executed</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl.ActivityNodeExecutionImpl#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityNodeExecutionImpl extends EObjectImpl implements ActivityNodeExecution {
	/**
	 * The cached value of the '{@link #getLogicalSuccessor() <em>Logical Successor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalSuccessor()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> logicalSuccessor;

	/**
	 * The cached value of the '{@link #getLogicalPredecessor() <em>Logical Predecessor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalPredecessor()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> logicalPredecessor;

	/**
	 * The cached value of the '{@link #getChronologicalSuccessor() <em>Chronological Successor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChronologicalSuccessor()
	 * @generated
	 * @ordered
	 */
	protected ActivityNodeExecution chronologicalSuccessor;

	/**
	 * The cached value of the '{@link #getChronologicalPredecessor() <em>Chronological Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChronologicalPredecessor()
	 * @generated
	 * @ordered
	 */
	protected ActivityNodeExecution chronologicalPredecessor;

	/**
	 * The default value of the '{@link #isExecuted() <em>Executed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExecuted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXECUTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExecuted() <em>Executed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExecuted()
	 * @generated
	 * @ordered
	 */
	protected boolean executed = EXECUTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNode() <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityNode node;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.ACTIVITY_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityNodeExecution> getLogicalSuccessor() {
		if (logicalSuccessor == null) {
			logicalSuccessor = new EObjectWithInverseResolvingEList.ManyInverse<ActivityNodeExecution>(ActivityNodeExecution.class, this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR, TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR);
		}
		return logicalSuccessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityNodeExecution> getLogicalPredecessor() {
		if (logicalPredecessor == null) {
			logicalPredecessor = new EObjectWithInverseResolvingEList.ManyInverse<ActivityNodeExecution>(ActivityNodeExecution.class, this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR, TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR);
		}
		return logicalPredecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution getChronologicalSuccessor() {
		if (chronologicalSuccessor != null && chronologicalSuccessor.eIsProxy()) {
			InternalEObject oldChronologicalSuccessor = (InternalEObject)chronologicalSuccessor;
			chronologicalSuccessor = (ActivityNodeExecution)eResolveProxy(oldChronologicalSuccessor);
			if (chronologicalSuccessor != oldChronologicalSuccessor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, oldChronologicalSuccessor, chronologicalSuccessor));
			}
		}
		return chronologicalSuccessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution basicGetChronologicalSuccessor() {
		return chronologicalSuccessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChronologicalSuccessor(ActivityNodeExecution newChronologicalSuccessor, NotificationChain msgs) {
		ActivityNodeExecution oldChronologicalSuccessor = chronologicalSuccessor;
		chronologicalSuccessor = newChronologicalSuccessor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, oldChronologicalSuccessor, newChronologicalSuccessor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChronologicalSuccessor(ActivityNodeExecution newChronologicalSuccessor) {
		if (newChronologicalSuccessor != chronologicalSuccessor) {
			NotificationChain msgs = null;
			if (chronologicalSuccessor != null)
				msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
			if (newChronologicalSuccessor != null)
				msgs = ((InternalEObject)newChronologicalSuccessor).eInverseAdd(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
			msgs = basicSetChronologicalSuccessor(newChronologicalSuccessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, newChronologicalSuccessor, newChronologicalSuccessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution getChronologicalPredecessor() {
		if (chronologicalPredecessor != null && chronologicalPredecessor.eIsProxy()) {
			InternalEObject oldChronologicalPredecessor = (InternalEObject)chronologicalPredecessor;
			chronologicalPredecessor = (ActivityNodeExecution)eResolveProxy(oldChronologicalPredecessor);
			if (chronologicalPredecessor != oldChronologicalPredecessor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, oldChronologicalPredecessor, chronologicalPredecessor));
			}
		}
		return chronologicalPredecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution basicGetChronologicalPredecessor() {
		return chronologicalPredecessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChronologicalPredecessor(ActivityNodeExecution newChronologicalPredecessor, NotificationChain msgs) {
		ActivityNodeExecution oldChronologicalPredecessor = chronologicalPredecessor;
		chronologicalPredecessor = newChronologicalPredecessor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, oldChronologicalPredecessor, newChronologicalPredecessor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChronologicalPredecessor(ActivityNodeExecution newChronologicalPredecessor) {
		if (newChronologicalPredecessor != chronologicalPredecessor) {
			NotificationChain msgs = null;
			if (chronologicalPredecessor != null)
				msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
			if (newChronologicalPredecessor != null)
				msgs = ((InternalEObject)newChronologicalPredecessor).eInverseAdd(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
			msgs = basicSetChronologicalPredecessor(newChronologicalPredecessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, newChronologicalPredecessor, newChronologicalPredecessor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution getActivityExecution() {
		if (eContainerFeatureID() != TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION) return null;
		return (ActivityExecution)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivityExecution(ActivityExecution newActivityExecution, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newActivityExecution, TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityExecution(ActivityExecution newActivityExecution) {
		if (newActivityExecution != eInternalContainer() || (eContainerFeatureID() != TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION && newActivityExecution != null)) {
			if (EcoreUtil.isAncestor(this, newActivityExecution))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newActivityExecution != null)
				msgs = ((InternalEObject)newActivityExecution).eInverseAdd(this, TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS, ActivityExecution.class, msgs);
			msgs = basicSetActivityExecution(newActivityExecution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION, newActivityExecution, newActivityExecution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExecuted() {
		return executed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecuted(boolean newExecuted) {
		boolean oldExecuted = executed;
		executed = newExecuted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED, oldExecuted, executed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode getNode() {
		if (node != null && node.eIsProxy()) {
			InternalEObject oldNode = (InternalEObject)node;
			node = (ActivityNode)eResolveProxy(oldNode);
			if (node != oldNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE, oldNode, node));
			}
		}
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode basicGetNode() {
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNode(ActivityNode newNode) {
		ActivityNode oldNode = node;
		node = newNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE, oldNode, node));
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalSuccessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalPredecessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (chronologicalSuccessor != null)
					msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalSuccessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (chronologicalPredecessor != null)
					msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalPredecessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActivityExecution((ActivityExecution)otherEnd, msgs);
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return eInternalContainer().eInverseRemove(this, TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS, ActivityExecution.class, msgs);
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				return isExecuted();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				if (resolve) return getNode();
				return basicGetNode();
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				setNode((ActivityNode)null);
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				return node != null;
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
		result.append(" (executed: ");
		result.append(executed);
		result.append(')');
		return result.toString();
	}

} //ActivityNodeExecutionImpl
