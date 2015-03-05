package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

public class FUMLIBM3561Test extends FUMLTest {

	private static final String IBM_3561_MODEL_DIR = "IBM/";
	private static final String IBM_3561_MODEL_NAME = "3561";

	private String getModelPath(int version) {
		return FUML_MODEL_DIR + IBM_3561_MODEL_DIR + IBM_3561_MODEL_NAME + "-" + version + ".uml";
	}
	
	private String getParameterDefinitionPath(int version, int i) {
		return FUML_MODEL_DIR + IBM_3561_MODEL_DIR + IBM_3561_MODEL_NAME + "-" + version + "_parameter_" + i + ".xmi";
	}

	@Test
	public void ibm_3561_1_3() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 3), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "a", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "a", NodeType.ACTION, "b", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "b", NodeType.ACTION, "c", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "c", NodeType.ACTION, "d", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.ACTION, "merge e", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge e", NodeType.MERGE, "e", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "e", NodeType.ACTION, "i", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "i", NodeType.DECISION, "f", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));

		assertTrue(notExecuted(trace, "g", NodeType.ACTION));
		assertTrue(notExecuted(trace, "h", NodeType.ACTION));
		assertTrue(notExecuted(trace, "i", NodeType.ACTION));
		assertTrue(notExecuted(trace, "j", NodeType.ACTION));
		assertTrue(notExecuted(trace, "k", NodeType.ACTION));
		assertTrue(notExecuted(trace, "l", NodeType.ACTION));
		assertTrue(notExecuted(trace, "loop", NodeType.DECISION));
	}
	
	@Test
	public void ibm_3561_1_2() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "a", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "a", NodeType.ACTION, "b", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "b", NodeType.ACTION, "c", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "c", NodeType.ACTION, "d", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.ACTION, "merge e", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge e", NodeType.MERGE, "e", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "e", NodeType.ACTION, "i", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "i", NodeType.DECISION, "g", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "g", NodeType.ACTION, "h", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "h", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));

		assertTrue(notExecuted(trace, "f", NodeType.ACTION));
		assertTrue(notExecuted(trace, "i", NodeType.ACTION));
		assertTrue(notExecuted(trace, "j", NodeType.ACTION));
		assertTrue(notExecuted(trace, "k", NodeType.ACTION));
		assertTrue(notExecuted(trace, "l", NodeType.ACTION));
		assertTrue(notExecuted(trace, "loop", NodeType.DECISION));
	}
	
	@Test
	public void ibm_3561_1_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "a", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "a", NodeType.ACTION, "b", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "b", NodeType.ACTION, "c", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "c", NodeType.ACTION, "d", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.ACTION, "merge e", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge e", NodeType.MERGE, "e", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "e", NodeType.ACTION, "i", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "k", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertEquals(5, getActivityExecutionsForOpaqueActionExecution(trace, "e").size());
		assertEquals(5, getActivityExecutionsForOpaqueActionExecution(trace, "i").size());
		assertEquals(5, getActivityExecutionsForOpaqueActionExecution(trace, "j").size());
		assertEquals(4, getActivityExecutionsForOpaqueActionExecution(trace, "l").size());

		assertTrue(notExecuted(trace, "f", NodeType.ACTION));
		assertTrue(notExecuted(trace, "g", NodeType.ACTION));
		assertTrue(notExecuted(trace, "h", NodeType.ACTION));
	}
}
