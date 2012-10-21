/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * (self.isAbstract and self.method->isEmpty()) xor (not self.isAbstract
 *                   and self.method->size() = 1)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsQuery <em>Is Query</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getLower <em>Lower</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getRedefinedOperation <em>Redefined Operation</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends BehavioralFeature {
	/**
	 * Returns the value of the '<em><b>Is Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Query</em>' attribute.
	 * @see #setIsQuery(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_IsQuery()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsQuery();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsQuery <em>Is Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Query</em>' attribute.
	 * @see #getIsQuery()
	 * @generated
	 */
	void setIsQuery(Object value);

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
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_IsOrdered()
	 * @model required="true" transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getIsOrdered();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsOrdered <em>Is Ordered</em>}' attribute.
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
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_IsUnique()
	 * @model required="true" transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getIsUnique();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getIsUnique <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unique</em>' attribute.
	 * @see #getIsUnique()
	 * @generated
	 */
	void setIsUnique(Object value);

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
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_Lower()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getLower();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(Object value);

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
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_Upper()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Object getUpper();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(Object value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Class#getOwnedOperation <em>Owned Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' container reference.
	 * @see #setClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_Class()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Class#getOwnedOperation
	 * @model opposite="ownedOperation" transient="false" ordered="false"
	 * @generated
	 */
	org.modelexecution.fuml.Syntax.Classes.Kernel.Class getClass_();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getClass_ <em>Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' container reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class value);

	/**
	 * Returns the value of the '<em><b>Redefined Operation</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefined Operation</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefined Operation</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_RedefinedOperation()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Operation> getRedefinedOperation();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Type)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getOperation_Type()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Operation#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

} // Operation
