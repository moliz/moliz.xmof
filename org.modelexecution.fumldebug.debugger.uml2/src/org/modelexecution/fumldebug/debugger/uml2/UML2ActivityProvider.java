/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.uml2;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.debugger.IActivityProvider;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class UML2ActivityProvider implements IActivityProvider {

	private ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();
	private ResourceSet resourceSet;

	public UML2ActivityProvider() {
		resourceSet = new ResourceSetImpl();
	}

	public UML2ActivityProvider(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
	public boolean canProvide(IResource resource) {
		Resource ecoreResource = loadResource(resource);
		if (ecoreResource != null) {
			boolean haveConverter = converterRegistry
					.haveConverter(ecoreResource);
			ecoreResource.unload();
			return haveConverter;
		} else {
			return false;
		}
	}

	private Resource loadResource(IResource resource) {
		try {
			if (resource instanceof IFile && resource.exists()) {
				IFile file = (IFile) resource;
				return loadResource(file);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	private Resource loadResource(IFile file) {
		return resourceSet.getResource(createURI(file), true);

	}

	private URI createURI(IFile file) {
		return URI.createURI("platform:/resource/"
				+ file.getProject().getName() + "/"
				+ file.getProjectRelativePath().toString());
	}

	@Override
	public Collection<Activity> getActivities(IResource resource) {
		Resource ecoreResource = loadResource(resource);
		if (ecoreResource != null) {
			IConverter converter = converterRegistry
					.getConverter(ecoreResource);
			if (converter != null) {
				IConversionResult conversionResult = converter
						.convert(ecoreResource);
				return conversionResult.getActivities();
			}
		}
		return Collections.emptyList();
	}

}
