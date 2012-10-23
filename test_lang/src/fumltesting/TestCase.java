/**
 */
package fumltesting;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fumltesting.TestCase#getName <em>Name</em>}</li>
 *   <li>{@link fumltesting.TestCase#getActivityUnderTest <em>Activity Under Test</em>}</li>
 * </ul>
 * </p>
 *
 * @see fumltesting.FumltestingPackage#getTestCase()
 * @model
 * @generated
 */
public interface TestCase extends EObject {
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
	 * @see fumltesting.FumltestingPackage#getTestCase_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link fumltesting.TestCase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Activity Under Test</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Under Test</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Under Test</em>' containment reference.
	 * @see #setActivityUnderTest(ActivityUnderTest)
	 * @see fumltesting.FumltestingPackage#getTestCase_ActivityUnderTest()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ActivityUnderTest getActivityUnderTest();

	/**
	 * Sets the value of the '{@link fumltesting.TestCase#getActivityUnderTest <em>Activity Under Test</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Under Test</em>' containment reference.
	 * @see #getActivityUnderTest()
	 * @generated
	 */
	void setActivityUnderTest(ActivityUnderTest value);

} // TestCase
