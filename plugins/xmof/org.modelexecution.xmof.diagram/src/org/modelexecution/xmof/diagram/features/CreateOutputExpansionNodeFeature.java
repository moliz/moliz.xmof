package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesFactory;

public class CreateOutputExpansionNodeFeature extends CreateExpansionNodeFeature {

	protected final static String PIN_TYPE_NAME = "Output Expansion Node";
	
	public CreateOutputExpansionNodeFeature(IFeatureProvider fp) {
		super(fp, PIN_TYPE_NAME);
	}

	@Override
	public ExpansionNode createExpansionNode(ICreateContext context) {
		ExpansionNode expansionNode = ExtraStructuredActivitiesFactory.eINSTANCE.createExpansionNode();
		
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);		
		expansionRegion.getOutputElement().add(expansionNode);

		return expansionNode;
	}
		
}