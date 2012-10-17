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

import org.modelexecution.fumldebug.core.Breakpoint;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Classes.Kernel.Element;

/**
 * @author Tanja Mayerhofer
 *
 */
public class BreakpointEventImpl extends SuspendEventImpl implements BreakpointEvent {

	private List<Breakpoint> breakpoint = new ArrayList<Breakpoint>();
	/**
	 * @param parent
	 * @param node
	 */
	public BreakpointEventImpl(int activityExecutionID, Element location, Event parent) {
		super(activityExecutionID, location, parent);
	}

	public List<Breakpoint> getBreakpoints() {
		return breakpoint;
	}
}
