/**
 */
package org.modelexecution.fuml.Syntax.Classes.Kernel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generalization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getIsSubstitutable <em>Is Substitutable</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getGeneral <em>General</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getSpecific <em>Specific</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getGeneralization()
 * @model
 * @generated
 */
public interface Generalization extends Element {
	/**
	 * Returns the value of the '<em><b>Is Substitutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Substitutable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Substitutable</em>' attribute.
	 * @see #setIsSubstitutable(Object)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getGeneralization_IsSubstitutable()
	 * @model ordered="false"
	 * @generated
	 */
	Object getIsSubstitutable();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getIsSubstitutable <em>Is Substitutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Substitutable</em>' attribute.
	 * @see #getIsSubstitutable()
	 * @generated
	 */
	void setIsSubstitutable(Object value);

	/**
	 * Returns the value of the '<em><b>General</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>General</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>General</em>' reference.
	 * @see #setGeneral(Classifier)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getGeneralization_General()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Classifier getGeneral();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getGeneral <em>General</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>General</em>' reference.
	 * @see #getGeneral()
	 * @generated
	 */
	void setGeneral(Classifier value);

	/**
	 * Returns the value of the '<em><b>Specific</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getGeneralization <em>Generalization</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specific</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specific</em>' container reference.
	 * @see #setSpecific(Classifier)
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.KernelPackage#getGeneralization_Specific()
	 * @see org.modelexecution.fuml.Syntax.Classes.Kernel.Classifier#getGeneralization
	 * @model opposite="generalization" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Classifier getSpecific();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Classes.Kernel.Generalization#getSpecific <em>Specific</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specific</em>' container reference.
	 * @see #getSpecific()
	 * @generated
	 */
	void setSpecific(Classifier value);

} // Generalization
