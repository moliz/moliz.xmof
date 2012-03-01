package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.event.Event;


public interface ExecutionEventProvider {

	public void addEventListener(ExecutionEventListener listener);
	
	public void removeEventListener(ExecutionEventListener listener);
	
	public void notifyEventListener(Event event);
	
}
