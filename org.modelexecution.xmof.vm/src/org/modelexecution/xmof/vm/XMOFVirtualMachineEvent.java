/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

public class XMOFVirtualMachineEvent {

	public enum Type {
		START, STOP, ERROR, SUSPEND;
	}

	private Type type;
	private XMOFVirtualMachine vm;
	private Exception exception;

	protected XMOFVirtualMachineEvent(Type type,
			XMOFVirtualMachine virtualMachine) {
		super();
		this.type = type;
		this.vm = virtualMachine;
	}

	protected XMOFVirtualMachineEvent(XMOFVirtualMachine virtualMachine,
			Exception exception) {
		super();
		this.type = Type.ERROR;
		this.vm = virtualMachine;
		this.exception = exception;
	}

	public Type getType() {
		return type;
	}

	public XMOFVirtualMachine getVirtualMachine() {
		return vm;
	}

	public Exception getException() {
		return exception;
	}

}
