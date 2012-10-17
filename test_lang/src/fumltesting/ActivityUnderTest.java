/**
 */
package fumltesting;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Under Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fumltesting.ActivityUnderTest#getActivity <em>Activity</em>}</li>
 *   <li>{@link fumltesting.ActivityUnderTest#getExecute_until <em>Execute until</em>}</li>
 * </ul>
 * </p>
 *
 * @see fumltesting.FumltestingPackage#getActivityUnderTest()
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
	 * @see fumltesting.FumltestingPackage#getActivityUnderTest_Activity()
	 * @model required="true"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link fumltesting.ActivityUnderTest#getActivity <em>Activity</em>}' reference.
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
	 * @see #setExecute_until(ActivityNode)
	 * @see fumltesting.FumltestingPackage#getActivityUnderTest_Execute_until()
	 * @model
	 * @generated
	 */
	ActivityNode getExecute_until();

	/**
	 * Sets the value of the '{@link fumltesting.ActivityUnderTest#getExecute_until <em>Execute until</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execute until</em>' reference.
	 * @see #getExecute_until()
	 * @generated
	 */
	void setExecute_until(ActivityNode value);

} // ActivityUnderTest
