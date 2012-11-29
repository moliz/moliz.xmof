/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.internal.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

public class InternalXMOFProcess extends Process {

	public enum Mode {
		DEBUG, RUN;
	}

	public static final int EXIT_VALUE = 0;

	private XMOFBasedModel model;
	private Mode mode;
	private XMOFVirtualMachine vm;

	public InternalXMOFProcess(XMOFBasedModel modelToBeExecuted, Mode mode) {
		this.model = modelToBeExecuted;
		this.mode = mode;
		initializeVM();
	}

	private void initializeVM() {
		vm = new XMOFVirtualMachine(model);
	}
	
	public XMOFVirtualMachine getVirtualMachine() {
		return vm;
	}

	public void run() {
		vm.run();
	}

	public boolean isInRunMode() {
		return Mode.RUN.equals(mode);
	}

	@Override
	public OutputStream getOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				// we don't need anything from outside using this stream
				// as communication is done directly using commands
			}
		};
	}

	@Override
	public InputStream getInputStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public InputStream getErrorStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public int waitFor() throws InterruptedException {
		return EXIT_VALUE;
	}

	@Override
	public int exitValue() {
		return EXIT_VALUE;
	}

	@Override
	public void destroy() {

	}

	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return vm.isRunning();
	}

}
