/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.Slot#getDefiningFeature <em>Defining Feature</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.Slot#getValue <em>Value</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Classes.Kernel.Slot#getOwningInstance <em>Owning Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getSlot()
 * @model
 * @generated
 */
public interface Slot extends EObject, EModelElement {
	/**
	 * Returns the value of the '<em><b>Defining Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The structural feature that specifies the values that may be held by the
	 *                 slot.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Defining Feature</em>' reference.
	 * @see #setDefiningFeature(EStructuralFeature)
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getSlot_DefiningFeature()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EStructuralFeature getDefiningFeature();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Classes.Kernel.Slot#getDefiningFeature <em>Defining Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defining Feature</em>' reference.
	 * @see #getDefiningFeature()
	 * @generated
	 */
	void setDefiningFeature(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelexecution.xmof.Syntax.Classes.Kernel.ValueSpecification}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value or values corresponding to the defining feature for the owning
	 *                   instance specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getSlot_Value()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueSpecification> getValue();

	/**
	 * Returns the value of the '<em><b>Owning Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.xmof.Syntax.Classes.Kernel.InstanceSpecification#getSlot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The instance specification that owns this slot.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Instance</em>' container reference.
	 * @see #setOwningInstance(InstanceSpecification)
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage#getSlot_OwningInstance()
	 * @see org.modelexecution.xmof.Syntax.Classes.Kernel.InstanceSpecification#getSlot
	 * @model opposite="slot" required="true" transient="false" ordered="false"
	 * @generated
	 */
	InstanceSpecification getOwningInstance();

	/**
	 * Sets the value of the '{@link org.modelexecution.xmof.Syntax.Classes.Kernel.Slot#getOwningInstance <em>Owning Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Instance</em>' container reference.
	 * @see #getOwningInstance()
	 * @generated
	 */
	void setOwningInstance(InstanceSpecification value);

} // Slot
