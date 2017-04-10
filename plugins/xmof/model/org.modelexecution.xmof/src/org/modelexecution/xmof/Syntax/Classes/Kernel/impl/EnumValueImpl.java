/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.xmof.Syntax.Classes.Kernel.EEnumLiteralSpecification;
import org.modelexecution.xmof.Syntax.Classes.Kernel.EnumValue;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.impl.EnumValueImpl#getEEnumLiteralSpecification <em>EEnum Literal Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumValueImpl extends ValueSpecificationImpl implements EnumValue {
	/**
	 * The cached value of the '{@link #getEEnumLiteralSpecification() <em>EEnum Literal Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEEnumLiteralSpecification()
	 * @generated
	 * @ordered
	 */
	protected EEnumLiteralSpecification eEnumLiteralSpecification;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.ENUM_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnumLiteralSpecification getEEnumLiteralSpecification() {
		return eEnumLiteralSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEEnumLiteralSpecification(EEnumLiteralSpecification newEEnumLiteralSpecification, NotificationChain msgs) {
		EEnumLiteralSpecification oldEEnumLiteralSpecification = eEnumLiteralSpecification;
		eEnumLiteralSpecification = newEEnumLiteralSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION, oldEEnumLiteralSpecification, newEEnumLiteralSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEEnumLiteralSpecification(EEnumLiteralSpecification newEEnumLiteralSpecification) {
		if (newEEnumLiteralSpecification != eEnumLiteralSpecification) {
			NotificationChain msgs = null;
			if (eEnumLiteralSpecification != null)
				msgs = ((InternalEObject)eEnumLiteralSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION, null, msgs);
			if (newEEnumLiteralSpecification != null)
				msgs = ((InternalEObject)newEEnumLiteralSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION, null, msgs);
			msgs = basicSetEEnumLiteralSpecification(newEEnumLiteralSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION, newEEnumLiteralSpecification, newEEnumLiteralSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION:
				return basicSetEEnumLiteralSpecification(null, msgs);
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
			case KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION:
				return getEEnumLiteralSpecification();
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
			case KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION:
				setEEnumLiteralSpecification((EEnumLiteralSpecification)newValue);
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
			case KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION:
				setEEnumLiteralSpecification((EEnumLiteralSpecification)null);
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
			case KernelPackage.ENUM_VALUE__EENUM_LITERAL_SPECIFICATION:
				return eEnumLiteralSpecification != null;
		}
		return super.eIsSet(featureID);
	}

} //EnumValueImpl
