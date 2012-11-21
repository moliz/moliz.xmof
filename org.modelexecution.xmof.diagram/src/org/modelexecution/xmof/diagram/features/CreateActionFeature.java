package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;

public abstract class CreateActionFeature extends AbstractCreateFeature {

	protected static final String CREATE_A = "Create a ";
	private static final String USER_QUESTION = "Enter new action name";

	public CreateActionFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		String actionName = ExampleUtil.askString("Create "
				+ getActionTypeName(), USER_QUESTION, getActionTypeName());
		if (actionName == null || actionName.trim().length() == 0) {
			return EMPTY;
		}

		Action action = createAction();
		// TODO handle correct resource
		// TODO add action and its pins to activity
		// We add the model element to the resource of the diagram for
		// simplicity's sake. Normally, a customer would use its own
		// model persistence layer for storing the business model separately.
		getDiagram().eResource().getContents().add(action);
		getDiagram().eResource().getContents().addAll(action.getInput());
		getDiagram().eResource().getContents().addAll(action.getOutput());
		action.setName(actionName);

		addGraphicalRepresentation(context, action);

		return new Object[] { action };
	}

	protected abstract String getActionTypeName();

	protected abstract Action createAction();

}