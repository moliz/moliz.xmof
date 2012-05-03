/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger;

import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.debugger.launch.internal.InternalActivityProcess;

public class ActivityProcess extends RuntimeProcess implements IProcess,
		ExecutionEventListener {

	private InternalActivityProcess activityProcess;

	public ActivityProcess(ILaunch launch, Process process, String name,
			@SuppressWarnings("rawtypes") Map attributes) {
		super(launch, process, name, attributes);
		Assert.isTrue(process instanceof InternalActivityProcess,
				"Process must be of type InternalActivityProcess.");
		this.activityProcess = (InternalActivityProcess) process;
		this.activityProcess.addExecutionEventListener(this);
	}

	public String getName() {
		return activityProcess.getActivityName() + " ("
				+ activityProcess.getRootExecutionID() + ")";
	}

	public int getRootExecutionId() {
		return activityProcess.getRootExecutionID();
	}

	@Override
	public synchronized boolean canTerminate() {
		return true; // activityProcess != null &&
						// activityProcess.canTerminate();
	}

	@Override
	public synchronized boolean isTerminated() {
		return activityProcess == null || activityProcess.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		activityProcess.destroy();
		super.terminate();
	}

	public boolean isSuspended() {
		// TODO check if last was stepevent
		return false;
	}

	@Override
	public void notify(Event event) {
		System.out.println(event);
	}

}
