/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.provider;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageList;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.core.sashwindows.di.util.DiResourceFactoryImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class PapyrusActivityProvider implements IActivityProvider {

	private final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();

	private IFile iFile;
	private ResourceSet resourceSet;
	private Resource diResource;

	private IConversionResult conversionResult;

	protected PapyrusActivityProvider(IFile iFile) {
		this.iFile = iFile;
		initializeResourceSet();
		loadResource();
		convertResource();
	}

	private void initializeResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(PapyrusActivityProviderFactory.FILE_EXT,
						new DiResourceFactoryImpl());
	}

	private void loadResource() {
		diResource = loadResource(iFile);
	}

	private Resource loadResource(IFile file) {
		return resourceSet.getResource(createURI(file), true);
	}

	private URI createURI(IFile file) {
		return URI.createURI("platform:/resource/" //$NON-NLS-1$
				+ file.getProject().getName() + "/" //$NON-NLS-1$
				+ file.getProjectRelativePath());
	}

	private void convertResource() {
		NamedElement namedElement = obtainFirstNamedElement();
		IConverter converter = converterRegistry.getConverter(namedElement);
		conversionResult = converter.convert(namedElement);
	}

	private NamedElement obtainFirstNamedElement() {
		SashWindowsMngr sashWindowMngr = obtainSashWindowMngr();
		PageList pageList = sashWindowMngr.getPageList();
		return obtainFirstNamedElement(pageList);
	}

	private SashWindowsMngr obtainSashWindowMngr() {
		for (EObject object : diResource.getContents()) {
			if (object instanceof SashWindowsMngr)
				return (SashWindowsMngr) object;
		}
		return null;
	}

	private NamedElement obtainFirstNamedElement(PageList pageList) {
		for (PageRef pageRef : pageList.getAvailablePage()) {
			EObject identifier = pageRef.getEmfPageIdentifier();
			if (identifier instanceof Diagram) {
				Diagram diagram = (Diagram) identifier;
				if (diagram.getElement() instanceof NamedElement) {
					return (NamedElement) diagram.getElement();
				}
			}
		}
		return null;
	}

	@Override
	public IResource getResource() {
		return iFile;
	}

	@Override
	public Collection<Activity> getActivities() {
		return conversionResult.getActivities();
	}

	@Override
	public Activity getActivity(String name) {
		for (Activity activity : conversionResult.getAllActivities()) {
			if (equalsName(name, activity)) {
				return activity;
			}
		}
		return null;
	}

	private boolean equalsName(String name, Activity activity) {
		return name.equals(activity.name)
				|| name.equals(activity.qualifiedName);
	}

	@Override
	public String getSourceFileName(
			fUML.Syntax.Classes.Kernel.NamedElement namedElement) {
		return iFile.getName();
	}

	@Override
	public void unload() {
		diResource.unload();
	}

}
