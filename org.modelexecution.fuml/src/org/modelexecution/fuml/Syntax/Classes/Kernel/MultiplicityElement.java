/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.upperValue->notEmpty() and
 *                   self.upperValue->asSequence()->first().oclIsKindOf(LiteralUnlimitedNatural)
 *                   and
 *  self.lowerValue->notEmpty() and
 *                   self.lowerValue->asSequence()->first().oclIsKindOf(LiteralInteger)
 *                 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getLower <em>Lower</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getLowerValue <em>Lower Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement()
 * @model
 * @generated
 */
public interface MultiplicityElement extends Element {
	/**
	 * Returns the value of the '<em><b>Is Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Ordered</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Ordered</em>' attribute.
	 * @see #setIsOrdered(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_IsOrdered()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsOrdered();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getIsOrdered <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Ordered</em>' attribute.
	 * @see #getIsOrdered()
	 * @generated
	 */
	void setIsOrdered(Object value);

	/**
	 * Returns the value of the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Unique</em>' attribute.
	 * @see #setIsUnique(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_IsUnique()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsUnique();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getIsUnique <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unique</em>' attribute.
	 * @see #getIsUnique()
	 * @generated
	 */
	void setIsUnique(Object value);

	/**
	 * Returns the value of the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper</em>' attribute.
	 * @see #setUpper(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_Upper()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getUpper();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(Object value);

	/**
	 * Returns the value of the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower</em>' attribute.
	 * @see #setLower(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_Lower()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getLower();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(Object value);

	/**
	 * Returns the value of the '<em><b>Upper Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Value</em>' containment reference.
	 * @see #setUpperValue(ValueSpecification)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_UpperValue()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ValueSpecification getUpperValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getUpperValue <em>Upper Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Value</em>' containment reference.
	 * @see #getUpperValue()
	 * @generated
	 */
	void setUpperValue(ValueSpecification value);

	/**
	 * Returns the value of the '<em><b>Lower Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Value</em>' containment reference.
	 * @see #setLowerValue(ValueSpecification)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getMultiplicityElement_LowerValue()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	ValueSpecification getLowerValue();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement#getLowerValue <em>Lower Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Value</em>' containment reference.
	 * @see #getLowerValue()
	 * @generated
	 */
	void setLowerValue(ValueSpecification value);

} // MultiplicityElement
