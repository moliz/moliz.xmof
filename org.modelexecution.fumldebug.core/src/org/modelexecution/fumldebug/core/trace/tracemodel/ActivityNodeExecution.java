/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution()
 * @model
 * @generated
 */
public interface ActivityNodeExecution extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Logical Successor</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Successor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Successor</em>' reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_LogicalSuccessor()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor
	 * @model opposite="logicalPredecessor" derived="true"
	 * @generated
	 */
	List<ActivityNodeExecution> getLogicalSuccessor();

	/**
	 * Returns the value of the '<em><b>Logical Predecessor</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Predecessor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Predecessor</em>' reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_LogicalPredecessor()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor
	 * @model opposite="logicalSuccessor" derived="true"
	 * @generated
	 */
	List<ActivityNodeExecution> getLogicalPredecessor();

	/**
	 * Returns the value of the '<em><b>Chronological Successor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chronological Successor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chronological Successor</em>' reference.
	 * @see #setChronologicalSuccessor(ActivityNodeExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_ChronologicalSuccessor()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor
	 * @model opposite="chronologicalPredecessor" derived="true"
	 * @generated
	 */
	ActivityNodeExecution getChronologicalSuccessor();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chronological Successor</em>' reference.
	 * @see #getChronologicalSuccessor()
	 * @generated
	 */
	void setChronologicalSuccessor(ActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Chronological Predecessor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chronological Predecessor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chronological Predecessor</em>' reference.
	 * @see #setChronologicalPredecessor(ActivityNodeExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_ChronologicalPredecessor()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor
	 * @model opposite="chronologicalSuccessor"
	 * @generated
	 */
	ActivityNodeExecution getChronologicalPredecessor();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chronological Predecessor</em>' reference.
	 * @see #getChronologicalPredecessor()
	 * @generated
	 */
	void setChronologicalPredecessor(ActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' attribute.
	 * @see #setNode(ActivityNode)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_Node()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNode" required="true" transient="true"
	 * @generated
	 */
	ActivityNode getNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' attribute.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(ActivityNode value);

	/**
	 * Returns the value of the '<em><b>Activity Execution</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Execution</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Execution</em>' container reference.
	 * @see #setActivityExecution(ActivityExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_ActivityExecution()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions
	 * @model opposite="nodeExecutions" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ActivityExecution getActivityExecution();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}' container reference.
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
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityNodeExecution_Executed()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isExecuted();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executed</em>' attribute.
	 * @see #isExecuted()
	 * @generated
	 */
	void setExecuted(boolean value);

	/**
	 * Adds a list of {@link TokenInstance} as input
	 * @param inputPin
	 * @param tokenInstances
	 */
//TODO	void addActivityNodeInput(InputPin inputPin, List<TokenInstance> tokenInstances);
	
	/**
	 * Adds a list of {@link TokenInstance} as output
	 * @param outputPin
	 * @param tokenInstances
	 */
//TODO	void addActivityNodeOutput(OutputPin outputPin, List<TokenInstance> tokenInstances);
	
} // ActivityNodeExecution
