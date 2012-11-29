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
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

public abstract class CreateActivityEdgeFeature extends
		AbstractCreateConnectionFeature {

	protected static final String CREATE_A = "Create a ";

	public CreateActivityEdgeFeature(IFeatureProvider fp, String name) {
		super(fp, name, CREATE_A + name);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// checks if source and target node are in the same activity or
		// structured activity node
		
		ActivityNode source = getActivityNode(context.getSourceAnchor());
		ActivityNode target = getActivityNode(context.getTargetAnchor());		
		
		if (source != null && target != null && source != target) {
			Activity commonActivity = getCommonActivity(source, target);
			StructuredActivityNode commonStructuredActivityNode = getCommonContainerStructuredNode(source, target);			
			return (commonActivity != null || commonStructuredActivityNode != null);
		}
		return false;
	}

	protected StructuredActivityNode getCommonContainerStructuredNode(ActivityNode source, ActivityNode target) {
		StructuredActivityNode sourceStructuredNode = getContainerStructuredActivityNode(source);
		StructuredActivityNode targetStructuredNode = getContainerStructuredActivityNode(target);
		
		if(sourceStructuredNode != null && targetStructuredNode != null && sourceStructuredNode.equals(targetStructuredNode)) {
			return sourceStructuredNode;
		}
		
		return null;
	}
	
	protected Activity getCommonActivity(ActivityNode source, ActivityNode target) {
		Activity sourceActivity = getContainerActivity(source);
		Activity targetActivity = getContainerActivity(target);
		
		if(sourceActivity != null && targetActivity != null && sourceActivity.equals(targetActivity)) {
			return sourceActivity;
		}
		
		return null;
	}
	
	protected ActivityNode getActivityNode(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof ActivityNode) {
				return (ActivityNode) object;
			}
		}
		return null;
	}

	protected Activity getContainerActivity(ActivityNode node) {
		Activity activity = null;
		if (node != null) {
			activity = node.getActivity();
			if(node instanceof Pin) {
				if(node.eContainer() instanceof Action) {
					activity = ((Action)node.eContainer()).getActivity();
				}
			}
		}
		return activity;
	}
	
	protected StructuredActivityNode getContainerStructuredActivityNode(ActivityNode node) {
		StructuredActivityNode structuredNode = null;
		if (node != null) {
			structuredNode = node.getInStructuredNode();
			if(node instanceof Pin) {
				if(node.eContainer() instanceof Action) {
					structuredNode = ((Action)node.eContainer()).getInStructuredNode();
				}
			} else if(node instanceof ExpansionNode) {
				structuredNode = ((ExpansionNode)node).getRegionAsInput();
				if(structuredNode == null) {
					structuredNode = ((ExpansionNode)node).getRegionAsOutput();
				}
			}				
		}
		return structuredNode;
	}
}
