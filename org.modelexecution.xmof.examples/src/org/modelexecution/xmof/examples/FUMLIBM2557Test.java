package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

public class FUMLIBM2557Test extends FUMLTest {

	private static final String IBM_2557_MODEL_DIR = "IBM/";
	private static final String IBM_2557_MODEL_NAME = "2557";

	private String getModelPath(int version) {
		return FUML_MODEL_DIR + IBM_2557_MODEL_DIR + IBM_2557_MODEL_NAME + "-" + version + ".uml";
	}
	
	private String getParameterDefinitionPath(int version, int v2558) {
		return FUML_MODEL_DIR + IBM_2557_MODEL_DIR + IBM_2557_MODEL_NAME + "-" + version + "_parameter_" + v2558 + ".xmi";
	}

	@Test
	public void ibm_2557_1_3() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 3), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "addedAction1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction1", NodeType.ACTION, "addedAction2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction2", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "ac1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "ac1", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "1167", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3725", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3741", NodeType.ACTION));
	}
	
	@Test
	public void ibm_2557_1_2() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "addedAction1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction1", NodeType.ACTION, "addedAction2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction2", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "1167", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1167", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "ac1", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3725", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3741", NodeType.ACTION));
	}
	
	@Test
	public void ibm_2557_1_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "addedAction1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction1", NodeType.ACTION, "addedAction2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "addedAction2", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "3725", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "3725", NodeType.ACTION, "3741", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "3741", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "1167", NodeType.ACTION));
		assertTrue(notExecuted(trace, "ac1", NodeType.ACTION));
	}
	
	@Test
	public void ibm_2557_2_3() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(2, 3), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "ac1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "ac1", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "1167", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3725", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3741", NodeType.ACTION));
	}
	
	@Test
	public void ibm_2557_2_2() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(2, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "1167", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1167", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "ac1", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3725", NodeType.ACTION));
		assertTrue(notExecuted(trace, "3741", NodeType.ACTION));
	}
	
	@Test
	public void ibm_2557_2_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(2, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "2560", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2560", NodeType.ACTION, "2842", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2842", NodeType.ACTION, "2464", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2464", NodeType.ACTION, "2559", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2559", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "1374", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "1374", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "2286", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "2286", NodeType.ACTION, "v2558", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "v2558", NodeType.DECISION, "3725", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "3725", NodeType.ACTION, "3741", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "3741", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "1167", NodeType.ACTION));
		assertTrue(notExecuted(trace, "ac1", NodeType.ACTION));
	}
}
