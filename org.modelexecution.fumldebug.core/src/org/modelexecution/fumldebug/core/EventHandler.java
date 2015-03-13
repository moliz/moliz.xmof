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
import java.util.HashMap;
import java.util.LinkedHashSet;
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
import org.modelexecution.fumldebug.core.event.FeatureValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.BreakpointEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ExtensionalValueEventImpl;
import org.modelexecution.fumldebug.core.event.impl.FeatureValueEventImpl;
import org.modelexecution.fumldebug.core.event.impl.SuspendEventImpl;

import fUML.Semantics.Actions.BasicActions.CallActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Element;

/**
 * @author Tanja
 *
 */
public class EventHandler implements ExecutionEventProvider { 

	private Set<ExecutionEventListener> listeners = new LinkedHashSet<ExecutionEventListener>();
	private Set<ExecutionEventListener> primaryListeners = new LinkedHashSet<ExecutionEventListener>();
	
	private ExecutionStatus executionStatus;
	
	private EventStore eventStore = new EventStore();
	
	public EventHandler(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}
	
	public void addEventListener(ExecutionEventListener listener) {
		listeners.add(listener);
	}

	public void addPrimaryEventListener(ExecutionEventListener listener) {
		primaryListeners.add(listener);
	}
	
	public void removeEventListener(ExecutionEventListener listener) {
		listeners.remove(listener);
	}

	public void notifyEventListener(Event event) {
		for(ExecutionEventListener l : new ArrayList<ExecutionEventListener>(primaryListeners)) {
			l.notify(event);
		}
		
		if(shouldTransferEvent(event)) {
			for (ExecutionEventListener l : new ArrayList<ExecutionEventListener>(listeners)) {
				l.notify(event);
			}
		}
	}
	
	private boolean shouldTransferEvent(Event event) {
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
	
	public void handleActivityEntry(ActivityExecution activityExecution, CallActionActivation callerNodeActivation) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityExecution);
		Activity activity = activityExecutionStatus.getActivity();	
		int executionID = activityExecutionStatus.getExecutionID();
		
		ActivityNodeEntryEvent callerActivityEntryEvent = null;
		if(callerNodeActivation != null) {
			callerActivityEntryEvent = eventStore.getActivityNodeEntryEvent(callerNodeActivation);
		}
				
		ActivityEntryEvent event = new ActivityEntryEventImpl(executionID, activity, callerActivityEntryEvent);	
		eventStore.addActivityEntryEvent(activityExecution, event);
		
