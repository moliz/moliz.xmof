package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.Value;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueDefinition;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.diff.util.EMFUtil;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.NamedElement;

public class CDTest implements ExecutionEventListener {

	private static final String PRIMITIVETYPE_isStringValue = "isStringValue";
	private static final String PRIMITIVETYPE_validateValue_primitiveType = "validateValue_primitiveType";
	private static final String PRIMITIVEVALUE_isPrimitiveValue = "isPrimitiveValue";
	private static final String PRIMITIVETYPE_validateType_primitiveType = "validateType_primitiveType";
	private static final String TYPE_validate_value_type = "validate_value_type";
	private static final String PROPERTY_validateValue_property = "validateValue_property";
	private static final String PROPERTY_validateValues_property = "validateValues_property";
	private static final String MULTIPLICITYELEMENT_validate_multiplicityElement = "validate_multiplicityElement";
	private static final String PROPERTY_validateMultiplicity_property = "validateMultiplicity_property";
	private static final String PROPERTY_validateUniqueness_featureValue_values = "validateUniqueness_featureValue_values";
	private static final String PROPERTY_validateUniquenexx_featureValue_property = "validateUniqueness_featureValue_property";
	private static final String PROPERTY_hasExactlyOneFeatureValue = "hasExactlyOneFeatureValue";
	private static final String PROPERTY_validate_property = "validate_property";
	private static final String VALUESPACE_validateLinkParticipation = "validateLinkParticipation";
	private static final String VALUESPACE_validateStructuralFeatures = "validateStructuralFeatures";
	private static final String CLASS_validateLinks = "validateLinks";
	private static final String CLASS_validateStructuralFeatureValues = "validateStructuralFeatureValues";
	private static final String CLASSIFIER_isConcrete = "isConcrete";
	private static final String CLASS_validate_object_class = "validate_object_class";
	private static final String VALUESPACE_validateTypes_valueSpace = "validateTypes_valueSpace";
	private static final String VALUESPACE_hasType = "hasType";
	private static final String VALUESPACE_validate_valueSpace = "validate_valueSpace";
	private static final String MODEL_main = "main";
	
	private static final String CONFIGURATIONMODEL_PATH = "configurationmodel.xmi";
	private static final String CLASSDIAGRAM_METAMODEL_PATH = "cd/classes.ecore";
	private static final String CLASSDIAGRAM_CONFIGURATION_PATH = "cd/classes.xmof";

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;

	private int activityExecutionID = -1;
	
	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	@After
	public void reset() {
		System.out.println("==============================");
		System.out.println(activityEntries);
		System.out.println("executed nodes: " + nodeCounter);
		System.out.println("==============================");
		activityEntries = "";
		activityExecutionID = -1;
		nodeCounter = 0;
	}

	@Test
	public void test1_ObjectType_ObjectWithoutType() {
		Trace trace = execute("cd/cd1.xmi", "cd/cd1parameters_test1.xmi");
		assertEquals(3, trace.getActivityExecutions().size());
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		
		// hasType returns false
		boolean checkHasType = checkActivityOutput(false, hasType);
		assertTrue(checkHasType);
		
		// output is false
		boolean checkoutput = checkActivityOutput(false, main, validate_valueSpace, hasType);
		assertTrue(checkoutput);		
	}
	
