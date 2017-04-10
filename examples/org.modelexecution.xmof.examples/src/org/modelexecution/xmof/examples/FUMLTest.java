package org.modelexecution.xmof.examples;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.BeforeClass;
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

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Loci.LociL1.Locus;

public abstract class FUMLTest extends SemanticsTest {

	private static final String FUML_METAMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";
	private static final String FUML_CONFIGURATION_PATH = "fuml/fuml.xmof";

	protected static final String FUML_MODEL_DIR = "test/fuml/";
	protected static final String FUML_BEHAVIOR_LIBRARY_FILENAME = "primitiveBehaviorLibrary.uml";
	private static final String FUML_BEHAVIOR_LIBRARY_PATH = FUML_MODEL_DIR
			+ FUML_BEHAVIOR_LIBRARY_FILENAME;
	private static final String FUML_TYPE_LIBRARY_FILENAME = "primitiveTypeLibrary.uml";
	private static final String FUML_TYPE_LIBRARY_PATH = FUML_MODEL_DIR
			+ FUML_TYPE_LIBRARY_FILENAME;

	protected static final String OPAQUE_ACTION_DO_ACTION = "doAction_OpaqueAction";
	protected static final String MAIN = "main";
	private static final String MAIN_OUTPUTS = "outputs";
	private static final String INTEGER_VALUE = "IntegerValue";
	private static final String INTEGER_VALUE_VALUE = "value_IntegerValue";
	private static final Object BOOLEAN_VALUE = "BooleanValue";
	private static final String BOOLEAN_VALUE_VALUE = "value_BooleanValue";
	private static final String FORK_NODE_ACTIVATION_FIRE = "fire_ForkNodeActivation";
	private static final String CONTROL_NODE_ACTIVATION_FIRE = "fire_ControlNodeActivation";
	protected static final String ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS = "getInitiallyEnabledNodeActivations_ActivityNodeActivationGroup";
	private static final String ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS_ENABLED_ACTIVATIONS = "enabledActivations";
	private static final String ACTIVITY_FINAL_NODE_ACTIVATION_FIRE = "fire_ActivityFinalNodeActivation";
	private static final String INITIAL_NODE_ACTIVATION_FIRE = "fire_InitialNodeActivation";
	private static final String VALUE_SPECIFICATION_ACTION_ACTIVATION_DO_ACTION = "doAction_ValueSpecificationActionActivation";
	private static final String CALL_ACTION_ACTIVATION_DO_ACTION = "doAction_CallActionActivation";
	private static final String DECISION_NODE_ACTIVATION_FIRE = "fire_DecisionNodeActivation";
	private static final String CREATE_OBJECT_ACTION_ACTIVATION_DO_ACTION = "doAction_CreateObjectActionActivation";
	private static final String ADD_STRUCTURAL_FEATURE_VALUE_ACTION_ACTIVATION_DO_ACTION = "doAction_AddStructuralFeatureValueActionActivation";
	private static final String READ_STRUCTURAL_FEATURE_ACTION_ACTIVATION_DO_ACTION = "doAction_ReadStructuralFeatureActionActivation";
	
	@BeforeClass
	public static void collectAllActivities() {
		SemanticsTest.collectAllActivities(FUML_CONFIGURATION_PATH);
	}
	
	protected boolean isExecutedBefore(Trace trace, String predecessorName,
			NodeType predecessorType, String successorName,
			NodeType successorType) {
		ActivityExecution predecessorExecution = getActivityExecutionForNodeExecution(
				trace, predecessorName, predecessorType);
		ActivityExecution successorExecution = getActivityExecutionForNodeExecution(
				trace, successorName, successorType);
		return successorExecution
				.isChronologicalSuccessorOf(predecessorExecution);
	}
	
	private boolean isExecuted(Trace trace, String nodeName, NodeType nodeType) {
		ActivityExecution execution = null;
		execution = getActivityExecutionForNodeExecution(trace, nodeName, nodeType);
		return execution != null;
	}

