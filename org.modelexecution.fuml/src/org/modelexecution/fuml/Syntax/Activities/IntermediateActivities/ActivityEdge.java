/**
 */
package org.modelexecution.fuml.Syntax.Activities.IntermediateActivities;

import org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;

import org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement;
import org.modelexecution.fuml.Syntax.Classes.Kernel.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.guard->notEmpty() implies
 *                 self.source.oclIsKindOf(DecisionNode)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getSource <em>Source</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getTarget <em>Target</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getInStructuredNode <em>In Structured Node</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getGuard <em>Guard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge()
 * @model abstract="true"
 * @generated
 */
public interface ActivityEdge extends RedefinableElement {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.Activity#getEdge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' container reference.
	 * @see #setActivity(Activity)
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge_Activity()
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.Activity#getEdge
	 * @model opposite="edge" transient="false" ordered="false"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getActivity <em>Activity</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' container reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityNode#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(ActivityNode)
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge_Source()
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityNode#getOutgoing
	 * @model opposite="outgoing" required="true" ordered="false"
	 * @generated
	 */
	ActivityNode getSource();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ActivityNode value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityNode#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(ActivityNode)
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge_Target()
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityNode#getIncoming
	 * @model opposite="incoming" required="true" ordered="false"
	 * @generated
	 */
	ActivityNode getTarget();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(ActivityNode value);

	/**
	 * Returns the value of the '<em><b>In Structured Node</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode#getEdge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Structured Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Structured Node</em>' container reference.
	 * @see #setInStructuredNode(StructuredActivityNode)
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge_InStructuredNode()
	 * @see org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode#getEdge
	 * @model opposite="edge" transient="false" ordered="false"
	 * @generated
	 */
	StructuredActivityNode getInStructuredNode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getInStructuredNode <em>In Structured Node</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Structured Node</em>' container reference.
	 * @see #getInStructuredNode()
	 * @generated
	 */
	void setInStructuredNode(StructuredActivityNode value);

	/**
	 * Returns the value of the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guard</em>' containment reference.
	 * @see #setGuard(ValueSpecification)
	 * @see org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage#getActivityEdge_Guard()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ValueSpecification getGuard();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.ActivityEdge#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(ValueSpecification value);

} // ActivityEdge
