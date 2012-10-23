/**
 */
package org.modelexecution.xmof.Syntax.Actions.CompleteActions;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;

import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reduce Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getReducer <em>Reducer</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getResult <em>Result</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getCollection <em>Collection</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getIsOrdered <em>Is Ordered</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReduceAction()
 * @model
 * @generated
 */
public interface ReduceAction extends Action {
	/**
	 * Returns the value of the '<em><b>Reducer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reducer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reducer</em>' reference.
	 * @see #setReducer(Behavior)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReduceAction_Reducer()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Behavior getReducer();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getReducer <em>Reducer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reducer</em>' reference.
	 * @see #getReducer()
	 * @generated
	 */
	void setReducer(Behavior value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference.
	 * @see #setResult(OutputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReduceAction_Result()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OutputPin getResult();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(OutputPin value);

	/**
	 * Returns the value of the '<em><b>Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' containment reference.
	 * @see #setCollection(InputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReduceAction_Collection()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	InputPin getCollection();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getCollection <em>Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection</em>' containment reference.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(InputPin value);

	/**
	 * Returns the value of the '<em><b>Is Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Ordered</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Ordered</em>' attribute.
	 * @see #setIsOrdered(Object)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReduceAction_IsOrdered()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsOrdered();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction#getIsOrdered <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Ordered</em>' attribute.
	 * @see #getIsOrdered()
	 * @generated
	 */
	void setIsOrdered(Object value);

} // ReduceAction
