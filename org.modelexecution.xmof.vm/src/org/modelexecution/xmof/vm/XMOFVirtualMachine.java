/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.xmof.XMOFConverter;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.xmof.Syntax.Classes.Kernel.MainEClass;
import org.modelexecution.xmof.vm.internal.XMOFInstanceMap;

import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * The virtual machine for executing {@link XMOFBasedModel xMOF-based models}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFVirtualMachine {

	private final ExecutionContext executionContext = ExecutionContext
			.getInstance();

	private XMOFBasedModel model;
	private IConversionResult xMOFConversionResult;
	private XMOFInstanceMap instanceMap;
	
	public int executionID = -1;
	
	public XMOFVirtualMachine(XMOFBasedModel modelToBeExecuted) {
		super();
		this.model = modelToBeExecuted;
		initialize();
		executionContext.addEventListener(new ExecutionEventListener() {			
			@Override
			public void notify(Event event) {
				if(event instanceof ActivityEntryEvent) {
					ActivityEntryEvent activityEntry = (ActivityEntryEvent)event;
					executionID = activityEntry.getActivityExecutionID();
					System.out.println("Activity Entry: " + activityEntry.getActivity().name);
				} else if(event instanceof ActivityExitEvent) {
					ActivityExitEvent activityExit = (ActivityExitEvent)event;
					System.out.println("Activity Exit: " + activityExit.getActivity().name);
				} else if(event instanceof ActivityNodeEntryEvent) {
					ActivityNodeEntryEvent nodeEntry = (ActivityNodeEntryEvent)event;
					System.out.println("Node Entry: " + nodeEntry.getNode().name + " (" + nodeEntry.getNode().getClass().getName() + ")");
				} else if(event instanceof ActivityNodeExitEvent) {
					ActivityNodeExitEvent nodeExit = (ActivityNodeExitEvent)event;
					System.out.println("Node Exit: " + nodeExit.getNode().name + " (" + nodeExit.getNode().getClass().getName() + ")");
				} else if(event instanceof SuspendEvent) {
					SuspendEvent suspendEvent = (SuspendEvent)event;
					if(suspendEvent.getLocation() instanceof Activity) {
						System.out.println("Suspend: " + ((Activity)suspendEvent.getLocation()).name);
					} else if (suspendEvent.getLocation() instanceof ActivityNode) {
						System.out.println("Suspend: " + ((ActivityNode)suspendEvent.getLocation()).name + "(" + ((ActivityNode)suspendEvent.getLocation()).getClass().getName() + ")");
					} else {
						System.out.println("Suspend: " + suspendEvent.getLocation());
					}
				}				
			}
		});
	}

	private void initialize() {
		convertMetamodel();
		initializeInstanceMap();
		replaceOpaqueBehaviors();
	}

	private void replaceOpaqueBehaviors() {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		for(Activity activity : xMOFConversionResult.getActivities()) {
			nodesWithBehavior.addAll(getBehaviorNodes(activity.node));
		}
		
		for(ActivityNode node : nodesWithBehavior) {
			if(node instanceof CallBehaviorAction) {
				CallBehaviorAction callBehaviorAction = (CallBehaviorAction)node;
				Behavior behavior = callBehaviorAction.behavior;
				OpaqueBehavior behaviorReplacement = executionContext.getOpaqueBehavior(behavior.name);
				callBehaviorAction.behavior = behaviorReplacement;
			} else if(node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode)node;
				Behavior behavior = decision.decisionInput;
				OpaqueBehavior behaviorReplacement = executionContext.getOpaqueBehavior(behavior.name);
				decision.decisionInput = behaviorReplacement;
			}
		}				
	}
	
	private List<ActivityNode> getBehaviorNodes(List<ActivityNode> nodes) {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		for(ActivityNode node : nodes) {
			if(node instanceof CallBehaviorAction) {
				CallBehaviorAction action = (CallBehaviorAction)node;
				nodesWithBehavior.add(action);					
			} else if(node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode)node;
				if(decision.decisionInput != null){
					nodesWithBehavior.add(decision);
				}
			}if(node instanceof StructuredActivityNode) {
				StructuredActivityNode structurednode = (StructuredActivityNode)node;
				nodesWithBehavior.addAll(getBehaviorNodes(structurednode.node));
			}
		}
		return nodesWithBehavior;
	}		

	private void initializeInstanceMap() {
		this.instanceMap = new XMOFInstanceMap(xMOFConversionResult,
				model.getModelElements(), executionContext.getLocus());
	}

	private void convertMetamodel() {
		XMOFConverter xMOFConverter = new XMOFConverter();
		if (xMOFConverter.canConvert(getMetamodelPackage())) {
			xMOFConversionResult = xMOFConverter.convert(getMetamodelPackage());
		}
	}

	private EPackage getMetamodelPackage() {
		return model.getMetamodelPackages().get(0);
	}

	public XMOFBasedModel getModel() {
		return model;
	}

	public boolean mayRun() {
		return isXMOFConversionOK();
	}

	private boolean isXMOFConversionOK() {
		return xMOFConversionResult != null
				&& xMOFConversionResult.getStatus().isOK();
	}

	public void run() {
		// TODO add some kind of listener
		// maybe set the instanceMapper as a listener which would allow it to
		// modify the model elements at runtime
		executeAllMainObjects();
	}

	private void executeAllMainObjects() {
		for (EObject mainClassObject : model.getMainEClassObjects()) {
			executionContext.execute(getClassifierBehavior(mainClassObject),
					instanceMap.getObject(mainClassObject), null);
		}
	}

	private Behavior getClassifierBehavior(EObject mainClassObject) {
		EClass mainEClassInstance = mainClassObject.eClass();
		Assert.isTrue(XMOFBasedModel.MAIN_E_CLASS
				.isInstance(mainEClassInstance));
		MainEClass mainEClass = (MainEClass) mainEClassInstance;
		return (Behavior) xMOFConversionResult.getFUMLElement(mainEClass
				.getClassifierBehavior());
	}

}
