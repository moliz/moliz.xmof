/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.*;

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
 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.IntegerFunctionsPackage
 * @generated
 */
public class IntegerFunctionsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IntegerFunctionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerFunctionsSwitch() {
		if (modelPackage == null) {
			modelPackage = IntegerFunctionsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
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
			case IntegerFunctionsPackage.INTEGER_PLUS: {
				IntegerPlus integerPlus = (IntegerPlus)theEObject;
				T result = caseIntegerPlus(integerPlus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_MINUS: {
				IntegerMinus integerMinus = (IntegerMinus)theEObject;
				T result = caseIntegerMinus(integerMinus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_TIMES: {
				IntegerTimes integerTimes = (IntegerTimes)theEObject;
				T result = caseIntegerTimes(integerTimes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_DIVIDE: {
				IntegerDivide integerDivide = (IntegerDivide)theEObject;
				T result = caseIntegerDivide(integerDivide);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_LESS: {
				IntegerLess integerLess = (IntegerLess)theEObject;
				T result = caseIntegerLess(integerLess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_GREATER: {
				IntegerGreater integerGreater = (IntegerGreater)theEObject;
				T result = caseIntegerGreater(integerGreater);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_LESS_OR_EQUALS: {
				IntegerLessOrEquals integerLessOrEquals = (IntegerLessOrEquals)theEObject;
				T result = caseIntegerLessOrEquals(integerLessOrEquals);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IntegerFunctionsPackage.INTEGER_GREATER_OR_EQUALS: {
				IntegerGreaterOrEquals integerGreaterOrEquals = (IntegerGreaterOrEquals)theEObject;
				T result = caseIntegerGreaterOrEquals(integerGreaterOrEquals);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Plus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Plus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerPlus(IntegerPlus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Minus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Minus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerMinus(IntegerMinus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Times</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Times</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerTimes(IntegerTimes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Divide</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Divide</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerDivide(IntegerDivide object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Less</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Less</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerLess(IntegerLess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Greater</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Greater</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerGreater(IntegerGreater object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Less Or Equals</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Less Or Equals</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerLessOrEquals(IntegerLessOrEquals object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Greater Or Equals</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Greater Or Equals</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerGreaterOrEquals(IntegerGreaterOrEquals object) {
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

} //IntegerFunctionsSwitch
