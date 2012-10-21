/**
 */
package org.modelexecution.fuml.Syntax.Actions.BasicActions.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelexecution.fuml.Syntax.Actions.BasicActions.Action;
import org.modelexecution.fuml.Syntax.Actions.BasicActions.BasicActionsPackage;
import org.modelexecution.fuml.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.fuml.Syntax.Actions.BasicActions.OutputPin;

import org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.impl.ExecutableNodeImpl;

import org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.impl.ActionImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.impl.ActionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.impl.ActionImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Actions.BasicActions.impl.ActionImpl#getIsLocallyReentrant <em>Is Locally Reentrant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ActionImpl extends ExecutableNodeImpl implements Action {
	/**
	 * The default value of the '{@link #getIsLocallyReentrant() <em>Is Locally Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsLocallyReentrant()
	 * @generated
	 * @ordered
	 */
	protected static final Object IS_LOCALLY_REENTRANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsLocallyReentrant() <em>Is Locally Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsLocallyReentrant()
	 * @generated
	 * @ordered
	 */
	protected Object isLocallyReentrant = IS_LOCALLY_REENTRANT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicActionsPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OutputPin> getOutput() {
		// TODO: implement this method to return the 'Output' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getContext() {
		Classifier context = basicGetContext();
		return context != null && context.eIsProxy() ? (Classifier)eResolveProxy((InternalEObject)context) : context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetContext() {
		// TODO: implement this method to return the 'Context' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InputPin> getInput() {
		// TODO: implement this method to return the 'Input' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getIsLocallyReentrant() {
		return isLocallyReentrant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsLocallyReentrant(Object newIsLocallyReentrant) {
		Object oldIsLocallyReentrant = isLocallyReentrant;
		isLocallyReentrant = newIsLocallyReentrant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicActionsPackage.ACTION__IS_LOCALLY_REENTRANT, oldIsLocallyReentrant, isLocallyReentrant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicActionsPackage.ACTION__OUTPUT:
				return getOutput();
			case BasicActionsPackage.ACTION__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case BasicActionsPackage.ACTION__INPUT:
				return getInput();
			case BasicActionsPackage.ACTION__IS_LOCALLY_REENTRANT:
				return getIsLocallyReentrant();
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
			case BasicActionsPackage.ACTION__IS_LOCALLY_REENTRANT:
				setIsLocallyReentrant(newValue);
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
			case BasicActionsPackage.ACTION__IS_LOCALLY_REENTRANT:
				setIsLocallyReentrant(IS_LOCALLY_REENTRANT_EDEFAULT);
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
			case BasicActionsPackage.ACTION__OUTPUT:
				return !getOutput().isEmpty();
			case BasicActionsPackage.ACTION__CONTEXT:
				return basicGetContext() != null;
			case BasicActionsPackage.ACTION__INPUT:
				return !getInput().isEmpty();
			case BasicActionsPackage.ACTION__IS_LOCALLY_REENTRANT:
				return IS_LOCALLY_REENTRANT_EDEFAULT == null ? isLocallyReentrant != null : !IS_LOCALLY_REENTRANT_EDEFAULT.equals(isLocallyReentrant);
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
		result.append(" (isLocallyReentrant: ");
		result.append(isLocallyReentrant);
		result.append(')');
		return result.toString();
	}

} //ActionImpl
