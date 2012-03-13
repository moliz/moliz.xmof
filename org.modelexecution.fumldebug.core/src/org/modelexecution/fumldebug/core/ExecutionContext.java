package org.modelexecution.fumldebug.core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.modelexecution.fumldebug.core.impl.ExecutionEventProviderImpl;

import fUML.Library.IntegerFunctions;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.FirstChoiceStrategy;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior;

public class ExecutionContext {

	private static ExecutionContext instance = new ExecutionContext();
	
	private ExecutionEventProvider eventprovider;
	
	private Locus locus = null;
		
	private PrimitiveType typeBoolean = null;
	private PrimitiveType typeString = null;
	private PrimitiveType typeInteger = null;
	private PrimitiveType typeUnlimitedNatural = null;
	
	private Hashtable<String, FunctionBehavior> functionBehaviors;
	
	private boolean isDebugMode = false;
	
	private List<ActivityNode> enabledNodes = new ArrayList<ActivityNode>();
	private List<ActivityNodeActivation> enabledActivityNodeActivations = new ArrayList<ActivityNodeActivation>();
	
	private ExecutionContext()
	{
		/*
		 * Locus initialization
		 */
		this.locus = new Locus();
		this.locus.setFactory(new ExecutionFactoryL3());  // Uses local subclass for ExecutionFactory
		this.locus.setExecutor(new Executor());

		this.locus.factory.setStrategy(new RedefinitionBasedDispatchStrategy());
		this.locus.factory.setStrategy(new FIFOGetNextEventStrategy());
		this.locus.factory.setStrategy(new FirstChoiceStrategy());
	
		typeBoolean = this.createPrimitiveType("Boolean");
		typeString = this.createPrimitiveType("String");
		typeInteger = this.createPrimitiveType("Integer");
		typeUnlimitedNatural = this.createPrimitiveType("UnlimitedNatural");
		
		/*
		 * Initialization of primitive behaviors 
		 */
		IntegerFunctions integerFunctions = new IntegerFunctions(typeInteger, typeBoolean, this.locus.factory);
		addFunctionBehavior(integerFunctions.integerGreater);
	}	
	
	public static ExecutionContext getInstance(){
		return instance;
	}
	
	private PrimitiveType createPrimitiveType(String name) {
		PrimitiveType type = new PrimitiveType();
		type.name = name;
		this.locus.factory.addBuiltInType(type);
		return type;
	}
		
	/**
	 * TODO
	 * Does it make sense to create one event provider per activity execution
	 * and to maintain a dictionary or something for active executions?
	 * @return
	 */
	public ExecutionEventProvider getExecutionEventProvider(){
		if(this.eventprovider == null) {
			this.eventprovider = new ExecutionEventProviderImpl();
		}
		return this.eventprovider;
	}
		
	public ParameterValueList execute(Behavior behavior, Object_ context, ParameterValueList inputs) {
		isDebugMode = false;
		return this.locus.executor.execute(behavior, context, inputs);
	}
	
	public void debug(Behavior behavior, Object_ context, ParameterValueList inputs) {
		isDebugMode = true;
		enabledNodes = new ArrayList<ActivityNode>();
		enabledActivityNodeActivations = new ArrayList<ActivityNodeActivation>();
		this.locus.executor.execute(behavior, context, inputs);
	}
	
	public void nextStep() {
		if(enabledActivityNodeActivations.size() == 0) {
			return;
		}	
		System.out.println("ExecutionContext nextStep ActivityExecution types size=" + enabledActivityNodeActivations.get(0).getActivityExecution().types.size());
		ActivityNodeActivation activation = enabledActivityNodeActivations.remove(0);
		System.out.println("ExecutionContext nextStep ActivityExecution types size=" + activation.getActivityExecution().types.size());
		activation.receiveOffer();
	}		
	
	private void addFunctionBehavior(FunctionBehavior behavior) {
		functionBehaviors = new Hashtable<String, FunctionBehavior>();
		functionBehaviors.put(behavior.name, behavior);
	}
	
	public FunctionBehavior getFunctionBehavior(String name) {
		if(functionBehaviors.containsKey(name)) {
			return functionBehaviors.get(name);
		}
		return null;
	}
	
	public enum FunctionBehaviors {
		;
		
		public enum Integer {
			IntegerGreater
		}
	}
	
	public ExtensionalValueList getExtensionalValues() {
		return locus.extensionalValues;
	}
	
	public void reset() {
		locus.extensionalValues = new ExtensionalValueList();
	}
	
	public boolean isDebugMode() {
		return isDebugMode;
	}
	
	public List<ActivityNode> getEnabledNodes() {
		return enabledNodes;
	}
	
	public void addEnabledActivityNodeActivation(ActivityNodeActivation activation) {
		System.out.println("ExecutionContext addEnabledActivityNodeActivation ActivityExecution types size=" + activation.getActivityExecution().types.size());
		enabledActivityNodeActivations.add(activation);
		System.out.println("ExecutionContext addEnabledActivityNodeActivation ActivityExecution types size=" + activation.getActivityExecution().types.size());
		enabledNodes.add(activation.node);
		System.out.println("ExecutionContext addEnabledActivityNodeActivation ActivityExecution types size=" + activation.getActivityExecution().types.size());
	}
}
