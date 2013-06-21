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

import java.util.Collection;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.debugger.model.internal.BidirectionalMap;

import fUML.Syntax.Actions.BasicActions.InputPin;

public class ActionValue extends Value {
	
	private static final String ACTION_VALUE_REFERENCED_TYPE_NAME = "action";
	
	private ActionExecution value = null;
	private BidirectionalMap<PinInputVariable, PinInputValue> pinInputVariables = new BidirectionalMap<PinInputVariable, PinInputValue>();

	public ActionValue(ActivityDebugTarget target, ActionExecution value) {
		super(target);
		this.value = value;
		initializeVariables();
	}

	private void initializeVariables() {
		for(Input input : value.getInputs()) {
			InputPin pin = input.getInputPin();
			PinInputVariable variable = new PinInputVariable(getActivityDebugTarget(), pin, this);
			PinInputValue value = new PinInputValue(getActivityDebugTarget(), input);
			pinInputVariables.put(variable, value);
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		return ACTION_VALUE_REFERENCED_TYPE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		return value.getNode().getClass().getSimpleName() + " (id=" + value.getNode().hashCode() + ")";
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		Collection<PinInputVariable> variables = pinInputVariables.getKeys();
		return variables.toArray(new IVariable[(variables.size())]);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		return value.getInputs().size() > 0;
	}

	public PinInputValue getPinInputValue(PinInputVariable variable) {
		return pinInputVariables.getByKey(variable);
	}
}
