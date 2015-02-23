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
package org.modelexecution.fuml.values.convert;

import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value;


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
	 * Returns the fUML value that has been created for the specified
	 * {@code input}.
	 * 
	 * @param input
	 *            to get corresponding fUML value for.
	 * @return the fUML value corresponding to {@code input}.
	 */
	public Value getOutputValue(fUML.Semantics.Classes.Kernel.Value input);

	/**
	 * Returns the input element that corresponds to the specified fUML
	 * {@code output} value.
	 * 
	 * @param output
	 *            the fUML value to get corresponding input for.
	 * @return the corresponding input element.
	 */
	public fUML.Semantics.Classes.Kernel.Value getInputValue(Value output);
	
	/**
	 * Returns the fUML feature value that has been created for the specified
	 * {@code input}.
	 * 
	 * @param input
	 *            to get corresponding fUML feature value for.
	 * @return the fUML value corresponding to {@code input}.
	 */
	public FeatureValue getOutputFeatureValue(fUML.Semantics.Classes.Kernel.FeatureValue input);

	/**
	 * Returns the input element that corresponds to the specified fUML
	 * {@code output} feature value.
	 * 
	 * @param output
	 *            the fUML feature value to get corresponding input for.
	 * @return the corresponding input element.
	 */
	public fUML.Semantics.Classes.Kernel.FeatureValue getInputFeatureValue(FeatureValue output);

	/**
	 * Determines if conversion caused errors.
	 * 
	 * @return true if conversion caused errors, false otherwise
	 */
	public boolean hasErrors();

	/**
	 * Returns the model conversion result used for the trace conversion.
	 * 
	 * @return the model conversion result used for the trace conversion.
	 */
	public org.modelexecution.fuml.convert.IConversionResult getModelConversionResult();
}
