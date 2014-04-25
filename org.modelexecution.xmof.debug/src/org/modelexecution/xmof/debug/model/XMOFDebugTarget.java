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
import java.util.List;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.Assert;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStep;
import org.eclipse.debug.core.model.IThread;
import org.modelexecution.xmof.debug.XMOFDebugPlugin;
import org.modelexecution.xmof.debug.internal.launch.XMOFLaunchConfigurationUtil;
import org.modelexecution.xmof.debug.process.XMOFProcess;

public class XMOFDebugTarget extends XMOFDebugElement implements IDebugTarget,
		IStep, IDebugEventSetListener {

	private ILaunch launch;
	private XMOFProcess process;
	private List<XMOFThread> threads = new ArrayList<XMOFThread>();

	public XMOFDebugTarget(ILaunch launch, IProcess process) {
		super(null);
		Assert.isTrue(process instanceof XMOFProcess,
				"Process must be of type XMOFProcess");
		this.launch = launch;
		this.process = (XMOFProcess) process;
		initializeThread();
		registerDebugEventListener();
		startProcess();
	}

	private void initializeThread() {
		addThread(XMOFLaunchConfigurationUtil
				.getConfigurationMetamodelPath(launch.getLaunchConfiguration()));
	}

	private void registerDebugEventListener() {
		DebugPlugin manager = DebugPlugin.getDefault();
		if (manager != null) {
			manager.addDebugEventListener(this);
		}
	}

	private void startProcess() {
		process.runProcess();
	}

	@Override
	public IDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	private void addThread(String threadName) {
		threads.add(createNewThread(threadName));
		fireContentChangeEvent();
	}

	private XMOFThread createNewThread(String threadName) {
		return new XMOFThread(this, threadName);
	}

	private void doTermination() {
		try {
			terminate();
		} catch (DebugException e) {
			XMOFDebugPlugin.log(e);
		}
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
		terminateThreads();
		fireTerminateEvent();
	}

	private void terminateThreads() throws DebugException {
		for (XMOFThread thread : new ArrayList<XMOFThread>(threads)) {
			thread.terminate();
			removeThread(thread);
		}
	}

	protected void removeThread(XMOFThread thread) {
		threads.remove(thread);
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
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return false;
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
	}

	@Override
	public IProcess getProcess() {
		return process;
	}

	@Override
	protected XMOFProcess getXMOFProcess() {
		return process;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		return threads.toArray(new IThread[threads.size()]);
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return !threads.isEmpty();
	}

	@Override
	public boolean canStepInto() {
		return getThreadThatCanStepInto() != null;
	}

	private XMOFThread getThreadThatCanStepInto() {
		for (XMOFThread thread : threads) {
			if (thread.canStepInto())
				return thread;
		}
		return null;
	}

	@Override
	public boolean canStepOver() {
		return getThreadThatCanStepOver() != null;
	}

	private XMOFThread getThreadThatCanStepOver() {
		for (XMOFThread thread : threads) {
			if (thread.canStepOver())
				return thread;
		}
		return null;
	}

	@Override
	public boolean canStepReturn() {
		return getThreadThatCanStepReturn() != null;
	}

	private XMOFThread getThreadThatCanStepReturn() {
		for (XMOFThread thread : threads) {
			if (thread.canStepReturn())
				return thread;
		}
		return null;
	}

	@Override
	public boolean isStepping() {
		return false;
	}

	@Override
	public void stepInto() throws DebugException {
		getThreadThatCanStepInto().stepInto();
	}

	@Override
	public void stepOver() throws DebugException {
		getThreadThatCanStepOver().stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {
		getThreadThatCanStepReturn().stepReturn();
	}

	@Override
	public String getName() throws DebugException {
		return XMOFLaunchConfigurationUtil.getModelFilePath(launch
				.getLaunchConfiguration());
		// return launch.getLaunchConfiguration().getName();
	}

	@Override
	public void handleDebugEvents(DebugEvent[] events) {
		for (int i = 0; i < events.length; ++i) {
			DebugEvent event = events[i];
			if (isRelevantDebugEvent(event)) {
				handleDebugEvent(event);
			}
		}
	}

	public boolean isRelevantDebugEvent(DebugEvent event) {
		if (event.getSource() == process)
			return true;
		return false;
	}

	private void handleDebugEvent(DebugEvent event) {
		switch (event.getKind()) {
		case DebugEvent.SUSPEND:
			updateThreads();
			updateResultVisualization();
			break;
		case DebugEvent.TERMINATE:
			updateResultVisualization();
			doTermination();
			break;
		}
	}

	private void updateThreads() {
		for (XMOFThread thread : threads) {
			thread.update();
		}
	}

	private void updateResultVisualization() {
		process.showExecutionResult();
	}
}
