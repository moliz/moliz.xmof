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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.FeatureValueEvent;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Classes.Kernel.Class_;

public class XMOFBasedModelSynchronizer implements ExecutionEventListener {

	private XMOFInstanceMap instanceMap;
	private EditingDomain editingDomain;

	public XMOFBasedModelSynchronizer(XMOFInstanceMap instanceMap,
			EditingDomain editingDomain) {
		this.instanceMap = instanceMap;
		this.editingDomain = editingDomain;
	}

	@Override
	public void notify(Event event) {
		if (event instanceof ExtensionalValueEvent)
			handleEvent((ExtensionalValueEvent) event);
	}

	private void handleEvent(ExtensionalValueEvent event) {
		switch (event.getType()) {
		case VALUE_CHANGED:
			handleValueChanged((FeatureValueEvent) event);
			break;
		default:
			break;
		}
		System.out.println(event);
	}

	private void handleValueChanged(FeatureValueEvent event) {
		EObject eObject = getModifiedObject(event);
		EStructuralFeature feature = getModifiedFeature(event);
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

	private Object getNewValue(FeatureValueEvent event) {
		// TODO only provide new value
		Value value = event.getFeatureValue().values.get(0);
		if (value instanceof IntegerValue)
			return ((IntegerValue) value).value;
		// TODO handle other types
		return null;
	}

	private EObject getModifiedObject(FeatureValueEvent event) {
		return event.getExtensionalValue() instanceof Object_ ? instanceMap
				.getEObject((Object_) event.getExtensionalValue()) : null;
	}

	private EStructuralFeature getModifiedFeature(FeatureValueEvent event) {
		Class_ class_ = ((Object_) event.getExtensionalValue()).types.get(0);
		EClass eClass = instanceMap.getEClass(class_);
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

	private void execute(Command cmd) {
		editingDomain.getCommandStack().execute(cmd);
	}
}
