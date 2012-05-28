/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.sourcelookup;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Source lookup director for fUML {@link Activity activities}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ActivitySourceLookupDirector extends AbstractSourceLookupDirector {

	@Override
	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[]{new ActivitySourceLookupParticipant()});
	}

}
