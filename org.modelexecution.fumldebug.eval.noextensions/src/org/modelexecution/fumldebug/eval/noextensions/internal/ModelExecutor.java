package org.modelexecution.fumldebug.eval.noextensions.internal;

import java.util.HashMap;
import java.util.Map;

import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fumldebug.core.InheritanceBasedDispatchStrategy;
import org.modelexecution.fumldebug.debugger.uml.UMLModelLoader;
import org.modelexecution.fumldebug.libraryregistry.OpaqueBehaviorCallReplacer;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.FirstChoiceStrategy;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

public class ModelExecutor {

	private Locus locus;
	private Map<String, OpaqueBehavior> opaqueBehaviors = new HashMap<String, OpaqueBehavior>();
	
	private IConversionResult conversionResult;
	private Activity activity;
	private ParameterValueList output;

	public ModelExecutor(String modelPath, String activityName) {
		setupExecutionEnvironment();
		conversionResult = loadAndConvertModel(modelPath);
		activity = getActivity(conversionResult, activityName);
	}

	private void setupExecutionEnvironment() {
		createLocus();
		registerOpaqueBehaviors();
	}

	private void createLocus() {
		locus = new Locus();
		locus.setFactory(new ExecutionFactoryL3());
		locus.setExecutor(new Executor());

		locus.factory.setStrategy(new InheritanceBasedDispatchStrategy());
		locus.factory.setStrategy(new FIFOGetNextEventStrategy());
		locus.factory.setStrategy(new FirstChoiceStrategy());
	}

	private void registerOpaqueBehaviors() {
		OpaqueBehaviorFactory factory = new OpaqueBehaviorFactory();
		factory.initialize();
		registerOpaqueBehavior(
				"UML::PrimitiveBehaviors::ListFunctions::ListSize",
				factory.getListsizeBehavior());
		registerOpaqueBehavior(
				"UML::PrimitiveBehaviors::ListFunctions::ListGet",
				factory.getListgetBehavior());
		registerOpaqueBehavior(
				"UML::PrimitiveBehaviors::IntegerFunctions::IntegerPlus",
				factory.getAddBehavior());
		registerOpaqueBehavior(
				"UML::PrimitiveBehaviors::IntegerFunctions::IntegerLess",
				factory.getLessBehavior());
	}

	private void registerOpaqueBehavior(String name,
			OpaqueBehaviorExecution opaqueBehaviorExecution) {
		locus.factory.primitiveBehaviorPrototypes.add(opaqueBehaviorExecution);
		opaqueBehaviors.put(name,
				(OpaqueBehavior) opaqueBehaviorExecution.types.get(0));
	}

	private IConversionResult loadAndConvertModel(String modelPath) {
		UMLModelLoader modelLoader = new UMLModelLoader().setModel(modelPath);
		IConversionResult conversionResult = modelLoader.getConversionResult();
		replaceOpaqueBehaviors(conversionResult);
		return conversionResult;
	}

	private void replaceOpaqueBehaviors(IConversionResult conversionResult) {
		OpaqueBehaviorCallReplacer.instance.replaceOpaqueBehaviorCalls(
				conversionResult.getActivities(), opaqueBehaviors);
	}

	private Activity getActivity(IConversionResult conversionResult, String activityName) {
		return conversionResult.getActivity(activityName);
	}

	public ParameterValueList execute() {
		output = locus.executor.execute(activity, null,
				new ParameterValueList());
		return output;
	}

	public Locus getLocus() {
		return locus;
	}
	
	public ParameterValueList getOutput() {
		return output;
	}
}
