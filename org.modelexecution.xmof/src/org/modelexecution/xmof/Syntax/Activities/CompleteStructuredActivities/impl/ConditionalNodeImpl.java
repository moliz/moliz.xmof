/**
 */
package org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;

import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.Clause;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.CompleteStructuredActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ConditionalNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.ConditionalNodeImpl#getIsDeterminate <em>Is Determinate</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.ConditionalNodeImpl#getIsAssured <em>Is Assured</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.ConditionalNodeImpl#getClause <em>Clause</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.ConditionalNodeImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalNodeImpl extends StructuredActivityNodeImpl implements ConditionalNode {
	/**
	 * The default value of the '{@link #getIsDeterminate() <em>Is Determinate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDeterminate()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_DETERMINATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDeterminate() <em>Is Determinate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDeterminate()
	 * @generated
	 * @ordered
	 */
	protected Object isDeterminate = IS_DETERMINATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsAssured() <em>Is Assured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAssured()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_ASSURED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsAssured() <em>Is Assured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAssured()
	 * @generated
	 * @ordered
	 */
	protected Object isAssured = IS_ASSURED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClause() <em>Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClause()
	 * @generated
	 * @ordered
	 */
	protected EList<Clause> clause;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputPin> result;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompleteStructuredActivitiesPackage.Literals.CONDITIONAL_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsDeterminate() {
		return isDeterminate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDeterminate(Object newIsDeterminate) {
		Object oldIsDeterminate = isDeterminate;
		isDeterminate = newIsDeterminate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_DETERMINATE, oldIsDeterminate, isDeterminate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsAssured() {
		return isAssured;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAssured(Object newIsAssured) {
		Object oldIsAssured = isAssured;
		isAssured = newIsAssured;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_ASSURED, oldIsAssured, isAssured));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Clause> getClause() {
		if (clause == null) {
			clause = new EObjectContainmentEList<Clause>(Clause.class, this, CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE);
		}
		return clause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OutputPin> getResult() {
		if (result == null) {
			result = new EObjectContainmentEList<OutputPin>(OutputPin.class, this, CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE:
				return ((InternalEList<?>)getClause()).basicRemove(otherEnd, msgs);
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT:
				return ((InternalEList<?>)getResult()).basicRemove(otherEnd, msgs);
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
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_DETERMINATE:
				return getIsDeterminate();
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_ASSURED:
				return getIsAssured();
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE:
				return getClause();
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT:
				return getResult();
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
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_DETERMINATE:
				setIsDeterminate(newValue);
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_ASSURED:
				setIsAssured(newValue);
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE:
				getClause().clear();
				getClause().addAll((Collection<? extends Clause>)newValue);
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT:
				getResult().clear();
				getResult().addAll((Collection<? extends OutputPin>)newValue);
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
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_DETERMINATE:
				setIsDeterminate(IS_DETERMINATE_EDEFAULT);
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_ASSURED:
				setIsAssured(IS_ASSURED_EDEFAULT);
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE:
				getClause().clear();
				return;
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT:
				getResult().clear();
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
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_DETERMINATE:
				return IS_DETERMINATE_EDEFAULT == null ? isDeterminate != null : !IS_DETERMINATE_EDEFAULT.equals(isDeterminate);
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__IS_ASSURED:
				return IS_ASSURED_EDEFAULT == null ? isAssured != null : !IS_ASSURED_EDEFAULT.equals(isAssured);
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__CLAUSE:
				return clause != null && !clause.isEmpty();
			case CompleteStructuredActivitiesPackage.CONDITIONAL_NODE__RESULT:
				return result != null && !result.isEmpty();
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
		result.append(" (isDeterminate: ");
		result.append(isDeterminate);
		result.append(", isAssured: ");
		result.append(isAssured);
		result.append(')');
		return result.toString();
	}

} //ConditionalNodeImpl
