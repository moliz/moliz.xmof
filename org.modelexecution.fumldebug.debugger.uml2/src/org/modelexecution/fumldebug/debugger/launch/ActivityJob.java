/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.launch;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityJob extends Job implements ExecutionEventListener {

	private static final String NAME_PREFIX = "Running activity ";

	private Activity acticity;
	private ExecutionContext executionContext;

	private boolean running = false;
	private int executionID = -1;

	public ActivityJob(Activity activity, ExecutionContext executionContext) {
		super(NAME_PREFIX + activity.name);
		this.acticity = activity;
		this.executionContext = executionContext;
		initialize();
	}

	private void initialize() {
		running = false;
		executionContext.getExecutionEventProvider().addEventListener(this);
	}

	public int getExecutionID() {
		return executionID;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		running = true;
		executionContext.debug(acticity, null, new ParameterValueList());
		while (running) {
			// session is running
		}
		return createOKStatus();
	}

	private Status createOKStatus() {
		return new Status(IStatus.OK, FUMLDebuggerPlugin.ID,
				"Finished executing activity " + acticity.name);
	}

	@Override
	public void notify(Event event) {
		if (isFirstActivityEntryEvent(event)) {
			saveExecutionID(event);
		} else if (isLastActivityExitEvent(event)) {
			running = false;
		}
	}

	private void saveExecutionID(Event event) {
		executionID = event.getActivityExecutionID();
	}

	private boolean isFirstActivityEntryEvent(Event event) {
		return event instanceof ActivityEntryEvent && executionID == -1;
	}

	private boolean isLastActivityExitEvent(Event event) {
		if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExitEvent = (ActivityExitEvent) event;
			if (executionID == activityExitEvent.getActivityExecutionID()) {
				return true;
			}
		}
		return false;
	}

}
