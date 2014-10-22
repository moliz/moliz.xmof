/**
 */
package org.modelexecution.fuml.trace.uml2.statesmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.statesmodel.State#getSnapshots <em>Snapshots</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.statesmodel.StatesmodelPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
	/**
	 * Returns the value of the '<em><b>Snapshots</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snapshots</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snapshots</em>' reference list.
	 * @see org.modelexecution.fuml.trace.uml2.statesmodel.StatesmodelPackage#getState_Snapshots()
	 * @model
	 * @generated
	 */
	EList<ValueSnapshot> getSnapshots();

} // State
