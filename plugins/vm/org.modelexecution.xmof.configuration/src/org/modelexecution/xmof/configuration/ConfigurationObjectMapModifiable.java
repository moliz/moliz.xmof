/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class ConfigurationObjectMapModifiable extends ConfigurationObjectMap{

	public ConfigurationObjectMapModifiable() {
		super(new ArrayList<EObject>(), new ArrayList<EPackage>());
	}

	public void addToMap(EObject originalObject, EObject mappedObject) {
		super.addToMap(originalObject, mappedObject);
	}

}
