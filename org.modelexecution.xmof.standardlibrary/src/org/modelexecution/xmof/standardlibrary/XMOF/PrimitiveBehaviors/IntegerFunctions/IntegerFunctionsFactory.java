/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.IntegerFunctionsPackage
 * @generated
 */
public interface IntegerFunctionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IntegerFunctionsFactory eINSTANCE = org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.impl.IntegerFunctionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Integer Plus</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Plus</em>'.
	 * @generated
	 */
	IntegerPlus createIntegerPlus();

	/**
	 * Returns a new object of class '<em>Integer Minus</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Minus</em>'.
	 * @generated
	 */
	IntegerMinus createIntegerMinus();

	/**
	 * Returns a new object of class '<em>Integer Times</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Times</em>'.
	 * @generated
	 */
	IntegerTimes createIntegerTimes();

	/**
	 * Returns a new object of class '<em>Integer Divide</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Divide</em>'.
	 * @generated
	 */
	IntegerDivide createIntegerDivide();

	/**
	 * Returns a new object of class '<em>Integer Less</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Less</em>'.
	 * @generated
	 */
	IntegerLess createIntegerLess();

	/**
	 * Returns a new object of class '<em>Integer Greater</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Greater</em>'.
	 * @generated
	 */
	IntegerGreater createIntegerGreater();

	/**
	 * Returns a new object of class '<em>Integer Less Or Equals</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Less Or Equals</em>'.
	 * @generated
	 */
	IntegerLessOrEquals createIntegerLessOrEquals();

	/**
	 * Returns a new object of class '<em>Integer Greater Or Equals</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Greater Or Equals</em>'.
	 * @generated
	 */
	IntegerGreaterOrEquals createIntegerGreaterOrEquals();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IntegerFunctionsPackage getIntegerFunctionsPackage();

} //IntegerFunctionsFactory
