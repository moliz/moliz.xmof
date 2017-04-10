package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Test;
import org.modelexecution.xmof.diff.XMOFMatcher;

public class PetriNetTest extends Evaluation {

	@Test
	public void testPetriNet_PN1PN2_markingEquivalence() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn2.xmi");
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}
	
	@Test
	public void testPetriNet_PN1PN2_finalMarking() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1FinalMarking(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn2.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}

}
