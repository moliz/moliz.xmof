package org.modelexecution.fuml.convert.xmof.internal.ecore;

import org.eclipse.emf.ecore.EModelElement;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;

import fUML.Syntax.Classes.Kernel.Element;

public class EnumValuePopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof org.modelexecution.xmof.Syntax.Classes.Kernel.EnumValue)
				|| !(fumlElement instanceof fUML.Syntax.Classes.Kernel.InstanceValue)) {
			return;
		}

		fUML.Syntax.Classes.Kernel.InstanceValue fumlNamedElement = (fUML.Syntax.Classes.Kernel.InstanceValue) fumlElement;
		org.modelexecution.xmof.Syntax.Classes.Kernel.EnumValue xmofElement = (org.modelexecution.xmof.Syntax.Classes.Kernel.EnumValue) element;

		org.modelexecution.xmof.Syntax.Classes.Kernel.EEnumLiteralSpecification eEnumLiteralSpecification = xmofElement
				.getEEnumLiteralSpecification();

		fumlNamedElement.instance = (fUML.Syntax.Classes.Kernel.InstanceSpecification) result
				.getFUMLElement(eEnumLiteralSpecification.getEEnumLiteral());
	}

}
