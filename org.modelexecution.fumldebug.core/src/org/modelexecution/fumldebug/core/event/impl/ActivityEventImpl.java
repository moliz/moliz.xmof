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

import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * @author Tanja Mayerhofer
 *
 */
public abstract class ActivityEventImpl extends TraceEventImpl implements ActivityEvent {

	private Activity activity;
	
	public ActivityEventImpl(int activityExecutionID, Activity activity, Event parent) {
		super(activityExecutionID, parent);
		this.activity = activity;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
}
