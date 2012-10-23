/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EParameter;

import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.isReentrant
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification <em>Specification</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getIsReentrant <em>Is Reentrant</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavior()
 * @model abstract="true"
 * @generated
 */
public interface Behavior extends BehavioredEClass {
	/**
	 * Returns the value of the '<em><b>Specification</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specification</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specification</em>' reference.
	 * @see #setSpecification(BehavioredEOperation)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavior_Specification()
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation#getMethod
	 * @model opposite="method" ordered="false"
	 * @generated
	 */
	BehavioredEOperation getSpecification();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification <em>Specification</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specification</em>' reference.
	 * @see #getSpecification()
	 * @generated
	 */
	void setSpecification(BehavioredEOperation value);

	/**
	 * Returns the value of the '<em><b>Is Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Reentrant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Reentrant</em>' attribute.
	 * @see #setIsReentrant(Object)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavior_IsReentrant()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsReentrant();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getIsReentrant <em>Is Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Reentrant</em>' attribute.
	 * @see #getIsReentrant()
	 * @generated
	 */
	void setIsReentrant(Object value);

	/**
	 * Returns the value of the '<em><b>Owned Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parameter</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavior_OwnedParameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<EParameter> getOwnedParameter();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' reference.
	 * @see #setContext(BehavioredClassifier)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavior_Context()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	BehavioredClassifier getContext();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(BehavioredClassifier value);

} // Behavior
