/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions.util;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadSelfAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.StructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.TestIdentityAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.WriteLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.WriteStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ExecutableNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage
 * @generated
 */
public class IntermediateActionsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IntermediateActionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntermediateActionsSwitch() {
		if (modelPackage == null) {
			modelPackage = IntermediateActionsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case IntermediateActionsPackage.STRUCTURAL_FEATURE_ACTION: {
				StructuralFeatureAction structuralFeatureAction = (StructuralFeatureAction)theEObject;
				T result = caseStructuralFeatureAction(structuralFeatureAction);
				if (result == null) result = caseAction(structuralFeatureAction);
				if (result == null) result = caseExecutableNode(structuralFeatureAction);
				if (result == null) result = caseActivityNode(structuralFeatureAction);
				if (result == null) result = caseENamedElement(structuralFeatureAction);
				if (result == null) result = caseEModelElement(structuralFeatureAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.TEST_IDENTITY_ACTION: {
				TestIdentityAction testIdentityAction = (TestIdentityAction)theEObject;
				T result = caseTestIdentityAction(testIdentityAction);
				if (result == null) result = caseAction(testIdentityAction);
				if (result == null) result = caseExecutableNode(testIdentityAction);
				if (result == null) result = caseActivityNode(testIdentityAction);
				if (result == null) result = caseENamedElement(testIdentityAction);
				if (result == null) result = caseEModelElement(testIdentityAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.VALUE_SPECIFICATION_ACTION: {
				ValueSpecificationAction valueSpecificationAction = (ValueSpecificationAction)theEObject;
				T result = caseValueSpecificationAction(valueSpecificationAction);
				if (result == null) result = caseAction(valueSpecificationAction);
				if (result == null) result = caseExecutableNode(valueSpecificationAction);
				if (result == null) result = caseActivityNode(valueSpecificationAction);
				if (result == null) result = caseENamedElement(valueSpecificationAction);
				if (result == null) result = caseEModelElement(valueSpecificationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.WRITE_LINK_ACTION: {
				WriteLinkAction writeLinkAction = (WriteLinkAction)theEObject;
				T result = caseWriteLinkAction(writeLinkAction);
				if (result == null) result = caseLinkAction(writeLinkAction);
				if (result == null) result = caseAction(writeLinkAction);
				if (result == null) result = caseExecutableNode(writeLinkAction);
				if (result == null) result = caseActivityNode(writeLinkAction);
				if (result == null) result = caseENamedElement(writeLinkAction);
				if (result == null) result = caseEModelElement(writeLinkAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.LINK_ACTION: {
				LinkAction linkAction = (LinkAction)theEObject;
				T result = caseLinkAction(linkAction);
				if (result == null) result = caseAction(linkAction);
				if (result == null) result = caseExecutableNode(linkAction);
				if (result == null) result = caseActivityNode(linkAction);
				if (result == null) result = caseENamedElement(linkAction);
				if (result == null) result = caseEModelElement(linkAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.LINK_END_DATA: {
				LinkEndData linkEndData = (LinkEndData)theEObject;
				T result = caseLinkEndData(linkEndData);
				if (result == null) result = caseEModelElement(linkEndData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.WRITE_STRUCTURAL_FEATURE_ACTION: {
				WriteStructuralFeatureAction writeStructuralFeatureAction = (WriteStructuralFeatureAction)theEObject;
				T result = caseWriteStructuralFeatureAction(writeStructuralFeatureAction);
				if (result == null) result = caseStructuralFeatureAction(writeStructuralFeatureAction);
				if (result == null) result = caseAction(writeStructuralFeatureAction);
				if (result == null) result = caseExecutableNode(writeStructuralFeatureAction);
				if (result == null) result = caseActivityNode(writeStructuralFeatureAction);
				if (result == null) result = caseENamedElement(writeStructuralFeatureAction);
				if (result == null) result = caseEModelElement(writeStructuralFeatureAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.REMOVE_STRUCTURAL_FEATURE_VALUE_ACTION: {
				RemoveStructuralFeatureValueAction removeStructuralFeatureValueAction = (RemoveStructuralFeatureValueAction)theEObject;
				T result = caseRemoveStructuralFeatureValueAction(removeStructuralFeatureValueAction);
				if (result == null) result = caseWriteStructuralFeatureAction(removeStructuralFeatureValueAction);
				if (result == null) result = caseStructuralFeatureAction(removeStructuralFeatureValueAction);
				if (result == null) result = caseAction(removeStructuralFeatureValueAction);
				if (result == null) result = caseExecutableNode(removeStructuralFeatureValueAction);
				if (result == null) result = caseActivityNode(removeStructuralFeatureValueAction);
				if (result == null) result = caseENamedElement(removeStructuralFeatureValueAction);
				if (result == null) result = caseEModelElement(removeStructuralFeatureValueAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.READ_LINK_ACTION: {
				ReadLinkAction readLinkAction = (ReadLinkAction)theEObject;
				T result = caseReadLinkAction(readLinkAction);
				if (result == null) result = caseLinkAction(readLinkAction);
				if (result == null) result = caseAction(readLinkAction);
				if (result == null) result = caseExecutableNode(readLinkAction);
				if (result == null) result = caseActivityNode(readLinkAction);
				if (result == null) result = caseENamedElement(readLinkAction);
				if (result == null) result = caseEModelElement(readLinkAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.READ_SELF_ACTION: {
				ReadSelfAction readSelfAction = (ReadSelfAction)theEObject;
				T result = caseReadSelfAction(readSelfAction);
				if (result == null) result = caseAction(readSelfAction);
				if (result == null) result = caseExecutableNode(readSelfAction);
				if (result == null) result = caseActivityNode(readSelfAction);
				if (result == null) result = caseENamedElement(readSelfAction);
				if (result == null) result = caseEModelElement(readSelfAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.READ_STRUCTURAL_FEATURE_ACTION: {
				ReadStructuralFeatureAction readStructuralFeatureAction = (ReadStructuralFeatureAction)theEObject;
				T result = caseReadStructuralFeatureAction(readStructuralFeatureAction);
				if (result == null) result = caseStructuralFeatureAction(readStructuralFeatureAction);
				if (result == null) result = caseAction(readStructuralFeatureAction);
				if (result == null) result = caseExecutableNode(readStructuralFeatureAction);
				if (result == null) result = caseActivityNode(readStructuralFeatureAction);
				if (result == null) result = caseENamedElement(readStructuralFeatureAction);
				if (result == null) result = caseEModelElement(readStructuralFeatureAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.LINK_END_CREATION_DATA: {
				LinkEndCreationData linkEndCreationData = (LinkEndCreationData)theEObject;
				T result = caseLinkEndCreationData(linkEndCreationData);
				if (result == null) result = caseLinkEndData(linkEndCreationData);
				if (result == null) result = caseEModelElement(linkEndCreationData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.LINK_END_DESTRUCTION_DATA: {
				LinkEndDestructionData linkEndDestructionData = (LinkEndDestructionData)theEObject;
				T result = caseLinkEndDestructionData(linkEndDestructionData);
				if (result == null) result = caseLinkEndData(linkEndDestructionData);
				if (result == null) result = caseEModelElement(linkEndDestructionData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.CLEAR_ASSOCIATION_ACTION: {
				ClearAssociationAction clearAssociationAction = (ClearAssociationAction)theEObject;
				T result = caseClearAssociationAction(clearAssociationAction);
				if (result == null) result = caseAction(clearAssociationAction);
				if (result == null) result = caseExecutableNode(clearAssociationAction);
				if (result == null) result = caseActivityNode(clearAssociationAction);
				if (result == null) result = caseENamedElement(clearAssociationAction);
				if (result == null) result = caseEModelElement(clearAssociationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.CLEAR_STRUCTURAL_FEATURE_ACTION: {
				ClearStructuralFeatureAction clearStructuralFeatureAction = (ClearStructuralFeatureAction)theEObject;
				T result = caseClearStructuralFeatureAction(clearStructuralFeatureAction);
				if (result == null) result = caseStructuralFeatureAction(clearStructuralFeatureAction);
				if (result == null) result = caseAction(clearStructuralFeatureAction);
				if (result == null) result = caseExecutableNode(clearStructuralFeatureAction);
				if (result == null) result = caseActivityNode(clearStructuralFeatureAction);
				if (result == null) result = caseENamedElement(clearStructuralFeatureAction);
				if (result == null) result = caseEModelElement(clearStructuralFeatureAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.CREATE_LINK_ACTION: {
				CreateLinkAction createLinkAction = (CreateLinkAction)theEObject;
				T result = caseCreateLinkAction(createLinkAction);
				if (result == null) result = caseWriteLinkAction(createLinkAction);
				if (result == null) result = caseLinkAction(createLinkAction);
				if (result == null) result = caseAction(createLinkAction);
				if (result == null) result = caseExecutableNode(createLinkAction);
				if (result == null) result = caseActivityNode(createLinkAction);
				if (result == null) result = caseENamedElement(createLinkAction);
				if (result == null) result = caseEModelElement(createLinkAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.CREATE_OBJECT_ACTION: {
				CreateObjectAction createObjectAction = (CreateObjectAction)theEObject;
				T result = caseCreateObjectAction(createObjectAction);
				if (result == null) result = caseAction(createObjectAction);
				if (result == null) result = caseExecutableNode(createObjectAction);
				if (result == null) result = caseActivityNode(createObjectAction);
				if (result == null) result = caseENamedElement(createObjectAction);
				if (result == null) result = caseEModelElement(createObjectAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.DESTROY_LINK_ACTION: {
				DestroyLinkAction destroyLinkAction = (DestroyLinkAction)theEObject;
				T result = caseDestroyLinkAction(destroyLinkAction);
				if (result == null) result = caseWriteLinkAction(destroyLinkAction);
				if (result == null) result = caseLinkAction(destroyLinkAction);
				if (result == null) result = caseAction(destroyLinkAction);
				if (result == null) result = caseExecutableNode(destroyLinkAction);
				if (result == null) result = caseActivityNode(destroyLinkAction);
				if (result == null) result = caseENamedElement(destroyLinkAction);
				if (result == null) result = caseEModelElement(destroyLinkAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.DESTROY_OBJECT_ACTION: {
				DestroyObjectAction destroyObjectAction = (DestroyObjectAction)theEObject;
				T result = caseDestroyObjectAction(destroyObjectAction);
				if (result == null) result = caseAction(destroyObjectAction);
				if (result == null) result = caseExecutableNode(destroyObjectAction);
				if (result == null) result = caseActivityNode(destroyObjectAction);
				if (result == null) result = caseENamedElement(destroyObjectAction);
				if (result == null) result = caseEModelElement(destroyObjectAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntermediateActionsPackage.ADD_STRUCTURAL_FEATURE_VALUE_ACTION: {
				AddStructuralFeatureValueAction addStructuralFeatureValueAction = (AddStructuralFeatureValueAction)theEObject;
				T result = caseAddStructuralFeatureValueAction(addStructuralFeatureValueAction);
				if (result == null) result = caseWriteStructuralFeatureAction(addStructuralFeatureValueAction);
				if (result == null) result = caseStructuralFeatureAction(addStructuralFeatureValueAction);
				if (result == null) result = caseAction(addStructuralFeatureValueAction);
				if (result == null) result = caseExecutableNode(addStructuralFeatureValueAction);
				if (result == null) result = caseActivityNode(addStructuralFeatureValueAction);
				if (result == null) result = caseENamedElement(addStructuralFeatureValueAction);
				if (result == null) result = caseEModelElement(addStructuralFeatureValueAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeatureAction(StructuralFeatureAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Identity Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Identity Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestIdentityAction(TestIdentityAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Specification Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Specification Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSpecificationAction(ValueSpecificationAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Write Link Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Write Link Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWriteLinkAction(WriteLinkAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkAction(LinkAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link End Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link End Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkEndData(LinkEndData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Write Structural Feature Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Write Structural Feature Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWriteStructuralFeatureAction(WriteStructuralFeatureAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Structural Feature Value Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Structural Feature Value Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveStructuralFeatureValueAction(RemoveStructuralFeatureValueAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Link Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Link Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadLinkAction(ReadLinkAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Self Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Self Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadSelfAction(ReadSelfAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Structural Feature Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Structural Feature Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadStructuralFeatureAction(ReadStructuralFeatureAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link End Creation Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link End Creation Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkEndCreationData(LinkEndCreationData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link End Destruction Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link End Destruction Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkEndDestructionData(LinkEndDestructionData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clear Association Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clear Association Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClearAssociationAction(ClearAssociationAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clear Structural Feature Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clear Structural Feature Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClearStructuralFeatureAction(ClearStructuralFeatureAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Create Link Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Link Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateLinkAction(CreateLinkAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Create Object Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Object Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateObjectAction(CreateObjectAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Destroy Link Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Destroy Link Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDestroyLinkAction(DestroyLinkAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Destroy Object Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Destroy Object Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDestroyObjectAction(DestroyObjectAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Structural Feature Value Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Structural Feature Value Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddStructuralFeatureValueAction(AddStructuralFeatureValueAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseENamedElement(ENamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityNode(ActivityNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executable Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executable Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutableNode(ExecutableNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAction(Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //IntermediateActionsSwitch
