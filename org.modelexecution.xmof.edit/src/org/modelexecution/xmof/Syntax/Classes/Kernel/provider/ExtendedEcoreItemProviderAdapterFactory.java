/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.Syntax.Classes.Kernel.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EPackageItemProvider;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelFactory;

public class ExtendedEcoreItemProviderAdapterFactory extends EcoreItemProviderAdapterFactory {
	
	@Override
	protected Adapter createAdapter(Notifier target, Object type) {
		if (target instanceof EPackage) {
			return new EPackageItemProvider(this) {
				@Override
				protected void collectNewChildDescriptors(
						Collection<Object> newChildDescriptors,
						Object object) {
					super.collectNewChildDescriptors(newChildDescriptors, object);
					newChildDescriptors.add
				      (createChildParameter
				        (EcorePackage.Literals.EPACKAGE__ECLASSIFIERS,
				         KernelFactory.eINSTANCE.createBehavioredEClass()));
					newChildDescriptors.add
				      (createChildParameter
				        (EcorePackage.Literals.EPACKAGE__ECLASSIFIERS,
				         KernelFactory.eINSTANCE.createMainEClass()));
				}
			};
		}
		return super.createAdapter(target, type);
	}

}
