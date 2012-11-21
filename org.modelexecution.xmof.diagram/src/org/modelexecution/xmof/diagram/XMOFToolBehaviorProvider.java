/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;

public class XMOFToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public XMOFToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public boolean equalsBusinessObjects(Object o1, Object o2) {
		if (o1 instanceof Pin && o2 instanceof Pin) {
			return o1 == o2;
		}
		return super.equalsBusinessObjects(o1, o2);
	}

}
