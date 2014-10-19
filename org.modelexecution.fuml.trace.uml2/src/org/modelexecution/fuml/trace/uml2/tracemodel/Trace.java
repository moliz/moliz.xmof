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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.Trace#getValueInstances <em>Value Instances</em>}</li>
 *   <li>{@link org.modelexecution.fuml.trace.uml2.tracemodel.Trace#getInitialLocusValueInstances <em>Initial Locus Value Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Executions</em>' containment reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getTrace_ActivityExecutions()
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution#getTrace
	 * @model opposite="trace" containment="true"
	 * @generated
	 */
	EList<ActivityExecution> getActivityExecutions();

	/**
	 * Returns the value of the '<em><b>Value Instances</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Instances</em>' containment reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getTrace_ValueInstances()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueInstance> getValueInstances();

	/**
	 * Returns the value of the '<em><b>Initial Locus Value Instances</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Locus Value Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Locus Value Instances</em>' reference list.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage#getTrace_InitialLocusValueInstances()
	 * @model
	 * @generated
	 */
	EList<ValueInstance> getInitialLocusValueInstances();

} // Trace
