/**
 */
package testlang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testlang.Test#getName <em>Name</em>}</li>
 *   <li>{@link testlang.Test#getActivityUnderTest <em>Activity Under Test</em>}</li>
 *   <li>{@link testlang.Test#getAssertions <em>Assertions</em>}</li>
 *   <li>{@link testlang.Test#getTestCase <em>Test Case</em>}</li>
 * </ul>
 * </p>
 *
 * @see testlang.TestlangPackage#getTest()
 * @model
 * @generated
 */
public interface Test extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see testlang.TestlangPackage#getTest_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link testlang.Test#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Activity Under Test</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link testlang.ActivityUnderTest#getTest <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Under Test</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Under Test</em>' containment reference.
	 * @see #setActivityUnderTest(ActivityUnderTest)
	 * @see testlang.TestlangPackage#getTest_ActivityUnderTest()
	 * @see testlang.ActivityUnderTest#getTest
	 * @model opposite="test" containment="true" required="true"
	 * @generated
	 */
	ActivityUnderTest getActivityUnderTest();

	/**
	 * Sets the value of the '{@link testlang.Test#getActivityUnderTest <em>Activity Under Test</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Under Test</em>' containment reference.
	 * @see #getActivityUnderTest()
	 * @generated
	 */
	void setActivityUnderTest(ActivityUnderTest value);

	/**
	 * Returns the value of the '<em><b>Assertions</b></em>' containment reference list.
	 * The list contents are of type {@link testlang.Assertion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assertions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assertions</em>' containment reference list.
	 * @see testlang.TestlangPackage#getTest_Assertions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Assertion> getAssertions();

	/**
	 * Returns the value of the '<em><b>Test Case</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link testlang.TestCase#getTests <em>Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case</em>' container reference.
	 * @see #setTestCase(TestCase)
	 * @see testlang.TestlangPackage#getTest_TestCase()
	 * @see testlang.TestCase#getTests
	 * @model opposite="tests" required="true" transient="false"
	 * @generated
	 */
	TestCase getTestCase();

	/**
	 * Sets the value of the '{@link testlang.Test#getTestCase <em>Test Case</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Case</em>' container reference.
	 * @see #getTestCase()
	 * @generated
	 */
	void setTestCase(TestCase value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean run();

} // Test
