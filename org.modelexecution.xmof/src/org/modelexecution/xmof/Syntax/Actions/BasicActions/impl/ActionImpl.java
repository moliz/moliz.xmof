/**
 */
package org.modelexecution.xmof.Syntax.Actions.BasicActions.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.impl.ExecutableNodeImpl;
import org.modelexecution.xmof.util.DerivedUnionEObjectEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.modelexecution.xmof.Syntax.Actions.BasicActions.impl.ActionImpl#isLocallyReentrant <em>Locally Reentrant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ActionImpl extends ExecutableNodeImpl implements Action {
	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected EClassifier context;

	/**
	 * The default value of the '{@link #isLocallyReentrant() <em>Locally Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocallyReentrant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCALLY_REENTRANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLocallyReentrant() <em>Locally Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocallyReentrant()
	 * @generated
	 * @ordered
	 */
	protected boolean locallyReentrant = LOCALLY_REENTRANT_EDEFAULT;

	private DerivedUnionEObjectEList<InputPin> input;
	
	private DerivedUnionEObjectEList<OutputPin> output;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasicActionsPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<OutputPin> getOutput() {
		if (output == null) {
			List<Integer> featureIDList = new ArrayList<Integer>();
			for (EStructuralFeature feature : this.eClass()
					.getEAllStructuralFeatures()) {
				if (hasOutputPinTypeAndIsNotDerived(feature)) {
					featureIDList.add(feature.getFeatureID());
				}
			}
			output = new DerivedUnionEObjectEList<OutputPin>(this.getClass(),
					this, BasicActionsPackage.ACTION__OUTPUT, toIntArray(featureIDList));

		}
		return output;
	}

	private boolean hasOutputPinTypeAndIsNotDerived(EStructuralFeature feature) {
		return BasicActionsPackage.eINSTANCE.getOutputPin().equals(
				feature.getEType())
				&& !feature.isDerived();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getContext() {
		if (context != null && context.eIsProxy()) {
			InternalEObject oldContext = (InternalEObject)context;
			context = (EClassifier)eResolveProxy(oldContext);
			if (context != oldContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BasicActionsPackage.ACTION__CONTEXT, oldContext, context));
			}
		}
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetContext() {
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(EClassifier newContext) {
		EClassifier oldContext = context;
		context = newContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicActionsPackage.ACTION__CONTEXT, oldContext, context));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<InputPin> getInput() {
		if (input == null) {
			List<Integer> featureIDList = new ArrayList<Integer>();
			for (EStructuralFeature feature : this.eClass()
					.getEAllStructuralFeatures()) {
				if (hasInputPinTypeAndIsNotDerived(feature)) {
					featureIDList.add(feature.getFeatureID());
				}
			}
			input = new DerivedUnionEObjectEList<InputPin>(this.getClass(),
					this, BasicActionsPackage.ACTION__INPUT,
					toIntArray(featureIDList));

		}
		return input;
	}

	private int[] toIntArray(List<Integer> featureIDList) {
		int[] featureIds = new int[featureIDList.size()];
		for (int i = 0; i < featureIDList.size(); i++)
			featureIds[i] = featureIDList.get(i);
		return featureIds;
	}

	private boolean hasInputPinTypeAndIsNotDerived(EStructuralFeature feature) {
		return BasicActionsPackage.eINSTANCE.getInputPin().equals(
				feature.getEType())
				&& !feature.isDerived();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocallyReentrant() {
		return locallyReentrant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocallyReentrant(boolean newLocallyReentrant) {
		boolean oldLocallyReentrant = locallyReentrant;
		locallyReentrant = newLocallyReentrant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasicActionsPackage.ACTION__LOCALLY_REENTRANT, oldLocallyReentrant, locallyReentrant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BasicActionsPackage.ACTION__OUTPUT:
				return getOutput();
			case BasicActionsPackage.ACTION__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case BasicActionsPackage.ACTION__INPUT:
				return getInput();
			case BasicActionsPackage.ACTION__LOCALLY_REENTRANT:
				return isLocallyReentrant();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BasicActionsPackage.ACTION__CONTEXT:
				setContext((EClassifier)newValue);
				return;
			case BasicActionsPackage.ACTION__LOCALLY_REENTRANT:
				setLocallyReentrant((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BasicActionsPackage.ACTION__CONTEXT:
				setContext((EClassifier)null);
				return;
			case BasicActionsPackage.ACTION__LOCALLY_REENTRANT:
				setLocallyReentrant(LOCALLY_REENTRANT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BasicActionsPackage.ACTION__OUTPUT:
				return !getOutput().isEmpty();
			case BasicActionsPackage.ACTION__CONTEXT:
				return context != null;
			case BasicActionsPackage.ACTION__INPUT:
				return !getInput().isEmpty();
			case BasicActionsPackage.ACTION__LOCALLY_REENTRANT:
				return locallyReentrant != LOCALLY_REENTRANT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (locallyReentrant: ");
		result.append(locallyReentrant);
		result.append(')');
		return result.toString();
	}

} //ActionImpl
