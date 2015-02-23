/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API
 * Tanja Mayerhofer - implementation
 */
package org.modelexecution.fuml.values.convert.uml2.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.modelexecution.fuml.convert.IConversionResult;

import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;

/**
 * Represents the input of a fUML trace to UML trace conversion and obtains its
 * elements to be converted.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class FUMLValueInput {

	private Object originalInput;
	private Collection<Object> elementsToConvert;
	private IConversionResult modelConversionResult;

	public FUMLValueInput(Object input, IConversionResult modelConversionResult) {
		super();
		this.originalInput = input;
		this.modelConversionResult = modelConversionResult;
		elementsToConvert = deriveElementsToConvertFromInput();
	}

	public Object getOriginalInput() {
		return originalInput;
	}

	private Collection<Object> deriveElementsToConvertFromInput() {
		if (originalInput instanceof ValueList) {
			return getElementToConvert((ValueList) originalInput);
		} else {
			return Collections.emptyList();
		}
	}

	private Collection<Object> getElementToConvert(ValueList values) {
		Collection<Object> elementsToConvert = new HashSet<Object>();
		for (Value value : values) {
			elementsToConvert.add(value);
			elementsToConvert.addAll(getElementsToConvert(value));
		}
		return elementsToConvert;
	}

	private Collection<Object> getElementsToConvert(Value value) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		if (value instanceof CompoundValue) {
			CompoundValue compoundValue = (CompoundValue) value;
			valuesToConvert.addAll(getElementsToConvert(compoundValue));
		}
		return valuesToConvert;
	}

	private Collection<Object> getElementsToConvert(CompoundValue value) {
		Collection<Object> elementsToConvert = new HashSet<Object>();
		for (FeatureValue featureValue : value.featureValues) {
			elementsToConvert.add(featureValue);
			elementsToConvert.addAll(getElementsToConvert(featureValue));
		}
		return elementsToConvert;
	}

	private Collection<Object> getElementsToConvert(FeatureValue value) {
		Collection<Object> elementsToConvert = new HashSet<Object>();
		for (Value v : value.values) {
			elementsToConvert.add(v);
			elementsToConvert.addAll(getElementsToConvert(v));
		}
		return elementsToConvert;
	}

	public Collection<Object> getElementsToConvert() {
		return Collections.unmodifiableCollection(elementsToConvert);
	}

	public IConversionResult getModelConversionResult() {
		return modelConversionResult;
	}

	public boolean hasInput() {
		return originalInput instanceof ValueList;
	}

	public boolean hasModelConversionResult() {
		boolean containsObjects = false;
		if (originalInput instanceof ValueList && modelConversionResult != null) {
			ValueList values = (ValueList) originalInput;
			for (Value value : values) {
				if (value instanceof Object_) {
					containsObjects = true;
					Object_ object_ = (Object_) value;
					if (modelConversionResult.getInputObject(object_.types
							.get(0)) != null)
						return true;
					else
						return false;
				}
			}
		}
		if(!containsObjects)
			return true;
		return false;
	}
}
