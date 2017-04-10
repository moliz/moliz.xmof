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
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Test;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.performance.TimeMeasurement;
import org.modelexecution.xmof.diff.test.internal.Report;
import org.modelexecution.xmof.diff.test.internal.TraceUtil;
import org.modelexecution.xmof.diff.test.internal.factory.ADHiringFactory;
import org.modelexecution.xmof.diff.test.internal.factory.Input;

/**
 * @author Tanja
 * 
 */
public class HiringADEvaluation extends ADEvaluation {

	protected static final String HIRING_V1_INPUT_RESOURCE_NAME = "hiringV1_input";
	protected static final String HIRING_V2_INPUT_RESOURCE_NAME = "hiringV2_input";
	protected static final String HIRING_V3_INPUT_RESOURCE_NAME = "hiringV3_input";
	protected static final String HIRING_V4_INPUT_RESOURCE_NAME = "hiringV4_input";
	
	@Test
	public void hiringV1V2TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V1, ADHiringFactory.HIRING_V2);
		Input input = createHiringV1V2InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(2, semanticMatchResults.size());	   // internal
		assertFalse(semanticMatchResults.get(0).matches()); // true
		assertTrue(semanticMatchResults.get(1).matches()); // false

		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(3, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(3, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);		
		printTraceInformationToFile("report/ad/hiring/hiringV1V2trace.csv", semanticMatchResults);
	}
	
	@Test
	public void hiringV1V2MatchPerformance_allInputs() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V1, ADHiringFactory.HIRING_V2);
			Input input = createHiringV1V2InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/hiring/hiringV1V2performance.csv");
	}

	@Test
	public void hiringV2V3TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V2, ADHiringFactory.HIRING_V3);
		Input input = createHiringV2V3InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(2, semanticMatchResults.size());	   // internal
		assertFalse(semanticMatchResults.get(0).matches()); // true
		assertTrue(semanticMatchResults.get(1).matches()); // false

		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(3, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(3, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/hiring/hiringV2V3trace.csv", semanticMatchResults);
	}
	
	@Test
	public void hiringV2V3MatchPerformance_allInputs() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V2, ADHiringFactory.HIRING_V3);
			Input input = createHiringV2V3InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/hiring/hiringV2V3performance.csv");
	}
	
	@Test
	public void hiringV3V4TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V3, ADHiringFactory.HIRING_V4);
		Input input = createHiringV3V4InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(2, semanticMatchResults.size());	   // internal
		assertTrue(semanticMatchResults.get(0).matches()); // true
		assertFalse(semanticMatchResults.get(1).matches()); // false

		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(8, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(3, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(4, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/hiring/hiringV3V4trace.csv", semanticMatchResults);
	}
	
	@Test
	public void hiringV3V4MatchPerformance_allInputs() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADHiringFactory.HIRING_V3, ADHiringFactory.HIRING_V4);
			Input input = createHiringV3V4InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/hiring/hiringV3V4performance.csv");
	}

	private Input createHiringV1V2InputAll() {
		Input input = new Input();
		
		ADHiringFactory factory = new ADHiringFactory(resourceSet);
		
		EObject hiringV1pvd_true = factory.createHiringV1ParameterValueDefintion(true);
		Resource hiringV1res_true = createParameterDefinitionResource(HIRING_V1_INPUT_RESOURCE_NAME + "1", hiringV1pvd_true);
		EObject hiringV2pvd_true = factory.createHiringV2ParameterValueDefintion(true);
		Resource hiringV2res_true = createParameterDefinitionResource(HIRING_V2_INPUT_RESOURCE_NAME +  "1", hiringV2pvd_true);
		input.addInputResources(hiringV1res_true, hiringV2res_true);
		
		EObject hiringV1pvd_false = factory.createHiringV1ParameterValueDefintion(false);
		Resource hiringV1res_false = createParameterDefinitionResource(HIRING_V1_INPUT_RESOURCE_NAME + "2", hiringV1pvd_false);
		EObject hiringV2pvd_false = factory.createHiringV2ParameterValueDefintion(false);
		Resource hiringV2res_false = createParameterDefinitionResource(HIRING_V2_INPUT_RESOURCE_NAME +  "2", hiringV2pvd_false);
		input.addInputResources(hiringV1res_false, hiringV2res_false);
		
		return input;
	}
	
	private Input createHiringV2V3InputAll() {
		Input input = new Input();
		
		ADHiringFactory factory = new ADHiringFactory(resourceSet);
		
		EObject hiringV2pvd_true = factory.createHiringV2ParameterValueDefintion(true);
		Resource hiringV2res_true = createParameterDefinitionResource(HIRING_V2_INPUT_RESOURCE_NAME + "1", hiringV2pvd_true);
		EObject hiringV3pvd_true = factory.createHiringV3ParameterValueDefintion(true);
		Resource hiringV3res_true = createParameterDefinitionResource(HIRING_V3_INPUT_RESOURCE_NAME +  "1", hiringV3pvd_true);
		input.addInputResources(hiringV2res_true, hiringV3res_true);
		
		EObject hiringV2pvd_false = factory.createHiringV2ParameterValueDefintion(false);
		Resource hiringV2res_false = createParameterDefinitionResource(HIRING_V2_INPUT_RESOURCE_NAME + "2", hiringV2pvd_false);
		EObject hiringV3pvd_false = factory.createHiringV3ParameterValueDefintion(false);
		Resource hiringV3res_false = createParameterDefinitionResource(HIRING_V3_INPUT_RESOURCE_NAME +  "2", hiringV3pvd_false);
		input.addInputResources(hiringV2res_false, hiringV3res_false);
		
		return input;
	}
	
	private Input createHiringV3V4InputAll() {
		Input input = new Input();
		
		ADHiringFactory factory = new ADHiringFactory(resourceSet);
		
		EObject hiringV3pvd_true = factory.createHiringV3ParameterValueDefintion(true);
		Resource hiringV3res_true = createParameterDefinitionResource(HIRING_V3_INPUT_RESOURCE_NAME + "1", hiringV3pvd_true);
		EObject hiringV4pvd_true = factory.createHiringV4ParameterValueDefintion(true);
		Resource hiringV4res_true = createParameterDefinitionResource(HIRING_V4_INPUT_RESOURCE_NAME +  "1", hiringV4pvd_true);
		input.addInputResources(hiringV3res_true, hiringV4res_true);
		
		EObject hiringV3pvd_false = factory.createHiringV3ParameterValueDefintion(false);
		Resource hiringV3res_false = createParameterDefinitionResource(HIRING_V3_INPUT_RESOURCE_NAME + "2", hiringV3pvd_false);
		EObject hiringV4pvd_false = factory.createHiringV4ParameterValueDefintion(false);
		Resource hiringV4res_false = createParameterDefinitionResource(HIRING_V4_INPUT_RESOURCE_NAME +  "2", hiringV4pvd_false);
		input.addInputResources(hiringV3res_false, hiringV4res_false);
		
		return input;
	}
	
}
