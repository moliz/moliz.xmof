/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.launch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class InternalActivityProcess extends Process implements IJobChangeListener {

	private static final int EXIT_CODE = 0;
	
	private Activity activity;
	private boolean isRunning = false;
	private ExecutionContext executionContext = ExecutionContext.getInstance();

	private ActivityJob activityJob;

	private InputStream inputStream;
	private InputStream errorInputStream;
	private OutputStream outputStream = null;

	public InternalActivityProcess(Activity activity) {
		this.activity = activity;
		initializeJob();
	}

	public void run() {
		activityJob.schedule();
	}

	public int getRootExecutionID() {
		return activityJob.getExecutionID();
	}

	private void initializeJob() {
		activityJob = new ActivityJob(activity, executionContext);
		activityJob.addJobChangeListener(this);
	}

	private boolean isRunning() {
		return isRunning; // Job.RUNNING == activityJob.getState();
	}

	public void addExecutionEventListener(ExecutionEventListener listener) {
		executionContext.getExecutionEventProvider().addEventListener(listener);
	}

	public void removeExecutionEventListener(ExecutionEventListener listener) {
		executionContext.getExecutionEventProvider().removeEventListener(
				listener);
	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = createInputStream();
		}
		return inputStream;
	}

	private InputStream createInputStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				return 0;
			}
		};
	}

	@Override
	public InputStream getErrorStream() {
		if (errorInputStream == null) {
			errorInputStream = createInputStream();
		}
		return errorInputStream;
	}

	@Override
	public int waitFor() throws InterruptedException {
		if (isRunning()) {
			wait();
		}
		return EXIT_CODE;
	}

	@Override
	public int exitValue() {
		if (isRunning()) {
			throw new IllegalThreadStateException("Process hasn't exited");
		}
		return EXIT_CODE;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
		this.isRunning = true;
	}

	@Override
	public void awake(IJobChangeEvent event) {
		this.isRunning = true;
	}

	@Override
	public void done(IJobChangeEvent event) {
		this.isRunning = false;
	}

	@Override
	public void running(IJobChangeEvent event) {
		this.isRunning = true;
	}

	@Override
	public void scheduled(IJobChangeEvent event) {
		this.isRunning = true;
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
		this.isRunning = true;
	}
}
