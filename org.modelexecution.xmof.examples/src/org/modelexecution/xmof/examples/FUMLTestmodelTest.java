package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Loci.LociL1.Locus;

public class FUMLTestmodelTest extends FUMLTest {

	private static final String FUML_EXEENV_FILENAME = "executionenvironment.xmi";

	private static final String EXECUTION_ENVIRONMENT = "ExecutionEnvironment";
	private static final String FUNCTION_BEHAVIOR = "FunctionBehavior";
	private static final String INTEGER_PLUS_FUNCTION_BEHAVIOR_EXECUTION = "IntegerPlusFunctionBehaviorExecution";

	private static final String MODEL_PATH = FUML_MODEL_DIR + "testmodel.uml";
	
	private String getParameterDefinitionPath(int modelNumber) {
		return FUML_MODEL_DIR + "test" + modelNumber + "parameter.xmi";
	}
	
	@Test
	public void test1_setupOfExecutionEnvironment() {
		setupVM(MODEL_PATH, getParameterDefinitionPath(1));

		// check presence of ExecutionEnvironment instance
		EObject executionEnvironmentObject = getResourceByFileName(
				FUML_EXEENV_FILENAME).getContents().get(0);
		assertNotNull(executionEnvironmentObject);
		assertEquals(EXECUTION_ENVIRONMENT, executionEnvironmentObject.eClass()
				.getName());
		assertTrue(existsModelElementAtLocus(executionEnvironmentObject));

		// check presence of OpaqueBehaviorExecution instance for primitive
		// behavior and link to corresponding FunctionBehavior
		EObject integerPlusFunctionBehavior = getResourceByFileName(
				FUML_BEHAVIOR_LIBRARY_FILENAME).getContents().get(0)
				.eContents().get(0).eContents().get(0);
		assertNotNull(integerPlusFunctionBehavior);
		assertEquals(FUNCTION_BEHAVIOR, integerPlusFunctionBehavior.eClass()
				.getName());
		assertTrue(existsModelElementAtLocus(integerPlusFunctionBehavior));

		EObject integerPlusFunctionBehaviorExecution = null;
		TreeIterator<EObject> eAllContents = executionEnvironmentObject
				.eAllContents();
		while (eAllContents.hasNext()) {
			EObject next = eAllContents.next();
			if (next.eClass().getName()
					.equals(INTEGER_PLUS_FUNCTION_BEHAVIOR_EXECUTION))
				integerPlusFunctionBehaviorExecution = next;
		}
		assertNotNull(integerPlusFunctionBehaviorExecution);
		assertTrue(existsModelElementAtLocus(integerPlusFunctionBehaviorExecution));

		Object_ integerPlusFunctionBehaviorFUMLObject = getFUMLObjectFromModelElement(integerPlusFunctionBehavior);
		Object_ integerPlusFunctionBehaviorExecutionFUMLObject = getFUMLObjectFromModelElement(integerPlusFunctionBehaviorExecution);
		Link typeLink = null;
		Locus locus = getLocus();
		for (ExtensionalValue extensionalValue : locus.extensionalValues) {
			if (extensionalValue instanceof Link) {
				Link link = (Link) extensionalValue;
				if (link.type.name.equals("types")) {
					List<Object_> linkedObjects = getLinkedObjects(link);
					Object_ linkedObject0 = linkedObjects.get(0);
					Object_ linkedObject1 = linkedObjects.get(1);
					if ((linkedObject0 == integerPlusFunctionBehaviorFUMLObject && linkedObject1 == integerPlusFunctionBehaviorExecutionFUMLObject)
							|| (linkedObject1 == integerPlusFunctionBehaviorFUMLObject && linkedObject0 == integerPlusFunctionBehaviorExecutionFUMLObject)) {
						typeLink = link;
					}
				}
			}
		}
		assertNotNull(typeLink);
		cleanup();
	}

