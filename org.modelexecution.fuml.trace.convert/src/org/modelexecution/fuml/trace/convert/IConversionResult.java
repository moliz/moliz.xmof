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
package org.modelexecution.fuml.trace.convert;

import org.modelexecution.fuml.trace.uml2.tracemodel.Trace;

/**
 * The result of a conversion performed by an {@link IConverter}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public interface IConversionResult {

	/**
	 * Returns the status of the conversion giving information on whether the
	 * conversion succeeded.
	 * 
	 * @return the conversion status.
	 */
	public IConversionStatus getStatus();

	/**
	 * Returns the input of the conversion.
	 * 
	 * @return the input.
	 */
	public Object getInput();

	/**
	 * Returns the fUML trace element that has been created for the specified
	 * {@code input}.
	 * 
	 * @param input
	 *            to get corresponding fUML trace element for.
	 * @return the fUML trace element corresponding to {@code input}.
	 */
	public Object getOutputUMLTraceElement(Object input);

	/**
	 * Returns the input element that corresponds to the specified fUML
	 * {@code output} model element.
	 * 
	 * @param output
	 *            the fUML model element to get corresponding input for.
	 * @return the corresponding input element.
	 */
	public Object getInputFUMLTraceElement(Object output);

	/**
	 * Determines if conversion caused errors.
	 * 
	 * @return true if conversion caused errors, false otherwise
	 */
	public boolean hasErrors();

	/**
	 * Returns the UML trace converted from the {@link #getInput() input}.
	 * 
	 * @return the converted UML trace.
	 */
	public Trace getTrace();

	/**
	 * Returns the model conversion result used for the trace conversion.
	 * 
	 * @return the model conversion result used for the trace conversion.
	 */
	public org.modelexecution.fuml.convert.IConversionResult getModelConversionResult();
}
