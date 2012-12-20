package org.modelexecution.fumldebug.core.behaviorlibrary;

import fUML.Debug;
import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;

public class IntegerLessThanFunctionBehaviorExecution extends
        LibraryBehavior {

    @Override
    public void doBody(
            fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList inputParameters,
            fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList outputParameters) {
    	
    	boolean less = false;
    	
    	if(inputParameters.size() == 2) {
    		if(inputParameters.get(0).values.get(0) instanceof IntegerValue && inputParameters.get(1).values.get(0) instanceof IntegerValue) {
    			int i1 = ((IntegerValue)inputParameters.get(0).values.get(0)).value;
    			int i2 = ((IntegerValue)inputParameters.get(1).values.get(0)).value;
    			less = i1 < i2;
    		}
    	}
    	
    	BooleanValue result = new BooleanValue();
    	result.value = less;
    	result.type = this.locus.factory.getBuiltInType("Boolean");

        Debug.println("[doBody] Integer Less Than, result=" + result.value);

		// Add output to the outputParameters list
		addValueToOutputList(result, outputParameters);
    }
    
	@Override
    public fUML.Semantics.Classes.Kernel.Value new_() {
        // Create a new instance of this kind of function behavior execution.
        return new IntegerLessThanFunctionBehaviorExecution();
    }   

}
