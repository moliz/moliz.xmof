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

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.TraceEvent;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * Describes a point in the future of the execution trace of an activity and
 * checks whether a specified {@link Event} marks this described point.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class TracePointDescription {

	public enum ExecutionMoment {
		ENTRY, EXIT;
	}

	private int executionID;
	private ExecutionMoment moment;
	private ActivityNode activityNode;

	public TracePointDescription(int executionID, ExecutionMoment moment) {
		super();
		this.executionID = executionID;
		this.moment = moment;
	}

	public TracePointDescription(int executionID, ExecutionMoment moment,
			ActivityNode activityNode) {
		super();
		this.executionID = executionID;
		this.moment = moment;
		this.activityNode = activityNode;
	}

	public boolean isMarkerReached(Event event) {
		if (activityNode == null) {
			return isActivityMarkerReached(event);
		} else {
			return isActivityNodeMarkerReached(event);
		}
	}

	private boolean isActivityMarkerReached(Event event) {
		switch (moment) {
		case ENTRY:
			return isActivityEntryEvent(event);
		case EXIT:
			return isActivityExitEvent(event);
		}
		return false;
	}

	private boolean isActivityEntryEvent(Event event) {
		return event instanceof ActivityEntryEvent
				&& executionID == getExecutionId(event);
	}

	private int getExecutionId(Event event) {
		if (event instanceof TraceEvent) {
			TraceEvent traceEvent = (TraceEvent) event;
			return traceEvent.getActivityExecutionID();
		}
		return 0;
	}

	private boolean isActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent
				&& executionID == getExecutionId(event);
	}

	private boolean isActivityNodeMarkerReached(Event event) {
		switch (moment) {
		case ENTRY:
			return isActivityNodeEntryEvent(event);
		case EXIT:
			return isActivityNodeExitEvent(event);
		}
		return false;
	}

	private boolean isActivityNodeEntryEvent(Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			return activityNode.equals(activityNodeEntryEvent.getNode());
		}
		return false;
	}

	private boolean isActivityNodeExitEvent(Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeExitEvent = (ActivityNodeExitEvent) event;
			return activityNode.equals(activityNodeExitEvent.getNode());
		}
		return false;
	}

}
