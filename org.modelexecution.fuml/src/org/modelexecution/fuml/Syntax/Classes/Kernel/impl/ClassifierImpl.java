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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier;
import org.modelexecution.fuml.Syntax.Classes.Kernel.ElementImport;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Feature;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization;
import org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage;
import org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Namespace;
import org.modelexecution.fuml.Syntax.Classes.Kernel.PackageImport;
import org.modelexecution.fuml.Syntax.Classes.Kernel.PackageableElement;
import org.modelexecution.fuml.Syntax.Classes.Kernel.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getElementImport <em>Element Import</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getPackageImport <em>Package Import</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getImportedMember <em>Imported Member</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getOwnedMember <em>Owned Member</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getInheritedMember <em>Inherited Member</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getGeneral <em>General</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.impl.ClassifierImpl#getIsFinalSpecialization <em>Is Final Specialization</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassifierImpl extends TypeImpl implements Classifier {
	/**
	 * The cached value of the '{@link #getElementImport() <em>Element Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementImport()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementImport> elementImport;

	/**
	 * The cached value of the '{@link #getPackageImport() <em>Package Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageImport()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageImport> packageImport;

	/**
	 * The default value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_ABSTRACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected Object isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGeneralization() <em>Generalization</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralization()
	 * @generated
	 * @ordered
	 */
	protected EList<Generalization> generalization;

	/**
	 * The default value of the '{@link #getIsFinalSpecialization() <em>Is Final Specialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsFinalSpecialization()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_FINAL_SPECIALIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsFinalSpecialization() <em>Is Final Specialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsFinalSpecialization()
	 * @generated
	 * @ordered
	 */
	protected Object isFinalSpecialization = IS_FINAL_SPECIALIZATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KernelPackage.Literals.CLASSIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getMember() {
		// TODO: implement this method to return the 'Member' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementImport> getElementImport() {
		if (elementImport == null) {
			elementImport = new EObjectContainmentWithInverseEList<ElementImport>(ElementImport.class, this, KernelPackage.CLASSIFIER__ELEMENT_IMPORT, KernelPackage.ELEMENT_IMPORT__IMPORTING_NAMESPACE);
		}
		return elementImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageImport> getPackageImport() {
		if (packageImport == null) {
			packageImport = new EObjectContainmentWithInverseEList<PackageImport>(PackageImport.class, this, KernelPackage.CLASSIFIER__PACKAGE_IMPORT, KernelPackage.PACKAGE_IMPORT__IMPORTING_NAMESPACE);
		}
		return packageImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getImportedMember() {
		// TODO: implement this method to return the 'Imported Member' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getOwnedMember() {
		// TODO: implement this method to return the 'Owned Member' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(Object newIsAbstract) {
		Object oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.CLASSIFIER__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Generalization> getGeneralization() {
		if (generalization == null) {
			generalization = new EObjectContainmentWithInverseEList<Generalization>(Generalization.class, this, KernelPackage.CLASSIFIER__GENERALIZATION, KernelPackage.GENERALIZATION__SPECIFIC);
		}
		return generalization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeature() {
		// TODO: implement this method to return the 'Feature' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getInheritedMember() {
		// TODO: implement this method to return the 'Inherited Member' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getAttribute() {
		// TODO: implement this method to return the 'Attribute' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getGeneral() {
		// TODO: implement this method to return the 'General' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsFinalSpecialization() {
		return isFinalSpecialization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsFinalSpecialization(Object newIsFinalSpecialization) {
		Object oldIsFinalSpecialization = isFinalSpecialization;
		isFinalSpecialization = newIsFinalSpecialization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KernelPackage.CLASSIFIER__IS_FINAL_SPECIALIZATION, oldIsFinalSpecialization, isFinalSpecialization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElementImport()).basicAdd(otherEnd, msgs);
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackageImport()).basicAdd(otherEnd, msgs);
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGeneralization()).basicAdd(otherEnd, msgs);
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
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				return ((InternalEList<?>)getElementImport()).basicRemove(otherEnd, msgs);
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				return ((InternalEList<?>)getPackageImport()).basicRemove(otherEnd, msgs);
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				return ((InternalEList<?>)getGeneralization()).basicRemove(otherEnd, msgs);
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
			case KernelPackage.CLASSIFIER__MEMBER:
				return getMember();
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				return getElementImport();
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				return getPackageImport();
			case KernelPackage.CLASSIFIER__IMPORTED_MEMBER:
				return getImportedMember();
			case KernelPackage.CLASSIFIER__OWNED_MEMBER:
				return getOwnedMember();
			case KernelPackage.CLASSIFIER__IS_ABSTRACT:
				return getIsAbstract();
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				return getGeneralization();
			case KernelPackage.CLASSIFIER__FEATURE:
				return getFeature();
			case KernelPackage.CLASSIFIER__INHERITED_MEMBER:
				return getInheritedMember();
			case KernelPackage.CLASSIFIER__ATTRIBUTE:
				return getAttribute();
			case KernelPackage.CLASSIFIER__GENERAL:
				return getGeneral();
			case KernelPackage.CLASSIFIER__IS_FINAL_SPECIALIZATION:
				return getIsFinalSpecialization();
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
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				getElementImport().clear();
				getElementImport().addAll((Collection<? extends ElementImport>)newValue);
				return;
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				getPackageImport().clear();
				getPackageImport().addAll((Collection<? extends PackageImport>)newValue);
				return;
			case KernelPackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract(newValue);
				return;
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				getGeneralization().addAll((Collection<? extends Generalization>)newValue);
				return;
			case KernelPackage.CLASSIFIER__GENERAL:
				getGeneral().clear();
				getGeneral().addAll((Collection<? extends Classifier>)newValue);
				return;
			case KernelPackage.CLASSIFIER__IS_FINAL_SPECIALIZATION:
				setIsFinalSpecialization(newValue);
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
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				getElementImport().clear();
				return;
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				getPackageImport().clear();
				return;
			case KernelPackage.CLASSIFIER__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				getGeneralization().clear();
				return;
			case KernelPackage.CLASSIFIER__GENERAL:
				getGeneral().clear();
				return;
			case KernelPackage.CLASSIFIER__IS_FINAL_SPECIALIZATION:
				setIsFinalSpecialization(IS_FINAL_SPECIALIZATION_EDEFAULT);
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
			case KernelPackage.CLASSIFIER__MEMBER:
				return !getMember().isEmpty();
			case KernelPackage.CLASSIFIER__ELEMENT_IMPORT:
				return elementImport != null && !elementImport.isEmpty();
			case KernelPackage.CLASSIFIER__PACKAGE_IMPORT:
				return packageImport != null && !packageImport.isEmpty();
			case KernelPackage.CLASSIFIER__IMPORTED_MEMBER:
				return !getImportedMember().isEmpty();
			case KernelPackage.CLASSIFIER__OWNED_MEMBER:
				return !getOwnedMember().isEmpty();
			case KernelPackage.CLASSIFIER__IS_ABSTRACT:
				return IS_ABSTRACT_EDEFAULT == null ? isAbstract != null : !IS_ABSTRACT_EDEFAULT.equals(isAbstract);
			case KernelPackage.CLASSIFIER__GENERALIZATION:
				return generalization != null && !generalization.isEmpty();
			case KernelPackage.CLASSIFIER__FEATURE:
				return !getFeature().isEmpty();
			case KernelPackage.CLASSIFIER__INHERITED_MEMBER:
				return !getInheritedMember().isEmpty();
			case KernelPackage.CLASSIFIER__ATTRIBUTE:
				return !getAttribute().isEmpty();
			case KernelPackage.CLASSIFIER__GENERAL:
				return !getGeneral().isEmpty();
			case KernelPackage.CLASSIFIER__IS_FINAL_SPECIALIZATION:
				return IS_FINAL_SPECIALIZATION_EDEFAULT == null ? isFinalSpecialization != null : !IS_FINAL_SPECIALIZATION_EDEFAULT.equals(isFinalSpecialization);
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
		if (baseClass == Namespace.class) {
			switch (derivedFeatureID) {
				case KernelPackage.CLASSIFIER__MEMBER: return KernelPackage.NAMESPACE__MEMBER;
				case KernelPackage.CLASSIFIER__ELEMENT_IMPORT: return KernelPackage.NAMESPACE__ELEMENT_IMPORT;
				case KernelPackage.CLASSIFIER__PACKAGE_IMPORT: return KernelPackage.NAMESPACE__PACKAGE_IMPORT;
				case KernelPackage.CLASSIFIER__IMPORTED_MEMBER: return KernelPackage.NAMESPACE__IMPORTED_MEMBER;
				case KernelPackage.CLASSIFIER__OWNED_MEMBER: return KernelPackage.NAMESPACE__OWNED_MEMBER;
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
		if (baseClass == Namespace.class) {
			switch (baseFeatureID) {
				case KernelPackage.NAMESPACE__MEMBER: return KernelPackage.CLASSIFIER__MEMBER;
				case KernelPackage.NAMESPACE__ELEMENT_IMPORT: return KernelPackage.CLASSIFIER__ELEMENT_IMPORT;
				case KernelPackage.NAMESPACE__PACKAGE_IMPORT: return KernelPackage.CLASSIFIER__PACKAGE_IMPORT;
				case KernelPackage.NAMESPACE__IMPORTED_MEMBER: return KernelPackage.CLASSIFIER__IMPORTED_MEMBER;
				case KernelPackage.NAMESPACE__OWNED_MEMBER: return KernelPackage.CLASSIFIER__OWNED_MEMBER;
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
		result.append(" (isAbstract: ");
		result.append(isAbstract);
		result.append(", isFinalSpecialization: ");
		result.append(isFinalSpecialization);
		result.append(')');
		return result.toString();
	}

} //ClassifierImpl
