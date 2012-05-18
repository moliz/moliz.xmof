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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;
import org.modelexecution.fumldebug.debugger.process.internal.ActivityExecCommand.Kind;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class InternalActivityProcess extends Process implements
		ExecutionEventListener {

	public enum Mode {
		DEBUG, RUN;
	}

	public static final int EXIT_VALUE = 0;

	private final ExecutionContext executionContext = ExecutionContext
			.getInstance();

	private Activity activity;
	private Mode mode = Mode.RUN;

	private Queue<Event> eventQueue;
	private Queue<ActivityExecCommand> cmdQueue;

	private int rootExecutionID = -1;
	private int lastExecutionID = -1;

	private boolean shouldTerminate = false;
	private boolean shouldSuspend = false;

	private TracePointDescription stopPointDescription;

	public InternalActivityProcess(Activity activity, Mode mode) {
		this.activity = activity;
		this.mode = mode;
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
		rootExecutionID = -1;
		lastExecutionID = -1;
	}

	private void initializeQueues() {
		cmdQueue = new LinkedList<ActivityExecCommand>();
		eventQueue = new LinkedList<Event>();
	}

	private void resetRuntimeFlags() {
		shouldTerminate = false;
		shouldSuspend = false;
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
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void run() throws Exception {
				ActivityExecCommand nextCommand = null;
				while ((nextCommand = cmdQueue.poll()) != null
						&& !shouldSuspend() && !shouldTerminate()) {
					nextCommand.execute(executionContext);
				}
			}

			@Override
			public void handleException(Throwable exception) {
				handleActivityRuntimeException(exception);
			}
		};
		SafeRunner.run(runnable);
	}

	private void handleActivityRuntimeException(Throwable exception) {
		queueEvent(createErrorEvent(exception));
		FUMLDebuggerPlugin.log(exception);
	}

	private Event createErrorEvent(Throwable exception) {
		return new ErrorEvent(exception);
	}

	@Override
	public void notify(Event event) {
		if (!inRunMode() || !isStepEvent(event)) {
			queueEvent(event);
		}
		checkForStateChange(event);
		queueResumeIfInRunMode(event);
		queueStepIfStopPointNotReached(event);
	}

	private void queueEvent(Event event) {
		eventQueue.offer(event);
	}

	private void checkForStateChange(Event event) {
		saveExecutionID(event);
		if (isFirstActivityEntryEvent(event)) {
			saveRootExecutionID(event);
		} else if (isFinalActivityExitEvent(event)) {
			terminate();
		}
	}

	private void saveExecutionID(Event event) {
		if (event instanceof TraceEvent) {
			lastExecutionID = ((TraceEvent) event).getActivityExecutionID();
		}
	}

	private void saveRootExecutionID(Event event) {
		if (event instanceof TraceEvent) {
			rootExecutionID = ((TraceEvent) event).getActivityExecutionID();
		}
	}

	public boolean isFirstActivityEntryEvent(Event event) {
		return event instanceof ActivityEntryEvent && rootExecutionID == -1;
	}

	public boolean isFinalActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent
				&& rootExecutionID == ((ActivityExitEvent) event)
						.getActivityExecutionID();
	}

	private void queueResumeIfInRunMode(Event event) {
		if (inRunMode() && isStepEvent(event)) {
			StepEvent stepEvent = (StepEvent) event;
			int currentExecutionID = stepEvent.getActivityExecutionID();
			queueCommand(createResumeCommand(currentExecutionID));
		}
	}

	private boolean inRunMode() {
		return Mode.RUN.equals(mode);
	}

	private boolean isStepEvent(Event event) {
		return event instanceof StepEvent;
	}

	private void queueStepIfStopPointNotReached(Event event) {
		if (stopPointDescription != null && isStepEvent(event)) {
			int currentExecutionID = ((StepEvent) event)
					.getActivityExecutionID();
			TraceEvent mostCurrentEvent = getMostCurrentTraceEventFromQueue();
			if (stopPointDescription.isMarkerReached(mostCurrentEvent)) {
				unsetStopPointDescription();
			} else {
				eventQueue.remove(event);
				queueCommand(createStepCommand(currentExecutionID));
			}
		}
	}

	private TraceEvent getMostCurrentTraceEventFromQueue() {
		TraceEvent mostCurrentEvent = null;
		for (Iterator<Event> iterator = eventQueue.iterator(); iterator
				.hasNext();) {
			Event event = iterator.next();
			if (event instanceof TraceEvent && !isStepEvent(event)) {
				mostCurrentEvent = (TraceEvent) event;
			}
		}
		return mostCurrentEvent;
	}

	public void resume() {
		resume(getLastExecutionID());
	}

	public void resume(int activityExecutionID) {
		setShouldSuspend(false);
		unsetStopPointDescription();
		queueCommand(createResumeCommand(activityExecutionID));
		performCommands();
	}

	private ActivityExecCommand createResumeCommand(int activityExecutionID) {
		return new ActivityExecCommand(activityExecutionID, Kind.RESUME);
	}

	public void nextStep(int executionID) {
		setShouldSuspend(false);
		queueCommand(createStepCommand(executionID));
		performCommands();
	}

	public void nextStep(int executionID, ActivityNode activityNode) {
		setShouldSuspend(false);
		queueCommand(createStepCommand(executionID, activityNode));
		performCommands();
	}

	public void stepUntil(int executionID,
			TracePointDescription pointDescription) {
		setStopPointDescription(pointDescription);
		nextStep(executionID);
	}

	private ActivityExecCommand createStepCommand(int executionID,
			ActivityNode activityNode) {
		return new ActivityExecCommand(executionID, activityNode,
				Kind.NEXT_STEP);
	}

	private ActivityExecCommand createStepCommand(int executionID) {
		return new ActivityExecCommand(executionID, Kind.NEXT_STEP);
	}

	private void setStopPointDescription(TracePointDescription pointDescription) {
		stopPointDescription = pointDescription;
	}

	private void unsetStopPointDescription() {
		stopPointDescription = null;
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

	public List<Event> pollEvents() {
		List<Event> eventList = new ArrayList<Event>(eventQueue);
		eventQueue.clear();
		return eventList;
	}

	public boolean shouldTerminate() {
		return shouldTerminate;
	}

	private void setShouldTerminate(boolean terminated) {
		shouldTerminate = terminated;
	}

	public boolean shouldSuspend() {
		return shouldSuspend;
	}

	private void setShouldSuspend(boolean suspend) {
		this.shouldSuspend = suspend;
	}

	public void suspend() {
		setShouldSuspend(true);
	}

	public void terminate() {
		setShouldTerminate(true);
		stopListeningToContext();
		executionContext.terminate(rootExecutionID);
	}

	@Override
	public OutputStream getOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				// we don't need anything from outside using this stream
				// as communication is done directly using commands
			}
		};
	}

	@Override
	public InputStream getInputStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public InputStream getErrorStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public int waitFor() throws InterruptedException {
		return EXIT_VALUE;
	}

	@Override
	public int exitValue() {
		return EXIT_VALUE;
	}

	@Override
	public void destroy() {
		terminate();
	}

}
