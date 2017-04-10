/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.Communications;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Trigger#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage#getTrigger()
 * @model
 * @generated
 */
public interface Trigger extends EObject, ENamedElement {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The event that causes the trigger.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event</em>' reference.
	 * @see #setEvent(Event)
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.CommunicationsPackage#getTrigger_Event()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Trigger#getEvent <em>Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

} // Trigger
