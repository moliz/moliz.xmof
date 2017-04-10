/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.diff.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.configuration.ConfigurationObjectMapModifiable;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFMatcherContext;
import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.builder.util.StatesBuilderUtil;
import org.modelexecution.xmof.states.states.State;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

/**
 * @author Tanja
 * 
 */
public class ECLMatchTest {

	private static final String PETRINET1_ECL_SEMANTICS_FINAL_MARKING_PATH = "ecl/petrinet1/semantics2.ecl";
	private static final String PETRINET1_ECL_SEMANTICS_PATH = "ecl/petrinet1/semantics.ecl";
	private static final String PETRINET1_ECL_SYNTAX_PATH = "ecl/petrinet1/syntax.ecl";
	private static final String PETRINET1_XMOF_PATH = "model/petrinet1/petrinet.xmof";
	private static final String PETRINET1_METAMODEL_PATH = "model/petrinet1/petrinet.ecore";	
	private static final String PLACE = "Place";
	private static final String PLACE_NAME = "name";
	private static final String PLACECONFIGURATION = "PlaceConfiguration";
	private static final String PLACECONFIGURATION_TOKENS = "tokens";
	
	private static final String CLASSDIAGRAM_ECL_SEMANTICS_PATH = "ecl/cd/semantics.ecl";
	private static final String CLASSDIAGRAM_ECL_SYNTAX_PATH = "ecl/cd/syntax.ecl";
	private static final String CLASSDIAGRAM_XMOF_PATH = "model/cd/classes.xmof";
	private static final String CLASSDIAGRAM_METAMODEL_PATH = "model/cd/classes.ecore";
	
	private static final String ACTIVITYDIAGRAM_ECL_SEMANTICS_PATH = "ecl/ad/semantics.ecl";
	private static final String ACTIVITYDIAGRAM_ECL_SYNTAX_PATH = "ecl/ad/syntax.ecl";
	private static final String ACTIVITYDIAGRAM_XMOF_PATH = "model/ad/activitydiagram.xmof";
	private static final String ACTIVITYDIAGRAM_METAMODEL_PATH = "model/ad/activitydiagram.ecore";
	private static final String ASSIGNEE = "assignee";
	private static final String BOOLEAN_UNARY_EXPRESSION = "BooleanUnaryExpression";
	private static final String BOOLEAN_VARIABLE = "BooleanVariable";
	private static final String VALUE = "value";
	private static final String INTEGER_VALUE = "IntegerValue";
	private static final String STRING_VARIABLE = "StringVariable";
	private static final String INTEGER_VARIABLE = "IntegerVariable";
	private static final String ACTIVITY = "Activity";
	private static final String OPAQUE_ACTION = "OpaqueAction";
	private static final String CONTROL_FLOW = "ControlFlow";

	private static final String NAME = "name";

