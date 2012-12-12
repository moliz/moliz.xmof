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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot;

import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

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
	 * The feature id for the '<em><b>User Parameter Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS = 0;

	/**
	 * The feature id for the '<em><b>Node Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__NODE_EXECUTIONS = 1;

	/**
	 * The feature id for the '<em><b>Caller</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__CALLER = 2;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Activity Execution ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID = 4;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_EXECUTION__TRACE = 5;

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
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR = 0;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR = 1;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = 2;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = 3;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__NODE = 4;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION = 5;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION__EXECUTED = 6;

	/**
	 * The number of structural features of the '<em>Activity Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVITY_NODE_EXECUTION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl <em>Action Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActionExecution()
	 * @generated
	 */
	public static final int ACTION_EXECUTION = 11;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__LOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__LOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__NODE = ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__ACTIVITY_EXECUTION = ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__EXECUTED = ACTIVITY_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__INPUTS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION__OUTPUTS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_EXECUTION_FEATURE_COUNT = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl <em>Call Action Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActionExecution()
	 * @generated
	 */
	public static final int CALL_ACTION_EXECUTION = 3;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__LOGICAL_SUCCESSOR = ACTION_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__LOGICAL_PREDECESSOR = ACTION_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTION_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTION_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__NODE = ACTION_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__ACTIVITY_EXECUTION = ACTION_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__EXECUTED = ACTION_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__INPUTS = ACTION_EXECUTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__OUTPUTS = ACTION_EXECUTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Callee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION__CALLEE = ACTION_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Action Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_ACTION_EXECUTION_FEATURE_COUNT = ACTION_EXECUTION_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>User Input Tokens</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT__USER_INPUT_TOKENS = 0;

	/**
	 * The feature id for the '<em><b>Input Parameter Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE = 1;

	/**
	 * The number of structural features of the '<em>User Parameter Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int USER_PARAMETER_INPUT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.InputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInput()
	 * @generated
	 */
	public static final int INPUT = 5;

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
	 * The feature id for the '<em><b>Consumed Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INPUT__CONSUMED_VALUE = 2;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INPUT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.OutputImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutput()
	 * @generated
	 */
	public static final int OUTPUT = 6;

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
	public static final int TOKEN_INSTANCE = 7;

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
	public static final int OBJECT_TOKEN_INSTANCE = 8;

	/**
	 * The feature id for the '<em><b>Traversed Edges</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_TOKEN_INSTANCE__TRAVERSED_EDGES = TOKEN_INSTANCE__TRAVERSED_EDGES;

	/**
	 * The feature id for the '<em><b>Value Instance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE = TOKEN_INSTANCE_FEATURE_COUNT + 0;

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
	public static final int CONTROL_TOKEN_INSTANCE = 9;

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
	public static final int VALUE_INSTANCE = 10;

	/**
	 * The feature id for the '<em><b>Snapshots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE__SNAPSHOTS = 0;

	/**
	 * The feature id for the '<em><b>Original</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE__ORIGINAL = 1;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE__VALUE_ID = 2;

	/**
	 * The number of structural features of the '<em>Value Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl <em>Control Node Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlNodeExecution()
	 * @generated
	 */
	public static final int CONTROL_NODE_EXECUTION = 12;

	/**
	 * The feature id for the '<em><b>Logical Successor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__LOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Logical Predecessor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__LOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Successor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR;

	/**
	 * The feature id for the '<em><b>Chronological Predecessor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR = ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__NODE = ACTIVITY_NODE_EXECUTION__NODE;

	/**
	 * The feature id for the '<em><b>Activity Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__ACTIVITY_EXECUTION = ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION;

	/**
	 * The feature id for the '<em><b>Executed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__EXECUTED = ACTIVITY_NODE_EXECUTION__EXECUTED;

	/**
	 * The feature id for the '<em><b>Routed Tokens</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION__ROUTED_TOKENS = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Control Node Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONTROL_NODE_EXECUTION_FEATURE_COUNT = ACTIVITY_NODE_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceSnapshotImpl <em>Value Instance Snapshot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceSnapshotImpl
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstanceSnapshot()
	 * @generated
	 */
	public static final int VALUE_INSTANCE_SNAPSHOT = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE_SNAPSHOT__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Value Instance Snapshot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_INSTANCE_SNAPSHOT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '<em>Activity</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.Activity
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivity()
	 * @generated
	 */
	public static final int ACTIVITY = 14;

	/**
	 * The meta object id for the '<em>Activity Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityNode()
	 * @generated
	 */
	public static final int ACTIVITY_NODE = 15;

	/**
	 * The meta object id for the '<em>Behavior</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getBehavior()
	 * @generated
	 */
	public static final int BEHAVIOR = 16;

	/**
	 * The meta object id for the '<em>Activity Parameter Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityParameterNode()
	 * @generated
	 */
	public static final int ACTIVITY_PARAMETER_NODE = 17;

	/**
	 * The meta object id for the '<em>Input Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.InputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getInputPin()
	 * @generated
	 */
	public static final int INPUT_PIN = 18;

	/**
	 * The meta object id for the '<em>Output Pin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Actions.BasicActions.OutputPin
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getOutputPin()
	 * @generated
	 */
	public static final int OUTPUT_PIN = 19;

	/**
	 * The meta object id for the '<em>Activity Edge</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Syntax.Activities.IntermediateActivities.ActivityEdge
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActivityEdge()
	 * @generated
	 */
	public static final int ACTIVITY_EDGE = 20;

	/**
	 * The meta object id for the '<em>Value</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fUML.Semantics.Classes.Kernel.Value
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValue()
	 * @generated
	 */
	public static final int VALUE = 21;

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
	private EClass callActionExecutionEClass = null;

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
	private EClass actionExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueInstanceSnapshotEClass = null;

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
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getUserParameterInputs <em>User Parameter Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>User Parameter Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getUserParameterInputs()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_UserParameterInputs() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(0);
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
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(1);
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
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(2);
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
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(3);
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
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for the container reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Trace</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace()
	 * @see #getActivityExecution()
	 * @generated
	 */
	public EReference getActivityExecution_Trace() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(5);
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
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor <em>Logical Successor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Logical Successor</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#getLogicalSuccessor()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EReference getActivityNodeExecution_LogicalSuccessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(0);
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
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(1);
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
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(2);
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
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(3);
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
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(4);
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
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted <em>Executed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executed</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution#isExecuted()
	 * @see #getActivityNodeExecution()
	 * @generated
	 */
	public EAttribute getActivityNodeExecution_Executed() {
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(6);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution <em>Call Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Action Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution
	 * @generated
	 */
	public EClass getCallActionExecution() {
		return callActionExecutionEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee <em>Callee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Callee</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution#getCallee()
	 * @see #getCallActionExecution()
	 * @generated
	 */
	public EReference getCallActionExecution_Callee() {
		return (EReference)callActionExecutionEClass.getEStructuralFeatures().get(0);
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
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput#getInputParameterNode <em>Input Parameter Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Parameter Node</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput#getInputParameterNode()
	 * @see #getUserParameterInput()
	 * @generated
	 */
	public EAttribute getUserParameterInput_InputParameterNode() {
		return (EAttribute)userParameterInputEClass.getEStructuralFeatures().get(1);
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
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.Input#getConsumedValue <em>Consumed Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Consumed Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.Input#getConsumedValue()
	 * @see #getInput()
	 * @generated
	 */
	public EReference getInput_ConsumedValue() {
		return (EReference)inputEClass.getEStructuralFeatures().get(2);
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
	 * Returns the meta object for the containment reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Instance</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance#getValueInstance()
	 * @see #getObjectTokenInstance()
	 * @generated
	 */
	public EReference getObjectTokenInstance_ValueInstance() {
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
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots <em>Snapshots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Snapshots</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getSnapshots()
	 * @see #getValueInstance()
	 * @generated
	 */
	public EReference getValueInstance_Snapshots() {
		return (EReference)valueInstanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal <em>Original</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Original</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getOriginal()
	 * @see #getValueInstance()
	 * @generated
	 */
	public EReference getValueInstance_Original() {
		return (EReference)valueInstanceEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance#getValueID()
	 * @see #getValueInstance()
	 * @generated
	 */
	public EAttribute getValueInstance_ValueID() {
		return (EAttribute)valueInstanceEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution <em>Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution
	 * @generated
	 */
	public EClass getActionExecution() {
		return actionExecutionEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getInputs()
	 * @see #getActionExecution()
	 * @generated
	 */
	public EReference getActionExecution_Inputs() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution#getOutputs()
	 * @see #getActionExecution()
	 * @generated
	 */
	public EReference getActionExecution_Outputs() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution <em>Control Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Node Execution</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution
	 * @generated
	 */
	public EClass getControlNodeExecution() {
		return controlNodeExecutionEClass;
	}


	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution#getRoutedTokens <em>Routed Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Routed Tokens</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution#getRoutedTokens()
	 * @see #getControlNodeExecution()
	 * @generated
	 */
	public EReference getControlNodeExecution_RoutedTokens() {
		return (EReference)controlNodeExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot <em>Value Instance Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Instance Snapshot</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot
	 * @generated
	 */
	public EClass getValueInstanceSnapshot() {
		return valueInstanceSnapshotEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstanceSnapshot#getValue()
	 * @see #getValueInstanceSnapshot()
	 * @generated
	 */
	public EAttribute getValueInstanceSnapshot_Value() {
		return (EAttribute)valueInstanceSnapshotEClass.getEStructuralFeatures().get(0);
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
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__NODE_EXECUTIONS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__CALLER);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__TRACE);

		activityNodeExecutionEClass = createEClass(ACTIVITY_NODE_EXECUTION);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__NODE);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__EXECUTED);

		callActionExecutionEClass = createEClass(CALL_ACTION_EXECUTION);
		createEReference(callActionExecutionEClass, CALL_ACTION_EXECUTION__CALLEE);

		userParameterInputEClass = createEClass(USER_PARAMETER_INPUT);
		createEReference(userParameterInputEClass, USER_PARAMETER_INPUT__USER_INPUT_TOKENS);
		createEAttribute(userParameterInputEClass, USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE);

		inputEClass = createEClass(INPUT);
		createEReference(inputEClass, INPUT__TOKENS);
		createEAttribute(inputEClass, INPUT__INPUT_PIN);
		createEReference(inputEClass, INPUT__CONSUMED_VALUE);

		outputEClass = createEClass(OUTPUT);
		createEReference(outputEClass, OUTPUT__TOKENS);
		createEAttribute(outputEClass, OUTPUT__OUTPUT_PIN);

		tokenInstanceEClass = createEClass(TOKEN_INSTANCE);
		createEAttribute(tokenInstanceEClass, TOKEN_INSTANCE__TRAVERSED_EDGES);

		objectTokenInstanceEClass = createEClass(OBJECT_TOKEN_INSTANCE);
		createEReference(objectTokenInstanceEClass, OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE);

		controlTokenInstanceEClass = createEClass(CONTROL_TOKEN_INSTANCE);

		valueInstanceEClass = createEClass(VALUE_INSTANCE);
		createEReference(valueInstanceEClass, VALUE_INSTANCE__SNAPSHOTS);
		createEReference(valueInstanceEClass, VALUE_INSTANCE__ORIGINAL);
		createEAttribute(valueInstanceEClass, VALUE_INSTANCE__VALUE_ID);

		actionExecutionEClass = createEClass(ACTION_EXECUTION);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__INPUTS);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__OUTPUTS);

		controlNodeExecutionEClass = createEClass(CONTROL_NODE_EXECUTION);
		createEReference(controlNodeExecutionEClass, CONTROL_NODE_EXECUTION__ROUTED_TOKENS);

		valueInstanceSnapshotEClass = createEClass(VALUE_INSTANCE_SNAPSHOT);
		createEAttribute(valueInstanceSnapshotEClass, VALUE_INSTANCE_SNAPSHOT__VALUE);

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
		callActionExecutionEClass.getESuperTypes().add(this.getActionExecution());
		objectTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());
		controlTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());
		actionExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());
		controlNodeExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());

		// Initialize classes and features; add operations and parameters
		initEClass(traceEClass, Trace.class, "Trace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrace_ActivityExecutions(), this.getActivityExecution(), this.getActivityExecution_Trace(), "activityExecutions", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityExecutionEClass, ActivityExecution.class, "ActivityExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityExecution_UserParameterInputs(), this.getUserParameterInput(), null, "userParameterInputs", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_NodeExecutions(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ActivityExecution(), "nodeExecutions", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_Caller(), this.getCallActionExecution(), this.getCallActionExecution_Callee(), "caller", null, 0, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_Activity(), this.getActivity(), "activity", null, 1, 1, ActivityExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_ActivityExecutionID(), ecorePackage.getEInt(), "activityExecutionID", null, 1, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_Trace(), this.getTrace(), this.getTrace_ActivityExecutions(), "trace", null, 1, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityNodeExecutionEClass, ActivityNodeExecution.class, "ActivityNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityNodeExecution_LogicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalPredecessor(), "logicalSuccessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_LogicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalSuccessor(), "logicalPredecessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalPredecessor(), "chronologicalSuccessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalSuccessor(), "chronologicalPredecessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityNodeExecution_Node(), this.getActivityNode(), "node", null, 1, 1, ActivityNodeExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ActivityExecution(), this.getActivityExecution(), this.getActivityExecution_NodeExecutions(), "activityExecution", null, 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getActivityNodeExecution_Executed(), ecorePackage.getEBoolean(), "executed", "false", 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callActionExecutionEClass, CallActionExecution.class, "CallActionExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallActionExecution_Callee(), this.getActivityExecution(), this.getActivityExecution_Caller(), "callee", null, 0, 1, CallActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userParameterInputEClass, UserParameterInput.class, "UserParameterInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserParameterInput_UserInputTokens(), this.getObjectTokenInstance(), null, "userInputTokens", null, 0, -1, UserParameterInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserParameterInput_InputParameterNode(), this.getActivityParameterNode(), "inputParameterNode", null, 1, 1, UserParameterInput.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInput_Tokens(), this.getTokenInstance(), null, "tokens", null, 0, -1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_InputPin(), this.getInputPin(), "inputPin", null, 0, 1, Input.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInput_ConsumedValue(), this.getValueInstanceSnapshot(), null, "consumedValue", null, 0, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutput_Tokens(), this.getTokenInstance(), null, "tokens", null, 0, -1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutput_OutputPin(), this.getOutputPin(), "outputPin", null, 0, 1, Output.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tokenInstanceEClass, TokenInstance.class, "TokenInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTokenInstance_TraversedEdges(), this.getActivityEdge(), "traversedEdges", null, 0, -1, TokenInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(objectTokenInstanceEClass, ObjectTokenInstance.class, "ObjectTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObjectTokenInstance_ValueInstance(), this.getValueInstance(), null, "valueInstance", null, 1, 1, ObjectTokenInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlTokenInstanceEClass, ControlTokenInstance.class, "ControlTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueInstanceEClass, ValueInstance.class, "ValueInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValueInstance_Snapshots(), this.getValueInstanceSnapshot(), null, "snapshots", null, 1, -1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueInstance_Original(), this.getValueInstanceSnapshot(), null, "original", null, 1, 1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInstance_ValueID(), ecorePackage.getEInt(), "valueID", null, 1, 1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionExecutionEClass, ActionExecution.class, "ActionExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionExecution_Inputs(), this.getInput(), null, "inputs", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionExecution_Outputs(), this.getOutput(), null, "outputs", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlNodeExecutionEClass, ControlNodeExecution.class, "ControlNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControlNodeExecution_RoutedTokens(), this.getTokenInstance(), null, "routedTokens", null, 1, -1, ControlNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueInstanceSnapshotEClass, ValueInstanceSnapshot.class, "ValueInstanceSnapshot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueInstanceSnapshot_Value(), this.getValue(), "value", null, 1, 1, ValueInstanceSnapshot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '<em><b>User Parameter Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS = eINSTANCE.getActivityExecution_UserParameterInputs();

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
		 * The meta object literal for the '<em><b>Trace</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTIVITY_EXECUTION__TRACE = eINSTANCE.getActivityExecution_Trace();

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
		 * The meta object literal for the '<em><b>Executed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ACTIVITY_NODE_EXECUTION__EXECUTED = eINSTANCE.getActivityNodeExecution_Executed();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl <em>Call Action Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.CallActionExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getCallActionExecution()
		 * @generated
		 */
		public static final EClass CALL_ACTION_EXECUTION = eINSTANCE.getCallActionExecution();

		/**
		 * The meta object literal for the '<em><b>Callee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CALL_ACTION_EXECUTION__CALLEE = eINSTANCE.getCallActionExecution_Callee();

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
		 * The meta object literal for the '<em><b>Input Parameter Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE = eINSTANCE.getUserParameterInput_InputParameterNode();

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
		 * The meta object literal for the '<em><b>Consumed Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference INPUT__CONSUMED_VALUE = eINSTANCE.getInput_ConsumedValue();

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
		 * The meta object literal for the '<em><b>Value Instance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OBJECT_TOKEN_INSTANCE__VALUE_INSTANCE = eINSTANCE.getObjectTokenInstance_ValueInstance();

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
		 * The meta object literal for the '<em><b>Snapshots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VALUE_INSTANCE__SNAPSHOTS = eINSTANCE.getValueInstance_Snapshots();

		/**
		 * The meta object literal for the '<em><b>Original</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VALUE_INSTANCE__ORIGINAL = eINSTANCE.getValueInstance_Original();

		/**
		 * The meta object literal for the '<em><b>Value ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute VALUE_INSTANCE__VALUE_ID = eINSTANCE.getValueInstance_ValueID();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl <em>Action Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActionExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getActionExecution()
		 * @generated
		 */
		public static final EClass ACTION_EXECUTION = eINSTANCE.getActionExecution();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTION_EXECUTION__INPUTS = eINSTANCE.getActionExecution_Inputs();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ACTION_EXECUTION__OUTPUTS = eINSTANCE.getActionExecution_Outputs();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl <em>Control Node Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ControlNodeExecutionImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getControlNodeExecution()
		 * @generated
		 */
		public static final EClass CONTROL_NODE_EXECUTION = eINSTANCE.getControlNodeExecution();

		/**
		 * The meta object literal for the '<em><b>Routed Tokens</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONTROL_NODE_EXECUTION__ROUTED_TOKENS = eINSTANCE.getControlNodeExecution_RoutedTokens();

		/**
		 * The meta object literal for the '{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceSnapshotImpl <em>Value Instance Snapshot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.ValueInstanceSnapshotImpl
		 * @see org.modelexecution.fumldebug.core.trace.tracemodel.impl.TracemodelPackageImpl#getValueInstanceSnapshot()
		 * @generated
		 */
		public static final EClass VALUE_INSTANCE_SNAPSHOT = eINSTANCE.getValueInstanceSnapshot();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute VALUE_INSTANCE_SNAPSHOT__VALUE = eINSTANCE.getValueInstanceSnapshot_Value();

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
