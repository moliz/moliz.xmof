/**
 */
package modeldebuggerconfig;

import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Node Step Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link modeldebuggerconfig.ActivityNodeStepDefinition#getCondition <em>Condition</em>}</li>
 *   <li>{@link modeldebuggerconfig.ActivityNodeStepDefinition#getActivityNode <em>Activity Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getActivityNodeStepDefinition()
 * @model
 * @generated
 */
public interface ActivityNodeStepDefinition extends StepDefinition {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getActivityNodeStepDefinition_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link modeldebuggerconfig.ActivityNodeStepDefinition#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Activity Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Node</em>' reference.
	 * @see #setActivityNode(ActivityNode)
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getActivityNodeStepDefinition_ActivityNode()
	 * @model
	 * @generated
	 */
	ActivityNode getActivityNode();

	/**
	 * Sets the value of the '{@link modeldebuggerconfig.ActivityNodeStepDefinition#getActivityNode <em>Activity Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Node</em>' reference.
	 * @see #getActivityNode()
	 * @generated
	 */
	void setActivityNode(ActivityNode value);

} // ActivityNodeStepDefinition
