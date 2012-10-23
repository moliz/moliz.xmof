/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

import org.modelexecution.xmof.Syntax.Classes.Kernel.impl.BehavioredEClassImpl;

import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.BehaviorImpl#getSpecification <em>Specification</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.BehaviorImpl#getIsReentrant <em>Is Reentrant</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.BehaviorImpl#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.BehaviorImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BehaviorImpl extends BehavioredEClassImpl implements Behavior {
	/**
	 * The cached value of the '{@link #getSpecification() <em>Specification</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecification()
	 * @generated
	 * @ordered
	 */
	protected BehavioredEOperation specification;

	/**
	 * The default value of the '{@link #getIsReentrant() <em>Is Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReentrant()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_REENTRANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsReentrant() <em>Is Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReentrant()
	 * @generated
	 * @ordered
	 */
	protected Object isReentrant = IS_REENTRANT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedParameter() <em>Owned Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<EParameter> ownedParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicBehaviorsPackage.Literals.BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredEOperation getSpecification() {
		if (specification != null && specification.eIsProxy()) {
			InternalEObject oldSpecification = (InternalEObject)specification;
			specification = (BehavioredEOperation)eResolveProxy(oldSpecification);
			if (specification != oldSpecification) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION, oldSpecification, specification));
			}
		}
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredEOperation basicGetSpecification() {
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpecification(BehavioredEOperation newSpecification, NotificationChain msgs) {
		BehavioredEOperation oldSpecification = specification;
		specification = newSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION, oldSpecification, newSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecification(BehavioredEOperation newSpecification) {
		if (newSpecification != specification) {
			NotificationChain msgs = null;
			if (specification != null)
				msgs = ((InternalEObject)specification).eInverseRemove(this, KernelPackage.BEHAVIORED_EOPERATION__METHOD, BehavioredEOperation.class, msgs);
			if (newSpecification != null)
				msgs = ((InternalEObject)newSpecification).eInverseAdd(this, KernelPackage.BEHAVIORED_EOPERATION__METHOD, BehavioredEOperation.class, msgs);
			msgs = basicSetSpecification(newSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION, newSpecification, newSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsReentrant() {
		return isReentrant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReentrant(Object newIsReentrant) {
		Object oldIsReentrant = isReentrant;
		isReentrant = newIsReentrant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicBehaviorsPackage.BEHAVIOR__IS_REENTRANT, oldIsReentrant, isReentrant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EParameter> getOwnedParameter() {
		if (ownedParameter == null) {
			ownedParameter = new EObjectContainmentEList<EParameter>(EParameter.class, this, BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER);
		}
		return ownedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredClassifier getContext() {
		BehavioredClassifier context = basicGetContext();
		return context != null && context.eIsProxy() ? (BehavioredClassifier)eResolveProxy((InternalEObject)context) : context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioredClassifier basicGetContext() {
		// TODO: implement this method to return the 'Context' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(BehavioredClassifier newContext) {
		// TODO: implement this method to set the 'Context' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				if (specification != null)
					msgs = ((InternalEObject)specification).eInverseRemove(this, KernelPackage.BEHAVIORED_EOPERATION__METHOD, BehavioredEOperation.class, msgs);
				return basicSetSpecification((BehavioredEOperation)otherEnd, msgs);
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
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				return basicSetSpecification(null, msgs);
			case BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER:
				return ((InternalEList<?>)getOwnedParameter()).basicRemove(otherEnd, msgs);
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
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				if (resolve) return getSpecification();
				return basicGetSpecification();
			case BasicBehaviorsPackage.BEHAVIOR__IS_REENTRANT:
				return getIsReentrant();
			case BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER:
				return getOwnedParameter();
			case BasicBehaviorsPackage.BEHAVIOR__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
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
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				setSpecification((BehavioredEOperation)newValue);
				return;
			case BasicBehaviorsPackage.BEHAVIOR__IS_REENTRANT:
				setIsReentrant(newValue);
				return;
			case BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER:
				getOwnedParameter().clear();
				getOwnedParameter().addAll((Collection<? extends EParameter>)newValue);
				return;
			case BasicBehaviorsPackage.BEHAVIOR__CONTEXT:
				setContext((BehavioredClassifier)newValue);
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
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				setSpecification((BehavioredEOperation)null);
				return;
			case BasicBehaviorsPackage.BEHAVIOR__IS_REENTRANT:
				setIsReentrant(IS_REENTRANT_EDEFAULT);
				return;
			case BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER:
				getOwnedParameter().clear();
				return;
			case BasicBehaviorsPackage.BEHAVIOR__CONTEXT:
				setContext((BehavioredClassifier)null);
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
			case BasicBehaviorsPackage.BEHAVIOR__SPECIFICATION:
				return specification != null;
			case BasicBehaviorsPackage.BEHAVIOR__IS_REENTRANT:
				return IS_REENTRANT_EDEFAULT == null ? isReentrant != null : !IS_REENTRANT_EDEFAULT.equals(isReentrant);
			case BasicBehaviorsPackage.BEHAVIOR__OWNED_PARAMETER:
				return ownedParameter != null && !ownedParameter.isEmpty();
			case BasicBehaviorsPackage.BEHAVIOR__CONTEXT:
				return basicGetContext() != null;
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
		result.append(" (isReentrant: ");
		result.append(isReentrant);
		result.append(')');
		return result.toString();
	}

} //BehaviorImpl
