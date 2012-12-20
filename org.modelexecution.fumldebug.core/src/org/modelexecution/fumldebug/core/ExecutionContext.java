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
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.event.TraceEvent;
import org.modelexecution.fumldebug.core.impl.NodeSelectionStrategyImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.impl.TraceImpl;

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
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
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
		
	private PrimitiveType typeBoolean = null;
	private PrimitiveType typeInteger = null;
	private PrimitiveType typeString = null;
	private PrimitiveType typeUnlimitedNatural = null;
	
	private Hashtable<String, OpaqueBehavior> opaqueBehaviors = new Hashtable<String, OpaqueBehavior>();
	
	private NodeSelectionStrategy nextNodeStrategy = new NodeSelectionStrategyImpl(); 
	
	private HashMap<ActivityExecution, ParameterValueList> activityExecutionOutput = new HashMap<ActivityExecution, ParameterValueList>();
	
	private HashMap<ActivityExecution, ExecutionStatus> activityExecutionStatus = new HashMap<ActivityExecution, ExecutionStatus>();
	
	private HashMap<ActivityExecution, Trace> activityExecutionTrace = new HashMap<ActivityExecution, Trace>();
	
	/*
	 * Data structure for storing executions to their IDs
	 * The executions started by the user (through call of execute(...) or debug(...) remain in this data structure in this execution context
	 * Executions called by such executions are deleted if their execution ended.
	 */
	private HashMap<Integer, ActivityExecution> activityExecutions = new HashMap<Integer, ActivityExecution>(); 
	
	// Data structure for storing set breakpoints
	private HashMap<ActivityNode, Breakpoint> breakpoints = new HashMap<ActivityNode, Breakpoint>();  					
	
	private ExecutionHierarchy executionhierarchy = new ExecutionHierarchy();
	
	private List<ActivityExecution> executionInResumeMode = new ArrayList<ActivityExecution>();	
	
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
		typeString = this.createPrimitiveType("String");
		typeInteger = this.createPrimitiveType("Integer");
		typeUnlimitedNatural = this.createPrimitiveType("UnlimitedNatural");
		
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
		return instance;
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
		
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		if(node == null) {
			nextnode = getNextNode(activityExecution);
			activityExecution = activityExecutions.get(nextnode.getExecutionID());
		} else {
			nextnode = new ActivityNodeChoice(executionID, node);
		}
		
		ExecutionStatus exestatus = activityExecutionStatus.get(activityExecution);
		boolean activityNodeWasEnabled = exestatus.getEnabledNodes().remove(nextnode.getActivityNode());

		if(!activityNodeWasEnabled) {
			throw new IllegalArgumentException(exception_illegalactivitynode);
		}
		
		ActivityNodeActivation activation = exestatus.removeActivation(nextnode.getActivityNode());
		TokenList tokens = exestatus.removeTokens(activation);
		
		if(activation == null || tokens == null) {
			throw new IllegalArgumentException(exception_noenablednodes); 
		}
		
