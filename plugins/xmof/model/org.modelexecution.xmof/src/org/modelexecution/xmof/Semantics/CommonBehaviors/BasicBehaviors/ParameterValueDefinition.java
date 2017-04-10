/**
 */
package org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Value Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueDefinition#getParameterValues <em>Parameter Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getParameterValueDefinition()
 * @model
 * @generated
 */
public interface ParameterValueDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Parameter Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Values</em>' containment reference list.
	 * @see org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage#getParameterValueDefinition_ParameterValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterValue> getParameterValues();

} // ParameterValueDefinition
