/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core;

import java.util.HashMap;

import fUML.Semantics.Actions.BasicActions.ActionActivation;
import fUML.Semantics.Actions.BasicActions.PinActivation;
import fUML.Semantics.Actions.CompleteActions.ReclassifyObjectActionActivation;
import fUML.Semantics.Actions.IntermediateActions.AddStructuralFeatureValueActionActivation;
import fUML.Semantics.Actions.IntermediateActions.ReadStructuralFeatureActionActivation;
import fUML.Semantics.Actions.IntermediateActions.RemoveStructuralFeatureValueActionActivation;
import fUML.Semantics.Actions.IntermediateActions.StructuralFeatureActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.FeatureValueList;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Actions.IntermediateActions.StructuralFeatureAction;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Class_List;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public aspect ExtensionalValueObservationAspect {

	/**
	 * New extensional value at locus
	 */
	private pointcut locusNewExtensionalValue(ExtensionalValue value) : call (void Locus.add(ExtensionalValue)) && args(value) && !(cflow(execution(Value Value.copy())));

	after(ExtensionalValue value) : locusNewExtensionalValue(value) {
		if (value.getClass() == Object_.class || value.getClass() == Link.class) {
			ExecutionContext.getInstance().eventHandler.handleExtensionalValueCreation(value);
		}
	}

	/**
	 * Extensional value removed from locus
	 */
	private pointcut locusExtensionalValueRemoved() : call (ExtensionalValue ExtensionalValueList.remove(int)) && withincode(void Locus.remove(ExtensionalValue));

	after() returning (Object obj) : locusExtensionalValueRemoved() {
		if (obj.getClass() == Object_.class || obj.getClass() == Link.class) {
			ExtensionalValue value = (ExtensionalValue) obj;
			ExecutionContext.getInstance().eventHandler.handleExtensionalValueDestruction(value);
		}
	}

	/**
	 * Classifier removed/added as type of object
	 */
	private HashMap<ReclassifyObjectActionActivation, Object_> reclassifications = new HashMap<ReclassifyObjectActionActivation, Object_>();

	private pointcut reclassifyObjectAction(ReclassifyObjectActionActivation activation) : execution (void ReclassifyObjectActionActivation.doAction()) && target(activation);

	before(ReclassifyObjectActionActivation activation) : reclassifyObjectAction(activation) {
		if (activation.pinActivations.size() > 0) {
			PinActivation pinactivation = activation.pinActivations.get(0);
			if (pinactivation.heldTokens.size() > 0) {
				if (pinactivation.heldTokens.get(0) instanceof ObjectToken) {
					ObjectToken token = (ObjectToken) pinactivation.heldTokens.get(0);
					if (token.value instanceof Reference) {
						Reference ref = (Reference) token.value;
						Object_ obj = ref.referent;
						if (obj != null) {
							reclassifications.put(activation, obj);
						}
					}
				}
			}
		}
	}

	after(ReclassifyObjectActionActivation activation) : reclassifyObjectAction(activation) {
		reclassifications.remove(activation);
	}

	private pointcut classifierRemovedAsObjectType(ReclassifyObjectActionActivation activation) : call (void Class_List.removeValue(int)) && this(activation) && withincode(void ActionActivation.doAction());

	after(ReclassifyObjectActionActivation activation) : classifierRemovedAsObjectType(activation) {
		Object_ o = reclassifications.get(activation);
		ExecutionContext.getInstance().eventHandler.handleObjectTypeRemoval(o);
	}

	private pointcut classifierAddedAsObjectType(ReclassifyObjectActionActivation activation) : call (void Class_List.addValue(Class_)) && this(activation) && withincode(void ActionActivation.doAction());

	after(ReclassifyObjectActionActivation activation) : classifierAddedAsObjectType(activation) {
		Object_ o = reclassifications.get(activation);
		ExecutionContext.getInstance().eventHandler.handleObjectTypeAddition(o);
	}

	/**
	 * Feature values removed from object
	 */
	private pointcut compoundValueRemoveFeatureValue(Object_ o) : call(FeatureValue FeatureValueList.remove(int)) && this(o);

	after(Object_ o) returning(Object value): compoundValueRemoveFeatureValue(o) {
		ExecutionContext.getInstance().eventHandler.handleFeatureValueDestruction(o, (FeatureValue) value);
	}

	/**
	 * Feature values added to object
	 */
	private pointcut compoundValueAddFeatureValue(Object_ o, FeatureValue value) : call(void FeatureValueList.addValue(FeatureValue)) && this(o) && args(value) && !cflow(execution(Object_ Locus.instantiate(Class_))) && !(cflow(execution(Value Value.copy())));

	after(Object_ o, FeatureValue value) : compoundValueAddFeatureValue(o, value) {
		ExecutionContext.getInstance().eventHandler.handleFeatureValueCreation(o, value);
	}

	/**
	 * Value of feature value set
	 */
	private pointcut featureValueSetValue(Object_ obj, FeatureValue value, ValueList values) : set(public ValueList FeatureValue.values) && this(obj) && target(value) && args(values) && withincode(void CompoundValue.setFeatureValue(StructuralFeature, ValueList, int)) && !cflow(execution(Object_ Locus.instantiate(Class_))) && !(cflow(execution(void ReclassifyObjectActionActivation.doAction()))) && !(cflow(execution(Value Value.copy())));

	after(Object_ obj, FeatureValue value, ValueList values) : featureValueSetValue(obj, value, values) {
		ExecutionContext.getInstance().eventHandler.handleFeatureValueChange(obj, value);
	}

	private HashMap<StructuralFeatureActionActivation, Object_> structfeaturevalueactions = new HashMap<StructuralFeatureActionActivation, Object_>();

	private pointcut structuralFeatureValueAction(StructuralFeatureActionActivation activation) : execution (void StructuralFeatureActionActivation.doAction()) && target(activation) && if(!(activation instanceof ReadStructuralFeatureActionActivation));

	before(StructuralFeatureActionActivation activation) : structuralFeatureValueAction(activation) {
		PinActivation pinactivation = activation.getPinActivation(((StructuralFeatureAction) activation.node).object);
		if (pinactivation != null) {
			if (pinactivation.heldTokens.size() > 0) {
				if (pinactivation.heldTokens.get(0) instanceof ObjectToken) {
					ObjectToken token = (ObjectToken) pinactivation.heldTokens
							.get(0);
					Object_ obj = null;
					if (token.value instanceof Reference) {
						Reference ref = (Reference) token.value;
						obj = ref.referent;
					} else if (token.value instanceof Object_) {
						obj = (Object_) token.value;
					}

					if (obj != null) {
						structfeaturevalueactions.put(activation, obj);
					}
				}
			}
		}
	}

	after(StructuralFeatureActionActivation activation) : structuralFeatureValueAction(activation) {
		structfeaturevalueactions.remove(activation);
	}

	private pointcut valueAddedToFeatureValue(AddStructuralFeatureValueActionActivation activation) : (call (void ValueList.addValue(Value)) || call (void ValueList.addValue(int, Value)) ) && this(activation) && withincode(void ActionActivation.doAction()) && !(cflow(execution(Value Value.copy())));

	after(AddStructuralFeatureValueActionActivation activation) : valueAddedToFeatureValue(activation) {
		handleFeatureValueChangedEvent(activation);
	}

	private pointcut valueRemovedFromFeatureValue(RemoveStructuralFeatureValueActionActivation activation) : call (Value ValueList.remove(int)) && this(activation) && withincode(void ActionActivation.doAction());

	after(RemoveStructuralFeatureValueActionActivation activation) : valueRemovedFromFeatureValue(activation) {
		handleFeatureValueChangedEvent(activation);
	}

	private void handleFeatureValueChangedEvent(StructuralFeatureActionActivation activation) {
		Object_ o = structfeaturevalueactions.get(activation);
		FeatureValue featureValue = o.getFeatureValue(((StructuralFeatureAction) activation.node).structuralFeature);
		if (featureValue.feature instanceof Property) {
			Property p = (Property) featureValue.feature;
			if (p.association != null) {
				return;
			}
		}
		ExecutionContext.getInstance().eventHandler.handleFeatureValueChange(o, featureValue);
	}

}
