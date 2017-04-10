/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior;

public abstract class VMTestFactory {

	private Map<String, FunctionBehavior> primitiveBehaviors = new HashMap<String, FunctionBehavior>();
	
	protected void loadPrimitiveBehaviors(ResourceSet resourceSet) {
		Resource libraryResource = resourceSet.getResource(URI.createURI("pathmap://XMOF_LIBRARY/xmof_library.xmof", true), true);
		for(TreeIterator<EObject> iterator = libraryResource.getAllContents();iterator.hasNext();) {
			EObject eObject = iterator.next();
			if(eObject instanceof FunctionBehavior) {
				FunctionBehavior functionBehavior = (FunctionBehavior) eObject;
				primitiveBehaviors.put(functionBehavior.getName(), functionBehavior);
			}
		}
	}
	
	protected FunctionBehavior getPrimitiveBehavior(String name) {
		return primitiveBehaviors.get(name);
	}
}
