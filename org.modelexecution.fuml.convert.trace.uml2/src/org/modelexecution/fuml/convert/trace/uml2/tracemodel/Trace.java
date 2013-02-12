/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Executions</em>' containment reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getTrace_ActivityExecutions()
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution#getTrace
	 * @model opposite="trace" containment="true"
	 * @generated
	 */
	EList<ActivityExecution> getActivityExecutions();

} // Trace
