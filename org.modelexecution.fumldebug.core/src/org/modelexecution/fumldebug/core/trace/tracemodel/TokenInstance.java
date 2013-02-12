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

import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Token Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance#getTraversedEdges <em>Traversed Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getTokenInstance()
 * @model abstract="true"
 * @generated
 */
public interface TokenInstance extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Traversed Edges</b></em>' attribute list.
	 * The list contents are of type {@link fUML.Syntax.Activities.IntermediateActivities.ActivityEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traversed Edges</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traversed Edges</em>' attribute list.
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#getTokenInstance_TraversedEdges()
	 * @model dataType="org.modelexecution.fumldebug.core.trace.tracemodel.ActivityEdge" transient="true" derived="true"
	 * @generated
	 */
	List<ActivityEdge> getTraversedEdges();

} // TokenInstance
