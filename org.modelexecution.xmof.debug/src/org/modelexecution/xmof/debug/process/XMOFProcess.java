/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.process;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modeldebuggerconfig.ActivityNodeStepDefinition;
import modeldebuggerconfig.DebuggerConfiguration;
import modeldebuggerconfig.ModeldebuggerconfigPackage;
import modeldebuggerconfig.StepDefinition;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.writer.EventWriter;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.configuration.profile.ProfileApplicationGenerator;
import org.modelexecution.xmof.debug.internal.launch.XMOFLaunchConfigurationUtil;
import org.modelexecution.xmof.debug.internal.process.InternalXMOFProcess;
import org.modelexecution.xmof.debug.internal.process.ProfileApplicationDisplayEditorRetriever;
import org.modelexecution.xmof.debug.internal.process.XMOFProfileAnnotationDisplayHandler;
import org.modelexecution.xmof.debug.logger.ConsoleLogger;
import org.modelexecution.xmof.vm.IXMOFVirtualMachineListener;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationManager;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;

public class XMOFProcess extends PlatformObject implements IProcess,
		IXMOFVirtualMachineListener {

	private ILaunch launch;
	private InternalXMOFProcess internalProcess;
	private String name;
	@SuppressWarnings("rawtypes")
	private Map attributes;
	private DebuggerConfiguration debugConfig;
	private ResourceSet resourceSet;

	private ConsoleLogger consoleLogger = new ConsoleLogger();
	private EventWriter eventWriter = new EventWriter();

	private boolean isStarted = false;

	private int lastLoggedEventIndex = -1;
	private ProfileApplicationGenerator generator;

	public XMOFProcess(ILaunch launch, Process process, String name,
			@SuppressWarnings("rawtypes") Map attributes) {
		setFields(launch, process, name, attributes);
		loadDebuggerConfiguration();
		internalProcess.getVirtualMachine().addVirtualMachineListener(this);
		if (isInDebugMode())
			setBreakpoints();
	}

	private void generateProfileApplication() {
		unloadProfileApplication();
		try {
			generator.generateProfileApplication();
		} catch (IOException e) {
		}
	}

	private void loadDebuggerConfiguration() {
		URI confPathURI = XMOFLaunchConfigurationUtil
				.getConfigurationMetamodelPathURI(launch
						.getLaunchConfiguration());
		URI debugConfPathUri = getModelDebuggerConfigurationURI(confPathURI);
		registerResourceFactory();

		ResourceSet resourceSet = this.internalProcess.getModel()
				.getMetamodelPackages().get(0).eResource().getResourceSet();
		try {
			Resource resource = EMFUtil.loadResource(resourceSet,
					debugConfPathUri);
			EObject eObject = resource.getContents().get(0);
			if (eObject instanceof DebuggerConfiguration)
				debugConfig = (DebuggerConfiguration) eObject;
		} catch (Exception e) {
		}
	}

	private void setBreakpoints() {
		for (StepDefinition stepDefinition : debugConfig.getStepDefinitions()) {
			if (stepDefinition instanceof ActivityNodeStepDefinition) {
				ActivityNodeStepDefinition activityNodeStepDefinition = (ActivityNodeStepDefinition) stepDefinition;
				setBreakpoint(activityNodeStepDefinition);
			}
		}
	}

	private void setBreakpoint(
			ActivityNodeStepDefinition activityNodeStepDefinition) {
		ActivityNode activityNode = activityNodeStepDefinition
				.getActivityNode();
		internalProcess.getVirtualMachine().addBreakpoint(activityNode);
	}

	private void registerResourceFactory() {
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = registry.getExtensionToFactoryMap();
		m.put(ModeldebuggerconfigPackage.MODEL_DEBUGGER_CONFIG_FILEEXTENSION,
				new XMIResourceFactoryImpl());
	}

	private URI getModelDebuggerConfigurationURI(URI confPathURI) {
		String confPath = confPathURI.toPlatformString(true);
		String confFilename = confPathURI.lastSegment();
		String debugConfPath = confPath.substring(0, confPath.length()
				- confFilename.length());
		debugConfPath = debugConfPath
				+ ModeldebuggerconfigPackage.MODEL_DEBUGGER_CONFIG_FILENAME;
		URI debugConfPathUri = EMFUtil.createPlatformResourceURI(debugConfPath);
		return debugConfPathUri;
	}

	private void openResult() {
		unloadProfileApplication();
		String modelPath = XMOFLaunchConfigurationUtil.getModelFilePath(launch
				.getLaunchConfiguration());
		String paPath = XMOFLaunchConfigurationUtil
				.getProfileApplicationFilePath(launch.getLaunchConfiguration());
		XMOFProfileAnnotationDisplayHandler handler = new XMOFProfileAnnotationDisplayHandler(
				modelPath, paPath);
		if (debugConfig != null)
			handler.setEditorID(debugConfig.getEditorID());
		PlatformUI.getWorkbench().getDisplay().asyncExec(handler);
	}

	private void logEvents() {
		List<Event> rawEvents = internalProcess.getRawEvents();
		if (rawEvents.size() > 0)
			for (int i = lastLoggedEventIndex + 1; i < rawEvents.size(); ++i) {
				Event event = rawEvents.get(i);
				logEvent(event);
			}
	}

	private void logEvent(Event event) {
		try {
			consoleLogger.write(eventWriter.write(event));
		} catch (IOException e) {
			// no output possible
		}
	}

	private void setFields(ILaunch launch, Process process, String name,
			@SuppressWarnings("rawtypes") Map attributes) {
		this.launch = launch;
		assertXMOFProcess(process);
		this.internalProcess = (InternalXMOFProcess) process;
		launch.addProcess(this);
		this.name = name;
		this.attributes = attributes;
	}

	private void assertXMOFProcess(Process process) {
		Assert.isTrue(process instanceof InternalXMOFProcess);
	}

	private boolean isInDebugMode() {
		return ILaunchManager.DEBUG_MODE.equals(launch.getLaunchMode());
	}

	public void runProcess() {
		isStarted = true;
		this.internalProcess.run();

		if (!isInDebugMode()) {
			showExecutionResult();
			fireTerminateEvent();
		}
	}

	@Override
	public boolean canTerminate() {
		return isSuspended();
	}

	@Override
	public boolean isTerminated() {
		return internalProcess.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		internalProcess.destroy();
	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		return consoleLogger;
	}

	@Override
	public int getExitValue() throws DebugException {
		return InternalXMOFProcess.EXIT_VALUE;
	}

	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAttribute(String key, String value) {
		if (attributes == null) {
			attributes = new HashMap(5);
		}
		Object origVal = attributes.get(key);
		if (origVal != null && origVal.equals(value)) {
			return; // nothing changed.
		}

		attributes.put(key, value);
		fireChangeEvent();
	}

	@Override
	public String getAttribute(String key) {
		if (attributes == null) {
			return null;
		}
		return (String) attributes.get(key);
	}

	protected void fireEvent(DebugEvent event) {
		DebugPlugin manager = DebugPlugin.getDefault();
		if (manager != null) {
			manager.fireDebugEventSet(new DebugEvent[] { event });
		}
	}

	protected void fireTerminateEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
	}

	protected void fireSuspendEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.SUSPEND));
	}

	protected void fireChangeEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.CHANGE));
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter.equals(IProcess.class)) {
			return this;
		}
		if (adapter.equals(IDebugTarget.class)) {
			ILaunch launch = getLaunch();
			IDebugTarget[] targets = launch.getDebugTargets();
			for (int i = 0; i < targets.length; i++) {
				if (this.equals(targets[i].getProcess())) {
					return targets[i];
				}
			}
			return null;
		}
		if (adapter.equals(ILaunch.class)) {
			return getLaunch();
		}
		// CONTEXTLAUNCHING
		if (adapter.equals(ILaunchConfiguration.class)) {
			return getLaunch().getLaunchConfiguration();
		}
		return super.getAdapter(adapter);
	}

	public void resume() {
		internalProcess.resume();
		fireChangeEvent();
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void stepInto() {
		resume();
	}

	public void stepOver() {
		resume();
	}

	public void stepReturn() {
		resume();
	}

	public Trace getExecutionTrace() {
		return internalProcess.getVirtualMachine().getExecutionTrace();
	}

	@Override
	public void notify(XMOFVirtualMachineEvent event) {
		switch (event.getType()) {
		case SUSPEND:
			fireSuspendEvent();
			break;
		case STOP:
			fireTerminateEvent();
			break;
		default:
			break;
		}
	}

	public void setProfileApplicationGenerator(
			ProfileApplicationGenerator generator) {
		this.generator = generator;
	}

	private void unloadProfileApplication() {
		URI paURI = XMOFLaunchConfigurationUtil.getProfileApplicationURI(launch
				.getLaunchConfiguration());
		unloadProfileApplicationFromProfileApplicationManager(paURI);
		unloadProfileApplicationFromEditor(paURI);
		unloadProfileApplicationFromResourceSet(paURI);
	}

	private void unloadProfileApplicationFromProfileApplicationManager(URI paURI) {
		ProfileApplicationManager manager = ProfileApplicationRegistry.INSTANCE
				.getProfileApplicationManager(resourceSet);
		manager.dispose();
	}

	private void unloadProfileApplicationFromEditor(URI paURI) {
		ProfileApplicationDisplayEditorRetriever editorRetriever = new ProfileApplicationDisplayEditorRetriever();
		editorRetriever.setEditorID(debugConfig.getEditorID());
		PlatformUI.getWorkbench().getDisplay().syncExec(editorRetriever);
		IEditorPart editor = editorRetriever.getEditor();
		ResourceSet editorResourceSet = getResourceSet(editor);
		unloadResource(editorResourceSet, paURI);
	}

	private void unloadProfileApplicationFromResourceSet(URI paURI) {
		unloadResource(resourceSet, paURI);
	}

	private void unloadResource(ResourceSet resourceSet, URI resourceURI) {
		if (hasResourceLoaded(resourceSet, resourceURI)) {
			Resource paResource = resourceSet.getResource(resourceURI, true);
			paResource.unload();
			resourceSet.getResources().remove(paResource);
		}
	}

	private boolean hasResourceLoaded(ResourceSet resourceSet, URI resourceURI) {
		if (resourceSet == null)
			return false;
		for (Resource resource : resourceSet.getResources()) {
			if (resource.getURI().equals(resourceURI)) {
				return true;
			}
		}
		return false;
	}

	private ResourceSet getResourceSet(IEditorPart editorPart) {
		if (editorPart == null)
			return null;
		Object adapter = editorPart.getAdapter(IEditingDomainProvider.class);
		if (adapter != null && adapter instanceof IEditingDomainProvider) {
			IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) adapter;
			if (editingDomainProvider.getEditingDomain() != null)
				return editingDomainProvider.getEditingDomain()
						.getResourceSet();
		}
		return null;
	}

	public void showExecutionResult() {
		logEvents();
		generateProfileApplication();
		openResult();
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public boolean isSuspended() {
		return internalProcess.isSuspended();
	}
}
