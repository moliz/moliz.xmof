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
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;

public class DisallowMovePinFeature extends DefaultMoveShapeFeature {

	public DisallowMovePinFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		if (super.canMoveShape(context)) {
			Object object = getBusinessObjectOfContext(context);
			if (object instanceof Pin) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	private Object getBusinessObjectOfContext(IMoveShapeContext context) {
		return getBusinessObjectForPictogramElement(context.getShape());
	}
}
