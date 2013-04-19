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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.FirstChoiceStrategy;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ExecutionContext implements ExecutionEventProvider{

	protected static final String exception_illegalexecutionid = "Illegal execution id.";
	protected static final String exception_noenablednodes = "No enabled nodes available.";
	protected static final String exception_illegalactivitynode = "Illegal activity node. Activity node is not enabled.";
	
	private static ExecutionContext instance = new ExecutionContext();
	
	private Collection<ExecutionEventListener> listeners = new HashSet<ExecutionEventListener>();
	
	private Locus locus = null;
		
	private Hashtable<String, OpaqueBehavior> opaqueBehaviors = new Hashtable<String, OpaqueBehavior>();
	
	private NodeSelectionStrategy nextNodeStrategy = new NodeSelectionStrategyImpl(); 
	
	private HashMap<Integer, ParameterValueList> activityExecutionOutput = new HashMap<Integer, ParameterValueList>();
		
	private HashMap<ActivityNode, Breakpoint> breakpoints = new HashMap<ActivityNode, Breakpoint>();  					
	 
	protected ExecutionStatus executionStatus = new ExecutionStatus(); //TODO protected because of aspect
	
	protected TraceHandler traceHandler = new TraceHandler();
	
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
	
		this.createPrimitiveType("Boolean");
		this.createPrimitiveType("String");
		this.createPrimitiveType("Integer");
		this.createPrimitiveType("UnlimitedNatural");
		
		initializeProvidedBehaviors();
	}	
	
	private void initializeProvidedBehaviors() {
		OpaqueBehaviorFactory behaviorFacotry = new OpaqueBehaviorFactory();
		behaviorFacotry.initialize();
		
		addOpaqueBehavior(behaviorFacotry.getListgetBehavior());
		addOpaqueBehavior(behaviorFacotry.getListsizeBehavior());
		addOpaqueBehavior(behaviorFacotry.getAddBehavior());
		addOpaqueBehavior(behaviorFacotry.getSubtractBehavior());
		addOpaqueBehavior(behaviorFacotry.getGreaterBehavior());
		addOpaqueBehavior(behaviorFacotry.getLessBehavior());
		addOpaqueBehavior(behaviorFacotry.getMultiplyBehavior());
		addOpaqueBehavior(behaviorFacotry.getDivideBehavior());
		addOpaqueBehavior(behaviorFacotry.getListindexofBehavior());
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
					
	public void execute(Behavior behavior, Object_ context, ParameterValueList inputs) {
		if(inputs == null) {
			inputs = new ParameterValueList();
		}		
		this.locus.executor.execute(behavior, context, inputs);		
	}
	
	public void executeStepwise(Behavior behavior, Object_ context, ParameterValueList inputs) {
		if(inputs == null) {
			inputs = new ParameterValueList();
		}
		this.locus.executor.execute(behavior, context, inputs);
	}
	
	public void nextStep(int executionID) throws IllegalArgumentException  {
		nextStep(executionID, null);
	}
	
	/**
	 * Performs the next execution step in the activity execution with the given ID 
	 * by executing the provided node 
	 * @param executionID ID of the activity execution for which the next step shall be performed
	 * @param node activity node which shall be executed in the next step
	 * @throws IllegalArgumentException if the executionID is invalid or the provided node is invalid (i.e., null or not enabled in this execution)
	 */
	public void nextStep(int executionID, ActivityNode node) throws IllegalArgumentException {
		ActivityNodeChoice nextnode = null;
		
		if(node == null) {
			nextnode = getNextNode(executionID);
		} else {
			nextnode = new ActivityNodeChoice(executionID, node);
		}
		
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(nextnode.getExecutionID());
		
		boolean activityNodeWasEnabled = activityExecutionStatus.isNodeEnabled(nextnode.getActivityNode());
		if(!activityNodeWasEnabled) {
			throw new IllegalArgumentException(exception_illegalactivitynode);
		}

		activityExecutionStatus.addExecutingActivation(nextnode.getActivityNode());
		ActivityNodeActivation activation = activityExecutionStatus.getEnabledActivation(nextnode.getActivityNode());
		TokenList tokens = activityExecutionStatus.getTokens(nextnode.getActivityNode());
		
		if(activation == null || tokens == null) {
			activityExecutionStatus.removeExecutingActivation(nextnode.getActivityNode());
			throw new IllegalArgumentException(exception_noenablednodes); 
		}
		
		activation.fire(tokens);		
		
		if(executionStatus.isExecutionRunning(executionID) && activityExecutionStatus.isInResumeMode()) {
			nextStep(executionID);
		}
	}			
	
	private ActivityNodeChoice getNextNode(int executionID) throws IllegalArgumentException {		
		if(!executionStatus.isExecutionRunning(executionID)) {
			if(!activityExecutionOutput.containsKey(executionID)) {
				throw new IllegalArgumentException(exception_illegalexecutionid);
			}
		}
//TODO
		ActivityNodeChoice nextnode = this.nextNodeStrategy.chooseNextNode(executionID, executionStatus);
		
		if(nextnode == null) {
			throw new IllegalArgumentException(exception_noenablednodes);
		}
		return nextnode;
	}
		
	/**
	 * Resumes the activity execution with the provided ID
	 * @param executionID ID of the activity execution which shall be resumed
	 * @throws IllegalArgumentException if the executionID is invalid
	 */
	public void resume(int executionID)  throws IllegalArgumentException {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		activityExecutionStatus.setWholeExecutionInResumeMode(true);
/*		ActivityExecution execution = this.activityExecutions.get(executionID);
		
		this.setExecutionInResumeMode(execution, true);
*/
		nextStep(executionID);
	}
	
	public OpaqueBehavior getOpaqueBehavior(String name) {
		if(opaqueBehaviors.containsKey(name)) {
			return opaqueBehaviors.get(name);
		}
		return null;
	}
	
	protected ExtensionalValueList getExtensionalValues() {
		return locus.extensionalValues;
	}
	
	public void reset() {
		locus.extensionalValues = new ExtensionalValueList();
		breakpoints = new HashMap<ActivityNode, Breakpoint>();
//TODO		this.executionhierarchy = new ExecutionHierarchy();
		activityExecutionOutput = new HashMap<Integer, ParameterValueList>();
//		this.activityExecutions = new HashMap<Integer, ActivityExecution>(); 
		listeners.clear();
		executionStatus = new ExecutionStatus();
		traceHandler = new TraceHandler();		
	}
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
//		ActivityExecution activityExecution = activityExecutions.get(executionID);
		
		List<ActivityNode> enablednodes = new ArrayList<ActivityNode>();
				
//		if(activityExecution != null) {
		enablednodes.addAll(executionStatus.getEnabledNodes(executionID));
//		}
		
		return enablednodes;
	}
	
	public ParameterValueList getActivityOutput(int executionID) {
		return activityExecutionOutput.get(executionID);
	}
	
	public Trace getTrace(int executionID) {		
		return traceHandler.getTrace(executionID);		 
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
	 * @return breakpoint that is set for the given ActivityNode, null if no breakpoint is set
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
	
	/**
	 * @param nextNodeStrategy the nextNodeStrategy to set
	 */
	public void setNextNodeStrategy(NodeSelectionStrategy nextNodeStrategy) {
		this.nextNodeStrategy = nextNodeStrategy;
	}
	
	public PrimitiveType getPrimitiveStringType() {
		return this.locus.factory.getBuiltInType("String");
	}
	
	public PrimitiveType getPrimitivIntegerType() {
		return this.locus.factory.getBuiltInType("Integer");
	}
	
	public PrimitiveType getPrimitiveBooleanType() {
		return this.locus.factory.getBuiltInType("Boolean");
	}
	
	public PrimitiveType getPrimitiveUnlimitedNaturalType() {
		return this.locus.factory.getBuiltInType("UnlimitedNatural");
	}
/*	
	protected boolean isExecutionInResumeMode(ActivityExecution execution) {
		ActivityExecution caller = this.executionhierarchy.getCaller(execution);		
		if(caller != null) {
			return isExecutionInResumeMode(caller);
		} else {
			return this.executionInResumeMode.contains(execution);
		}				
	}
	
	protected void setExecutionInResumeMode(ActivityExecution execution, boolean resume) {
		ActivityExecution caller = this.executionhierarchy.getCaller(execution);		
		if(caller != null) {
			setExecutionInResumeMode(caller, resume);
		} else {
			if(resume) {
				if(!this.executionInResumeMode.contains(execution)){
					this.executionInResumeMode.add(execution);
				}
			} else {
				this.executionInResumeMode.remove(execution);
			}
		}		
	}*/
	
	/**
	 * Removes this execution and all called executions from the hierarchy.
	 * @param execution
	 */
	protected void removeActivityExecution(int executionID) {
		executionStatus.removeActivityExecution(executionID);
/*		
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
			activityExecutionStatus.remove(callees.get(i));
		}
		
		executionhierarchy.removeExecution(execution);		
		activityExecutionStatus.remove(execution);*/
	}
	/*
	private void removeExecution(ActivityExecution execution) {
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
		}
		this.activityExecutions.remove(execution.hashCode());
	}*/
	
	/**
	 * Terminates the execution of an activity.
	 * If the executionID of an called activity execution (e.g., CallBehaviorAction) is provided, 
	 * the whole activity execution including the root activity execution and all called executions
	 * are terminated as well. 
	 * @param executionID of the activity execution that shall be terminated. 
	 */
	public void terminate(int executionID) {		
		//TODO is this necessary? removeActivityExecution now removes complete execution, was this meant to be used in another way?
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		ActivityExecutionStatus rootActivityExecutionStatus = activityExecutionStatus.getRootCallerExecutionStatus();
		executionStatus.removeActivityExecution(rootActivityExecutionStatus.getExecutionID());
	}
	
	/**
	 * Adds a new activity execution to the execution context
	 * @param execution
	 */
	protected void addActivityExecution(ActivityExecution activityExecution, ActivityNodeActivation caller, ActivityEntryEvent entryevent) {
		executionStatus.addActivityExecution(activityExecution, caller, entryevent);
/*	TODO	ActivityExecutionStatus executionstatus = new ActivityExecutionStatus(execution);
		
		executionstatus.setActivityEntryEvent(entryevent);
		
		activityExecutionStatus.put(execution, executionstatus);
		activityExecutions.put(execution.hashCode(), execution);
		
		ActivityExecution callerExecution = null;
		
		if(caller != null) {
			executionstatus.setActivityCalls(caller);
			callerExecution = caller.getActivityExecution();			
		}
		
		executionhierarchy.addExecution(execution, callerExecution);	*/
	}		

	/**
	 * Provides the activity execution status of the given activity execution
	 * @param execution
	 * @return
	 */
	/*TODO
	protected ActivityExecutionStatus getActivityExecutionStatus(ActivityExecution execution) {
		return activityExecutionStatus.get(execution);
	}*/
	
	/**
	 * Determines if the given activity execution has enabled nodes including called activities
	 * @param execution
	 * @return
	 */
	protected boolean hasEnabledNodesIncludingCallees(int executionID) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		boolean anyNodesEnabled = activityExecutionStatus.hasEnabledNodesIncludingCallees();
		return anyNodesEnabled;
		/* TODO
		ActivityExecutionStatus executionstatus = activityExecutionStatus.get(execution);
		
		if(executionstatus == null) {
			return false;
		}
		
		if(executionstatus.hasEnabledNodes()) {
			return true;
		}
		
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		
		if(callees != null) {
			for(int i=0;i<callees.size();++i) {
				boolean hasenablednodes = hasEnabledNodesIncludingCallees(callees.get(i));
				if(hasenablednodes) {
					return true;
				}
			}
		}
		return false;*/
	}
		
	/**
	 * Determines if the caller of the given activity execution has enabled nodes
	 * @param execution
	 * @return
	 */
	protected boolean hasCallerEnabledNodes(int executionID) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		ActivityExecutionStatus callerActivityExecutionStatus = activityExecutionStatus.getDirectCallerExecutionStatus();
		boolean callerHasEnabledNodes = callerActivityExecutionStatus.hasEnabledNodesIncludingCallees();
		return callerHasEnabledNodes;
	}
