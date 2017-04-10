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
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Action;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;

public class CreateDestroyObjectActionFeature extends CreateActionFeature {

	protected final static String ACTION_TYPE_NAME = "Destroy Object Action";

	public CreateDestroyObjectActionFeature(IFeatureProvider fp) {
		super(fp, ACTION_TYPE_NAME);
	}

	@Override
	protected String getActionTypeName() {
		return ACTION_TYPE_NAME;
	}

	@Override
	protected Action createAction() {
		return IntermediateActionsFactory.eINSTANCE
				.createDestroyObjectAction();
	}

}