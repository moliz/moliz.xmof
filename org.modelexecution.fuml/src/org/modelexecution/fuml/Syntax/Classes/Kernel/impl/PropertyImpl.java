/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.modelexecution.fuml.Syntax.Classes.Kernel.AggregationKind;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Association;
import org.modelexecution.fuml.Syntax.Classes.Kernel.DataType;
import org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getIsDerivedUnion <em>Is Derived Union</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getOwningAssociation <em>Owning Association</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getDatatype <em>Datatype</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.PropertyImpl#getOpposite <em>Opposite</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyImpl extends StructuralFeatureImpl implements Property {
	/**
	 * The default value of the '{@link #getIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerived()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_DERIVED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerived()
	 * @generated
	 * @ordered
	 */
	protected Object isDerived = IS_DERIVED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsDerivedUnion() <em>Is Derived Union</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerivedUnion()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_DERIVED_UNION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsDerivedUnion() <em>Is Derived Union</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerivedUnion()
	 * @generated
	 * @ordered
	 */
	protected Object isDerivedUnion = IS_DERIVED_UNION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected static final AggregationKind AGGREGATION_EDEFAULT = AggregationKind.NONE;

	/**
	 * The cached value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected AggregationKind aggregation = AGGREGATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_COMPOSITE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAssociation() <em>Association</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociation()
	 * @generated
	 * @ordered
	 */
	protected Association association;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsDerived() {
		return isDerived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDerived(Object newIsDerived) {
		Object oldIsDerived = isDerived;
		isDerived = newIsDerived;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__IS_DERIVED, oldIsDerived, isDerived));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsDerivedUnion() {
		return isDerivedUnion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDerivedUnion(Object newIsDerivedUnion) {
		Object oldIsDerivedUnion = isDerivedUnion;
		isDerivedUnion = newIsDerivedUnion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__IS_DERIVED_UNION, oldIsDerivedUnion, isDerivedUnion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationKind getAggregation() {
		return aggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAggregation(AggregationKind newAggregation) {
		AggregationKind oldAggregation = aggregation;
		aggregation = newAggregation == null ? AGGREGATION_EDEFAULT : newAggregation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__AGGREGATION, oldAggregation, aggregation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsComposite() {
		// TODO: implement this method to return the 'Is Composite' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsComposite(Object newIsComposite) {
		// TODO: implement this method to set the 'Is Composite' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association getOwningAssociation() {
		if (eContainerFeatureID() != KernelPackage.PROPERTY__OWNING_ASSOCIATION) return null;
		return (Association)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningAssociation(Association newOwningAssociation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningAssociation, KernelPackage.PROPERTY__OWNING_ASSOCIATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwningAssociation(Association newOwningAssociation) {
		if (newOwningAssociation != eInternalContainer() || (eContainerFeatureID() != KernelPackage.PROPERTY__OWNING_ASSOCIATION && newOwningAssociation != null)) {
			if (EcoreUtil.isAncestor(this, newOwningAssociation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningAssociation != null)
				msgs = ((InternalEObject)newOwningAssociation).eInverseAdd(this, KernelPackage.ASSOCIATION__OWNED_END, Association.class, msgs);
			msgs = basicSetOwningAssociation(newOwningAssociation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__OWNING_ASSOCIATION, newOwningAssociation, newOwningAssociation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association getAssociation() {
		if (association != null && association.eIsProxy()) {
			InternalEObject oldAssociation = (InternalEObject)association;
			association = (Association)eResolveProxy(oldAssociation);
			if (association != oldAssociation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KernelPackage.PROPERTY__ASSOCIATION, oldAssociation, association));
			}
		}
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Association basicGetAssociation() {
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssociation(Association newAssociation, NotificationChain msgs) {
		Association oldAssociation = association;
		association = newAssociation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__ASSOCIATION, oldAssociation, newAssociation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociation(Association newAssociation) {
		if (newAssociation != association) {
			NotificationChain msgs = null;
			if (association != null)
				msgs = ((InternalEObject)association).eInverseRemove(this, KernelPackage.ASSOCIATION__MEMBER_END, Association.class, msgs);
			if (newAssociation != null)
				msgs = ((InternalEObject)newAssociation).eInverseAdd(this, KernelPackage.ASSOCIATION__MEMBER_END, Association.class, msgs);
			msgs = basicSetAssociation(newAssociation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__ASSOCIATION, newAssociation, newAssociation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getDatatype() {
		if (eContainerFeatureID() != KernelPackage.PROPERTY__DATATYPE) return null;
		return (DataType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatatype(DataType newDatatype, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDatatype, KernelPackage.PROPERTY__DATATYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatatype(DataType newDatatype) {
		if (newDatatype != eInternalContainer() || (eContainerFeatureID() != KernelPackage.PROPERTY__DATATYPE && newDatatype != null)) {
			if (EcoreUtil.isAncestor(this, newDatatype))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDatatype != null)
				msgs = ((InternalEObject)newDatatype).eInverseAdd(this, KernelPackage.DATA_TYPE__OWNED_ATTRIBUTE, DataType.class, msgs);
			msgs = basicSetDatatype(newDatatype, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__DATATYPE, newDatatype, newDatatype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.modelexecution.fuml.Syntax.Classes.Kernel.Class getClass_() {
		if (eContainerFeatureID() != KernelPackage.PROPERTY__CLASS) return null;
		return (org.modelexecution.fuml.Syntax.Classes.Kernel.Class)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class newClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClass, KernelPackage.PROPERTY__CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass_(org.modelexecution.fuml.Syntax.Classes.Kernel.Class newClass) {
		if (newClass != eInternalContainer() || (eContainerFeatureID() != KernelPackage.PROPERTY__CLASS && newClass != null)) {
			if (EcoreUtil.isAncestor(this, newClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClass != null)
				msgs = ((InternalEObject)newClass).eInverseAdd(this, KernelPackage.CLASS__OWNED_ATTRIBUTE, org.modelexecution.fuml.Syntax.Classes.Kernel.Class.class, msgs);
			msgs = basicSetClass_(newClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.PROPERTY__CLASS, newClass, newClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getOpposite() {
		Property opposite = basicGetOpposite();
		return opposite != null && opposite.eIsProxy() ? (Property)eResolveProxy((InternalEObject)opposite) : opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetOpposite() {
		// TODO: implement this method to return the 'Opposite' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpposite(Property newOpposite) {
		// TODO: implement this method to set the 'Opposite' reference
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
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningAssociation((Association)otherEnd, msgs);
			case KernelPackage.PROPERTY__ASSOCIATION:
				if (association != null)
					msgs = ((InternalEObject)association).eInverseRemove(this, KernelPackage.ASSOCIATION__MEMBER_END, Association.class, msgs);
				return basicSetAssociation((Association)otherEnd, msgs);
			case KernelPackage.PROPERTY__DATATYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDatatype((DataType)otherEnd, msgs);
			case KernelPackage.PROPERTY__CLASS:
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
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				return basicSetOwningAssociation(null, msgs);
			case KernelPackage.PROPERTY__ASSOCIATION:
				return basicSetAssociation(null, msgs);
			case KernelPackage.PROPERTY__DATATYPE:
				return basicSetDatatype(null, msgs);
			case KernelPackage.PROPERTY__CLASS:
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
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				return eInternalContainer().eInverseRemove(this, KernelPackage.ASSOCIATION__OWNED_END, Association.class, msgs);
			case KernelPackage.PROPERTY__DATATYPE:
				return eInternalContainer().eInverseRemove(this, KernelPackage.DATA_TYPE__OWNED_ATTRIBUTE, DataType.class, msgs);
			case KernelPackage.PROPERTY__CLASS:
				return eInternalContainer().eInverseRemove(this, KernelPackage.CLASS__OWNED_ATTRIBUTE, org.modelexecution.fuml.Syntax.Classes.Kernel.Class.class, msgs);
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
			case KernelPackage.PROPERTY__IS_DERIVED:
				return getIsDerived();
			case KernelPackage.PROPERTY__IS_DERIVED_UNION:
				return getIsDerivedUnion();
			case KernelPackage.PROPERTY__AGGREGATION:
				return getAggregation();
			case KernelPackage.PROPERTY__IS_COMPOSITE:
				return getIsComposite();
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				return getOwningAssociation();
			case KernelPackage.PROPERTY__ASSOCIATION:
				if (resolve) return getAssociation();
				return basicGetAssociation();
			case KernelPackage.PROPERTY__DATATYPE:
				return getDatatype();
			case KernelPackage.PROPERTY__CLASS:
				return getClass_();
			case KernelPackage.PROPERTY__OPPOSITE:
				if (resolve) return getOpposite();
				return basicGetOpposite();
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
			case KernelPackage.PROPERTY__IS_DERIVED:
				setIsDerived(newValue);
				return;
			case KernelPackage.PROPERTY__IS_DERIVED_UNION:
				setIsDerivedUnion(newValue);
				return;
			case KernelPackage.PROPERTY__AGGREGATION:
				setAggregation((AggregationKind)newValue);
				return;
			case KernelPackage.PROPERTY__IS_COMPOSITE:
				setIsComposite(newValue);
				return;
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				setOwningAssociation((Association)newValue);
				return;
			case KernelPackage.PROPERTY__ASSOCIATION:
				setAssociation((Association)newValue);
				return;
			case KernelPackage.PROPERTY__DATATYPE:
				setDatatype((DataType)newValue);
				return;
			case KernelPackage.PROPERTY__CLASS:
				setClass_((org.modelexecution.fuml.Syntax.Classes.Kernel.Class)newValue);
				return;
			case KernelPackage.PROPERTY__OPPOSITE:
				setOpposite((Property)newValue);
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
			case KernelPackage.PROPERTY__IS_DERIVED:
				setIsDerived(IS_DERIVED_EDEFAULT);
				return;
			case KernelPackage.PROPERTY__IS_DERIVED_UNION:
				setIsDerivedUnion(IS_DERIVED_UNION_EDEFAULT);
				return;
			case KernelPackage.PROPERTY__AGGREGATION:
				setAggregation(AGGREGATION_EDEFAULT);
				return;
			case KernelPackage.PROPERTY__IS_COMPOSITE:
				setIsComposite(IS_COMPOSITE_EDEFAULT);
				return;
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				setOwningAssociation((Association)null);
				return;
			case KernelPackage.PROPERTY__ASSOCIATION:
				setAssociation((Association)null);
				return;
			case KernelPackage.PROPERTY__DATATYPE:
				setDatatype((DataType)null);
				return;
			case KernelPackage.PROPERTY__CLASS:
				setClass_((org.modelexecution.fuml.Syntax.Classes.Kernel.Class)null);
				return;
			case KernelPackage.PROPERTY__OPPOSITE:
				setOpposite((Property)null);
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
			case KernelPackage.PROPERTY__IS_DERIVED:
				return IS_DERIVED_EDEFAULT == null ? isDerived != null : !IS_DERIVED_EDEFAULT.equals(isDerived);
			case KernelPackage.PROPERTY__IS_DERIVED_UNION:
				return IS_DERIVED_UNION_EDEFAULT == null ? isDerivedUnion != null : !IS_DERIVED_UNION_EDEFAULT.equals(isDerivedUnion);
			case KernelPackage.PROPERTY__AGGREGATION:
				return aggregation != AGGREGATION_EDEFAULT;
			case KernelPackage.PROPERTY__IS_COMPOSITE:
				return IS_COMPOSITE_EDEFAULT == null ? getIsComposite() != null : !IS_COMPOSITE_EDEFAULT.equals(getIsComposite());
			case KernelPackage.PROPERTY__OWNING_ASSOCIATION:
				return getOwningAssociation() != null;
			case KernelPackage.PROPERTY__ASSOCIATION:
				return association != null;
			case KernelPackage.PROPERTY__DATATYPE:
				return getDatatype() != null;
			case KernelPackage.PROPERTY__CLASS:
				return getClass_() != null;
			case KernelPackage.PROPERTY__OPPOSITE:
				return basicGetOpposite() != null;
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
		result.append(" (isDerived: ");
		result.append(isDerived);
		result.append(", isDerivedUnion: ");
		result.append(isDerivedUnion);
		result.append(", aggregation: ");
		result.append(aggregation);
		result.append(')');
		return result.toString();
	}

} //PropertyImpl
