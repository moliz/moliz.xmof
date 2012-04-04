/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */

package org.modelexecution.fumldebug.core.event.impl;

import org.modelexecution.fumldebug.core.event.Event;

public class EventImpl implements Event {

	private long timestamp = 0;
	
	private Event parent;
	
	private int activityExecutionID;
	
	public EventImpl(int activityExecutionID) {
		super();
		timestamp = System.currentTimeMillis();
		this.activityExecutionID = activityExecutionID;
	}
	
	public EventImpl(int activityExecutionID, Event parent) {
		this(activityExecutionID);
		this.parent = parent;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Event getParent() {
		return parent;
	}
	
	public int getActivityExecutionID() {
		return activityExecutionID;
	}

}
