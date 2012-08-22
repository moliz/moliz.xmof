/**
 */
package testlang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testlang.TestCase#getSystemUnderTest <em>System Under Test</em>}</li>
 *   <li>{@link testlang.TestCase#getTests <em>Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @see testlang.TestlangPackage#getTestCase()
 * @model
 * @generated
 */
public interface TestCase extends EObject {
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
	 * @see testlang.TestlangPackage#getTestCase_SystemUnderTest()
	 * @model required="true"
	 * @generated
	 */
	Model getSystemUnderTest();

	/**
	 * Sets the value of the '{@link testlang.TestCase#getSystemUnderTest <em>System Under Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Under Test</em>' reference.
	 * @see #getSystemUnderTest()
	 * @generated
	 */
	void setSystemUnderTest(Model value);

	/**
	 * Returns the value of the '<em><b>Tests</b></em>' containment reference list.
	 * The list contents are of type {@link testlang.Test}.
	 * It is bidirectional and its opposite is '{@link testlang.Test#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tests</em>' containment reference list.
	 * @see testlang.TestlangPackage#getTestCase_Tests()
	 * @see testlang.Test#getTestCase
	 * @model opposite="testCase" containment="true"
	 * @generated
	 */
	EList<Test> getTests();

} // TestCase
