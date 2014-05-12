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

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Classes.Kernel.Parameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityInputs <em>Activity Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityOutputs <em>Activity Outputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getContextValueSnapshot <em>Context Value Snapshot</em>}</li>
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
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Activity Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Inputs</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_ActivityInputs()
	 * @model containment="true"
	 * @generated
	 */
	List<InputParameterSetting> getActivityInputs();

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
	 * Returns the value of the '<em><b>Activity Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Outputs</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_ActivityOutputs()
	 * @model containment="true"
	 * @generated
	 */
	List<OutputParameterSetting> getActivityOutputs();

	/**
	 * Returns the value of the '<em><b>Context Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Value Snapshot</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Value Snapshot</em>' reference.
	 * @see #setContextValueSnapshot(ValueSnapshot)
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getActivityExecution_ContextValueSnapshot()
	 * @model
	 * @generated
	 */
	ValueSnapshot getContextValueSnapshot();

	/**
	 * Sets the value of the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getContextValueSnapshot <em>Context Value Snapshot</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Value Snapshot</em>' reference.
	 * @see #getContextValueSnapshot()
	 * @generated
	 */
	void setContextValueSnapshot(ValueSnapshot value);

	/**
	 * Returns the instances of {@link ActivityNodeExecution} for the given node 
	 * @param node
	 * @return
	 */
	List<ActivityNodeExecution> getNodeExecutionsByNode(ActivityNode node);
	
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

	ActivityNodeExecution getExecutionForEnabledNode(ActivityNode node);

	List<ActivityNode> getReachablePredecessorNodes(ActivityNode node);
	
	List<ActivityNode> getReachableSuccessorNodes(ActivityNode node);
	
	List<ActivityParameterNode> getInputActivityParamenterNodes();
	
	List<ActivityParameterNode> getOutputActivityParameterNodes();
	
	List<Parameter> getInputParameters();
	
	List<Parameter> getOutputParameters();

	boolean isChronologicalSuccessorOf(ActivityExecution activityExecution);

	ActivityNodeExecution getLastExecutedNode();
	
} // ActivityExecution
