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
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;

import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalSuccessor <em>Logical Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getLogicalPredecessor <em>Logical Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalSuccessor <em>Chronological Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getChronologicalPredecessor <em>Chronological Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl#isExecuted <em>Executed</em>}</li>
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
	protected EClass eStaticClass() {
		return TracemodelPackage.Literals.ACTIVITY_NODE_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
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
	 * @generated not
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
	 * @generated not
	 */
	public ActivityNodeExecution getChronologicalSuccessor() {
		return this.chronologicalSuccessor;
		/*TODO
		if(this.chronologicalSuccessor == null) {
			if(this.outputs != null && this.outputs.size() > 0) {
				// node has to be executed already
				ActivityExecution activityExecution = this.getActivityExecution();
				if(activityExecution != null) {
					int indexInList = activityExecution.getNodeExecutions().indexOf(this);
					int listsize = activityExecution.getNodeExecutions().size();
					if(indexInList != -1 && indexInList < listsize-1) {
						ActivityNodeExecution possibleSuccessor = activityExecution.getNodeExecutions().get(indexInList+1);
						if(possibleSuccessor.getOutputs() != null && possibleSuccessor.getOutputs().size() > 0) {
							chronologicalSuccessor = possibleSuccessor;
						}
					}
				}
			}
		}		
		return chronologicalSuccessor;*/
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
	 * @generated not
	 */
	public ActivityNodeExecution getChronologicalPredecessor() {
		return this.chronologicalPredecessor;
		/*TODO
		if(this.chronologicalPredecessor == null) {
			if(this.outputs != null && this.outputs.size() > 0) {
				// node has to be executed already
				ActivityExecution activityExecution = this.getActivityExecution();
				if(activityExecution != null) {
					int indexInList = activityExecution.getNodeExecutions().indexOf(this);
					if(indexInList > 0) {
						ActivityNodeExecution predecessor = activityExecution.getNodeExecutions().get(indexInList-1);;
						if(predecessor.getOutputs() != null && predecessor.getOutputs().size() > 0) {
							//predecessor node has to be executed already
							chronologicalPredecessor = predecessor;
						}
					}
				}
			}
		}		
		return chronologicalPredecessor;*/
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
	public ActivityNode getNode() {
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
	@SuppressWarnings("unchecked")
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				return getNode();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				return isExecuted();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				setNode((ActivityNode)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted((Boolean)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				setExecuted(EXECUTED_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION__EXECUTED:
				return executed != EXECUTED_EDEFAULT;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (node: ");
		result.append(node);
		result.append(", executed: ");
		result.append(executed);
		result.append(')');
		return result.toString();
	}
/* TODO
	@Override
	public void addActivityNodeInput(InputPin inputPin, List<TokenInstance> tokenInstances) {
		Set<TokenInstance> tokens = new HashSet<TokenInstance>(tokenInstances);
		Input input = new InputImpl();
		input.setInputPin(inputPin);		
		input.getTokens().addAll(tokens);
		this.getInputs().add(input);
	}

	@Override
	public void addActivityNodeOutput(OutputPin outputPin, List<TokenInstance> tokenInstances) {	
		Output output = new OutputImpl();
		output.setOutputPin(outputPin);
		if(tokenInstances != null) {
			output.getTokens().addAll(tokenInstances);
		}
		this.getOutputs().add(output);				
	}
*/	
	ActivityNodeExecution getDirectTokenProvider(TokenInstance token) {
		List<ActivityNodeExecution> tokenProvider = this.getActivityExecution().getNodeExecutionsWithTokenOutput(token);
		List<ActivityEdge> traversedEdges = token.getTraversedEdges();
		
		ActivityNodeExecution provider = null;		
		
		for (ActivityNodeExecution r : tokenProvider) {
			for(ActivityEdge e : traversedEdges) {
				if(e.target.equals(this.node) && e.source.equals(r.getNode())) {
					provider = r;
					break;
				}
			}
			if(provider != null) {
				break;
			}
		}

		return provider;
	}

	ActivityNodeExecution getDirectTokenReceiver(TokenInstance token) {
		List<ActivityNodeExecution> tokenReceivers = this.getActivityExecution().getNodeExecutionsWithTokenInput(token);
		List<ActivityEdge> traversedEdges = token.getTraversedEdges();
		
		ActivityNodeExecution receiver = null;	
		
		for (ActivityNodeExecution r : tokenReceivers) {
			for(ActivityEdge e : traversedEdges) {
				if(e.source.equals(this.node) && e.target.equals(r.getNode())) {
					receiver = r;
					break;
				}
			}
			if(receiver != null) {
				break;
			}
		}
		return receiver;
	}

} //ActivityNodeExecutionImpl
