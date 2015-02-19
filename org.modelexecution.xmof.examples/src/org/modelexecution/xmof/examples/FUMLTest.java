package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Loci.LociL1.Locus;

public class FUMLTest extends SemanticsTest {
	
	private static final String FUML_METAMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";
	private static final String FUML_CONFIGURATION_PATH = "fuml/fuml.xmof";

	private static final String FUML_MODEL_DIR = "test/fuml/";
	private static final String FUML_BEHAVIOR_LIBRARY_FILENAME = "primitiveBehaviorLibrary.uml";
	private static final String FUML_BEHAVIOR_LIBRARY_PATH = FUML_MODEL_DIR
			+ FUML_BEHAVIOR_LIBRARY_FILENAME;
	private static final String FUML_TYPE_LIBRARY_FILENAME = "primitiveTypeLibrary.uml";
	private static final String FUML_TYPE_LIBRARY_PATH = FUML_MODEL_DIR
			+ FUML_TYPE_LIBRARY_FILENAME;
	private static final String FUML_EXEENV_FILENAME = "executionenvironment.xmi";

	private static final String EXECUTION_ENVIRONMENT = "ExecutionEnvironment";
	private static final String FUNCTION_BEHAVIOR = "FunctionBehavior";
	private static final String INTEGER_PLUS_FUNCTION_BEHAVIOR_EXECUTION = "IntegerPlusFunctionBehaviorExecution";

	private static final String OPAQUE_ACTION_DO_ACTION = "doAction_OpaqueAction";
	private static final String MAIN = "main";
	private static final String MAIN_OUTPUTS = "outputs";
	private static final String INTEGER_VALUE = "IntegerValue";
	private static final String INTEGER_VALUE_VALUE = "value_IntegerValue";
	private static final String FORK_NODE_ACTIVATION_FIRE = "fire_ForkNodeActivation";
	private static final String CONTROL_NODE_ACTIVATION_FIRE = "fire_ControlNodeActivation";
	private static final String ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS = "getInitiallyEnabledNodeActivations_ActivityNodeActivationGroup";
	private static final String ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS_ENABLED_ACTIVATIONS = "enabledActivations";
	private static final String ACTIVITY_FINAL_NODE_ACTIVATION_FIRE = "fire_ActivityFinalNodeActivation";
	private static final String INITIAL_NODE_ACTIVATION_FIRE = "fire_InitialNodeActivation";
	private static final String VALUE_SPECIFICATION_ACTION_ACTIVATION_DO_ACTION = "doAction_ValueSpecificationActionActivation";
	private static final String CALL_ACTION_ACTIVATION_DO_ACTION = "doAction_CallActionActivation";
	
	@BeforeClass
	public static void collectAllActivities() {
		SemanticsTest.collectAllActivities(FUML_CONFIGURATION_PATH);
	}

	@Test
	public void test1_setupOfExecutionEnvironment() {
		setupVM("test/fuml/testmodel.uml", "test/fuml/test1parameter.xmi");

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
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test1parameter.xmi", true);
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
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test2parameter.xmi", true);
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
		
		//TODO assertTrue(checkInitiallyEnabledNodes(trace, "OpaqueAction1"));
	}

	@Test
	public void test3_parameters() {
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test3parameter.xmi", true);
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
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test4parameter.xmi", true);
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

		//TODO assertTrue(checkInitiallyEnabledNodes(trace, "action1"));
	}
	
	@Test
	public void test5_initialFinal() {
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test5parameter.xmi", true);
		
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

		//TODO assertTrue(checkInitiallyEnabledNodes(trace, "initialNode"));
	}
	
	@Test
	public void test6_valueSpecificationAction() {
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test6parameter.xmi", true);
		
		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify 19");
		assertNotNull(valueSpecificationActionExecution);
		
		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 19;
		checkActivityModelOutput(trace, "test6_output", outputvalue);
	}
	
