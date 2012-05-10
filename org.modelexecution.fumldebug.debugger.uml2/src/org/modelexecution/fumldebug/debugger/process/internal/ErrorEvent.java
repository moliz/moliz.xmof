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

import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Documents an error during the execution of an fUML {@link Activity}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ErrorEvent implements Event {

	private long timestamp;
	private Throwable error;

	protected ErrorEvent(Throwable error) {
		super();
		this.error = error;
		this.timestamp = System.currentTimeMillis();
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Returns the occurred error.
	 * 
	 * @return the occurred error.
	 */
	public Throwable getError() {
		return error;
	}

}
