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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.xmof.debug.model.XMOFDebugTarget;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class ObjectValue extends Value {

	private static final String OBJECT_VALUE_REFERENCED_TYPE_NAME = "object";
	private Object_ object = null;
	private List<FeatureValueVariable> featureValueVariables = new ArrayList<FeatureValueVariable>();

	public ObjectValue(XMOFDebugTarget target, Object_ object) {
		super(target);
		this.object = object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		return OBJECT_VALUE_REFERENCED_TYPE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		StringBuffer valueString = new StringBuffer();
		for (Iterator<Classifier> iterator = object.getTypes().iterator(); iterator
				.hasNext();) {
			Classifier classifier = iterator.next();
			valueString.append(classifier.qualifiedName);
			if (iterator.hasNext())
				valueString.append(",");
		}
		valueString.append(" (id=" + object.hashCode() + ")");
		return valueString.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		for (FeatureValue featureValue : object.featureValues) {
			if (shouldCreateVariable(featureValue)) {
				FeatureValueVariable variable = createFeatureValueVariable(featureValue);
				featureValueVariables.add(variable);
			}
		}
		return featureValueVariables
				.toArray(new IVariable[(featureValueVariables.size())]);
	}

	private boolean shouldCreateVariable(FeatureValue featureValue) {
		StructuralFeature structuralFeature = featureValue.feature;
		if (structuralFeature instanceof Property) {
			Property property = (Property) structuralFeature;
			if (property.association != null)
				// do not consider associations
				return false;
		}
		return true;
	}

	private FeatureValueVariable createFeatureValueVariable(
			FeatureValue featureValue) {
		FeatureValueVariable variable = new FeatureValueVariable(
				getXMOFDebugTarget(), object, featureValue.feature);
		return variable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		return object.getFeatureValues().size() > 0;
	}

}
