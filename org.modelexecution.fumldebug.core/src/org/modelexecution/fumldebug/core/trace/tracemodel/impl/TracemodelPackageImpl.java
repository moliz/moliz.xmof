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

import fUML.Semantics.Classes.Kernel.Value;

import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;

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
 * @generated
 */
public class TracemodelPackageImpl extends EPackageImpl {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "tracemodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://tracemodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "tracemodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TracemodelPackageImpl eINSTANCE = org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTrace()
	 * @generated
	 */
	public static final int TRACE = 0;

	/**
	 * The feature id for the '<em><b>Activity Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRACE__ACTIVITY_EXECUTIONS = 0;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRACE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl <em>Activity Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityExecution()
	 * @generated
	 */
	public static final int ACTIVITY_EXECUTION = 1;

	/**
	 * The feature id for the '<em><b>Parameter Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__PARAMETER_INPUTS = 0;

	/**
	 * The feature id for the '<em><b>Parameter Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__PARAMETER_OUTPUTS = 1;

	/**
	 * The feature id for the '<em><b>Node Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__NODE_EXECUTIONS = 2;

	/**
	 * The feature id for the '<em><b>Caller</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__CALLER = 3;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Activity Execution ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID = 5;

	/**
	 * The number of structural features of the '<em>Activity Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl <em>Activity Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNodeExecution()
	 * @generated
	 */
	public static final int ACTIVITY_NODE_EXECUTION = 2;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__INPUTS = 0;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__OUTPUTS = 1;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = 2;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = 3;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = 4;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = 5;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__NODE = 6;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = 7;

	/**
	 * The number of structural features of the '<em>Activity Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActivityNodeExecutionImpl <em>Call Activity Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActivityNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActivityNodeExecution()
	 * @generated
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION = 3;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__INPUTS = ACTIVITY_NODE_EXECUTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__OUTPUTS = ACTIVITY_NODE_EXECUTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__NODE = ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Callee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__CALLEE = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Called Behavior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION__CALLED_BEHAVIOR = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Call Activity Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTIVITY_NODE_EXECUTION_FEATURE_COUNT = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl <em>Parameter Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterInput()
	 * @generated
	 */
	public static final int PARAMETER_INPUT = 5;

	/**
	 * The feature id for the '<em><b>Parameter Input Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_INPUT__PARAMETER_INPUT_TOKENS = 0;

	/**
	 * The feature id for the '<em><b>Input Parameter Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_INPUT__INPUT_PARAMETER_NODE = 1;

	/**
	 * The number of structural features of the '<em>Parameter Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl <em>User Parameter Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getUserParameterInput()
	 * @generated
	 */
	public static final int USER_PARAMETER_INPUT = 4;

	/**
	 * The feature id for the '<em><b>Parameter Input Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT__PARAMETER_INPUT_TOKENS = PARAMETER_INPUT__PARAMETER_INPUT_TOKENS;

	/**
	 * The feature id for the '<em><b>Input Parameter Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE = PARAMETER_INPUT__INPUT_PARAMETER_NODE;

	/**
	 * The feature id for the '<em><b>User Input Tokens</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT__USER_INPUT_TOKENS = PARAMETER_INPUT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Parameter Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT_FEATURE_COUNT = PARAMETER_INPUT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl <em>Parameter Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterOutput()
	 * @generated
	 */
	public static final int PARAMETER_OUTPUT = 6;

	/**
	 * The feature id for the '<em><b>Parameter Output Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS = 0;

	/**
	 * The feature id for the '<em><b>Output Parameter Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE = 1;

	/**
	 * The number of structural features of the '<em>Parameter Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_OUTPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInput()
	 * @generated
	 */
	public static final int INPUT = 7;

	/**
	 * The feature id for the '<em><b>Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INPUT__TOKENS = 0;

	/**
	 * The feature id for the '<em><b>Input Pin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INPUT__INPUT_PIN = 1;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutput()
	 * @generated
	 */
	public static final int OUTPUT = 8;

	/**
	 * The feature id for the '<em><b>Tokens</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUT__TOKENS = 0;

	/**
	 * The feature id for the '<em><b>Output Pin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUT__OUTPUT_PIN = 1;

	/**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl <em>Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTokenInstance()
	 * @generated
	 */
	public static final int TOKEN_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TOKEN_INSTANCE__TRAVERSED_EDGES = 0;

