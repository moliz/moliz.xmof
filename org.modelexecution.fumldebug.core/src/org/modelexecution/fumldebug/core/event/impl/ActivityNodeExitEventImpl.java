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
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ActivityNodeExitEventImpl extends ActivityNodeEventImpl implements ActivityNodeExitEvent {
	
	public ActivityNodeExitEventImpl(int activityExecutionID, ActivityNode node, Event parent){
		super(activityExecutionID, node, parent);
	}
	
	@Override
	public String toString() {
		return "ActivityNodeExitEvent node = " + this.getNode().name + " (" + this.getNode().getClass().getName() + ")";
	}

}