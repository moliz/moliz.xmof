/**
 */
package org.modelexecution.xmof.Semantics.Classes.Kernel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue#getEObject <em>EObject</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage#getObjectValue()
 * @model
 * @generated
 */
public interface ObjectValue extends Value {
	/**
	 * Returns the value of the '<em><b>EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EObject</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EObject</em>' reference.
	 * @see #setEObject(EObject)
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage#getObjectValue_EObject()
	 * @model required="true"
	 * @generated
	 */
	EObject getEObject();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue#getEObject <em>EObject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EObject</em>' reference.
	 * @see #getEObject()
	 * @generated
	 */
	void setEObject(EObject value);

} // ObjectValue
