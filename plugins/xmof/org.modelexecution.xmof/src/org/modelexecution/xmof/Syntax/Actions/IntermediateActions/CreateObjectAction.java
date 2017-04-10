/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions;

import org.eclipse.emf.ecore.EClassifier;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Create Object Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction#getResult <em>Result</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction#getClassifier <em>Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getCreateObjectAction()
 * @model
 * @generated
 */
public interface CreateObjectAction extends Action {
	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gives the output pin on which the result is put.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Result</em>' containment reference.
	 * @see #setResult(OutputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getCreateObjectAction_Result()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	OutputPin getResult();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(OutputPin value);

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Classifier to be instantiated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier</em>' reference.
	 * @see #setClassifier(EClassifier)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getCreateObjectAction_Classifier()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EClassifier getClassifier();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction#getClassifier <em>Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' reference.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(EClassifier value);

} // CreateObjectAction
