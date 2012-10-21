/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavioral Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.concurrency = CallConcurrencyKind::sequential
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getMethod <em>Method</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getConcurrency <em>Concurrency</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getBehavioralFeature()
 * @model abstract="true"
 * @generated
 */
public interface BehavioralFeature extends Feature {
	/**
	 * Returns the value of the '<em><b>Owned Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parameter</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getBehavioralFeature_OwnedParameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getOwnedParameter();

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getBehavioralFeature_IsAbstract()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsAbstract();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #getIsAbstract()
	 * @generated
	 */
	void setIsAbstract(Object value);

	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getBehavioralFeature_Method()
	 * @see org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification
	 * @model opposite="specification" ordered="false"
	 * @generated
	 */
	EList<Behavior> getMethod();

	/**
	 * Returns the value of the '<em><b>Concurrency</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concurrency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrency</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind
	 * @see #setConcurrency(CallConcurrencyKind)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getBehavioralFeature_Concurrency()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	CallConcurrencyKind getConcurrency();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.BehavioralFeature#getConcurrency <em>Concurrency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrency</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind
	 * @see #getConcurrency()
	 * @generated
	 */
	void setConcurrency(CallConcurrencyKind value);

} // BehavioralFeature
