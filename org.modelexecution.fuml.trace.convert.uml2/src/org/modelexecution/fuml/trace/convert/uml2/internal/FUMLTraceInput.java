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
package org.modelexecution.fuml.trace.convert.uml2.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Represents the input of a fUML trace to UML trace conversion and obtains its elements to
 * be converted.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class FUMLTraceInput {

	private Object originalInput;
	private Collection<EObject> traceElementsToConvert;
	private Collection<Object> valuesToConvert;
	private IConversionResult modelConversionResult;

	public FUMLTraceInput(Object input, IConversionResult modelConversionResult) {
		super();
		this.originalInput = input;
		this.modelConversionResult = modelConversionResult;
		traceElementsToConvert = deriveTraceElementsToConvertFromInput();
		valuesToConvert = deriveValuesToConvertFromInput();
	}

	public Object getOriginalInput() {
		return originalInput;
	}

	private Collection<EObject> deriveTraceElementsToConvertFromInput() {
		if (originalInput instanceof Trace) {
			return getTraceElementsToConvertFromInputTrace((Trace)originalInput);
		} else {
			return Collections.emptyList();
		}
	}
	
	private Collection<EObject> getTraceElementsToConvertFromInputTrace(Trace inputTrace) {
		Collection<EObject> traceElementsToConvert = new HashSet<EObject>();
		traceElementsToConvert.add(inputTrace);
		for(TreeIterator<EObject> eAllContents = inputTrace.eAllContents();eAllContents.hasNext();) {
			traceElementsToConvert.add(eAllContents.next());
		}
		return traceElementsToConvert;
	}

	private Collection<Object> deriveValuesToConvertFromInput() {
		if (originalInput instanceof Trace) {
			return getValuesToConvert((Trace)originalInput);
		} else {
			return Collections.emptyList();
		}
	}
	
	private Collection<Object> getValuesToConvert(Trace inputTrace) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		for(ValueInstance valueInstance : inputTrace.getValueInstances()) {
			valuesToConvert.addAll(getValuesToConvert(valueInstance));
		}
		return valuesToConvert;
	}
	
	private Collection<Object> getValuesToConvert(ValueInstance valueInstance) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		
		Value runtimeValue = valueInstance.getRuntimeValue();
		if(runtimeValue != null) {
			valuesToConvert.add(runtimeValue);
			valuesToConvert.addAll(getValuesToConvert(runtimeValue));
		}
		
		for(ValueSnapshot valueSnapshot : valueInstance.getSnapshots()) {
			valuesToConvert.addAll(getValuesToConvert(valueSnapshot));
		}
		return valuesToConvert;
	}
	
	private Collection<Object> getValuesToConvert(ValueSnapshot valueSnapshot) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		Value value = valueSnapshot.getValue();
		if(value != null) {
			valuesToConvert.add(value);
			valuesToConvert.addAll(getValuesToConvert(value));
		}
		return valuesToConvert;
	}
	
	private Collection<Object> getValuesToConvert(Value value) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		if(value instanceof CompoundValue) {
			CompoundValue compoundValue = (CompoundValue) value;
			valuesToConvert.addAll(getValuesToConvert(compoundValue));
		}
		return valuesToConvert;
	}
	
	private Collection<Object> getValuesToConvert(CompoundValue value) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		for(FeatureValue featureValue : value.featureValues) {
			valuesToConvert.add(featureValue);
			valuesToConvert.addAll(getValuesToConvert(featureValue));
		}
		return valuesToConvert;
	}
	
	private Collection<Object> getValuesToConvert(FeatureValue value) {
		Collection<Object> valuesToConvert = new HashSet<Object>();
		for(Value v : value.values) {
			valuesToConvert.add(v);
			valuesToConvert.addAll(getValuesToConvert(v));
		}
		return valuesToConvert;
	}
	
	public Collection<EObject> getTraceElementsToConvert() {
		return Collections.unmodifiableCollection(traceElementsToConvert);
	}
	
	public Collection<Object> getValuesToConvert() {
		return Collections.unmodifiableCollection(valuesToConvert);
	}

	public IConversionResult getModelConversionResult() {
		return modelConversionResult;
	}

	public boolean containsTrace() {
		return originalInput instanceof Trace;
	}
	
	public boolean containsModelConversionResult() {
		Trace traceToConvert = getTraceToConvert();
		if(traceToConvert != null) {
			if(traceToConvert.getActivityExecutions().size() > 0) {
				Activity fumlActivity = traceToConvert.getActivityExecutions().get(0).getActivity();
				Object umlActivity = modelConversionResult.getInputObject(fumlActivity);
				return (umlActivity instanceof org.eclipse.uml2.uml.Activity);
			} else {
				return true;
			}
		}
		return false;
	}
	
	private Trace getTraceToConvert() {
		if(originalInput instanceof Trace) {
			return (Trace)originalInput;
		}
		return null;
	}

}
