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

import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;
import org.modelexecution.fumldebug.core.event.FeatureValueEvent;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;

/**
 * @author Tanja Mayerhofer
 *
 */
public class FeatureValueEventImpl extends ExtensionalValueEventImpl implements
		FeatureValueEvent {

	private FeatureValue featureValue;
	
	public FeatureValueEventImpl(ExtensionalValue extensionalValue, ExtensionalValueEventType type, FeatureValue featureValue) {
		super(extensionalValue, type);
		this.featureValue = featureValue;
	}
	
	public FeatureValue getFeatureValue() {
		return this.featureValue;
	}

}
