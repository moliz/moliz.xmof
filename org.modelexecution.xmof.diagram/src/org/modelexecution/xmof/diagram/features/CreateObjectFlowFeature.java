/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.InputPin;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.OutputPin;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.DecisionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ForkNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.JoinNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.MergeNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ObjectFlow;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind;

public class CreateObjectFlowFeature extends CreateActivityEdgeFeature {

	public CreateObjectFlowFeature(IFeatureProvider fp) {
		super(fp, "Object Flow");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {				
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		if (sourceAnchor == null || targetAnchor == null) {
			return false;
		}	
		
		ActivityNode sourceobject = getActivityNode(sourceAnchor);
		ActivityNode targetobject = getActivityNode(targetAnchor);

		if (sourceobject == null || targetobject == null) {
			return false;
		}
		
		Activity commonActivity = getCommonActivity(sourceobject, targetobject);
		StructuredActivityNode commonStructuredActivityNode = getCommonContainerStructuredNode(sourceobject, targetobject);
		
		boolean containerok = false;
		
		if(!(commonActivity == null && commonStructuredActivityNode == null)) {
			containerok = true;
		} else {
			if(sourceobject instanceof InputPin) {
				if(targetobject instanceof InputPin) {
					if(sourceobject.eContainer().equals(targetobject.eContainer().eContainer()) && sourceobject.eContainer() instanceof ExpansionRegion) {
						containerok = true;
					}
				} else {
					if(sourceobject.eContainer().equals(targetobject.eContainer()) && sourceobject.eContainer() instanceof ExpansionRegion) {
						containerok = true;
					}
				}
			} 
		}

		if(!containerok) {
			return false;
		}
		
		boolean sourceok = false;
		boolean targetok = false;

		if (sourceobject instanceof DecisionNode
				|| sourceobject instanceof MergeNode
				|| sourceobject instanceof ForkNode
				|| sourceobject instanceof JoinNode
				|| sourceobject instanceof OutputPin) {
			sourceok = true;
		} else if (sourceobject instanceof ExpansionNode) {
			sourceok = isOutputExpansionNode((ExpansionNode) sourceobject) || isInputExpansionNode((ExpansionNode) sourceobject);
		} else if (sourceobject instanceof ActivityParameterNode) {
			sourceok = isInputActivityParameterNode((ActivityParameterNode)sourceobject);
		} else if (sourceobject instanceof InputPin) {
			sourceok = (((InputPin) sourceobject).eContainer() instanceof ExpansionRegion);				
		}

		if (targetobject instanceof DecisionNode
				|| targetobject instanceof MergeNode
				|| targetobject instanceof ForkNode
				|| targetobject instanceof JoinNode
				|| targetobject instanceof InputPin) {
			targetok = true;
		} else if (targetobject instanceof ExpansionNode) {
			targetok = isInputExpansionNode((ExpansionNode) targetobject) || isOutputExpansionNode(targetobject);
		} else if (targetobject instanceof ActivityParameterNode) {
			targetok = isOutputActivityParameterNode((ActivityParameterNode)targetobject);
		}

		return sourceok && targetok;
	}

	private boolean isInputActivityParameterNode(
			Object activityParameterNode) {
		if(activityParameterNode == null || !(activityParameterNode instanceof ActivityParameterNode)) {
			return false;
		}			
		DirectedParameter parameter = ((ActivityParameterNode)activityParameterNode).getParameter();
		return parameter.getDirection() == ParameterDirectionKind.IN || parameter.getDirection() == ParameterDirectionKind.INOUT; 
	}
	
	private boolean isOutputActivityParameterNode(
			ActivityParameterNode activityParameterNode) {
		DirectedParameter parameter = activityParameterNode.getParameter();
		return parameter.getDirection() == ParameterDirectionKind.RETURN || parameter.getDirection() == ParameterDirectionKind.INOUT || parameter.getDirection() == ParameterDirectionKind.OUT; 
	}

	private boolean isInputExpansionNode(Object object) {
		if (object == null || !(object instanceof ExpansionNode)) {
			return false;
		}
		ExpansionNode expansionNode = (ExpansionNode)object;
		ExpansionRegion expansionRegion = (ExpansionRegion) expansionNode
				.getRegionAsInput();
		
		if (expansionRegion != null) {
			return true;
		}

		return false;
	}

	private boolean isOutputExpansionNode(Object object) {
		if (object == null || !(object instanceof ExpansionNode)) {
			return false;
		}
		ExpansionNode expansionNode = (ExpansionNode) object;
		ExpansionRegion expansionRegion = (ExpansionRegion) expansionNode
				.getRegionAsOutput();
		
		if(expansionRegion != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		ActivityNode source = (ActivityNode) getBusinessObjectForPictogramElement(context
				.getSourceAnchor().getParent());
		ActivityNode target = (ActivityNode) getBusinessObjectForPictogramElement(context
				.getTargetAnchor().getParent());

		if (source != null && target != null) {
			newConnection = createObjectFlow(context, source, target);
		}
		return newConnection;
	}

	private Connection createObjectFlow(ICreateConnectionContext context,
			ActivityNode source, ActivityNode target) {
		ObjectFlow objectFlow = createObjectFlow(source, target);
		AddConnectionContext addContext = new AddConnectionContext(
				context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(objectFlow);
		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	private ObjectFlow createObjectFlow(ActivityNode source, ActivityNode target) {
		ObjectFlow objectFlow = IntermediateActivitiesFactory.eINSTANCE
				.createObjectFlow();
		objectFlow.setSource(source);
		objectFlow.setTarget(target);
		source.getOutgoing().add(objectFlow);
		target.getIncoming().add(objectFlow);
		
		Activity commonActivity = getCommonActivity(source, target);
		StructuredActivityNode commonStructuredActivityNode = getCommonContainerStructuredNode(source, target);

		if(commonStructuredActivityNode != null) {
			commonStructuredActivityNode.getEdge().add(objectFlow);
		} else if(commonActivity != null) {
			commonActivity.getEdge().add(objectFlow);
		} else {
			if(source instanceof InputPin && source.eContainer() instanceof ExpansionRegion) {
				((ExpansionRegion)source.eContainer()).getEdge().add(objectFlow);
			}
		}
		
		return objectFlow;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		if (sourceAnchor == null) {
			return false;
		}

		Object object = getBusinessObjectForPictogramElement(sourceAnchor
				.getParent());

		if (object instanceof OutputPin || object instanceof MergeNode
				|| object instanceof DecisionNode || object instanceof ForkNode
				|| object instanceof JoinNode || isOutputExpansionNode(object) || isInputExpansionNode(object) || isInputActivityParameterNode(object)) {
			return true;
		} else if(object instanceof InputPin) {			
			InputPin pin = (InputPin) object;
			if(pin.eContainer() instanceof ExpansionRegion) {
				return true;
			}
		}

		return false;
	}

}
