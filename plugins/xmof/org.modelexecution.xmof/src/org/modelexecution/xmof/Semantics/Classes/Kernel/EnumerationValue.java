/**
 */
package org.modelexecution.xmof.Semantics.Classes.Kernel;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An enumeration value is a value whose (single) type is an enumeration. 
 *                 It's literal must be an owned literal of it's type.
 *  
 *  
 *               
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getLiteral <em>Literal</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage#getEnumerationValue()
 * @model
 * @generated
 */
public interface EnumerationValue extends Value {
	/**
	 * Returns the value of the '<em><b>Literal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The literal value of this enumeration value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Literal</em>' reference.
	 * @see #setLiteral(EEnumLiteral)
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage#getEnumerationValue_Literal()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EEnumLiteral getLiteral();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getLiteral <em>Literal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal</em>' reference.
	 * @see #getLiteral()
	 * @generated
	 */
	void setLiteral(EEnumLiteral value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EEnum)
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage#getEnumerationValue_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EEnum getType();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EEnum value);

} // EnumerationValue
