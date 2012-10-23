/**
 */
package org.modelexecution.xmof.Syntax.Activities.IntermediateActivities;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;

import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.classifierBehavior->isEmpty()
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity#getStructuredNode <em>Structured Node</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity#getNode <em>Node</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity#getEdge <em>Edge</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends Behavior {
	/**
	 * Returns the value of the '<em><b>Structured Node</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structured Node</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structured Node</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivity_StructuredNode()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<StructuredActivityNode> getStructuredNode();

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivity_Node()
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode#getActivity
	 * @model opposite="activity" containment="true" ordered="false"
	 * @generated
	 */
	EList<ActivityNode> getNode();

	/**
	 * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Read Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Read Only</em>' attribute.
	 * @see #setIsReadOnly(Object)
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivity_IsReadOnly()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsReadOnly();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity#getIsReadOnly <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Read Only</em>' attribute.
	 * @see #getIsReadOnly()
	 * @generated
	 */
	void setIsReadOnly(Object value);

	/**
	 * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityEdge}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityEdge#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivity_Edge()
	 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityEdge#getActivity
	 * @model opposite="activity" containment="true" ordered="false"
	 * @generated
	 */
	EList<ActivityEdge> getEdge();

} // Activity
