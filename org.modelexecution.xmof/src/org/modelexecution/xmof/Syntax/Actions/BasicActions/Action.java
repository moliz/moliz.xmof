/**
 */
package org.modelexecution.xmof.Syntax.Actions.BasicActions;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ExecutableNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getOutput <em>Output</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getContext <em>Context</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getInput <em>Input</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#isLocallyReentrant <em>Locally Reentrant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage#getAction()
 * @model abstract="true"
 * @generated
 */
public interface Action extends ExecutableNode {
	/**
	 * Returns the value of the '<em><b>Output</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ordered set of output pins connected to the Action. The action places its
	 *                   results onto pins in this set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Output</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage#getAction_Output()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<OutputPin> getOutput();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The classifier that owns the behavior of which this action is a part.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Context</em>' reference.
	 * @see #setContext(EClassifier)
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage#getAction_Context()
	 * @model ordered="false"
	 * @generated
	 */
	EClassifier getContext();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(EClassifier value);

	/**
	 * Returns the value of the '<em><b>Input</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage#getAction_Input()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<InputPin> getInput();

	/**
	 * Returns the value of the '<em><b>Locally Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locally Reentrant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locally Reentrant</em>' attribute.
	 * @see #setLocallyReentrant(boolean)
	 * @see org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage#getAction_LocallyReentrant()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isLocallyReentrant();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.Action#isLocallyReentrant <em>Locally Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locally Reentrant</em>' attribute.
	 * @see #isLocallyReentrant()
	 * @generated
	 */
	void setLocallyReentrant(boolean value);

} // Action
