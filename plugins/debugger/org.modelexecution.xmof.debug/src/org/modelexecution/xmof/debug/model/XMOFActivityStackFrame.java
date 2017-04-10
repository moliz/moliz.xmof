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
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.xmof.debug.model.variables.ObjectValue;
import org.modelexecution.xmof.debug.model.variables.ObjectVariable;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Class_;

public class XMOFActivityStackFrame extends XMOFStackFrame implements
		IStackFrame {

	private ActivityExecution activityExecution;

	public XMOFActivityStackFrame(XMOFThread thread,
			ActivityExecution activityExecution) {
		super(thread);
		this.activityExecution = activityExecution;
	}

	public ActivityExecution getActivityExecution() {
		return activityExecution;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		Object_ contextObject = getActivityContextObject();
		if (contextObject != null) {
			ObjectVariable contextObjectVariable = createContextObjectVariable(contextObject);
			IVariable[] variables = new IVariable[] { contextObjectVariable };
			return variables;
		}
		return new IVariable[] {};
	}

	private ObjectVariable createContextObjectVariable(Object_ contextObject) {
		ObjectValue contextObjectValue = new ObjectValue(getXMOFDebugTarget(),
				contextObject);
		ObjectVariable contextObjectVariable = new ObjectVariable(
				getXMOFDebugTarget(), "context", contextObjectValue);
		return contextObjectVariable;
	}

	private Object_ getActivityContextObject() {
		ValueSnapshot context = activityExecution.getContextValueSnapshot();
		if (context != null) {
			Value contextRuntimeValue = context.getRuntimeValue();
			if (contextRuntimeValue instanceof Object_)
				return (Object_) contextRuntimeValue;
		}
		return null;
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return getActivityContextObject() != null;
	}

	@Override
	public String getName() throws DebugException {
		Activity activity = getActivity();
		String name = "";
		Class_ activityOwner = getActivityOwner();
		if (activityOwner != null) {
			name += activityOwner.name + " - ";
		}
		name += activity.name;
		return name;
	}

	private Class_ getActivityOwner() {
		Activity activity = getActivity();
		if (activity.owner != null) {
			if (activity.owner instanceof Class_)
				return (Class_) activity.owner;
		}
		return null;
	}

	private Activity getActivity() {
		return activityExecution.getActivity();
	}

}
