/**
 */
package testlang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Suite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testlang.TestSuite#getSystemUnderTest <em>System Under Test</em>}</li>
 *   <li>{@link testlang.TestSuite#getTests <em>Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @see testlang.TestlangPackage#getTestSuite()
 * @model
 * @generated
 */
public interface TestSuite extends EObject {
	/**
	 * Returns the value of the '<em><b>System Under Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Under Test</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Under Test</em>' reference.
	 * @see #setSystemUnderTest(Model)
	 * @see testlang.TestlangPackage#getTestSuite_SystemUnderTest()
	 * @model required="true"
	 * @generated
	 */
	Model getSystemUnderTest();

	/**
	 * Sets the value of the '{@link testlang.TestSuite#getSystemUnderTest <em>System Under Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Under Test</em>' reference.
	 * @see #getSystemUnderTest()
	 * @generated
	 */
	void setSystemUnderTest(Model value);

	/**
	 * Returns the value of the '<em><b>Tests</b></em>' containment reference list.
	 * The list contents are of type {@link testlang.TestCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tests</em>' containment reference list.
	 * @see testlang.TestlangPackage#getTestSuite_Tests()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCase> getTests();

} // TestSuite
