/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.uml2.uml.Activity;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelFactory;
import org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage;

/**
 * This is the item provider adapter for a {@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivityExecutionItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecutionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addCallerPropertyDescriptor(object);
			addActivityPropertyDescriptor(object);
			addActivityExecutionIDPropertyDescriptor(object);
			addContextValueSnapshotPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Caller feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCallerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ActivityExecution_caller_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ActivityExecution_caller_feature", "_UI_ActivityExecution_type"),
				 TracemodelPackage.Literals.ACTIVITY_EXECUTION__CALLER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Activity feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActivityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ActivityExecution_activity_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ActivityExecution_activity_feature", "_UI_ActivityExecution_type"),
				 TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Activity Execution ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActivityExecutionIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ActivityExecution_activityExecutionID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ActivityExecution_activityExecutionID_feature", "_UI_ActivityExecution_type"),
				 TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Context Value Snapshot feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContextValueSnapshotPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ActivityExecution_contextValueSnapshot_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ActivityExecution_contextValueSnapshot_feature", "_UI_ActivityExecution_type"),
				 TracemodelPackage.Literals.ACTIVITY_EXECUTION__CONTEXT_VALUE_SNAPSHOT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY_INPUTS);
			childrenFeatures.add(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS);
			childrenFeatures.add(TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY_OUTPUTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ActivityExecution.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ActivityExecution"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		ActivityExecution activityExecution = (ActivityExecution)object;
		Activity activity = activityExecution.getActivity();
		return getString("_UI_ActivityExecution_type") +  " " + activityExecution.getActivityExecutionID() + " for Activity " + activity.getName();
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ActivityExecution.class)) {
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_INPUTS:
			case TracemodelPackage.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
			case TracemodelPackage.ACTIVITY_EXECUTION__ACTIVITY_OUTPUTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY_INPUTS,
				 TracemodelFactory.eINSTANCE.createInputParameterSetting()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createActionExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createCallActionExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createControlNodeExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createDecisionNodeExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createStructuredActivityNodeExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createInitialNodeExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__NODE_EXECUTIONS,
				 TracemodelFactory.eINSTANCE.createExpansionRegionExecution()));

		newChildDescriptors.add
			(createChildParameter
				(TracemodelPackage.Literals.ACTIVITY_EXECUTION__ACTIVITY_OUTPUTS,
				 TracemodelFactory.eINSTANCE.createOutputParameterSetting()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TracemodelUmlEditPlugin.INSTANCE;
	}

}
