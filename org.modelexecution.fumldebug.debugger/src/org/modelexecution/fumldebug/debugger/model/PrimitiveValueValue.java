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
import org.eclipse.debug.core.model.IVariable;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.UnlimitedNaturalValue;

public class PrimitiveValueValue extends Value {

	private static final String PRIMITIVE_VALUE_REFERENCED_TYPE_NAME = "primitive value";
	private static final String INTEGER_VALUE_REFERENCED_TYPE_NAME = "integer value";
	private static final String STRING_VALUE_REFERENCED_TYPE_NAME = "string value";
	private static final String BOOLEAN_VALUE_REFERENCED_TYPE_NAME = "boolean value";
	private static final String UNLIMITED_NATURAL_VALUE_REFERENCED_TYPE_NAME = "unlimited natural value";
	
	private PrimitiveValue value = null;
	
	public PrimitiveValueValue(ActivityDebugTarget target, PrimitiveValue value) {
		super(target);
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		if(value instanceof IntegerValue) {
			return INTEGER_VALUE_REFERENCED_TYPE_NAME;
		} else if(value instanceof StringValue) {
			return STRING_VALUE_REFERENCED_TYPE_NAME;
		} else if(value instanceof BooleanValue) {
			return BOOLEAN_VALUE_REFERENCED_TYPE_NAME;
		} else if(value instanceof UnlimitedNaturalValue) {
			return UNLIMITED_NATURAL_VALUE_REFERENCED_TYPE_NAME;
		}
		return PRIMITIVE_VALUE_REFERENCED_TYPE_NAME;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		if(value instanceof IntegerValue) {			
			IntegerValue integerValue = (IntegerValue) value;
			return Integer.toString(integerValue.value);
		} else if(value instanceof StringValue) {
			StringValue stringValue = (StringValue) value;
			return stringValue.value;
		} else if(value instanceof BooleanValue) {
			BooleanValue booleanValue = (BooleanValue) value;
			return Boolean.toString(booleanValue.value);
		} else if(value instanceof UnlimitedNaturalValue) {			
			UnlimitedNaturalValue unlimitedNaturalValue = (UnlimitedNaturalValue) value;
			return Integer.toString(unlimitedNaturalValue.value.naturalValue);
		}
		return value.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		return new IVariable[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		return false;
	}

}
