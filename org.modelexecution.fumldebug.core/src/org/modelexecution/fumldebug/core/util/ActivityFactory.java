/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.util;

import UMLPrimitiveTypes.UnlimitedNatural;
import fUML.Syntax.Actions.BasicActions.*;
import fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ActivityFactory {

	public static Class_ createClass(String name) {
		Class_ class_ = new Class_();
		class_.setName(name);
		return class_;
	}	
	
	public static Property createProperty(String name, int lower, int upper, Type type, Class_ class_) {
		return createProperty(name, lower, upper, type, class_, false, AggregationKind.none);
	}
	
	public static Property createProperty(String name, int lower, int upper, Type type, Class_ class_, boolean isUnique) {
		return createProperty(name, lower, upper, type, class_, isUnique, AggregationKind.none);
	}
	public static Property createProperty(String name, int lower, int upper, Type type, Class_ class_, boolean isUnique, AggregationKind aggregationkind) {
		Property property= new Property();
		property.setName(name);
		property.setLower(lower);
		property.setUpper(upper);
		property.setType(type);
		property.setIsUnique(isUnique);
		property.aggregation = aggregationkind;
		class_.addOwnedAttribute(property);
		return property;
	}
	
	public static Association createAssociation(String name, PropertyList memberEnds) {
		Association association = new Association();
		association.name = name;
		association.memberEnd.addAll(memberEnds);
		for(int i=0;i<memberEnds.size();++i) {
			memberEnds.get(i).association = association;
		}
		return association;
	}
	
	public static Activity createActivity(String name) {
		Activity activity = new Activity();
		activity.setName(name);
		return activity;
	}
	
	public static InitialNode createInitialNode(Activity activity, String name) {
		InitialNode initialnode = new InitialNode();
		initialnode.setName(name);
		initialnode.activity = activity;
		activity.addNode(initialnode);
		return initialnode;
	}	
	
	public static ActivityFinalNode createActivityFinalNode(Activity activity, String name) {
		ActivityFinalNode finalnode = new ActivityFinalNode();
		finalnode.setName(name);
		finalnode.activity = activity;
		activity.addNode(finalnode);
		return finalnode;
	}	
	
	public static ForkNode createForkNode(Activity activity, String name) {
		ForkNode forknode = new ForkNode();		
		forknode.setName(name);
		forknode.activity = activity;
		activity.addNode(forknode);
		return forknode;
	}	
	
	public static MergeNode createMergeNode(Activity activity, String name) {
		MergeNode mergenode = new MergeNode();		
		mergenode.setName(name);
		mergenode.activity = activity;
		activity.addNode(mergenode);
		return mergenode;
	}	
	
	public static JoinNode createJoinNode(Activity activity, String name) {
		JoinNode joinnode = new JoinNode();		
		joinnode.setName(name);
		joinnode.activity = activity;
		activity.addNode(joinnode);
		return joinnode;
	}	
	
	public static DecisionNode createDecisionNode(Activity activity, String name) {
		DecisionNode decisionnode = new DecisionNode();		
		decisionnode.setName(name);
		decisionnode.activity = activity;
		activity.addNode(decisionnode);						
		return decisionnode;
	}	
	
	public static DecisionNode createDecisionNode(Activity activity, String name, Behavior decisionBehavior) {
		DecisionNode decisionnode = createDecisionNode(activity, name);		
		decisionnode.setDecisionInput(decisionBehavior);
		return decisionnode;
	}	
	
	public static CreateObjectAction createCreateObjectAction(Activity activity, String name, Class_ class_) {
		CreateObjectAction createobjectaction = new CreateObjectAction();
		createobjectaction.setName(name);
		
		OutputPin outputpin_createobject = new OutputPin();
		outputpin_createobject.setName("OutputPin (" + name + ")");
		createobjectaction.result = outputpin_createobject;		
		createobjectaction.output.add(outputpin_createobject);
		
		createobjectaction.classifier = class_;
		
		createobjectaction.activity = activity;
		activity.addNode(createobjectaction);
		
		return createobjectaction;
	}
	
	public static DestroyObjectAction createDestroyObjectAction(Activity activity, String name, boolean isDestroyLinks, boolean isDestroyOwnedObjects) {
		DestroyObjectAction destroyobjectaction = new DestroyObjectAction();
		destroyobjectaction.setName(name);
		destroyobjectaction.setIsDestroyLinks(isDestroyLinks);
		destroyobjectaction.setIsDestroyOwnedObjects(isDestroyOwnedObjects);
		
		InputPin inputpin_destroyobject = new InputPin();
		inputpin_destroyobject.setName("InputPin (" + name + ")");
		destroyobjectaction.input.add(inputpin_destroyobject);		
		destroyobjectaction.target = inputpin_destroyobject;
		
		destroyobjectaction.activity = activity;
		activity.addNode(destroyobjectaction);
		
		return destroyobjectaction;
	}
	
	private static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name)
	{
		ValueSpecificationAction valuespecaction = new ValueSpecificationAction();
		valuespecaction.setName(name);
		
		OutputPin outputpin_valuespec = new OutputPin();
		outputpin_valuespec.setName("OutputPin (" + name + ")");
		valuespecaction.result = outputpin_valuespec;
		valuespecaction.output.add(outputpin_valuespec);		
		
		valuespecaction.activity = activity;
		activity.addNode(valuespecaction);
		
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, String value)
	{
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);					
		LiteralString value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;		
		return valuespecaction;
	}	
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, int value)
	{
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);
		LiteralInteger value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;		
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, boolean value)
	{
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);		
		LiteralBoolean value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, UnlimitedNatural value)
	{
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);		
		LiteralUnlimitedNatural value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}	
	
	public static LiteralString createValueSpecification(String value) {
		LiteralString value_valuespec = new LiteralString();
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	public static LiteralInteger createValueSpecification(int value) {
		LiteralInteger value_valuespec = new LiteralInteger();		
		value_valuespec.value = value;
		return value_valuespec;
	}

	public static LiteralBoolean createValueSpecification(boolean value) {
		LiteralBoolean value_valuespec = new LiteralBoolean();		
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	public static LiteralUnlimitedNatural createValueSpecification(
			UnlimitedNatural value) {
		LiteralUnlimitedNatural value_valuespec = new LiteralUnlimitedNatural();		
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature)
	{
		return createAddStructuralFeatureValueAction(activity, name, feature, true);
	}
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature, boolean isReplace)
	{
		AddStructuralFeatureValueAction addstructuralfeaturevalueaction = new AddStructuralFeatureValueAction();
		addstructuralfeaturevalueaction.setName(name);
		
		OutputPin outputpin_addstructuralfeaturevalue = new OutputPin();
		outputpin_addstructuralfeaturevalue.setName("OutputPin result (" + name + ")");
		addstructuralfeaturevalueaction.result = outputpin_addstructuralfeaturevalue;
		addstructuralfeaturevalueaction.output.add(outputpin_addstructuralfeaturevalue);
		
		InputPin input_object_addstructuralfeaturevalue = new InputPin();
		input_object_addstructuralfeaturevalue.setName("InputPin object (" + name + ")");
		input_object_addstructuralfeaturevalue.setLower(1);
		input_object_addstructuralfeaturevalue.setUpper(1);		
		addstructuralfeaturevalueaction.object = input_object_addstructuralfeaturevalue;
		addstructuralfeaturevalueaction.input.add(input_object_addstructuralfeaturevalue);
		
		InputPin input_value_addstructuralfeaturevalue = new InputPin();
		input_value_addstructuralfeaturevalue.setName("InputPin value (" + name + ")");
		input_value_addstructuralfeaturevalue.setLower(1);
		input_value_addstructuralfeaturevalue.setUpper(1);
		addstructuralfeaturevalueaction.value = input_value_addstructuralfeaturevalue;
		addstructuralfeaturevalueaction.input.add(input_value_addstructuralfeaturevalue);
		
		addstructuralfeaturevalueaction.structuralFeature = feature;
		
		addstructuralfeaturevalueaction.isReplaceAll = isReplace;
		
		addstructuralfeaturevalueaction.activity = activity;
		activity.addNode(addstructuralfeaturevalueaction);
		
		return addstructuralfeaturevalueaction;
	}
	
	public static ReadStructuralFeatureAction createReadStructuralFeatureAction(Activity activity, String name, StructuralFeature feature)
	{
		ReadStructuralFeatureAction action = new ReadStructuralFeatureAction();
		action.setName(name);
		
		OutputPin outputpin = new OutputPin();
		outputpin.setName("OutputPin result (" + name + ")");
		action.result = outputpin;
		action.output.add(outputpin);
		
		InputPin input_object = new InputPin();
		input_object.setName("InputPin object (" + name + ")");
		input_object.setLower(1);
		input_object.setUpper(1);		
		action.object = input_object;
		action.input.add(input_object);
			
		action.structuralFeature = feature;
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static ClearStructuralFeatureAction createClearStructuralFeatureAction(Activity activity, String name, StructuralFeature feature)
	{
		ClearStructuralFeatureAction action = new ClearStructuralFeatureAction();
		action.setName(name);
		
		OutputPin outputpin = new OutputPin();
		outputpin.setName("OutputPin result (" + name + ")");
		action.result = outputpin;
		action.output.add(outputpin);
		
		InputPin input_object = new InputPin();
		input_object.setName("InputPin object (" + name + ")");
		input_object.setLower(1);
		input_object.setUpper(1);		
		action.object = input_object;
		action.input.add(input_object);
		
		action.structuralFeature = feature;
			
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static RemoveStructuralFeatureValueAction createRemoveStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature, boolean removeAt)
	{
		RemoveStructuralFeatureValueAction action = new RemoveStructuralFeatureValueAction();
		action.setName(name);
		
		OutputPin outputpin = new OutputPin();
		outputpin.setName("OutputPin result (" + name + ")");
		action.result = outputpin;
		action.output.add(outputpin);
		
		InputPin input_object = new InputPin();
		input_object.setName("InputPin object (" + name + ")");
		input_object.setLower(1);
		input_object.setUpper(1);		
		action.object = input_object;
		action.input.add(input_object);
		
		InputPin input_value = new InputPin();
		input_value.setName("InputPin value (" + name + ")");
		input_value.setLower(1);
		input_value.setUpper(1);		
		action.value = input_value;
		action.input.add(input_value);
		
		if(removeAt) {
			InputPin input_removeAt = new InputPin();
			input_removeAt.setName("InputPin removeAt (" + name + ")");
			input_removeAt.setLower(1);
			input_removeAt.setUpper(1);		
			action.removeAt = input_removeAt;
			action.input.add(input_removeAt);
		}
		
		action.structuralFeature = feature;					
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static CallBehaviorAction createCallBehaviorAction(Activity activity, String name, Behavior behavior) {
		CallBehaviorAction action = new CallBehaviorAction();
		action.setName(name);
		
		action.behavior = behavior;
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static CallBehaviorAction createCallBehaviorAction(Activity activity, String name, Behavior behavior, int resultoutputpins) {
		CallBehaviorAction action = createCallBehaviorAction(activity, name, behavior);
		for(int i=0;i<resultoutputpins;++i){
			OutputPin pin = new OutputPin();
			pin.setName("OutputPin " + (i+1) + "(" + name + ")");
			OutputPinList output_callaction = new OutputPinList();
			output_callaction.add(pin);
			action.result.add(pin);
			action.output.add(pin);
		}	
		return action;
	}
	
	public static CreateLinkAction createCreateLinkAction(Activity activity, String name, PropertyList linkends) {
		CreateLinkAction action = new CreateLinkAction();
		action.setName(name);
		
		for(int i=0;i<linkends.size();++i) {
			Property linkend = linkends.get(i);			
			
			InputPin pin = new InputPin();
			pin.setName("InputPin (" + name + ": property=" + linkend.name + ")");
			action.input.add(pin);
			
			LinkEndCreationData creationdata = new LinkEndCreationData();
			creationdata.end = linkend;
			creationdata.value = pin;
			action.addEndData(creationdata);
		}
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static DestroyLinkAction createDestroyLinkAction(Activity activity, String name, PropertyList linkends) {
		DestroyLinkAction action = new DestroyLinkAction();
		action.setName(name);
		
		for(int i=0;i<linkends.size();++i) {
			Property linkend = linkends.get(i);			
			
			InputPin pin = new InputPin();
			pin.setName("InputPin (" + name + ": property=" + linkend.name + ")");
			action.input.add(pin);
			
			LinkEndDestructionData destructiondata = new LinkEndDestructionData();
			destructiondata.end = linkend;
			destructiondata.value = pin;
			action.addEndData(destructiondata);
		}
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static ClearAssociationAction createClearAssociationAction(Activity activity, String name, Association association) {
		ClearAssociationAction action = new ClearAssociationAction();
		action.setName(name);
		
		InputPin pin = new InputPin();
		pin.setName("InputPin (" + name + ")");
		action.object = pin;
		action.input.add(pin);
			
		action.association = association;
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static ReclassifyObjectAction createReclassifyObjectAction(Activity activity, String name, ClassifierList newClassifier, ClassifierList oldClassifier) {
		ReclassifyObjectAction action = new ReclassifyObjectAction();
		action.setName(name);
		
		InputPin pin = new InputPin();
		pin.setName("InputPin (" + name + ")");
		action.object = pin;
		action.input.add(pin);
			
		action.newClassifier.addAll(newClassifier);
		action.oldClassifier.addAll(oldClassifier);				
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static ActivityParameterNode createActivityParameterNode(Activity activity, String name, Parameter parameter) {
		ActivityParameterNode paramnode = new ActivityParameterNode();
		paramnode.name = name + " (activity=" + activity.name + " parameter=" + parameter.name + ")";
		paramnode.activity = activity;
		paramnode.parameter = parameter;
		activity.node.add(paramnode);
		return paramnode;
	}
	
	public static Parameter createParameter(Activity activity, String name, ParameterDirectionKind direction) {
		Parameter param = new Parameter();
		param.setDirection(direction);
		param.name = name + " (" + activity.name +  ")";
		activity.ownedParameter.add(param);
		return param;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target)
	{
		ControlFlow cflow = new ControlFlow();
		cflow.setName("ControlFlow " + source.name + " --> " + target.name);
		cflow.source = source;
		cflow.target = target;
		
		source.outgoing.add(cflow);
		target.incoming.add(cflow);
		
		cflow.activity = activity;
		activity.addEdge(cflow);
		
		return cflow;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target, String guard)
	{
		ControlFlow cflow = createControlFlow(activity, source, target);
		cflow.guard = createValueSpecification(guard);						
		return cflow;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target, int guard)
	{
		ControlFlow cflow = createControlFlow(activity, source, target);
		cflow.guard = createValueSpecification(guard);						
		return cflow;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target, boolean guard)
	{
		ControlFlow cflow = createControlFlow(activity, source, target);
		cflow.guard = createValueSpecification(guard);						
		return cflow;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target, UnlimitedNatural guard)
	{
		ControlFlow cflow = createControlFlow(activity, source, target);
		cflow.guard = createValueSpecification(guard);						
		return cflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, OutputPin source, InputPin target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, ActivityNode source, InputPin target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, OutputPin source, ActivityNode target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, OutputPin source, ActivityParameterNode target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, OutputPin source, DecisionNode target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, ForkNode source, DecisionNode target)
	{
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createDecisionInputFlow(Activity activity, OutputPin source, DecisionNode target) {
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
		target.setDecisionInputFlow(oflow);
		
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, DecisionNode source, ActivityParameterNode target) {
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
				
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
}
