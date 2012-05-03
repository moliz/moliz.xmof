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

import java.util.ArrayList;
import java.util.List;

import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Element;

/**
 * @author Tanja Mayerhofer
 *
 */
public class StepEventImpl extends TraceEventImpl implements StepEvent {

	private Element location;
	
	private List<ActivityNode> newEnabledNodes = new ArrayList<ActivityNode>();
	
	public StepEventImpl(int activityExecutionID, Element location, Event parent) {
		super(activityExecutionID, parent);
		this.location = location;
	}
	
	public Element getLocation() {
		return location;
	}
	
	public List<ActivityNode> getNewEnabledNodes() {
		return newEnabledNodes;
	}
	
}
