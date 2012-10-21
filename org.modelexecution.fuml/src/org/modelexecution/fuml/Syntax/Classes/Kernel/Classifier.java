/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getInheritedMember <em>Inherited Member</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getGeneral <em>General</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getIsFinalSpecialization <em>Is Final Specialization</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends Type, Namespace {
	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_IsAbstract()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsAbstract();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #getIsAbstract()
	 * @generated
	 */
	void setIsAbstract(Object value);

	/**
	 * Returns the value of the '<em><b>Generalization</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getSpecific <em>Specific</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generalization</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generalization</em>' containment reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_Generalization()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getSpecific
	 * @model opposite="specific" containment="true" ordered="false"
	 * @generated
	 */
	EList<Generalization> getGeneralization();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Feature}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Feature#getFeaturingClassifier <em>Featuring Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_Feature()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Feature#getFeaturingClassifier
	 * @model opposite="featuringClassifier" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Feature> getFeature();

	/**
	 * Returns the value of the '<em><b>Inherited Member</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.NamedElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherited Member</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherited Member</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_InheritedMember()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<NamedElement> getInheritedMember();

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_Attribute()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Property> getAttribute();

	/**
	 * Returns the value of the '<em><b>General</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>General</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>General</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_General()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Classifier> getGeneral();

	/**
	 * Returns the value of the '<em><b>Is Final Specialization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Final Specialization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Final Specialization</em>' attribute.
	 * @see #setIsFinalSpecialization(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getClassifier_IsFinalSpecialization()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsFinalSpecialization();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getIsFinalSpecialization <em>Is Final Specialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Final Specialization</em>' attribute.
	 * @see #getIsFinalSpecialization()
	 * @generated
	 */
	void setIsFinalSpecialization(Object value);

} // Classifier
