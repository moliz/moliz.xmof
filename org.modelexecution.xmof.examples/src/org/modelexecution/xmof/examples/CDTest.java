package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.NamedElement;
import fUML.Syntax.Classes.Kernel.Property;

public class CDTest implements ExecutionEventListener {
	
	private static final String OBJECT = "Object";
	private static final String OBJECT_NAME = "name";
	private static final String ASSOCIATION = "AssociationConfiguration";
	private static final String ASSOCIATION_NAME = "name";
	private static final String PROPERTY = "PropertyConfiguration";
	private static final String PROPERTY_NAME = "name";

	private static final String ASSOCIATION_validateObjectEnd = "validateObjectEnd";
	private static final String ASSOCIATION_validateMultiplicity = "validateMultiplicity";
	private static final String ASSOCIATION_validateUniqueness = "validateUniqueness";
	private static final String ASSOCIATION_validateValueTypes = "validateValueTypes";
	private static final String ASSOCIATION_validateValueSize = "validateValueSize";
	private static final String ASSOCIATION_validateFeatures = "validateFeatures";
	private static final String ASSOCIATION_validateFeatureValueSize = "validateFeatureValueSize";
	private static final String ASSOCIATION_validateLink = "validateLink";
	private static final String ASSOCIATION_validateLinks_association = "validateLinks_association";
	private static final String ASSOCIATION_validateEnd = "validateEnd";
	private static final String ASSOCIATION_validateOtherEnd = "validateOtherEnd";
	private static final String ASSOCIATION_validate_objectPropertyProperty = "validate_objectPropertyProperty";
	private static final String ASSOCIATION_validate_object_property = "validate_object_property";
	private static final String ASSOCIATION_validate_association = "validate_association";
	private static final String CLASS_validateType_class = "validateType_class";
	private static final String CLASS_isReference = "isReference";
	private static final String OBJECT_conformsTo_object = "conformsTo_object";
	private static final String CLASSIFIER_conformsTo_classifier = "conformsTo_classifier";
	private static final String CLASS_validateValue_class = "validateValue_class";
	private static final String ENUMERATION_validateType_enumeration = "validateType_enumeration";
	private static final String ENUMERATION_isEnumerationValue = "isEnumerationValue";
	private static final String ENUMERATION_validateValue_enumeration = "validateValue_enumeration";
	private static final String ENUMERATION_isValidLiteral = "isValidLiteral";
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
	private static final String PROPERTY_validateUniqueness_featureValue_property = "validateUniqueness_featureValue_property";
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
	
