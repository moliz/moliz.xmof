/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.model;

import java.util.Collections;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ActivityNodeStackFrame extends ActivityDebugElement implements
		IStackFrame {

	private ActivityNodeThread thread;

	public ActivityNodeStackFrame(ActivityNodeThread thread) {
		super(thread.getActivityDebugTarget());
		this.thread = thread;
	}

	public ActivityNode getActivityNode() {
		return thread.getActivityNode();
	}

	@Override
	public boolean canStepInto() {
		return thread.canStepInto();
	}

	@Override
	public boolean canStepOver() {
		return thread.canStepOver();
	}

	@Override
	public boolean canStepReturn() {
		return thread.canStepReturn();
	}

	@Override
	public boolean isStepping() {
		return thread.isStepping();
	}

	@Override
	public void stepInto() throws DebugException {
		thread.stepInto();
	}

	@Override
	public void stepOver() throws DebugException {
		thread.stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {
		thread.stepReturn();
	}

	@Override
	public boolean canResume() {
		return thread.canResume();
	}

	@Override
	public boolean canSuspend() {
		return thread.canSuspend();
	}

	@Override
	public boolean isSuspended() {
		return thread.isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		thread.resume();
	}

	@Override
	public void suspend() throws DebugException {
		thread.suspend();
	}

	@Override
	public boolean canTerminate() {
		return thread.canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return thread.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		thread.terminate();
	}

	@Override
	public void notify(Event event) {
		// noop so far
	}

	@Override
	public IThread getThread() {
		return thread;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		return Collections.emptySet().toArray(new IVariable[] {});
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return false;
	}

	@Override
	public String getName() throws DebugException {
		return thread.getActivityNode().name;
	}

	@Override
	public int getLineNumber() throws DebugException {
		return 0;
	}

	@Override
	public int getCharStart() throws DebugException {
		return 0;
	}

	@Override
	public int getCharEnd() throws DebugException {
		return 0;
	}

	@Override
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return Collections.emptySet().toArray(new IRegisterGroup[] {});
	}

	@Override
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

}
