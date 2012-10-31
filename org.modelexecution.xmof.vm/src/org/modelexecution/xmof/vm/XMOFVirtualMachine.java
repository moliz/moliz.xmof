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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.xmof.XMOFConverter;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.xmof.Syntax.Classes.Kernel.MainEClass;
import org.modelexecution.xmof.vm.internal.XMOFInstanceMapper;

import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * The virtual machine for executing {@link XMOFBasedModel xMOF-based models}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFVirtualMachine {

	private final ExecutionContext executionContext = ExecutionContext
			.getInstance();

	private XMOFBasedModel model;
	private IConversionResult xMOFConversionResult;
	private XMOFInstanceMapper instanceMapper;

	public XMOFVirtualMachine(XMOFBasedModel modelToBeExecuted) {
		super();
		this.model = modelToBeExecuted;
		initialize();
	}

	private void initialize() {
		convertMetamodel();
		initializeInstanceMapper();
	}

	private void initializeInstanceMapper() {
		this.instanceMapper = new XMOFInstanceMapper(xMOFConversionResult,
				model.getModelElements());
	}

	private void convertMetamodel() {
		XMOFConverter xMOFConverter = new XMOFConverter();
		if (xMOFConverter.canConvert(getMetamodelPackage())) {
			xMOFConversionResult = xMOFConverter.convert(getMetamodelPackage());
		}
	}

	private EPackage getMetamodelPackage() {
		return model.getMetamodelPackages().get(0);
	}

	public XMOFBasedModel getModel() {
		return model;
	}

	public boolean mayRun() {
		return isXMOFConversionOK();
	}

	private boolean isXMOFConversionOK() {
		return xMOFConversionResult != null
				&& xMOFConversionResult.getStatus().isOK();
	}

	public void run() {
		initializeLocus();
		// TODO add some kind of listener
		// maybe set the instanceMapper as a listener which would allow it to
		// modify the model elements at runtime
		executeAllMainObjects();
	}

	private void initializeLocus() {
		executionContext.getExtensionalValues().addAll(
				instanceMapper.getExtensionalValues());
	}

	private void executeAllMainObjects() {
		for (EObject mainClassObject : model.getMainEClassObjects()) {
			executionContext.execute(getClassifierBehavior(mainClassObject),
					instanceMapper.getObject(mainClassObject), null);
		}
	}

	private Behavior getClassifierBehavior(EObject mainClassObject) {
		EClass mainEClassInstance = mainClassObject.eClass();
		Assert.isTrue(XMOFBasedModel.MAIN_E_CLASS
				.isInstance(mainEClassInstance));
		MainEClass mainEClass = (MainEClass) mainEClassInstance;
		return (Behavior) xMOFConversionResult.getFUMLElement(mainEClass
				.getClassifierBehavior());
	}

}
