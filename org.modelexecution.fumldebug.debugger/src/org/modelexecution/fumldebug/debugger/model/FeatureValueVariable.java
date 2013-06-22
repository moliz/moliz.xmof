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

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class FeatureValueVariable extends Variable {

	private ExtensionalValueValue extensionalValueValue;
	private StructuralFeature structuralFeature;
	
	public FeatureValueVariable(ActivityDebugTarget target, ExtensionalValueValue extensionalValue, StructuralFeature structuralFeature) {
		super(target);
		this.extensionalValueValue = extensionalValue;
		this.structuralFeature = structuralFeature;
	}


	/* (non-Javadoc
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	@Override
	public IValue getValue() throws DebugException{
		ExtensionalValue extensionalValue = extensionalValueValue.getValue();
		FeatureValue featureValue = extensionalValue.getFeatureValue(structuralFeature);
		return new FeatureValueValue(getActivityDebugTarget(), featureValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	@Override
	public String getName() throws DebugException {
		return structuralFeature.name;
	}

}
