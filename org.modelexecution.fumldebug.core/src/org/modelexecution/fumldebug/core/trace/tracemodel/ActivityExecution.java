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

import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getUserParameterInputs <em>User Parameter Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution()
 * @model
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
	 * Returns the value of the '<em><b>User Parameter Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Parameter Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Parameter Inputs</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_UserParameterInputs()
	 * @model containment="true"
	 * @generated
	 */
	List<UserParameterInput> getUserParameterInputs();

	/**
	 * Returns the value of the '<em><b>Node Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Executions</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_NodeExecutions()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution
	 * @model opposite="activityExecution" containment="true"
	 * @generated
	 */
	List<ActivityNodeExecution> getNodeExecutions();

	/**
	 * Returns the value of the '<em><b>Caller</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller</em>' reference.
	 * @see #setCaller(CallActionExecution)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_Caller()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee
	 * @model opposite="callee"
	 * @generated
	 */
	CallActionExecution getCaller();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller</em>' reference.
	 * @see #getCaller()
	 * @generated
	 */
	void setCaller(CallActionExecution value);

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
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_Activity()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.Activity" required="true" transient="true"
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
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_ActivityExecutionID()
	 * @model required="true"
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

	/**
	 * Returns the value of the '<em><b>Trace</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace</em>' container reference.
	 * @see #setTrace(Trace)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_Trace()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions
	 * @model opposite="activityExecutions" required="true" transient="false"
	 * @generated
	 */
	Trace getTrace();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace</em>' container reference.
	 * @see #getTrace()
	 * @generated
	 */
	void setTrace(Trace value);

	/**
	 * Returns the instances of {@link ActivityNodeExecution} for the given node 
	 * @param node
	 * @return
	 */
	List<ActivityNodeExecution> getNodeExecutionsByNode(ActivityNode node);
	
	/**
	 * Returns the instance of {@link ActivityNodeExecution} for the given node
	 * which does not have an output
	 * @param node
	 * @return
	 */
	//TODO List<ActivityNodeExecution> getNodeExecutionsByNodeWithoutOutput(ActivityNode node);

	/**
	 * Adds a {@link ParameterInput} to the {@link ActivityExecution}
	 * @param activityParameterNode
	 * @param values
	 */
	//TODO void addParameterInput(ActivityParameterNode activityParameterNode, List<Value> values);
	
	/**
	 * Adds a {@link UserParameterInput} to the {@link ActivityExecution}
	 * @param activityParameterNode
	 * @param values
	 */
	//TODO void addUserParameterInput(ActivityParameterNode activityParameterNode, List<Value> values);
	
	/**
	 * Adds a {@link ParameterOutput} to the {@link ActivityExecution}
	 * @param activityParameterNode
	 * @param values
	 */
	//TODO void addParameterOutput(ActivityParameterNode activityParameterNode, List<Value> values);
	
	/**
	 * Adds an {@link ActivityNodeExecution}
	 * @param activityNode
	 * @return
	 */
	ActivityNodeExecution addActivityNodeExecution(ActivityNode activityNode);
	
	/**
	 * Returns the {@link ActivityNodeExecution} that produced the given {@link TokenInstance} as output
	 * @param tokenInstance
	 * @return
	 */
	List<ActivityNodeExecution> getNodeExecutionsWithTokenOutput(TokenInstance tokenInstance);
	
	/**
	 * Returns the {@link ActivityNodeExecution} that consumed the given {@link TokenInstance} as input
	 * @param tokenInstance
	 * @return
	 */
	List<ActivityNodeExecution> getNodeExecutionsWithTokenInput(TokenInstance tokenInstance);

	List<ActivityNodeExecution> getExecutionsForEnabledNode(ActivityNode node);

	/**
	 * Inserts finished {@link ActivityNodeExecution} correctly according to chronological order
	 * @param activityNodeExecution
	 */
	//TODO public void setActivityNodeExecutionFinishedExecution(ActivityNodeExecution activityNodeExecution);
	
	CallActionExecution getActiveCallActionExecution(CallAction action);
} // ActivityExecution
