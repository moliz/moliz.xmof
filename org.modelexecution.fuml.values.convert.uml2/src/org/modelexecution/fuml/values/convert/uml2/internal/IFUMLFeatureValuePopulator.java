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

import org.modelexecution.fuml.values.convert.IConversionResult;

import fUML.Semantics.Classes.Kernel.FeatureValue;

/**
 * Interface for a type-specific trace element populator.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public interface IFUMLFeatureValuePopulator {

	/**
	 * Populates the feature values of {@code uml2Element} to
	 * {@code fumlElement}.
	 * 
	 * @param umlTraceElement
	 *            to be populated.
	 * @param fumlTraceElement
	 *            acting as input for the population.
	 * @param result
	 *            for accessing the mapping between other UML trace elements and
	 *            fUML trace elements.
	 * @param modelConversionResult
	 *            for accessing the mapping between fUML model elements and
	 *            original model elements
	 */
	void populate(
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue umlFeatureValue,
			FeatureValue fumlFeatureValue,
			IConversionResult result,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult);

}
