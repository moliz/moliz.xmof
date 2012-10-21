/**
 */
package org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.fuml.Syntax.Actions.BasicActions.OutputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getIsDeterminate <em>Is Determinate</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getIsAssured <em>Is Assured</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getClause <em>Clause</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getConditionalNode()
 * @model
 * @generated
 */
public interface ConditionalNode extends StructuredActivityNode {
	/**
	 * Returns the value of the '<em><b>Is Determinate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Determinate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Determinate</em>' attribute.
	 * @see #setIsDeterminate(Object)
	 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getConditionalNode_IsDeterminate()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsDeterminate();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getIsDeterminate <em>Is Determinate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Determinate</em>' attribute.
	 * @see #getIsDeterminate()
	 * @generated
	 */
	void setIsDeterminate(Object value);

	/**
	 * Returns the value of the '<em><b>Is Assured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Assured</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Assured</em>' attribute.
	 * @see #setIsAssured(Object)
	 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getConditionalNode_IsAssured()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsAssured();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.ConditionalNode#getIsAssured <em>Is Assured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Assured</em>' attribute.
	 * @see #getIsAssured()
	 * @generated
	 */
	void setIsAssured(Object value);

	/**
	 * Returns the value of the '<em><b>Clause</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.Clause}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clause</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clause</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getConditionalNode_Clause()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<Clause> getClause();

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
	 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getConditionalNode_Result()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputPin> getResult();

} // ConditionalNode
