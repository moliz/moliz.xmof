package org.modelexecution.xmof.diff.test.internal.factory;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ADAnonFactory extends ADInputFactory {

	public static final String ANON_V1 = "model/ad/anonCompany/ExampleB/ExampleBV1.xmi";
	public static final String ANON_V2 = "model/ad/anonCompany/ExampleB/ExampleBV2.xmi";
	public static final String ANON_V3 = "model/ad/anonCompany/ExampleB/ExampleBV3.xmi";

	public ADAnonFactory(ResourceSet resourceSet) {
		super(resourceSet);
	}

	public EObject createAnonV1ParameterValueDefintion(boolean exists,
			boolean found) {
		EObject existsInputValue = createAnonV1ExistsValue(exists);
		EObject foundInputValue = createAnonV1FoundValue(found);
		EObject parameterValueDefinition = createParameterValueDefinition(
				existsInputValue, foundInputValue);
		return parameterValueDefinition;
	}

	private EObject createAnonV1ExistsValue(boolean value) {
		EObject existsVariable = getVariable(ANON_V1, "exists");
		return createBooleanInputValueObject(existsVariable, value);
	}

	private EObject createAnonV1FoundValue(boolean value) {
		EObject foundVariable = getVariable(ANON_V1, "found");
		return createBooleanInputValueObject(foundVariable, value);
	}

	public EObject createAnonV2ParameterValueDefintion(boolean exists,
			boolean found) {
		EObject existsInputValue = createAnonV2ExistsValue(exists);
		EObject foundInputValue = createAnonV2FoundValue(found);
		EObject parameterValueDefinition = createParameterValueDefinition(
				existsInputValue, foundInputValue);
		return parameterValueDefinition;
	}

	private EObject createAnonV2ExistsValue(boolean value) {
		EObject existsVariable = getVariable(ANON_V2, "exists");
		return createBooleanInputValueObject(existsVariable, value);
	}

	private EObject createAnonV2FoundValue(boolean value) {
		EObject foundVariable = getVariable(ANON_V2, "found");
		return createBooleanInputValueObject(foundVariable, value);
	}

	public EObject createAnonV3ParameterValueDefintion(boolean exists,
			boolean found, boolean acc) {
		EObject existsInputValue = createAnonV3ExistsValue(exists);
		EObject foundInputValue = createAnonV3FoundValue(found);
		EObject accInputValue = createAnonV3AccValue(acc);
		EObject parameterValueDefinition = createParameterValueDefinition(
				existsInputValue, foundInputValue, accInputValue);
		return parameterValueDefinition;
	}

	private EObject createAnonV3ExistsValue(boolean value) {
		EObject existsVariable = getVariable(ANON_V3, "exists");
		return createBooleanInputValueObject(existsVariable, value);
	}

	private EObject createAnonV3FoundValue(boolean value) {
		EObject foundVariable = getVariable(ANON_V3, "found");
		return createBooleanInputValueObject(foundVariable, value);
	}
	
	private EObject createAnonV3AccValue(boolean value) {
		EObject accVariable = getVariable(ANON_V3, "acc");
		return createBooleanInputValueObject(accVariable, value);
	}

}
