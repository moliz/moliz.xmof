/**
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EOperationItemProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage;

/**
 * This is the item provider adapter for a
 * {@link org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NOT (changed super class to respective ecore edit class)
 */
public class BehavioredEOperationItemProvider extends EOperationItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BehavioredEOperationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT (removed inherited property descriptors)
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			// imitate EOperation as we want to get all possible choices for
			// EType as EOperation has
			super.getPropertyDescriptors(EcoreFactory.eINSTANCE
					.createEOperation());
			addMethodPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Method feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_BehavioredEOperation_method_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_BehavioredEOperation_method_feature",
						"_UI_BehavioredEOperation_type"),
				KernelPackage.Literals.BEHAVIORED_EOPERATION__METHOD, true,
				false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT (removed inherited)
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns BehavioredEOperation.gif. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT (delegate to super class)
	 */
	@Override
	public Object getImage(Object object) {
		// return overlayImage(object,
		// getResourceLocator().getImage("full/obj16/BehavioredEOperation"));
		return super.getImage(object);
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT (delegate to super class)
	 */
	@Override
	public String getText(Object object) {
		// String label = ((BehavioredEOperation)object).getName();
		// return label == null || label.length() == 0 ?
		// getString("_UI_BehavioredEOperation_type") :
		// getString("_UI_BehavioredEOperation_type") + " " + label;
		return super.getText(object);
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(BehavioredEOperation.class)) {
		case KernelPackage.BEHAVIORED_EOPERATION__NAME:
		case KernelPackage.BEHAVIORED_EOPERATION__ORDERED:
		case KernelPackage.BEHAVIORED_EOPERATION__UNIQUE:
		case KernelPackage.BEHAVIORED_EOPERATION__LOWER_BOUND:
		case KernelPackage.BEHAVIORED_EOPERATION__UPPER_BOUND:
		case KernelPackage.BEHAVIORED_EOPERATION__MANY:
		case KernelPackage.BEHAVIORED_EOPERATION__REQUIRED:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case KernelPackage.BEHAVIORED_EOPERATION__EANNOTATIONS:
		case KernelPackage.BEHAVIORED_EOPERATION__EGENERIC_TYPE:
		case KernelPackage.BEHAVIORED_EOPERATION__ETYPE_PARAMETERS:
		case KernelPackage.BEHAVIORED_EOPERATION__EPARAMETERS:
		case KernelPackage.BEHAVIORED_EOPERATION__EGENERIC_EXCEPTIONS:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				EcorePackage.Literals.EOPERATION__EPARAMETERS,
				KernelFactory.eINSTANCE.createDirectedParameter()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return XMOFEditPlugin.INSTANCE;
	}

}
