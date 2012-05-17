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

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.Assert;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.debugger.process.ActivityProcess;

public class ActivityDebugTarget extends ActivityDebugElement implements
		IDebugTarget { // , IBreakpointManagerListener {

	private ILaunch launch;
	private ActivityProcess process;
	private ActivityThread rootThread;

	public ActivityDebugTarget(ILaunch launch, IProcess process) {
		super(null);
		Assert.isTrue(process instanceof ActivityProcess,
				"Process must be of type ActivityProcess");
		this.launch = launch;
		this.process = (ActivityProcess) process;
		this.process.addEventListener(this);
	}

	@Override
	public IDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public void notify(Event event) {
		if (isRootActivityEntryEvent(event)) {
			initializeThread(event);
		}
	}

	private boolean isRootActivityEntryEvent(Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			return hasRootExecutionId(activityEntryEvent);
		}
		return false;
	}

	private void initializeThread(Event event) {
		ActivityEvent activityEvent = (ActivityEvent) event;
		rootThread = new ActivityThread(this, activityEvent.getActivity(),
				activityEvent.getActivityExecutionID());
	}

	private boolean hasRootExecutionId(ActivityEvent event) {
		return process.getRootExecutionId() == event.getActivityExecutionID();
	}

	@Override
	public boolean canTerminate() {
		return process.canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return process.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		process.terminate();
	}

	@Override
	public boolean canResume() {
		return !isTerminated() && isSuspended();
	}

	@Override
	public boolean canSuspend() {
		return !isTerminated() && !isSuspended();
	}

	@Override
	public boolean isSuspended() {
		return !isTerminated() && isStarted();
	}

	public boolean isStarted() {
		return process.isStarted();
	}

	@Override
	public void resume() throws DebugException {
		process.resume();
	}

	@Override
	public void suspend() throws DebugException {
		process.suspend();
	}

	@Override
	public boolean canDisconnect() {
		return false;
	}

	@Override
	public void disconnect() throws DebugException {
		process = null;
	}

	@Override
	public boolean isDisconnected() {
		return process == null;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IProcess getProcess() {
		return process;
	}
	
	protected ActivityProcess getActivityProcess() {
		return process;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		return new IThread[] { rootThread };
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return false;
	}

	@Override
	public String getName() throws DebugException {
		return process.getName();
	}
}