	private ActivityExecution getActivityExecutionForNodeExecution(Trace trace, String nodeName,
			NodeType nodeType) {
		ActivityExecution execution = null;
		switch(nodeType) {
		case ACTION: 
			execution = getActivityExecutionForActionExecution(
					trace, nodeName);
			break;
		case DECISION:
			execution = getActivityExecutionForDecisionNodeExecution(trace, nodeName);
			break;
		case FINAL:
			execution = getActivityExecutionForActivityFinalNodeExecution(trace, nodeName);
			break;
		case FORK:
			execution = getActivityExecutionForForkNodeExecution(trace, nodeName);
			break;
		case INITIAL:
			execution = getActivityExecutionForInitialNodeExecution(trace, nodeName);
			break;
		case JOIN:
			execution = getActivityExecutionForJoinNodeExecution(trace, nodeName);
			break;
		case MERGE:
			execution = getActivityExecutionForMergeNodeExecution(trace, nodeName);
			break;
			
		}
		return execution;
	}
	
	protected boolean notExecuted(Trace trace, String nodeName, NodeType nodeType) {
		return !isExecuted(trace, nodeName, nodeType);
	}

	private boolean checkInitiallyEnabledNodes(Trace trace, String... nodeNames) {
		ActivityExecution activityExecution = getActivityExecution(trace,
				ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS);
		List<ValueSnapshot> output = getOutput(
				activityExecution,
				ACTIVITY_NODE_ACTIVATION_GROUP_GET_INITIALLY_ENABLED_NODE_ACTIVATIONS_ENABLED_ACTIVATIONS);
		if (output.size() != nodeNames.length)
			return false;

		boolean allInitiallyEnabledNodesFound = true;
		for (int i = 0; i < nodeNames.length; ++i) {
			boolean initiallyEnabledNodeFound = false;
			for (ValueSnapshot o : output) {
				if (isSemanticVisitorOfRuntimeModelElement(trace,
						(Object_) o.getRuntimeValue(), nodeNames[i]))
					initiallyEnabledNodeFound = true;
			}
			if (!initiallyEnabledNodeFound)
				allInitiallyEnabledNodesFound = false;
		}
		return allInitiallyEnabledNodesFound;
	}

