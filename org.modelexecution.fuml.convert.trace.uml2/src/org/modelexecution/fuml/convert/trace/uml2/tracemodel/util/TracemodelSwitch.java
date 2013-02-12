/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.*;

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
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage
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
			case TracemodelPackage.USER_PARAMETER_INPUT: {
				UserParameterInput userParameterInput = (UserParameterInput)theEObject;
				T result = caseUserParameterInput(userParameterInput);
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
			case TracemodelPackage.VALUE_INSTANCE: {
				ValueInstance valueInstance = (ValueInstance)theEObject;
				T result = caseValueInstance(valueInstance);
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
			case TracemodelPackage.VALUE_INSTANCE_SNAPSHOT: {
				ValueInstanceSnapshot valueInstanceSnapshot = (ValueInstanceSnapshot)theEObject;
				T result = caseValueInstanceSnapshot(valueInstanceSnapshot);
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
	 * Returns the result of interpreting the object as an instance of '<em>User Parameter Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Parameter Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserParameterInput(UserParameterInput object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Value Instance Snapshot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Instance Snapshot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueInstanceSnapshot(ValueInstanceSnapshot object) {
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
