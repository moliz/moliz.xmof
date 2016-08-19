/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntegerFunctionsFactoryImpl extends EFactoryImpl implements IntegerFunctionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IntegerFunctionsFactory init() {
		try {
			IntegerFunctionsFactory theIntegerFunctionsFactory = (IntegerFunctionsFactory)EPackage.Registry.INSTANCE.getEFactory(IntegerFunctionsPackage.eNS_URI);
			if (theIntegerFunctionsFactory != null) {
				return theIntegerFunctionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IntegerFunctionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerFunctionsFactoryImpl() {
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
			case IntegerFunctionsPackage.INTEGER_PLUS: return createIntegerPlus();
			case IntegerFunctionsPackage.INTEGER_MINUS: return createIntegerMinus();
			case IntegerFunctionsPackage.INTEGER_TIMES: return createIntegerTimes();
			case IntegerFunctionsPackage.INTEGER_DIVIDE: return createIntegerDivide();
			case IntegerFunctionsPackage.INTEGER_LESS: return createIntegerLess();
			case IntegerFunctionsPackage.INTEGER_GREATER: return createIntegerGreater();
			case IntegerFunctionsPackage.INTEGER_LESS_OR_EQUALS: return createIntegerLessOrEquals();
			case IntegerFunctionsPackage.INTEGER_GREATER_OR_EQUALS: return createIntegerGreaterOrEquals();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerPlus createIntegerPlus() {
		IntegerPlusImpl integerPlus = new IntegerPlusImpl();
		return integerPlus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerMinus createIntegerMinus() {
		IntegerMinusImpl integerMinus = new IntegerMinusImpl();
		return integerMinus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerTimes createIntegerTimes() {
		IntegerTimesImpl integerTimes = new IntegerTimesImpl();
		return integerTimes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerDivide createIntegerDivide() {
		IntegerDivideImpl integerDivide = new IntegerDivideImpl();
		return integerDivide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLess createIntegerLess() {
		IntegerLessImpl integerLess = new IntegerLessImpl();
		return integerLess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerGreater createIntegerGreater() {
		IntegerGreaterImpl integerGreater = new IntegerGreaterImpl();
		return integerGreater;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLessOrEquals createIntegerLessOrEquals() {
		IntegerLessOrEqualsImpl integerLessOrEquals = new IntegerLessOrEqualsImpl();
		return integerLessOrEquals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerGreaterOrEquals createIntegerGreaterOrEquals() {
		IntegerGreaterOrEqualsImpl integerGreaterOrEquals = new IntegerGreaterOrEqualsImpl();
		return integerGreaterOrEquals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerFunctionsPackage getIntegerFunctionsPackage() {
		return (IntegerFunctionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IntegerFunctionsPackage getPackage() {
		return IntegerFunctionsPackage.eINSTANCE;
	}

} //IntegerFunctionsFactoryImpl
