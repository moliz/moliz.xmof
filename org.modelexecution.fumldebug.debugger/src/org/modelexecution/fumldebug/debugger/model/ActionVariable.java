/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;

import fUML.Syntax.Actions.BasicActions.Action;

public class ActionVariable extends Variable {

	private static final String ACTION_VARIABLE_VARIABLE = "this";
	
	private Action action;
	private int executionId;

	public ActionVariable(ActivityDebugTarget target, Action action, int executionId) {
		super(target);
		this.action = action;
		this.executionId = executionId;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException {
		return ((ActivityDebugTarget)getDebugTarget()).getVariableValue(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	@Override
	public String getName() throws DebugException {
		return ACTION_VARIABLE_VARIABLE;
	}

	public Action getAction() {
		return action;
	}

	public int getExecutionId() {
		return executionId;
	}

}
