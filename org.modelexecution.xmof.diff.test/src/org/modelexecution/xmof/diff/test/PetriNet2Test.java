package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.test.internal.factory.Input;
import org.modelexecution.xmof.vm.util.EMFUtil;

public class PetriNet2Test extends Evaluation {

	private static final String PETRINET_1_INPUT_P1 = "model/petrinet2/petrinet1_parameter_p1.xmi";
	private static final String PETRINET_2_INPUT_P1 = "model/petrinet2/petrinet2_parameter_p1.xmi";
	
	private static final String PETRINET_1_INPUT_P2 = "model/petrinet2/petrinet1_parameter_p2.xmi";
	private static final String PETRINET_2_INPUT_P2 = "model/petrinet2/petrinet2_parameter_p2.xmi";
	
	private static final String PETRINET_1_INPUT_P2P3 = "model/petrinet2/petrinet1_parameter_p2p3.xmi";
	private static final String PETRINET_2_INPUT_P2P3 = "model/petrinet2/petrinet2_parameter_p2p3.xmi";
	
	@Test
	public void testPetriNet_PN1PN2_markingEquivalence_input_p1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet2(
				"model/petrinet2/petrinet1.petrinet2", "model/petrinet2/petrinet2.petrinet2");
		Input input = createPetrinet12Input_p1();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}
	
	@Test
	public void testPetriNet_PN1PN2_finalMarking_input_p1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet2FinalMarking(
				"model/petrinet2/petrinet1.petrinet2", "model/petrinet2/petrinet2.petrinet2");
		Input input = createPetrinet12Input_p1();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}
	
	@Test
	public void testPetriNet_PN1PN2_finalMarking_input_p2() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet2FinalMarking(
				"model/petrinet2/petrinet1.petrinet2", "model/petrinet2/petrinet2.petrinet2");
		Input input = createPetrinet12Input_p2();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testPetriNet_PN1PN2_finalMarking_fUMLTrace_input_p1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet2FinalMarking(
				"model/petrinet2/petrinet1.petrinet2", "model/petrinet2/petrinet2.petrinet2");
		Input input = createPetrinet12Input_p1();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
		XMOFSemanticMatchResult xmofSemanticMatchResult = matcher.getSemanticMatchResults().get(0);
		Trace fumlTraceLeft = xmofSemanticMatchResult.getStateSystemLeft().getTrace();
		Trace fumlTraceRight = xmofSemanticMatchResult.getStateSystemRight().getTrace();
		PetriNet2FumlTraceMatcher traceMatcher = new PetriNet2FumlTraceMatcher();		
		assertTrue(traceMatcher.matchTrace(fumlTraceLeft, fumlTraceRight));
	}
	
	@Test
	public void testPetriNet_PN1PN2_finalMarking_fUMLTrace_input_p2p3() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet2FinalMarking(
				"model/petrinet2/petrinet1.petrinet2", "model/petrinet2/petrinet2.petrinet2");
		Input input = createPetrinet12Input_p2p3();
		addInputToXMOFMatcherContext(matcher.getXMOFMatcherContext(), input);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
		XMOFSemanticMatchResult xmofSemanticMatchResult = matcher.getSemanticMatchResults().get(0);
		Trace fumlTraceLeft = xmofSemanticMatchResult.getStateSystemLeft().getTrace();
		Trace fumlTraceRight = xmofSemanticMatchResult.getStateSystemRight().getTrace();
		PetriNet2FumlTraceMatcher traceMatcher = new PetriNet2FumlTraceMatcher();
		assertFalse(traceMatcher.matchTrace(fumlTraceLeft, fumlTraceRight));
	}

	private Input createPetrinet12Input_p1() {
		Input input = new Input();
		Resource petrinet1_input1 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_1_INPUT_P1));
		Resource petrinet2_input1 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_2_INPUT_P1));
		input.addInputResources(petrinet1_input1, petrinet2_input1);
		return input;
	}
	
	private Input createPetrinet12Input_p2() {
		Input input = new Input();
		Resource petrinet1_input2 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_1_INPUT_P2));
		Resource petrinet2_input2 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_2_INPUT_P2));
		input.addInputResources(petrinet1_input2, petrinet2_input2);
		return input;
	}

	private Input createPetrinet12Input_p2p3() {
		Input input = new Input();
		Resource petrinet1_input2 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_1_INPUT_P2P3));
		Resource petrinet2_input2 = EMFUtil.loadResource(resourceSet, EMFUtil
				.createFileURI(PETRINET_2_INPUT_P2P3));
		input.addInputResources(petrinet1_input2, petrinet2_input2);
		return input;
	}

}
