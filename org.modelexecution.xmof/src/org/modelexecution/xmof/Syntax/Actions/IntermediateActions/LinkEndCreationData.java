/**
 */
package org.modelexecution.xmof.Syntax.Actions.IntermediateActions;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link End Creation Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData#getIsReplaceAll <em>Is Replace All</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData#getInsertAt <em>Insert At</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndCreationData()
 * @model
 * @generated
 */
public interface LinkEndCreationData extends LinkEndData {
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
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndCreationData_IsReplaceAll()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsReplaceAll();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData#getIsReplaceAll <em>Is Replace All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Replace All</em>' attribute.
	 * @see #getIsReplaceAll()
	 * @generated
	 */
	void setIsReplaceAll(Object value);

	/**
	 * Returns the value of the '<em><b>Insert At</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insert At</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insert At</em>' reference.
	 * @see #setInsertAt(InputPin)
	 * @see org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage#getLinkEndCreationData_InsertAt()
	 * @model ordered="false"
	 * @generated
	 */
	InputPin getInsertAt();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData#getInsertAt <em>Insert At</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Insert At</em>' reference.
	 * @see #getInsertAt()
	 * @generated
	 */
	void setInsertAt(InputPin value);

} // LinkEndCreationData
