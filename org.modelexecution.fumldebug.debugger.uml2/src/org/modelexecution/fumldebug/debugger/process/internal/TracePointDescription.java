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
 * checks whether a specified {@link Event} matches this described point.
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

	public boolean isMatch(Event event) {
		if (activityNode == null) {
			return matchesActivityPoint(event);
		} else {
			return matchesActivityNodePoint(event);
		}
	}

	private boolean matchesActivityPoint(Event event) {
		switch (moment) {
		case ENTRY:
			return matchesActivityEntryEvent(event);
		case EXIT:
			return matchesActivityExitEvent(event);
		}
		return false;
	}

	private boolean matchesActivityEntryEvent(Event event) {
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

	private boolean matchesActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent
				&& executionID == getExecutionId(event);
	}

	private boolean matchesActivityNodePoint(Event event) {
		switch (moment) {
		case ENTRY:
			return matchesActivityNodeEntryEvent(event);
		case EXIT:
			return matchesActivityNodeExitEvent(event);
		}
		return false;
	}

	private boolean matchesActivityNodeEntryEvent(Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			return activityNode.equals(activityNodeEntryEvent.getNode());
		}
		return false;
	}

	private boolean matchesActivityNodeExitEvent(Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeExitEvent = (ActivityNodeExitEvent) event;
			return activityNode.equals(activityNodeExitEvent.getNode());
		}
		return false;
	}

}
