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

import org.eclipse.uml2.uml.ActivityEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Token Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance#getTraversedEdges <em>Traversed Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getTokenInstance()
 * @model abstract="true"
 * @generated
 */
public interface TokenInstance extends EObject {
	/**
	 * Returns the value of the '<em><b>Traversed Edges</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.ActivityEdge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traversed Edges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traversed Edges</em>' reference list.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage#getTokenInstance_TraversedEdges()
	 * @model
	 * @generated
	 */
	EList<ActivityEdge> getTraversedEdges();

} // TokenInstance
