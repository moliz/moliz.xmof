/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.papyrus;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageList;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.core.sashwindows.di.util.DiResourceFactoryImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.papyrus.util.DiResourceUtil;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class PapyrusModelExecutor {

	private static final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();

	private ResourceSet resourceSet;
	private Resource diResource;

	private int executionID = -1;
	private Trace trace;
	private String modelPath;

	public PapyrusModelExecutor(String modelpath) {
		this.modelPath = modelpath;
		initializeResourceSet();
	}

	private void initializeResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("di", new DiResourceFactoryImpl()); //$NON-NLS-1$
	}

	public Trace executeActivity(String name) {
		loadModel(modelPath);
		IConversionResult conversionResult = convertDiResource();
		Activity activity = conversionResult.getActivity(name);
		executeActivity(activity);
		return getTrace();
	}

	private void loadModel(String path) {
		diResource = resourceSet.getResource(getFileURI(path), true);
	}

	private URI getFileURI(String path) {
		return URI.createFileURI(new File(path).getAbsolutePath());
	}

	private IConversionResult convertDiResource() {
		NamedElement namedElement = obtainFirstNamedElement();
		IConverter converter = getConverter(namedElement);
		return converter.convert(namedElement);
	}

	private NamedElement obtainFirstNamedElement() {
		SashWindowsMngr sashWindowMngr = DiResourceUtil
				.obtainSashWindowMngr(diResource);
		PageList pageList = sashWindowMngr.getPageList();
		return DiResourceUtil.obtainFirstNamedElement(pageList);
	}

	private IConverter getConverter(NamedElement namedElement) {
		return converterRegistry.getConverter(namedElement);
	}

	private void executeActivity(Activity activity) {
		addEventListener(
				new ExecutionEventListener() {
					@Override
					public void notify(Event event) {
						if (executionID == -1) {
							if (event instanceof ActivityEntryEvent) {
								ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
								executionID = activityEntryEvent
										.getActivityExecutionID();
							}
						}
					}
				});
		getExecutionContext().execute(activity, null, new ParameterValueList());
		trace = getExecutionContext().getTrace(executionID);
		executionID = -1;
	}

	public void addEventListener(ExecutionEventListener eventListener) {
		getExecutionContext().getExecutionEventProvider().addEventListener(eventListener);
	}
	
	public void removeEventListener(ExecutionEventListener eventListener) {
		getExecutionContext().getExecutionEventProvider().removeEventListener(eventListener);
	}

	public ExecutionContext getExecutionContext() {
		return ExecutionContext.getInstance();
	}

	public Trace getTrace() {
		return trace;
	}
	
	public Resource getModelResource() {
		return diResource;
	}

}
