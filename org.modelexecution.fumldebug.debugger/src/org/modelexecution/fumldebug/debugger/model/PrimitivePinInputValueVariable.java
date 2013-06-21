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

import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Value;

public class PrimitivePinInputValueVariable extends ValueVariable {

	private PinInputValue pinInputValue;	

	public PrimitivePinInputValueVariable(ActivityDebugTarget target, String name, PinInputValue pinInputValue) {
		super(target, name);
		this.pinInputValue = pinInputValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException {
		Value value = pinInputValue.getValue(this);
		if (value instanceof PrimitiveValue)
			return new PrimitiveValueValue(getActivityDebugTarget(),
					(PrimitiveValue) value);
		return null; 
	}

}