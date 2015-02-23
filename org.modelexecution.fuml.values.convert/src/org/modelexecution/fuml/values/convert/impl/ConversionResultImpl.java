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
package org.modelexecution.fuml.values.convert.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.modelexecution.fuml.values.convert.IConversionResult;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Value;

/**
 * Default implementation of an {@link IConversionResult}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ConversionResultImpl implements IConversionResult {

	private ConversionStatusImpl status;

	private Object input;

	private Map<Value, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value> valueInputToOutputMap = new HashMap<>();
	private Map<org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value, Value> valueOutputToInputMap = new HashMap<>();
	
	private Map<FeatureValue, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue> featureValueInputToOutputMap = new HashMap<>();
	private Map<org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue, FeatureValue> featureValueOutputToInputMap = new HashMap<>();

	private org.modelexecution.fuml.convert.IConversionResult modelConversionResult;

	public ConversionResultImpl() {
	}

	public ConversionResultImpl(Object input, org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		this.input = input;
		this.modelConversionResult = modelConversionResult;
	}

	public void addInOutMapping(Object input, Object output) {
		if(input instanceof Value && output instanceof org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value) {
			addInOutMapping((Value) input, (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value) output);
		} else if( input instanceof FeatureValue && output instanceof org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue) {
			addInOutMapping((FeatureValue)input, (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue) output); 
		}		
	}
	
	private void addInOutMapping(Value input, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value output) {
		valueInputToOutputMap.put(input, output);
		valueOutputToInputMap.put(output, input);
	}
	
	private void addInOutMapping(FeatureValue input, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue output) {
		featureValueInputToOutputMap.put(input, output);
		featureValueOutputToInputMap.put(output, input);
	}
	
	public Collection<Entry<Object, Object>> getMappings() {
		Map<Object, Object> mappings = new HashMap<>();
		
		for (Iterator<Entry<Value, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value>> iterator = getValueMappings()
				.iterator(); iterator.hasNext();) {
			Entry<Value, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value> valueMappingEntry = iterator.next();
			mappings.put(valueMappingEntry.getKey(), valueMappingEntry.getValue());
		}
		
		for (Iterator<Entry<FeatureValue, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue>> iterator = getFeatureValueMappings()
				.iterator(); iterator.hasNext();) {
			Entry<FeatureValue, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue> featureValueMappingEntry = iterator.next();
			mappings.put(featureValueMappingEntry.getKey(), featureValueMappingEntry.getValue());
		}
		
		return mappings.entrySet();
	}

	private Collection<Entry<Value, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value>> getValueMappings() {
		return valueInputToOutputMap.entrySet();
	}
	
	private Collection<Entry<FeatureValue, org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue>> getFeatureValueMappings() {
		return featureValueInputToOutputMap.entrySet();
	}

	public void setStatus(ConversionStatusImpl status) {
		this.status = status;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	@Override
	public ConversionStatusImpl getStatus() {
		return status;
	}

	@Override
	public Object getInput() {
		return input;
	}

	@Override
	public org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value getOutputValue(Value input) {
		return valueInputToOutputMap.get(input);
	}

	@Override
	public Value getInputValue(org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value output) {
		return valueOutputToInputMap.get(output);
	}

	@Override
	public boolean hasErrors() {
		return this.getStatus().getSeverity() == IStatus.ERROR;
	}

	public org.modelexecution.fuml.convert.IConversionResult getModelConversionResult() {
		return modelConversionResult;
	}

	public void setModelConversionResult(org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		this.modelConversionResult = modelConversionResult;
	}

	@Override
	public org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue getOutputFeatureValue(
			fUML.Semantics.Classes.Kernel.FeatureValue input) {
		return featureValueInputToOutputMap.get(input);
	}

	@Override
	public fUML.Semantics.Classes.Kernel.FeatureValue getInputFeatureValue(
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue output) {
		return featureValueOutputToInputMap.get(output);
	}
}
