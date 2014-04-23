/**
 */
package modeldebuggerconfig.impl;

import modeldebuggerconfig.ActivityNodeStepDefinition;
import modeldebuggerconfig.DebuggerConfiguration;
import modeldebuggerconfig.ModeldebuggerconfigFactory;
import modeldebuggerconfig.ModeldebuggerconfigPackage;
import modeldebuggerconfig.StepDefinition;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Package;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage;

import org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage;

import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage;

import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage;

import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage;

import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;

import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;

import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModeldebuggerconfigPackageImpl extends EPackageImpl implements ModeldebuggerconfigPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass debuggerConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityNodeStepDefinitionEClass = null;

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
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModeldebuggerconfigPackageImpl() {
		super(eNS_URI, ModeldebuggerconfigFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ModeldebuggerconfigPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModeldebuggerconfigPackage init() {
		if (isInited) return (ModeldebuggerconfigPackage)EPackage.Registry.INSTANCE.getEPackage(ModeldebuggerconfigPackage.eNS_URI);

		// Obtain or create and register package
		ModeldebuggerconfigPackageImpl theModeldebuggerconfigPackage = (ModeldebuggerconfigPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModeldebuggerconfigPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModeldebuggerconfigPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BasicBehaviorsPackage.eINSTANCE.eClass();
		CommunicationsPackage.eINSTANCE.eClass();
		KernelPackage.eINSTANCE.eClass();
		IntermediateActivitiesPackage.eINSTANCE.eClass();
		CompleteStructuredActivitiesPackage.eINSTANCE.eClass();
		ExtraStructuredActivitiesPackage.eINSTANCE.eClass();
		IntermediateActionsPackage.eINSTANCE.eClass();
		CompleteActionsPackage.eINSTANCE.eClass();
		BasicActionsPackage.eINSTANCE.eClass();
		org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage.eINSTANCE.eClass();
		LociL1Package.eINSTANCE.eClass();
		org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theModeldebuggerconfigPackage.createPackageContents();

		// Initialize created meta-data
		theModeldebuggerconfigPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModeldebuggerconfigPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModeldebuggerconfigPackage.eNS_URI, theModeldebuggerconfigPackage);
		return theModeldebuggerconfigPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDebuggerConfiguration() {
		return debuggerConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDebuggerConfiguration_StepDefinitions() {
		return (EReference)debuggerConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDebuggerConfiguration_EditorID() {
		return (EAttribute)debuggerConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDebuggerConfiguration_ConfigurationPackage() {
		return (EReference)debuggerConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStepDefinition() {
		return stepDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityNodeStepDefinition() {
		return activityNodeStepDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityNodeStepDefinition_Condition() {
		return (EAttribute)activityNodeStepDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeStepDefinition_ActivityNode() {
		return (EReference)activityNodeStepDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldebuggerconfigFactory getModeldebuggerconfigFactory() {
		return (ModeldebuggerconfigFactory)getEFactoryInstance();
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
		debuggerConfigurationEClass = createEClass(DEBUGGER_CONFIGURATION);
		createEReference(debuggerConfigurationEClass, DEBUGGER_CONFIGURATION__STEP_DEFINITIONS);
		createEAttribute(debuggerConfigurationEClass, DEBUGGER_CONFIGURATION__EDITOR_ID);
		createEReference(debuggerConfigurationEClass, DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE);

		stepDefinitionEClass = createEClass(STEP_DEFINITION);

		activityNodeStepDefinitionEClass = createEClass(ACTIVITY_NODE_STEP_DEFINITION);
		createEAttribute(activityNodeStepDefinitionEClass, ACTIVITY_NODE_STEP_DEFINITION__CONDITION);
		createEReference(activityNodeStepDefinitionEClass, ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE);
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

		// Obtain other dependent packages
		IntermediateActivitiesPackage theIntermediateActivitiesPackage = (IntermediateActivitiesPackage)EPackage.Registry.INSTANCE.getEPackage(IntermediateActivitiesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		activityNodeStepDefinitionEClass.getESuperTypes().add(this.getStepDefinition());

		// Initialize classes, features, and operations; add parameters
		initEClass(debuggerConfigurationEClass, DebuggerConfiguration.class, "DebuggerConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDebuggerConfiguration_StepDefinitions(), this.getStepDefinition(), null, "stepDefinitions", null, 0, -1, DebuggerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDebuggerConfiguration_EditorID(), ecorePackage.getEString(), "editorID", null, 0, 1, DebuggerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDebuggerConfiguration_ConfigurationPackage(), ecorePackage.getEPackage(), null, "configurationPackage", null, 1, 1, DebuggerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stepDefinitionEClass, StepDefinition.class, "StepDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activityNodeStepDefinitionEClass, ActivityNodeStepDefinition.class, "ActivityNodeStepDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivityNodeStepDefinition_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, ActivityNodeStepDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeStepDefinition_ActivityNode(), theIntermediateActivitiesPackage.getActivityNode(), null, "activityNode", null, 0, 1, ActivityNodeStepDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ModeldebuggerconfigPackageImpl
