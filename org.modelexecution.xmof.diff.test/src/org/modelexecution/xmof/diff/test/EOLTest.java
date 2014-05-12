package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.types.EolOrderedSet;
import org.eclipse.epsilon.eol.types.EolSequence;
import org.eclipse.epsilon.eol.types.EolSet;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.states.states.Event;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesFactory;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.states.states.Transition;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.util.EMFUtil;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class EOLTest {

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;

	private static final TracemodelFactory TRACE_FACTORY = TracemodelFactory.eINSTANCE;
	private static final TracemodelPackage TRACE_PACKAGE = TracemodelPackage.eINSTANCE;

	private static final StatesFactory STATES_FACTORY = StatesFactory.eINSTANCE;
	private static final StatesPackage STATES_PACKAGE = StatesPackage.eINSTANCE;
	
	@Before
	public void init() {
		resourceSet = EMFUtil.createResourceSet();
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}
	
	@Test
	public void test1_FindActivityExecutionByActivityQualifiedName() throws Exception {
		Activity activity = new Activity();
		activity.qualifiedName = "lala";
		activity.name = "lala";
		
		ActivityExecution activityExecution = createActivityExecution(activity);
		Trace trace = createTrace(activityExecution);	
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test1.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());

		Object result = module.execute();
		assertTrue(result instanceof EolSet);
		EolSet<?> foundActivityExecutions = (EolSet<?>)result;
		assertEquals(1, foundActivityExecutions.size());
		assertEquals(activityExecution, foundActivityExecutions.iterator().next());
	}

	@Test
	public void test2_ActivityExecutionsChronologicalSuccessorCalculation() throws Exception {
		Activity activity = new Activity();
		ActivityNode node = new CreateObjectAction();
		
		ActivityNodeExecution nodeExecution1 = createActionExecution(node);
		ActivityNodeExecution nodeExecution2 = createActionExecution(node);
		ActivityNodeExecution nodeExecution3 = createActionExecution(node);
		setChronologicalSuccessor(nodeExecution1, nodeExecution2);
		setChronologicalSuccessor(nodeExecution2, nodeExecution3);
		
		ActivityExecution activityExecution1 = createActivityExecution(activity, nodeExecution1, nodeExecution2);
		ActivityExecution activityExecution2 = createActivityExecution(activity, nodeExecution3);
		
		Trace trace = createTrace(activityExecution1, activityExecution2);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test2.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertTrue(result instanceof Boolean);
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test3_OrderedSetInsertAt() throws Exception {
		
		ActivityExecution activityExecution1 = createActivityExecution();
		ActivityExecution activityExecution2 = createActivityExecution();
		
		Trace trace = createTrace(activityExecution1, activityExecution2);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test3.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertTrue(result instanceof EolOrderedSet);
		EolOrderedSet<?> sortedActivityExecutions = (EolOrderedSet<?>)result;
		assertEquals(2, sortedActivityExecutions.size());
		Iterator<?> iterator = sortedActivityExecutions.iterator();
		assertEquals(activityExecution2, iterator.next());
		assertEquals(activityExecution1, iterator.next());
	}
	
	@Test
	public void test4_SortActivityExecutionsChronologically() throws Exception {
		Activity activity = new Activity();
		ActivityNode node = new CreateObjectAction();
		
		ActivityNodeExecution nodeExecution1 = createActionExecution(node);
		ActivityNodeExecution nodeExecution2 = createActionExecution(node);
		ActivityNodeExecution nodeExecution3 = createActionExecution(node);
		ActivityNodeExecution nodeExecution4 = createActionExecution(node);
		setChronologicalSuccessor(nodeExecution1, nodeExecution2);
		setChronologicalSuccessor(nodeExecution2, nodeExecution3);
		setChronologicalSuccessor(nodeExecution3, nodeExecution4);
		
		ActivityExecution activityExecution1 = createActivityExecution(activity, nodeExecution1, nodeExecution2);
		ActivityExecution activityExecution2 = createActivityExecution(activity, nodeExecution3);
		ActivityExecution activityExecution3 = createActivityExecution(activity, nodeExecution4);
		
		Trace trace = createTrace(activityExecution3, activityExecution2, activityExecution1);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test4.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertTrue(result instanceof EolOrderedSet);
		EolOrderedSet<?> sortedActivityExecutions = (EolOrderedSet<?>)result;
		assertEquals(3, sortedActivityExecutions.size());
		Iterator<?> iterator = sortedActivityExecutions.iterator();
		assertEquals(activityExecution1, iterator.next());
		assertEquals(activityExecution2, iterator.next());
		assertEquals(activityExecution3, iterator.next());
	}
	
	@Test
	public void test5_GetActivityContext_CallOperationAction() throws Exception {
		Activity activity = new Activity();
		CallOperationAction callOperationAction = new CallOperationAction();
		InputPin targetInputPin = new InputPin();
		callOperationAction.setTarget(targetInputPin);
		
		CallActionExecution callActionExecution = createCallActionExecution(callOperationAction);

		ActivityExecution activityExecution1 = createActivityExecution(activity, callActionExecution);
		ActivityExecution activityExecution2 = createActivityExecution(activity);
		setCaller(activityExecution2, callActionExecution);
		
		Object_ value = new Object_();
		ValueSnapshot valueSnapshot = createValueSnapshot(value);
		activityExecution2.setContextValueSnapshot(valueSnapshot);
		
		Object_ runtimeValue = new Object_();
		ValueInstance valueInstance = createValueInstance(runtimeValue, valueSnapshot);
		
		Trace trace = createTrace(activityExecution1, activityExecution2);
		setValueInstances(trace, valueInstance);
		
		InputValue inputValue = createInputValue(valueSnapshot);
		Input input = createInput(targetInputPin, inputValue);
		
		setInputs(callActionExecution, input);		
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test5.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		XMOFInstanceMap instanceMap = new XMOFInstanceMap(new ConversionResultImpl(), new ArrayList<EObject>(), null);
		EObject configurationObject = TRACE_FACTORY.createTrace(); // represents xmof configuration object
		instanceMap.addMapping(runtimeValue, configurationObject);
		EpsilonUtil.setVariableToMdule(module, "instanceMap", instanceMap);
		
		Object result = module.execute();
		assertTrue(result instanceof EObject);
		assertTrue(result == configurationObject);
	}
	
	@Test
	public void test6_GetActivityContext_CallBehaviorAction() throws Exception {
		Activity activity = new Activity();
		CallOperationAction callOperationAction = new CallOperationAction();
		InputPin targetInputPin = new InputPin();
		callOperationAction.setTarget(targetInputPin);
		CallBehaviorAction callBehaviorAction = new CallBehaviorAction();
		
		CallActionExecution callOperationActionExecution = createCallActionExecution(callOperationAction);
		CallActionExecution callBehaviorActionExecution = createCallActionExecution(callBehaviorAction);

		ActivityExecution activityExecution1 = createActivityExecution(activity, callOperationActionExecution);
		ActivityExecution activityExecution2 = createActivityExecution(activity, callBehaviorActionExecution);
		ActivityExecution activityExecution3 = createActivityExecution(activity);
		setCaller(activityExecution2, callOperationActionExecution);
		setCaller(activityExecution3, callBehaviorActionExecution);
		
		Object_ value = new Object_();
		ValueSnapshot valueSnapshot = createValueSnapshot(value);
		activityExecution2.setContextValueSnapshot(valueSnapshot);
		activityExecution3.setContextValueSnapshot(valueSnapshot);
		
		Object_ runtimeValue = new Object_();
		ValueInstance valueInstance = createValueInstance(runtimeValue, valueSnapshot);
		
		Trace trace = createTrace(activityExecution1, activityExecution2, activityExecution3);
		setValueInstances(trace, valueInstance);
		
		InputValue inputValue = createInputValue(valueSnapshot);
		Input input = createInput(targetInputPin, inputValue);
		
		setInputs(callOperationActionExecution, input);		
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		
		InMemoryEmfModel model = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		File eolFile = EMFUtil.createFile("ecl/test6.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, model);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		XMOFInstanceMap instanceMap = new XMOFInstanceMap(new ConversionResultImpl(), new ArrayList<EObject>(), null);
		EObject configurationObject = TRACE_FACTORY.createTrace(); // represents xmof configuration object
		instanceMap.addMapping(runtimeValue, configurationObject);
		EpsilonUtil.setVariableToMdule(module, "instanceMap", instanceMap);
		
		Object result = module.execute();
		assertTrue(result instanceof EolSequence<?>);
		EolSequence<?> contextValues = (EolSequence<?>)result;
		assertEquals(3, contextValues.size());
		Iterator<?> contextValuesIterator = contextValues.iterator();
		assertTrue(contextValuesIterator.next() == null);
		assertTrue(contextValuesIterator.next() == configurationObject);
		assertTrue(contextValuesIterator.next() == configurationObject);
	}
	
	@Test
	public void test7_GetStateAfterActivityExecution_LastExecutedNode() throws EolRuntimeException {
		Activity activity = new Activity();
		ActivityNode node = new CreateObjectAction();
		
		ActionExecution actionExecution = createActionExecution(node);
		ActivityExecution activityExecution = createActivityExecution(activity, actionExecution);
		Trace trace = createTrace(activityExecution);
		
		StateSystem stateSystem = createStateSystem(trace);
		State state1 = createState();
		State state2 = createState();
		Transition transition = createTransition(state1, state2, actionExecution);
		setStates(stateSystem, state1, state2);
		setTransitions(stateSystem, transition);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		InMemoryEmfModel traceModel = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		
		Resource statesModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("statesmodel.xmi"), stateSystem);
		InMemoryEmfModel statesModel = EpsilonUtil.createInMemoryEmfModel("statesmodel", statesModelResource, STATES_PACKAGE);
		
		File eolFile = EMFUtil.createFile("ecl/test7_9.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, traceModel, statesModel);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertNotNull(result);
		assertTrue(result instanceof EObject);
		assertEquals(state2, result);
	}
	
	@Test
	public void test8_GetStateAfterActivityExecution_ChronologicalSuccessorOfLastExecutedNode() throws EolRuntimeException {
		Activity activity = new Activity();
		ActivityNode node = new CreateObjectAction();
		
		ActionExecution actionExecution1 = createActionExecution(node);
		ActivityExecution activityExecution1 = createActivityExecution(activity, actionExecution1);
		
		ActionExecution actionExecution2 = createActionExecution(node);
		setChronologicalSuccessor(actionExecution1, actionExecution2);
		ActivityExecution activityExecution2 = createActivityExecution(activity, actionExecution2);
		
		Trace trace = createTrace(activityExecution1, activityExecution2);
		
		StateSystem stateSystem = createStateSystem(trace);
		State state1 = createState();
		State state2 = createState();
		Transition transition = createTransition(state1, state2, actionExecution2);
		setStates(stateSystem, state1, state2);
		setTransitions(stateSystem, transition);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		InMemoryEmfModel traceModel = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		
		Resource statesModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("statesmodel.xmi"), stateSystem);
		InMemoryEmfModel statesModel = EpsilonUtil.createInMemoryEmfModel("statesmodel", statesModelResource, STATES_PACKAGE);
		
		File eolFile = EMFUtil.createFile("ecl/test7_9.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, traceModel, statesModel);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertNotNull(result);
		assertTrue(result instanceof EObject);
		assertEquals(state1, result);
	}
	
	@Test
	public void test9_GetStateAfterActivityExecution_LastState() throws EolRuntimeException {
		Activity activity = new Activity();
		ActivityNode node = new CreateObjectAction();
		
		ActionExecution actionExecution1 = createActionExecution(node);
		ActivityExecution activityExecution1 = createActivityExecution(activity, actionExecution1);
			
		Trace trace = createTrace(activityExecution1);
		
		StateSystem stateSystem = createStateSystem(trace);
		State state1 = createState();
		setStates(stateSystem, state1);
		
		Resource traceModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("tracemodel.xmi"), trace);
		InMemoryEmfModel traceModel = EpsilonUtil.createInMemoryEmfModel("tracemodel", traceModelResource, TRACE_PACKAGE);
		
		Resource statesModelResource = EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI("statesmodel.xmi"), stateSystem);
		InMemoryEmfModel statesModel = EpsilonUtil.createInMemoryEmfModel("statesmodel", statesModelResource, STATES_PACKAGE);
		
		File eolFile = EMFUtil.createFile("ecl/test7_9.eol");
		EolModule module = EpsilonUtil.createEolModule(eolFile, traceModel, statesModel);
		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass().getClassLoader());
		
		Object result = module.execute();
		assertNotNull(result);
		assertTrue(result instanceof EObject);
		assertEquals(state1, result);
	}
	
	private ActivityExecution createActivityExecution(Activity activity, ActivityNodeExecution... nodeExecutions) {
		ActivityExecution activityExecution = createActivityExecution();
		activityExecution.setActivity(activity);
		if(nodeExecutions.length > 0) {
			List<ActivityNodeExecution> nodeExecutionsAsList = Arrays.asList(nodeExecutions);
			activityExecution.getNodeExecutions().addAll(nodeExecutionsAsList);
		}
		return activityExecution;
	}
	
	private ActivityExecution createActivityExecution() {
		return TRACE_FACTORY.createActivityExecution();
	}
	
	private Trace createTrace(ActivityExecution... activityExecutions) {
		Trace trace = TRACE_FACTORY.createTrace();
		List<ActivityExecution> activityExecutionsAsList = Arrays.asList(activityExecutions);
		trace.getActivityExecutions().addAll(activityExecutionsAsList);
		return trace;
	}
	
	private ActionExecution createActionExecution(ActivityNode node) {
		ActionExecution nodeExecution = TRACE_FACTORY.createActionExecution();
		nodeExecution.setNode(node);
		nodeExecution.setExecuted(true);
		return nodeExecution;
	}
	
	private CallActionExecution createCallActionExecution(CallAction action) {
		CallActionExecution nodeExecution = TRACE_FACTORY.createCallActionExecution();
		nodeExecution.setNode(action);
		return nodeExecution;
	}
	
	private void setChronologicalSuccessor(ActivityNodeExecution predecessorActivityNodeExecution, ActivityNodeExecution successorActivityNodeExecution) {
		predecessorActivityNodeExecution.setChronologicalSuccessor(successorActivityNodeExecution);
	}
	
	private void setCaller(ActivityExecution activityExcecution, CallActionExecution callActionExecution) {
		activityExcecution.setCaller(callActionExecution);
	}
	
	private ValueInstance createValueInstance(Value runtimeValue, ValueSnapshot... valueSnapshots) {
		ValueInstance valueInstance = TRACE_FACTORY.createValueInstance();
		valueInstance.setRuntimeValue(runtimeValue);
		if(valueSnapshots.length > 0) {
			List<ValueSnapshot> valueSnapshotsAsList = Arrays.asList(valueSnapshots);
			valueInstance.getSnapshots().addAll(valueSnapshotsAsList);
		}
		return valueInstance;
	}
	
	private ValueSnapshot createValueSnapshot(Value value) {
		ValueSnapshot valueSnapshot = TRACE_FACTORY.createValueSnapshot();
		valueSnapshot.setValue(value);
		return valueSnapshot;
	}
	
	private void setValueInstances(Trace trace, ValueInstance... valueInstances) {
		List<ValueInstance> valueInstancesAsList = Arrays.asList(valueInstances);
		trace.getValueInstances().addAll(valueInstancesAsList);
	}
	
	private InputValue createInputValue(ValueSnapshot inputValueSnapshot) {
		InputValue inputValue = TRACE_FACTORY.createInputValue();
		inputValue.setValueSnapshot(inputValueSnapshot);
		return inputValue;
	}
	
	private Input createInput(InputPin inputPin, InputValue... inputValues) {
		Input input = TRACE_FACTORY.createInput();
		input.setInputPin(inputPin);
		if(inputValues.length > 0) {
			List<InputValue> inputValuesAsList = Arrays.asList(inputValues);
			input.getInputValues().addAll(inputValuesAsList);
		}
		return input;
	}
	
	private void setInputs(ActionExecution actionExecution, Input... inputs) {
		List<Input> inputsAsList = Arrays.asList(inputs);
		actionExecution.getInputs().addAll(inputsAsList);
	}
	
	private StateSystem createStateSystem(Trace trace) {
		StateSystem stateSystem = STATES_FACTORY.createStateSystem();
		stateSystem.setTrace(trace);
		return stateSystem;
	}
	
	private void setStates(StateSystem stateSystem, State... states) {
		List<State> statesAsList = Arrays.asList(states);
		stateSystem.getStates().addAll(statesAsList);
	}
	
	private void setTransitions(StateSystem stateSystem, Transition... transitions) {
		List<Transition> transitionsAsList = Arrays.asList(transitions);
		stateSystem.getTransitions().addAll(transitionsAsList);
	}
	
	private State createState() {
		return STATES_FACTORY.createState();
	}
	
	private Transition createTransition(State source, State target, ActionExecution actionExecution) {
		Transition transition = STATES_FACTORY.createTransition();
		transition.setSource(source);
		transition.setTarget(target);
		Event event = STATES_FACTORY.createEvent();
		event.setActionExecution(actionExecution);
		transition.setEvent(event);
		return transition;
	}

}
