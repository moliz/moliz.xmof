/**
 */
package testlang.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.Model;

import testlang.TestCase;
import testlang.TestSuite;
import testlang.TestlangPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Suite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link testlang.impl.TestSuiteImpl#getSystemUnderTest <em>System Under Test</em>}</li>
 *   <li>{@link testlang.impl.TestSuiteImpl#getTests <em>Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestSuiteImpl extends EObjectImpl implements TestSuite {
	/**
	 * The cached value of the '{@link #getSystemUnderTest() <em>System Under Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemUnderTest()
	 * @generated
	 * @ordered
	 */
	protected Model systemUnderTest;

	/**
	 * The cached value of the '{@link #getTests() <em>Tests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTests()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCase> tests;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestSuiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestlangPackage.Literals.TEST_SUITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getSystemUnderTest() {
		if (systemUnderTest != null && systemUnderTest.eIsProxy()) {
			InternalEObject oldSystemUnderTest = (InternalEObject)systemUnderTest;
			systemUnderTest = (Model)eResolveProxy(oldSystemUnderTest);
			if (systemUnderTest != oldSystemUnderTest) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST, oldSystemUnderTest, systemUnderTest));
			}
		}
		return systemUnderTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetSystemUnderTest() {
		return systemUnderTest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemUnderTest(Model newSystemUnderTest) {
		Model oldSystemUnderTest = systemUnderTest;
		systemUnderTest = newSystemUnderTest;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST, oldSystemUnderTest, systemUnderTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestCase> getTests() {
		if (tests == null) {
			tests = new EObjectContainmentEList<TestCase>(TestCase.class, this, TestlangPackage.TEST_SUITE__TESTS);
		}
		return tests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestlangPackage.TEST_SUITE__TESTS:
				return ((InternalEList<?>)getTests()).basicRemove(otherEnd, msgs);
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
			case TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST:
				if (resolve) return getSystemUnderTest();
				return basicGetSystemUnderTest();
			case TestlangPackage.TEST_SUITE__TESTS:
				return getTests();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST:
				setSystemUnderTest((Model)newValue);
				return;
			case TestlangPackage.TEST_SUITE__TESTS:
				getTests().clear();
				getTests().addAll((Collection<? extends TestCase>)newValue);
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
			case TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST:
				setSystemUnderTest((Model)null);
				return;
			case TestlangPackage.TEST_SUITE__TESTS:
				getTests().clear();
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
			case TestlangPackage.TEST_SUITE__SYSTEM_UNDER_TEST:
				return systemUnderTest != null;
			case TestlangPackage.TEST_SUITE__TESTS:
				return tests != null && !tests.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestSuiteImpl
