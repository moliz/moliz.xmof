package org.modelexecution.xmof.diff.test.internal;

import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ActivityNodeWrapper {

	private ActivityNode activityNode;

	public ActivityNodeWrapper(ActivityNode activityNode) {
		this.activityNode = activityNode;
	}
	
	public boolean isCallOperatinAction() {
		return activityNode instanceof CallOperationAction;
	}
	
	public boolean isCallBehaviorAction() {
		return activityNode instanceof CallBehaviorAction;
	}
	
}
