package org.modelexecution.xmof.examples;

import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;

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
	
	@Test
	public void test2() {
		Activity activity = ActivityFactory.createActivity("activity");
		ValueSpecificationAction valueSpecificationAction = ActivityFactory.createValueSpecificationAction(activity, "vs", 1);
		ForkNode forkNode = ActivityFactory.createForkNode(activity, "fork");
		CallBehaviorAction callBehaviorAction = ActivityFactory.createCallBehaviorAction(activity, "call", null, 0, 1);
		ActivityFactory.createObjectFlow(activity, valueSpecificationAction.result, forkNode);
		ActivityFactory.createObjectFlow(activity, forkNode, callBehaviorAction.argument.get(0));
		ExecutionContext.getInstance().execute(activity, null, null);
		
	}

}
