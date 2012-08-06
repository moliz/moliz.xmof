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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.modelexecution.fumldebug.debugger.model.ActivityDebugTarget;
import org.modelexecution.fumldebug.debugger.model.ActivityNodeStackFrame;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * The source lookup participant for fUML {@link Activity activities}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ActivitySourceLookupParticipant extends
		AbstractSourceLookupParticipant {

	@Override
	public String getSourceName(Object object) throws CoreException {
		if (object instanceof ActivityNodeStackFrame) {
			ActivityNodeStackFrame stackFrame = (ActivityNodeStackFrame) object;
			ActivityNode activityNode = stackFrame.getActivityNode();
			ActivityDebugTarget debugTarget = stackFrame.getActivityDebugTarget();
			IActivityProvider activityProvider = debugTarget.getActivityProvider();
			return activityProvider.getSourceFileName(activityNode);
		}
		return null;
	}
}
