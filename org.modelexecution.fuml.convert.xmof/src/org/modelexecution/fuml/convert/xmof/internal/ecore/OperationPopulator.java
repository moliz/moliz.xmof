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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;

public class OperationPopulator implements IElementPopulator {

	@Override
	public void populate(Element fumlElement, EModelElement element,
			ConversionResultImpl result) {
		if (!(element instanceof EOperation)
				|| !(fumlElement instanceof Operation)) {
			return;
		}

		EOperation eOperation = (EOperation) element;
		Operation umlOperation = (Operation) fumlElement;

		for (EParameter eParameter : eOperation.getEParameters()) {
			umlOperation.addOwnedParameter((Parameter) result
					.getFUMLElement(eParameter));
		}

		if (eOperation instanceof BehavioredEOperation) {
			BehavioredEOperation behavioredEOperation = (BehavioredEOperation) eOperation;
			for (Behavior xmofBehavior : behavioredEOperation.getMethod()) {
				umlOperation
						.addMethod((fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior) result
								.getFUMLElement(xmofBehavior));
			}
		}
	}
}
