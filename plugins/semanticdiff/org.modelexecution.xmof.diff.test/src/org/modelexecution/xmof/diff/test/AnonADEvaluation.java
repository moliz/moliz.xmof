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

import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Test;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.performance.TimeMeasurement;
import org.modelexecution.xmof.diff.test.internal.Report;
import org.modelexecution.xmof.diff.test.internal.factory.ADAnonFactory;
import org.modelexecution.xmof.diff.test.internal.factory.Input;

/**
 * @author Tanja
 * 
 */
public class AnonADEvaluation extends ADEvaluation {

	protected static final String ANON_V1_INPUT_RESOURCE_NAME = "anonV1_input";
	protected static final String ANON_V2_INPUT_RESOURCE_NAME = "anonV2_input";
	protected static final String ANON_V3_INPUT_RESOURCE_NAME = "anonV3_input";
	
	@Test
	public void anonV1V2TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADAnonFactory.ANON_V1, ADAnonFactory.ANON_V2);
		Input input = createAnonV1V2InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(4, semanticMatchResults.size());	   // exists found
		assertTrue(semanticMatchResults.get(0).matches()); // false false
		assertTrue(semanticMatchResults.get(1).matches()); // false true
		assertFalse(semanticMatchResults.get(2).matches()); // true false
		assertFalse(semanticMatchResults.get(3).matches()); // true true

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/anonCompany/ExampleB/anonV1V2trace.csv", semanticMatchResults);
	}

	@Test
	public void anonV1V2MatchPerformance_allInputs() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADAnonFactory.ANON_V1, ADAnonFactory.ANON_V2);
			Input input = createAnonV1V2InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/anonCompany/ExampleB/anonV1V2performance.csv");
	}
	
	@Test
	public void anonV1V2MatchPerformance_singleInput() throws EolRuntimeException {
		HashSet<XMOFMatcher> matchers = new HashSet<XMOFMatcher>();
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADAnonFactory.ANON_V1, ADAnonFactory.ANON_V2);
			matchers.add(matcher);
			Input input = createAnonV1V2InputSingle();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		System.out.println(TimeMeasurement.INSTANCE);
	}

	private Input createAnonV1V2InputAll() {
		Input input = new Input();
		
		ADAnonFactory factory = new ADAnonFactory(resourceSet);

		int inputCounter = 0;
		
		++inputCounter;
		EObject anonV1pvd_false_false = factory.createAnonV1ParameterValueDefintion(false, false);
		Resource anonV1res_false_false = createParameterDefinitionResource(ANON_V1_INPUT_RESOURCE_NAME + inputCounter, anonV1pvd_false_false);
		EObject anonV2pvd_false_false = factory.createAnonV2ParameterValueDefintion(false, false);
		Resource anonV2res_false_false = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_false_false);
		input.addInputResources(anonV1res_false_false, anonV2res_false_false);
		
		++inputCounter;
		EObject anonV1pvd_false_true = factory.createAnonV1ParameterValueDefintion(false, true);
		Resource anonV1res_false_true = createParameterDefinitionResource(ANON_V1_INPUT_RESOURCE_NAME + inputCounter, anonV1pvd_false_true);
		EObject anonV2pvd_false_true = factory.createAnonV2ParameterValueDefintion(false, true);
		Resource anonV2res_false_true = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_false_true);
		input.addInputResources(anonV1res_false_true, anonV2res_false_true);
		
		++inputCounter;
		EObject anonV1pvd_true_false = factory.createAnonV1ParameterValueDefintion(true, false);
		Resource anonV1res_true_false = createParameterDefinitionResource(ANON_V1_INPUT_RESOURCE_NAME + inputCounter, anonV1pvd_true_false);
		EObject anonV2pvd_true_false = factory.createAnonV2ParameterValueDefintion(true, false);
		Resource anonV2res_true_false = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_true_false);
		input.addInputResources(anonV1res_true_false, anonV2res_true_false);
		
		++inputCounter;
		EObject anonV1pvd_true_true = factory.createAnonV1ParameterValueDefintion(true, true);
		Resource anonV1res_true_true = createParameterDefinitionResource(ANON_V1_INPUT_RESOURCE_NAME + inputCounter, anonV1pvd_true_true);
		EObject anonV2pvd_true_true = factory.createAnonV2ParameterValueDefintion(true, false);
		Resource anonV2res_true_true = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_true_true);
		input.addInputResources(anonV1res_true_true, anonV2res_true_true);
		
		return input;
	}
	
	private Input createAnonV1V2InputSingle() {
		Input input = new Input();
		
		ADAnonFactory factory = new ADAnonFactory(resourceSet);

		EObject anonV1pvd_false_false = factory.createAnonV1ParameterValueDefintion(false, false);
		Resource anonV1res_false_false = createParameterDefinitionResource("anonV1_input1", anonV1pvd_false_false);
		EObject anonV2pvd_false_false = factory.createAnonV2ParameterValueDefintion(false, false);
		Resource anonV2res_false_false = createParameterDefinitionResource("anonV2_input1", anonV2pvd_false_false);
		input.addInputResources(anonV1res_false_false, anonV2res_false_false);
				
		return input;
	}
	
	@Test
	public void anonV2V3TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADAnonFactory.ANON_V2, ADAnonFactory.ANON_V3);
		Input input = createAnonV2V3InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(8, semanticMatchResults.size());	   // exists found acc
		assertTrue(semanticMatchResults.get(0).matches()); // true true true
		assertTrue(semanticMatchResults.get(1).matches()); // true true false
		assertTrue(semanticMatchResults.get(2).matches()); // true false true
		assertTrue(semanticMatchResults.get(3).matches()); // true false false
		assertTrue(semanticMatchResults.get(4).matches()); // false true true
		assertTrue(semanticMatchResults.get(5).matches()); // false true false
		assertFalse(semanticMatchResults.get(6).matches()); // false false true
		assertFalse(semanticMatchResults.get(7).matches()); // false false false

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/anonCompany/ExampleB/anonV2V3trace.csv", semanticMatchResults);
	}
	
	@Test
	public void anonV2V3MatchPerformance_allInputs() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherAD(ADAnonFactory.ANON_V2, ADAnonFactory.ANON_V3);
			Input input = createAnonV2V3InputAll();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}
		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/ad/anonCompany/ExampleB/anonV2V3performance.csv");
	}

	private Input createAnonV2V3InputAll() {
		Input input = new Input();
		
		ADAnonFactory factory = new ADAnonFactory(resourceSet);

		int inputCounter = 0;
		
		++inputCounter;
		EObject anonV2pvd_true_true = factory.createAnonV2ParameterValueDefintion(true, true);
		Resource anonV2res_true_true = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_true_true);
		EObject anonV3pvd_true_true_true = factory.createAnonV3ParameterValueDefintion(true, true, true);
		Resource anonV3res_true_true_true = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_true_true_true);
		input.addInputResources(anonV2res_true_true, anonV3res_true_true_true);
		
		++inputCounter;
		EObject anonV2pvd_true_true_2 = factory.createAnonV2ParameterValueDefintion(true, true);
		Resource anonV2res_true_true_2 = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME + inputCounter, anonV2pvd_true_true_2);
		EObject anonV3pvd_true_true_false = factory.createAnonV3ParameterValueDefintion(true, true, false);
		Resource anonV3res_true_true_false = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_true_true_false);
		input.addInputResources(anonV2res_true_true_2, anonV3res_true_true_false);
		
		++inputCounter;
		EObject anonV2pvd_true_false = factory.createAnonV2ParameterValueDefintion(true, false);
		Resource anonV2res_true_false = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_true_false);
		EObject anonV3pvd_true_false_true = factory.createAnonV3ParameterValueDefintion(true, false, true);
		Resource anonV3res_true_false_true = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_true_false_true);
		input.addInputResources(anonV2res_true_false, anonV3res_true_false_true);
		
		++inputCounter;
		EObject anonV2pvd_true_false_2 = factory.createAnonV2ParameterValueDefintion(true, false);
		Resource anonV2res_true_false_2 = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME + inputCounter, anonV2pvd_true_false_2);
		EObject anonV3pvd_true_false_false = factory.createAnonV3ParameterValueDefintion(true, false, false);
		Resource anonV3res_true_false_false = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_true_false_false);
		input.addInputResources(anonV2res_true_false_2, anonV3res_true_false_false);
		
		++inputCounter;
		EObject anonV2pvd_false_true = factory.createAnonV2ParameterValueDefintion(false, true);
		Resource anonV2res_false_true = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_false_true);
		EObject anonV3pvd_false_true_true = factory.createAnonV3ParameterValueDefintion(false, true, true);
		Resource anonV3res_false_true_true = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_false_true_true);
		input.addInputResources(anonV2res_false_true, anonV3res_false_true_true);
		
		++inputCounter;
		EObject anonV2pvd_false_true_2 = factory.createAnonV2ParameterValueDefintion(false, true);
		Resource anonV2res_false_true_2 = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME + inputCounter, anonV2pvd_false_true_2);
		EObject anonV3pvd_false_true_false = factory.createAnonV3ParameterValueDefintion(false, true, false);
		Resource anonV3res_false_true_false = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_false_true_false);
		input.addInputResources(anonV2res_false_true_2, anonV3res_false_true_false);
		
		++inputCounter;
		EObject anonV2pvd_false_false = factory.createAnonV2ParameterValueDefintion(false, false);
		Resource anonV2res_false_false = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME +  inputCounter, anonV2pvd_false_false);
		EObject anonV3pvd_false_false_true = factory.createAnonV3ParameterValueDefintion(false, false, true);
		Resource anonV3res_false_false_true = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_false_false_true);
		input.addInputResources(anonV2res_false_false, anonV3res_false_false_true);
		
		++inputCounter;
		EObject anonV2pvd_false_false_2 = factory.createAnonV2ParameterValueDefintion(false, false);
		Resource anonV2res_false_false_2 = createParameterDefinitionResource(ANON_V2_INPUT_RESOURCE_NAME + inputCounter, anonV2pvd_false_false_2);
		EObject anonV3pvd_false_false_false = factory.createAnonV3ParameterValueDefintion(false, false, false);
		Resource anonV3res_false_false_false = createParameterDefinitionResource(ANON_V3_INPUT_RESOURCE_NAME + inputCounter, anonV3pvd_false_false_false);
		input.addInputResources(anonV2res_false_false_2, anonV3res_false_false_false);
		
		return input;
	}
	
}
