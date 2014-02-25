package org.modelexecution.xmof.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
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

	private static final String PROPERTY = "PropertyConfiguration";
	private static final String PROPERTY_NAME = "name";
	
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
		Set<ActivityExecution> conformsTo_object_executions = getActivityExecutions(trace, OBJECT_conformsTo_object);
		Set<ActivityExecution> conformsTo_classifier_executions = getActivityExecutions(trace, CLASSIFIER_conformsTo_classifier);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_executions = new HashSet<ActivityExecution>();
		validate_value_type_executions.addAll(validateType_class_executions);
		validate_value_type_executions.addAll(isReference_executions);
		validate_value_type_executions.addAll(conformsTo_object_executions);
		validate_value_type_executions.addAll(conformsTo_classifier_executions);
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
		Set<ActivityExecution> conformsTo_object_executions = getActivityExecutions(trace, OBJECT_conformsTo_object);
		Set<ActivityExecution> conformsTo_classifier_executions = getActivityExecutions(trace, CLASSIFIER_conformsTo_classifier);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		assertEquals(2, validateType_class_executions.size());
		assertEquals(2, isReference_executions.size());
		assertEquals(2, conformsTo_object_executions.size());
		assertEquals(2, conformsTo_classifier_executions.size());
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_executions = new HashSet<ActivityExecution>();
		validate_value_type_executions.addAll(validateType_class_executions);
		validate_value_type_executions.addAll(isReference_executions);
		validate_value_type_executions.addAll(conformsTo_object_executions);
		validate_value_type_executions.addAll(conformsTo_classifier_executions);
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
		Set<ActivityExecution> conformsTo_object_executions = getActivityExecutions(trace, OBJECT_conformsTo_object);
		Set<ActivityExecution> conformsTo_classifier_executions = getActivityExecutions(trace, CLASSIFIER_conformsTo_classifier);
		Set<ActivityExecution> validateValue_class_executions = getActivityExecutions(trace, CLASS_validateValue_class);
		assertEquals(2, validate_value_type_executions.size());
		assertEquals(4, validateType_class_executions.size());
		assertEquals(4, isReference_executions.size());
		assertEquals(4, conformsTo_object_executions.size());
		assertEquals(4, conformsTo_classifier_executions.size());
		assertEquals(2, validateValue_class_executions.size());
		
		ActivityExecution validateLinks = getActivityExecution(trace, CLASS_validateLinks); 
		ActivityExecution validateStructuralFeatures = getActivityExecution(trace, VALUESPACE_validateStructuralFeatures);
		ActivityExecution validateLinkParticipation = getActivityExecution(trace, VALUESPACE_validateLinkParticipation);
		
		// All validation activities for Type.validate_value_type return true
		Set<ActivityExecution> validate_value_type_allExecutions = new HashSet<ActivityExecution>();
		validate_value_type_allExecutions.addAll(validateType_class_executions);
		validate_value_type_allExecutions.addAll(isReference_executions);
		validate_value_type_allExecutions.addAll(conformsTo_object_executions);
		validate_value_type_allExecutions.addAll(conformsTo_classifier_executions);
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
		if(result.size() > 0)
			return result;
		return null;
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
								.getInputObjectToken().getTransportedValue()
								.getRuntimeValue();
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
		vm.getRawExecutionContext().reset();
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
	
	private class InstanceSpecification extends ValueSpecification{
		private String type;
		private List<PropertyValueSpecification> values;
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public List<PropertyValueSpecification> getValues() {
			if(values == null)
				values = new ArrayList<PropertyValueSpecification>();
			return values;
		}
		
		public void addValue(PropertyValueSpecification value) {
			values.add(value);
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
		
		public String getProperty() {
			return property;
		}
		
		public void setProperty(String property) {
			this.property = property;
		}
		
		public List<ValueSpecification> getValueSpecifications() {
			if(valueSpecifications == null)
				valueSpecifications = new ArrayList<ValueSpecification>();
			return valueSpecifications;
		}
		
		public void addValueSpecification(ValueSpecification value) {
			valueSpecifications.add(value);
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
	
	private class IntegerValueSpecification extends ValueSpecification {
		private int value;
		
		public int getValue() {
			return value;
		}
		
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
	
	private class BooleanValueSpecification extends ValueSpecification {
		private boolean value;
		
		public boolean getValue() {
			return value;
		}
		
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
		
		public String getValue() {
			return value;
		}
		
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
}