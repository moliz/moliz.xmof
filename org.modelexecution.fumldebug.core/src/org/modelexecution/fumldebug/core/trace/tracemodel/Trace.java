/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.Trace#getActivityExecutions <em>Activity Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Activity Executions</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Executions</em>' containment reference list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getTrace_ActivityExecutions()
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution#getTrace
	 * @model opposite="trace" containment="true"
	 * @generated
	 */
	List<ActivityExecution> getActivityExecutions();
	
	/**
	 * Returns the {@link ActivityExecution} with the given activityExecutionID
	 * @param activityExecutionID
	 * @return
	 */
	ActivityExecution getActivityExecutionByID(int activityExecutionID);

	/**
	 * Adds an {@link ActivityExecution} to the {@link Trace}
	 * @param activity
	 * @param activityExecutionID
	 * @return
	 */
	ActivityExecution addActivityExecution(Activity activity, int activityExecutionID);
	
	ActivityNodeExecution getLastActivityNodeExecution();
		
} // Trace
