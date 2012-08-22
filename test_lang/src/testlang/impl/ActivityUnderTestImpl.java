/**
 */
package testlang.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ExecutableNode;

import testlang.ActivityUnderTest;
import testlang.Test;
import testlang.TestlangPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Under Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link testlang.impl.ActivityUnderTestImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link testlang.impl.ActivityUnderTestImpl#getExecute_until <em>Execute until</em>}</li>
 *   <li>{@link testlang.impl.ActivityUnderTestImpl#getTest <em>Test</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityUnderTestImpl extends EObjectImpl implements ActivityUnderTest {
	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity;

	/**
	 * The cached value of the '{@link #getExecute_until() <em>Execute until</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecute_until()
	 * @generated
	 * @ordered
	 */
	protected ExecutableNode execute_until;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityUnderTestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestlangPackage.Literals.ACTIVITY_UNDER_TEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getActivity() {
		if (activity != null && activity.eIsProxy()) {
			InternalEObject oldActivity = (InternalEObject)activity;
			activity = (Activity)eResolveProxy(oldActivity);
			if (activity != oldActivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY, oldActivity, activity));
			}
		}
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(Activity newActivity) {
		Activity oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY, oldActivity, activity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutableNode getExecute_until() {
		if (execute_until != null && execute_until.eIsProxy()) {
			InternalEObject oldExecute_until = (InternalEObject)execute_until;
			execute_until = (ExecutableNode)eResolveProxy(oldExecute_until);
			if (execute_until != oldExecute_until) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL, oldExecute_until, execute_until));
			}
		}
		return execute_until;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutableNode basicGetExecute_until() {
		return execute_until;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecute_until(ExecutableNode newExecute_until) {
		ExecutableNode oldExecute_until = execute_until;
		execute_until = newExecute_until;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL, oldExecute_until, execute_until));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Test getTest() {
		if (eContainerFeatureID() != TestlangPackage.ACTIVITY_UNDER_TEST__TEST) return null;
		return (Test)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTest(Test newTest, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTest, TestlangPackage.ACTIVITY_UNDER_TEST__TEST, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTest(Test newTest) {
		if (newTest != eInternalContainer() || (eContainerFeatureID() != TestlangPackage.ACTIVITY_UNDER_TEST__TEST && newTest != null)) {
			if (EcoreUtil.isAncestor(this, newTest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTest != null)
				msgs = ((InternalEObject)newTest).eInverseAdd(this, TestlangPackage.TEST__ACTIVITY_UNDER_TEST, Test.class, msgs);
			msgs = basicSetTest(newTest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestlangPackage.ACTIVITY_UNDER_TEST__TEST, newTest, newTest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTest((Test)otherEnd, msgs);
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				return basicSetTest(null, msgs);
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				return eInternalContainer().eInverseRemove(this, TestlangPackage.TEST__ACTIVITY_UNDER_TEST, Test.class, msgs);
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
			case TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				if (resolve) return getExecute_until();
				return basicGetExecute_until();
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				return getTest();
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				setExecute_until((ExecutableNode)newValue);
				return;
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				setTest((Test)newValue);
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				setActivity((Activity)null);
				return;
			case TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				setExecute_until((ExecutableNode)null);
				return;
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				setTest((Test)null);
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
			case TestlangPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				return activity != null;
			case TestlangPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				return execute_until != null;
			case TestlangPackage.ACTIVITY_UNDER_TEST__TEST:
				return getTest() != null;
		}
		return super.eIsSet(featureID);
	}

} //ActivityUnderTestImpl
