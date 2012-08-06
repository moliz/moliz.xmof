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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.modelexecution.fumldebug.core.Breakpoint;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.impl.BreakpointImpl;
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

	private TracePointDescription stepUntilPoint;

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
		stepUntilPoint = null;
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

	private void queueStepCommand(int executionID) {
		queueCommand(createStepCommand(executionID));
	}

	private void queueStepCommand(int executionID, ActivityNode activityNode) {
		queueCommand(createStepCommand(executionID, activityNode));
	}

	private ActivityExecCommand createStepCommand(int executionID,
			ActivityNode activityNode) {
		return new ActivityExecCommand(executionID, activityNode,
				Kind.NEXT_STEP);
	}

	private ActivityExecCommand createStepCommand(int executionID) {
		return new ActivityExecCommand(executionID, Kind.NEXT_STEP);
	}

	private void queueResumeCommand(int executionID) {
		queueCommand(createResumeCommand(executionID));
	}

	private ActivityExecCommand createResumeCommand(int activityExecutionID) {
		return new ActivityExecCommand(activityExecutionID, Kind.RESUME);
	}

	public void performCommands() {
		ISafeRunnable runnable = new ISafeRunnable() {
			int executionID = -1;

			@Override
			public void run() throws Exception {
				ActivityExecCommand nextCommand = null;
				while ((nextCommand = cmdQueue.poll()) != null
						&& !shouldSuspend() && !shouldTerminate()) {
					executionID = nextCommand.getExecutionID();
					nextCommand.execute(executionContext);
				}
			}

			@Override
			public void handleException(Throwable exception) {
				handleActivityRuntimeException(exception, executionID);
			}
		};
		SafeRunner.run(runnable);
	}

	private void handleActivityRuntimeException(Throwable exception,
			int executionID) {
		queueEvent(createErrorEvent(exception, executionID));
		FUMLDebuggerPlugin.log(exception);
	}

	private Event createErrorEvent(Throwable exception, int executionID) {
		return new ErrorEvent(exception, executionID);
	}

	@Override
	public void notify(Event event) {
		if (!concernsThisProcess(event))
			return;

		if (!inRunMode() || !isStepEvent(event))
			queueEvent(event);

		updateProcessState(event);
		updateStepUntilPointState(event);

		if (inRunMode() && isStepEvent(event)) {
			unqueueEvent(event);
			queueResumeCommand(lastExecutionID);
		}

		if (isBreakpointEvent(event)) {
			unsetStepUntilPoint();
		}

		if (haveStepUntilPoint() && isStepEvent(event)) {
			unqueueEvent(event);
			queueStepCommand(lastExecutionID);
		}
	}

	private boolean concernsThisProcess(Event event) {
		return isFirstActivityEntryEvent(event) || hasOwnExecutionIDs(event)
				|| hasParentHavingCorrectExecutionID(event);
	}

	private boolean hasOwnExecutionIDs(Event event) {
		return rootExecutionID == getExecutionID(event)
				|| lastExecutionID == getExecutionID(event);
	}

	private int getExecutionID(Event event) {
		if (isTraceEvent(event)) {
			return ((TraceEvent) event).getActivityExecutionID();
		} else if (isErrorEvent(event)) {
			return ((ErrorEvent) event).getActivityExecutionID();
		} else {
			return -1;
		}
	}

	private boolean isErrorEvent(Event event) {
		return event instanceof ErrorEvent;
	}

	private boolean isTraceEvent(Event event) {
		return event instanceof TraceEvent;
	}

	private boolean hasParentHavingCorrectExecutionID(Event event) {
		if (!isTraceEvent(event))
			return false;
		TraceEvent currentEvent = (TraceEvent) event;
		Event parentEvent = null;
		while ((parentEvent = currentEvent.getParent()) != null) {
			if (hasOwnExecutionIDs(parentEvent)) {
				return true;
			} else if (isTraceEvent(parentEvent)) {
				currentEvent = (TraceEvent) parentEvent;
			} else {
				return false;
			}
		}
		return false;
	}

	private void queueEvent(Event event) {
		eventQueue.offer(event);
	}

	private void unqueueEvent(Event event) {
		eventQueue.remove(event);
	}

	private void updateProcessState(Event event) {
		saveExecutionID(event);
		if (isFirstActivityEntryEvent(event)) {
			saveRootExecutionID(event);
		} else if (isFinalActivityExitEvent(event)) {
			terminate();
		}
	}

	private void saveExecutionID(Event event) {
		if (isTraceEvent(event)) {
			lastExecutionID = ((TraceEvent) event).getActivityExecutionID();
		}
	}

	private void saveRootExecutionID(Event event) {
		if (isTraceEvent(event)) {
			rootExecutionID = ((TraceEvent) event).getActivityExecutionID();
		}
	}

	public boolean isFirstActivityEntryEvent(Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			return this.activity.equals(activityEntryEvent.getActivity());
		}
		return false;
	}

	public boolean isFinalActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent
				&& rootExecutionID == ((ActivityExitEvent) event)
						.getActivityExecutionID();
	}

	private void updateStepUntilPointState(Event event) {
		if (haveStepUntilPoint() && stepUntilPoint.isMatch(event))
			unsetStepUntilPoint();
	}

	private boolean inRunMode() {
		return Mode.RUN.equals(mode);
	}

	private boolean isStepEvent(Event event) {
		return event instanceof StepEvent;
	}

	private boolean isBreakpointEvent(Event event) {
		return event instanceof BreakpointEvent;
	}

	private boolean haveStepUntilPoint() {
		return stepUntilPoint != null;
	}

	public void resume() {
		setShouldSuspend(false);
		queueCommand(createResumeCommand(rootExecutionID));
		performCommands();
	}

	public void nextStep(int executionID) {
		setShouldSuspend(false);
		queueStepCommand(executionID);
		performCommands();
	}

	public void nextStep(int executionID, ActivityNode activityNode) {
		setShouldSuspend(false);
		queueStepCommand(executionID, activityNode);
		performCommands();
	}

	public void stepUntil(int executionID,
			TracePointDescription pointDescription) {
		setStopPointDescription(pointDescription);
		nextStep(executionID);
	}

	private void setStopPointDescription(TracePointDescription pointDescription) {
		stepUntilPoint = pointDescription;
	}

	private void unsetStepUntilPoint() {
		stepUntilPoint = null;
	}

	public int getRootExecutionID() {
		return rootExecutionID;
	}

	public int getLastExecutionID() {
		return lastExecutionID;
	}

	public Activity getRootActivity() {
		return activity;
	}

	public String getActivityName() {
		return activity.qualifiedName;
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

	public void addBreakpoint(ActivityNode node) {
		if (node != null) {
			Breakpoint breakpoint = new BreakpointImpl(node);
			executionContext.addBreakpoint(breakpoint);
		}
	}

	public void removeBreakpoint(ActivityNode node) {
		if (node != null) {
			Breakpoint breakpoint = executionContext.getBreakpoint(node);
			if (breakpoint != null) {
				executionContext.removeBreakpoint(breakpoint);
			}
		}
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
