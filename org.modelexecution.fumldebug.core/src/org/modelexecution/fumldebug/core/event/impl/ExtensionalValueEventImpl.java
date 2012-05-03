/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.event.impl;

import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExtensionalValueEventImpl extends EventImpl implements
		ExtensionalValueEvent {

	private ExtensionalValueEventType type;
	private ExtensionalValue value;
	
	public ExtensionalValueEventImpl(ExtensionalValue value, ExtensionalValueEventType type) {
		super();
		this.value = value;
		this.type = type;
	}
	
	public ExtensionalValue getExtensionalValue() {
		return value;
	}

	public ExtensionalValueEventType getType() {
		return type;
	}

}
