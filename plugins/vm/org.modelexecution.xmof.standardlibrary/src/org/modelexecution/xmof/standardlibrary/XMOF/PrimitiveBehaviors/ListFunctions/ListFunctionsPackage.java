/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListFunctionsFactory
 * @model kind="package"
 * @generated
 */
public interface ListFunctionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ListFunctions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://xmofstandardlibrary/1.0/primitivebehaviors/listfunctions";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xmoflib.primitivebehaviors.listfunctions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ListFunctionsPackage eINSTANCE = org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListGetImpl <em>List Get</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListGetImpl
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListGet()
	 * @generated
	 */
	int LIST_GET = 0;

	/**
	 * The number of structural features of the '<em>List Get</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GET_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>List Get</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListSizeImpl <em>List Size</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListSizeImpl
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListSize()
	 * @generated
	 */
	int LIST_SIZE = 1;

	/**
	 * The number of structural features of the '<em>List Size</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_SIZE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>List Size</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_SIZE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListIndexOfImpl <em>List Index Of</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListIndexOfImpl
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListIndexOf()
	 * @generated
	 */
	int LIST_INDEX_OF = 2;

	/**
	 * The number of structural features of the '<em>List Index Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_INDEX_OF_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>List Index Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_INDEX_OF_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListGet <em>List Get</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Get</em>'.
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListGet
	 * @generated
	 */
	EClass getListGet();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListSize <em>List Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Size</em>'.
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListSize
	 * @generated
	 */
	EClass getListSize();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListIndexOf <em>List Index Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Index Of</em>'.
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListIndexOf
	 * @generated
	 */
	EClass getListIndexOf();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ListFunctionsFactory getListFunctionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListGetImpl <em>List Get</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListGetImpl
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListGet()
		 * @generated
		 */
		EClass LIST_GET = eINSTANCE.getListGet();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListSizeImpl <em>List Size</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListSizeImpl
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListSize()
		 * @generated
		 */
		EClass LIST_SIZE = eINSTANCE.getListSize();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListIndexOfImpl <em>List Index Of</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListIndexOfImpl
		 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl#getListIndexOf()
		 * @generated
		 */
		EClass LIST_INDEX_OF = eINSTANCE.getListIndexOf();

	}

} //ListFunctionsPackage
