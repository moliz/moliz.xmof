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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.DebugPlugin;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.debugger.process.ActivityProcess;
import org.modelexecution.fumldebug.debugger.process.internal.ActivityExecCommand.Kind;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class InternalActivityProcess extends Process implements
		ExecutionEventListener {

	public enum Mode {
		DEBUG, RUN;
	}

	private static final int EXIT_CODE = 0;
	private final ExecutionContext executionContext = ExecutionContext
			.getInstance();

	private Activity activity;
	private Mode mode = Mode.RUN;

	private Queue<Event> eventQueue;
	private Queue<ActivityExecCommand> cmdQueue;

	private int rootExecutionID = -1;
	private int lastExecutionID = -1;

	private PipedInputStream externalInputStream;
	private PipedInputStream externalErrorInputStream;

	private PipedOutputStream stdOutput;
	private PipedOutputStream errOutput;

	private boolean isTerminated = false;
	private boolean isStarted = false;

	// TODO do we need the observer or is it sufficient for it to read the
	// events from the event queue
	private ActivityProcess observer;

	public InternalActivityProcess(Activity activity, Mode mode) {
		this.activity = activity;
		this.mode = mode;
	}

	public void setListener(ActivityProcess activityProcess) {
		this.observer = activityProcess;
	}

	public void run() {
		initialize();
		startListeningToContext();
		queueCommand(new ActivityExecCommand(activity, Kind.START));
		performCommands();
	}

	private void initialize() {
		initializeQueues();
		resetRuntimeFlags();
		createStreams();
		rootExecutionID = -1;
		lastExecutionID = -1;
	}

	private void initializeQueues() {
		cmdQueue = new LinkedList<ActivityExecCommand>();
		eventQueue = new LinkedList<Event>();
	}

	private void resetRuntimeFlags() {
		isTerminated = false;
		isStarted = false;
	}

	private void createStreams() {
		externalInputStream = new PipedInputStream();
		externalErrorInputStream = new PipedInputStream();
		try {
			stdOutput = new PipedOutputStream(externalInputStream);
			errOutput = new PipedOutputStream(externalErrorInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startListeningToContext() {
		addExecutionEventListener(this);
	}

	private void stopListeningToContext() {
		removeExecutionEventListener(this);
	}

	public void queueCommand(ActivityExecCommand command) {
		cmdQueue.offer(command);
	}

	public void performCommands() {
		if (isTerminated())
			return;

		ActivityExecCommand nextCommand = null;
		while ((nextCommand = cmdQueue.poll()) != null) {
			executeCommandSavely(nextCommand);
		}

		if (!isTerminated() && observer != null) {
			observer.notifySuspended();
		}
	}

	private void executeCommandSavely(final ActivityExecCommand command) {
		ISafeRunnable runnable = new ISafeRunnable() {

			@Override
			public void run() throws Exception {
				command.execute(executionContext);
			}

			@Override
			public void handleException(Throwable exception) {
				handleActivityRuntimeException(exception);
			}
		};
		SafeRunner.run(runnable);
	}

	@Override
	public void notify(Event event) {
		logEvent(event);
		queueEvent(event);
		checkForStateChange(event);
		queueResumeIfInRunMode(event);
	}

	private void logEvent(Event event) {
		// TODO log mechanism to output
		System.out.println(event);
		writeToOutput(event.toString());
	}

	private void writeToOutput(String string) {
		try {
			stdOutput.write(string.getBytes());
			stdOutput.flush();
		} catch (IOException e) {
			DebugPlugin.log(e);
		}
	}

	private void handleActivityRuntimeException(Throwable exception) {
		// TODO log to own plugin
		DebugPlugin.log(exception);
		try {
			PrintStream printStream = new PrintStream(errOutput);
			exception.printStackTrace(printStream);
			printStream.flush();
			errOutput.flush();
		} catch (Exception e) {
			DebugPlugin.log(e);
		}
	}

	private void queueEvent(Event event) {
		eventQueue.offer(event);
	}

	private void checkForStateChange(Event event) {
		saveExecutionID(event);
		if (isFirstActivityEntryEvent(event)) {
			saveRootExecutionID(event);
			setStarted(true);
		} else if (isLastActivityExitEvent(event)) {
			terminate();
		}
	}

	private void saveExecutionID(Event event) {
		lastExecutionID = event.getActivityExecutionID();
	}

	private void saveRootExecutionID(Event event) {
		rootExecutionID = event.getActivityExecutionID();
	}

	private boolean isFirstActivityEntryEvent(Event event) {
		return event instanceof ActivityEntryEvent && rootExecutionID == -1;
	}

	private boolean isLastActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent
				&& rootExecutionID == event.getActivityExecutionID();
	}

	private void queueResumeIfInRunMode(Event event) {
		if (inRunMode() && isStepEvent(event)) {
			queueResumeCommand(event.getActivityExecutionID());
		}
	}

	private boolean inRunMode() {
		return Mode.RUN.equals(mode);
	}

	private boolean isStepEvent(Event event) {
		return event instanceof StepEvent;
	}

	public void resume(int activityExecutionID) {
		queueResumeCommand(activityExecutionID);
		performCommands();
	}

	public void resume() {
		queueResumeCommand(getLastExecutionID());
		performCommands();
	}

	private void queueResumeCommand(int activityExecutionID) {
		queueCommand(new ActivityExecCommand(activityExecutionID, Kind.RESUME));
	}

	public int getRootExecutionID() {
		return rootExecutionID;
	}

	public int getLastExecutionID() {
		return lastExecutionID;
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

	public List<Event> pollAllEvents() {
		List<Event> eventList = new ArrayList<Event>(eventQueue);
		eventQueue.clear();
		return eventList;
	}

	public boolean isRunning() {
		return isStarted() && !isTerminated();
	}

	public boolean isStarted() {
		return isStarted;
	}

	private void setStarted(boolean started) {
		isStarted = started;
	}

	public boolean canTerminate() {
		return isRunning();
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	private void setTerminated(boolean terminated) {
		isTerminated = terminated;
	}

	public boolean isSuspended() {
		return isStarted() && !isTerminated();
	}

	public void suspend() {
		// TODO do we have to do something here?
	}

	public void terminate() {
		stopListeningToContext();
		sendExitSignal();
		setTerminated(true);
		closeAllStreams();
		if (observer != null) {
			observer.notifyTerminated();
		}
	}

	private void sendExitSignal() {
		try {
			stdOutput.write(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeAllStreams() {
		try {
			stdOutput.close();
			errOutput.close();
			externalErrorInputStream.close();
			externalInputStream.close();
		} catch (IOException e) {
			DebugPlugin.log(e);
		}
	}

	@Override
	public OutputStream getOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				// we don't need anything from outside using this stream
			}
		};
	}

	@Override
	public InputStream getInputStream() {
		return externalInputStream;
	}

	@Override
	public InputStream getErrorStream() {
		return externalErrorInputStream;
	}

	@Override
	public int waitFor() throws InterruptedException {
		return EXIT_CODE;
	}

	@Override
	public int exitValue() {
		return EXIT_CODE;
	}

	@Override
	public void destroy() {
		terminate();
	}

}