	@Test
	public void test7_callBehaviorAction() {
		Trace trace = execute("test/fuml/testmodel.uml",
				"test/fuml/test7parameter.xmi", true);
		
		ActivityExecution callBehaviorActionExecution = getActivityExecutionForActionExecution(
				trace, "call test6");
		ActivityExecution valueSpecificationActionExecution = getActivityExecutionForActionExecution(
				trace, "specify 19");
		assertNotNull(callBehaviorActionExecution);
		assertNotNull(valueSpecificationActionExecution);
		
		IntegerValue outputvalue = new IntegerValue();
		outputvalue.value = 19;
		checkActivityModelOutput(trace, "test6_output", outputvalue);
	}

	private boolean checkInitiallyEnabledNodes(Trace trace, String... nodeNames) {
		ActivityExecution activityExecution = getActivityExecution(trace, ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS);
		List<ValueSnapshot> output = getOutput(activityExecution, ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS_ENABLED_ACTIVATIONS);
		if(output.size() != nodeNames.length)
			return false;
		
		boolean allInitiallyEnabledNodesFound = true;
		for (int i = 0; i < nodeNames.length; ++i) {
			boolean initiallyEnabledNodeFound = false;
			for(ValueSnapshot o : output) {
				if(isSemanticVisitorOfRuntimeModelElement(trace, (Object_)o.getRuntimeValue(), nodeNames[i]))
					initiallyEnabledNodeFound = true;
			}
			if(!initiallyEnabledNodeFound)
				allInitiallyEnabledNodesFound = false;
		}
		return allInitiallyEnabledNodesFound;
	}

	private boolean checkActivityModelOutput(Trace trace, String parameterName,
			Value... values) {
		List<Object_> outputValues = getActivityModelOutput(trace,
				parameterName);
		if (outputValues.size() != values.length)
			return false;
		for (int i = 0; i < values.length; ++i) {
			Value expectedValue = values[i];
			Object_ value = outputValues.get(i);
			if (!equals(expectedValue, value))
				return false;
		}
		return true;
	}

	private List<Object_> getActivityModelOutput(Trace trace,
			String parameterName) {
		List<ValueSnapshot> output = getActivityModelOutput(trace);
		Object_ parameterValue = null;
		for (ValueSnapshot o : output) {
			Object_ outputObject = (Object_) o.getRuntimeValue();
			Object_ parameter = getLinkedObject(trace, outputObject,
					"parameter_ParameterValue");
			Value name = getFeatureValue(parameter, "name");
			if (((StringValue) name).value.equals(parameterName)) {
				parameterValue = outputObject;
			}
		}

		List<Object_> parameterValueValues = getLinkedObjects(trace,
				parameterValue, "values_ParameterValue");
		return parameterValueValues;
	}

	private List<ValueSnapshot> getActivityModelOutput(Trace trace) {
		ActivityExecution activityExecution_main = getActivityExecution(trace,
				MAIN);
		List<ValueSnapshot> output = getOutput(activityExecution_main,
				MAIN_OUTPUTS);
		return output;
	}

	private boolean equals(Value expectedValue, Value value) {
		if (expectedValue instanceof IntegerValue && value instanceof Object_) {
			Object_ valueObject = (Object_) value;
			if (valueObject.types.get(0).name.equals(INTEGER_VALUE)) {
				IntegerValue v = (IntegerValue) getFeatureValue(valueObject,
						INTEGER_VALUE_VALUE);
				return equals((IntegerValue) expectedValue, v);
			}
		}
		return false;
	}

	private boolean equals(IntegerValue value1, IntegerValue value2) {
		return value1.value == value2.value;
	}

	private List<ValueSnapshot> getOutput(ActivityExecution activityExecution,
			String parameterName) {
		List<ValueSnapshot> values = new ArrayList<ValueSnapshot>();
		for (OutputParameterSetting output : activityExecution
				.getActivityOutputs()) {
			if (output.getParameter().name.equals(parameterName)) {
				for (OutputParameterValue outputValue : output
						.getParameterValues()) {
					ValueSnapshot valueSnapshot = outputValue
							.getValueSnapshot();
					values.add(valueSnapshot);
				}
			}
		}
		return values;
	}
	
