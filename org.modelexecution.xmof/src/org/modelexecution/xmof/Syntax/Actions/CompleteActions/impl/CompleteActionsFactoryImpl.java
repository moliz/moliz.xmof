/**
 */
package org.modelexecution.xmof.Syntax.Actions.CompleteActions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.AcceptEventAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadExtentAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartObjectBehaviorAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompleteActionsFactoryImpl extends EFactoryImpl implements CompleteActionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CompleteActionsFactory init() {
		try {
			CompleteActionsFactory theCompleteActionsFactory = (CompleteActionsFactory)EPackage.Registry.INSTANCE.getEFactory(CompleteActionsPackage.eNS_URI);
			if (theCompleteActionsFactory != null) {
				return theCompleteActionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CompleteActionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteActionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CompleteActionsPackage.START_CLASSIFIER_BEHAVIOR_ACTION: return createStartClassifierBehaviorAction();
			case CompleteActionsPackage.START_OBJECT_BEHAVIOR_ACTION: return createStartObjectBehaviorAction();
			case CompleteActionsPackage.REDUCE_ACTION: return createReduceAction();
			case CompleteActionsPackage.READ_EXTENT_ACTION: return createReadExtentAction();
			case CompleteActionsPackage.READ_IS_CLASSIFIED_OBJECT_ACTION: return createReadIsClassifiedObjectAction();
			case CompleteActionsPackage.RECLASSIFY_OBJECT_ACTION: return createReclassifyObjectAction();
			case CompleteActionsPackage.ACCEPT_EVENT_ACTION: return createAcceptEventAction();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartClassifierBehaviorAction createStartClassifierBehaviorAction() {
		StartClassifierBehaviorActionImpl startClassifierBehaviorAction = new StartClassifierBehaviorActionImpl();
		return startClassifierBehaviorAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartObjectBehaviorAction createStartObjectBehaviorAction() {
		StartObjectBehaviorActionImpl startObjectBehaviorAction = new StartObjectBehaviorActionImpl();
		return startObjectBehaviorAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReduceAction createReduceAction() {
		ReduceActionImpl reduceAction = new ReduceActionImpl();
		return reduceAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ReadExtentAction createReadExtentAction() {
		ReadExtentActionImpl readExtentAction = new ReadExtentActionImpl();
		
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName("result"); //$NON-NLS-1$
		outputPin.setLowerBound(0);
		outputPin.setUpperBound(-1);
		readExtentAction.setResult(outputPin);
		
		return readExtentAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ReadIsClassifiedObjectAction createReadIsClassifiedObjectAction() {
		ReadIsClassifiedObjectActionImpl readIsClassifiedObjectAction = new ReadIsClassifiedObjectActionImpl();
		
		InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputPin.setName("object");
		inputPin.setLowerBound(1);
		inputPin.setUpperBound(1);
		readIsClassifiedObjectAction.setObject(inputPin);
		
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName("result"); //$NON-NLS-1$
		outputPin.setLowerBound(1);
		outputPin.setUpperBound(1);
		readIsClassifiedObjectAction.setResult(outputPin);
		
		return readIsClassifiedObjectAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReclassifyObjectAction createReclassifyObjectAction() {
		ReclassifyObjectActionImpl reclassifyObjectAction = new ReclassifyObjectActionImpl();
		return reclassifyObjectAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AcceptEventAction createAcceptEventAction() {
		AcceptEventActionImpl acceptEventAction = new AcceptEventActionImpl();
		return acceptEventAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteActionsPackage getCompleteActionsPackage() {
		return (CompleteActionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CompleteActionsPackage getPackage() {
		return CompleteActionsPackage.eINSTANCE;
	}

} //CompleteActionsFactoryImpl
