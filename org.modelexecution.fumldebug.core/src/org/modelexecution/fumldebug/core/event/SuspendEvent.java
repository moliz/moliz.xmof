/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.event;

import java.util.List;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Element;

/**
 * Represents an debugging step.
 * An step occurs in the following cases 
 * (if the Activity is executed stepwise or an according breakpoint has been set):
 * (1) An activity node has been executed
 * (2) An activity execution started and the initial enabled nodes have been determined 
 * 
 * @author Tanja Mayerhofer
 *
 */
public interface SuspendEvent extends TraceEvent {

	/**
	 * Returns the location at which the step occurred.
	 * The location is
	 * (a) the ActivityNode executed last
	 * (b) the Activity in the case that the Execution of this Activity just started
	 * @return The location at which the step occurred
	 */
	public Element getLocation();
	
	/**
	 * Returns a list of ActivityNodes that have been 
	 * enabled since the last step.
	 * @return a list of ActivityNodes that have been enabled 
	 * since the last step
	 */
	public List<ActivityNode> getNewEnabledNodes();
	
}
