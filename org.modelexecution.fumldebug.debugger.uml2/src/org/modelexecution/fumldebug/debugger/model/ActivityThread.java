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

import java.util.List;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;

import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ActivityThread extends ActivityDebugElement implements IThread {

	private final Activity activity;
	private final int rootExecutionId;
	private int currentExecutionId;
	private List<ActivityNode> newEnabledNodes;

	private boolean isTerminated = false;
	private boolean isStepping = false;

	public ActivityThread(ActivityDebugTarget target, Activity activity,
			int executionId) {
		super(target);
		this.activity = activity;
		this.rootExecutionId = executionId;
		this.currentExecutionId = executionId;
		getActivityProcess().addEventListener(this);
		fireCreationEvent();
		processMissedEvents();
	}

	@Override
	public void notify(Event event) {
		if (isRootActivityExitEvent(event)) {
			doTermination();
		} else if (isChildStepEvent(event)) {
			StepEvent stepEvent = (StepEvent) event;
			setSteppingStopped();
			updateEnabledNodes(stepEvent);
			currentExecutionId = stepEvent.getActivityExecutionID();
		}
	}

	private boolean isRootActivityExitEvent(Event event) {
		if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExitEvent = (ActivityExitEvent) event;
			return concernsCurrentActivity(activityExitEvent)
					&& hasRootExecutionId(activityExitEvent);
		}
		return false;
	}

	private boolean concernsCurrentActivity(ActivityEvent activityEvent) {
		return activity.equals(activityEvent.getActivity());
	}

	private boolean hasRootExecutionId(TraceEvent traceEvent) {
		return rootExecutionId == traceEvent.getActivityExecutionID();
	}

	private void doTermination() {
		isTerminated = true;
		getActivityProcess().removeEventListener(this);
		fireTerminateEvent();
		notifyDebugTargetChange();
	}

	private void notifyDebugTargetChange() {
		getActivityDebugTarget().fireChangeEvent(DebugEvent.UNSPECIFIED);
	}

	private boolean isChildStepEvent(Event event) {
		if (event instanceof StepEvent) {
			StepEvent stepEvent = (StepEvent) event;
			return hasCurrentExecutionId(stepEvent)
					|| isChildEventOfCurrentActivity(stepEvent);
		}
		return false;
	}

	private boolean hasCurrentExecutionId(StepEvent event) {
		return rootExecutionId == event.getActivityExecutionID();
	}

	private boolean isChildEventOfCurrentActivity(StepEvent activityNodeEvent) {
		TraceEvent currentEvent = activityNodeEvent;
		Event parentEvent = null;
		while ((parentEvent = currentEvent.getParent()) != null) {
			if (parentEvent instanceof TraceEvent) {
				if (parentEvent instanceof ActivityEntryEvent) {
					ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) parentEvent;
					if (concernsCurrentActivity(activityEntryEvent)) {
						return true;
					}
				}
				currentEvent = (TraceEvent) parentEvent;
			} else {
				return false;
			}
		}
		return false;
	}

	private void updateEnabledNodes(StepEvent event) {
		newEnabledNodes = event.getNewEnabledNodes();
	}

	private boolean haveEnabledNode() {
		return newEnabledNodes != null && newEnabledNodes.size() > 0;
	}

	private ActivityNode getFirstEnabledNode() {
		return haveEnabledNode() ? newEnabledNodes.get(0) : null;
	}

	private boolean isCallAction(ActivityNode activityNode) {
		if (activityNode == null)
			return false;
		return activityNode instanceof CallAction;
	}

	@Override
	public boolean canResume() {
		return !isTerminated;
	}

	@Override
	public boolean canSuspend() {
		return false;
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
		return !isTerminated() && isCallAction(getFirstEnabledNode());
	}

	@Override
	public boolean canStepOver() {
		return !isTerminated() && haveEnabledNode();
	}

	@Override
	public boolean canStepReturn() {
		return !isTerminated() && haveEnabledNode();
	}

	@Override
	public boolean isStepping() {
		return isStepping;
	}

	private void setSteppingStarted() {
		isStepping = true;
	}

	private void setSteppingStopped() {
		isStepping = false;
	}

	@Override
	public void stepInto() throws DebugException {
		setSteppingStarted();
		getActivityProcess()
				.stepInto(currentExecutionId, getFirstEnabledNode());
	}

	@Override
	public void stepOver() throws DebugException {
		setSteppingStarted();
		getActivityProcess()
				.stepOver(currentExecutionId, getFirstEnabledNode());
	}

	@Override
	public void stepReturn() throws DebugException {
		setSteppingStarted();
		getActivityProcess().stepReturn(rootExecutionId, getFirstEnabledNode());
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
		return activity.name + " Thread (" + currentExecutionId + ")";
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		// TODO Auto-generated method stub
		return new IBreakpoint[] {};
	}

}
