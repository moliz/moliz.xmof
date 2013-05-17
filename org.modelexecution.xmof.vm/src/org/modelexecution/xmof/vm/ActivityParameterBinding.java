/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter;

public class ActivityParameterBinding {

	private ActivityParameterNode paramterNode;
	private List<Object> values;

	public ActivityParameterBinding(ActivityParameterNode paramter,
			List<Object> values) {
		this.paramterNode = paramter;
		this.values = values;
	}

	public ActivityParameterBinding(ActivityParameterNode paramter, Object value) {
		this.paramterNode = paramter;
		this.values = createList(value);
	}

	private List<Object> createList(Object value) {
		List<Object> list = new ArrayList<Object>();
		list.add(value);
		return list;
	}

	public ActivityParameterNode getParamterNode() {
		return paramterNode;
	}

	public DirectedParameter getParameter() {
		return paramterNode.getParameter();
	}

	public List<Object> getValues() {
		return Collections.unmodifiableList(values);
	}

}
