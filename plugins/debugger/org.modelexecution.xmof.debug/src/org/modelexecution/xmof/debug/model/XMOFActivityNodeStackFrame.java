/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class XMOFActivityNodeStackFrame extends XMOFStackFrame implements
		IStackFrame {

	private ActivityNodeExecution activityNodeExecution;

	public XMOFActivityNodeStackFrame(XMOFThread thread,
			ActivityNodeExecution activityNodeExecution) {
		super(thread);
		this.activityNodeExecution = activityNodeExecution;
	}

	public ActivityNodeExecution getActivityNodeExecution() {
		return activityNodeExecution;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		return new IVariable[] {};
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return false;
	}

	@Override
	public String getName() throws DebugException {
		ActivityNode node = activityNodeExecution.getNode();
		String nodeType = node.getClass().getName();
		String nodeName = node.name;
		return nodeType + " " + nodeName;
	}

}
