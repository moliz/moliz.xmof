/**
 */
package testlang;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ExecutableNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Under Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testlang.ActivityUnderTest#getActivity <em>Activity</em>}</li>
 *   <li>{@link testlang.ActivityUnderTest#getExecute_until <em>Execute until</em>}</li>
 *   <li>{@link testlang.ActivityUnderTest#getTest <em>Test</em>}</li>
 * </ul>
 * </p>
 *
 * @see testlang.TestlangPackage#getActivityUnderTest()
 * @model
 * @generated
 */
public interface ActivityUnderTest extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see testlang.TestlangPackage#getActivityUnderTest_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link testlang.ActivityUnderTest#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

	/**
	 * Returns the value of the '<em><b>Execute until</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execute until</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execute until</em>' reference.
	 * @see #setExecute_until(ExecutableNode)
	 * @see testlang.TestlangPackage#getActivityUnderTest_Execute_until()
	 * @model
	 * @generated
	 */
	ExecutableNode getExecute_until();

	/**
	 * Sets the value of the '{@link testlang.ActivityUnderTest#getExecute_until <em>Execute until</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execute until</em>' reference.
	 * @see #getExecute_until()
	 * @generated
	 */
	void setExecute_until(ExecutableNode value);

	/**
	 * Returns the value of the '<em><b>Test</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link testlang.Test#getActivityUnderTest <em>Activity Under Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test</em>' container reference.
	 * @see #setTest(Test)
	 * @see testlang.TestlangPackage#getActivityUnderTest_Test()
	 * @see testlang.Test#getActivityUnderTest
	 * @model opposite="activityUnderTest" required="true" transient="false"
	 * @generated
	 */
	Test getTest();

	/**
	 * Sets the value of the '{@link testlang.ActivityUnderTest#getTest <em>Test</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test</em>' container reference.
	 * @see #getTest()
	 * @generated
	 */
	void setTest(Test value);

} // ActivityUnderTest
