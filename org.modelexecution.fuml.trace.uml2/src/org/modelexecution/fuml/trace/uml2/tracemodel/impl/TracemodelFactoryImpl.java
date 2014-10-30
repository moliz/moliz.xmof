/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.modelexecution.fuml.trace.uml2.tracemodel.*;

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
			TracemodelFactory theTracemodelFactory = (TracemodelFactory)EPackage.Registry.INSTANCE.getEFactory(TracemodelPackage.eNS_URI);
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
			case TracemodelPackage.CALL_ACTION_EXECUTION: return createCallActionExecution();
			case TracemodelPackage.INPUT: return createInput();
			case TracemodelPackage.OUTPUT: return createOutput();
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE: return createObjectTokenInstance();
			case TracemodelPackage.CONTROL_TOKEN_INSTANCE: return createControlTokenInstance();
			case TracemodelPackage.ACTION_EXECUTION: return createActionExecution();
			case TracemodelPackage.CONTROL_NODE_EXECUTION: return createControlNodeExecution();
			case TracemodelPackage.VALUE_INSTANCE: return createValueInstance();
			case TracemodelPackage.VALUE_SNAPSHOT: return createValueSnapshot();
			case TracemodelPackage.DECISION_NODE_EXECUTION: return createDecisionNodeExecution();
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION: return createStructuredActivityNodeExecution();
			case TracemodelPackage.INPUT_VALUE: return createInputValue();
			case TracemodelPackage.OUTPUT_VALUE: return createOutputValue();
			case TracemodelPackage.INPUT_PARAMETER_VALUE: return createInputParameterValue();
			case TracemodelPackage.INPUT_PARAMETER_SETTING: return createInputParameterSetting();
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING: return createOutputParameterSetting();
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE: return createOutputParameterValue();
			case TracemodelPackage.INITIAL_NODE_EXECUTION: return createInitialNodeExecution();
			case TracemodelPackage.EXPANSION_INPUT: return createExpansionInput();
			case TracemodelPackage.EXPANSION_REGION_EXECUTION: return createExpansionRegionExecution();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
	public CallActionExecution createCallActionExecution() {
		CallActionExecutionImpl callActionExecution = new CallActionExecutionImpl();
		return callActionExecution;
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
	public ValueInstance createValueInstance() {
		ValueInstanceImpl valueInstance = new ValueInstanceImpl();
		return valueInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSnapshot createValueSnapshot() {
		ValueSnapshotImpl valueSnapshot = new ValueSnapshotImpl();
		return valueSnapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionNodeExecution createDecisionNodeExecution() {
		DecisionNodeExecutionImpl decisionNodeExecution = new DecisionNodeExecutionImpl();
		return decisionNodeExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredActivityNodeExecution createStructuredActivityNodeExecution() {
		StructuredActivityNodeExecutionImpl structuredActivityNodeExecution = new StructuredActivityNodeExecutionImpl();
		return structuredActivityNodeExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputValue createInputValue() {
		InputValueImpl inputValue = new InputValueImpl();
		return inputValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputValue createOutputValue() {
		OutputValueImpl outputValue = new OutputValueImpl();
		return outputValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputParameterValue createInputParameterValue() {
		InputParameterValueImpl inputParameterValue = new InputParameterValueImpl();
		return inputParameterValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputParameterSetting createInputParameterSetting() {
		InputParameterSettingImpl inputParameterSetting = new InputParameterSettingImpl();
		return inputParameterSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputParameterSetting createOutputParameterSetting() {
		OutputParameterSettingImpl outputParameterSetting = new OutputParameterSettingImpl();
		return outputParameterSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputParameterValue createOutputParameterValue() {
		OutputParameterValueImpl outputParameterValue = new OutputParameterValueImpl();
		return outputParameterValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialNodeExecution createInitialNodeExecution() {
		InitialNodeExecutionImpl initialNodeExecution = new InitialNodeExecutionImpl();
		return initialNodeExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionInput createExpansionInput() {
		ExpansionInputImpl expansionInput = new ExpansionInputImpl();
		return expansionInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpansionRegionExecution createExpansionRegionExecution() {
		ExpansionRegionExecutionImpl expansionRegionExecution = new ExpansionRegionExecutionImpl();
		return expansionRegionExecution;
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
