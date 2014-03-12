package org.modelexecution.xmof.diff;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.configuration.ConfigurationObjectMapModifiable;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.builder.util.StatesBuilderUtil;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

public class XMOFMatcher {

	private static final String LEFT_MODEL_NAME = "Left";
	private static final String RIGHT_MODEL_NAME = "Right";

	private XMOFMatcherContext context;

	private MatchTrace matchTraceSyntax;
	
	private List<XMOFSemanticMatchResult> semanticMatchResults;

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
		matchSemantically();
		return obtainMatchResult();
	}

	private void matchSemantically() {
		semanticMatchResults = new ArrayList<XMOFSemanticMatchResult>();
		
		if(context.getParameterResourcesLeft().size() == 0) {
			XMOFSemanticMatchResult semanticMatchResult = matchSemantically(null, null);
			semanticMatchResults.add(semanticMatchResult);
		} else {
			for(int i=0;i<context.getParameterResourcesLeft().size();++i) {
				Resource parameterResourceLeft = context.getParameterResourcesLeft().get(i);
				Resource parameterResourceRight = context.getParameterResourcesRight().get(i);
				XMOFSemanticMatchResult semanticMatchResult = matchSemantically(parameterResourceLeft, parameterResourceRight);				
				semanticMatchResults.add(semanticMatchResult);
			}
		}
		
	}
	
	private XMOFSemanticMatchResult matchSemantically(Resource parameterResourceLeft, Resource parameterResourceRight) {
		XMOFSemanticMatchResult semanticMatchResult = new XMOFSemanticMatchResult();
		
		semanticMatchResult.setParameterResourceLeft(parameterResourceLeft);
		semanticMatchResult.setParameterResourceRight(parameterResourceRight);
		
		executeModels(semanticMatchResult);
		matchSemantically(semanticMatchResult);
		return semanticMatchResult;
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
		if(semanticMatchResults == null)
			return false;
		if(semanticMatchResults.size() == 0)
			return false;
		
		for(XMOFSemanticMatchResult semanticMatchResult : semanticMatchResults) {
			StateSystem stateSystemLeft = semanticMatchResult.getStateSystemLeft();
			StateSystem stateSystemRight = semanticMatchResult.getStateSystemRight();
			MatchTrace matchTraceSemantics = semanticMatchResult.getMatchTraceSemantics();
			if(matchTraceSemantics == null) {
				return false;
			}
			Match match = matchTraceSemantics.getMatch(stateSystemLeft, stateSystemRight);
			if (match == null)
				return false;
			if (!match.isMatching())
				return false;
		}		
		return true;
	}

	private void executeModels(XMOFSemanticMatchResult matchResult) {
		Resource parameterResourceLeft = matchResult.getParameterResourceLeft();
		ConfigurationObjectMap configurationObjectMapLeft = createConfigurationObjectMap(
				context.getModelResourceLeft(),
				context.getConfigurationMetamodelResource(),
				parameterResourceLeft);
		matchResult.setConfigurationObjectMapLeft(configurationObjectMapLeft);
		Resource configurationModelResourceLeft = createConfigurationModelResource(configurationObjectMapLeft);
		matchResult.setConfigurationModelResourceLeft(configurationModelResourceLeft);
		StatesBuilder statesBuilderLeft = execute(configurationModelResourceLeft, parameterResourceLeft, configurationObjectMapLeft);
		StateSystem stateSystemLeft = statesBuilderLeft.getStateSystem();
		matchResult.setStateSystemLeft(stateSystemLeft);
		Resource stateSystemResourceLeft = EMFUtil.createResource(
				context.getResourceSet(), context.getEditingDomain(),
				EMFUtil.createFileURI("stateSystemLeft.xmi"), stateSystemLeft);
		matchResult.setStateSystemResourceLeft(stateSystemResourceLeft);
		XMOFInstanceMap instanceMapLeft = statesBuilderLeft.getVM().getInstanceMap();
		matchResult.setInstanceMapLeft(instanceMapLeft);

		Resource parameterResourceRight = matchResult.getParameterResourceRight();
		ConfigurationObjectMap configurationObjectMapRight = createConfigurationObjectMap(
				context.getModelResourceRight(),
				context.getConfigurationMetamodelResource(),
				parameterResourceRight);
		matchResult.setConfigurationObjectMapRight(configurationObjectMapRight);
		Resource configurationModelResourceRight = createConfigurationModelResource(configurationObjectMapRight);
		matchResult.setConfigurationModelResourceRight(configurationModelResourceRight);
		StatesBuilder statesBuilderRight = execute(configurationModelResourceRight, parameterResourceRight, configurationObjectMapRight);
		StateSystem stateSystemRight = statesBuilderRight.getStateSystem();
		matchResult.setStateSystemRight(stateSystemRight);
		Resource stateSystemResourceRight = EMFUtil.createResource(
				context.getResourceSet(), context.getEditingDomain(),
				EMFUtil.createFileURI("stateSystemRight.xmi"), stateSystemRight);
		matchResult.setStateSystemResourceRight(stateSystemResourceRight);
		XMOFInstanceMap instanceMapRight = statesBuilderRight.getVM().getInstanceMap();
		matchResult.setInstanceMapRight(instanceMapRight);
	}
	
	private ConfigurationObjectMap createConfigurationObjectMap(
			Resource modelResource, Resource configurationMetamodelResource, Resource parameterResource) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationMetamodelResource,
						modelResource, parameterResource);
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

	private StatesBuilder execute(Resource configurationModelResource, Resource parameterResource, ConfigurationObjectMap configurationObjectMap) {
		List<ParameterValue> parameterValueConfiguration = XMOFUtil.getParameterValueConfiguration(
				parameterResource, configurationObjectMap);
		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(
				context.getResourceSet(), context.getEditingDomain(),
				configurationModelResource, parameterValueConfiguration);
		return execute(vm, configurationModelResource);
	}
	
	private StatesBuilder execute(XMOFVirtualMachine vm, Resource configurationModelResource) {
		StatesBuilder statesBuilder = StatesBuilderUtil.createStatesBuilder(vm,
				configurationModelResource);
		vm.run();
		vm.getRawExecutionContext().reset();
		return statesBuilder;
	}

	private void matchSemantically(XMOFSemanticMatchResult matchResult) {
		EclModule moduleSemantics = createEclModuleForSemanticMatching(matchResult);
		MatchTrace matchTraceSemantics = EpsilonUtil.executeModule(moduleSemantics);
		matchResult.setMatchTraceSemantics(matchTraceSemantics);
	}

	private EclModule createEclModuleForSemanticMatching(XMOFSemanticMatchResult matchResult) {
		ConfigurationObjectMap configurationObjectMap = joinConfiugrationObjectMaps(
				matchResult.getConfigurationObjectMapLeft(), matchResult.getConfigurationObjectMapRight());
		XMOFInstanceMap instanceMap = joinInstanceMaps(matchResult.getInstanceMapLeft(),
				matchResult.getInstanceMapRight());

		EPackage traceEPackage = TracemodelPackage.eINSTANCE;
		EPackage statesEPackage = StatesPackage.eINSTANCE;

		Collection<EPackage> ePackages = new HashSet<EPackage>();
		ePackages.add(EMFUtil.getRootEPackage(context.getMetamodelResource()));
		ePackages.add(traceEPackage);
		ePackages.add(statesEPackage);
		ePackages.addAll(configurationObjectMap.getConfigurationPackages());

		EclModule moduleSemantics = EpsilonUtil.createEclModule(
				context.getEclFileSemantics(), matchResult.getStateSystemResourceLeft(),
				LEFT_MODEL_NAME, matchResult.getStateSystemResourceRight(), RIGHT_MODEL_NAME,
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

		moduleSemantics.getContext().setWarningStream(new PrintStream(new OutputStreamNoOutput()));

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

	public List<MatchTrace> getMatchTracesSemantics() {
		List<MatchTrace> matchTracesSemantics = new ArrayList<MatchTrace>();
		for (XMOFSemanticMatchResult semanticMatchResult : semanticMatchResults) {
			MatchTrace matchTraceSemantics = semanticMatchResult.getMatchTraceSemantics();
			if(matchTraceSemantics != null)
				matchTracesSemantics.add(matchTraceSemantics);
		}
		return matchTracesSemantics;
	}
	
	public XMOFMatcherContext getXMOFMatcherContext() {
		return context;
	}
	
	public List<XMOFSemanticMatchResult> getSemanticMatchResults() {
		return semanticMatchResults;
	}
	
	private class OutputStreamNoOutput extends OutputStream {

		@Override
		public void write(int b) throws IOException {
		}

	}
	
}