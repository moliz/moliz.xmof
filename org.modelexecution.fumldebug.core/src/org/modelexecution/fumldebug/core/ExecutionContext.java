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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.modelexecution.fumldebug.core.impl.ExecutionEventProviderImpl;

import fUML.Library.IntegerFunctions;
import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
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
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ExecutionContext {

	private static ExecutionContext instance = new ExecutionContext();
	
	private ExecutionEventProvider eventprovider;
	
	protected Locus locus = null;
		
	private PrimitiveType typeBoolean = null;
	private PrimitiveType typeInteger = null;
	
	protected Hashtable<String, OpaqueBehavior> opaqueBehaviors = new Hashtable<String, OpaqueBehavior>();
	
	private boolean isDebugMode = false;
	
	protected HashMap<ActivityExecution, List<ActivationConsumedTokens>> enabledActivations = new HashMap<ActivityExecution, List<ActivationConsumedTokens>>(); 
	
	protected HashMap<ActivityExecution, ParameterValueList> activityExecutionOutput = new HashMap<ActivityExecution, ParameterValueList>();
	
	protected HashMap<Integer, ActivityExecution> activityExecutions = new HashMap<Integer, ActivityExecution>(); 
	
	// Data structure for storing set breakpoints
	private HashMap<ActivityNode, Breakpoint> breakpoints = new HashMap<ActivityNode, Breakpoint>();  	
	
	// Determines if the current execution mode is "resume"
	protected boolean isResume = false;
	
	protected ExecutionContext()
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
			
	public ExecutionEventProvider getExecutionEventProvider(){
		if(this.eventprovider == null) {
			this.eventprovider = new ExecutionEventProviderImpl();
		}
		return this.eventprovider;
	}
		
	public ParameterValueList execute(Behavior behavior, Object_ context, ParameterValueList inputs) {
		/*
		 * TODO: This method executes the Behavior using the pure fUML engine
		 * This means that the execution order of the activity nodes is likely to be
		 * different from the execution order when using the debug method. 
		 * So another function should be provided that enables the execution in the
		 * same order as the debug method (if nextStep() is called after every Step event)
		 * Maybe offer possibility to set boolean flag "pureFUML"
		 */
		
		isDebugMode = false;		
		return this.locus.executor.execute(behavior, context, inputs);
	}
	
	public void debug(Behavior behavior, Object_ context, ParameterValueList inputs) {
		isDebugMode = true;
		this.locus.executor.execute(behavior, context, inputs);
	}

	public void nextStep(int executionID) {
		nextStep(executionID, StepDepth.STEP_NODE);
	}
	
	public void nextStep(int executionID, ActivityNode node) {
		nextStep(executionID, StepDepth.STEP_NODE, node);
	}
	
	public void nextStep(int executionID, StepDepth depth) {						
		nextStep(executionID, depth, null);
	}
	
	public void nextStep(int executionID, StepDepth depth, ActivityNode node) {	
		ActivationConsumedTokens nextnode = getNextNode(executionID, node);
		nextStep(nextnode);		
	}			
				
	public void resume(int executionID) {
		isResume = true;
		nextStep(executionID);
	}
	
	private ActivationConsumedTokens getNextNode(int executionID, ActivityNode node) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		List<ActivationConsumedTokens> activationConsumedTokens = enabledActivations.get(activityExecution);
		
		if(activationConsumedTokens.size() == 0) {
			return null;
		}
		
		ActivationConsumedTokens nextnode = null;
		
		if(node == null) {
			nextnode = activationConsumedTokens.remove(0);
		} else {
			for(int i=0; i<activationConsumedTokens.size(); ++i) {
				if(activationConsumedTokens.get(i).getActivation().node == node) {
					nextnode = activationConsumedTokens.remove(i);
				}
			}			
			if(nextnode == null) {
				nextnode = activationConsumedTokens.remove(0);
			}					
		}
		return nextnode;
	}
	
	private void nextStep(ActivationConsumedTokens nextnode) {
		ActivityNodeActivation activation = nextnode.getActivation();
		if(activation instanceof ActionActivation) {
			((ActionActivation)activation).firing = true;
		}	
		activation.fire(nextnode.getTokens());
	}
	
	private void addFunctionBehavior(FunctionBehavior behavior) { 
		opaqueBehaviors.put(behavior.name, behavior);
	}
	
	public OpaqueBehavior getOpaqueBehavior(String name) {
		if(opaqueBehaviors.containsKey(name)) {
			return opaqueBehaviors.get(name);
		}
		return null;
	}
	
	public ExtensionalValueList getExtensionalValues() {
		return locus.extensionalValues;
	}
	
	public void reset() {
		locus.extensionalValues = new ExtensionalValueList();
		this.breakpoints = new HashMap<ActivityNode, Breakpoint>();
		this.enabledActivations = new HashMap<ActivityExecution, List<ActivationConsumedTokens>>(); 		
		this.activityExecutionOutput = new HashMap<ActivityExecution, ParameterValueList>();
		this.activityExecutions = new HashMap<Integer, ActivityExecution>(); 
	}
	
	protected boolean isDebugMode() {
		return isDebugMode;
	}
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		List<ActivationConsumedTokens> activationConsumedTokens = enabledActivations.get(activityExecution);		
	
		List<ActivityNode> nodes = new ArrayList<ActivityNode>();
		
		if(activationConsumedTokens != null) {
			for(int i=0;i<activationConsumedTokens.size();++i) {
				ActivityNode node = activationConsumedTokens.get(i).getActivation().node;
				if(node != null) {					
					nodes.add(node);
				}
			}
		}
		return nodes;
	}
	
	public ParameterValueList getActivityOutput(int executionID) {
		ActivityExecution execution = this.activityExecutions.get(executionID);
		ParameterValueList output = this.activityExecutionOutput.get(execution);
		return output;
	}
	
	/**
	 * Adds a breakpoint to the specified ActivityNode
	 * @param breakpoint Breakpoint that shall be added
	 */
	public void addBreakpoint(Breakpoint breakpoint) {
		if(breakpoint == null) {
			return;
		}
		ActivityNode activitynode = breakpoint.getActivityNode();
		if(activitynode == null || activitynode.activity == null) {
			return;
		}
		
		breakpoints.put(activitynode, breakpoint);			
	}
	
	/**
	 * Provides information if a breakpoint is set for the given ActivityNode
	 * @param activitynode ActivityNode for which shall be checked if a breakpoint is set
	 * @return true if a breakpoint is set for the given ActivityNode, false otherwise
	 */
	public Breakpoint getBreakpoint(ActivityNode activitynode) {		
		if(activitynode == null || activitynode.activity == null) {
			return null;
		}				
		return this.breakpoints.get(activitynode);		
	}
	
	/**
	 * Removes the breakpoint of the given ActivityNode (if one is set)
	 * @param activitynode ActivityNode for which a set breakpoint shall be removed
	 */
	public void removeBreakpoint(Breakpoint breakpoint) {
		if(breakpoint == null) {
			return;
		}
		ActivityNode activitynode = breakpoint.getActivityNode();
		if(activitynode == null || activitynode.activity == null) {
			return;
		}				
		this.breakpoints.remove(activitynode);				
	}
	
}
