package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.builder.util.StatesBuilderUtil;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.CreateLinkAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;

public class StatesBuilderAndSerializerTestViaXMOFExample {

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;

	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	@Test
	public void testADExample() throws IOException {
		EMFUtil.loadMetamodel(resourceSet,
				EMFUtil.createFileURI("model/ad/activitydiagram.ecore"));
		Resource modelResource = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI("model/ad/activity1_exe.xmi"));
		Resource configurationMetamodelResource = EMFUtil.loadResource(
				resourceSet,
				EMFUtil.createFileURI("model/ad/activitydiagram.xmof"));
		Resource configurationModelResource = XMOFUtil.createConfigurationModelResource(resourceSet, editingDomain, configurationMetamodelResource, modelResource);
		
		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet, editingDomain, configurationModelResource);
		StatesBuilder statesBuilder = StatesBuilderUtil.createStatesBuilder(vm, configurationModelResource);
		
		vm.run();

		StateSystem stateSystem = statesBuilder.getStateSystem();
		assertNotNull(stateSystem);

		EMFUtil.persist(resourceSet, editingDomain,
				EMFUtil.createFileURI("states/activity1_states.xmi"),
				stateSystem);

		Trace trace = stateSystem.getTrace();
		assertNotNull(trace);

		List<ActionExecution> actionExecutionsWithSideEffects = getActionExecutionsWithSideEffects(trace);
		assertEquals(actionExecutionsWithSideEffects.size() + 1, stateSystem
				.getStates().size());
	}

	private static final List<Class<? extends Action>> actionTypesWithSideEffects = Arrays
			.asList(CreateObjectAction.class, DestroyObjectAction.class,
					AddStructuralFeatureValueAction.class,
					ClearAssociationAction.class, CreateLinkAction.class,
					DestroyLinkAction.class, ReclassifyObjectAction.class,
					ClearStructuralFeatureAction.class,
					RemoveStructuralFeatureValueAction.class);

	private List<ActionExecution> getActionExecutionsWithSideEffects(Trace trace) {
		List<ActionExecution> actionExecutionsWithSideEffects = new ArrayList<ActionExecution>();
		for (ActivityExecution activityExecution : trace
				.getActivityExecutions()) {
			actionExecutionsWithSideEffects
					.addAll(getActionExecutionsWithSideEffects(activityExecution));
		}
		return actionExecutionsWithSideEffects;
	}

	private List<ActionExecution> getActionExecutionsWithSideEffects(
			ActivityExecution activityExecution) {
		List<ActionExecution> actionExecutionsWithSideEffects = new ArrayList<ActionExecution>();
		for (ActivityNodeExecution activityNodeExecution : activityExecution
				.getNodeExecutions()) {
			if (isActionExecutionWithSideEffects(activityNodeExecution)) {
				actionExecutionsWithSideEffects
						.add((ActionExecution) activityNodeExecution);
			}
		}
		return actionExecutionsWithSideEffects;
	}

	private boolean isActionExecutionWithSideEffects(
			ActivityNodeExecution activityNodeExecution) {
		return activityNodeExecution instanceof ActionExecution
				&& actionTypesWithSideEffects.contains(activityNodeExecution
						.getNode().getClass());
	}

}
