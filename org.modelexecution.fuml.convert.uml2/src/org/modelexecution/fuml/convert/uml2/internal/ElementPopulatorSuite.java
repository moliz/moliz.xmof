/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.uml2.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.Element;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;

/**
 * Populates the feature values of fUML elements according to corresponding UML2
 * elements. This is done using generated instances of ITypePopulator.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ElementPopulatorSuite {

	private Collection<IElementPopulator> elementPopulators = new ArrayList<>();

	private ConversionResultImpl result;

	public ElementPopulatorSuite(ConversionResultImpl result) {
		this.result = result;
		initializePopulators();
	}

	private void initializePopulators() {
		elementPopulators.add(new NamedElementPopulator());
	}

	public void populate(fUML.Syntax.Classes.Kernel.Element fUMLElement,
			Element uml2Element) {
		for (IElementPopulator populator : elementPopulators) {
			populator.populate(fUMLElement, uml2Element, result);
		}
	}

}
