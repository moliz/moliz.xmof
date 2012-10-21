/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Redefinable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement#getIsLeaf <em>Is Leaf</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement#getRedefinedElement <em>Redefined Element</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement#getRedefinitionContext <em>Redefinition Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getRedefinableElement()
 * @model abstract="true"
 * @generated
 */
public interface RedefinableElement extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Is Leaf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Leaf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Leaf</em>' attribute.
	 * @see #setIsLeaf(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getRedefinableElement_IsLeaf()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsLeaf();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement#getIsLeaf <em>Is Leaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Leaf</em>' attribute.
	 * @see #getIsLeaf()
	 * @generated
	 */
	void setIsLeaf(Object value);

	/**
	 * Returns the value of the '<em><b>Redefined Element</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.RedefinableElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefined Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefined Element</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getRedefinableElement_RedefinedElement()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<RedefinableElement> getRedefinedElement();

	/**
	 * Returns the value of the '<em><b>Redefinition Context</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefinition Context</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefinition Context</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getRedefinableElement_RedefinitionContext()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Classifier> getRedefinitionContext();

} // RedefinableElement
