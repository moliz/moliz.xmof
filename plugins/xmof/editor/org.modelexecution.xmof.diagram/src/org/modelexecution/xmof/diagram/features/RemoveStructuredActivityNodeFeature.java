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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class RemoveStructuredActivityNodeFeature extends RemoveActionFeature {

	public RemoveStructuredActivityNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void remove(IRemoveContext context) {
		removeStructuredActivityNodes(context);
		super.remove(context);
	}

	private void removeStructuredActivityNodes(IRemoveContext context) {
		EList<EObject> structuredActivityNodes = context.getPictogramElement()
				.getLink().getBusinessObjects();
		for (EObject eObject : structuredActivityNodes) {
			if (eObject instanceof StructuredActivityNode) {
				StructuredActivityNode structuredActivityNode = (StructuredActivityNode) eObject;
				removeInputPins(structuredActivityNode.getStructuredNodeInput());
				removeOutputPins(structuredActivityNode.getStructuredNodeOutput());
			}
			if (eObject instanceof ExpansionRegion) {
				ExpansionRegion expansionRegion = (ExpansionRegion) eObject;
				removeExpansionNodes(expansionRegion.getOutputElement());
				removeExpansionNodes(expansionRegion.getInputElement());
			}
		}
	}

	private void removeExpansionNodes(EList<ExpansionNode> expansionNodes) {
		for (ExpansionNode expansionNode : new BasicEList<ExpansionNode>(
				expansionNodes)) {
			removeEdges(expansionNode.getIncoming());
			removeEdges(expansionNode.getOutgoing());
			PictogramElement shape = getExpansionNodeShape(expansionNode);
			remove(shape);
		}
	}

	private PictogramElement getExpansionNodeShape(ExpansionNode expansionNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(
				expansionNode);
	}

}
