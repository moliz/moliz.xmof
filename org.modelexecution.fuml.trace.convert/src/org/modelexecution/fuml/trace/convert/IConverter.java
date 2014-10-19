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


/**
 * A converter, which convert fUML traces into UML traces.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public interface IConverter {

	/**
	 * Specifies whether this converter is able to convert {@code input}.
	 * 
	 * @param input
	 *            the input to be checked.
	 * @return <code>true</code> if it can convert {@code input}; otherwise
	 *         <code>false</code>.
	 */
	public boolean canConvert(Object input, org.modelexecution.fuml.convert.IConversionResult modelConversionResult);

	/**
	 * Converts the specified {@code input} into an UML trace and returns
	 * the result in the form of a {@link IConversionResult}.
	 * 
	 * @param input
	 *            the fUML trace to be converted.
	 * @return the result of the conversion.
	 */
	public IConversionResult convert(Object input, org.modelexecution.fuml.convert.IConversionResult modelConversionResult);

}
