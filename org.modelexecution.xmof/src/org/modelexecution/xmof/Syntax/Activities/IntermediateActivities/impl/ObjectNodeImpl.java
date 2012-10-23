/**
 */
package org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#isMany <em>Many</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#getEType <em>EType</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.ObjectNodeImpl#getEGenericType <em>EGeneric Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ObjectNodeImpl extends ActivityNodeImpl implements ObjectNode {
	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #isMany() <em>Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMany()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANY_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntermediateActivitiesPackage.Literals.OBJECT_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdered(boolean newOrdered) {
		boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND, oldUpperBound, upperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMany() {
		// TODO: implement this method to return the 'Many' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequired() {
		// TODO: implement this method to return the 'Required' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getEType() {
		EClassifier eType = basicGetEType();
		return eType != null && eType.eIsProxy() ? (EClassifier)eResolveProxy((InternalEObject)eType) : eType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetEType() {
		// TODO: implement this method to return the 'EType' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEType(EClassifier newEType) {
		// TODO: implement this method to set the 'EType' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEType() {
		// TODO: implement this method to unset the 'EType' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEType() {
		// TODO: implement this method to return whether the 'EType' reference is set
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EGenericType getEGenericType() {
		// TODO: implement this method to return the 'EGeneric Type' containment reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEGenericType(EGenericType newEGenericType, NotificationChain msgs) {
		// TODO: implement this method to set the contained 'EGeneric Type' containment reference
		// -> this method is automatically invoked to keep the containment relationship in synch
		// -> do not modify other features
		// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEGenericType(EGenericType newEGenericType) {
		// TODO: implement this method to set the 'EGeneric Type' containment reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetEGenericType(NotificationChain msgs) {
		// TODO: implement this method to unset the contained 'EGeneric Type' containment reference
		// -> this method is automatically invoked to keep the containment relationship in synch
		// -> do not modify other features
		// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEGenericType() {
		// TODO: implement this method to unset the 'EGeneric Type' containment reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEGenericType() {
		// TODO: implement this method to return whether the 'EGeneric Type' containment reference is set
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				return basicUnsetEGenericType(msgs);
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
			case IntermediateActivitiesPackage.OBJECT_NODE__ORDERED:
				return isOrdered();
			case IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE:
				return isUnique();
			case IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND:
				return getLowerBound();
			case IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND:
				return getUpperBound();
			case IntermediateActivitiesPackage.OBJECT_NODE__MANY:
				return isMany();
			case IntermediateActivitiesPackage.OBJECT_NODE__REQUIRED:
				return isRequired();
			case IntermediateActivitiesPackage.OBJECT_NODE__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				return getEGenericType();
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
			case IntermediateActivitiesPackage.OBJECT_NODE__ORDERED:
				setOrdered((Boolean)newValue);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND:
				setLowerBound((Integer)newValue);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND:
				setUpperBound((Integer)newValue);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				setEGenericType((EGenericType)newValue);
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
			case IntermediateActivitiesPackage.OBJECT_NODE__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__ETYPE:
				unsetEType();
				return;
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				unsetEGenericType();
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
			case IntermediateActivitiesPackage.OBJECT_NODE__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__MANY:
				return isMany() != MANY_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__REQUIRED:
				return isRequired() != REQUIRED_EDEFAULT;
			case IntermediateActivitiesPackage.OBJECT_NODE__ETYPE:
				return isSetEType();
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				return isSetEGenericType();
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
		if (baseClass == ETypedElement.class) {
			switch (derivedFeatureID) {
				case IntermediateActivitiesPackage.OBJECT_NODE__ORDERED: return EcorePackage.ETYPED_ELEMENT__ORDERED;
				case IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE: return EcorePackage.ETYPED_ELEMENT__UNIQUE;
				case IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND: return EcorePackage.ETYPED_ELEMENT__LOWER_BOUND;
				case IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND: return EcorePackage.ETYPED_ELEMENT__UPPER_BOUND;
				case IntermediateActivitiesPackage.OBJECT_NODE__MANY: return EcorePackage.ETYPED_ELEMENT__MANY;
				case IntermediateActivitiesPackage.OBJECT_NODE__REQUIRED: return EcorePackage.ETYPED_ELEMENT__REQUIRED;
				case IntermediateActivitiesPackage.OBJECT_NODE__ETYPE: return EcorePackage.ETYPED_ELEMENT__ETYPE;
				case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE: return EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE;
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
		if (baseClass == ETypedElement.class) {
			switch (baseFeatureID) {
				case EcorePackage.ETYPED_ELEMENT__ORDERED: return IntermediateActivitiesPackage.OBJECT_NODE__ORDERED;
				case EcorePackage.ETYPED_ELEMENT__UNIQUE: return IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE;
				case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND: return IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND;
				case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND: return IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND;
				case EcorePackage.ETYPED_ELEMENT__MANY: return IntermediateActivitiesPackage.OBJECT_NODE__MANY;
				case EcorePackage.ETYPED_ELEMENT__REQUIRED: return IntermediateActivitiesPackage.OBJECT_NODE__REQUIRED;
				case EcorePackage.ETYPED_ELEMENT__ETYPE: return IntermediateActivitiesPackage.OBJECT_NODE__ETYPE;
				case EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE: return IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE;
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
		result.append(" (ordered: ");
		result.append(ordered);
		result.append(", unique: ");
		result.append(unique);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(')');
		return result.toString();
	}

} //ObjectNodeImpl
