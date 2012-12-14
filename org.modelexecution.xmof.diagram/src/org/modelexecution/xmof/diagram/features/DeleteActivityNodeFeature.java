package org.modelexecution.xmof.diagram.features;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityEdge;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityNode;

public class DeleteActivityNodeFeature extends DefaultDeleteFeature {

	public DeleteActivityNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		deleteActivityNodes(context);
		super.delete(context);
	}

	private void deleteActivityNodes(IDeleteContext context) {
		EList<EObject> nodes = context.getPictogramElement().getLink()
				.getBusinessObjects();
		for (EObject eObject : nodes) {
			if (eObject instanceof ActivityNode) {
				ActivityNode activitynode = (ActivityNode) eObject;
				deleteEdges(activitynode.getIncoming());
				deleteEdges(activitynode.getOutgoing());
			}
		}
	}
	
	protected void deleteEdges(EList<ActivityEdge> edges) {
		for (ActivityEdge edge : new BasicEList<ActivityEdge>(edges)) {
			delete(getEdgeConnection(edge));
		}
	}

	protected PictogramElement getEdgeConnection(ActivityEdge edge) {
		return getFeatureProvider().getPictogramElementForBusinessObject(edge);
	}
	
	protected void delete(PictogramElement pictogramElement) {
		IDeleteContext deleteContext = new DeleteContext(pictogramElement);
		IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(
				deleteContext);
		if (deleteFeature != null) {
			deleteFeature.delete(deleteContext);
		}
	}
}
