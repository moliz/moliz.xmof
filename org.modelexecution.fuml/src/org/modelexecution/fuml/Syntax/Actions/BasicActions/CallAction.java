/**
 */
package org.modelexecution.fuml.Syntax.Actions.BasicActions;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.CallAction#getIsSynchronous <em>Is Synchronous</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.CallAction#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Actions.BasicActions.BasicActionsPackage#getCallAction()
 * @model abstract="true"
 * @generated
 */
public interface CallAction extends InvocationAction {
	/**
	 * Returns the value of the '<em><b>Is Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Synchronous</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Synchronous</em>' attribute.
	 * @see #setIsSynchronous(Object)
	 * @see org.modelexecution.fuml.Syntax.Actions.BasicActions.BasicActionsPackage#getCallAction_IsSynchronous()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsSynchronous();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.CallAction#getIsSynchronous <em>Is Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Synchronous</em>' attribute.
	 * @see #getIsSynchronous()
	 * @generated
	 */
	void setIsSynchronous(Object value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Actions.BasicActions.BasicActionsPackage#getCallAction_Result()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputPin> getResult();

} // CallAction
