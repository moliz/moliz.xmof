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

import fUML.Syntax.Activities.IntermediateActivities.Activity;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterInputs <em>Parameter Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterOutputs <em>Parameter Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface ActivityExecution extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Parameter Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Inputs</em>' containment reference list.
	 * @generated
	 */
	List<ParameterInput> getParameterInputs();

	/**
	 * Returns the value of the '<em><b>Parameter Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Outputs</em>' containment reference list.
	 * @generated
	 */
	List<ParameterOutput> getParameterOutputs();

	/**
	 * Returns the value of the '<em><b>Node Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Executions</em>' containment reference list.
	 * @generated
	 */
	List<ActivityNodeExecution> getNodeExecutions();

	/**
	 * Returns the value of the '<em><b>Caller</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller</em>' reference.
	 * @see #setCaller(CallActivityNodeExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCallee
	 * @generated
	 */
	CallActivityNodeExecution getCaller();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller</em>' reference.
	 * @see #getCaller()
	 * @generated
	 */
	void setCaller(CallActivityNodeExecution value);

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' attribute.
	 * @see #setActivity(Activity)
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' attribute.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

	/**
	 * Returns the value of the '<em><b>Activity Execution ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Execution ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Execution ID</em>' attribute.
	 * @see #setActivityExecutionID(int)
	 * @generated
	 */
	int getActivityExecutionID();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Execution ID</em>' attribute.
	 * @see #getActivityExecutionID()
	 * @generated
	 */
	void setActivityExecutionID(int value);

} // ActivityExecution
