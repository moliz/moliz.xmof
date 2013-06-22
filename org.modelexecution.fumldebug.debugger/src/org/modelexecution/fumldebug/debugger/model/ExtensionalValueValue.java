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

import fUML.Semantics.Classes.Kernel.ExtensionalValue;


public class ExtensionalValueValue extends Value {

	private ExtensionalValue extensionalValue;
	
	public ExtensionalValueValue(ActivityDebugTarget target, ExtensionalValue extensionalValue) {
		super(target);
		this.extensionalValue = extensionalValue;
	}

	public ExtensionalValue getValue() {
		return extensionalValue;
	}
}
