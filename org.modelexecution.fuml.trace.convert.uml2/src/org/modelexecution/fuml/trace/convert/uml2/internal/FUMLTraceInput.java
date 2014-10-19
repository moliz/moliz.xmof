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
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

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
	private Collection<Value> valuesToConvert;
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

	private Collection<Value> deriveValuesToConvertFromInput() {
		if (originalInput instanceof Trace) {
			return getValuesToConvertFromInputTrace((Trace)originalInput);
		} else {
			return Collections.emptyList();
		}
	}
	
	private Collection<Value> getValuesToConvertFromInputTrace(Trace inputTrace) {
		Collection<Value> valuesToConvert = new HashSet<Value>();		
		for(TreeIterator<EObject> eAllContents = inputTrace.eAllContents();eAllContents.hasNext();) {
			EObject eObject = eAllContents.next();
			if(eObject instanceof ValueSnapshot) {
				ValueSnapshot valueSnapshot = (ValueSnapshot) eObject;
				valuesToConvert.add(valueSnapshot.getValue());
			}
		}
		return valuesToConvert;
	}
	
	public Collection<EObject> getTraceElementsToConvert() {
		return Collections.unmodifiableCollection(traceElementsToConvert);
	}
	
	public Collection<Value> getValuesToConvert() {
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
