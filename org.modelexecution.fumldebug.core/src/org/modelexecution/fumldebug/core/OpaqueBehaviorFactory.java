package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.behaviorlibrary.IntegerLessThanFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListGetFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListIndexOfFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListSizeFunctionBehaviorExecution;

import fUML.Library.IntegerFunctionImplementation.IntegerDivideFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerGreaterFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerMinusFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerPlusFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerTimesFunctionBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class OpaqueBehaviorFactory {
	
	private OpaqueBehaviorExecution listgetBehavior;
	private OpaqueBehaviorExecution listsizeBehavior;
	private OpaqueBehaviorExecution addBehavior;
	private OpaqueBehaviorExecution subtractBehavior;
	private OpaqueBehaviorExecution greaterBehavior;
	private OpaqueBehaviorExecution lessBehavior;
	private OpaqueBehaviorExecution multiplyBehavior;
	private OpaqueBehaviorExecution divideBehavior;
	private OpaqueBehaviorExecution listindexofBehavior;
	
	public void initialize() {
		listgetBehavior = createListgetBehavior();
		listsizeBehavior = createListsizeBehavior();
		addBehavior = createAddBehavior();
		subtractBehavior = createSubtractBehavior();
		greaterBehavior = createGreaterBehavior();
		lessBehavior = createLessBehavior();
		multiplyBehavior = createMultiplyBehavior();
		divideBehavior = createDivideBehavior();
		listindexofBehavior = createListIndexOfBehavior();
	}
	
	private OpaqueBehaviorExecution createListIndexOfBehavior() {
		OpaqueBehavior behavior = new OpaqueBehavior();	
		behavior.setName("listindexof");
		Parameter inputlist = createParameter("list", ParameterDirectionKind.in, 0, -1);
		Parameter inputindex = createParameter("object", ParameterDirectionKind.in, 1, 1);
		Parameter output = createParameter("result", ParameterDirectionKind.out, 1, 1);
		behavior.ownedParameter.add(inputlist);
		behavior.ownedParameter.add(inputindex);
		behavior.ownedParameter.add(output);
		
		ListIndexOfFunctionBehaviorExecution execution = new ListIndexOfFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}
	
	private OpaqueBehaviorExecution createMultiplyBehavior() {
		OpaqueBehavior behavior = createBinaryBehavior("multiply");
		
		IntegerTimesFunctionBehaviorExecution execution = new IntegerTimesFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}
	
	private OpaqueBehaviorExecution createDivideBehavior() {
		OpaqueBehavior behavior = createBinaryBehavior("divide");
		
		IntegerDivideFunctionBehaviorExecution execution = new IntegerDivideFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}

	private OpaqueBehaviorExecution createListgetBehavior() {
		OpaqueBehavior behavior = new OpaqueBehavior();	
		behavior.setName("listget");
		Parameter inputlist = createParameter("list", ParameterDirectionKind.in, 0, -1);
		Parameter inputindex = createParameter("index", ParameterDirectionKind.in, 1, 1);
		Parameter output = createParameter("result", ParameterDirectionKind.out, 1, 1);
		behavior.ownedParameter.add(inputlist);
		behavior.ownedParameter.add(inputindex);
		behavior.ownedParameter.add(output);
		
		ListGetFunctionBehaviorExecution listgetexecution = new ListGetFunctionBehaviorExecution();
		listgetexecution.types.add(behavior);
		
		return listgetexecution;
	}
	
	private OpaqueBehaviorExecution createListsizeBehavior() {
		OpaqueBehavior behavior = new OpaqueBehavior();		
		behavior.setName("listsize");
		Parameter inputlist = createParameter("list", ParameterDirectionKind.in, 0, -1);
		Parameter output = createParameter("result", ParameterDirectionKind.out, 1, 1);
		behavior.ownedParameter.add(inputlist);
		behavior.ownedParameter.add(output);
		
		ListSizeFunctionBehaviorExecution listsizeexecution = new ListSizeFunctionBehaviorExecution();
		listsizeexecution.types.add(behavior);
		
		return listsizeexecution;
	}
	
	private OpaqueBehaviorExecution createAddBehavior() {
		OpaqueBehavior behavior = createBinaryBehavior("add");
		
		IntegerPlusFunctionBehaviorExecution execution = new IntegerPlusFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}

	private OpaqueBehaviorExecution createSubtractBehavior() {
		OpaqueBehavior behavior  = createBinaryBehavior("subtract");
		
		IntegerMinusFunctionBehaviorExecution execution = new IntegerMinusFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}
	
	private OpaqueBehaviorExecution createGreaterBehavior() {
		OpaqueBehavior behavior  = createBinaryBehavior("greater");
		
		IntegerGreaterFunctionBehaviorExecution execution = new IntegerGreaterFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}
	
	private OpaqueBehaviorExecution createLessBehavior() {
		OpaqueBehavior behavior  = createBinaryBehavior("less");
		
		IntegerLessThanFunctionBehaviorExecution execution = new IntegerLessThanFunctionBehaviorExecution();
		execution.types.add(behavior);
		
		return execution;
	}
	
	private OpaqueBehavior createBinaryBehavior(String name) {
		OpaqueBehavior behavior = new OpaqueBehavior();		
		behavior.name = name;
		Parameter x = createParameter("x", ParameterDirectionKind.in, 1, 1);
		Parameter y = createParameter("y", ParameterDirectionKind.in, 1, 1);
		Parameter output = createParameter("result", ParameterDirectionKind.out, 1, 1);
		behavior.ownedParameter.add(x);
		behavior.ownedParameter.add(y);
		behavior.ownedParameter.add(output);
		return behavior;
	}
	
	private Parameter createParameter(String name, ParameterDirectionKind direction, int lowerBound, int upperBound) {
		Parameter param = new Parameter();
		param.setName(name);
		param.setDirection(direction);
		param.multiplicityElement.setLower(lowerBound);
		param.multiplicityElement.setUpper(upperBound);
		return param;		
	}

	public OpaqueBehaviorExecution getListgetBehavior() {
		return listgetBehavior;
	}

	public OpaqueBehaviorExecution getListsizeBehavior() {
		return listsizeBehavior;
	}

	public OpaqueBehaviorExecution getAddBehavior() {
		return addBehavior;
	}

	public OpaqueBehaviorExecution getSubtractBehavior() {
		return subtractBehavior;
	}

	public OpaqueBehaviorExecution getGreaterBehavior() {
		return greaterBehavior;
	}

	public OpaqueBehaviorExecution getLessBehavior() {
		return lessBehavior;
	}

	public OpaqueBehaviorExecution getMultiplyBehavior() {
		return multiplyBehavior;
	}

	public OpaqueBehaviorExecution getDivideBehavior() {
		return divideBehavior;
	}

	public OpaqueBehaviorExecution getListindexofBehavior() {
		return listindexofBehavior;
	}
	
}
