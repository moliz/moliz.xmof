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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.BeforeClass;
import org.modelexecution.xmof.diff.XMOFMatcher;
import org.modelexecution.xmof.diff.XMOFMatcherContext;
import org.modelexecution.xmof.diff.test.internal.factory.Input;
import org.modelexecution.xmof.vm.util.EMFUtil;

/**
 * @author Tanja
 * 
 */
public abstract class Evaluation {

	protected static final String PETRINET1_ECL_SEMANTICS_PATH = "ecl/petrinet1/semantics.ecl";
	protected static final String PETRINET1_ECL_SYNTAX_PATH = "ecl/petrinet1/syntax.ecl";
	protected static final String PETRINET1_XMOF_PATH = "model/petrinet1/petrinet.xmof";
	protected static final String PETRINET1_METAMODEL_PATH = "model/petrinet1/petrinet.ecore";	
	
	protected static final String CLASSDIAGRAM_ECL_SEMANTICS_PATH = "ecl/cd/semantics.ecl";
	protected static final String CLASSDIAGRAM_ECL_SYNTAX_PATH = "ecl/cd/syntax.ecl";
	protected static final String CLASSDIAGRAM_XMOF_PATH = "model/cd/classes.xmof";
	protected static final String CLASSDIAGRAM_METAMODEL_PATH = "model/cd/classes.ecore";
	
	protected static final String ACTIVITYDIAGRAM_ECL_SEMANTICS_PATH = "ecl/ad/semantics2.ecl";
	protected static final String ACTIVITYDIAGRAM_ECL_SYNTAX_PATH = "ecl/ad/syntax.ecl";
	protected static final String ACTIVITYDIAGRAM_XMOF_PATH = "model/ad/activitydiagram.xmof";
	protected static final String ACTIVITYDIAGRAM_METAMODEL_PATH = "model/ad/activitydiagram.ecore";

	protected ResourceSet resourceSet;
	protected EditingDomain editingDomain;

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
	}
	
	@Before
	public void setup() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	protected XMOFMatcher prepareXMOFMatcherAD(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(ACTIVITYDIAGRAM_METAMODEL_PATH,
				ACTIVITYDIAGRAM_XMOF_PATH, ACTIVITYDIAGRAM_ECL_SYNTAX_PATH,
				ACTIVITYDIAGRAM_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	protected XMOFMatcher prepareXMOFMatcherCD(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(CLASSDIAGRAM_METAMODEL_PATH,
				CLASSDIAGRAM_XMOF_PATH, CLASSDIAGRAM_ECL_SYNTAX_PATH,
				CLASSDIAGRAM_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	protected XMOFMatcher prepareXMOFMatcherPetriNet1(
			String modelFilePathLeft, String modelFilePathRight) {
		return prepareXMOFMatcher(PETRINET1_METAMODEL_PATH,
				PETRINET1_XMOF_PATH, PETRINET1_ECL_SYNTAX_PATH,
				PETRINET1_ECL_SEMANTICS_PATH, modelFilePathLeft,
				modelFilePathRight);
	}
	
	protected XMOFMatcher prepareXMOFMatcher(String metamodelPath,
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
	
	protected void addInputToXMOFMatcherContext(XMOFMatcherContext context, Input input) {
		context.getParameterResourcesLeft().addAll(input.getInputResourcesLeft());
		context.getParameterResourcesRight().addAll(input.getInputResourcesRight());
	}
	
	protected Resource createParameterDefinitionResource(String resourceFilePath,
			EObject parameterValueDefinition) {
		return EMFUtil.createResource(resourceSet, editingDomain,
				EMFUtil.createFileURI(resourceFilePath),
				parameterValueDefinition);
	}
}
