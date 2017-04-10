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
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;

public class DisallowRemoveActivityFeature extends DefaultRemoveFeature {

	public DisallowRemoveActivityFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canRemove(IRemoveContext context) {
		if (super.canRemove(context)) {
			Object object = getBusinessObjectOfContext(context);
			if (object instanceof Activity) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	private Object getBusinessObjectOfContext(IRemoveContext context) {
		return getBusinessObjectForPictogramElement(context.getPictogramElement());
	}

}
