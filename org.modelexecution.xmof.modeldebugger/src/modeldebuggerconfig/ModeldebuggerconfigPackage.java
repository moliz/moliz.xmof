/**
 */
package modeldebuggerconfig;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see modeldebuggerconfig.ModeldebuggerconfigFactory
 * @model kind="package"
 * @generated
 */
public interface ModeldebuggerconfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "modeldebuggerconfig";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://modeldebuggerconfig/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "modeldebuggerconfig";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModeldebuggerconfigPackage eINSTANCE = modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link modeldebuggerconfig.impl.DebuggerConfigurationImpl <em>Debugger Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see modeldebuggerconfig.impl.DebuggerConfigurationImpl
	 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getDebuggerConfiguration()
	 * @generated
	 */
	int DEBUGGER_CONFIGURATION = 0;

	/**
	 * The feature id for the '<em><b>Step Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_CONFIGURATION__STEP_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Editor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_CONFIGURATION__EDITOR_ID = 1;

	/**
	 * The feature id for the '<em><b>Configuration Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE = 2;

	/**
	 * The number of structural features of the '<em>Debugger Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_CONFIGURATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Debugger Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link modeldebuggerconfig.impl.StepDefinitionImpl <em>Step Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see modeldebuggerconfig.impl.StepDefinitionImpl
	 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getStepDefinition()
	 * @generated
	 */
	int STEP_DEFINITION = 1;

	/**
	 * The number of structural features of the '<em>Step Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_DEFINITION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Step Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_DEFINITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl <em>Activity Node Step Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl
	 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getActivityNodeStepDefinition()
	 * @generated
	 */
	int ACTIVITY_NODE_STEP_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_STEP_DEFINITION__CONDITION = STEP_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE = STEP_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Activity Node Step Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_STEP_DEFINITION_FEATURE_COUNT = STEP_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Activity Node Step Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_STEP_DEFINITION_OPERATION_COUNT = STEP_DEFINITION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link modeldebuggerconfig.DebuggerConfiguration <em>Debugger Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Debugger Configuration</em>'.
	 * @see modeldebuggerconfig.DebuggerConfiguration
	 * @generated
	 */
	EClass getDebuggerConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link modeldebuggerconfig.DebuggerConfiguration#getStepDefinitions <em>Step Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Step Definitions</em>'.
	 * @see modeldebuggerconfig.DebuggerConfiguration#getStepDefinitions()
	 * @see #getDebuggerConfiguration()
	 * @generated
	 */
	EReference getDebuggerConfiguration_StepDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link modeldebuggerconfig.DebuggerConfiguration#getEditorID <em>Editor ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editor ID</em>'.
	 * @see modeldebuggerconfig.DebuggerConfiguration#getEditorID()
	 * @see #getDebuggerConfiguration()
	 * @generated
	 */
	EAttribute getDebuggerConfiguration_EditorID();

	/**
	 * Returns the meta object for the reference '{@link modeldebuggerconfig.DebuggerConfiguration#getConfigurationPackage <em>Configuration Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Configuration Package</em>'.
	 * @see modeldebuggerconfig.DebuggerConfiguration#getConfigurationPackage()
	 * @see #getDebuggerConfiguration()
	 * @generated
	 */
	EReference getDebuggerConfiguration_ConfigurationPackage();

	/**
	 * Returns the meta object for class '{@link modeldebuggerconfig.StepDefinition <em>Step Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step Definition</em>'.
	 * @see modeldebuggerconfig.StepDefinition
	 * @generated
	 */
	EClass getStepDefinition();

	/**
	 * Returns the meta object for class '{@link modeldebuggerconfig.ActivityNodeStepDefinition <em>Activity Node Step Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Node Step Definition</em>'.
	 * @see modeldebuggerconfig.ActivityNodeStepDefinition
	 * @generated
	 */
	EClass getActivityNodeStepDefinition();

	/**
	 * Returns the meta object for the attribute '{@link modeldebuggerconfig.ActivityNodeStepDefinition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see modeldebuggerconfig.ActivityNodeStepDefinition#getCondition()
	 * @see #getActivityNodeStepDefinition()
	 * @generated
	 */
	EAttribute getActivityNodeStepDefinition_Condition();

	/**
	 * Returns the meta object for the reference '{@link modeldebuggerconfig.ActivityNodeStepDefinition#getActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity Node</em>'.
	 * @see modeldebuggerconfig.ActivityNodeStepDefinition#getActivityNode()
	 * @see #getActivityNodeStepDefinition()
	 * @generated
	 */
	EReference getActivityNodeStepDefinition_ActivityNode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModeldebuggerconfigFactory getModeldebuggerconfigFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link modeldebuggerconfig.impl.DebuggerConfigurationImpl <em>Debugger Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see modeldebuggerconfig.impl.DebuggerConfigurationImpl
		 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getDebuggerConfiguration()
		 * @generated
		 */
		EClass DEBUGGER_CONFIGURATION = eINSTANCE.getDebuggerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Step Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEBUGGER_CONFIGURATION__STEP_DEFINITIONS = eINSTANCE.getDebuggerConfiguration_StepDefinitions();

		/**
		 * The meta object literal for the '<em><b>Editor ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEBUGGER_CONFIGURATION__EDITOR_ID = eINSTANCE.getDebuggerConfiguration_EditorID();

		/**
		 * The meta object literal for the '<em><b>Configuration Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE = eINSTANCE.getDebuggerConfiguration_ConfigurationPackage();

		/**
		 * The meta object literal for the '{@link modeldebuggerconfig.impl.StepDefinitionImpl <em>Step Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see modeldebuggerconfig.impl.StepDefinitionImpl
		 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getStepDefinition()
		 * @generated
		 */
		EClass STEP_DEFINITION = eINSTANCE.getStepDefinition();

		/**
		 * The meta object literal for the '{@link modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl <em>Activity Node Step Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl
		 * @see modeldebuggerconfig.impl.ModeldebuggerconfigPackageImpl#getActivityNodeStepDefinition()
		 * @generated
		 */
		EClass ACTIVITY_NODE_STEP_DEFINITION = eINSTANCE.getActivityNodeStepDefinition();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_NODE_STEP_DEFINITION__CONDITION = eINSTANCE.getActivityNodeStepDefinition_Condition();

		/**
		 * The meta object literal for the '<em><b>Activity Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE = eINSTANCE.getActivityNodeStepDefinition_ActivityNode();

	}

} //ModeldebuggerconfigPackage
