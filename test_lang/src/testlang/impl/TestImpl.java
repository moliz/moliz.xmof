/**
 */
package testlang.impl;

import java.awt.print.Paper;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.papyrus.examples.PapyrusModelExecutor;

import testlang.ActivityUnderTest;
import testlang.Assertion;
import testlang.Test;
import testlang.TestCase;
import testlang.TestlangPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link testlang.impl.TestImpl#getName <em>Name</em>}</li>
 *   <li>{@link testlang.impl.TestImpl#getActivityUnderTest <em>Activity Under Test</em>}</li>
 *   <li>{@link testlang.impl.TestImpl#getAssertions <em>Assertions</em>}</li>
 *   <li>{@link testlang.impl.TestImpl#getTestCase <em>Test Case</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestImpl extends EObjectImpl implements Test {
	
	
	private PapyrusModelExecutor executor = new PapyrusModelExecutor();
	
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
	 * The cached value of the '{@link #getAssertions() <em>Assertions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssertions()
	 * @generated
	 * @ordered
	 */
	protected EList<Assertion> assertions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestlangPackage.Literals.TEST;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.TEST__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestlangPackage.TEST__ACTIVITY_UNDER_TEST, oldActivityUnderTest, newActivityUnderTest);
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
				msgs = ((InternalEObject)activityUnderTest).eInverseRemove(this, TestlangPackage.ACTIVITY_UNDER_TEST__TEST, ActivityUnderTest.class, msgs);
			if (newActivityUnderTest != null)
				msgs = ((InternalEObject)newActivityUnderTest).eInverseAdd(this, TestlangPackage.ACTIVITY_UNDER_TEST__TEST, ActivityUnderTest.class, msgs);
			msgs = basicSetActivityUnderTest(newActivityUnderTest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.TEST__ACTIVITY_UNDER_TEST, newActivityUnderTest, newActivityUnderTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Assertion> getAssertions() {
		if (assertions == null) {
			assertions = new EObjectContainmentEList<Assertion>(Assertion.class, this, TestlangPackage.TEST__ASSERTIONS);
		}
		return assertions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCase getTestCase() {
		if (eContainerFeatureID() != TestlangPackage.TEST__TEST_CASE) return null;
		return (TestCase)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTestCase(TestCase newTestCase, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTestCase, TestlangPackage.TEST__TEST_CASE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestCase(TestCase newTestCase) {
		if (newTestCase != eInternalContainer() || (eContainerFeatureID() != TestlangPackage.TEST__TEST_CASE && newTestCase != null)) {
			if (EcoreUtil.isAncestor(this, newTestCase))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTestCase != null)
				msgs = ((InternalEObject)newTestCase).eInverseAdd(this, TestlangPackage.TEST_CASE__TESTS, TestCase.class, msgs);
			msgs = basicSetTestCase(newTestCase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.TEST__TEST_CASE, newTestCase, newTestCase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean run() {
		String activity = getActivityUnderTest().getActivity().getName();
		executor.executeActivity(activity);
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				if (activityUnderTest != null)
					msgs = ((InternalEObject)activityUnderTest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TestlangPackage.TEST__ACTIVITY_UNDER_TEST, null, msgs);
				return basicSetActivityUnderTest((ActivityUnderTest)otherEnd, msgs);
			case TestlangPackage.TEST__TEST_CASE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTestCase((TestCase)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				return basicSetActivityUnderTest(null, msgs);
			case TestlangPackage.TEST__ASSERTIONS:
				return ((InternalEList<?>)getAssertions()).basicRemove(otherEnd, msgs);
			case TestlangPackage.TEST__TEST_CASE:
				return basicSetTestCase(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case TestlangPackage.TEST__TEST_CASE:
				return eInternalContainer().eInverseRemove(this, TestlangPackage.TEST_CASE__TESTS, TestCase.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestlangPackage.TEST__NAME:
				return getName();
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				return getActivityUnderTest();
			case TestlangPackage.TEST__ASSERTIONS:
				return getAssertions();
			case TestlangPackage.TEST__TEST_CASE:
				return getTestCase();
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
			case TestlangPackage.TEST__NAME:
				setName((String)newValue);
				return;
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				setActivityUnderTest((ActivityUnderTest)newValue);
				return;
			case TestlangPackage.TEST__ASSERTIONS:
				getAssertions().clear();
				getAssertions().addAll((Collection<? extends Assertion>)newValue);
				return;
			case TestlangPackage.TEST__TEST_CASE:
				setTestCase((TestCase)newValue);
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
			case TestlangPackage.TEST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				setActivityUnderTest((ActivityUnderTest)null);
				return;
			case TestlangPackage.TEST__ASSERTIONS:
				getAssertions().clear();
				return;
			case TestlangPackage.TEST__TEST_CASE:
				setTestCase((TestCase)null);
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
			case TestlangPackage.TEST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestlangPackage.TEST__ACTIVITY_UNDER_TEST:
				return activityUnderTest != null;
			case TestlangPackage.TEST__ASSERTIONS:
				return assertions != null && !assertions.isEmpty();
			case TestlangPackage.TEST__TEST_CASE:
				return getTestCase() != null;
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

} //TestImpl
