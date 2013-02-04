/*
* Copyright (c) 2012 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API and generator
*/
package org.modelexecution.fuml.convert.xmof.internal.gen;
    	
import javax.annotation.Generated;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;
import java.util.Collection;
import java.util.ArrayList;
import org.modelexecution.fuml.convert.xmof.internal.ecore.*;

@Generated(value="Generated by org.modelexecution.fuml.convert.xmof.gen.ElementPopulatorGenerator.xtend")
public class ElementPopulatorSuite {

	private Collection<IElementPopulator> elementPopulators = new ArrayList<>();

	private ConversionResultImpl result;

	public ElementPopulatorSuite(ConversionResultImpl result) {
		this.result = result;
		initializePopulators();
	}

	private void initializePopulators() {
		elementPopulators.add(new ClassAndAssociationPopulator());
    	elementPopulators.add(new NamedElementPopulator());
    	elementPopulators.add(new EnumerationPopulator());
    	elementPopulators.add(new EnumerationLiteralPopulator());
    	elementPopulators.add(new TypedElementPopulator());
    	elementPopulators.add(new MultiplicityElementPopulator());
    	elementPopulators.add(new StructuralFeaturePopulator());
    	elementPopulators.add(new DirectedParameterPopulator());
    	elementPopulators.add(new OperationPopulator());
    	elementPopulators.add(new PackagePopulator());
		elementPopulators.add(new OpaqueBehaviorPopulator());
		elementPopulators.add(new BehaviorPopulator());
		elementPopulators.add(new BehavioredClassifierPopulator());
		elementPopulators.add(new TriggerPopulator());
		elementPopulators.add(new SignalPopulator());
		elementPopulators.add(new SignalEventPopulator());
		elementPopulators.add(new ReceptionPopulator());
		elementPopulators.add(new InstanceSpecificationPopulator());
		elementPopulators.add(new SlotPopulator());
		elementPopulators.add(new InstanceValuePopulator());
		elementPopulators.add(new LiteralBooleanPopulator());
		elementPopulators.add(new LiteralIntegerPopulator());
		elementPopulators.add(new LiteralStringPopulator());
		elementPopulators.add(new LiteralUnlimitedNaturalPopulator());
		elementPopulators.add(new ActivityEdgePopulator());
		elementPopulators.add(new ActivityPopulator());
		elementPopulators.add(new ActivityNodePopulator());
		elementPopulators.add(new DecisionNodePopulator());
		elementPopulators.add(new ActivityParameterNodePopulator());
		elementPopulators.add(new LoopNodePopulator());
		elementPopulators.add(new ClausePopulator());
		elementPopulators.add(new ConditionalNodePopulator());
		elementPopulators.add(new StructuredActivityNodePopulator());
		elementPopulators.add(new ExpansionNodePopulator());
		elementPopulators.add(new ExpansionRegionPopulator());
		elementPopulators.add(new StructuralFeatureActionPopulator());
		elementPopulators.add(new TestIdentityActionPopulator());
		elementPopulators.add(new ValueSpecificationActionPopulator());
		elementPopulators.add(new LinkActionPopulator());
		elementPopulators.add(new LinkEndDataPopulator());
		elementPopulators.add(new WriteStructuralFeatureActionPopulator());
		elementPopulators.add(new RemoveStructuralFeatureValueActionPopulator());
		elementPopulators.add(new ReadLinkActionPopulator());
		elementPopulators.add(new ReadSelfActionPopulator());
		elementPopulators.add(new ReadStructuralFeatureActionPopulator());
		elementPopulators.add(new LinkEndCreationDataPopulator());
		elementPopulators.add(new LinkEndDestructionDataPopulator());
		elementPopulators.add(new ClearAssociationActionPopulator());
		elementPopulators.add(new ClearStructuralFeatureActionPopulator());
		elementPopulators.add(new CreateObjectActionPopulator());
		elementPopulators.add(new DestroyObjectActionPopulator());
		elementPopulators.add(new AddStructuralFeatureValueActionPopulator());
		elementPopulators.add(new StartClassifierBehaviorActionPopulator());
		elementPopulators.add(new StartObjectBehaviorActionPopulator());
		elementPopulators.add(new ReduceActionPopulator());
		elementPopulators.add(new ReadExtentActionPopulator());
		elementPopulators.add(new ReadIsClassifiedObjectActionPopulator());
		elementPopulators.add(new ReclassifyObjectActionPopulator());
		elementPopulators.add(new AcceptEventActionPopulator());
		elementPopulators.add(new ActionPopulator());
		elementPopulators.add(new CallActionPopulator());
		elementPopulators.add(new InvocationActionPopulator());
		elementPopulators.add(new SendSignalActionPopulator());
		elementPopulators.add(new CallBehaviorActionPopulator());
		elementPopulators.add(new CallOperationActionPopulator());
	}

	public void populate(fUML.Syntax.Classes.Kernel.Element fumlNamedElement,
			org.eclipse.emf.ecore.EModelElement xmofElement) {
		for (IElementPopulator populator : elementPopulators) {
			populator.populate(fumlNamedElement, xmofElement, result);
		}
	}

}