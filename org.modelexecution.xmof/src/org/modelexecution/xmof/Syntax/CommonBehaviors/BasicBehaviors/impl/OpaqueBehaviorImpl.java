/**
 */
package org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Opaque Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.OpaqueBehaviorImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.impl.OpaqueBehaviorImpl#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OpaqueBehaviorImpl extends BehaviorImpl implements OpaqueBehavior {
	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected EList<Object> body;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected EList<Object> language;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OpaqueBehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicBehaviorsPackage.Literals.OPAQUE_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Object> getBody() {
		if (body == null) {
			body = new EDataTypeEList<Object>(Object.class, this, BasicBehaviorsPackage.OPAQUE_BEHAVIOR__BODY);
		}
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Object> getLanguage() {
		if (language == null) {
			language = new EDataTypeUniqueEList<Object>(Object.class, this, BasicBehaviorsPackage.OPAQUE_BEHAVIOR__LANGUAGE);
		}
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__BODY:
				return getBody();
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__LANGUAGE:
				return getLanguage();
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
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__BODY:
				getBody().clear();
				getBody().addAll((Collection<? extends Object>)newValue);
				return;
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__LANGUAGE:
				getLanguage().clear();
				getLanguage().addAll((Collection<? extends Object>)newValue);
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
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__BODY:
				getBody().clear();
				return;
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__LANGUAGE:
				getLanguage().clear();
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
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__BODY:
				return body != null && !body.isEmpty();
			case BasicBehaviorsPackage.OPAQUE_BEHAVIOR__LANGUAGE:
				return language != null && !language.isEmpty();
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
		result.append(" (body: ");
		result.append(body);
		result.append(", language: ");
		result.append(language);
		result.append(')');
		return result.toString();
	}

} //OpaqueBehaviorImpl
