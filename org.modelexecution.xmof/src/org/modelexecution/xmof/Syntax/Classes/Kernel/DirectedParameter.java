/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EParameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Directed Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getDirectedParameter()
 * @model
 * @generated
 */
public interface DirectedParameter extends EObject, EParameter {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind
	 * @see #setDirection(ParameterDirectionKind)
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getDirectedParameter_Direction()
	 * @model required="true"
	 * @generated
	 */
	ParameterDirectionKind getDirection();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(ParameterDirectionKind value);

} // DirectedParameter