	@Test
	public void test2_ObjectType_ObjectWithAbstractType() {
		Trace trace = execute("cd/cd1.xmi", "cd/cd1parameters_test2.xmi");
		assertEquals(6, trace.getActivityExecutions().size());
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		
		// hasType returns true
		boolean checkHasType = checkActivityOutput(true, hasType);
		assertTrue(checkHasType);
		
		// isConcrete returns false
		boolean checkIsConcrete = checkActivityOutput(false, isConcrete);
		assertTrue(checkIsConcrete);
		
		// output is false		
		boolean checkOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkOutput);
	}
	
	@Test
	public void test3_ObjectType_ObjectWithConcreteType() {
		Trace trace = execute("cd/cd1.xmi", "cd/cd1parameters_test3.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// all the called validation activities return true
		boolean checkTrueOutputs = checkActivityOutput(true, main, validate_valueSpace, hasType, validateTypes_valueSpace, validate_object_class, isConcrete, validateStructuralFeatureValues, validateLinks, validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrueOutputs);		
	}
	
	@Test
	public void test4_PrimitiveValue_ObjectWithPrimitiveFeatureValue() {
		Trace trace = execute("cd/cd1.xmi", "cd/cd1parameters_test4.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		// activities for validating one property
		ActivityExecution validate_property = getActivityExecution(trace, PROPERTY_validate_property);
		ActivityExecution hasExactlyOneFeatureValue = getActivityExecution(trace, PROPERTY_hasExactlyOneFeatureValue);
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniquenexx_featureValue_property);
		ActivityExecution validateUniqueness_featureValue_values = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_values);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validate_multiplicityElement = getActivityExecution(trace, MULTIPLICITYELEMENT_validate_multiplicityElement);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_primitiveType = getActivityExecution(trace, PRIMITIVETYPE_validateType_primitiveType);
		ActivityExecution isPrimitiveValue = getActivityExecution(trace, PRIMITIVEVALUE_isPrimitiveValue);
		ActivityExecution validateValue_primitiveType = getActivityExecution(trace, PRIMITIVETYPE_validateValue_primitiveType);
		ActivityExecution isStringValue = getActivityExecution(trace, PRIMITIVETYPE_isStringValue);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
				
		// the property is valid
		boolean checkProperty = checkActivityOutput(true, validate_property,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property,
				validateUniqueness_featureValue_values,
				validateMultiplicity_property, validate_multiplicityElement,
				validateValues_property, validateValue_property,
				validate_value_type, validateType_primitiveType,
				isPrimitiveValue, validateValue_primitiveType, isStringValue);
		assertTrue(checkProperty);
		
		// also the other validation activities return true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				validateTypes_valueSpace, validate_object_class, isConcrete,
				validateStructuralFeatureValues, validateLinks,
				validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrueOutputs);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main, validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test5_PrimitiveValue_ObjectWithPrimitiveFeatureValue() {
		Trace trace = execute("cd/cd1.xmi", "cd/cd1parameters_test5.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		// activities for validating one property
		ActivityExecution validate_property = getActivityExecution(trace, PROPERTY_validate_property);
		ActivityExecution hasExactlyOneFeatureValue = getActivityExecution(trace, PROPERTY_hasExactlyOneFeatureValue);
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniquenexx_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
				
		// FeatureValue for property is missngProperty.hasExactlyOneFeatureValue returns false
		boolean checkHasExactlyOneFeatureValue = checkActivityOutput(false, hasExactlyOneFeatureValue);
		assertTrue(checkHasExactlyOneFeatureValue);
		
		// the property is invalid
		boolean checkProperty = checkActivityOutput(false, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// remaining validation activities for Property.validate_property have not been executed
		assertNull(validateUniqueness_featureValue_property);
		assertNull(validateMultiplicity_property);
		assertNull(validateValues_property);
		
		// remaining validation activities for Class.validate_object_class have not been executed
		assertNull(validateLinks);
		
		// remaining validation activities for ValueSpace.validateTypes_valueSpace have not been executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType, isConcrete);
		assertTrue(checkTrueOutputs); 
		
		// output is true
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	private boolean checkObjectUnique(Object... objects) {
		List<Object> objectList = Arrays.asList(objects);
		Set<Object> objectSet = new HashSet<Object>(objectList);
		return objectList.size() == objectSet.size();
	}
	
	private boolean checkObjectNotNull(Object... objects) {
		for(Object object : objects) {
			if(object == null)
				return false;
		}
		return true;
	}
	
	private ActivityExecution getActivityExecution(Trace trace, String activityName) {
		Set<ActivityExecution> activityExecutions = new HashSet<ActivityExecution>(); 
		for(ActivityExecution activityExecution : trace.getActivityExecutions()) {
			if(activityExecution.getActivity().name.equals(activityName)) {
				activityExecutions.add(activityExecution);
			}
		}
		if(activityExecutions.size() != 1)
			return null;
		else
			return activityExecutions.iterator().next();
	}

	private boolean checkForNoActivityOutput(ActivityExecution... executions) {
		for(ActivityExecution execution : executions) {
			boolean checkForNoActivityOutput = checkForNoActivityOutput(execution);
			if(!checkForNoActivityOutput)
				return false;
		}
		return true;
	}
	
	private boolean checkForNoActivityOutput(ActivityExecution execution) {
		List<fUML.Semantics.Classes.Kernel.Value> activityOutput = getActivityOutput(execution);
		return activityOutput.size() == 0;
	}
	
	private boolean checkActivityOutput(boolean expectedResult, ActivityExecution... executions) {
		for(ActivityExecution execution : executions) {
			boolean check = checkActivityOutput(expectedResult, execution);
			if(!check)
				return false;
		}
		return true;
	}
	
	private boolean checkActivityOutput(boolean expectedResult, ActivityExecution execution) {
		List<fUML.Semantics.Classes.Kernel.Value> activityOutput = getActivityOutput(execution);
		if(activityOutput.size() == 1) {
			fUML.Semantics.Classes.Kernel.Value outputValue = activityOutput.get(0);
			if(outputValue instanceof BooleanValue) {
				BooleanValue booleanOutputValue = (BooleanValue) outputValue;
				return booleanOutputValue.value == expectedResult;
			}
		}
		return false;
	}
	
	private List<fUML.Semantics.Classes.Kernel.Value> getActivityOutput(
			ActivityExecution execution) {
		List<fUML.Semantics.Classes.Kernel.Value> activityOutput = new ArrayList<fUML.Semantics.Classes.Kernel.Value>();
		for (OutputParameterSetting output : execution.getActivityOutputs()) {
			for (OutputParameterValue outputValue : output.getParameterValues()) {
				ValueSnapshot valueSnapshot = outputValue.getValueSnapshot();
				fUML.Semantics.Classes.Kernel.Value value = valueSnapshot
						.getValue();
				activityOutput.add(value);
			}
		}
		return activityOutput;
	}
	
	private Trace execute(String modelPath, String parameterDefinitionPath) {
		loadMetamodel();
		
		Resource modelResource = loadResource(modelPath);

		Resource configurationResource = loadResource(CLASSDIAGRAM_CONFIGURATION_PATH);
		
		Resource parameterDefintionResource = loadResource(parameterDefinitionPath);

		XMOFVirtualMachine vm = createVM(modelResource, configurationResource,
				parameterDefintionResource);

		vm.addRawExecutionEventListener(this);
		vm.run();
		vm.removeRawExecutionEventListener(this);

		Trace trace = vm.getRawExecutionContext().getTrace(activityExecutionID);
		return trace;
	}

	private XMOFVirtualMachine createVM(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		ConfigurationObjectMap configurationObjectMap = createConfigurationObjectMap(
				modelResource, configurationResource, parameterDefintionResource);

		Resource configurationModelResource = EMFUtil.createResource(
				resourceSet, editingDomain,
				EMFUtil.createFileURI(CONFIGURATIONMODEL_PATH),
				configurationObjectMap.getConfigurationObjects());

		List<ParameterValue> parameterValueConfiguration = getParameterValueConfiguration(
				parameterDefintionResource, configurationObjectMap);

		XMOFBasedModel model = new XMOFBasedModel(
				configurationModelResource.getContents(),
				parameterValueConfiguration, editingDomain);
		
		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		return vm;
	}

	private ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		Collection<EObject> parameterValueObjects = getParameterValueObjects(parameterDefintionResource);
		
		Collection<EObject> inputElements = new ArrayList<EObject>();
		inputElements.addAll(modelResource.getContents());
		inputElements.addAll(parameterValueObjects);

		Collection<EPackage> configurationPackages = EMFUtil
				.getEPackages(configurationResource);
		ConfigurationObjectMap configurationObjectMap = new ConfigurationObjectMap(
				inputElements, configurationPackages);
		return configurationObjectMap;
	}

	private void loadMetamodel() {
		EMFUtil.loadMetamodel(resourceSet,
				EMFUtil.createFileURI(CLASSDIAGRAM_METAMODEL_PATH));
	}
	
	private Resource loadResource(String filePath) {
		return EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI(filePath));
	}
	
	private Collection<EObject> getParameterValueObjects(
			Resource parameterDefinitionResource) {
		Collection<ParameterValue> parameterValues = getParameterValues(parameterDefinitionResource);
		Collection<EObject> parameterValueObjects = new BasicEList<EObject>();
		for (ParameterValue parameterValue : parameterValues) {
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					ObjectValue objectValue = (ObjectValue) value;
					EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						parameterValueObjects.add(referencedEObject);
					}
				}
			}
		}
		return parameterValueObjects;
	}

	private List<ParameterValue> getParameterValueConfiguration(
			Resource parameterDefinitionResource,
			ConfigurationObjectMap configurationMap) {
		Collection<ParameterValue> parameterValues = getParameterValues(parameterDefinitionResource);
		Copier copier = new EcoreUtil.Copier(true, false);
		copier.copyAll(parameterValues);
		copier.copyReferences();

		List<ParameterValue> parameterValueConfiguration = new ArrayList<ParameterValue>();
		for (ParameterValue parameterValue : parameterValues) {
			ParameterValue parameterValueConf = (ParameterValue) copier
					.get(parameterValue);
			parameterValueConf.setParameter(parameterValue.getParameter());
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					ObjectValue objectValue = (ObjectValue) value;
					EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						EObject referencedEObjectConf = configurationMap
								.getConfigurationObject(referencedEObject);
						ObjectValue objectValueConf = (ObjectValue) copier
								.get(value);
						objectValueConf.setEObject(referencedEObjectConf);
					}
				}
			}
			parameterValueConfiguration.add(parameterValueConf);
		}
		return parameterValueConfiguration;
	}

	private Collection<ParameterValue> getParameterValues(Resource parameterDefinitionResource) {
		EList<ParameterValue> parameterValues = new BasicEList<ParameterValue>();
		for (EObject eObject : parameterDefinitionResource.getContents()) {
			if (eObject instanceof ParameterValueDefinition) {
				ParameterValueDefinition parameterValueDefinition = (ParameterValueDefinition) eObject;
				parameterValues.addAll(parameterValueDefinition
						.getParameterValues());
			}
		}
		return parameterValues;
	}
	
	private String activityEntries = "";
	private int nodeCounter = 0;
	@Override
	public void notify(Event event) { //TODO remove
		if (event instanceof ActivityNodeEntryEvent)
			++nodeCounter;
		if (activityExecutionID == -1 && event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			activityExecutionID = activityEntryEvent.getActivityExecutionID();
		}
		
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			ActivityNode caller = null;
			if(activityEntryEvent.getParent() instanceof ActivityNodeEntryEvent) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) activityEntryEvent.getParent();
				caller = activityNodeEntryEvent.getNode();
			}
			activityEntries +=  printActivity(activityEntryEvent.getActivity()) + " (called by " + printActivityNode(caller) + ")" + "\n";
		}
	}
	
	private String printActivity(Activity activity) {
		String operationClassifierName = "";
		String operationName = "";
		String activityClassifierName = "";
		String activityName = "";
		try{
			operationClassifierName = activity.specification.namespace.name;
			operationName = activity.specification.name;
			activityClassifierName = ((NamedElement)activity.owner).name;
			activityName = activity.name;
		} catch(Exception e){
		}
		return operationClassifierName + "." + operationName + "/" + activityClassifierName + "." + activityName;
	}
	
	private String printActivityNode(ActivityNode node) {
		String nodeName = "";
		String activityName = "";
		String activityClassifierName = "";
		String operationClassifierName = "";
		String operationName = "";
		try{ 
			nodeName = node.name;
			activityName = node.activity.name;
			activityClassifierName = ((NamedElement)node.activity.owner).name;
			operationName = node.activity.specification.name;
			operationClassifierName = node.activity.specification.namespace.name;
		}catch(Exception e) {
			
		}
		return nodeName + " of activity " + activityClassifierName + "." + activityName + " operation " + operationClassifierName + "." + operationName; 
	}
}