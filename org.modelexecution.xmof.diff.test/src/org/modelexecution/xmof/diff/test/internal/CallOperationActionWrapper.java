package org.modelexecution.xmof.diff.test.internal;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;

public class CallOperationActionWrapper {

	private CallOperationAction action;

	public CallOperationActionWrapper(CallOperationAction action) {
		this.action = action;
	}
	
	public InputPin getTargetInputPin() {
		return action.target;
	}
	
}
