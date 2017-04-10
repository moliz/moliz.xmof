/**
 */
package org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.IntegerFunctionsFactory;
import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.IntegerFunctionsPackage;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.ListFunctionsPackage;

import org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.ListFunctions.impl.ListFunctionsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntegerFunctionsPackageImpl extends EPackageImpl implements IntegerFunctionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected String packageFilename = "IntegerFunctions.xmof";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerPlusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerMinusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerTimesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerDivideEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerGreaterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLessOrEqualsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerGreaterOrEqualsEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions.IntegerFunctionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IntegerFunctionsPackageImpl() {
		super(eNS_URI, IntegerFunctionsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link IntegerFunctionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static IntegerFunctionsPackage init() {
		if (isInited) return (IntegerFunctionsPackage)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI);

		// Obtain or create and register package
		IntegerFunctionsPackageImpl theIntegerFunctionsPackage = (IntegerFunctionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof IntegerFunctionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new IntegerFunctionsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ListFunctionsPackageImpl theListFunctionsPackage = (ListFunctionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ListFunctionsPackage.eNS_URI) instanceof ListFunctionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ListFunctionsPackage.eNS_URI) : ListFunctionsPackage.eINSTANCE);

		// Load packages
		theIntegerFunctionsPackage.loadPackage();
		theListFunctionsPackage.loadPackage();

		// Fix loaded packages
		theIntegerFunctionsPackage.fixPackageContents();
		theListFunctionsPackage.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theIntegerFunctionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IntegerFunctionsPackage.eNS_URI, theIntegerFunctionsPackage);
		return theIntegerFunctionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerPlus() {
		if (integerPlusEClass == null) {
			integerPlusEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(0);
		}
		return integerPlusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerMinus() {
		if (integerMinusEClass == null) {
			integerMinusEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(1);
		}
		return integerMinusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerTimes() {
		if (integerTimesEClass == null) {
			integerTimesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(2);
		}
		return integerTimesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerDivide() {
		if (integerDivideEClass == null) {
			integerDivideEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(3);
		}
		return integerDivideEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerLess() {
		if (integerLessEClass == null) {
			integerLessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(4);
		}
		return integerLessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerGreater() {
		if (integerGreaterEClass == null) {
			integerGreaterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(5);
		}
		return integerGreaterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerLessOrEquals() {
		if (integerLessOrEqualsEClass == null) {
			integerLessOrEqualsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(6);
		}
		return integerLessOrEqualsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerGreaterOrEquals() {
		if (integerGreaterOrEqualsEClass == null) {
			integerGreaterOrEqualsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(IntegerFunctionsPackage.eNS_URI).getEClassifiers().get(7);
		}
		return integerGreaterOrEqualsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerFunctionsFactory getIntegerFunctionsFactory() {
		return (IntegerFunctionsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.modelexecution.xmof.standardlibrary.XMOF.PrimitiveBehaviors.IntegerFunctions." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //IntegerFunctionsPackageImpl
