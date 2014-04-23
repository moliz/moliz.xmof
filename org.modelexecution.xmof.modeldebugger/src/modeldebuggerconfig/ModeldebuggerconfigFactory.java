/**
 */
package modeldebuggerconfig;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see modeldebuggerconfig.ModeldebuggerconfigPackage
 * @generated
 */
public interface ModeldebuggerconfigFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModeldebuggerconfigFactory eINSTANCE = modeldebuggerconfig.impl.ModeldebuggerconfigFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Debugger Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Debugger Configuration</em>'.
	 * @generated
	 */
	DebuggerConfiguration createDebuggerConfiguration();

	/**
	 * Returns a new object of class '<em>Activity Node Step Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Node Step Definition</em>'.
	 * @generated
	 */
	ActivityNodeStepDefinition createActivityNodeStepDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModeldebuggerconfigPackage getModeldebuggerconfigPackage();

} //ModeldebuggerconfigFactory