	private static Set<String> allActivities = new HashSet<String>();
	private static Set<String> executedActivities = new HashSet<String>();
	
	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
	}
	
	@BeforeClass
	public static void collectAllActivities() {
		ResourceSet resourceSet = EMFUtil.createResourceSet();
		Resource configuration = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI(CLASSDIAGRAM_CONFIGURATION_PATH));
		for (TreeIterator<EObject> treeIterator = configuration
				.getAllContents(); treeIterator.hasNext();) {
			EObject eObject = treeIterator.next();
			if (eObject instanceof org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) {
				org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity activity = (org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) eObject;
				allActivities.add(activity.getName());
			}
		}
	}	
	
	@AfterClass
	public static void printConfigurationActivityCoverage() {
		System.out.println(executedActivities.size() + "/" + allActivities.size() + " executed");
		System.out.println("not executed:");
		Set<String> notExecutedActivities = new HashSet<String>(allActivities);
		notExecutedActivities.removeAll(executedActivities);
		for(String notExecutedActivity : notExecutedActivities) {
			System.out.println(notExecutedActivity.toString());
		}
	}
	
	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	@After
	public void reset() {
		activityExecutionID = -1;
//		System.out.println("executed nodes: " + nodeCounter);
		nodeCounter = 0;
	}

	@Test
	public void test1_ObjectType_ObjectWithoutType() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test1.xmi");
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
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test2.xmi");
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
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test3.xmi");
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
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test4.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		Set<ActivityExecution> validate_multiplicityElement_executions = getActivityExecutions(trace, MULTIPLICITYELEMENT_validate_multiplicityElement);
		Iterator<ActivityExecution> iterator = validate_multiplicityElement_executions.iterator();
		ActivityExecution validate_multiplicityElement1 = iterator.next();
		ActivityExecution validate_multiplicityElement2 = iterator.next();
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
				validateMultiplicity_property, validate_multiplicityElement1,
				validate_multiplicityElement2, validateValues_property,
				validateValue_property, validate_value_type,
				validateType_primitiveType, isPrimitiveValue,
				validateValue_primitiveType, isStringValue);
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
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test5.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
				
		// FeatureValue for property is missing: Property.hasExactlyOneFeatureValue returns false
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
	
	@Test
	public void test6_PrimitiveValue_ObjectWithWrongPrimitiveFeatureValueType() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test6.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_primitiveType = getActivityExecution(trace, PRIMITIVETYPE_validateType_primitiveType);
		ActivityExecution isPrimitiveValue = getActivityExecution(trace, PRIMITIVEVALUE_isPrimitiveValue);
		ActivityExecution validateValue_primitiveType = getActivityExecution(trace, PRIMITIVETYPE_validateValue_primitiveType);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
			
		// wrong value type (Integer instead of String) was defined for feature value: PrimitiveType.validateType_primitiveType returns false 
		boolean checkValidateType_primitiveType = checkActivityOutput(false, validateType_primitiveType);
		assertTrue(checkValidateType_primitiveType);
		
		// other validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property,
				validateMultiplicity_property, isPrimitiveValue);
		assertTrue(checkTrue_validate_property);
		
		// the property is invalid
		boolean checkProperty = checkActivityOutput(false, validate_value_type,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
		
		// remaining validation activity for Type.validate_value_type has not been executed
		assertNull(validateValue_primitiveType);
		
		// remaining validation activity for Class.validate_object_class has not been executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace have not been executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test7_PrimitiveValue_ObjectWithWrongPrimitiveFeatureValue() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test7.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
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
			
		// wrong value instance (IntegerValue instead of StringValue) was defined for feature value: PrimitiveType.isStringValue returns false 
		boolean checkValidateType_primitiveType = checkActivityOutput(false, isStringValue);
		assertTrue(checkValidateType_primitiveType);
		
		// other validation activity of Type.validate_value_type return true;
		boolean checkTrue_validate_value_type = checkActivityOutput(true, isPrimitiveValue, validateType_primitiveType);
		assertTrue(checkTrue_validate_value_type);
		
		// other validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property,
				validateMultiplicity_property, isPrimitiveValue);
		assertTrue(checkTrue_validate_property);
		
		// the property is invalid
		boolean checkProperty = checkActivityOutput(false, validateValue_primitiveType, validate_value_type,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class has not been executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace have not been executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test8_PrimitiveValue_ObjectWithPrimitiveFeatureValueNotFulfillingMultiplicityLower() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test8.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validate_multiplicityElement = getActivityExecution(trace, MULTIPLICITYELEMENT_validate_multiplicityElement);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// lower bound of 1 is not fulfilled: MultiplicityElement.validate_multiplicityElement returns false 
		boolean checkValidate_multiplicityElement = checkActivityOutput(false, validate_multiplicityElement);
		assertTrue(checkValidate_multiplicityElement);
		
		// other validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);
		
		// remaining validation activity for Property.validate_property has not been executed
		assertNull(validateValues_property);
		
		// the property is invalid
		boolean checkProperty = checkActivityOutput(false, validateMultiplicity_property, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class has not been executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace have not been executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test9_PrimitiveValue_ObjectWithPrimitiveFeatureValueNotFulfillingMultiplicityUpper() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test9.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validate_multiplicityElement = getActivityExecution(trace, MULTIPLICITYELEMENT_validate_multiplicityElement);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// upper bound of 1 is not fulfilled: MultiplicityElement.validate_multiplicityElement returns false 
		boolean checkValidate_multiplicityElement = checkActivityOutput(false, validate_multiplicityElement);
		assertTrue(checkValidate_multiplicityElement);
		
		// other validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);
		
		// remaining validation activity for Property.validate_property has not been executed
		assertNull(validateValues_property);
		
		// the property is invalid
		boolean checkProperty = checkActivityOutput(false, validateMultiplicity_property, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class has not been executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace have not been executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test10_PrimitiveValue_ObjectWithPrimitiveFeatureValueWithoutValueFulfillingMultiplicityLower0() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test10.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validate_multiplicityElement = getActivityExecution(trace, MULTIPLICITYELEMENT_validate_multiplicityElement);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// lower bound of 0 is fulfilled: MultiplicityElement.validate_multiplicityElement returns true 
		boolean checkValidate_multiplicityElement = checkActivityOutput(true, validate_multiplicityElement);
		assertTrue(checkValidate_multiplicityElement);
		
		// all validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateUniqueness_featureValue_property, validateMultiplicity_property, validateValues_property);
		assertTrue(checkTrue_validate_property);
		
		// the property is valid
		boolean checkProperty = checkActivityOutput(true, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true, validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);
		
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test11_PrimitiveValue_ObjectWithMultiplePrimitiveFeatureValueForSameFeature() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test11.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// multiple feature values for same property: Property.hasExactlyOneFeatureValue returns false 
		boolean checkFalse_hasExactlyOneFeatureValue = checkActivityOutput(false, hasExactlyOneFeatureValue);
		assertTrue(checkFalse_hasExactlyOneFeatureValue);
		
		// remaining validation activities for Property.validate_property are not executed
		assertNull(validateUniqueness_featureValue_property);
		assertNull(validateMultiplicity_property);
		assertNull(validateValues_property);
		
		// property is invalid
		boolean checkProperty = checkActivityOutput(false, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class is not executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace are not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
		
	@Test
	public void test12_PrimitiveValue_ObjectWithPrimitiveFeatureValueNotBelogingToClass() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test12.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		// not executed
		
		// object has feature value for feature not belonging to class type: ValueSpace.validateStructuralFeatures return false
		boolean checkFalse_validateStructuralFeatures = checkActivityOutput(false, validateStructuralFeatures);
		assertTrue(checkFalse_validateStructuralFeatures);

		// ValueSpace.hasType returns true
		boolean checkTrue_hasType = checkActivityOutput(true, hasType);
		assertTrue(checkTrue_hasType);
		
		// ValueSpace.validateTypes_valueSpace returns true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true, validateTypes_valueSpace);
		assertTrue(checkTrue_validateTypes_valueSpace);
				
		// remaining validation activity for VaplueSpace.validateTypes_valueSpace is not executed
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main, validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test13_PrimitiveValue_ObjectWithUniqueFeatureValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test13.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateUniqueness_featureValue_values = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_values);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// values of feature value are unique: 
		// Property.validateUniqueness_featureValue_property and Property.validateUniqueness_featureValue_values return true 
		boolean checkTrue_validateUniqueness = checkActivityOutput(true, 
				validateUniqueness_featureValue_property, 
				validateUniqueness_featureValue_values);
		assertTrue(checkTrue_validateUniqueness);
		
		// all validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue,
				validateMultiplicity_property, validateValues_property);
		assertTrue(checkTrue_validate_property);
		
		// property is valid
		boolean checkProperty = checkActivityOutput(true, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true, validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);
		
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main, validate_valueSpace, validateTypes_valueSpace, validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test14_PrimitiveValue_ObjectWithNonUniqueFeatureValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test14.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateUniqueness_featureValue_values = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_values);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// values of feature value are not unique: 
		// Property.validateUniqueness_featureValue_property and Property.validateUniqueness_featureValue_values return false 
		boolean checkTrue_validateUniqueness = checkActivityOutput(false, 
				validateUniqueness_featureValue_property, 
				validateUniqueness_featureValue_values);
		assertTrue(checkTrue_validateUniqueness);
		
		// Property.hasExactlyOneFeatureValue for Property.validate_property returns true
		boolean checkTrue_hasExactlyOneFeatureValue = checkActivityOutput(true,
				hasExactlyOneFeatureValue);
		assertTrue(checkTrue_hasExactlyOneFeatureValue);
		
		// remaining validation activities for Property.validate_property were not executed
		assertNull(validateMultiplicity_property);
		assertNull(validateValues_property);
		
		// property is invalid
		boolean checkProperty = checkActivityOutput(false, validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test15_EnumerationValue_ObjectWithEnumerationValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test15.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_enumeration = getActivityExecution(trace, ENUMERATION_validateType_enumeration);
		Set<ActivityExecution> isEnumerationValue_executions = getActivityExecutions(trace, ENUMERATION_isEnumerationValue);
		assertEquals(2, isEnumerationValue_executions.size());
		Iterator<ActivityExecution> iterator = isEnumerationValue_executions.iterator();
		ActivityExecution isEnumerationValue1 = iterator.next();
		ActivityExecution isEnumerationValue2 = iterator.next();
		ActivityExecution validateValue_enumeration = getActivityExecution(trace, ENUMERATION_validateValue_enumeration);
		ActivityExecution isValidLiteral = getActivityExecution(trace, ENUMERATION_isValidLiteral);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// feature value has valid EnumerationValue
		boolean checkTrue_validate_value_type = checkActivityOutput(true,
				validate_value_type, validateType_enumeration,
				isEnumerationValue1, isEnumerationValue2, validateValue_enumeration, isValidLiteral);
		assertTrue(checkTrue_validate_value_type);
		
		// all validation activities for Property.validate_property returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property,
				validateValues_property, validateValue_property);
		assertTrue(checkTrue_validate_property);
		
		// property is valid
		boolean checkProperty = checkActivityOutput(true, validate_property,
				validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true,
				validateLinks);
		assertTrue(checkTrue_validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true,
				validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);
		
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test16_EnumerationValue_ObjectWithPrimitiveValueforEnumeration() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test16.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_enumeration = getActivityExecution(trace, ENUMERATION_validateType_enumeration);
		ActivityExecution isEnumerationValue = getActivityExecution(trace, ENUMERATION_isEnumerationValue);
		ActivityExecution validateValue_enumeration = getActivityExecution(trace, ENUMERATION_validateValue_enumeration);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// feature value has StringValue instead of EnumerationValue:
		// Enumeration.isEnumerationValue returns false
		boolean checkFalse_isEnumerationValue = checkActivityOutput(false, isEnumerationValue);
		assertTrue(checkFalse_isEnumerationValue);
		
		// Enumeration.validateType_enumeration and Type.validate_value_type return false
		boolean checkFalse_enumeration_type = checkActivityOutput(false, validateType_enumeration, validate_value_type);
		assertTrue(checkFalse_enumeration_type);
	
		// Enumeration.validateValue_enumeration is not executed
		assertNull(validateValue_enumeration);
		
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);
		
		// property is valid
		boolean checkProperty = checkActivityOutput(false, validateValue_property, validateValues_property, validate_property,
				validateStructuralFeatureValues);
		assertTrue(checkProperty);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);
				
		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);
		
		// remaining validation activities for VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test17_EnumerationValue_ObjectWithWrongEnumerationValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test17.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_enumeration = getActivityExecution(trace, ENUMERATION_validateType_enumeration);
		Set<ActivityExecution> isEnumerationValue_executions = getActivityExecutions(trace, ENUMERATION_isEnumerationValue);
		assertEquals(2, isEnumerationValue_executions.size());
		Iterator<ActivityExecution> iterator = isEnumerationValue_executions.iterator();
		ActivityExecution isEnumerationValue1 = iterator.next();
		ActivityExecution isEnumerationValue2 = iterator.next();
		ActivityExecution validateValue_enumeration = getActivityExecution(trace, ENUMERATION_validateValue_enumeration);
		ActivityExecution isValidLiteral = getActivityExecution(trace, ENUMERATION_isValidLiteral);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// EnumerationValue refers to wrong EnumerationLiteral: Enumeration.isValidLiteral returns false
		boolean checkFalse_isValidLiteral = checkActivityOutput(false, isValidLiteral);
		assertTrue(checkFalse_isValidLiteral);
		
		// Enumeration.validateValue_enumeration returns false
		boolean checkFalse_validateValue_enumeration = checkActivityOutput(false, validateValue_enumeration);
		assertTrue(checkFalse_validateValue_enumeration);
		
		// other validation activities for Type.validate_value_type return true
		boolean checkTrue_validate_value_type = checkActivityOutput(true,
				validateType_enumeration,
				isEnumerationValue1, isEnumerationValue2);
		assertTrue(checkTrue_validate_value_type);
		
		// Type.validate_value_type returns false
		boolean checkFalse_validate_value_type = checkActivityOutput(false, validate_value_type);
		assertTrue(checkFalse_validate_value_type);
		
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is invalid
		boolean checkProperty = checkActivityOutput(false,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrueOutputs = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrueOutputs);

		// remaining validation activity for Class.validate_object_class was not
		// executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test18_ObjectValue_ObjectWithObjectReference() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test18.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		Set<ActivityExecution> validateType_class_executions = getActivityExecutions(trace, CLASS_validateType_class);
		Set<ActivityExecution> isReference_executions = getActivityExecutions(trace, CLASS_isReference);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_executions = new HashSet<ActivityExecution>();
		validate_value_type_executions.addAll(validateType_class_executions);
		validate_value_type_executions.addAll(isReference_executions);
		validate_value_type_executions.addAll(validateValue_class_executions);
		validate_value_type_executions.add(validate_value_type);
		ActivityExecution[] validate_value_type_executions_asArray = validate_value_type_executions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								validate_value_type_executions.size()));
		boolean checkTrue_validate_value_type = checkActivityOutput(true,
				validate_value_type_executions_asArray);
		assertTrue(checkTrue_validate_value_type);
				
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is valid
		boolean checkProperty = checkActivityOutput(true,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true, validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test19_ObjectValue_ObjectWithObject() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test19.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_classs = getActivityExecution(trace, CLASS_validateType_class);
		ActivityExecution isReference = getActivityExecution(trace, CLASS_isReference);
		ActivityExecution conformsTo_object_executions = getActivityExecution(trace, OBJECT_conformsTo_object);
		ActivityExecution conformsTo_classifier_executions = getActivityExecution(trace, CLASSIFIER_conformsTo_classifier);
		ActivityExecution validateValue_class = getActivityExecution(trace, CLASS_validateValue_class);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// feature value directly contains referenced object: Class.isReference returns false
		boolean checkFalse_isReference = checkActivityOutput(false, isReference);
		assertTrue(checkFalse_isReference);
		
		// remaining validation activities for Class.validateType_class were not executed
		assertNull(conformsTo_object_executions);
		assertNull(conformsTo_classifier_executions);
		
		// Class.validateValue was not executed
		assertNull(validateValue_class);
		
		// value is invalid: Class.validateType_class and Type.validate_value_type returns false
		boolean checkFalse_valueInvalid = checkActivityOutput(false, validateType_classs, validate_value_type);
		assertTrue(checkFalse_valueInvalid);
						
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is invalid
		boolean checkProperty = checkActivityOutput(false,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test20_ObjectValue_ObjectWithReferenceToObjectWithWrongType() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test20.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		ActivityExecution validateType_classs = getActivityExecution(trace, CLASS_validateType_class);
		ActivityExecution isReference = getActivityExecution(trace, CLASS_isReference);
		ActivityExecution conformsTo_object_executions = getActivityExecution(trace, OBJECT_conformsTo_object);
		ActivityExecution conformsTo_classifier_executions = getActivityExecution(trace, CLASSIFIER_conformsTo_classifier);
		ActivityExecution validateValue_class = getActivityExecution(trace, CLASS_validateValue_class);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// referenced object is of wrong type: Classifier.conformsTo_classifier and Object.conformsTo_object return false
		boolean checkFalse_conformsTo = checkActivityOutput(false, conformsTo_classifier_executions, conformsTo_object_executions);
		assertTrue(checkFalse_conformsTo);
		
		// Class.isReference returns true
		boolean checkTrue_isReference = checkActivityOutput(true, isReference);
		assertTrue(checkTrue_isReference);
		
		// remaining validation activity of Type.validate_value_type is not exeucted
		assertNull(validateValue_class);
		
		// value is invalid: Class.validateType_class and
		// Type.validate_value_type returns false
		boolean checkFalse_valueInvalid = checkActivityOutput(false,
				validateType_classs, validate_value_type);
		assertTrue(checkFalse_valueInvalid);
		
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is invalid
		boolean checkProperty = checkActivityOutput(false,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true,
				hasType, isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class was not
		// executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test21_ObjectValue_ObjectWithObjectReferenceToSubtype() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test21.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		ActivityExecution validateValue_property = getActivityExecution(trace, PROPERTY_validateValue_property);
		
		ActivityExecution validate_value_type = getActivityExecution(trace, TYPE_validate_value_type);
		Set<ActivityExecution> validateType_class_executions = getActivityExecutions(trace, CLASS_validateType_class);
		Set<ActivityExecution> isReference_executions = getActivityExecutions(trace, CLASS_isReference);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		assertEquals(2, validateType_class_executions.size());
		assertEquals(2, isReference_executions.size());
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_executions = new HashSet<ActivityExecution>();
		validate_value_type_executions.addAll(validateType_class_executions);
		validate_value_type_executions.addAll(isReference_executions);
		validate_value_type_executions.addAll(validateValue_class_executions);
		validate_value_type_executions.add(validate_value_type);
		ActivityExecution[] validate_value_type_executions_asArray = validate_value_type_executions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								validate_value_type_executions.size()));
		boolean checkTrue_validate_value_type = checkActivityOutput(true,
				validate_value_type_executions_asArray);
		assertTrue(checkTrue_validate_value_type);
				
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is valid
		boolean checkProperty = checkActivityOutput(true,
				validateValue_property, validateValues_property,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true, validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test22_ObjectValue_ObjectWithUniqueObjectReferences() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test22.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		Set<ActivityExecution> validateValues_property_executions = getActivityExecutions(trace, PROPERTY_validateValues_property);
		Set<ActivityExecution> validateValue_property_executions = getActivityExecutions(trace, PROPERTY_validateValue_property);
		assertEquals(1, validateValues_property_executions.size());
		assertEquals(2, validateValue_property_executions.size());
		
		Set<ActivityExecution> validate_value_type_executions = getActivityExecutions(trace, TYPE_validate_value_type);
		Set<ActivityExecution> validateType_class_executions = getActivityExecutions(trace, CLASS_validateType_class);
		Set<ActivityExecution> isReference_executions = getActivityExecutions(trace, CLASS_isReference);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		assertEquals(2, validate_value_type_executions.size());
		assertEquals(4, validateType_class_executions.size());
		assertEquals(4, isReference_executions.size());
		assertEquals(2, validateValue_class_executions.size());
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_allExecutions = new HashSet<ActivityExecution>();
		validate_value_type_allExecutions.addAll(validateType_class_executions);
		validate_value_type_allExecutions.addAll(isReference_executions);
		validate_value_type_allExecutions.addAll(validateValue_class_executions);
		validate_value_type_allExecutions.addAll(validate_value_type_executions);
		ActivityExecution[] validate_value_type_executions_asArray = validate_value_type_allExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								validate_value_type_allExecutions.size()));
		boolean checkTrue_validate_value_type = checkActivityOutput(true,
				validate_value_type_executions_asArray);
		assertTrue(checkTrue_validate_value_type);
				
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is valid
		Set<ActivityExecution> validateProperty_allExecutions = new HashSet<ActivityExecution>();
		validateProperty_allExecutions.addAll(validateValue_property_executions);
		validateProperty_allExecutions.addAll(validateValues_property_executions);
		validateProperty_allExecutions.add(validate_property);
		validateProperty_allExecutions.add(validateStructuralFeatureValues);
		ActivityExecution[] validateProperty_allExecutions_asArray = validateProperty_allExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								validateProperty_allExecutions.size()));
		boolean checkProperty = checkActivityOutput(true,
				validateProperty_allExecutions_asArray);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true,
				validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test23_ObjectValue_ObjectWithNonUniqueObjectReferences() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test23.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateUniqueness_featureValue_values = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_values);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// referenced objects are not unique: Property.validateUniqueness_featureValue_values returns false
		boolean checkFalse_validateUniqueness_featureValue_values = checkActivityOutput(
				false, validateUniqueness_featureValue_values,
				validateUniqueness_featureValue_property);
		assertTrue(checkFalse_validateUniqueness_featureValue_values);
				 
		// Propery.hasExactlyOneFeatureValue returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue);
		assertTrue(checkTrue_validate_property);
		
		// remaining validation activities for Property.validate_property were not executed
		assertNull(validateMultiplicity_property);
		assertNull(validateValues_property);

		// property is invalid
		boolean checkProperty = checkActivityOutput(false,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test24_EnumerationValue_ObjectWithUniqueEnumerationValues() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test24.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		Set<ActivityExecution> validateValues_property_executions = getActivityExecutions(trace, PROPERTY_validateValues_property);
		Set<ActivityExecution> validateValue_property_executions = getActivityExecutions(trace, PROPERTY_validateValue_property);
		assertEquals(1, validateValues_property_executions.size());
		assertEquals(2, validateValue_property_executions.size());
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// values are valid
		Set<ActivityExecution> validate_value_allExecutions = new HashSet<ActivityExecution>();
		validate_value_allExecutions.addAll(validateValues_property_executions);
		validate_value_allExecutions.addAll(validateValue_property_executions);
		ActivityExecution[] validate_value_allExecutions_asArray = validate_value_allExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								validate_value_allExecutions.size()));
		boolean checkValuesValid = checkActivityOutput(true,
				validate_value_allExecutions_asArray);
		assertTrue(checkValuesValid);
				
		// other validation activities for Property.validate_property
		// returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue, validateMultiplicity_property,
				validateUniqueness_featureValue_property);
		assertTrue(checkTrue_validate_property);

		// property is valid
		boolean checkPropertyValid = checkActivityOutput(true,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkPropertyValid);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class returns true
		boolean checkTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(checkTrue_validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace return true
		boolean checkTrue_validateTypes_valueSpace = checkActivityOutput(true,
				validateStructuralFeatures, validateLinkParticipation);
		assertTrue(checkTrue_validateTypes_valueSpace);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test25_EnumerationValue_ObjectWithNonUniqueEnumerationValues() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test25.xmi");
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
		ActivityExecution validateUniqueness_featureValue_property = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_property);
		ActivityExecution validateUniqueness_featureValue_values = getActivityExecution(trace, PROPERTY_validateUniqueness_featureValue_values);
		ActivityExecution validateMultiplicity_property = getActivityExecution(trace, PROPERTY_validateMultiplicity_property);
		ActivityExecution validateValues_property = getActivityExecution(trace, PROPERTY_validateValues_property);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// enumeration values are not unique: Property.validateUniqueness_featureValue_values returns false
		boolean checkFalse_validateUniqueness_featureValue_values = checkActivityOutput(
				false, validateUniqueness_featureValue_values,
				validateUniqueness_featureValue_property);
		assertTrue(checkFalse_validateUniqueness_featureValue_values);
				 
		// Propery.hasExactlyOneFeatureValue returned true
		boolean checkTrue_validate_property = checkActivityOutput(true,
				hasExactlyOneFeatureValue);
		assertTrue(checkTrue_validate_property);
		
		// remaining validation activities for Property.validate_property were not executed
		assertNull(validateMultiplicity_property);
		assertNull(validateValues_property);

		// property is invalid
		boolean checkProperty = checkActivityOutput(false,
				validate_property, validateStructuralFeatureValues);
		assertTrue(checkProperty);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace, validateTypes_valueSpace,
				validate_object_class);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test26_MemberInheritance_ObjectWithMissingFeatureValueForInheritedProperty() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test26.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		// validation of property 'degree'
		StringValueSpecification property_degree_name_value = createStringValueSpecification("degree");
		PropertyValueSpecification property_degree_name = createPropertyValueSpecification(PROPERTY_NAME, property_degree_name_value);
		InstanceSpecification property_degree = createInstanceSpecification(PROPERTY, property_degree_name);
		ActivityExecution validate_property_degree = getActivityExecution(trace, PROPERTY_validate_property, property_degree);

		// validation of property 'firstname'
		StringValueSpecification property_firstname_name_value = createStringValueSpecification("firstname");
		PropertyValueSpecification property_firstname_name = createPropertyValueSpecification(PROPERTY_NAME, property_firstname_name_value);
		InstanceSpecification property_firstname = createInstanceSpecification(PROPERTY, property_firstname_name);
		ActivityExecution validate_property_firstname = getActivityExecution(trace, PROPERTY_validate_property, property_firstname);
		ActivityExecution hasExactlyOneFeatureValue_firstname = getActivityExecution(trace, PROPERTY_hasExactlyOneFeatureValue, property_firstname);
		
		// validation of property 'lastname'
		StringValueSpecification property_lastname_name_value = createStringValueSpecification("lastname");
		PropertyValueSpecification property_lastname_name = createPropertyValueSpecification(PROPERTY_NAME, property_lastname_name_value);
		InstanceSpecification property_lastname = createInstanceSpecification(PROPERTY, property_lastname_name);
		ActivityExecution validate_property_lastname = getActivityExecution(trace, PROPERTY_validate_property, property_lastname);
		ActivityExecution hasExactlyOneFeatureValue_lastname = getActivityExecution(trace, PROPERTY_hasExactlyOneFeatureValue, property_lastname);

		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// property 'degree' is valid
		boolean validateTrue_validate_property_degree = checkActivityOutput(
				true, validate_property_degree);
		assertTrue(validateTrue_validate_property_degree);
		
		// property 'firstname' is invalid because feature value is missing
		boolean validateFalse_validate_property_firstname = checkActivityOutput(
				false, hasExactlyOneFeatureValue_firstname,
				validate_property_firstname);
		assertTrue(validateFalse_validate_property_firstname);
		
		// property 'lastname' is invalid because feature value is missing
		boolean validateFalse_validate_property_lastname = checkActivityOutput(
				false, hasExactlyOneFeatureValue_lastname,
				validate_property_lastname);
		assertTrue(validateFalse_validate_property_lastname);

		// properties are invalid
		boolean validateFalse_properties = checkActivityOutput(false,
				validateStructuralFeatureValues, validate_object_class,
				validateTypes_valueSpace);
		assertTrue(validateFalse_properties);
		
		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);

		// remaining validation activity for Class.validate_object_class was not executed
		assertNull(validateLinks);

		// remaining validation activities for
		// VaplueSpace.validateTypes_valueSpace were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test27_MemberInheritance_ObjectWithFeatureValueForNotInheritedProperty() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test27.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		// validation of property 'degree'
		StringValueSpecification property_degree_name_value = createStringValueSpecification("degree");
		PropertyValueSpecification property_degree_name = createPropertyValueSpecification(PROPERTY_NAME, property_degree_name_value);
		InstanceSpecification property_degree = createInstanceSpecification(PROPERTY, property_degree_name);
		ActivityExecution validate_property_degree = getActivityExecution(trace, PROPERTY_validate_property, property_degree);

		// validation of property 'firstname'
		StringValueSpecification property_firstname_name_value = createStringValueSpecification("firstname");
		PropertyValueSpecification property_firstname_name = createPropertyValueSpecification(PROPERTY_NAME, property_firstname_name_value);
		InstanceSpecification property_firstname = createInstanceSpecification(PROPERTY, property_firstname_name);
		ActivityExecution validate_property_firstname = getActivityExecution(trace, PROPERTY_validate_property, property_firstname);
		
		// validation of property 'lastname'
		StringValueSpecification property_lastname_name_value = createStringValueSpecification("lastname");
		PropertyValueSpecification property_lastname_name = createPropertyValueSpecification(PROPERTY_NAME, property_lastname_name_value);
		InstanceSpecification property_lastname = createInstanceSpecification(PROPERTY, property_lastname_name);
		ActivityExecution validate_property_lastname = getActivityExecution(trace, PROPERTY_validate_property, property_lastname);

		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// properties 'degree', 'firstname', and 'lastname' are valid
		boolean validateTrue_validate_property = checkActivityOutput(
				true, validate_property_degree, validate_property_firstname, validate_property_lastname);
		assertTrue(validateTrue_validate_property);
		
		boolean validateTrue_properties = checkActivityOutput(true,
				validateStructuralFeatureValues, validate_object_class,
				validateTypes_valueSpace);
		assertTrue(validateTrue_properties);
		
		// remaining validation activity for Class.validate_object_class returns true
		boolean validateTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(validateTrue_validateLinks);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);
				
		// object has feature value for not inherited property 'insuranceNumber':
		// ValueSpace.validateStructuralFeatures returns false
		boolean validateFalse_validateStructuralFeatures = checkActivityOutput(false, validateStructuralFeatures);
		assertTrue(validateFalse_validateStructuralFeatures);

		// remaining validation activity for
		// VaplueSpace.validateTypes_valueSpace was not executed
		assertNull(validateLinkParticipation);

		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test28_MemberInheritance_ObjectWithFeatureValueForInheritedProperty() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test28.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		ActivityExecution hasType = getActivityExecution(trace, VALUESPACE_hasType);
		
		ActivityExecution validateTypes_valueSpace = getActivityExecution(trace, VALUESPACE_validateTypes_valueSpace);
		ActivityExecution validate_object_class = getActivityExecution(trace, CLASS_validate_object_class);
		ActivityExecution isConcrete = getActivityExecution(trace, CLASSIFIER_isConcrete);
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		// validation of property 'degree'
		StringValueSpecification property_degree_name_value = createStringValueSpecification("degree");
		PropertyValueSpecification property_degree_name = createPropertyValueSpecification(PROPERTY_NAME, property_degree_name_value);
		InstanceSpecification property_degree = createInstanceSpecification(PROPERTY, property_degree_name);
		ActivityExecution validate_property_degree = getActivityExecution(trace, PROPERTY_validate_property, property_degree);

		// validation of property 'firstname'
		StringValueSpecification property_firstname_name_value = createStringValueSpecification("firstname");
		PropertyValueSpecification property_firstname_name = createPropertyValueSpecification(PROPERTY_NAME, property_firstname_name_value);
		InstanceSpecification property_firstname = createInstanceSpecification(PROPERTY, property_firstname_name);
		ActivityExecution validate_property_firstname = getActivityExecution(trace, PROPERTY_validate_property, property_firstname);
		
		// validation of property 'lastname'
		StringValueSpecification property_lastname_name_value = createStringValueSpecification("lastname");
		PropertyValueSpecification property_lastname_name = createPropertyValueSpecification(PROPERTY_NAME, property_lastname_name_value);
		InstanceSpecification property_lastname = createInstanceSpecification(PROPERTY, property_lastname_name);
		ActivityExecution validate_property_lastname = getActivityExecution(trace, PROPERTY_validate_property, property_lastname);

		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// properties 'degree', 'firstname', and 'lastname' are valid
		boolean validateTrue_validate_property = checkActivityOutput(
				true, validate_property_degree, validate_property_firstname, validate_property_lastname);
		assertTrue(validateTrue_validate_property);
		
		boolean validateTrue_properties = checkActivityOutput(true,
				validateStructuralFeatureValues, validate_object_class,
				validateTypes_valueSpace);
		assertTrue(validateTrue_properties);
		
		// remaining validation activity for Class.validate_object_class returns true
		boolean validateTrue_validateLinks = checkActivityOutput(true, validateLinks);
		assertTrue(validateTrue_validateLinks);

		// ValueSpace.hasType and Classifier.isConcrete returned true
		boolean checkTrue_hasType_isConcrete = checkActivityOutput(true, hasType,
				isConcrete);
		assertTrue(checkTrue_hasType_isConcrete);
				
		// ValueSpace.validateStructuralFeatures returns true
		boolean validateTrue_validateStructuralFeatures = checkActivityOutput(true, validateStructuralFeatures);
		assertTrue(validateTrue_validateStructuralFeatures);

		// remaining validation activity for
		// VaplueSpace.validateTypes_valueSpace returns true
		boolean validateTrue_validateLinkParticipation = checkActivityOutput(true, validateLinkParticipation);
		assertTrue(validateTrue_validateLinkParticipation);

		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test29_Link_SimpleLink() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test29.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> linkValidationActivityExecutions = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		linkValidationActivityExecutions.addAll(validate_object_property_executions);
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		linkValidationActivityExecutions.addAll(validate_objectPropertyProperty_executions);
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateOtherEnd_executions);
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(2, validateEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateEnd_executions);
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(2, validateLinks_association_executions.size());
		linkValidationActivityExecutions.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(2, validateLink_executions.size());
		linkValidationActivityExecutions.addAll(validateLink_executions);
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(3, isConcrete_executions.size());
		linkValidationActivityExecutions.addAll(isConcrete_executions);
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(2, validateFeatureValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatureValueSize_executions);

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(2, validateFeatures_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatures_executions);
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(2, validateValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateValueSize_executions);
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(2, validateValueTypes_executions.size());
		linkValidationActivityExecutions.addAll(validateValueTypes_executions);
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(2, validateUniqueness_executions.size());
		linkValidationActivityExecutions.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(2, validateMultiplicity_executions.size());
		linkValidationActivityExecutions.addAll(validateMultiplicity_executions);
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(1, validateObjectEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateObjectEnd_executions);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);

		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// validation activities for links returns true
		ActivityExecution[] linkValidationActivityExecutions_asArray = linkValidationActivityExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								linkValidationActivityExecutions.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
		
		// association is fulfilled
		boolean validateTrue_association = checkActivityOutput(true, validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues, validateStructuralFeatures);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// link participation is valid
		boolean validateTrue_linkParticiptation = checkActivityOutput(true, validateLinkParticipation);
		assertTrue(validateTrue_linkParticiptation);
	
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test30_Link_SimpleLinkWithInvalidMultiplicityUpper() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test30.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> trueLinkValidationActivityExecutionsForOtherEnd = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(2, validateLink_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateLink_executions);
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(3, isConcrete_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(isConcrete_executions);
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(2, validateFeatureValueSize_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateFeatureValueSize_executions);

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(2, validateFeatures_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateFeatures_executions);
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(2, validateValueSize_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateValueSize_executions);
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(2, validateValueTypes_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateValueTypes_executions);
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(1, validateUniqueness_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(1, validateMultiplicity_executions.size());
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		// not executed
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		// not executed
		
		// multiplicity 1..1 of containment_container property is not fulfilled:
		// Association.validateMultiplicity returns false
		boolean checkFalse_validateMultiplicity = checkActivityOutput(false, validateMultiplicity_executions.iterator().next());
		assertTrue(checkFalse_validateMultiplicity);
		
		// Association.validateOtherEnd returns false
		boolean checkFalse_validateOtherEnd = checkActivityOutput(false,
				validateOtherEnd_executions.iterator().next(),
				validateEnd_executions.iterator().next());
		assertTrue(checkFalse_validateOtherEnd);
		
		// association is not fulfilled
		boolean checkFalse_associationInvalid = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association,
				validateLinks);
		assertTrue(checkFalse_associationInvalid);
		
		// object end was not validated
		assertEquals(0, validateObjectEnd_executions.size());
		
		// ValueSpace.validateStructuralFeatures and ValueSpace.validateLinkParticipation were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// remaining link validation activities for otherEnd return true
		ActivityExecution[] linkValidationActivityExecutions_asArray = trueLinkValidationActivityExecutionsForOtherEnd
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								trueLinkValidationActivityExecutionsForOtherEnd.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
				
		// objects fulfill properties
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true, validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test31_Link_SimpleLinkWithInvalidMultiplicityLower() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test31.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> trueLinkValidationActivityExecutionsForOtherEnd = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(0, validateLink_executions.size());
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(1, validateUniqueness_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(1, validateMultiplicity_executions.size());
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// multiplicity 1..1 of containment_container property is not fulfilled:
		// Association.validateMultiplicity returns false
		boolean checkFalse_validateMultiplicity = checkActivityOutput(false, validateMultiplicity_executions.iterator().next());
		assertTrue(checkFalse_validateMultiplicity);
		
		// Association.validateOtherEnd returns false
		boolean checkFalse_validateOtherEnd = checkActivityOutput(false,
				validateOtherEnd_executions.iterator().next(),
				validateEnd_executions.iterator().next());
		assertTrue(checkFalse_validateOtherEnd);
		
		// association is not fulfilled
		boolean checkFalse_associationInvalid = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association,
				validateLinks);
		assertTrue(checkFalse_associationInvalid);
		
		// object end was not validated
		assertEquals(0, validateObjectEnd_executions.size());
		
		// ValueSpace.validateStructuralFeatures and ValueSpace.validateLinkParticipation were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// Association.validateLink and Association.validateUniqueness return true
		boolean validateTrue_links = checkActivityOutput(true,
				validateLinks_association_executions.iterator().next(),
				validateUniqueness_executions.iterator().next());
		assertTrue(validateTrue_links);
				
		// objects fulfill properties
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true, validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test32_Link_SimpleLinkWithInvalidUniqueness() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test32.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> trueLinkValidationActivityExecutionsForOtherEnd = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(2, validateLink_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateLink_executions);
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(3, isConcrete_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(isConcrete_executions);
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(2, validateFeatureValueSize_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateFeatureValueSize_executions);

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(2, validateFeatures_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateFeatures_executions);
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(2, validateValueSize_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateValueSize_executions);
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(2, validateValueTypes_executions.size());
		trueLinkValidationActivityExecutionsForOtherEnd.addAll(validateValueTypes_executions);
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(1, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// uniqueness of "elementGroup_element_element" property is not fulfilled:
		// Association.validateUniqueness returns false
		boolean checkFalse_validateUniqueness = checkActivityOutput(false, validateUniqueness_executions.iterator().next());
		assertTrue(checkFalse_validateUniqueness);
		
		// Association.validateOtherEnd returns false
		boolean checkFalse_validateOtherEnd = checkActivityOutput(false,
				validateOtherEnd_executions.iterator().next(),
				validateEnd_executions.iterator().next());
		assertTrue(checkFalse_validateOtherEnd);
		
		// association is not fulfilled
		boolean checkFalse_associationInvalid = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association,
				validateLinks);
		assertTrue(checkFalse_associationInvalid);
		
		// object end was not validated
		assertEquals(0, validateObjectEnd_executions.size());
		
		// ValueSpace.validateStructuralFeatures and ValueSpace.validateLinkParticipation were not executed
		assertNull(validateStructuralFeatures);
		assertNull(validateLinkParticipation);
		
		// remaining link validation activities for otherEnd return true
		ActivityExecution[] linkValidationActivityExecutions_asArray = trueLinkValidationActivityExecutionsForOtherEnd
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								trueLinkValidationActivityExecutionsForOtherEnd.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
				
		// objects fulfill properties
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true, validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test33_Link_ObjectLinkedWithInvalidAssociation() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test33.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> linkValidationActivityExecutions = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		linkValidationActivityExecutions.addAll(validate_object_property_executions);
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		linkValidationActivityExecutions.addAll(validate_objectPropertyProperty_executions);
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateOtherEnd_executions);
		
		// Association.validateEnd is only executed for Association.validateOtherEnd because for
		// Association.validateObjectEnd, no linked objects are found
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateEnd_executions);
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		linkValidationActivityExecutions.addAll(validateLinks_association_executions);
		
		// the validation activities for Association.validateLinks_association are not executed because no matching links exist
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(0, validateLink_executions.size());
				
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(0, validateFeatureValueSize_executions.size());

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(0, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(0, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(0, validateValueTypes_executions.size());
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(1, validateUniqueness_executions.size());
		linkValidationActivityExecutions.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(1, validateMultiplicity_executions.size());
		linkValidationActivityExecutions.addAll(validateMultiplicity_executions);
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(1, validateObjectEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateObjectEnd_executions);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);

		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// validation activities for links returns true
		ActivityExecution[] linkValidationActivityExecutions_asArray = linkValidationActivityExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								linkValidationActivityExecutions.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
		
		// association is fulfilled
		boolean validateTrue_association = checkActivityOutput(true, validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues, validateStructuralFeatures);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// link participation is invalid because existing link has no association set as type
		boolean validateFalse_linkParticiptation = checkActivityOutput(false, validateLinkParticipation);
		assertTrue(validateFalse_linkParticiptation);
	
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test34_Link_MalformedLink_NotExactlyTwoFeatureValues() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test34.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(2, isConcrete_executions.size());
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(1, validateFeatureValueSize_executions.size());

		// remaining validation activities for Association.validateLink are not executed
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(0, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(0, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(0, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// Link has third Feature Value
		// Association.validateFeatureValueSize returns false
		boolean checkFalse_validateFeatureValueSize = checkActivityOutput(false, validateFeatureValueSize_executions.iterator().next());
		assertTrue(checkFalse_validateFeatureValueSize);
		
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test35_Link_MalformedLink_MissingFeatureValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test35.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(2, isConcrete_executions.size());
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(1, validateFeatureValueSize_executions.size());

		
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(1, validateFeatures_executions.size());
		
		// remaining validation activities for Association.validateLink are not executed
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(0, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(0, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// link has feature value for property not belonging to association
		// Association.validateFeatures returns false
		boolean checkFalse_validateFeatureValueSize = checkActivityOutput(false, validateFeatures_executions.iterator().next());
		assertTrue(checkFalse_validateFeatureValueSize);
		
		// Association.validateFeatureValueSize returned true
		boolean checkTrue_validateFeatureValueSize = checkActivityOutput(true, validateFeatureValueSize_executions.iterator().next());
		assertTrue(checkTrue_validateFeatureValueSize);
		
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test36_Link_MalformedLink_MissingValue() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test36.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(2, isConcrete_executions.size());
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(1, validateFeatureValueSize_executions.size());
		
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(1, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(1, validateValueSize_executions.size());
		
		// remaining validation activities for Association.validateLink are not executed
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(0, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// feature value for association end 'elementGroup_element_element' has no value
		// Association.validateValueSize returns false
		boolean checkFalse_validateFeatureValueSize = checkActivityOutput(false, validateValueSize_executions.iterator().next());
		assertTrue(checkFalse_validateFeatureValueSize);
		
		// Association.validateFeatureValueSize and Association.validateFeatures returned true
		boolean checkTrue_validateLink = checkActivityOutput(true,
				validateFeatureValueSize_executions.iterator().next(),
				validateFeatures_executions.iterator().next());
		assertTrue(checkTrue_validateLink);
		
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test37_Link_MalformedLink_WrongValueTypeReferenceToObjectOfWrongType() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test37.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(2, isConcrete_executions.size());
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(1, validateFeatureValueSize_executions.size());
		
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(1, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(1, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(1, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// feature value for association end 'elementGroup_element_element' is of wrong type
		// Association.validateValueTypes returns false
		boolean checkFalse_validateFeatureValueSize = checkActivityOutput(false, validateValueTypes_executions.iterator().next());
		assertTrue(checkFalse_validateFeatureValueSize);
		
		// remaining validation activities for Association.validateLink returned true
		// Association.validateFeatureValueSize and Association.validateFeatures returned true
		boolean checkTrue_validateLink = checkActivityOutput(true,
				validateFeatureValueSize_executions.iterator().next(),
				validateFeatures_executions.iterator().next(),
				validateValueSize_executions.iterator().next());
		assertTrue(checkTrue_validateLink);
		
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test38_Link_MalformedLink_WrongValueTypeContainsObject() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test38.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete);
		assertEquals(2, isConcrete_executions.size());
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(1, validateFeatureValueSize_executions.size());
		
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(1, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(1, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(1, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// feature value for association end 'elementGroup_element_element' is of wrong type
		// Association.validateValueTypes returns false
		boolean checkFalse_validateFeatureValueSize = checkActivityOutput(false, validateValueTypes_executions.iterator().next());
		assertTrue(checkFalse_validateFeatureValueSize);
		
		// remaining validation activities for Association.validateLink returned true
		// Association.validateFeatureValueSize and Association.validateFeatures returned true
		boolean checkTrue_validateLink = checkActivityOutput(true,
				validateFeatureValueSize_executions.iterator().next(),
				validateFeatures_executions.iterator().next(),
				validateValueSize_executions.iterator().next());
		assertTrue(checkTrue_validateLink);
		
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test39_Link_AbstractLink() { 
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test39.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		
		StringValueSpecification association_abstractAssociation_name_value = createStringValueSpecification("abstractAssociation");
		PropertyValueSpecification association_abstractAssociation_name = createPropertyValueSpecification(ASSOCIATION_NAME, association_abstractAssociation_name_value);
		InstanceSpecification association_abstractAssociation = createInstanceSpecification(ASSOCIATION, association_abstractAssociation_name);
		
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property); 
		assertEquals(1, validate_object_property_executions.size()); 
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty);
		assertEquals(1, validate_objectPropertyProperty_executions.size());
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd);
		assertEquals(1, validateOtherEnd_executions.size());
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd);
		assertEquals(1, validateEnd_executions.size());
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association);
		assertEquals(1, validateLinks_association_executions.size());
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink);
		assertEquals(1, validateLink_executions.size());
		
		ActivityExecution isConcrete_abstractAssociation = getActivityExecution(trace, CLASSIFIER_isConcrete, association_abstractAssociation);
		assertNotNull(isConcrete_abstractAssociation);
		
		// remaining validation activities for Association.validateLink are not executed
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize);
		assertEquals(0, validateFeatureValueSize_executions.size());
		
		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures);
		assertEquals(0, validateFeatures_executions.size());
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize);
		assertEquals(0, validateValueSize_executions.size());
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes);
		assertEquals(0, validateValueTypes_executions.size());
		
		// remaining validation activities for Association.validateEnd are not executed
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness);
		assertEquals(0, validateUniqueness_executions.size());
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity);
		assertEquals(0, validateMultiplicity_executions.size());

		// objectEnd is not checked
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd);
		assertEquals(0, validateObjectEnd_executions.size());
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		assertNull(validateStructuralFeatures);
		
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		assertNull(validateLinkParticipation);
		
		// link has abstract association as type
		// Classifier.isConcrete returns false
		boolean checkFalse_isConcrete_abstractAssociation_executions = checkActivityOutput(
				false, isConcrete_abstractAssociation);
		assertTrue(checkFalse_isConcrete_abstractAssociation_executions);
				
		// other end is invalid
		boolean checkFalse_otherEnd = checkActivityOutput(false,
				validateLink_executions.iterator().next(),
				validateLinks_association_executions.iterator().next(),
				validateEnd_executions.iterator().next(),
				validateOtherEnd_executions.iterator().next());
		assertTrue(checkFalse_otherEnd);
				
		// association is not fulfilled
		boolean validateTrue_association = checkActivityOutput(false,
				validate_objectPropertyProperty_executions.iterator().next(),
				validate_object_property_executions.iterator().next(),
				validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// output is false
		boolean checkActivityOutput = checkActivityOutput(false, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test40_Link_LinkWithObjectsOfEndTypeSubTypes_ObjectLinkingItself_ObjectLinkingItselfWithInheritance() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test40.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		
		// association
		StringValueSpecification association_groupElementAssociation_name_value = createStringValueSpecification("groupElementAssociation");
		PropertyValueSpecification association_groupElementAssociation_name = createPropertyValueSpecification(ASSOCIATION_NAME, association_groupElementAssociation_name_value);
		InstanceSpecification association_groupElementAssociation = createInstanceSpecification(ASSOCIATION, association_groupElementAssociation_name);

		// association end1
		StringValueSpecification property_groupElementAssociation_groupElement1_name_value = createStringValueSpecification("groupElementAssociation_groupElement1");
		PropertyValueSpecification property_groupElementAssociation_groupElement1_name = createPropertyValueSpecification(PROPERTY_NAME, property_groupElementAssociation_groupElement1_name_value);
		InstanceSpecification property_groupElementAssociation_groupElement1 = createInstanceSpecification(PROPERTY, property_groupElementAssociation_groupElement1_name);

		// association end2
		StringValueSpecification property_groupElementAssociation_groupElement2_name_value = createStringValueSpecification("groupElementAssociation_groupElement2");
		PropertyValueSpecification property_groupElementAssociation_groupElement2_name = createPropertyValueSpecification(PROPERTY_NAME, property_groupElementAssociation_groupElement2_name_value);
		InstanceSpecification property_groupElementAssociation_groupElement2 = createInstanceSpecification(PROPERTY, property_groupElementAssociation_groupElement2_name);
		
		// obj41a
		StringValueSpecification object_obj40_name_value = createStringValueSpecification("object40");
		PropertyValueSpecification object_obj40_name = createPropertyValueSpecification(OBJECT_NAME, object_obj40_name_value);
		InstanceSpecification object_obj40 = createInstanceSpecification(OBJECT, object_obj40_name);
				
		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association, association_groupElementAssociation);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> linkValidationActivityExecutions = new HashSet<ActivityExecution>();
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property, association_groupElementAssociation); 
		assertEquals(2, validate_object_property_executions.size()); 
		linkValidationActivityExecutions.addAll(validate_object_property_executions);
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty, association_groupElementAssociation);
		assertEquals(2, validate_objectPropertyProperty_executions.size());
		linkValidationActivityExecutions.addAll(validate_objectPropertyProperty_executions);
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation);
		assertEquals(2, validateOtherEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateOtherEnd_executions);
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd, association_groupElementAssociation);
		assertEquals(4, validateEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateEnd_executions);
		// Association.validateEnd is executed for the following parameters
		// (object40, groupElementAssociation_groupElement1, groupElementAssociation_groupElement2) twice
		// (object40, groupElementAssociation_groupElement2, groupElementAssociation_groupElement1) twice
		ParameterValueSpecification parameter_object_obj40 = createParameterValueSpecification("object", object_obj40);
		ParameterValueSpecification parameter_objectEnd_groupElement1 = createParameterValueSpecification("objectEnd", property_groupElementAssociation_groupElement1);
		ParameterValueSpecification parameter_objectEnd_groupElement2 = createParameterValueSpecification("objectEnd", property_groupElementAssociation_groupElement2);
		ParameterValueSpecification parameter_otherEnd_groupElement1 = createParameterValueSpecification("otherEnd", property_groupElementAssociation_groupElement1);
		ParameterValueSpecification parameter_otherEnd_groupElement2 = createParameterValueSpecification("otherEnd", property_groupElementAssociation_groupElement2);
		Set<ActivityExecution> validateEnd_groupElement1_groupElement2 = getActivityExecutions(trace, ASSOCIATION_validateEnd, association_groupElementAssociation, 
				parameter_object_obj40, 
				parameter_objectEnd_groupElement1, 
				parameter_otherEnd_groupElement2);
		Set<ActivityExecution> validateEnd_groupElement2_groupElement1 = getActivityExecutions(trace, ASSOCIATION_validateEnd, association_groupElementAssociation, 
				parameter_object_obj40, 
				parameter_objectEnd_groupElement2, 
				parameter_otherEnd_groupElement1);
		assertEquals(2, validateEnd_groupElement1_groupElement2.size());
		assertEquals(2, validateEnd_groupElement2_groupElement1.size());
		assertFalse(validateEnd_groupElement1_groupElement2.removeAll(validateEnd_groupElement2_groupElement1));
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association, association_groupElementAssociation);
		assertEquals(4, validateLinks_association_executions.size());
		linkValidationActivityExecutions.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink, association_groupElementAssociation);
		assertEquals(4, validateLink_executions.size());
		linkValidationActivityExecutions.addAll(validateLink_executions);
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete, association_groupElementAssociation);
		assertEquals(4, isConcrete_executions.size());
		linkValidationActivityExecutions.addAll(isConcrete_executions);
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize, association_groupElementAssociation);
		assertEquals(4, validateFeatureValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatureValueSize_executions);

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures, association_groupElementAssociation);
		assertEquals(4, validateFeatures_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatures_executions);
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize, association_groupElementAssociation);
		assertEquals(4, validateValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateValueSize_executions);
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes, association_groupElementAssociation);
		assertEquals(4, validateValueTypes_executions.size());
		linkValidationActivityExecutions.addAll(validateValueTypes_executions);
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness, association_groupElementAssociation);
		assertEquals(4, validateUniqueness_executions.size());
		linkValidationActivityExecutions.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity, association_groupElementAssociation);
		assertEquals(4, validateMultiplicity_executions.size());
		linkValidationActivityExecutions.addAll(validateMultiplicity_executions);
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd, association_groupElementAssociation);
		assertEquals(2, validateObjectEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateObjectEnd_executions);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);

		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// validation activities for links returns true
		ActivityExecution[] linkValidationActivityExecutions_asArray = linkValidationActivityExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								linkValidationActivityExecutions.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
		
		// association is fulfilled
		boolean validateTrue_association = checkActivityOutput(true, validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues, validateStructuralFeatures);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// link participation is valid
		boolean validateTrue_linkParticiptation = checkActivityOutput(true, validateLinkParticipation);
		assertTrue(validateTrue_linkParticiptation);
	
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
	@Test
	public void test41_Link_LinkWithObjectsOfEndTypeSubTypes() {
		Trace trace = execute("test/cd/cd1.xmi", "test/cd/cd1parameters_test41.xmi");
		ActivityExecution main = getActivityExecution(trace, MODEL_main);
		ActivityExecution validate_valueSpace = getActivityExecution(trace, VALUESPACE_validate_valueSpace);
		
		ActivityExecution validateStructuralFeatureValues = getActivityExecution(trace, CLASS_validateStructuralFeatureValues);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks);
		
		// association
		StringValueSpecification association_groupElementAssociation_name_value = createStringValueSpecification("groupElementAssociation");
		PropertyValueSpecification association_groupElementAssociation_name = createPropertyValueSpecification(ASSOCIATION_NAME, association_groupElementAssociation_name_value);
		InstanceSpecification association_groupElementAssociation = createInstanceSpecification(ASSOCIATION, association_groupElementAssociation_name);

		ActivityExecution validate_association = getActivityExecution(trace, ASSOCIATION_validate_association, association_groupElementAssociation);
		assertNotNull(validate_association);
				
		Set<ActivityExecution> linkValidationActivityExecutions = new HashSet<ActivityExecution>();
		
		// association end1
		StringValueSpecification property_groupElementAssociation_groupElement1_name_value = createStringValueSpecification("groupElementAssociation_groupElement1");
		PropertyValueSpecification property_groupElementAssociation_groupElement1_name = createPropertyValueSpecification(PROPERTY_NAME, property_groupElementAssociation_groupElement1_name_value);
		InstanceSpecification property_groupElementAssociation_groupElement1 = createInstanceSpecification(PROPERTY, property_groupElementAssociation_groupElement1_name);
		
		// association end2
		StringValueSpecification property_groupElementAssociation_groupElement2_name_value = createStringValueSpecification("groupElementAssociation_groupElement2");
		PropertyValueSpecification property_groupElementAssociation_groupElement2_name = createPropertyValueSpecification(PROPERTY_NAME, property_groupElementAssociation_groupElement2_name_value);
		InstanceSpecification property_groupElementAssociation_groupElement2 = createInstanceSpecification(PROPERTY, property_groupElementAssociation_groupElement2_name); 
		
		Set<ActivityExecution> validate_object_property_executions = getActivityExecutions(trace, ASSOCIATION_validate_object_property, association_groupElementAssociation);
		assertEquals(2, validate_object_property_executions.size()); 
		linkValidationActivityExecutions.addAll(validate_object_property_executions);
		// Association.validate_object_property is once called for end 'groupElementAssociation_groupElement1'
		// and once for end 'groupElementAssociation_groupElement2'
		ParameterValueSpecification parameter_conformingMemberEndSpecification_groupElement1 = createParameterValueSpecification("conformingMemberEnd", property_groupElementAssociation_groupElement1);
		ParameterValueSpecification parameter_conformingMemberEndSpecification_groupElement2 = createParameterValueSpecification("conformingMemberEnd", property_groupElementAssociation_groupElement2);
		ActivityExecution validate_object_property_groupElement1 = getActivityExecution(trace, ASSOCIATION_validate_object_property, association_groupElementAssociation, parameter_conformingMemberEndSpecification_groupElement1); 
		ActivityExecution validate_object_property_groupElement2 = getActivityExecution(trace, ASSOCIATION_validate_object_property, association_groupElementAssociation, parameter_conformingMemberEndSpecification_groupElement2);
		assertNotNull(validate_object_property_groupElement1);
		assertNotNull(validate_object_property_groupElement2);
		assertTrue(validate_object_property_groupElement1 != validate_object_property_groupElement2);
		
		Set<ActivityExecution> validate_objectPropertyProperty_executions = getActivityExecutions(trace, ASSOCIATION_validate_objectPropertyProperty, association_groupElementAssociation);
		assertEquals(2, validate_objectPropertyProperty_executions.size());
		linkValidationActivityExecutions.addAll(validate_objectPropertyProperty_executions);
		
		Set<ActivityExecution> validateOtherEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation);
		assertEquals(2, validateOtherEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateOtherEnd_executions);
		// Association.validateOtherEnd is once called with parameters (obj41a, groupElementAssociation_groupElement1, groupElementAssociation_groupElement2)
		// and once with parameters (obj41a, groupElementAssociation_groupElement2, groupElementAssociation_groupElement1)
		ParameterValueSpecification parameter_objectEnd_groupElement1 = createParameterValueSpecification("objectEnd", property_groupElementAssociation_groupElement1);
		ParameterValueSpecification parameter_objectEnd_groupElement2 = createParameterValueSpecification("objectEnd", property_groupElementAssociation_groupElement2);
		ParameterValueSpecification parameter_otherEnd_groupElement1 = createParameterValueSpecification("otherEnd", property_groupElementAssociation_groupElement1);
		ParameterValueSpecification parameter_otherEnd_groupElement2 = createParameterValueSpecification("otherEnd", property_groupElementAssociation_groupElement2);
		ActivityExecution validateOtherEnd_groupElement1_objectEnd = getActivityExecution(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation, parameter_objectEnd_groupElement1, parameter_otherEnd_groupElement2);
		ActivityExecution validateOtherEnd_groupElement2_objectEnd = getActivityExecution(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation, parameter_objectEnd_groupElement2, parameter_otherEnd_groupElement1);
		assertNotNull(validateOtherEnd_groupElement1_objectEnd);
		assertNotNull(validateOtherEnd_groupElement2_objectEnd);
		assertTrue(validateOtherEnd_groupElement1_objectEnd != validateOtherEnd_groupElement2_objectEnd);		
		
		Set<ActivityExecution> validateEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateEnd, association_groupElementAssociation);
		assertEquals(3, validateEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateEnd_executions);
		// Association.validateEnd is called with the following parameters 
		// (obj41a, groupElementAssociation_groupElement1, groupElementAssociation_groupElement2)
		// (obj41a, groupElementAssociation_groupElement2, groupElementAssociation_groupElement1)
		// (obj41b, groupElementAssociation_groupElement2, groupElementAssociation_groupElement1)		
		// obj41a
		StringValueSpecification object_obj41a_name_value = createStringValueSpecification("object41a");
		PropertyValueSpecification object_obj41a_name = createPropertyValueSpecification(OBJECT_NAME, object_obj41a_name_value);
		InstanceSpecification object_obj41a = createInstanceSpecification(OBJECT, object_obj41a_name);
		ParameterValueSpecification parameter_object_obj41a = createParameterValueSpecification("object", object_obj41a);
		// obj41b
		StringValueSpecification object_obj41b_name_value = createStringValueSpecification("object41b");
		PropertyValueSpecification object_obj41b_name = createPropertyValueSpecification(OBJECT_NAME, object_obj41b_name_value);
		InstanceSpecification object_obj41b = createInstanceSpecification(OBJECT, object_obj41b_name);
		ParameterValueSpecification parameter_object_obj41b = createParameterValueSpecification("object", object_obj41b);
		
		ActivityExecution validateEnd_obj41a_element2_element1 = getActivityExecution(trace, ASSOCIATION_validateEnd, association_groupElementAssociation, 
				parameter_object_obj41a, 
				parameter_objectEnd_groupElement2, 
				parameter_otherEnd_groupElement1);
		ActivityExecution validateEnd_obj41a_element1_element2 = getActivityExecution(trace, ASSOCIATION_validateEnd, association_groupElementAssociation, 
				parameter_object_obj41a, 
				parameter_objectEnd_groupElement1, 
				parameter_otherEnd_groupElement2);
		ActivityExecution validateEnd_obj41b_element2_element1 = getActivityExecution(trace, ASSOCIATION_validateEnd, association_groupElementAssociation, 
				parameter_object_obj41b, 
				parameter_objectEnd_groupElement2, 
				parameter_otherEnd_groupElement1);
		assertNotNull(validateEnd_obj41a_element2_element1);
		assertNotNull(validateEnd_obj41a_element1_element2);
		assertNotNull(validateEnd_obj41b_element2_element1);
		assertTrue(validateEnd_obj41a_element2_element1 != validateEnd_obj41a_element1_element2);
		assertTrue(validateEnd_obj41a_element1_element2 != validateEnd_obj41b_element2_element1);
		assertTrue(validateEnd_obj41b_element2_element1 != validateEnd_obj41a_element2_element1);
		
		Set<ActivityExecution> validateLinks_association_executions = getActivityExecutions(trace, ASSOCIATION_validateLinks_association, association_groupElementAssociation);
		assertEquals(3, validateLinks_association_executions.size());
		linkValidationActivityExecutions.addAll(validateLinks_association_executions);
		
		Set<ActivityExecution> validateLink_executions = getActivityExecutions(trace, ASSOCIATION_validateLink, association_groupElementAssociation);
		assertEquals(2, validateLink_executions.size());
		linkValidationActivityExecutions.addAll(validateLink_executions);
		
		Set<ActivityExecution> isConcrete_executions = getActivityExecutions(trace, CLASSIFIER_isConcrete, association_groupElementAssociation);
		assertEquals(2, isConcrete_executions.size());
		linkValidationActivityExecutions.addAll(isConcrete_executions);
		
		Set<ActivityExecution> validateFeatureValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatureValueSize, association_groupElementAssociation);
		assertEquals(2, validateFeatureValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatureValueSize_executions);

		Set<ActivityExecution> validateFeatures_executions = getActivityExecutions(trace, ASSOCIATION_validateFeatures, association_groupElementAssociation);
		assertEquals(2, validateFeatures_executions.size());
		linkValidationActivityExecutions.addAll(validateFeatures_executions);
		
		Set<ActivityExecution> validateValueSize_executions = getActivityExecutions(trace, ASSOCIATION_validateValueSize, association_groupElementAssociation);
		assertEquals(2, validateValueSize_executions.size());
		linkValidationActivityExecutions.addAll(validateValueSize_executions);
		
		Set<ActivityExecution> validateValueTypes_executions = getActivityExecutions(trace, ASSOCIATION_validateValueTypes, association_groupElementAssociation);
		assertEquals(2, validateValueTypes_executions.size());
		linkValidationActivityExecutions.addAll(validateValueTypes_executions);
		
		Set<ActivityExecution> validateUniqueness_executions = getActivityExecutions(trace, ASSOCIATION_validateUniqueness, association_groupElementAssociation);
		assertEquals(3, validateUniqueness_executions.size());
		linkValidationActivityExecutions.addAll(validateUniqueness_executions);
		
		Set<ActivityExecution> validateMultiplicity_executions = getActivityExecutions(trace, ASSOCIATION_validateMultiplicity, association_groupElementAssociation);
		assertEquals(3, validateMultiplicity_executions.size());
		linkValidationActivityExecutions.addAll(validateMultiplicity_executions);
		
		Set<ActivityExecution> validateObjectEnd_executions = getActivityExecutions(trace, ASSOCIATION_validateObjectEnd, association_groupElementAssociation);
		assertEquals(2, validateObjectEnd_executions.size());
		linkValidationActivityExecutions.addAll(validateObjectEnd_executions);
		// Association.validateObjectEnd is once called with parameters (obj41a, groupElementAssociation_groupElement1, groupElementAssociation_groupElement2)
		// and once with parameters (obj41a, groupElementAssociation_groupElement2, groupElementAssociation_groupElement1)
		ActivityExecution validateObjectEnd_groupElement1_objectEnd = getActivityExecution(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation, parameter_objectEnd_groupElement1, parameter_otherEnd_groupElement2);
		ActivityExecution validateObjectEnd_groupElement2_objectEnd = getActivityExecution(trace, ASSOCIATION_validateOtherEnd, association_groupElementAssociation, parameter_objectEnd_groupElement2, parameter_otherEnd_groupElement1);
		assertNotNull(validateObjectEnd_groupElement1_objectEnd);
		assertNotNull(validateObjectEnd_groupElement2_objectEnd);
		assertTrue(validateObjectEnd_groupElement1_objectEnd != validateObjectEnd_groupElement2_objectEnd);
		
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);

		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);

		// validation activities for links returns true
		ActivityExecution[] linkValidationActivityExecutions_asArray = linkValidationActivityExecutions
				.toArray((ActivityExecution[]) java.lang.reflect.Array
						.newInstance(ActivityExecution.class,
								linkValidationActivityExecutions.size()));
		boolean validateTrue_links = checkActivityOutput(true, linkValidationActivityExecutions_asArray);
		assertTrue(validateTrue_links);
		
		// association is fulfilled
		boolean validateTrue_association = checkActivityOutput(true, validate_association, validateLinks);
		assertTrue(validateTrue_association);
		
		// properties are fulfilled
		boolean validateTrue_validateStructuralFeatureValues = checkActivityOutput(true,validateStructuralFeatureValues, validateStructuralFeatures);
		assertTrue(validateTrue_validateStructuralFeatureValues);		
		
		// link participation is valid
		boolean validateTrue_linkParticiptation = checkActivityOutput(true, validateLinkParticipation);
		assertTrue(validateTrue_linkParticiptation);
	
		// output is true
		boolean checkActivityOutput = checkActivityOutput(true, main,
				validate_valueSpace);
		assertTrue(checkActivityOutput);
	}
	
