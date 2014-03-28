package org.modelexecution.xmof.diff.test.internal;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityWrapper {

	private Activity activity;

	public ActivityWrapper(Activity activity) {
		this.activity = activity;
	}
	
	public String getQualifiedName() {
		return activity.qualifiedName;
	}
	
	public String getName() {
		return activity.name;
	}
}
