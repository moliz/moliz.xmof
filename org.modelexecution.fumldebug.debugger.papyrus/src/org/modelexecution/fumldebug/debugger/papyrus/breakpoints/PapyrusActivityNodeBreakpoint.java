/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.breakpoints;

import org.modelexecution.fumldebug.debugger.breakpoints.ActivityNodeBreakpoint;
import org.modelexecution.fumldebug.debugger.papyrus.provider.PapyrusActivityProvider;

/**
 * Papyrus-specific implementation of the {@link ActivityNodeBreakpoint}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusActivityNodeBreakpoint extends ActivityNodeBreakpoint {

	@Override
	public String getModelIdentifier() {
		return PapyrusActivityProvider.MODEL_TYPE_IDENTIFIER;
	}

}
