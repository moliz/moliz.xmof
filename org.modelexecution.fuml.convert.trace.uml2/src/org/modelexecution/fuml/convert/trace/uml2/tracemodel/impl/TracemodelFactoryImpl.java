/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TracemodelFactoryImpl extends EFactoryImpl implements TracemodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TracemodelFactory init() {
		try {
			TracemodelFactory theTracemodelFactory = (TracemodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.modelexecution.org/trace/uml2/1.0"); 
			if (theTracemodelFactory != null) {
				return theTracemodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TracemodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracemodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TracemodelPackage.TRACE: return createTrace();
			case TracemodelPackage.ACTIVITY_EXECUTION: return createActivityExecution();
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION: return createActivityNodeExecution();
			case TracemodelPackage.CALL_ACTION_EXECUTION: return createCallActionExecution();
			case TracemodelPackage.USER_PARAMETER_INPUT: return createUserParameterInput();
			case TracemodelPackage.INPUT: return createInput();
			case TracemodelPackage.OUTPUT: return createOutput();
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE: return createObjectTokenInstance();
			case TracemodelPackage.CONTROL_TOKEN_INSTANCE: return createControlTokenInstance();
			case TracemodelPackage.VALUE_INSTANCE: return createValueInstance();
			case TracemodelPackage.ACTION_EXECUTION: return createActionExecution();
			case TracemodelPackage.CONTROL_NODE_EXECUTION: return createControlNodeExecution();
			case TracemodelPackage.VALUE_INSTANCE_SNAPSHOT: return createValueInstanceSnapshot();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace createTrace() {
		TraceImpl trace = new TraceImpl();
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution createActivityExecution() {
		ActivityExecutionImpl activityExecution = new ActivityExecutionImpl();
		return activityExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeExecution createActivityNodeExecution() {
		ActivityNodeExecutionImpl activityNodeExecution = new ActivityNodeExecutionImpl();
		return activityNodeExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecution createCallActionExecution() {
		CallActionExecutionImpl callActionExecution = new CallActionExecutionImpl();
		return callActionExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserParameterInput createUserParameterInput() {
		UserParameterInputImpl userParameterInput = new UserParameterInputImpl();
		return userParameterInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Input createInput() {
		InputImpl input = new InputImpl();
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Output createOutput() {
		OutputImpl output = new OutputImpl();
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectTokenInstance createObjectTokenInstance() {
		ObjectTokenInstanceImpl objectTokenInstance = new ObjectTokenInstanceImpl();
		return objectTokenInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlTokenInstance createControlTokenInstance() {
		ControlTokenInstanceImpl controlTokenInstance = new ControlTokenInstanceImpl();
		return controlTokenInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstance createValueInstance() {
		ValueInstanceImpl valueInstance = new ValueInstanceImpl();
		return valueInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionExecution createActionExecution() {
		ActionExecutionImpl actionExecution = new ActionExecutionImpl();
		return actionExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlNodeExecution createControlNodeExecution() {
		ControlNodeExecutionImpl controlNodeExecution = new ControlNodeExecutionImpl();
		return controlNodeExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueInstanceSnapshot createValueInstanceSnapshot() {
		ValueInstanceSnapshotImpl valueInstanceSnapshot = new ValueInstanceSnapshotImpl();
		return valueInstanceSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracemodelPackage getTracemodelPackage() {
		return (TracemodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TracemodelPackage getPackage() {
		return TracemodelPackage.eINSTANCE;
	}

} //TracemodelFactoryImpl
