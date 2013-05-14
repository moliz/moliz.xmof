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
import org.eclipse.emf.common.util.BasicEList;
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

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.UnlimitedNaturalValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

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
		Assert.isTrue(editingDomain.getResourceSet().equals(
				resource.getResourceSet()));
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
		case VALUE_ADDED:
			handleFeatureValueAdded((FeatureValueEvent) event);
			break;
		case VALUE_REMOVED:
			handleFeatureValueRemoved((FeatureValueEvent) event);
		default:
			break;
		}
	}

	private void handleFeatureValueRemoved(FeatureValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		int position = event.getPosition();
		StructuralFeature feature = event.getFeature();		
		ValueList values = event.getValues();
		if (extensionalValue instanceof Object_) {
			handleFeatureValueRemovedToObject((Object_) extensionalValue, feature, values, position);
		}
		
	}

	private void handleFeatureValueRemovedToObject(Object_ object,
			StructuralFeature feature, ValueList values, int position) {
		EObject eObject = instanceMap.getEObject(object);
		EStructuralFeature eStructuralFeature = getEStructuralFeature(feature);
		
		Command cmd = null;
		if(eStructuralFeature.isMany()) {				
			Object existingValues = eObject.eGet(eStructuralFeature);
			if(existingValues instanceof EList<?>) {
				EList<?> newValues = new BasicEList<Object>((EList<?>)existingValues);
				for (int i = 0; i < values.size(); i++) {
					newValues.remove(position);
				}			
				cmd = new RemoveCommand(editingDomain, (EList<?>) eObject.eGet(eStructuralFeature), (EList<?>) eObject.eGet(eStructuralFeature));
				execute(cmd);
				cmd = new AddCommand(editingDomain, (EList<?>) eObject.eGet(eStructuralFeature), newValues);
				execute(cmd);
			}
		} else {
			cmd = new SetCommand(editingDomain, eObject, eStructuralFeature, SetCommand.UNSET_VALUE);
			execute(cmd);
		}
	}

	private void handleFeatureValueAdded(FeatureValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		int position = event.getPosition();
		StructuralFeature feature = event.getFeature();		
		ValueList values = event.getValues();
		if (extensionalValue instanceof Object_) {
			handleFeatureValueAddedToObject((Object_) extensionalValue, feature, values, position);
		}

	}

	private void handleFeatureValueAddedToObject(Object_ object,
			StructuralFeature feature, ValueList values, int position) {
		EObject eObject = instanceMap.getEObject(object);
		EStructuralFeature eStructuralFeature = getEStructuralFeature(feature);
				
		EList<Object> addedValues = new BasicEList<Object>(); 
		for(Value value : values) {
			Object newValue = getNewValue(value);
			addedValues.add(newValue);
		}
		Command cmd = null;
		if(eStructuralFeature.isMany()) {
			cmd = new AddCommand(editingDomain, (EList<?>) eObject.eGet(eStructuralFeature), addedValues, position);
		} else {
			cmd = new SetCommand(editingDomain, eObject, eStructuralFeature, addedValues.get(0));			
		}
		execute(cmd);
	}

	private Object getNewValue(Value value) {
		if (value instanceof IntegerValue) {
			return ((IntegerValue) value).value;
		} else if(value instanceof StringValue) {
			return ((StringValue)value).value;
		} else if(value instanceof BooleanValue) {
			return ((BooleanValue)value).value;
		} else if(value instanceof UnlimitedNaturalValue) {
			return ((UnlimitedNaturalValue)value).value.naturalValue;
		}
		return null;
	}

	private EStructuralFeature getEStructuralFeature(StructuralFeature structuralFeature) {
		for(Classifier classifier : structuralFeature.featuringClassifier) {
			if(classifier instanceof Class_) {
				EClass eClass = instanceMap.getEClass((Class_)classifier);
				EStructuralFeature eStructuralFeature = getEStructuralFeatureByName(eClass, structuralFeature.name);
				if(eStructuralFeature != null) {
					return eStructuralFeature;
				}
			}
		}
		return null;
	}

	private EStructuralFeature getEStructuralFeatureByName(EClass eClass, String featureName) {
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (featureName.equals(feature.getName())) {
				return feature;
			}
		}
		return null;
	}
	
	private void handleExtensionalValueDestruction(ExtensionalValueEvent event) {
		ExtensionalValue extensionalValue = event.getExtensionalValue();
		if (extensionalValue instanceof Object_) {
			handleObjectDestruction((Object_) extensionalValue);
		}

	}

	private void handleObjectDestruction(Object_ object) {
		EObject eObject = instanceMap.getEObject(object);

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

	private EClass getEClass(Object_ object) {
		Class_ class_ = object.types.get(0);
		EClass eClass = instanceMap.getEClass(class_);
		return eClass;
	}

	private void execute(Command cmd) {
		editingDomain.getCommandStack().execute(cmd);
	}
}
