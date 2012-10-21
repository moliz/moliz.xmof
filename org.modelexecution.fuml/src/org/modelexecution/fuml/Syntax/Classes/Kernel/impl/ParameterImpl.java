/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage;
import org.modelexecution.fuml.Syntax.Classes.Kernel.MultiplicityElement;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Operation;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Parameter;
import org.modelexecution.fuml.Syntax.Classes.Kernel.ParameterDirectionKind;
import org.modelexecution.fuml.Syntax.Classes.Kernel.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ParameterImpl#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {
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
	 * The cached value of the '{@link #getIsOrdered() <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOrdered()
	 * @generated
	 * @ordered
	 */
	protected Object isOrdered = IS_ORDERED_EDEFAULT;

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
	 * The cached value of the '{@link #getIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsUnique()
	 * @generated
	 * @ordered
	 */
	protected Object isUnique = IS_UNIQUE_EDEFAULT;

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
	 * The default value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected static final Object LOWER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperValue()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification upperValue;

	/**
	 * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerValue()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification lowerValue;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterDirectionKind DIRECTION_EDEFAULT = ParameterDirectionKind.IN;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected ParameterDirectionKind direction = DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsOrdered() {
		return isOrdered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsOrdered(Object newIsOrdered) {
		Object oldIsOrdered = isOrdered;
		isOrdered = newIsOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__IS_ORDERED, oldIsOrdered, isOrdered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsUnique() {
		return isUnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUnique(Object newIsUnique) {
		Object oldIsUnique = isUnique;
		isUnique = newIsUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__IS_UNIQUE, oldIsUnique, isUnique));
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
	public ValueSpecification getUpperValue() {
		return upperValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUpperValue(ValueSpecification newUpperValue, NotificationChain msgs) {
		ValueSpecification oldUpperValue = upperValue;
		upperValue = newUpperValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__UPPER_VALUE, oldUpperValue, newUpperValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperValue(ValueSpecification newUpperValue) {
		if (newUpperValue != upperValue) {
			NotificationChain msgs = null;
			if (upperValue != null)
				msgs = ((InternalEObject)upperValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KernelPackage.PARAMETER__UPPER_VALUE, null, msgs);
			if (newUpperValue != null)
				msgs = ((InternalEObject)newUpperValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KernelPackage.PARAMETER__UPPER_VALUE, null, msgs);
			msgs = basicSetUpperValue(newUpperValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__UPPER_VALUE, newUpperValue, newUpperValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSpecification getLowerValue() {
		return lowerValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLowerValue(ValueSpecification newLowerValue, NotificationChain msgs) {
		ValueSpecification oldLowerValue = lowerValue;
		lowerValue = newLowerValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__LOWER_VALUE, oldLowerValue, newLowerValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerValue(ValueSpecification newLowerValue) {
		if (newLowerValue != lowerValue) {
			NotificationChain msgs = null;
			if (lowerValue != null)
				msgs = ((InternalEObject)lowerValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KernelPackage.PARAMETER__LOWER_VALUE, null, msgs);
			if (newLowerValue != null)
				msgs = ((InternalEObject)newLowerValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KernelPackage.PARAMETER__LOWER_VALUE, null, msgs);
			msgs = basicSetLowerValue(newLowerValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__LOWER_VALUE, newLowerValue, newLowerValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDirectionKind getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(ParameterDirectionKind newDirection) {
		ParameterDirectionKind oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {
		if (operation != null && operation.eIsProxy()) {
			InternalEObject oldOperation = (InternalEObject)operation;
			operation = (Operation)eResolveProxy(oldOperation);
			if (operation != oldOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KernelPackage.PARAMETER__OPERATION, oldOperation, operation));
			}
		}
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {
		Operation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PARAMETER__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KernelPackage.PARAMETER__UPPER_VALUE:
				return basicSetUpperValue(null, msgs);
			case KernelPackage.PARAMETER__LOWER_VALUE:
				return basicSetLowerValue(null, msgs);
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
			case KernelPackage.PARAMETER__IS_ORDERED:
				return getIsOrdered();
			case KernelPackage.PARAMETER__IS_UNIQUE:
				return getIsUnique();
			case KernelPackage.PARAMETER__UPPER:
				return getUpper();
			case KernelPackage.PARAMETER__LOWER:
				return getLower();
			case KernelPackage.PARAMETER__UPPER_VALUE:
				return getUpperValue();
			case KernelPackage.PARAMETER__LOWER_VALUE:
				return getLowerValue();
			case KernelPackage.PARAMETER__DIRECTION:
				return getDirection();
			case KernelPackage.PARAMETER__OPERATION:
				if (resolve) return getOperation();
				return basicGetOperation();
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
			case KernelPackage.PARAMETER__IS_ORDERED:
				setIsOrdered(newValue);
				return;
			case KernelPackage.PARAMETER__IS_UNIQUE:
				setIsUnique(newValue);
				return;
			case KernelPackage.PARAMETER__UPPER:
				setUpper(newValue);
				return;
			case KernelPackage.PARAMETER__LOWER:
				setLower(newValue);
				return;
			case KernelPackage.PARAMETER__UPPER_VALUE:
				setUpperValue((ValueSpecification)newValue);
				return;
			case KernelPackage.PARAMETER__LOWER_VALUE:
				setLowerValue((ValueSpecification)newValue);
				return;
			case KernelPackage.PARAMETER__DIRECTION:
				setDirection((ParameterDirectionKind)newValue);
				return;
			case KernelPackage.PARAMETER__OPERATION:
				setOperation((Operation)newValue);
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
			case KernelPackage.PARAMETER__IS_ORDERED:
				setIsOrdered(IS_ORDERED_EDEFAULT);
				return;
			case KernelPackage.PARAMETER__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
				return;
			case KernelPackage.PARAMETER__UPPER:
				setUpper(UPPER_EDEFAULT);
				return;
			case KernelPackage.PARAMETER__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case KernelPackage.PARAMETER__UPPER_VALUE:
				setUpperValue((ValueSpecification)null);
				return;
			case KernelPackage.PARAMETER__LOWER_VALUE:
				setLowerValue((ValueSpecification)null);
				return;
			case KernelPackage.PARAMETER__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case KernelPackage.PARAMETER__OPERATION:
				setOperation((Operation)null);
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
			case KernelPackage.PARAMETER__IS_ORDERED:
				return IS_ORDERED_EDEFAULT == null ? isOrdered != null : !IS_ORDERED_EDEFAULT.equals(isOrdered);
			case KernelPackage.PARAMETER__IS_UNIQUE:
				return IS_UNIQUE_EDEFAULT == null ? isUnique != null : !IS_UNIQUE_EDEFAULT.equals(isUnique);
			case KernelPackage.PARAMETER__UPPER:
				return UPPER_EDEFAULT == null ? getUpper() != null : !UPPER_EDEFAULT.equals(getUpper());
			case KernelPackage.PARAMETER__LOWER:
				return LOWER_EDEFAULT == null ? getLower() != null : !LOWER_EDEFAULT.equals(getLower());
			case KernelPackage.PARAMETER__UPPER_VALUE:
				return upperValue != null;
			case KernelPackage.PARAMETER__LOWER_VALUE:
				return lowerValue != null;
			case KernelPackage.PARAMETER__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case KernelPackage.PARAMETER__OPERATION:
				return operation != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MultiplicityElement.class) {
			switch (derivedFeatureID) {
				case KernelPackage.PARAMETER__IS_ORDERED: return KernelPackage.MULTIPLICITY_ELEMENT__IS_ORDERED;
				case KernelPackage.PARAMETER__IS_UNIQUE: return KernelPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE;
				case KernelPackage.PARAMETER__UPPER: return KernelPackage.MULTIPLICITY_ELEMENT__UPPER;
				case KernelPackage.PARAMETER__LOWER: return KernelPackage.MULTIPLICITY_ELEMENT__LOWER;
				case KernelPackage.PARAMETER__UPPER_VALUE: return KernelPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE;
				case KernelPackage.PARAMETER__LOWER_VALUE: return KernelPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MultiplicityElement.class) {
			switch (baseFeatureID) {
				case KernelPackage.MULTIPLICITY_ELEMENT__IS_ORDERED: return KernelPackage.PARAMETER__IS_ORDERED;
				case KernelPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE: return KernelPackage.PARAMETER__IS_UNIQUE;
				case KernelPackage.MULTIPLICITY_ELEMENT__UPPER: return KernelPackage.PARAMETER__UPPER;
				case KernelPackage.MULTIPLICITY_ELEMENT__LOWER: return KernelPackage.PARAMETER__LOWER;
				case KernelPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE: return KernelPackage.PARAMETER__UPPER_VALUE;
				case KernelPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE: return KernelPackage.PARAMETER__LOWER_VALUE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (isOrdered: ");
		result.append(isOrdered);
		result.append(", isUnique: ");
		result.append(isUnique);
		result.append(", direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //ParameterImpl
