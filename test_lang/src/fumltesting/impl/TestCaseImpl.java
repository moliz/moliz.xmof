/**
 */
package fumltesting.impl;

import fumltesting.ActivityUnderTest;
import fumltesting.FumltestingPackage;
import fumltesting.TestCase;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fumltesting.impl.TestCaseImpl#getName <em>Name</em>}</li>
 *   <li>{@link fumltesting.impl.TestCaseImpl#getActivityUnderTest <em>Activity Under Test</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestCaseImpl extends EObjectImpl implements TestCase {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivityUnderTest() <em>Activity Under Test</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityUnderTest()
	 * @generated
	 * @ordered
	 */
	protected ActivityUnderTest activityUnderTest;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FumltestingPackage.Literals.TEST_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FumltestingPackage.TEST_CASE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityUnderTest getActivityUnderTest() {
		return activityUnderTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivityUnderTest(ActivityUnderTest newActivityUnderTest, NotificationChain msgs) {
		ActivityUnderTest oldActivityUnderTest = activityUnderTest;
		activityUnderTest = newActivityUnderTest;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST, oldActivityUnderTest, newActivityUnderTest);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityUnderTest(ActivityUnderTest newActivityUnderTest) {
		if (newActivityUnderTest != activityUnderTest) {
			NotificationChain msgs = null;
			if (activityUnderTest != null)
				msgs = ((InternalEObject)activityUnderTest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST, null, msgs);
			if (newActivityUnderTest != null)
				msgs = ((InternalEObject)newActivityUnderTest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST, null, msgs);
			msgs = basicSetActivityUnderTest(newActivityUnderTest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST, newActivityUnderTest, newActivityUnderTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST:
				return basicSetActivityUnderTest(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FumltestingPackage.TEST_CASE__NAME:
				return getName();
			case FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST:
				return getActivityUnderTest();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FumltestingPackage.TEST_CASE__NAME:
				setName((String)newValue);
				return;
			case FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST:
				setActivityUnderTest((ActivityUnderTest)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FumltestingPackage.TEST_CASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST:
				setActivityUnderTest((ActivityUnderTest)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FumltestingPackage.TEST_CASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FumltestingPackage.TEST_CASE__ACTIVITY_UNDER_TEST:
				return activityUnderTest != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TestCaseImpl
