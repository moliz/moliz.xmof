/**
 */
package org.modelexecution.xmof.Syntax.Activities.IntermediateActivities;

import org.eclipse.emf.ecore.EFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage
 * @generated
 */
public interface IntermediateActivitiesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	IntermediateActivitiesFactory eINSTANCE = org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.impl.IntermediateActivitiesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Object Flow</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Object Flow</em>'.
	 * @generated
	 */
	ObjectFlow createObjectFlow();

	/**
	 * Returns a new object of class '<em>Activity</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity</em>'.
	 * @generated
	 */
	Activity createActivity();

	/**
	 * Returns a new object of class '<em>Activity</em>' that realizes the
	 * specified {@code operation} in terms of its
	 * {@link BehavioredEOperation#getMethod() method}. The created activity is
	 * added to the {@link BehavioredClassifier} containing the specified
	 * {@code operation}.
	 * 
	 * @param operation
	 *            to create an activity for
	 * @return created activity
	 */
	Activity createActivity(BehavioredEOperation operation);

	/**
	 * Returns a new object of class '<em>Activity</em>' that realizes the
	 * specified {@code operation} in terms of its
	 * {@link BehavioredEOperation#getMethod() method}. The created activity is
	 * added to the {@link BehavioredClassifier} containing the specified
	 * {@code operation}.
	 * 
	 * @param operation
	 *            to create an activity for
	 * @param addActivityToBehavioredClassifier
	 *            whether or not to add the created activity to the
	 *            {@link BehavioredClassifier} containing the specified
	 *            {@code operation}.
	 * @return created activity
	 */
	Activity createActivity(BehavioredEOperation operation,
			boolean addActivityToBehavioredClassifier);

	/**
	 * Prepares the specified {@link Activity} so that it realizes the specified
	 * {@code operation} in terms of its
	 * {@link BehavioredEOperation#getMethod() method}. The created activity is
	 * added to the {@link BehavioredClassifier} containing the specified
	 * {@code operation}.
	 * 
	 * @param operation
	 *            to create an activity for
	 * @param addActivityToBehavioredClassifier
	 *            whether or not to add the created activity to the
	 *            {@link BehavioredClassifier} containing the specified
	 *            {@code operation}.
	 * @return created activity
	 */
	public void prepareActivityForOperation(Activity activity,
			BehavioredEOperation operation,
			boolean addActivityToBehavioredClassifier);

	/**
	 * Returns a new object of class '<em>Merge Node</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Merge Node</em>'.
	 * @generated
	 */
	MergeNode createMergeNode();

	/**
	 * Returns a new object of class '<em>Join Node</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Node</em>'.
	 * @generated
	 */
	JoinNode createJoinNode();

	/**
	 * Returns a new object of class '<em>Initial Node</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Initial Node</em>'.
	 * @generated
	 */
	InitialNode createInitialNode();

	/**
	 * Returns a new object of class '<em>Fork Node</em>'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Fork Node</em>'.
	 * @generated
	 */
	ForkNode createForkNode();

	/**
	 * Returns a new object of class '<em>Control Flow</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Control Flow</em>'.
	 * @generated
	 */
	ControlFlow createControlFlow();

	/**
	 * Returns a new object of class '<em>Decision Node</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Decision Node</em>'.
	 * @generated
	 */
	DecisionNode createDecisionNode();

	/**
	 * Returns a new object of class '<em>Activity Final Node</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activity Final Node</em>'.
	 * @generated
	 */
	ActivityFinalNode createActivityFinalNode();

	/**
	 * Returns a new object of class '<em>Activity Parameter Node</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activity Parameter Node</em>'.
	 * @generated
	 */
	ActivityParameterNode createActivityParameterNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IntermediateActivitiesPackage getIntermediateActivitiesPackage();

} // IntermediateActivitiesFactory
