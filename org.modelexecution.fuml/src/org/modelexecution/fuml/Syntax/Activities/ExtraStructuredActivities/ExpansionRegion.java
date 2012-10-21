/**
 */
package org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities;

import org.eclipse.emf.common.util.EList;

import org.modelexecution.fuml.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expansion Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * self.edge->forAll(self.node->includes(source) and
 *                   self.node->includes(target))
 * self.mode != ExpansionKind::stream
 * self.output->isEmpty()
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion#getMode <em>Mode</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion#getInputElement <em>Input Element</em>}</li>
 *   <li>{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion#getOutputElement <em>Output Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage#getExpansionRegion()
 * @model
 * @generated
 */
public interface ExpansionRegion extends StructuredActivityNode {
	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionKind
	 * @see #setMode(ExpansionKind)
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage#getExpansionRegion_Mode()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExpansionKind getMode();

	/**
	 * Sets the value of the '{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionKind
	 * @see #getMode()
	 * @generated
	 */
	void setMode(ExpansionKind value);

	/**
	 * Returns the value of the '<em><b>Input Element</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode#getRegionAsInput <em>Region As Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Element</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage#getExpansionRegion_InputElement()
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode#getRegionAsInput
	 * @model opposite="regionAsInput" required="true" ordered="false"
	 * @generated
	 */
	EList<ExpansionNode> getInputElement();

	/**
	 * Returns the value of the '<em><b>Output Element</b></em>' reference list.
	 * The list contents are of type {@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode}.
	 * It is bidirectional and its opposite is '{@link org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode#getRegionAsOutput <em>Region As Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Element</em>' reference list.
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesPackage#getExpansionRegion_OutputElement()
	 * @see org.modelexecution.fuml.Syntax.Activities.ExtraStructuredActivities.ExpansionNode#getRegionAsOutput
	 * @model opposite="regionAsOutput" ordered="false"
	 * @generated
	 */
	EList<ExpansionNode> getOutputElement();

} // ExpansionRegion
