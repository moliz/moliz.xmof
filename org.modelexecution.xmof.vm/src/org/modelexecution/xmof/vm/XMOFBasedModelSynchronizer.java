/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.FeatureValueEvent;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Classes.Kernel.Class_;

public class XMOFBasedModelSynchronizer implements ExecutionEventListener {

	private XMOFInstanceMap instanceMap;
	private EditingDomain editingDomain;
	private Resource modelResource;

	public XMOFBasedModelSynchronizer(XMOFInstanceMap instanceMap,
			EditingDomain editingDomain) {
		this.instanceMap = instanceMap;
		this.editingDomain = editingDomain;
	}

	public void setModelResource(Resource resource) {
		Assert.isTrue(editingDomain.getResourceSet().equals(resource.getResourceSet()));
		this.modelResource = resource;
	}

	@Override
	public void notify(Event event) {
		if (event instanceof ExtensionalValueEvent) {
			handleEvent((ExtensionalValueEvent) event);
		}
	}

	private void handleEvent(ExtensionalValueEvent event) {
		switch (event.getType()) {
		case CREATION:
			handleExtensionalValueCreation(event);
			break;
		case DESTRUCTION:
			handleExtensionalValueDestruction(event);
			break;
		case VALUE_CHANGED:
			handleFeatureValueChange((FeatureValueEvent) event);
			break;
		default:
			break;
		}
	}

	private void handleExtensionalValueDestruction(ExtensionalValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		if (extensionalValue instanceof Object_) {
			handleObjectDestruction((Object_) extensionalValue);
		}

	}

	private void handleObjectDestruction(Object_ extensionalValue) {
		EObject eObject = instanceMap.getEObject(extensionalValue);

		Command cmd = null;
		if (eObject.eContainer() != null) {
			cmd = new RemoveCommand(editingDomain, eObject.eContainer(),
					eObject.eContainingFeature(), eObject);
		} else {
			cmd = new RemoveCommand(editingDomain, getModelResource()
					.getContents(), eObject);
		}
		execute(cmd);
	}

	public Resource getModelResource() {
		if (modelResource == null) {
			return editingDomain.getResourceSet().getResources().get(0);
		} else {
			return modelResource;
		}
	}

	private void handleExtensionalValueCreation(ExtensionalValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		if (extensionalValue instanceof Object_) {
			handleObjectCreation((Object_) extensionalValue);
		}

	}

	private void handleObjectCreation(Object_ object) {
		EClass eClass = getEClass(object);
		EObject eObject = EcoreUtil.create(eClass);

		Command cmd = new AddCommand(editingDomain, getModelResource()
				.getContents(), eObject);
		execute(cmd);
	}

	private void handleFeatureValueChange(FeatureValueEvent event) { // TODO
																		// refactor
		EObject eObject = getModifiedObject(event);
		EStructuralFeature feature = getFeature(event);
		Object value = getNewValue(event);

		Command cmd;
		if (!feature.isMany()) {
			cmd = new SetCommand(editingDomain, eObject, feature, value);
		} else {
			cmd = new AddCommand(editingDomain,
					(EList<?>) eObject.eGet(feature), value);
		}
		execute(cmd);
	}

	private Object getNewValue(FeatureValueEvent event) { // TODO refactor
		// TODO only provide new value
		Value value = event.getFeatureValue().values.get(0);
		if (value instanceof IntegerValue)
			return ((IntegerValue) value).value;
		// TODO handle other types
		return null;
	}

	private EObject getModifiedObject(FeatureValueEvent event) { // TODO
																	// refactor
		return event.getExtensionalValue() instanceof Object_ ? instanceMap
				.getEObject((Object_) event.getExtensionalValue()) : null;
	}

	private EStructuralFeature getFeature(FeatureValueEvent event) { // TODO
																		// refactor
		EClass eClass = getEClass((Object_) event.getExtensionalValue());
		String featureName = event.getFeatureValue().feature.name;
		return getFeatureByName(featureName, eClass.getEAllStructuralFeatures());
	}

	private EStructuralFeature getFeatureByName(String featureName,
			EList<EStructuralFeature> features) {
		for (EStructuralFeature feature : features)
			if (featureName.equals(feature.getName()))
				return feature;
		return null;
	}

	private EClass getEClass(Object_ object) {
		Class_ class_ = object.types.get(0);
		EClass eClass = instanceMap.getEClass(class_);
		return eClass;
	}

	private void execute(Command cmd) {
		editingDomain.getCommandStack().execute(cmd);
	}
}
