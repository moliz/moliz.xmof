/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.decorations;

import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration.PreferedPosition;
import org.eclipse.papyrus.infra.services.decoration.util.IDecoration;
import org.modelexecution.fumldebug.debugger.papyrus.presentation.PapyrusDebugPresentation;

/**
 * A decorator for annotating debug information.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class DebugDecorator extends AbstractIconDecorator {

	public DebugDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
	}

	@Override
	protected String getDecorationId(View view) {
		return PapyrusDebugPresentation.getCurrentNodeDecorationId(view);
	}

	@Override
	protected void configureDecoration(IDecoration decoration) {
		decoration.setPosition(PreferedPosition.NORTH_WEST);
	}

	@Override
	protected String getToolTipMessage() {
		return "Currently enabled activity node.";
	}

}
