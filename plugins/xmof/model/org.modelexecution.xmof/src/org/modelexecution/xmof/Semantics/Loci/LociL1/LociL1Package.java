/**
 */
package org.modelexecution.xmof.Semantics.Loci.LociL1;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Factory
 * @model kind="package"
 * @generated
 */
public interface LociL1Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "LociL1";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelexecution.org/xmof/semantics/loci/locil1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fUML.Semantics.Loci.LociL1";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LociL1Package eINSTANCE = org.modelexecution.xmof.Semantics.Loci.LociL1.impl.LociL1PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Loci.LociL1.impl.SemanticVisitorImpl <em>Semantic Visitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.impl.SemanticVisitorImpl
	 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.impl.LociL1PackageImpl#getSemanticVisitor()
	 * @generated
	 */
	int SEMANTIC_VISITOR = 0;

	/**
	 * The number of structural features of the '<em>Semantic Visitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_VISITOR_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Loci.LociL1.SemanticVisitor <em>Semantic Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic Visitor</em>'.
	 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.SemanticVisitor
	 * @generated
	 */
	EClass getSemanticVisitor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LociL1Factory getLociL1Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Loci.LociL1.impl.SemanticVisitorImpl <em>Semantic Visitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.impl.SemanticVisitorImpl
		 * @see org.modelexecution.xmof.Semantics.Loci.LociL1.impl.LociL1PackageImpl#getSemanticVisitor()
		 * @generated
		 */
		EClass SEMANTIC_VISITOR = eINSTANCE.getSemanticVisitor();

	}

} //LociL1Package
