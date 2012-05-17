/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityThread extends ActivityDebugElement implements IThread {

	private final Activity activity;
	private final int executionId;

	private boolean isTerminated = false;

	public ActivityThread(IDebugTarget target, Activity activity,
			int executionId) {
		super(target);
		this.activity = activity;
		this.executionId = executionId;
		fireCreationEvent();
	}

	@Override
	public void notify(Event event) {
		if (isThreadActivityExitEvent(event)) {
			doTermination();
		}
	}

	private boolean isThreadActivityExitEvent(Event event) {
		if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExitEvent = (ActivityExitEvent) event;
			return activity.equals(activityExitEvent.getActivity())
					&& executionId == activityExitEvent
							.getActivityExecutionID();
		}
		return false;
	}

	private void doTermination() {
		isTerminated = true;
		fireTerminateEvent();
	}

	@Override
	public boolean canResume() {
		return !isTerminated;
	}

	@Override
	public boolean canSuspend() {
		return !isTerminated;
	}

	@Override
	public boolean isSuspended() {
		return !isTerminated;
	}

	@Override
	public void resume() throws DebugException {
		getDebugTarget().resume();
	}

	@Override
	public void suspend() throws DebugException {
		getDebugTarget().suspend();
	}

	@Override
	public boolean canStepInto() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepReturn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStepping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stepInto() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepOver() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stepReturn() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canTerminate() {
		return !isTerminated;
	}

	@Override
	public boolean isTerminated() {
		return isTerminated;
	}

	@Override
	public void terminate() throws DebugException {
		doTermination();
	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {
		// TODO Auto-generated method stub
		return new IStackFrame[] {};
	}

	@Override
	public boolean hasStackFrames() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPriority() throws DebugException {
		return 0;
	}

	@Override
	public IStackFrame getTopStackFrame() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() throws DebugException {
		return activity.name + " Thread";
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		// TODO Auto-generated method stub
		return new IBreakpoint[] {};
	}

}
