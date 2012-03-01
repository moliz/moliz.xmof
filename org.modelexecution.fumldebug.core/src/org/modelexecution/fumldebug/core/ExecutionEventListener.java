package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.event.Event;


public interface ExecutionEventListener {

	public void notify(Event event);
	
}
