/**
 */
package org.modelexecution.xmof.Syntax.Actions.CompleteActions.util;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InvocationAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.AcceptEventAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadExtentAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartObjectBehaviorAction;
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
 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage
 * @generated
 */
public class CompleteActionsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CompleteActionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteActionsSwitch() {
		if (modelPackage == null) {
			modelPackage = CompleteActionsPackage.eINSTANCE;
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
			case CompleteActionsPackage.START_CLASSIFIER_BEHAVIOR_ACTION: {
				StartClassifierBehaviorAction startClassifierBehaviorAction = (StartClassifierBehaviorAction)theEObject;
				T result = caseStartClassifierBehaviorAction(startClassifierBehaviorAction);
				if (result == null) result = caseAction(startClassifierBehaviorAction);
				if (result == null) result = caseExecutableNode(startClassifierBehaviorAction);
				if (result == null) result = caseActivityNode(startClassifierBehaviorAction);
				if (result == null) result = caseENamedElement(startClassifierBehaviorAction);
				if (result == null) result = caseEModelElement(startClassifierBehaviorAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.START_OBJECT_BEHAVIOR_ACTION: {
				StartObjectBehaviorAction startObjectBehaviorAction = (StartObjectBehaviorAction)theEObject;
				T result = caseStartObjectBehaviorAction(startObjectBehaviorAction);
				if (result == null) result = caseCallAction(startObjectBehaviorAction);
				if (result == null) result = caseInvocationAction(startObjectBehaviorAction);
				if (result == null) result = caseAction(startObjectBehaviorAction);
				if (result == null) result = caseExecutableNode(startObjectBehaviorAction);
				if (result == null) result = caseActivityNode(startObjectBehaviorAction);
				if (result == null) result = caseENamedElement(startObjectBehaviorAction);
				if (result == null) result = caseEModelElement(startObjectBehaviorAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.REDUCE_ACTION: {
				ReduceAction reduceAction = (ReduceAction)theEObject;
				T result = caseReduceAction(reduceAction);
				if (result == null) result = caseAction(reduceAction);
				if (result == null) result = caseExecutableNode(reduceAction);
				if (result == null) result = caseActivityNode(reduceAction);
				if (result == null) result = caseENamedElement(reduceAction);
				if (result == null) result = caseEModelElement(reduceAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.READ_EXTENT_ACTION: {
				ReadExtentAction readExtentAction = (ReadExtentAction)theEObject;
				T result = caseReadExtentAction(readExtentAction);
				if (result == null) result = caseAction(readExtentAction);
				if (result == null) result = caseExecutableNode(readExtentAction);
				if (result == null) result = caseActivityNode(readExtentAction);
				if (result == null) result = caseENamedElement(readExtentAction);
				if (result == null) result = caseEModelElement(readExtentAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.READ_IS_CLASSIFIED_OBJECT_ACTION: {
				ReadIsClassifiedObjectAction readIsClassifiedObjectAction = (ReadIsClassifiedObjectAction)theEObject;
				T result = caseReadIsClassifiedObjectAction(readIsClassifiedObjectAction);
				if (result == null) result = caseAction(readIsClassifiedObjectAction);
				if (result == null) result = caseExecutableNode(readIsClassifiedObjectAction);
				if (result == null) result = caseActivityNode(readIsClassifiedObjectAction);
				if (result == null) result = caseENamedElement(readIsClassifiedObjectAction);
				if (result == null) result = caseEModelElement(readIsClassifiedObjectAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.RECLASSIFY_OBJECT_ACTION: {
				ReclassifyObjectAction reclassifyObjectAction = (ReclassifyObjectAction)theEObject;
				T result = caseReclassifyObjectAction(reclassifyObjectAction);
				if (result == null) result = caseAction(reclassifyObjectAction);
				if (result == null) result = caseExecutableNode(reclassifyObjectAction);
				if (result == null) result = caseActivityNode(reclassifyObjectAction);
				if (result == null) result = caseENamedElement(reclassifyObjectAction);
				if (result == null) result = caseEModelElement(reclassifyObjectAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CompleteActionsPackage.ACCEPT_EVENT_ACTION: {
				AcceptEventAction acceptEventAction = (AcceptEventAction)theEObject;
				T result = caseAcceptEventAction(acceptEventAction);
				if (result == null) result = caseAction(acceptEventAction);
				if (result == null) result = caseExecutableNode(acceptEventAction);
				if (result == null) result = caseActivityNode(acceptEventAction);
				if (result == null) result = caseENamedElement(acceptEventAction);
				if (result == null) result = caseEModelElement(acceptEventAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start Classifier Behavior Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Classifier Behavior Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartClassifierBehaviorAction(StartClassifierBehaviorAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Start Object Behavior Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Object Behavior Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartObjectBehaviorAction(StartObjectBehaviorAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reduce Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reduce Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReduceAction(ReduceAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Extent Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Extent Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadExtentAction(ReadExtentAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Is Classified Object Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Is Classified Object Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadIsClassifiedObjectAction(ReadIsClassifiedObjectAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reclassify Object Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reclassify Object Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReclassifyObjectAction(ReclassifyObjectAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Accept Event Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Accept Event Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAcceptEventAction(AcceptEventAction object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Invocation Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invocation Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvocationAction(InvocationAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallAction(CallAction object) {
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

} //CompleteActionsSwitch
