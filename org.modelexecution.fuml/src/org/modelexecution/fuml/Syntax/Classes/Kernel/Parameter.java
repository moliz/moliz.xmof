/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends TypedElement, MultiplicityElement {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelexecution.fuml.Syntax.Classes.Kernel.ParameterDirectionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.ParameterDirectionKind
	 * @see #setDirection(ParameterDirectionKind)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getParameter_Direction()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ParameterDirectionKind getDirection();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.ParameterDirectionKind
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(ParameterDirectionKind value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getParameter_Operation()
	 * @model ordered="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

} // Parameter
