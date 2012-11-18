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

import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsFactory;
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.ValueSpecificationAction;

public class CreateValueSpecificationActionFeature extends
		AbstractCreateFeature {

	private static final String TITLE = "Create Value Specification Action";
	private static final String USER_QUESTION = "Enter new action name";

	public CreateValueSpecificationActionFeature(IFeatureProvider fp) {
		super(fp, "Value Specification Action", "Create a Value Specification Action");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		String actionName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
		if (actionName == null || actionName.trim().length() == 0) {
			return EMPTY;
		}

		ValueSpecificationAction action = IntermediateActionsFactory.eINSTANCE
				.createValueSpecificationAction();
		// TODO handle correct resource
		// We add the model element to the resource of the diagram for
		// simplicity's sake. Normally, a customer would use its own
		// model persistence layer for storing the business model separately.
		getDiagram().eResource().getContents().add(action);
		action.setName(actionName);

		addGraphicalRepresentation(context, action);

		return new Object[] { action };
	}

}
