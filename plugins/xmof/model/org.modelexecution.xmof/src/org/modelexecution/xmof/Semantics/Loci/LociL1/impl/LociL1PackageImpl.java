/**
 */
package org.modelexecution.xmof.Semantics.Loci.LociL1.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Factory;
import org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Package;
import org.modelexecution.xmof.Semantics.Loci.LociL1.SemanticVisitor;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.BasicActionsPackageImpl;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.impl.CompleteActionsPackageImpl;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.impl.IntermediateActionsPackageImpl;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.CompleteStructuredActivitiesPackageImpl;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.impl.ExtraStructuredActivitiesPackageImpl;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.IntermediateActivitiesPackageImpl;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;
import org.modelexecution.xmof.Syntax.Classes.Kernel.impl.KernelPackageImpl;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.BasicBehaviorsPackageImpl;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.impl.CommunicationsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LociL1PackageImpl extends EPackageImpl implements LociL1Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semanticVisitorEClass = null;

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
	 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LociL1PackageImpl() {
		super(eNS_URI, LociL1Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LociL1Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LociL1Package init() {
		if (isInited) return (LociL1Package)EPackage.Registry.INSTANCE.getEPackage(LociL1Package.eNS_URI);

		// Obtain or create and register package
		LociL1PackageImpl theLociL1Package = (LociL1PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LociL1PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LociL1PackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		BasicBehaviorsPackageImpl theBasicBehaviorsPackage = (BasicBehaviorsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasicBehaviorsPackage.eNS_URI) instanceof BasicBehaviorsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasicBehaviorsPackage.eNS_URI) : BasicBehaviorsPackage.eINSTANCE);
		CommunicationsPackageImpl theCommunicationsPackage = (CommunicationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommunicationsPackage.eNS_URI) instanceof CommunicationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommunicationsPackage.eNS_URI) : CommunicationsPackage.eINSTANCE);
		KernelPackageImpl theKernelPackage = (KernelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KernelPackage.eNS_URI) instanceof KernelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KernelPackage.eNS_URI) : KernelPackage.eINSTANCE);
		IntermediateActivitiesPackageImpl theIntermediateActivitiesPackage = (IntermediateActivitiesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IntermediateActivitiesPackage.eNS_URI) instanceof IntermediateActivitiesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IntermediateActivitiesPackage.eNS_URI) : IntermediateActivitiesPackage.eINSTANCE);
		CompleteStructuredActivitiesPackageImpl theCompleteStructuredActivitiesPackage = (CompleteStructuredActivitiesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CompleteStructuredActivitiesPackage.eNS_URI) instanceof CompleteStructuredActivitiesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CompleteStructuredActivitiesPackage.eNS_URI) : CompleteStructuredActivitiesPackage.eINSTANCE);
		ExtraStructuredActivitiesPackageImpl theExtraStructuredActivitiesPackage = (ExtraStructuredActivitiesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtraStructuredActivitiesPackage.eNS_URI) instanceof ExtraStructuredActivitiesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtraStructuredActivitiesPackage.eNS_URI) : ExtraStructuredActivitiesPackage.eINSTANCE);
		IntermediateActionsPackageImpl theIntermediateActionsPackage = (IntermediateActionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IntermediateActionsPackage.eNS_URI) instanceof IntermediateActionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IntermediateActionsPackage.eNS_URI) : IntermediateActionsPackage.eINSTANCE);
		CompleteActionsPackageImpl theCompleteActionsPackage = (CompleteActionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CompleteActionsPackage.eNS_URI) instanceof CompleteActionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CompleteActionsPackage.eNS_URI) : CompleteActionsPackage.eINSTANCE);
		BasicActionsPackageImpl theBasicActionsPackage = (BasicActionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasicActionsPackage.eNS_URI) instanceof BasicActionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasicActionsPackage.eNS_URI) : BasicActionsPackage.eINSTANCE);
		org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl theKernelPackage_1 = (org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage.eNS_URI) instanceof org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage.eNS_URI) : org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage.eINSTANCE);
		org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.impl.BasicBehaviorsPackageImpl theBasicBehaviorsPackage_1 = (org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.impl.BasicBehaviorsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage.eNS_URI) instanceof org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.impl.BasicBehaviorsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage.eNS_URI) : org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage.eINSTANCE);

		// Create package meta-data objects
		theLociL1Package.createPackageContents();
		theBasicBehaviorsPackage.createPackageContents();
		theCommunicationsPackage.createPackageContents();
		theKernelPackage.createPackageContents();
		theIntermediateActivitiesPackage.createPackageContents();
		theCompleteStructuredActivitiesPackage.createPackageContents();
		theExtraStructuredActivitiesPackage.createPackageContents();
		theIntermediateActionsPackage.createPackageContents();
		theCompleteActionsPackage.createPackageContents();
		theBasicActionsPackage.createPackageContents();
		theKernelPackage_1.createPackageContents();
		theBasicBehaviorsPackage_1.createPackageContents();

		// Initialize created meta-data
		theLociL1Package.initializePackageContents();
		theBasicBehaviorsPackage.initializePackageContents();
		theCommunicationsPackage.initializePackageContents();
		theKernelPackage.initializePackageContents();
		theIntermediateActivitiesPackage.initializePackageContents();
		theCompleteStructuredActivitiesPackage.initializePackageContents();
		theExtraStructuredActivitiesPackage.initializePackageContents();
		theIntermediateActionsPackage.initializePackageContents();
		theCompleteActionsPackage.initializePackageContents();
		theBasicActionsPackage.initializePackageContents();
		theKernelPackage_1.initializePackageContents();
		theBasicBehaviorsPackage_1.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLociL1Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LociL1Package.eNS_URI, theLociL1Package);
		return theLociL1Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemanticVisitor() {
		return semanticVisitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LociL1Factory getLociL1Factory() {
		return (LociL1Factory)getEFactoryInstance();
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
		semanticVisitorEClass = createEClass(SEMANTIC_VISITOR);
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

		// Initialize classes and features; add operations and parameters
		initEClass(semanticVisitorEClass, SemanticVisitor.class, "SemanticVisitor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //LociL1PackageImpl
