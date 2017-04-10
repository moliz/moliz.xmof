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
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlNode;

public class DisallowResizeControlNodeFeature extends DefaultResizeShapeFeature {

	public DisallowResizeControlNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		if (super.canResizeShape(context)) {
			Object object = getBusinessObjectOfContext(context);
			if (object instanceof ControlNode) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	private Object getBusinessObjectOfContext(IResizeShapeContext context) {
		return getBusinessObjectForPictogramElement(context
				.getPictogramElement());
	}

}