	/**
	 * The number of structural features of the '<em>Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TOKEN_INSTANCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl <em>Object Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObjectTokenInstance()
	 * @generated
	 */
	public static final int OBJECT_TOKEN_INSTANCE = 10;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES = TOKEN_INSTANCE__TRAVERSED_EDGES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_TOKEN_INSTANCE__VALUE = TOKEN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_TOKEN_INSTANCE_FEATURE_COUNT = TOKEN_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl <em>Control Token Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlTokenInstance()
	 * @generated
	 */
	public static final int CONTROL_TOKEN_INSTANCE = 11;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_TOKEN_INSTANCE__TRAVERSED_EDGES = TOKEN_INSTANCE__TRAVERSED_EDGES;

	/**
	 * The number of structural features of the '<em>Control Token Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_TOKEN_INSTANCE_FEATURE_COUNT = TOKEN_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl <em>Value Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstance()
	 * @generated
	 */
	public static final int VALUE_INSTANCE = 12;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Value Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '<em>Activity</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivity()
	 * @generated
	 */
	public static final int ACTIVITY = 13;

	/**
	 * The meta object id for the '<em>Activity Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNode()
	 * @generated
	 */
	public static final int ACTIVITY_NODE = 14;

	/**
	 * The meta object id for the '<em>Behavior</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getBehavior()
	 * @generated
	 */
	public static final int BEHAVIOR = 15;

	/**
	 * The meta object id for the '<em>Activity Parameter Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityParameterNode()
	 * @generated
	 */
	public static final int ACTIVITY_PARAMETER_NODE = 16;

	/**
	 * The meta object id for the '<em>Input Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.InputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputPin()
	 * @generated
	 */
	public static final int INPUT_PIN = 17;

	/**
	 * The meta object id for the '<em>Output Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.OutputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputPin()
	 * @generated
	 */
	public static final int OUTPUT_PIN = 18;

	/**
	 * The meta object id for the '<em>Activity Edge</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityEdge()
	 * @generated
	 */
	public static final int ACTIVITY_EDGE = 19;

