/**
 */
package org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class IntermediateActivitiesFactoryImpl extends EFactoryImpl implements
		IntermediateActivitiesFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static IntermediateActivitiesFactory init() {
		try {
			IntermediateActivitiesFactory theIntermediateActivitiesFactory = (IntermediateActivitiesFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.modelexecution.org/xmof/syntax/activities/intermediateactivities");
			if (theIntermediateActivitiesFactory != null) {
				return theIntermediateActivitiesFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IntermediateActivitiesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public IntermediateActivitiesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case IntermediateActivitiesPackage.OBJECT_FLOW:
			return createObjectFlow();
		case IntermediateActivitiesPackage.ACTIVITY:
			return createActivity();
		case IntermediateActivitiesPackage.MERGE_NODE:
			return createMergeNode();
		case IntermediateActivitiesPackage.JOIN_NODE:
			return createJoinNode();
		case IntermediateActivitiesPackage.INITIAL_NODE:
			return createInitialNode();
		case IntermediateActivitiesPackage.FORK_NODE:
			return createForkNode();
		case IntermediateActivitiesPackage.CONTROL_FLOW:
			return createControlFlow();
		case IntermediateActivitiesPackage.DECISION_NODE:
			return createDecisionNode();
		case IntermediateActivitiesPackage.ACTIVITY_FINAL_NODE:
			return createActivityFinalNode();
		case IntermediateActivitiesPackage.ACTIVITY_PARAMETER_NODE:
			return createActivityParameterNode();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ObjectFlow createObjectFlow() {
		ObjectFlowImpl objectFlow = new ObjectFlowImpl();
		return objectFlow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	public Activity createActivity(BehavioredEOperation operation) {
		return createActivity(operation, true);
	}

	public Activity createActivity(BehavioredEOperation operation,
			boolean addActivityToBehavioredClassifier) {
		Activity activity = createActivity();
		prepareActivityForOperation(activity,
				operation, addActivityToBehavioredClassifier);
		return activity;
	}

	public void prepareActivityForOperation(Activity activity,
			BehavioredEOperation operation, boolean addActivityToBehavioredClassifier) {
		activity.setName(operation.getName());
		operation.getMethod().add(activity);
		if (addActivityToBehavioredClassifier) {
			addToOwnedBehavior(operation, activity);
		}
		addParameters(operation, activity);
	}

	private void addToOwnedBehavior(BehavioredEOperation operation,
			Activity activity) {
		if (operation.getEContainingClass() instanceof BehavioredEClass) {
			BehavioredEClass behavioredEClass = (BehavioredEClass) operation
					.getEContainingClass();
			behavioredEClass.getOwnedBehavior().add(activity);
		}
	}

	private void addParameters(BehavioredEOperation operation, Activity activity) {
		if (operation.getEType() != null) {
			DirectedParameter parameter = createDirectedParameter(operation);
			addParameterNode(activity, parameter);
		}

		for (EParameter eParameter : operation.getEParameters()) {
			DirectedParameter parameter = createDirectedParameter(eParameter);
			addParameterNode(activity, parameter);
		}
	}

	private DirectedParameter createDirectedParameter(
			BehavioredEOperation operation) {
		DirectedParameter parameter = KernelFactory.eINSTANCE
				.createDirectedParameter();
		parameter.setEType(operation.getEType());
		parameter.setEGenericType(operation.getEGenericType());
		parameter.setLowerBound(operation.getLowerBound());
		parameter.setOrdered(operation.isOrdered());
		parameter.setUnique(operation.isUnique());
		parameter.setUpperBound(operation.getUpperBound());
		parameter.setDirection(ParameterDirectionKind.RETURN);
		parameter.setName("return");
		return parameter;
	}

	private DirectedParameter createDirectedParameter(EParameter eParameter) {
		DirectedParameter parameter = KernelFactory.eINSTANCE
				.createDirectedParameter();
		parameter.setName(eParameter.getName());
		parameter.setEType(eParameter.getEType());
		parameter.setEGenericType(eParameter.getEGenericType());
		parameter.setLowerBound(eParameter.getLowerBound());
		parameter.setOrdered(eParameter.isOrdered());
		parameter.setUnique(eParameter.isUnique());
		parameter.setUpperBound(eParameter.getUpperBound());
		if(eParameter instanceof DirectedParameter) {
			parameter.setDirection(((DirectedParameter)eParameter).getDirection());
		} else {
			parameter.setDirection(ParameterDirectionKind.IN);
		}
		return parameter;
	}

	private void addParameterNode(Activity activity, DirectedParameter parameter) {
		ActivityParameterNode parameterNode = createParameterNode(parameter);
		parameterNode.setActivity(activity);
		activity.getNode().add(parameterNode);
		activity.getOwnedParameter().add(parameter);
	}

	private ActivityParameterNode createParameterNode(
			DirectedParameter parameter) {
		ActivityParameterNode parameterNode = IntermediateActivitiesFactory.eINSTANCE
				.createActivityParameterNode();
		parameterNode.setEGenericType(parameter.getEGenericType());
		parameterNode.setEType(parameter.getEType());
		parameterNode.setLowerBound(parameter.getLowerBound());
		parameterNode.setOrdered(parameter.isOrdered());
		parameterNode.setUnique(parameter.isUnique());
		parameterNode.setUpperBound(parameter.getUpperBound());
		parameterNode.setParameter(parameter);
		parameterNode.setName(parameter.getName());
		return parameterNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MergeNode createMergeNode() {
		MergeNodeImpl mergeNode = new MergeNodeImpl();
		return mergeNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public JoinNode createJoinNode() {
		JoinNodeImpl joinNode = new JoinNodeImpl();
		return joinNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InitialNode createInitialNode() {
		InitialNodeImpl initialNode = new InitialNodeImpl();
		return initialNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ForkNode createForkNode() {
		ForkNodeImpl forkNode = new ForkNodeImpl();
		return forkNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ControlFlow createControlFlow() {
		ControlFlowImpl controlFlow = new ControlFlowImpl();
		return controlFlow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DecisionNode createDecisionNode() {
		DecisionNodeImpl decisionNode = new DecisionNodeImpl();
		return decisionNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ActivityFinalNode createActivityFinalNode() {
		ActivityFinalNodeImpl activityFinalNode = new ActivityFinalNodeImpl();
		return activityFinalNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ActivityParameterNode createActivityParameterNode() {
		ActivityParameterNodeImpl activityParameterNode = new ActivityParameterNodeImpl();
		return activityParameterNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IntermediateActivitiesPackage getIntermediateActivitiesPackage() {
		return (IntermediateActivitiesPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IntermediateActivitiesPackage getPackage() {
		return IntermediateActivitiesPackage.eINSTANCE;
	}

} // IntermediateActivitiesFactoryImpl
