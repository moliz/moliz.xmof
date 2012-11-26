package org.modelexecution.xmof.diagram.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;

public class ActionFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
		if (bo instanceof Action) {
			return true;
		}
		return false;
	}

}