//		if(activation instanceof ActionActivation) {
//			((ActionActivation)activation).firing = true;
//		}	
		
		activation.fire(tokens);
		
		if(this.isExecutionInResumeMode(activityExecution)) {
			nextStep(executionID);
		}
	}			
	
	private ActivityNodeChoice getNextNode(ActivityExecution activityExecution) throws IllegalArgumentException {	
		if(activityExecution == null) {
			throw new IllegalArgumentException(exception_illegalexecutionid);
		}

		ActivityNodeChoice nextnode = this.nextNodeStrategy.chooseNextNode(activityExecution, this.executionhierarchy, activityExecutionStatus);
		
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
		ActivityExecution execution = this.activityExecutions.get(executionID);
		
		this.setExecutionInResumeMode(execution, true);

		nextStep(executionID);
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
	
	protected void reset() {
		locus.extensionalValues = new ExtensionalValueList();
		this.breakpoints = new HashMap<ActivityNode, Breakpoint>();
		this.executionhierarchy = new ExecutionHierarchy();
		this.activityExecutionOutput = new HashMap<ActivityExecution, ParameterValueList>();
		this.activityExecutions = new HashMap<Integer, ActivityExecution>(); 
		this.listeners.clear();
	}
	
	public List<ActivityNode> getEnabledNodes(int executionID) {
		ActivityExecution activityExecution = activityExecutions.get(executionID);
		
		List<ActivityNode> enablednodes = new ArrayList<ActivityNode>();
				
		if(activityExecution != null) {
			
			ExecutionStatus exestatus = activityExecutionStatus.get(activityExecution);
			
			if(exestatus != null) {
				enablednodes = exestatus.getEnabledNodes();
			}

		}
		
		return enablednodes;
	}
	
	public ParameterValueList getActivityOutput(int executionID) {
		ActivityExecution execution = this.activityExecutions.get(executionID);
		ParameterValueList output = this.activityExecutionOutput.get(execution);
		return output;
	}
	
	public Trace getTrace(int executionID) {		
		ActivityExecution execution = this.activityExecutions.get(executionID);			
		return getTrace(execution);
	}
	
	protected Trace getTrace(ActivityExecution execution) {
		if(execution == null) {
			return null;
		}
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(execution);
		Trace trace = this.activityExecutionTrace.get(rootExecution);		
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
		return this.typeString;
	}
	
	public PrimitiveType getPrimitivIntegerType() {
		return this.typeInteger;
	}
	
	public PrimitiveType getPrimitiveBooleanType() {
		return this.typeBoolean;
	}
	
	public PrimitiveType getPrimitiveUnlimitedNaturalType() {
		return this.typeUnlimitedNatural;
	}
	
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
	}
	
	/**
	 * Removes this execution and all called executions from the hierarchy.
	 * @param execution
	 */
	protected void removeActivityExecution(ActivityExecution execution) {
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
			activityExecutionStatus.remove(callees.get(i));
		}
		
		executionhierarchy.removeExecution(execution);		
		activityExecutionStatus.remove(execution);
	}
	
	private void removeExecution(ActivityExecution execution) {
		List<ActivityExecution> callees = executionhierarchy.getCallee(execution);
		for(int i=0;i<callees.size();++i){
			removeExecution(callees.get(i));
		}
		this.activityExecutions.remove(execution.hashCode());
	}
	
	/**
	 * Terminates the execution of an activity.
	 * If the executionID of an called activity execution (e.g., CallBehaviorAction) is provided, 
	 * the whole activity execution including the root activity execution and all called executions
	 * are terminated as well. 
	 * @param executionID of the activity execution that shall be terminated. 
	 */
	public void terminate(int executionID) {
		ActivityExecution execution = this.activityExecutions.get(executionID);
		ActivityExecution rootExecution = executionhierarchy.getRootCaller(execution);		
		
		this.removeActivityExecution(rootExecution);
	}
	
	/**
	 * Adds a new activity execution to the execution context
	 * @param execution
	 */
	protected void addActivityExecution(ActivityExecution execution, ActivityNodeActivation caller, ActivityEntryEvent entryevent) {
		ExecutionStatus executionstatus = new ExecutionStatus();
		
		executionstatus.setActivityEntryEvent(entryevent);
		
		activityExecutionStatus.put(execution, executionstatus);
		activityExecutions.put(execution.hashCode(), execution);
		
		ActivityExecution callerExecution = null;
		
		if(caller != null) {
			executionstatus.setActivityCalls(caller);
			callerExecution = caller.getActivityExecution();			
		}
		
		executionhierarchy.addExecution(execution, callerExecution);	
	}		

	/**
	 * Provides the activity execution status of the given activity execution
	 * @param execution
	 * @return
	 */
	protected ExecutionStatus getActivityExecutionStatus(ActivityExecution execution) {
		return activityExecutionStatus.get(execution);
	}
	
	/**
	 * Determines if the given activity execution has enabled nodes including called activities
	 * @param execution
	 * @return
	 */
	protected boolean hasEnabledNodesIncludingCallees(ActivityExecution execution) {
		ExecutionStatus executionstatus = activityExecutionStatus.get(execution);
		
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
		return false;
	}
		
	/**
	 * Determines if the caller of the given activity execution has enabled nodes
	 * @param execution
	 * @return
	 */
	protected boolean hasCallerEnabledNodes(ActivityExecution execution) {
		ActivityExecution caller = executionhierarchy.getCaller(execution);
		if(caller == null) {
			return false;
		}
		return hasEnabledNodesIncludingCallees(caller);
	}
	
	protected ExecutionHierarchy getExecutionHierarchy() {
		return this.executionhierarchy;
	}
	
	protected ActivityExecution getActivityExecution(int executionID) {
		return activityExecutions.get(executionID); 
	}
	
	public void addOpaqueBehavior(OpaqueBehaviorExecution behaviorexecution){
		locus.factory.addPrimitiveBehaviorPrototype(behaviorexecution);
		OpaqueBehavior behavior = (OpaqueBehavior)behaviorexecution.types.get(0);
		this.opaqueBehaviors.put(behavior.name, behavior);	
	}
	
	public Locus getLocus() {
		return this.locus;
	}
	
	protected void setActivityExecutionOutput(ActivityExecution execution, ParameterValueList output) {
		this.activityExecutionOutput.put(execution, output);
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
			ActivityExecution execution = getActivityExecution(executionID);	

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
				if(isExecutionInResumeMode(execution)) {
					return false;
				}				
			}
		}
		return true;
	}
		
	private void traceHandleActivityEntryEvent(ActivityEntryEvent event) {
		int executionID = event.getActivityExecutionID();		
		Activity activity = event.getActivity();		
		ActivityExecution execution = getActivityExecution(executionID);
		
		Trace trace = null; 
		if(event.getParent() == null) {	// create new trace
			trace = new TraceImpl();
			activityExecutionTrace.put(execution, trace);					
		} else { // get existing trace
			trace = getTrace(execution);
		}
		
		// add activity execution to trace
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution = trace.addActivityExecution(activity, executionID);
		
		// set caller of activity execution
		if(event.getParent() != null && event.getParent() instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent parentevent = (ActivityNodeEntryEvent)event.getParent();
			int parentexeID = parentevent.getActivityExecutionID();
			org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution parentExecution = trace.getActivityExecutionByID(parentexeID);
 			
			if(parentExecution != null && parentevent.getNode() instanceof CallAction) { 
				CallActionExecution callerNodeExecution = parentExecution.getActiveCallActionExecution((CallAction)parentevent.getNode());
				if(callerNodeExecution != null) {					
					activityExecution.setCaller(callerNodeExecution);
					callerNodeExecution.setCallee(activityExecution);
				}
			}
			
		}
		
		// add activity inputs to trace
		for(int i=0;i<activity.node.size();i++) {
			if(activity.node.get(i) instanceof ActivityParameterNode) {
				ActivityParameterNode activityParameterNode = (ActivityParameterNode)activity.node.get(i);
				Parameter parameter = activityParameterNode.parameter;
				if(parameter.direction == ParameterDirectionKind.in || parameter.direction == ParameterDirectionKind.inout) {
//					ParameterValue parameterValue = execution.getParameterValue(parameter);
//					ValueList values = parameterValue.values;
/* TODO					
					if(event.getParent() == null) {
						activityExecution.addUserParameterInput(activityParameterNode, values);
					} else {
						activityExecution.addParameterInput(activityParameterNode, values);
					}
*/					
				}
			}
		}
	}
	
	private void traceHandleActivityExitEvent(ActivityExitEvent event) {
/*TODO		
		int executionID = event.getActivityExecutionID();
		ActivityExecution execution = getActivityExecution(executionID);
		
		// add output to trace
		Trace trace = getTrace(execution);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecutionTrace = trace.getActivityExecutionByID(executionID);				
		
		ActivityParameterNodeActivationList outputActivations = execution.activationGroup.getOutputParameterNodeActivations();
		for (int i = 0; i < outputActivations.size(); i++) {						
			ActivityParameterNodeActivation outputActivation = outputActivations.getValue(i);
			ActivityParameterNode parameternode = (ActivityParameterNode) (outputActivation.node); 
			Parameter parameter = parameternode.parameter;
			ParameterValue parameterValue = execution.getParameterValue(parameter);			
			ValueList parameterValues = parameterValue.values;			
			activityExecutionTrace.addParameterOutput(parameternode, parameterValues);								
		}
*/		
	}
	
	private void traceHandleActivityNodeExitEvent(ActivityNodeExitEvent event) {
		/*
		int executionID = event.getActivityExecutionID();
		ActivityNode node = event.getNode();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution traceActivityExecution = trace.getActivityExecutionByID(executionID);
		
		List<ActivityNodeExecution> nodeExecutions = traceActivityExecution.getExecutionsForEnabledNode(node);
		
		// There should only be one execution for one node in the trace that has not been finished yet
		// Otherwise, the inputs have to be taken into consideration								
		ActivityNodeExecution traceCurrentNodeExecution = nodeExecutions.get(0);
		*/
		
		
/*		TODO
		
		ActivityExecution execution = getActivityExecution(executionID);
		ExecutionStatus executionStatus = getActivityExecutionStatus(execution);	
		
		ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);	
		
*/		
	
//		nodeExecution.getActivityExecution().setActivityNodeExecutionFinishedExecution(nodeExecution);		
		// add output through output pins
/* TODO		
		if(activation instanceof ActionActivation) {
			ActionActivation actionActivation = (ActionActivation)activation;
			Action action = (Action)actionActivation.node;
			for(OutputPin outputPin : action.output) {					
				PinActivation outputPinActivation = actionActivation.getPinActivation(outputPin);
				List<Token> sentTokens = executionStatus.removeTokenSending(outputPinActivation);
				
				if(sentTokens == null) {
					sentTokens = outputPinActivation.heldTokens;
				}
				
				List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();
				if(sentTokens != null) {
					for(Token token : sentTokens) {		
						TokenInstance tokenInstance = executionStatus.getTokenInstance(token);
						if(tokenInstance == null) {
							// token instance has not been added as output yet
							ObjectTokenInstance otokenInstance = new ObjectTokenInstanceImpl();
							ValueInstance valueInstance = new ValueInstanceImpl();
							if(token.getValue() instanceof Reference) {
								valueInstance.setValue(((Reference)token.getValue()).referent.copy());
							} else {
								if(token.getValue()!=null) {
									valueInstance.setValue(token.getValue().copy());
								}
							}
							otokenInstance.setValue(valueInstance);									
							executionStatus.addTokenInstance(token, otokenInstance);
							tokenInstances.add(otokenInstance);
						}
					}
					if(tokenInstances.size() > 0) {
						nodeExecution.addActivityNodeOutput(outputPin, tokenInstances);	
					}
				}
			}
		}
*/
/* TODO		
		// add output through edges
		List<Token> sentTokens = executionStatus.removeTokenSending(activation);		

		if(sentTokens != null) {			
			List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();
			Set<Token> s = new HashSet<Token>(sentTokens);
//			for(Token token : sentTokens) {
			for(Token token : s) {
				Token t = token;
				TokenInstance tokenInstance = null;
				if(token instanceof ForkedToken) {
					ForkedToken forkedToken = (ForkedToken)token;
					if(s.contains(forkedToken.baseToken)) {
						continue;
					}
					t = forkedToken.baseToken;
				}
				
				if(t instanceof ControlToken){					
					tokenInstance = new ControlTokenInstanceImpl();
				} else if (t instanceof ObjectToken){
					tokenInstance = new ObjectTokenInstanceImpl();
					ValueInstance valueInstance = new ValueInstanceImpl();
					if(t.getValue() instanceof Reference) {
						valueInstance.setValue(((Reference)t.getValue()).referent.copy());
					} else {
						valueInstance.setValue(t.getValue().copy());
					}
					((ObjectTokenInstance)tokenInstance).setValue(valueInstance);
				}
				if(tokenInstance != null) {																				
					executionStatus.addTokenInstance(token, tokenInstance);
					tokenInstances.add(tokenInstance);
				}					
			}
			if(tokenInstances.size() > 0) {
				nodeExecution.addActivityNodeOutput(null, tokenInstances);
			}
		}
		
		if(nodeExecution.getOutputs().size() == 0) {
			nodeExecution.addActivityNodeOutput(null, null);
		}
*/		
	}
	
	private void traceHandleSuspendEvent(SuspendEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityExecution execution = getActivityExecution(executionID);
		//ExecutionStatus executionStatus = getActivityExecutionStatus(execution);
		
		Trace trace = getTrace(execution);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution = trace.getActivityExecutionByID(executionID);
		
		// add enabled nodes to trace
		List<ActivityNode> enabledNodes = event.getNewEnabledNodes();
		for(int i=0;i<enabledNodes.size();++i) {
			ActivityNode node = enabledNodes.get(i);
			//ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);		

			ActivityNodeExecution activityNodeExecution = activityExecution.addActivityNodeExecution(node);
/* TODO				
			List<Token> tokens = new ArrayList<Token>();
			tokens.addAll(executionStatus.getEnabledActivationTokens(activation));
		
			// add input through input pins
			if(activation instanceof ActionActivation) {
				ActionActivation actionActivation = (ActionActivation)activation;
				Action action = (Action)actionActivation.node;
				for(InputPin inputPin : action.input) {
					PinActivation inputPinActivation = actionActivation.getPinActivation(inputPin);
					TokenList heldtokens = inputPinActivation.heldTokens;
					
					List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();							
					for(Token token : heldtokens) {
						Token originalToken = executionStatus.getOriginalToken(token);
						if(tokens.contains(originalToken)) {																		
							TokenInstance tokenInstance = executionStatus.getTokenInstance(originalToken);
							if(tokenInstance != null) {
								List<ActivityEdge> traversedEdges = executionStatus.getTraversedActivityEdges(originalToken);
								List<ActivityEdge> traversedEdgesForNode = getTraversedEdge(traversedEdges, inputPinActivation.node); 
								tokenInstance.getTraversedEdges().addAll(traversedEdgesForNode);
								tokenInstances.add(tokenInstance);
							}
							tokens.remove(originalToken);
						}
					}							
					activityNodeExecution.addActivityNodeInput(inputPin, tokenInstances);
				}
*/				
			}
/* TODO			
			// add input through edges			
			List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();
			
			if(activation instanceof DecisionNodeActivation) {
				DecisionNodeActivation decisionNodeActivation = (DecisionNodeActivation)activation;
				ActivityEdgeInstance decisionInputFlowInstance = decisionNodeActivation.getDecisionInputFlowInstance();				
				
				if(decisionInputFlowInstance != null) {
					List<Token> decisionInputTokens = new ArrayList<Token>();
					for(Offer o : decisionInputFlowInstance.offers) {					
							decisionInputTokens.addAll(o.offeredTokens);
					}
				
					tokens.add(decisionInputTokens.get(0));
				}
			}
			
			for(Token token : tokens) {
				TokenInstance tokenInstance = executionStatus.getTokenInstance(token);
				if(token instanceof ForkedToken && tokenInstance == null) {
					// The input token is provided by an anonymous fork node
					Token baseToken = ((ForkedToken) token).baseToken;
					tokenInstance = executionStatus.getTokenInstance(baseToken);							
//					token = ((ForkedToken) token).baseToken;
//					tokenInstance = executionStatus.getTokenInstance(token);							
				}				
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

			if(tokenInstances.size() > 0) {
				activityNodeExecution.addActivityNodeInput(null, tokenInstances);
			}
		}
*/		
	}
	
	private void traceHandleActivityNodeEntryEvent(ActivityNodeEntryEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityNode node = event.getNode();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution traceActivityExecution = trace.getActivityExecutionByID(executionID);
		
		List<ActivityNodeExecution> nodeExecutions = traceActivityExecution.getExecutionsForEnabledNode(node);
		
		// There should only be one execution for one node in the trace that has not been finished yet
		// Otherwise, the inputs have to be taken into consideration								
		ActivityNodeExecution traceCurrentNodeExecution = nodeExecutions.get(0);
		
		// Set the chronological predecessor / successor relationship
		ActivityNodeExecution traceLastNodeExecution = trace.getLastActivityNodeExecution();
		if(traceLastNodeExecution != null) {
			traceLastNodeExecution.setChronologicalSuccessor(traceCurrentNodeExecution);
			traceCurrentNodeExecution.setChronologicalPredecessor(traceLastNodeExecution);
		}		
		
		// Mark node as executed
		traceCurrentNodeExecution.setExecuted(true);
	}
	
	/* TODO
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
	}*/
}