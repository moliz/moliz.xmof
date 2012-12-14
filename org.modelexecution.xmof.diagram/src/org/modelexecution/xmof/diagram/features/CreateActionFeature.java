package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;

public abstract class CreateActionFeature extends AbstractCreateFeature {

	protected static final String CREATE_A = "Create a ";
	private static final String USER_QUESTION = "Enter new action name";

	public CreateActionFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return (getTargetActivity(context) != null || getTargetExpansionRegion(context) != null);
	}

	@Override
	public Object[] create(ICreateContext context) {
		String actionName = ExampleUtil.askString("Create "
				+ getActionTypeName(), USER_QUESTION, getActionTypeName());
		if (actionName == null || actionName.trim().length() == 0) {
			return EMPTY;
		}

		Action action = createAction();
		
		Activity targetActivity = getTargetActivity(context);
		ExpansionRegion targetExpansionRegion = getTargetExpansionRegion(context);
		
		if (targetActivity != null) { // action is created within activity
			targetActivity.getNode().add(action);
		} else if (targetExpansionRegion != null ){ // action is created within expansion region
			targetExpansionRegion.getNode().add(action);
		}
		
		action.setName(actionName);

		addGraphicalRepresentation(context, action);

		return new Object[] { action };
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

	protected abstract String getActionTypeName();

	protected abstract Action createAction();

}