/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.states.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.states.states.Event;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesFactory;
import org.modelexecution.xmof.states.states.Transition;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class StatesBuilder extends EContentAdapter implements
		ExecutionEventListener {

	private static final StatesFactory STATES = StatesFactory.eINSTANCE;

	private Resource modelResource;

	private StateSystem stateSystem = null;

	private Map<org.modelexecution.fumldebug.core.event.Event, State> event2state = new HashMap<org.modelexecution.fumldebug.core.event.Event, State>();
	private ActivityNodeEntryEvent currentActionEntryEvent = null;

	private XMOFVirtualMachine vm;

	private int rootActivityExecutionID = -1;
	private Trace trace = null;

	public StatesBuilder(Resource modelResource) {
		this.modelResource = modelResource;
		initialize();
	}

	private void initialize() {
		addResourceAdapter();
		createStateSystem();
	}

	private void addResourceAdapter() {
		this.modelResource.eAdapters().add(this);
	}

	private void createStateSystem() {
		stateSystem = STATES.createStateSystem();
		State state = createNewState();
		stateSystem.getStates().add(state);
	}

	@Override
	public void notify(org.modelexecution.fumldebug.core.event.Event event) {
		if (isActionEntry(event)) {
			currentActionEntryEvent = getActionEntry(event);
		} else if (isActionExit(event)) {
			currentActionEntryEvent = null;
		} else if (isActivityEntry(event) && !rootActivityExecutionSet()) {
			rootActivityExecutionID = getActivityExecutionID(event);
			retrieveTraceForStateSystem();
		}
	}

	private void retrieveTraceForStateSystem() {
		if (traceExists()) {
			this.trace = getTrace();
			stateSystem.setTrace(trace);
		}
	}

	private boolean traceExists() {
		if (isVMSet())
			return getTrace() != null;
		else
			return false;
	}

	private Trace getTrace() {
		Trace trace = vm.getRawExecutionContext().getTrace(
				rootActivityExecutionID);
		return trace;
	}

	private boolean isVMSet() {
		return vm != null;
	}

	private boolean rootActivityExecutionSet() {
		return rootActivityExecutionID != -1;
	}

	private int getActivityExecutionID(
			org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			return activityEntryEvent.getActivityExecutionID();
		}
		return -1;
	}

	protected boolean isActivityEntry(
			org.modelexecution.fumldebug.core.event.Event event) {
		return getActivityExecutionID(event) != -1;
	}

	protected boolean isActionEntry(
			org.modelexecution.fumldebug.core.event.Event event) {
		ActivityNodeEntryEvent actionEntry = getActionEntry(event);
		return actionEntry != null;
	}

	protected boolean isActionExit(
			org.modelexecution.fumldebug.core.event.Event event) {
		ActivityNodeExitEvent actionExit = getActionExit(event);
		return actionExit != null;
	}

	private ActivityNodeEntryEvent getActionEntry(
			org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			if (isActionEvent(activityNodeEntryEvent))
				return activityNodeEntryEvent;
		}
		return null;
	}

	private boolean isActionEvent(ActivityNodeEvent event) {
		Action action = getAction(event);
		return action != null;
	}

	private ActivityNodeExitEvent getActionExit(
			org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeExitEvent = (ActivityNodeExitEvent) event;
			if (isActionEvent(activityNodeExitEvent))
				return activityNodeExitEvent;
		}
		return null;
	}

	private Action getAction(ActivityNodeEvent nodeEntryEvent) {
		ActivityNode node = nodeEntryEvent.getNode();
		if (node instanceof Action)
			return (Action) node;
		else
			return null;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (isNewStateRequired())
			addNewState();
		else
			updateState(getLastState());
		adapt(notification);
	}

	private void adapt(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.REMOVE_MANY:
		case Notification.REMOVE:
		case Notification.UNSET:
			EObject eObject = getEObject(notification);
			adapt(eObject);
		}

	}

	private void adapt(EObject eObject) {
		if (eObject == null)
			return;
		if (!eObject.eAdapters().contains(this))
			eObject.eAdapters().add(this);
	}

	private EObject getEObject(Notification notification) {
		if (notification.getOldValue() instanceof EObject)
			return (EObject) notification.getOldValue();
		return null;
	}

	private void updateState(State lastState) {
		addObjectsToState(lastState);
	}

	protected boolean isNewStateRequired() {
		return !event2state.containsKey(currentActionEntryEvent);
	}

	protected void addNewState() {
		State lastState = getLastState();
		State newState = createNewState();
		event2state.put(currentActionEntryEvent, newState);
		stateSystem.getStates().add(newState);
		if (stateSystem.getStates().size() > 1) {
			Transition transition = createNewTransition(lastState, newState,
					createEvent());
			stateSystem.getTransitions().add(transition);
		}
	}

	private Event createEvent() {
		if (currentActionEntryEvent != null) {
			Event event = STATES.createEvent();
			event.setQualifiedName(getCurrentEventQualifiedName());
			event.setActionExecution(getCurrentActionExecution());
			return event;
		}
		return null;
	}

	private ActionExecution getCurrentActionExecution() {
		if (currentActionEntryEvent != null && trace != null) {
			int activityExecutionID = currentActionEntryEvent
					.getActivityExecutionID();
			Action action = getAction(currentActionEntryEvent);
			ActivityExecution activityExecution = trace
					.getActivityExecutionByID(activityExecutionID);
			return getActionExecution(activityExecution, action);
		}
		return null;
	}

	private ActionExecution getActionExecution(
			ActivityExecution activityExecution, Action action) {
		List<ActivityNodeExecution> nodeExecutions = activityExecution
				.getNodeExecutionsByNode(action);
		for (ActivityNodeExecution nodeExecution : nodeExecutions) {
			if (nodeExecution.isUnderExecution()
					&& nodeExecution instanceof ActionExecution) {
				ActionExecution actionExecution = (ActionExecution) nodeExecution;
				return actionExecution;
			}
		}
		return null;
	}

	private String getCurrentEventQualifiedName() {
		if (currentActionEntryEvent != null) {
			return currentActionEntryEvent.getNode().qualifiedName;
		}
		return null;
	}

	private State getLastState() {
		int stateNumber = stateSystem.getStates().size();
		State lastState = stateSystem.getStates().get(stateNumber - 1);
		return lastState;
	}

	private State createNewState() {
		State state = STATES.createState();
		addObjectsToState(state);
		return state;
	}
	
	private void addObjectsToState(State state) {
		EList<EObject> contents = modelResource.getContents();
		Copier copier = new Copier();
//		copier.copyAll(contents);
//		copier.copyReferences();
		
		state.getObjects().clear();
		for(EObject originalObject : contents) {
			EObject copy = copier.copy(originalObject);
			state.getObjects().add(copy);
			stateSystem.addObjectsState(state, originalObject, copy);
		}
		
		copier.copyReferences();
	}

	private Transition createNewTransition(State source, State target,
			Event event) {
		Transition transition = STATES.createTransition();
		transition.setSource(source);
		transition.setTarget(target);
		transition.setEvent(event);
		return transition;
	}

	public StateSystem getStateSystem() {
		return stateSystem;
	}

	public void setVM(XMOFVirtualMachine vm) {
		this.vm = vm;
	}
	
	public XMOFVirtualMachine getVM() {
		return vm;
	}
}
