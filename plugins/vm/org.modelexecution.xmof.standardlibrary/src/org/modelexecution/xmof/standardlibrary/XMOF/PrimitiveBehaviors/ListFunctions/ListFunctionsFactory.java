/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListFunctionsPackage
 * @generated
 */
public interface ListFunctionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ListFunctionsFactory eINSTANCE = org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>List Get</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Get</em>'.
	 * @generated
	 */
	ListGet createListGet();

	/**
	 * Returns a new object of class '<em>List Size</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Size</em>'.
	 * @generated
	 */
	ListSize createListSize();

	/**
	 * Returns a new object of class '<em>List Index Of</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Index Of</em>'.
	 * @generated
	 */
	ListIndexOf createListIndexOf();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ListFunctionsPackage getListFunctionsPackage();

} //ListFunctionsFactory
