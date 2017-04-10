/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlNode;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesFactory;

public class CreateJoinNodeFeature extends CreateControlNodeFeature {

	protected final static String CONTROL_NODE_TYPE_NAME = "Join Node";
	
	public CreateJoinNodeFeature(IFeatureProvider fp) {
		super(fp, CONTROL_NODE_TYPE_NAME);
	}

	@Override
	protected String getControlNodeTypeName() {
		return CONTROL_NODE_TYPE_NAME;
	}

	@Override
	protected ControlNode createControlNode() {
		return IntermediateActivitiesFactory.eINSTANCE.createJoinNode();
	}

}
