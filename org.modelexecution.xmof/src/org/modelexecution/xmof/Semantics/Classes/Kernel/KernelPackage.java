/**
 */
package org.modelexecution.xmof.Semantics.Classes.Kernel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.modelexecution.xmof.Semantics.Loci.LociL1.LociL1Package;

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
 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.KernelFactory
 * @model kind="package"
 * @generated
 */
public interface KernelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Kernel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modelexecution.org/xmof/semantics/classes/kernel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fUML.Semantics.Classes.Kernel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KernelPackage eINSTANCE = org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 5;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = LociL1Package.SEMANTIC_VISITOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.PrimitiveValueImpl <em>Primitive Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.PrimitiveValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getPrimitiveValue()
	 * @generated
	 */
	int PRIMITIVE_VALUE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_VALUE__TYPE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.StringValueImpl <em>String Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.StringValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getStringValue()
	 * @generated
	 */
	int STRING_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE__TYPE = PRIMITIVE_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE__VALUE = PRIMITIVE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_FEATURE_COUNT = PRIMITIVE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.IntegerValueImpl <em>Integer Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.IntegerValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getIntegerValue()
	 * @generated
	 */
	int INTEGER_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE__TYPE = PRIMITIVE_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE__VALUE = PRIMITIVE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE_FEATURE_COUNT = PRIMITIVE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.EnumerationValueImpl <em>Enumeration Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.EnumerationValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getEnumerationValue()
	 * @generated
	 */
	int ENUMERATION_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Literal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_VALUE__LITERAL = VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_VALUE__TYPE = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Enumeration Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.BooleanValueImpl <em>Boolean Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.BooleanValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getBooleanValue()
	 * @generated
	 */
	int BOOLEAN_VALUE = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE__TYPE = PRIMITIVE_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE__VALUE = PRIMITIVE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_FEATURE_COUNT = PRIMITIVE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ObjectValueImpl <em>Object Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ObjectValueImpl
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getObjectValue()
	 * @generated
	 */
	int OBJECT_VALUE = 6;

	/**
	 * The feature id for the '<em><b>EObject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_VALUE__EOBJECT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.PrimitiveValue <em>Primitive Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.PrimitiveValue
	 * @generated
	 */
	EClass getPrimitiveValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.PrimitiveValue#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.PrimitiveValue#getType()
	 * @see #getPrimitiveValue()
	 * @generated
	 */
	EReference getPrimitiveValue_Type();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.StringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.StringValue
	 * @generated
	 */
	EClass getStringValue();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.StringValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.StringValue#getValue()
	 * @see #getStringValue()
	 * @generated
	 */
	EAttribute getStringValue_Value();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.IntegerValue <em>Integer Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.IntegerValue
	 * @generated
	 */
	EClass getIntegerValue();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.IntegerValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.IntegerValue#getValue()
	 * @see #getIntegerValue()
	 * @generated
	 */
	EAttribute getIntegerValue_Value();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue <em>Enumeration Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue
	 * @generated
	 */
	EClass getEnumerationValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Literal</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getLiteral()
	 * @see #getEnumerationValue()
	 * @generated
	 */
	EReference getEnumerationValue_Literal();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.EnumerationValue#getType()
	 * @see #getEnumerationValue()
	 * @generated
	 */
	EReference getEnumerationValue_Type();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.BooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.BooleanValue
	 * @generated
	 */
	EClass getBooleanValue();

	/**
	 * Returns the meta object for the attribute '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.BooleanValue#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.BooleanValue#isValue()
	 * @see #getBooleanValue()
	 * @generated
	 */
	EAttribute getBooleanValue_Value();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for class '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue <em>Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Value</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue
	 * @generated
	 */
	EClass getObjectValue();

	/**
	 * Returns the meta object for the reference '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue#getEObject <em>EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EObject</em>'.
	 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue#getEObject()
	 * @see #getObjectValue()
	 * @generated
	 */
	EReference getObjectValue_EObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KernelFactory getKernelFactory();

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
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.PrimitiveValueImpl <em>Primitive Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.PrimitiveValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getPrimitiveValue()
		 * @generated
		 */
		EClass PRIMITIVE_VALUE = eINSTANCE.getPrimitiveValue();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIMITIVE_VALUE__TYPE = eINSTANCE.getPrimitiveValue_Type();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.StringValueImpl <em>String Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.StringValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getStringValue()
		 * @generated
		 */
		EClass STRING_VALUE = eINSTANCE.getStringValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VALUE__VALUE = eINSTANCE.getStringValue_Value();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.IntegerValueImpl <em>Integer Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.IntegerValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getIntegerValue()
		 * @generated
		 */
		EClass INTEGER_VALUE = eINSTANCE.getIntegerValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_VALUE__VALUE = eINSTANCE.getIntegerValue_Value();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.EnumerationValueImpl <em>Enumeration Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.EnumerationValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getEnumerationValue()
		 * @generated
		 */
		EClass ENUMERATION_VALUE = eINSTANCE.getEnumerationValue();

		/**
		 * The meta object literal for the '<em><b>Literal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_VALUE__LITERAL = eINSTANCE.getEnumerationValue_Literal();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_VALUE__TYPE = eINSTANCE.getEnumerationValue_Type();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.BooleanValueImpl <em>Boolean Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.BooleanValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getBooleanValue()
		 * @generated
		 */
		EClass BOOLEAN_VALUE = eINSTANCE.getBooleanValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VALUE__VALUE = eINSTANCE.getBooleanValue_Value();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '{@link org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ObjectValueImpl <em>Object Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.ObjectValueImpl
		 * @see org.modelexecution.xmof.Semantics.Classes.Kernel.impl.KernelPackageImpl#getObjectValue()
		 * @generated
		 */
		EClass OBJECT_VALUE = eINSTANCE.getObjectValue();

		/**
		 * The meta object literal for the '<em><b>EObject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT_VALUE__EOBJECT = eINSTANCE.getObjectValue_EObject();

	}

} //KernelPackage
