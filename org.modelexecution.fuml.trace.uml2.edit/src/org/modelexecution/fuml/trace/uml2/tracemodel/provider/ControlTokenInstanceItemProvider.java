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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActionExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution;

/**
 * This is the item provider adapter for a {@link org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlTokenInstanceItemProvider extends TokenInstanceItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlTokenInstanceItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns ControlTokenInstance.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ControlTokenInstance"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String controlTokenString = "";
		ControlTokenInstance controlTokenInstance = (ControlTokenInstance)object;
		EObject eContainer = controlTokenInstance.eContainer();
		if(eContainer instanceof InitialNodeExecution) {
			controlTokenString += getControlTokenIdString((InitialNodeExecution) eContainer, controlTokenInstance);
		} else if(eContainer instanceof ActionExecution) {
			controlTokenString += getControlTokenIdString((ActionExecution) eContainer, controlTokenInstance);
		}
		return getString("_UI_ControlTokenInstance_type") + " " + controlTokenString;
	}
	
	private String getControlTokenIdString(ActionExecution actionExecution, ControlTokenInstance controlTokenInstance) {
		int indexOfControlToken = actionExecution.getOutgoingControl().indexOf(controlTokenInstance);
		String activityNodeIdText = TraceElementTextUtil.getActivityNodeIdText(actionExecution);
		return "ct" + indexOfControlToken + " provided by " + getString("_UI_ActionExecution_type") + " " + activityNodeIdText;
	}
	
	private String getControlTokenIdString(InitialNodeExecution initialNodeExecution, ControlTokenInstance controlTokenInstance) {
		String activityNodeIdText = TraceElementTextUtil.getActivityNodeIdText(initialNodeExecution);
		return "ct0 provided by " + getString("_UI_InitialNodeExecution_type") + " " + activityNodeIdText;
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
	}

}
