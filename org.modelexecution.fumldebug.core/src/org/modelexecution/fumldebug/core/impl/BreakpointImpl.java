/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.impl;

import org.modelexecution.fumldebug.core.Breakpoint;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Tanja Mayerhofer
 *
 */
public class BreakpointImpl implements Breakpoint {

	private ActivityNode node;
	
	public BreakpointImpl(ActivityNode node) {
		this.node = node;
	}
	
	public ActivityNode getActivityNode(){
		return node;
	}

}
