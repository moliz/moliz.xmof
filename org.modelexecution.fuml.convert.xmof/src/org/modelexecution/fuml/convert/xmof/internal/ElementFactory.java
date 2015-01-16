/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.xmof.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.SendSignalAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.AcceptEventAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadExtentAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.ReduceAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.CompleteActions.StartObjectBehaviorAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.CreateObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndCreationData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.LinkEndDestructionData;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadLinkAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadSelfAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.TestIdentityAction;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.Clause;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.ConditionalNode;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.LoopNode;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlFlow;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.InitialNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;
import org.modelexecution.xmof.Syntax.Classes.Kernel.EnumValue;
import org.modelexecution.xmof.Syntax.Classes.Kernel.InstanceSpecification;
import org.modelexecution.xmof.Syntax.Classes.Kernel.InstanceValue;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralBoolean;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralInteger;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralNull;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralString;
import org.modelexecution.xmof.Syntax.Classes.Kernel.LiteralUnlimitedNatural;
import org.modelexecution.xmof.Syntax.Classes.Kernel.Slot;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Reception;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Signal;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.SignalEvent;
import org.modelexecution.xmof.Syntax.CommonBehaviors.Communications.Trigger;

/**
 * Factory for {@link fUML.Syntax.Classes.Kernel.Element fUML elements}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ElementFactory {

	public fUML.Syntax.Classes.Kernel.Element create(EObject element) {
		String className = element.eClass().getName();
		if ("EReference".equals(className) && element instanceof EReference) {
			return create((EReference) element);
		} else if ("EAttribute".equals(className) && element instanceof EAttribute) {
			return create((EAttribute) element);
		} else if ("EClass".equals(className) && element instanceof EClass) {
			return create((EClass) element);
		} else if ("BehavioredEClass".equals(className) && element instanceof EClass) {
			return create((EClass) element);
		} else if ("EDataType".equals(className) && element instanceof EDataType) {
			return create((EDataType) element);
		} else if ("EEnum".equals(className) && element instanceof EEnum) {
			return create((EEnum) element);
		} else if ("EEnumLiteral".equals(className) && element instanceof EEnumLiteral) {
			return create((EEnumLiteral) element);
		} else if ("EOperation".equals(className) && element instanceof EOperation) {
			return create((EOperation) element);
		} else if ("BehavioredEOperation".equals(className) && element instanceof EOperation) {
			return create((EOperation) element);
		} else if ("EPackage".equals(className) && element instanceof EPackage) {
			return create((EPackage) element);
		} else if ("EParameter".equals(className) && element instanceof EParameter) {
			return create((EParameter) element);
		} else if ("DirectedParameter".equals(className) && element instanceof EParameter) {
			return create((EParameter) element);
		} else if ("ETypedElement".equals(className) && element instanceof ETypedElement) {
			return create((ETypedElement) element);
		} else if ("InstanceSpecification".equals(className) && element instanceof InstanceSpecification) {
			return create((InstanceSpecification) element);
		} else if ("InstanceValue".equals(className) && element instanceof InstanceValue) {
			return create((InstanceValue) element);
		} else if ("LiteralBoolean".equals(className) && element instanceof LiteralBoolean) {
			return create((LiteralBoolean) element);
		} else if ("LiteralInteger".equals(className) && element instanceof LiteralInteger) {
			return create((LiteralInteger) element);
		} else if ("LiteralNull".equals(className) && element instanceof LiteralNull) {
			return create((LiteralNull) element);
		} else if ("LiteralString".equals(className) && element instanceof LiteralString) {
			return create((LiteralString) element);
		} else if ("LiteralUnlimitedNatural".equals(className) && element instanceof LiteralUnlimitedNatural) {
			return create((LiteralUnlimitedNatural) element);
		} else if ("EnumValue".equals(className) && element instanceof EnumValue) {
			return create((EnumValue) element);
		} else if ("Slot".equals(className) && element instanceof Slot) {
			return create((Slot) element);
		} else if ("FunctionBehavior".equals(className) && element instanceof FunctionBehavior) {
			return create((FunctionBehavior) element);
		} else if ("OpaqueBehavior".equals(className) && element instanceof OpaqueBehavior) {
			return create((OpaqueBehavior) element);
		} else if ("Reception".equals(className) && element instanceof Reception) {
			return create((Reception) element);
		} else if ("Signal".equals(className) && element instanceof Signal) {
			return create((Signal) element);
		} else if ("SignalEvent".equals(className) && element instanceof SignalEvent) {
			return create((SignalEvent) element);
		} else if ("Trigger".equals(className) && element instanceof Trigger) {
			return create((Trigger) element);
		} else if ("CallBehaviorAction".equals(className) && element instanceof CallBehaviorAction) {
			return create((CallBehaviorAction) element);
		} else if ("CallOperationAction".equals(className) && element instanceof CallOperationAction) {
			return create((CallOperationAction) element);
		} else if ("InputPin".equals(className) && element instanceof InputPin) {
			return create((InputPin) element);
		} else if ("OutputPin".equals(className) && element instanceof OutputPin) {
			return create((OutputPin) element);
		} else if ("SendSignalAction".equals(className) && element instanceof SendSignalAction) {
			return create((SendSignalAction) element);
		} else if ("AddStructuralFeatureValueAction".equals(className) && element instanceof AddStructuralFeatureValueAction) {
			return create((AddStructuralFeatureValueAction) element);
		} else if ("ClearAssociationAction".equals(className) && element instanceof ClearAssociationAction) {
			return create((ClearAssociationAction) element);
		} else if ("ClearStructuralFeatureAction".equals(className) && element instanceof ClearStructuralFeatureAction) {
			return create((ClearStructuralFeatureAction) element);
		} else if ("RemoveStructuralFeatureValueAction".equals(className) && element instanceof RemoveStructuralFeatureValueAction) {
			return create((RemoveStructuralFeatureValueAction) element);
		} else if ("CreateLinkAction".equals(className) && element instanceof CreateLinkAction) {
			return create((CreateLinkAction) element);
		} else if ("CreateObjectAction".equals(className) && element instanceof CreateObjectAction) {
			return create((CreateObjectAction) element);
		} else if ("DestroyLinkAction".equals(className) && element instanceof DestroyLinkAction) {
			return create((DestroyLinkAction) element);
		} else if ("DestroyObjectAction".equals(className) && element instanceof DestroyObjectAction) {
			return create((DestroyObjectAction) element);
		} else if ("LinkEndCreationData".equals(className) && element instanceof LinkEndCreationData) {
			return create((LinkEndCreationData) element);
		} else if ("LinkEndData".equals(className) && element instanceof LinkEndData) {
			return create((LinkEndData) element);
		} else if ("LinkEndDestructionData".equals(className) && element instanceof LinkEndDestructionData) {
			return create((LinkEndDestructionData) element);
		} else if ("ReadLinkAction".equals(className) && element instanceof ReadLinkAction) {
			return create((ReadLinkAction) element);
		} else if ("ReadSelfAction".equals(className) && element instanceof ReadSelfAction) {
			return create((ReadSelfAction) element);
		} else if ("ReadStructuralFeatureAction".equals(className) && element instanceof ReadStructuralFeatureAction) {
			return create((ReadStructuralFeatureAction) element);
		} else if ("TestIdentityAction".equals(className) && element instanceof TestIdentityAction) {
			return create((TestIdentityAction) element);
		} else if ("ValueSpecificationAction".equals(className) && element instanceof ValueSpecificationAction) {
			return create((ValueSpecificationAction) element);
		} else if ("AcceptEventAction".equals(className) && element instanceof AcceptEventAction) {
			return create((AcceptEventAction) element);
		} else if ("ReadExtentAction".equals(className) && element instanceof ReadExtentAction) {
			return create((ReadExtentAction) element);
		} else if ("ReadIsClassifiedObjectAction".equals(className) && element instanceof ReadIsClassifiedObjectAction) {
			return create((ReadIsClassifiedObjectAction) element);
		} else if ("ReclassifyObjectAction".equals(className) && element instanceof ReclassifyObjectAction) {
			return create((ReclassifyObjectAction) element);
		} else if ("ReduceAction".equals(className) && element instanceof ReduceAction) {
			return create((ReduceAction) element);
		} else if ("StartClassifierBehaviorAction".equals(className) && element instanceof StartClassifierBehaviorAction) {
			return create((StartClassifierBehaviorAction) element);
		} else if ("StartObjectBehaviorAction".equals(className) && element instanceof StartObjectBehaviorAction) {
			return create((StartObjectBehaviorAction) element);
		} else if ("Activity".equals(className) && element instanceof Activity) {
			return create((Activity) element);
		} else if ("ActivityFinalNode".equals(className) && element instanceof ActivityFinalNode) {
			return create((ActivityFinalNode) element);
		} else if ("ActivityParameterNode".equals(className) && element instanceof ActivityParameterNode) {
			return create((ActivityParameterNode) element);
		} else if ("ControlFlow".equals(className) && element instanceof ControlFlow) {
			return create((ControlFlow) element);
		} else if ("DecisionNode".equals(className) && element instanceof DecisionNode) {
			return create((DecisionNode) element);
		} else if ("ForkNode".equals(className) && element instanceof ForkNode) {
			return create((ForkNode) element);
		} else if ("InitialNode".equals(className) && element instanceof InitialNode) {
			return create((InitialNode) element);
		} else if ("JoinNode".equals(className) && element instanceof JoinNode) {
			return create((JoinNode) element);
		} else if ("MergeNode".equals(className) && element instanceof MergeNode) {
			return create((MergeNode) element);
		} else if ("ObjectFlow".equals(className) && element instanceof ObjectFlow) {
			return create((ObjectFlow) element);
		} else if ("Clause".equals(className) && element instanceof Clause) {
			return create((Clause) element);
		} else if ("ConditionalNode".equals(className) && element instanceof ConditionalNode) {
			return create((ConditionalNode) element);
		} else if ("LoopNode".equals(className) && element instanceof LoopNode) {
			return create((LoopNode) element);
		} else if ("StructuredActivityNode".equals(className) && element instanceof StructuredActivityNode) {
			return create((StructuredActivityNode) element);
		} else if ("ExpansionNode".equals(className) && element instanceof ExpansionNode) {
			return create((ExpansionNode) element);
		} else if ("ExpansionRegion".equals(className) && element instanceof ExpansionRegion) {
			return create((ExpansionRegion) element);
		}
		return null;
	}

	public fUML.Syntax.Classes.Kernel.Association create(EReference element) {
		return new fUML.Syntax.Classes.Kernel.Association();
	}

	public fUML.Syntax.Classes.Kernel.Property create(EAttribute element) {
		return new fUML.Syntax.Classes.Kernel.Property();
	}

	public fUML.Syntax.Classes.Kernel.Class_ create(EClass element) {
		return new fUML.Syntax.Classes.Kernel.Class_();
	}

	public fUML.Syntax.Classes.Kernel.DataType create(EDataType element) {
		return new fUML.Syntax.Classes.Kernel.DataType();
	}

	public fUML.Syntax.Classes.Kernel.Enumeration create(EEnum element) {
		return new fUML.Syntax.Classes.Kernel.Enumeration();
	}

	public fUML.Syntax.Classes.Kernel.EnumerationLiteral create(
			EEnumLiteral element) {
		return new fUML.Syntax.Classes.Kernel.EnumerationLiteral();
	}

	public fUML.Syntax.Classes.Kernel.InstanceSpecification create(
			InstanceSpecification element) {
		return new fUML.Syntax.Classes.Kernel.InstanceSpecification();
	}

	public fUML.Syntax.Classes.Kernel.InstanceValue create(InstanceValue element) {
		return new fUML.Syntax.Classes.Kernel.InstanceValue();
	}
	
	public fUML.Syntax.Classes.Kernel.InstanceValue create(EnumValue element) {
		return new fUML.Syntax.Classes.Kernel.InstanceValue();
	}

	public fUML.Syntax.Classes.Kernel.LiteralBoolean create(
			LiteralBoolean element) {
		return new fUML.Syntax.Classes.Kernel.LiteralBoolean();
	}

	public fUML.Syntax.Classes.Kernel.LiteralInteger create(
			LiteralInteger element) {
		return new fUML.Syntax.Classes.Kernel.LiteralInteger();
	}

	public fUML.Syntax.Classes.Kernel.LiteralNull create(LiteralNull element) {
		return new fUML.Syntax.Classes.Kernel.LiteralNull();
	}

	public fUML.Syntax.Classes.Kernel.LiteralString create(LiteralString element) {
		return new fUML.Syntax.Classes.Kernel.LiteralString();
	}

	public fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural create(
			LiteralUnlimitedNatural element) {
		return new fUML.Syntax.Classes.Kernel.LiteralUnlimitedNatural();
	}

	public fUML.Syntax.Classes.Kernel.Operation create(EOperation element) {
		return new fUML.Syntax.Classes.Kernel.Operation();
	}

	public fUML.Syntax.Classes.Kernel.Package create(EPackage element) {
		return new fUML.Syntax.Classes.Kernel.Package();
	}

	public fUML.Syntax.Classes.Kernel.Parameter create(EParameter element) {
		return new fUML.Syntax.Classes.Kernel.Parameter();
	}

	public fUML.Syntax.Classes.Kernel.Slot create(Slot element) {
		return new fUML.Syntax.Classes.Kernel.Slot();
	}

	public fUML.Syntax.Classes.Kernel.TypedElement create(ETypedElement element) {
		return new fUML.Syntax.Classes.Kernel.TypedElement();
	}

	public fUML.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior create(
			FunctionBehavior element) {
		return new fUML.Syntax.CommonBehaviors.BasicBehaviors.FunctionBehavior();
	}

	public fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior create(
			OpaqueBehavior element) {
		return new fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior();
	}

	public fUML.Syntax.CommonBehaviors.Communications.Reception create(
			Reception element) {
		return new fUML.Syntax.CommonBehaviors.Communications.Reception();
	}

	public fUML.Syntax.CommonBehaviors.Communications.Signal create(
			Signal element) {
		return new fUML.Syntax.CommonBehaviors.Communications.Signal();
	}

	public fUML.Syntax.CommonBehaviors.Communications.SignalEvent create(
			SignalEvent element) {
		return new fUML.Syntax.CommonBehaviors.Communications.SignalEvent();
	}

	public fUML.Syntax.CommonBehaviors.Communications.Trigger create(
			Trigger element) {
		return new fUML.Syntax.CommonBehaviors.Communications.Trigger();
	}

	public fUML.Syntax.Actions.BasicActions.CallBehaviorAction create(
			CallBehaviorAction element) {
		return new fUML.Syntax.Actions.BasicActions.CallBehaviorAction();
	}

	public fUML.Syntax.Actions.BasicActions.CallOperationAction create(
			CallOperationAction element) {
		return new fUML.Syntax.Actions.BasicActions.CallOperationAction();
	}

	public fUML.Syntax.Actions.BasicActions.InputPin create(InputPin element) {
		return new fUML.Syntax.Actions.BasicActions.InputPin();
	}

	public fUML.Syntax.Actions.BasicActions.OutputPin create(OutputPin element) {
		return new fUML.Syntax.Actions.BasicActions.OutputPin();
	}

	public fUML.Syntax.Actions.BasicActions.SendSignalAction create(
			SendSignalAction element) {
		return new fUML.Syntax.Actions.BasicActions.SendSignalAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction create(
			AddStructuralFeatureValueAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction create(
			ClearAssociationAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction create(
			ClearStructuralFeatureAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.CreateLinkAction create(
			CreateLinkAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.CreateLinkAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.CreateObjectAction create(
			CreateObjectAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.CreateObjectAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction create(
			DestroyLinkAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction create(
			DestroyObjectAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData create(
			LinkEndCreationData element) {
		return new fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData();
	}

	public fUML.Syntax.Actions.IntermediateActions.LinkEndData create(
			LinkEndData element) {
		return new fUML.Syntax.Actions.IntermediateActions.LinkEndData();
	}

	public fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData create(
			LinkEndDestructionData element) {
		return new fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData();
	}

	public fUML.Syntax.Actions.IntermediateActions.ReadLinkAction create(
			ReadLinkAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ReadLinkAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.ReadSelfAction create(
			ReadSelfAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ReadSelfAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction create(
			ReadStructuralFeatureAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction create(
			RemoveStructuralFeatureValueAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.TestIdentityAction create(
			TestIdentityAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.TestIdentityAction();
	}

	public fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction create(
			ValueSpecificationAction element) {
		return new fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction();
	}

	public fUML.Syntax.Actions.CompleteActions.AcceptEventAction create(
			AcceptEventAction element) {
		return new fUML.Syntax.Actions.CompleteActions.AcceptEventAction();
	}

	public fUML.Syntax.Actions.CompleteActions.ReadExtentAction create(
			ReadExtentAction element) {
		return new fUML.Syntax.Actions.CompleteActions.ReadExtentAction();
	}

	public fUML.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction create(
			ReadIsClassifiedObjectAction element) {
		return new fUML.Syntax.Actions.CompleteActions.ReadIsClassifiedObjectAction();
	}

	public fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction create(
			ReclassifyObjectAction element) {
		return new fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction();
	}

	public fUML.Syntax.Actions.CompleteActions.ReduceAction create(
			ReduceAction element) {
		return new fUML.Syntax.Actions.CompleteActions.ReduceAction();
	}

	public fUML.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction create(
			StartClassifierBehaviorAction element) {
		return new fUML.Syntax.Actions.CompleteActions.StartClassifierBehaviorAction();
	}

	public fUML.Syntax.Actions.CompleteActions.StartObjectBehaviorAction create(
			StartObjectBehaviorAction element) {
		return new fUML.Syntax.Actions.CompleteActions.StartObjectBehaviorAction();
	}

	public fUML.Syntax.Activities.IntermediateActivities.Activity create(
			Activity element) {
		return new fUML.Syntax.Activities.IntermediateActivities.Activity();
	}

	public fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode create(
			ActivityFinalNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode create(
			ActivityParameterNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.ControlFlow create(
			ControlFlow element) {
		return new fUML.Syntax.Activities.IntermediateActivities.ControlFlow();
	}

	public fUML.Syntax.Activities.IntermediateActivities.DecisionNode create(
			DecisionNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.DecisionNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.ForkNode create(
			ForkNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.ForkNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.InitialNode create(
			InitialNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.InitialNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.JoinNode create(
			JoinNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.JoinNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.MergeNode create(
			MergeNode element) {
		return new fUML.Syntax.Activities.IntermediateActivities.MergeNode();
	}

	public fUML.Syntax.Activities.IntermediateActivities.ObjectFlow create(
			ObjectFlow element) {
		return new fUML.Syntax.Activities.IntermediateActivities.ObjectFlow();
	}

	public fUML.Syntax.Activities.CompleteStructuredActivities.Clause create(
			Clause element) {
		return new fUML.Syntax.Activities.CompleteStructuredActivities.Clause();
	}

	public fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode create(
			ConditionalNode element) {
		return new fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode();
	}

	public fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode create(
			LoopNode element) {
		return new fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode();
	}

	public fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode create(
			StructuredActivityNode element) {
		return new fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode();
	}

	public fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode create(
			ExpansionNode element) {
		return new fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode();
	}

	public fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion create(
			ExpansionRegion element) {
		return new fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion();
	}

}
