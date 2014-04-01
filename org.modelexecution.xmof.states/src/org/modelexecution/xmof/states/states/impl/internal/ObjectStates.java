package org.modelexecution.xmof.states.states.impl.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.modelexecution.xmof.states.states.State;

public class ObjectStates {

	private State state;
	
	private Map<EObject, EObject> originalToCopy = new HashMap<EObject, EObject>();
	private Map<EObject, EObject> copyToOrignal = new HashMap<EObject, EObject>();
	
	public ObjectStates(State state) {
		this.state = state;
	}
	
	public void addObjectState(EObject eObject, EObject eObjectState) {
		originalToCopy.put(eObject, eObjectState);
		copyToOrignal.put(eObjectState, eObject);
	}
	
	public State getState() {
		return state;
	}
	
	public EObject getEObjectState(EObject eObject) {
		return originalToCopy.get(eObject);
	}
	
	public EObject getEObject(EObject eObjectState) {
		return copyToOrignal.get(eObjectState);
	}
	
}