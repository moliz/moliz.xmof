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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.xmof.states.states.Event;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesFactory;
import org.modelexecution.xmof.states.states.Transition;

import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class StatesBuilder extends EContentAdapter implements
		ExecutionEventListener {

	private static final StatesFactory STATES = StatesFactory.eINSTANCE;

	private Resource modelResource;

	private Action currentAction = null;

	private StateSystem stateSystem = null;

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
		if (isActionEntry(event))
			currentAction = getActionEntry(event);
		else if (isActionExit(event))
			currentAction = null;
	}

	private boolean isActionEntry(
			org.modelexecution.fumldebug.core.event.Event event) {
		Action action = getActionEntry(event);
		return action != null;
	}

	private boolean isActionExit(
			org.modelexecution.fumldebug.core.event.Event event) {
		Action action = getActionExit(event);
		return action != null;
	}

	private Action getActionEntry(
			org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			return getAction(activityNodeEntryEvent.getNode());
		}
		return null;
	}

	private Action getActionExit(
			org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeExitEvent = (ActivityNodeExitEvent) event;
			return getAction(activityNodeExitEvent.getNode());
		}
		return null;
	}

	private Action getAction(ActivityNode node) {
		if (node instanceof Action)
			return (Action) node;
		else
			return null;
	}

	@Override
	public void notifyChanged(Notification notification) {
		addNewState();
	}

	private void addNewState() {
		State lastState = getLastState();
		State newState = createNewState();
		stateSystem.getStates().add(newState);
		if (stateSystem.getStates().size() > 1) {
			Transition transition = createNewTransition(lastState, newState,
					getCurrentEvent());
			stateSystem.getTransitions().add(transition);
		}
	}

	private String getCurrentEvent() {
		if (currentAction != null) {
			return currentAction.qualifiedName;
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
		EList<EObject> contents = modelResource.getContents();
		Copier copier = new Copier();
		Collection<EObject> contentsCopy = copier.copyAll(contents);
		copier.copyReferences();
		state.getObjects().addAll(contentsCopy);
		return state;
	}

	private Transition createNewTransition(State source, State target,
			String eventQualifiedName) {
		Transition transition = STATES.createTransition();
		transition.setSource(source);
		transition.setTarget(target);
		Event event = STATES.createEvent();
		event.setQualifiedName(eventQualifiedName);
		transition.setEvent(event);
		return transition;
	}

	public StateSystem getStateSystem() {
		return stateSystem;
	}
}
