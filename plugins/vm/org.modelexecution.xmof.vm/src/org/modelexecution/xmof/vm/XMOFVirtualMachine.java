/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.core.Breakpoint;
import org.modelexecution.fumldebug.core.BreakpointImpl;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.libraryregistry.LibraryRegistry;
import org.modelexecution.fumldebug.libraryregistry.OpaqueBehaviorCallReplacer;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent.Type;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * The virtual machine for executing {@link XMOFBasedModel xMOF-based models}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFVirtualMachine implements ExecutionEventListener {

	private final ExecutionContext executionContext = ExecutionContext.getInstance();

	private XMOFBasedModel model;
	private IConversionResult xMOFConversionResult;
	private XMOFInstanceMap instanceMap;

	private LinkedHashSet<IXMOFVirtualMachineListener> vmListener;
	private LinkedHashSet<ExecutionEventListener> rawListener;

	private boolean isRunning = false;
	private boolean isSuspended = false;

	private boolean suspendAfterStep = false;

	public int executionID = -1;

	private XMOFBasedModelSynchronizer modelSynchronizer;

	private Mode mode;
	private List<Integer> executingActivityIDs = new ArrayList<Integer>();

	private enum Mode {
		DEBUG, RUN;
	}

	public XMOFVirtualMachine(XMOFBasedModel modelToBeExecuted) {
		super();
		executionContext.reset();
		this.model = modelToBeExecuted;
		initialize();
	}

	private void initialize() {
		initializeListeners();
		convertMetamodel();
		initializeInstanceMap();
		registerOpaqueBehaviors();
	}

	private void initializeListeners() {
		vmListener = new LinkedHashSet<IXMOFVirtualMachineListener>();
		rawListener = new LinkedHashSet<ExecutionEventListener>();
	}

	protected void initializeInstanceMap() {
		this.instanceMap = new XMOFInstanceMap(xMOFConversionResult, model.getModelElements(),
				executionContext.getLocus());
	}

	private void convertMetamodel() {
		EPackage metamodelPackage = getMetamodelPackage();
		IConverter converter = ConverterRegistry.getInstance().getConverter(metamodelPackage);
		xMOFConversionResult = converter.convert(metamodelPackage);
	}

	private EPackage getMetamodelPackage() {
		return model.getMetamodelPackages().get(0);
	}

	private void registerOpaqueBehaviors() {
		LibraryRegistry libraryRegistry = new LibraryRegistry(getRawExecutionContext());
		Map<String, OpaqueBehavior> registeredOpaqueBehaviors = libraryRegistry.loadRegisteredLibraries();
		OpaqueBehaviorCallReplacer.instance.replaceOpaqueBehaviorCalls(xMOFConversionResult.getAllActivities(),
				registeredOpaqueBehaviors);
	}

	private void initializeModelSynchronizer() {
		modelSynchronizer = new XMOFBasedModelSynchronizer(instanceMap, model.getEditingDomain());
		modelSynchronizer.setModelResource(model.getModelResource());
	}

	public void setSynchronizeModel(boolean isSynchronizeModel) {
		if (isSynchronizeModel)
			installModelSynchronizer();
		else
			uninstallModelSynchronizer();
	}

	public void setSynchronizeModel(XMOFBasedModelSynchronizer modelSynchronizer) {
		this.modelSynchronizer = modelSynchronizer;
		setSynchronizeModel(true);
	}

	public void addVirtualMachineListener(IXMOFVirtualMachineListener listener) {
		vmListener.add(listener);
	}

	public void removeVirtualMachineListener(IXMOFVirtualMachineListener listener) {
		vmListener.remove(listener);
	}

	public void addRawExecutionEventListener(ExecutionEventListener listener) {
		rawListener.add(listener);
	}

	public void removeRawExecutionEventListener(ExecutionEventListener listener) {
		rawListener.remove(listener);
	}

	public XMOFBasedModel getModel() {
		return model;
	}

	public ExecutionContext getRawExecutionContext() {
		return executionContext;
	}

	public boolean mayRun() {
		return isXMOFConversionOK();
	}

	private boolean isXMOFConversionOK() {
		return xMOFConversionResult != null && !xMOFConversionResult.hasErrors();
	}

	private void startListeningToRawEvents() {
		executionContext.addEventListener(this);
	}

	private void stopListeningToRawEvents() {
		executionContext.removeEventListener(this);
	}

	public void run() {
		mode = Mode.RUN;
		execute();
	}

	public void debug() {
		mode = Mode.DEBUG;
		execute();
	}

	public void addBreakpoint(
			org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode activityNode) {
		Breakpoint breakpoint = createBreakpoint(activityNode);
		if (breakpoint != null)
			executionContext.addBreakpoint(breakpoint);
	}

	public void removeBreakpoint(
			org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode activityNode) {
		Breakpoint breakpoint = createBreakpoint(activityNode);
		if (breakpoint != null)
			executionContext.removeBreakpoint(breakpoint);
	}

	private Breakpoint createBreakpoint(
			org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode activityNode) {
		ActivityNode fUMLActivityNode = getFUMLActivityNode(activityNode);
		if (fUMLActivityNode != null) {
			Breakpoint breakpoint = new BreakpointImpl(fUMLActivityNode);
			return breakpoint;
		}
		return null;
	}

	private ActivityNode getFUMLActivityNode(
			org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode activityNode) {
		Element fumlElement = xMOFConversionResult.getFUMLElement(activityNode);
		if (fumlElement instanceof ActivityNode)
			return (ActivityNode) fumlElement;
		return null;
	}

	public void run(Activity activity, EObject contextObject,
			List<org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue> parameterValues) {
		mode = Mode.RUN;
		execute(activity, contextObject, parameterValues);
	}

	public void debug(Activity activity, EObject contextObject,
			List<org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue> parameterValues) {
		mode = Mode.DEBUG;
		execute(activity, contextObject, parameterValues);
	}

	public void execute() {
		prepareForExecution();
		executeAllMainObjects();
	}

	private void execute(Activity activity, EObject contextObject,
			List<org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue> parameterValues) {
		prepareForExecution();
		executeBehavior(activity, contextObject, parameterValues);
	}

	public void resume() {
		if (isRunning && isSuspended) {
			isSuspended = false;
			resumeExecution();
		}
	}

	protected void resumeExecution() {
		executionContext.resume(getActivityExecutionID());
	}

	private void prepareForExecution() {
		isRunning = true;
		notifyVirtualMachineListenerStart();
		startListeningToRawEvents();
	}

	private void cleanUpAfterExecution() {
		uninstallModelSynchronizer();
		stopListeningToRawEvents();
		notifyVirtualMachineListenerStop();
		isRunning = false;
	}

	private void executeBehavior(Activity activity, EObject contextObject,
			List<org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue> parameterValues) {

		executionContext.executeStepwise((Behavior) this.xMOFConversionResult.getFUMLElement(activity),
				instanceMap.getObject(contextObject), convertToParameterValueList(parameterValues));
		while (isRunning && !isSuspended) {
			try {
				resumeExecution();
			} catch (Exception e) {

				// Notify the listeners
				notifyVirtualMachineListenerError(e);

//				// Try to find the class where the exception occured
//				String className = e.getStackTrace()[0].getClassName();
//				Class<?> c = null;
//				try {
//					c = Class.forName(className);
//				} catch (ClassNotFoundException e1) {
//				}
//
//				// If it's an ExecutionEventListener implementation, then we
//				// just print the exception
//				if (c != null && Arrays.asList(c.getInterfaces()).contains(ExecutionEventListener.class))
//					e.printStackTrace();
//
//				// Else it's an error in the fuml interpreter itself, thus we
//				// re-throw to stop the execution
//				else
				e.printStackTrace();
					throw new RuntimeException(e);
			}
		}

	}

	private ParameterValueList convertToParameterValueList(
			Collection<org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue> parameterValues) {
		ParameterValueList list = new ParameterValueList();
		if (parameterValues == null)
			return list;
		for (org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue parameterValue : parameterValues) {
			list.add(createParameterValue(parameterValue));
		}
		return list;
	}

	private ParameterValue createParameterValue(
			org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue parameterValue) {
		ParameterValue fumlParameterValue = new ParameterValue();
		fumlParameterValue.parameter = (Parameter) xMOFConversionResult.getFUMLElement(parameterValue.getParameter());
		fumlParameterValue.values = createParameterValues(parameterValue.getValues());
		return fumlParameterValue;
	}

	private ValueList createParameterValues(EList<org.modelexecution.xmof.Semantics.Classes.Kernel.Value> values) {
		ValueList parameterValues = new ValueList();
		if (values != null) {
			for (org.modelexecution.xmof.Semantics.Classes.Kernel.Value value : values) {
				Value parameterValue = instanceMap.getValue(value);
				if (parameterValue instanceof Object_) {
					Reference reference = new Reference();
					reference.referent = (Object_) parameterValue;
					parameterValue = reference;
				}
				if (parameterValue != null) {
					parameterValues.add(parameterValue);
				}
			}
		}
		return parameterValues;
	}

	private void notifyVirtualMachineListenerStart() {
		XMOFVirtualMachineEvent event = new XMOFVirtualMachineEvent(Type.START, this);
		notifyVirtualMachineListener(event);
	}

	private void notifyVirtualMachineListenerStop() {
		XMOFVirtualMachineEvent event = new XMOFVirtualMachineEvent(Type.STOP, this);
		notifyVirtualMachineListener(event);
	}

	private void notifyVirtualMachineListenerError(Exception exception) {
		XMOFVirtualMachineEvent event = new XMOFVirtualMachineEvent(this, exception);
		notifyVirtualMachineListener(event);
	}

	private void installModelSynchronizer() {
		if (modelSynchronizer == null) {
			initializeModelSynchronizer();
		}
		addRawExecutionEventListener(modelSynchronizer);
	}

	private void uninstallModelSynchronizer() {
		removeRawExecutionEventListener(modelSynchronizer);
	}

	private void notifyVirtualMachineListener(XMOFVirtualMachineEvent event) {
		for (IXMOFVirtualMachineListener listener : new ArrayList<IXMOFVirtualMachineListener>(vmListener)) {
			try {
				listener.notify(event);
			} catch (Exception e) {
				// ignore exception thrown by listeners
			}
		}
	}

	private void executeAllMainObjects() {
		for (EObject mainClassObject : model.getMainEClassObjects()) {
			executeBehavior(getMainActivity(mainClassObject), mainClassObject, model.getParameterValues());
		}
	}

	private Activity getMainActivity(EObject mainClassObject) {
		EClass eClass = mainClassObject.eClass();
		BehavioredEOperation mainOperation = getMainOperation(eClass);
		Activity mainActivity = getMethod(eClass, mainOperation);
		return mainActivity;
	}

	private Activity getMethod(EClass eClass, BehavioredEOperation mainOperation) {
		if (!(eClass instanceof BehavioredEClass))
			return null;
		BehavioredEClass behavioredEClass = (BehavioredEClass) eClass;
		for (org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior behavior : behavioredEClass
				.getOwnedBehavior()) {
			if (mainOperation.getMethod().contains(behavior) && behavior instanceof Activity) {
				return (Activity) behavior;
			}
		}
		for (EClass eSuperClass : eClass.getESuperTypes()) {
			// TODO maybe another traversing algorithm should be used
			Activity method = getMethod(eSuperClass, mainOperation);
			if (method != null)
				return method;
		}
		return null;
	}

	private BehavioredEOperation getMainOperation(EClass eClass) {
		for (EOperation eOperation : eClass.getEAllOperations()) {
			if (eOperation instanceof BehavioredEOperation && eOperation.getName().equals(XMOFBasedModel.MAIN)) {
				return (BehavioredEOperation) eOperation;
			}
		}
		return null;
	}

	@Override
	public void notify(Event event) {
		if (isRunning() && concernsCurrentExecution(event)) {
			debugPrint(event);
			Collection<XMOFVirtualMachineEvent> vmEventsToDeliver = processRawEvent(event);
			notifyRawExecutionEventListeners(event);
			notifyXMOFVirtualMachineListener(vmEventsToDeliver);

			if (shouldTerminate()) {
				cleanUpAfterExecution();
			}
		}
	}

	protected Collection<XMOFVirtualMachineEvent> processRawEvent(Event event) {
		LinkedHashSet<XMOFVirtualMachineEvent> eventsToDeliver = new LinkedHashSet<XMOFVirtualMachineEvent>();
		if (event instanceof ActivityEntryEvent) {
			addExecutingActivity((ActivityEntryEvent) event);
		} else if (event instanceof ActivityExitEvent) {
			removeExecutingActivity((ActivityExitEvent) event);
		} else if (event instanceof SuspendEvent) {
			XMOFVirtualMachineEvent suspendEvent = suspend((SuspendEvent) event);
			if (suspendEvent != null) {
				eventsToDeliver.add(suspendEvent);
			}
		}
		return eventsToDeliver;
	}

	private XMOFVirtualMachineEvent suspend(SuspendEvent suspendEvent) {
		XMOFVirtualMachineEvent vmSuspendEvent = null;
		if (mode == Mode.DEBUG && (suspendAfterStep || suspendEvent instanceof BreakpointEvent)) {
			isSuspended = true;
			vmSuspendEvent = createSuspendEvent();
		}
		return vmSuspendEvent;
	}

	private XMOFVirtualMachineEvent createSuspendEvent() {
		XMOFVirtualMachineEvent event = new XMOFVirtualMachineEvent(Type.SUSPEND, this);
		return event;
	}

	private void removeExecutingActivity(ActivityExitEvent activityExitEvent) {
		executingActivityIDs.remove((Object) activityExitEvent.getActivityExecutionID());
	}

	private void addExecutingActivity(ActivityEntryEvent activityEntryEvent) {
		int activityExecutionID = activityEntryEvent.getActivityExecutionID();
		executingActivityIDs.add(activityExecutionID);
		if (getActivityExecutionID() == -1)
			setActivityExecutionID(activityExecutionID);
	}

	private boolean shouldTerminate() {
		return executingActivityIDs.size() == 0 && isRunning;
	}

	private boolean concernsCurrentExecution(Event event) {
		Element eventLocation = getEventLocation(event);
		return concernsCurrentExecution(eventLocation);
	}

	private Element getEventLocation(Event event) {
		if (event instanceof ActivityEvent)
			return ((ActivityEvent) event).getActivity();
		else if (event instanceof ActivityNodeEvent)
			return ((ActivityNodeEvent) event).getNode();
		else if (event instanceof SuspendEvent) {
			return ((SuspendEvent) event).getLocation();
		}
		return null;
	}

	private boolean concernsCurrentExecution(Element element) {
		if (element == null)
			return true;
		Object inputObject = xMOFConversionResult.getInputObject(element);
		return inputObject != null;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public XMOFInstanceMap getInstanceMap() {
		return instanceMap;
	}

	private void notifyRawExecutionEventListeners(Event event) {
		for (ExecutionEventListener listener : new ArrayList<ExecutionEventListener>(rawListener)) {
			listener.notify(event);
		}
	}

	private void notifyXMOFVirtualMachineListener(Collection<XMOFVirtualMachineEvent> events) {
		for (IXMOFVirtualMachineListener listener : new ArrayList<IXMOFVirtualMachineListener>(vmListener)) {
			for (XMOFVirtualMachineEvent event : events) {
				listener.notify(event);
			}
		}
	}

	private void debugPrint(Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntry = (ActivityEntryEvent) event;
			System.out.println("Activity Entry: " + activityEntry.getActivity().name);
		} else if (event instanceof ActivityExitEvent) {
			ActivityExitEvent activityExit = (ActivityExitEvent) event;
			System.out.println("Activity Exit: " + activityExit.getActivity().name);
		} else if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent nodeEntry = (ActivityNodeEntryEvent) event;
			System.out.println(
					"Node Entry: " + nodeEntry.getNode().name + " (" + nodeEntry.getNode().getClass().getName() + ")");
		} else if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent nodeExit = (ActivityNodeExitEvent) event;
			System.out.println(
					"Node Exit: " + nodeExit.getNode().name + " (" + nodeExit.getNode().getClass().getName() + ")");
		} else if (event instanceof SuspendEvent) {
			SuspendEvent suspendEvent = (SuspendEvent) event;
			if (suspendEvent.getLocation() instanceof fUML.Syntax.Activities.IntermediateActivities.Activity) {
				System.out.println("Suspend: "
						+ ((fUML.Syntax.Activities.IntermediateActivities.Activity) suspendEvent.getLocation()).name);
			} else if (suspendEvent.getLocation() instanceof ActivityNode) {
				System.out.println("Suspend: " + ((ActivityNode) suspendEvent.getLocation()).name + "("
						+ ((ActivityNode) suspendEvent.getLocation()).getClass().getName() + ")");
			} else {
				System.out.println("Suspend: " + suspendEvent.getLocation());
			}
		}
	}

	public Trace getExecutionTrace() {
		return executionContext.getTrace(getActivityExecutionID());
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public IConversionResult getxMOFConversionResult() {
		return xMOFConversionResult;
	}

	/**
	 * Defines whether the execution should suspend after each primitive
	 * execution step (equivalent to the step of the fUML virtual machine) or
	 * only when hitting a breakpoint
	 * 
	 * @param suspendAfterStep
	 *            true if execution should suspend after each primitive
	 *            execution step, false if it should be only suspended when
	 *            breakpoint is het
	 */
	public void shouldSuspendAfterStep(boolean suspendAfterStep) {
		this.suspendAfterStep = suspendAfterStep;
	}

	/**
	 * Performs a single step (equivalent to step of fUML virtual machine)
	 */
	public void step() {
		if (isRunning && isSuspended) {
			isSuspended = false;
			executionContext.nextStep(getActivityExecutionID());
		}
	}

	protected int getActivityExecutionID() {
		return this.executionID;
	}

	protected void setActivityExecutionID(int activityExecutionID) {
		this.executionID = activityExecutionID;
	}

}