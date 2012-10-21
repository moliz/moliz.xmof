/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Operation;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getIsQuery <em>Is Query</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getRedefinedOperation <em>Redefined Operation</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.OperationImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationImpl extends BehavioralFeatureImpl implements Operation {
	/**
	 * The default value of the '{@link #getIsQuery() <em>Is Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsQuery()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_QUERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsQuery() <em>Is Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsQuery()
	 * @generated
	 * @ordered
	 */
	protected Object isQuery = IS_QUERY_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsOrdered() <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_ORDERED_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsUnique()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_UNIQUE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected static final Object LOWER_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected static final Object UPPER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRedefinedOperation() <em>Redefined Operation</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedOperation()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> redefinedOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsQuery() {
		return isQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsQuery(Object newIsQuery) {
		Object oldIsQuery = isQuery;
		isQuery = newIsQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.OPERATION__IS_QUERY, oldIsQuery, isQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsOrdered() {
		// TODO: implement this method to return the 'Is Ordered' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOrdered(Object newIsOrdered) {
		// TODO: implement this method to set the 'Is Ordered' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsUnique() {
		// TODO: implement this method to return the 'Is Unique' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUnique(Object newIsUnique) {
		// TODO: implement this method to set the 'Is Unique' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getLower() {
		// TODO: implement this method to return the 'Lower' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLower(Object newLower) {
		// TODO: implement this method to set the 'Lower' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getUpper() {
		// TODO: implement this method to return the 'Upper' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpper(Object newUpper) {
		// TODO: implement this method to set the 'Upper' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.modelexecution.fuml.Syntax.Classes.Kernel.Class getClass_() {
		if (eContainerFeatureID() != KernelPackage.OPERATION__CLASS) return null;
		return (org.modelexecution.fuml.Syntax.Classes.Kernel.Class)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class newClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClass, KernelPackage.OPERATION__CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class newClass) {
		if (newClass != eInternalContainer() || (eContainerFeatureID() != KernelPackage.OPERATION__CLASS && newClass != null)) {
			if (EcoreUtil.isAncestor(this, newClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClass != null)
				msgs = ((InternalEObject)newClass).eInverseAdd(this, KernelPackage.CLASS__OWNED_OPERATION, org.modelexecution.fuml.Syntax.Classes.Kernel.Class.class, msgs);
			msgs = basicSetClass_(newClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.OPERATION__CLASS, newClass, newClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getRedefinedOperation() {
		if (redefinedOperation == null) {
			redefinedOperation = new EObjectResolvingEList<Operation>(Operation.class, this, KernelPackage.OPERATION__REDEFINED_OPERATION);
		}
		return redefinedOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		Type type = basicGetType();
		return type != null && type.eIsProxy() ? (Type)eResolveProxy((InternalEObject)type) : type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		// TODO: implement this method to return the 'Type' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		// TODO: implement this method to set the 'Type' reference
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
			case KernelPackage.OPERATION__CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClass_((org.modelexecution.fuml.Syntax.Classes.Kernel.Class)otherEnd, msgs);
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
			case KernelPackage.OPERATION__CLASS:
				return basicSetClass_(null, msgs);
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
			case KernelPackage.OPERATION__CLASS:
				return eInternalContainer().eInverseRemove(this, KernelPackage.CLASS__OWNED_OPERATION, org.modelexecution.fuml.Syntax.Classes.Kernel.Class.class, msgs);
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
			case KernelPackage.OPERATION__IS_QUERY:
				return getIsQuery();
			case KernelPackage.OPERATION__IS_ORDERED:
				return getIsOrdered();
			case KernelPackage.OPERATION__IS_UNIQUE:
				return getIsUnique();
			case KernelPackage.OPERATION__LOWER:
				return getLower();
			case KernelPackage.OPERATION__UPPER:
				return getUpper();
			case KernelPackage.OPERATION__CLASS:
				return getClass_();
			case KernelPackage.OPERATION__REDEFINED_OPERATION:
				return getRedefinedOperation();
			case KernelPackage.OPERATION__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case KernelPackage.OPERATION__IS_QUERY:
				setIsQuery(newValue);
				return;
			case KernelPackage.OPERATION__IS_ORDERED:
				setIsOrdered(newValue);
				return;
			case KernelPackage.OPERATION__IS_UNIQUE:
				setIsUnique(newValue);
				return;
			case KernelPackage.OPERATION__LOWER:
				setLower(newValue);
				return;
			case KernelPackage.OPERATION__UPPER:
				setUpper(newValue);
				return;
			case KernelPackage.OPERATION__CLASS:
				setClass_((org.modelexecution.fuml.Syntax.Classes.Kernel.Class)newValue);
				return;
			case KernelPackage.OPERATION__REDEFINED_OPERATION:
				getRedefinedOperation().clear();
				getRedefinedOperation().addAll((Collection<? extends Operation>)newValue);
				return;
			case KernelPackage.OPERATION__TYPE:
				setType((Type)newValue);
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
			case KernelPackage.OPERATION__IS_QUERY:
				setIsQuery(IS_QUERY_EDEFAULT);
				return;
			case KernelPackage.OPERATION__IS_ORDERED:
				setIsOrdered(IS_ORDERED_EDEFAULT);
				return;
			case KernelPackage.OPERATION__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
				return;
			case KernelPackage.OPERATION__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case KernelPackage.OPERATION__UPPER:
				setUpper(UPPER_EDEFAULT);
				return;
			case KernelPackage.OPERATION__CLASS:
				setClass_((org.modelexecution.fuml.Syntax.Classes.Kernel.Class)null);
				return;
			case KernelPackage.OPERATION__REDEFINED_OPERATION:
				getRedefinedOperation().clear();
				return;
			case KernelPackage.OPERATION__TYPE:
				setType((Type)null);
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
			case KernelPackage.OPERATION__IS_QUERY:
				return IS_QUERY_EDEFAULT == null ? isQuery != null : !IS_QUERY_EDEFAULT.equals(isQuery);
			case KernelPackage.OPERATION__IS_ORDERED:
				return IS_ORDERED_EDEFAULT == null ? getIsOrdered() != null : !IS_ORDERED_EDEFAULT.equals(getIsOrdered());
			case KernelPackage.OPERATION__IS_UNIQUE:
				return IS_UNIQUE_EDEFAULT == null ? getIsUnique() != null : !IS_UNIQUE_EDEFAULT.equals(getIsUnique());
			case KernelPackage.OPERATION__LOWER:
				return LOWER_EDEFAULT == null ? getLower() != null : !LOWER_EDEFAULT.equals(getLower());
			case KernelPackage.OPERATION__UPPER:
				return UPPER_EDEFAULT == null ? getUpper() != null : !UPPER_EDEFAULT.equals(getUpper());
			case KernelPackage.OPERATION__CLASS:
				return getClass_() != null;
			case KernelPackage.OPERATION__REDEFINED_OPERATION:
				return redefinedOperation != null && !redefinedOperation.isEmpty();
			case KernelPackage.OPERATION__TYPE:
				return basicGetType() != null;
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
		result.append(" (isQuery: ");
		result.append(isQuery);
		result.append(')');
		return result.toString();
	}

} //OperationImpl
