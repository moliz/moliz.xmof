package org.modelexecution.fuml.convert.uml2.internal.extrapopulators;

import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.uml2.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;

public class DestroyLinkActionPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, org.eclipse.uml2.uml.Element uml2Element, ConversionResultImpl result) {
		if (!(uml2Element instanceof org.eclipse.uml2.uml.DestroyLinkAction)
				|| !(fumlElement instanceof fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction)) {
			return;
		}

		fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction fumlDestroyLinkAction = (fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction) fumlElement;
		org.eclipse.uml2.uml.DestroyLinkAction uml2DestroyLinkAction = (org.eclipse.uml2.uml.DestroyLinkAction) uml2Element;

		for (org.eclipse.uml2.uml.LinkEndData uml2LinkEndData : uml2DestroyLinkAction.getEndData()) {
			if (uml2LinkEndData instanceof org.eclipse.uml2.uml.LinkEndDestructionData) {
				org.eclipse.uml2.uml.LinkEndDestructionData uml2LinkEndDestructionData = (org.eclipse.uml2.uml.LinkEndDestructionData) uml2LinkEndData;
				fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData fumlLinkEndDestructionData = (fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionData) result
						.getFUMLElement(uml2LinkEndDestructionData);
				fumlDestroyLinkAction.addEndData(fumlLinkEndDestructionData);
			}
		}
	}

}
