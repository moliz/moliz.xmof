/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.papyrus.util;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.resource.IModel;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.services.ExtensionServicesRegistry;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServiceMultiException;
import org.eclipse.papyrus.infra.core.services.ServiceNotFoundException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Utility functions for DI resources.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusResourceUtil {

	public static UmlModel loadModel(URI modelUri) {
		UmlModel umlModel = null;
		ModelSet modelSet = initializeModelSet();
		try {
			modelSet.loadModels(modelUri);
		} catch (ModelMultiException e) {
		}
		IModel model = modelSet.getModel(UmlModel.MODEL_ID);
		if (model instanceof UmlModel) {
			umlModel = (UmlModel) model;
		}
		return umlModel;
	}
	
	private static ModelSet initializeModelSet() {
		ModelSet modelSet = null;
		ServicesRegistry registry = createServicesRegistry();
		if (registry != null) {
			try {
				modelSet = registry.getService(ModelSet.class);
			} catch (ServiceException e) {
			}
		}
		return modelSet;
	}

	private static ServicesRegistry createServicesRegistry() {
		ServicesRegistry registry = null;
		try {
			registry = new ExtensionServicesRegistry(
					org.eclipse.papyrus.infra.core.Activator.PLUGIN_ID);
		} catch (ServiceException e) {
		}

		if (registry != null) {
			try {
				registry.startServicesByClassKeys(ModelSet.class);
			} catch (ServiceMultiException | ServiceNotFoundException e) {
			}
		}
		return registry;
	}

	public static NamedElement obtainFirstNamedElement(UmlModel umlModel) {
		for (TreeIterator<EObject> allContents = umlModel.getResource()
				.getAllContents(); allContents.hasNext();) {
			EObject eObject = allContents.next();
			if (eObject instanceof NamedElement)
				return (NamedElement) eObject;
		}

		return null;
	}
	
	public static View getNotationElement(NamedElement namedElement, View view) {
		if (equals(namedElement, view.getElement())) {
			return view;
		} else {
			return findNotationInChildren(namedElement, view);
		}
	}

	private static View findNotationInChildren(NamedElement namedElement,
			View view) {
		TreeIterator<EObject> iterator = view.eAllContents();
		while (iterator.hasNext()) {
			EObject eObject = iterator.next();
			if (eObject instanceof View) {
				View childView = (View) eObject;
				if (equals(namedElement, childView.getElement())) {
					return childView;
				}
			}
		}
		return null;
	}

	private static boolean equals(EObject eObject1, EObject eObject2) {
		return eObject1.equals(eObject2);
	}

	public static View getNotationElement(String qName, View view) {
		EObject element = view.getElement();
		if (element instanceof NamedElement) {
			NamedElement namedElement = (NamedElement) element;
			if (qName.equals(namedElement.getQualifiedName())) {
				return view;
			} else {
				return findNotationInChildren(qName, view);
			}
		}
		return null;
	}

	private static View findNotationInChildren(String qName, View view) {
		TreeIterator<EObject> iterator = view.eAllContents();
		while (iterator.hasNext()) {
			EObject eObject = iterator.next();
			if (eObject instanceof View) {
				View childView = (View) eObject;
				EObject element = childView.getElement();
				if (element instanceof NamedElement) {
					NamedElement namedElement = (NamedElement) element;
					if (qName.equals(namedElement.getQualifiedName())) {
						return childView;
					}
				}
			}
		}
		return null;
	}

}
