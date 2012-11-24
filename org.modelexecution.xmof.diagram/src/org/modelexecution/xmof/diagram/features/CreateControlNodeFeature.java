package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlNode;

public abstract class CreateControlNodeFeature extends AbstractCreateFeature {

	protected static final String CREATE_A = "Create a ";

	public CreateControlNodeFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return (getTargetActivity(context) != null || getTargetExpansionRegion(context) != null);
	}

	@Override
	public Object[] create(ICreateContext context) {
		ControlNode controlNode = createControlNode();
		
		Activity targetActivity = getTargetActivity(context);
		ExpansionRegion targetExpansionRegion = getTargetExpansionRegion(context);
		
		if(targetActivity != null) { // control node is created within to activity
			targetActivity.getNode().add(controlNode);
		} else if(targetExpansionRegion != null) { // control node is created within to expansion region
			targetExpansionRegion.getNode().add(controlNode);
		}
		
		addGraphicalRepresentation(context, controlNode);

		return new Object[] { controlNode };
	}

	private Activity getTargetActivity(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof Activity) {
				return (Activity) object;
			}
		}
		return null;
	}

	private ExpansionRegion getTargetExpansionRegion(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof ExpansionRegion) {
				return (ExpansionRegion) object;
			}
		}
		return null;
	}

	protected abstract String getControlNodeTypeName();

	protected abstract ControlNode createControlNode();

}