/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link End Destruction Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData#getIsDestroyDuplicates <em>Is Destroy Duplicates</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData#getDestroyAt <em>Destroy At</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndDestructionData()
 * @model
 * @generated
 */
public interface LinkEndDestructionData extends LinkEndData {
	/**
	 * Returns the value of the '<em><b>Is Destroy Duplicates</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Destroy Duplicates</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Destroy Duplicates</em>' attribute.
	 * @see #setIsDestroyDuplicates(Object)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndDestructionData_IsDestroyDuplicates()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsDestroyDuplicates();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData#getIsDestroyDuplicates <em>Is Destroy Duplicates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Destroy Duplicates</em>' attribute.
	 * @see #getIsDestroyDuplicates()
	 * @generated
	 */
	void setIsDestroyDuplicates(Object value);

	/**
	 * Returns the value of the '<em><b>Destroy At</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destroy At</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destroy At</em>' reference.
	 * @see #setDestroyAt(InputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndDestructionData_DestroyAt()
	 * @model ordered="false"
	 * @generated
	 */
	InputPin getDestroyAt();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData#getDestroyAt <em>Destroy At</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destroy At</em>' reference.
	 * @see #getDestroyAt()
	 * @generated
	 */
	void setDestroyAt(InputPin value);

} // LinkEndDestructionData
