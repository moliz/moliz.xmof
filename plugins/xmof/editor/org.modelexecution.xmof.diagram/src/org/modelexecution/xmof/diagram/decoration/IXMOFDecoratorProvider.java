package org.modelexecution.xmof.diagram.decoration;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.IDecorator;

public interface IXMOFDecoratorProvider {

	public IDecorator[] getDecorators(PictogramElement pe);

}
