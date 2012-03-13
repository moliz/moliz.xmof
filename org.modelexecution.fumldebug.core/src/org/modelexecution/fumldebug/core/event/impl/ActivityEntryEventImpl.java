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

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ActivityEntryEventImpl extends ActivityEventImpl implements
		ActivityEntryEvent {

	public ActivityEntryEventImpl(Activity activity) {
		super(activity);
	}

}
