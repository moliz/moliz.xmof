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
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

/**
 * @author Tanja Mayerhofer
 *
 */
public class FeatureValueEventImpl extends ExtensionalValueEventImpl implements
		FeatureValueEvent {

	private FeatureValue featureValue;
	private StructuralFeature feature;
	private ValueList values;
	private int position;
	
	public FeatureValueEventImpl(ExtensionalValue extensionalValue, ExtensionalValueEventType type, FeatureValue featureValue, ValueList values, int position) {
		super(extensionalValue, type);
		this.featureValue = featureValue;
		this.feature = this.featureValue.feature;
		this.values = values;
		this.position = position;
	}
	
	public FeatureValueEventImpl(ExtensionalValue extensionalValue, ExtensionalValueEventType type, FeatureValue featureValue) {
		super(extensionalValue, type);
		this.featureValue = featureValue;
		this.feature = this.featureValue.feature;
	}
	
	public FeatureValue getFeatureValue() {
		return this.featureValue;
	}

	public StructuralFeature getFeature() {
		return feature;
	}

	public ValueList getValues() {
		return values;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "FeatureValueEvent type = " +  getType() + System.getProperty("line.separator") + " value = " + getExtensionalValue() + System.getProperty("line.separator") + " feature = " + feature.name;
	}

}
