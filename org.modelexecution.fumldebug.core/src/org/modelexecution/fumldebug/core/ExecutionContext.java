/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.modelexecution.fumldebug.core.impl.ExecutionEventProviderImpl;

import fUML.Library.IntegerFunctions;
import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
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
	private PrimitiveType typeInteger = null;
	
	private Hashtable<String, FunctionBehavior> functionBehaviors = new Hashtable<String, FunctionBehavior>();
	
	private boolean isDebugMode = false;
	
	private List<ActivationConsumedTokens> activationConsumedTokens = new ArrayList<ActivationConsumedTokens>();
		
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
		this.createPrimitiveType("String");
		typeInteger = this.createPrimitiveType("Integer");
		this.createPrimitiveType("UnlimitedNatural");
		
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
		activationConsumedTokens = new ArrayList<ActivationConsumedTokens>();
		this.locus.executor.execute(behavior, context, inputs);
	}
	
	public void nextStep() {
		nextStep(StepDepth.STEP_NODE);
	}
	
	public void nextStep(ActivityNode node) {
		nextStep(StepDepth.STEP_NODE, node);
	}
	
	public void nextStep(StepDepth depth, ActivityNode node) {
		if(activationConsumedTokens.size() == 0) {
			return;
		}			
		ActivationConsumedTokens nextnode = null;
		for(int i=0;i<activationConsumedTokens.size();++i) {
			if(activationConsumedTokens.get(i).activation.node == node) {
				nextnode = activationConsumedTokens.remove(i);
			}
		}
		
		if(nextnode == null) {
			nextnode = activationConsumedTokens.remove(0);
		}		
		if(nextnode.getActivation() instanceof ActionActivation) {
			((ActionActivation)nextnode.getActivation()).firing = true;
		}
		
		nextnode.getActivation().fire(nextnode.getTokens());
	}			
	
	public void nextStep(StepDepth depth) {
		if(activationConsumedTokens.size() == 0) {
			return;
		}				
		ActivationConsumedTokens node = activationConsumedTokens.remove(0);
		ActivityNodeActivation activation = node.activation;
		if(activation instanceof ActionActivation) {
			((ActionActivation)activation).firing = true;
		}	
		activation.fire(node.getTokens());
	}		
	
	private void addFunctionBehavior(FunctionBehavior behavior) { 
		functionBehaviors.put(behavior.name, behavior);
	}
	
	public FunctionBehavior getFunctionBehavior(String name) {
		if(functionBehaviors.containsKey(name)) {
			return functionBehaviors.get(name);
		}
		return null;
	}
	
	public ExtensionalValueList getExtensionalValues() {
		return locus.extensionalValues;
	}
	
	public void reset() {
		locus.extensionalValues = new ExtensionalValueList();
	}
	
	protected boolean isDebugMode() {
		return isDebugMode;
	}
	
	protected void addEnabledActivityNodeActivation(ActivityNodeActivation activation, TokenList tokens) {
		activationConsumedTokens.add(new ActivationConsumedTokens(activation, tokens));
		if(activation instanceof ActionActivation) {
			((ActionActivation)activation).firing = false;
		}		
	}

	protected void addEnabledActivityNodeActivation(int position, ActivityNodeActivation activation, TokenList tokens) {
		activationConsumedTokens.add(position, new ActivationConsumedTokens(activation, tokens));
		if(activation instanceof ActionActivation) {
			((ActionActivation)activation).firing = false;
		}
	}
	
	private class ActivationConsumedTokens {
		private ActivityNodeActivation activation;
		private TokenList tokens;
		
		public ActivationConsumedTokens(ActivityNodeActivation activation, TokenList tokens) {
			this.activation = activation;
			this.tokens = tokens;
		}
		
		public ActivityNodeActivation getActivation() {
			return activation;
		}
		
		public TokenList getTokens() {
			return tokens;
		}
	}
		
	public List<ActivityNode> getEnabledNodes() {
		List<ActivityNode> nodes = new ArrayList<ActivityNode>();
		for(int i=0;i<activationConsumedTokens.size();++i) {
			ActivityNode node = activationConsumedTokens.get(i).activation.node;
			if(node != null) {					
				nodes.add(node);
			}
		}
		return nodes;
	}
}
