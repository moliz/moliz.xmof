/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and generator
 */
package org.modelexecution.fuml.convert.xmof.internal.ecore;

import org.eclipse.emf.ecore.EModelElement;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;

public class DirectedParameterPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof DirectedParameter)
				|| !(fumlElement instanceof Parameter)) {
			return;
		}

		DirectedParameter directedParamter = (DirectedParameter) element;
		Parameter umlParameter = (Parameter) fumlElement;

		umlParameter.direction = getDirection(directedParamter.getDirection());
	}

	private ParameterDirectionKind getDirection(
			org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind direction) {
		switch (direction.getValue()) {
		case org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind.IN_VALUE:
			return ParameterDirectionKind.in;
		case org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind.INOUT_VALUE:
			return ParameterDirectionKind.inout;
		case org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind.OUT_VALUE:
			return ParameterDirectionKind.out;
		case org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind.RETURN_VALUE:
			return ParameterDirectionKind.return_;
		}
		return null;
	}
}
