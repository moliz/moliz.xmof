/**
 */
package org.modelexecution.fuml.Semantics.Loci.LociL1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.Semantics.Classes.Kernel.ExtensionalValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locus</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Semantics.Loci.LociL1.Locus#getExtensionalValues <em>Extensional Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Semantics.Loci.LociL1.LociL1Package#getLocus()
 * @model
 * @generated
 */
public interface Locus extends EObject {
	/**
	 * Returns the value of the '<em><b>Extensional Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Semantics.Classes.Kernel.ExtensionalValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensional Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensional Values</em>' containment reference list.
	 * @see org.modelexecution.fuml.Semantics.Loci.LociL1.LociL1Package#getLocus_ExtensionalValues()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ExtensionalValue> getExtensionalValues();

} // Locus
