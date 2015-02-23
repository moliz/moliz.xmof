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

import java.util.Collection;
import java.util.ArrayList;

import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.values.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.BooleanValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.CompoundValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.DataValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.EnumerationValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.FeatureValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.IntegerValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.LinkPopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.ObjectPopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.PrimitiveValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.ReferencePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.StringValuePopulator;
import org.modelexecution.fuml.values.convert.uml2.internal.populator.UnlimitedNaturalValuePopulator;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Value;

public class UML2ValuePopulatorSuite {

	private Collection<IFUMLValuePopulator> valuePopulators = new ArrayList<>();
	private IFUMLFeatureValuePopulator featureValuePopulator;

	private ConversionResultImpl result;

	private IConversionResult modelConversionResult;

	public UML2ValuePopulatorSuite(ConversionResultImpl result,
			IConversionResult modelConversionResult) {
		this.result = result;
		this.modelConversionResult = modelConversionResult;
		initializePopulators();
	}

	private void initializePopulators() {
		valuePopulators.add(new PrimitiveValuePopulator());
		valuePopulators.add(new UnlimitedNaturalValuePopulator());
		valuePopulators.add(new StringValuePopulator());
		valuePopulators.add(new IntegerValuePopulator());
		valuePopulators.add(new BooleanValuePopulator());
		valuePopulators.add(new DataValuePopulator());
		valuePopulators.add(new EnumerationValuePopulator());
		valuePopulators.add(new CompoundValuePopulator());
		valuePopulators.add(new ObjectPopulator());
		valuePopulators.add(new LinkPopulator());
		valuePopulators.add(new ReferencePopulator());

		featureValuePopulator = new FeatureValuePopulator();
	}

	public void populate(Object umlElement, Object fumlElement) {
		if (fumlElement instanceof FeatureValue) {
			featureValuePopulator
					.populate(
							(org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue) umlElement,
							(FeatureValue) fumlElement, result,
							modelConversionResult);
		} else if (fumlElement instanceof Value) {
			for (IFUMLValuePopulator populator : valuePopulators) {
				populator
						.populate(
								(org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value) umlElement,
								(Value) fumlElement, result,
								modelConversionResult);
			}
		}
	}

}
