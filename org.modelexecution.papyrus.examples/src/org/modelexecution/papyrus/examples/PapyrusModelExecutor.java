/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.papyrus.examples;

import java.io.File;

import org.junit.Test;

/**
 * The purpose of this class is to demonstrate how to execute a Papyrus activity
 * diagram using the Moliz fUML engine.
 * 
 * See also org.modelexecution.fumldebug.papyrus.PapyrusModelExecutor
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusModelExecutor {

	@Test
	public void executeCallBehaviorActivity() {
		org.modelexecution.fumldebug.papyrus.PapyrusModelExecutor executor = new org.modelexecution.fumldebug.papyrus.PapyrusModelExecutor(
				new File("models/PersonCD.di").getAbsolutePath()); //$NON-NLS-1$
		executor.executeActivity("CallBehaviorAD"); //$NON-NLS-1$		
	}

}