	@Test
	public void test1_activityExecution() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(1), true);
		assertNotNull(getActivityExecution(trace, MAIN));
		assertNotNull(getActivityExecution(trace, "execute_Executor"));

		ActivityExecution createExecution = getActivityExecution(trace,
				"createExecution_ExecutionFactory");
		assertNotNull(createExecution);
		assertEquals(1, createExecution.getActivityOutputs().get(0)
				.getParameterValues().size());
		assertNotNull(createExecution.getActivityOutputs().get(0)
				.getParameterValues().get(0).getValueSnapshot());

		assertNotNull(getActivityExecution(trace, "execute_ActivityExecution"));
		assertNotNull(getActivityExecution(trace,
				"activate_ActivityNodeActivationGroup"));
		assertNotNull(getActivityExecution(trace,
				"createNodeActivations_ActivityNodeActivationGroup"));
		assertNotNull(getActivityExecution(trace,
				"createEdgeInstances_ActivityNodeActivationGroup"));
		assertNotNull(getActivityExecution(trace,
				"run_ActivityNodeActivationGroup"));
		assertNotNull(getActivityExecution(trace,
				"runNodes_ActivityNodeActivationGroup"));
		assertNotNull(getActivityExecution(trace,
				ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS));
	}

	@Test
	public void test2_opaqueActionExecution() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(2), true);
		Set<ActivityExecution> activityExecutions_doAction = getActivityExecutions(
				trace, OPAQUE_ACTION_DO_ACTION);
		assertEquals(3, activityExecutions_doAction.size());

		ActivityExecution opaqueAction1Execution = getActivityExecutionForActionExecution(
				trace, "OpaqueAction1");
		ActivityExecution opaqueAction2Execution = getActivityExecutionForActionExecution(
				trace, "OpaqueAction2");
		ActivityExecution opaqueAction3Execution = getActivityExecutionForActionExecution(
				trace, "OpaqueAction3");

		assertTrue(opaqueAction2Execution
				.isChronologicalSuccessorOf(opaqueAction1Execution));
		assertTrue(opaqueAction3Execution
				.isChronologicalSuccessorOf(opaqueAction2Execution));

		// TODO assertTrue(checkInitiallyEnabledNodes(trace, "OpaqueAction1"));
	}

	@Test
	public void test3_parameters() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(3), true);
		ActivityExecution activityExecution_main = getActivityExecution(trace,
				MAIN);
		assertNotNull(activityExecution_main);

		IntegerValue outputvalue1 = new IntegerValue();
		outputvalue1.value = 1;
		assertTrue(checkActivityModelOutput(trace, "test3_output1",
				outputvalue1));

		IntegerValue outputvalue2 = new IntegerValue();
		outputvalue2.value = 2;
		assertTrue(checkActivityModelOutput(trace, "test3_output2",
				outputvalue2));
	}

	@Test
	public void test4_forkJoin() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(4), true);
		Set<ActivityExecution> activityExecutions_doAction = getActivityExecutions(
				trace, OPAQUE_ACTION_DO_ACTION);
		assertEquals(4, activityExecutions_doAction.size());

		ActivityExecution opaqueAction1Execution = getActivityExecutionForActionExecution(
				trace, "action1");
		ActivityExecution opaqueAction2Execution = getActivityExecutionForActionExecution(
				trace, "action2");
		ActivityExecution opaqueAction3Execution = getActivityExecutionForActionExecution(
				trace, "action3");
		ActivityExecution opaqueAction4Execution = getActivityExecutionForActionExecution(
				trace, "action4");
		ActivityExecution forkNodeExecution = getActivityExecutionForForkNodeExecution(
				trace, "forkNode");
		ActivityExecution joinNodeExecution = getActivityExecutionForJoinNodeExecution(
				trace, "joinNode");

		assertTrue(forkNodeExecution
				.isChronologicalSuccessorOf(opaqueAction1Execution));
		assertTrue(opaqueAction2Execution
				.isChronologicalSuccessorOf(forkNodeExecution));
		assertTrue(opaqueAction3Execution
				.isChronologicalSuccessorOf(forkNodeExecution));
		assertTrue(joinNodeExecution
				.isChronologicalSuccessorOf(opaqueAction2Execution));
		assertTrue(joinNodeExecution
				.isChronologicalSuccessorOf(opaqueAction3Execution));
		assertTrue(opaqueAction4Execution
				.isChronologicalSuccessorOf(joinNodeExecution));

		// TODO assertTrue(checkInitiallyEnabledNodes(trace, "action1"));
	}

	@Test
	public void test5_initialFinal() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(5), true);

		ActivityExecution opaqueAction1Execution = getActivityExecutionForActionExecution(
				trace, "action1");
		ActivityExecution initialNodeExecution = getActivityExecutionForInitialNodeExecution(
				trace, "initialNode");
		ActivityExecution activityFinalNodeExecution = getActivityExecutionForActivityFinalNodeExecution(
				trace, "activityFinalNode");

		assertTrue(opaqueAction1Execution
				.isChronologicalSuccessorOf(initialNodeExecution));
		assertTrue(activityFinalNodeExecution
				.isChronologicalSuccessorOf(initialNodeExecution));

		// TODO assertTrue(checkInitiallyEnabledNodes(trace, "initialNode"));
	}

	@Test
	public void test6_valueSpecificationAction() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(6), true);

		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify 19");
		assertNotNull(valueSpecificationActionExecution);

		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 19;
		assertTrue(checkActivityModelOutput(trace, "test6_output", outputvalue));
	}

	@Test
	public void test7_callBehaviorAction() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(7), true);

		ActivityExecution callBehaviorActionExecution = getActivityExecutionForActionExecution(
				trace, "call test6");
		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify 19");
		assertNotNull(callBehaviorActionExecution);
		assertNotNull(valueSpecificationActionExecution);

		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 19;
		assertTrue(checkActivityModelOutput(trace, "test7_output", outputvalue));
	}

	@Test
	public void test8_plus() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(8), true);

		ActivityExecution valueSpecificationAction1Execution = getActivityExecutionForActionExecution(
				trace, "specify 1");
		ActivityExecution valueSpecificationAction2Execution = getActivityExecutionForActionExecution(
				trace, "specify 2");
		ActivityExecution callBehaviorActionExecution = getActivityExecutionForActionExecution(
				trace, "call IntegerPlus");

		assertTrue(callBehaviorActionExecution
				.isChronologicalSuccessorOf(valueSpecificationAction1Execution));
		assertTrue(callBehaviorActionExecution
				.isChronologicalSuccessorOf(valueSpecificationAction2Execution));

		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 3;
		assertTrue(checkActivityModelOutput(trace, "test8_output", outputvalue));

		// TODO check initially enabled nodes
	}

	@Test
	public void test9_greater() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(9), true);

		ActivityExecution valueSpecificationAction1Execution = getActivityExecutionForActionExecution(
				trace, "specify 1");
		ActivityExecution valueSpecificationAction2Execution = getActivityExecutionForActionExecution(
				trace, "specify 0");
		ActivityExecution callBehaviorActionExecution = getActivityExecutionForActionExecution(
				trace, "call IntegerGreater");

		assertTrue(callBehaviorActionExecution
				.isChronologicalSuccessorOf(valueSpecificationAction1Execution));
		assertTrue(callBehaviorActionExecution
				.isChronologicalSuccessorOf(valueSpecificationAction2Execution));

		BooleanValue outputvalue = new BooleanValue();
		outputvalue.value = true;
		assertTrue(checkActivityModelOutput(trace, "test9_output", outputvalue));

		// TODO check initially enabled nodes
	}

	@Test
	public void test10_decisionMerge() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(10), true);

		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify true");
		ActivityExecution opaqueAction1Execution = getActivityExecutionForActionExecution(
				trace, "action1");
		ActivityExecution opaqueAction2Execution = getActivityExecutionForActionExecution(
				trace, "action2");
		ActivityExecution opaqueAction3Execution = getActivityExecutionForActionExecution(
				trace, "action3");
		ActivityExecution decisionNodeExecution = getActivityExecutionForDecisionNodeExecution(
				trace, "decisionNode");
		ActivityExecution mergeNodeExecution = getActivityExecutionForMergeNodeExecution(
				trace, "mergeNode");

		assertTrue(decisionNodeExecution
				.isChronologicalSuccessorOf(valueSpecificationActionExecution));
		assertTrue(opaqueAction1Execution
				.isChronologicalSuccessorOf(decisionNodeExecution));
		assertTrue(mergeNodeExecution
				.isChronologicalSuccessorOf(opaqueAction1Execution));
		assertTrue(opaqueAction3Execution
				.isChronologicalSuccessorOf(mergeNodeExecution));
		assertNull(opaqueAction2Execution);

		// TODO check initially enabled nodes
	}

	@Test
	public void test11_objectActions() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(11), true);

		ActivityExecution createObjectActionExecution = getActivityExecutionForActionExecution(
				trace, "create TestClass");
		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify 8");
		ActivityExecution addStructuralFeatureValueActionExecution = getActivityExecutionForActionExecution(
				trace, "set testAttribute");
		ActivityExecution readStructuralFeatureActionExecution = getActivityExecutionForActionExecution(
				trace, "read testAttribute");

		assertTrue(addStructuralFeatureValueActionExecution
				.isChronologicalSuccessorOf(createObjectActionExecution));
		assertTrue(addStructuralFeatureValueActionExecution
				.isChronologicalSuccessorOf(valueSpecificationActionExecution));
		assertTrue(readStructuralFeatureActionExecution
				.isChronologicalSuccessorOf(addStructuralFeatureValueActionExecution));

		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 8;
		assertTrue(checkActivityModelOutput(trace, "test11_output", outputvalue));

		// TODO check initially enabled nodes
	}

	@Test
	public void test12_decisionWithDecisionInputFlow() {
		Trace trace = execute(MODEL_PATH,
				getParameterDefinitionPath(12), true);

		ActivityExecution initialNodeExecution = getActivityExecutionForInitialNodeExecution(
				trace, "initialNode");
		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify true");
		ActivityExecution decisionNodeExecution = getActivityExecutionForDecisionNodeExecution(
				trace, "decisionNode");
		ActivityExecution opaqueAction1Execution = getActivityExecutionForActionExecution(
				trace, "action1");
		ActivityExecution opaqueAction2Execution = getActivityExecutionForActionExecution(
				trace, "action2");

		assertTrue(decisionNodeExecution
				.isChronologicalSuccessorOf(initialNodeExecution));
		assertTrue(decisionNodeExecution
				.isChronologicalSuccessorOf(valueSpecificationActionExecution));
		assertTrue(opaqueAction1Execution
				.isChronologicalSuccessorOf(decisionNodeExecution));
		assertNull(opaqueAction2Execution);

		// TODO check initially enabled nodes
	}
	
	
}
