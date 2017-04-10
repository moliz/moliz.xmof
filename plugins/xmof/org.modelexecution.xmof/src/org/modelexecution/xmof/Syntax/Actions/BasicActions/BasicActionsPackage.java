/**
 */
package org.modelexecution.xmof.Syntax.Actions.BasicActions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;

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
 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory
 * @model kind="package"
 * @generated
 */
public interface BasicActionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "BasicActions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelexecution.org/xmof/syntax/actions/basicactions";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fUML.Syntax.Actions.BasicActions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasicActionsPackage eINSTANCE = org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__EANNOTATIONS = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__IN_STRUCTURED_NODE = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTIVITY = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTGOING = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__INCOMING = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTPUT = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTEXT = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__INPUT = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LOCALLY_REENTRANT = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = CompleteStructuredActivitiesPackage.EXECUTABLE_NODE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.PinImpl <em>Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.PinImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getPin()
	 * @generated
	 */
	int PIN = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__EANNOTATIONS = IntermediateActivitiesPackage.OBJECT_NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__NAME = IntermediateActivitiesPackage.OBJECT_NODE__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__IN_STRUCTURED_NODE = IntermediateActivitiesPackage.OBJECT_NODE__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__ACTIVITY = IntermediateActivitiesPackage.OBJECT_NODE__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__OUTGOING = IntermediateActivitiesPackage.OBJECT_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__INCOMING = IntermediateActivitiesPackage.OBJECT_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__ORDERED = IntermediateActivitiesPackage.OBJECT_NODE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__UNIQUE = IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__LOWER_BOUND = IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__UPPER_BOUND = IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__MANY = IntermediateActivitiesPackage.OBJECT_NODE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__REQUIRED = IntermediateActivitiesPackage.OBJECT_NODE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__ETYPE = IntermediateActivitiesPackage.OBJECT_NODE__ETYPE;

	/**
	 * The feature id for the '<em><b>EGeneric Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN__EGENERIC_TYPE = IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE;

	/**
	 * The number of structural features of the '<em>Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIN_FEATURE_COUNT = IntermediateActivitiesPackage.OBJECT_NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InputPinImpl <em>Input Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InputPinImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getInputPin()
	 * @generated
	 */
	int INPUT_PIN = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__EANNOTATIONS = PIN__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__NAME = PIN__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__IN_STRUCTURED_NODE = PIN__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ACTIVITY = PIN__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OUTGOING = PIN__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INCOMING = PIN__INCOMING;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ORDERED = PIN__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__UNIQUE = PIN__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__LOWER_BOUND = PIN__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__UPPER_BOUND = PIN__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__MANY = PIN__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__REQUIRED = PIN__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ETYPE = PIN__ETYPE;

	/**
	 * The feature id for the '<em><b>EGeneric Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__EGENERIC_TYPE = PIN__EGENERIC_TYPE;

	/**
	 * The number of structural features of the '<em>Input Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN_FEATURE_COUNT = PIN_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InvocationActionImpl <em>Invocation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InvocationActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getInvocationAction()
	 * @generated
	 */
	int INVOCATION_ACTION = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__EANNOTATIONS = ACTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__IN_STRUCTURED_NODE = ACTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__ACTIVITY = ACTION__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__OUTGOING = ACTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__INCOMING = ACTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__OUTPUT = ACTION__OUTPUT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__CONTEXT = ACTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__INPUT = ACTION__INPUT;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__LOCALLY_REENTRANT = ACTION__LOCALLY_REENTRANT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION__ARGUMENT = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Invocation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCATION_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallActionImpl <em>Call Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallAction()
	 * @generated
	 */
	int CALL_ACTION = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__EANNOTATIONS = INVOCATION_ACTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__NAME = INVOCATION_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__IN_STRUCTURED_NODE = INVOCATION_ACTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__ACTIVITY = INVOCATION_ACTION__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__OUTGOING = INVOCATION_ACTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__INCOMING = INVOCATION_ACTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__OUTPUT = INVOCATION_ACTION__OUTPUT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__CONTEXT = INVOCATION_ACTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__INPUT = INVOCATION_ACTION__INPUT;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__LOCALLY_REENTRANT = INVOCATION_ACTION__LOCALLY_REENTRANT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__ARGUMENT = INVOCATION_ACTION__ARGUMENT;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__SYNCHRONOUS = INVOCATION_ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION__RESULT = INVOCATION_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Call Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTION_FEATURE_COUNT = INVOCATION_ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.SendSignalActionImpl <em>Send Signal Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.SendSignalActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getSendSignalAction()
	 * @generated
	 */
	int SEND_SIGNAL_ACTION = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__EANNOTATIONS = INVOCATION_ACTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__NAME = INVOCATION_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__IN_STRUCTURED_NODE = INVOCATION_ACTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__ACTIVITY = INVOCATION_ACTION__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__OUTGOING = INVOCATION_ACTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__INCOMING = INVOCATION_ACTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__OUTPUT = INVOCATION_ACTION__OUTPUT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__CONTEXT = INVOCATION_ACTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__INPUT = INVOCATION_ACTION__INPUT;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__LOCALLY_REENTRANT = INVOCATION_ACTION__LOCALLY_REENTRANT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__ARGUMENT = INVOCATION_ACTION__ARGUMENT;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__TARGET = INVOCATION_ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION__SIGNAL = INVOCATION_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Send Signal Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_SIGNAL_ACTION_FEATURE_COUNT = INVOCATION_ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallBehaviorActionImpl <em>Call Behavior Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallBehaviorActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallBehaviorAction()
	 * @generated
	 */
	int CALL_BEHAVIOR_ACTION = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__EANNOTATIONS = CALL_ACTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__NAME = CALL_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__IN_STRUCTURED_NODE = CALL_ACTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__ACTIVITY = CALL_ACTION__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__OUTGOING = CALL_ACTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__INCOMING = CALL_ACTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__OUTPUT = CALL_ACTION__OUTPUT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__CONTEXT = CALL_ACTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__INPUT = CALL_ACTION__INPUT;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__LOCALLY_REENTRANT = CALL_ACTION__LOCALLY_REENTRANT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__ARGUMENT = CALL_ACTION__ARGUMENT;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__SYNCHRONOUS = CALL_ACTION__SYNCHRONOUS;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__RESULT = CALL_ACTION__RESULT;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION__BEHAVIOR = CALL_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Behavior Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_BEHAVIOR_ACTION_FEATURE_COUNT = CALL_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallOperationActionImpl <em>Call Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallOperationActionImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallOperationAction()
	 * @generated
	 */
	int CALL_OPERATION_ACTION = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__EANNOTATIONS = CALL_ACTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__NAME = CALL_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__IN_STRUCTURED_NODE = CALL_ACTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__ACTIVITY = CALL_ACTION__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__OUTGOING = CALL_ACTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__INCOMING = CALL_ACTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Output</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__OUTPUT = CALL_ACTION__OUTPUT;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__CONTEXT = CALL_ACTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Input</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__INPUT = CALL_ACTION__INPUT;

	/**
	 * The feature id for the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__LOCALLY_REENTRANT = CALL_ACTION__LOCALLY_REENTRANT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__ARGUMENT = CALL_ACTION__ARGUMENT;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__SYNCHRONOUS = CALL_ACTION__SYNCHRONOUS;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__RESULT = CALL_ACTION__RESULT;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__OPERATION = CALL_ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__TARGET = CALL_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Call Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION_FEATURE_COUNT = CALL_ACTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.OutputPinImpl <em>Output Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.OutputPinImpl
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getOutputPin()
	 * @generated
	 */
	int OUTPUT_PIN = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__EANNOTATIONS = PIN__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__NAME = PIN__NAME;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__IN_STRUCTURED_NODE = PIN__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ACTIVITY = PIN__ACTIVITY;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OUTGOING = PIN__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INCOMING = PIN__INCOMING;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ORDERED = PIN__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__UNIQUE = PIN__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__LOWER_BOUND = PIN__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__UPPER_BOUND = PIN__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__MANY = PIN__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__REQUIRED = PIN__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ETYPE = PIN__ETYPE;

	/**
	 * The feature id for the '<em><b>EGeneric Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__EGENERIC_TYPE = PIN__EGENERIC_TYPE;

	/**
	 * The number of structural features of the '<em>Output Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN_FEATURE_COUNT = PIN_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getOutput()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Output();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getContext()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Context();

	/**
	 * Returns the meta object for the reference list '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getInput()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Input();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#isLocallyReentrant <em>Locally Reentrant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locally Reentrant</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#isLocallyReentrant()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_LocallyReentrant();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Pin</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin
	 * @generated
	 */
	EClass getInputPin();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin <em>Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pin</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin
	 * @generated
	 */
	EClass getPin();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction <em>Call Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction
	 * @generated
	 */
	EClass getCallAction();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction#isSynchronous <em>Synchronous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronous</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction#isSynchronous()
	 * @see #getCallAction()
	 * @generated
	 */
	EAttribute getCallAction_Synchronous();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Result</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction#getResult()
	 * @see #getCallAction()
	 * @generated
	 */
	EReference getCallAction_Result();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.InvocationAction <em>Invocation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invocation Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.InvocationAction
	 * @generated
	 */
	EClass getInvocationAction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.InvocationAction#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Argument</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.InvocationAction#getArgument()
	 * @see #getInvocationAction()
	 * @generated
	 */
	EReference getInvocationAction_Argument();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction <em>Send Signal Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Send Signal Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction
	 * @generated
	 */
	EClass getSendSignalAction();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction#getTarget()
	 * @see #getSendSignalAction()
	 * @generated
	 */
	EReference getSendSignalAction_Target();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signal</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction#getSignal()
	 * @see #getSendSignalAction()
	 * @generated
	 */
	EReference getSendSignalAction_Signal();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction <em>Call Behavior Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Behavior Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction
	 * @generated
	 */
	EClass getCallBehaviorAction();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Behavior</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction#getBehavior()
	 * @see #getCallBehaviorAction()
	 * @generated
	 */
	EReference getCallBehaviorAction_Behavior();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction <em>Call Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Operation Action</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction
	 * @generated
	 */
	EClass getCallOperationAction();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction#getOperation()
	 * @see #getCallOperationAction()
	 * @generated
	 */
	EReference getCallOperationAction_Operation();

	/**
	 * Returns the meta object for the containment reference '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction#getTarget()
	 * @see #getCallOperationAction()
	 * @generated
	 */
	EReference getCallOperationAction_Target();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Pin</em>'.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin
	 * @generated
	 */
	EClass getOutputPin();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BasicActionsFactory getBasicActionsFactory();

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
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__OUTPUT = eINSTANCE.getAction_Output();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTEXT = eINSTANCE.getAction_Context();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__INPUT = eINSTANCE.getAction_Input();

		/**
		 * The meta object literal for the '<em><b>Locally Reentrant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__LOCALLY_REENTRANT = eINSTANCE.getAction_LocallyReentrant();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InputPinImpl <em>Input Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InputPinImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getInputPin()
		 * @generated
		 */
		EClass INPUT_PIN = eINSTANCE.getInputPin();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.PinImpl <em>Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.PinImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getPin()
		 * @generated
		 */
		EClass PIN = eINSTANCE.getPin();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallActionImpl <em>Call Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallAction()
		 * @generated
		 */
		EClass CALL_ACTION = eINSTANCE.getCallAction();

		/**
		 * The meta object literal for the '<em><b>Synchronous</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALL_ACTION__SYNCHRONOUS = eINSTANCE.getCallAction_Synchronous();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_ACTION__RESULT = eINSTANCE.getCallAction_Result();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InvocationActionImpl <em>Invocation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.InvocationActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getInvocationAction()
		 * @generated
		 */
		EClass INVOCATION_ACTION = eINSTANCE.getInvocationAction();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INVOCATION_ACTION__ARGUMENT = eINSTANCE.getInvocationAction_Argument();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.SendSignalActionImpl <em>Send Signal Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.SendSignalActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getSendSignalAction()
		 * @generated
		 */
		EClass SEND_SIGNAL_ACTION = eINSTANCE.getSendSignalAction();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_SIGNAL_ACTION__TARGET = eINSTANCE.getSendSignalAction_Target();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_SIGNAL_ACTION__SIGNAL = eINSTANCE.getSendSignalAction_Signal();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallBehaviorActionImpl <em>Call Behavior Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallBehaviorActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallBehaviorAction()
		 * @generated
		 */
		EClass CALL_BEHAVIOR_ACTION = eINSTANCE.getCallBehaviorAction();

		/**
		 * The meta object literal for the '<em><b>Behavior</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_BEHAVIOR_ACTION__BEHAVIOR = eINSTANCE.getCallBehaviorAction_Behavior();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallOperationActionImpl <em>Call Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.CallOperationActionImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getCallOperationAction()
		 * @generated
		 */
		EClass CALL_OPERATION_ACTION = eINSTANCE.getCallOperationAction();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_OPERATION_ACTION__OPERATION = eINSTANCE.getCallOperationAction_Operation();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_OPERATION_ACTION__TARGET = eINSTANCE.getCallOperationAction_Target();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.OutputPinImpl <em>Output Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.OutputPinImpl
		 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl#getOutputPin()
		 * @generated
		 */
		EClass OUTPUT_PIN = eINSTANCE.getOutputPin();

	}

} //BasicActionsPackage
