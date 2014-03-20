/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.performance.TimeMeasurement;
import org.modelexecution.xmof.diff.test.internal.Report;
import org.modelexecution.xmof.diff.test.internal.TraceUtil;
import org.modelexecution.xmof.diff.test.internal.factory.ADIBM2557Factory;
import org.modelexecution.xmof.diff.test.internal.factory.Input;

/**
 * @author Tanja
 * 
 */
public class IBM2557ADEvaluation extends ADEvaluation {

	protected static final String IBM2557_V1_INPUT_RESOURCE_NAME = "IBM2557V1_input";
	protected static final String IBM2557_V2_INPUT_RESOURCE_NAME = "IBM2557V2_input";
	
	@Test
	public void IBM2557V1V2TestMatchResult_allInputs() {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADIBM2557Factory.IBM2557_V1, ADIBM2557Factory.IBM2557_V2);
		Input input = createIBM2557V1V2InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(6, semanticMatchResults.size());	   // i
		assertFalse(semanticMatchResults.get(0).matches()); // 1
		assertFalse(semanticMatchResults.get(1).matches()); // 2
		assertFalse(semanticMatchResults.get(2).matches()); // 3
		assertFalse(semanticMatchResults.get(3).matches()); // 4
		assertFalse(semanticMatchResults.get(4).matches()); // 5
		assertFalse(semanticMatchResults.get(5).matches()); // 6

		assertEquals(10, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(9, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(9, TraceUtil.getActivityExecutions(semanticMatchResults.get(2).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(2).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(9, TraceUtil.getActivityExecutions(semanticMatchResults.get(3).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(3).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(9, TraceUtil.getActivityExecutions(semanticMatchResults.get(4).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(4).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(9, TraceUtil.getActivityExecutions(semanticMatchResults.get(5).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(5).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/IBM/ibm2557V1V2trace.csv", semanticMatchResults);
	}
	
	@Test
	public void IBM2557V1V2MatchPerformance_allInputs() {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADIBM2557Factory.IBM2557_V1, ADIBM2557Factory.IBM2557_V2);
			Input input = createIBM2557V1V2InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/IBM/ibm2557V1V2performance.csv");
	}

	private Input createIBM2557V1V2InputAll() {
		Input input = new Input();
		
		ADIBM2557Factory factory = new ADIBM2557Factory(resourceSet);
		
		for(int i=1;i<=6;++i) {
			EObject ibm2557V1pvd = factory.createIBM2557V1ParameterValueDefintion(i);
			Resource ibm2557V1res = createParameterDefinitionResource(IBM2557_V1_INPUT_RESOURCE_NAME + i, ibm2557V1pvd);
			EObject ibm32557V2pvd = factory.createIBM3561V2ParameterValueDefintion(i);
			Resource ibm2557V2res = createParameterDefinitionResource(IBM2557_V2_INPUT_RESOURCE_NAME +  i, ibm32557V2pvd);
			input.addInputResources(ibm2557V1res, ibm2557V2res);
		}
		
		return input;
	}
	
}
