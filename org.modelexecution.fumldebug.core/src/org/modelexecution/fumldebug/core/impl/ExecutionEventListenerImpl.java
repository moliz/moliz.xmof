package org.modelexecution.fumldebug.core.impl;

import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;

public class ExecutionEventListenerImpl implements ExecutionEventListener {

	@Override
	public void notify(Event event) {
		// TODO Auto-generated method stub
		System.out.println(event.toString());
	}

}
