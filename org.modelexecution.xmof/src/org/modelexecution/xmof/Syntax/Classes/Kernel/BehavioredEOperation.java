/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behaviored EOperation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getBehavioredEOperation()
 * @model
 * @generated
 */
public interface BehavioredEOperation extends EObject, EOperation {
	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' reference list.
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getBehavioredEOperation_Method()
	 * @see org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior#getSpecification
	 * @model opposite="specification"
	 * @generated
	 */
	EList<Behavior> getMethod();

} // BehavioredEOperation
