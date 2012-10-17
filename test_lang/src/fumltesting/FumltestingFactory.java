/**
 */
package fumltesting;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fumltesting.FumltestingPackage
 * @generated
 */
public interface FumltestingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FumltestingFactory eINSTANCE = fumltesting.impl.FumltestingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Suite</em>'.
	 * @generated
	 */
	TestSuite createTestSuite();

	/**
	 * Returns a new object of class '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case</em>'.
	 * @generated
	 */
	TestCase createTestCase();

	/**
	 * Returns a new object of class '<em>Activity Under Test</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Under Test</em>'.
	 * @generated
	 */
	ActivityUnderTest createActivityUnderTest();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FumltestingPackage getFumltestingPackage();

} //FumltestingFactory
