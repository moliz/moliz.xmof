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

import java.util.Arrays;
import java.util.List;

import UMLPrimitiveTypes.UnlimitedNatural;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.BasicActions.OutputPinList;
import fUML.Syntax.Actions.CompleteActions.ReadExtentAction;
import fUML.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction;
import fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import fUML.Syntax.Actions.CompleteActions.ReduceAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.CreateLinkAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData;
import fUML.Syntax.Actions.IntermediateActions.LinkEndData;
import fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData;
import fUML.Syntax.Actions.IntermediateActions.ReadLinkAction;
import fUML.Syntax.Actions.IntermediateActions.ReadSelfAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.TestIdentityAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.Clause;
import fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.AggregationKind;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.ClassifierList;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.LiteralString;
import fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.ParameterList;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.PropertyList;
import fUML.Syntax.Classes.Kernel.StructuralFeature;
import fUML.Syntax.Classes.Kernel.Type;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

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
		Property property = createProperty(name, lower, upper, type);
		property.setIsUnique(isUnique);
		property.aggregation = aggregationkind;
		class_.addOwnedAttribute(property);
		return property;
	}
	
	public static Property createProperty(String name, int lower, int upper, Type type) {
		Property property= new Property();
		property.setName(name);
		property.setLower(lower);
		property.setUpper(upper);
		property.setType(type);		
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
		InitialNode initialnode = createInitialNode(name);
		initialnode.activity = activity;
		activity.addNode(initialnode);
		return initialnode;
	}	
	
	public static InitialNode createInitialNode(String name) {
		InitialNode initialnode = new InitialNode();
		initialnode.setName(name);
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
		ForkNode forknode = createForkNode(name);	
		forknode.activity = activity;
		activity.addNode(forknode);
		return forknode;
	}	
	
	public static ForkNode createForkNode(String name) {
		ForkNode forknode = new ForkNode();		
		forknode.setName(name);
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
		CreateObjectAction createobjectaction = createCreateObjectAction(name, class_);

		createobjectaction.activity = activity;
		activity.addNode(createobjectaction);
		
		return createobjectaction;
	}
	
	public static CreateObjectAction createCreateObjectAction(String name, Class_ class_) {
		CreateObjectAction createobjectaction = new CreateObjectAction();
		createobjectaction.setName(name);
		
		OutputPin outputpin_createobject = new OutputPin();
		outputpin_createobject.setName("OutputPin (" + name + ")");
		outputpin_createobject.setType(class_);
		createobjectaction.result = outputpin_createobject;		
		createobjectaction.output.add(outputpin_createobject);
		
		createobjectaction.classifier = class_;
		
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
	
	private static ValueSpecificationAction createValueSpecificationAction(String name) {
		ValueSpecificationAction valuespecaction = new ValueSpecificationAction();
		valuespecaction.setName(name);
		
		OutputPin outputpin_valuespec = new OutputPin();
		outputpin_valuespec.setName("OutputPin (" + name + ")");
		valuespecaction.result = outputpin_valuespec;
		valuespecaction.output.add(outputpin_valuespec);		
		
		return valuespecaction;
	}
	
	private static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(name);

		valuespecaction.activity = activity;
		activity.addNode(valuespecaction);
		
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, String value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);
		LiteralString value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}	
	
	public static ValueSpecificationAction createValueSpecificationAction(String name, String value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(name);					
		LiteralString value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;		
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, int value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);
		LiteralInteger value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;	
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(String name, int value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(name);
		LiteralInteger value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;		
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, boolean value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);		
		LiteralBoolean value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(String name, boolean value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(name);		
		LiteralBoolean value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}
	
	public static ValueSpecificationAction createValueSpecificationAction(Activity activity, String name, UnlimitedNatural value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(activity, name);		
		LiteralUnlimitedNatural value_valuespec = createValueSpecification(value);
		valuespecaction.value = value_valuespec;
		return valuespecaction;
	}	
	
	public static ValueSpecificationAction createValueSpecificationAction(String name, UnlimitedNatural value) {
		ValueSpecificationAction valuespecaction = createValueSpecificationAction(name);		
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
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature) {
		return createAddStructuralFeatureValueAction(activity, name, feature, true);
	}
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature, boolean isReplace) {
		AddStructuralFeatureValueAction action = createAddStructuralFeatureValueAction(name, feature, isReplace);
		action.activity = activity;
		activity.addNode(action);		
		return action;
	}
	
	public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction(String name, StructuralFeature feature, boolean isReplace) {
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

		return addstructuralfeaturevalueaction;		
	}
	
	public static ReadStructuralFeatureAction createReadStructuralFeatureAction(Activity activity, String name, StructuralFeature feature) {
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
	
	public static ClearStructuralFeatureAction createClearStructuralFeatureAction(Activity activity, String name, StructuralFeature feature) {
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
	
	public static RemoveStructuralFeatureValueAction createRemoveStructuralFeatureValueAction(Activity activity, String name, StructuralFeature feature, boolean removeAt) {
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
		CallBehaviorAction action = createCallBehaviorAction(name, behavior);

		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static CallBehaviorAction createCallBehaviorAction(String name, Behavior behavior) {
		CallBehaviorAction action = new CallBehaviorAction();
		action.setName(name);
		
		action.behavior = behavior;
		
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
	
	public static CallBehaviorAction createCallBehaviorAction(String name, Behavior behavior, int resultoutputpins) {
		CallBehaviorAction action = createCallBehaviorAction(name, behavior);
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
	
	public static CallBehaviorAction createCallBehaviorAction(Activity activity, String name, Behavior behavior, int resultoutputpins, int inputpins) {
		CallBehaviorAction action = createCallBehaviorAction(activity, name, behavior, resultoutputpins);
		for(int i=0;i<inputpins;++i){
			InputPin pin = new InputPin();
			pin.setName("InputPin " + (i+1) + "(" + name + ")");
			pin.setLower(1);
			pin.setUpper(-1);
			action.argument.add(pin);
			action.input.add(pin);
		}	
		return action;
	}
	
	public static CallBehaviorAction createCallBehaviorAction(String name, Behavior behavior, int resultoutputpins, int inputpins) {
		CallBehaviorAction action = createCallBehaviorAction(name, behavior, resultoutputpins);
		for(int i=0;i<inputpins;++i){
			InputPin pin = new InputPin();
			pin.setName("InputPin " + (i+1) + "(" + name + ")");
			pin.setLower(1);
			pin.setUpper(-1);
			action.argument.add(pin);
			action.input.add(pin);
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
	
	public static ReadLinkAction createReadLinkAction(Activity activity, String name, PropertyList linkendsinput, Property linktoread) {
		ReadLinkAction action = new ReadLinkAction();
		action.setName(name);
		
		for(int i=0;i<linkendsinput.size();++i) {
			Property linkend = linkendsinput.get(i);			
			
			InputPin pin = new InputPin();
			pin.setName("InputPin (" + name + ": property=" + linkend.name + ")");
			action.input.add(pin);
			
			LinkEndData enddata = new LinkEndData();
			enddata.end = linkend;
			enddata.value = pin;
			action.addEndData(enddata);
		}
		
		LinkEndData enddata = new LinkEndData();
		enddata.end = linktoread;
		action.addEndData(enddata);
		
		OutputPin pin_result = new OutputPin();
		action.output.add(pin_result);
		action.result = pin_result;
		
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
		Parameter param = createParameter(name, direction);
		param.name = name + " (" + activity.name +  ")";
		activity.ownedParameter.add(param);
		return param;
	}
	
	public static Parameter createParameter(Activity activity, String name, ParameterDirectionKind direction, Type type) {
		Parameter param = createParameter(activity, name, direction);
		param.type = type;
		return param;
	}
	
	public static Parameter createParameter(String name, ParameterDirectionKind direction) {
		Parameter param = new Parameter();
		param.setDirection(direction);
		param.name = name;
		return param;
	}
	
	public static Parameter createParameter(String name, ParameterDirectionKind direction, Type type) {
		Parameter param = createParameter(name, direction);
		param.type = type;
		return param;
	}
	
	public static ControlFlow createControlFlow(Activity activity, ActivityNode source, ActivityNode target)
	{
		ControlFlow cflow = createControlFlow(source, target);

		cflow.activity = activity;
		activity.addEdge(cflow);
		
		return cflow;
	}
	
	public static ControlFlow createControlFlow(ActivityNode source, ActivityNode target)
	{
		ControlFlow cflow = new ControlFlow();
		cflow.setName("ControlFlow " + source.name + " --> " + target.name);
		cflow.source = source;
		cflow.target = target;
		
		source.outgoing.add(cflow);
		target.incoming.add(cflow);
		
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
	
	public static ObjectFlow createDecisionInputFlow(StructuredActivityNode node, OutputPin source, DecisionNode target) {
		ObjectFlow oflow = createObjectFlow(node, source, target);
		target.setDecisionInputFlow(oflow);
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(StructuredActivityNode node, ActivityNode source, ActivityNode target) {
//		ObjectFlow oflow = createObjectFlow(node.activity, source, target);
		ObjectFlow oflow = createObjectFlow(source, target);
		source.inStructuredNode = node;
		target.inStructuredNode = node;
		node.edge.add(oflow);
		
//		node.activity.edge.remove(oflow);
//		oflow.activity = null;
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(StructuredActivityNode node, ActivityNode source, ActivityNode target, boolean guard) {
		ObjectFlow oflow = createObjectFlow(node, source, target);
		LiteralBoolean guardliteral = new LiteralBoolean();
		guardliteral.value = guard;
		oflow.guard = guardliteral;
		
		node.activity.edge.remove(oflow);
		oflow.activity = null;
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, ActivityNode source, ActivityNode target) {
		ObjectFlow oflow = createObjectFlow(source, target);
				
		oflow.activity = activity;
		activity.addEdge(oflow);
		
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(ActivityNode source, ActivityNode target) {
		ObjectFlow oflow = new ObjectFlow();
		oflow.setName("ObjectFlow " + source.name + " --> " + target.name);
		oflow.source = source;
		oflow.target = target;
		
		source.outgoing.add(oflow);
		target.incoming.add(oflow);
				
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, ActivityNode source, ActivityNode target, boolean guard) {
		ObjectFlow oflow = createObjectFlow(activity, source, target);
		LiteralBoolean guardliteral = new LiteralBoolean();
		guardliteral.value = guard;
		oflow.guard = guardliteral;
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(Activity activity, ActivityNode source, ActivityNode target, int guard) {
		ObjectFlow oflow = createObjectFlow(activity, source, target);
		LiteralInteger guardliteral = new LiteralInteger();
		guardliteral.value = guard;
		oflow.guard = guardliteral;
		return oflow;
	}
	
	public static ObjectFlow createObjectFlow(StructuredActivityNode structuredActivityNode, ActivityNode source, ActivityNode target, int guard) {
		ObjectFlow oflow = createObjectFlow(structuredActivityNode, source, target);
		LiteralInteger guardliteral = new LiteralInteger();
		guardliteral.value = guard;
		oflow.guard = guardliteral;
		return oflow;
	}
	
	public static CallOperationAction createCallOperationAction(Activity activity, String name, Operation operation) {
		CallOperationAction action = new CallOperationAction();
		action.setName(name);
	
		action.setOperation(operation);
		
		InputPin targetpin = new InputPin();
		targetpin.setName("InputPin " + " target (" + name + ")");
		action.setTarget(targetpin);
		action.input.add(targetpin);
		
		for(Parameter param : operation.ownedParameter) {
			if(param.direction == ParameterDirectionKind.in || param.direction == ParameterDirectionKind.inout) {
				InputPin inputpin =  new InputPin();
				inputpin.setName("InputPin " + param.name + " (" + name + " )");
				action.argument.add(inputpin);
				action.input.add(inputpin);
			} else {
				OutputPin outputpin = new OutputPin();
				outputpin.setName("OutputPin " + param.name + "(" + name + ")");
				action.result.add(outputpin);
				action.output.add(outputpin);
			}
		}
		
		action.activity = activity;
		activity.node.add(action);
		return action;
	}

	public static Operation createOperation(String name, ParameterList parameter, Behavior method, Class_ class_) {
		Operation operation = new Operation();
		operation.name = name;
		if(parameter != null) {
			operation.ownedParameter.addAll(parameter);
		}
		operation.addMethod(method);
		class_.addOwnedOperation(operation);
		class_.addOwnedBehavior(method);
		return operation;
	}

	public static ReadSelfAction createReadSelfAction(Activity activity, String name) {
		ReadSelfAction action = new ReadSelfAction();
		action.setName(name);
		
		OutputPin pin = new OutputPin();
		pin.setName("OutputPin (" + name + ")");
		action.setResult(pin);		
		
		action.activity = activity;
		activity.node.add(action);
		return action;
	}
	
	public static ExpansionRegion createExpansionRegion(String name, ExpansionKind mode, List<ActivityNode> nodes, int inexpansionnodes, int outexpansionnodes) {
		ExpansionRegion region = new ExpansionRegion();
		region.setName(name);		
		region.setMode(mode);
		
		addNodesToStructuredActivityNode(region, nodes.toArray(new ActivityNode[nodes.size()]));
		
		for(int i=0;i<(inexpansionnodes + outexpansionnodes);++i) {
			ExpansionNode expnode = new ExpansionNode();			
			
			if(i<inexpansionnodes) {
				expnode.setName("ExpansionNode input " + (i+1) + " (" + name + ")");
				region.inputElement.add(expnode);
				expnode.regionAsInput = region;
			} else {
				expnode.setName("ExpansionNode output " + (i-inexpansionnodes+1) + " (" + name + ")");
				region.outputElement.add(expnode);
				expnode.regionAsOutput = region;
			}
		}		
		return region;
	}
	
	public static ExpansionRegion createExpansionRegion(Activity activity, String name, ExpansionKind mode, List<ActivityNode> nodes, int inexpansionnodes, int outexpansionnodes) {
		ExpansionRegion region = createExpansionRegion(name, mode, nodes, inexpansionnodes, outexpansionnodes);

		for(ExpansionNode expansionNode : region.inputElement) {
			expansionNode.activity = activity;
			activity.node.add(expansionNode);
		}
		
		for(ExpansionNode expansionNode : region.outputElement) {
			expansionNode.activity = activity;
			activity.node.add(expansionNode);
		}
		
		region.activity = activity;
		activity.node.add(region);
		return region;
	}
	
	public static ExpansionRegion createExpansionRegion(StructuredActivityNode structuredActivityNode, String name, ExpansionKind mode, List<ActivityNode> nodes, int inexpansionnodes, int outexpansionnodes) {
		ExpansionRegion region = createExpansionRegion(name, mode, nodes, inexpansionnodes, outexpansionnodes);
		region.setName(name);		
		region.setMode(mode);
		
		for(ExpansionNode expansionNode : region.inputElement) {
			expansionNode.inStructuredNode = structuredActivityNode;
			structuredActivityNode.node.add(expansionNode);
		}
		
		for(ExpansionNode expansionNode : region.outputElement) {
			expansionNode.inStructuredNode = structuredActivityNode;
			structuredActivityNode.node.add(expansionNode);
		}
		
		region.inStructuredNode = structuredActivityNode;
		structuredActivityNode.node.add(region);
		return region;
	}
	
	public static ExpansionRegion createExpansionRegion(Activity activity, String name, ExpansionKind mode, List<ActivityNode> nodes, int inexpansionnodes, int outexpansionnodes, int inputpins) {
		ExpansionRegion region = createExpansionRegion(activity, name, mode, nodes, inexpansionnodes, outexpansionnodes);
		
		for(int i=0;i<inputpins;++i) {
			InputPin inputpin = new InputPin();
			inputpin.name = "InputPin input " + (i+1) + " (" + region.name + ")";
			inputpin.setLower(1);
			inputpin.setUpper(1);
			region.addStructuredNodeInput(inputpin);
		}
		
		return region;
	}

	public static TestIdentityAction createTestIdentityAction(Activity activity, String name) {
		TestIdentityAction action = createTestIdentityAction(name);
				
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}
	
	public static TestIdentityAction createTestIdentityAction(String name) {
		TestIdentityAction action = new TestIdentityAction();
		action.setName(name);
		
		OutputPin outputpin = new OutputPin();
		outputpin.setName("OutputPin result (" + name + ")");
		action.result = outputpin;
		action.output.add(outputpin);
		
		InputPin input_first = new InputPin();
		input_first.setName("InputPin first (" + name + ")");
		input_first.setLower(0);
		input_first.setUpper(-1);		
		action.first = input_first;
		action.input.add(input_first);
		
		InputPin input_second = new InputPin();
		input_second.setName("InputPin second (" + name + ")");
		input_second.setLower(1);
		input_second.setUpper(1);
		action.second = input_second;
		action.input.add(input_second);
				
		return action;
	}

	public static ReadIsClassifiedObjectAction createReadIsClassifiedObjectAction(
			Activity activity, String name, Classifier classifier) {
		ReadIsClassifiedObjectAction action = new ReadIsClassifiedObjectAction();
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
		
		action.classifier = classifier;
		
		action.activity = activity;
		activity.addNode(action);
		
		return action;
	}

	public static InputPin createInputPin(String name, int lower, int upper) {
		InputPin pin = new InputPin();
		pin.setName("InputPin " + name);
		pin.setLower(lower);
		pin.setUpper(upper);
		return pin;
	}

	public static OutputPin createOutputPin(String name, int lower, int upper) {
		OutputPin pin = new OutputPin();
		pin.setName("OutputPin " + name);
		pin.setLower(lower);
		pin.setUpper(upper);
		return pin;
	}

	public static StructuredActivityNode createStructuredActivityNode(
			Activity activity, String name) {
		StructuredActivityNode structurednode = createStructuredActivityNode(name);
		
		activity.node.add(structurednode);
		structurednode.activity = activity;
		
		return structurednode;
	}
	
	public static StructuredActivityNode createStructuredActivityNode(String name) {
		StructuredActivityNode structurednode = new StructuredActivityNode();
		structurednode.setName(name);
		
		return structurednode;
	}

	public static Object_ createObject(Class_ type) {
		Object_ o = new Object_();
		o.types.add(type);
		o.createFeatureValues();
		return o;
	}
	
	public static StringValue createStringValue(String string) {
		StringValue stringValue = new StringValue();
		stringValue.value = string;
		return stringValue;
	}
	
	public static ParameterValue createParameterValue(Parameter parameter, Value... values) {
		ValueList valuelist = new ValueList();
		valuelist.addAll(Arrays.asList(values));
		
		ParameterValue parametervalue = new ParameterValue();
		parametervalue.parameter = parameter;
		parametervalue.values = valuelist;
		
		return parametervalue;
	}
			
	public static ParameterValueList createParameterValueList(ParameterValue... parametervalues) {
		ParameterValueList parametervaluelist = new ParameterValueList();
		parametervaluelist.addAll(Arrays.asList(parametervalues));
		return parametervaluelist;
	}

	public static ReadExtentAction createReadExtentAction(Activity activity,
			String name, Class_ class_) {
		ReadExtentAction action = new ReadExtentAction();
		
		action.classifier = class_;
		action.name = name;

		OutputPin result = new OutputPin();
		result.setName("OutputPin result (" + name + ")");
		action.result = result;
		action.output.add(result);
		
		action.activity = activity;
		activity.node.add(action);
		
		return action;
	}

	public static IntegerValue createIntegerValue(int integer) {
		IntegerValue integerValue = new IntegerValue();
		integerValue.value = integer;
		return integerValue;
	}

	public static ReduceAction createReduceAction(Activity activity, String name, OpaqueBehavior behavior) {
		ReduceAction action = new ReduceAction();
		
		action.reducer = behavior;
		
		InputPin input = new InputPin();
		input.setLower(2);
		input.setUpper(-1);
		action.collection = input;
		action.input.add(input);
		
		OutputPin result = new OutputPin();
		action.result = result;
		action.output.add(result);
		
		action.name = name;
		
		action.activity = activity;
		activity.node.add(action);
		
		return action;
	}
	
	public static Clause createClause(OutputPin decider, OutputPin... bodyOutput) {
		Clause clause = new Clause();
				
		clause.decider = decider;
		
		for(OutputPin output : bodyOutput) {
			clause.bodyOutput.add(output);
		}
		
		return clause;
	}
	
	public static void addTestNodesToClause(Clause clause, ExecutableNode... nodes) {
		for(ExecutableNode node : nodes) {
			clause.addTest(node);
		}
	}
	
	public static void addBodyNodesToClause(Clause clause, ExecutableNode... nodes) {
		for(ExecutableNode node : nodes) {
			clause.addBody(node);
		}
	}
	
	public static ConditionalNode createConditionalNode(String name, int resultPins, Clause... clauses) {
		ConditionalNode node = new ConditionalNode();
		node.name = name;	
		for(Clause clause : clauses) {
			node.addClause(clause);
			
			for(ExecutableNode b : clause.body) {
				node.addNode(b);
			}
			
			for(ExecutableNode t : clause.test) {
				node.addNode(t);
			}
//			node.addStructuredNodeOutput(clause.decider);			
		}
		
		for(int i=0;i<resultPins;++i) {
			OutputPin pin = new OutputPin();
			pin.name = "OutputPin result" + (i+1) + " (" + name + ")";
			node.result.add(pin);
			node.addStructuredNodeOutput(pin);
		}
		
		return node;
	}
	
	public static ConditionalNode createConditionalNode(Activity activity, String name, int resultPins, Clause... clauses) {
		ConditionalNode node = createConditionalNode(name, resultPins, clauses);
		node.activity = activity;
		activity.node.add(node);
		return node;
	}
	
	public static void addInputPinsToStructuredActivityNode(StructuredActivityNode node, InputPin... inputpins) {
		for(InputPin inputpin : inputpins) {
			node.addStructuredNodeInput(inputpin);
		}
	}
	
	public static void addOutputPinsToStructuredActivityNode(StructuredActivityNode node, OutputPin... outputpins) {
		for(OutputPin outputpin : outputpins) {
			node.addStructuredNodeOutput(outputpin);
		}
	}
	
	public static void addNodesToStructuredActivityNode(StructuredActivityNode node, ActivityNode... nodes) {
		for(ActivityNode n : nodes) {
			node.addNode(n);
			if(n.activity != null) {
				n.activity.node.remove(n);
				n.activity = null;
			}
		}
	}	
	
	public static void removeNodesFromStructuredActivityNode(StructuredActivityNode node, ActivityNode... nodes) {
		for(ActivityNode n : nodes) {
			node.node.remove(n);
			n.inStructuredNode = null;
		}
	}
	
	public static void addEdgesToStructuredActivityNode(StructuredActivityNode node, ActivityEdge... edges) {
		for(ActivityEdge edge : edges) {
			node.addEdge(edge);
		}
	}

	public static void setObjectProperty(Object_ object, Property property, Value... values) {
		ValueList valuelist = new ValueList();
		valuelist.addAll(Arrays.asList(values));
		object.setFeatureValue(property, valuelist, 0);			
	}

	public static LoopNode createLoopNode(String name, int result, boolean testFirst) {
		LoopNode node = new LoopNode();
		node.setName(name);
		node.isTestedFirst = testFirst;
		for(int i=0;i<result;++i) {
			OutputPin resultpin = new OutputPin();
			resultpin.setName("OutputPin result" + (i+1) + " (" + name + ")");
			node.addResult(resultpin);
		}
		return node;
	}
	
	public static LoopNode createLoopNode(Activity activity, String name, int result, boolean testFirst) {
		LoopNode node = createLoopNode(name, result, testFirst);
		activity.addNode(node);
		return node;
	}

	public static void setLoopNodeDecider(LoopNode loopnode, OutputPin decider) {
		loopnode.setDecider(decider);		
	}

	public static void addTestNodesToLoopNode(LoopNode loopnode, ExecutableNode... nodes) {
		addNodesToStructuredActivityNode(loopnode, nodes);
		for(ExecutableNode node : nodes) {
			loopnode.addTest(node);
		}
	}

	public static void addBodyNodesToLoopNode(LoopNode loopnode, ExecutableNode... nodes) {
		addNodesToStructuredActivityNode(loopnode, nodes);
		for(ExecutableNode node : nodes) {
			loopnode.addBodyPart(node);
		}
	}

	public static Link createLink(Association association,
			Property end1, Object_ object1, Property end2, Object_ object2) {
		Link link = new Link();
		link.type = association;
		
		FeatureValue featureValue1 = new FeatureValue();
		featureValue1.feature = end1;
		featureValue1.values.add(object1);
		featureValue1.position = 0;
		
		FeatureValue featureValue2 = new FeatureValue();
		featureValue2.feature = end2;
		featureValue2.values.add(object2);
		featureValue2.position = 0;
		
		link.featureValues.add(featureValue1);
		link.featureValues.add(featureValue2);
		
		return link;
	}

}
