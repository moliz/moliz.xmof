/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.xmof.Syntax.Classes.Kernel.EEnumLiteralSpecification;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EEnum Literal Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.impl.EEnumLiteralSpecificationImpl#getEEnumLiteral <em>EEnum Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EEnumLiteralSpecificationImpl extends InstanceSpecificationImpl implements EEnumLiteralSpecification {
	/**
	 * The cached value of the '{@link #getEEnumLiteral() <em>EEnum Literal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEEnumLiteral()
	 * @generated
	 * @ordered
	 */
	protected EEnumLiteral eEnumLiteral;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EEnumLiteralSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.EENUM_LITERAL_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnumLiteral getEEnumLiteral() {
		if (eEnumLiteral != null && eEnumLiteral.eIsProxy()) {
			InternalEObject oldEEnumLiteral = (InternalEObject)eEnumLiteral;
			eEnumLiteral = (EEnumLiteral)eResolveProxy(oldEEnumLiteral);
			if (eEnumLiteral != oldEEnumLiteral) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL, oldEEnumLiteral, eEnumLiteral));
			}
		}
		return eEnumLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnumLiteral basicGetEEnumLiteral() {
		return eEnumLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEEnumLiteral(EEnumLiteral newEEnumLiteral) {
		EEnumLiteral oldEEnumLiteral = eEnumLiteral;
		eEnumLiteral = newEEnumLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL, oldEEnumLiteral, eEnumLiteral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL:
				if (resolve) return getEEnumLiteral();
				return basicGetEEnumLiteral();
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
			case KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL:
				setEEnumLiteral((EEnumLiteral)newValue);
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
			case KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL:
				setEEnumLiteral((EEnumLiteral)null);
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
			case KernelPackage.EENUM_LITERAL_SPECIFICATION__EENUM_LITERAL:
				return eEnumLiteral != null;
		}
		return super.eIsSet(featureID);
	}

} //EEnumLiteralSpecificationImpl
