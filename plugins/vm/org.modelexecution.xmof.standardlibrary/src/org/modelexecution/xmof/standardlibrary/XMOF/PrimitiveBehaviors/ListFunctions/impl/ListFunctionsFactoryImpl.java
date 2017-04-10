/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ListFunctionsFactoryImpl extends EFactoryImpl implements ListFunctionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ListFunctionsFactory init() {
		try {
			ListFunctionsFactory theListFunctionsFactory = (ListFunctionsFactory)EPackage.Registry.INSTANCE.getEFactory(ListFunctionsPackage.eNS_URI);
			if (theListFunctionsFactory != null) {
				return theListFunctionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ListFunctionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListFunctionsFactoryImpl() {
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
			case ListFunctionsPackage.LIST_GET: return createListGet();
			case ListFunctionsPackage.LIST_SIZE: return createListSize();
			case ListFunctionsPackage.LIST_INDEX_OF: return createListIndexOf();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListGet createListGet() {
		ListGetImpl listGet = new ListGetImpl();
		return listGet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListSize createListSize() {
		ListSizeImpl listSize = new ListSizeImpl();
		return listSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListIndexOf createListIndexOf() {
		ListIndexOfImpl listIndexOf = new ListIndexOfImpl();
		return listIndexOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListFunctionsPackage getListFunctionsPackage() {
		return (ListFunctionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ListFunctionsPackage getPackage() {
		return ListFunctionsPackage.eINSTANCE;
	}

} //ListFunctionsFactoryImpl
