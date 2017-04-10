/**
 */
package org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EGenericTypeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectNode;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Object Node</b></em>'. <!-- end-user-doc -->
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
public abstract class ObjectNodeImpl extends ActivityNodeImpl implements
		ObjectNode {
	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #isMany() <em>Many</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isMany()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANY_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #getEType() <em>EType</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEType()
	 * @generated NOT
	 * @ordered
	 */
	protected EClassifier eType;

	/**
	 * The cached value of the '{@link #getEGenericType()
	 * <em>EGeneric Type</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getEGenericType()
	 * @generated NOT
	 * @ordered
	 */
	protected EGenericType eGenericType;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IntermediateActivitiesPackage.Literals.OBJECT_NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdered(boolean newOrdered) {
		boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__LOWER_BOUND, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IntermediateActivitiesPackage.OBJECT_NODE__UPPER_BOUND, oldUpperBound, upperBound));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isMany() {
		int upper = getUpperBound();
		return upper > 1 || upper == UNBOUNDED_MULTIPLICITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isRequired() {
		int lower = getLowerBound();
		return lower >= 1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getEType() {
		EClassifier eType = basicGetEType();
		return eType != null && eType.eIsProxy() ? (EClassifier)eResolveProxy((InternalEObject)eType) : eType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EClassifier basicGetEType() {
		return eType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setEType(EClassifier newEType) {
		NotificationChain msgs = setEType(newEType, null);
		EGenericType newEGenericType = null;
		if (newEType != null) {
			newEGenericType = EcoreFactory.eINSTANCE.createEGenericType();
			newEGenericType.setEClassifier(eType);
		}
		msgs = setEGenericType(newEGenericType, msgs);
		if (msgs != null) {
			msgs.dispatch();
		}
	}

	public NotificationChain setEType(EClassifier newEType,
			NotificationChain msgs) {
		EClassifier oldEType = eType;
		eType = newEType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, EcorePackage.ETYPED_ELEMENT__ETYPE,
					oldEType, eType);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	public NotificationChain setEGenericType(EGenericType newEGenericType,
			NotificationChain msgs) {
		if (newEGenericType != eGenericType) {
			if (eGenericType != null)
				msgs = ((InternalEObject) eGenericType).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE,
						null, msgs);
			if (newEGenericType != null)
				msgs = ((InternalEObject) newEGenericType).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE,
						null, msgs);
			msgs = basicSetEGenericType(newEGenericType, msgs);
		} else if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE,
					newEGenericType, newEGenericType);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unsetEType() {
		setEType(null);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSetEType() {
		return eType != null && eGenericType.getETypeParameter() == null
				&& eGenericType.getETypeArguments().isEmpty();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EGenericType getEGenericType() {
		return eGenericType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public NotificationChain basicSetEGenericType(EGenericType newEGenericType,
			NotificationChain msgs) {
		EGenericType oldEGenericType = eGenericType;
		eGenericType = newEGenericType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE,
					oldEGenericType, newEGenericType);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		if (newEGenericType == null) {
			if (eType != null) {
				msgs = setEType(null, msgs);
			}
		} else {
			EClassifier newEType = ((EGenericTypeImpl) newEGenericType)
					.basicGetERawType();
			if (newEType != eType) {
				msgs = setEType(newEType, msgs);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setEGenericType(EGenericType newEGenericType) {
		NotificationChain msgs = setEGenericType(newEGenericType, null);
		if (msgs != null) {
			msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public NotificationChain basicUnsetEGenericType(NotificationChain msgs) {
		msgs = setEType(null, msgs);
		return basicSetEGenericType(null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unsetEGenericType() {
		setEGenericType(null);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSetEGenericType() {
		return eGenericType != null && !isSetEType();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IntermediateActivitiesPackage.OBJECT_NODE__EGENERIC_TYPE:
				return basicUnsetEGenericType(msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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

} // ObjectNodeImpl
