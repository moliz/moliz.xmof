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
	
	public static Property createProperty(String name, int lower, int upper, Class_ class_)
	{
		Property property= new Property();
		property.setName(name);
		property.setLower(lower);
		property.setUpper(upper);
		class_.ownedAttribute.add(property);
		return property;
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
	
	private static LiteralString createValueSpecification(String value) {
		LiteralString value_valuespec = new LiteralString();
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	private static LiteralInteger createValueSpecification(int value) {
		LiteralInteger value_valuespec = new LiteralInteger();		
		value_valuespec.value = value;
		return value_valuespec;
	}

	private static LiteralBoolean createValueSpecification(boolean value) {
		LiteralBoolean value_valuespec = new LiteralBoolean();		
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	private static LiteralUnlimitedNatural createValueSpecification(
			UnlimitedNatural value) {
		LiteralUnlimitedNatural value_valuespec = new LiteralUnlimitedNatural();		
		value_valuespec.value = value;
		return value_valuespec;
	}
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature)
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
		
		addstructuralfeaturevalueaction.isReplaceAll = true;
		
		addstructuralfeaturevalueaction.activity = activity;
		activity.addNode(addstructuralfeaturevalueaction);
		
		return addstructuralfeaturevalueaction;
	}
	
	public static CallBehaviorAction createCallBehaviorAction(Activity activity, String name, Behavior behavior) {
		CallBehaviorAction action = new CallBehaviorAction();
		action.setName(name);
		
		action.behavior = behavior;
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
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
	
}
