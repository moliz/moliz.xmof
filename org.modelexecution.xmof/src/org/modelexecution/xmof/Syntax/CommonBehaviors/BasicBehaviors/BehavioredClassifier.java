/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behaviored Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.ownedBehavior->forAll(b:Behavior|b = self.classifierBehavior xor
 *                   self.allFeatures()->includes(b.specification))
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier#getOwnedBehavior <em>Owned Behavior</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier#getClassifierBehavior <em>Classifier Behavior</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavioredClassifier()
 * @model abstract="true"
 * @generated
 */
public interface BehavioredClassifier extends EObject, EClassifier {
	/**
	 * Returns the value of the '<em><b>Owned Behavior</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Behavior</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Behavior</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavioredClassifier_OwnedBehavior()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Behavior> getOwnedBehavior();

	/**
	 * Returns the value of the '<em><b>Classifier Behavior</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier Behavior</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classifier Behavior</em>' reference.
	 * @see #setClassifierBehavior(Behavior)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getBehavioredClassifier_ClassifierBehavior()
	 * @model ordered="false"
	 * @generated
	 */
	Behavior getClassifierBehavior();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier#getClassifierBehavior <em>Classifier Behavior</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier Behavior</em>' reference.
	 * @see #getClassifierBehavior()
	 * @generated
	 */
	void setClassifierBehavior(Behavior value);

} // BehavioredClassifier
