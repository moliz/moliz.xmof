/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution()
 * @model
 * @generated
 */
public interface ActivityNodeExecution extends EObject {
	/**
	 * Returns the value of the '<em><b>Logical Successor</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Successor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Successor</em>' reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_LogicalSuccessor()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalPredecessor
	 * @model opposite="logicalPredecessor" derived="true"
	 * @generated
	 */
	EList<ActivityNodeExecution> getLogicalSuccessor();

	/**
	 * Returns the value of the '<em><b>Logical Predecessor</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Predecessor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Predecessor</em>' reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_LogicalPredecessor()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getLogicalSuccessor
	 * @model opposite="logicalSuccessor" derived="true"
	 * @generated
	 */
	EList<ActivityNodeExecution> getLogicalPredecessor();

	/**
	 * Returns the value of the '<em><b>Chronological Successor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chronological Successor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chronological Successor</em>' reference.
	 * @see #setChronologicalSuccessor(ActivityNodeExecution)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_ChronologicalSuccessor()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalPredecessor
	 * @model opposite="chronologicalPredecessor" derived="true"
	 * @generated
	 */
	ActivityNodeExecution getChronologicalSuccessor();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chronological Successor</em>' reference.
	 * @see #getChronologicalSuccessor()
	 * @generated
	 */
	void setChronologicalSuccessor(ActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Chronological Predecessor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chronological Predecessor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chronological Predecessor</em>' reference.
	 * @see #setChronologicalPredecessor(ActivityNodeExecution)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_ChronologicalPredecessor()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalSuccessor
	 * @model opposite="chronologicalSuccessor"
	 * @generated
	 */
	ActivityNodeExecution getChronologicalPredecessor();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chronological Predecessor</em>' reference.
	 * @see #getChronologicalPredecessor()
	 * @generated
	 */
	void setChronologicalPredecessor(ActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Activity Execution</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Execution</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Execution</em>' container reference.
	 * @see #setActivityExecution(ActivityExecution)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_ActivityExecution()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getNodeExecutions
	 * @model opposite="nodeExecutions" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ActivityExecution getActivityExecution();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Execution</em>' container reference.
	 * @see #getActivityExecution()
	 * @generated
	 */
	void setActivityExecution(ActivityExecution value);

	/**
	 * Returns the value of the '<em><b>Executed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executed</em>' attribute.
	 * @see #setExecuted(boolean)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_Executed()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isExecuted();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executed</em>' attribute.
	 * @see #isExecuted()
	 * @generated
	 */
	void setExecuted(boolean value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(ActivityNode)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityNodeExecution_Node()
	 * @model required="true"
	 * @generated
	 */
	ActivityNode getNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(ActivityNode value);

} // ActivityNodeExecution
