package org.modelexecution.xmof.Syntax.Actions.BasicActions.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;

public class ActionInputOutputTest {

	@Test
	public void testAddStructuralFeatureAction() {
		AddStructuralFeatureValueAction addFeatureValue = IntermediateActionsFactory.eINSTANCE
				.createAddStructuralFeatureValueAction();
		addFeatureValue.setInsertAt(createInputPin("insertAt"));
		addFeatureValue.setObject(createInputPin("object"));
		addFeatureValue.setValue(createInputPin("value"));
		addFeatureValue.setResult(createOutputPin("result"));
		assertEquals(1, addFeatureValue.getOutput().size());
		assertEquals(3, addFeatureValue.getInput().size());
	}

	private InputPin createInputPin(String name) {
		InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputPin.setName(name);
		return inputPin;
	}
	
	private OutputPin createOutputPin(String name) {
		OutputPin outputPin = BasicActionsFactory.eINSTANCE.createOutputPin();
		outputPin.setName(name);
		return outputPin;
	}

}
