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
package org.modelexecution.fuml.trace.convert.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.modelexecution.fuml.trace.convert.IConversionResult;
import org.modelexecution.fuml.trace.uml2.tracemodel.Trace;

/**
 * Default implementation of an {@link IConversionResult}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ConversionResultImpl implements IConversionResult {

	private ConversionStatusImpl status;

	private Object input;

	private Map<Object, Object> inputToOutputMap = new HashMap<>();
	private Map<Object, Object> outputToInputMap = new HashMap<>();

	private Trace trace;

	private org.modelexecution.fuml.convert.IConversionResult modelConversionResult;

	public ConversionResultImpl() {
	}

	public ConversionResultImpl(Object input, org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		this.input = input;
		this.modelConversionResult = modelConversionResult;
	}

	public void addInOutMapping(Object input, Object output) {
		inputToOutputMap.put(input, output);
		outputToInputMap.put(output, input);

		if (output instanceof Trace) {
			setTrace((Trace) output);
		}
	}

	public Collection<Entry<Object, Object>> getMappings() {
		return inputToOutputMap.entrySet();
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
	public Object getOutputUMLTraceElement(Object input) {
		return inputToOutputMap.get(input);
	}

	@Override
	public Object getInputFUMLTraceElement(Object output) {
		return outputToInputMap.get(output);
	}

	@Override
	public boolean hasErrors() {
		return this.getStatus().getSeverity() == IStatus.ERROR;
	}

	private void setTrace(Trace trace) {
		this.trace = trace;
	}

	@Override
	public Trace getTrace() {
		return trace;
	}

	public org.modelexecution.fuml.convert.IConversionResult getModelConversionResult() {
		return modelConversionResult;
	}

	public void setModelConversionResult(org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		this.modelConversionResult = modelConversionResult;
	}
}
