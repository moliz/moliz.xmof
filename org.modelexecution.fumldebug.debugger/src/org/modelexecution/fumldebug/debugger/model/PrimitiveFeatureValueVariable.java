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

public class PrimitiveFeatureValueVariable extends Variable {

	private String name;
	private FeatureValueValue featureValueValue;

	public PrimitiveFeatureValueVariable(ActivityDebugTarget target, String name,
			FeatureValueValue featureValue) {
		super(target);
		this.name = name;
		this.featureValueValue = featureValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException {
		Value value = featureValueValue.getValue(this);
		if (value instanceof PrimitiveValue)
			return new PrimitiveValueValue(getActivityDebugTarget(),
					(PrimitiveValue) value);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	@Override
	public String getName() throws DebugException {
		return name;
	}

}