	private ActivityExecution getActivityExecutionForActivityFinalNodeExecution(Trace trace, String activityFinalNodeName) {
		return getActivityExecutionForContextObject(trace,
				ACTIVITY_FINAL_NODE_ACTIVATION_FIRE, activityFinalNodeName);
	}
	
	private ActivityExecution getActivityExecutionForInitialNodeExecution(Trace trace, String initialNodeName) {
		return getActivityExecutionForContextObject(trace,
				INITIAL_NODE_ACTIVATION_FIRE, initialNodeName);
	}

	private ActivityExecution getActivityExecutionForJoinNodeExecution(
			Trace trace, String joinNodeName) {
		return getActivityExecutionForContextObject(trace,
				CONTROL_NODE_ACTIVATION_FIRE, joinNodeName);
	}

	private ActivityExecution getActivityExecutionForForkNodeExecution(
			Trace trace, String forkNodeName) {
		return getActivityExecutionForContextObject(trace,
				FORK_NODE_ACTIVATION_FIRE, forkNodeName);
	}

	private ActivityExecution getActivityExecutionForActionExecution(
			Trace trace, String actionName) {
		ActivityExecution activityExecution = null;
		activityExecution = getActivityExecutionForContextObject(trace,
				OPAQUE_ACTION_DO_ACTION, actionName);
		if(activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					VALUE_SPECIFICATION_ACTION_ACTIVATION_DO_ACTION, actionName);
		}
		if(activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					CALL_ACTION_ACTIVATION_DO_ACTION, actionName);
		}
		return activityExecution;
	}

	private ActivityExecution getActivityExecutionForContextObject(Trace trace,
			String activityName, String contextObjectName) {
		Set<ActivityExecution> activityExecutionsForContextObject = getActivityExecutionsForContextObject(trace,
				activityName, contextObjectName);
		if (activityExecutionsForContextObject.size() == 1)
			return activityExecutionsForContextObject.iterator().next();
		else
			return null;
	}

	private Set<ActivityExecution> getActivityExecutionsForContextObject(Trace trace, String activityName,
			String contextObjectName) {
		Set<ActivityExecution> foundActivityExecutions = new LinkedHashSet<ActivityExecution>();
		Set<ActivityExecution> activityExecutions = getActivityExecutions(
				trace, activityName);
		for (ActivityExecution activityExecution : activityExecutions) {
			Object_ semanticVisitor = getContextObject(activityExecution);
			if (isSemanticVisitorOfRuntimeModelElement(trace, semanticVisitor, contextObjectName))
				foundActivityExecutions.add(activityExecution);
		}
		return foundActivityExecutions;
	}
	
	private boolean isSemanticVisitorOfRuntimeModelElement(Trace trace, Object_ semanticVisitor, String elementName) {
		Object_ runtimeModelElement = getLinkedObject(trace,
				semanticVisitor, "runtimeModelElement");
		if (((StringValue) getFeatureValue(runtimeModelElement, "name")).value
				.equals(elementName))
			return true;
		return false;
	}

	private Value getFeatureValue(Object_ object_, String featureName) {
		for (FeatureValue featureValue : object_.featureValues) {
			if (featureValue.feature.name.equals(featureName))
				return featureValue.values.get(0);
		}
		return null;
	}

	private Object_ getContextObject(ActivityExecution activityExecution) {
		return (Object_) ((ValueInstance) activityExecution
				.getContextValueSnapshot().eContainer()).getRuntimeValue();
	}

	private Object_ getLinkedObject(Trace trace, Object_ object_,
			String associationEnd) {
		List<Object_> linkedObjects = getLinkedObjects(trace, object_,
				associationEnd);
		if (linkedObjects.size() == 1)
			return linkedObjects.get(0);
		return null;
	}

	private List<Object_> getLinkedObjects(Trace trace, Object_ object_,
			String associationEnd) {
		Set<Link> links = new LinkedHashSet<Link>();
		for (ValueInstance valueInstance : trace.getValueInstances()) {
			Value runtimeValue = valueInstance.getRuntimeValue();
			if (runtimeValue instanceof Link) {
				Link l = (Link) runtimeValue;
				boolean hasAssociationEnd = false;
				boolean linksObject = false;
				for (FeatureValue featureValue : l.featureValues) {
					if (featureValue.feature.name.equals(associationEnd))
						hasAssociationEnd = true;
					for (Value value : featureValue.values) {
						Reference reference = (Reference) value;
						if (reference.referent == object_)
							linksObject = true;
					}
				}
				if (hasAssociationEnd && linksObject) {
					links.add(l);
				}
			}
		}
		List<Object_> linkedObjects = new ArrayList<Object_>();
		for (Link link : links) {
			for (FeatureValue featureValue : link.featureValues) {
				if (featureValue.feature.name.equals(associationEnd)) {
					linkedObjects
							.add(((Reference) featureValue.values.get(0)).referent);
				}
			}
		}
		return linkedObjects;
	}

	private List<Object_> getLinkedObjects(Link link) {
		List<Object_> linkedObjects = new ArrayList<Object_>();
		linkedObjects.add(getLinkedObject(link, 0));
		linkedObjects.add(getLinkedObject(link, 1));
		return linkedObjects;
	}

	private Object_ getLinkedObject(Link link, int featureValuePosition) {
		Reference reference = (Reference) link.featureValues
				.get(featureValuePosition).values.get(0);
		return reference.referent;
	}

	private boolean existsModelElementAtLocus(EObject eObject) {
		Locus locus = getLocus();
		EObject configurationObject = getConfigurationObject(eObject);
		if (configurationObject == null)
			return false;
		Object_ fumlObject = getFUMLObjectFromConfigurationObject(configurationObject);
		if (fumlObject == null)
			return false;
		if (!locus.extensionalValues.contains(fumlObject))
			return false;
		return true;
	}

	private Object_ getFUMLObjectFromModelElement(EObject eObject) {
		EObject configurationObject = getConfigurationObject(eObject);
		Object_ fumlObject = getFUMLObjectFromConfigurationObject(configurationObject);
		return fumlObject;
	}

	private Object_ getFUMLObjectFromConfigurationObject(EObject eObject) {
		XMOFInstanceMap instanceMap = getInstanceMap();
		return instanceMap.getObject(eObject);
	}

	private EObject getConfigurationObject(EObject eObject) {
		ConfigurationObjectMap configurationObjectMap = getConfigurationObjectMap();
		return configurationObjectMap.getConfigurationObject(eObject);
	}

	private Resource getResourceByFileName(String name) {
		ResourceSet resourceSet = getResourceSet();
		for (Resource resource : resourceSet.getResources()) {
			if (resource.getURI().lastSegment().equals(name)) {
				return resource;
			}
		}
		return null;
	}

	XMOFVirtualMachine setupVM(String modelPath, String parameterDefinitionPath) {
		return setupVM(modelPath, parameterDefinitionPath, FUML_METAMODEL_PATH,
				FUML_CONFIGURATION_PATH);
	}

	private Trace execute(String modelPath, String parameterDefinitionPath,
			boolean cleanup) {
		return execute(modelPath, parameterDefinitionPath, FUML_METAMODEL_PATH,
				FUML_CONFIGURATION_PATH, cleanup);
	}

	@Override
	void loadMetamodel(String metamodelPath) {
	}

	@Override
	ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		Resource primitiveTypeLibraryPath = loadResource(FUML_TYPE_LIBRARY_PATH);
		Resource primitiveBehaviorLibraryPath = loadResource(FUML_BEHAVIOR_LIBRARY_PATH);
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationResource,
						modelResource, parameterDefintionResource,
						primitiveTypeLibraryPath, primitiveBehaviorLibraryPath);
		return configurationObjectMap;
	}
	
}
