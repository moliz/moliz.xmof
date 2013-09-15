/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadSelfAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.TestIdentityAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntermediateActionsFactoryImpl extends EFactoryImpl implements IntermediateActionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntermediateActionsFactory init() {
		try {
			IntermediateActionsFactory theIntermediateActionsFactory = (IntermediateActionsFactory)EPackage.Registry.INSTANCE.getEFactory(IntermediateActionsPackage.eNS_URI);
			if (theIntermediateActionsFactory != null) {
				return theIntermediateActionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IntermediateActionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntermediateActionsFactoryImpl() {
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
			case IntermediateActionsPackage.TEST_IDENTITY_ACTION: return createTestIdentityAction();
			case IntermediateActionsPackage.VALUE_SPECIFICATION_ACTION: return createValueSpecificationAction();
			case IntermediateActionsPackage.LINK_END_DATA: return createLinkEndData();
			case IntermediateActionsPackage.REMOVE_STRUCTURAL_FEATURE_VALUE_ACTION: return createRemoveStructuralFeatureValueAction();
			case IntermediateActionsPackage.READ_LINK_ACTION: return createReadLinkAction();
			case IntermediateActionsPackage.READ_SELF_ACTION: return createReadSelfAction();
			case IntermediateActionsPackage.READ_STRUCTURAL_FEATURE_ACTION: return createReadStructuralFeatureAction();
			case IntermediateActionsPackage.LINK_END_CREATION_DATA: return createLinkEndCreationData();
			case IntermediateActionsPackage.LINK_END_DESTRUCTION_DATA: return createLinkEndDestructionData();
			case IntermediateActionsPackage.CLEAR_ASSOCIATION_ACTION: return createClearAssociationAction();
			case IntermediateActionsPackage.CLEAR_STRUCTURAL_FEATURE_ACTION: return createClearStructuralFeatureAction();
			case IntermediateActionsPackage.CREATE_LINK_ACTION: return createCreateLinkAction();
			case IntermediateActionsPackage.CREATE_OBJECT_ACTION: return createCreateObjectAction();
			case IntermediateActionsPackage.DESTROY_LINK_ACTION: return createDestroyLinkAction();
			case IntermediateActionsPackage.DESTROY_OBJECT_ACTION: return createDestroyObjectAction();
			case IntermediateActionsPackage.ADD_STRUCTURAL_FEATURE_VALUE_ACTION: return createAddStructuralFeatureValueAction();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TestIdentityAction createTestIdentityAction() {
		TestIdentityActionImpl action = new TestIdentityActionImpl();
		
		InputPin inputpin1 = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpin1.setName("first");
		inputpin1.setLowerBound(1);
		inputpin1.setUpperBound(1);
		action.setFirst(inputpin1);
		
		InputPin inputpin2 = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpin2.setName("second");
		inputpin2.setLowerBound(1);
		inputpin2.setUpperBound(1);
		action.setSecond(inputpin2);
		
		OutputPin outputpin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputpin.setName("result");
		outputpin.setLowerBound(1);
		outputpin.setUpperBound(1);
		action.setResult(outputpin);		
		
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ValueSpecificationAction createValueSpecificationAction() {
		ValueSpecificationActionImpl action = new ValueSpecificationActionImpl();
		
		OutputPin outputpin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputpin.setName("result");
		outputpin.setLowerBound(1);
		outputpin.setUpperBound(1);
		action.setResult(outputpin);
		
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkEndData createLinkEndData() {
		LinkEndDataImpl linkEndData = new LinkEndDataImpl();
		return linkEndData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RemoveStructuralFeatureValueAction createRemoveStructuralFeatureValueAction() {
		RemoveStructuralFeatureValueActionImpl action = new RemoveStructuralFeatureValueActionImpl();

		OutputPin outputpin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputpin.setName("result");
		outputpin.setLowerBound(1);
		outputpin.setUpperBound(1);
		action.setResult(outputpin);

		InputPin inputpinObject = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpinObject.setName("object");
		inputpinObject.setLowerBound(1);
		inputpinObject.setUpperBound(1);
		action.setObject(inputpinObject);

		InputPin inputpinValue = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpinValue.setName("value");
		inputpinValue.setLowerBound(1);
		inputpinValue.setUpperBound(1);
		action.setValue(inputpinValue);

		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadLinkAction createReadLinkAction() {
		ReadLinkActionImpl readLinkAction = new ReadLinkActionImpl();
		return readLinkAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ReadSelfAction createReadSelfAction() {
		ReadSelfActionImpl action = new ReadSelfActionImpl();
		
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName("result"); //$NON-NLS-1$
		outputPin.setLowerBound(1);
		outputPin.setUpperBound(1);
		action.setResult(outputPin);
		
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ReadStructuralFeatureAction createReadStructuralFeatureAction() {
		ReadStructuralFeatureActionImpl action = new ReadStructuralFeatureActionImpl();				
		
		OutputPin outputpin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputpin.setName("result");
		outputpin.setLowerBound(0);
		outputpin.setUpperBound(0);
		action.setResult(outputpin);
		
		InputPin inputpin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpin.setName("object");
		inputpin.setLowerBound(1);
		inputpin.setUpperBound(1);		
		action.setObject(inputpin);
		
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkEndCreationData createLinkEndCreationData() {
		LinkEndCreationDataImpl linkEndCreationData = new LinkEndCreationDataImpl();
		return linkEndCreationData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkEndDestructionData createLinkEndDestructionData() {
		LinkEndDestructionDataImpl linkEndDestructionData = new LinkEndDestructionDataImpl();
		return linkEndDestructionData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClearAssociationAction createClearAssociationAction() {
		ClearAssociationActionImpl clearAssociationAction = new ClearAssociationActionImpl();
		return clearAssociationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ClearStructuralFeatureAction createClearStructuralFeatureAction() {
		ClearStructuralFeatureActionImpl action = new ClearStructuralFeatureActionImpl();

		OutputPin outputpin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputpin.setName("result");
		outputpin.setLowerBound(1);
		outputpin.setUpperBound(1);
		action.setResult(outputpin);

		InputPin inputpin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpin.setName("object");
		inputpin.setLowerBound(1);
		inputpin.setUpperBound(1);
		action.setObject(inputpin);

		return action;	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateLinkAction createCreateLinkAction() {
		CreateLinkActionImpl createLinkAction = new CreateLinkActionImpl();
		return createLinkAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CreateObjectAction createCreateObjectAction() {
		CreateObjectActionImpl createObjectAction = new CreateObjectActionImpl();
		
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName("result"); //$NON-NLS-1$
		outputPin.setLowerBound(1);
		outputPin.setUpperBound(1);
		createObjectAction.setResult(outputPin);
		
		return createObjectAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DestroyLinkAction createDestroyLinkAction() {
		DestroyLinkActionImpl destroyLinkAction = new DestroyLinkActionImpl();
		return destroyLinkAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DestroyObjectAction createDestroyObjectAction() {
		DestroyObjectActionImpl destroyObjectAction = new DestroyObjectActionImpl();
		
		InputPin inputpin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputpin.setName("target");
		inputpin.setLowerBound(1);
		inputpin.setUpperBound(1);		
		destroyObjectAction.setTarget(inputpin);
		
		return destroyObjectAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public AddStructuralFeatureValueAction createAddStructuralFeatureValueAction() {
		AddStructuralFeatureValueActionImpl action = new AddStructuralFeatureValueActionImpl();
		
		InputPin inputPinObject = BasicActionsFactory.eINSTANCE.createInputPin();
		inputPinObject.setName("object"); //$NON-NLS-1$
		inputPinObject.setLowerBound(1);
		inputPinObject.setUpperBound(1);		
		action.setObject(inputPinObject);
		
		InputPin inputPinValue = BasicActionsFactory.eINSTANCE.createInputPin();
		inputPinValue.setName("value"); //$NON-NLS-1$
		inputPinValue.setLowerBound(1);
		inputPinValue.setUpperBound(1);
		action.setValue(inputPinValue);
		
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName("result"); //$NON-NLS-1$
		outputPin.setLowerBound(1);
		outputPin.setUpperBound(1);
		action.setResult(outputPin);
		
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntermediateActionsPackage getIntermediateActionsPackage() {
		return (IntermediateActionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IntermediateActionsPackage getPackage() {
		return IntermediateActionsPackage.eINSTANCE;
	}

} //IntermediateActionsFactoryImpl
