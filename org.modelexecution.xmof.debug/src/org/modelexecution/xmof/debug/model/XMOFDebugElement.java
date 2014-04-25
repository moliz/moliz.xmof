/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.model;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.model.DebugElement;
import org.modelexecution.xmof.debug.XMOFDebugPlugin;
import org.modelexecution.xmof.debug.process.XMOFProcess;

public abstract class XMOFDebugElement extends DebugElement {

	public XMOFDebugElement(XMOFDebugTarget target) {
		super(target);
	}

	@Override
	public String getModelIdentifier() {
		return XMOFDebugPlugin.MODEL_TYPE_IDENTIFIER;
	}

	public XMOFDebugTarget getXMOFDebugTarget() {
		return getDebugTarget() != null ? (XMOFDebugTarget) getDebugTarget()
				: null;
	}

	protected XMOFProcess getXMOFProcess() {
		return getXMOFDebugTarget() != null ? getXMOFDebugTarget()
				.getXMOFProcess() : null;
	}

	protected void fireContentChangeEvent() {
		fireChangeEvent(DebugEvent.CONTENT);
	}
}