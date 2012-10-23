/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.Communications;

import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reception</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.method->isEmpty()
 * not self.isAbstract
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Reception#getSignal <em>Signal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage#getReception()
 * @model
 * @generated
 */
public interface Reception extends BehavioredEOperation {
	/**
	 * Returns the value of the '<em><b>Signal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal</em>' reference.
	 * @see #setSignal(Signal)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage#getReception_Signal()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Signal getSignal();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Reception#getSignal <em>Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal</em>' reference.
	 * @see #getSignal()
	 * @generated
	 */
	void setSignal(Signal value);

} // Reception
