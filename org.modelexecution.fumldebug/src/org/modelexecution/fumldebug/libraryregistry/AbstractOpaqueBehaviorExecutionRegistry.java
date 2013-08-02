/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.libraryregistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.modelexecution.fumldebug.core.ExecutionContext;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public abstract class AbstractOpaqueBehaviorExecutionRegistry implements
		IOpaqueBehaviorExecutionRegistry {

	private static String LIBRARY_EXTENSION_ID = "org.modelexecution.fuml.library";
	private static String LIBRARY_EXTENSION_ELEMENT_NAME = "library";
	private static String LIBRARY_EXTENSION_ATTRIBUTE_LIBRARY_NAME = "name";
	private static String LIBRARY_EXTENSION_ATTRIBUTE_LIBRARY_PATH = "path";

	private Map<String, OpaqueBehavior> opaqueBehaviors = new HashMap<String, OpaqueBehavior>();
	
	protected OpaqueBehavior getOpaqueBehavior(String qualifiedName) {
		return opaqueBehaviors.get(qualifiedName);
	}
	
	protected void createOpaqueBehaviorsMap(Collection<OpaqueBehavior> fUMLOpaqueBehaviors) {
		for(OpaqueBehavior fUMLOpaqueBehavior : fUMLOpaqueBehaviors) {
			opaqueBehaviors.put(fUMLOpaqueBehavior.qualifiedName, fUMLOpaqueBehavior);
		}
	}
	
	protected String getLibraryPath(String libraryname) {
		String libraryPath = null;
		IConfigurationElement libraryConfigElement = getLibraryConfigurationElement(libraryname);
		if (libraryConfigElement != null) {
			libraryPath = libraryConfigElement
					.getAttribute(LIBRARY_EXTENSION_ATTRIBUTE_LIBRARY_PATH);
		}
		return libraryPath;
	}

	private IConfigurationElement getLibraryConfigurationElement(
			String libraryname) {
		IConfigurationElement[] configElements = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						LIBRARY_EXTENSION_ID);
		for (int i = 0; i < configElements.length; ++i) {
			IConfigurationElement configElement = configElements[i];
			if (LIBRARY_EXTENSION_ELEMENT_NAME.equals(configElement.getName())) {
				String registeredlibraryname = configElement
						.getAttribute(LIBRARY_EXTENSION_ATTRIBUTE_LIBRARY_NAME);
				if (libraryname.equals(registeredlibraryname)) {
					return configElement;
				}
			}
		}
		return null;
	}

	protected void registerOpaqueBehaviorExecution(
			OpaqueBehaviorExecution opaqueBehaviorExecution, String qualifiedName,
			ExecutionContext executionContext) {
		opaqueBehaviorExecution.types.add(opaqueBehaviors.get(qualifiedName));
		executionContext.addOpaqueBehavior(opaqueBehaviorExecution);
	}
	
}
