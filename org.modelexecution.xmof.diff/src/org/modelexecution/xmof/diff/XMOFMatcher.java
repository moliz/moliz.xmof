package org.modelexecution.xmof.diff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.configuration.ConfigurationObjectMapModifiable;
import org.modelexecution.xmof.diff.util.EMFUtil;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.diff.util.XMOFUtil;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

public class XMOFMatcher {

	private static final String LEFT_MODEL_NAME = "Left";
	private static final String RIGHT_MODEL_NAME = "Right";

	private XMOFMatcherContext context;

	private ConfigurationObjectMap configurationObjectMapLeft;
	private ConfigurationObjectMap configurationObjectMapRight;

	private StateSystem stateSystemLeft;
	private StateSystem stateSystemRight;

	private Resource stateSystemResourceLeft;
	private Resource stateSystemResourceRight;

	private XMOFInstanceMap instanceMapLeft;
	private XMOFInstanceMap instanceMapRight;

	private MatchTrace matchTraceSyntax;
	private MatchTrace matchTraceSemantics;

	public void setXMOFMatcherContext(XMOFMatcherContext context) {
		this.context = context;
	}

	public boolean canMatch() {
		if (context != null) {
			return context.isComplete();
		}
		return false;
	}

	public boolean match() {
		if (!canMatch()) {
			return false;
		}

		matchSyntactically();
		executeModels();
		matchSemantically();
		return obtainMatchResult();
	}

	private void matchSyntactically() {
		EclModule moduleSyntax = createEclModuleForSyntacticMatching();
		matchTraceSyntax = EpsilonUtil.executeModule(moduleSyntax);
	}

	private EclModule createEclModuleForSyntacticMatching() {
		EPackage rootMetamodelEPackage = EMFUtil.getRootEPackage(context
				.getMetamodelResource());
		EclModule moduleSyntax = EpsilonUtil.createEclModule(
				context.getEclFileSyntax(), context.getModelResourceLeft(),
				LEFT_MODEL_NAME, context.getModelResourceRight(),
				RIGHT_MODEL_NAME, rootMetamodelEPackage);
		return moduleSyntax;
	}

	private boolean obtainMatchResult() {
		if (matchTraceSemantics != null) {
			Match match = matchTraceSemantics.getMatch(stateSystemLeft,
					stateSystemRight);
			if (match != null) {
				return match.isMatching();
			}
		}
		return false;
	}

	private void executeModels() {
		configurationObjectMapLeft = createConfigurationObjectMap(
				context.getModelResourceLeft(),
				context.getConfigurationMetamodelResource());
		Resource configurationModelResourceLeft = createConfigurationModelResource(configurationObjectMapLeft);
		StatesBuilder statesBuilderLeft = execute(configurationModelResourceLeft);
		stateSystemLeft = statesBuilderLeft.getStateSystem();
		stateSystemResourceLeft = EMFUtil.createResource(
				context.getResourceSet(), context.getEditingDomain(),
				EMFUtil.createFileURI("stateSystemLeft.xmi"), stateSystemLeft);
		instanceMapLeft = statesBuilderLeft.getVM().getInstanceMap();

		configurationObjectMapRight = createConfigurationObjectMap(
				context.getModelResourceRight(),
				context.getConfigurationMetamodelResource());
		Resource configurationModelResourceRight = createConfigurationModelResource(configurationObjectMapRight);
		StatesBuilder statesBuilderRight = execute(configurationModelResourceRight);
		stateSystemRight = statesBuilderRight.getStateSystem();
		stateSystemResourceRight = EMFUtil.createResource(
				context.getResourceSet(), context.getEditingDomain(),
				EMFUtil.createFileURI("stateSystemLeft.xmi"), stateSystemRight);
		instanceMapRight = statesBuilderRight.getVM().getInstanceMap();
	}

