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
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.impl.DefaultMoveAnchorFeature;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;

public class DisallowMovePinFeature extends DefaultMoveAnchorFeature {

	public DisallowMovePinFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveAnchor(IMoveAnchorContext context) {
		if (super.canMoveAnchor(context)) {
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

	private Object getBusinessObjectOfContext(IMoveAnchorContext context) {
		return getBusinessObjectForPictogramElement(context.getAnchor());
	}

}
