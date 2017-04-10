package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

public class FUMLAnonExampleBTest extends FUMLTest{

	private static final String ANON_EXAMPLEB_MODEL_DIR = "anonCompany/ExampleB/";
	private static final String ANON_EXAMPLEB_MODEL_NAME = "ExampleB";
	
	private String getModelPath(int version) {
		return FUML_MODEL_DIR + ANON_EXAMPLEB_MODEL_DIR
				+ ANON_EXAMPLEB_MODEL_NAME + "V" + version + ".uml";
	}

	private String getParameterDefinitionPath(int version, boolean exists,
			boolean found, boolean acc) {
		String parameterDefinitionPath = FUML_MODEL_DIR
				+ ANON_EXAMPLEB_MODEL_DIR + ANON_EXAMPLEB_MODEL_NAME + "V"
				+ version + "_parameter_" + exists + "_" + found;
		if (version == 3)
			parameterDefinitionPath += "_" + acc;
		parameterDefinitionPath += ".xmi";
		return parameterDefinitionPath;
	}
	
	@Test
	public void anonCompany_ExampleB_V1_false_false() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, false, false, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont ext", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont ext", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "merge do req", NodeType.MERGE));
		assertTrue(notExecuted(trace, "do req", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V1_false_true() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, false, true, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "acc", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_false() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, true, false, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "search", NodeType.ACTION));
		assertTrue(notExecuted(trace, "found", NodeType.DECISION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_true() {
		Trace trace = execute(getModelPath(1),
				getParameterDefinitionPath(1, true, true, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "search", NodeType.ACTION));
		assertTrue(notExecuted(trace, "found", NodeType.DECISION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_false() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, false, false, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont ext", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont ext", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "ord", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "merge do req", NodeType.MERGE));
		assertTrue(notExecuted(trace, "do req", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_true() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, false, true, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "ord", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_false() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, true, false, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "ord", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "ord", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "search", NodeType.ACTION));
		assertTrue(notExecuted(trace, "found", NodeType.DECISION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_true() {
		Trace trace = execute(getModelPath(2),
				getParameterDefinitionPath(2, true, true, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "ord", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "ord", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "search", NodeType.ACTION));
		assertTrue(notExecuted(trace, "found", NodeType.DECISION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_false_false() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, false, false, false), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont ext", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont ext", NodeType.ACTION, "acc", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.DECISION, "val", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "val", NodeType.ACTION, "no acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "no acc", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
		
		assertTrue(notExecuted(trace, "ord", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_false_true() {
		Trace trace = execute(getModelPath(3),
				getParameterDefinitionPath(3, false, false, true), true);
		
		assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont ext", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "cont ext", NodeType.ACTION, "acc", NodeType.DECISION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.DECISION, "acc", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "acc", NodeType.ACTION, "merge do req", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
		assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
		assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));

		assertTrue(notExecuted(trace, "val", NodeType.ACTION));
		assertTrue(notExecuted(trace, "no acc", NodeType.ACTION));
		assertTrue(notExecuted(trace, "ord", NodeType.ACTION));
		assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_true() {
		Set<Trace> traces = new HashSet<Trace>();
		String modelPath = getModelPath(3);
		
		traces.add(execute(modelPath, getParameterDefinitionPath(3, false, true, true), true));
		reset();
		traces.add(execute(modelPath, getParameterDefinitionPath(3, false, true, false), true));
		
		
		for(Iterator<Trace> iterator = traces.iterator(); iterator.hasNext();) {
			Trace trace = iterator.next();
			assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
			assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "search", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "search", NodeType.ACTION, "found", NodeType.DECISION));
			assertTrue(isExecutedBefore(trace, "found", NodeType.DECISION, "cont", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "cont", NodeType.ACTION, "merge do req", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
			
			assertTrue(notExecuted(trace, "ord", NodeType.ACTION));
			assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
		}
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_true() {
		Set<Trace> traces = new HashSet<Trace>();
		String modelPath = getModelPath(3);
		
		traces.add(execute(modelPath, getParameterDefinitionPath(3, true, true, true), true));
		reset();
		traces.add(execute(modelPath, getParameterDefinitionPath(3, true, true, false), true));
		
		for(Iterator<Trace> iterator = traces.iterator(); iterator.hasNext();) {
			Trace trace = iterator.next();
			assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
			assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "ord", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "ord", NodeType.ACTION, "merge do req", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
			
			assertTrue(notExecuted(trace, "search", NodeType.ACTION));
			assertTrue(notExecuted(trace, "found", NodeType.DECISION));
			assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
			assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
		}
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_false() {
		Set<Trace> traces = new HashSet<Trace>();
		String modelPath = getModelPath(3);
		
		traces.add(execute(modelPath, getParameterDefinitionPath(3, true, false, true), true));
		reset();
		traces.add(execute(modelPath, getParameterDefinitionPath(3, true, false, false), true));
		
		for(Iterator<Trace> iterator = traces.iterator(); iterator.hasNext();) {
			Trace trace = iterator.next();
			assertTrue(isExecutedBefore(trace, "initial", NodeType.INITIAL, "req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "req", NodeType.ACTION, "n code", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "n code", NodeType.ACTION, "def", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "def", NodeType.ACTION, "cont int", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "cont int", NodeType.ACTION, "exists", NodeType.DECISION));
			assertTrue(isExecutedBefore(trace, "exists", NodeType.DECISION, "ord", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "ord", NodeType.ACTION, "merge do req", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge do req", NodeType.MERGE, "do req", NodeType.ACTION));
			assertTrue(isExecutedBefore(trace, "do req", NodeType.ACTION, "merge final", NodeType.MERGE));
			assertTrue(isExecutedBefore(trace, "merge final", NodeType.MERGE, "final", NodeType.FINAL));
			
			assertTrue(notExecuted(trace, "search", NodeType.ACTION));
			assertTrue(notExecuted(trace, "found", NodeType.DECISION));
			assertTrue(notExecuted(trace, "cont", NodeType.ACTION));
			assertTrue(notExecuted(trace, "cont ext", NodeType.ACTION));
		}
	}
}
