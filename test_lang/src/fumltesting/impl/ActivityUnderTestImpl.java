/**
 */
package fumltesting.impl;

import fumltesting.ActivityUnderTest;
import fumltesting.FumltestingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Under Test</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fumltesting.impl.ActivityUnderTestImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link fumltesting.impl.ActivityUnderTestImpl#getExecute_until <em>Execute until</em>}</li>
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
	protected ActivityNode execute_until;

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
		return FumltestingPackage.Literals.ACTIVITY_UNDER_TEST;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY, oldActivity, activity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY, oldActivity, activity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode getExecute_until() {
		if (execute_until != null && execute_until.eIsProxy()) {
			InternalEObject oldExecute_until = (InternalEObject)execute_until;
			execute_until = (ActivityNode)eResolveProxy(oldExecute_until);
			if (execute_until != oldExecute_until) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL, oldExecute_until, execute_until));
			}
		}
		return execute_until;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode basicGetExecute_until() {
		return execute_until;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecute_until(ActivityNode newExecute_until) {
		ActivityNode oldExecute_until = execute_until;
		execute_until = newExecute_until;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL, oldExecute_until, execute_until));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				if (resolve) return getActivity();
				return basicGetActivity();
			case FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				if (resolve) return getExecute_until();
				return basicGetExecute_until();
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
			case FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				setExecute_until((ActivityNode)newValue);
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
			case FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				setActivity((Activity)null);
				return;
			case FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				setExecute_until((ActivityNode)null);
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
			case FumltestingPackage.ACTIVITY_UNDER_TEST__ACTIVITY:
				return activity != null;
			case FumltestingPackage.ACTIVITY_UNDER_TEST__EXECUTE_UNTIL:
				return execute_until != null;
		}
		return super.eIsSet(featureID);
	}

} //ActivityUnderTestImpl
