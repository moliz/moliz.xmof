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
import org.modelexecution.fuml.Syntax.Classes.Kernel.StructuralFeature;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Type;
import org.modelexecution.fuml.Syntax.Classes.Kernel.TypedElement;
import org.modelexecution.fuml.Syntax.Classes.Kernel.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getUpper <em>Upper</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.StructuralFeatureImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StructuralFeatureImpl extends FeatureImpl implements StructuralFeature {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

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
	 * The default value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_READ_ONLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected Object isReadOnly = IS_READ_ONLY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.STRUCTURAL_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KernelPackage.STRUCTURAL_FEATURE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED, oldIsOrdered, isOrdered));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE, oldIsUnique, isUnique));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE, oldUpperValue, newUpperValue);
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
				msgs = ((InternalEObject)upperValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE, null, msgs);
			if (newUpperValue != null)
				msgs = ((InternalEObject)newUpperValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE, null, msgs);
			msgs = basicSetUpperValue(newUpperValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE, newUpperValue, newUpperValue));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE, oldLowerValue, newLowerValue);
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
				msgs = ((InternalEObject)lowerValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE, null, msgs);
			if (newLowerValue != null)
				msgs = ((InternalEObject)newLowerValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE, null, msgs);
			msgs = basicSetLowerValue(newLowerValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE, newLowerValue, newLowerValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsReadOnly() {
		return isReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(Object newIsReadOnly) {
		Object oldIsReadOnly = isReadOnly;
		isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.STRUCTURAL_FEATURE__IS_READ_ONLY, oldIsReadOnly, isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
				return basicSetUpperValue(null, msgs);
			case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
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
			case KernelPackage.STRUCTURAL_FEATURE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED:
				return getIsOrdered();
			case KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE:
				return getIsUnique();
			case KernelPackage.STRUCTURAL_FEATURE__UPPER:
				return getUpper();
			case KernelPackage.STRUCTURAL_FEATURE__LOWER:
				return getLower();
			case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
				return getUpperValue();
			case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
				return getLowerValue();
			case KernelPackage.STRUCTURAL_FEATURE__IS_READ_ONLY:
				return getIsReadOnly();
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
			case KernelPackage.STRUCTURAL_FEATURE__TYPE:
				setType((Type)newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED:
				setIsOrdered(newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE:
				setIsUnique(newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__UPPER:
				setUpper(newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__LOWER:
				setLower(newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
				setUpperValue((ValueSpecification)newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
				setLowerValue((ValueSpecification)newValue);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_READ_ONLY:
				setIsReadOnly(newValue);
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
			case KernelPackage.STRUCTURAL_FEATURE__TYPE:
				setType((Type)null);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED:
				setIsOrdered(IS_ORDERED_EDEFAULT);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__UPPER:
				setUpper(UPPER_EDEFAULT);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
				setUpperValue((ValueSpecification)null);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
				setLowerValue((ValueSpecification)null);
				return;
			case KernelPackage.STRUCTURAL_FEATURE__IS_READ_ONLY:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
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
			case KernelPackage.STRUCTURAL_FEATURE__TYPE:
				return type != null;
			case KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED:
				return IS_ORDERED_EDEFAULT == null ? isOrdered != null : !IS_ORDERED_EDEFAULT.equals(isOrdered);
			case KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE:
				return IS_UNIQUE_EDEFAULT == null ? isUnique != null : !IS_UNIQUE_EDEFAULT.equals(isUnique);
			case KernelPackage.STRUCTURAL_FEATURE__UPPER:
				return UPPER_EDEFAULT == null ? getUpper() != null : !UPPER_EDEFAULT.equals(getUpper());
			case KernelPackage.STRUCTURAL_FEATURE__LOWER:
				return LOWER_EDEFAULT == null ? getLower() != null : !LOWER_EDEFAULT.equals(getLower());
			case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE:
				return upperValue != null;
			case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE:
				return lowerValue != null;
			case KernelPackage.STRUCTURAL_FEATURE__IS_READ_ONLY:
				return IS_READ_ONLY_EDEFAULT == null ? isReadOnly != null : !IS_READ_ONLY_EDEFAULT.equals(isReadOnly);
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
		if (baseClass == TypedElement.class) {
			switch (derivedFeatureID) {
				case KernelPackage.STRUCTURAL_FEATURE__TYPE: return KernelPackage.TYPED_ELEMENT__TYPE;
				default: return -1;
			}
		}
		if (baseClass == MultiplicityElement.class) {
			switch (derivedFeatureID) {
				case KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED: return KernelPackage.MULTIPLICITY_ELEMENT__IS_ORDERED;
				case KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE: return KernelPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE;
				case KernelPackage.STRUCTURAL_FEATURE__UPPER: return KernelPackage.MULTIPLICITY_ELEMENT__UPPER;
				case KernelPackage.STRUCTURAL_FEATURE__LOWER: return KernelPackage.MULTIPLICITY_ELEMENT__LOWER;
				case KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE: return KernelPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE;
				case KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE: return KernelPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE;
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
		if (baseClass == TypedElement.class) {
			switch (baseFeatureID) {
				case KernelPackage.TYPED_ELEMENT__TYPE: return KernelPackage.STRUCTURAL_FEATURE__TYPE;
				default: return -1;
			}
		}
		if (baseClass == MultiplicityElement.class) {
			switch (baseFeatureID) {
				case KernelPackage.MULTIPLICITY_ELEMENT__IS_ORDERED: return KernelPackage.STRUCTURAL_FEATURE__IS_ORDERED;
				case KernelPackage.MULTIPLICITY_ELEMENT__IS_UNIQUE: return KernelPackage.STRUCTURAL_FEATURE__IS_UNIQUE;
				case KernelPackage.MULTIPLICITY_ELEMENT__UPPER: return KernelPackage.STRUCTURAL_FEATURE__UPPER;
				case KernelPackage.MULTIPLICITY_ELEMENT__LOWER: return KernelPackage.STRUCTURAL_FEATURE__LOWER;
				case KernelPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE: return KernelPackage.STRUCTURAL_FEATURE__UPPER_VALUE;
				case KernelPackage.MULTIPLICITY_ELEMENT__LOWER_VALUE: return KernelPackage.STRUCTURAL_FEATURE__LOWER_VALUE;
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
		result.append(", isReadOnly: ");
		result.append(isReadOnly);
		result.append(')');
		return result.toString();
	}

} //StructuralFeatureImpl
