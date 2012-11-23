package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;

public abstract class CreateExpansionNodeFeature extends AbstractCreateFeature {

	protected static final String CREATE_A = "Create a ";
	
	public CreateExpansionNodeFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return getTargetExpansionRegion(context) != null;
	}
	
	@Override
	public Object[] create(ICreateContext context) {
		ExpansionNode expansionNode = createExpansionNode(context);
		
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);		

		Activity activity = expansionRegion.getActivity();
		activity.getNode().add(expansionNode);
				
		addGraphicalRepresentation(context, expansionNode);

		return new Object[] { expansionNode };
	}
			
	protected ExpansionRegion getTargetExpansionRegion(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof ExpansionRegion) {
				return (ExpansionRegion) object;
			}
		}
		return null;
	}
	
	protected abstract ExpansionNode createExpansionNode(ICreateContext context);

}