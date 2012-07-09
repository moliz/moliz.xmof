/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation based on the EventTest in org.modelexecution.fumldebugcore.test
 */
package org.modelexecution.fumldebug.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * This test only verifies whether the events (and thus the aspects)
 * work properly when executing an activity via the {@link ExecutionContext}
 * provided by the plug-in org.modelexecution.fumldebug. Therefore, this
 * test only executes one activity and checks if events are notified. If
 * this is the case, the aspects seem to work.
 * 
 * This test is based on EventTest in org.modelexecution.fumldebug.core.test.
 * 
 * @author Philip Langer
 *
 */
public class AspectsViaPluginTest implements ExecutionEventListener {
	
	private List<Event> eventlist = new ArrayList<Event>();
	
	public AspectsViaPluginTest() {
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}

	@Test
	public void testEventsWhenExecutingActivity() {
		Activity activity = ActivityFactory.createActivity("Activity TestActivityExecution");
		ExecutionContext.getInstance().execute(activity, null, new ParameterValueList());
		
		assertEquals(2, eventlist.size());
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		assertEquals(activity, ((ActivityEntryEvent)eventlist.get(0)).getActivity());
		assertTrue(eventlist.get(1) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(1)).getActivity());		
	}
	
	@Override
	public void notify(Event event) {		
		eventlist.add(event);
	}
	
}
