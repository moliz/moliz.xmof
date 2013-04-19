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
import java.util.Set;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.InitialNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterSetting;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputParameterValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl;

import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Actions.BasicActions.PinActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityParameterNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ControlToken;
import fUML.Semantics.Activities.IntermediateActivities.DecisionNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ForkedToken;
import fUML.Semantics.Activities.IntermediateActivities.InitialNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.FirstChoiceStrategy;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;
import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ExecutionContext implements ExecutionEventProvider{
	protected static final TracemodelFactory TRACE_FACTORY = TracemodelFactory.eINSTANCE;
	
	protected static final String exception_illegalexecutionid = "Illegal execution id.";
	protected static final String exception_noenablednodes = "No enabled nodes available.";
	protected static final String exception_illegalactivitynode = "Illegal activity node. Activity node is not enabled.";
	
	private static ExecutionContext instance = new ExecutionContext();
	
	private Collection<ExecutionEventListener> listeners = new HashSet<ExecutionEventListener>();
	
	private Locus locus = null;
		
	private Hashtable<String, OpaqueBehavior> opaqueBehaviors = new Hashtable<String, OpaqueBehavior>();
	
	private NodeSelectionStrategy nextNodeStrategy = new NodeSelectionStrategyImpl(); 
	
	private HashMap<Integer, ParameterValueList> activityExecutionOutput = new HashMap<Integer, ParameterValueList>();
	
//TODO	private HashMap<ActivityExecution, ActivityExecutionStatus> activityExecutionStatus = new HashMap<ActivityExecution, ActivityExecutionStatus>();
	
	private HashMap<Integer, Trace> activityExecutionTrace = new HashMap<Integer, Trace>();
	
	/*
	 * Data structure for storing executions to their IDs
	 * The executions started by the user (through call of execute(...) or debug(...) remain in this data structure in this execution context
	 * Executions called by such executions are deleted if their execution ended.
	 */
//TODO	private HashMap<Integer, ActivityExecution> activityExecutions = new HashMap<Integer, ActivityExecution>(); 
	
	// Data structure for storing set breakpoints
	private HashMap<ActivityNode, Breakpoint> breakpoints = new HashMap<ActivityNode, Breakpoint>();  					
	
//	private HashMap<ActivityExecution, ExecutionStatus> executionStatus = new HashMap<ActivityExecution, ExecutionStatus>(); 
	protected ExecutionStatus executionStatus = new ExecutionStatus(); //TODO protected because of aspect
//TODO	private ExecutionHierarchy executionhierarchy = new ExecutionHierarchy();
	
//TODO	private List<ActivityExecution> executionInResumeMode = new ArrayList<ActivityExecution>();	
	
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
		activityExecutionTrace = new HashMap<Integer, Trace>();
		executionStatus = new ExecutionStatus();
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
		int rootExecutionID = executionStatus.getRootExecutionID(executionID);
		Trace trace = activityExecutionTrace.get(rootExecutionID);		
		return trace; 
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
		if(event instanceof TraceEvent) {
			TraceEvent traceEvent = (TraceEvent)event;
			int executionID = traceEvent.getActivityExecutionID();
			ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
//TODO			ActivityExecution execution = getActivityExecution(executionID);	

			if(event instanceof ActivityEntryEvent) {
				ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent)event;
				traceHandleActivityEntryEvent(activityEntryEvent);													
			} else if (event instanceof ActivityExitEvent) {	
				ActivityExitEvent activityExitEvent = (ActivityExitEvent)event;
				traceHandleActivityExitEvent(activityExitEvent);
			} else if (event instanceof ActivityNodeEntryEvent) {
				ActivityNodeEntryEvent nodeEntryEvent = (ActivityNodeEntryEvent)event;
				traceHandleActivityNodeEntryEvent(nodeEntryEvent);
			} else if(event instanceof ActivityNodeExitEvent) {
				ActivityNodeExitEvent nodeExitEvent = (ActivityNodeExitEvent)event;				
				traceHandleActivityNodeExitEvent(nodeExitEvent);
			} else if(event instanceof SuspendEvent) {
				SuspendEvent suspendEvent = (SuspendEvent)event;				
				traceHandleSuspendEvent(suspendEvent);				
				if(activityExecutionStatus.isInResumeMode() && !(event instanceof BreakpointEvent)) {
					return false;
				}				
			}
		} else if(event instanceof ExtensionalValueEvent){
			traceHandleExtensionalValueEvent((ExtensionalValueEvent)event);
		}
		return true;
	}
		
	private void traceHandleExtensionalValueEvent(ExtensionalValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();

		if(extensionalValue instanceof Object_) {
			Object_ object = (Object_)extensionalValue;
			ExtensionalValueEventType eventType = event.getType();

			Collection<Trace> allActiveTraces = this.activityExecutionTrace.values();
			for(Trace trace : allActiveTraces) {
				if(eventType == ExtensionalValueEventType.CREATION) {
					ValueInstance valueInstance = createValueInstance(object);
					trace.getValueInstances().add(valueInstance);

				} else {
					ValueInstance valueInstance = trace.getValueInstance(object);
					if(valueInstance != null) {
						if(eventType == ExtensionalValueEventType.DESTRUCTION) {							
							valueInstance.setDestroyed(true);
						} else {
							ValueSnapshot valueSnapshot = createValueSnapshot(object);
							valueInstance.getSnapshots().add(valueSnapshot);
						}
					}
				}
			}
		}
	}

	private void traceHandleActivityEntryEvent(ActivityEntryEvent event) {
		int executionID = event.getActivityExecutionID();		
		Activity activity = event.getActivity();		
		//TODO ActivityExecution execution = getActivityExecution(executionID);
		
		Trace trace = null; 
		if(event.getParent() == null) {	// create new trace
			trace = new TraceImpl();
			initializeTraceWithObjectsAtLocus(trace);
			activityExecutionTrace.put(executionID, trace);					
		} else { // get existing trace
			trace = getTrace(executionID);
		}
		
		// add activity execution to trace
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution = trace.addActivityExecution(activity, executionID);
		
		// set caller of activity execution
		if(event.getParent() != null && event.getParent() instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent parentevent = (ActivityNodeEntryEvent)event.getParent();
			int parentexeID = parentevent.getActivityExecutionID();
			org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution parentExecution = trace.getActivityExecutionByID(parentexeID);
 			
			if(parentExecution != null && parentevent.getNode() instanceof CallAction) { 
				//CallActionExecution callerNodeExecution = parentExecution.getActiveCallActionExecution((CallAction)parentevent.getNode());
				ActivityNodeExecution callActionAcitivtyNodeExecution = parentExecution.getExecutionForEnabledNode(parentevent.getNode());
				if(callActionAcitivtyNodeExecution != null && callActionAcitivtyNodeExecution instanceof CallActionExecution) {
					CallActionExecution callerNodeExecution = (CallActionExecution)callActionAcitivtyNodeExecution;
					activityExecution.setCaller(callerNodeExecution);
					callerNodeExecution.setCallee(activityExecution);
				}				
			}			
		}
	}
	
	private InputParameterSetting createInputParameterSetting(org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution, Parameter parameter, ValueList values) {
		InputParameterSetting parameterSetting = TRACE_FACTORY.createInputParameterSetting();
		parameterSetting.setParameter(parameter);		
		for(Value value : values) {						
			ValueInstance valueInstance = getOrCreateValueInstance(activityExecution.getTrace(), value);						
			InputParameterValue parameterValue = TRACE_FACTORY.createInputParameterValue();
			parameterValue.setValueInstance(valueInstance);
			parameterValue.setValueSnapshot(valueInstance.getLatestSnapshot());
			parameterSetting.getParameterValues().add(parameterValue);
		}			
		return parameterSetting;		
	}
	
	private void initializeTraceWithObjectsAtLocus(Trace trace) {
		for(ExtensionalValue extensionalValue : this.locus.extensionalValues) {
			if(extensionalValue.getClass().equals(Object_.class)) {
				ValueInstance valueInstance = createValueInstance(extensionalValue);
				trace.getValueInstances().add(valueInstance);
				trace.getInitialLocusValueInstances().add(valueInstance);
			}
		}		
	}
		
	private ValueInstance createValueInstance(Value value) {
		Value value_ = value;
		if(value instanceof Reference) {
			value_ = ((Reference)value).referent;
		}
		ValueInstance valueInstance = TracemodelFactory.eINSTANCE.createValueInstance();
		valueInstance.setRuntimeValue(value_);
		ValueSnapshot valueSnapshot = createValueSnapshot(value_);
		valueInstance.getSnapshots().add(valueSnapshot);
		valueInstance.setOriginal(valueSnapshot);
		return valueInstance;
	}

	private ValueSnapshot createValueSnapshot(Value value) {
		ValueSnapshot valueSnapshot = TracemodelFactory.eINSTANCE.createValueSnapshot();
		valueSnapshot.setValue(value.copy());
		return valueSnapshot;
	}

	private void traceHandleActivityExitEvent(ActivityExitEvent event) { //TODO shift trace handling into own class
		// add activity outputs to trace
		int executionID = event.getActivityExecutionID();
//TODO		ActivityExecution execution = getActivityExecution(executionID);	
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);	
		ActivityExecution execution = activityExecutionStatus.getActivityExecution();
				
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution = trace.getActivityExecutionByID(executionID);	
		
		List<Parameter> outputParametersWithoutParameterNode = new ArrayList<Parameter>();
		outputParametersWithoutParameterNode.addAll(activityExecution.getOutputParameters()); 
					
		List<ActivityParameterNode> outputActivityParameterNodes = activityExecution.getOutputActivityParameterNodes();
		for(ActivityParameterNode outputActivityParameterNode : outputActivityParameterNodes) {
			ActivityParameterNodeActivation outputActivityParameterNodeActivation = (ActivityParameterNodeActivation)execution.activationGroup.getNodeActivation(outputActivityParameterNode);
			TokenList heldTokens = outputActivityParameterNodeActivation.heldTokens;

			outputParametersWithoutParameterNode.remove(outputActivityParameterNode.parameter);
			OutputParameterSetting outputParameterSetting = TRACE_FACTORY.createOutputParameterSetting();
			outputParameterSetting.setParameter(outputActivityParameterNode.parameter);
			for(Token token : heldTokens) {
				Token originalToken = activityExecutionStatus.getOriginalToken(token);
				TokenInstance tokenInstance = activityExecutionStatus.getTokenInstance(originalToken);
				if(tokenInstance != null && tokenInstance instanceof ObjectTokenInstance) {
					ObjectTokenInstance otokenInstance = (ObjectTokenInstance)tokenInstance;

					List<ActivityEdge> traversedEdges = activityExecutionStatus.getTraversedActivityEdges(originalToken);
					List<ActivityEdge> traversedEdgesForNode = getTraversedEdge(traversedEdges, outputActivityParameterNode); 
					otokenInstance.getTraversedEdges().addAll(traversedEdgesForNode);

					OutputParameterValue outputParameterValue = createOutputParameterValue(trace, otokenInstance);
					outputParameterSetting.getParameterValues().add(outputParameterValue);
				}
			}				
			activityExecution.getActivityOutputs().add(outputParameterSetting);

		}
					
		for(Parameter inputParameter : outputParametersWithoutParameterNode) {
			ParameterValue parameterValue = execution.getParameterValue(inputParameter);
			if(parameterValue != null) {
				InputParameterSetting parameterSetting = createInputParameterSetting(activityExecution, inputParameter, parameterValue.values);
				activityExecution.getActivityInputs().add(parameterSetting);
			}
		}
	}
	
	private void traceHandleActivityNodeExitEvent(ActivityNodeExitEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityNode node = event.getNode();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution traceActivityExecution = trace.getActivityExecutionByID(executionID);
		
		ActivityNodeExecution traceCurrentNodeExecution = traceActivityExecution.getExecutionForEnabledNode(node);		
				
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);	
		ActivityExecution execution = activityExecutionStatus.getActivityExecution();
		
		
		ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);	
				
		// add output through output pins	
		if(activation instanceof ActionActivation && traceCurrentNodeExecution instanceof ActionExecution) {
			ActionExecution actionExecution = (ActionExecution)traceCurrentNodeExecution;
			ActionActivation actionActivation = (ActionActivation)activation;
			Action action = (Action)actionActivation.node;
			for(OutputPin outputPin : action.output) {					
				PinActivation outputPinActivation = actionActivation.getPinActivation(outputPin);
				List<Token> sentTokens = activityExecutionStatus.removeTokenSending(outputPinActivation);
				
				if(sentTokens == null) { // happens if a pin has no outgoing edge
					sentTokens = outputPinActivation.heldTokens;
				}
				
				List<OutputValue> outputValues = new ArrayList<OutputValue>();
				if(sentTokens != null) {
					for(Token token : sentTokens) {		
//						TokenInstance tokenInstance = executionStatus.getTokenInstance(token);
//						if(tokenInstance == null) {	// token instance has not been added as output yet
							OutputValue outputValue = createOutputValue(trace, token);
							activityExecutionStatus.addTokenInstance(token, outputValue.getOutputObjectToken());
							outputValues.add(outputValue);
//						}
					}
					if(outputValues.size() > 0) {
						Output output = TracemodelFactory.eINSTANCE.createOutput();
						output.setOutputPin(outputPin);
						output.getOutputValues().addAll(outputValues);
						actionExecution.getOutputs().add(output);	
					}
				}
			}


		
			// add output through edges
			List<Token> sentTokens = activityExecutionStatus.removeTokenSending(activation);		
	
			if(sentTokens != null) {			
				Set<Token> sentTokens_ = new HashSet<Token>(sentTokens);
				for(Token token : sentTokens_) {
					Token token_ = token;
					ControlTokenInstance ctrlTokenInstance = null;
					if(token instanceof ForkedToken) {
						ForkedToken forkedToken = (ForkedToken)token;
						if(sentTokens_.contains(forkedToken.baseToken)) {
							continue;
						}
						token_ = forkedToken.baseToken;
					}
					
					if(token_ instanceof ControlToken){					
						ctrlTokenInstance = TracemodelFactory.eINSTANCE.createControlTokenInstance();
						activityExecutionStatus.addTokenInstance(token, ctrlTokenInstance);
						actionExecution.getOutgoingControl().add(ctrlTokenInstance);						
					}/* else if (token_ instanceof ObjectToken){
						tokenInstance = new ObjectTokenInstanceImpl();
						ValueInstance valueInstance = new ValueInstanceImpl();
						if(token_.getValue() instanceof Reference) {
							valueInstance.setValue(((Reference)token_.getValue()).referent.copy());
						} else {
							valueInstance.setValue(token_.getValue().copy());
						}
						((ObjectTokenInstance)tokenInstance).setValue(valueInstance);
					}*/
					/*
					if(ctrlTokenInstance != null) {																				
						executionStatus.addTokenInstance(token, tokenInstance);
						actionExecution.getOutgoingControl().add(tokenInstance);
					}*/					
				}
/*				if(tokenInstances.size() > 0) {
					nodeExecution.addActivityNodeOutput(null, tokenInstances);
				}*/
			}
			/*
			if(nodeExecution.getOutputs().size() == 0) {
				nodeExecution.addActivityNodeOutput(null, null);
			}*/
		} else if(activation instanceof InitialNodeActivation) {
			InitialNodeExecution initialNodeExecution = (InitialNodeExecution)traceCurrentNodeExecution;			
			List<Token> sentTokens = activityExecutionStatus.removeTokenSending(activation);					
			if(sentTokens != null && sentTokens.size() > 0) {			
				Token token = sentTokens.get(0);
				if(token instanceof ControlToken){					
					ControlTokenInstance ctrlTokenInstance = TracemodelFactory.eINSTANCE.createControlTokenInstance();
					activityExecutionStatus.addTokenInstance(token, ctrlTokenInstance);
					initialNodeExecution.setOutgoingControl(ctrlTokenInstance);						
				}	
			}				
		}
		
		// Mark node as executed
		traceCurrentNodeExecution.setUnderExecution(false);
		traceCurrentNodeExecution.setExecuted(true);
		
		return;
	}

	private ValueInstance getOrCreateValueInstance(Trace trace, Value value) {
		ValueInstance valueInstance;
		
		Value value_ = value;
		if(value instanceof Reference) {
			value_ = ((Reference)value).referent;
		}
	
		ValueInstance existingValueInstance = trace.getValueInstance(value_);
		if(existingValueInstance != null) {
			valueInstance = existingValueInstance;
		} else {
			valueInstance = createValueInstance(value_);
			trace.getValueInstances().add(valueInstance);
		}
		
		return valueInstance;
	}
	
	private void traceHandleSuspendEvent(SuspendEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);	
		ActivityExecution execution = activityExecutionStatus.getActivityExecution();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution = trace.getActivityExecutionByID(executionID);

		if(event.getLocation() instanceof Activity) { // add object tokens from activity input parameter node executions to trace			
			List<Parameter> inputParametersWithoutParameterNode = new ArrayList<Parameter>();
			inputParametersWithoutParameterNode.addAll(activityExecution.getInputParameters()); 
						
			List<ActivityParameterNode> inputActivityParameterNodes = activityExecution.getInputActivityParamenterNodes();
			for(ActivityParameterNode inputActivityParameterNode : inputActivityParameterNodes) {
				ActivityParameterNodeActivation inputActivityParameterNodeActivation = (ActivityParameterNodeActivation)execution.activationGroup.getNodeActivation(inputActivityParameterNode);
				List<Token> sentTokens = activityExecutionStatus.removeTokenSending(inputActivityParameterNodeActivation);
				if(sentTokens == null) { // happens if a parameter node has no outgoing edge
					sentTokens = inputActivityParameterNodeActivation.heldTokens;
				}
				
				if(sentTokens != null && sentTokens.size() > 0) {
					inputParametersWithoutParameterNode.remove(inputActivityParameterNode.parameter);
					InputParameterSetting inputParameterSetting = TRACE_FACTORY.createInputParameterSetting();
					inputParameterSetting.setParameter(inputActivityParameterNode.parameter);
					for(Token token : sentTokens) {
						InputParameterValue inputParameterValue = createInputParameterValue(trace, token);
						inputParameterSetting.getParameterValues().add(inputParameterValue);
						
						activityExecutionStatus.addTokenInstance(token, inputParameterValue.getParameterInputObjectToken());						
					}
					
					activityExecution.getActivityInputs().add(inputParameterSetting);
				}
			}
						
			for(Parameter inputParameter : inputParametersWithoutParameterNode) {
				ParameterValue parameterValue = execution.getParameterValue(inputParameter);
				if(parameterValue != null) {
					InputParameterSetting parameterSetting = createInputParameterSetting(activityExecution, inputParameter, parameterValue.values);
					activityExecution.getActivityInputs().add(parameterSetting);
				}
			}			
		}
		
		// add enabled nodes to trace
		List<ActivityNode> enabledNodes = event.getNewEnabledNodes();
		for(int i=0;i<enabledNodes.size();++i) {
			ActivityNode node = enabledNodes.get(i);		

			ActivityNodeExecution activityNodeExecution = createActivityNodeExecution(node);
			activityExecution.getNodeExecutions().add(activityNodeExecution);			
			
			if(activityNodeExecution instanceof ActionExecution && !(activityNodeExecution instanceof StructuredActivityNodeExecution)) {
				ActionExecution actionExecution = (ActionExecution)activityNodeExecution;
				ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);

				if(activation == null) {
					//there is an issue with expansion regions
					return;
				}
				List<Token> tokens = getTokensForEnabledNode(activityExecutionStatus, activation, enabledNodes, i); 
				
				// add input through input pins
				ActionActivation actionActivation = (ActionActivation)activation;
				Action action = (Action)actionActivation.node;
				for(InputPin inputPin : action.input) {
					PinActivation inputPinActivation = actionActivation.getPinActivation(inputPin);
					TokenList heldtokens = inputPinActivation.heldTokens;

					List<InputValue> inputValues = new ArrayList<InputValue>();							
					for(Token token : heldtokens) {
						Token originalToken = activityExecutionStatus.getOriginalToken(token);								
						if(tokens.contains(originalToken)) {	
							TokenInstance tokenInstance = activityExecutionStatus.getTokenInstance(originalToken);
							if(tokenInstance != null && tokenInstance instanceof ObjectTokenInstance) {
								ObjectTokenInstance otokenInstance = (ObjectTokenInstance)tokenInstance;

								List<ActivityEdge> traversedEdges = activityExecutionStatus.getTraversedActivityEdges(originalToken);
								List<ActivityEdge> traversedEdgesForNode = getTraversedEdge(traversedEdges, inputPinActivation.node); 
								otokenInstance.getTraversedEdges().addAll(traversedEdgesForNode);

								InputValue inputValue = TRACE_FACTORY.createInputValue();
								inputValue.setInputObjectToken(otokenInstance);
								inputValues.add(inputValue);
							}
							tokens.remove(originalToken);
						}
					}							

					if(inputValues.size() > 0) {
						Input input = TRACE_FACTORY.createInput();
						input.setInputPin(inputPin);
						input.getInputValues().addAll(inputValues);
						actionExecution.getInputs().add(input);
					}
				}

				// add input through edges		
				List<ControlTokenInstance> ctokenInstances = getInputControlTokenInstances(tokens, node, activityExecutionStatus); //control tokens remained in list TODO refactor
				actionExecution.getIncomingControl().addAll(ctokenInstances);
				
			} else if(activityNodeExecution instanceof ControlNodeExecution) {
				ControlNodeExecution controlNodeExecution = (ControlNodeExecution)activityNodeExecution;
				ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);
				
				if(activation != null) { // TODO there is an issue with expansion regions
					List<Token> tokens = getTokensForEnabledNode(activityExecutionStatus, activation, enabledNodes, i); 
					
					// add input through edges		
					List<TokenInstance> tokenInstances = getInputTokenInstances(tokens, node, activityExecutionStatus); 
					controlNodeExecution.getRoutedTokens().addAll(tokenInstances);
				}
			} 			
		}			
	}

	private List<Token> getTokensForEnabledNode(ActivityExecutionStatus executionStatus, ActivityNodeActivation activation,
			List<ActivityNode> enabledNodes, int i) {
		List<TokenList> tokensets = executionStatus.getEnabledActivationTokens(activation);
		// in one step one particular node can only be enabled once, 
		// i.e, the tokens sent to this node in the last step (enabling the node) are added at last
		TokenList lastTokenList = tokensets.get(tokensets.size()-1);
		return new ArrayList<Token>(lastTokenList);
	}
	
	
	private InputParameterValue createInputParameterValue(Trace trace, Token token) {
		ObjectTokenInstance otokenInstance = TRACE_FACTORY.createObjectTokenInstance();
		InputParameterValue inputParameterValue = TRACE_FACTORY.createInputParameterValue();
		inputParameterValue.setParameterInputObjectToken(otokenInstance);

		if(token.getValue() != null) {
			ValueInstance valueInstance = getOrCreateValueInstance(trace, token.getValue());
			otokenInstance.setTransportedValue(valueInstance);									
			inputParameterValue.setValueSnapshot(valueInstance.getLatestSnapshot());
			inputParameterValue.setValueInstance(valueInstance);
		}
		
		return inputParameterValue;
	}
	
	private OutputParameterValue createOutputParameterValue(Trace trace, ObjectTokenInstance otokenInstance) {		
		OutputParameterValue outputParameterValue = TRACE_FACTORY.createOutputParameterValue();
		outputParameterValue.setParameterOutputObjectToken(otokenInstance);

		outputParameterValue.setValueSnapshot(otokenInstance.getTransportedValue().getLatestSnapshot());
		outputParameterValue.setValueInstance(otokenInstance.getTransportedValue());
		
		return outputParameterValue;				
	}
	
	private OutputValue createOutputValue(Trace trace, Token token) {
		ObjectTokenInstance otokenInstance = TRACE_FACTORY.createObjectTokenInstance();
		OutputValue outputValue = TRACE_FACTORY.createOutputValue();
		outputValue.setOutputObjectToken(otokenInstance);
		
		if(token.getValue() != null) {
			ValueInstance valueInstance = getOrCreateValueInstance(trace, token.getValue());
			otokenInstance.setTransportedValue(valueInstance);									
			outputValue.setOutputValueSnapshot(valueInstance.getLatestSnapshot());
		}
		
		return outputValue;
	}
	
	private List<ControlTokenInstance> getInputControlTokenInstances(List<Token> tokens, ActivityNode node, ActivityExecutionStatus executionStatus) {
		//TODO move this into ExecutionStatus?
		List<ControlTokenInstance> ctokenInstances = new ArrayList<ControlTokenInstance>();
		
		List<TokenInstance> tokenInstances = getInputTokenInstances(tokens, node, executionStatus);
		for(TokenInstance tokenInstance : tokenInstances) {
			if(tokenInstance instanceof ControlTokenInstance) {
				ctokenInstances.add((ControlTokenInstance)tokenInstance);
			}
		}
		return ctokenInstances;
	}
	
	private List<TokenInstance> getInputTokenInstances(List<Token> tokens, ActivityNode node, ActivityExecutionStatus executionStatus) {
		//TODO move this into ExecutionStatus?
		List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();
		for(Token token : tokens) {
			TokenInstance tokenInstance = executionStatus.getTokenInstance(token);
			if(tokenInstance != null) {
				List<ActivityEdge> traversedEdges = executionStatus.getTraversedActivityEdges(token);
				List<ActivityEdge> traversedEdgesForNode = getTraversedEdge(traversedEdges, node);
				
				for(ActivityEdge e : traversedEdgesForNode) {
					if(!tokenInstance.getTraversedEdges().contains(e)) {
						tokenInstance.getTraversedEdges().add(e);
					}
				}
				tokenInstances.add(tokenInstance);
			}
		}	
		return tokenInstances;
	}
	
	private ActivityNodeExecution createActivityNodeExecution(ActivityNode activityNode) {
		ActivityNodeExecution activityNodeExecution;
		if(activityNode instanceof DecisionNode) {
			activityNodeExecution = TRACE_FACTORY.createDecisionNodeExecution();
		} else if(activityNode instanceof CallAction) {
			activityNodeExecution = TRACE_FACTORY.createCallActionExecution();
		} else if(activityNode instanceof StructuredActivityNode) {
			activityNodeExecution = TRACE_FACTORY.createStructuredActivityNodeExecution();
		} else if(activityNode instanceof Action) {
			activityNodeExecution = TRACE_FACTORY.createActionExecution();
		} else if(activityNode instanceof InitialNode) {
			activityNodeExecution = TRACE_FACTORY.createInitialNodeExecution();
		} else if(activityNode instanceof ControlNode) {
			activityNodeExecution = TRACE_FACTORY.createControlNodeExecution();
		} else {
			return null;
		}
		activityNodeExecution.setNode(activityNode);	
		return activityNodeExecution;
	}

	private void traceHandleActivityNodeEntryEvent(ActivityNodeEntryEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityNode node = event.getNode();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution traceActivityExecution = trace.getActivityExecutionByID(executionID);

		ActivityNodeExecution traceCurrentNodeExecution = traceActivityExecution.getExecutionForEnabledNode(node);
		
		// There should only be one execution for one node in the trace that has not been finished yet
		// Otherwise, the inputs have to be taken into consideration
		if(traceCurrentNodeExecution == null) { // TODO there is an issue with expansion regions
			return;
		}
		
		// Mark activity node execution as under execution 
		traceCurrentNodeExecution.setUnderExecution(true);
		
		// Set the chronological predecessor / successor relationship
		setChronologicalRelationships(traceCurrentNodeExecution);
		
		// Set latest value snapshot for input values
		if(traceCurrentNodeExecution instanceof ActionExecution) {
			ActionExecution actionExecution = (ActionExecution)traceCurrentNodeExecution;
			for(Input input : actionExecution.getInputs()) {
				for(InputValue inputValue : input.getInputValues()) {
					ObjectTokenInstance objectTokenInstance = inputValue.getInputObjectToken();
					ValueInstance transportedValue = objectTokenInstance.getTransportedValue();
					ValueSnapshot latestValueSnapshot = transportedValue.getLatestSnapshot();
					inputValue.setInputValueSnapshot(latestValueSnapshot);
				}
			}
		} else if(traceCurrentNodeExecution instanceof DecisionNodeExecution) {
			ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);	
			ActivityExecution execution = activityExecutionStatus.getActivityExecution();
			ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);
			DecisionNodeExecution decisionNodeExecution = (DecisionNodeExecution)traceCurrentNodeExecution;
			DecisionNodeActivation decisionNodeActivation = (DecisionNodeActivation)activation;
			
			if(activation == null) { // TODO there is an issue with expansion regions
				return;
			}
			
			ActivityEdgeInstance decisionInputFlowInstance = decisionNodeActivation.getDecisionInputFlowInstance();				
			
			if(decisionInputFlowInstance != null) {
				List<Token> decisionInputTokens = new ArrayList<Token>();
				if(decisionInputFlowInstance.offers.size() > 0) {
					decisionInputTokens.addAll(decisionInputFlowInstance.offers.get(0).offeredTokens);
				}
				if(decisionInputTokens.size() > 0) {
					if(decisionInputTokens.get(0) instanceof ObjectToken) {
						ObjectToken decisionInputToken = (ObjectToken)decisionInputTokens.get(0);
						List<Token> decisionInputTokens_ = new ArrayList<Token>();
						decisionInputTokens_.add(decisionInputToken);
						TokenInstance decisionInputTokenInstance = getInputTokenInstances(decisionInputTokens_, node, activityExecutionStatus).get(0);
						if(decisionInputTokenInstance instanceof ObjectTokenInstance) {
							ObjectTokenInstance decisionInputObjectTokenInstance = (ObjectTokenInstance)decisionInputTokenInstance;
							InputValue inputValue = TRACE_FACTORY.createInputValue();
							inputValue.setInputObjectToken(decisionInputObjectTokenInstance);
							decisionNodeExecution.setDecisionInputValue(inputValue);
							ValueInstance transportedValue = decisionInputObjectTokenInstance.getTransportedValue();
							ValueSnapshot latestValueSnapshot = transportedValue.getLatestSnapshot();
							inputValue.setInputValueSnapshot(latestValueSnapshot);
						}
					}
				}							
			}			
		}
	}

	private void setChronologicalRelationships(ActivityNodeExecution activityNodeExecution) {
		Trace trace = activityNodeExecution.getActivityExecution().getTrace();
		ActivityNodeExecution traceLastNodeExecution = trace.getLastActivityNodeExecution();
		if(traceLastNodeExecution != null && !traceLastNodeExecution.equals(activityNodeExecution)) {
			traceLastNodeExecution.setChronologicalSuccessor(activityNodeExecution);
			activityNodeExecution.setChronologicalPredecessor(traceLastNodeExecution);
		}
	}
	
	private List<ActivityEdge> getTraversedEdge(List<ActivityEdge> edges, ActivityNode targetNode) {
		List<ActivityEdge> traversedEdges = new ArrayList<ActivityEdge>();
		if(edges != null) {
			for(ActivityEdge edge : edges) {
				if(edge.target.equals(targetNode)) {
					traversedEdges.add(edge);
				}
			}
		}
		return traversedEdges;
	}
}