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
import org.modelexecution.xmof.diff.test.internal.factory.ADIBM3561Factory;
import org.modelexecution.xmof.diff.test.internal.factory.Input;

/**
 * @author Tanja
 * 
 */
public class IBM3561ADEvaluation extends ADEvaluation {

	protected static final String IBM3561_V1_INPUT_RESOURCE_NAME = "IBM3561V1_input";
	protected static final String IBM3561_V2_INPUT_RESOURCE_NAME = "IBM3561V2_input";
	
	@Test
	public void IBM3561V1V2TestMatchResult_allInputs() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(ADIBM3561Factory.IBM3561_V1, ADIBM3561Factory.IBM3561_V2);
		Input input = createIBM3561V1V2InputAll();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		
		List<XMOFSemanticMatchResult> semanticMatchResults = matcher.getSemanticMatchResults();
		assertEquals(6, semanticMatchResults.size());	   // i
		assertFalse(semanticMatchResults.get(0).matches()); // 1
		assertTrue(semanticMatchResults.get(1).matches()); // 2
		assertTrue(semanticMatchResults.get(2).matches()); // 3
		assertTrue(semanticMatchResults.get(3).matches()); // 4
		assertTrue(semanticMatchResults.get(4).matches()); // 5
		assertTrue(semanticMatchResults.get(5).matches()); // 6

		assertEquals(60, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(64, TraceUtil.getActivityExecutions(semanticMatchResults.get(0).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(7, TraceUtil.getActivityExecutions(semanticMatchResults.get(1).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(2).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(2).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(3).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(3).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(4).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(4).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(5).getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());
		assertEquals(6, TraceUtil.getActivityExecutions(semanticMatchResults.get(5).getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile("report/ad/IBM/V1V2trace.csv", semanticMatchResults);
		report.printReportToFile("report/ad/IBM/V1V2performance-1execution.csv");
	}

	private Input createIBM3561V1V2InputAll() {
		Input input = new Input();
		
		ADIBM3561Factory factory = new ADIBM3561Factory(resourceSet);
		
		for(int i=1;i<=6;++i) {
			EObject ibm3561V1pvd = factory.createIBM3561V1ParameterValueDefintion(i);
			Resource ibm3561V1res = createParameterDefinitionResource(IBM3561_V1_INPUT_RESOURCE_NAME + i, ibm3561V1pvd);
			EObject ibm3561V2pvd = factory.createIBM3561V2ParameterValueDefintion(i);
			Resource ibm3561V2res = createParameterDefinitionResource(IBM3561_V2_INPUT_RESOURCE_NAME +  i, ibm3561V2pvd);
			input.addInputResources(ibm3561V1res, ibm3561V2res);
		}
		
		return input;
	}
	
	@SuppressWarnings("unused")
	private Input createIBM3561V1V2InputEquivalencePartitioning() {
		Input input = new Input();
		
		ADIBM3561Factory factory = new ADIBM3561Factory(resourceSet);
		
		for(int i=1;i<=3;++i) {
			EObject ibm3561V1pvd = factory.createIBM3561V1ParameterValueDefintion(i);
			Resource ibm3561V1res = createParameterDefinitionResource(IBM3561_V1_INPUT_RESOURCE_NAME + i, ibm3561V1pvd);
			EObject ibm3561V2pvd = factory.createIBM3561V2ParameterValueDefintion(i);
			Resource ibm3561V2res = createParameterDefinitionResource(IBM3561_V2_INPUT_RESOURCE_NAME +  i, ibm3561V2pvd);
			input.addInputResources(ibm3561V1res, ibm3561V2res);
		}
		
		return input;
	}
}
