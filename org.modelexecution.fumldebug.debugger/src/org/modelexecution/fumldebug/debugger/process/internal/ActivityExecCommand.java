/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.process.internal;

import org.modelexecution.fumldebug.core.ExecutionContext;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ActivityExecCommand {

	public enum Kind {
		START, NEXT_STEP, RESUME;
	}

	private int executionID = -1;
	private Kind kind;
	private ActivityNode activityNode = null;
	private Activity activity = null;

	public ActivityExecCommand(int executionID, Kind kind) {
		super();
		this.executionID = executionID;
		this.kind = kind;
	}

	public ActivityExecCommand(Activity activity, Kind kind) {
		super();
		this.kind = kind;
		this.activity = activity;
	}

	public ActivityExecCommand(int executionID, ActivityNode activityNode,
			Kind kind) {
		super();
		this.executionID = executionID;
		this.kind = kind;
		this.activityNode = activityNode;
	}

	public int getExecutionID() {
		return executionID;
	}

	public Kind getKind() {
		return kind;
	}

	public void execute(ExecutionContext context) {
		switch (kind) {
		case START:
			start(context);
			break;
		case NEXT_STEP:
			callNextStep(context);
			break;
		case RESUME:
			callResume(context);
			break;
		}
	}

	private void start(ExecutionContext context) {
		context.debug(activity, null, new ParameterValueList());
	}

	private void callNextStep(ExecutionContext context) {
		if (activityNode == null) {
			context.nextStep(executionID);
		} else {
			context.nextStep(executionID, activityNode);
		}
	}

	private void callResume(ExecutionContext context) {
		context.resume(executionID);
	}

}
