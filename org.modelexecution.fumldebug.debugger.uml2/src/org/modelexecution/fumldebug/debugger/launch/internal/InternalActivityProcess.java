/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.launch.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.debugger.launch.internal.ActivityExecCommand.Kind;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class InternalActivityProcess extends Process implements
		ExecutionEventListener {

	public enum Mode {
		DEBUG, RUN;
	}

	private static final int EXIT_CODE = 0;

	private Activity activity;

	private ExecutionContext executionContext = ExecutionContext.getInstance();
	private Mode mode = Mode.RUN;

	private ActivityThread activityThread;
	private int rootExecutionId = -1;

	private InputStream inputStream;
	private InputStream errorInputStream;
	private OutputStream outputStream = null;

	public InternalActivityProcess(Activity activity, Mode mode) {
		this.activity = activity;
		this.mode = mode;
		initializeThread();
	}

	private void initializeThread() {
		activityThread = new ActivityThread(activity, executionContext);
	}

	private boolean isRunning() {
		return activityThread.isAlive();
	}

	public void run() {
		executionContext.getExecutionEventProvider().addEventListener(this);
		activityThread.start();
	}

	@Override
	public void notify(Event event) {
		System.out.println(event);
		if (isFirstActivityEntryEvent(event)) {
			saveExecutionID(event);
		} else if (isLastActivityExitEvent(event)) {
			terminate();
		}
		if (inRunMode() && isStepEvent(event)) {
			resume(event.getActivityExecutionID());
		}
	}

	private void saveExecutionID(Event event) {
		rootExecutionId = event.getActivityExecutionID();
	}

	private boolean isFirstActivityEntryEvent(Event event) {
		return event instanceof ActivityEntryEvent && rootExecutionId == -1;
	}

	private boolean isLastActivityExitEvent(Event event) {
		if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExitEvent = (ActivityExitEvent) event;
			if (rootExecutionId == activityExitEvent.getActivityExecutionID()) {
				return true;
			}
		}
		return false;
	}

	private boolean inRunMode() {
		return Mode.RUN.equals(mode);
	}

	private boolean isStepEvent(Event event) {
		return event instanceof StepEvent;
	}

	public int getRootExecutionID() {
		return rootExecutionId;
	}

	public String getActivityName() {
		return activity.name;
	}

	public void addExecutionEventListener(ExecutionEventListener listener) {
		executionContext.getExecutionEventProvider().addEventListener(listener);
	}

	public void removeExecutionEventListener(ExecutionEventListener listener) {
		executionContext.getExecutionEventProvider().removeEventListener(
				listener);
	}

	public void resume(int activityExecutionID) {
		activityThread.addCommand(new ActivityExecCommand(activityExecutionID,
				Kind.RESUME));
	}

	public boolean canTerminate() {
		return !isTerminated();
	}

	public boolean isTerminated() {
		return activityThread == null || !activityThread.isAlive();
	}

	public void terminate() {
		activityThread.sendStopSignal();
		activityThread.interrupt();
		activityThread = null;
	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = createInputStream();
		}
		return inputStream;
	}

	private InputStream createInputStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				return 0;
			}
		};
	}

	@Override
	public InputStream getErrorStream() {
		if (errorInputStream == null) {
			errorInputStream = createInputStream();
		}
		return errorInputStream;
	}

	@Override
	public int waitFor() throws InterruptedException {
		if (isRunning()) {
			wait();
		}
		return EXIT_CODE;
	}

	@Override
	public int exitValue() {
		if (isRunning()) {
			throw new IllegalThreadStateException("Process hasn't exited");
		}
		return EXIT_CODE;
	}

	@Override
	public void destroy() {
		terminate();
	}

}
