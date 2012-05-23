package org.modelexecution.fumldebug.core;

import fUML.Debug;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

public class Return5BehaviorExecution extends OpaqueBehaviorExecution{
	
	public Return5BehaviorExecution() {
		
	}

	public void doBody(ParameterValueList inputParameters,
			ParameterValueList outputParameters) {		
		IntegerValue result = new IntegerValue();
		result.value = 5;

		Debug.println("[doBody] result = " + result.value);

		ValueList values = new ValueList();
		values.addValue(result);
		outputParameters.getValue(0).values = values;
	}

	public Value new_() {
		return new Return5BehaviorExecution();
	}
}
