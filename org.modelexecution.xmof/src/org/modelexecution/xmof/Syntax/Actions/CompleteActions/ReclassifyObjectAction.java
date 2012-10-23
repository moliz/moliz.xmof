/**
 */
package org.modelexecution.xmof.Syntax.Actions.CompleteActions;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClassifier;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reclassify Object Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.oldClassifier->forAll(oclIsKindOf(Class)) and
 *                   self.newClassifier->forAll(oclIsKindOf(Class))
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getIsReplaceAll <em>Is Replace All</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getOldClassifier <em>Old Classifier</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getObject <em>Object</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getNewClassifier <em>New Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReclassifyObjectAction()
 * @model
 * @generated
 */
public interface ReclassifyObjectAction extends Action {
	/**
	 * Returns the value of the '<em><b>Is Replace All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Replace All</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Replace All</em>' attribute.
	 * @see #setIsReplaceAll(Object)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReclassifyObjectAction_IsReplaceAll()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsReplaceAll();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getIsReplaceAll <em>Is Replace All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Replace All</em>' attribute.
	 * @see #getIsReplaceAll()
	 * @generated
	 */
	void setIsReplaceAll(Object value);

	/**
	 * Returns the value of the '<em><b>Old Classifier</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClassifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Old Classifier</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Classifier</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReclassifyObjectAction_OldClassifier()
	 * @model ordered="false"
	 * @generated
	 */
	EList<EClassifier> getOldClassifier();

	/**
	 * Returns the value of the '<em><b>Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' containment reference.
	 * @see #setObject(InputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReclassifyObjectAction_Object()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	InputPin getObject();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction#getObject <em>Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' containment reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(InputPin value);

	/**
	 * Returns the value of the '<em><b>New Classifier</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClassifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Classifier</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Classifier</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Actions.CompleteActions.CompleteActionsPackage#getReclassifyObjectAction_NewClassifier()
	 * @model ordered="false"
	 * @generated
	 */
	EList<EClassifier> getNewClassifier();

} // ReclassifyObjectAction
