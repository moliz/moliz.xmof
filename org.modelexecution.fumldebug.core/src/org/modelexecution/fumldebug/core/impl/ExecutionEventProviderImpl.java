package org.modelexecution.fumldebug.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.ExecutionEventProvider;
import org.modelexecution.fumldebug.core.event.Event;

public class ExecutionEventProviderImpl implements ExecutionEventProvider {

	private List<ExecutionEventListener> listeners = new ArrayList<ExecutionEventListener>();
	
	@Override
	public void addEventListener(ExecutionEventListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void removeEventListener(ExecutionEventListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void notifyEventListener(Event event) {
		for(ExecutionEventListener l : listeners){
			l.notify(event);
		}
	}
	
}
