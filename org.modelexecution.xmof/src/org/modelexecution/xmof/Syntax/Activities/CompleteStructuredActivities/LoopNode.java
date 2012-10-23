/**
 */
package org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Loop Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.setupPart->isEmpty()
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getIsTestedFirst <em>Is Tested First</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getDecider <em>Decider</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getTest <em>Test</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getBodyOutput <em>Body Output</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getLoopVariableInput <em>Loop Variable Input</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getBodyPart <em>Body Part</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getResult <em>Result</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getLoopVariable <em>Loop Variable</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getSetupPart <em>Setup Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode()
 * @model
 * @generated
 */
public interface LoopNode extends StructuredActivityNode {
	/**
	 * Returns the value of the '<em><b>Is Tested First</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Tested First</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Tested First</em>' attribute.
	 * @see #setIsTestedFirst(Object)
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_IsTestedFirst()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsTestedFirst();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getIsTestedFirst <em>Is Tested First</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Tested First</em>' attribute.
	 * @see #getIsTestedFirst()
	 * @generated
	 */
	void setIsTestedFirst(Object value);

	/**
	 * Returns the value of the '<em><b>Decider</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decider</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decider</em>' reference.
	 * @see #setDecider(OutputPin)
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_Decider()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	OutputPin getDecider();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode#getDecider <em>Decider</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decider</em>' reference.
	 * @see #getDecider()
	 * @generated
	 */
	void setDecider(OutputPin value);

	/**
	 * Returns the value of the '<em><b>Test</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ExecutableNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_Test()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<ExecutableNode> getTest();

	/**
	 * Returns the value of the '<em><b>Body Output</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Output</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Output</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_BodyOutput()
	 * @model
	 * @generated
	 */
	EList<OutputPin> getBodyOutput();

	/**
	 * Returns the value of the '<em><b>Loop Variable Input</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Variable Input</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Variable Input</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_LoopVariableInput()
	 * @model containment="true"
	 * @generated
	 */
	EList<InputPin> getLoopVariableInput();

	/**
	 * Returns the value of the '<em><b>Body Part</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ExecutableNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Part</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Part</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_BodyPart()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ExecutableNode> getBodyPart();

	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_Result()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputPin> getResult();

	/**
	 * Returns the value of the '<em><b>Loop Variable</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Variable</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Variable</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_LoopVariable()
	 * @model
	 * @generated
	 */
	EList<OutputPin> getLoopVariable();

	/**
	 * Returns the value of the '<em><b>Setup Part</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ExecutableNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setup Part</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Setup Part</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage#getLoopNode_SetupPart()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ExecutableNode> getSetupPart();

} // LoopNode
