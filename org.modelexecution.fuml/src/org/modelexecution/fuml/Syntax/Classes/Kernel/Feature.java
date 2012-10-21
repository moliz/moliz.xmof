/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * not self.isStatic
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Feature#getFeaturingClassifier <em>Featuring Classifier</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Feature#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getFeature()
 * @model abstract="true"
 * @generated
 */
public interface Feature extends RedefinableElement {
	/**
	 * Returns the value of the '<em><b>Featuring Classifier</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Featuring Classifier</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Featuring Classifier</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getFeature_FeaturingClassifier()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getFeature
	 * @model opposite="feature" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Classifier> getFeaturingClassifier();

	/**
	 * Returns the value of the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Static</em>' attribute.
	 * @see #setIsStatic(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getFeature_IsStatic()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getIsStatic();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Feature#getIsStatic <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Static</em>' attribute.
	 * @see #getIsStatic()
	 * @generated
	 */
	void setIsStatic(Object value);

} // Feature
