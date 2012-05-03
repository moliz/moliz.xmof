/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.launch.internal;

import java.util.LinkedList;
import java.util.Queue;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.debugger.launch.internal.ActivityExecCommand.Kind;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityThread extends Thread {

	private static final long SLEEP_TIME = 500;

	private Activity activity;
	private ExecutionContext context;

	private Queue<ActivityExecCommand> cmdQueue = new LinkedList<ActivityExecCommand>();

	public ActivityThread(Activity activity, ExecutionContext context) {
		this.activity = activity;
		this.context = context;
	}

	@Override
	public void run() {
		startActivity();
		goAsleep();
		while (haveNoTerminateCmd()) {
			processNextCmd();
			goAsleep();
		}
	}

	private void goAsleep() {
		try {
			sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
		}
	}

	private void startActivity() {
		synchronized (context) {
			context.debug(activity, null, new ParameterValueList());
		}
	}

	private synchronized boolean haveNoTerminateCmd() {
		return isCmdQueueEmpty()
				|| !Kind.TERMINATE.equals(cmdQueue.peek().getKind());
	}

	private void processNextCmd() {
		if (!isCmdQueueEmpty()) {
			ActivityExecCommand cmd;
			synchronized (cmdQueue) {
				cmd = cmdQueue.poll();
			}
			synchronized (context) {
				cmd.execute(context);
			}
		}
	}

	private synchronized boolean isCmdQueueEmpty() {
		return cmdQueue.peek() == null;
	}

	public synchronized void addCommand(ActivityExecCommand cmd) {
		cmdQueue.offer(cmd);
	}

	public synchronized void sendStopSignal() {
		cmdQueue.offer(new ActivityExecCommand(-1, Kind.TERMINATE));
	}

}