//	StringValueSpecification property_elementGroup_element_element_name_value = createStringValueSpecification("elementGroup_element_element");
//	PropertyValueSpecification property_elementGroup_element_element_name = createPropertyValueSpecification("name", property_elementGroup_element_element_name_value);
//	InstanceSpecification property_elementGroup_element_element = createInstanceSpecification("PropertyConfiguration", property_elementGroup_element_element_name);

	
	private ActivityExecution getActivityExecution(Trace trace, String activityName) {
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace,
				activityName);
		if(activityExecutions.size() != 1)
			return null;
		return activityExecutions.iterator().next();
	}

	private Set<ActivityExecution> getActivityExecutions(Trace trace, String activityName) {
		Set<ActivityExecution> activityExecutions = new HashSet<ActivityExecution>(); 
		for(ActivityExecution activityExecution : trace.getActivityExecutions()) {
			if(activityExecution.getActivity().name.equals(activityName)) {
				activityExecutions.add(activityExecution);
			}
		}
		return activityExecutions;
	}

	private Set<ActivityExecution> getActivityExecutions(Trace trace, String activityName, InstanceSpecification target) {
		Set<ActivityExecution> result = new HashSet<ActivityExecution>();
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace,
				activityName);
		for(ActivityExecution activityExecution : activityExecutions) {
			Object_ targetObject = getTargetObject(activityExecution);
			if(target.equals(targetObject))
				result.add(activityExecution);
		}
		return result;
	}
	
	private ActivityExecution getActivityExecution(Trace trace, String activityName, InstanceSpecification target, ParameterValueSpecification... parameterValueSpecifications) {
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace,
				activityName, target, parameterValueSpecifications);
		if(activityExecutions.size() != 1)
			return null;
		return activityExecutions.iterator().next();
	}
	
	private Set<ActivityExecution> getActivityExecutions(Trace trace, String activityName, InstanceSpecification target, ParameterValueSpecification... parameterValueSpecifications) {
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace,
				activityName, target);
		return getActivityExecutions(activityExecutions, parameterValueSpecifications);
	}


	private Set<ActivityExecution> getActivityExecutions(
			Set<ActivityExecution> activityExecutions,
			ParameterValueSpecification... parameterValueSpecifications) {
		Set<ActivityExecution> result = new HashSet<ActivityExecution>();
		for(ActivityExecution activityExecution : activityExecutions) {
			if(hasInputs(activityExecution, parameterValueSpecifications))
				result.add(activityExecution);
		}
		return result;
	}
	
	private boolean hasInputs(ActivityExecution activityExecution, ParameterValueSpecification... inputs) {
		for(ParameterValueSpecification parameterValueSpecification : inputs) {
			if(!hasInput(activityExecution, parameterValueSpecification))
				return false;
		}
		return true;
	}
	
	private boolean hasInput(ActivityExecution activityExecution, ParameterValueSpecification input) {
		List<fUML.Semantics.Classes.Kernel.Value> parameterValues = getParameterValues(activityExecution, input.getParameter());
		for (ValueSpecification valueSpecification : input.getValues()) {
			for (fUML.Semantics.Classes.Kernel.Value parameterValue : parameterValues) {
				if(!valueSpecification.equals(parameterValue)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private ActivityExecution getActivityExecution(Trace trace, String activityName, InstanceSpecification target) {
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace, activityName, target);
		if(activityExecutions.size() != 1)
			return null;
		return activityExecutions.iterator().next();
	}
	
	private Object_ getTargetObject(ActivityExecution activityExecution) {
		CallActionExecution callerExecution = activityExecution.getCaller();
		ActivityNode callerNode = callerExecution.getNode();
		if (callerNode instanceof CallOperationAction) {
			CallOperationAction callOperationAction = (CallOperationAction) callerNode;
			for (Input input : callerExecution.getInputs()) {
				if (input.getInputPin() == callOperationAction.target) {
					for (InputValue inputValue : input.getInputValues()) {
						fUML.Semantics.Classes.Kernel.Value targetValue = inputValue
								.getInputValueSnapshot().getValue();
						if (targetValue instanceof Object_) {
							Object_ targetObject = (Object_) targetValue;
							return targetObject;
						}
					}
				}
			}
		}
		return null;
	}
	
	private List<fUML.Semantics.Classes.Kernel.Value> getParameterValues(ActivityExecution activityExecution, String parameter) {
		List<fUML.Semantics.Classes.Kernel.Value> parameterValues = new ArrayList<fUML.Semantics.Classes.Kernel.Value>();
		for(InputParameterSetting activityInput : activityExecution.getActivityInputs()) {
			if(activityInput.getParameter().name.equals(parameter)) {
				for(InputParameterValue parameterValue : activityInput.getParameterValues()) {
					fUML.Semantics.Classes.Kernel.Value value = parameterValue.getValueSnapshot().getValue();
					if(value != null)
						parameterValues.add(value);
				}
			}
		}
		return parameterValues;
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
		vm.getRawExecutionContext().reset();
		return trace;
	}

	private XMOFVirtualMachine createVM(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		ConfigurationObjectMap configurationObjectMap = createConfigurationObjectMap(
				modelResource, configurationResource,
				parameterDefintionResource);

		Resource configurationModelResource = EMFUtil.createResource(
				resourceSet, editingDomain,
				EMFUtil.createFileURI(CONFIGURATIONMODEL_PATH),
				configurationObjectMap.getConfigurationObjects());

		List<ParameterValue> parameterValueConfiguration = XMOFUtil
				.getParameterValueConfiguration(parameterDefintionResource,
						configurationObjectMap);

		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet,
				editingDomain, configurationModelResource,
				parameterValueConfiguration);
		return vm;
	}

	private ConfigurationObjectMap createConfigurationObjectMap(
			Resource modelResource, Resource configurationResource,
			Resource parameterDefintionResource) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationResource,
						modelResource, parameterDefintionResource);
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
	
	@SuppressWarnings("unused")
	private int nodeCounter = 0;
	@Override
	public void notify(Event event) { 
		if (event instanceof ActivityNodeEntryEvent) {
			++nodeCounter;
		}
		if (activityExecutionID == -1 && event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			activityExecutionID = activityEntryEvent.getActivityExecutionID();
		}
		
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			executedActivities.add(activityEntryEvent.getActivity().name);
		}
//		if (event instanceof ActivityEntryEvent) {
//			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
//			debugPrint(activityEntryEvent);
//		}
	}

	@SuppressWarnings("unused")
	private void debugPrint(ActivityEntryEvent activityEntryEvent) {
		ActivityNode caller = null;
		if(activityEntryEvent.getParent() instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) activityEntryEvent.getParent();
			caller = activityNodeEntryEvent.getNode();
		}
		System.out.println(getDebugString(activityEntryEvent.getActivity()) + " (called by " + getDebugString(caller) + ")" + "\n");
	}
	
	private String getDebugString(Activity activity) {
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
	
	private String getDebugString(ActivityNode node) {
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
	
	private InstanceSpecification createInstanceSpecification(String type, PropertyValueSpecification... properties) {
		InstanceSpecification instanceSpecification = new InstanceSpecification();
		instanceSpecification.setType(type);
		instanceSpecification.getValues().addAll(Arrays.asList(properties));
		return instanceSpecification;
	}
	
	private PropertyValueSpecification createPropertyValueSpecification(String property, ValueSpecification... valueSpecifications) {
		PropertyValueSpecification propertyValueSpecification = new PropertyValueSpecification();
		propertyValueSpecification.setProperty(property);
		propertyValueSpecification.getValueSpecifications().addAll(Arrays.asList(valueSpecifications));
		return propertyValueSpecification;
	}
	
	private StringValueSpecification createStringValueSpecification(String value) {
		StringValueSpecification spec = new StringValueSpecification();
		spec.setValue(value);
		return spec;
	}
	
	private ParameterValueSpecification createParameterValueSpecification(String parameter, ValueSpecification... valueSpecifications) {
		ParameterValueSpecification spec = new ParameterValueSpecification();
		spec.setParameter(parameter);
		spec.getValues().addAll(Arrays.asList(valueSpecifications));
		return spec;
	}
	
	private class InstanceSpecification extends ValueSpecification{
		private String type;
		private List<PropertyValueSpecification> values;
		
		public void setType(String type) {
			this.type = type;
		}
		
		public List<PropertyValueSpecification> getValues() {
			if(values == null)
				values = new ArrayList<PropertyValueSpecification>();
			return values;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof InstanceSpecification) 
				return obj == this;
			
			if (obj instanceof Object_) {
				Object_ object = (Object_)obj;
				if(equalsType(object) && equalsProperties(object))
					return true;
			}
			return false;
		}

		private boolean equalsType(Object_ object) {
			for(Class_ type : object.types) {
				if(type.name.equals(this.type)) {
					return true;
				}
			}
			return false;
		}
		
		private boolean equalsProperties(Object_ object) {
			for(PropertyValueSpecification propertySpecification : this.getValues()) {
				FeatureValue featureValue = getFeatureValue(object, propertySpecification);
				if(!propertySpecification.equals(featureValue))
					return false;
			}
			return true;
		}
		
		private FeatureValue getFeatureValue(Object_ object, PropertyValueSpecification propertySpecification) {
			for(FeatureValue featureValue : object.featureValues) {
				if(propertySpecification.equals(featureValue.feature)) {
					return featureValue;
				}
			}
			return null;
		}
	}
	
	private class PropertyValueSpecification {
		private String property;
		private List<ValueSpecification> valueSpecifications;
		
		public void setProperty(String property) {
			this.property = property;
		}
		
		public List<ValueSpecification> getValueSpecifications() {
			if(valueSpecifications == null)
				valueSpecifications = new ArrayList<ValueSpecification>();
			return valueSpecifications;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof PropertyValueSpecification)
				return obj == this;
			
			if(obj instanceof Property) {
				Property property = (Property) obj;
				if(property.name.equals(this.property))
					return true;
			}
			
			if(obj instanceof FeatureValue) {
				FeatureValue featureValue = (FeatureValue) obj;
				if(featureValue.values.size() == getValueSpecifications().size()) {
					for(int i=0;i<getValueSpecifications().size();++i) {
						fUML.Semantics.Classes.Kernel.Value value = featureValue.values.get(i);
						ValueSpecification valueSpecification = getValueSpecifications().get(i);
						if(!valueSpecification.equals(value))
							return false;
					}
				}
				return true;
			}
			
			return false;
		}
	}
	
	private abstract class ValueSpecification {
		
	}
	
	@SuppressWarnings("unused")
	private class IntegerValueSpecification extends ValueSpecification {
		private int value;
		
		public void setValue(int value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof IntegerValue) {
				IntegerValue integerValue = (IntegerValue) obj;
				if(integerValue.value == this.value)
					return true;
			}
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private class BooleanValueSpecification extends ValueSpecification {
		private boolean value;
		
		public void setValue(boolean value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof BooleanValue) {
				BooleanValue booleanValue = (BooleanValue) obj;
				if(booleanValue.value == this.value)
					return true;
			}
			return false;
		}
	}
	
	private class StringValueSpecification extends ValueSpecification {
		private String value;
		
		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof StringValue) {
				StringValue stringValue = (StringValue) obj;
				if(stringValue.value.equals(this.value))
					return true;
			}
			return false;
		}
	}
	
	private class ParameterValueSpecification {
		private String parameter;
		private List<ValueSpecification> values;
		
		public String getParameter() {
			return parameter;
		}
		
		public void setParameter(String parameter) {
			this.parameter = parameter;
		}
		
		public List<ValueSpecification> getValues() {
			if(values == null)
				values = new ArrayList<ValueSpecification>();
			return values;
		}		
	}
}