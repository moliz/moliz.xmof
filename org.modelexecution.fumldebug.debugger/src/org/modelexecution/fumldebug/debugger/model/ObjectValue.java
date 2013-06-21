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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.debugger.model.internal.BidirectionalMap;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class ObjectValue extends Value {

	private static final String OBJECT_VALUE_REFERENCED_TYPE_NAME = "object";
	private Object_ value = null;
	private BidirectionalMap<FeatureValueVariable, StructuralFeature> featureVariables = new BidirectionalMap<FeatureValueVariable, StructuralFeature>();

	public ObjectValue(ActivityDebugTarget target, Object_ value) {
		super(target);
		this.value = value;
		updateVariables();
	}

	private void updateVariables() {
		for (FeatureValue featureValue : value.featureValues) {
			StructuralFeature structuralFeature = featureValue.feature;
			if (structuralFeature instanceof Property) {
				Property property = (Property) structuralFeature;
				if (property.association != null)
					// do not consider associations
					continue;
			}
			if (!featureVariables.containsValue(structuralFeature)) {
				FeatureValueVariable variable = new FeatureValueVariable(
						getActivityDebugTarget(), this, structuralFeature);
				featureVariables.put(variable, structuralFeature);
			}
		}

		for (StructuralFeature structuralFeature : featureVariables.getValues()) {
			if (value.getFeatureValue(structuralFeature) == null)
				featureVariables.removeByValue(structuralFeature);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		return OBJECT_VALUE_REFERENCED_TYPE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		StringBuffer valueString = new StringBuffer();
		for (Iterator<Classifier> iterator = value.getTypes().iterator(); iterator
				.hasNext();) {
			Classifier classifier = iterator.next();
			valueString.append(classifier.qualifiedName);
			if (iterator.hasNext())
				valueString.append(",");
		}
		valueString.append(" (id=" + value.hashCode() + ")");
		return valueString.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		updateVariables();
		Collection<FeatureValueVariable> variables = featureVariables.getKeys();
		return variables.toArray(new IVariable[(variables.size())]);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		updateVariables();
		Collection<FeatureValueVariable> variables = featureVariables.getKeys();
		return variables.size() > 0;
	}

	public Object_ getValue() {
		return value;
	}
}
