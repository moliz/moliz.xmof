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

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class FeatureValueVariable extends Variable {

	private ExtensionalValue extensionalValue;
	private StructuralFeature structuralFeature;

	public FeatureValueVariable(XMOFDebugTarget target,
			ExtensionalValue extensionalValue,
			StructuralFeature structuralFeature) {
		super(target, structuralFeature.name);
		this.extensionalValue = extensionalValue;
		this.structuralFeature = structuralFeature;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException {
		FeatureValue featureValue = extensionalValue
				.getFeatureValue(structuralFeature);
		return new FeatureValueValue(getXMOFDebugTarget(), featureValue);
	}

}
