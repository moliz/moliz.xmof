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
 * A representation of the model object '<em><b>Structured Activity Node Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.StructuredActivityNodeExecution#getNestedNodeExecutions <em>Nested Node Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getStructuredActivityNodeExecution()
 * @model
 * @generated
 */
public interface StructuredActivityNodeExecution extends ActionExecution {
	/**
	 * Returns the value of the '<em><b>Nested Node Executions</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Node Executions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested Node Executions</em>' reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getStructuredActivityNodeExecution_NestedNodeExecutions()
	 * @model
	 * @generated
	 */
	EList<ActivityNodeExecution> getNestedNodeExecutions();

} // StructuredActivityNodeExecution
