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
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;

public class DisallowDeleteActivityFeature extends DefaultDeleteFeature {

	public DisallowDeleteActivityFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canDelete(IDeleteContext context) {
		if (super.canDelete(context)) {
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
	
	private Object getBusinessObjectOfContext(IDeleteContext context) {
		return getBusinessObjectForPictogramElement(context.getPictogramElement());
	}

}
