package org.modelexecution.fumldebug.core.behaviorlibrary;

import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

public abstract class LibraryBehavior extends OpaqueBehaviorExecution {

	public LibraryBehavior() {
		super();
	}

	protected void addValueToOutputList(Value value, ParameterValueList outputParameters) {
		
		// The ParameterValue has already been created and added to the ParameterValueList.
		// Retrieve the first ParameterValue element in this list.
		ParameterValue parameterValue = outputParameters.getValue(0);
	
		// Create a new ValueList and add the value that is passed in as an argument
		ValueList valueList = new ValueList();
		valueList.add(value);
		
		// Connect the ParameterValue list to the ParameterValue
		parameterValue.values = valueList;		
	}

	protected void addEmptyValueListToOutputList(ParameterValueList outputParameters) {
		
		// The ParameterValue has already been created and added to the ParameterValueList.
		// Retrieve the first ParameterValue element in this list.
		ParameterValue parameterValue = outputParameters.getValue(0);
	
		// Create a new ValueList and leave it empty
		ValueList valueList = new ValueList();
		
		// Connect the empty ParameterValue list to the ParameterValue
		parameterValue.values = valueList;
	}

}