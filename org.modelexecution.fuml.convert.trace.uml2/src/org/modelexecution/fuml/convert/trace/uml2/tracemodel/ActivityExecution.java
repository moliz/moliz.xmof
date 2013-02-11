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

import org.eclipse.uml2.uml.Activity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getUserParameterInputs <em>User Parameter Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getTrace <em>Trace</em>}</li>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution()
 * @model
 * @generated
 */
public interface ActivityExecution extends EObject {
	/**
	 * Returns the value of the '<em><b>User Parameter Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Parameter Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Parameter Inputs</em>' containment reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_UserParameterInputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<UserParameterInput> getUserParameterInputs();

	/**
	 * Returns the value of the '<em><b>Node Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Executions</em>' containment reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_NodeExecutions()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution#getActivityExecution
	 * @model opposite="activityExecution" containment="true"
	 * @generated
	 */
	EList<ActivityNodeExecution> getNodeExecutions();

	/**
	 * Returns the value of the '<em><b>Caller</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller</em>' reference.
	 * @see #setCaller(CallActionExecution)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_Caller()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution#getCallee
	 * @model opposite="callee"
	 * @generated
	 */
	CallActionExecution getCaller();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getCaller <em>Caller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller</em>' reference.
	 * @see #getCaller()
	 * @generated
	 */
	void setCaller(CallActionExecution value);

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
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_ActivityExecutionID()
	 * @model required="true"
	 * @generated
	 */
	int getActivityExecutionID();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Execution ID</em>' attribute.
	 * @see #getActivityExecutionID()
	 * @generated
	 */
	void setActivityExecutionID(int value);

	/**
	 * Returns the value of the '<em><b>Trace</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace</em>' container reference.
	 * @see #setTrace(Trace)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_Trace()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace#getActivityExecutions
	 * @model opposite="activityExecutions" required="true" transient="false"
	 * @generated
	 */
	Trace getTrace();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getTrace <em>Trace</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace</em>' container reference.
	 * @see #getTrace()
	 * @generated
	 */
	void setTrace(Trace value);

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getActivityExecution_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

} // ActivityExecution
