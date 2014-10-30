/*
 * Copyright (c) 2013 Vienna University of Technology.
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
import java.util.List;
import java.util.Set;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
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
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ExpansionRegionExecution;
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
import fUML.Semantics.Activities.CompleteStructuredActivities.ConditionalNodeActivation;
import fUML.Semantics.Activities.CompleteStructuredActivities.StructuredActivityNodeActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionNodeActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation;
import fUML.Semantics.Activities.ExtraStructuredActivities.TokenSet;
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
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.CreateLinkAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectNode;
import fUML.Syntax.Classes.Kernel.Parameter;

public class TraceHandler implements ExecutionEventListener {

	protected static final TracemodelFactory TRACE_FACTORY = TracemodelFactory.eINSTANCE;
	
	private HashMap<Integer, Trace> activityExecutionTrace = new HashMap<Integer, Trace>();
	
	private HashMap<Token, TokenInstance> tokenInstances = new HashMap<Token, TokenInstance>();
	private HashMap<TokenInstance, Token> tokenInstancesToToken = new HashMap<TokenInstance, Token>();
		
	private ExecutionStatus executionStatus;
	
	public TraceHandler(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}
	
	/* (non-Javadoc)
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.modelexecution.fumldebug.core.event.Event)
	 */
	public void notify(Event event) {
		if(event instanceof TraceEvent) {
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
			}
		} else if(event instanceof ExtensionalValueEvent){
			traceHandleExtensionalValueEvent((ExtensionalValueEvent)event);
		}
	}
	
	public Trace getTrace(int executionID) {
		int rootExecutionID = executionStatus.getRootExecutionID(executionID);
		return activityExecutionTrace.get(rootExecutionID);		 
	}
	
	private void traceHandleActivityEntryEvent(ActivityEntryEvent event) {
		int executionID = event.getActivityExecutionID();		
		Activity activity = event.getActivity();		
		
		Trace trace = null; 
		if(event.getParent() == null) {	// create new trace
			trace = new TraceImpl();
			initializeTraceWithExtensionalValuesAtLocus(trace);
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
		
		setActivityExecutionContext(activityExecution);
	}

	private void setActivityExecutionContext(
			org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution) {
		int executionID = activityExecution.getActivityExecutionID();
		Trace trace = activityExecution.getTrace();
		
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
		ActivityExecution activityExecutionVM = activityExecutionStatus.getActivityExecution();
		Object_ context = activityExecutionVM.context;
		
		if(context instanceof Execution)
			return;
		
		ValueInstance contextValueInstance = getOrCreateValueInstance(trace, context);
		if (contextValueInstance != null)
			activityExecution.setContextValueSnapshot(contextValueInstance.getLatestSnapshot());
	}

	private void traceHandleActivityExitEvent(ActivityExitEvent event) {
		// add activity outputs to trace
		int executionID = event.getActivityExecutionID();	
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
				TokenInstance tokenInstance = getTokenInstance(originalToken);				
				if(tokenInstance == null) {
					tokenInstance = getTokenInstanceConsideringCopies(token, activityExecutionStatus);
				}
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

	private void traceHandleActivityNodeEntryEvent(ActivityNodeEntryEvent event) {
		int executionID = event.getActivityExecutionID();
		ActivityNode node = event.getNode();
		
		Trace trace = getTrace(executionID);
		org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution traceActivityExecution = trace.getActivityExecutionByID(executionID);

		// There should only be one execution for one node in the trace that has not been finished yet
		// Otherwise, the inputs have to be taken into consideration
		ActivityNodeExecution traceCurrentNodeExecution = traceActivityExecution.getExecutionForEnabledNode(node);
		
		// Mark activity node execution as under execution 
		traceCurrentNodeExecution.setUnderExecution(true);
		
		// Set the chronological predecessor / successor relationship
		setChronologicalRelationships(traceCurrentNodeExecution);
		
		// Set latest value snapshot for input values
		if(traceCurrentNodeExecution instanceof ActionExecution) {
			ActionExecution actionExecution = (ActionExecution)traceCurrentNodeExecution;
			
			List<InputValue> inputValues = new ArrayList<InputValue>();
			for(Input input : actionExecution.getInputs()) { 
				inputValues.addAll(input.getInputValues());
			}
			
			if(traceCurrentNodeExecution instanceof ExpansionRegionExecution) {
				ExpansionRegionExecution expansionRegionExecution = (ExpansionRegionExecution)traceCurrentNodeExecution;
				for(ExpansionInput input : expansionRegionExecution.getExpansionInputs()) {
					inputValues.addAll(input.getExpansionInputValues());
				}
			}
						 
			for(InputValue inputValue : inputValues) {
				ObjectTokenInstance objectTokenInstance = inputValue.getInputObjectToken();
				ValueInstance transportedValue = objectTokenInstance.getTransportedValue();
				ValueSnapshot latestValueSnapshot = transportedValue.getLatestSnapshot();
				inputValue.setValueSnapshot(latestValueSnapshot);
			}			
		} else if(traceCurrentNodeExecution instanceof DecisionNodeExecution) {
			DecisionNodeExecution decisionNodeExecution = (DecisionNodeExecution)traceCurrentNodeExecution;
			ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(executionID);
			DecisionNodeActivation decisionNodeActivation = (DecisionNodeActivation)activityExecutionStatus.getExecutingActivation(decisionNodeExecution.getNode());

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
							inputValue.setValueSnapshot(latestValueSnapshot);
						}
					}
				}							
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
		//ActivityExecution execution = activityExecutionStatus.getActivityExecution();		
		//ActivityNodeActivation activation = execution.activationGroup.getNodeActivation(node);	
		ActivityNodeActivation activation = activityExecutionStatus.getExecutingActivation(node);
			
		if(activation instanceof ExpansionRegionActivation) {
			StructuredActivityNodeExecution expansionExecution = (StructuredActivityNodeExecution)traceCurrentNodeExecution;
			ExpansionRegionActivation expansionActivation = (ExpansionRegionActivation)activation;
			ExpansionRegion expansionRegion = (ExpansionRegion)expansionActivation.node;
			
			for(ExpansionNode expansionNode : expansionRegion.outputElement) {
				ExpansionNodeActivation expansionNodeActivation = (ExpansionNodeActivation)expansionActivation.group.getNodeActivation(expansionNode);
				
				List<Token> sentTokens = activityExecutionStatus.removeTokenSending(expansionNodeActivation);
				
				if(sentTokens == null) { // happens if no outgoing edge exist
					sentTokens = expansionNodeActivation.heldTokens;
				}
				if(sentTokens != null) {
					// an expansion region does not produce object tokens as output
					// but instead forwards outputs of contained actions
					// (thus the traversed edges of the sent tokens have to be updated)
					updateObjectOutput(sentTokens, activityExecutionStatus);					
				}
			}	
			
			List<Token> sentTokens = activityExecutionStatus.removeTokenSending(expansionActivation);		
			addControlOutput(expansionExecution, sentTokens);
		} else if(activation instanceof ActionActivation && traceCurrentNodeExecution instanceof ActionExecution) {
			ActionExecution actionExecution = (ActionExecution)traceCurrentNodeExecution;
			ActionActivation actionActivation = (ActionActivation)activation;
			Action action = (Action)actionActivation.node;
			for(OutputPin outputPin : action.output) {					
				PinActivation outputPinActivation = actionActivation.getPinActivation(outputPin);
				List<Token> sentTokens = activityExecutionStatus.removeTokenSending(outputPinActivation);
				
				if(sentTokens == null) { // happens if a pin has no outgoing edge
					sentTokens = outputPinActivation.heldTokens;
				}
				if(sentTokens != null) {
					if(activation instanceof StructuredActivityNodeActivation && !(activation instanceof ConditionalNodeActivation)) {
						// a structured activity node does not produce object tokens as output
						// but instead forwards outputs of contained actions
						// (thus the traversed edges of the sent tokens have to be updated)
						updateObjectOutput(sentTokens, activityExecutionStatus);						
					} else {
						addObjectOutput(actionExecution, outputPin, sentTokens);
					}
				}
			}

			List<Token> sentTokens = activityExecutionStatus.removeTokenSending(activation);		
			addControlOutput(actionExecution, sentTokens);
		} else if(activation instanceof InitialNodeActivation) {
			InitialNodeExecution initialNodeExecution = (InitialNodeExecution)traceCurrentNodeExecution;			
			List<Token> sentTokens = activityExecutionStatus.removeTokenSending(activation);					
			if(sentTokens != null && sentTokens.size() > 0) {			
				Token token = sentTokens.get(0);
				if(token instanceof ControlToken){					
					ControlTokenInstance ctrlTokenInstance = TracemodelFactory.eINSTANCE.createControlTokenInstance();
					addTokenInstance(token, ctrlTokenInstance);
					initialNodeExecution.setOutgoingControl(ctrlTokenInstance);						
				}	
			}				
		}
		
		// Mark node as executed 
		traceCurrentNodeExecution.setUnderExecution(false);
		traceCurrentNodeExecution.setExecuted(true);
	}

	private void updateObjectOutput(List<Token> tokens,
			ActivityExecutionStatus activityExecutionStatus) {
		for(Token token : tokens) {	
			TokenInstance tokenInstance = getTokenInstanceConsideringCopies(token, activityExecutionStatus);
			if(tokenInstance == null) {
				// this might happen if no object token was produced as output.
				// in this case a new object token is sent (see method void ObjectNodeActivation.sendOffers(TokenList))
				continue;
			}
			List<ActivityEdge> traversedEdges = activityExecutionStatus.getTraversedActivityEdges(token);
			tokenInstance.getTraversedEdges().addAll(traversedEdges);
			traversedEdges = activityExecutionStatus.getTraversedActivityEdges(activityExecutionStatus.getOriginalToken(token));
			tokenInstance.getTraversedEdges().addAll(traversedEdges);
		}
	}

	private void addObjectOutput(ActionExecution actionExecution, OutputPin outputPin, List<Token> tokens) {
		Trace trace = actionExecution.getActivityExecution().getTrace();
		
		List<OutputValue> outputValues = new ArrayList<OutputValue>();
		for(Token token : tokens) {		
			ValueInstance valueInstance = getOrCreateValueInstance(trace, token.getValue());
			ObjectTokenInstance objectTokenInstance = createObjectTokenInstance(valueInstance);
			addTokenInstance(token, objectTokenInstance);
			OutputValue outputValue = createOutputValue(objectTokenInstance);							
			outputValues.add(outputValue);
		}
		if(outputValues.size() > 0) {
			Output output = TracemodelFactory.eINSTANCE.createOutput(); 
			output.setOutputPin((OutputPin)outputPin);
			output.getOutputValues().addAll(outputValues);
			actionExecution.getOutputs().add(output);	
		}
	}
	
	private OutputValue createOutputValue(ObjectTokenInstance objectTokenInstance) {
		OutputValue outputValue = TRACE_FACTORY.createOutputValue();
		outputValue.setOutputObjectToken(objectTokenInstance);
		ValueInstance transprotedValue = objectTokenInstance.getTransportedValue();
		if(transprotedValue != null) {
			outputValue.setValueSnapshot(transprotedValue.getLatestSnapshot());
		}
		return outputValue;
	}
	
	private ObjectTokenInstance createObjectTokenInstance(ValueInstance valueInstance) {
		ObjectTokenInstance otokenInstance = TRACE_FACTORY.createObjectTokenInstance();
		otokenInstance.setTransportedValue(valueInstance);
		return otokenInstance;
	}

	private void addControlOutput(ActionExecution actionExecution, List<Token> tokens) {
		if(tokens == null) {
			return;
		}

		Set<Token> sentTokens_ = new HashSet<Token>(tokens);
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
				addTokenInstance(token, ctrlTokenInstance);
				actionExecution.getOutgoingControl().add(ctrlTokenInstance);						
			}					
		}

	}
	
	private void traceHandleExtensionalValueEvent(ExtensionalValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		ExtensionalValueEventType eventType = event.getType();

		Collection<Trace> allActiveTraces = this.activityExecutionTrace.values();
		for(Trace trace : allActiveTraces) {
			if(eventType == ExtensionalValueEventType.CREATION) {					
				ValueInstance valueInstance = createValueInstance(extensionalValue);
				valueInstance.setCreator(getCreator(extensionalValue));
				trace.getValueInstances().add(valueInstance);
			} else {
				ValueInstance valueInstance = trace.getValueInstance(extensionalValue);
				if(valueInstance != null) {					
					if(eventType == ExtensionalValueEventType.DESTRUCTION) {							
						valueInstance.setDestroyer(getDestroyer(extensionalValue));
					} else if(!(extensionalValue instanceof Link)){
						ValueSnapshot valueSnapshot = createValueSnapshot(extensionalValue);
						valueInstance.getSnapshots().add(valueSnapshot);
					}
				}
			}
		}
	}	
	
	private ActivityNodeExecution getCreator(Value value) {
		ActivityNodeExecution creator = null;
		if(value instanceof Link) {
			creator = getLinkCreator();
		} else if(value.getClass().equals(Object_.class)) {
			creator = getObjectCreator();
		}
		return creator;
	}
	
	private ActivityNodeExecution getDestroyer(Value value) {
		ActivityNodeExecution destroyer = null;
		if(value instanceof Link) {
			destroyer = getLinkDestroyer();
		} else if(value.getClass().equals(Object_.class)) {
			destroyer = getObjectDestroyer();
		}
		return destroyer;
	}
	
	private ActivityNodeExecution getLinkCreator() {
		List<Class<?>> linkCreatorActionTypes = new ArrayList<Class<?>>();
		linkCreatorActionTypes.add(CreateLinkAction.class);
		linkCreatorActionTypes.add(AddStructuralFeatureValueAction.class);
		List<ActivityNodeExecution> potentialCreators = getCurrentlyExecutingActivityNodeExecutionsOfType(linkCreatorActionTypes);
		if(potentialCreators.size() > 0) { 			
			return potentialCreators.get(0);
		}		
		return null;
	}	
	
	private ActivityNodeExecution getObjectCreator() {
		List<Class<?>> objectCreatorActionTypes = new ArrayList<Class<?>>();
		objectCreatorActionTypes.add(CreateObjectAction.class);
		List<ActivityNodeExecution> potentialCreators = getCurrentlyExecutingActivityNodeExecutionsOfType(objectCreatorActionTypes);
		if(potentialCreators.size() > 0) { 			
			return potentialCreators.get(0);
		}		
		return null;
	}
	
	private ActivityNodeExecution getLinkDestroyer() {
		List<Class<?>> linkDestroyerActionTypes = new ArrayList<Class<?>>();
		linkDestroyerActionTypes.add(ClearAssociationAction.class);
		linkDestroyerActionTypes.add(DestroyLinkAction.class);
		linkDestroyerActionTypes.add(ClearStructuralFeatureAction.class);
		linkDestroyerActionTypes.add(RemoveStructuralFeatureValueAction.class);
		List<ActivityNodeExecution> potentialDestroyers = getCurrentlyExecutingActivityNodeExecutionsOfType(linkDestroyerActionTypes);
		if(potentialDestroyers.size() > 0) { 			
			return potentialDestroyers.get(0);
		}		
		return null;
	}	
	
	private ActivityNodeExecution getObjectDestroyer() {
		List<Class<?>> objectDestroyerActionTypes = new ArrayList<Class<?>>();
		objectDestroyerActionTypes.add(DestroyObjectAction.class);
		List<ActivityNodeExecution> potentialDestroyers = getCurrentlyExecutingActivityNodeExecutionsOfType(objectDestroyerActionTypes);
		if(potentialDestroyers.size() > 0) { 			
			return potentialDestroyers.get(0);
		}		
		return null;
	}
	
	private List<ActivityNodeExecution> getCurrentlyExecutingActivityNodeExecutionsOfType(List<Class<?>> types) {
		// should always provide exactly one (because only one node should be executing), 
		// otherwise more sophisticated implementation is necessary
		List<ActivityNodeExecution> result = new ArrayList<ActivityNodeExecution>();
		Collection<ActivityNodeExecution> currentlyExecutingActivityNodeExecutions = getCurrentlyExecutingActivityNodeExecutions();
		for(ActivityNodeExecution activityNodeExecution : currentlyExecutingActivityNodeExecutions) {
			if(types.contains(activityNodeExecution.getNode().getClass())) {
				result.add(activityNodeExecution);
			}
		}
		return result;
	}
	
	
	private Collection<ActivityNodeExecution> getCurrentlyExecutingActivityNodeExecutions() {
		List<ActivityNodeExecution> allExecutingActivityNodeExecutions = new ArrayList<ActivityNodeExecution>();		
		Collection<Trace> allActiveTraces = this.activityExecutionTrace.values();
		for(Trace trace : allActiveTraces) {
			for(org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution : trace.getActivityExecutions()) {
				ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityExecution.getActivityExecutionID());
				if(activityExecutionStatus != null) {
					Set<ActivityNode> executingNodes = activityExecutionStatus.getExecutingNodes();
					for(ActivityNode executingNode : executingNodes) {
						ActivityNodeExecution executingNodeExecution = activityExecution.getExecutionForEnabledNode(executingNode);
						if(executingNodeExecution != null) {
							allExecutingActivityNodeExecutions.add(executingNodeExecution);
						}
					}
				}
			}
		}
		return allExecutingActivityNodeExecutions;
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
						
						addTokenInstance(token, inputParameterValue.getParameterInputObjectToken());						
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
		for(ActivityNode node : enabledNodes) {
			ActivityNodeExecution activityNodeExecution = createActivityNodeExecution(node);
			activityExecution.getNodeExecutions().add(activityNodeExecution);			

			ActivityNodeActivation activityNodeActivation = activityExecutionStatus.getEnabledActivation(activityNodeExecution.getNode());
			
			if(activityNodeExecution instanceof ExpansionRegionExecution) {
				ExpansionRegionExecution expansionExecution = (ExpansionRegionExecution)activityNodeExecution;
				ExpansionRegionActivation expansionActivation = (ExpansionRegionActivation)activityNodeActivation;
				ExpansionRegion expansionRegion = (ExpansionRegion)expansionExecution.getNode();
 				
				for(int i=0;i<expansionRegion.input.size();++i) {
					InputPin inputPin = expansionRegion.input.get(i);
					TokenSet tokenSet = expansionActivation.inputTokens.get(i);
					TokenList tokens = tokenSet.tokens;
					addObjectInput(expansionExecution, tokens, inputPin, activityExecutionStatus);					
				}
				
				for(int i=0;i<expansionRegion.inputElement.size();++i) {
					ExpansionNode expansionNode = expansionRegion.inputElement.get(i);
					TokenSet tokenSet = expansionActivation.inputExpansionTokens.get(i);
					TokenList tokens = tokenSet.tokens;
					addObjectExpansionInput(expansionExecution, tokens, expansionNode, activityExecutionStatus);					
				}
				
				List<Token> tokens = getTokensForEnabledNode(activityExecutionStatus, expansionActivation);
				addControlInput(expansionExecution, node, tokens, activityExecutionStatus);

			} else if(activityNodeExecution instanceof ActionExecution) {
				ActionExecution actionExecution = (ActionExecution)activityNodeExecution;
				ActionActivation actionActivation = (ActionActivation)activityNodeActivation;
				Action action = (Action)actionActivation.node;

				// add input through input pins
				List<Token> tokens = getTokensForEnabledNode(activityExecutionStatus, actionActivation);
				for(InputPin inputPin : action.input) {
					PinActivation inputPinActivation = actionActivation.getPinActivation(inputPin);
					TokenList heldtokens = inputPinActivation.heldTokens;

					addObjectInput(actionExecution, heldtokens, inputPin, activityExecutionStatus);
					tokens.removeAll(heldtokens);
					
				}

				addControlInput(actionExecution, node, tokens,	activityExecutionStatus);

			} else if(activityNodeExecution instanceof ControlNodeExecution) {
				ControlNodeExecution controlNodeExecution = (ControlNodeExecution)activityNodeExecution;
				List<Token> tokens = getTokensForEnabledNode(activityExecutionStatus, activityNodeActivation); 
				addControlInput(controlNodeExecution, node, tokens, activityExecutionStatus);
			} 

			if(node.inStructuredNode != null) {
				StructuredActivityNode container = node.inStructuredNode;
				StructuredActivityNodeExecution containerExecution = (StructuredActivityNodeExecution)activityExecution.getExecutionForEnabledNode(container);
				containerExecution.getNestedNodeExecutions().add(activityNodeExecution);

			}
		}			
	}
	
	private void addObjectExpansionInput(ExpansionRegionExecution expansionExecution, TokenList tokens, ExpansionNode expansionNode, ActivityExecutionStatus activityExecutionStatus) {
		List<InputValue> inputValues = createInputValues(tokens, expansionNode, activityExecutionStatus);	
		if(inputValues.size() > 0) {
			ExpansionInput input = TRACE_FACTORY.createExpansionInput();
			input.setExpansionNode(expansionNode);
			input.getExpansionInputValues().addAll(inputValues);
			expansionExecution.getExpansionInputs().add(input);
		}
	}
	
	private void addObjectInput(ActionExecution actionExecution, TokenList tokens, InputPin inputPin, ActivityExecutionStatus activityExecutionStatus) {
		List<InputValue> inputValues = createInputValues(tokens, inputPin, activityExecutionStatus);	
		if(inputValues.size() > 0) {
			Input input = TRACE_FACTORY.createInput();
			input.setInputPin((InputPin)inputPin);			
			input.getInputValues().addAll(inputValues);
			actionExecution.getInputs().add(input);
		}
	}

	private List<InputValue> createInputValues(TokenList tokens, ObjectNode objectNode, ActivityExecutionStatus activityExecutionStatus) {
		List<InputValue> inputValues = new ArrayList<InputValue>();	
		for(Token token : tokens) {						
			Token originalToken = activityExecutionStatus.getOriginalToken(token);
			TokenInstance tokenInstance = getTokenInstance(originalToken);
			if(tokenInstance == null) {
				tokenInstance = getTokenInstanceConsideringCopies(token, activityExecutionStatus);
			}
			if(tokenInstance != null && tokenInstance instanceof ObjectTokenInstance) {
				ObjectTokenInstance otokenInstance = (ObjectTokenInstance)tokenInstance;

				List<ActivityEdge> traversedEdges = activityExecutionStatus.getTraversedActivityEdges(originalToken);
				List<ActivityEdge> traversedEdgesForNode = getTraversedEdge(traversedEdges, objectNode); 
				otokenInstance.getTraversedEdges().addAll(traversedEdgesForNode);

				InputValue inputValue = TRACE_FACTORY.createInputValue();
				inputValue.setInputObjectToken(otokenInstance);
				inputValues.add(inputValue);
			}  			
		}
		return inputValues;
	}

	private void addControlInput(ControlNodeExecution controlNodeExecution, ActivityNode node, List<Token> tokens, ActivityExecutionStatus activityExecutionStatus) {		
		List<TokenInstance> tokenInstances = getInputTokenInstances(tokens, node, activityExecutionStatus); 
		controlNodeExecution.getRoutedTokens().addAll(tokenInstances);
	}

	private void addControlInput(ActionExecution actionExecution, ActivityNode node, List<Token> tokens, ActivityExecutionStatus activityExecutionStatus) {		
		List<ControlTokenInstance> ctokenInstances = getInputControlTokenInstances(tokens, node, activityExecutionStatus);
		actionExecution.getIncomingControl().addAll(ctokenInstances);
	}
	
	private InputParameterSetting createInputParameterSetting(org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution activityExecution, Parameter parameter, ValueList values) {
		InputParameterSetting parameterSetting = TRACE_FACTORY.createInputParameterSetting();
		parameterSetting.setParameter(parameter);		
		for(Value value : values) {						
			ValueInstance valueInstance = getOrCreateValueInstance(activityExecution.getTrace(), value);						
			InputParameterValue parameterValue = TRACE_FACTORY.createInputParameterValue();
			parameterValue.setValueSnapshot(valueInstance.getLatestSnapshot());
			parameterSetting.getParameterValues().add(parameterValue);
		}			
		return parameterSetting;		
	}
	
	private void initializeTraceWithExtensionalValuesAtLocus(Trace trace) {
		for(ExtensionalValue extensionalValue : ExecutionContext.getInstance().getLocus().extensionalValues) {
			if(extensionalValue.getClass().equals(Object_.class) || extensionalValue.getClass().equals(Link.class)) {
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
//		if(!(value_ instanceof Link)) {
			ValueSnapshot valueSnapshot = createValueSnapshot(value_);
			valueInstance.getSnapshots().add(valueSnapshot);
			valueInstance.setOriginal(valueSnapshot);
//		}
		return valueInstance;
	}
	
	private ValueSnapshot createValueSnapshot(Value value) {
		ValueSnapshot valueSnapshot = TracemodelFactory.eINSTANCE.createValueSnapshot();
		valueSnapshot.setValue(value.copy());
		return valueSnapshot;
	}
	
	private ValueInstance getOrCreateValueInstance(Trace trace, Value value) {
		if(value == null) {
			return null;
		}
		
		ValueInstance valueInstance;
		
		Value value_ = value;
		if(value instanceof Reference) {
			value_ = ((Reference)value).referent;
		}
	
		if(valueInstanceExists(trace, value_)) {
			valueInstance = trace.getValueInstance(value_);
		} else {
			valueInstance = createValueInstance(value_);
			trace.getValueInstances().add(valueInstance);
		}
		
		return valueInstance;
	}
	
	private boolean valueInstanceExists(Trace trace, Value value) {
		ValueInstance existingValueInstance = trace.getValueInstance(value);
		return existingValueInstance != null;
	}
	
	private List<Token> getTokensForEnabledNode(ActivityExecutionStatus executionStatus, ActivityNodeActivation activation) {
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
		}
		
		return inputParameterValue;
	}
	
	private OutputParameterValue createOutputParameterValue(Trace trace, ObjectTokenInstance otokenInstance) {		
		OutputParameterValue outputParameterValue = TRACE_FACTORY.createOutputParameterValue();
		outputParameterValue.setParameterOutputObjectToken(otokenInstance);

		outputParameterValue.setValueSnapshot(otokenInstance.getTransportedValue().getLatestSnapshot());
		
		return outputParameterValue;				
	}	
	
	private List<ControlTokenInstance> getInputControlTokenInstances(List<Token> tokens, ActivityNode node, ActivityExecutionStatus activityExecutionStatus) {
		List<ControlTokenInstance> ctokenInstances = new ArrayList<ControlTokenInstance>();
		
		List<TokenInstance> tokenInstances = getInputTokenInstances(tokens, node, activityExecutionStatus);
		for(TokenInstance tokenInstance : tokenInstances) {
			if(tokenInstance instanceof ControlTokenInstance) {
				ctokenInstances.add((ControlTokenInstance)tokenInstance);
			}
		}
		return ctokenInstances;
	}
	
	private List<TokenInstance> getInputTokenInstances(List<Token> tokens, ActivityNode node, ActivityExecutionStatus activityExecutionStatus) {
		List<TokenInstance> tokenInstances = new ArrayList<TokenInstance>();
		for(Token token : tokens) {
			TokenInstance tokenInstance = getTokenInstance(token);
			if(tokenInstance != null) {
				List<ActivityEdge> traversedEdges = activityExecutionStatus.getTraversedActivityEdges(token);
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
		} else if(activityNode instanceof ExpansionRegion) {
			activityNodeExecution = TRACE_FACTORY.createExpansionRegionExecution();
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
	
	private TokenInstance getTokenInstanceConsideringCopies(Token token, ActivityExecutionStatus activityExecutionStatus) {
		TokenInstance tokenInstance = tokenInstances.get(token);
		if(token instanceof ForkedToken && tokenInstance == null) {
			// The input token is provided by an anonymous fork node
			Token baseToken = ((ForkedToken) token).baseToken;
			tokenInstance = tokenInstances.get(baseToken);	
			token = baseToken;
		}
		
		if(tokenInstance == null) {
			Token originalToken = activityExecutionStatus.getOriginalToken(token);
			if(originalToken != token) {
				tokenInstance = getTokenInstanceConsideringCopies(originalToken, activityExecutionStatus);
			}
		}
		
		return tokenInstance;
	}
	
	private TokenInstance getTokenInstance(Token token) {
		TokenInstance tokenInstance = tokenInstances.get(token);
		if(token instanceof ForkedToken && tokenInstance == null) {
			// The input token is provided by an anonymous fork node
			Token baseToken = ((ForkedToken) token).baseToken;
			tokenInstance = tokenInstances.get(baseToken);							
		}		
		return tokenInstance;
	}
	
	private void addTokenInstance(Token token, TokenInstance tokenInstance) {
		tokenInstances.put(token, tokenInstance);
		tokenInstancesToToken.put(tokenInstance, token);
	}

}
