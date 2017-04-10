package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

public class FUMLNokiaExampleATest extends FUMLTest{
 
	private static final String NOKIA_EXAMPLEA_MODEL_DIR = "Nokia/ExampleA/";
	private static final String NOKIA_EXAMPLEA_MODEL_NAME = "ExampleA";

	private String getModelPath(int version) {
		return FUML_MODEL_DIR + NOKIA_EXAMPLEA_MODEL_DIR + NOKIA_EXAMPLEA_MODEL_NAME + "V" + version + ".uml";
	}
	
	private String getParameterDefinitionPath(int version, int f, int d) {
		return FUML_MODEL_DIR + NOKIA_EXAMPLEA_MODEL_DIR + NOKIA_EXAMPLEA_MODEL_NAME + "V" + version + "_parameter_" + f + "_" + d + ".xmi";
	}
	
	@Test
	public void nokia_ExampleA_V1_1_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V1_1_2() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V1_2_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 2, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V1_2_2() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, 2, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V2_1_1() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(2, 1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V2_1_2() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, 1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V2_2_1() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, 2, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V2_2_2() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, 2, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V3_1_1() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, 1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V3_1_2() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, 1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V3_2_1() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, 2, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V3_2_2() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, 2, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V4_1_1() {
		Trace trace = execute(getModelPath(4),
				getParameterDefinitionPath(4, 1, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V4_1_2() {
		Trace trace = execute(getModelPath(4),
				getParameterDefinitionPath(4, 1, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "n form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n form", NodeType.ACTION, "create", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "create", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "t form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "attach", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V4_2_1() {
		Trace trace = execute(getModelPath(4),
				getParameterDefinitionPath(4, 2, 1), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "dec", NodeType.ACTION));
	}
	
	@Test
	public void nokia_ExampleA_V4_2_2() {
		Trace trace = execute(getModelPath(4),
				getParameterDefinitionPath(4, 2, 2), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "set 1", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 1", NodeType.ACTION, "f", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "f", NodeType.DECISION, "t form", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t form", NodeType.ACTION, "attach", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "attach", NodeType.ACTION, "merge f", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge f", NodeType.MERGE, "set 2", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "set 2", NodeType.ACTION, "exe", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "exe", NodeType.ACTION, "fork", NodeType.FORK));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "t insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "fork", NodeType.FORK, "m insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "o insp", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "t insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "o insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "m insp", NodeType.ACTION, "join", NodeType.JOIN));
		assertTrue(isExecutedBefore(trace, "join", NodeType.JOIN, "rep", NodeType.ACTION));		
		assertTrue(isExecutedBefore(trace, "rep", NodeType.ACTION, "d", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "d", NodeType.DECISION, "dec", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "dec", NodeType.ACTION, "merge d", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge d", NodeType.MERGE, "rectify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "rectify", NodeType.ACTION, "notify", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "notify", NodeType.ACTION, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "n form", NodeType.ACTION));
		assertTrue(notExecuted(trace, "create", NodeType.ACTION));
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
	}
}
