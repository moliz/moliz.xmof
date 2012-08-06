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

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration.PreferedPosition;
import org.eclipse.papyrus.infra.services.decoration.util.IDecoration;
import org.modelexecution.fumldebug.debugger.papyrus.breakpoints.PapyrusActivityNodeBreakpoint;

/**
 * A decorator for annotating {@link PapyrusActivityNodeBreakpoint breakpoints}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class BreakpointDecorator extends AbstractIconDecorator {

	private static final String BREAKPOINT_DECORATIONID_POSTFIX = "_breakpoint"; //$NON-NLS-1$

	public static String getId(View view) {
		return ViewUtil.getIdStr(view) + BREAKPOINT_DECORATIONID_POSTFIX;
	}

	public BreakpointDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
	}

	@Override
	protected String getDecorationId(View view) {
		return getId(view);
	}

	@Override
	protected void configureDecoration(IDecoration decoration) {
		decoration.setPosition(PreferedPosition.NORTH_EAST);
	}

	@Override
	protected String getToolTipMessage() {
		return "fUML Activity Node Breakpoint";
	}

}
