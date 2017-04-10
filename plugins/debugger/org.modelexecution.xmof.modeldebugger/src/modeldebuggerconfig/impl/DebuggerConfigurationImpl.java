/**
 */
package modeldebuggerconfig.impl;

import java.util.Collection;

import modeldebuggerconfig.DebuggerConfiguration;
import modeldebuggerconfig.ModeldebuggerconfigPackage;
import modeldebuggerconfig.StepDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Debugger Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link modeldebuggerconfig.impl.DebuggerConfigurationImpl#getStepDefinitions <em>Step Definitions</em>}</li>
 *   <li>{@link modeldebuggerconfig.impl.DebuggerConfigurationImpl#getEditorID <em>Editor ID</em>}</li>
 *   <li>{@link modeldebuggerconfig.impl.DebuggerConfigurationImpl#getConfigurationPackage <em>Configuration Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DebuggerConfigurationImpl extends MinimalEObjectImpl.Container implements DebuggerConfiguration {
	/**
	 * The cached value of the '{@link #getStepDefinitions() <em>Step Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<StepDefinition> stepDefinitions;

	/**
	 * The default value of the '{@link #getEditorID() <em>Editor ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorID()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getEditorID() <em>Editor ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorID()
	 * @generated
	 * @ordered
	 */
	protected String editorID = EDITOR_ID_EDEFAULT;
	/**
	 * The cached value of the '{@link #getConfigurationPackage() <em>Configuration Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage configurationPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DebuggerConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModeldebuggerconfigPackage.Literals.DEBUGGER_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StepDefinition> getStepDefinitions() {
		if (stepDefinitions == null) {
			stepDefinitions = new EObjectContainmentEList<StepDefinition>(StepDefinition.class, this, ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS);
		}
		return stepDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditorID() {
		return editorID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorID(String newEditorID) {
		String oldEditorID = editorID;
		editorID = newEditorID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__EDITOR_ID, oldEditorID, editorID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getConfigurationPackage() {
		if (configurationPackage != null && configurationPackage.eIsProxy()) {
			InternalEObject oldConfigurationPackage = (InternalEObject)configurationPackage;
			configurationPackage = (EPackage)eResolveProxy(oldConfigurationPackage);
			if (configurationPackage != oldConfigurationPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE, oldConfigurationPackage, configurationPackage));
			}
		}
		return configurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetConfigurationPackage() {
		return configurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurationPackage(EPackage newConfigurationPackage) {
		EPackage oldConfigurationPackage = configurationPackage;
		configurationPackage = newConfigurationPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE, oldConfigurationPackage, configurationPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS:
				return ((InternalEList<?>)getStepDefinitions()).basicRemove(otherEnd, msgs);
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
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS:
				return getStepDefinitions();
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__EDITOR_ID:
				return getEditorID();
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE:
				if (resolve) return getConfigurationPackage();
				return basicGetConfigurationPackage();
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
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS:
				getStepDefinitions().clear();
				getStepDefinitions().addAll((Collection<? extends StepDefinition>)newValue);
				return;
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__EDITOR_ID:
				setEditorID((String)newValue);
				return;
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE:
				setConfigurationPackage((EPackage)newValue);
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
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS:
				getStepDefinitions().clear();
				return;
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__EDITOR_ID:
				setEditorID(EDITOR_ID_EDEFAULT);
				return;
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE:
				setConfigurationPackage((EPackage)null);
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
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__STEP_DEFINITIONS:
				return stepDefinitions != null && !stepDefinitions.isEmpty();
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__EDITOR_ID:
				return EDITOR_ID_EDEFAULT == null ? editorID != null : !EDITOR_ID_EDEFAULT.equals(editorID);
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION__CONFIGURATION_PACKAGE:
				return configurationPackage != null;
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
		result.append(" (editorID: ");
		result.append(editorID);
		result.append(')');
		return result.toString();
	}

} //DebuggerConfigurationImpl