	protected boolean checkActivityModelOutput(Trace trace, String parameterName,
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
		} else if (expectedValue instanceof BooleanValue
				&& value instanceof Object_) {
			Object_ valueObject = (Object_) value;
			if (valueObject.types.get(0).name.equals(BOOLEAN_VALUE)) {
				BooleanValue v = (BooleanValue) getFeatureValue(valueObject,
						BOOLEAN_VALUE_VALUE);
				return equals((BooleanValue) expectedValue, v);
			}
		}
		return false;
	}

	private boolean equals(IntegerValue value1, IntegerValue value2) {
		return value1.value == value2.value;
	}

	private boolean equals(BooleanValue value1, BooleanValue value2) {
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

	protected ActivityExecution getActivityExecutionForMergeNodeExecution(
			Trace trace, String mergeNodeName) {
		return getActivityExecutionForContextObject(trace,
				CONTROL_NODE_ACTIVATION_FIRE, mergeNodeName);
	}

	protected ActivityExecution getActivityExecutionForDecisionNodeExecution(
			Trace trace, String decisionNodeName) {
		return getActivityExecutionForContextObject(trace,
				DECISION_NODE_ACTIVATION_FIRE, decisionNodeName);
	}

	protected ActivityExecution getActivityExecutionForActivityFinalNodeExecution(
			Trace trace, String activityFinalNodeName) {
		return getActivityExecutionForContextObject(trace,
				ACTIVITY_FINAL_NODE_ACTIVATION_FIRE, activityFinalNodeName);
	}

	protected ActivityExecution getActivityExecutionForInitialNodeExecution(
			Trace trace, String initialNodeName) {
		return getActivityExecutionForContextObject(trace,
				INITIAL_NODE_ACTIVATION_FIRE, initialNodeName);
	}

	protected ActivityExecution getActivityExecutionForJoinNodeExecution(
			Trace trace, String joinNodeName) {
		return getActivityExecutionForContextObject(trace,
				CONTROL_NODE_ACTIVATION_FIRE, joinNodeName);
	}

	protected ActivityExecution getActivityExecutionForForkNodeExecution(
			Trace trace, String forkNodeName) {
		return getActivityExecutionForContextObject(trace,
				FORK_NODE_ACTIVATION_FIRE, forkNodeName);
	}

	protected ActivityExecution getActivityExecutionForActionExecution(
			Trace trace, String actionName) {
		ActivityExecution activityExecution = null;
		activityExecution = getActivityExecutionForContextObject(trace,
				OPAQUE_ACTION_DO_ACTION, actionName);
		if (activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					VALUE_SPECIFICATION_ACTION_ACTIVATION_DO_ACTION, actionName);
		}
		if (activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					CALL_ACTION_ACTIVATION_DO_ACTION, actionName);
		}
		if (activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					CREATE_OBJECT_ACTION_ACTIVATION_DO_ACTION, actionName);
		}
		if (activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					ADD_STRUCTURAL_FEATURE_VALUE_ACTION_ACTIVATION_DO_ACTION,
					actionName);
		}
		if (activityExecution == null) {
			activityExecution = getActivityExecutionForContextObject(trace,
					READ_STRUCTURAL_FEATURE_ACTION_ACTIVATION_DO_ACTION,
					actionName);
		}
		return activityExecution;
	}
	
	protected Set<ActivityExecution> getActivityExecutionsForOpaqueActionExecution(
			Trace trace, String actionName) {
		Set<ActivityExecution> activityExecutionsForContextObject = getActivityExecutionsForContextObject(
				trace, OPAQUE_ACTION_DO_ACTION, actionName);
		return activityExecutionsForContextObject;
	}
	
	private ActivityExecution getActivityExecutionForContextObject(Trace trace,
			String activityName, String contextObjectName) {
		Set<ActivityExecution> activityExecutionsForContextObject = getActivityExecutionsForContextObject(
				trace, activityName, contextObjectName);
		if (activityExecutionsForContextObject.size() == 1)
			return activityExecutionsForContextObject.iterator().next();
		else
			return null;
	}

	private Set<ActivityExecution> getActivityExecutionsForContextObject(
			Trace trace, String activityName, String contextObjectName) {
		Set<ActivityExecution> foundActivityExecutions = new LinkedHashSet<ActivityExecution>();
		Set<ActivityExecution> activityExecutions = getActivityExecutions(
				trace, activityName);
		for (ActivityExecution activityExecution : activityExecutions) {
			Object_ semanticVisitor = getContextObject(activityExecution);
			if (isSemanticVisitorOfRuntimeModelElement(trace, semanticVisitor,
					contextObjectName))
				foundActivityExecutions.add(activityExecution);
		}
		return foundActivityExecutions;
	}

	private boolean isSemanticVisitorOfRuntimeModelElement(Trace trace,
			Object_ semanticVisitor, String elementName) {
		Object_ runtimeModelElement = getLinkedObject(trace, semanticVisitor,
				"runtimeModelElement");
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

	protected List<Object_> getLinkedObjects(Link link) {
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

	protected boolean existsModelElementAtLocus(EObject eObject) {
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

	protected Object_ getFUMLObjectFromModelElement(EObject eObject) {
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

	protected Resource getResourceByFileName(String name) {
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

	protected Trace execute(String modelPath, String parameterDefinitionPath,
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

	enum NodeType {
		ACTION, INITIAL, FINAL, DECISION, MERGE, FORK, JOIN
	}
}
