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

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalSuccessor <em>Logical Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalPredecessor <em>Logical Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalSuccessor <em>Chronological Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalPredecessor <em>Chronological Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityNodeExecutionImpl extends EObjectImpl implements ActivityNodeExecution {
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
	 * The default value of the '{@link #getNode() <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityNode NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNode() <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityNode node = NODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackageImpl.Literals.ACTIVITY_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Input> getInputs() {
		if (inputs == null) {
			inputs = new BasicInternalEList<Input>(Input.class);
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
			outputs = new BasicInternalEList<Output>(Output.class);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ActivityNodeExecution> getLogicalSuccessor() {
		if (logicalSuccessor == null) {
			logicalSuccessor = new BasicInternalEList<ActivityNodeExecution>(ActivityNodeExecution.class);
		}
		return logicalSuccessor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ActivityNodeExecution> getLogicalPredecessor() {
		if (logicalPredecessor == null) {
			logicalPredecessor = new BasicInternalEList<ActivityNodeExecution>(ActivityNodeExecution.class);
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
				msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
			if (newChronologicalSuccessor != null)
				msgs = ((InternalEObject)newChronologicalSuccessor).eInverseAdd(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
			msgs = basicSetChronologicalSuccessor(newChronologicalSuccessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
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
				msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
			if (newChronologicalPredecessor != null)
				msgs = ((InternalEObject)newChronologicalPredecessor).eInverseAdd(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
			msgs = basicSetChronologicalPredecessor(newChronologicalPredecessor, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode getNode() {
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNode(ActivityNode newNode) {
		node = newNode;
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalSuccessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLogicalPredecessor()).basicAdd(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (chronologicalSuccessor != null)
					msgs = ((InternalEObject)chronologicalSuccessor).eInverseRemove(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalSuccessor((ActivityNodeExecution)otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (chronologicalPredecessor != null)
					msgs = ((InternalEObject)chronologicalPredecessor).eInverseRemove(this, TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR, ActivityNodeExecution.class, msgs);
				return basicSetChronologicalPredecessor((ActivityNodeExecution)otherEnd, msgs);
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__INPUTS:
				return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return ((InternalEList<?>)getLogicalSuccessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return ((InternalEList<?>)getLogicalPredecessor()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return basicSetChronologicalSuccessor(null, msgs);
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return basicSetChronologicalPredecessor(null, msgs);
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__INPUTS:
				return getInputs();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__OUTPUTS:
				return getOutputs();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return getLogicalSuccessor();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return getLogicalPredecessor();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				if (resolve) return getChronologicalSuccessor();
				return basicGetChronologicalSuccessor();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				if (resolve) return getChronologicalPredecessor();
				return basicGetChronologicalPredecessor();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__NODE:
				return getNode();
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends Input>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends Output>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				getLogicalSuccessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				getLogicalPredecessor().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__NODE:
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__INPUTS:
				getInputs().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__OUTPUTS:
				getOutputs().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				getLogicalSuccessor().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				getLogicalPredecessor().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				setChronologicalSuccessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				setChronologicalPredecessor((ActivityNodeExecution)null);
				return;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
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
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR:
				return logicalSuccessor != null && !logicalSuccessor.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR:
				return logicalPredecessor != null && !logicalPredecessor.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR:
				return chronologicalSuccessor != null;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR:
				return chronologicalPredecessor != null;
			case TracemodelPackageImpl.ACTIVITY_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
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
		result.append(" (node: ");
		result.append(node);
		result.append(')');
		return result.toString();
	}

} //ActivityNodeExecutionImpl
