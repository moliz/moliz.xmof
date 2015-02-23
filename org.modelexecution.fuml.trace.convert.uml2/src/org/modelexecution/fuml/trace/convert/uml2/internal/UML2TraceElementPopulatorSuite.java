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
import java.util.ArrayList;

import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ActionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ActivityExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ActivityNodeExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.CallActionExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ControlNodeExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.DecisionNodeExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ExpansionInputPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ExpansionRegionExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InitialNodeExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InputOutputValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InputParameterSettingPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InputParameterValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InputPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.InputValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ObjectTokenInstancePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.OutputParameterSettingPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.OutputParameterValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.OutputPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.OutputValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ParameterSettingPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ParameterValuePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.StructuredActivityNodeExecutionPopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.TokenInstancePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.TracePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ValueInstancePopulator;
import org.modelexecution.fuml.trace.convert.uml2.internal.populator.ValueSnapshotPopulator;

public class UML2TraceElementPopulatorSuite {

	private Collection<IUML2TraceElementPopulator> populators = new ArrayList<>();

	private ConversionResultImpl result;

	private IConversionResult modelConversionResult;

	public UML2TraceElementPopulatorSuite(ConversionResultImpl result, IConversionResult modelConversionResult) {
		this.result = result;
		this.modelConversionResult = modelConversionResult;
		initializePopulators();
	}

	private void initializePopulators() {
		populators.add(new ActionPopulator());
		populators.add(new ActivityExecutionPopulator());
		populators.add(new ActivityNodeExecutionPopulator());
		populators.add(new CallActionExecutionPopulator());
		populators.add(new ControlNodeExecutionPopulator());
		populators.add(new DecisionNodeExecutionPopulator());
		populators.add(new ExpansionInputPopulator());
		populators.add(new ExpansionRegionExecutionPopulator());
		populators.add(new InitialNodeExecutionPopulator());
		populators.add(new InputOutputValuePopulator());
		populators.add(new InputParameterSettingPopulator());
		populators.add(new InputParameterValuePopulator());
		populators.add(new InputPopulator());
		populators.add(new InputValuePopulator());
		populators.add(new ObjectTokenInstancePopulator());
		populators.add(new OutputParameterSettingPopulator());
		populators.add(new OutputParameterValuePopulator());
		populators.add(new OutputPopulator());
		populators.add(new OutputValuePopulator());
		populators.add(new ParameterSettingPopulator());
		populators.add(new ParameterValuePopulator());
		populators.add(new StructuredActivityNodeExecutionPopulator());
		populators.add(new TokenInstancePopulator());
		populators.add(new TracePopulator());
		populators.add(new ValueInstancePopulator());
		populators.add(new ValueSnapshotPopulator());
	}

	public void populate(Object umlTraceElement,
			Object fumlTraceElement) {
		for (IUML2TraceElementPopulator populator : populators) {
			populator.populate(umlTraceElement, fumlTraceElement, result, modelConversionResult);
		}
	}

}
