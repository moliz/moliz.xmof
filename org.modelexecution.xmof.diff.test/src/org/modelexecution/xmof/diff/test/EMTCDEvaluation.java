package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Test;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.performance.TimeMeasurement;
import org.modelexecution.xmof.diff.test.internal.Report;
import org.modelexecution.xmof.diff.test.internal.TraceUtil;
import org.modelexecution.xmof.diff.test.internal.factory.Input;
import org.modelexecution.xmof.vm.util.EMFUtil;

public class EMTCDEvaluation extends Evaluation {

	private static final String EMT_V1 = "model/cd/EMT/EMTv1.xmi";
	private static final String EMT_V2 = "model/cd/EMT/EMTv2.xmi";
	
	private static final String EMT_V1_WITNESS1 = "model/cd/EMT/EMTv1witness1parameter.xmi";
	private static final String EMT_V2_WITNESS1 = "model/cd/EMT/EMTv2witness1parameter.xmi";
	private static final String EMT_V1_WITNESS2 = "model/cd/EMT/EMTv1witness2parameter.xmi";
	private static final String EMT_V2_WITNESS2 = "model/cd/EMT/EMTv2witness2parameter.xmi";
	private static final String EMT_V1_WITNESS3 = "model/cd/EMT/EMTv1witness3parameter.xmi";
	private static final String EMT_V2_WITNESS3 = "model/cd/EMT/EMTv2witness3parameter.xmi";
	
	private static final String[] VALIDATE = new String[] {
			"classesConfiguration.PropertyConfiguration.validate_property", 
			"classesConfiguration.PropertyConfiguration.validateValue_property",
			"classesConfiguration.AssociationConfiguration.validate_association", 
			"classesConfiguration.AssociationConfiguration.validateLink" };

	@Test
	public void emtV1V2TestMatchResult_witness1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
		Input input = createEMTV1V2Input_witness1();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());

		List<XMOFSemanticMatchResult> semanticMatchResults = matcher
				.getSemanticMatchResults();
		assertEquals(1, semanticMatchResults.size());
		assertFalse(semanticMatchResults.get(0).matches());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile(
				"report/cd/EMT/emtV1V2_witness1_trace.csv",
				semanticMatchResults);
	}
	
	@Test
	public void emtV1V2TestMatchPerformance_witness1() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
			Input input = createEMTV1V2Input_witness1();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/cd/EMT/emtV1V2_witness1_performance.csv");
	}
	
	@Test
	public void emtV1V2TestMatchResult_witness2() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
		Input input = createEMTV1V2Input_witness2();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());

		List<XMOFSemanticMatchResult> semanticMatchResults = matcher
				.getSemanticMatchResults();
		assertEquals(1, semanticMatchResults.size());
		assertFalse(semanticMatchResults.get(0).matches());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile(
				"report/cd/EMT/emtV1V2_witness2_trace.csv",
				semanticMatchResults);
	}
	
	@Test
	public void emtV1V2TestMatchPerformance_witness2() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
			Input input = createEMTV1V2Input_witness2();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/cd/EMT/emtV1V2_witness2_performance.csv");
	}
	
	@Test
	public void emtV1V2TestMatchResult_witness3() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
		Input input = createEMTV1V2Input_witness3();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());

		List<XMOFSemanticMatchResult> semanticMatchResults = matcher
				.getSemanticMatchResults();
		assertEquals(1, semanticMatchResults.size());
		assertFalse(semanticMatchResults.get(0).matches());

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		printTraceInformationToConsole(semanticMatchResults);
		printTraceInformationToFile(
				"report/cd/EMT/emtV1V2_witness3_trace.csv",
				semanticMatchResults);
	}
	
	@Test
	public void emtV1V2TestMatchPerformance_witness3() throws EolRuntimeException {
		for(int i=0;i<20;++i) {
			setup();
			XMOFMatcher matcher = prepareXMOFMatcherCD(EMT_V1, EMT_V2);
			Input input = createEMTV1V2Input_witness3();
			addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
			matcher.match();
		}

		Report report = new Report(TimeMeasurement.INSTANCE);
		report.printReportToConsole();
		report.printReportToFile("report/cd/EMT/emtV1V2_witness3_performance.csv");
	}
		
	private Input createEMTV1V2Input_witness1() {
		Input input = new Input();
		Resource emtV1_witness1 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V1_WITNESS1));
		Resource emtV2_witness1 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V2_WITNESS1));
		input.addInputResources(emtV1_witness1, emtV2_witness1);
		return input;
	}
	
	private Input createEMTV1V2Input_witness2() {
		Input input = new Input();
		Resource emtV1_witness2 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V1_WITNESS2));
		Resource emtV2_witness1 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V2_WITNESS2));
		input.addInputResources(emtV1_witness2, emtV2_witness1);
		return input;
	}
	
	private Input createEMTV1V2Input_witness3() {
		Input input = new Input();
		Resource emtV1_performance4 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V1_WITNESS3));
		Resource emtV2_performance4 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(EMT_V2_WITNESS3));
		input.addInputResources(emtV1_performance4, emtV2_performance4);
		return input;
	}

	public void printTraceInformationToFile(String filepath,
			List<XMOFSemanticMatchResult> semanticMatchResults) {
		File file = new File(filepath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(printTraceInformation(semanticMatchResults));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printTraceInformationToConsole(
			List<XMOFSemanticMatchResult> semanticMatchResults) {
		System.out.println(printTraceInformation(semanticMatchResults));
	}

	public String printTraceInformation(
			List<XMOFSemanticMatchResult> semanticMatchResults) {
		StringBuffer str = new StringBuffer();
		str.append("input;#states left;#states right;#activity executions left;#activity executions right");
		for (int i = 0; i < VALIDATE.length;++i) {
			str.append(";" + VALIDATE[i] + " left");
		}
		for (int i = 0; i < VALIDATE.length;++i) {
			str.append(";" + VALIDATE[i] + " right");
		}
		
		for (int i = 0; i < semanticMatchResults.size(); ++i) {
			str.append(System.getProperty("line.separator"));
			XMOFSemanticMatchResult semanticMatchResult = semanticMatchResults
					.get(i);
			str.append((i + 1) + ";");
			str.append(semanticMatchResult.getStateSystemLeft().getStates().size()+ ";");
			str.append(semanticMatchResult.getStateSystemRight().getStates().size()	+ ";");
			str.append(semanticMatchResult.getStateSystemLeft().getTrace().getActivityExecutions().size() + ";");
			str.append(semanticMatchResult.getStateSystemRight().getTrace().getActivityExecutions().size());
			for (int j = 0; j < VALIDATE.length; ++j) {
				str.append(";" + TraceUtil.getActivityExecutions(semanticMatchResult.getStateSystemLeft().getTrace(), VALIDATE[j]).size());
			}
			for (int j = 0; j < VALIDATE.length; ++j) {
				str.append(";" + TraceUtil.getActivityExecutions(semanticMatchResult.getStateSystemRight().getTrace(), VALIDATE[j]).size());
			}			
		}
		return str.toString();
	}
}
