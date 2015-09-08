package org.modelexecution.fuml.convert.uml2.internal.extrapopulators;

import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.uml2.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;

public class CreateLinkActionPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, org.eclipse.uml2.uml.Element uml2Element, ConversionResultImpl result) {
		if (!(uml2Element instanceof org.eclipse.uml2.uml.CreateLinkAction)
				|| !(fumlElement instanceof fUML.Syntax.Actions.IntermediateActions.CreateLinkAction)) {
			return;
		}

		fUML.Syntax.Actions.IntermediateActions.CreateLinkAction fumlCreateLinkAction = (fUML.Syntax.Actions.IntermediateActions.CreateLinkAction) fumlElement;
		org.eclipse.uml2.uml.CreateLinkAction uml2CreateLinkAction = (org.eclipse.uml2.uml.CreateLinkAction) uml2Element;

		for (org.eclipse.uml2.uml.LinkEndData uml2LinkEndData : uml2CreateLinkAction.getEndData()) {
			if (uml2LinkEndData instanceof org.eclipse.uml2.uml.LinkEndCreationData) {
				org.eclipse.uml2.uml.LinkEndCreationData uml2LinkEndCreationData = (org.eclipse.uml2.uml.LinkEndCreationData) uml2LinkEndData;
				fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData fumlLinkEndCreationData = (fUML.Syntax.Actions.IntermediateActions.LinkEndCreationData) result
						.getFUMLElement(uml2LinkEndCreationData);
				fumlCreateLinkAction.addEndData(fumlLinkEndCreationData);
			}
		}

	}

}