	private ConfigurationObjectMap createConfigurationObjectMap(
			Resource modelResource, Resource configurationMetamodelResource) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationMetamodelResource,
						modelResource);
		return configurationObjectMap;
	}

	private Resource createConfigurationModelResource(
			ConfigurationObjectMap configurationObjectMap) {
		Resource configurationModelResource = EMFUtil.createResource(
				context.getResourceSet(), context.getEditingDomain(),
				EMFUtil.createFileURI("configurationmodel.xmi"),
				configurationObjectMap.getConfigurationObjects());
		return configurationModelResource;
	}

	private StatesBuilder execute(Resource configurationModelResource) {
		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(
				context.getResourceSet(), context.getEditingDomain(),
				configurationModelResource);

		StatesBuilder statesBuilder = XMOFUtil.createStatesBuilder(vm,
				configurationModelResource);
		vm.run();

		return statesBuilder;
	}

	private void matchSemantically() {
		EclModule moduleSemantics = createEclModuleForSemanticMatching();
		matchTraceSemantics = EpsilonUtil.executeModule(moduleSemantics);
	}

	private EclModule createEclModuleForSemanticMatching() {
		ConfigurationObjectMap configurationObjectMap = joinConfiugrationObjectMaps(
				configurationObjectMapLeft, configurationObjectMapRight);
		XMOFInstanceMap instanceMap = joinInstanceMaps(instanceMapLeft,
				instanceMapRight);

		EPackage traceEPackage = TracemodelPackage.eINSTANCE;
		EPackage statesEPackage = StatesPackage.eINSTANCE;

		Collection<EPackage> ePackages = new HashSet<EPackage>();
		ePackages.add(EMFUtil.getRootEPackage(context.getMetamodelResource()));
		ePackages.add(traceEPackage);
		ePackages.add(statesEPackage);
		ePackages.addAll(configurationObjectMap.getConfigurationPackages());

		EclModule moduleSemantics = EpsilonUtil.createEclModule(
				context.getEclFileSemantics(), stateSystemResourceLeft,
				LEFT_MODEL_NAME, stateSystemResourceRight, RIGHT_MODEL_NAME,
				ePackages);

		EpsilonUtil.setNativeTypeDelegateToModule(
				moduleSemantics,
				context.getNativeTypeDelegate() != null ? context
						.getNativeTypeDelegate() : this.getClass()
						.getClassLoader());

		EpsilonUtil.setVariableToMdule(moduleSemantics, "instanceMap",
				instanceMap);

		EpsilonUtil.setVariableToMdule(moduleSemantics,
				"configurationObjectMap", configurationObjectMap);

		EpsilonUtil.setMatchTraceToModule(moduleSemantics, matchTraceSyntax);

		return moduleSemantics;
	}

	private ConfigurationObjectMap joinConfiugrationObjectMaps(
			ConfigurationObjectMap... maps) {
		ConfigurationObjectMapModifiable joinedMap = new ConfigurationObjectMapModifiable();
		for (ConfigurationObjectMap map : maps) {
			addMappings(joinedMap, map);
			addConfigurationPackages(joinedMap, map);
		}
		return joinedMap;
	}

	private void addMappings(ConfigurationObjectMapModifiable joinedMap,
			ConfigurationObjectMap map) {
		for (EObject configurationObject : map.getConfigurationObjects()) {
			joinedMap.addToMap(map.getOriginalObject(configurationObject),
					configurationObject);
		}
	}

	private void addConfigurationPackages(
			ConfigurationObjectMapModifiable joinedMap,
			ConfigurationObjectMap map) {
		for (EPackage ePackage : map.getConfigurationPackages()) {
			if (!joinedMap.getConfigurationPackages().contains(ePackage))
				joinedMap.getConfigurationPackages().add(ePackage);
		}
	}

	private XMOFInstanceMap joinInstanceMaps(XMOFInstanceMap... maps) {
		XMOFInstanceMap joinedMap = new XMOFInstanceMap(
				new ConversionResultImpl(), new ArrayList<EObject>(), null);
		for (XMOFInstanceMap map : maps)
			addMappings(joinedMap, map);
		return joinedMap;
	}

	private void addMappings(XMOFInstanceMap targetMap,
			XMOFInstanceMap sourceMap) {
		for (EObject eObject : sourceMap.getAllEObjects()) {
			targetMap.addMapping(sourceMap.getObject(eObject), eObject);
		}
	}

	public MatchTrace getMatchTraceSyntax() {
		return matchTraceSyntax;
	}

	public MatchTrace getMatchTraceSemantics() {
		return matchTraceSemantics;
	}
}
