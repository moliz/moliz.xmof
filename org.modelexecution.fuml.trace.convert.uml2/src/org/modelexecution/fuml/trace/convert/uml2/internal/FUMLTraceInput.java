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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Represents the input of a fUML trace to UML trace conversion and obtains its
 * elements to be converted.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class FUMLTraceInput {

	private Object originalInput;
	private Collection<EObject> traceElementsToConvert;
	private Collection<Value> valuesToConvert;
	private Collection<Value> runtimeValues;
	private IConversionResult modelConversionResult;

	public FUMLTraceInput(Object input, IConversionResult modelConversionResult) {
		super();
		this.originalInput = input;
		this.modelConversionResult = modelConversionResult;
		traceElementsToConvert = deriveTraceElementsToConvertFromInput();
		valuesToConvert = deriveValuesToConvertFromInput();
		runtimeValues = deriveRuntimeValuesFromInput();
	}

	public Object getOriginalInput() {
		return originalInput;
	}

	private Collection<EObject> deriveTraceElementsToConvertFromInput() {
		if (originalInput instanceof Trace) {
			return getTraceElementsToConvertFromInputTrace((Trace) originalInput);
		} else {
			return Collections.emptyList();
		}
	}

	private Collection<EObject> getTraceElementsToConvertFromInputTrace(
			Trace inputTrace) {
		Collection<EObject> traceElementsToConvert = new HashSet<EObject>();
		traceElementsToConvert.add(inputTrace);
		for (TreeIterator<EObject> eAllContents = inputTrace.eAllContents(); eAllContents
				.hasNext();) {
			traceElementsToConvert.add(eAllContents.next());
		}
		return traceElementsToConvert;
	}

	private Collection<Value> deriveValuesToConvertFromInput() {
		if (originalInput instanceof Trace) {
			return getValuesToConvert((Trace) originalInput);
		} else {
			return Collections.emptyList();
		}
	}

	private Collection<Value> getValuesToConvert(Trace inputTrace) {
		Collection<Value> valuesToConvert = new HashSet<Value>();
		for (ValueInstance valueInstance : inputTrace.getValueInstances()) {
			valuesToConvert.addAll(getValuesToConvert(valueInstance));
		}
		return valuesToConvert;
	}

	private Collection<Value> getValuesToConvert(ValueInstance valueInstance) {
		Collection<Value> valuesToConvert = new HashSet<Value>();

		Value runtimeValue = valueInstance.getRuntimeValue();
		if (runtimeValue != null) {
			valuesToConvert.add(runtimeValue);
		}

		for (ValueSnapshot valueSnapshot : valueInstance.getSnapshots()) {
			valuesToConvert.addAll(getValuesToConvert(valueSnapshot));
		}
		return valuesToConvert;
	}

	private Collection<Value> getValuesToConvert(ValueSnapshot valueSnapshot) {
		Collection<Value> valuesToConvert = new HashSet<Value>();
		Value value = valueSnapshot.getValue();
		if (value != null) {
			valuesToConvert.add(value);
		}
		return valuesToConvert;
	}

	private Collection<Value> deriveRuntimeValuesFromInput() {
		if (originalInput instanceof Trace) {
			return getRuntimeValues((Trace) originalInput);
		} else {
			return Collections.emptyList();
		}
	}

	private Collection<Value> getRuntimeValues(Trace trace) {
		Collection<Value> runtimeValues = new ArrayList<Value>();
		for (ValueInstance valueInstance : trace.getValueInstances()) {			
			Value runtimeValue = valueInstance.getRuntimeValue();
			if(runtimeValue != null)
				runtimeValues.add(runtimeValue);
		}
		return runtimeValues;
	}

	public Collection<EObject> getTraceElementsToConvert() {
		return Collections.unmodifiableCollection(traceElementsToConvert);
	}

	public Collection<Value> getValuesToConvert() {
		Collection<Value> valuesToConvert = new HashSet<Value>();
		valuesToConvert.addAll(this.valuesToConvert);
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
		if (traceToConvert != null) {
			if (traceToConvert.getActivityExecutions().size() > 0) {
				Activity fumlActivity = traceToConvert.getActivityExecutions()
						.get(0).getActivity();
				Object umlActivity = modelConversionResult
						.getInputObject(fumlActivity);
				return (umlActivity instanceof org.eclipse.uml2.uml.Activity);
			} else {
				return true;
			}
		}
		return false;
	}

	private Trace getTraceToConvert() {
		if (originalInput instanceof Trace) {
			return (Trace) originalInput;
		}
		return null;
	}

	public Collection<Value> getRuntimeValues() {
		return this.runtimeValues;
	}

}
