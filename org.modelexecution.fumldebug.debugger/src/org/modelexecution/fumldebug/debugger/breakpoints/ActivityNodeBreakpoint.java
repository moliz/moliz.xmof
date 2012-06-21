/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.breakpoints;

import org.eclipse.core.resources.IMarker;
import org.eclipse.debug.core.model.Breakpoint;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * An activity node breakpoint is attached to an fUML {@link ActivityNode} for
 * suspending the execution of an {@link Activity} at the respective activity
 * node.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public abstract class ActivityNodeBreakpoint extends Breakpoint {

	public static final String MARKER_ID = "org.modelexecution.fumldebug.debugger.activityNodeBreakpointMarker"; //$NON-NLS-1$
	public static final String ACTIVITY_NODE_LOCATION = "org.modelexecution.fumldebug.debugger.activityNodeQName"; //$NON-NLS-1$
	private static final String SEP = "#";

	public String getQualifiedNameOfActivityNode() {
		IMarker marker = getMarker();
		return marker.getAttribute(ACTIVITY_NODE_LOCATION, (String) null);
	}

	@Override
	public String toString() {
		return getMarker().getResource().getProjectRelativePath() + SEP
				+ getQualifiedNameOfActivityNode();
	}

}
