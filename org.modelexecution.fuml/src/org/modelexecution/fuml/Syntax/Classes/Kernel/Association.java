/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.memberEnd->symmetricDifference(self.ownedEnd)->isEmpty()
 * not self.isDerived
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getEndType <em>End Type</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getMemberEnd <em>Member End</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getNavigableOwnedEnd <em>Navigable Owned End</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getOwnedEnd <em>Owned End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends Classifier {
	/**
	 * Returns the value of the '<em><b>Is Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Derived</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Derived</em>' attribute.
	 * @see #setIsDerived(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation_IsDerived()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsDerived();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Association#getIsDerived <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Derived</em>' attribute.
	 * @see #getIsDerived()
	 * @generated
	 */
	void setIsDerived(Object value);

	/**
	 * Returns the value of the '<em><b>End Type</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Type</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation_EndType()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Type> getEndType();

	/**
	 * Returns the value of the '<em><b>Member End</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property#getAssociation <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member End</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member End</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation_MemberEnd()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Property#getAssociation
	 * @model opposite="association" lower="2"
	 * @generated
	 */
	EList<Property> getMemberEnd();

	/**
	 * Returns the value of the '<em><b>Navigable Owned End</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigable Owned End</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigable Owned End</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation_NavigableOwnedEnd()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Property> getNavigableOwnedEnd();

	/**
	 * Returns the value of the '<em><b>Owned End</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property#getOwningAssociation <em>Owning Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned End</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned End</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getAssociation_OwnedEnd()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Property#getOwningAssociation
	 * @model opposite="owningAssociation" containment="true"
	 * @generated
	 */
	EList<Property> getOwnedEnd();

} // Association
