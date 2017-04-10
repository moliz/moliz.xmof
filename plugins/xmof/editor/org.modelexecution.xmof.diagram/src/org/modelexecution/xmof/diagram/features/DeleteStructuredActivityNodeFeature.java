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
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.modelexecution.xmof.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;

public class DeleteStructuredActivityNodeFeature extends DeleteActionFeature {

	public DeleteStructuredActivityNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		deleteStructuredActivityNodes(context);
		super.delete(context);
	}

	private void deleteStructuredActivityNodes(IDeleteContext context) {
		EList<EObject> structuredNodes = context.getPictogramElement()
				.getLink().getBusinessObjects();
		for (EObject eObject : structuredNodes) {
			if (eObject instanceof StructuredActivityNode) {
				StructuredActivityNode structuredActivityNode = (StructuredActivityNode) eObject;
				deleteInputPins(structuredActivityNode.getStructuredNodeInput());
				deleteOutputPins(structuredActivityNode.getStructuredNodeOutput());
			}
			if (eObject instanceof ExpansionRegion) {
				ExpansionRegion expansionRegion = (ExpansionRegion) eObject;
				deleteExpansionNodes(expansionRegion.getOutputElement());
				deleteExpansionNodes(expansionRegion.getInputElement());
			}
		}
	}

	private void deleteExpansionNodes(EList<ExpansionNode> expansionNodes) {
		for (ExpansionNode expansionNode : new BasicEList<ExpansionNode>(
				expansionNodes)) {
			deleteEdges(expansionNode.getIncoming());
			deleteEdges(expansionNode.getOutgoing());
			PictogramElement shape = getExpansionNodeShape(expansionNode);
			delete(shape);
		}
	}

	private PictogramElement getExpansionNodeShape(ExpansionNode expansionNode) {
		return getFeatureProvider().getPictogramElementForBusinessObject(
				expansionNode);
	}
	
}
