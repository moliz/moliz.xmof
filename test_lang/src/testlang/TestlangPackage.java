/**
 */
package testlang;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	 * The meta object id for the '{@link testlang.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.TestCaseImpl
	 * @see testlang.impl.TestlangPackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 0;

	/**
	 * The feature id for the '<em><b>System Under Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__SYSTEM_UNDER_TEST = 0;

	/**
	 * The feature id for the '<em><b>Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TESTS = 1;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link testlang.impl.TestImpl <em>Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.TestImpl
	 * @see testlang.impl.TestlangPackageImpl#getTest()
	 * @generated
	 */
	int TEST = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__NAME = 0;

	/**
	 * The feature id for the '<em><b>Activity Under Test</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__ACTIVITY_UNDER_TEST = 1;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__ASSERTIONS = 2;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TEST_CASE = 3;

	/**
	 * The number of structural features of the '<em>Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_FEATURE_COUNT = 4;

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
	 * The feature id for the '<em><b>Test</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_UNDER_TEST__TEST = 2;

	/**
	 * The number of structural features of the '<em>Activity Under Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_UNDER_TEST_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link testlang.impl.AssertionImpl <em>Assertion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.impl.AssertionImpl
	 * @see testlang.impl.TestlangPackageImpl#getAssertion()
	 * @generated
	 */
	int ASSERTION = 3;

	/**
	 * The feature id for the '<em><b>Ocl Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__OCL_EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__OPERATOR = 1;

	/**
	 * The feature id for the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__NODE = 2;

	/**
	 * The number of structural features of the '<em>Assertion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link testlang.TempOperator <em>Temp Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see testlang.TempOperator
	 * @see testlang.impl.TestlangPackageImpl#getTempOperator()
	 * @generated
	 */
	int TEMP_OPERATOR = 4;


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
	 * Returns the meta object for the reference '{@link testlang.TestCase#getSystemUnderTest <em>System Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System Under Test</em>'.
	 * @see testlang.TestCase#getSystemUnderTest()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_SystemUnderTest();

	/**
	 * Returns the meta object for the containment reference list '{@link testlang.TestCase#getTests <em>Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tests</em>'.
	 * @see testlang.TestCase#getTests()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Tests();

	/**
	 * Returns the meta object for class '{@link testlang.Test <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test</em>'.
	 * @see testlang.Test
	 * @generated
	 */
	EClass getTest();

	/**
	 * Returns the meta object for the attribute '{@link testlang.Test#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see testlang.Test#getName()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_Name();

	/**
	 * Returns the meta object for the containment reference '{@link testlang.Test#getActivityUnderTest <em>Activity Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity Under Test</em>'.
	 * @see testlang.Test#getActivityUnderTest()
	 * @see #getTest()
	 * @generated
	 */
	EReference getTest_ActivityUnderTest();

	/**
	 * Returns the meta object for the containment reference list '{@link testlang.Test#getAssertions <em>Assertions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assertions</em>'.
	 * @see testlang.Test#getAssertions()
	 * @see #getTest()
	 * @generated
	 */
	EReference getTest_Assertions();

	/**
	 * Returns the meta object for the container reference '{@link testlang.Test#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Test Case</em>'.
	 * @see testlang.Test#getTestCase()
	 * @see #getTest()
	 * @generated
	 */
	EReference getTest_TestCase();

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
	 * Returns the meta object for the container reference '{@link testlang.ActivityUnderTest#getTest <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Test</em>'.
	 * @see testlang.ActivityUnderTest#getTest()
	 * @see #getActivityUnderTest()
	 * @generated
	 */
	EReference getActivityUnderTest_Test();

	/**
	 * Returns the meta object for class '{@link testlang.Assertion <em>Assertion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assertion</em>'.
	 * @see testlang.Assertion
	 * @generated
	 */
	EClass getAssertion();

	/**
	 * Returns the meta object for the attribute '{@link testlang.Assertion#getOclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ocl Expression</em>'.
	 * @see testlang.Assertion#getOclExpression()
	 * @see #getAssertion()
	 * @generated
	 */
	EAttribute getAssertion_OclExpression();

	/**
	 * Returns the meta object for the attribute '{@link testlang.Assertion#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see testlang.Assertion#getOperator()
	 * @see #getAssertion()
	 * @generated
	 */
	EAttribute getAssertion_Operator();

	/**
	 * Returns the meta object for the reference '{@link testlang.Assertion#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node</em>'.
	 * @see testlang.Assertion#getNode()
	 * @see #getAssertion()
	 * @generated
	 */
	EReference getAssertion_Node();

	/**
	 * Returns the meta object for enum '{@link testlang.TempOperator <em>Temp Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temp Operator</em>'.
	 * @see testlang.TempOperator
	 * @generated
	 */
	EEnum getTempOperator();

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
		 * The meta object literal for the '{@link testlang.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.TestCaseImpl
		 * @see testlang.impl.TestlangPackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>System Under Test</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__SYSTEM_UNDER_TEST = eINSTANCE.getTestCase_SystemUnderTest();

		/**
		 * The meta object literal for the '<em><b>Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__TESTS = eINSTANCE.getTestCase_Tests();

		/**
		 * The meta object literal for the '{@link testlang.impl.TestImpl <em>Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.TestImpl
		 * @see testlang.impl.TestlangPackageImpl#getTest()
		 * @generated
		 */
		EClass TEST = eINSTANCE.getTest();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__NAME = eINSTANCE.getTest_Name();

		/**
		 * The meta object literal for the '<em><b>Activity Under Test</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST__ACTIVITY_UNDER_TEST = eINSTANCE.getTest_ActivityUnderTest();

		/**
		 * The meta object literal for the '<em><b>Assertions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST__ASSERTIONS = eINSTANCE.getTest_Assertions();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST__TEST_CASE = eINSTANCE.getTest_TestCase();

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

		/**
		 * The meta object literal for the '<em><b>Test</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_UNDER_TEST__TEST = eINSTANCE.getActivityUnderTest_Test();

		/**
		 * The meta object literal for the '{@link testlang.impl.AssertionImpl <em>Assertion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.impl.AssertionImpl
		 * @see testlang.impl.TestlangPackageImpl#getAssertion()
		 * @generated
		 */
		EClass ASSERTION = eINSTANCE.getAssertion();

		/**
		 * The meta object literal for the '<em><b>Ocl Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSERTION__OCL_EXPRESSION = eINSTANCE.getAssertion_OclExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSERTION__OPERATOR = eINSTANCE.getAssertion_Operator();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSERTION__NODE = eINSTANCE.getAssertion_Node();

		/**
		 * The meta object literal for the '{@link testlang.TempOperator <em>Temp Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see testlang.TempOperator
		 * @see testlang.impl.TestlangPackageImpl#getTempOperator()
		 * @generated
		 */
		EEnum TEMP_OPERATOR = eINSTANCE.getTempOperator();

	}

} //TestlangPackage
