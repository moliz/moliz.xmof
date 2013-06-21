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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Actions.BasicActions.InputPin;

public class PinInputValue extends org.modelexecution.fumldebug.debugger.model.Value {

	private static final String PIN_INPUT_VALUE_REFERENCED_TYPE_NAME = "input";
	private Input value = null;
	private HashMap<ValueVariable, Value> valueVariables = new HashMap<ValueVariable, Value>();
	private List<ValueVariable> variables = new ArrayList<ValueVariable>();

	public PinInputValue(ActivityDebugTarget target, Input value) {
		super(target);
		this.value = value;
		initializeVariables();
	}

	private void initializeVariables() {
		List<Value> values = getValues();
		for (int i = 0; i < values.size(); i++) {
			Value value = values.get(i);
			if (value instanceof PrimitiveValue) {
				PrimitivePinInputValueVariable variable = new PrimitivePinInputValueVariable(
						getActivityDebugTarget(), "[" + i + "]", this);
				valueVariables.put(variable, value);
				variables.add(variable);
			} else if (value instanceof Object_) {
				ObjectVariable objectVariable = obtainObjectVariable((Object_) value);
				if (objectVariable != null)
					variables.add(objectVariable);
			}
		}
	}

	private ObjectVariable obtainObjectVariable(Object_ object) {
		IVariable[] locusVariables = getActivityDebugTarget()
				.getLocusVariables();
		for (int i = 0; i < locusVariables.length; ++i) {
			if (locusVariables[i] instanceof LocusVariable) {
				LocusVariable locusVariable = (LocusVariable) locusVariables[i];
				IValue locusVariableValue = getActivityDebugTarget()
						.getVariableValue(locusVariable);
				if (locusVariableValue instanceof LocusValue) {
					LocusValue locusValue = (LocusValue) locusVariableValue;
					return locusValue.getObjectVariable((Object_) object);
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		return PIN_INPUT_VALUE_REFERENCED_TYPE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		InputPin pin = value.getInputPin();		
		String typeName = "";
		if(pin.typedElement != null) 
			if(pin.typedElement.type != null)
				typeName = pin.typedElement.type.qualifiedName + " ";
		String multiplicity = "";
			if(pin.multiplicityElement != null) {
				multiplicity = "[" + pin.multiplicityElement.lower + ".." + pin.multiplicityElement.upper.naturalValue + "]";				
			}
		return typeName + multiplicity;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		return variables.toArray(new IVariable[variables.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		return getValues().size() > 0;
	}

	private List<Value> getValues() {
		List<Value> values = new ArrayList<Value>();
		for (InputValue inputValue : value.getInputValues()) {
			Value value = obtainRuntimeValueSafely(inputValue);
			if (value != null) {
				values.add(value);
			}
		}
		return values;
	}

	private Value obtainRuntimeValueSafely(InputValue inputValue) {
		Value value = null;
		try {
			value = inputValue.getInputObjectToken().getTransportedValue()
					.getRuntimeValue();
		} catch (NullPointerException e) {
		}
		return value;
	}

	public Value getValue(PrimitivePinInputValueVariable variable) {
		return valueVariables.get(variable);
	}

}