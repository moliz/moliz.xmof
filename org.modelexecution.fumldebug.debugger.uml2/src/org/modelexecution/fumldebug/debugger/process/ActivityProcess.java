/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.process;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.modelexecution.fumldebug.debugger.process.internal.InternalActivityProcess;

public class ActivityProcess extends RuntimeProcess implements IProcess {

	private InternalActivityProcess activityProcess;

	public ActivityProcess(ILaunch launch, Process process, String name,
			@SuppressWarnings("rawtypes") Map attributes) {
		super(launch, process, name, attributes);
		Assert.isTrue(process instanceof InternalActivityProcess,
				"Process must be of type InternalActivityProcess.");
		this.activityProcess = (InternalActivityProcess) process;
		this.activityProcess.setListener(this);
		launch.addProcess(this);
		this.activityProcess.run();
	}

	public String getName() {
		return activityProcess.getActivityName() + " (" //$NON-NLS-1$
				+ activityProcess.getRootExecutionID() + ")"; //$NON-NLS-1$
	}

	public int getRootExecutionId() {
		return activityProcess.getRootExecutionID();
	}

	@Override
	public synchronized boolean canTerminate() {
		return activityProcess != null && activityProcess.canTerminate();
	}

	@Override
	public synchronized boolean isTerminated() {
		return activityProcess == null || activityProcess.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		activityProcess.destroy();
		super.terminate();
		fireTerminateEvent();
	}

	public boolean isSuspended() {
		return activityProcess.isSuspended();
	}

	public void resume() {
		activityProcess.resume();
		fireChangeEvent();
	}

	public void suspend() {
		activityProcess.suspend();
		fireChangeEvent();
	}

	public void notifyTerminated() {
		fireTerminateEvent();
	}

	public void notifySuspended() {
		fireEvent(new DebugEvent(this, DebugEvent.SUSPEND));
	}
	
	@Override
	public IStreamsProxy getStreamsProxy() {
		return new IStreamsProxy() {
			
			@Override
			public void write(String input) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("here");
			}
			
			@Override
			public IStreamMonitor getOutputStreamMonitor() {
				return new IStreamMonitor() {
					
					@Override
					public void removeListener(IStreamListener listener) {
						// TODO Auto-generated method stub
						System.out.println("remove");
					}
					
					@Override
					public String getContents() {
						return "Hallo";
					}
					
					@Override
					public void addListener(IStreamListener listener) {
						// TODO Auto-generated method stub
						System.out.println("add listener");
					}
				};
			}
			
			@Override
			public IStreamMonitor getErrorStreamMonitor() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
