package org.modelexecution.xmof.diff.test.internal.factory;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ADIBM3561Factory extends ADInputFactory {

	public static final String IBM3561_V1 = "model/ad/IBM/3561-1.xmi";
	public static final String IBM3561_V2 = "model/ad/IBM/3561-2.xmi";
	
	public ADIBM3561Factory(ResourceSet resourceSet) {
		super(resourceSet);
	}

	public EObject createIBM3561V1ParameterValueDefintion(int internal) {
		EObject internalInputValue = createIBM3561V1InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createIBM3561V1InternalValue(int value) {
		EObject internalVariable = getVariable(IBM3561_V1, "i");
		return createIntegerInputValueObject(internalVariable, value);
	}

	public EObject createIBM3561V2ParameterValueDefintion(int internal) {
		EObject internalInputValue = createIBM3561V2InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(
				internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createIBM3561V2InternalValue(int value) {
		EObject internalVariable = getVariable(IBM3561_V2, "i");
		return createIntegerInputValueObject(internalVariable, value);
	}

}
