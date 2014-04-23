/**
 */
package modeldebuggerconfig.impl;

import modeldebuggerconfig.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModeldebuggerconfigFactoryImpl extends EFactoryImpl implements ModeldebuggerconfigFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModeldebuggerconfigFactory init() {
		try {
			ModeldebuggerconfigFactory theModeldebuggerconfigFactory = (ModeldebuggerconfigFactory)EPackage.Registry.INSTANCE.getEFactory(ModeldebuggerconfigPackage.eNS_URI);
			if (theModeldebuggerconfigFactory != null) {
				return theModeldebuggerconfigFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModeldebuggerconfigFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldebuggerconfigFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModeldebuggerconfigPackage.DEBUGGER_CONFIGURATION: return createDebuggerConfiguration();
			case ModeldebuggerconfigPackage.ACTIVITY_NODE_STEP_DEFINITION: return createActivityNodeStepDefinition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DebuggerConfiguration createDebuggerConfiguration() {
		DebuggerConfigurationImpl debuggerConfiguration = new DebuggerConfigurationImpl();
		return debuggerConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityNodeStepDefinition createActivityNodeStepDefinition() {
		ActivityNodeStepDefinitionImpl activityNodeStepDefinition = new ActivityNodeStepDefinitionImpl();
		return activityNodeStepDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModeldebuggerconfigPackage getModeldebuggerconfigPackage() {
		return (ModeldebuggerconfigPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModeldebuggerconfigPackage getPackage() {
		return ModeldebuggerconfigPackage.eINSTANCE;
	}

} //ModeldebuggerconfigFactoryImpl
