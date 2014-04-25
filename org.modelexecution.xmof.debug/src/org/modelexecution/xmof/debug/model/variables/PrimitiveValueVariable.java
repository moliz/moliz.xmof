/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.debug.model.variables;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.modelexecution.xmof.debug.model.XMOFDebugTarget;

import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Value;

public class PrimitiveValueVariable extends Variable {

	private FeatureValueValue featureValueValue;

	public PrimitiveValueVariable(XMOFDebugTarget target, String name,
			FeatureValueValue featureValue) {
		super(target, name);
		this.featureValueValue = featureValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException {
		Value value = featureValueValue.getValue(this);
		if (value instanceof PrimitiveValue)
			return new PrimitiveValueValue(getXMOFDebugTarget(),
					(PrimitiveValue) value);
		return null;
	}

}
