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
import java.util.ArrayList;
import java.util.List;

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

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class PapyrusModelExecutor {

	private static final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();
	private static final String PLATFORM_RESOURCE = "platform:/resource";

	private ResourceSet resourceSet;
	private Resource diResource;

	private int executionID = -1;
	private Trace trace;
	private String modelPath;
	private IConversionResult conversionResult;

	public PapyrusModelExecutor(String modelpath) {
		this.modelPath = modelpath;
		initializeResourceSet();
	}

	private void initializeResourceSet() {
		resourceSet = createResourceSet();
	}

	protected ResourceSet createResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("di", new DiResourceFactoryImpl()); //$NON-NLS-1$
		return resourceSet;
	}

	public Trace executeActivity(String name, Object_ context,
			ParameterValueList parameterValues) {
		loadModel(modelPath);
		conversionResult = convertDiResource();
		replaceOpaqueBehaviors();
		Activity activity = conversionResult.getActivity(name);
		executeActivity(activity, context, parameterValues);
		return getTrace();
	}
	
	private void replaceOpaqueBehaviors() {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		for (fUML.Syntax.Activities.IntermediateActivities.Activity activity : conversionResult
				.getAllActivities()) {
			nodesWithBehavior.addAll(getBehaviorNodes(activity.node));
		}

		for (ActivityNode node : nodesWithBehavior) {
			if (node instanceof CallBehaviorAction) {
				CallBehaviorAction callBehaviorAction = (CallBehaviorAction) node;
				Behavior behavior = callBehaviorAction.behavior;
				OpaqueBehavior behaviorReplacement = getExecutionContext()
						.getOpaqueBehavior(behavior.name);
				if (behaviorReplacement != null) {
					callBehaviorAction.behavior = behaviorReplacement;
				}
			} else if (node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode) node;
				Behavior behavior = decision.decisionInput;
				OpaqueBehavior behaviorReplacement = getExecutionContext()
						.getOpaqueBehavior(behavior.name);
				if (behaviorReplacement != null) {
					decision.decisionInput = behaviorReplacement;
				}
			}
		}
	}
	
	private List<ActivityNode> getBehaviorNodes(List<ActivityNode> nodes) {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		for (ActivityNode node : nodes) {
			if (node instanceof CallBehaviorAction) {
				CallBehaviorAction action = (CallBehaviorAction) node;
				nodesWithBehavior.add(action);
			} else if (node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode) node;
				if (decision.decisionInput != null) {
					nodesWithBehavior.add(decision);
				}
			}
			if (node instanceof StructuredActivityNode) {
				StructuredActivityNode structurednode = (StructuredActivityNode) node;
				nodesWithBehavior.addAll(getBehaviorNodes(structurednode.node));
			}
		}
		return nodesWithBehavior;
	}

	public Trace executeActivity(String name) {
		return executeActivity(name, null, new ParameterValueList());
	}

	private void loadModel(String path) {
		if(path.contains(PLATFORM_RESOURCE)) {
			diResource = resourceSet.getResource(getResourceURI(path), true);
		} else {
			diResource = resourceSet.getResource(getFileURI(path), true);
		}
		
	}

	private URI getResourceURI(String path) {
		return URI.createPlatformResourceURI(path.replace(PLATFORM_RESOURCE, ""), true);
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

	private void executeActivity(Activity activity, Object_ context,
			ParameterValueList parameterValues) {
		addEventListener(new ExecutionEventListener() {
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
		getExecutionContext().execute(activity, context, parameterValues);
		trace = getExecutionContext().getTrace(executionID);
		executionID = -1;
	}

	public void addEventListener(ExecutionEventListener eventListener) {
		getExecutionContext().addEventListener(eventListener);
	}

	public void removeEventListener(ExecutionEventListener eventListener) {
		getExecutionContext().addEventListener(eventListener);
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

	public IConversionResult getConversionResult() {
		return conversionResult;
	}

}