/*TODO	
	protected ExecutionHierarchy getExecutionHierarchy() {
		return this.executionhierarchy;
	}
*/	
	/* TODO
	protected ActivityExecution getActivityExecution(int executionID) {
		return activityExecutions.get(executionID); 
	}*/
	
	public void addOpaqueBehavior(OpaqueBehaviorExecution behaviorexecution){
		locus.factory.addPrimitiveBehaviorPrototype(behaviorexecution);
		OpaqueBehavior behavior = (OpaqueBehavior)behaviorexecution.types.get(0);
		this.opaqueBehaviors.put(behavior.name, behavior);	
	}
	
	protected Locus getLocus() {
		return this.locus;
	}
	
	protected void setActivityExecutionOutput(int executionID, ParameterValueList output) {
		this.activityExecutionOutput.put(executionID, output);
	}
	
	public void addEventListener(ExecutionEventListener listener) {
		listeners.add(listener);
	}

	public void removeEventListener(ExecutionEventListener listener) {
		listeners.remove(listener);
	}

	public void notifyEventListener(Event event) {	
		if(!handleEvent(event)){
			return;
		}
		for (ExecutionEventListener l : new ArrayList<ExecutionEventListener>(
				listeners)) {
			l.notify(event);
		}
	}
	
	private boolean handleEvent(Event event) {
		traceHandler.notify(event);
		
		if(event instanceof SuspendEvent) {
			SuspendEvent suspendEvent = (SuspendEvent)event;
			int executionID = suspendEvent.getActivityExecutionID();
			ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);				
			if(activityExecutionStatus.isInResumeMode() && !(suspendEvent instanceof BreakpointEvent)) {
				return false;
			}				
		} 
		return true;
	}
	
}