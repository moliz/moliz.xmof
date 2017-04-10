/**
 */
package modeldebuggerconfig.impl;

import modeldebuggerconfig.ActivityNodeStepDefinition;
import modeldebuggerconfig.ModeldebuggerconfigPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Node Step Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link modeldebuggerconfig.impl.ActivityNodeStepDefinitionImpl#getActivityNode <em>Activity Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityNodeStepDefinitionImpl extends StepDefinitionImpl implements ActivityNodeStepDefinition {
	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivityNode() <em>Activity Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityNode activityNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityNodeStepDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldebuggerconfigPackage.Literals.ACTIVITY_NODE_STEP_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode getActivityNode() {
		if (activityNode != null && activityNode.eIsProxy()) {
			InternalEObject oldActivityNode = (InternalEObject)activityNode;
			activityNode = (ActivityNode)eResolveProxy(oldActivityNode);
			if (activityNode != oldActivityNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE, oldActivityNode, activityNode));
			}
		}
		return activityNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNode basicGetActivityNode() {
		return activityNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityNode(ActivityNode newActivityNode) {
		ActivityNode oldActivityNode = activityNode;
		activityNode = newActivityNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE, oldActivityNode, activityNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__CONDITION:
				return getCondition();
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE:
				if (resolve) return getActivityNode();
				return basicGetActivityNode();
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
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__CONDITION:
				setCondition((String)newValue);
				return;
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE:
				setActivityNode((ActivityNode)newValue);
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
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__CONDITION:
				setCondition(CONDITION_EDEFAULT);
				return;
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE:
				setActivityNode((ActivityNode)null);
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
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__CONDITION:
				return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION__ACTIVITY_NODE:
				return activityNode != null;
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
		result.append(" (condition: ");
		result.append(condition);
		result.append(')');
		return result.toString();
	}

} //ActivityNodeStepDefinitionImpl
