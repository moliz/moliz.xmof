package org.modelexecution.xmof.examples;

import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class TestPlainFuml {

	@Test
	public void test() {
		Activity activity = ActivityFactory.createActivity("activity");
		Activity calledActivity = ActivityFactory.createActivity("callesActivity");
		CallBehaviorAction action1 = ActivityFactory.createCallBehaviorAction(activity, "call a1", calledActivity);
		CallBehaviorAction action2 = ActivityFactory.createCallBehaviorAction(activity, "call a2", calledActivity);
		ActivityFactory.createControlFlow(activity, action1, action2);
		ExecutionContext.getInstance().execute(activity, null, null);
		
	
	}

}
