package org.modelexecution.xmof.diff.test.internal;

import java.util.HashSet;
import java.util.Set;

import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class TraceUtil {

	public static Set<ActivityExecution> getActivityExecutions(Trace trace, String activityQualifiedName) {
		Set<ActivityExecution> activityExecutions = new HashSet<ActivityExecution>();
		for(ActivityExecution activityExecution : trace.getActivityExecutions()) {
			Activity activity = activityExecution.getActivity();
			if(activity.qualifiedName.equals(activityQualifiedName))
				activityExecutions.add(activityExecution);
		}
		return activityExecutions;
	}
	
}