	private static final String LEFT_MODEL_NAME = "Left";
	private static final String RIGHT_MODEL_NAME = "Right";

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
	}
	
	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	@Test
	public void testADSyntacticMatch() {
		Resource metamodelResource = EMFUtil.loadMetamodel(resourceSet,
				EMFUtil.createFileURI(ACTIVITYDIAGRAM_METAMODEL_PATH));
		EPackage metamodelPackage = EMFUtil.getRootEPackage(metamodelResource);
		Resource leftModelResource = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI("model/ad/activity1.xmi"));
		Resource rightModelResource = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI("model/ad/activity2.xmi"));

		File eclFile = EMFUtil.createFile(ACTIVITYDIAGRAM_ECL_SYNTAX_PATH);
		EclModule module = EpsilonUtil.createEclModule(eclFile,
				leftModelResource, LEFT_MODEL_NAME, rightModelResource,
				RIGHT_MODEL_NAME, metamodelPackage);

		MatchTrace matchTrace = EpsilonUtil.executeModule(module);
		assertNotNull(matchTrace);

		List<Match> matches = getMatchesFromModule(module);
		assertTrue(matches.size() > 0);

		List<Match> uniqueMatchingMatchesFromModule = getUniqueMatchingMatchesFromModule(module);
		assertEquals(9, uniqueMatchingMatchesFromModule.size());

		EClass activityEClass = getEClass(metamodelPackage, ACTIVITY);
		EObject activityLeft = getEObjectsOfType(leftModelResource,
				activityEClass).iterator().next();
		EObject activityRight = getEObjectsOfType(rightModelResource,
				activityEClass).iterator().next();
		assertTrue(matches(module, activityLeft, activityRight));

		EClass opaqueActionClass = getEClass(metamodelPackage, OPAQUE_ACTION);
		EObject action1Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action1");
		EObject action1Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action1");
		assertTrue(matches(module, action1Left, action1Right));

		EObject action2Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action2_left");
		EObject action2Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action2_right");
		assertFalse(matches(module, action2Left, action2Right));

		EObject action3Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action3");
		EObject action3Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, opaqueActionClass,
				opaqueActionClass.getEStructuralFeature(NAME), "action3");
		assertTrue(matches(module, action3Left, action3Right));

		EClass controlFlowClass = getEClass(metamodelPackage, CONTROL_FLOW);
		EObject edgeLeft = getEObjectOfTypeWithFeatureValue(leftModelResource,
				controlFlowClass, controlFlowClass.getEStructuralFeature(NAME),
				"e1");
		EObject edgeRight = getEObjectOfTypeWithFeatureValue(
				rightModelResource, controlFlowClass,
				controlFlowClass.getEStructuralFeature(NAME), "c1");
		assertTrue(matches(module, edgeLeft, edgeRight));

		EClass integerVariableEClass = getEClass(metamodelPackage,
				INTEGER_VARIABLE);
		EObject variable1Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, integerVariableEClass,
				integerVariableEClass.getEStructuralFeature(NAME), "var1");
		EObject variable1Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, integerVariableEClass,
				integerVariableEClass.getEStructuralFeature(NAME), "var1");
		assertTrue(matches(module, variable1Left, variable1Right));

		EClass booleanVariableEClass = getEClass(metamodelPackage,
				BOOLEAN_VARIABLE);
		EObject variable3Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var3");
		EObject variable3Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var3");
		assertTrue(matches(module, variable3Left, variable3Right));

		EObject variable4Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var4");
		EObject variable4Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var4");
		assertTrue(matches(module, variable4Left, variable4Right));

		EObject variable5Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var5_left");
		EObject variable5Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, booleanVariableEClass,
				booleanVariableEClass.getEStructuralFeature(NAME), "var5_right");
		assertFalse(matches(module, variable5Left, variable5Right));

		EClass stringVariableEClass = getEClass(metamodelPackage,
				STRING_VARIABLE);
		EObject variable2Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, integerVariableEClass,
				integerVariableEClass.getEStructuralFeature(NAME), "var2");
		EObject variable2Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, stringVariableEClass,
				stringVariableEClass.getEStructuralFeature(NAME), "var2");
		assertFalse(matches(module, variable2Left, variable2Right));

		EClass integerValueEClass = getEClass(metamodelPackage, INTEGER_VALUE);
		EObject value0Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, integerValueEClass,
				integerValueEClass.getEStructuralFeature(VALUE), 0);
		EObject value5Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, integerValueEClass,
				integerValueEClass.getEStructuralFeature(VALUE), 5);
		EObject value0Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, integerValueEClass,
				integerValueEClass.getEStructuralFeature(VALUE), 0);
		assertTrue(matches(module, value0Left, value0Right));
		assertFalse(hasMatchingMatches(module, value5Left));

		EClass booleanUnaryExpressionClass = getEClass(metamodelPackage,
				BOOLEAN_UNARY_EXPRESSION);
		EObject booleanUnaryExpression1Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, booleanUnaryExpressionClass,
				booleanUnaryExpressionClass.getEStructuralFeature(ASSIGNEE),
				variable3Left);
		EObject booleanUnaryExpressionR2ight = getEObjectOfTypeWithFeatureValue(
				rightModelResource, booleanUnaryExpressionClass,
				booleanUnaryExpressionClass.getEStructuralFeature(ASSIGNEE),
				variable3Right);
		assertTrue(matches(module, booleanUnaryExpression1Left,
				booleanUnaryExpressionR2ight));

		EObject booleanUnaryExpression2Left = getEObjectOfTypeWithFeatureValue(
				leftModelResource, booleanUnaryExpressionClass,
				booleanUnaryExpressionClass.getEStructuralFeature(ASSIGNEE),
				variable5Left);
		EObject booleanUnaryExpression2Right = getEObjectOfTypeWithFeatureValue(
				rightModelResource, booleanUnaryExpressionClass,
				booleanUnaryExpressionClass.getEStructuralFeature(ASSIGNEE),
				variable5Right);
		assertFalse(matches(module, booleanUnaryExpression2Left,
				booleanUnaryExpression2Right));
	}

	private boolean hasMatchingMatches(EclModule module, EObject eObject) {
		List<Match> matchingMatchesFromModule = getUniqueMatchingMatchesFromModule(module);
		for (Match match : matchingMatchesFromModule) {
			if (match.getLeft() == eObject || match.getRight() == eObject)
				return true;
		}
		return false;
	}

	private EClass getEClass(EPackage ePackage, String name) {
		EClassifier eClassifier = ePackage.getEClassifier(name);
		if (eClassifier instanceof EClass)
			return (EClass) eClassifier;
		return null;
	}

	private boolean matches(EclModule module, EObject activityLeft,
			EObject activityRight) {
		Match match = module.getContext().getMatchTrace()
				.getMatch(activityLeft, activityRight);
		return match.isMatching();
	}

	private EObject getEObjectOfTypeWithFeatureValue(Resource modelResource,
			EClass eClass, EStructuralFeature eStructuralfeature, Object value) {
		Set<EObject> eObjects = getEObjectsOfType(modelResource, eClass);
		Iterator<EObject> iterator = eObjects.iterator();
		while (iterator.hasNext()) {
			EObject eObject = iterator.next();
			Object eObjectValue = eObject.eGet(eStructuralfeature);
			if (eObjectValue.equals(value))
				return eObject;
		}
		return null;
	}

	private Set<EObject> getEObjectsOfType(Resource modelResource, EClass eClass) {
		Set<EObject> eObjects = new HashSet<EObject>();
		TreeIterator<EObject> allContents = modelResource.getAllContents();
		while (allContents.hasNext()) {
			EObject eObject = allContents.next();
			if (eObject.eClass() == eClass)
				eObjects.add(eObject);
		}
		return eObjects;
	}

	private List<Match> getMatchesFromModule(EclModule module) {
		return module.getContext().getMatchTrace().getMatches();
	}

	private List<Match> getUniqueMatchingMatchesFromModule(EclModule module) {
		List<Match> uniqueMatchingMatches = new ArrayList<Match>();
		List<Match> matchingMatches = getMatchingMatchesFromModule(module);
		HashMap<Object, Set<Object>> matchedObjects = new HashMap<Object, Set<Object>>();
		for (Match match : matchingMatches) {
			Object left = match.getLeft();
			Object right = match.getRight();
			if (left != right) {
				if (!matchedObjects.containsKey(left))
					matchedObjects.put(left, new HashSet<Object>());
				if (!matchedObjects.containsKey(right))
					matchedObjects.put(right, new HashSet<Object>());
				if (!matchedObjects.get(left).contains(right)
						&& !matchedObjects.get(right).contains(left))
					uniqueMatchingMatches.add(match);
				matchedObjects.get(left).add(right);
				matchedObjects.get(right).add(left);
			}
		}
		return uniqueMatchingMatches;
	}

	private List<Match> getMatchingMatchesFromModule(EclModule module) {
		List<Match> matchingMatches = new ArrayList<Match>();
		List<Match> matches = module.getContext().getMatchTrace().getMatches();
		for (Match match : matches) {
			if (match.isMatching())
				matchingMatches.add(match);
		}
		return matchingMatches;
	}

	@Test
	public void testADSemanticMatch() {
		Resource adMetamodelResource = loadMetamodel(ACTIVITYDIAGRAM_METAMODEL_PATH);
		Resource modelResourceLeft = loadModel("model/ad/activity3left.xmi");
		Resource modelResourceRight = loadModel("model/ad/activity3right.xmi");
		EPackage adEPackage = EMFUtil.getRootEPackage(adMetamodelResource);

		// Match models syntactically
		File eclFileSyntax = EMFUtil.createFile(ACTIVITYDIAGRAM_ECL_SYNTAX_PATH);
		EclModule moduleSyntax = EpsilonUtil.createEclModule(eclFileSyntax,
				modelResourceLeft, LEFT_MODEL_NAME, modelResourceRight,
				RIGHT_MODEL_NAME, adEPackage);

		MatchTrace matchTraceSyntax = EpsilonUtil.executeModule(moduleSyntax);

		// Execute models
		ConfigurationObjectMap configurationObjectMapLeft = createConfigurationObjectMap(
				modelResourceLeft, ACTIVITYDIAGRAM_XMOF_PATH);
		Resource configurationModelResourceLeft = createConfigurationModelResource(configurationObjectMapLeft);
		StatesBuilder statesBuilderLeft = execute(configurationModelResourceLeft);
		StateSystem stateSystemLeft = statesBuilderLeft.getStateSystem();
		assertNotNull(stateSystemLeft);
		Resource stateSystemLeftResource = EMFUtil.createResource(resourceSet,
				editingDomain, EMFUtil.createFileURI("stateSystemLeft.xmi"),
				stateSystemLeft);

		ConfigurationObjectMap configurationObjectMapRight = createConfigurationObjectMap(
				modelResourceRight, ACTIVITYDIAGRAM_XMOF_PATH);
		Resource configurationModelResourceRight = createConfigurationModelResource(configurationObjectMapRight);
		StatesBuilder statesBuilderRight = execute(configurationModelResourceRight);
		StateSystem stateSystemRight = statesBuilderRight.getStateSystem();
		assertNotNull(stateSystemRight);
		Resource stateSystemRightResource = EMFUtil.createResource(resourceSet,
				editingDomain, EMFUtil.createFileURI("stateSystemLeft.xmi"),
				stateSystemRight);

		ConfigurationObjectMap configurationObjectMap = joinConfiugrationObjectMaps(
				configurationObjectMapLeft, configurationObjectMapRight);

		EPackage traceEPackage = TracemodelPackage.eINSTANCE;
		EPackage statesEPackage = StatesPackage.eINSTANCE;

		Collection<EPackage> ePackages = new HashSet<EPackage>();
		ePackages.add(adEPackage);
		ePackages.add(traceEPackage);
		ePackages.add(statesEPackage);
		ePackages.addAll(configurationObjectMap.getConfigurationPackages());

		// Match models semantically
		File eclFile = EMFUtil.createFile(ACTIVITYDIAGRAM_ECL_SEMANTICS_PATH);
		EclModule module = EpsilonUtil.createEclModule(eclFile,
				stateSystemLeftResource, LEFT_MODEL_NAME,
				stateSystemRightResource, RIGHT_MODEL_NAME, ePackages);

		EpsilonUtil.setNativeTypeDelegateToModule(module, this.getClass()
				.getClassLoader());

		XMOFInstanceMap instanceMapLeft = statesBuilderLeft.getVM()
				.getInstanceMap();
		XMOFInstanceMap instanceMapRight = statesBuilderRight.getVM()
				.getInstanceMap();
		XMOFInstanceMap instanceMap = joinInstanceMaps(instanceMapLeft,
				instanceMapRight);
		EpsilonUtil.setVariableToMdule(module, "instanceMap", instanceMap);

		EpsilonUtil.setVariableToMdule(module, "configurationObjectMap",
				configurationObjectMap);

		EpsilonUtil.setMatchTraceToModule(module, matchTraceSyntax);

		MatchTrace matchTrace = EpsilonUtil.executeModule(module);
		assertNotNull(matchTrace);
		Match match1 = matchTrace.getMatch(stateSystemLeft, stateSystemRight);
		assertTrue(match1.isMatching());
		Match match2 = matchTrace.getMatch(stateSystemRight, stateSystemLeft);
		assertTrue(match2.isMatching());
		Match match3 = matchTrace.getMatch(stateSystemRight, stateSystemRight);
		assertTrue(match3.isMatching());
		Match match4 = matchTrace.getMatch(stateSystemLeft, stateSystemLeft);
		assertTrue(match4.isMatching());
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

	private void addConfigurationPackages(
			ConfigurationObjectMapModifiable joinedMap,
			ConfigurationObjectMap map) {
		for (EPackage ePackage : map.getConfigurationPackages()) {
			if (!joinedMap.getConfigurationPackages().contains(ePackage))
				joinedMap.getConfigurationPackages().add(ePackage);
		}
	}

	private void addMappings(ConfigurationObjectMapModifiable joinedMap,
			ConfigurationObjectMap map) {
		for (EObject configurationObject : map.getConfigurationObjects()) {
			joinedMap.addToMap(map.getOriginalObject(configurationObject),
					configurationObject);
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

	private StatesBuilder execute(Resource configurationModelResource) {
		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet,
				editingDomain, configurationModelResource);

		StatesBuilder statesBuilder = StatesBuilderUtil.createStatesBuilder(vm,
				configurationModelResource);
		vm.run();

		return statesBuilder;
	}

	private Resource createConfigurationModelResource(
			ConfigurationObjectMap configurationObjectMap) {
		Resource configurationModelResource = EMFUtil.createResource(
				resourceSet, editingDomain,
				EMFUtil.createFileURI("configurationmodel.xmi"),
				configurationObjectMap.getConfigurationObjects());
		return configurationModelResource;
	}

	private ConfigurationObjectMap createConfigurationObjectMap(
			Resource modelResource, String xmofFilePath) {
		Resource configurationMetamodelResource = loadModel(xmofFilePath);

		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationMetamodelResource,
						modelResource);
		return configurationObjectMap;
	}

	private Resource loadMetamodel(String metamodelFilePath) {
		Resource metamodelResource = EMFUtil.loadMetamodel(resourceSet,
				EMFUtil.createFileURI(metamodelFilePath));
		return metamodelResource;
	}

	private Resource loadModel(String modelFilePath) {
		Resource modelResource = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI(modelFilePath));
		return modelResource;
	}

	@Test
	public void testADSemanticMatchWithMatcher() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD("model/ad/activity3left.xmi", "model/ad/activity3right.xmi");
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}

	@Test
	public void testADSemanticMatch_NoMatch_DifferentVariableValues() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD("model/ad/activity4left.xmi", "model/ad/activity4right.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}

	@Test
	public void testADSemanticMatch_NoMatch_DifferentActionNames() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherAD(
				"model/ad/activity5left.xmi", "model/ad/activity5right.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}

	@Test
	public void testCDSemanticMatch_EMTNonWitness1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(
				"model/cd/EMT/EMTv1.xmi", "model/cd/EMT/EMTv2.xmi");
		XMOFMatcherContext context = matcher.getXMOFMatcherContext();
		addLeftParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv1nonwitness1parameter.xmi");
		addRightParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv2nonwitness1parameter.xmi");
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}
	
	@Test
	public void testCDSemanticMatch_EMTWitness1() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(
				"model/cd/EMT/EMTv1.xmi", "model/cd/EMT/EMTv2.xmi");
		XMOFMatcherContext context = matcher.getXMOFMatcherContext();
		addLeftParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv1witness1parameter.xmi");
		addRightParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv2witness1parameter.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testCDSemanticMatch_EMTWitness2() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(
				"model/cd/EMT/EMTv1.xmi", "model/cd/EMT/EMTv2.xmi");
		XMOFMatcherContext context = matcher.getXMOFMatcherContext();
		addLeftParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv1witness2parameter.xmi");
		addRightParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv2witness2parameter.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testCDSemanticMatch_EMT() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherCD(
				"model/cd/EMT/EMTv1.xmi", "model/cd/EMT/EMTv2.xmi");
		XMOFMatcherContext context = matcher.getXMOFMatcherContext();
		addLeftParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv1nonwitness1parameter.xmi");
		addLeftParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv1witness1parameter.xmi");
		addRightParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv2nonwitness1parameter.xmi");
		addRightParameterToXMOFMatcherContext(context, "model/cd/EMT/EMTv2witness1parameter.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testPetriNetSemanticMatch_PN1PN2() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn2.xmi");
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());

		XMOFMatcherContext context = matcher.getXMOFMatcherContext();

		Resource metamodelResource = context.getMetamodelResource();
		EPackage metamodelRootEPackage = EMFUtil
				.getRootEPackage(metamodelResource);
		EClass placeEClass = getEClass(metamodelRootEPackage, PLACE);
		EStructuralFeature placeNameEStructuralFeature = placeEClass
				.getEStructuralFeature(PLACE_NAME);

		Resource configurationMetamodelResource = context
				.getConfigurationMetamodelResource();
		EPackage configurationRootEPackage = EMFUtil
				.getRootEPackage(configurationMetamodelResource);
		EClass placeConfigurationClass = getEClass(configurationRootEPackage,
				PLACECONFIGURATION);
		EStructuralFeature placeConfigurationTokensEStructuralFeature = placeConfigurationClass
				.getEStructuralFeature(PLACECONFIGURATION_TOKENS);

		XMOFSemanticMatchResult semanticMatchResult = matcher
				.getSemanticMatchResults().get(0);

		Resource configurationModelResourceLeft = semanticMatchResult
				.getConfigurationModelResourceLeft();
		EObject p1Left = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceLeft, placeConfigurationClass,
				placeNameEStructuralFeature, "p1");
		EObject p2Left = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceLeft, placeConfigurationClass,
				placeNameEStructuralFeature, "p2");
		EObject p3Left = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceLeft, placeConfigurationClass,
				placeNameEStructuralFeature, "p3");
		EObject p4Left = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceLeft, placeConfigurationClass,
				placeNameEStructuralFeature, "p4");

		StateSystem stateSystemLeft = semanticMatchResult.getStateSystemLeft();
		State lastStateLeft = stateSystemLeft.getLastState();
		EObject p1FinalLeft = lastStateLeft.getObjectState(p1Left);
		EObject p2FinalLeft = lastStateLeft.getObjectState(p2Left);
		EObject p3FinalLeft = lastStateLeft.getObjectState(p3Left);
		EObject p4FinalLeft = lastStateLeft.getObjectState(p4Left);

		assertEquals(0, p1FinalLeft.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(0, p2FinalLeft.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(0, p3FinalLeft.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(1, p4FinalLeft.eGet(placeConfigurationTokensEStructuralFeature));
		
		Resource configurationModelResourceRight = semanticMatchResult
				.getConfigurationModelResourceRight();
		EObject p1Right = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceRight, placeConfigurationClass,
				placeNameEStructuralFeature, "p1");
		EObject p2Right = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceRight, placeConfigurationClass,
				placeNameEStructuralFeature, "p2");
		EObject p3Right = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceRight, placeConfigurationClass,
				placeNameEStructuralFeature, "p3");
		EObject p4Right = getEObjectOfTypeWithFeatureValue(
				configurationModelResourceRight, placeConfigurationClass,
				placeNameEStructuralFeature, "p4");

		StateSystem stateSystemRight = semanticMatchResult.getStateSystemRight();
		State lastStateRight = stateSystemRight.getLastState();
		EObject p1FinalRight = lastStateRight.getObjectState(p1Right);
		EObject p2FinalRight = lastStateRight.getObjectState(p2Right);
		EObject p3FinalRight = lastStateRight.getObjectState(p3Right);
		EObject p4FinalRight = lastStateRight.getObjectState(p4Right);

		assertEquals(0, p1FinalRight.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(1, p2FinalRight.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(1, p3FinalRight.eGet(placeConfigurationTokensEStructuralFeature));
		assertEquals(0, p4FinalRight.eGet(placeConfigurationTokensEStructuralFeature));
	}
	
	@Test
	public void testPetriNetSemanticMatch_PN1PN3() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn3.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testPetriNetSemanticMatch_PN1PN2_finalMarking() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1FinalMarking(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn3.xmi");
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}
	
	@Test
	public void testPetriNetSemanticMatch_PN1PN1_finalMarking() throws EolRuntimeException {
		XMOFMatcher matcher = prepareXMOFMatcherPetriNet1FinalMarking(
				"model/petrinet1/pn1.xmi", "model/petrinet1/pn1.xmi");
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}
	
	private XMOFMatcher prepareXMOFMatcherAD(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(ACTIVITYDIAGRAM_METAMODEL_PATH,
				ACTIVITYDIAGRAM_XMOF_PATH, ACTIVITYDIAGRAM_ECL_SYNTAX_PATH,
				ACTIVITYDIAGRAM_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	private XMOFMatcher prepareXMOFMatcherCD(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(CLASSDIAGRAM_METAMODEL_PATH,
				CLASSDIAGRAM_XMOF_PATH, CLASSDIAGRAM_ECL_SYNTAX_PATH,
				CLASSDIAGRAM_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	private XMOFMatcher prepareXMOFMatcherPetriNet1(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(PETRINET1_METAMODEL_PATH,
				PETRINET1_XMOF_PATH, PETRINET1_ECL_SYNTAX_PATH,
				PETRINET1_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	private XMOFMatcher prepareXMOFMatcherPetriNet1FinalMarking(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(PETRINET1_METAMODEL_PATH,
				PETRINET1_XMOF_PATH, PETRINET1_ECL_SYNTAX_PATH,
				PETRINET1_ECL_SEMANTICS_FINAL_MARKING_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	private XMOFMatcher prepareXMOFMatcher(String metamodelPath,
			String configurationPath, String syntactixEclPath,
			String semanticEclPath, String modelFilePathLeft,
			String modelFilePathRight) {
		XMOFMatcherContext context = new XMOFMatcherContext();
		context.setResourceSet(resourceSet);
		context.setEditingDomain(editingDomain);
		context.setMetamodelResource(metamodelPath);
		context.setModelResourceLeft(modelFilePathLeft);
		context.setModelResourceRight(modelFilePathRight);
		context.setConfigurationMetamodelResource(configurationPath);
		context.setEclFileSyntax(syntactixEclPath);
		context.setEclFileSemantics(semanticEclPath);
		context.setNativeTypeDelegate(this.getClass().getClassLoader());
		XMOFMatcher matcher = new XMOFMatcher();
		matcher.setXMOFMatcherContext(context);
		return matcher;
	}
	
	private void addLeftParameterToXMOFMatcherContext(XMOFMatcherContext context, String... parameterFilePaths) {
		for (String parameterFilePath : parameterFilePaths) {
			context.addParameterResourceLeft(parameterFilePath);
		}
	}
	
	private void addRightParameterToXMOFMatcherContext(XMOFMatcherContext context, String... parameterFilePaths) {
		for (String parameterFilePath : parameterFilePaths) {
			context.addParameterResourceRight(parameterFilePath);
		}
	}
	
}