		notifyEventListener(event);
	}
	
	public void handleActivityExit(ActivityExecution activityExecution) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityExecution);
		Activity activity = activityExecutionStatus.getActivity();
		int executionID = activityExecutionStatus.getExecutionID();
		ActivityEntryEvent entryevent = eventStore.removeActivityEntryEvent(activityExecution);
		
		ActivityExitEvent exitevent = new ActivityExitEventImpl(executionID, activity, entryevent);
		notifyEventListener(exitevent);
	}
	
	public void handleActivityNodeEntry(ActivityNodeActivation activityNodeActivation) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityNodeActivation.getActivityExecution());
		ActivityExecution activityExecution = activityExecutionStatus.getActivityExecution();
		int executionID = activityExecutionStatus.getExecutionID();
		ActivityEntryEvent activityentry = eventStore.getActivityEntryEvent(activityExecution);

		ActivityNodeEntryEvent nodeentry = new ActivityNodeEntryEventImpl(executionID, activityNodeActivation.node, activityentry);		
		eventStore.addActivityNodeEntryEvent(activityNodeActivation, nodeentry);
 		notifyEventListener(nodeentry); 	
	}
	
	public void handleActivityNodeExit(ActivityNodeActivation activityNodeActivation) {	
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityNodeActivation.getActivityExecution());
		if (activityExecutionStatus == null) {
			return;
		}
		
		int executionID = activityExecutionStatus.getExecutionID();
		ActivityNodeEntryEvent nodeentry = eventStore.removeActivityNodeEntryEvent(activityNodeActivation);
		
		ActivityNodeExitEvent event = new ActivityNodeExitEventImpl(executionID, activityNodeActivation.node, nodeentry);
		notifyEventListener(event);
	}
	
	public void handleSuspension(ActivityExecution activityExecution, Element location, List<ActivityNode> newEnabledNodes) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityExecution);
		int executionID = activityExecutionStatus.getExecutionID();
		ActivityEntryEvent activityEntryEvent = eventStore.getActivityEntryEvent(activityExecution);
		
		SuspendEvent event = new SuspendEventImpl(executionID, location, activityEntryEvent);
		event.getNewEnabledNodes().addAll(newEnabledNodes);
		notifyEventListener(event);		
	}
	
	public void handleBreakpointSuspension(ActivityExecution activityExecution, Element location, List<Breakpoint> hitBreakpoints, List<ActivityNode> newEnabledNodes) {
		ActivityExecutionStatus activityExecutionStatus = executionStatus.getActivityExecutionStatus(activityExecution);
		int executionID = activityExecutionStatus.getExecutionID();
		ActivityEntryEvent activityEntryEvent = eventStore.getActivityEntryEvent(activityExecution);
		
		BreakpointEvent event = new BreakpointEventImpl(executionID, location, activityEntryEvent);
		event.getBreakpoints().addAll(hitBreakpoints);
		event.getNewEnabledNodes().addAll(newEnabledNodes);
		notifyEventListener(event);		
	}
	
	public void handleExtensionalValueCreation(ExtensionalValue extensionalValue) {
		ExtensionalValueEvent event = new ExtensionalValueEventImpl(extensionalValue, ExtensionalValueEventType.CREATION);
		notifyEventListener(event);
	}
	
	public void handleExtensionalValueDestruction(ExtensionalValue extensionalValue) {
		ExtensionalValueEvent event = new ExtensionalValueEventImpl(extensionalValue, ExtensionalValueEventType.DESTRUCTION);
		notifyEventListener(event);
	}
	
	public void handleObjectTypeAddition(Object_ object) {
		ExtensionalValueEvent event = new ExtensionalValueEventImpl(object, ExtensionalValueEventType.TYPE_ADDED);
		notifyEventListener(event);
	}
	
	public void handleObjectTypeRemoval(Object_ object) {
		ExtensionalValueEvent event = new ExtensionalValueEventImpl(object, ExtensionalValueEventType.TYPE_REMOVED);
		notifyEventListener(event);
	}
	
	public void handleFeatureValueCreation(Object_ object, FeatureValue featureValue) {
		FeatureValueEvent event = new FeatureValueEventImpl(object, ExtensionalValueEventType.VALUE_CREATION, featureValue);
		notifyEventListener(event);
	}
	
	public void handleFeatureValueDestruction(Object_ object, FeatureValue featureValue) {
		FeatureValueEvent event = new FeatureValueEventImpl(object, ExtensionalValueEventType.VALUE_DESTRUCTION, featureValue);
		notifyEventListener(event);
	}
	
	public void handleFeatureValueAdded(Object_ object, FeatureValue featureValue, ValueList values, int position) {
		FeatureValueEvent event = new FeatureValueEventImpl(object, ExtensionalValueEventType.VALUE_ADDED, featureValue, values, position);
		notifyEventListener(event);
	}
	
	public void handleFeatureValueRemoved(Object_ object, FeatureValue featureValue, ValueList values, int position) {
		FeatureValueEvent event = new FeatureValueEventImpl(object, ExtensionalValueEventType.VALUE_REMOVED, featureValue, values, position);
		notifyEventListener(event);
	}
	
	private class EventStore {
		private HashMap<ActivityExecution, ActivityEntryEvent> activityEntryEvents = new HashMap<ActivityExecution, ActivityEntryEvent>();
		private HashMap<ActivityNodeActivation, ActivityNodeEntryEvent> activityNodeEntryEvents = new HashMap<ActivityNodeActivation, ActivityNodeEntryEvent>();
		
		private void addActivityEntryEvent(ActivityExecution activityExecution, ActivityEntryEvent activityEntryEvent) {
			activityEntryEvents.put(activityExecution, activityEntryEvent);
		}
		
		private void addActivityNodeEntryEvent(ActivityNodeActivation activityNodeActivation, ActivityNodeEntryEvent activityNodeEntryEvent) {
			activityNodeEntryEvents.put(activityNodeActivation, activityNodeEntryEvent);
		}
		
		private ActivityEntryEvent removeActivityEntryEvent(ActivityExecution activityExecution) {
			return activityEntryEvents.remove(activityExecution);
		}
		
		private ActivityNodeEntryEvent removeActivityNodeEntryEvent(ActivityNodeActivation activityNodeActivation) {
			return activityNodeEntryEvents.remove(activityNodeActivation);
		}
		
		private ActivityEntryEvent getActivityEntryEvent(ActivityExecution activityExecution) {
			return activityEntryEvents.get(activityExecution);
		}
		
		private ActivityNodeEntryEvent getActivityNodeEntryEvent(ActivityNodeActivation activityNodeActivation) {
			return activityNodeEntryEvents.get(activityNodeActivation);
		}
	}
	
}
