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
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.configuration.ConfigurationObjectMapModifiable;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFMatcherContext;
import org.modelexecution.xmof.diff.util.EMFUtil;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.diff.util.XMOFUtil;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

/**
 * @author Tanja
 * 
 */
public class ECLMatchTest {

	private static final String ECL_SEMANTICS_PATH = "ecl/ad/semantics.ecl";
	private static final String ECL_SYNTAX_PATH = "ecl/ad/syntax.ecl";
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

		File eclFile = EMFUtil.createFile(ECL_SYNTAX_PATH);
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
		File eclFileSyntax = EMFUtil.createFile(ECL_SYNTAX_PATH);
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
		File eclFile = EMFUtil.createFile(ECL_SEMANTICS_PATH);
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

		StatesBuilder statesBuilder = XMOFUtil.createStatesBuilder(vm,
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
	public void testADSemanticMatchWithMatcher() {
		XMOFMatcherContext context = prepareXMOFMatcherContext("model/ad/activity3left.xmi", "model/ad/activity3right.xmi");
		XMOFMatcher matcher = new XMOFMatcher();
		matcher.setXMOFMatcherContext(context);
		assertTrue(matcher.canMatch());
		assertTrue(matcher.match());
	}

	@Test
	public void testADSemanticMatch_NoMatch_DifferentVariableValues() {
		XMOFMatcherContext context = prepareXMOFMatcherContext("model/ad/activity4left.xmi", "model/ad/activity4right.xmi");
		XMOFMatcher matcher = new XMOFMatcher();
		matcher.setXMOFMatcherContext(context);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}

	@Test
	public void testADSemanticMatch_NoMatch_DifferentActionNames() {
		XMOFMatcherContext context = prepareXMOFMatcherContext(
				"model/ad/activity5left.xmi", "model/ad/activity5right.xmi");
		XMOFMatcher matcher = new XMOFMatcher();
		matcher.setXMOFMatcherContext(context);
		assertTrue(matcher.canMatch());
		assertFalse(matcher.match());
	}

	private XMOFMatcherContext prepareXMOFMatcherContext(
			String modelFilePathLeft, String modelFilePathRight) {
		XMOFMatcherContext context = new XMOFMatcherContext();
		context.setResourceSet(resourceSet);
		context.setEditingDomain(editingDomain);
		context.setMetamodelResource(ACTIVITYDIAGRAM_METAMODEL_PATH);
		context.setModelResourceLeft(modelFilePathLeft);
		context.setModelResourceRight(modelFilePathRight);
		context.setConfigurationMetamodelResource(ACTIVITYDIAGRAM_XMOF_PATH);
		context.setEclFileSyntax(ECL_SYNTAX_PATH);
		context.setEclFileSemantics(ECL_SEMANTICS_PATH);
		context.setNativeTypeDelegate(this.getClass().getClassLoader());
		return context;
	}
}
