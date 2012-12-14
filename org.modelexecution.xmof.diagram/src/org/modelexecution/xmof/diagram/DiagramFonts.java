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

import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public final class DiagramFonts {

	private static IGaService getGaService() {
		return Graphiti.getGaService();
	}
	
	public static Font getActivityNameFont(Diagram diagram) {
		return getGaService().manageDefaultFont(diagram, false, true);
	}

	public static Font getActionNameFont(Diagram diagram) {
		return getGaService().manageDefaultFont(diagram, false, true);
	}

	public static Font getActionTypeNameFont(Diagram diagram) {
		return getGaService().manageDefaultFont(diagram, false, false);
	}

	public static Font getPinNameFont(Diagram diagram) {
		return getGaService().manageDefaultFont(diagram, false, false);
	}
	
	public static Font getActivityParameterNodeNameFont(Diagram diagram) {
		return getGaService().manageDefaultFont(diagram, false, false);
	}

}
