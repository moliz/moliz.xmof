package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.event.Event;

public interface ExecutionEventEmitter {
	
	public void notify(Event event);
	
}
