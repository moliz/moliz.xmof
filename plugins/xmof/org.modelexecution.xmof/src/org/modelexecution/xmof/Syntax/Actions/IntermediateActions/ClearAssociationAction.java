/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions;

import org.eclipse.emf.ecore.EReference;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Clear Association Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction#getObject <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getClearAssociationAction()
 * @model
 * @generated
 */
public interface ClearAssociationAction extends Action {
	/**
	 * Returns the value of the '<em><b>Association</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Association to be cleared.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Association</em>' reference.
	 * @see #setAssociation(EReference)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getClearAssociationAction_Association()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EReference getAssociation();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction#getAssociation <em>Association</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association</em>' reference.
	 * @see #getAssociation()
	 * @generated
	 */
	void setAssociation(EReference value);

	/**
	 * Returns the value of the '<em><b>Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gives the input pin from which is obtained the object whose participation in
	 *                   the association is to be cleared.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Object</em>' containment reference.
	 * @see #setObject(InputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getClearAssociationAction_Object()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	InputPin getObject();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction#getObject <em>Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' containment reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(InputPin value);

} // ClearAssociationAction
