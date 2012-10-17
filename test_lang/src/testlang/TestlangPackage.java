/**
 */
package testlang;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see testlang.TestlangFactory
 * @model kind="package"
 * @generated
 */
public interface TestlangPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testlang";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://testlang";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "testlang";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestlangPackage eINSTANCE = testlang.impl.TestlangPackageImpl.init();

	/**
	 * The meta object id for the '{@link testlang.impl.TestSuiteImpl <em>Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.TestSuiteImpl
	 * @see testlang.impl.TestlangPackageImpl#getTestSuite()
	 * @generated
	 */
	int TEST_SUITE = 0;

	/**
	 * The feature id for the '<em><b>System Under Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__SYSTEM_UNDER_TEST = 0;

	/**
	 * The feature id for the '<em><b>Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__TESTS = 1;

	/**
	 * The number of structural features of the '<em>Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link testlang.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.TestCaseImpl
	 * @see testlang.impl.TestlangPackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Activity Under Test</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ACTIVITY_UNDER_TEST = 1;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link testlang.impl.ActivityUnderTestImpl <em>Activity Under Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.ActivityUnderTestImpl
	 * @see testlang.impl.TestlangPackageImpl#getActivityUnderTest()
	 * @generated
	 */
	int ACTIVITY_UNDER_TEST = 2;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_UNDER_TEST__ACTIVITY = 0;

	/**
	 * The feature id for the '<em><b>Execute until</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_UNDER_TEST__EXECUTE_UNTIL = 1;

	/**
	 * The number of structural features of the '<em>Activity Under Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_UNDER_TEST_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link testlang.TestSuite <em>Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Suite</em>'.
	 * @see testlang.TestSuite
	 * @generated
	 */
	EClass getTestSuite();

	/**
	 * Returns the meta object for the reference '{@link testlang.TestSuite#getSystemUnderTest <em>System Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System Under Test</em>'.
	 * @see testlang.TestSuite#getSystemUnderTest()
	 * @see #getTestSuite()
	 * @generated
	 */
	EReference getTestSuite_SystemUnderTest();

	/**
	 * Returns the meta object for the containment reference list '{@link testlang.TestSuite#getTests <em>Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tests</em>'.
	 * @see testlang.TestSuite#getTests()
	 * @see #getTestSuite()
	 * @generated
	 */
	EReference getTestSuite_Tests();

	/**
	 * Returns the meta object for class '{@link testlang.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case</em>'.
	 * @see testlang.TestCase
	 * @generated
	 */
	EClass getTestCase();

	/**
	 * Returns the meta object for the attribute '{@link testlang.TestCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see testlang.TestCase#getName()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_Name();

	/**
	 * Returns the meta object for the containment reference '{@link testlang.TestCase#getActivityUnderTest <em>Activity Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity Under Test</em>'.
	 * @see testlang.TestCase#getActivityUnderTest()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_ActivityUnderTest();

	/**
	 * Returns the meta object for class '{@link testlang.ActivityUnderTest <em>Activity Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Under Test</em>'.
	 * @see testlang.ActivityUnderTest
	 * @generated
	 */
	EClass getActivityUnderTest();

	/**
	 * Returns the meta object for the reference '{@link testlang.ActivityUnderTest#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see testlang.ActivityUnderTest#getActivity()
	 * @see #getActivityUnderTest()
	 * @generated
	 */
	EReference getActivityUnderTest_Activity();

	/**
	 * Returns the meta object for the reference '{@link testlang.ActivityUnderTest#getExecute_until <em>Execute until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execute until</em>'.
	 * @see testlang.ActivityUnderTest#getExecute_until()
	 * @see #getActivityUnderTest()
	 * @generated
	 */
	EReference getActivityUnderTest_Execute_until();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestlangFactory getTestlangFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link testlang.impl.TestSuiteImpl <em>Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.TestSuiteImpl
		 * @see testlang.impl.TestlangPackageImpl#getTestSuite()
		 * @generated
		 */
		EClass TEST_SUITE = eINSTANCE.getTestSuite();

		/**
		 * The meta object literal for the '<em><b>System Under Test</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SUITE__SYSTEM_UNDER_TEST = eINSTANCE.getTestSuite_SystemUnderTest();

		/**
		 * The meta object literal for the '<em><b>Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SUITE__TESTS = eINSTANCE.getTestSuite_Tests();

		/**
		 * The meta object literal for the '{@link testlang.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.TestCaseImpl
		 * @see testlang.impl.TestlangPackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__NAME = eINSTANCE.getTestCase_Name();

		/**
		 * The meta object literal for the '<em><b>Activity Under Test</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__ACTIVITY_UNDER_TEST = eINSTANCE.getTestCase_ActivityUnderTest();

		/**
		 * The meta object literal for the '{@link testlang.impl.ActivityUnderTestImpl <em>Activity Under Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.ActivityUnderTestImpl
		 * @see testlang.impl.TestlangPackageImpl#getActivityUnderTest()
		 * @generated
		 */
		EClass ACTIVITY_UNDER_TEST = eINSTANCE.getActivityUnderTest();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_UNDER_TEST__ACTIVITY = eINSTANCE.getActivityUnderTest_Activity();

		/**
		 * The meta object literal for the '<em><b>Execute until</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_UNDER_TEST__EXECUTE_UNTIL = eINSTANCE.getActivityUnderTest_Execute_until();

	}

} //TestlangPackage
