/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getName <em>Name</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getNamespace <em>Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getNamedElement()
 * @model abstract="true"
 * @generated
 */
public interface NamedElement extends Element {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getNamedElement_Name()
	 * @model ordered="false"
	 * @generated
	 */
	Object getName();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(Object value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelexecution.fuml.Syntax.Classes.Kernel.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getNamedElement_Visibility()
	 * @model ordered="false"
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Name</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getNamedElement_QualifiedName()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getQualifiedName();

	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Namespace#getOwnedMember <em>Owned Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' reference.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getNamedElement_Namespace()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Namespace#getOwnedMember
	 * @model opposite="ownedMember" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Namespace getNamespace();

} // NamedElement
