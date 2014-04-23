/**
 */
package modeldebuggerconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Debugger Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link modeldebuggerconfig.DebuggerConfiguration#getStepDefinitions <em>Step Definitions</em>}</li>
 *   <li>{@link modeldebuggerconfig.DebuggerConfiguration#getEditorID <em>Editor ID</em>}</li>
 *   <li>{@link modeldebuggerconfig.DebuggerConfiguration#getConfigurationPackage <em>Configuration Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getDebuggerConfiguration()
 * @model
 * @generated
 */
public interface DebuggerConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Step Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link modeldebuggerconfig.StepDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step Definitions</em>' containment reference list.
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getDebuggerConfiguration_StepDefinitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<StepDefinition> getStepDefinitions();

	/**
	 * Returns the value of the '<em><b>Editor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor ID</em>' attribute.
	 * @see #setEditorID(String)
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getDebuggerConfiguration_EditorID()
	 * @model
	 * @generated
	 */
	String getEditorID();

	/**
	 * Sets the value of the '{@link modeldebuggerconfig.DebuggerConfiguration#getEditorID <em>Editor ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor ID</em>' attribute.
	 * @see #getEditorID()
	 * @generated
	 */
	void setEditorID(String value);

	/**
	 * Returns the value of the '<em><b>Configuration Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Package</em>' reference.
	 * @see #setConfigurationPackage(EPackage)
	 * @see modeldebuggerconfig.ModeldebuggerconfigPackage#getDebuggerConfiguration_ConfigurationPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getConfigurationPackage();

	/**
	 * Sets the value of the '{@link modeldebuggerconfig.DebuggerConfiguration#getConfigurationPackage <em>Configuration Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Package</em>' reference.
	 * @see #getConfigurationPackage()
	 * @generated
	 */
	void setConfigurationPackage(EPackage value);

} // DebuggerConfiguration
