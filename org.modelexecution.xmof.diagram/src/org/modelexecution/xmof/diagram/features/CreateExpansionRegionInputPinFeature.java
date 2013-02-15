package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExtraStructuredActivitiesFactory;

public class CreateExpansionRegionInputPinFeature extends AbstractCreateFeature {

	protected static final String CREATE_A = "Create a ";
	protected final static String PIN_TYPE_NAME = "Expansion Region Input Pin";
	
	public CreateExpansionRegionInputPinFeature(IFeatureProvider fp) {
		super(fp, PIN_TYPE_NAME, CREATE_A + PIN_TYPE_NAME);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return getTargetExpansionRegion(context) != null;
	}
	
	@Override
	public Object[] create(ICreateContext context) {
		InputPin inputPin = createInputPin(context);
		
		addGraphicalRepresentation(context, inputPin);

		return new Object[] { inputPin };
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
	
	private InputPin createInputPin(ICreateContext context) {
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);
		
		InputPin inputPin = BasicActionsFactory.eINSTANCE.createInputPin();
		inputPin.setName("input" + (expansionRegion.getStructuredNodeInput().size() + 1));
		
		expansionRegion.getStructuredNodeInput().add(inputPin);
		
		return inputPin;
	}
	
	public ExpansionNode createExpansionNode(ICreateContext context) {
		ExpansionNode expansionNode = ExtraStructuredActivitiesFactory.eINSTANCE.createExpansionNode();
		
		ExpansionRegion expansionRegion = getTargetExpansionRegion(context);		
		expansionRegion.getInputElement().add(expansionNode);
				
		return expansionNode;
	}

}