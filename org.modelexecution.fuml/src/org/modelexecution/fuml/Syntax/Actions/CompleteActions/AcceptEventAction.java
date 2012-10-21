/**
 */
package org.modelexecution.fuml.Syntax.Actions.CompleteActions;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.fuml.Syntax.Actions.BasicActions.Action;
import org.modelexecution.fuml.Syntax.Actions.BasicActions.OutputPin;

import org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Trigger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Accept Event Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.context.oclAsType(Class).isActive
 * self.trigger.event->forAll(oclIsKindOf(SignalEvent))
 * let cls:Class = self.context.oclAsType(Class) in
 *  let
 *                   classes:Bag(Class) =
 *                   cls.allParents()->select(oclIsKindOf(Class))->collect(oclAsType(Class))->union(cls->asBag())
 *                   in
 *                   classes.ownedReception.signal->includesAll(self.trigger.event->collect(oclAsType(SignalEvent)).signal)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.CompleteActions.AcceptEventAction#getIsUnmarshall <em>Is Unmarshall</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.CompleteActions.AcceptEventAction#getResult <em>Result</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.CompleteActions.AcceptEventAction#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Actions.CompleteActions.CompleteActionsPackage#getAcceptEventAction()
 * @model
 * @generated
 */
public interface AcceptEventAction extends Action {
	/**
	 * Returns the value of the '<em><b>Is Unmarshall</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Unmarshall</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Unmarshall</em>' attribute.
	 * @see #setIsUnmarshall(Object)
	 * @see org.modelexecution.fuml.Syntax.Actions.CompleteActions.CompleteActionsPackage#getAcceptEventAction_IsUnmarshall()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsUnmarshall();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Actions.CompleteActions.AcceptEventAction#getIsUnmarshall <em>Is Unmarshall</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unmarshall</em>' attribute.
	 * @see #getIsUnmarshall()
	 * @generated
	 */
	void setIsUnmarshall(Object value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Actions.CompleteActions.CompleteActionsPackage#getAcceptEventAction_Result()
	 * @model ordered="false"
	 * @generated
	 */
	EList<OutputPin> getResult();

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.CommonBehaviors.Communications.Trigger}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Actions.CompleteActions.CompleteActionsPackage#getAcceptEventAction_Trigger()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<Trigger> getTrigger();

} // AcceptEventAction
