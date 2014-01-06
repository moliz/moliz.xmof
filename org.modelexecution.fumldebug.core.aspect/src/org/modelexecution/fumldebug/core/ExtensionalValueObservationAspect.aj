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
import fUML.Semantics.Actions.IntermediateActions.StructuralFeatureActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Classes.Kernel.StructuredValue;
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
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Class_List;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

public aspect ExtensionalValueObservationAspect {
	
	// needed for link destruction event (type is set to null before link is removed from locus)
	private HashMap<Link, Association> linkTypes = new HashMap<Link, Association>();

	private pointcut activityExecution(ActivityExecution execution) : call (void Execution.execute()) && withincode(ParameterValueList Executor.execute(Behavior, Object_, ParameterValueList)) && target(execution);
	
	before(ActivityExecution execution) : activityExecution(execution) {
		for(ExtensionalValue value : execution.locus.extensionalValues) {
			if(value instanceof Link) {
				Link link = (Link)value;
				linkTypes.put(link, link.type);
			}
		}
	}
		
	/**
	 * New extensional value at locus
	 */
	private pointcut locusNewExtensionalValue(ExtensionalValue value) : call (void Locus.add(ExtensionalValue)) && args(value) && !(cflow(execution(Value Value.copy())));

	after(ExtensionalValue value) : locusNewExtensionalValue(value) {
		if (value.getClass() == Object_.class || value.getClass() == Link.class) {			
			ExecutionContext.getInstance().eventHandler.handleExtensionalValueCreation(value);			
		}		
		if(value.getClass() == Link.class) {		
			Link link = (Link)value;
			linkTypes.put(link, link.type);
		}
	}

	/**
	 * Extensional value removed from locus
	 */
	private pointcut locusExtensionalValueRemoved() : call (ExtensionalValue ExtensionalValueList.remove(int)) && withincode(void Locus.remove(ExtensionalValue));

	after() returning (Object obj) : locusExtensionalValueRemoved() {
		if (obj.getClass() == Object_.class || obj.getClass() == Link.class) {
			ExtensionalValue value = (ExtensionalValue) obj;
			if(value instanceof Link) {
				Link link = (Link)value;
				link.type = linkTypes.get(link);
			}
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
	private pointcut featureValueSetValue(Object_ obj, StructuralFeature feature, ValueList values, int position) : call(void StructuredValue.setFeatureValue(StructuralFeature, ValueList, int)) && target(obj) && args(feature, values, position) && cflow(execution(void ActionActivation.doAction())) && !cflow(execution(Object_ Locus.instantiate(Class_))) && !(cflow(execution(void ReclassifyObjectActionActivation.doAction()))) && !(cflow(execution(Value Value.copy())));

	void around(Object_ obj, StructuralFeature feature, ValueList values, int position) : featureValueSetValue(obj, feature, values, position) {	
		FeatureValue oldFeatureValue = obj.getFeatureValue(feature);
		ValueList oldValues = new ValueList();
		int oldValuesCount = 0;
		if(oldFeatureValue != null && oldFeatureValue.values != null) {
			oldValues.addAll(oldFeatureValue.values);
			oldValuesCount = oldFeatureValue.values.size();
		}
		proceed(obj, feature, values, position);
		FeatureValue newFeatureValue = obj.getFeatureValue(feature);
		int newValuesCount = 0;
		if(newFeatureValue != null && newFeatureValue.values != null) {
			newValuesCount = newFeatureValue.values.size();
		}
		
		if(oldValuesCount > 0) {
			ExecutionContext.getInstance().eventHandler.handleFeatureValueRemoved(obj, oldFeatureValue, oldValues, 0);
		}
		
		if(newValuesCount > 0) {
			ExecutionContext.getInstance().eventHandler.handleFeatureValueAdded(obj, newFeatureValue, values, position);
		}
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

	private pointcut valueAddedToFeatureValue(AddStructuralFeatureValueActionActivation activation, Value value) : call (void ValueList.addValue(Value)) && args(value) && this(activation) && withincode(void ActionActivation.doAction()) && !(cflow(execution(Value Value.copy())));

	after(AddStructuralFeatureValueActionActivation activation, Value value) : valueAddedToFeatureValue(activation, value) {
		Object_ object = getTargetObject(activation);
		StructuralFeature feature = getTargetStructuralFeature(activation);
		if(object != null && feature != null && value != null) {
			ValueList values = new ValueList();
			values.add(value);
			FeatureValue featureValue = object.getFeatureValue(feature);
			ExecutionContext.getInstance().eventHandler.handleFeatureValueAdded(object, featureValue, values, featureValue.values.size()-values.size());
		}
	}
	
	private pointcut valueAddedToFeatureValueAtPosition(AddStructuralFeatureValueActionActivation activation, Value value, int position) : call (void ValueList.addValue(int, Value)) && args(position, value) && this(activation) && withincode(void ActionActivation.doAction()) && !(cflow(execution(Value Value.copy())));

	after(AddStructuralFeatureValueActionActivation activation, Value value, int position) : valueAddedToFeatureValueAtPosition(activation, value, position) {
		Object_ object = getTargetObject(activation);
		StructuralFeature feature = getTargetStructuralFeature(activation);
		if(object != null && feature != null && value != null) {
			ValueList values = new ValueList();
			values.add(value);
			FeatureValue featureValue = object.getFeatureValue(feature);
			ExecutionContext.getInstance().eventHandler.handleFeatureValueAdded(object, featureValue, values, position);
		}
	}
	
	private pointcut valueRemovedFromFeatureValue(StructuralFeatureActionActivation activation, int position) : call (Value ValueList.remove(int)) && args(position) && this(activation) && withincode(void ActionActivation.doAction());

	Value around(StructuralFeatureActionActivation activation, int position) /*returning (Value value)*/: valueRemovedFromFeatureValue(activation, position) {
		Value value = proceed(activation, position);
		Object_ object = getTargetObject(activation);
		StructuralFeature feature = getTargetStructuralFeature(activation);
		if(object != null && feature != null && value != null) {
			ValueList values = new ValueList();
			values.add(value);
			FeatureValue featureValue = object.getFeatureValue(feature);
			ExecutionContext.getInstance().eventHandler.handleFeatureValueRemoved(object, featureValue, values, position);
		}
		return value;
	}
	
	private Object_ getTargetObject(StructuralFeatureActionActivation activation) {
		return structfeaturevalueactions.get(activation);
	}
	
	private StructuralFeature getTargetStructuralFeature(StructuralFeatureActionActivation activation) {
		StructuralFeatureAction action = (StructuralFeatureAction) activation.node;
		StructuralFeature feature = action.structuralFeature;
		if(feature instanceof Property) {
			Property property = (Property)feature;
			if(property.association != null) {
				feature = null;
			}
		}
		return feature;
	}

}
