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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

public class XMOFThread extends XMOFDebugElement implements IThread {

	private List<XMOFStackFrame> stackFrames = new ArrayList<XMOFStackFrame>();

	private String name;

	public XMOFThread(XMOFDebugTarget target, String name) {
		super(target);
		this.name = name;
		fireCreationEvent();
	}

	@Override
	public boolean canResume() {
		return false;
	}

	@Override
	public boolean canSuspend() {
		return false;
	}

	@Override
	public boolean isSuspended() {
		return getDebugTarget().isSuspended();
	}

	@Override
	public void resume() throws DebugException {
	}

	@Override
	public void suspend() throws DebugException {
	}

	@Override
	public boolean canStepInto() {
		return false;
	}

	@Override
	public boolean canStepOver() {
		return false;
	}

	@Override
	public boolean canStepReturn() {
		return false;
	}

	@Override
	public boolean isStepping() {
		return false;
	}

	@Override
	public void stepInto() throws DebugException {
	}

	@Override
	public void stepOver() throws DebugException {
	}

	@Override
	public void stepReturn() throws DebugException {
	}

	@Override
	public boolean canTerminate() {
		return getDebugTarget().canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return getDebugTarget().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		doTermination();
	}

	private void doTermination() {
		getXMOFDebugTarget().removeThread(this);
		fireTerminateEvent();
	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {
		List<XMOFStackFrame> stackFramesInReverseOrder = new ArrayList<XMOFStackFrame>(
				stackFrames);
		Collections.reverse(stackFramesInReverseOrder);
		return stackFramesInReverseOrder.toArray(new IStackFrame[] {});
	}

	@Override
	public boolean hasStackFrames() throws DebugException {
		return getStackFrames().length > 0;
	}

	@Override
	public int getPriority() throws DebugException {
		return 0;
	}

	@Override
	public IStackFrame getTopStackFrame() throws DebugException {
		if (stackFrames.size() > 0)
			return stackFrames.get(0);
		return null;
	}

	@Override
	public String getName() throws DebugException {
		return name;
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		return new IBreakpoint[] {};
	}

	public void update() {
		Trace trace = getXMOFDebugTarget().getXMOFProcess().getExecutionTrace();
		ActivityNodeExecution enabledActivityNodeExecution = getEnabledActivityNodeExecution(trace);
		List<ActivityExecution> activityExecutionHierarchy = getActivityExecutionHierarchyForActivityNodeExecution(enabledActivityNodeExecution);

		Collection<XMOFActivityStackFrame> unchangedStackFrames = getUnchangedActivityExecutionStackFrames(activityExecutionHierarchy);
		List<XMOFStackFrame> stackFramesToBeRemoved = new ArrayList<XMOFStackFrame>(
				stackFrames);
		stackFramesToBeRemoved.removeAll(unchangedStackFrames);
		removeStackFrames(stackFramesToBeRemoved);

		// add new stack frames for activity executions
		int newExecutionStartIndex = unchangedStackFrames.size();
		if (newExecutionStartIndex < activityExecutionHierarchy.size()) {
			List<ActivityExecution> newActivityExecutions = activityExecutionHierarchy
					.subList(newExecutionStartIndex,
							activityExecutionHierarchy.size());
			addStackFrames(newActivityExecutions);
		}

		// add new stack frame for activity node execution
		addStackFrame(enabledActivityNodeExecution);

		fireContentChangeEvent();
	}

	private void removeStackFrames(List<XMOFStackFrame> stackFramesToBeRemoved) {
		for (XMOFStackFrame stackFrameToBeRemoved : stackFramesToBeRemoved) {
			stackFrames.remove(stackFrameToBeRemoved);
		}
	}

	private void addStackFrames(List<ActivityExecution> activityExecutions) {
		for (ActivityExecution activityExecution : activityExecutions)
			addStackFrame(activityExecution);
	}

	private void addStackFrame(ActivityExecution activityExecution) {
		stackFrames.add(createStackFrame(activityExecution));
	}

	private void addStackFrame(ActivityNodeExecution activityNodeExecution) {
		stackFrames.add(createStackFrame(activityNodeExecution));
	}

	private XMOFStackFrame createStackFrame(ActivityExecution activityExecution) {
		return new XMOFActivityStackFrame(this, activityExecution);
	}

	private XMOFStackFrame createStackFrame(
			ActivityNodeExecution activityNodeExecution) {
		return new XMOFActivityNodeStackFrame(this, activityNodeExecution);
	}

	private Collection<XMOFActivityStackFrame> getUnchangedActivityExecutionStackFrames(
			List<ActivityExecution> activityExecutionHierarchy) {
		Collection<XMOFActivityStackFrame> stackFramesToBeUpdated = new HashSet<XMOFActivityStackFrame>();

		int size = Math.min(stackFrames.size(),
				activityExecutionHierarchy.size());

		for (int i = 0; i < size; ++i) {
			ActivityExecution activityExecution = activityExecutionHierarchy
					.get(i);
			XMOFStackFrame stackFrame = stackFrames.get(i);
			if (stackFrame instanceof XMOFActivityStackFrame) {
				XMOFActivityStackFrame activityStackFrame = (XMOFActivityStackFrame) stackFrame;
				if (activityStackFrame.getActivityExecution() == activityExecution)
					stackFramesToBeUpdated.add(activityStackFrame);
				else
					break;
			} else
				break;

		}
		return stackFramesToBeUpdated;
	}

	private List<ActivityExecution> getActivityExecutionHierarchyForActivityNodeExecution(
			ActivityNodeExecution activityNodeExecution) {
		List<ActivityExecution> activityExecutionHierarchy = new ArrayList<ActivityExecution>();
		ActivityExecution currentActivityExecution = activityNodeExecution
				.getActivityExecution();
		activityExecutionHierarchy.add(currentActivityExecution);

		ActivityExecution callerActivityExecution = getCallerActivityExecution(currentActivityExecution);
		while (callerActivityExecution != null) {
			activityExecutionHierarchy.add(0, callerActivityExecution);
			callerActivityExecution = getCallerActivityExecution(callerActivityExecution);
		}

		return activityExecutionHierarchy;
	}

	private ActivityNodeExecution getEnabledActivityNodeExecution(Trace trace) {
		List<ActivityNodeExecution> enabledNodeExecutions = getEnabledActivityNodeExecutions(trace);
		if (enabledNodeExecutions.size() > 0)
			return enabledNodeExecutions.get(0);
		return null;
	}

	private List<ActivityNodeExecution> getEnabledActivityNodeExecutions(
			Trace trace) {
		List<ActivityNodeExecution> enabledNodeExecutions = new ArrayList<ActivityNodeExecution>();
		for (ActivityExecution activityExecution : trace
				.getActivityExecutions()) {
			for (ActivityNodeExecution nodeExecution : activityExecution
					.getNodeExecutions()) {
				if (!nodeExecution.isExecuted()
						&& !nodeExecution.isUnderExecution())
					enabledNodeExecutions.add(nodeExecution);
			}
		}
		return enabledNodeExecutions;
	}

	private ActivityExecution getCallerActivityExecution(
			ActivityExecution callee) {
		CallActionExecution caller = callee.getCaller();
		if (caller != null)
			return caller.getActivityExecution();
		return null;
	}
}