	/**
	 * The meta object id for the '<em>Value</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Semantics.Classes.Kernel.Value
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValue()
	 * @generated
	 */
	public static final int VALUE = 20;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callActivityNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userParameterInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterOutputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectTokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlTokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType behaviorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityParameterNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType inputPinEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType outputPinEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityEdgeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType valueEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TracemodelPackageImpl() {
		super(eNS_URI, ((EFactory)TracemodelFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TracemodelPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TracemodelPackageImpl init() {
		if (isInited) return (TracemodelPackageImpl)EPackage.Registry.INSTANCE.getEPackage(TracemodelPackageImpl.eNS_URI);

		// Obtain or create and register package
		TracemodelPackageImpl theTracemodelPackage = (TracemodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracemodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracemodelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTracemodelPackage.createPackageContents();

		// Initialize created meta-data
		theTracemodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTracemodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TracemodelPackageImpl.eNS_URI, theTracemodelPackage);
		return theTracemodelPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activity Executions</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions()
	 * @see #getTrace()
	 * @generated
	 */
	public EReference getTrace_ActivityExecutions() {
		return (EReference)traceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution
	 * @generated
	 */
	public EClass getActivityExecution() {
		return activityExecutionEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterInputs <em>Parameter Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterInputs()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_ParameterInputs() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterOutputs <em>Parameter Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Outputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getParameterOutputs()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_ParameterOutputs() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions <em>Node Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node Executions</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getNodeExecutions()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_NodeExecutions() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller <em>Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Caller</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getCaller()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_Caller() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivity()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EAttribute getActivityExecution_Activity() {
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID <em>Activity Execution ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Execution ID</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getActivityExecutionID()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EAttribute getActivityExecution_ActivityExecutionID() {
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution <em>Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution
	 * @generated
	 */
	public EClass getActivityNodeExecution() {
		return activityNodeExecutionEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getInputs()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_Inputs() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getOutputs()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_Outputs() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Successor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_LogicalSuccessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor <em>Logical Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Predecessor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalPredecessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_LogicalPredecessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor <em>Chronological Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Chronological Successor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalSuccessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_ChronologicalSuccessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor <em>Chronological Predecessor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Chronological Predecessor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getChronologicalPredecessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_ChronologicalPredecessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getNode()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EAttribute getActivityNodeExecution_Node() {
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * Returns the meta object for the container reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Activity Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getActivityExecution()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_ActivityExecution() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(7);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution <em>Call Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Activity Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution
	 * @generated
	 */
	public EClass getCallActivityNodeExecution() {
		return callActivityNodeExecutionEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callee</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCallee()
	 * @see #getCallActivityNodeExecution()
	 * @generated
	 */
	public EReference getCallActivityNodeExecution_Callee() {
		return (EReference)callActivityNodeExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCalledBehavior <em>Called Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Behavior</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActivityNodeExecution#getCalledBehavior()
	 * @see #getCallActivityNodeExecution()
	 * @generated
	 */
	public EAttribute getCallActivityNodeExecution_CalledBehavior() {
		return (EAttribute)callActivityNodeExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput <em>User Parameter Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Parameter Input</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput
	 * @generated
	 */
	public EClass getUserParameterInput() {
		return userParameterInputEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput#getUserInputTokens <em>User Input Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>User Input Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput#getUserInputTokens()
	 * @see #getUserParameterInput()
	 * @generated
	 */
	public EReference getUserParameterInput_UserInputTokens() {
		return (EReference)userParameterInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput <em>Parameter Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Input</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput
	 * @generated
	 */
	public EClass getParameterInput() {
		return parameterInputEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getParameterInputTokens <em>Parameter Input Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter Input Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getParameterInputTokens()
	 * @see #getParameterInput()
	 * @generated
	 */
	public EReference getParameterInput_ParameterInputTokens() {
		return (EReference)parameterInputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getInputParameterNode <em>Input Parameter Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Parameter Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput#getInputParameterNode()
	 * @see #getParameterInput()
	 * @generated
	 */
	public EAttribute getParameterInput_InputParameterNode() {
		return (EAttribute)parameterInputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput <em>Parameter Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Output</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput
	 * @generated
	 */
	public EClass getParameterOutput() {
		return parameterOutputEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput#getParameterOutputTokens <em>Parameter Output Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter Output Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput#getParameterOutputTokens()
	 * @see #getParameterOutput()
	 * @generated
	 */
	public EReference getParameterOutput_ParameterOutputTokens() {
		return (EReference)parameterOutputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput#getOutputParameterNode <em>Output Parameter Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Parameter Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ParameterOutput#getOutputParameterNode()
	 * @see #getParameterOutput()
	 * @generated
	 */
	public EAttribute getParameterOutput_OutputParameterNode() {
		return (EAttribute)parameterOutputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input
	 * @generated
	 */
	public EClass getInput() {
		return inputEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getTokens <em>Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input#getTokens()
	 * @see #getInput()
	 * @generated
	 */
	public EReference getInput_Tokens() {
		return (EReference)inputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Pin</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input#getInputPin()
	 * @see #getInput()
	 * @generated
	 */
	public EAttribute getInput_InputPin() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output
	 * @generated
	 */
	public EClass getOutput() {
		return outputEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output#getTokens <em>Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output#getTokens()
	 * @see #getOutput()
	 * @generated
	 */
	public EReference getOutput_Tokens() {
		return (EReference)outputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Pin</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Output#getOutputPin()
	 * @see #getOutput()
	 * @generated
	 */
	public EAttribute getOutput_OutputPin() {
		return (EAttribute)outputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance <em>Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance
	 * @generated
	 */
	public EClass getTokenInstance() {
		return tokenInstanceEClass;
	}

	/**
	 * Returns the meta object for the attribute list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance#getTraversedEdges <em>Traversed Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Traversed Edges</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance#getTraversedEdges()
	 * @see #getTokenInstance()
	 * @generated
	 */
	public EAttribute getTokenInstance_TraversedEdges() {
		return (EAttribute)tokenInstanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance <em>Object Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance
	 * @generated
	 */
	public EClass getObjectTokenInstance() {
		return objectTokenInstanceEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getValue()
	 * @see #getObjectTokenInstance()
	 * @generated
	 */
	public EReference getObjectTokenInstance_Value() {
		return (EReference)objectTokenInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance <em>Control Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Token Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance
	 * @generated
	 */
	public EClass getControlTokenInstance() {
		return controlTokenInstanceEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance
	 * @generated
	 */
	public EClass getValueInstance() {
		return valueInstanceEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValue()
	 * @see #getValueInstance()
	 * @generated
	 */
	public EAttribute getValueInstance_Value() {
		return (EAttribute)valueInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
	 * @generated
	 */
	public EDataType getActivity() {
		return activityEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.ActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Node</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
	 * @generated
	 */
	public EDataType getActivityNode() {
		return activityNodeEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Behavior</em>'.
	 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
	 * @generated
	 */
	public EDataType getBehavior() {
		return behaviorEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode <em>Activity Parameter Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Parameter Node</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode
	 * @generated
	 */
	public EDataType getActivityParameterNode() {
		return activityParameterNodeEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Actions.BasicActions.InputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Input Pin</em>'.
	 * @see fUML.Syntax.Actions.BasicActions.InputPin
	 * @generated
	 */
	public EDataType getInputPin() {
		return inputPinEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Actions.BasicActions.OutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Output Pin</em>'.
	 * @see fUML.Syntax.Actions.BasicActions.OutputPin
	 * @generated
	 */
	public EDataType getOutputPin() {
		return outputPinEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Syntax.Activities.IntermediateActivities.ActivityEdge <em>Activity Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Activity Edge</em>'.
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
	 * @generated
	 */
	public EDataType getActivityEdge() {
		return activityEdgeEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link fUML.Semantics.Classes.Kernel.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Value</em>'.
	 * @see fUML.Semantics.Classes.Kernel.Value
	 * @generated
	 */
	public EDataType getValue() {
		return valueEDataType;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public TracemodelFactory getTracemodelFactory() {
		return (TracemodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		traceEClass = createEClass(TRACE);
		createEReference(traceEClass, TRACE__ACTIVITY_EXECUTIONS);

		activityExecutionEClass = createEClass(ACTIVITY_EXECUTION);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__PARAMETER_INPUTS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__PARAMETER_OUTPUTS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__NODE_EXECUTIONS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__CALLER);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID);

		activityNodeExecutionEClass = createEClass(ACTIVITY_NODE_EXECUTION);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__INPUTS);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__OUTPUTS);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__NODE);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION);

		callActivityNodeExecutionEClass = createEClass(CALL_ACTIVITY_NODE_EXECUTION);
		createEReference(callActivityNodeExecutionEClass, CALL_ACTIVITY_NODE_EXECUTION__CALLEE);
		createEAttribute(callActivityNodeExecutionEClass, CALL_ACTIVITY_NODE_EXECUTION__CALLED_BEHAVIOR);

		userParameterInputEClass = createEClass(USER_PARAMETER_INPUT);
		createEReference(userParameterInputEClass, USER_PARAMETER_INPUT__USER_INPUT_TOKENS);

		parameterInputEClass = createEClass(PARAMETER_INPUT);
		createEReference(parameterInputEClass, PARAMETER_INPUT__PARAMETER_INPUT_TOKENS);
		createEAttribute(parameterInputEClass, PARAMETER_INPUT__INPUT_PARAMETER_NODE);

		parameterOutputEClass = createEClass(PARAMETER_OUTPUT);
		createEReference(parameterOutputEClass, PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS);
		createEAttribute(parameterOutputEClass, PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE);

		inputEClass = createEClass(INPUT);
		createEReference(inputEClass, INPUT__TOKENS);
		createEAttribute(inputEClass, INPUT__INPUT_PIN);

		outputEClass = createEClass(OUTPUT);
		createEReference(outputEClass, OUTPUT__TOKENS);
		createEAttribute(outputEClass, OUTPUT__OUTPUT_PIN);

		tokenInstanceEClass = createEClass(TOKEN_INSTANCE);
		createEAttribute(tokenInstanceEClass, TOKEN_INSTANCE__TRAVERSED_EDGES);

		objectTokenInstanceEClass = createEClass(OBJECT_TOKEN_INSTANCE);
		createEReference(objectTokenInstanceEClass, OBJECT_TOKEN_INSTANCE__VALUE);

		controlTokenInstanceEClass = createEClass(CONTROL_TOKEN_INSTANCE);

		valueInstanceEClass = createEClass(VALUE_INSTANCE);
		createEAttribute(valueInstanceEClass, VALUE_INSTANCE__VALUE);

		// Create data types
		activityEDataType = createEDataType(ACTIVITY);
		activityNodeEDataType = createEDataType(ACTIVITY_NODE);
		behaviorEDataType = createEDataType(BEHAVIOR);
		activityParameterNodeEDataType = createEDataType(ACTIVITY_PARAMETER_NODE);
		inputPinEDataType = createEDataType(INPUT_PIN);
		outputPinEDataType = createEDataType(OUTPUT_PIN);
		activityEdgeEDataType = createEDataType(ACTIVITY_EDGE);
		valueEDataType = createEDataType(VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		callActivityNodeExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());
		userParameterInputEClass.getESuperTypes().add(this.getParameterInput());
		objectTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());
		controlTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(traceEClass, Trace.class, "Trace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrace_ActivityExecutions(), this.getActivityExecution(), null, "activityExecutions", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityExecutionEClass, ActivityExecution.class, "ActivityExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityExecution_ParameterInputs(), this.getParameterInput(), null, "parameterInputs", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_ParameterOutputs(), this.getParameterOutput(), null, "parameterOutputs", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_NodeExecutions(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ActivityExecution(), "nodeExecutions", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_Caller(), this.getCallActivityNodeExecution(), this.getCallActivityNodeExecution_Callee(), "caller", null, 0, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_Activity(), this.getActivity(), "activity", null, 1, 1, ActivityExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_ActivityExecutionID(), ecorePackage.getEInt(), "activityExecutionID", null, 1, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityNodeExecutionEClass, ActivityNodeExecution.class, "ActivityNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityNodeExecution_Inputs(), this.getInput(), null, "inputs", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_Outputs(), this.getOutput(), null, "outputs", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_LogicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalPredecessor(), "logicalSuccessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_LogicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalSuccessor(), "logicalPredecessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalPredecessor(), "chronologicalSuccessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalSuccessor(), "chronologicalPredecessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityNodeExecution_Node(), this.getActivityNode(), "node", null, 1, 1, ActivityNodeExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ActivityExecution(), this.getActivityExecution(), this.getActivityExecution_NodeExecutions(), "activityExecution", null, 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callActivityNodeExecutionEClass, CallActivityNodeExecution.class, "CallActivityNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallActivityNodeExecution_Callee(), this.getActivityExecution(), this.getActivityExecution_Caller(), "callee", null, 0, 1, CallActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCallActivityNodeExecution_CalledBehavior(), this.getBehavior(), "calledBehavior", null, 1, 1, CallActivityNodeExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userParameterInputEClass, UserParameterInput.class, "UserParameterInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserParameterInput_UserInputTokens(), this.getObjectTokenInstance(), null, "userInputTokens", null, 0, -1, UserParameterInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterInputEClass, ParameterInput.class, "ParameterInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterInput_ParameterInputTokens(), this.getObjectTokenInstance(), null, "parameterInputTokens", null, 0, -1, ParameterInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterInput_InputParameterNode(), this.getActivityParameterNode(), "inputParameterNode", null, 1, 1, ParameterInput.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterOutputEClass, ParameterOutput.class, "ParameterOutput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterOutput_ParameterOutputTokens(), this.getObjectTokenInstance(), null, "parameterOutputTokens", null, 0, -1, ParameterOutput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterOutput_OutputParameterNode(), this.getActivityParameterNode(), "outputParameterNode", null, 1, 1, ParameterOutput.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInput_Tokens(), this.getTokenInstance(), null, "tokens", null, 0, -1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_InputPin(), this.getInputPin(), "inputPin", null, 0, 1, Input.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutput_Tokens(), this.getTokenInstance(), null, "tokens", null, 0, -1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutput_OutputPin(), this.getOutputPin(), "outputPin", null, 0, 1, Output.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tokenInstanceEClass, TokenInstance.class, "TokenInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTokenInstance_TraversedEdges(), this.getActivityEdge(), "traversedEdges", null, 0, -1, TokenInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(objectTokenInstanceEClass, ObjectTokenInstance.class, "ObjectTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObjectTokenInstance_Value(), this.getValueInstance(), null, "value", null, 1, 1, ObjectTokenInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlTokenInstanceEClass, ControlTokenInstance.class, "ControlTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueInstanceEClass, ValueInstance.class, "ValueInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueInstance_Value(), this.getValue(), "value", null, 1, 1, ValueInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(activityEDataType, Activity.class, "Activity", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityNodeEDataType, ActivityNode.class, "ActivityNode", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(behaviorEDataType, Behavior.class, "Behavior", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityParameterNodeEDataType, ActivityParameterNode.class, "ActivityParameterNode", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(inputPinEDataType, InputPin.class, "InputPin", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(outputPinEDataType, OutputPin.class, "OutputPin", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityEdgeEDataType, ActivityEdge.class, "ActivityEdge", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(valueEDataType, Value.class, "Value", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

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
	public interface Literals {
		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTrace()
		 * @generated
		 */
		public static final EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Activity Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TRACE__ACTIVITY_EXECUTIONS = eINSTANCE.getTrace_ActivityExecutions();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl <em>Activity Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityExecution()
		 * @generated
		 */
		public static final EClass ACTIVITY_EXECUTION = eINSTANCE.getActivityExecution();

		/**
		 * The meta object literal for the '<em><b>Parameter Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__PARAMETER_INPUTS = eINSTANCE.getActivityExecution_ParameterInputs();

		/**
		 * The meta object literal for the '<em><b>Parameter Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__PARAMETER_OUTPUTS = eINSTANCE.getActivityExecution_ParameterOutputs();

		/**
		 * The meta object literal for the '<em><b>Node Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__NODE_EXECUTIONS = eINSTANCE.getActivityExecution_NodeExecutions();

		/**
		 * The meta object literal for the '<em><b>Caller</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__CALLER = eINSTANCE.getActivityExecution_Caller();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ACTIVITY_EXECUTION__ACTIVITY = eINSTANCE.getActivityExecution_Activity();

		/**
		 * The meta object literal for the '<em><b>Activity Execution ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID = eINSTANCE.getActivityExecution_ActivityExecutionID();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl <em>Activity Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNodeExecution()
		 * @generated
		 */
		public static final EClass ACTIVITY_NODE_EXECUTION = eINSTANCE.getActivityNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__INPUTS = eINSTANCE.getActivityNodeExecution_Inputs();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__OUTPUTS = eINSTANCE.getActivityNodeExecution_Outputs();

		/**
		 * The meta object literal for the '<em><b>Logical Successor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = eINSTANCE.getActivityNodeExecution_LogicalSuccessor();

		/**
		 * The meta object literal for the '<em><b>Logical Predecessor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = eINSTANCE.getActivityNodeExecution_LogicalPredecessor();

		/**
		 * The meta object literal for the '<em><b>Chronological Successor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = eINSTANCE.getActivityNodeExecution_ChronologicalSuccessor();

		/**
		 * The meta object literal for the '<em><b>Chronological Predecessor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = eINSTANCE.getActivityNodeExecution_ChronologicalPredecessor();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ACTIVITY_NODE_EXECUTION__NODE = eINSTANCE.getActivityNodeExecution_Node();

		/**
		 * The meta object literal for the '<em><b>Activity Execution</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = eINSTANCE.getActivityNodeExecution_ActivityExecution();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActivityNodeExecutionImpl <em>Call Activity Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActivityNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActivityNodeExecution()
		 * @generated
		 */
		public static final EClass CALL_ACTIVITY_NODE_EXECUTION = eINSTANCE.getCallActivityNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Callee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CALL_ACTIVITY_NODE_EXECUTION__CALLEE = eINSTANCE.getCallActivityNodeExecution_Callee();

		/**
		 * The meta object literal for the '<em><b>Called Behavior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CALL_ACTIVITY_NODE_EXECUTION__CALLED_BEHAVIOR = eINSTANCE.getCallActivityNodeExecution_CalledBehavior();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl <em>User Parameter Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.UserParameterInputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getUserParameterInput()
		 * @generated
		 */
		public static final EClass USER_PARAMETER_INPUT = eINSTANCE.getUserParameterInput();

		/**
		 * The meta object literal for the '<em><b>User Input Tokens</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference USER_PARAMETER_INPUT__USER_INPUT_TOKENS = eINSTANCE.getUserParameterInput_UserInputTokens();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl <em>Parameter Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterInput()
		 * @generated
		 */
		public static final EClass PARAMETER_INPUT = eINSTANCE.getParameterInput();

		/**
		 * The meta object literal for the '<em><b>Parameter Input Tokens</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PARAMETER_INPUT__PARAMETER_INPUT_TOKENS = eINSTANCE.getParameterInput_ParameterInputTokens();

		/**
		 * The meta object literal for the '<em><b>Input Parameter Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PARAMETER_INPUT__INPUT_PARAMETER_NODE = eINSTANCE.getParameterInput_InputParameterNode();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl <em>Parameter Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterOutputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getParameterOutput()
		 * @generated
		 */
		public static final EClass PARAMETER_OUTPUT = eINSTANCE.getParameterOutput();

		/**
		 * The meta object literal for the '<em><b>Parameter Output Tokens</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PARAMETER_OUTPUT__PARAMETER_OUTPUT_TOKENS = eINSTANCE.getParameterOutput_ParameterOutputTokens();

		/**
		 * The meta object literal for the '<em><b>Output Parameter Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PARAMETER_OUTPUT__OUTPUT_PARAMETER_NODE = eINSTANCE.getParameterOutput_OutputParameterNode();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInput()
		 * @generated
		 */
		public static final EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '<em><b>Tokens</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference INPUT__TOKENS = eINSTANCE.getInput_Tokens();

		/**
		 * The meta object literal for the '<em><b>Input Pin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INPUT__INPUT_PIN = eINSTANCE.getInput_InputPin();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutput()
		 * @generated
		 */
		public static final EClass OUTPUT = eINSTANCE.getOutput();

		/**
		 * The meta object literal for the '<em><b>Tokens</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OUTPUT__TOKENS = eINSTANCE.getOutput_Tokens();

		/**
		 * The meta object literal for the '<em><b>Output Pin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute OUTPUT__OUTPUT_PIN = eINSTANCE.getOutput_OutputPin();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl <em>Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getTokenInstance()
		 * @generated
		 */
		public static final EClass TOKEN_INSTANCE = eINSTANCE.getTokenInstance();

		/**
		 * The meta object literal for the '<em><b>Traversed Edges</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute TOKEN_INSTANCE__TRAVERSED_EDGES = eINSTANCE.getTokenInstance_TraversedEdges();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl <em>Object Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ObjectTokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getObjectTokenInstance()
		 * @generated
		 */
		public static final EClass OBJECT_TOKEN_INSTANCE = eINSTANCE.getObjectTokenInstance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OBJECT_TOKEN_INSTANCE__VALUE = eINSTANCE.getObjectTokenInstance_Value();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl <em>Control Token Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlTokenInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlTokenInstance()
		 * @generated
		 */
		public static final EClass CONTROL_TOKEN_INSTANCE = eINSTANCE.getControlTokenInstance();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl <em>Value Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstance()
		 * @generated
		 */
		public static final EClass VALUE_INSTANCE = eINSTANCE.getValueInstance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute VALUE_INSTANCE__VALUE = eINSTANCE.getValueInstance_Value();

		/**
		 * The meta object literal for the '<em>Activity</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivity()
		 * @generated
		 */
		public static final EDataType ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em>Activity Node</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNode()
		 * @generated
		 */
		public static final EDataType ACTIVITY_NODE = eINSTANCE.getActivityNode();

		/**
		 * The meta object literal for the '<em>Behavior</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getBehavior()
		 * @generated
		 */
		public static final EDataType BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em>Activity Parameter Node</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityParameterNode()
		 * @generated
		 */
		public static final EDataType ACTIVITY_PARAMETER_NODE = eINSTANCE.getActivityParameterNode();

		/**
		 * The meta object literal for the '<em>Input Pin</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Actions.BasicActions.InputPin
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputPin()
		 * @generated
		 */
		public static final EDataType INPUT_PIN = eINSTANCE.getInputPin();

		/**
		 * The meta object literal for the '<em>Output Pin</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Actions.BasicActions.OutputPin
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputPin()
		 * @generated
		 */
		public static final EDataType OUTPUT_PIN = eINSTANCE.getOutputPin();

		/**
		 * The meta object literal for the '<em>Activity Edge</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityEdge()
		 * @generated
		 */
		public static final EDataType ACTIVITY_EDGE = eINSTANCE.getActivityEdge();

		/**
		 * The meta object literal for the '<em>Value</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fUML.Semantics.Classes.Kernel.Value
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValue()
		 * @generated
		 */
		public static final EDataType VALUE = eINSTANCE.getValue();

	}

} //TracemodelPackageImpl
