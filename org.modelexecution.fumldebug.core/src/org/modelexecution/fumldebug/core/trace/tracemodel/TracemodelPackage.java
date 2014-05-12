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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory
 * @model kind="package"
 * @generated
 */
public interface TracemodelPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tracemodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://tracemodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracemodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracemodelPackage eINSTANCE = org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 0;

	/**
	 * The feature id for the '<em><b>Activity Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__ACTIVITY_EXECUTIONS = 0;

	/**
	 * The feature id for the '<em><b>Value Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__VALUE_INSTANCES = 1;

	/**
	 * The feature id for the '<em><b>Initial Locus Value Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__INITIAL_LOCUS_VALUE_INSTANCES = 2;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl <em>Activity Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityExecution()
	 * @generated
	 */
	int ACTIVITY_EXECUTION = 1;

	/**
	 * The feature id for the '<em><b>Activity Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__ACTIVITY_INPUTS = 0;

	/**
	 * The feature id for the '<em><b>Node Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__NODE_EXECUTIONS = 1;

	/**
	 * The feature id for the '<em><b>Caller</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__CALLER = 2;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Activity Execution ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID = 4;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__TRACE = 5;

	/**
	 * The feature id for the '<em><b>Activity Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__ACTIVITY_OUTPUTS = 6;

	/**
	 * The feature id for the '<em><b>Context Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION__CONTEXT_VALUE_SNAPSHOT = 7;

	/**
	 * The number of structural features of the '<em>Activity Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_EXECUTION_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl <em>Activity Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNodeExecution()
	 * @generated
	 */
	int ACTIVITY_NODE_EXECUTION = 2;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = 0;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = 1;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = 2;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = 3;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__NODE = 4;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = 5;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__EXECUTED = 6;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION = 7;

	/**
	 * The number of structural features of the '<em>Activity Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_EXECUTION_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl <em>Action Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActionExecution()
	 * @generated
	 */
	int ACTION_EXECUTION = 10;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__LOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__LOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__NODE = ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__ACTIVITY_EXECUTION = ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__EXECUTED = ACTIVITY_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__UNDER_EXECUTION = ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__INPUTS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__OUTPUTS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Incoming Control</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__INCOMING_CONTROL = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Outgoing Control</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION__OUTGOING_CONTROL = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Action Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_EXECUTION_FEATURE_COUNT = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl <em>Call Action Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActionExecution()
	 * @generated
	 */
	int CALL_ACTION_EXECUTION = 3;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR = ACTION_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR = ACTION_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__NODE = ACTION_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION = ACTION_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__EXECUTED = ACTION_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__UNDER_EXECUTION = ACTION_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__INPUTS = ACTION_EXECUTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__OUTPUTS = ACTION_EXECUTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Incoming Control</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__INCOMING_CONTROL = ACTION_EXECUTION__INCOMING_CONTROL;

	/**
	 * The feature id for the '<em><b>Outgoing Control</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__OUTGOING_CONTROL = ACTION_EXECUTION__OUTGOING_CONTROL;

	/**
	 * The feature id for the '<em><b>Callee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION__CALLEE = ACTION_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Action Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_EXECUTION_FEATURE_COUNT = ACTION_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterSettingImpl <em>Parameter Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterSettingImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterSetting()
	 * @generated
	 */
	int PARAMETER_SETTING = 4;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_SETTING__PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Parameter Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_SETTING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 5;

	/**
	 * The feature id for the '<em><b>Input Pin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__INPUT_PIN = 0;

	/**
	 * The feature id for the '<em><b>Input Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__INPUT_VALUES = 1;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutput()
	 * @generated
	 */
	int OUTPUT = 6;

	/**
	 * The feature id for the '<em><b>Output Pin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__OUTPUT_PIN = 0;

	/**
	 * The feature id for the '<em><b>Output Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__OUTPUT_VALUES = 1;

	/**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl <em>Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTokenInstance()
	 * @generated
	 */
	int TOKEN_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_INSTANCE__TRAVERSED_EDGES = 0;

	/**
	 * The number of structural features of the '<em>Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_INSTANCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl <em>Object Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObjectTokenInstance()
	 * @generated
	 */
	int OBJECT_TOKEN_INSTANCE = 8;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES = TOKEN_INSTANCE__TRAVERSED_EDGES;

	/**
	 * The feature id for the '<em><b>Transported Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE = TOKEN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_TOKEN_INSTANCE_FEATURE_COUNT = TOKEN_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl <em>Control Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlTokenInstance()
	 * @generated
	 */
	int CONTROL_TOKEN_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TOKEN_INSTANCE__TRAVERSED_EDGES = TOKEN_INSTANCE__TRAVERSED_EDGES;

	/**
	 * The number of structural features of the '<em>Control Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_TOKEN_INSTANCE_FEATURE_COUNT = TOKEN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl <em>Value Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstance()
	 * @generated
	 */
	int VALUE_INSTANCE = 12;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl <em>Decision Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getDecisionNodeExecution()
	 * @generated
	 */
	int DECISION_NODE_EXECUTION = 14;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.StructuredActivityNodeExecutionImpl <em>Structured Activity Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.StructuredActivityNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getStructuredActivityNodeExecution()
	 * @generated
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION = 15;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl <em>Control Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlNodeExecution()
	 * @generated
	 */
	int CONTROL_NODE_EXECUTION = 11;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__LOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__LOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__NODE = ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__ACTIVITY_EXECUTION = ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__EXECUTED = ACTIVITY_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__UNDER_EXECUTION = ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Routed Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION__ROUTED_TOKENS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Control Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_NODE_EXECUTION_FEATURE_COUNT = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Runtime Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE__RUNTIME_VALUE = 0;

	/**
	 * The feature id for the '<em><b>Snapshots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE__SNAPSHOTS = 1;

	/**
	 * The feature id for the '<em><b>Original</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE__ORIGINAL = 2;

	/**
	 * The feature id for the '<em><b>Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE__CREATOR = 3;

	/**
	 * The feature id for the '<em><b>Destroyer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE__DESTROYER = 4;

	/**
	 * The number of structural features of the '<em>Value Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_INSTANCE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueSnapshotImpl <em>Value Snapshot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueSnapshotImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueSnapshot()
	 * @generated
	 */
	int VALUE_SNAPSHOT = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SNAPSHOT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Value Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SNAPSHOT__VALUE_INSTANCE = 1;

	/**
	 * The number of structural features of the '<em>Value Snapshot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SNAPSHOT_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__LOGICAL_SUCCESSOR = CONTROL_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__LOGICAL_PREDECESSOR = CONTROL_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = CONTROL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = CONTROL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__NODE = CONTROL_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__ACTIVITY_EXECUTION = CONTROL_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__EXECUTED = CONTROL_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__UNDER_EXECUTION = CONTROL_NODE_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Routed Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__ROUTED_TOKENS = CONTROL_NODE_EXECUTION__ROUTED_TOKENS;

	/**
	 * The feature id for the '<em><b>Decision Input Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE = CONTROL_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Decision Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_NODE_EXECUTION_FEATURE_COUNT = CONTROL_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = ACTION_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = ACTION_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE = ACTION_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = ACTION_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED = ACTION_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION = ACTION_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS = ACTION_EXECUTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS = ACTION_EXECUTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Incoming Control</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL = ACTION_EXECUTION__INCOMING_CONTROL;

	/**
	 * The feature id for the '<em><b>Outgoing Control</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL = ACTION_EXECUTION__OUTGOING_CONTROL;

	/**
	 * The feature id for the '<em><b>Nested Node Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS = ACTION_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Structured Activity Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_ACTIVITY_NODE_EXECUTION_FEATURE_COUNT = ACTION_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputOutputValueImpl <em>Input Output Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputOutputValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputOutputValue()
	 * @generated
	 */
	int INPUT_OUTPUT_VALUE = 26;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_OUTPUT_VALUE__VALUE_SNAPSHOT = 0;

	/**
	 * The number of structural features of the '<em>Input Output Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_OUTPUT_VALUE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl <em>Input Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputValue()
	 * @generated
	 */
	int INPUT_VALUE = 16;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_VALUE__VALUE_SNAPSHOT = INPUT_OUTPUT_VALUE__VALUE_SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Input Object Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_VALUE__INPUT_OBJECT_TOKEN = INPUT_OUTPUT_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Input Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_VALUE_FEATURE_COUNT = INPUT_OUTPUT_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputValueImpl <em>Output Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputValue()
	 * @generated
	 */
	int OUTPUT_VALUE = 17;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_VALUE__VALUE_SNAPSHOT = INPUT_OUTPUT_VALUE__VALUE_SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Output Object Token</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN = INPUT_OUTPUT_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Output Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_VALUE_FEATURE_COUNT = INPUT_OUTPUT_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterValueImpl <em>Parameter Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterValue()
	 * @generated
	 */
	int PARAMETER_VALUE = 23;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_VALUE__VALUE_SNAPSHOT = 0;

	/**
	 * The number of structural features of the '<em>Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_VALUE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterValueImpl <em>Input Parameter Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputParameterValue()
	 * @generated
	 */
	int INPUT_PARAMETER_VALUE = 18;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_VALUE__VALUE_SNAPSHOT = PARAMETER_VALUE__VALUE_SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Parameter Input Object Token</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN = PARAMETER_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Input Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_VALUE_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterSettingImpl <em>Input Parameter Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterSettingImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputParameterSetting()
	 * @generated
	 */
	int INPUT_PARAMETER_SETTING = 19;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_SETTING__PARAMETER = PARAMETER_SETTING__PARAMETER;

	/**
	 * The feature id for the '<em><b>Parameter Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_SETTING__PARAMETER_VALUES = PARAMETER_SETTING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Input Parameter Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_SETTING_FEATURE_COUNT = PARAMETER_SETTING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterSettingImpl <em>Output Parameter Setting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterSettingImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputParameterSetting()
	 * @generated
	 */
	int OUTPUT_PARAMETER_SETTING = 20;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_SETTING__PARAMETER = PARAMETER_SETTING__PARAMETER;

	/**
	 * The feature id for the '<em><b>Parameter Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES = PARAMETER_SETTING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Output Parameter Setting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_SETTING_FEATURE_COUNT = PARAMETER_SETTING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterValueImpl <em>Output Parameter Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterValueImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputParameterValue()
	 * @generated
	 */
	int OUTPUT_PARAMETER_VALUE = 21;

	/**
	 * The feature id for the '<em><b>Value Snapshot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_VALUE__VALUE_SNAPSHOT = PARAMETER_VALUE__VALUE_SNAPSHOT;

	/**
	 * The feature id for the '<em><b>Parameter Output Object Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN = PARAMETER_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Output Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_VALUE_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InitialNodeExecutionImpl <em>Initial Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InitialNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInitialNodeExecution()
	 * @generated
	 */
	int INITIAL_NODE_EXECUTION = 22;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__LOGICAL_SUCCESSOR = CONTROL_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__LOGICAL_PREDECESSOR = CONTROL_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = CONTROL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = CONTROL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__NODE = CONTROL_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__ACTIVITY_EXECUTION = CONTROL_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__EXECUTED = CONTROL_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__UNDER_EXECUTION = CONTROL_NODE_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Routed Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__ROUTED_TOKENS = CONTROL_NODE_EXECUTION__ROUTED_TOKENS;

	/**
	 * The feature id for the '<em><b>Outgoing Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION__OUTGOING_CONTROL = CONTROL_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Initial Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_NODE_EXECUTION_FEATURE_COUNT = CONTROL_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionInput()
	 * @generated
	 */
	int EXPANSION_INPUT = 24;

	/**
	 * The feature id for the '<em><b>Expansion Input Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__EXPANSION_INPUT_VALUES = 0;

	/**
	 * The feature id for the '<em><b>Expansion Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__EXPANSION_NODE = 1;

	/**
	 * The number of structural features of the '<em>Expansion Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionRegionExecutionImpl <em>Expansion Region Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionRegionExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionRegionExecution()
	 * @generated
	 */
	int EXPANSION_REGION_EXECUTION = 25;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__LOGICAL_SUCCESSOR = STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__LOGICAL_PREDECESSOR = STRUCTURED_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_SUCCESSOR = STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__CHRONOLOGICAL_PREDECESSOR = STRUCTURED_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__NODE = STRUCTURED_ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__ACTIVITY_EXECUTION = STRUCTURED_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__EXECUTED = STRUCTURED_ACTIVITY_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Under Execution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__UNDER_EXECUTION = STRUCTURED_ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__INPUTS = STRUCTURED_ACTIVITY_NODE_EXECUTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__OUTPUTS = STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Incoming Control</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__INCOMING_CONTROL = STRUCTURED_ACTIVITY_NODE_EXECUTION__INCOMING_CONTROL;

	/**
	 * The feature id for the '<em><b>Outgoing Control</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__OUTGOING_CONTROL = STRUCTURED_ACTIVITY_NODE_EXECUTION__OUTGOING_CONTROL;

	/**
	 * The feature id for the '<em><b>Nested Node Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__NESTED_NODE_EXECUTIONS = STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Expansion Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS = STRUCTURED_ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expansion Region Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_REGION_EXECUTION_FEATURE_COUNT = STRUCTURED_ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '<em>Activity</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 27;

	/**
	 * The meta object id for the '<em>Activity Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNode()
	 * @generated
	 */
	int ACTIVITY_NODE = 28;

	/**
	 * The meta object id for the '<em>Behavior</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getBehavior()
	 * @generated
	 */
	int BEHAVIOR = 29;

	/**
	 * The meta object id for the '<em>Parameter</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Classes.Kernel.Parameter
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 30;

	/**
	 * The meta object id for the '<em>Input Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.InputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputPin()
	 * @generated
	 */
	int INPUT_PIN = 31;

	/**
	 * The meta object id for the '<em>Output Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.OutputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputPin()
	 * @generated
	 */
	int OUTPUT_PIN = 32;

	/**
	 * The meta object id for the '<em>Activity Edge</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityEdge()
	 * @generated
	 */
	int ACTIVITY_EDGE = 33;

	/**
	 * The meta object id for the '<em>Value</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Semantics.Classes.Kernel.Value
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 34;


	/**
	 * The meta object id for the '<em>Primitive Value</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Semantics.Classes.Kernel.PrimitiveValue
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getPrimitiveValue()
	 * @generated
	 */
	int PRIMITIVE_VALUE = 35;

	/**
	 * The meta object id for the '<em>Object </em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Semantics.Classes.Kernel.Object_
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObject_()
	 * @generated
	 */
	int OBJECT_ = 36;


	/**
	 * The meta object id for the '<em>Expansion Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionNode()
	 * @generated
	 */
	int EXPANSION_NODE = 37;


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Executions</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_ActivityExecutions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getValueInstances <em>Value Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value Instances</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getValueInstances()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_ValueInstances();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getInitialLocusValueInstances <em>Initial Locus Value Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Initial Locus Value Instances</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getInitialLocusValueInstances()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_InitialLocusValueInstances();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution
	 * @generated
	 */
	EClass getActivityExecution();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityInputs <em>Activity Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityInputs()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_ActivityInputs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node Executions</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_NodeExecutions();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Caller</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_Caller();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EAttribute getActivityExecution_Activity();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Execution ID</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EAttribute getActivityExecution_ActivityExecutionID();

	/**
	 * Returns the meta object for the container reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Trace</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_Trace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityOutputs <em>Activity Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Outputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityOutputs()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_ActivityOutputs();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getContextValueSnapshot <em>Context Value Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context Value Snapshot</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getContextValueSnapshot()
	 * @see #getActivityExecution()
	 * @generated
	 */
	EReference getActivityExecution_ContextValueSnapshot();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution <em>Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution
	 * @generated
	 */
	EClass getActivityNodeExecution();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Successor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EReference getActivityNodeExecution_LogicalSuccessor();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Predecessor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EReference getActivityNodeExecution_LogicalPredecessor();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Chronological Successor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EReference getActivityNodeExecution_ChronologicalSuccessor();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Chronological Predecessor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EReference getActivityNodeExecution_ChronologicalPredecessor();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EAttribute getActivityNodeExecution_Node();

	/**
	 * Returns the meta object for the container reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Activity Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EReference getActivityNodeExecution_ActivityExecution();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executed</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EAttribute getActivityNodeExecution_Executed();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isUnderExecution <em>Under Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Under Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isUnderExecution()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	EAttribute getActivityNodeExecution_UnderExecution();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution <em>Call Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Action Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution
	 * @generated
	 */
	EClass getCallActionExecution();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callee</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee()
	 * @see #getCallActionExecution()
	 * @generated
	 */
	EReference getCallActionExecution_Callee();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterSetting <em>Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Setting</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterSetting
	 * @generated
	 */
	EClass getParameterSetting();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterSetting#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterSetting#getParameter()
	 * @see #getParameterSetting()
	 * @generated
	 */
	EAttribute getParameterSetting_Parameter();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Pin</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_InputPin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputValues <em>Input Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Values</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputValues()
	 * @see #getInput()
	 * @generated
	 */
	EReference getInput_InputValues();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output
	 * @generated
	 */
	EClass getOutput();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Pin</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputPin()
	 * @see #getOutput()
	 * @generated
	 */
	EAttribute getOutput_OutputPin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputValues <em>Output Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Values</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputValues()
	 * @see #getOutput()
	 * @generated
	 */
	EReference getOutput_OutputValues();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance <em>Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance
	 * @generated
	 */
	EClass getTokenInstance();

	/**
	 * Returns the meta object for the attribute list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance#getTraversedEdges <em>Traversed Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Traversed Edges</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance#getTraversedEdges()
	 * @see #getTokenInstance()
	 * @generated
	 */
	EAttribute getTokenInstance_TraversedEdges();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance <em>Object Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance
	 * @generated
	 */
	EClass getObjectTokenInstance();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getTransportedValue <em>Transported Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transported Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getTransportedValue()
	 * @see #getObjectTokenInstance()
	 * @generated
	 */
	EReference getObjectTokenInstance_TransportedValue();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance <em>Control Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance
	 * @generated
	 */
	EClass getControlTokenInstance();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance
	 * @generated
	 */
	EClass getValueInstance();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getRuntimeValue <em>Runtime Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runtime Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getRuntimeValue()
	 * @see #getValueInstance()
	 * @generated
	 */
	EAttribute getValueInstance_RuntimeValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Snapshots</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots()
	 * @see #getValueInstance()
	 * @generated
	 */
	EReference getValueInstance_Snapshots();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Original</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal()
	 * @see #getValueInstance()
	 * @generated
	 */
	EReference getValueInstance_Original();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getCreator <em>Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Creator</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getCreator()
	 * @see #getValueInstance()
	 * @generated
	 */
	EReference getValueInstance_Creator();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getDestroyer <em>Destroyer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destroyer</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getDestroyer()
	 * @see #getValueInstance()
	 * @generated
	 */
	EReference getValueInstance_Destroyer();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot <em>Value Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Snapshot</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot
	 * @generated
	 */
	EClass getValueSnapshot();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValue()
	 * @see #getValueSnapshot()
	 * @generated
	 */
	EAttribute getValueSnapshot_Value();

	/**
	 * Returns the meta object for the container reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Value Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot#getValueInstance()
	 * @see #getValueSnapshot()
	 * @generated
	 */
	EReference getValueSnapshot_ValueInstance();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution <em>Decision Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution
	 * @generated
	 */
	EClass getDecisionNodeExecution();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution#getDecisionInputValue <em>Decision Input Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Decision Input Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution#getDecisionInputValue()
	 * @see #getDecisionNodeExecution()
	 * @generated
	 */
	EReference getDecisionNodeExecution_DecisionInputValue();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution <em>Structured Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structured Activity Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution
	 * @generated
	 */
	EClass getStructuredActivityNodeExecution();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution#getNestedNodeExecutions <em>Nested Node Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nested Node Executions</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution#getNestedNodeExecutions()
	 * @see #getStructuredActivityNodeExecution()
	 * @generated
	 */
	EReference getStructuredActivityNodeExecution_NestedNodeExecutions();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputValue <em>Input Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputValue
	 * @generated
	 */
	EClass getInputValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputValue#getInputObjectToken <em>Input Object Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Input Object Token</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputValue#getInputObjectToken()
	 * @see #getInputValue()
	 * @generated
	 */
	EReference getInputValue_InputObjectToken();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue <em>Output Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue
	 * @generated
	 */
	EClass getOutputValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue#getOutputObjectToken <em>Output Object Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output Object Token</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue#getOutputObjectToken()
	 * @see #getOutputValue()
	 * @generated
	 */
	EReference getOutputValue_OutputObjectToken();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue <em>Input Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Parameter Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue
	 * @generated
	 */
	EClass getInputParameterValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue#getParameterInputObjectToken <em>Parameter Input Object Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parameter Input Object Token</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue#getParameterInputObjectToken()
	 * @see #getInputParameterValue()
	 * @generated
	 */
	EReference getInputParameterValue_ParameterInputObjectToken();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting <em>Input Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Parameter Setting</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting
	 * @generated
	 */
	EClass getInputParameterSetting();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting#getParameterValues <em>Parameter Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Values</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting#getParameterValues()
	 * @see #getInputParameterSetting()
	 * @generated
	 */
	EReference getInputParameterSetting_ParameterValues();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting <em>Output Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Parameter Setting</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting
	 * @generated
	 */
	EClass getOutputParameterSetting();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting#getParameterValues <em>Parameter Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Values</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting#getParameterValues()
	 * @see #getOutputParameterSetting()
	 * @generated
	 */
	EReference getOutputParameterSetting_ParameterValues();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue <em>Output Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Parameter Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue
	 * @generated
	 */
	EClass getOutputParameterValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue#getParameterOutputObjectToken <em>Parameter Output Object Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter Output Object Token</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue#getParameterOutputObjectToken()
	 * @see #getOutputParameterValue()
	 * @generated
	 */
	EReference getOutputParameterValue_ParameterOutputObjectToken();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution <em>Initial Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initial Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution
	 * @generated
	 */
	EClass getInitialNodeExecution();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution#getOutgoingControl <em>Outgoing Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outgoing Control</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution#getOutgoingControl()
	 * @see #getInitialNodeExecution()
	 * @generated
	 */
	EReference getInitialNodeExecution_OutgoingControl();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue <em>Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue
	 * @generated
	 */
	EClass getParameterValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue#getValueSnapshot <em>Value Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value Snapshot</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue#getValueSnapshot()
	 * @see #getParameterValue()
	 * @generated
	 */
	EReference getParameterValue_ValueSnapshot();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput <em>Expansion Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Input</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput
	 * @generated
	 */
	EClass getExpansionInput();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionInputValues <em>Expansion Input Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expansion Input Values</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionInputValues()
	 * @see #getExpansionInput()
	 * @generated
	 */
	EReference getExpansionInput_ExpansionInputValues();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionNode <em>Expansion Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expansion Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput#getExpansionNode()
	 * @see #getExpansionInput()
	 * @generated
	 */
	EAttribute getExpansionInput_ExpansionNode();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution <em>Expansion Region Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Region Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution
	 * @generated
	 */
	EClass getExpansionRegionExecution();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution#getExpansionInputs <em>Expansion Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expansion Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution#getExpansionInputs()
	 * @see #getExpansionRegionExecution()
	 * @generated
	 */
	EReference getExpansionRegionExecution_ExpansionInputs();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputOutputValue <em>Input Output Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Output Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputOutputValue
	 * @generated
	 */
	EClass getInputOutputValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.InputOutputValue#getValueSnapshot <em>Value Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value Snapshot</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.InputOutputValue#getValueSnapshot()
	 * @see #getInputOutputValue()
	 * @generated
	 */
	EReference getInputOutputValue_ValueSnapshot();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution <em>Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution
	 * @generated
	 */
	EClass getActionExecution();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getInputs()
	 * @see #getActionExecution()
	 * @generated
	 */
	EReference getActionExecution_Inputs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutputs()
	 * @see #getActionExecution()
	 * @generated
	 */
	EReference getActionExecution_Outputs();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getIncomingControl <em>Incoming Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Control</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getIncomingControl()
	 * @see #getActionExecution()
	 * @generated
	 */
	EReference getActionExecution_IncomingControl();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutgoingControl <em>Outgoing Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Control</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutgoingControl()
	 * @see #getActionExecution()
	 * @generated
	 */
	EReference getActionExecution_OutgoingControl();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution <em>Control Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution
	 * @generated
	 */
	EClass getControlNodeExecution();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution#getRoutedTokens <em>Routed Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Routed Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution#getRoutedTokens()
	 * @see #getControlNodeExecution()
	 * @generated
	 */
	EReference getControlNodeExecution_RoutedTokens();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
	 * @model instanceClass="fUML.Syntax.Activities.IntermediateActivities.Activity" serializeable="false"
	 * @generated
	 */
	EDataType getActivity();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.ActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Node</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
	 * @model instanceClass="fUML.Syntax.Activities.IntermediateActivities.ActivityNode" serializeable="false"
	 * @generated
	 */
	EDataType getActivityNode();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Behavior</em>'.
	 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
	 * @model instanceClass="fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior" serializeable="false"
	 * @generated
	 */
	EDataType getBehavior();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Classes.Kernel.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Parameter</em>'.
	 * @see fUML.Syntax.Classes.Kernel.Parameter
	 * @model instanceClass="fUML.Syntax.Classes.Kernel.Parameter" serializeable="false"
	 * @generated
	 */
	EDataType getParameter();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Actions.BasicActions.InputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Input Pin</em>'.
	 * @see fUML.Syntax.Actions.BasicActions.InputPin
	 * @model instanceClass="fUML.Syntax.Actions.BasicActions.InputPin" serializeable="false"
	 * @generated
	 */
	EDataType getInputPin();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Actions.BasicActions.OutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Output Pin</em>'.
	 * @see fUML.Syntax.Actions.BasicActions.OutputPin
	 * @model instanceClass="fUML.Syntax.Actions.BasicActions.OutputPin" serializeable="false"
	 * @generated
	 */
	EDataType getOutputPin();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.ActivityEdge <em>Activity Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Edge</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
	 * @model instanceClass="fUML.Syntax.Activities.IntermediateActivities.ActivityEdge" serializeable="false"
	 * @generated
	 */
	EDataType getActivityEdge();

	/**
	 * Returns the meta object for data type '{@link fUML.Semantics.Classes.Kernel.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Value</em>'.
	 * @see fUML.Semantics.Classes.Kernel.Value
	 * @model instanceClass="fUML.Semantics.Classes.Kernel.Value" serializeable="false"
	 * @generated
	 */
	EDataType getValue();

	/**
	 * Returns the meta object for data type '{@link fUML.Semantics.Classes.Kernel.PrimitiveValue <em>Primitive Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Primitive Value</em>'.
	 * @see fUML.Semantics.Classes.Kernel.PrimitiveValue
	 * @model instanceClass="fUML.Semantics.Classes.Kernel.PrimitiveValue"
	 * @generated
	 */
	EDataType getPrimitiveValue();

	/**
	 * Returns the meta object for data type '{@link fUML.Semantics.Classes.Kernel.Object_ <em>Object </em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object </em>'.
	 * @see fUML.Semantics.Classes.Kernel.Object_
	 * @model instanceClass="fUML.Semantics.Classes.Kernel.Object_"
	 * @generated
	 */
	EDataType getObject_();

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode <em>Expansion Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Expansion Node</em>'.
	 * @see fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode
	 * @model instanceClass="fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode"
	 * @generated
	 */
	EDataType getExpansionNode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TracemodelFactory getTracemodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Activity Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__ACTIVITY_EXECUTIONS = eINSTANCE.getTrace_ActivityExecutions();

		/**
		 * The meta object literal for the '<em><b>Value Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__VALUE_INSTANCES = eINSTANCE.getTrace_ValueInstances();

		/**
		 * The meta object literal for the '<em><b>Initial Locus Value Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__INITIAL_LOCUS_VALUE_INSTANCES = eINSTANCE.getTrace_InitialLocusValueInstances();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl <em>Activity Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityExecution()
		 * @generated
		 */
		EClass ACTIVITY_EXECUTION = eINSTANCE.getActivityExecution();

		/**
		 * The meta object literal for the '<em><b>Activity Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__ACTIVITY_INPUTS = eINSTANCE.getActivityExecution_ActivityInputs();

		/**
		 * The meta object literal for the '<em><b>Node Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__NODE_EXECUTIONS = eINSTANCE.getActivityExecution_NodeExecutions();

		/**
		 * The meta object literal for the '<em><b>Caller</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__CALLER = eINSTANCE.getActivityExecution_Caller();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_EXECUTION__ACTIVITY = eINSTANCE.getActivityExecution_Activity();

		/**
		 * The meta object literal for the '<em><b>Activity Execution ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID = eINSTANCE.getActivityExecution_ActivityExecutionID();

		/**
		 * The meta object literal for the '<em><b>Trace</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__TRACE = eINSTANCE.getActivityExecution_Trace();

		/**
		 * The meta object literal for the '<em><b>Activity Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__ACTIVITY_OUTPUTS = eINSTANCE.getActivityExecution_ActivityOutputs();

		/**
		 * The meta object literal for the '<em><b>Context Value Snapshot</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_EXECUTION__CONTEXT_VALUE_SNAPSHOT = eINSTANCE.getActivityExecution_ContextValueSnapshot();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl <em>Activity Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNodeExecution()
		 * @generated
		 */
		EClass ACTIVITY_NODE_EXECUTION = eINSTANCE.getActivityNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Logical Successor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = eINSTANCE.getActivityNodeExecution_LogicalSuccessor();

		/**
		 * The meta object literal for the '<em><b>Logical Predecessor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = eINSTANCE.getActivityNodeExecution_LogicalPredecessor();

		/**
		 * The meta object literal for the '<em><b>Chronological Successor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = eINSTANCE.getActivityNodeExecution_ChronologicalSuccessor();

		/**
		 * The meta object literal for the '<em><b>Chronological Predecessor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = eINSTANCE.getActivityNodeExecution_ChronologicalPredecessor();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_NODE_EXECUTION__NODE = eINSTANCE.getActivityNodeExecution_Node();

		/**
		 * The meta object literal for the '<em><b>Activity Execution</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = eINSTANCE.getActivityNodeExecution_ActivityExecution();

		/**
		 * The meta object literal for the '<em><b>Executed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_NODE_EXECUTION__EXECUTED = eINSTANCE.getActivityNodeExecution_Executed();

		/**
		 * The meta object literal for the '<em><b>Under Execution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION = eINSTANCE.getActivityNodeExecution_UnderExecution();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl <em>Call Action Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActionExecution()
		 * @generated
		 */
		EClass CALL_ACTION_EXECUTION = eINSTANCE.getCallActionExecution();

		/**
		 * The meta object literal for the '<em><b>Callee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_ACTION_EXECUTION__CALLEE = eINSTANCE.getCallActionExecution_Callee();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterSettingImpl <em>Parameter Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterSettingImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterSetting()
		 * @generated
		 */
		EClass PARAMETER_SETTING = eINSTANCE.getParameterSetting();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_SETTING__PARAMETER = eINSTANCE.getParameterSetting_Parameter();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInput()
		 * @generated
		 */
		EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '<em><b>Input Pin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__INPUT_PIN = eINSTANCE.getInput_InputPin();

		/**
		 * The meta object literal for the '<em><b>Input Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT__INPUT_VALUES = eINSTANCE.getInput_InputValues();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutput()
		 * @generated
		 */
		EClass OUTPUT = eINSTANCE.getOutput();

		/**
		 * The meta object literal for the '<em><b>Output Pin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTPUT__OUTPUT_PIN = eINSTANCE.getOutput_OutputPin();

		/**
		 * The meta object literal for the '<em><b>Output Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT__OUTPUT_VALUES = eINSTANCE.getOutput_OutputValues();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl <em>Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTokenInstance()
		 * @generated
		 */
		EClass TOKEN_INSTANCE = eINSTANCE.getTokenInstance();

		/**
		 * The meta object literal for the '<em><b>Traversed Edges</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOKEN_INSTANCE__TRAVERSED_EDGES = eINSTANCE.getTokenInstance_TraversedEdges();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl <em>Object Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObjectTokenInstance()
		 * @generated
		 */
		EClass OBJECT_TOKEN_INSTANCE = eINSTANCE.getObjectTokenInstance();

		/**
		 * The meta object literal for the '<em><b>Transported Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE = eINSTANCE.getObjectTokenInstance_TransportedValue();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl <em>Control Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlTokenInstance()
		 * @generated
		 */
		EClass CONTROL_TOKEN_INSTANCE = eINSTANCE.getControlTokenInstance();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl <em>Value Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstance()
		 * @generated
		 */
		EClass VALUE_INSTANCE = eINSTANCE.getValueInstance();

		/**
		 * The meta object literal for the '<em><b>Runtime Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_INSTANCE__RUNTIME_VALUE = eINSTANCE.getValueInstance_RuntimeValue();

		/**
		 * The meta object literal for the '<em><b>Snapshots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_INSTANCE__SNAPSHOTS = eINSTANCE.getValueInstance_Snapshots();

		/**
		 * The meta object literal for the '<em><b>Original</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_INSTANCE__ORIGINAL = eINSTANCE.getValueInstance_Original();

		/**
		 * The meta object literal for the '<em><b>Creator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_INSTANCE__CREATOR = eINSTANCE.getValueInstance_Creator();

		/**
		 * The meta object literal for the '<em><b>Destroyer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_INSTANCE__DESTROYER = eINSTANCE.getValueInstance_Destroyer();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueSnapshotImpl <em>Value Snapshot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueSnapshotImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueSnapshot()
		 * @generated
		 */
		EClass VALUE_SNAPSHOT = eINSTANCE.getValueSnapshot();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_SNAPSHOT__VALUE = eINSTANCE.getValueSnapshot_Value();

		/**
		 * The meta object literal for the '<em><b>Value Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_SNAPSHOT__VALUE_INSTANCE = eINSTANCE.getValueSnapshot_ValueInstance();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl <em>Decision Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.DecisionNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getDecisionNodeExecution()
		 * @generated
		 */
		EClass DECISION_NODE_EXECUTION = eINSTANCE.getDecisionNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Decision Input Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE = eINSTANCE.getDecisionNodeExecution_DecisionInputValue();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.StructuredActivityNodeExecutionImpl <em>Structured Activity Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.StructuredActivityNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getStructuredActivityNodeExecution()
		 * @generated
		 */
		EClass STRUCTURED_ACTIVITY_NODE_EXECUTION = eINSTANCE.getStructuredActivityNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Nested Node Executions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS = eINSTANCE.getStructuredActivityNodeExecution_NestedNodeExecutions();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl <em>Input Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputValue()
		 * @generated
		 */
		EClass INPUT_VALUE = eINSTANCE.getInputValue();

		/**
		 * The meta object literal for the '<em><b>Input Object Token</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_VALUE__INPUT_OBJECT_TOKEN = eINSTANCE.getInputValue_InputObjectToken();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputValueImpl <em>Output Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputValue()
		 * @generated
		 */
		EClass OUTPUT_VALUE = eINSTANCE.getOutputValue();

		/**
		 * The meta object literal for the '<em><b>Output Object Token</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN = eINSTANCE.getOutputValue_OutputObjectToken();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterValueImpl <em>Input Parameter Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputParameterValue()
		 * @generated
		 */
		EClass INPUT_PARAMETER_VALUE = eINSTANCE.getInputParameterValue();

		/**
		 * The meta object literal for the '<em><b>Parameter Input Object Token</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_PARAMETER_VALUE__PARAMETER_INPUT_OBJECT_TOKEN = eINSTANCE.getInputParameterValue_ParameterInputObjectToken();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterSettingImpl <em>Input Parameter Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputParameterSettingImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputParameterSetting()
		 * @generated
		 */
		EClass INPUT_PARAMETER_SETTING = eINSTANCE.getInputParameterSetting();

		/**
		 * The meta object literal for the '<em><b>Parameter Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_PARAMETER_SETTING__PARAMETER_VALUES = eINSTANCE.getInputParameterSetting_ParameterValues();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterSettingImpl <em>Output Parameter Setting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterSettingImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputParameterSetting()
		 * @generated
		 */
		EClass OUTPUT_PARAMETER_SETTING = eINSTANCE.getOutputParameterSetting();

		/**
		 * The meta object literal for the '<em><b>Parameter Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_PARAMETER_SETTING__PARAMETER_VALUES = eINSTANCE.getOutputParameterSetting_ParameterValues();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterValueImpl <em>Output Parameter Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputParameterValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputParameterValue()
		 * @generated
		 */
		EClass OUTPUT_PARAMETER_VALUE = eINSTANCE.getOutputParameterValue();

		/**
		 * The meta object literal for the '<em><b>Parameter Output Object Token</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_PARAMETER_VALUE__PARAMETER_OUTPUT_OBJECT_TOKEN = eINSTANCE.getOutputParameterValue_ParameterOutputObjectToken();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InitialNodeExecutionImpl <em>Initial Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InitialNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInitialNodeExecution()
		 * @generated
		 */
		EClass INITIAL_NODE_EXECUTION = eINSTANCE.getInitialNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Outgoing Control</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INITIAL_NODE_EXECUTION__OUTGOING_CONTROL = eINSTANCE.getInitialNodeExecution_OutgoingControl();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterValueImpl <em>Parameter Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterValue()
		 * @generated
		 */
		EClass PARAMETER_VALUE = eINSTANCE.getParameterValue();

		/**
		 * The meta object literal for the '<em><b>Value Snapshot</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_VALUE__VALUE_SNAPSHOT = eINSTANCE.getParameterValue_ValueSnapshot();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionInputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionInput()
		 * @generated
		 */
		EClass EXPANSION_INPUT = eINSTANCE.getExpansionInput();

		/**
		 * The meta object literal for the '<em><b>Expansion Input Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPANSION_INPUT__EXPANSION_INPUT_VALUES = eINSTANCE.getExpansionInput_ExpansionInputValues();

		/**
		 * The meta object literal for the '<em><b>Expansion Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPANSION_INPUT__EXPANSION_NODE = eINSTANCE.getExpansionInput_ExpansionNode();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionRegionExecutionImpl <em>Expansion Region Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ExpansionRegionExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionRegionExecution()
		 * @generated
		 */
		EClass EXPANSION_REGION_EXECUTION = eINSTANCE.getExpansionRegionExecution();

		/**
		 * The meta object literal for the '<em><b>Expansion Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPANSION_REGION_EXECUTION__EXPANSION_INPUTS = eINSTANCE.getExpansionRegionExecution_ExpansionInputs();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputOutputValueImpl <em>Input Output Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputOutputValueImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputOutputValue()
		 * @generated
		 */
		EClass INPUT_OUTPUT_VALUE = eINSTANCE.getInputOutputValue();

		/**
		 * The meta object literal for the '<em><b>Value Snapshot</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_OUTPUT_VALUE__VALUE_SNAPSHOT = eINSTANCE.getInputOutputValue_ValueSnapshot();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl <em>Action Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActionExecution()
		 * @generated
		 */
		EClass ACTION_EXECUTION = eINSTANCE.getActionExecution();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_EXECUTION__INPUTS = eINSTANCE.getActionExecution_Inputs();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_EXECUTION__OUTPUTS = eINSTANCE.getActionExecution_Outputs();

		/**
		 * The meta object literal for the '<em><b>Incoming Control</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_EXECUTION__INCOMING_CONTROL = eINSTANCE.getActionExecution_IncomingControl();

		/**
		 * The meta object literal for the '<em><b>Outgoing Control</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_EXECUTION__OUTGOING_CONTROL = eINSTANCE.getActionExecution_OutgoingControl();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl <em>Control Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlNodeExecution()
		 * @generated
		 */
		EClass CONTROL_NODE_EXECUTION = eINSTANCE.getControlNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Routed Tokens</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_NODE_EXECUTION__ROUTED_TOKENS = eINSTANCE.getControlNodeExecution_RoutedTokens();

		/**
		 * The meta object literal for the '<em>Activity</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivity()
		 * @generated
		 */
		EDataType ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em>Activity Node</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNode()
		 * @generated
		 */
		EDataType ACTIVITY_NODE = eINSTANCE.getActivityNode();

		/**
		 * The meta object literal for the '<em>Behavior</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getBehavior()
		 * @generated
		 */
		EDataType BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em>Parameter</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Classes.Kernel.Parameter
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameter()
		 * @generated
		 */
		EDataType PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em>Input Pin</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Actions.BasicActions.InputPin
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputPin()
		 * @generated
		 */
		EDataType INPUT_PIN = eINSTANCE.getInputPin();

		/**
		 * The meta object literal for the '<em>Output Pin</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Actions.BasicActions.OutputPin
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputPin()
		 * @generated
		 */
		EDataType OUTPUT_PIN = eINSTANCE.getOutputPin();

		/**
		 * The meta object literal for the '<em>Activity Edge</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityEdge()
		 * @generated
		 */
		EDataType ACTIVITY_EDGE = eINSTANCE.getActivityEdge();

		/**
		 * The meta object literal for the '<em>Value</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Semantics.Classes.Kernel.Value
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValue()
		 * @generated
		 */
		EDataType VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em>Primitive Value</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Semantics.Classes.Kernel.PrimitiveValue
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getPrimitiveValue()
		 * @generated
		 */
		EDataType PRIMITIVE_VALUE = eINSTANCE.getPrimitiveValue();

		/**
		 * The meta object literal for the '<em>Object </em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Semantics.Classes.Kernel.Object_
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObject_()
		 * @generated
		 */
		EDataType OBJECT_ = eINSTANCE.getObject_();

		/**
		 * The meta object literal for the '<em>Expansion Node</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getExpansionNode()
		 * @generated
		 */
		EDataType EXPANSION_NODE = eINSTANCE.getExpansionNode();

	}

} //TracemodelPackage
