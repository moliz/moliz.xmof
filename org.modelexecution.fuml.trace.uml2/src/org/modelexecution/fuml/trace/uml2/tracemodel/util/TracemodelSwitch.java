/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.modelexecution.fuml.trace.uml2.tracemodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage
 * @generated
 */
public class TracemodelSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracemodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracemodelSwitch() {
		if (modelPackage == null) {
			modelPackage = TracemodelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TracemodelPackage.TRACE: {
				Trace trace = (Trace)theEObject;
				T result = caseTrace(trace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.ACTIVITY_EXECUTION: {
				ActivityExecution activityExecution = (ActivityExecution)theEObject;
				T result = caseActivityExecution(activityExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.ACTIVITY_NODE_EXECUTION: {
				ActivityNodeExecution activityNodeExecution = (ActivityNodeExecution)theEObject;
				T result = caseActivityNodeExecution(activityNodeExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.CALL_ACTION_EXECUTION: {
				CallActionExecution callActionExecution = (CallActionExecution)theEObject;
				T result = caseCallActionExecution(callActionExecution);
				if (result == null) result = caseActionExecution(callActionExecution);
				if (result == null) result = caseActivityNodeExecution(callActionExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.PARAMETER_SETTING: {
				ParameterSetting parameterSetting = (ParameterSetting)theEObject;
				T result = caseParameterSetting(parameterSetting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INPUT: {
				Input input = (Input)theEObject;
				T result = caseInput(input);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.OUTPUT: {
				Output output = (Output)theEObject;
				T result = caseOutput(output);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.TOKEN_INSTANCE: {
				TokenInstance tokenInstance = (TokenInstance)theEObject;
				T result = caseTokenInstance(tokenInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.OBJECT_TOKEN_INSTANCE: {
				ObjectTokenInstance objectTokenInstance = (ObjectTokenInstance)theEObject;
				T result = caseObjectTokenInstance(objectTokenInstance);
				if (result == null) result = caseTokenInstance(objectTokenInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.CONTROL_TOKEN_INSTANCE: {
				ControlTokenInstance controlTokenInstance = (ControlTokenInstance)theEObject;
				T result = caseControlTokenInstance(controlTokenInstance);
				if (result == null) result = caseTokenInstance(controlTokenInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.ACTION_EXECUTION: {
				ActionExecution actionExecution = (ActionExecution)theEObject;
				T result = caseActionExecution(actionExecution);
				if (result == null) result = caseActivityNodeExecution(actionExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.CONTROL_NODE_EXECUTION: {
				ControlNodeExecution controlNodeExecution = (ControlNodeExecution)theEObject;
				T result = caseControlNodeExecution(controlNodeExecution);
				if (result == null) result = caseActivityNodeExecution(controlNodeExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.VALUE_INSTANCE: {
				ValueInstance valueInstance = (ValueInstance)theEObject;
				T result = caseValueInstance(valueInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.VALUE_SNAPSHOT: {
				ValueSnapshot valueSnapshot = (ValueSnapshot)theEObject;
				T result = caseValueSnapshot(valueSnapshot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.DECISION_NODE_EXECUTION: {
				DecisionNodeExecution decisionNodeExecution = (DecisionNodeExecution)theEObject;
				T result = caseDecisionNodeExecution(decisionNodeExecution);
				if (result == null) result = caseControlNodeExecution(decisionNodeExecution);
				if (result == null) result = caseActivityNodeExecution(decisionNodeExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.STRUCTURED_ACTIVITY_NODE_EXECUTION: {
				StructuredActivityNodeExecution structuredActivityNodeExecution = (StructuredActivityNodeExecution)theEObject;
				T result = caseStructuredActivityNodeExecution(structuredActivityNodeExecution);
				if (result == null) result = caseActionExecution(structuredActivityNodeExecution);
				if (result == null) result = caseActivityNodeExecution(structuredActivityNodeExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INPUT_VALUE: {
				InputValue inputValue = (InputValue)theEObject;
				T result = caseInputValue(inputValue);
				if (result == null) result = caseInputOutputValue(inputValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.OUTPUT_VALUE: {
				OutputValue outputValue = (OutputValue)theEObject;
				T result = caseOutputValue(outputValue);
				if (result == null) result = caseInputOutputValue(outputValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INPUT_PARAMETER_VALUE: {
				InputParameterValue inputParameterValue = (InputParameterValue)theEObject;
				T result = caseInputParameterValue(inputParameterValue);
				if (result == null) result = caseParameterValue(inputParameterValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INPUT_PARAMETER_SETTING: {
				InputParameterSetting inputParameterSetting = (InputParameterSetting)theEObject;
				T result = caseInputParameterSetting(inputParameterSetting);
				if (result == null) result = caseParameterSetting(inputParameterSetting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.OUTPUT_PARAMETER_SETTING: {
				OutputParameterSetting outputParameterSetting = (OutputParameterSetting)theEObject;
				T result = caseOutputParameterSetting(outputParameterSetting);
				if (result == null) result = caseParameterSetting(outputParameterSetting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.OUTPUT_PARAMETER_VALUE: {
				OutputParameterValue outputParameterValue = (OutputParameterValue)theEObject;
				T result = caseOutputParameterValue(outputParameterValue);
				if (result == null) result = caseParameterValue(outputParameterValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INITIAL_NODE_EXECUTION: {
				InitialNodeExecution initialNodeExecution = (InitialNodeExecution)theEObject;
				T result = caseInitialNodeExecution(initialNodeExecution);
				if (result == null) result = caseControlNodeExecution(initialNodeExecution);
				if (result == null) result = caseActivityNodeExecution(initialNodeExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.PARAMETER_VALUE: {
				ParameterValue parameterValue = (ParameterValue)theEObject;
				T result = caseParameterValue(parameterValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.EXPANSION_INPUT: {
				ExpansionInput expansionInput = (ExpansionInput)theEObject;
				T result = caseExpansionInput(expansionInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.EXPANSION_REGION_EXECUTION: {
				ExpansionRegionExecution expansionRegionExecution = (ExpansionRegionExecution)theEObject;
				T result = caseExpansionRegionExecution(expansionRegionExecution);
				if (result == null) result = caseStructuredActivityNodeExecution(expansionRegionExecution);
				if (result == null) result = caseActionExecution(expansionRegionExecution);
				if (result == null) result = caseActivityNodeExecution(expansionRegionExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracemodelPackage.INPUT_OUTPUT_VALUE: {
				InputOutputValue inputOutputValue = (InputOutputValue)theEObject;
				T result = caseInputOutputValue(inputOutputValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrace(Trace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityExecution(ActivityExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Node Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityNodeExecution(ActivityNodeExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Action Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Action Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallActionExecution(CallActionExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Setting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Setting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterSetting(ParameterSetting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInput(Input object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutput(Output object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Token Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Token Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTokenInstance(TokenInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Token Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Token Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectTokenInstance(ObjectTokenInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Token Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Token Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlTokenInstance(ControlTokenInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionExecution(ActionExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Node Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlNodeExecution(ControlNodeExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueInstance(ValueInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Snapshot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Snapshot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSnapshot(ValueSnapshot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decision Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decision Node Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecisionNodeExecution(DecisionNodeExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structured Activity Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structured Activity Node Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuredActivityNodeExecution(StructuredActivityNodeExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputValue(InputValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutputValue(OutputValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Parameter Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputParameterValue(InputParameterValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Parameter Setting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Parameter Setting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputParameterSetting(InputParameterSetting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output Parameter Setting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output Parameter Setting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutputParameterSetting(OutputParameterSetting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output Parameter Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutputParameterValue(OutputParameterValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initial Node Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initial Node Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInitialNodeExecution(InitialNodeExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterValue(ParameterValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expansion Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expansion Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpansionInput(ExpansionInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expansion Region Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expansion Region Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpansionRegionExecution(ExpansionRegionExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Output Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Output Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputOutputValue(InputOutputValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TracemodelSwitch
