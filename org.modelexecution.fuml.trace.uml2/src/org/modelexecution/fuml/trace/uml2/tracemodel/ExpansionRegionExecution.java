/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expansion Region Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionRegionExecution#getExpansionInputs <em>Expansion Inputs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getExpansionRegionExecution()
 * @model
 * @generated
 */
public interface ExpansionRegionExecution extends StructuredActivityNodeExecution {
	/**
	 * Returns the value of the '<em><b>Expansion Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expansion Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expansion Inputs</em>' containment reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getExpansionRegionExecution_ExpansionInputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExpansionInput> getExpansionInputs();

} // ExpansionRegionExecution
